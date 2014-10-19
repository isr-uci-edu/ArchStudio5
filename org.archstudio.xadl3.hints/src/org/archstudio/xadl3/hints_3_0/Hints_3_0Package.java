/**
 */
package org.archstudio.xadl3.hints_3_0;

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
 * xADL 3 Hints Schema
 * 
 * This schema defines the concept of rendering hints that can be applied to different objects to permit them to be
 * hinted.
 * 
 * Changelog: - 3.0.1 * Deprecated value, replaced by hint attribute - 3.0.0 * Initial version
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
 * @see org.archstudio.xadl3.hints_3_0.Hints_3_0Factory
 * @model kind="package"
 * @generated
 */
public interface Hints_3_0Package extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "hints_3_0";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.archstudio.org/xadl3/schemas/hints-3.0.xsd";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "hints_3_0";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	Hints_3_0Package eINSTANCE = org.archstudio.xadl3.hints_3_0.impl.Hints_3_0PackageImpl.init();

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.hints_3_0.impl.HintImpl <em>Hint</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.hints_3_0.impl.HintImpl
	 * @see org.archstudio.xadl3.hints_3_0.impl.Hints_3_0PackageImpl#getHint()
	 * @generated
	 */
	int HINT = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int HINT__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Hint</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int HINT__HINT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int HINT__NAME = 2;

	/**
	 * The number of structural features of the '<em>Hint</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int HINT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Hint</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int HINT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.hints_3_0.impl.HintsExtensionImpl
	 * <em>Hints Extension</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.hints_3_0.impl.HintsExtensionImpl
	 * @see org.archstudio.xadl3.hints_3_0.impl.Hints_3_0PackageImpl#getHintsExtension()
	 * @generated
	 */
	int HINTS_EXTENSION = 1;

	/**
	 * The feature id for the '<em><b>Hint</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int HINTS_EXTENSION__HINT = Xadlcore_3_0Package.EXTENSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Hints Extension</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int HINTS_EXTENSION_FEATURE_COUNT = Xadlcore_3_0Package.EXTENSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Hints Extension</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int HINTS_EXTENSION_OPERATION_COUNT = Xadlcore_3_0Package.EXTENSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.hints_3_0.impl.ValueImpl <em>Value</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.hints_3_0.impl.ValueImpl
	 * @see org.archstudio.xadl3.hints_3_0.impl.Hints_3_0PackageImpl#getValue()
	 * @generated
	 */
	int VALUE = 2;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VALUE__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Data</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VALUE__DATA = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VALUE__TYPE = 2;

	/**
	 * The number of structural features of the '<em>Value</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VALUE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Value</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VALUE_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.hints_3_0.Hint <em>Hint</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Hint</em>'.
	 * @see org.archstudio.xadl3.hints_3_0.Hint
	 * @generated
	 */
	EClass getHint();

	/**
	 * Returns the meta object for the containment reference '{@link org.archstudio.xadl3.hints_3_0.Hint#getValue
	 * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see org.archstudio.xadl3.hints_3_0.Hint#getValue()
	 * @see #getHint()
	 * @generated
	 */
	EReference getHint_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.hints_3_0.Hint#getHint <em>Hint</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Hint</em>'.
	 * @see org.archstudio.xadl3.hints_3_0.Hint#getHint()
	 * @see #getHint()
	 * @generated
	 */
	EAttribute getHint_Hint();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.hints_3_0.Hint#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.archstudio.xadl3.hints_3_0.Hint#getName()
	 * @see #getHint()
	 * @generated
	 */
	EAttribute getHint_Name();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.hints_3_0.HintsExtension <em>Hints Extension</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Hints Extension</em>'.
	 * @see org.archstudio.xadl3.hints_3_0.HintsExtension
	 * @generated
	 */
	EClass getHintsExtension();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.hints_3_0.HintsExtension#getHint <em>Hint</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Hint</em>'.
	 * @see org.archstudio.xadl3.hints_3_0.HintsExtension#getHint()
	 * @see #getHintsExtension()
	 * @generated
	 */
	EReference getHintsExtension_Hint();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.hints_3_0.Value <em>Value</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Value</em>'.
	 * @see org.archstudio.xadl3.hints_3_0.Value
	 * @generated
	 */
	EClass getValue();

	/**
	 * Returns the meta object for the containment reference list '{@link org.archstudio.xadl3.hints_3_0.Value#getValue
	 * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Value</em>'.
	 * @see org.archstudio.xadl3.hints_3_0.Value#getValue()
	 * @see #getValue()
	 * @generated
	 */
	EReference getValue_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.hints_3_0.Value#getData <em>Data</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Data</em>'.
	 * @see org.archstudio.xadl3.hints_3_0.Value#getData()
	 * @see #getValue()
	 * @generated
	 */
	EAttribute getValue_Data();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.hints_3_0.Value#getType <em>Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.archstudio.xadl3.hints_3_0.Value#getType()
	 * @see #getValue()
	 * @generated
	 */
	EAttribute getValue_Type();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Hints_3_0Factory getHints_3_0Factory();

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
		 * The meta object literal for the '{@link org.archstudio.xadl3.hints_3_0.impl.HintImpl <em>Hint</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.hints_3_0.impl.HintImpl
		 * @see org.archstudio.xadl3.hints_3_0.impl.Hints_3_0PackageImpl#getHint()
		 * @generated
		 */
		EClass HINT = eINSTANCE.getHint();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference HINT__VALUE = eINSTANCE.getHint_Value();

		/**
		 * The meta object literal for the '<em><b>Hint</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute HINT__HINT = eINSTANCE.getHint_Hint();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute HINT__NAME = eINSTANCE.getHint_Name();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.hints_3_0.impl.HintsExtensionImpl
		 * <em>Hints Extension</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.hints_3_0.impl.HintsExtensionImpl
		 * @see org.archstudio.xadl3.hints_3_0.impl.Hints_3_0PackageImpl#getHintsExtension()
		 * @generated
		 */
		EClass HINTS_EXTENSION = eINSTANCE.getHintsExtension();

		/**
		 * The meta object literal for the '<em><b>Hint</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference HINTS_EXTENSION__HINT = eINSTANCE.getHintsExtension_Hint();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.hints_3_0.impl.ValueImpl <em>Value</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.hints_3_0.impl.ValueImpl
		 * @see org.archstudio.xadl3.hints_3_0.impl.Hints_3_0PackageImpl#getValue()
		 * @generated
		 */
		EClass VALUE = eINSTANCE.getValue();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference VALUE__VALUE = eINSTANCE.getValue_Value();

		/**
		 * The meta object literal for the '<em><b>Data</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute VALUE__DATA = eINSTANCE.getValue_Data();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute VALUE__TYPE = eINSTANCE.getValue_Type();

	}

} //Hints_3_0Package
