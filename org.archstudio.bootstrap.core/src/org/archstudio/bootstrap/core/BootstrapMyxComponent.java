package org.archstudio.bootstrap.core;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.eclipse.emf.common.util.URI;
import org.xml.sax.SAXException;

import org.archstudio.aim.ArchitectureInstantiationException;
import org.archstudio.aim.IAIM;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;

public class BootstrapMyxComponent extends AbstractMyxSimpleBrick implements IMyxDynamicBrick {

	public static final IMyxName INTERFACE_NAME_OUT_AIM = MyxUtils.createName("aim");
	public static final IMyxName INTERFACE_NAME_OUT_XARCH = MyxUtils.createName("xarch");

	protected IAIM aim = null;
	protected IXArchADT xarch = null;

	protected String uriString = null;

	public BootstrapMyxComponent() {
	}

	public void init() {
	}

	public void begin() {
		Thread t = new Thread() {
			public void run() {
				_begin();
			};
		};
		t.start();
	}

	public void _begin() {
		Properties initProperties = MyxUtils.getInitProperties(this);
		String uriString = initProperties.getProperty("uri");
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

	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_XARCH)) {
			xarch = (IXArchADT) serviceObject;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_AIM)) {
			aim = (IAIM) serviceObject;
		}
	}

	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_XARCH)) {
			xarch = null;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_AIM)) {
			aim = null;
		}
	}

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	public Object getServiceObject(IMyxName interfaceName) {
		return null;
	}

}
