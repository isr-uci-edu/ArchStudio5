/**
 */
package org.archstudio.xadl3.osgiimplementation_3_0;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.archstudio.xadl3.osgiimplementation_3_0.Osgiimplementation_3_0Package
 * @generated
 */
public interface Osgiimplementation_3_0Factory extends EFactory {
	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	Osgiimplementation_3_0Factory eINSTANCE = org.archstudio.xadl3.osgiimplementation_3_0.impl.Osgiimplementation_3_0FactoryImpl
			.init();

	/**
	 * Returns a new object of class '<em>OS Gi Implementation</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>OS Gi Implementation</em>'.
	 * @generated
	 */
	OSGiImplementation createOSGiImplementation();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	Osgiimplementation_3_0Package getOsgiimplementation_3_0Package();

} //Osgiimplementation_3_0Factory
