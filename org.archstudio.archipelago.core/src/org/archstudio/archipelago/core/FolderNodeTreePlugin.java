package org.archstudio.archipelago.core;

import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.viewers.TreeViewer;

public class FolderNodeTreePlugin extends AbstractArchipelagoTreePlugin {
	public FolderNodeTreePlugin(TreeViewer viewer, ArchipelagoServices AS, ObjRef xArchRef) {
		this.labelProvider = new FolderNodeLabelProvider(AS);
	}
}
