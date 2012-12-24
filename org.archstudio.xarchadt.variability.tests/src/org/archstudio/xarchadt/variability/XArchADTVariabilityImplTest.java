package org.archstudio.xarchadt.variability;

import java.util.List;

import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl3.hints_3_0.HintsExtension;
import org.archstudio.xadl3.hints_3_0.Hints_3_0Package;
import org.archstudio.xadl3.structure_3_0.Component;
import org.archstudio.xadl3.structure_3_0.Direction;
import org.archstudio.xadl3.structure_3_0.Interface;
import org.archstudio.xadl3.structure_3_0.Structure;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Factory;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xadl3.variability_3_0.Variability_3_0Factory;
import org.archstudio.xadl3.variability_3_0.Variability_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.DocumentRoot;
import org.archstudio.xadl3.xadlcore_3_0.XADLType;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Factory;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.archstudio.xarchadt.common.AbstractXArchADTTest;
import org.archstudio.xarchadt.variability.IXArchADTVariability.ChangeStatus;
import org.archstudio.xarchadt.variability.core.XArchADTVariabilityImpl;

import com.google.common.collect.Lists;

@SuppressWarnings("unused")
public class XArchADTVariabilityImplTest extends AbstractXArchADTTest {

	IXArchADTVariability xarch;

	Xadlcore_3_0Package corePackage;
	Xadlcore_3_0Factory coreFactory;
	Variability_3_0Package varPackage;
	Variability_3_0Factory varFactory;
	Structure_3_0Package strPackage;
	Structure_3_0Factory strFactory;

	DocumentRoot documentRoot;
	XADLType xadlType;
	Structure structure;

	@Override
	protected IXArchADT createXArch() {
		return new XArchADTVariabilityImpl();
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();

		xarch = (IXArchADTVariability) super.xarch;

		corePackage = Xadlcore_3_0Package.eINSTANCE;
		coreFactory = XArchADTProxy.proxy(xarch, corePackage.getNsURI());
		varPackage = Variability_3_0Package.eINSTANCE;
		varFactory = XArchADTProxy.proxy(xarch, varPackage.getNsURI());
		strPackage = Structure_3_0Package.eINSTANCE;
		strFactory = XArchADTProxy.proxy(xarch, strPackage.getNsURI());

		documentRoot = XArchADTProxy.proxy(xarch, documentRootRef);
		xadlType = coreFactory.createXADLType();
		documentRoot.setXADL(xadlType);
		structure = strFactory.createStructure();
		structure.setId(UIDGenerator.generateUID());
		xadlType.getTopLevelElement().add(structure);
	}

	public void testAttribute() {
		xarch.setChangeSetsEnabled(documentRootRef, true);
		List<ObjRef> originalChangeSets = xarch.getAppliedChangeSets(documentRootRef);
		structure.setName("A");
		List<ObjRef> updatedChangeSets = VariabilityUtils.createAndApplyChangeSet(xarch, documentRootRef,
				"Rename Structure");
		structure.setName("B");
		assertEquals("B", structure.getName());
		xarch.applyChangeSets(documentRootRef, originalChangeSets);
		assertEquals("A", structure.getName());
		xarch.applyChangeSets(documentRootRef, updatedChangeSets);
		assertEquals("B", structure.getName());
	}

	public void testAttributeReset() {
		xarch.setChangeSetsEnabled(documentRootRef, true);
		List<ObjRef> originalChangeSets = xarch.getAppliedChangeSets(documentRootRef);
		structure.setName("A");
		List<ObjRef> updatedChangeSets = VariabilityUtils.createAndApplyChangeSet(xarch, documentRootRef,
				"Rename Structure");
		structure.setName("B");
		assertEquals("B", structure.getName());
		xarch.applyChangeSets(documentRootRef, originalChangeSets);
		assertEquals("A", structure.getName());
		structure.setName("B");
		assertEquals("A", structure.getName());
		xarch.applyChangeSets(documentRootRef, updatedChangeSets);
		assertEquals("B", structure.getName());
	}

