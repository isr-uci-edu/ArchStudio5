package org.archstudio.eclipse.core.startup;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.archstudio.aim.ArchitectureInstantiationException;
import org.archstudio.aim.IAIM;
import org.archstudio.aim.core.AIMImpl;
import org.archstudio.myx.eclipse.MyxEclipseBrickLoader;
import org.archstudio.myx.eclipse.MyxEclipseClassManager;
import org.archstudio.myx.eclipse.MyxEclipseUtils;
import org.archstudio.myx.eclipse.MyxProgessMonitor;
import org.archstudio.myx.fw.IMyxBrickLoader;
import org.archstudio.myx.fw.IMyxProgressMonitor;
import org.archstudio.myx.fw.IMyxRuntime;
import org.archstudio.myx.fw.MyxBrickLoaderException;
import org.archstudio.myx.fw.MyxClassManagerException;
import org.archstudio.myx.fw.MyxJavaClassBrickLoader;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.myxgen.MyxGenEclipseBrickLoader;
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

	private static Object lock = new Object();
	private static boolean instantiated = false;

	@Override
	public void earlyStartup() {
		synchronized (lock) {
			if (!instantiated) {
				instantiated = true;
				//Job job = new Job("Initializing ArchStudio...") {
				//	@Override
				//	protected IStatus run(IProgressMonitor monitor) {
				//		instantiate(new MyxProgessMonitor(monitor));
				//		return Status.OK_STATUS;
				//	}
				//};
				//job.setPriority(Job.INTERACTIVE);
				//job.schedule();
				instantiate(new MyxProgessMonitor(new NullProgressMonitor()));
			}
		}
	}

	public static void instantiate() {
		new InstantiateArchStudio().earlyStartup();
	}

	public void instantiate(IMyxProgressMonitor monitor) {
		try {
			MyxEclipseUtils.register();

			final IXArchADT xarch = new XArchADTImpl();
			final IMyxRuntime myxRuntime = MyxUtils.getDefaultImplementation().createRuntime();
			final IAIM aim = new AIMImpl(xarch, myxRuntime);

			registerClassManager(myxRuntime, MyxEclipseClassManager.class);
			registerBrickLoader(myxRuntime, MyxGenEclipseBrickLoader.class);
			registerBrickLoader(myxRuntime, MyxEclipseBrickLoader.class);
			registerBrickLoader(myxRuntime, MyxJavaClassBrickLoader.class);

			ObjRef docRootRef = xarch.load(URI.createURI(ARCHSTUDIO_URI),
					SystemUtils.blt(ARCHSTUDIO_DESCRIPTION.openStream()));
			ObjRef xADLRef = (ObjRef) xarch.get(docRootRef, "xADL");
			if (xADLRef == null) {
				throw new RuntimeException("Can't find top-level xADL element in document: " + ARCHSTUDIO_URI);
			}
			List<ObjRef> structureRefs = XadlUtils.getAllSubstitutionGroupElementsByType(xarch, xADLRef,
					"topLevelElement", Structure_3_0Package.eNS_URI, "Structure");
			if (structureRefs.size() == 0) {
				throw new RuntimeException("Can't find structure element in document: " + ARCHSTUDIO_URI);
			}

			aim.instantiate("system", docRootRef, structureRefs.get(0), monitor);
			aim.begin("system", monitor);
		}
		catch (ArchitectureInstantiationException aie) {
			throw new RuntimeException("Can't instantiate architecture with URI " + ARCHSTUDIO_URI, aie);
		}
		catch (SAXException saxe) {
			throw new RuntimeException("Can't load file with URI " + ARCHSTUDIO_URI, saxe);
		}
		catch (IOException ioe) {
			throw new RuntimeException("Can't load file with URI " + ARCHSTUDIO_URI, ioe);
		}
		catch (Exception e) {
			throw new RuntimeException("Can't instantiate architecture with URI " + ARCHSTUDIO_URI, e);
		}
	}

	private void registerClassManager(IMyxRuntime myxRuntime, Class<? extends MyxEclipseClassManager> classManagerClass)
			throws MyxClassManagerException {
		myxRuntime.addClassManager(MyxUtils.createName(classManagerClass.getName()), classManagerClass, null);
	}

	private void registerBrickLoader(IMyxRuntime myxRuntime, Class<? extends IMyxBrickLoader> brickLoaderClass)
			throws MyxBrickLoaderException {
		myxRuntime.addBrickLoader(MyxUtils.createName(brickLoaderClass.getName()), brickLoaderClass, null);
	}
}
