package org.archstudio.archipelago.core.structure;

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
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.myx.fw.Services;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl.bna.utils.XArchADTOperations;
import org.archstudio.xadl3.statechart_1_0.Statechart;
import org.archstudio.xadl3.statechart_1_0.StatechartSpecification;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xadl3.structure_3_0.Brick;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.swt.graphics.Point;

public class StructureDropLogic extends AbstractTreeDropLogic {

	protected final IXArchADT xarch;

	public StructureDropLogic(IBNAWorld world, Services services, ObjRef documentRootRef) {
		super(world, services, documentRootRef);
		this.xarch = services.get(IXArchADT.class);
	}

	protected ObjRef getBrickRef(IBNAView view, Iterable<IThing> things) {
		IHasWorld w = Assemblies.getThingOfType(model, firstOrNull(things), IHasWorld.class);
		IThing o = Assemblies.getThingWithProperty(model, w, IHasObjRef.OBJREF_KEY);
		if (o != null) {
			ObjRef brickRef = o.get(IHasObjRef.OBJREF_KEY);
			if (XadlUtils.isInstanceOf(xarch, brickRef, Structure_3_0Package.Literals.BRICK)) {
				return brickRef;
			}
		}
		return null;
	}

	@Override
	protected boolean acceptDrop(IBNAView view, DNDType type, DNDData data, Iterable<IThing> things,
			ICoordinate location) {
		{
			ObjRef structureRef = data.getData(ObjRef.class);
			if (XadlUtils.isInstanceOf(xarch, structureRef, Structure_3_0Package.Literals.STRUCTURE)) {
				ObjRef brickRef = getBrickRef(view, things);
				if (brickRef != null) {
					data.setDropType(DNDActionType.LINK);
					return true;
				}
			}
		}
		{
			ObjRef statechartRef = data.getData(ObjRef.class);
			if (XadlUtils.isInstanceOf(xarch, statechartRef, Statechart_1_0Package.Literals.STATECHART)) {
				ObjRef brickRef = getBrickRef(view, things);
				if (brickRef != null) {
					data.setDropType(DNDActionType.LINK);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected void doDrop(IBNAView view, DNDType type, DNDData data, Iterable<IThing> things, ICoordinate location) {
		{
			ObjRef structureRef = data.getData(ObjRef.class);
			if (XadlUtils.isInstanceOf(xarch, structureRef, Structure_3_0Package.Literals.STRUCTURE)) {
				ObjRef brickRef = getBrickRef(view, things);
				if (brickRef != null) {
					// Set up a substructure if one doesn't already exist.
					XArchADTOperations xarch = new XArchADTOperations(this.xarch);
					ObjRef subStructureRef = (ObjRef) xarch.get(brickRef, "subStructure");
					if (subStructureRef == null) {
						subStructureRef = XadlUtils.create(xarch, Structure_3_0Package.Literals.SUB_STRUCTURE);
						xarch.set(subStructureRef, "id", UIDGenerator.generateUID("subStructure"));
						xarch.set(brickRef, "subStructure", subStructureRef);
					}
					xarch.set(subStructureRef, "innerStructureLink", structureRef);
					xarch.done("Assign Substructure");

					Point worldPoint = location.getWorldPoint();
					ArchipelagoUtils.showUserNotification(world, "Substructure Assigned", worldPoint.x, worldPoint.y);
				}
			}
		}
		{
			Statechart statechart = SystemUtils.castOrNull(XArchADTProxy.proxy(xarch, data.getData(ObjRef.class)),
					Statechart.class);
			if (statechart != null) {
				Brick brick = XArchADTProxy.proxy(xarch, getBrickRef(view, things));
				if (brick != null) {
					// Set up a statechart specification if one doesn't already exist.
					XArchADTOperations xarch = new XArchADTOperations(this.xarch);
					StatechartSpecification statechartSpec = XadlUtils.createExt(xarch, brick,
							Statechart_1_0Package.Literals.STATECHART_SPECIFICATION);
					if (statechartSpec.getId() == null) {
						statechartSpec.setId(UIDGenerator.generateUID());
					}
					statechartSpec.setStatechart(statechart);
					xarch.done("Assign Statechart");

					Point worldPoint = location.getWorldPoint();
					ArchipelagoUtils.showUserNotification(world, "Statechart Assigned", worldPoint.x, worldPoint.y);
				}
			}
		}
	}
}
