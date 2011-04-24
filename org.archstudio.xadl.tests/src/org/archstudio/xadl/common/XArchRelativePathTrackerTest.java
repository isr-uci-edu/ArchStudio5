package org.archstudio.xadl.common;

import junit.framework.TestCase;

import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.core.XArchADTImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

public class XArchRelativePathTrackerTest extends TestCase {

	static {
		@SuppressWarnings("unused")
		EPackage p;
		p = Xadlcore_3_0Package.eINSTANCE;
		p = Structure_3_0Package.eINSTANCE;
	}

	IXArchADT xarch;
	int documentCount = 0;
	ObjRef documentRootRef;
	ObjRef xadlTypeRef;
	ObjRef structureRef;
	ObjRef componentRef;
	ObjRef interfaceRef;

	@Override
	protected void setUp() throws Exception {
		this.xarch = new XArchADTImpl();

		this.documentRootRef = xarch.createDocument(URI.createURI("urn://" + (++documentCount)));
		this.xadlTypeRef = xarch.create(Xadlcore_3_0Package.eNS_URI, "XADLType");
		xarch.set(documentRootRef, "XADL", xadlTypeRef);
		this.structureRef = xarch.create(Structure_3_0Package.eNS_URI, "Structure");
		xarch.add(xadlTypeRef, "TopLevelElement", structureRef);
		this.componentRef = xarch.create(Structure_3_0Package.eNS_URI, "Component");
		xarch.add(structureRef, "component", componentRef);
		this.interfaceRef = xarch.create(Structure_3_0Package.eNS_URI, "Interface");
		xarch.add(componentRef, "interface", interfaceRef);
	}

	public void testXArchRelativePathTracker() {
		// TODO: test XArchRelativePathTracker
	}
}
