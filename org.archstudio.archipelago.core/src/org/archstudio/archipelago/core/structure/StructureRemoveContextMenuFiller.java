package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.util.AbstractRemoveContextMenuFiller;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.viewers.TreeViewer;

public class StructureRemoveContextMenuFiller extends AbstractRemoveContextMenuFiller {

	public StructureRemoveContextMenuFiller(TreeViewer viewer, ArchipelagoServices services, ObjRef xArchRef) {
		super(viewer, services, xArchRef);
	}

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

	protected void remove(ObjRef targetRef) {
		if (targetRef != null) {
			if (XadlUtils.isInstanceOf(AS.xarch, targetRef, Structure_3_0Package.Literals.STRUCTURE)) {
				AS.xarch.remove(xArchRef, "Object", targetRef);
				return;
			}
		}
		super.remove(targetRef);
	}
}
