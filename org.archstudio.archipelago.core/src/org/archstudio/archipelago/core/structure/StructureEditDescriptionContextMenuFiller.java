package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.util.AbstractEditNameContextMenuFiller;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.viewers.TreeViewer;

public class StructureEditDescriptionContextMenuFiller extends AbstractEditNameContextMenuFiller {

	public StructureEditDescriptionContextMenuFiller(TreeViewer viewer, ArchipelagoServices services, ObjRef xArchRef) {
		super(viewer, services, xArchRef);
	}

	@Override
	protected boolean matches(Object node) {
		if (node != null) {
			if (node instanceof ObjRef) {
				ObjRef targetRef = (ObjRef) node;
				if (XadlUtils.isInstanceOf(AS.xarch, targetRef, Structure_3_0Package.Literals.STRUCTURE)) {
					return true;
				}
			}
		}
		return false;
	}
}
