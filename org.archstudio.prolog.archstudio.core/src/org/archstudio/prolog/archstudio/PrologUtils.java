package org.archstudio.prolog.archstudio;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.archstudio.prolog.engine.ProofContext;
import org.archstudio.prolog.parser.ParseException;
import org.archstudio.prolog.parser.PrologParser;
import org.archstudio.prolog.term.ComplexTerm;
import org.archstudio.prolog.term.ConstantTerm;
import org.archstudio.prolog.term.StringTerm;
import org.archstudio.prolog.term.Term;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl3.prolog_3_0.PrologExtension;
import org.archstudio.xadl3.prolog_3_0.Statement;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class PrologUtils {

	public static List<ComplexTerm> getFacts(ProofContext proofContext, IProgressMonitor monitor, EObject eObject)
			throws ParseException {
		SubMonitor progress = SubMonitor.convert(monitor, "Calculating Facts", 1);
		try {
			List<ComplexTerm> facts = Lists.newArrayList();
			Set<String> nsURIs = Sets.newHashSet();
			StringBuffer sb = new StringBuffer();
			processEObject(proofContext, facts, nsURIs, sb, 0, progress.newChild(1), null, null, eObject, null);

			// add prolog for schema NS URIs
			IExtensionRegistry reg = Platform.getExtensionRegistry();
			if (reg != null) {
				// The Extension Registry can be null if we're running outside of Eclipse
				for (IConfigurationElement statements : reg
						.getConfigurationElementsFor("org.archstudio.prolog.archstudio.prologstatement")) {
					String nsURI = statements.getAttribute("nsURI");
					if (nsURIs.contains(nsURI)) {
						for (IConfigurationElement child : statements.getChildren()) {
							String statement = child.getValue();
							if (statement != null && statement.trim().length() > 0) {
								sb.append("\n% ").append(nsURI).append("\n");
								sb.append(statement).append("\n");
								for (Term term : PrologParser.parseTerms(proofContext, statement)) {
									facts.add((ComplexTerm) term);
								}
							}
						}
					}
				}
			}

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

	private static void processEObject(ProofContext proofContext, List<ComplexTerm> facts, Set<String> nsURIs,
			StringBuffer sb, int indent, SubMonitor monitor, String parentName, EObject parent, EObject eObject,
			String featureName) throws ParseException {

		if (eObject == null) {
			return;
		}

		// Record the NS URI of each eObject
		nsURIs.add(eObject.eClass().getEPackage().getNsURI());

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
			else if (feature instanceof EReference && !((EReference) feature).isContainment()) {
				processEReference(facts, sb, indent + 1, name, eObject, (EReference) feature);

				if (hasFeatureName) {
					processEReference(facts, sb, indent + 1, featureName, eObject, (EReference) feature);
				}

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

						processEObject(proofContext, facts, nsURIs, sb, indent + 1, childMonitor.newChild(1), name,
								eObject, (EObject) childObject, feature.getName());
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
			else { // Plain children
				if (hasFeatureName && eObject.eGet(feature) != null) {
					processFeatureNameInParentChild(facts, sb, indent + 1, featureName, eObject,
							(EObject) eObject.eGet(feature), feature.getName());
				}

				processEObject(proofContext, facts, nsURIs, sb, indent + 1, featureMonitor.newChild(1), name, eObject,
						(EObject) eObject.eGet(feature), feature.getName());
			}
		}

		// Process prolog statements
		if (eObject instanceof PrologExtension) {
			for (Statement statement : ((PrologExtension) eObject).getStatement()) {
				String value = statement.getValue();
				if (value == null || value.trim().length() == 0) {
					continue;
				}

				sb.append(Strings.repeat(" ", indent + 1)).append(value).append("\n");
				for (Term term : PrologParser.parseTerms(proofContext, value)) {
					facts.add((ComplexTerm) term);
				}
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

	@SuppressWarnings("unchecked")
	private static void processEReference(List<ComplexTerm> facts, StringBuffer sb, int indent, String parentType,
			EObject parent, EReference eReference) {

		String refType = eReference.getName();
		if (parent.eGet(eReference) instanceof EObject) {
			EObject refObj = (EObject) parent.eGet(eReference);
			if (refObj != null) {
				facts.add(formatFact(sb, indent, parentType, parent, refType, refObj));
			}
		}
		else if (parent.eGet(eReference) instanceof EList) {
			for (EObject refObj : (EList<EObject>) parent.eGet(eReference)) {
				facts.add(formatFact(sb, indent, parentType, parent, refType, refObj));
			}
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
		return new ComplexTerm(SystemUtils.uncapFirst(name), Lists.newArrayList(toTerm(value)));
	}

	private static ComplexTerm formatFact(StringBuffer sb, int indent, String name, Object value1, Object value2) {
		sb.append(
				Strings.repeat(" ", indent) + SystemUtils.uncapFirst(name) + "(" + toAtom(value1) + ","
						+ toAtom(value2) + ").").append("\n");
		return new ComplexTerm(SystemUtils.uncapFirst(name), Lists.newArrayList(toTerm(value1), toTerm(value2)));
	}

	private static ComplexTerm formatFact(StringBuffer sb, int indent, String name1, Object value1, String name2,
			Object value2) {
		sb.append(
				Strings.repeat(" ", indent) + SystemUtils.uncapFirst(name1) + "_" + SystemUtils.uncapFirst(name2) + "("
						+ toAtom(value1) + "," + toAtom(value2) + ").").append("\n");
		return new ComplexTerm(SystemUtils.uncapFirst(name1) + "_" + SystemUtils.uncapFirst(name2), Lists.newArrayList(
				toTerm(value1), toTerm(value2)));
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
