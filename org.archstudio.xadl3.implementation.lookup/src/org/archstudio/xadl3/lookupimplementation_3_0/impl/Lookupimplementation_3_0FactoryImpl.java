/**
 */
package org.archstudio.xadl3.lookupimplementation_3_0.impl;

import org.archstudio.xadl3.lookupimplementation_3_0.LookupImplementation;
import org.archstudio.xadl3.lookupimplementation_3_0.Lookupimplementation_3_0Factory;
import org.archstudio.xadl3.lookupimplementation_3_0.Lookupimplementation_3_0Package;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class Lookupimplementation_3_0FactoryImpl extends EFactoryImpl implements Lookupimplementation_3_0Factory {
	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static Lookupimplementation_3_0Factory init() {
		try {
			Lookupimplementation_3_0Factory theLookupimplementation_3_0Factory = (Lookupimplementation_3_0Factory) EPackage.Registry.INSTANCE
					.getEFactory("http://www.archstudio.org/xadl3/schemas/lookupimplementation-3.0.xsd");
			if (theLookupimplementation_3_0Factory != null) {
				return theLookupimplementation_3_0Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Lookupimplementation_3_0FactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Lookupimplementation_3_0FactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case Lookupimplementation_3_0Package.LOOKUP_IMPLEMENTATION:
			return createLookupImplementation();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public LookupImplementation createLookupImplementation() {
		LookupImplementationImpl lookupImplementation = new LookupImplementationImpl();
		return lookupImplementation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Lookupimplementation_3_0Package getLookupimplementation_3_0Package() {
		return (Lookupimplementation_3_0Package) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Lookupimplementation_3_0Package getPackage() {
		return Lookupimplementation_3_0Package.eINSTANCE;
	}

} //Lookupimplementation_3_0FactoryImpl
