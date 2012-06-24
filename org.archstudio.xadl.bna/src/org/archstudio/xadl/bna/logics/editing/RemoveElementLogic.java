package org.archstudio.xadl.bna.logics.editing;

import java.util.List;
import java.util.Set;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;

import com.google.common.collect.Sets;

public class RemoveElementLogic extends AbstractThingLogic implements IBNAMenuListener {

	final protected IXArchADT xarch;

	public RemoveElementLogic(IXArchADT xarch) {
		this.xarch = xarch;
	}

	@Override
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
				@Override
				public void run() {
					for (ObjRef objRef : objRefs) {
						XadlUtils.remove(xarch, objRef);
					}
				}
			});
		}
	}
}
