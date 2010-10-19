package org.archstudio.archipelago.core.structure;

import org.eclipse.swt.dnd.DropTargetEvent;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.util.AbstractTreeDropLogic;
import org.archstudio.swtutils.LocalSelectionTransfer;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.things.glass.BoxGlassThing;

public class StructureDropLogic extends AbstractTreeDropLogic{
	public StructureDropLogic(ArchipelagoServices services, ObjRef documentRootRef){
		super(services, documentRootRef);
	}
	
	protected boolean acceptDrop(IBNAView view, DropTargetEvent event, IThing t, int worldX, int worldY, Object data){
		if(t == null){
			//This is a drop on nothing...just return.
			return false;
		}
		else if(t instanceof BoxGlassThing){
			//Drop on a glass thing
			
			IThing pt = view.getWorld().getBNAModel().getParentThing(t);
			if(pt != null){
				if(StructureMapper.isComponentAssemblyRootThing(pt)){
					if(data == null){
						return true;
					}
					else if(data instanceof ObjRef){
						if(AS.xarch.isInstanceOf((ObjRef)data, "edu.uci.isr.xadl3.structure_3_0.Structure")){
							return true;
						}
					}
				}
				else if(StructureMapper.isConnectorAssemblyRootThing(pt)){
					if(data == null){
						return true;
					}
					else if(data instanceof ObjRef){
						if(AS.xarch.isInstanceOf((ObjRef)data, "edu.uci.isr.xadl3.structure_3_0.Structure")){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public void drop(IBNAView view, DropTargetEvent event, IThing t, int worldX, int worldY){
		if(pulser != null){
			view.getWorld().getBNAModel().removeThing(pulser);
			pulser = null;
		}
		
		if(!acceptDrop(view, event, t, worldX, worldY)){
			return;
		}
		
		if(t == null){
			throw new IllegalArgumentException("Drop on null - this shouldn't happen.");
		}
		
		ObjRef outerRef = null;
		if(t != null){
			if(t instanceof BoxGlassThing){
				IThing pt = view.getWorld().getBNAModel().getParentThing(t);
				if(pt != null){
					String xArchID = pt.getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
					if(xArchID != null){
						outerRef = AS.xarch.getByID(documentRootRef, xArchID);
					}
				}
			}
		}
		
		if(outerRef != null){
			LocalSelectionTransfer transfer = LocalSelectionTransfer.getInstance();
			if(transfer.isSupportedType(event.currentDataType)){
				Object selection = transfer.nativeToJava(event.currentDataType);
				Object data = getDataFromSelection(selection);
				if((data != null) && (data instanceof ObjRef)){
					ObjRef structureRef = (ObjRef)data;
					
					// Set up a substructure if one doesn't already exist.
					ObjRef subStructureRef = (ObjRef)AS.xarch.get(outerRef, "subStructure");
					if(subStructureRef == null){
						subStructureRef = AS.xarch.create("edu.uci.isr.xadl3.structure_3_0", "subStructure");
						AS.xarch.set(subStructureRef, "id", UIDGenerator.generateUID("subStructure"));
						AS.xarch.set(outerRef, "subStructure", subStructureRef);
					}
					AS.xarch.set(subStructureRef, "innerStructureLink", structureRef);
					
					ArchipelagoUtils.showUserNotification(view.getWorld().getBNAModel(), "Substructure Assigned", worldX, worldY);
				}
			}
		}
	}

}
