package org.archstudio.xarchadt.common;

import javax.xml.xpath.XPathException;

import org.archstudio.xadl3.structure_3_0.Component;
import org.archstudio.xadl3.structure_3_0.Interface;
import org.archstudio.xadl3.structure_3_0.Structure;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Factory;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.DocumentRoot;
import org.archstudio.xadl3.xadlcore_3_0.XADLType;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Factory;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.emf.common.util.URI;

import com.google.common.collect.Lists;

public class XArchADTImplTest extends AbstractXArchADTTest {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		DocumentRoot documentRoot = XArchADTProxy.proxy(xarch, documentRootRef);
		documentRoot.setXADL(((Xadlcore_3_0Factory) XArchADTProxy.proxy(xarch, Xadlcore_3_0Package.eNS_URI))
				.createXADLType());
	}

	public void testCreate() {
		assertNotNull(documentRootRef);
		assertEquals(documentRootRef, xarch.getDocumentRootRef(URI.createURI(documentUrn)));
		assertEquals(documentUrn, xarch.getURI(documentRootRef).toString());
	}

	public void testXQuery() throws XPathException {
		Structure_3_0Factory structures = XArchADTProxy.proxy(xarch, Structure_3_0Package.eNS_URI);

		DocumentRoot documentRoot = XArchADTProxy.proxy(xarch, documentRootRef);
		XADLType xadlType = documentRoot.getXADL();
		Structure structure = structures.createStructure();
		xadlType.getTopLevelElement().add(structure);

		Component c1 = structures.createComponent();
		c1.setId("abc");
		Interface i1 = structures.createInterface();
		Interface i2 = structures.createInterface();
		c1.getInterface().add(i1);
		c1.getInterface().add(i2);

		Component c2 = structures.createComponent();
		c2.setId("xyz");

		Component c3 = structures.createComponent();
		c3.setId("pqr");

		Component c4 = structures.createComponent();
		c4.setId("def");
		Interface i3 = structures.createInterface();
		Interface i4 = structures.createInterface();
		c4.getInterface().add(i3);
		c4.getInterface().add(i4);

		Component c5 = structures.createComponent();
		c5.setId("stu");

		structure.getComponent().addAll(Lists.newArrayList(c1, c2, c3, c4, c5));

		assertEquals(XArchADTProxy.unproxy(Lists.newArrayList(i1, i2, i3, i4)),
				xarch.resolveObjRefs(XArchADTProxy.unproxy(xadlType), "/topLevelElement/component/interface"));
		assertEquals(XArchADTProxy.unproxy(Lists.newArrayList(i1, i2, i3, i4)),
				xarch.resolveObjRefs(XArchADTProxy.unproxy(structure), "/component/interface"));
		assertEquals(XArchADTProxy.unproxy(Lists.newArrayList(i1, i2)),
				xarch.resolveObjRefs(XArchADTProxy.unproxy(c1), "/interface"));
		assertEquals(XArchADTProxy.unproxy(Lists.newArrayList(i1, i2)),
				xarch.resolveObjRefs(XArchADTProxy.unproxy(structure), "/component[@id='abc']/interface"));
		assertEquals(XArchADTProxy.unproxy(Lists.newArrayList(i1, i2)), xarch.resolveObjRefs(
				XArchADTProxy.unproxy(xadlType), "/topLevelElement/component[@id='abc']/interface"));
		assertEquals(XArchADTProxy.unproxy(Lists.newArrayList(i3, i4)), xarch.resolveObjRefs(
				XArchADTProxy.unproxy(xadlType), "/topLevelElement/component[@id='def']/interface"));
		assertEquals(Lists.newArrayList(Structure_3_0Package.eNS_URI),
				xarch.resolveSerializables(XArchADTProxy.unproxy(xadlType), "namespace-uri(/*)"));
		assertEquals(
				XArchADTProxy.unproxy(Lists.newArrayList(i1, i2, i3, i4)),
				xarch.resolveObjRefs(XArchADTProxy.unproxy(xadlType), "/topLevelElement[namespace-uri()='"
						+ Structure_3_0Package.eINSTANCE.getNsURI() + "']/component/interface"));
	}
}
