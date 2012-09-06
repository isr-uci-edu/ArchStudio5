package org.archstudio.xarchadt.variability;

import java.util.List;
import java.util.Set;

import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl3.variability_3_0.ChangeSet;
import org.archstudio.xadl3.variability_3_0.TransformChangeSetOfChanges;
import org.archstudio.xadl3.variability_3_0.Variability;
import org.archstudio.xadl3.variability_3_0.Variability_3_0Factory;
import org.archstudio.xadl3.variability_3_0.Variability_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.DocumentRoot;
import org.archstudio.xadl3.xadlcore_3_0.XADLType;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class VariabilityUtils {

	@Nullable
	public static final Variability getVariability(IXArchADT xarch, ObjRef documentRootRef) {
		DocumentRoot documentRoot = XArchADTProxy.proxy(xarch, documentRootRef);
		if (documentRoot != null) {
			XADLType xadlType = documentRoot.getXADL();
			if (xadlType != null) {
				for (EObject eObject : xadlType.getTopLevelElement()) {
					if (eObject instanceof Variability)
						return (Variability) eObject;
				}
			}
		}
		return null;
	}

	public static final List<ObjRef> createAndApplyChangeSet(IXArchADTVariability xarch, ObjRef documentRootRef,
			String name) {
		Variability variability = getVariability(xarch, documentRootRef);
		Variability_3_0Factory f = XArchADTProxy.proxy(xarch, Variability_3_0Package.eINSTANCE.getNsURI());
		ChangeSet changeSet = f.createChangeSetOfChanges();
		changeSet.setId(UIDGenerator.generateUID());
		changeSet.setName(name);
		variability.getChangeSet().add(changeSet);
		List<ObjRef> appliedChangeSetRefs = xarch.getAppliedChangeSets(documentRootRef);
		appliedChangeSetRefs.add(XArchADTProxy.unproxy(changeSet));
		xarch.applyChangeSets(documentRootRef, appliedChangeSetRefs);
		xarch.setActiveChangeSet(documentRootRef, XArchADTProxy.unproxy(changeSet));
		return appliedChangeSetRefs;
	}

	public static final void updateDynamicChangeSet(IXArchADTVariability xarch, ObjRef documentRootRef,
			ObjRef transformChangeSetRef, ChangeSetTransform transform) {
		Variability variability = getVariability(xarch, documentRootRef);
		TransformChangeSetOfChanges changeSet = XArchADTProxy.proxy(xarch, transformChangeSetRef);

		// clear active change set
		ObjRef activeChangeSetRef = xarch.getActiveChangeSet(documentRootRef);
		xarch.setActiveChangeSet(documentRootRef, null);

		// turn off explicit change sets
		Set<ObjRef> explicitChangeSetRefs = xarch.getExplicitChangeSets(documentRootRef);
		xarch.setExplicitChangeSets(documentRootRef, Sets.<ObjRef> newHashSet());

		// apply only change sets up to, but excluding the transform change set
		List<ObjRef> appliedChangeSetRefs = Lists.newArrayList(xarch.getAppliedChangeSets(documentRootRef));
		List<ObjRef> allChangeSetRefs = Lists.newArrayList(XArchADTProxy.unproxy(variability.getChangeSet()));
		List<ObjRef> releventChangeSetRefs = allChangeSetRefs.subList(0,
				allChangeSetRefs.indexOf(transformChangeSetRef));
		List<ObjRef> newAppliedChangeSetRefs = Lists.newArrayList(appliedChangeSetRefs);
		newAppliedChangeSetRefs.retainAll(releventChangeSetRefs);
		xarch.applyChangeSets(documentRootRef, newAppliedChangeSetRefs);

		// clear change set that will be dynamically populated, apply it
		changeSet.setElementChange(null);
		newAppliedChangeSetRefs.add(transformChangeSetRef);
		xarch.applyChangeSets(documentRootRef, newAppliedChangeSetRefs);
		
		// perform the transform
		xarch.setActiveChangeSet(documentRootRef, transformChangeSetRef);
		transform.transform(xarch, documentRootRef);

		// restore the original applied, explicit and active change sets
		xarch.applyChangeSets(documentRootRef, appliedChangeSetRefs);
		xarch.setExplicitChangeSets(documentRootRef, explicitChangeSetRefs);
		xarch.setActiveChangeSet(documentRootRef, activeChangeSetRef);
	}
}
