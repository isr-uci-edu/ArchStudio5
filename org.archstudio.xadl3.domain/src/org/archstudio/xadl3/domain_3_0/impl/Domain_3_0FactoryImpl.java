/**
 */
package org.archstudio.xadl3.domain_3_0.impl;

import org.archstudio.xadl3.domain_3_0.Domain;
import org.archstudio.xadl3.domain_3_0.DomainExtension;
import org.archstudio.xadl3.domain_3_0.DomainType;
import org.archstudio.xadl3.domain_3_0.Domain_3_0Factory;
import org.archstudio.xadl3.domain_3_0.Domain_3_0Package;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
public class Domain_3_0FactoryImpl extends EFactoryImpl implements Domain_3_0Factory {
	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static Domain_3_0Factory init() {
		try {
			Domain_3_0Factory theDomain_3_0Factory = (Domain_3_0Factory) EPackage.Registry.INSTANCE
					.getEFactory("http://www.archstudio.org/xadl3/schemas/domain-3.0.xsd");
			if (theDomain_3_0Factory != null) {
				return theDomain_3_0Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Domain_3_0FactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public Domain_3_0FactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case Domain_3_0Package.DOMAIN:
			return createDomain();
		case Domain_3_0Package.DOMAIN_EXTENSION:
			return createDomainExtension();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
		case Domain_3_0Package.DOMAIN_TYPE:
			return createDomainTypeFromString(eDataType, initialValue);
		case Domain_3_0Package.DOMAIN_TYPE_OBJECT:
			return createDomainTypeObjectFromString(eDataType, initialValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
		case Domain_3_0Package.DOMAIN_TYPE:
			return convertDomainTypeToString(eDataType, instanceValue);
		case Domain_3_0Package.DOMAIN_TYPE_OBJECT:
			return convertDomainTypeObjectToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Domain createDomain() {
		DomainImpl domain = new DomainImpl();
		return domain;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DomainExtension createDomainExtension() {
		DomainExtensionImpl domainExtension = new DomainExtensionImpl();
		return domainExtension;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DomainType createDomainTypeFromString(EDataType eDataType, String initialValue) {
		DomainType result = DomainType.get(initialValue);
		if (result == null) {
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
					+ eDataType.getName() + "'");
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertDomainTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DomainType createDomainTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createDomainTypeFromString(Domain_3_0Package.Literals.DOMAIN_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertDomainTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertDomainTypeToString(Domain_3_0Package.Literals.DOMAIN_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Domain_3_0Package getDomain_3_0Package() {
		return (Domain_3_0Package) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Domain_3_0Package getPackage() {
		return Domain_3_0Package.eINSTANCE;
	}

} //Domain_3_0FactoryImpl
