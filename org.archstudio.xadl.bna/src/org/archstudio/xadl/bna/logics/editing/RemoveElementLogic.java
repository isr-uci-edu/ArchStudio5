package org.archstudio.xadl.bna.logics.editing;

import java.util.List;
import java.util.Set;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.editing.BNAOperations;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class RemoveElementLogic extends AbstractThingLogic implements IBNAMenuListener {

	final protected IXArchADT xarch;

	public RemoveElementLogic(IXArchADT xarch) {
		this.xarch = xarch;
	}

	public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager m) {
		final Set<ObjRef> objRefs = Sets.newHashSet();
		for (IThing thing : BNAUtils.getSelectedThings(view.getBNAWorld().getBNAModel())) {
			ObjRef objRef = thing.get(IHasObjRef.OBJREF_KEY);
			if (objRef != null) {
				objRefs.add(objRef);
			}
		}
		if (things.size() > 0) {
			ObjRef objRef = things.get(0).get(IHasObjRef.OBJREF_KEY);
			if (objRef != null) {
				objRefs.add(objRef);
			}
		}
		if (objRefs.size() > 0) {
			m.add(new Action(objRefs.size() == 1 ? "Remove" : "Remove " + objRefs.size() + " Elements") {

				public void run() {
					final List<Runnable> undo = Lists.newArrayList();
					final List<Runnable> redo = Lists.newArrayList();
					for (final ObjRef objRef : objRefs) {
						final ObjRef parentRef = xarch.getParent(objRef);
						if (parentRef != null) {
							IXArchADTTypeMetadata type = xarch.getTypeMetadata(parentRef);
							final String elementName = xarch.getContainingFeatureName(objRef);
							switch (type.getFeatures().get(elementName).getType()) {
							case ATTRIBUTE:
							case ELEMENT_SINGLE:
								undo.add(new Runnable() {
									public void run() {
										xarch.set(parentRef, elementName, objRef);
									}
								});
								redo.add(new Runnable() {
									public void run() {
										xarch.clear(parentRef, elementName);
									}
								});
								break;
							case ELEMENT_MULTIPLE:
								undo.add(new Runnable() {
									public void run() {
										xarch.add(parentRef, elementName, objRef);
									}
								});
								redo.add(new Runnable() {
									public void run() {
										xarch.remove(parentRef, elementName, objRef);
									}
								});
								break;
							}
						}
					}

					BNAOperations.runnable("Remove", new Runnable() {

						public void run() {
							for (Runnable r : undo) {
								r.run();
							}
						}
					}, new Runnable() {

						public void run() {
							for (Runnable r : redo) {
								r.run();
							}
						}
					}, true);
				}
			});
		}
	}
}
