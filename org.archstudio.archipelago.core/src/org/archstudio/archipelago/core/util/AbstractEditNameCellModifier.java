package org.archstudio.archipelago.core.util;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.viewers.ICellModifier;

public abstract class AbstractEditNameCellModifier implements ICellModifier{
	protected ArchipelagoServices AS = null;
	protected ObjRef xArchRef = null;
	
	public AbstractEditNameCellModifier(ArchipelagoServices services, ObjRef xArchRef){
		this.AS = services;
		this.xArchRef = xArchRef;
	}
	
	public Object getValue(Object element, String property){
		if(element instanceof ObjRef){
			ObjRef targetRef = (ObjRef)element;
			String name = XadlUtils.getName(AS.xarch, targetRef);
			return name;
		}
		return null;
	}
	
	public void modify(Object element, String property, Object value){
		if(element instanceof ObjRef){
			ObjRef targetRef = (ObjRef)element;
			if(value != null){
				String newName = value.toString();
				XadlUtils.setName(AS.xarch, targetRef, newName);
			}
		}
	}
}
