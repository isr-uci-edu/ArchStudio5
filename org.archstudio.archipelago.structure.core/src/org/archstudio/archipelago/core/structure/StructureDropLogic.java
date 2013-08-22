package org.archstudio.archipelago.core.structure;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.util.AbstractTreeDropLogic;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasMutableWorld;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.myx.fw.Services;
import org.archstudio.swtutils.LocalSelectionTransfer;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl.bna.utils.XArchADTOperations;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.graphics.Point;

public class StructureDropLogic extends AbstractTreeDropLogic {

	protected final IXArchADT xarch;

	public StructureDropLogic(Services services, ObjRef documentRootRef) {
		super(services, documentRootRef);
		this.xarch = services.get(IXArchADT.class);
	}

	@Override
	protected boolean acceptDrop(IBNAView view, DropTargetEvent event, Iterable<IThing> ts, ICoordinate location,
			Object data) {
		IHasMutableWorld t = Assemblies.getThingOfType(getBNAModel(), firstOrNull(ts), IHasMutableWorld.class);
		if (t != null) {
			IThing p = Assemblies.getThingWithProperty(getBNAModel(), t, IHasObjRef.OBJREF_KEY);
			if (p != null) {
				if (XadlUtils.isInstanceOf(xarch, p.get(IHasObjRef.OBJREF_KEY), Structure_3_0Package.Literals.BRICK)) {
					if (data == null) {
						return true;
					}
					else if (data instanceof ObjRef) {
						if (XadlUtils.isInstanceOf(xarch, (ObjRef) data, Structure_3_0Package.Literals.STRUCTURE)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public void drop(IBNAView view, DropTargetEvent event, List<IThing> ts, ICoordinate location) {
		if (pulser != null) {
			view.getBNAWorld().getBNAModel().removeThing(pulser);
			pulser = null;
		}

		ObjRef outerRef = null;
		if (acceptDrop(view, event, ts, location)) {
			IHasMutableWorld t = Assemblies.getThingOfType(getBNAModel(), firstOrNull(ts), IHasMutableWorld.class);
			if (t != null) {
				IThing p = Assemblies.getThingWithProperty(getBNAModel(), t, IHasObjRef.OBJREF_KEY);
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
					ObjRef structureRef = (ObjRef) data;

					// Set up a substructure if one doesn't already exist.
					ObjRef subStructureRef = (ObjRef) xarch.get(outerRef, "subStructure");
					if (subStructureRef == null) {
						subStructureRef = XadlUtils.create(xarch, Structure_3_0Package.Literals.SUB_STRUCTURE);
						xarch.set(subStructureRef, "id", UIDGenerator.generateUID("subStructure"));
						xarch.set(outerRef, "subStructure", subStructureRef);
					}
					XArchADTOperations.set("Assign Substructure", xarch, subStructureRef, "innerStructureLink",
							structureRef);

					Point worldPoint = location.getWorldPoint();
					ArchipelagoUtils.showUserNotification(view.getBNAWorld(), "Substructure Assigned", worldPoint.x,
							worldPoint.y);
				}
			}
		}
	}

}
