package org.archstudio.xarchadt.core;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.xarchadt.common.IXArchADTFileListener;
import org.archstudio.xarchadt.common.IXArchADTModelListener;
import org.archstudio.xarchadt.common.XArchADTFileEvent;
import org.archstudio.xarchadt.common.XArchADTModelEvent;

public class XArchADTMyxComponent
    extends AbstractMyxSimpleBrick
    implements IXArchADTModelListener, IXArchADTFileListener, IMyxDynamicBrick{

	public static final IMyxName INTERFACE_NAME_IN_XARCH = MyxUtils.createName("xarch");
	public static final IMyxName INTERFACE_NAME_OUT_FILEEVENTS = MyxUtils.createName("fileevents");
	public static final IMyxName INTERFACE_NAME_OUT_MODELEVENTS = MyxUtils.createName("modelevents");

	protected XArchADTImpl impl = null;
	protected Collection<IXArchADTModelListener> modelListeners = new CopyOnWriteArrayList<IXArchADTModelListener>();
	protected Collection<IXArchADTFileListener> fileListeners = new CopyOnWriteArrayList<IXArchADTFileListener>();

	public XArchADTMyxComponent(){
		this.impl = new XArchADTImpl();
	}

	@Override
	public void init(){
		impl.addXArchADTFileListener(this);
		impl.addXArchADTModelListener(this);
	}

	public synchronized void interfaceConnected(IMyxName interfaceName, Object serviceObject){
		if(interfaceName.equals(INTERFACE_NAME_OUT_FILEEVENTS)){
			fileListeners.add((IXArchADTFileListener)serviceObject);
		}
		else if(interfaceName.equals(INTERFACE_NAME_OUT_MODELEVENTS)){
			modelListeners.add((IXArchADTModelListener)serviceObject);
		}
	}

	public synchronized void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject){
		if(interfaceName.equals(INTERFACE_NAME_OUT_FILEEVENTS)){
			fileListeners.remove(serviceObject);
		}
		else if(interfaceName.equals(INTERFACE_NAME_OUT_MODELEVENTS)){
			modelListeners.remove(serviceObject);
		}
	}

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject){
	}

	public Object getServiceObject(IMyxName interfaceName){
		if(interfaceName.equals(INTERFACE_NAME_IN_XARCH)){
			return impl;
		}
		return null;
	}

	public void handleXArchADTFileEvent(XArchADTFileEvent evt){
		for(IXArchADTFileListener fileListener : fileListeners){
			fileListener.handleXArchADTFileEvent(evt);
		}
	}

	public void handleXArchADTModelEvent(XArchADTModelEvent evt){
		for(IXArchADTModelListener modelListener : modelListeners){
			modelListener.handleXArchADTModelEvent(evt);
		}
	}
}
