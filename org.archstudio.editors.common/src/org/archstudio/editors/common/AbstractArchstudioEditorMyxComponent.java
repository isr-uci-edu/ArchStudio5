package org.archstudio.editors.common;

import org.archstudio.editormanager.common.IEditorManager;
import org.archstudio.filemanager.common.IFileManager;
import org.archstudio.filemanager.common.IFileManagerListener;
import org.archstudio.launcher.common.ILaunchData;
import org.archstudio.launcher.common.ILaunchable;
import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.resources.common.IResources;
import org.archstudio.xarchadt.common.IXArchADT;
import org.archstudio.xarchadt.common.IXArchADTFileListener;
import org.archstudio.xarchadt.common.IXArchADTModelListener;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.xarchadt.common.XArchADTFileEvent;
import org.archstudio.xarchadt.common.XArchADTModelEvent;
import org.eclipse.core.runtime.IProgressMonitor;

public abstract class AbstractArchstudioEditorMyxComponent extends AbstractMyxSimpleBrick implements IXArchADTFileListener, IXArchADTModelListener, IFocusEditorListener, IFileManagerListener, ILaunchable {
	
	public static final IMyxName INTERFACE_NAME_OUT_RESOURCES = MyxUtils.createName("resources");
	public static final IMyxName INTERFACE_NAME_OUT_EDITORMANAGER = MyxUtils.createName("editormanager");
	public static final IMyxName INTERFACE_NAME_OUT_FILEMANAGER = MyxUtils.createName("filemanager");
	public static final IMyxName INTERFACE_NAME_OUT_XARCH = MyxUtils.createName("xarch");
	public static final IMyxName INTERFACE_NAME_IN_LAUNCHER = MyxUtils.createName("launcher");
	public static final IMyxName INTERFACE_NAME_IN_FLATEVENTS = MyxUtils.createName("modelevents");
	public static final IMyxName INTERFACE_NAME_IN_FILEEVENTS = MyxUtils.createName("fileevents");
	public static final IMyxName INTERFACE_NAME_IN_FOCUSEDITOREVENTS = MyxUtils.createName("focuseditorevents");
	public static final IMyxName INTERFACE_NAME_IN_FILEMANAGEREVENTS = MyxUtils.createName("filemanagerevents");

	protected MyxRegistry er = MyxRegistry.getSharedInstance();
	protected IXArchADT xarch = null;
	protected IFileManager fileman = null;
	protected IEditorManager editorManager = null;
	protected IResources resources = null;

	protected String editorName = null;
	protected String eclipseEditorID = null;
	protected boolean registerWithEditorManager = false;
	protected boolean handleUnattachedXArchFlatEvents = false;
	
	protected ILaunchData launchData = null;
	
	public AbstractArchstudioEditorMyxComponent(String editorName, String eclipseEditorID, boolean registerWithEditorManager){
		super();
		this.editorName = editorName;
		this.eclipseEditorID = eclipseEditorID;
		this.registerWithEditorManager = registerWithEditorManager;
	}
	
	public void begin(){
		xarch = (IXArchADT)MyxUtils.getFirstRequiredServiceObject(this, INTERFACE_NAME_OUT_XARCH);
		fileman = (IFileManager)MyxUtils.getFirstRequiredServiceObject(this, INTERFACE_NAME_OUT_FILEMANAGER);
		editorManager = (IEditorManager)MyxUtils.getFirstRequiredServiceObject(this, INTERFACE_NAME_OUT_EDITORMANAGER);
		resources = (IResources)MyxUtils.getFirstRequiredServiceObject(this, INTERFACE_NAME_OUT_RESOURCES);
		
		if(registerWithEditorManager){
			editorManager.registerEditor(editorName, null);
		}
		er.register(this);
	}
	
	public void end(){
		er.unregister(this);
		if(registerWithEditorManager){
			editorManager.unregisterEditor(editorName);
		}
	}

	public Object getServiceObject(IMyxName interfaceName){
		if(interfaceName.equals(INTERFACE_NAME_IN_FILEEVENTS)){
			return this;
		}
		else if(interfaceName.equals(INTERFACE_NAME_IN_FLATEVENTS)){
			return this;
		}
		else if(interfaceName.equals(INTERFACE_NAME_IN_FOCUSEDITOREVENTS)){
			return this;
		}
		else if(interfaceName.equals(INTERFACE_NAME_IN_FILEMANAGEREVENTS)){
			return this;
		}
		else if(interfaceName.equals(INTERFACE_NAME_IN_LAUNCHER)){
			return this;
		}
		return null;
	}
	
	public IEditorManager getEditorManager(){
		return editorManager;
	}

	public IFileManager getFileManager(){
		return fileman;
	}

	public IResources getResources(){
		return resources;
	}

	public IXArchADT getXArchADT(){
		return xarch;
	}

	public void handleXArchADTFileEvent(XArchADTFileEvent evt){
		for(Object o : er.getObjects(this)){
			if(o instanceof IXArchADTFileListener){
				((IXArchADTFileListener)o).handleXArchADTFileEvent(evt);
			}
		}
	}
	
	public void setHandleUnattachedXArchFlatEvents(boolean handle){
		this.handleUnattachedXArchFlatEvents = handle;
	}
	
	public void handleXArchADTModelEvent(XArchADTModelEvent evt){
		//if((!handleUnattachedXArchFlatEvents) && (!evt.getIsAttached())){
		//	return;
		//}
		for(Object o : er.getObjects(this)){
			if(o instanceof IXArchADTModelListener){
				((IXArchADTModelListener)o).handleXArchADTModelEvent(evt);
			}
		}
	}

	@Override
	public void focusEditor(String editorName, ObjRef[] refs){
		if((editorName != null) && editorName.equals(this.editorName)){
			if(refs.length == 1){
				ObjRef ref = refs[0];
				FocusEditorUtils.focusEditor(xarch, ref, eclipseEditorID, editorName);
			}
		}
	}
	
	public void fileDirtyStateChanged(ObjRef documentRootRef, boolean dirty){
		for(Object o : er.getObjects(this)) {
			if(o instanceof IFileManagerListener){
				((IFileManagerListener)o).fileDirtyStateChanged(documentRootRef, dirty);
			}
		}
	}
	
	public void fileSaving(ObjRef documentRootRef, IProgressMonitor monitor){
		for(Object o : er.getObjects(this)) {
			if(o instanceof IFileManagerListener){
				((IFileManagerListener)o).fileSaving(documentRootRef, monitor);
			}
		}
	}
}
