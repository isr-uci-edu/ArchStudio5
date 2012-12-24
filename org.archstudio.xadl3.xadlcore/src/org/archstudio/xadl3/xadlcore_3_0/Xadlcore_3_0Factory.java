/**
 */
package org.archstudio.xadl3.xadlcore_3_0;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package
 * @generated
 */
public interface Xadlcore_3_0Factory extends EFactory {
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	Xadlcore_3_0Factory eINSTANCE = org.archstudio.xadl3.xadlcore_3_0.impl.Xadlcore_3_0FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Document Root</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
	DocumentRoot createDocumentRoot();

	/**
	 * Returns a new object of class '<em>Simple Link</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Simple Link</em>'.
	 * @generated
	 */
	SimpleLink createSimpleLink();

	/**
	 * Returns a new object of class '<em>XADL Type</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>XADL Type</em>'.
	 * @generated
	 */
	XADLType createXADLType();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	Xadlcore_3_0Package getXadlcore_3_0Package();

} //Xadlcore_3_0Factory
