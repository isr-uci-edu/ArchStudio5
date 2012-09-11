package org.archstudio.prolog;

import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import alice.tuprolog.InvalidLibraryException;
import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.Prolog;
import alice.tuprolog.Theory;
import alice.tuprolog.lib.JavaLibrary;

public class PrologUtils {

	public static void addFacts(Prolog engine, IProgressMonitor monitor, EObject eObject)
			throws InvalidTheoryException, InvalidLibraryException {
		JavaLibrary lib = (JavaLibrary) engine.loadLibrary(JavaLibrary.class.getName());
		SubMonitor progress = SubMonitor.convert(monitor, "Calculating Facts", 1);
		try {
			processEObject(engine, lib, progress.newChild(1), null, -1, eObject, null);
		}
		finally {
			if (monitor != null) {
				monitor.done();
			}
		}

	}

	private static void processEObject(Prolog engine, JavaLibrary lib, SubMonitor monitor, String parentName,
			long parentId, EObject eObject, String featureName) throws InvalidTheoryException {

		if (eObject == null) {
			return;
		}

		// Process root eObject
		String name = eObject.eClass().getName();
		long id = XArchADTProxy.unproxy(eObject).getUID();
		lib.register(XArchADTProxy.unproxy(eObject));
		engine.addTheory(new Theory(formatFact(name, id)));

		// Process Feature Name
		if (featureName != null && !name.equals(featureName)) {
			engine.addTheory(new Theory(formatFact(name, id, "featureName", featureName)));
		}

		// add relationship of eObject to parent
		if (parentId >= 0) {
			engine.addTheory(new Theory(formatFact(parentName, parentId, name, id)));
		}

		// Process Structural Features
		SubMonitor featureMonitor = monitor.newChild(1).setWorkRemaining(
				eObject.eClass().getEAllStructuralFeatures().size());
		for (EStructuralFeature feature : eObject.eClass().getEAllStructuralFeatures()) {

			if (monitor.isCanceled()) {
				break;
			}

			if (feature instanceof EAttribute) { // e.g., id, name
				processEAttribute(engine, lib, name, id, eObject, feature);
				featureMonitor.worked(1);
			}
			else if (feature.isMany()) { // e.g., components, connectors, brick interfaces
				SubMonitor childMonitor = featureMonitor.newChild(1).setWorkRemaining(
						((EList<?>) eObject.eGet(feature)).size());
				for (Object childObject : (EList<?>) eObject.eGet(feature)) {
					if (childObject instanceof EObject) {
						lib.register(XArchADTProxy.unproxy((EObject) childObject));
						processEObject(engine, lib, childMonitor.newChild(1), name, id, (EObject) childObject,
								feature.getName());
					}
					else {
						processEAttribute(engine, lib, name, id, eObject, feature);
						childMonitor.worked(1);
					}
				}
			}
			else if (feature instanceof EReference && !((EReference) feature).isContainment()) {
				processEReference(engine, lib, name, id, eObject, (EReference) feature);
				featureMonitor.worked(1);
			}
			else { // Plain children
				processEObject(engine, lib, featureMonitor.newChild(1), name, id, (EObject) eObject.eGet(feature),
						feature.getName());
			}

		}
	}

	private static void processEAttribute(Prolog engine, JavaLibrary lib, String parentType, long parentId,
			EObject eObject, EStructuralFeature eAttribute) throws InvalidTheoryException {
		String attributeName = eAttribute.getName();

		// Ignore attributes with no value
		Object attributeValue = eObject.eGet(eAttribute);
		if (attributeValue == null) {
			return;
		}

		engine.addTheory(new Theory(formatFact(parentType, parentId, attributeName, attributeValue)));
	}

	private static void processEReference(Prolog engine, JavaLibrary lib, String parentType, long parentId,
			EObject eObject, EReference eReference) throws InvalidTheoryException {

		String refType = eReference.getName();
		EObject obj = (EObject) eObject.eGet(eReference);

		if (obj != null) {
			long refId = XArchADTProxy.unproxy(obj).getUID();
			lib.register(XArchADTProxy.unproxy(obj));
			engine.addTheory(new Theory(formatFact(parentType, parentId, refType, refId)));
		}

	}

	private static String formatFact(String name, Object value) {
		return debugFact(SystemUtils.uncapFirst(name) + "(" + toAtom(value) + ").");
	}

	private static String formatFact(String name1, Object value1, String name2, Object value2) {
		return debugFact(SystemUtils.uncapFirst(name1) + "_" + SystemUtils.uncapFirst(name2) + "(" + toAtom(value1)
				+ "," + toAtom(value2) + ").");
	}

	private static String debugFact(String fact) {
		System.out.println(fact);
		return fact;
	}

	private static String toAtom(Object value) {
		String atom;
		if (value instanceof Number) {
			atom = value.toString();
		}
		else {
			atom = "'" + value.toString().replace('\'', '\"').replaceAll("\\s+", " ") + "'";
		}
		return atom;
	}
}
