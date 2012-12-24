/**
 */
package org.archstudio.xadl3.variability_3_0;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package
 * @generated
 */
public interface Variability_3_0Factory extends EFactory {
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	Variability_3_0Factory eINSTANCE = org.archstudio.xadl3.variability_3_0.impl.Variability_3_0FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Attribute Change</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Attribute Change</em>'.
	 * @generated
	 */
	AttributeChange createAttributeChange();

	/**
	 * Returns a new object of class '<em>Change</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Change</em>'.
	 * @generated
	 */
	Change createChange();

	/**
	 * Returns a new object of class '<em>Change Set Of Changes</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Change Set Of Changes</em>'.
	 * @generated
	 */
	ChangeSetOfChanges createChangeSetOfChanges();

	/**
	 * Returns a new object of class '<em>Document Root</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
	DocumentRoot createDocumentRoot();

	/**
	 * Returns a new object of class '<em>Element Change</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Element Change</em>'.
	 * @generated
	 */
	ElementChange createElementChange();

	/**
	 * Returns a new object of class '<em>Element Many Change</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Element Many Change</em>'.
	 * @generated
	 */
	ElementManyChange createElementManyChange();

	/**
	 * Returns a new object of class '
	 * <em>Java Transform Change Set Of Changes</em>'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return a new object of class '
	 *         <em>Java Transform Change Set Of Changes</em>'.
	 * @generated
	 */
	JavaTransformChangeSetOfChanges createJavaTransformChangeSetOfChanges();

	/**
	 * Returns a new object of class '<em>Transform Change Set Of Changes</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Transform Change Set Of Changes</em>'.
	 * @generated
	 */
	TransformChangeSetOfChanges createTransformChangeSetOfChanges();

	/**
	 * Returns a new object of class '<em>Variability</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Variability</em>'.
	 * @generated
	 */
	Variability createVariability();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	Variability_3_0Package getVariability_3_0Package();

} //Variability_3_0Factory
