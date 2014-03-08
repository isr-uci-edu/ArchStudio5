/**
 */
package org.archstudio.xadl3.prolog_3_0;

import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
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
 * xADL 3 Prolog Schema
 * 
 * This schema defines additional prolog statements to be included in generated prolog
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
 * <!-- end-model-doc -->
 * 
 * @see org.archstudio.xadl3.prolog_3_0.Prolog_3_0Factory
 * @model kind="package"
 * @generated
 */
public interface Prolog_3_0Package extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "prolog_3_0";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.archstudio.org/xadl3/schemas/prolog-3.0.xsd";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "prolog_3_0";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	Prolog_3_0Package eINSTANCE = org.archstudio.xadl3.prolog_3_0.impl.Prolog_3_0PackageImpl.init();

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.prolog_3_0.impl.PrologExtensionImpl
	 * <em>Prolog Extension</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.prolog_3_0.impl.PrologExtensionImpl
	 * @see org.archstudio.xadl3.prolog_3_0.impl.Prolog_3_0PackageImpl#getPrologExtension()
	 * @generated
	 */
	int PROLOG_EXTENSION = 0;

	/**
	 * The feature id for the '<em><b>Statement</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROLOG_EXTENSION__STATEMENT = Xadlcore_3_0Package.EXTENSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Prolog Extension</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROLOG_EXTENSION_FEATURE_COUNT = Xadlcore_3_0Package.EXTENSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Prolog Extension</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROLOG_EXTENSION_OPERATION_COUNT = Xadlcore_3_0Package.EXTENSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.prolog_3_0.impl.StatementImpl <em>Statement</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.prolog_3_0.impl.StatementImpl
	 * @see org.archstudio.xadl3.prolog_3_0.impl.Prolog_3_0PackageImpl#getStatement()
	 * @generated
	 */
	int STATEMENT = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATEMENT__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Statement</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Statement</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATEMENT_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.prolog_3_0.PrologExtension
	 * <em>Prolog Extension</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Prolog Extension</em>'.
	 * @see org.archstudio.xadl3.prolog_3_0.PrologExtension
	 * @generated
	 */
	EClass getPrologExtension();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.prolog_3_0.PrologExtension#getStatement <em>Statement</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Statement</em>'.
	 * @see org.archstudio.xadl3.prolog_3_0.PrologExtension#getStatement()
	 * @see #getPrologExtension()
	 * @generated
	 */
	EReference getPrologExtension_Statement();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.prolog_3_0.Statement <em>Statement</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Statement</em>'.
	 * @see org.archstudio.xadl3.prolog_3_0.Statement
	 * @generated
	 */
	EClass getStatement();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.prolog_3_0.Statement#getValue
	 * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.archstudio.xadl3.prolog_3_0.Statement#getValue()
	 * @see #getStatement()
	 * @generated
	 */
	EAttribute getStatement_Value();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Prolog_3_0Factory getProlog_3_0Factory();

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
		 * The meta object literal for the '{@link org.archstudio.xadl3.prolog_3_0.impl.PrologExtensionImpl
		 * <em>Prolog Extension</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.prolog_3_0.impl.PrologExtensionImpl
		 * @see org.archstudio.xadl3.prolog_3_0.impl.Prolog_3_0PackageImpl#getPrologExtension()
		 * @generated
		 */
		EClass PROLOG_EXTENSION = eINSTANCE.getPrologExtension();

		/**
		 * The meta object literal for the '<em><b>Statement</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROLOG_EXTENSION__STATEMENT = eINSTANCE.getPrologExtension_Statement();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.prolog_3_0.impl.StatementImpl
		 * <em>Statement</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.prolog_3_0.impl.StatementImpl
		 * @see org.archstudio.xadl3.prolog_3_0.impl.Prolog_3_0PackageImpl#getStatement()
		 * @generated
		 */
		EClass STATEMENT = eINSTANCE.getStatement();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute STATEMENT__VALUE = eINSTANCE.getStatement_Value();

	}

} //Prolog_3_0Package
