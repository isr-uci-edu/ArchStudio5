package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.util.AbstractEditNameContextMenuFiller;
import org.archstudio.myx.fw.Services;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.viewers.TreeViewer;

public class StructureEditDescriptionContextMenuFiller extends AbstractEditNameContextMenuFiller {

	protected final IXArchADT xarch;

	public StructureEditDescriptionContextMenuFiller(TreeViewer viewer, Services AS, ObjRef xArchRef) {
		super(viewer, AS, xArchRef);
		this.xarch = AS.get(IXArchADT.class);
	}

	@Override
	protected boolean matches(Object node) {
		if (node != null) {
			if (node instanceof ObjRef) {
				ObjRef targetRef = (ObjRef) node;
				if (XadlUtils.isInstanceOf(xarch, targetRef, Structure_3_0Package.Literals.STRUCTURE)) {
					return true;
				}
			}
		}
		return false;
	}
}
