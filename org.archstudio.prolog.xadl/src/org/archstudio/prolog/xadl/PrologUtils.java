package org.archstudio.prolog.xadl;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class PrologUtils {

	public static List<ComplexTerm> getFacts(IProgressMonitor monitor, EObject eObject) {
		SubMonitor progress = SubMonitor.convert(monitor, "Calculating Facts", 1);
		try {
			List<ComplexTerm> facts = Lists.newArrayList();
			StringBuffer sb = new StringBuffer();
			processEObject(facts, sb, 0, progress.newChild(1), null, null, eObject, null);

			StringSelection stringSelection = new StringSelection(sb.toString());
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
			return facts;
		}
		finally {
			if (monitor != null) {
				monitor.done();
			}
		}
	}

	private static void processEObject(List<ComplexTerm> facts, StringBuffer sb, int indent, SubMonitor monitor,
			String parentName, ObjRef parentId, EObject eObject, String featureName) {

		if (eObject == null) {
			return;
		}

		// Process root eObject
		String name = eObject.eClass().getName();
		ObjRef id = XArchADTProxy.unproxy(eObject);
		facts.add(formatFact(sb, indent, name, id));

		// Process Feature Name
		if (featureName != null && !name.equals(featureName)) {
			facts.add(formatFact(sb, indent, name, id, "featureName", featureName));
		}

		// add relationship of eObject to parent
		if (parentId != null) {
			facts.add(formatFact(sb, indent, parentName, parentId, name, id));
		}

		// Process Structural Features
		SubMonitor featureMonitor = monitor.newChild(1).setWorkRemaining(
				eObject.eClass().getEAllStructuralFeatures().size());
		for (EStructuralFeature feature : sort(eObject.eClass().getEAllStructuralFeatures())) {

			if (monitor.isCanceled()) {
				break;
			}

			if (feature instanceof EAttribute) { // e.g., id, name
				processEAttribute(facts, sb, indent + 1, name, id, eObject, feature);
				featureMonitor.worked(1);
			}
			else if (feature.isMany()) { // e.g., components, connectors, brick interfaces
				SubMonitor childMonitor = featureMonitor.newChild(1).setWorkRemaining(
						((EList<?>) eObject.eGet(feature)).size());
				for (Object childObject : (EList<?>) eObject.eGet(feature)) {
					if (childObject instanceof EObject) {
						processEObject(facts, sb, indent + 1, childMonitor.newChild(1), name, id,
								(EObject) childObject, feature.getName());
					}
					else {
						processEAttribute(facts, sb, indent + 1, name, id, eObject, feature);
						childMonitor.worked(1);
					}
				}
			}
			else if (feature instanceof EReference && !((EReference) feature).isContainment()) {
				processEReference(facts, sb, indent + 1, name, id, eObject, (EReference) feature);
				featureMonitor.worked(1);
			}
			else { // Plain children
				processEObject(facts, sb, indent + 1, featureMonitor.newChild(1), name, id,
						(EObject) eObject.eGet(feature), feature.getName());
			}

		}
	}

	private static List<EStructuralFeature> sort(EList<EStructuralFeature> eAllStructuralFeatures) {
		List<EStructuralFeature> sorted = Lists.newArrayList(eAllStructuralFeatures);
		Collections.sort(sorted, new Comparator<EStructuralFeature>() {
			@Override
			public int compare(EStructuralFeature arg0, EStructuralFeature arg1) {
				if (sortIsSimple(arg0) && !sortIsSimple(arg1)) {
					return -1;
				}
				if (!sortIsSimple(arg0) && sortIsSimple(arg1)) {
					return 1;
				}
				return arg0.getName().compareTo(arg1.getName());
			}
		});
		return sorted;
	}

	private static boolean sortIsSimple(EStructuralFeature f) {
		return f instanceof EAttribute;
	}

	private static void processEAttribute(List<ComplexTerm> facts, StringBuffer sb, int indent, String parentType,
			ObjRef parentId, EObject eObject, EStructuralFeature eAttribute) {
		String attributeName = eAttribute.getName();

		// Ignore attributes with no value
		Object attributeValue = eObject.eGet(eAttribute);
		if (attributeValue == null) {
			return;
		}

		facts.add(formatFact(sb, indent, parentType, parentId, attributeName, attributeValue));
	}

	private static void processEReference(List<ComplexTerm> facts, StringBuffer sb, int indent, String parentType,
			ObjRef parentId, EObject eObject, EReference eReference) {

		String refType = eReference.getName();
		EObject obj = (EObject) eObject.eGet(eReference);

		if (obj != null) {
			ObjRef refId = XArchADTProxy.unproxy(obj);
			facts.add(formatFact(sb, indent, parentType, parentId, refType, refId));
		}

	}

	private static ComplexTerm formatFact(StringBuffer sb, int indent, String name, Object value) {
		sb.append(Strings.repeat(" ", indent) + SystemUtils.uncapFirst(name) + "(" + toAtom(value) + ").").append("\n");
		return new ComplexTerm(SystemUtils.uncapFirst(name), new ConstantTerm(value));
	}

	private static ComplexTerm formatFact(StringBuffer sb, int indent, String name1, Object value1, String name2,
			Object value2) {
		sb.append(
				Strings.repeat(" ", indent) + SystemUtils.uncapFirst(name1) + "_" + SystemUtils.uncapFirst(name2) + "("
						+ toAtom(value1) + "," + toAtom(value2) + ").").append("\n");
		return new ComplexTerm(SystemUtils.uncapFirst(name1) + "_" + SystemUtils.uncapFirst(name2), new ConstantTerm(
				value1), new ConstantTerm(value2));
	}

	private static String toAtom(Object value) {
		String atom;
		if (value instanceof ObjRef) {
			atom = Long.toString(((ObjRef) value).getUID());
		}
		else if (value instanceof Number) {
			atom = value.toString();
		}
		else {
			atom = "'" + value.toString().replace('\'', '\"').replaceAll("\\s+", " ") + "'";
		}
		return atom;
	}
}
