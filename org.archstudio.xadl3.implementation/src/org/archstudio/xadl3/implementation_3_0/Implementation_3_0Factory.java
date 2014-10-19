/**
 */
package org.archstudio.xadl3.implementation_3_0;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 * 
 * @see org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package
 * @generated
 */
public interface Implementation_3_0Factory extends EFactory {
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	Implementation_3_0Factory eINSTANCE = org.archstudio.xadl3.implementation_3_0.impl.Implementation_3_0FactoryImpl
			.init();

	/**
	 * Returns a new object of class '<em>Implementation Extension</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Implementation Extension</em>'.
	 * @generated
	 */
	ImplementationExtension createImplementationExtension();

	/**
	 * Returns a new object of class '<em>Initialization Parameter</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Initialization Parameter</em>'.
	 * @generated
	 */
	InitializationParameter createInitializationParameter();

	/**
	 * Returns a new object of class '<em>Initialization Parameters Implementation</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return a new object of class '<em>Initialization Parameters Implementation</em>'.
	 * @generated
	 */
	InitializationParametersImplementation createInitializationParametersImplementation();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	Implementation_3_0Package getImplementation_3_0Package();

} //Implementation_3_0Factory
