package org.archstudio.prolog.xadl;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.StringTerm;
import org.archstudio.prolog.term.Term;
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
			String parentName, EObject parent, EObject eObject, String featureName) {

		if (eObject == null) {
			return;
		}

		// Process Name and Feature Name
		String name = eObject.eClass().getName();
		boolean hasFeatureName = false;
		if (featureName != null && !SystemUtils.uncapFirst(name).equals(SystemUtils.uncapFirst(featureName))) {
			hasFeatureName = true;
		}

		// add relationship of eObject to parent
		if (parent != null) {
			facts.add(formatFact(sb, indent, parentName, parent, name, eObject));

			if (hasFeatureName) {
				facts.add(formatFact(sb, indent, parentName, parent, featureName, eObject));
			}
		}

		// Process root eObject
		facts.add(formatFact(sb, indent, name, eObject));
		if (hasFeatureName) {
			facts.add(formatFact(sb, indent, featureName, eObject));
		}

		// Process Structural Features
		SubMonitor featureMonitor = monitor.newChild(1).setWorkRemaining(
				eObject.eClass().getEAllStructuralFeatures().size());
		boolean isDocumentRoot = eObject.eContainer() == null;
		for (EStructuralFeature feature : sort(eObject.eClass().getEAllStructuralFeatures())) {

			if (monitor.isCanceled()) {
				break;
			}
			if (isDocumentRoot) {
				if (feature.getName().equals("mixed")) {
					continue;
				}
				if (feature.getName().equals("xMLNSPrefixMap")) {
					continue;
				}
				if (feature.getName().equals("xSISchemaLocation")) {
					continue;
				}
				if (feature.getName().equals("topLevelElement")) {
					continue;
				}
			}

			if (feature instanceof EAttribute) { // e.g., id, name
				processEAttribute(facts, sb, indent + 1, name, eObject, feature);
				featureMonitor.worked(1);
			}
			else if (feature.isMany()) { // e.g., components, connectors, brick interfaces
				SubMonitor childMonitor = featureMonitor.newChild(1).setWorkRemaining(
						((EList<?>) eObject.eGet(feature)).size());
				for (Object childObject : (EList<?>) eObject.eGet(feature)) {
					if (childObject instanceof EObject) {
						if (hasFeatureName) {
							processFeatureNameInParentChild(facts, sb, indent + 1, featureName, eObject,
									(EObject) childObject, feature.getName());
						}

						processEObject(facts, sb, indent + 1, childMonitor.newChild(1), name, eObject,
								(EObject) childObject, feature.getName());
					}
					else {
						if (hasFeatureName) {
							processEAttribute(facts, sb, indent + 1, featureName, eObject, feature);
						}

						processEAttribute(facts, sb, indent + 1, name, eObject, feature);

						childMonitor.worked(1);
					}
				}
			}
			else if (feature instanceof EReference && !((EReference) feature).isContainment()) {
				processEReference(facts, sb, indent + 1, name, eObject, (EReference) feature);

				if (hasFeatureName) {
					processEReference(facts, sb, indent + 1, featureName, eObject, (EReference) feature);
				}

				featureMonitor.worked(1);
			}
			else { // Plain children
				if (hasFeatureName && eObject.eGet(feature) != null) {
					processFeatureNameInParentChild(facts, sb, indent + 1, featureName, eObject,
							(EObject) eObject.eGet(feature), feature.getName());
				}

				processEObject(facts, sb, indent + 1, featureMonitor.newChild(1), name, eObject,
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
			EObject parent, EStructuralFeature eAttribute) {

		String attributeName = eAttribute.getName();

		// Ignore attributes with no value
		Object attributeValue = parent.eGet(eAttribute);
		if (attributeValue == null) {
			return;
		}

		//		facts.add(formatFact(sb, indent, parentType, parentId, attributeName, attributeValue));
		facts.add(formatFact(sb, indent, attributeName, parent, attributeValue));
	}

	private static void processEReference(List<ComplexTerm> facts, StringBuffer sb, int indent, String parentType,
			EObject parent, EReference eReference) {

		String refType = eReference.getName();
		EObject refObj = (EObject) parent.eGet(eReference);

		if (refObj != null) {
			facts.add(formatFact(sb, indent, parentType, parent, refType, refObj));
		}
	}

	private static void processFeatureNameInParentChild(List<ComplexTerm> facts, StringBuffer sb, int indent,
			String parentName, EObject parent, EObject child, String featureName) {

		String name = child.eClass().getName();

		// add relationship of eObject to parent feature name
		facts.add(formatFact(sb, indent, parentName, parent, name, child));

		if (featureName != null && !SystemUtils.uncapFirst(name).equals(SystemUtils.uncapFirst(featureName))) {
			facts.add(formatFact(sb, indent, parentName, parent, featureName, child));
		}
	}

	private static ComplexTerm formatFact(StringBuffer sb, int indent, String name, Object value) {
		sb.append(Strings.repeat(" ", indent) + SystemUtils.uncapFirst(name) + "(" + toAtom(value) + ").").append("\n");
		return new ComplexTerm(SystemUtils.uncapFirst(name), toTerm(value));
	}

	private static ComplexTerm formatFact(StringBuffer sb, int indent, String name, Object value1, Object value2) {
		sb.append(
				Strings.repeat(" ", indent) + SystemUtils.uncapFirst(name) + "(" + toAtom(value1) + ","
						+ toAtom(value2) + ").").append("\n");
		return new ComplexTerm(SystemUtils.uncapFirst(name), toTerm(value1), toTerm(value2));
	}

	private static ComplexTerm formatFact(StringBuffer sb, int indent, String name1, Object value1, String name2,
			Object value2) {
		sb.append(
				Strings.repeat(" ", indent) + SystemUtils.uncapFirst(name1) + "_" + SystemUtils.uncapFirst(name2) + "("
						+ toAtom(value1) + "," + toAtom(value2) + ").").append("\n");
		return new ComplexTerm(SystemUtils.uncapFirst(name1) + "_" + SystemUtils.uncapFirst(name2), toTerm(value1),
				toTerm(value2));
	}

	private static Term toTerm(Object value) {
		if (value instanceof Number) {
			return new ConstantTerm(value);
		}
		if (value instanceof EObject) {
			return new ConstantTerm(value);
		}
		return new StringTerm("" + value);
	}

	private static String toAtom(Object value) {
		String atom;
		if (value instanceof EObject) {
			ObjRef id = XArchADTProxy.unproxy((EObject) value);
			atom = Long.toString(id.getUID());
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
