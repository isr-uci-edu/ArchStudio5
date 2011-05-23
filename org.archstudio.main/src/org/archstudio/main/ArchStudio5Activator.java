package org.archstudio.main;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.archstudio.aim.ArchitectureInstantiationException;
import org.archstudio.aim.IAIM;
import org.archstudio.aim.core.AIMImpl;
import org.archstudio.myx.eclipse.MyxEclipseUtils;
import org.archstudio.myx.fw.IMyxRuntime;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.core.XArchADTImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.xml.sax.SAXException;

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
	@Override
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
	@Override
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

	private void startApplication() throws Exception {
		System.out.println("Starting app");

		Properties bootstrapInitProperties = new Properties();
		bootstrapInitProperties.put("uri", ARCHSTUDIO_DESCRIPTION_URI);

		MyxEclipseUtils.register();

		final IXArchADT xarch = new XArchADTImpl();
		final IMyxRuntime myxRuntime = MyxUtils.getDefaultImplementation().createRuntime();
		final IAIM aim = new AIMImpl(xarch, myxRuntime);

		myxRuntime.addClassManager(MyxUtils.createName("EclipseClassManager"),
				org.archstudio.myx.eclipse.MyxEclipseClassManager.class.getName(), null);
		myxRuntime.addBrickLoader(MyxUtils.createName("EclipseBrickLoader"),
				org.archstudio.myx.eclipse.MyxEclipseBrickLoader.class.getName(), null);
		myxRuntime.addBrickLoader(MyxUtils.createName("MyxGenEclipseBrickLoader"),
				org.archstudio.myxgen.MyxGenEclipseBrickLoader.class.getName(), null);

		new Thread(new Runnable() {
			@Override
			public void run() {
				String uriString = ARCHSTUDIO_DESCRIPTION_URI;
				try {
					ObjRef docRootRef = xarch.load(URI.createURI(uriString));
					ObjRef xADLRef = (ObjRef) xarch.get(docRootRef, "xADL");
					if (xADLRef == null) {
						throw new RuntimeException("Can't find top-level xADL element in document: " + uriString);
					}
					List<ObjRef> structureRefs = XadlUtils.getAllSubstitutionGroupElementsByType(xarch, xADLRef,
							"topLevelElement", Structure_3_0Package.eNS_URI, "Structure");
					if (structureRefs.size() == 0) {
						throw new RuntimeException("Can't find structure element in document: " + uriString);
					}

					aim.instantiate("system", docRootRef, structureRefs.get(0));
					aim.begin("system");
				}
				catch (ArchitectureInstantiationException aie) {
					aie.printStackTrace();
					throw new RuntimeException("Can't instantiate architecture with URI " + uriString, aie);
				}
				catch (SAXException saxe) {
					saxe.printStackTrace();
					throw new RuntimeException("Can't load file with URI " + uriString, saxe);
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
					throw new RuntimeException("Can't load file with URI " + uriString, ioe);
				}
			}
		}).start();
	}
}
