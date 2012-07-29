package org.archstudio.archipelago.core.util;

import org.archstudio.myx.fw.Services;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.viewers.ICellModifier;

public abstract class AbstractEditNameCellModifier implements ICellModifier {
	protected Services AS = null;
	protected ObjRef xArchRef = null;
	protected IXArchADT xarch = null;

	public AbstractEditNameCellModifier(Services AS, ObjRef xArchRef) {
		this.AS = AS;
		this.xArchRef = xArchRef;
		this.xarch = AS.get(IXArchADT.class);
	}

	public Object getValue(Object element, String property) {
		if (element instanceof ObjRef) {
			ObjRef targetRef = (ObjRef) element;
			String name = XadlUtils.getName(xarch, targetRef);
			return name;
		}
		return null;
	}

	public void modify(Object element, String property, Object value) {
		if (element instanceof ObjRef) {
			ObjRef targetRef = (ObjRef) element;
			if (value != null) {
				String newName = value.toString();
				XArchADTOperations.set("Rename", xarch, targetRef, "name", newName);
			}
		}
	}
}
