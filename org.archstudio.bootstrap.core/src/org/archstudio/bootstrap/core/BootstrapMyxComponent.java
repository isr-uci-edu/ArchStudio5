package org.archstudio.bootstrap.core;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.archstudio.aim.ArchitectureInstantiationException;
import org.archstudio.myx.fw.MyxNullProgressMonitor;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.emf.common.util.URI;
import org.xml.sax.SAXException;

/**
 * Myx brick: "Bootstrap Impl"
 * 
 * @see org.archstudio.bootstrap.core.BootstrapMyxComponentStub
 * @generated
 */
public class BootstrapMyxComponent extends org.archstudio.bootstrap.core.BootstrapMyxComponentStub {

	protected String uriString = null;

	@Override
	public void begin() {
		Thread t = new Thread() {

			@Override
			public void run() {
				_begin();
			};
		};
		t.start();
	}

	public void _begin() {
		Map<String, String> initProperties = MyxUtils.getInitProperties(this);
		String uriString = initProperties.get("uri");
		if (uriString == null) {
			throw new RuntimeException("No 'uri' property in initialization properties for bootstrapper.");
		}
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

			aim.instantiate("system", docRootRef, structureRefs.get(0), new MyxNullProgressMonitor());
			aim.begin("system", new MyxNullProgressMonitor());
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
}