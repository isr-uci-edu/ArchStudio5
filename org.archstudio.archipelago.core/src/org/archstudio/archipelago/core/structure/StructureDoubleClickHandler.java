package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.IArchipelagoTreeDoubleClickHandler;
import org.archstudio.xarchadt.common.ObjRef;

public class StructureDoubleClickHandler implements IArchipelagoTreeDoubleClickHandler{

	protected ArchipelagoServices AS = null;
	
	public StructureDoubleClickHandler(ArchipelagoServices archipelagoServices){
		this.AS = archipelagoServices;
	}
	
	protected boolean isTargetNode(Object ref){
		if(ref instanceof ObjRef){
			String refPath = AS.xarch.getTagsOnlyPathString((ObjRef)ref);
			if(refPath.equals("xADL/structure")){
				return true;
			}
		}
		return false;
	}
	
	public void treeNodeDoubleClicked(Object treeNode){
		if(isTargetNode(treeNode)){		
			ObjRef structureRef = (ObjRef)treeNode;
			StructureEditorSupport.setupEditor(AS, structureRef);
		}
	}
	
}
