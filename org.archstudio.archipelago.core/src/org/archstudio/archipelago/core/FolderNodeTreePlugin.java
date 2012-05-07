package org.archstudio.archipelago.core;

import org.archstudio.myx.fw.Services;
import org.archstudio.resources.IResources;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.viewers.TreeViewer;

public class FolderNodeTreePlugin extends AbstractArchipelagoTreePlugin {
	public FolderNodeTreePlugin(TreeViewer viewer, Services AS, ObjRef xArchRef) {
		this.labelProvider = new FolderNodeLabelProvider(AS.get(IResources.class));
	}
}
