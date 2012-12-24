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
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * 
 * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package
 * @generated
 */
public class Variability_3_0AdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static Variability_3_0Package modelPackage;

	/**
	 * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public Variability_3_0AdapterFactory() {
		if (modelPackage == null) {
			modelPackage = Variability_3_0Package.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc --> This implementation returns <code>true</code> if
	 * the object is either the model's package or is an instance object of the
	 * model. <!-- end-user-doc -->
	 * 
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */

	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Variability_3_0Switch<Adapter> modelSwitch = new Variability_3_0Switch<Adapter>() {

		public Adapter caseAttributeChange(AttributeChange object) {
			return createAttributeChangeAdapter();
		}

		public Adapter caseChange(Change object) {
			return createChangeAdapter();
		}

		public Adapter caseChangeSet(ChangeSet object) {
			return createChangeSetAdapter();
		}

		public Adapter caseChangeSetOfChanges(ChangeSetOfChanges object) {
			return createChangeSetOfChangesAdapter();
		}

		public Adapter caseDocumentRoot(DocumentRoot object) {
			return createDocumentRootAdapter();
		}

		public Adapter caseElementChange(ElementChange object) {
			return createElementChangeAdapter();
		}

		public Adapter caseElementManyChange(ElementManyChange object) {
			return createElementManyChangeAdapter();
		}

		public Adapter caseJavaTransformChangeSetOfChanges(JavaTransformChangeSetOfChanges object) {
			return createJavaTransformChangeSetOfChangesAdapter();
		}

		public Adapter caseRelationship(Relationship object) {
			return createRelationshipAdapter();
		}

		public Adapter caseTransformChangeSetOfChanges(TransformChangeSetOfChanges object) {
			return createTransformChangeSetOfChangesAdapter();
		}

		public Adapter caseVariability(Variability object) {
			return createVariabilityAdapter();
		}

		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param target
	 *            the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */

	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.variability_3_0.AttributeChange
	 * <em>Attribute Change</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.variability_3_0.AttributeChange
	 * @generated
	 */
	public Adapter createAttributeChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.variability_3_0.Change <em>Change</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.variability_3_0.Change
	 * @generated
	 */
	public Adapter createChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.variability_3_0.ChangeSet
	 * <em>Change Set</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.variability_3_0.ChangeSet
	 * @generated
	 */
	public Adapter createChangeSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.variability_3_0.ChangeSetOfChanges
	 * <em>Change Set Of Changes</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.variability_3_0.ChangeSetOfChanges
	 * @generated
	 */
	public Adapter createChangeSetOfChangesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.variability_3_0.DocumentRoot
	 * <em>Document Root</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.variability_3_0.DocumentRoot
	 * @generated
	 */
	public Adapter createDocumentRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.variability_3_0.ElementChange
	 * <em>Element Change</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.variability_3_0.ElementChange
	 * @generated
	 */
	public Adapter createElementChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.variability_3_0.ElementManyChange
	 * <em>Element Many Change</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.variability_3_0.ElementManyChange
	 * @generated
	 */
	public Adapter createElementManyChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.variability_3_0.JavaTransformChangeSetOfChanges
	 * <em>Java Transform Change Set Of Changes</em>}'. <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the
	 * cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.variability_3_0.JavaTransformChangeSetOfChanges
	 * @generated
	 */
	public Adapter createJavaTransformChangeSetOfChangesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.variability_3_0.Relationship
	 * <em>Relationship</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.variability_3_0.Relationship
	 * @generated
	 */
	public Adapter createRelationshipAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.variability_3_0.TransformChangeSetOfChanges
	 * <em>Transform Change Set Of Changes</em>}'. <!-- begin-user-doc --> This
	 * default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.variability_3_0.TransformChangeSetOfChanges
	 * @generated
	 */
	public Adapter createTransformChangeSetOfChangesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.variability_3_0.Variability
	 * <em>Variability</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.variability_3_0.Variability
	 * @generated
	 */
	public Adapter createVariabilityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This
	 * default implementation returns null. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //Variability_3_0AdapterFactory
