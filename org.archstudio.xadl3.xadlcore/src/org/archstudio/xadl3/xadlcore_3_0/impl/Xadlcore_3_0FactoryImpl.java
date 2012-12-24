/**
 */
package org.archstudio.xadl3.xadlcore_3_0.impl;

import org.archstudio.xadl3.xadlcore_3_0.DocumentRoot;
import org.archstudio.xadl3.xadlcore_3_0.SimpleLink;
import org.archstudio.xadl3.xadlcore_3_0.XADLType;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Factory;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
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
public class Xadlcore_3_0FactoryImpl extends EFactoryImpl implements Xadlcore_3_0Factory {
	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static Xadlcore_3_0Factory init() {
		try {
			Xadlcore_3_0Factory theXadlcore_3_0Factory = (Xadlcore_3_0Factory) EPackage.Registry.INSTANCE
					.getEFactory("http://www.archstudio.org/xadl3/schemas/xadlcore-3.0.xsd");
			if (theXadlcore_3_0Factory != null) {
				return theXadlcore_3_0Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Xadlcore_3_0FactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public Xadlcore_3_0FactoryImpl() {
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
		case Xadlcore_3_0Package.DOCUMENT_ROOT:
			return createDocumentRoot();
		case Xadlcore_3_0Package.SIMPLE_LINK:
			return createSimpleLink();
		case Xadlcore_3_0Package.XADL_TYPE:
			return createXADLType();
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
	public SimpleLink createSimpleLink() {
		SimpleLinkImpl simpleLink = new SimpleLinkImpl();
		return simpleLink;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public XADLType createXADLType() {
		XADLTypeImpl xadlType = new XADLTypeImpl();
		return xadlType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Xadlcore_3_0Package getXadlcore_3_0Package() {
		return (Xadlcore_3_0Package) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Xadlcore_3_0Package getPackage() {
		return Xadlcore_3_0Package.eINSTANCE;
	}

} //Xadlcore_3_0FactoryImpl
