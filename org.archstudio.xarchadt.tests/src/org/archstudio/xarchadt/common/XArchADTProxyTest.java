package org.archstudio.xarchadt.common;

import org.archstudio.xadl3.structure_3_0.Component;
import org.archstudio.xadl3.structure_3_0.Structure;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Factory;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xadl3.structure_3_0.SubStructure;
import org.archstudio.xadl3.xadlcore_3_0.DocumentRoot;
import org.archstudio.xadl3.xadlcore_3_0.XADLType;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Factory;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Sets;

public class XArchADTProxyTest extends AbstractXArchADTTest {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		DocumentRoot documentRoot = XArchADTProxy.proxy(xarch, documentRootRef);
		documentRoot.setXADL(((Xadlcore_3_0Factory) XArchADTProxy.proxy(xarch, Xadlcore_3_0Package.eNS_URI))
				.createXADLType());
	}

	private void assertConsistent(EObject eObject) {
		assertNotNull(eObject);
		assertNotNull(XArchADTProxy.unproxy(eObject));
		assertSame(eObject, XArchADTProxy.proxy(xarch, XArchADTProxy.unproxy(eObject)));
	}

	public void testCreateDocument() {
		DocumentRoot documentRoot = XArchADTProxy.proxy(xarch, documentRootRef);
		assertConsistent(documentRoot);
		XADLType xadlType = documentRoot.getXADL();
		assertConsistent(xadlType);
	}

	public void testCreate() {
		Structure_3_0Factory structures = XArchADTProxy.proxy(xarch, Structure_3_0Package.eNS_URI);
		Component component = structures.createComponent();
		assertConsistent(component);
	}

	public void testCache() {
		assertSame(XArchADTProxy.proxy(xarch, documentRootRef), XArchADTProxy.proxy(xarch, documentRootRef));
		DocumentRoot documentRoot = XArchADTProxy.proxy(xarch, documentRootRef);
		assertSame(documentRoot.getXADL(), documentRoot.getXADL());
	}

	public void testSetClear() {
		Structure_3_0Factory structures = XArchADTProxy.proxy(xarch, Structure_3_0Package.eNS_URI);

		Component component = structures.createComponent();
		assertEquals(null, component.getId());
		component.setId("ID1");
		assertEquals("ID1", component.getId());
		component.setId("ID2");
		assertEquals("ID2", component.getId());
		component.setId(null);
		assertEquals(null, component.getId());

		SubStructure subStructure = structures.createSubStructure();
		assertEquals(null, component.getSubStructure());
		component.setSubStructure(subStructure);
		assertEquals(subStructure, component.getSubStructure());
		component.setSubStructure(null);
		assertEquals(null, component.getSubStructure());
	}

	public void testAddRemove() {
		DocumentRoot documentRoot = XArchADTProxy.proxy(xarch, documentRootRef);
		assertConsistent(documentRoot);
		XADLType xadlType = documentRoot.getXADL();
		assertConsistent(xadlType);

		Structure_3_0Factory structures = XArchADTProxy.proxy(xarch, Structure_3_0Package.eNS_URI);
		Structure structure = structures.createStructure();
		assertConsistent(structure);
		xadlType.getTopLevelElement().add(structure);

		Component component1 = structures.createComponent();
		assertConsistent(component1);
		Component component2 = structures.createComponent();
		assertConsistent(component2);

		structure.getComponent().add(component1);
		assertEquals(Sets.newHashSet(component1), Sets.newHashSet(structure.getComponent()));

		structure.getComponent().add(component2);
		assertEquals(Sets.newHashSet(component1, component2), Sets.newHashSet(structure.getComponent()));

		structure.getComponent().remove(component1);
		assertEquals(Sets.newHashSet(component2), Sets.newHashSet(structure.getComponent()));

		structure.getComponent().remove(component2);
		assertEquals(Sets.newHashSet(), Sets.newHashSet(structure.getComponent()));
	}

	public void testEClass() {
		assertEquals(((DocumentRoot) XArchADTProxy.proxy(xarch, documentRootRef)).eClass(),
				Xadlcore_3_0Package.Literals.DOCUMENT_ROOT);
		assertEquals(((DocumentRoot) XArchADTProxy.proxy(xarch, documentRootRef)).eClass().getEPackage(),
				Xadlcore_3_0Package.Literals.DOCUMENT_ROOT.getEPackage());
	}
}
