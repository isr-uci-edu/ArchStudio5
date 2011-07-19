package org.archstudio.filemanager;

import java.io.File;

import org.archstudio.xarchadt.ObjRef;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;

public interface IFileManager {

	public boolean isOpen(IFile f);

	public ObjRef getDocumentRootRef(IFile f);

	public ObjRef open(String toolID, IFile f) throws CantOpenFileException;

	public ObjRef open(String toolID, File f) throws CantOpenFileException;

	public void close(String toolID, ObjRef documentRootRef);

	public void save(ObjRef documentRootRef, IProgressMonitor monitor);

	public void saveAs(ObjRef documentRootRef, IFile f);

	public void makeDirty(ObjRef documentRootRef);

	public void makeClean(ObjRef documentRootRef);

	public boolean isDirty(ObjRef documentRootRef);
}
