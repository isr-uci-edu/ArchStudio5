package org.archstudio.archipelago.core.structure;

import org.eclipse.core.runtime.IProgressMonitor;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.filemanager.common.IFileManagerListener;
import org.archstudio.xarchadt.common.ObjRef;

public class StructureFileManagerListener implements IFileManagerListener{
	protected ArchipelagoServices AS = null;
	protected ObjRef documentRootRef = null;
	
	public StructureFileManagerListener(ArchipelagoServices services, ObjRef documentRootRef){
		this.AS = services;
		this.documentRootRef = documentRootRef;
	}
	
	public void fileDirtyStateChanged(ObjRef xArchRef, boolean dirty){
	}
	
	public void fileSaving(final ObjRef documentRootRef, final IProgressMonitor monitor){
		if(documentRootRef.equals(this.documentRootRef)){
			monitor.subTask("Writing Hints");
			StructureEditorSupport.writeHints(AS, documentRootRef, monitor);
		}
	}
}
