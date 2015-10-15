package org.archstudio.xadl.bna.logics.editing;

import java.util.List;
import java.util.Set;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.editing.BNAOperations;
import org.archstudio.bna.ui.IBNAMenuListener2;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAAction;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
import org.archstudio.utils.resources.Resources;
import org.archstudio.utils.resources.swt.ImageUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class RemoveElementLogic extends AbstractThingLogic implements IBNAMenuListener2 {

	final protected IXArchADT xarch;

	public RemoveElementLogic(IBNAWorld world, IXArchADT xarch) {
		super(world);
		this.xarch = xarch;
	}

	@Override
	public void fillMenu(IBNAView view, ICoordinate location, ThingsAtLocation thingsAtLocation, IMenuManager menu) {
		BNAUtils.checkLock();
		if (thingsAtLocation.getViewAtLocation() != null) {
			return;
		}
		if (thingsAtLocation.getThingAtLocation() != null) {
			final Set<ObjRef> objRefs = Sets.newHashSet();
			for (IThing thing : BNAUtils.getSelectedThings(view.getBNAWorld().getBNAModel())) {
				IThing objRefThing = Assemblies.getThingWithProperty(model, thing, IHasObjRef.OBJREF_KEY);
				if (objRefThing != null) {
					ObjRef objRef = thing.get(IHasObjRef.OBJREF_KEY);
					if (objRef != null) {
						objRefs.add(objRef);
					}
				}
			}
			IThing objRefThing =
					Assemblies.getThingWithProperty(model, thingsAtLocation.getThing(), IHasObjRef.OBJREF_KEY);
			if (objRefThing != null) {
				ObjRef objRef = objRefThing.get(IHasObjRef.OBJREF_KEY);
				if (objRef != null) {
					objRefs.add(objRef);
				}
			}
			if (objRefs.size() > 0) {
				menu.add(new BNAAction(objRefs.size() == 1 ? "Delete" : "Delete " + objRefs.size() + " Elements") {

					@Override
					public void runWithLock() {
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
										@Override
										public void run() {
											xarch.set(parentRef, elementName, objRef);
										}
									});
									redo.add(new Runnable() {
										@Override
										public void run() {
											xarch.clear(parentRef, elementName);
										}
									});
									break;
								case ELEMENT_MULTIPLE:
									undo.add(new Runnable() {
										@Override
										public void run() {
											xarch.add(parentRef, elementName, objRef);
										}
									});
									redo.add(new Runnable() {
										@Override
										public void run() {
											xarch.remove(parentRef, elementName, objRef);
										}
									});
									break;
								}
							}
						}

						BNAOperations.runnable("Delete", new Runnable() {

							@Override
							public void run() {
								for (Runnable r : undo) {
									r.run();
								}
							}
						}, new Runnable() {

							@Override
							public void run() {
								for (Runnable r : redo) {
									r.run();
								}
							}
						}, true);
					}

					@Override
					public ImageDescriptor getImageDescriptor() {
						return ImageUtils
								.toImageDescriptor(ImageUtils.getImage(Display.getDefault(), Resources.DELETE_ICON_16));
					}
				});
			}
		}
	}
}
