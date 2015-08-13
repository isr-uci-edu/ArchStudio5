package org.archstudio.xadl;

import java.util.Arrays;
import java.util.List;

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

import junit.framework.TestCase;

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

	@Override
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
	}

	private void initTracker(ObjRef rootRef, String xPath) {
		tracker = new XArchRelativePathTracker(xarch, rootRef, xPath, false);
		tracker.addTrackerListener(new IXArchRelativePathTrackerListener() {
			@Override
			public void processAdd(List<ObjRef> descendantRefs, ObjRef addedRef) {
				results.add("A:" + addedRef);
			}

			@Override
			public void processUpdate(List<ObjRef> descendantRefs, String descendantPath, ObjRef modifiedRef,
					XArchADTModelEvent relativeEvt) {
				results.add("U:" + modifiedRef + ":" + descendantPath);
			}

			@Override
			public void processRemove(List<ObjRef> descendantRefs, ObjRef objRef) {
				results.add("R:" + objRef);
			}
		});
		((XArchADTImpl) xarch).addXArchADTModelListener(tracker);
		tracker.startScanning();
	}

	private static String a(EObject o) {
		return "A:" + XArchADTProxy.unproxy(o);
	}

	private static String u(EObject o, String path) {
		return "U:" + XArchADTProxy.unproxy(o) + ":" + path;
	}

	private static String r(EObject o) {
		return "R:" + XArchADTProxy.unproxy(o);
	}

	private void assertEvents(String... a) {
		assertEquals(Arrays.asList(a), results);
	}

	public void testTrack1() {
		initTracker(XArchADTProxy.unproxy(structure), "component");
		assertEvents(a(comp));

		structure.getComponent().remove(comp);
		assertEvents(a(comp), r(comp));
	}

	public void testTrack1Attribute() {
		initTracker(XArchADTProxy.unproxy(structure), "component");

		assertEvents(a(comp));
		comp.setId("SomeID");
		assertEvents(a(comp), u(comp, ""));
		comp.setId(null);
		assertEvents(a(comp), u(comp, ""), u(comp, ""));
	}

	public void testTrack1Attribute2() {
		initTracker(XArchADTProxy.unproxy(structure), "component");
		assertEvents(a(comp));

		iface.setId(null);
		assertEvents(a(comp));
		iface.setId("SomeID");
		assertEvents(a(comp), u(comp, "interface"));
	}

	public void testTrack1Attributec() {
		initTracker(XArchADTProxy.unproxy(structure), "component");

		assertEvents(a(comp));
		comp.setId("SomeID");
		assertEvents(a(comp), u(comp, ""));
		xarch.clear(XArchADTProxy.unproxy(comp), "id");
		assertEvents(a(comp), u(comp, ""), u(comp, ""));
	}

	public void testTrack1Attribute2c() {
		initTracker(XArchADTProxy.unproxy(structure), "component");
		assertEvents(a(comp));

		xarch.clear(XArchADTProxy.unproxy(iface), "id");
		assertEvents(a(comp));
		iface.setId("SomeID");
		assertEvents(a(comp), u(comp, "interface"));
	}

	public void testTrack1Attribute3() {
		initTracker(XArchADTProxy.unproxy(structure), "component");
		assertEvents(a(comp));

		iface.setId("SomeID");
		assertEvents(a(comp), u(comp, "interface"));
		iface.setId("SomeOtherId");
		assertEvents(a(comp), u(comp, "interface"), u(comp, "interface"));
	}

	public void testTrack1Substructure1() {
		initTracker(XArchADTProxy.unproxy(structure), "component");

		assertEvents(a(comp));
		comp.setSubStructure(null);
		assertEvents(a(comp), u(comp, ""));
		comp.setSubStructure(compSubStructure);
		assertEvents(a(comp), u(comp, ""), u(comp, ""));
		compSubStructure.setId("id");
		assertEvents(a(comp), u(comp, ""), u(comp, ""), u(comp, "subStructure"));
	}

	public void testTrack1Add1() {
		initTracker(XArchADTProxy.unproxy(structure), "component");
		assertEvents(a(comp));

		Component comp2 = structureF.createComponent();

		structure.getComponent().add(comp2);
		assertEvents(a(comp), a(comp2));
		structure.getComponent().remove(comp2);
		assertEvents(a(comp), a(comp2), r(comp2));
	}

	public void testTrack1Add1_2() {
		initTracker(XArchADTProxy.unproxy(structure), "component");
		assertEvents(a(comp));

		Component comp2 = structureF.createComponent();
		Interface iface2 = structureF.createInterface();

		structure.getComponent().add(comp2);
		assertEvents(a(comp), a(comp2));
		comp2.getInterface().add(iface2);
		assertEvents(a(comp), a(comp2), u(comp2, ""));
		iface2.setId("id");
		assertEvents(a(comp), a(comp2), u(comp2, ""), u(comp2, "interface"));
		comp2.getInterface().remove(iface2);
		assertEvents(a(comp), a(comp2), u(comp2, ""), u(comp2, "interface"), u(comp2, ""));
		structure.getComponent().remove(comp2);
		assertEvents(a(comp), a(comp2), u(comp2, ""), u(comp2, "interface"), u(comp2, ""), r(comp2));
	}

	public void testTrack1Add2_1() {
		initTracker(XArchADTProxy.unproxy(structure), "component");
		assertEvents(a(comp));

		Component comp2 = structureF.createComponent();
		Interface iface2 = structureF.createInterface();

		comp2.getInterface().add(iface2);
		assertEvents(a(comp));
		structure.getComponent().add(comp2);
		assertEvents(a(comp), a(comp2));
		structure.getComponent().remove(comp2);
		assertEvents(a(comp), a(comp2), r(comp2));
		comp2.getInterface().remove(iface2);
		assertEvents(a(comp), a(comp2), r(comp2));
	}

	public void testTrack2Add1_2() {
		initTracker(XArchADTProxy.unproxy(structure), "component/interface");
		assertEvents(a(iface));

		Component comp2 = structureF.createComponent();
		Interface iface2 = structureF.createInterface();

		structure.getComponent().add(comp2);
		assertEvents(a(iface));
		comp2.getInterface().add(iface2);
		assertEvents(a(iface), a(iface2));
		comp2.getInterface().remove(iface2);
		assertEvents(a(iface), a(iface2), r(iface2));
		structure.getComponent().remove(comp2);
		assertEvents(a(iface), a(iface2), r(iface2));
	}

	public void testTrack2Add2_1() {
		initTracker(XArchADTProxy.unproxy(structure), "component/interface");
		assertEvents(a(iface));

		Component comp2 = structureF.createComponent();
		Interface iface2 = structureF.createInterface();

		comp2.getInterface().add(iface2);
		assertEvents(a(iface));
		structure.getComponent().add(comp2);
		assertEvents(a(iface), a(iface2));
		structure.getComponent().remove(comp2);
		assertEvents(a(iface), a(iface2), r(iface2));
		comp2.getInterface().remove(iface2);
		assertEvents(a(iface), a(iface2), r(iface2));
	}

	public void testTrack2Add2_3() {
		initTracker(XArchADTProxy.unproxy(structure), "component/interface");
		assertEvents(a(iface));

		Component comp2 = structureF.createComponent();
		Interface iface2 = structureF.createInterface();

		comp2.getInterface().add(iface2);
		assertEvents(a(iface));
		structure.getComponent().add(comp2);
		assertEvents(a(iface), a(iface2));

		HintsExtension compHintsExtension = hintsF.createHintsExtension();
		comp2.getExt().add(compHintsExtension);

		assertEvents(a(iface), a(iface2));

		HintsExtension ifaceHintsExtension = hintsF.createHintsExtension();
		iface2.getExt().add(ifaceHintsExtension);

		assertEvents(a(iface), a(iface2), u(iface2, ""));
	}

	public void testExtension1() {
		initTracker(XArchADTProxy.unproxy(structure), //
				"component/ext[*[namespace-uri()='" + Domain_3_0Package.eNS_URI + "']]");
		assertEvents();

		DomainExtension domainExt = domainF.createDomainExtension();
		comp.getExt().add(domainExt);
		assertEvents(a(domainExt));

		HintsExtension hintsExt = hintsF.createHintsExtension();
		comp.getExt().add(hintsExt);
		assertEvents(a(domainExt));

		comp.getExt().remove(domainExt);
		assertEvents(a(domainExt), r(domainExt));

		Component comp2 = structureF.createComponent();
		DomainExtension domainExt2 = domainF.createDomainExtension();
		comp2.getExt().add(domainExt2);
		HintsExtension hintsExt2 = hintsF.createHintsExtension();
		comp2.getExt().add(hintsExt2);
		structure.getComponent().add(comp2);

		assertEvents(a(domainExt), r(domainExt), a(domainExt2));
	}

	public void testAttributeChange() {
		initTracker(XArchADTProxy.unproxy(structure), //
				"component[contains(@name, 'x')]");

		Component comp1 = structureF.createComponent();
		comp1.setName("1x");
		structure.getComponent().add(comp1);
		assertEvents(a(comp1));

		Component comp2 = structureF.createComponent();
		structure.getComponent().add(comp2);
		assertEvents(a(comp1));
		comp2.setName("2x");
		assertEvents(a(comp1), a(comp2));

		comp1.setName("1y");
		assertEvents(a(comp1), a(comp2), r(comp1));

		comp2.setName("2y");
		assertEvents(a(comp1), a(comp2), r(comp1), r(comp2));
	}

	public void testChildAttributeChange() {
		initTracker(XArchADTProxy.unproxy(structure), //
				"component[contains(@name, 'x')]/interface");

		Component comp1 = structureF.createComponent();
		Interface iface1a = structureF.createInterface();
		comp1.getInterface().add(iface1a);

		comp1.setName("1x");
		structure.getComponent().add(comp1);
		assertEvents(a(iface1a));

		Component comp2 = structureF.createComponent();
		Interface iface2a = structureF.createInterface();
		comp2.getInterface().add(iface2a);

		structure.getComponent().add(comp2);
		assertEvents(a(iface1a));
		comp2.setName("2x");
		assertEvents(a(iface1a), a(iface2a));

		comp1.setName("1y");
		assertEvents(a(iface1a), a(iface2a), r(iface1a));

		comp2.setName("2y");
		assertEvents(a(iface1a), a(iface2a), r(iface1a), r(iface2a));
	}
}
