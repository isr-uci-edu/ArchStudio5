package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.util.AbstractEditNameCellModifier;
import org.archstudio.xarchadt.common.ObjRef;

public class StructureEditDescriptionCellModifier extends AbstractEditNameCellModifier{

	public StructureEditDescriptionCellModifier(ArchipelagoServices services, ObjRef xArchRef){
		super(services, xArchRef);
	}
	
	public boolean canModify(Object element, String property){
		if((element != null) && (element instanceof ObjRef)){
			ObjRef targetRef = (ObjRef)element;
			if(AS.xarch.isInstanceOf(targetRef, "edu.uci.isr.xadl3.structure_3_0.Structure")){
				return true;
			}
		}
		return false;
	}
}
