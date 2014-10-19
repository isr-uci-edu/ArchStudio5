/**
 */
package org.archstudio.xadl3.archlight_3_0;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 * 
 * @see org.archstudio.xadl3.archlight_3_0.Archlight_3_0Package
 * @generated
 */
public interface Archlight_3_0Factory extends EFactory {
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	Archlight_3_0Factory eINSTANCE = org.archstudio.xadl3.archlight_3_0.impl.Archlight_3_0FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Archlight</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Archlight</em>'.
	 * @generated
	 */
	Archlight createArchlight();

	/**
	 * Returns a new object of class '<em>Document Root</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
	DocumentRoot createDocumentRoot();

	/**
	 * Returns a new object of class '<em>Test</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Test</em>'.
	 * @generated
	 */
	Test createTest();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	Archlight_3_0Package getArchlight_3_0Package();

} //Archlight_3_0Factory
