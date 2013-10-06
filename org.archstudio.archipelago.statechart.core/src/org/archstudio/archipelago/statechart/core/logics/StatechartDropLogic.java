package org.archstudio.archipelago.statechart.core.logics;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.util.AbstractTreeDropLogic;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.DNDActionType;
import org.archstudio.bna.constants.DNDData;
import org.archstudio.bna.constants.DNDType;
import org.archstudio.bna.facets.IHasMutableWorld;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.myx.fw.Services;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.graphics.Point;

public class StatechartDropLogic extends AbstractTreeDropLogic {

	protected final IXArchADT xarch;

	public StatechartDropLogic(IBNAWorld world, Services services, ObjRef documentRootRef) {
		super(world, services, documentRootRef);
		this.xarch = services.get(IXArchADT.class);
	}

	protected ObjRef getStateRef(IBNAView view, Iterable<IThing> things) {
		IHasMutableWorld w = Assemblies.getThingOfType(model, firstOrNull(things), IHasMutableWorld.class);
		IThing o = Assemblies.getThingWithProperty(model, w, IHasObjRef.OBJREF_KEY);
		if (o != null) {
			ObjRef stateRef = o.get(IHasObjRef.OBJREF_KEY);
			if (XadlUtils.isInstanceOf(xarch, stateRef, Statechart_1_0Package.Literals.STATE)) {
				return stateRef;
			}
		}
		return null;
	}

	@Override
	protected boolean acceptDrop(IBNAView view, DNDType type, DNDData data, Iterable<IThing> things,
			ICoordinate location) {
		ObjRef statechartRef = data.getData(ObjRef.class);
		if (XadlUtils.isInstanceOf(xarch, statechartRef, Statechart_1_0Package.Literals.STATECHART)) {
			ObjRef stateRef = getStateRef(view, things);
			if (stateRef != null) {
				data.setDropType(DNDActionType.LINK);
				return true;
			}
		}
		return false;
	}

	@Override
	protected void doDrop(IBNAView view, DNDType type, DNDData data, Iterable<IThing> things, ICoordinate location) {
		ObjRef statechartRef = data.getData(ObjRef.class);
		if (XadlUtils.isInstanceOf(xarch, statechartRef, Statechart_1_0Package.Literals.STATECHART)) {
			ObjRef stateRef = getStateRef(view, things);
			if (stateRef != null) {
				// Set up a substructure if one doesn't already exist.
				ObjRef subStatechartRef = (ObjRef) xarch.get(stateRef, "subStatechart");
				if (subStatechartRef == null) {
					subStatechartRef = XadlUtils.create(xarch, Statechart_1_0Package.Literals.SUB_STATECHART);
					xarch.set(subStatechartRef, "id", UIDGenerator.generateUID("subStatechart"));
					xarch.set(stateRef, "subStatechart", subStatechartRef);
				}
				xarch.set(subStatechartRef, "innerStatechart", statechartRef);

				Point worldPoint = location.getWorldPoint();
				ArchipelagoUtils.showUserNotification(view.getBNAWorld(), "Substatechart Assigned", worldPoint.x,
						worldPoint.y);
			}
		}
	}

}
