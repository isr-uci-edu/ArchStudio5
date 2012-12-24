/**
 */
package org.archstudio.xadl3.variability_3_0.util;

import org.archstudio.xadl3.variability_3_0.AttributeChange;
import org.archstudio.xadl3.variability_3_0.Change;
import org.archstudio.xadl3.variability_3_0.ChangeSet;
import org.archstudio.xadl3.variability_3_0.ChangeSetOfChanges;
import org.archstudio.xadl3.variability_3_0.DocumentRoot;
import org.archstudio.xadl3.variability_3_0.ElementChange;
import org.archstudio.xadl3.variability_3_0.ElementManyChange;
import org.archstudio.xadl3.variability_3_0.JavaTransformChangeSetOfChanges;
import org.archstudio.xadl3.variability_3_0.Relationship;
import org.archstudio.xadl3.variability_3_0.TransformChangeSetOfChanges;
import org.archstudio.xadl3.variability_3_0.Variability;
import org.archstudio.xadl3.variability_3_0.Variability_3_0Package;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * 
 * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package
 * @generated
 */
public class Variability_3_0Switch<T> extends Switch<T> {
	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static Variability_3_0Package modelPackage;

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public Variability_3_0Switch() {
		if (modelPackage == null) {
			modelPackage = Variability_3_0Package.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */

	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns
	 * a non null result; it yields that result. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the first non-null result returned by a <code>caseXXX</code>
	 *         call.
	 * @generated
	 */

	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case Variability_3_0Package.ATTRIBUTE_CHANGE: {
			AttributeChange attributeChange = (AttributeChange) theEObject;
			T result = caseAttributeChange(attributeChange);
			if (result == null) {
				result = caseChange(attributeChange);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case Variability_3_0Package.CHANGE: {
			Change change = (Change) theEObject;
			T result = caseChange(change);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case Variability_3_0Package.CHANGE_SET: {
			ChangeSet changeSet = (ChangeSet) theEObject;
			T result = caseChangeSet(changeSet);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case Variability_3_0Package.CHANGE_SET_OF_CHANGES: {
			ChangeSetOfChanges changeSetOfChanges = (ChangeSetOfChanges) theEObject;
			T result = caseChangeSetOfChanges(changeSetOfChanges);
			if (result == null) {
				result = caseChangeSet(changeSetOfChanges);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case Variability_3_0Package.DOCUMENT_ROOT: {
			DocumentRoot documentRoot = (DocumentRoot) theEObject;
			T result = caseDocumentRoot(documentRoot);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case Variability_3_0Package.ELEMENT_CHANGE: {
			ElementChange elementChange = (ElementChange) theEObject;
			T result = caseElementChange(elementChange);
			if (result == null) {
				result = caseChange(elementChange);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case Variability_3_0Package.ELEMENT_MANY_CHANGE: {
			ElementManyChange elementManyChange = (ElementManyChange) theEObject;
			T result = caseElementManyChange(elementManyChange);
			if (result == null) {
				result = caseChange(elementManyChange);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case Variability_3_0Package.JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES: {
			JavaTransformChangeSetOfChanges javaTransformChangeSetOfChanges = (JavaTransformChangeSetOfChanges) theEObject;
			T result = caseJavaTransformChangeSetOfChanges(javaTransformChangeSetOfChanges);
			if (result == null) {
				result = caseTransformChangeSetOfChanges(javaTransformChangeSetOfChanges);
			}
			if (result == null) {
				result = caseChangeSetOfChanges(javaTransformChangeSetOfChanges);
			}
			if (result == null) {
				result = caseChangeSet(javaTransformChangeSetOfChanges);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case Variability_3_0Package.RELATIONSHIP: {
			Relationship relationship = (Relationship) theEObject;
			T result = caseRelationship(relationship);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case Variability_3_0Package.TRANSFORM_CHANGE_SET_OF_CHANGES: {
			TransformChangeSetOfChanges transformChangeSetOfChanges = (TransformChangeSetOfChanges) theEObject;
			T result = caseTransformChangeSetOfChanges(transformChangeSetOfChanges);
			if (result == null) {
				result = caseChangeSetOfChanges(transformChangeSetOfChanges);
			}
			if (result == null) {
				result = caseChangeSet(transformChangeSetOfChanges);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case Variability_3_0Package.VARIABILITY: {
			Variability variability = (Variability) theEObject;
			T result = caseVariability(variability);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Attribute Change</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Attribute Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttributeChange(AttributeChange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Change</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChange(Change object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Change Set</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Change Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChangeSet(ChangeSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Change Set Of Changes</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Change Set Of Changes</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChangeSetOfChanges(ChangeSetOfChanges object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Document Root</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Document Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDocumentRoot(DocumentRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Element Change</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Element Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElementChange(ElementChange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Element Many Change</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Element Many Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElementManyChange(ElementManyChange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Java Transform Change Set Of Changes</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Java Transform Change Set Of Changes</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJavaTransformChangeSetOfChanges(JavaTransformChangeSetOfChanges object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Relationship</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Relationship</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelationship(Relationship object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Transform Change Set Of Changes</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate
	 * the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Transform Change Set Of Changes</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransformChangeSetOfChanges(TransformChangeSetOfChanges object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Variability</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Variability</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVariability(Variability object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>EObject</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch, but this is
	 * the last case anyway. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */

	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //Variability_3_0Switch
