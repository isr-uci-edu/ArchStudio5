package org.archstudio.eclipse.core.startup;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.archstudio.aim.ArchitectureInstantiationException;
import org.archstudio.aim.IAIM;
import org.archstudio.aim.core.AIMImpl;
import org.archstudio.myx.fw.IMyxProgressMonitor;
import org.archstudio.myx.fw.IMyxRuntime;
import org.archstudio.myx.fw.eclipse.MyxProgessMonitor;
import org.archstudio.myx.fw.equinox.MyxEquinoxRuntime;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.core.XArchADTImpl;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.ui.IStartup;
import org.xml.sax.SAXException;

public class InstantiateArchStudio implements IStartup {

	public static final URL ARCHSTUDIO_DESCRIPTION = InstantiateArchStudio.class.getResource("archstudio5.xml");
	public static final String ARCHSTUDIO_URI = ARCHSTUDIO_DESCRIPTION.toString();

	public InstantiateArchStudio() {
	}

	private final static Object lock = new Object();
	private static boolean instantiated = false;

	@Override
	public void earlyStartup() {
		synchronized (lock) {
			if (instantiated) {
				return;
			}
			instantiated = true;
		}
		//Job job = new Job("Initializing ArchStudio...") {
		//	
		//	protected IStatus run(IProgressMonitor monitor) {
		//		instantiate(new MyxProgessMonitor(monitor));
		//		return Status.OK_STATUS;
		//	}
		//};
		//job.setPriority(Job.INTERACTIVE);
		//job.schedule();
		new Thread(new Runnable() {

			@Override
			public void run() {
				instantiate(new MyxProgessMonitor(new NullProgressMonitor() {
					//protected PrintStream printStream = System.err;
					//
					//
					//public void beginTask(String name, int totalWork) {
					//	if (name != null && name.length() != 0) {
					//		printStream.println(">>> " + name);
					//	}
					//	super.beginTask(name, totalWork);
					//}
					//
					//
					//public void setTaskName(String name) {
					//	if (name != null && name.length() != 0) {
					//		printStream.println("<>> " + name);
					//	}
					//	super.setTaskName(name);
					//}
					//
					//
					//public void subTask(String name) {
					//	if (name != null && name.length() != 0) {
					//		printStream.println(">>  " + name);
					//	}
					//	super.subTask(name);
					//}
				}));
			}
		}).start();
	}

	public static void instantiate() {
		new InstantiateArchStudio().earlyStartup();
	}

	public void instantiate(IMyxProgressMonitor monitor) {
		// allow override of archstudio.xml file using org.archstudio.startup.uri
		try {
			File f = new File(System.getProperty("org.archstudio.startup.uri", null));
			if (f.exists()) {
				System.setProperty("org.archstudio.startup.uri", f.toURI().toString());
			}
		}
		catch (Throwable t) {
		}
		URI docRootURI = URI.createURI(System.getProperty("org.archstudio.startup.uri", ARCHSTUDIO_URI));
		try {
			final IXArchADT xarch = new XArchADTImpl();
			final IMyxRuntime myxRuntime = new MyxEquinoxRuntime();
			final IAIM aim = new AIMImpl(xarch, myxRuntime);

			InputStream docRootIS = new URL(docRootURI.toString()).openStream();
			ObjRef docRootRef = xarch.load(docRootURI, SystemUtils.blt(docRootIS));

			ObjRef xADLRef = (ObjRef) xarch.get(docRootRef, "xADL");
			if (xADLRef == null) {
				throw new RuntimeException("Can't find top-level xADL element in document: " + docRootURI);
			}
			List<ObjRef> structureRefs = XadlUtils.getAllSubstitutionGroupElementsByType(xarch, xADLRef,
					"topLevelElement", Structure_3_0Package.eNS_URI, "Structure");
			if (structureRefs.size() == 0) {
				throw new RuntimeException("Can't find structure element in document: " + docRootURI);
			}

			aim.instantiate("system", docRootRef, structureRefs.get(0), monitor);
			aim.begin("system", monitor);
		}
		catch (ArchitectureInstantiationException aie) {
			throw new RuntimeException("Can't instantiate architecture with URI " + docRootURI, aie);
		}
		catch (SAXException saxe) {
			throw new RuntimeException("Can't load file with URI " + docRootURI, saxe);
		}
		catch (IOException ioe) {
			throw new RuntimeException("Can't load file with URI " + docRootURI, ioe);
		}
		catch (Exception e) {
			throw new RuntimeException("Can't instantiate architecture with URI " + docRootURI, e);
		}
	}

}
