package org.archstudio.main;

import java.util.Collections;
import java.util.Properties;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import org.archstudio.myx.eclipse.MyxEclipseUtils;
import org.archstudio.myx.fw.EMyxInterfaceDirection;
import org.archstudio.myx.fw.IMyxBrickDescription;
import org.archstudio.myx.fw.IMyxInterfaceDescription;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.IMyxRuntime;
import org.archstudio.myx.fw.MyxBasicBrickInitializationData;
import org.archstudio.myx.fw.MyxJavaClassBrickDescription;
import org.archstudio.myx.fw.MyxJavaClassInterfaceDescription;
import org.archstudio.myx.fw.MyxUtils;

/**
 * The activator class controls the plug-in life cycle
 */
public class ArchStudio5Activator extends AbstractUIPlugin {
	
	// The plug-in ID
	public static final String PLUGIN_ID = "org.archstudio.main";

	public static final String ARCHSTUDIO_DESCRIPTION_URI = "platform:/plugin/" + PLUGIN_ID + "/archstudio5.xml";

	// The shared instance
	private static ArchStudio5Activator plugin;
	
	/**
	 * The constructor
	 */
	public ArchStudio5Activator() {
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		System.err.println("Starting main plugin");
		super.start(context);
		plugin = this;
		startApplication();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
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
	public static ArchStudio5Activator getDefault() {
		return plugin;
	}

	private void startApplication() throws Exception{
		System.out.println("Starting app");
		
		Properties bootstrapInitProperties = new Properties();
		bootstrapInitProperties.put("uri", ARCHSTUDIO_DESCRIPTION_URI);
		
		MyxEclipseUtils.register();
		
		IMyxRuntime myxRuntime = MyxUtils.getDefaultImplementation().createRuntime();
		myxRuntime.addClassManager(MyxUtils.createName("EclipseClassManager"), "org.archstudio.myx.eclipse.MyxEclipseClassManager", null);
		myxRuntime.addBrickLoader(MyxUtils.createName("EclipseBrickLoader"), "org.archstudio.myx.eclipse.MyxEclipseBrickLoader", null);
		
		IMyxInterfaceDescription xarchInterfaceDescription = 
			new MyxJavaClassInterfaceDescription(Collections.singleton("org.archstudio.xarchadt.common.IXArchADT"));
		IMyxName xarchInterfaceName = MyxUtils.createName("xarch");
		
		IMyxInterfaceDescription myxRuntimeInterfaceDescription = 
			new MyxJavaClassInterfaceDescription(Collections.singleton("org.archstudio.myx.fw.IMyxRuntime"));
		IMyxName myxRuntimeInterfaceName = MyxUtils.createName("myxruntime");
		
		IMyxInterfaceDescription aimInterfaceDescription = 
			new MyxJavaClassInterfaceDescription(Collections.singleton("org.archstudio.aim.common.IAIM"));
		IMyxName aimInterfaceName = MyxUtils.createName("aim");
		
		IMyxBrickDescription xarchadtBrickDescription = new MyxJavaClassBrickDescription("org.archstudio.xarchadt.core.XArchADTMyxComponent");
		IMyxName xarchadtBrickName = MyxUtils.createName("XArchADT");
		
		IMyxBrickDescription aimBrickDescription = new MyxJavaClassBrickDescription("org.archstudio.aim.core.AIMMyxComponent");
		IMyxName aimBrickName = MyxUtils.createName("AIM");
		
		IMyxBrickDescription myxRuntimeBrickDescription = new MyxJavaClassBrickDescription("org.archstudio.myx.comp.MyxRuntimeComponent");
		IMyxName myxRuntimeBrickName = MyxUtils.createName("MyxRuntime");
		
		IMyxBrickDescription bootstrapBrickDescription = new MyxJavaClassBrickDescription("org.archstudio.bootstrap.core.BootstrapMyxComponent");
		IMyxName bootstrapBrickName = MyxUtils.createName("Bootstrap");
		
		myxRuntime.addBrick(MyxUtils.DEFAULT_PATH, xarchadtBrickName, xarchadtBrickDescription, null);
		myxRuntime.init(MyxUtils.DEFAULT_PATH, xarchadtBrickName);
		myxRuntime.addInterface(MyxUtils.DEFAULT_PATH, xarchadtBrickName, xarchInterfaceName, 
			xarchInterfaceDescription, EMyxInterfaceDirection.IN);

		myxRuntime.addBrick(MyxUtils.DEFAULT_PATH, myxRuntimeBrickName, myxRuntimeBrickDescription, null);
		myxRuntime.init(MyxUtils.DEFAULT_PATH, myxRuntimeBrickName);
		myxRuntime.addInterface(MyxUtils.DEFAULT_PATH, myxRuntimeBrickName, myxRuntimeInterfaceName, 
			myxRuntimeInterfaceDescription, EMyxInterfaceDirection.IN);

		myxRuntime.addBrick(MyxUtils.DEFAULT_PATH, aimBrickName, aimBrickDescription, null);
		myxRuntime.init(MyxUtils.DEFAULT_PATH, aimBrickName);
		myxRuntime.addInterface(MyxUtils.DEFAULT_PATH, aimBrickName, myxRuntimeInterfaceName, 
				myxRuntimeInterfaceDescription, EMyxInterfaceDirection.OUT);
		myxRuntime.addInterface(MyxUtils.DEFAULT_PATH, aimBrickName, xarchInterfaceName, 
			xarchInterfaceDescription, EMyxInterfaceDirection.OUT);
		myxRuntime.addInterface(MyxUtils.DEFAULT_PATH, aimBrickName, aimInterfaceName, 
			aimInterfaceDescription, EMyxInterfaceDirection.IN);
		
		myxRuntime.addBrick(MyxUtils.DEFAULT_PATH, bootstrapBrickName, bootstrapBrickDescription, new MyxBasicBrickInitializationData(bootstrapInitProperties));
		myxRuntime.init(MyxUtils.DEFAULT_PATH, bootstrapBrickName);
		myxRuntime.addInterface(MyxUtils.DEFAULT_PATH, bootstrapBrickName, xarchInterfaceName, 
				xarchInterfaceDescription, EMyxInterfaceDirection.OUT);
		myxRuntime.addInterface(MyxUtils.DEFAULT_PATH, bootstrapBrickName, aimInterfaceName, 
			aimInterfaceDescription, EMyxInterfaceDirection.OUT);
		
		myxRuntime.addWeld(myxRuntime.createWeld(MyxUtils.DEFAULT_PATH, aimBrickName, myxRuntimeInterfaceName, 
				MyxUtils.DEFAULT_PATH, myxRuntimeBrickName, myxRuntimeInterfaceName));

		myxRuntime.addWeld(myxRuntime.createWeld(MyxUtils.DEFAULT_PATH, aimBrickName, xarchInterfaceName, 
			MyxUtils.DEFAULT_PATH, xarchadtBrickName, xarchInterfaceName));
		
		myxRuntime.addWeld(myxRuntime.createWeld(MyxUtils.DEFAULT_PATH, bootstrapBrickName, xarchInterfaceName, 
				MyxUtils.DEFAULT_PATH, xarchadtBrickName, xarchInterfaceName));

		myxRuntime.addWeld(myxRuntime.createWeld(MyxUtils.DEFAULT_PATH, bootstrapBrickName, aimInterfaceName, 
				MyxUtils.DEFAULT_PATH, aimBrickName, aimInterfaceName));
		
		//TODO: Add an event pump here.
		myxRuntime.begin(MyxUtils.DEFAULT_PATH, xarchadtBrickName);
		myxRuntime.begin(MyxUtils.DEFAULT_PATH, myxRuntimeBrickName);
		myxRuntime.begin(MyxUtils.DEFAULT_PATH, aimBrickName);
		myxRuntime.begin(MyxUtils.DEFAULT_PATH, bootstrapBrickName);
	}
}
