/**
 */
package org.archstudio.xadl3.lookupimplementation_3_0;

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
 * xADL 3 Lookup Implementation Schema
 * 
 * This schema defines the concrete concept of a Lookup-based implementation.
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
 * @see org.archstudio.xadl3.lookupimplementation_3_0.Lookupimplementation_3_0Factory
 * @model kind="package"
 * @generated
 */
public interface Lookupimplementation_3_0Package extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "lookupimplementation_3_0";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.archstudio.org/xadl3/schemas/lookupimplementation-3.0.xsd";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "lookupimplementation_3_0";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	Lookupimplementation_3_0Package eINSTANCE = org.archstudio.xadl3.lookupimplementation_3_0.impl.Lookupimplementation_3_0PackageImpl
			.init();

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.lookupimplementation_3_0.impl.LookupImplementationImpl
	 * <em>Lookup Implementation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.lookupimplementation_3_0.impl.LookupImplementationImpl
	 * @see org.archstudio.xadl3.lookupimplementation_3_0.impl.Lookupimplementation_3_0PackageImpl#getLookupImplementation()
	 * @generated
	 */
	int LOOKUP_IMPLEMENTATION = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOOKUP_IMPLEMENTATION__ID = Implementation_3_0Package.IMPLEMENTATION__ID;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOOKUP_IMPLEMENTATION__EXT = Implementation_3_0Package.IMPLEMENTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Lookup</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOOKUP_IMPLEMENTATION__LOOKUP = Implementation_3_0Package.IMPLEMENTATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Lookup Implementation</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOOKUP_IMPLEMENTATION_FEATURE_COUNT = Implementation_3_0Package.IMPLEMENTATION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Lookup Implementation</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOOKUP_IMPLEMENTATION_OPERATION_COUNT = Implementation_3_0Package.IMPLEMENTATION_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.lookupimplementation_3_0.LookupImplementation
	 * <em>Lookup Implementation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Lookup Implementation</em>'.
	 * @see org.archstudio.xadl3.lookupimplementation_3_0.LookupImplementation
	 * @generated
	 */
	EClass getLookupImplementation();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.lookupimplementation_3_0.LookupImplementation#getExt <em>Ext</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ext</em>'.
	 * @see org.archstudio.xadl3.lookupimplementation_3_0.LookupImplementation#getExt()
	 * @see #getLookupImplementation()
	 * @generated
	 */
	EReference getLookupImplementation_Ext();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.lookupimplementation_3_0.LookupImplementation#getLookup <em>Lookup</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Lookup</em>'.
	 * @see org.archstudio.xadl3.lookupimplementation_3_0.LookupImplementation#getLookup()
	 * @see #getLookupImplementation()
	 * @generated
	 */
	EAttribute getLookupImplementation_Lookup();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Lookupimplementation_3_0Factory getLookupimplementation_3_0Factory();

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
		 * {@link org.archstudio.xadl3.lookupimplementation_3_0.impl.LookupImplementationImpl
		 * <em>Lookup Implementation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.lookupimplementation_3_0.impl.LookupImplementationImpl
		 * @see org.archstudio.xadl3.lookupimplementation_3_0.impl.Lookupimplementation_3_0PackageImpl#getLookupImplementation()
		 * @generated
		 */
		EClass LOOKUP_IMPLEMENTATION = eINSTANCE.getLookupImplementation();

		/**
		 * The meta object literal for the '<em><b>Ext</b></em>' containment reference list feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference LOOKUP_IMPLEMENTATION__EXT = eINSTANCE.getLookupImplementation_Ext();

		/**
		 * The meta object literal for the '<em><b>Lookup</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute LOOKUP_IMPLEMENTATION__LOOKUP = eINSTANCE.getLookupImplementation_Lookup();

	}

} //Lookupimplementation_3_0Package
