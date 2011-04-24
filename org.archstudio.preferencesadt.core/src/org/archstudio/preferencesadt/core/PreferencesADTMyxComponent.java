package org.archstudio.preferencesadt.core;

import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

public class PreferencesADTMyxComponent extends AbstractMyxSimpleBrick implements IPropertyChangeListener, IMyxDynamicBrick{

	public static final IMyxName INTERFACE_NAME_IN_PREFS = MyxUtils.createName("preferences");
	public static final IMyxName INTERFACE_NAME_OUT_PROPERTYEVENTS = MyxUtils.createName("propertyevents");

	protected IPreferenceStore prefs = null;
	protected IPropertyChangeListener propertyListener = null;
	
	public PreferencesADTMyxComponent(){
	}
	
	public void init(){
		// It is necessary to use the activator in this plugin rather than the main
		// ArchStudio plugin because if you don't, you can get a ClassCircularityException
		// if you go to the preference pages first before you activate an ArchStudio view
		// or perspective
		this.prefs = PreferencesADTActivator.getDefault().getPreferenceStore();
		prefs.addPropertyChangeListener(this);
	}
	
	public synchronized void interfaceConnected(IMyxName interfaceName, Object serviceObject){
		if(interfaceName.equals(INTERFACE_NAME_OUT_PROPERTYEVENTS)){
			propertyListener = (IPropertyChangeListener)serviceObject;
		}
	}
	
	public synchronized void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject){
		if(interfaceName.equals(INTERFACE_NAME_OUT_PROPERTYEVENTS)){
			propertyListener = null;
		}
	}
	
	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject){}
	
	public Object getServiceObject(IMyxName interfaceName){
		if(interfaceName.equals(INTERFACE_NAME_IN_PREFS)){
			return prefs;
		}
		return null;
	}
	
	public synchronized void propertyChange(PropertyChangeEvent event){
		if(propertyListener != null){
			propertyListener.propertyChange(event);
		}
	}
}
