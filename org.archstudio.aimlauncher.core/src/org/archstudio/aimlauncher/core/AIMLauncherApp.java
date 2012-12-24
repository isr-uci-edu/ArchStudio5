package org.archstudio.aimlauncher.core;

import java.io.IOException;

import org.archstudio.aim.ArchitectureInstantiationException;
import org.archstudio.aim.IAIM;
import org.archstudio.aim.core.AIMImpl;
import org.archstudio.myx.fw.IMyxRuntime;
import org.archstudio.myx.fw.MyxNullProgressMonitor;
import org.archstudio.myx.fw.equinox.MyxEquinoxRuntime;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.core.XArchADTImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class AIMLauncherApp implements IApplication {

	public Object start(IApplicationContext context) throws Exception {
		// instantiates an architecture
		// - architecture is specified in "architecture" environment variable
		// - top level element is specified in the "topLevelElement" environment variable

		String architecture = System.getenv("architecture");
		String topLevelElement = System.getenv("topLevelElement");

		URI docRootURI = URI.createURI(System.getProperty("org.archstudio.startup.uri", "urn://aim-architecture"));
		try {
			final IXArchADT xarch = new XArchADTImpl();
			final IMyxRuntime myxRuntime = new MyxEquinoxRuntime();
			final IAIM aim = new AIMImpl(xarch, myxRuntime);

			ObjRef docRootRef = xarch.load(docRootURI, architecture.getBytes());
			ObjRef xADLRef = (ObjRef) xarch.get(docRootRef, "xADL");
			if (xADLRef == null) {
				throw new RuntimeException("Can't find top-level xADL element in document: " + architecture);
			}
			ObjRef structureRef = null;
			for (ObjRef candidateStructureRef : XadlUtils.getAllSubstitutionGroupElementsByType(xarch, xADLRef,
					"topLevelElement", Structure_3_0Package.eNS_URI, "Structure")) {
				if (topLevelElement.equals(xarch.get(candidateStructureRef, "name"))) {
					structureRef = candidateStructureRef;
				}
			}
			if (structureRef == null) {
				throw new RuntimeException("Can't find structure element in document: " + topLevelElement);
			}

			aim.instantiate("system", docRootRef, structureRef, new MyxNullProgressMonitor());
			aim.begin("system", new MyxNullProgressMonitor());
			while (true) {
				synchronized (this) {
					try {
						wait();
					}
					catch (InterruptedException e) {
					}
				}
			}
		}
		catch (ArchitectureInstantiationException aie) {
			throw new RuntimeException("Can't instantiate architecture: " + architecture, aie);
		}
		catch (IOException ioe) {
			throw new RuntimeException("Can't load architecture: " + architecture, ioe);
		}
		catch (Exception e) {
			throw new RuntimeException("Can't instantiate architecture: " + architecture, e);
		}
	}

	public void stop() {
	}

}
