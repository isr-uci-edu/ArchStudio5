package org.archstudio.filemanager.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.archstudio.filemanager.CantOpenFileException;
import org.archstudio.filemanager.IFileManager;
import org.archstudio.filemanager.IFileManagerListener;
import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTFileListener;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTFileEvent;
import org.archstudio.xarchadt.XArchADTModelEvent;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;

public class FileManagerMyxComponent extends AbstractMyxSimpleBrick implements IXArchADTFileListener, IXArchADTModelListener, IFileManager {

	public static final IMyxName INTERFACE_NAME_OUT_XARCH = MyxUtils.createName("xarch");
	public static final IMyxName INTERFACE_NAME_IN_FLATEVENTS = MyxUtils.createName("modelevents");
	public static final IMyxName INTERFACE_NAME_IN_FILEEVENTS = MyxUtils.createName("fileevents");

	public static final IMyxName INTERFACE_NAME_IN_FILEMANAGER = MyxUtils.createName("filemanager");

	public static final IMyxName INTERFACE_NAME_OUT_FILEMANAGEREVENTS = MyxUtils.createName("filemanagerevents");

	private MyxRegistry er = MyxRegistry.getSharedInstance();

	protected IXArchADT xarch = null;
	protected IFileManagerListener fileManagerListener = null;

	protected Set<ObjRef> dirtySet = Collections.synchronizedSet(new HashSet<ObjRef>());

	//Keeps track of which tools have which documents open. When no tools have
	//a document open, it is closed in xArchADT.
	protected Map<ObjRef, List<String>> openerMap = Collections.synchronizedMap(new HashMap<ObjRef, List<String>>());

	public FileManagerMyxComponent() {
	}

	public void begin() {
		xarch = (IXArchADT) MyxUtils.getFirstRequiredServiceObject(this, INTERFACE_NAME_OUT_XARCH);
		fileManagerListener = (IFileManagerListener) MyxUtils.getFirstRequiredServiceObject(this, INTERFACE_NAME_OUT_FILEMANAGEREVENTS);
		er.register(this);
	}

	public void end() {
		er.unregister(this);
	}

	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(INTERFACE_NAME_IN_FILEEVENTS)) {
			return this;
		}
		else if (interfaceName.equals(INTERFACE_NAME_IN_FLATEVENTS)) {
			return this;
		}
		else if (interfaceName.equals(INTERFACE_NAME_IN_FILEMANAGER)) {
			return this;
		}
		return null;
	}

	@Override
	public void handleXArchADTFileEvent(XArchADTFileEvent evt) {
		for (Object o : er.getObjects(this)) {
			if (o instanceof IXArchADTFileListener) {
				((IXArchADTFileListener) o).handleXArchADTFileEvent(evt);
			}
		}
	}

	@Override
	public void handleXArchADTModelEvent(XArchADTModelEvent evt) {
		for (Object o : er.getObjects(this)) {
			if (o instanceof IXArchADTModelListener) {
				((IXArchADTModelListener) o).handleXArchADTModelEvent(evt);
			}
		}
		ObjRef documentRootRef = xarch.getDocumentRootRef(evt.getSource());
		makeDirty(documentRootRef);
	}

	private static URI getURI(IFile f) {
		return URI.createFileURI(f.getFullPath().toPortableString());
	}

	private static URI getURI(java.io.File f) {
		return URI.createFileURI(f.getPath());
	}

	public boolean isOpen(IFile f) {
		URI uri = getURI(f);
		return xarch.getOpenURIs().contains(uri);
	}

	public ObjRef getDocumentRootRef(IFile f) {
		URI uri = getURI(f);
		return xarch.getDocumentRootRef(uri);
	}

	public ObjRef open(String toolID, IFile f) throws CantOpenFileException {
		InputStream is = null;
		OutputStream os = null;
		URI uri = null;
		try {
			uri = getURI(f);
			ObjRef documentRootRef = xarch.load(uri);

			List<String> toolList = openerMap.get(documentRootRef);
			if (toolList == null) {
				toolList = new ArrayList<String>();
			}
			toolList.add(toolID);
			openerMap.put(documentRootRef, toolList);

			return documentRootRef;
		}
		catch (Exception e) {
			throw new CantOpenFileException("Can't open file: " + uri, e);
		}
		finally {
			try {
				if (is != null)
					is.close();
			}
			catch (IOException e) {
			}
			try {
				if (os != null)
					os.close();
			}
			catch (IOException e2) {
			}
		}
	}

	public ObjRef open(String toolID, java.io.File f) throws CantOpenFileException {
		InputStream is = null;
		OutputStream os = null;
		URI uri = null;
		try {
			uri = getURI(f);
			ObjRef documentRootRef = xarch.load(uri);

			List<String> toolList = openerMap.get(documentRootRef);
			if (toolList == null) {
				toolList = new ArrayList<String>();
			}
			toolList.add(toolID);
			openerMap.put(documentRootRef, toolList);

			return documentRootRef;
		}
		catch (Exception e) {
			throw new CantOpenFileException("Can't open file: " + uri, e);
		}
		finally {
			try {
				if (is != null)
					is.close();
			}
			catch (IOException e) {
			}
			try {
				if (os != null)
					os.close();
			}
			catch (IOException e2) {
			}
		}
	}

	@Override
	public void close(String toolID, ObjRef documentRootRef) {
		List<String> toolList = openerMap.get(documentRootRef);
		URI uri = xarch.getURI(documentRootRef);
		if (toolList == null) {
			xarch.close(uri);
			return;
		}

		toolList.remove(toolID);
		if (toolList.size() == 0) {
			xarch.close(uri);
			openerMap.remove(documentRootRef);
		}
	}

	public void makeDirty(ObjRef xArchRef) {
		if (dirtySet.contains(xArchRef)) {
			return;
		}
		dirtySet.add(xArchRef);
		if (fileManagerListener != null) {
			fileManagerListener.fileDirtyStateChanged(xArchRef, true);
		}
	}

	public void makeClean(ObjRef xArchRef) {
		if (!dirtySet.contains(xArchRef)) {
			return;
		}
		dirtySet.remove(xArchRef);
		if (fileManagerListener != null) {
			fileManagerListener.fileDirtyStateChanged(xArchRef, false);
		}
	}

	public boolean isDirty(ObjRef xArchRef) {
		return dirtySet.contains(xArchRef);
	}

	public void save(ObjRef xArchRef, IProgressMonitor monitor) {
		if (monitor != null) {
			monitor.beginTask("Saving File", 100);
			monitor.worked(1);
		}
		if (fileManagerListener != null) {
			monitor.subTask("Notifying Editors");
			monitor.worked(2);
			try {
				fileManagerListener.fileSaving(xArchRef, monitor);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (monitor != null) {
			monitor.worked(80);
		}

		URI uri = xarch.getURI(xArchRef);
		try {
			xarch.save(uri);
			makeClean(xArchRef);
		}
		catch (IOException ioe) {
			//TODO: Handle
		}
		if (monitor != null) {
			monitor.worked(100);
		}
		monitor.done();
	}

	public void saveAs(ObjRef xArchRef, IFile f) {
	}

}
