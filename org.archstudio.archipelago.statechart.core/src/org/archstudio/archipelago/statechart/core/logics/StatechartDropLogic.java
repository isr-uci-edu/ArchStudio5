package org.archstudio.archipelago.statechart.core.logics;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.util.AbstractTreeDropLogic;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasMutableWorld;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.myx.fw.Services;
import org.archstudio.swtutils.LocalSelectionTransfer;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.graphics.Point;

public class StatechartDropLogic extends AbstractTreeDropLogic {

	protected final IXArchADT xarch;

	public StatechartDropLogic(IBNAWorld world, Services services, ObjRef documentRootRef) {
		super(world, services, documentRootRef);
		this.xarch = services.get(IXArchADT.class);
	}

	@Override
	protected boolean acceptDrop(IBNAView view, DropTargetEvent event, List<IThing> ts, ICoordinate location,
			Object data) {
		IHasMutableWorld t = Assemblies.getThingOfType(model, firstOrNull(ts), IHasMutableWorld.class);
		if (t != null) {
			IThing p = Assemblies.getThingWithProperty(model, t, IHasObjRef.OBJREF_KEY);
			if (p != null) {
				if (XadlUtils.isInstanceOf(xarch, p.get(IHasObjRef.OBJREF_KEY), Statechart_1_0Package.Literals.STATE)) {
					if (data == null) {
						return true;
					}
					else if (data instanceof ObjRef) {
						if (XadlUtils.isInstanceOf(xarch, (ObjRef) data, Statechart_1_0Package.Literals.STATECHART)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	synchronized public void drop(IBNAView view, DropTargetEvent event, List<IThing> ts, ICoordinate location) {
		if (pulser != null) {
			view.getBNAWorld().getBNAModel().removeThing(pulser);
			pulser = null;
		}

		ObjRef outerRef = null;
		if (acceptDrop(view, event, ts, location)) {
			IHasMutableWorld t = Assemblies.getThingOfType(model, firstOrNull(ts), IHasMutableWorld.class);
			if (t != null) {
				IThing p = Assemblies.getThingWithProperty(model, t, IHasObjRef.OBJREF_KEY);
				if (p != null) {
					outerRef = p.get(IHasObjRef.OBJREF_KEY);
				}
			}
		}

		if (outerRef != null) {
			LocalSelectionTransfer transfer = LocalSelectionTransfer.getInstance();
			if (transfer.isSupportedType(event.currentDataType)) {
				Object selection = transfer.nativeToJava(event.currentDataType);
				Object data = getDataFromSelection(selection);
				if (data != null && data instanceof ObjRef) {
					ObjRef statechartRef = (ObjRef) data;

					// Set up a substructure if one doesn't already exist.
					ObjRef subStatechartRef = (ObjRef) xarch.get(outerRef, "subStatechart");
					if (subStatechartRef == null) {
						subStatechartRef = XadlUtils.create(xarch, Statechart_1_0Package.Literals.SUB_STATECHART);
						xarch.set(subStatechartRef, "id", UIDGenerator.generateUID("subStatechart"));
						xarch.set(outerRef, "subStatechart", subStatechartRef);
					}
					xarch.set(subStatechartRef, "innerStatechart", statechartRef);

					Point worldPoint = location.getWorldPoint();
					ArchipelagoUtils.showUserNotification(view.getBNAWorld(), "Substatechart Assigned", worldPoint.x,
							worldPoint.y);
				}
			}
		}
	}

}
