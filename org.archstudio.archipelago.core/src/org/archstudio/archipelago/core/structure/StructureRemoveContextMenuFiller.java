package org.archstudio.archipelago.core.structure;

import org.eclipse.jface.viewers.TreeViewer;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.util.AbstractRemoveContextMenuFiller;
import org.archstudio.xarchadt.common.ObjRef;

public class StructureRemoveContextMenuFiller extends AbstractRemoveContextMenuFiller{

	public StructureRemoveContextMenuFiller(TreeViewer viewer, ArchipelagoServices services, ObjRef xArchRef){
		super(viewer, services, xArchRef);
	}
	
	protected boolean matches(Object node){
		if(node != null){
			if(node instanceof ObjRef){
				ObjRef targetRef = (ObjRef)node;
				if(AS.xarch.isInstanceOf(targetRef, "org.archstudio.xadl3.structure_3_0.Structure")){
					return true;
				}
			}
		}
		return false;
	}
	
	protected void remove(ObjRef targetRef){
		if(targetRef != null){
			if(AS.xarch.isInstanceOf(targetRef, "org.archstudio.xadl3.structure_3_0.Structure")){
				AS.xarch.remove(xArchRef, "Object", targetRef);
				return;
			}
		}
		super.remove(targetRef);
	}
}