	public void testAdd() {
		xarch.setChangeSetsEnabled(documentRootRef, true);
		List<ObjRef> originalChangeSets = xarch.getAppliedChangeSets(documentRootRef);
		List<ObjRef> updatedChangeSets = VariabilityUtils.createAndApplyChangeSet(xarch, documentRootRef,
				"Add Component");
		Component c = strFactory.createComponent();
		String oldID;
		c.setId(oldID = UIDGenerator.generateUID());
		structure.getComponent().add(c);
		assertEquals(oldID, c.getId());
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		assertEquals(structure, c.eContainer());
		xarch.applyChangeSets(documentRootRef, originalChangeSets);
		assertEquals(oldID, c.getId());
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		assertEquals(null, c.eContainer());
		xarch.applyChangeSets(documentRootRef, updatedChangeSets);
		assertEquals(oldID, c.getId());
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		assertEquals(structure, c.eContainer());
	}

	public void testExplicitAdd() {
		xarch.setChangeSetsEnabled(documentRootRef, true);
		List<ObjRef> updatedChangeSets = VariabilityUtils.createAndApplyChangeSet(xarch, documentRootRef,
				"Add Component");
		Component c = strFactory.createComponent();
		String oldID;
		c.setId(oldID = UIDGenerator.generateUID());
		structure.getComponent().add(c);
		assertEquals(oldID, c.getId());
		assertEquals(structure, c.eContainer());
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		xarch.setExplicitChangeSets(documentRootRef, Lists.newArrayList(updatedChangeSets.get(1)));
		assertEquals(oldID, c.getId());
		assertEquals(structure, c.eContainer());
		assertEquals(ChangeStatus.EXPLICITLY_ADDED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		xarch.setExplicitChangeSets(documentRootRef, Lists.<ObjRef> newArrayList());
		assertEquals(oldID, c.getId());
		assertEquals(structure, c.eContainer());
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
	}

	public void testOverviewAdd() {
		xarch.setChangeSetsEnabled(documentRootRef, true);
		List<ObjRef> originalChangeSets = xarch.getAppliedChangeSets(documentRootRef);
		List<ObjRef> updatedChangeSets = VariabilityUtils.createAndApplyChangeSet(xarch, documentRootRef,
				"Add Component");
		Component c = strFactory.createComponent();
		String oldID;
		c.setId(oldID = UIDGenerator.generateUID());
		structure.getComponent().add(c);
		xarch.applyChangeSets(documentRootRef, originalChangeSets);
		assertEquals(oldID, c.getId());
		assertEquals(null, c.eContainer());
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		xarch.setOverviewModeEnabled(documentRootRef, true);
		assertEquals(oldID, c.getId());
		assertEquals(structure, c.eContainer());
		assertEquals(ChangeStatus.OVERVIEW, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		xarch.setOverviewModeEnabled(documentRootRef, false);
		assertEquals(oldID, c.getId());
		assertEquals(null, c.eContainer());
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
	}

	public void testRemove() {
		xarch.setChangeSetsEnabled(documentRootRef, true);
		List<ObjRef> originalChangeSets = xarch.getAppliedChangeSets(documentRootRef);
		Component c = strFactory.createComponent();
		String oldID;
		c.setId(oldID = UIDGenerator.generateUID());
		structure.getComponent().add(c);
		assertEquals(oldID, c.getId());
		assertEquals(structure, c.eContainer());
		List<ObjRef> updatedChangeSets = VariabilityUtils.createAndApplyChangeSet(xarch, documentRootRef,
				"Remove Component");
		structure.getComponent().remove(c);
		assertEquals(oldID, c.getId());
		assertEquals(null, c.eContainer());
		xarch.applyChangeSets(documentRootRef, originalChangeSets);
		// Note: the old objref should be used here
		assertEquals(oldID, c.getId());
		assertEquals(structure, c.eContainer());
		xarch.applyChangeSets(documentRootRef, updatedChangeSets);
		assertEquals(oldID, c.getId());
		assertEquals(null, c.eContainer());
	}

	public void testExplicitRemove() {
		xarch.setChangeSetsEnabled(documentRootRef, true);
		Component c = strFactory.createComponent();
		String oldID;
		c.setId(oldID = UIDGenerator.generateUID());
		structure.getComponent().add(c);
		assertEquals(structure, c.eContainer());
		List<ObjRef> updatedChangeSets = VariabilityUtils.createAndApplyChangeSet(xarch, documentRootRef,
				"Remove Component");
		structure.getComponent().remove(c);
		assertEquals(oldID, c.getId());
		assertEquals(null, c.eContainer());
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		xarch.setExplicitChangeSets(documentRootRef, Lists.newArrayList(updatedChangeSets.get(1)));
		assertEquals(oldID, c.getId());
		assertEquals(structure, c.eContainer());
		assertEquals(ChangeStatus.EXPLICITLY_REMOVED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		xarch.setExplicitChangeSets(documentRootRef, Lists.<ObjRef> newArrayList());
		assertEquals(oldID, c.getId());
		assertEquals(null, c.eContainer());
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
	}

	public void testExplicitAddWithRemove() {
		xarch.setChangeSetsEnabled(documentRootRef, true);
		Component c = strFactory.createComponent();
		String oldID;
		c.setId(oldID = UIDGenerator.generateUID());
		structure.getComponent().add(c);
		assertEquals(structure, c.eContainer());
		List<ObjRef> updatedChangeSets = VariabilityUtils.createAndApplyChangeSet(xarch, documentRootRef,
				"Remove Component");
		structure.getComponent().remove(c);
		assertEquals(oldID, c.getId());
		assertEquals(null, c.eContainer());
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		xarch.setExplicitChangeSets(documentRootRef, Lists.newArrayList(updatedChangeSets.get(0)));
		assertEquals(oldID, c.getId());
		assertEquals(structure, c.eContainer());
		assertEquals(ChangeStatus.EXPLICITLY_ADDED_BUT_REALLY_REMOVED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		xarch.setExplicitChangeSets(documentRootRef, Lists.<ObjRef> newArrayList());
		assertEquals(oldID, c.getId());
		assertEquals(null, c.eContainer());
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
	}

	public void testOverviewRemove() {
		xarch.setChangeSetsEnabled(documentRootRef, true);
		List<ObjRef> originalChangeSets = xarch.getAppliedChangeSets(documentRootRef);
		Component c = strFactory.createComponent();
		String oldID;
		c.setId(oldID = UIDGenerator.generateUID());
		structure.getComponent().add(c);
		List<ObjRef> updatedChangeSets = VariabilityUtils.createAndApplyChangeSet(xarch, documentRootRef,
				"Remove Component");
		structure.getComponent().remove(c);
		assertEquals(oldID, c.getId());
		assertEquals(null, c.eContainer());
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		xarch.setOverviewModeEnabled(documentRootRef, true);
		assertEquals(oldID, c.getId());
		assertEquals(structure, c.eContainer());
		assertEquals(ChangeStatus.OVERVIEW, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		xarch.setOverviewModeEnabled(documentRootRef, false);
		assertEquals(oldID, c.getId());
		assertEquals(null, c.eContainer());
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
	}

	public void testModifyParents() {
		xarch.setChangeSetsEnabled(documentRootRef, true);
		List<ObjRef> originalChangeSets = xarch.getAppliedChangeSets(documentRootRef);
		Component c = strFactory.createComponent();
		String oldID;
		c.setId(oldID = UIDGenerator.generateUID());
		structure.getComponent().add(c);
		List<ObjRef> updatedChangeSets = VariabilityUtils.createAndApplyChangeSet(xarch, documentRootRef,
				"Add Interface");
		xarch.setExplicitChangeSets(documentRootRef, Lists.newArrayList(updatedChangeSets.get(1)));
		assertEquals(oldID, c.getId());
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(structure)));
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		Interface i = strFactory.createInterface();
		i.setId(UIDGenerator.generateUID());
		c.getInterface().add(i);
		assertEquals(ChangeStatus.EXPLICITLY_MODIFIED, xarch.getChangeStatus(XArchADTProxy.unproxy(structure)));
		assertEquals(ChangeStatus.EXPLICITLY_MODIFIED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		assertEquals(ChangeStatus.EXPLICITLY_ADDED, xarch.getChangeStatus(XArchADTProxy.unproxy(i)));
	}

	public void testModifyParentsViaAttribute() {
		xarch.setChangeSetsEnabled(documentRootRef, true);
		List<ObjRef> originalChangeSets = xarch.getAppliedChangeSets(documentRootRef);
		Component c = strFactory.createComponent();
		String oldID;
		c.setId(oldID = UIDGenerator.generateUID());
		structure.getComponent().add(c);
		Interface i = strFactory.createInterface();
		i.setId(UIDGenerator.generateUID());
		c.getInterface().add(i);
		List<ObjRef> updatedChangeSets = VariabilityUtils.createAndApplyChangeSet(xarch, documentRootRef,
				"Modify Interface");
		xarch.setExplicitChangeSets(documentRootRef, Lists.newArrayList(updatedChangeSets.get(1)));
		assertEquals(oldID, c.getId());
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(structure)));
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(i)));
		i.setDirection(Direction.NONE);
		assertEquals(ChangeStatus.EXPLICITLY_MODIFIED, xarch.getChangeStatus(XArchADTProxy.unproxy(structure)));
		assertEquals(ChangeStatus.EXPLICITLY_MODIFIED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		assertEquals(ChangeStatus.EXPLICITLY_MODIFIED, xarch.getChangeStatus(XArchADTProxy.unproxy(i)));
	}

	public void testInitialCreate() {
		Component c = strFactory.createComponent();
		String oldID;
		c.setId(oldID = UIDGenerator.generateUID());
		structure.getComponent().add(c);
		Interface i = strFactory.createInterface();
		i.setId(UIDGenerator.generateUID());
		c.getInterface().add(i);
		HintsExtension e = XArchADTProxy.getExtension(xarch, i, Hints_3_0Package.Literals.HINTS_EXTENSION, true);
		xarch.setChangeSetsEnabled(documentRootRef, true);
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(structure)));
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(i)));
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(e)));
		xarch.applyChangeSets(documentRootRef, xarch.getAppliedChangeSets(documentRootRef));
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(structure)));
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(i)));
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(e)));
		xarch.setExplicitChangeSets(documentRootRef, Lists.<ObjRef> newArrayList());
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(structure)));
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(i)));
		assertEquals(ChangeStatus.ATTACHED, xarch.getChangeStatus(XArchADTProxy.unproxy(e)));
	}

	public void testOverviewWithDependent() {
		xarch.setChangeSetsEnabled(documentRootRef, true);
		List<ObjRef> originalChangeSets = xarch.getAppliedChangeSets(documentRootRef);
		List<ObjRef> updatedChangeSets = VariabilityUtils.createAndApplyChangeSet(xarch, documentRootRef,
				"Add Component");
		Component c = strFactory.createComponent();
		String oldID;
		c.setId(oldID = UIDGenerator.generateUID());
		structure.getComponent().add(c);
		List<ObjRef> allChangeSets = VariabilityUtils.createAndApplyChangeSet(xarch, documentRootRef, "Add Interface");
		Interface i = strFactory.createInterface();
		i.setId(UIDGenerator.generateUID());
		c.getInterface().add(i);
		xarch.applyChangeSets(documentRootRef, Lists.<ObjRef> newArrayList());
		xarch.setOverviewModeEnabled(documentRootRef, true);
		assertEquals(ChangeStatus.OVERVIEW, xarch.getChangeStatus(XArchADTProxy.unproxy(structure)));
		assertEquals(ChangeStatus.OVERVIEW, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		assertEquals(ChangeStatus.OVERVIEW, xarch.getChangeStatus(XArchADTProxy.unproxy(i)));
		xarch.applyChangeSets(documentRootRef, updatedChangeSets.subList(1, 2));
		assertEquals(ChangeStatus.OVERVIEW, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		assertEquals(ChangeStatus.OVERVIEW, xarch.getChangeStatus(XArchADTProxy.unproxy(structure)));
		assertEquals(ChangeStatus.OVERVIEW, xarch.getChangeStatus(XArchADTProxy.unproxy(i)));
		xarch.applyChangeSets(documentRootRef, allChangeSets.subList(2, 3));
		assertEquals(ChangeStatus.OVERVIEW, xarch.getChangeStatus(XArchADTProxy.unproxy(structure)));
		assertEquals(ChangeStatus.OVERVIEW, xarch.getChangeStatus(XArchADTProxy.unproxy(c)));
		assertEquals(ChangeStatus.OVERVIEW, xarch.getChangeStatus(XArchADTProxy.unproxy(i)));
	}
}
