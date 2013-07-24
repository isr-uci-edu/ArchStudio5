/**
 */
package org.archstudio.xadl3.javaimplementation_3_0;

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
 * xADL 3 Java Implementation Schema
 * 
 * This schema defines the concrete concept of a Java implementation.
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
 * @see org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Factory
 * @model kind="package"
 * @generated
 */
public interface Javaimplementation_3_0Package extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "javaimplementation_3_0";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.archstudio.org/xadl3/schemas/javaimplementation-3.0.xsd";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "javaimplementation_3_0";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	Javaimplementation_3_0Package eINSTANCE = org.archstudio.xadl3.javaimplementation_3_0.impl.Javaimplementation_3_0PackageImpl
			.init();

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.javaimplementation_3_0.impl.ClassPathEntryImpl
	 * <em>Class Path Entry</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.javaimplementation_3_0.impl.ClassPathEntryImpl
	 * @see org.archstudio.xadl3.javaimplementation_3_0.impl.Javaimplementation_3_0PackageImpl#getClassPathEntry()
	 * @generated
	 */
	int CLASS_PATH_ENTRY = 0;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CLASS_PATH_ENTRY__EXT = 0;

	/**
	 * The feature id for the '<em><b>Entry</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CLASS_PATH_ENTRY__ENTRY = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CLASS_PATH_ENTRY__ID = 2;

	/**
	 * The number of structural features of the '<em>Class Path Entry</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CLASS_PATH_ENTRY_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Class Path Entry</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CLASS_PATH_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.javaimplementation_3_0.impl.JavaClassImpl
	 * <em>Java Class</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.javaimplementation_3_0.impl.JavaClassImpl
	 * @see org.archstudio.xadl3.javaimplementation_3_0.impl.Javaimplementation_3_0PackageImpl#getJavaClass()
	 * @generated
	 */
	int JAVA_CLASS = 1;

	/**
	 * The feature id for the '<em><b>Class Path Entry</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__CLASS_PATH_ENTRY = 0;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__EXT = 1;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__CLASS_NAME = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS__ID = 3;

	/**
	 * The number of structural features of the '<em>Java Class</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Java Class</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_CLASS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.javaimplementation_3_0.impl.JavaImplementationImpl
	 * <em>Java Implementation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.javaimplementation_3_0.impl.JavaImplementationImpl
	 * @see org.archstudio.xadl3.javaimplementation_3_0.impl.Javaimplementation_3_0PackageImpl#getJavaImplementation()
	 * @generated
	 */
	int JAVA_IMPLEMENTATION = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_IMPLEMENTATION__ID = Implementation_3_0Package.IMPLEMENTATION__ID;

	/**
	 * The feature id for the '<em><b>Main Class</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_IMPLEMENTATION__MAIN_CLASS = Implementation_3_0Package.IMPLEMENTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Aux Class</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_IMPLEMENTATION__AUX_CLASS = Implementation_3_0Package.IMPLEMENTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_IMPLEMENTATION__EXT = Implementation_3_0Package.IMPLEMENTATION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Java Implementation</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_IMPLEMENTATION_FEATURE_COUNT = Implementation_3_0Package.IMPLEMENTATION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Java Implementation</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_IMPLEMENTATION_OPERATION_COUNT = Implementation_3_0Package.IMPLEMENTATION_OPERATION_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry
	 * <em>Class Path Entry</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Class Path Entry</em>'.
	 * @see org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry
	 * @generated
	 */
	EClass getClassPathEntry();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry#getExt <em>Ext</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ext</em>'.
	 * @see org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry#getExt()
	 * @see #getClassPathEntry()
	 * @generated
	 */
	EReference getClassPathEntry_Ext();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry#getEntry <em>Entry</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Entry</em>'.
	 * @see org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry#getEntry()
	 * @see #getClassPathEntry()
	 * @generated
	 */
	EAttribute getClassPathEntry_Entry();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry#getId <em>Id</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry#getId()
	 * @see #getClassPathEntry()
	 * @generated
	 */
	EAttribute getClassPathEntry_Id();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.javaimplementation_3_0.JavaClass
	 * <em>Java Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Java Class</em>'.
	 * @see org.archstudio.xadl3.javaimplementation_3_0.JavaClass
	 * @generated
	 */
	EClass getJavaClass();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.javaimplementation_3_0.JavaClass#getClassPathEntry <em>Class Path Entry</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Class Path Entry</em>'.
	 * @see org.archstudio.xadl3.javaimplementation_3_0.JavaClass#getClassPathEntry()
	 * @see #getJavaClass()
	 * @generated
	 */
	EReference getJavaClass_ClassPathEntry();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.javaimplementation_3_0.JavaClass#getExt <em>Ext</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ext</em>'.
	 * @see org.archstudio.xadl3.javaimplementation_3_0.JavaClass#getExt()
	 * @see #getJavaClass()
	 * @generated
	 */
	EReference getJavaClass_Ext();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.javaimplementation_3_0.JavaClass#getClassName <em>Class Name</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Class Name</em>'.
	 * @see org.archstudio.xadl3.javaimplementation_3_0.JavaClass#getClassName()
	 * @see #getJavaClass()
	 * @generated
	 */
	EAttribute getJavaClass_ClassName();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.javaimplementation_3_0.JavaClass#getId
	 * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.archstudio.xadl3.javaimplementation_3_0.JavaClass#getId()
	 * @see #getJavaClass()
	 * @generated
	 */
	EAttribute getJavaClass_Id();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation
	 * <em>Java Implementation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Java Implementation</em>'.
	 * @see org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation
	 * @generated
	 */
	EClass getJavaImplementation();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation#getMainClass <em>Main Class</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Main Class</em>'.
	 * @see org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation#getMainClass()
	 * @see #getJavaImplementation()
	 * @generated
	 */
	EReference getJavaImplementation_MainClass();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation#getAuxClass <em>Aux Class</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Aux Class</em>'.
	 * @see org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation#getAuxClass()
	 * @see #getJavaImplementation()
	 * @generated
	 */
	EReference getJavaImplementation_AuxClass();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation#getExt <em>Ext</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ext</em>'.
	 * @see org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation#getExt()
	 * @see #getJavaImplementation()
	 * @generated
	 */
	EReference getJavaImplementation_Ext();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Javaimplementation_3_0Factory getJavaimplementation_3_0Factory();

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
		 * The meta object literal for the '{@link org.archstudio.xadl3.javaimplementation_3_0.impl.ClassPathEntryImpl
		 * <em>Class Path Entry</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.javaimplementation_3_0.impl.ClassPathEntryImpl
		 * @see org.archstudio.xadl3.javaimplementation_3_0.impl.Javaimplementation_3_0PackageImpl#getClassPathEntry()
		 * @generated
		 */
		EClass CLASS_PATH_ENTRY = eINSTANCE.getClassPathEntry();

		/**
		 * The meta object literal for the '<em><b>Ext</b></em>' containment reference list feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CLASS_PATH_ENTRY__EXT = eINSTANCE.getClassPathEntry_Ext();

		/**
		 * The meta object literal for the '<em><b>Entry</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CLASS_PATH_ENTRY__ENTRY = eINSTANCE.getClassPathEntry_Entry();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CLASS_PATH_ENTRY__ID = eINSTANCE.getClassPathEntry_Id();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.javaimplementation_3_0.impl.JavaClassImpl
		 * <em>Java Class</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.javaimplementation_3_0.impl.JavaClassImpl
		 * @see org.archstudio.xadl3.javaimplementation_3_0.impl.Javaimplementation_3_0PackageImpl#getJavaClass()
		 * @generated
		 */
		EClass JAVA_CLASS = eINSTANCE.getJavaClass();

		/**
		 * The meta object literal for the '<em><b>Class Path Entry</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference JAVA_CLASS__CLASS_PATH_ENTRY = eINSTANCE.getJavaClass_ClassPathEntry();

		/**
		 * The meta object literal for the '<em><b>Ext</b></em>' containment reference list feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference JAVA_CLASS__EXT = eINSTANCE.getJavaClass_Ext();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute JAVA_CLASS__CLASS_NAME = eINSTANCE.getJavaClass_ClassName();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute JAVA_CLASS__ID = eINSTANCE.getJavaClass_Id();

		/**
		 * The meta object literal for the '
		 * {@link org.archstudio.xadl3.javaimplementation_3_0.impl.JavaImplementationImpl <em>Java Implementation</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.javaimplementation_3_0.impl.JavaImplementationImpl
		 * @see org.archstudio.xadl3.javaimplementation_3_0.impl.Javaimplementation_3_0PackageImpl#getJavaImplementation()
		 * @generated
		 */
		EClass JAVA_IMPLEMENTATION = eINSTANCE.getJavaImplementation();

		/**
		 * The meta object literal for the '<em><b>Main Class</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference JAVA_IMPLEMENTATION__MAIN_CLASS = eINSTANCE.getJavaImplementation_MainClass();

		/**
		 * The meta object literal for the '<em><b>Aux Class</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference JAVA_IMPLEMENTATION__AUX_CLASS = eINSTANCE.getJavaImplementation_AuxClass();

		/**
		 * The meta object literal for the '<em><b>Ext</b></em>' containment reference list feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference JAVA_IMPLEMENTATION__EXT = eINSTANCE.getJavaImplementation_Ext();

	}

} //Javaimplementation_3_0Package
