/**
 */
package org.archstudio.xadl3.prolog_3_0;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 * 
 * @see org.archstudio.xadl3.prolog_3_0.Prolog_3_0Package
 * @generated
 */
public interface Prolog_3_0Factory extends EFactory {
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	Prolog_3_0Factory eINSTANCE = org.archstudio.xadl3.prolog_3_0.impl.Prolog_3_0FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Prolog Extension</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Prolog Extension</em>'.
	 * @generated
	 */
	PrologExtension createPrologExtension();

	/**
	 * Returns a new object of class '<em>Statement</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>Statement</em>'.
	 * @generated
	 */
	Statement createStatement();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	Prolog_3_0Package getProlog_3_0Package();

} //Prolog_3_0Factory
