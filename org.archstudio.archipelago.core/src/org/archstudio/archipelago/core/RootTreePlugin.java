package org.archstudio.archipelago.core;

import org.archstudio.myx.fw.Services;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.viewers.TreeViewer;

public class RootTreePlugin extends AbstractArchipelagoTreePlugin {
	public RootTreePlugin(TreeViewer viewer, final Services AS, ObjRef documentRootRef) {
		this.contentProvider = new RootContentProvider(AS, documentRootRef);
		this.labelProvider = new RootLabelProvider(AS);

		final TreeViewer fviewer = viewer;
		this.editorFocuser = new IArchipelagoEditorFocuser() {
			@Override
			public void focusEditor(String editorName, ObjRef[] refs) {
				fviewer.setSelection(null);
			}
		};

		this.contextMenuFillers = new IArchipelagoTreeContextMenuFiller[0];
	}
}
