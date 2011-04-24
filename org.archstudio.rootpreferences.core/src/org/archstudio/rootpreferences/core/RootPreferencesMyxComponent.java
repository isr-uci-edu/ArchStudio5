package org.archstudio.rootpreferences.core;

import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.resources.common.IResources;
import org.eclipse.jface.preference.IPreferenceStore;

public class RootPreferencesMyxComponent extends AbstractMyxSimpleBrick 
implements IMyxDynamicBrick{

	public static final IMyxName INTERFACE_NAME_OUT_PREFERENCES = MyxUtils.createName("preferences");
	public static final IMyxName INTERFACE_NAME_OUT_RESOURCES = MyxUtils.createName("resources");
	
	private MyxRegistry er = MyxRegistry.getSharedInstance();

	protected IResources resources = null;
	protected IPreferenceStore preferences = null;
	
	public RootPreferencesMyxComponent(){
	}
	
	public void begin(){
		er.register(this);
	}
	
	public void end(){
		er.unregister(this);
	}
	
	public synchronized void interfaceConnected(IMyxName interfaceName, Object serviceObject){
		if(interfaceName.equals(INTERFACE_NAME_OUT_PREFERENCES)){
			preferences = (IPreferenceStore)serviceObject;
		}
		else if(interfaceName.equals(INTERFACE_NAME_OUT_RESOURCES)){
			resources = (IResources)serviceObject;
		}
	}
	
	public synchronized void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject){
		if(interfaceName.equals(INTERFACE_NAME_OUT_PREFERENCES)){
			preferences = null;
		}
		else if(interfaceName.equals(INTERFACE_NAME_OUT_RESOURCES)){
			resources = null;
		}
	}
	
	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject){}
	
	public Object getServiceObject(IMyxName interfaceName){
		return null;
	}
	
}
