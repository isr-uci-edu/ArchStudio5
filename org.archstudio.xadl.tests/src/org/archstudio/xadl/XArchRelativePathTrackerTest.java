package org.archstudio.xadl;

import java.util.List;

import junit.framework.TestCase;

import org.archstudio.xadl3.domain_3_0.DomainExtension;
import org.archstudio.xadl3.domain_3_0.Domain_3_0Factory;
import org.archstudio.xadl3.domain_3_0.Domain_3_0Package;
import org.archstudio.xadl3.hints_3_0.HintsExtension;
import org.archstudio.xadl3.hints_3_0.Hints_3_0Factory;
import org.archstudio.xadl3.hints_3_0.Hints_3_0Package;
import org.archstudio.xadl3.structure_3_0.Component;
import org.archstudio.xadl3.structure_3_0.Interface;
import org.archstudio.xadl3.structure_3_0.Structure;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Factory;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xadl3.structure_3_0.SubStructure;
import org.archstudio.xadl3.xadlcore_3_0.DocumentRoot;
import org.archstudio.xadl3.xadlcore_3_0.XADLType;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Factory;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.XArchADTProxy;
import org.archstudio.xarchadt.core.XArchADTImpl;
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
		p = Domain_3_0Package.eINSTANCE;
	}

	IXArchADT xarch;
	int documentCount = 0;

	Xadlcore_3_0Factory coreF;
	Structure_3_0Factory structureF;
	Hints_3_0Factory hintsF;
	Domain_3_0Factory domainF;

	DocumentRoot documentRoot;
	XADLType xadl;
	Structure structure;
	Component comp;
	Interface iface;
	SubStructure compSubStructure;
	Structure subStructure;
	Component subComp;

	XArchRelativePathTracker tracker;
	List<String> results;

	protected void setUp() throws Exception {
		xarch = new XArchADTImpl();

		coreF = XArchADTProxy.proxy(xarch, Xadlcore_3_0Package.eNS_URI);
		structureF = XArchADTProxy.proxy(xarch, Structure_3_0Package.eNS_URI);
		hintsF = XArchADTProxy.proxy(xarch, Hints_3_0Package.eNS_URI);
		domainF = XArchADTProxy.proxy(xarch, Domain_3_0Package.eNS_URI);

		documentRoot = XArchADTProxy.proxy(xarch, xarch.createDocument(URI.createURI("urn://" + ++documentCount)));
		xadl = coreF.createXADLType();
		structure = structureF.createStructure();
		comp = structureF.createComponent();
		iface = structureF.createInterface();
		compSubStructure = structureF.createSubStructure();
		subStructure = structureF.createStructure();
		subComp = structureF.createComponent();

		documentRoot.setXADL(xadl);
		xadl.getTopLevelElement().add(structure);
		structure.getComponent().add(comp);
		comp.getInterface().add(iface);
		comp.setSubStructure(compSubStructure);
		compSubStructure.setInnerStructureLink(subStructure);
		subStructure.getComponent().add(subComp);

		results = Lists.newArrayList();
		tracker = new XArchRelativePathTracker(xarch);
		tracker.addTrackerListener(new IXArchRelativePathTrackerListener() {

			public void processAdd(List<ObjRef> relLineageRefs, ObjRef objRef) {
				results.add("A:" + objRef);
			}

			public void processUpdate(List<ObjRef> relLineageRefs, String relPath, ObjRef objRef, XArchADTModelEvent evt) {
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

	private static String a(String... s) {
		StringBuffer sb = new StringBuffer();
		for (String s0 : s) {
			sb.append(s0);
			sb.append("\n");
		}
		return sb.toString();
	}

	private void assertEquals(String a) {
		assertEquals(a, a(results.toArray(new String[results.size()])));
	}

	public void testTrack1() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();
		assertEquals(a(e("A", comp)));

		structure.getComponent().remove(comp);
		assertEquals(a(e("A", comp), e("R", comp)));
	}

	public void testTrack1Attribute() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();

		assertEquals(a(e("A", comp)));
		comp.setId("SomeID");
		assertEquals(a(e("A", comp), e("U", comp)));
		comp.setId(null);
		assertEquals(a(e("A", comp), e("U", comp), e("U", comp)));
	}

	public void testTrack1Attribute2() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();
		assertEquals(a(e("A", comp)));

		iface.setId(null);
		assertEquals(a(e("A", comp)));
		iface.setId("SomeID");
		assertEquals(a(e("A", comp), e("U", comp)));
	}

	public void testTrack1Attributec() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();

		assertEquals(a(e("A", comp)));
		comp.setId("SomeID");
		assertEquals(a(e("A", comp), e("U", comp)));
		xarch.clear(XArchADTProxy.unproxy(comp), "id");
		assertEquals(a(e("A", comp), e("U", comp), e("U", comp)));
	}

	public void testTrack1Attribute2c() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();
		assertEquals(a(e("A", comp)));

		xarch.clear(XArchADTProxy.unproxy(iface), "id");
		assertEquals(a(e("A", comp)));
		iface.setId("SomeID");
		assertEquals(a(e("A", comp), e("U", comp)));
	}

	public void testTrack1Attribute3() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();
		assertEquals(a(e("A", comp)));

		iface.setId("SomeID");
		assertEquals(a(e("A", comp), e("U", comp)));
		iface.setId("SomeOtherId");
		assertEquals(a(e("A", comp), e("U", comp), e("U", comp)));
	}

	public void testTrack1Substructure1() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();

		assertEquals(a(e("A", comp)));
		comp.setSubStructure(null);
		assertEquals(a(e("A", comp), e("U", comp)));
		comp.setSubStructure(compSubStructure);
		assertEquals(a(e("A", comp), e("U", comp), e("U", comp)));
	}

	public void testTrack1Substructure1c() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();

		assertEquals(a(e("A", comp)));
		xarch.clear(XArchADTProxy.unproxy(comp), "SubStructure");
		assertEquals(a(e("A", comp), e("U", comp)));
		comp.setSubStructure(compSubStructure);
		assertEquals(a(e("A", comp), e("U", comp), e("U", comp)));
	}

	public void testTrack1Add1() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();
		assertEquals(a(e("A", comp)));

		Component comp2 = structureF.createComponent();

		structure.getComponent().add(comp2);
		assertEquals(a(e("A", comp), e("A", comp2)));
		structure.getComponent().remove(comp2);
		assertEquals(a(e("A", comp), e("A", comp2), e("R", comp2)));
	}

	public void testTrack1Add1_2() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();
		assertEquals(a(e("A", comp)));

		Component comp2 = structureF.createComponent();
		Interface iface2 = structureF.createInterface();

		structure.getComponent().add(comp2);
		assertEquals(a(e("A", comp), e("A", comp2)));
		comp2.getInterface().add(iface2);
		assertEquals(a(e("A", comp), e("A", comp2), e("U", comp2)));
		comp2.getInterface().remove(iface2);
		assertEquals(a(e("A", comp), e("A", comp2), e("U", comp2), e("U", comp2)));
		structure.getComponent().remove(comp2);
		assertEquals(a(e("A", comp), e("A", comp2), e("U", comp2), e("U", comp2), e("R", comp2)));
	}

	public void testTrack1Add2_1() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component");
		tracker.startScanning();
		assertEquals(a(e("A", comp)));

		Component comp2 = structureF.createComponent();
		Interface iface2 = structureF.createInterface();

		comp2.getInterface().add(iface2);
		assertEquals(a(e("A", comp)));
		structure.getComponent().add(comp2);
		assertEquals(a(e("A", comp), e("A", comp2)));
		structure.getComponent().remove(comp2);
		assertEquals(a(e("A", comp), e("A", comp2), e("R", comp2)));
		comp2.getInterface().remove(iface2);
		assertEquals(a(e("A", comp), e("A", comp2), e("R", comp2)));
	}

	public void testTrack2Add1_2() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component/interface");
		tracker.startScanning();
		assertEquals(a(e("A", iface)));

		Component comp2 = structureF.createComponent();
		Interface iface2 = structureF.createInterface();

		structure.getComponent().add(comp2);
		assertEquals(a(e("A", iface)));
		comp2.getInterface().add(iface2);
		assertEquals(a(e("A", iface), e("A", iface2)));
		comp2.getInterface().remove(iface2);
		assertEquals(a(e("A", iface), e("A", iface2), e("R", iface2)));
		structure.getComponent().remove(comp2);
		assertEquals(a(e("A", iface), e("A", iface2), e("R", iface2)));
	}

	public void testTrack2Add2_1() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component/interface");
		tracker.startScanning();
		assertEquals(a(e("A", iface)));

		Component comp2 = structureF.createComponent();
		Interface iface2 = structureF.createInterface();

		comp2.getInterface().add(iface2);
		assertEquals(a(e("A", iface)));
		structure.getComponent().add(comp2);
		assertEquals(a(e("A", iface), e("A", iface2)));
		structure.getComponent().remove(comp2);
		assertEquals(a(e("A", iface), e("A", iface2), e("R", iface2)));
		comp2.getInterface().remove(iface2);
		assertEquals(a(e("A", iface), e("A", iface2), e("R", iface2)));
	}

	public void testTrack2Add2_3() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), "component/interface");
		tracker.startScanning();
		assertEquals(a(e("A", iface)));

		Component comp2 = structureF.createComponent();
		Interface iface2 = structureF.createInterface();

		comp2.getInterface().add(iface2);
		assertEquals(a(e("A", iface)));
		structure.getComponent().add(comp2);
		assertEquals(a(e("A", iface), e("A", iface2)));

		HintsExtension compHintsExtension = hintsF.createHintsExtension();
		comp2.getExt().add(compHintsExtension);

		assertEquals(a(e("A", iface), e("A", iface2)));

		HintsExtension ifaceHintsExtension = hintsF.createHintsExtension();
		iface2.getExt().add(ifaceHintsExtension);

		assertEquals(a(e("A", iface), e("A", iface2), e("U", iface2)));
	}

	public void testExtension1() {
		tracker.setTrackInfo(XArchADTProxy.unproxy(structure), //
				"component/ext[*[namespace-uri()='" + Domain_3_0Package.eNS_URI + "']]");
		tracker.startScanning();
		assertEquals(a());

		DomainExtension domainExt = domainF.createDomainExtension();
		comp.getExt().add(domainExt);
		assertEquals(a(e("A", domainExt)));

		HintsExtension hintsExt = hintsF.createHintsExtension();
		comp.getExt().add(hintsExt);
		assertEquals(a(e("A", domainExt)));

		comp.getExt().remove(domainExt);
		assertEquals(a(e("A", domainExt), e("R", domainExt)));

		Component comp2 = structureF.createComponent();
		DomainExtension domainExt2 = domainF.createDomainExtension();
		comp2.getExt().add(domainExt2);
		HintsExtension hintsExt2 = hintsF.createHintsExtension();
		comp2.getExt().add(hintsExt2);
		structure.getComponent().add(comp2);

		assertEquals(a(e("A", domainExt), e("R", domainExt), e("A", domainExt2)));
	}
}
