/**
 */
package org.archstudio.xadl3.domain_3_0;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 * 
 * @see org.archstudio.xadl3.domain_3_0.Domain_3_0Package
 * @generated
 */
public interface Domain_3_0Factory extends EFactory {
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	Domain_3_0Factory eINSTANCE = org.archstudio.xadl3.domain_3_0.impl.Domain_3_0FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Domain</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Domain</em>'.
	 * @generated
	 */
	Domain createDomain();

	/**
	 * Returns a new object of class '<em>Domain Extension</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Domain Extension</em>'.
	 * @generated
	 */
	DomainExtension createDomainExtension();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	Domain_3_0Package getDomain_3_0Package();

} //Domain_3_0Factory
