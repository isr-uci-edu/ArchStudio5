package org.archstudio.eclipsedev.core;

import java.util.Collections;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import org.archstudio.myx.eclipse.MyxEclipseUtils;
import org.archstudio.myx.fw.EMyxInterfaceDirection;
import org.archstudio.myx.fw.IMyxBrickDescription;
import org.archstudio.myx.fw.IMyxInterfaceDescription;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.IMyxRuntime;
import org.archstudio.myx.fw.MyxJavaClassBrickDescription;
import org.archstudio.myx.fw.MyxJavaClassInterfaceDescription;
import org.archstudio.myx.fw.MyxUtils;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.archstudio.eclipsedev";

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		startApplication();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	private void startApplication() throws Exception {
		MyxEclipseUtils.register();

		IMyxRuntime myxRuntime = MyxUtils.getDefaultImplementation().createRuntime();
		myxRuntime.addBrickLoader(MyxUtils.createName("EclipseBrickLoader"), "org.archstudio.myx.eclipse.MyxEclipseBrickLoader", null);

		IMyxInterfaceDescription dataBindingInterfaceDescription = new MyxJavaClassInterfaceDescription(Collections
		        .singleton("org.archstudio.dblgen.IDataBindingGenerator"));
		IMyxInterfaceDescription monitorInterfaceDescription = new MyxJavaClassInterfaceDescription(Collections
		        .singleton("org.eclipse.emf.common.util.Monitor"));
		IMyxInterfaceDescription preferencesInterfaceDescription = new MyxJavaClassInterfaceDescription(Collections
		        .singleton("org.eclipse.jface.preference.IPreferenceStore"));

		IMyxName dataBindingInterfaceName = MyxUtils.createName("databinding");
		IMyxName monitorInterfaceName = MyxUtils.createName("monitor");
		IMyxName preferencesInterfaceName = MyxUtils.createName("preferences");

		IMyxBrickDescription dataBindingBrickDescription = new MyxJavaClassBrickDescription("org.archstudio.dblgen.core.DataBindingGeneratorMyxComponent");
		IMyxName dataBindingBrickName = MyxUtils.createName("DataBinding");

		IMyxBrickDescription preferencesADTBrickDescription = new MyxJavaClassBrickDescription("org.archstudio.preferencesadt.core.PreferencesADTMyxComponent");
		IMyxName preferencesADTBrickName = MyxUtils.createName("PreferencesADT");
		
		IMyxBrickDescription eclipseDevBrickDescription = new MyxJavaClassBrickDescription("org.archstudio.eclipsedev.core.EclipseDevMyxComponent");
		IMyxName eclipseDevBrickName = MyxUtils.createName("EclipseDev");

		IMyxBrickDescription eclipseDevPreferencesBrickDescription = new MyxJavaClassBrickDescription("org.archstudio.eclipsedev.core.prefs.EclipseDevPreferencesMyxComponent");
		IMyxName eclipseDevPreferencesBrickName = MyxUtils.createName("EclipseDevPreferences");
		
		myxRuntime.addBrick(MyxUtils.DEFAULT_PATH, dataBindingBrickName, dataBindingBrickDescription, null);
		myxRuntime.init(MyxUtils.DEFAULT_PATH, dataBindingBrickName);
		myxRuntime.addInterface(MyxUtils.DEFAULT_PATH, dataBindingBrickName, dataBindingInterfaceName, dataBindingInterfaceDescription,
		        EMyxInterfaceDirection.IN);
		myxRuntime.addInterface(MyxUtils.DEFAULT_PATH, dataBindingBrickName, monitorInterfaceName, monitorInterfaceDescription, EMyxInterfaceDirection.OUT);

		myxRuntime.addBrick(MyxUtils.DEFAULT_PATH, eclipseDevBrickName, eclipseDevBrickDescription, null);
		myxRuntime.init(MyxUtils.DEFAULT_PATH, eclipseDevBrickName);
		myxRuntime.addInterface(MyxUtils.DEFAULT_PATH, eclipseDevBrickName, monitorInterfaceName, monitorInterfaceDescription, EMyxInterfaceDirection.IN);
		myxRuntime.addInterface(MyxUtils.DEFAULT_PATH, eclipseDevBrickName, preferencesInterfaceName, preferencesInterfaceDescription,
		        EMyxInterfaceDirection.OUT);
		myxRuntime.addInterface(MyxUtils.DEFAULT_PATH, eclipseDevBrickName, dataBindingInterfaceName, dataBindingInterfaceDescription,
		        EMyxInterfaceDirection.OUT);

		myxRuntime.addBrick(MyxUtils.DEFAULT_PATH, preferencesADTBrickName, preferencesADTBrickDescription, null);
		myxRuntime.init(MyxUtils.DEFAULT_PATH, preferencesADTBrickName);
		myxRuntime.addInterface(MyxUtils.DEFAULT_PATH, preferencesADTBrickName, preferencesInterfaceName, preferencesInterfaceDescription, EMyxInterfaceDirection.IN);
		
		myxRuntime.addBrick(MyxUtils.DEFAULT_PATH, eclipseDevPreferencesBrickName, eclipseDevPreferencesBrickDescription, null);
		myxRuntime.init(MyxUtils.DEFAULT_PATH, eclipseDevPreferencesBrickName);
		myxRuntime.addInterface(MyxUtils.DEFAULT_PATH, eclipseDevPreferencesBrickName, preferencesInterfaceName, preferencesInterfaceDescription, EMyxInterfaceDirection.OUT);
		
		myxRuntime.addWeld(myxRuntime.createWeld(MyxUtils.DEFAULT_PATH, eclipseDevBrickName, dataBindingInterfaceName, MyxUtils.DEFAULT_PATH,
		        dataBindingBrickName, dataBindingInterfaceName));
		myxRuntime.addWeld(myxRuntime.createWeld(MyxUtils.DEFAULT_PATH, eclipseDevBrickName, preferencesInterfaceName, MyxUtils.DEFAULT_PATH,
		        preferencesADTBrickName, preferencesInterfaceName));
		myxRuntime.addWeld(myxRuntime.createWeld(MyxUtils.DEFAULT_PATH, eclipseDevPreferencesBrickName, preferencesInterfaceName, MyxUtils.DEFAULT_PATH, 
				preferencesADTBrickName, preferencesInterfaceName));

		//TODO: Add an event pump here.

		myxRuntime.addWeld(myxRuntime.createWeld(MyxUtils.DEFAULT_PATH, dataBindingBrickName, monitorInterfaceName, MyxUtils.DEFAULT_PATH, eclipseDevBrickName,
		        monitorInterfaceName));
		
		myxRuntime.begin(MyxUtils.DEFAULT_PATH, dataBindingBrickName);
		myxRuntime.begin(MyxUtils.DEFAULT_PATH, preferencesADTBrickName);
		myxRuntime.begin(MyxUtils.DEFAULT_PATH, eclipseDevPreferencesBrickName);
		myxRuntime.begin(MyxUtils.DEFAULT_PATH, eclipseDevBrickName);
	}

}
