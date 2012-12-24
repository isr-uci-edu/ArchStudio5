/**
 */
package org.archstudio.xadl3.osgiimplementation_3_0.impl;

import org.archstudio.xadl3.osgiimplementation_3_0.OSGiImplementation;
import org.archstudio.xadl3.osgiimplementation_3_0.Osgiimplementation_3_0Factory;
import org.archstudio.xadl3.osgiimplementation_3_0.Osgiimplementation_3_0Package;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class Osgiimplementation_3_0FactoryImpl extends EFactoryImpl implements Osgiimplementation_3_0Factory {
	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static Osgiimplementation_3_0Factory init() {
		try {
			Osgiimplementation_3_0Factory theOsgiimplementation_3_0Factory = (Osgiimplementation_3_0Factory) EPackage.Registry.INSTANCE
					.getEFactory("http://www.archstudio.org/xadl3/schemas/osgiimplementation-3.0.xsd");
			if (theOsgiimplementation_3_0Factory != null) {
				return theOsgiimplementation_3_0Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Osgiimplementation_3_0FactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public Osgiimplementation_3_0FactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case Osgiimplementation_3_0Package.OS_GI_IMPLEMENTATION:
			return createOSGiImplementation();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public OSGiImplementation createOSGiImplementation() {
		OSGiImplementationImpl osGiImplementation = new OSGiImplementationImpl();
		return osGiImplementation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Osgiimplementation_3_0Package getOsgiimplementation_3_0Package() {
		return (Osgiimplementation_3_0Package) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Osgiimplementation_3_0Package getPackage() {
		return Osgiimplementation_3_0Package.eINSTANCE;
	}

} //Osgiimplementation_3_0FactoryImpl
