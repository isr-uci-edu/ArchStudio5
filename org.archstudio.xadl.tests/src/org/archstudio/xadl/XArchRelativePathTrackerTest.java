package org.archstudio.xadl;

import java.util.List;

import junit.framework.TestCase;

import org.archstudio.xadl3.structure_3_0.Component;
import org.archstudio.xadl3.structure_3_0.Interface;
import org.archstudio.xadl3.structure_3_0.Structure;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Factory;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.DocumentRoot;
import org.archstudio.xadl3.xadlcore_3_0.XADLType;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Factory;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.XArchADTPath;
import org.archstudio.xarchadt.core.XArchADTImpl;
import org.archstudio.xarchadt.core.XArchADTProxy;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import com.google.common.collect.Lists;

public class XArchRelativePathTrackerTest extends TestCase {

	static {
		@SuppressWarnings("unused")
		EPackage p;
		p = Xadlcore_3_0Package.eINSTANCE;
		p = Structure_3_0Package.eINSTANCE;
	}

	IXArchADT xarch;
	int documentCount = 0;

	Xadlcore_3_0Factory coreF;
	Structure_3_0Factory structureF;

	DocumentRoot documentRoot;
	XADLType xadl;
	Structure structure;
	Component component;
	Interface iface;

	XArchRelativePathTracker tracker;
	List<String> results;

	@Override
	protected void setUp() throws Exception {
		xarch = new XArchADTImpl();

		coreF = XArchADTProxy.proxy(xarch, Xadlcore_3_0Package.eNS_URI);
		structureF = XArchADTProxy.proxy(xarch, Structure_3_0Package.eNS_URI);

		documentRoot = XArchADTProxy.proxy(xarch, xarch.createDocument(URI.createURI("urn://" + ++documentCount)));
		xadl = coreF.createXADLType();
		structure = structureF.createStructure();
		component = structureF.createComponent();
		iface = structureF.createInterface();

		documentRoot.setXADL(xadl);
		xadl.getTopLevelElement().add(structure);
		structure.getComponent().add(component);
		component.getInterface().add(iface);

		results = Lists.newArrayList();
		tracker = new XArchRelativePathTracker(xarch);
		tracker.addTrackerListener(new IXArchRelativePathTrackerListener() {

			public void processUpdate(ObjRef objRef, List<ObjRef> relativeAncestorRefs, XArchADTModelEvent evt,
					XArchADTPath relativeSourceTargetPath) {
				results.add("U:" + objRef);
			}

			public void processRemove(ObjRef objRef, List<ObjRef> relativeAncestorRefs) {
				results.add("R:" + objRef);
			}

			public void processAdd(ObjRef objRef, List<ObjRef> relativeAncestorRefs) {
				results.add("A:" + objRef);
			}
		});
		((XArchADTImpl) xarch).addXArchADTModelListener(tracker);
	}

	private static String e(String s, EObject o) {
		return s + ":" + XArchADTProxy.unproxy(o);
	}

	public void testXArchRelativePathTracker() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();

		assertEquals(Lists.newArrayList(e("A", component)), results);

		structure.getComponent().remove(component);

		assertEquals(Lists.newArrayList(e("A", component), e("R", component)), results);
	}

	public void testXArchRelativePathTrackerSet() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();

		component.setId("SomeID");

		assertEquals(Lists.newArrayList(e("A", component), e("U", component)), results);

		component.setId(null);

		assertEquals(Lists.newArrayList(e("A", component), e("U", component), e("U", component)), results);
	}

	public void testXArchRelativePathTrackerAdd() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();

		Component component2 = structureF.createComponent();
		structure.getComponent().add(component2);

		assertEquals(Lists.newArrayList(e("A", component), e("A", component2)), results);

		structure.getComponent().remove(component2);

		assertEquals(Lists.newArrayList(e("A", component), e("A", component2), e("R", component2)), results);
	}

	public void testXArchRelativePathTrackerAdd2Child() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component/interface");
		tracker.startScanning();

		Component component2 = structureF.createComponent();
		structure.getComponent().add(component2);
		Interface iface2 = structureF.createInterface();
		component2.getInterface().add(iface2);

		assertEquals(Lists.newArrayList(e("A", iface), e("A", iface2)), results);

		component2.getInterface().remove(iface2);

		assertEquals(Lists.newArrayList(e("A", iface), e("A", iface2), e("R", iface2)), results);

		structure.getComponent().remove(component2);

		assertEquals(Lists.newArrayList(e("A", iface), e("A", iface2), e("R", iface2)), results);
	}

	public void testXArchRelativePathTrackerAdd2Child2() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component/interface");
		tracker.startScanning();

		Component component2 = structureF.createComponent();
		Interface iface2 = structureF.createInterface();
		component2.getInterface().add(iface2);
		structure.getComponent().add(component2);

		assertEquals(Lists.newArrayList(e("A", iface), e("A", iface2)), results);

		structure.getComponent().remove(component2);

		assertEquals(Lists.newArrayList(e("A", iface), e("A", iface2), e("R", iface2)), results);

		component2.getInterface().remove(iface2);

		assertEquals(Lists.newArrayList(e("A", iface), e("A", iface2), e("R", iface2)), results);
	}
}
