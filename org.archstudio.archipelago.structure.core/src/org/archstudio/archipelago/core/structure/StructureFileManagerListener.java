package org.archstudio.archipelago.core.structure;

import org.archstudio.filemanager.IFileManagerListener;
import org.archstudio.myx.fw.Services;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.core.runtime.IProgressMonitor;

public class StructureFileManagerListener implements IFileManagerListener {
	protected Services AS = null;
	protected ObjRef documentRootRef = null;

	public StructureFileManagerListener(Services AS, ObjRef documentRootRef) {
		this.AS = AS;
		this.documentRootRef = documentRootRef;
	}

	public void fileDirtyStateChanged(ObjRef xArchRef, boolean dirty) {
	}

	public void fileSaving(final ObjRef documentRootRef, final IProgressMonitor monitor) {
		//if(documentRootRef.equals(this.documentRootRef)){
		//	monitor.subTask("Writing Hints");
		//	StructureEditorSupport.writeHints(AS, documentRootRef, monitor);
		//}
	}
}
