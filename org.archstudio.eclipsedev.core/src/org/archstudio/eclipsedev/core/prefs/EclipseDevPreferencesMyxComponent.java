package org.archstudio.eclipsedev.core.prefs;

import org.eclipse.jface.preference.IPreferenceStore;

import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

public class EclipseDevPreferencesMyxComponent extends AbstractMyxSimpleBrick implements IMyxDynamicBrick{
	public static final IMyxName INTERFACE_NAME_OUT_PREFERENCES = MyxUtils.createName("preferences");
	
	protected IPreferenceStore prefs = null;
	
	private MyxRegistry er = MyxRegistry.getSharedInstance();

	public EclipseDevPreferencesMyxComponent(){
	}
	
	public void begin(){
		er.register(this);
	}
	
	public void end(){
		er.unregister(this);
	}

	public synchronized void interfaceConnected(IMyxName interfaceName, Object serviceObject){
		if(interfaceName.equals(INTERFACE_NAME_OUT_PREFERENCES)){
			prefs = (IPreferenceStore)serviceObject;
		}
	}
	
	public synchronized void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject){
		if(interfaceName.equals(INTERFACE_NAME_OUT_PREFERENCES)){
			prefs = null;
		}
	}
	
	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject){}
	
	public Object getServiceObject(IMyxName interfaceName){
		return null;
	}
}
