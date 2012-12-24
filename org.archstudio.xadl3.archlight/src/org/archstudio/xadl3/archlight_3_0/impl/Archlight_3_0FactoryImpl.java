/**
 */
package org.archstudio.xadl3.archlight_3_0.impl;

import org.archstudio.xadl3.archlight_3_0.Archlight;
import org.archstudio.xadl3.archlight_3_0.Archlight_3_0Factory;
import org.archstudio.xadl3.archlight_3_0.Archlight_3_0Package;
import org.archstudio.xadl3.archlight_3_0.DocumentRoot;
import org.archstudio.xadl3.archlight_3_0.Test;
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
public class Archlight_3_0FactoryImpl extends EFactoryImpl implements Archlight_3_0Factory {
	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static Archlight_3_0Factory init() {
		try {
			Archlight_3_0Factory theArchlight_3_0Factory = (Archlight_3_0Factory) EPackage.Registry.INSTANCE
					.getEFactory("http://www.archstudio.org/xadl3/schemas/archlight-3.0.xsd");
			if (theArchlight_3_0Factory != null) {
				return theArchlight_3_0Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Archlight_3_0FactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public Archlight_3_0FactoryImpl() {
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
		case Archlight_3_0Package.ARCHLIGHT:
			return createArchlight();
		case Archlight_3_0Package.DOCUMENT_ROOT:
			return createDocumentRoot();
		case Archlight_3_0Package.TEST:
			return createTest();
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
	public Archlight createArchlight() {
		ArchlightImpl archlight = new ArchlightImpl();
		return archlight;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Test createTest() {
		TestImpl test = new TestImpl();
		return test;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Archlight_3_0Package getArchlight_3_0Package() {
		return (Archlight_3_0Package) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Archlight_3_0Package getPackage() {
		return Archlight_3_0Package.eINSTANCE;
	}

} //Archlight_3_0FactoryImpl
