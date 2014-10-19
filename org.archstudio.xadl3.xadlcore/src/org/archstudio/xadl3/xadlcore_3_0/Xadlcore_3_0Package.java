/**
 */
package org.archstudio.xadl3.xadlcore_3_0;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc --> <!-- begin-model-doc -->
 * 
 * 
 * xADL 3 Core Schema
 * 
 * This schema defines the core elements for xADL 3 documents. The schema is intentionally small, defining only a few
 * elements that are intended to be common across xADL descriptions and extensions, regardless of domain.
 * 
 * Changelog: - 3.0.0: * Initial version
 * 
 * 
 * <!-- end-model-doc -->
 * 
 * @see org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Factory
 * @model kind="package"
 * @generated
 */
public interface Xadlcore_3_0Package extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "xadlcore_3_0";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.archstudio.org/xadl3/schemas/xadlcore-3.0.xsd";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "xadlcore_3_0";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	Xadlcore_3_0Package eINSTANCE = org.archstudio.xadl3.xadlcore_3_0.impl.Xadlcore_3_0PackageImpl.init();

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.xadlcore_3_0.impl.DocumentRootImpl
	 * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.xadlcore_3_0.impl.DocumentRootImpl
	 * @see org.archstudio.xadl3.xadlcore_3_0.impl.Xadlcore_3_0PackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 0;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Top Level Element</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TOP_LEVEL_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>XADL</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XADL = 4;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Document Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.xadlcore_3_0.impl.ExtensionImpl <em>Extension</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.xadlcore_3_0.impl.ExtensionImpl
	 * @see org.archstudio.xadl3.xadlcore_3_0.impl.Xadlcore_3_0PackageImpl#getExtension()
	 * @generated
	 */
	int EXTENSION = 1;

	/**
	 * The number of structural features of the '<em>Extension</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTENSION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Extension</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EXTENSION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.xadlcore_3_0.impl.SimpleLinkImpl <em>Simple Link</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.xadlcore_3_0.impl.SimpleLinkImpl
	 * @see org.archstudio.xadl3.xadlcore_3_0.impl.Xadlcore_3_0PackageImpl#getSimpleLink()
	 * @generated
	 */
	int SIMPLE_LINK = 2;

	/**
	 * The feature id for the '<em><b>Href</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SIMPLE_LINK__HREF = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SIMPLE_LINK__ID = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SIMPLE_LINK__TYPE = 2;

	/**
	 * The number of structural features of the '<em>Simple Link</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SIMPLE_LINK_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Simple Link</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SIMPLE_LINK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.xadlcore_3_0.impl.XADLTypeImpl <em>XADL Type</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.xadlcore_3_0.impl.XADLTypeImpl
	 * @see org.archstudio.xadl3.xadlcore_3_0.impl.Xadlcore_3_0PackageImpl#getXADLType()
	 * @generated
	 */
	int XADL_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Top Level Element</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int XADL_TYPE__TOP_LEVEL_ELEMENT = 0;

	/**
	 * The number of structural features of the '<em>XADL Type</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int XADL_TYPE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>XADL Type</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int XADL_TYPE_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.xadlcore_3_0.DocumentRoot <em>Document Root</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.archstudio.xadl3.xadlcore_3_0.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link org.archstudio.xadl3.xadlcore_3_0.DocumentRoot#getMixed
	 * <em>Mixed</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.archstudio.xadl3.xadlcore_3_0.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.archstudio.xadl3.xadlcore_3_0.DocumentRoot#getXMLNSPrefixMap
	 * <em>XMLNS Prefix Map</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.archstudio.xadl3.xadlcore_3_0.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.archstudio.xadl3.xadlcore_3_0.DocumentRoot#getXSISchemaLocation
	 * <em>XSI Schema Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.archstudio.xadl3.xadlcore_3_0.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.archstudio.xadl3.xadlcore_3_0.DocumentRoot#getTopLevelElement <em>Top Level Element</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Top Level Element</em>'.
	 * @see org.archstudio.xadl3.xadlcore_3_0.DocumentRoot#getTopLevelElement()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_TopLevelElement();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.archstudio.xadl3.xadlcore_3_0.DocumentRoot#getXADL <em>XADL</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>XADL</em>'.
	 * @see org.archstudio.xadl3.xadlcore_3_0.DocumentRoot#getXADL()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XADL();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.xadlcore_3_0.Extension <em>Extension</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Extension</em>'.
	 * @see org.archstudio.xadl3.xadlcore_3_0.Extension
	 * @generated
	 */
	EClass getExtension();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.xadlcore_3_0.SimpleLink <em>Simple Link</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Simple Link</em>'.
	 * @see org.archstudio.xadl3.xadlcore_3_0.SimpleLink
	 * @generated
	 */
	EClass getSimpleLink();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.xadlcore_3_0.SimpleLink#getHref
	 * <em>Href</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Href</em>'.
	 * @see org.archstudio.xadl3.xadlcore_3_0.SimpleLink#getHref()
	 * @see #getSimpleLink()
	 * @generated
	 */
	EAttribute getSimpleLink_Href();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.xadlcore_3_0.SimpleLink#getId <em>Id</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.archstudio.xadl3.xadlcore_3_0.SimpleLink#getId()
	 * @see #getSimpleLink()
	 * @generated
	 */
	EAttribute getSimpleLink_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.xadlcore_3_0.SimpleLink#getType
	 * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.archstudio.xadl3.xadlcore_3_0.SimpleLink#getType()
	 * @see #getSimpleLink()
	 * @generated
	 */
	EAttribute getSimpleLink_Type();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.xadlcore_3_0.XADLType <em>XADL Type</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>XADL Type</em>'.
	 * @see org.archstudio.xadl3.xadlcore_3_0.XADLType
	 * @generated
	 */
	EClass getXADLType();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.xadlcore_3_0.XADLType#getTopLevelElement <em>Top Level Element</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Top Level Element</em>'.
	 * @see org.archstudio.xadl3.xadlcore_3_0.XADLType#getTopLevelElement()
	 * @see #getXADLType()
	 * @generated
	 */
	EReference getXADLType_TopLevelElement();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Xadlcore_3_0Factory getXadlcore_3_0Factory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each operation of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.xadlcore_3_0.impl.DocumentRootImpl
		 * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.xadlcore_3_0.impl.DocumentRootImpl
		 * @see org.archstudio.xadl3.xadlcore_3_0.impl.Xadlcore_3_0PackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Top Level Element</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TOP_LEVEL_ELEMENT = eINSTANCE.getDocumentRoot_TopLevelElement();

		/**
		 * The meta object literal for the '<em><b>XADL</b></em>' containment reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XADL = eINSTANCE.getDocumentRoot_XADL();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.xadlcore_3_0.impl.ExtensionImpl
		 * <em>Extension</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.xadlcore_3_0.impl.ExtensionImpl
		 * @see org.archstudio.xadl3.xadlcore_3_0.impl.Xadlcore_3_0PackageImpl#getExtension()
		 * @generated
		 */
		EClass EXTENSION = eINSTANCE.getExtension();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.xadlcore_3_0.impl.SimpleLinkImpl
		 * <em>Simple Link</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.xadlcore_3_0.impl.SimpleLinkImpl
		 * @see org.archstudio.xadl3.xadlcore_3_0.impl.Xadlcore_3_0PackageImpl#getSimpleLink()
		 * @generated
		 */
		EClass SIMPLE_LINK = eINSTANCE.getSimpleLink();

		/**
		 * The meta object literal for the '<em><b>Href</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SIMPLE_LINK__HREF = eINSTANCE.getSimpleLink_Href();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SIMPLE_LINK__ID = eINSTANCE.getSimpleLink_Id();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SIMPLE_LINK__TYPE = eINSTANCE.getSimpleLink_Type();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.xadlcore_3_0.impl.XADLTypeImpl
		 * <em>XADL Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.xadlcore_3_0.impl.XADLTypeImpl
		 * @see org.archstudio.xadl3.xadlcore_3_0.impl.Xadlcore_3_0PackageImpl#getXADLType()
		 * @generated
		 */
		EClass XADL_TYPE = eINSTANCE.getXADLType();

		/**
		 * The meta object literal for the '<em><b>Top Level Element</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference XADL_TYPE__TOP_LEVEL_ELEMENT = eINSTANCE.getXADLType_TopLevelElement();

	}

} //Xadlcore_3_0Package
