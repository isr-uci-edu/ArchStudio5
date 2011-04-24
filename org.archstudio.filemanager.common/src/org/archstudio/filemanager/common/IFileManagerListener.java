package org.archstudio.filemanager.common;

import org.archstudio.xarchadt.common.ObjRef;
import org.eclipse.core.runtime.IProgressMonitor;

public interface IFileManagerListener{
	public void fileDirtyStateChanged(ObjRef documentRootRef, boolean dirty);
	public void fileSaving(ObjRef documentRootRef, IProgressMonitor monitor);
}
