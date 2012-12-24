package org.archstudio.xarchadt.common;

import junit.framework.TestCase;

import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.core.XArchADTImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

public abstract class AbstractXArchADTTest extends TestCase {

	protected IXArchADT xarch;
	protected String documentUrn = "urn://" + this.getClass().getName() + "/";
	protected ObjRef documentRootRef;

	protected IXArchADT createXArch() {
		return new XArchADTImpl();
	}

	protected void setUp() throws Exception {
		super.setUp();

		// Initialize core packages by simply referring to them
		@SuppressWarnings("unused")
		EPackage p;
		p = Xadlcore_3_0Package.eINSTANCE;
		p = Structure_3_0Package.eINSTANCE;

		xarch = createXArch();
		// ensure a unique, empty document for each test
		documentUrn += documentUrn.length();
		documentRootRef = xarch.createDocument(URI.createURI(documentUrn));
	}
}
