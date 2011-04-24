package org.archstudio.archipelago.core;

import org.eclipse.jface.viewers.TreeViewer;

import org.archstudio.xarchadt.ObjRef;

public class FolderNodeTreePlugin extends AbstractArchipelagoTreePlugin{
	public FolderNodeTreePlugin(TreeViewer viewer, ArchipelagoServices AS, ObjRef xArchRef){
		this.labelProvider = new FolderNodeLabelProvider(AS);
	}
}
