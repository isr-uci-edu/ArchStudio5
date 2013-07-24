/**
 */
package org.archstudio.xadl3.osgiimplementation_3_0;

import org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package;
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
 * xADL 3 OSGi Implementation Schema
 * 
 * This schema defines the concrete concept of a OSGi-based implementation.
 * 
 * Changelog: - 3.0.0 * Initial version
 * 
 * 
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
 * 
 * 
 * xADL 3 Abstract Implementation Schema
 * 
 * This schema defines the concept of an implementation. The concept is abstract, so it is intended to be specialized by
 * various specific types of implementations. However, it exists so that implementations can be treated similarly.
 * 
 * Changelog: - 3.0.0 * Initial version
 * 
 * 
 * <!-- end-model-doc -->
 * 
 * @see org.archstudio.xadl3.osgiimplementation_3_0.Osgiimplementation_3_0Factory
 * @model kind="package"
 * @generated
 */
public interface Osgiimplementation_3_0Package extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "osgiimplementation_3_0";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.archstudio.org/xadl3/schemas/osgiimplementation-3.0.xsd";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "osgiimplementation_3_0";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	Osgiimplementation_3_0Package eINSTANCE = org.archstudio.xadl3.osgiimplementation_3_0.impl.Osgiimplementation_3_0PackageImpl
			.init();

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.osgiimplementation_3_0.impl.OSGiImplementationImpl
	 * <em>OS Gi Implementation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.osgiimplementation_3_0.impl.OSGiImplementationImpl
	 * @see org.archstudio.xadl3.osgiimplementation_3_0.impl.Osgiimplementation_3_0PackageImpl#getOSGiImplementation()
	 * @generated
	 */
	int OS_GI_IMPLEMENTATION = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OS_GI_IMPLEMENTATION__ID = Implementation_3_0Package.IMPLEMENTATION__ID;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OS_GI_IMPLEMENTATION__EXT = Implementation_3_0Package.IMPLEMENTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Bundle</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OS_GI_IMPLEMENTATION__BUNDLE = Implementation_3_0Package.IMPLEMENTATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>OS Gi Implementation</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OS_GI_IMPLEMENTATION_FEATURE_COUNT = Implementation_3_0Package.IMPLEMENTATION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>OS Gi Implementation</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OS_GI_IMPLEMENTATION_OPERATION_COUNT = Implementation_3_0Package.IMPLEMENTATION_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.osgiimplementation_3_0.OSGiImplementation
	 * <em>OS Gi Implementation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>OS Gi Implementation</em>'.
	 * @see org.archstudio.xadl3.osgiimplementation_3_0.OSGiImplementation
	 * @generated
	 */
	EClass getOSGiImplementation();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.osgiimplementation_3_0.OSGiImplementation#getExt <em>Ext</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ext</em>'.
	 * @see org.archstudio.xadl3.osgiimplementation_3_0.OSGiImplementation#getExt()
	 * @see #getOSGiImplementation()
	 * @generated
	 */
	EReference getOSGiImplementation_Ext();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.osgiimplementation_3_0.OSGiImplementation#getBundle <em>Bundle</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Bundle</em>'.
	 * @see org.archstudio.xadl3.osgiimplementation_3_0.OSGiImplementation#getBundle()
	 * @see #getOSGiImplementation()
	 * @generated
	 */
	EAttribute getOSGiImplementation_Bundle();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Osgiimplementation_3_0Factory getOsgiimplementation_3_0Factory();

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
		 * The meta object literal for the '
		 * {@link org.archstudio.xadl3.osgiimplementation_3_0.impl.OSGiImplementationImpl <em>OS Gi Implementation</em>}
		 * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.osgiimplementation_3_0.impl.OSGiImplementationImpl
		 * @see org.archstudio.xadl3.osgiimplementation_3_0.impl.Osgiimplementation_3_0PackageImpl#getOSGiImplementation()
		 * @generated
		 */
		EClass OS_GI_IMPLEMENTATION = eINSTANCE.getOSGiImplementation();

		/**
		 * The meta object literal for the '<em><b>Ext</b></em>' containment reference list feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference OS_GI_IMPLEMENTATION__EXT = eINSTANCE.getOSGiImplementation_Ext();

		/**
		 * The meta object literal for the '<em><b>Bundle</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute OS_GI_IMPLEMENTATION__BUNDLE = eINSTANCE.getOSGiImplementation_Bundle();

	}

} //Osgiimplementation_3_0Package
