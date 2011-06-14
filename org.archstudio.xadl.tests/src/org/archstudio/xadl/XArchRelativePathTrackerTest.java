package org.archstudio.xadl;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.archstudio.xadl3.hints_3_0.HintsExtension;
import org.archstudio.xadl3.hints_3_0.Hints_3_0Factory;
import org.archstudio.xadl3.hints_3_0.Hints_3_0Package;
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
		p = Hints_3_0Package.eINSTANCE;
	}

	IXArchADT xarch;
	int documentCount = 0;

	Xadlcore_3_0Factory coreF;
	Structure_3_0Factory structureF;
	Hints_3_0Factory hintsF;

	DocumentRoot documentRoot;
	XADLType xadl;
	Structure structure;
	Component comp;
	Interface iface;

	XArchRelativePathTracker tracker;
	List<String> results;

	@Override
	protected void setUp() throws Exception {
		xarch = new XArchADTImpl();

		coreF = XArchADTProxy.proxy(xarch, Xadlcore_3_0Package.eNS_URI);
		structureF = XArchADTProxy.proxy(xarch, Structure_3_0Package.eNS_URI);
		hintsF = XArchADTProxy.proxy(xarch, Hints_3_0Package.eNS_URI);

		documentRoot = XArchADTProxy.proxy(xarch, xarch.createDocument(URI.createURI("urn://" + ++documentCount)));
		xadl = coreF.createXADLType();
		structure = structureF.createStructure();
		comp = structureF.createComponent();
		iface = structureF.createInterface();

		documentRoot.setXADL(xadl);
		xadl.getTopLevelElement().add(structure);
		structure.getComponent().add(comp);
		comp.getInterface().add(iface);

		results = a();
		tracker = new XArchRelativePathTracker(xarch);
		tracker.addTrackerListener(new IXArchRelativePathTrackerListener() {

			public void processAdd(List<ObjRef> relLineageRefs, ObjRef objRef) {
				results.add("A:" + objRef);
			}

			public void processUpdate(List<ObjRef> relLineageRefs, XArchADTPath relPath, ObjRef objRef,
					XArchADTModelEvent evt) {
				results.add("U:" + objRef);
			}

			public void processRemove(List<ObjRef> relLineageRefs, ObjRef objRef) {
				results.add("R:" + objRef);
			}
		});
		((XArchADTImpl) xarch).addXArchADTModelListener(tracker);
	}

	private static String e(String s, EObject o) {
		return s + ":" + XArchADTProxy.unproxy(o);
	}

	private static List<String> a(String... s) {
		return Lists.newArrayList(Arrays.asList(s));
	}

	public void testTrack1() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();

		assertEquals(a(e("A", comp)), results);
		structure.getComponent().remove(comp);
		assertEquals(a(e("A", comp), e("R", comp)), results);
	}

	public void testTrack1Attribute() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();

		assertEquals(a(e("A", comp)), results);
		comp.setId("SomeID");
		assertEquals(a(e("A", comp), e("U", comp)), results);
		comp.setId(null);
		assertEquals(a(e("A", comp), e("U", comp), e("U", comp)), results);
	}

	public void testTrack1Attribute2() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();

		assertEquals(a(e("A", comp)), results);
		iface.setId("SomeID");
		assertEquals(a(e("A", comp), e("U", comp)), results);
		iface.setId(null);
		assertEquals(a(e("A", comp), e("U", comp), e("U", comp)), results);
	}

	public void testTrack1Add1() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();

		Component comp2 = structureF.createComponent();

		assertEquals(a(e("A", comp)), results);
		structure.getComponent().add(comp2);
		assertEquals(a(e("A", comp), e("A", comp2)), results);
		structure.getComponent().remove(comp2);
		assertEquals(a(e("A", comp), e("A", comp2), e("R", comp2)), results);
	}

	public void testTrack1Add1_2() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();

		Component comp2 = structureF.createComponent();
		Interface iface2 = structureF.createInterface();

		assertEquals(a(e("A", comp)), results);
		structure.getComponent().add(comp2);
		assertEquals(a(e("A", comp), e("A", comp2)), results);
		comp2.getInterface().add(iface2);
		assertEquals(a(e("A", comp), e("A", comp2), e("U", comp2)), results);
		comp2.getInterface().remove(iface2);
		assertEquals(a(e("A", comp), e("A", comp2), e("U", comp2), e("U", comp2)), results);
		structure.getComponent().remove(comp2);
		assertEquals(a(e("A", comp), e("A", comp2), e("U", comp2), e("U", comp2), e("R", comp2)), results);
	}

	public void testTrack1Add2_1() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();

		Component comp2 = structureF.createComponent();
		Interface iface2 = structureF.createInterface();

		assertEquals(a(e("A", comp)), results);
		comp2.getInterface().add(iface2);
		assertEquals(a(e("A", comp)), results);
		structure.getComponent().add(comp2);
		assertEquals(a(e("A", comp), e("A", comp2)), results);
		structure.getComponent().remove(comp2);
		assertEquals(a(e("A", comp), e("A", comp2), e("R", comp2)), results);
		comp2.getInterface().remove(iface2);
		assertEquals(a(e("A", comp), e("A", comp2), e("R", comp2)), results);
	}

	public void testTrack2Add1_2() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component/interface");
		tracker.startScanning();

		Component comp2 = structureF.createComponent();
		Interface iface2 = structureF.createInterface();

		assertEquals(a(e("A", iface)), results);
		structure.getComponent().add(comp2);
		assertEquals(a(e("A", iface)), results);
		comp2.getInterface().add(iface2);
		assertEquals(a(e("A", iface), e("A", iface2)), results);
		comp2.getInterface().remove(iface2);
		assertEquals(a(e("A", iface), e("A", iface2), e("R", iface2)), results);
		structure.getComponent().remove(comp2);
		assertEquals(a(e("A", iface), e("A", iface2), e("R", iface2)), results);
	}

	public void testTrack2Add2_1() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component/interface");
		tracker.startScanning();

		Component comp2 = structureF.createComponent();
		Interface iface2 = structureF.createInterface();

		assertEquals(a(e("A", iface)), results);
		comp2.getInterface().add(iface2);
		assertEquals(a(e("A", iface)), results);
		structure.getComponent().add(comp2);
		assertEquals(a(e("A", iface), e("A", iface2)), results);
		structure.getComponent().remove(comp2);
		assertEquals(a(e("A", iface), e("A", iface2), e("R", iface2)), results);
		comp2.getInterface().remove(iface2);
		assertEquals(a(e("A", iface), e("A", iface2), e("R", iface2)), results);
	}

	public void testTrack2Add2_3() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component/interface");
		tracker.startScanning();

		Component comp2 = structureF.createComponent();
		Interface iface2 = structureF.createInterface();

		assertEquals(a(e("A", iface)), results);
		comp2.getInterface().add(iface2);
		assertEquals(a(e("A", iface)), results);
		structure.getComponent().add(comp2);
		assertEquals(a(e("A", iface), e("A", iface2)), results);

		HintsExtension compHintsExtension = hintsF.createHintsExtension();
		comp2.getExt().add(compHintsExtension);

		assertEquals(a(e("A", iface), e("A", iface2)), results);

		HintsExtension ifaceHintsExtension = hintsF.createHintsExtension();
		iface2.getExt().add(ifaceHintsExtension);

		assertEquals(a(e("A", iface), e("A", iface2), e("U", iface2)), results);
	}
}
