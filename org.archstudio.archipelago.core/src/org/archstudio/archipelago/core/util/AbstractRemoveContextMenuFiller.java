package org.archstudio.archipelago.core.util;

import org.archstudio.archipelago.core.IArchipelagoTreeContextMenuFiller;
import org.archstudio.myx.fw.Services;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.TreeViewer;

public abstract class AbstractRemoveContextMenuFiller implements IArchipelagoTreeContextMenuFiller {
	protected TreeViewer viewer = null;
	protected Services AS = null;
	protected ObjRef xArchRef = null;
	protected IXArchADT xarch = null;

	public AbstractRemoveContextMenuFiller(TreeViewer viewer, Services AS, ObjRef xArchRef) {
		this.viewer = viewer;
		this.AS = AS;
		this.xArchRef = xArchRef;
		this.xarch = AS.get(IXArchADT.class);
	}

	protected abstract boolean matches(Object node);

	public void fillContextMenu(IMenuManager m, Object[] selectedNodes) {
		if (selectedNodes != null && selectedNodes.length == 1) {
			Object selectedNode = selectedNodes[0];
			if (selectedNode instanceof ObjRef && matches(selectedNodes[0])) {
				final ObjRef targetRef = (ObjRef) selectedNode;
				IAction removeAction = new Action("Remove") {
					public void run() {
						remove(targetRef);
					}
				};
				m.add(removeAction);
			}
		}
	}

	protected void remove(ObjRef targetRef) {
		String tagName = xarch.getContainingFeatureName(targetRef);
		if (tagName != null) {
			ObjRef parentRef = xarch.getParent(targetRef);
			if (parentRef != null) {
				xarch.remove(parentRef, tagName, targetRef);
			}
		}
	}
}
