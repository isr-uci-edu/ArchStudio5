package org.archstudio.xarchadt.variability;

import java.util.List;

import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl3.variability_3_0.ChangeSet;
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
}
