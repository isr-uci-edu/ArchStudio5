/**
 */
package org.archstudio.xadl3.domain_3_0;

import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc --> <!-- begin-model-doc -->
 * 
 * 
 * xADL 3 Domain Schema
 * 
 * This schema defines the concept of a domain, usually applied to interfaces.
 * In some architectural styles, (Myx, C2) interfaces have different domains
 * that establish dependency relationships and topological layout.
 * 
 * Changelog: - 3.0.0 * Initial version
 * 
 * 
 * 
 * 
 * xADL 3 Core Schema
 * 
 * This schema defines the core elements for xADL 3 documents. The schema is
 * intentionally small, defining only a few elements that are intended to be
 * common across xADL descriptions and extensions, regardless of domain.
 * 
 * Changelog: - 3.0.0: * Initial version
 * 
 * 
 * <!-- end-model-doc -->
 * 
 * @see org.archstudio.xadl3.domain_3_0.Domain_3_0Factory
 * @model kind="package" annotation=
 *        "http://www.archstudio.org/xadl3/schemas/extensionHint appinfo='\r\n\t\t\t<hint extensionSchema=\"http://www.archstudio.org/xadl3/schemas/domain-3.0.xsd\" extensionType=\"DomainExtension\" targetSchema=\"http://www.archstudio.org/xadl3/schemas/structure-3.0.xsd\" targetType=\"Interface\" xmlns=\"http://www.archstudio.org/xadl3/schemas/domain-3.0.xsd\"/>\r\n\t\t'"
 * @generated
 */
public interface Domain_3_0Package extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "domain_3_0";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.archstudio.org/xadl3/schemas/domain-3.0.xsd";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "domain_3_0";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	Domain_3_0Package eINSTANCE = org.archstudio.xadl3.domain_3_0.impl.Domain_3_0PackageImpl.init();

	/**
	 * The meta object id for the '
	 * {@link org.archstudio.xadl3.domain_3_0.impl.DomainImpl <em>Domain</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.domain_3_0.impl.DomainImpl
	 * @see org.archstudio.xadl3.domain_3_0.impl.Domain_3_0PackageImpl#getDomain()
	 * @generated
	 */
	int DOMAIN = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOMAIN__TYPE = 0;

	/**
	 * The number of structural features of the '<em>Domain</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOMAIN_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '
	 * {@link org.archstudio.xadl3.domain_3_0.impl.DomainExtensionImpl
	 * <em>Domain Extension</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.domain_3_0.impl.DomainExtensionImpl
	 * @see org.archstudio.xadl3.domain_3_0.impl.Domain_3_0PackageImpl#getDomainExtension()
	 * @generated
	 */
	int DOMAIN_EXTENSION = 1;

	/**
	 * The feature id for the '<em><b>Domain</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOMAIN_EXTENSION__DOMAIN = Xadlcore_3_0Package.EXTENSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Domain Extension</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOMAIN_EXTENSION_FEATURE_COUNT = Xadlcore_3_0Package.EXTENSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '
	 * {@link org.archstudio.xadl3.domain_3_0.DomainType <em>Domain Type</em>}'
	 * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.domain_3_0.DomainType
	 * @see org.archstudio.xadl3.domain_3_0.impl.Domain_3_0PackageImpl#getDomainType()
	 * @generated
	 */
	int DOMAIN_TYPE = 2;

	/**
	 * The meta object id for the '<em>Domain Type Object</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.domain_3_0.DomainType
	 * @see org.archstudio.xadl3.domain_3_0.impl.Domain_3_0PackageImpl#getDomainTypeObject()
	 * @generated
	 */
	int DOMAIN_TYPE_OBJECT = 3;

	/**
	 * Returns the meta object for class '
	 * {@link org.archstudio.xadl3.domain_3_0.Domain <em>Domain</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Domain</em>'.
	 * @see org.archstudio.xadl3.domain_3_0.Domain
	 * @generated
	 */
	EClass getDomain();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.domain_3_0.Domain#getType <em>Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.archstudio.xadl3.domain_3_0.Domain#getType()
	 * @see #getDomain()
	 * @generated
	 */
	EAttribute getDomain_Type();

	/**
	 * Returns the meta object for class '
	 * {@link org.archstudio.xadl3.domain_3_0.DomainExtension
	 * <em>Domain Extension</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Domain Extension</em>'.
	 * @see org.archstudio.xadl3.domain_3_0.DomainExtension
	 * @generated
	 */
	EClass getDomainExtension();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.archstudio.xadl3.domain_3_0.DomainExtension#getDomain
	 * <em>Domain</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Domain</em>'.
	 * @see org.archstudio.xadl3.domain_3_0.DomainExtension#getDomain()
	 * @see #getDomainExtension()
	 * @generated
	 */
	EReference getDomainExtension_Domain();

	/**
	 * Returns the meta object for enum '
	 * {@link org.archstudio.xadl3.domain_3_0.DomainType <em>Domain Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Domain Type</em>'.
	 * @see org.archstudio.xadl3.domain_3_0.DomainType
	 * @generated
	 */
	EEnum getDomainType();

	/**
	 * Returns the meta object for data type '
	 * {@link org.archstudio.xadl3.domain_3_0.DomainType
	 * <em>Domain Type Object</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for data type '<em>Domain Type Object</em>'.
	 * @see org.archstudio.xadl3.domain_3_0.DomainType
	 * @model instanceClass="org.archstudio.xadl3.domain_3_0.DomainType"
	 *        extendedMetaData="name='DomainType:Object' baseType='DomainType'"
	 * @generated
	 */
	EDataType getDomainTypeObject();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Domain_3_0Factory getDomain_3_0Factory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that
	 * represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '
		 * {@link org.archstudio.xadl3.domain_3_0.impl.DomainImpl
		 * <em>Domain</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.archstudio.xadl3.domain_3_0.impl.DomainImpl
		 * @see org.archstudio.xadl3.domain_3_0.impl.Domain_3_0PackageImpl#getDomain()
		 * @generated
		 */
		EClass DOMAIN = eINSTANCE.getDomain();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DOMAIN__TYPE = eINSTANCE.getDomain_Type();

		/**
		 * The meta object literal for the '
		 * {@link org.archstudio.xadl3.domain_3_0.impl.DomainExtensionImpl
		 * <em>Domain Extension</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.domain_3_0.impl.DomainExtensionImpl
		 * @see org.archstudio.xadl3.domain_3_0.impl.Domain_3_0PackageImpl#getDomainExtension()
		 * @generated
		 */
		EClass DOMAIN_EXTENSION = eINSTANCE.getDomainExtension();

		/**
		 * The meta object literal for the '<em><b>Domain</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOMAIN_EXTENSION__DOMAIN = eINSTANCE.getDomainExtension_Domain();

		/**
		 * The meta object literal for the '
		 * {@link org.archstudio.xadl3.domain_3_0.DomainType
		 * <em>Domain Type</em>}' enum. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.domain_3_0.DomainType
		 * @see org.archstudio.xadl3.domain_3_0.impl.Domain_3_0PackageImpl#getDomainType()
		 * @generated
		 */
		EEnum DOMAIN_TYPE = eINSTANCE.getDomainType();

		/**
		 * The meta object literal for the '<em>Domain Type Object</em>' data
		 * type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.domain_3_0.DomainType
		 * @see org.archstudio.xadl3.domain_3_0.impl.Domain_3_0PackageImpl#getDomainTypeObject()
		 * @generated
		 */
		EDataType DOMAIN_TYPE_OBJECT = eINSTANCE.getDomainTypeObject();

	}

} //Domain_3_0Package
