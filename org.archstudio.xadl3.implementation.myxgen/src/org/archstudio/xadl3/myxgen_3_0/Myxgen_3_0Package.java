/**
 */
package org.archstudio.xadl3.myxgen_3_0;

import org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc --> <!-- begin-model-doc -->
 * 
 * 
 * xADL 3 MyxGen Schema
 * 
 * This schema defines references to MyxGen bricks.
 * 
 * Changelog: - 3.0.0 * Initial version
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
 * @see org.archstudio.xadl3.myxgen_3_0.Myxgen_3_0Factory
 * @model kind="package"
 * @generated
 */
public interface Myxgen_3_0Package extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "myxgen_3_0";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.archstudio.org/xadl3/schemas/myxgen-3.0.xsd";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "myxgen_3_0";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	Myxgen_3_0Package eINSTANCE = org.archstudio.xadl3.myxgen_3_0.impl.Myxgen_3_0PackageImpl.init();

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.myxgen_3_0.impl.MyxGenImpl <em>Myx Gen</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.myxgen_3_0.impl.MyxGenImpl
	 * @see org.archstudio.xadl3.myxgen_3_0.impl.Myxgen_3_0PackageImpl#getMyxGen()
	 * @generated
	 */
	int MYX_GEN = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MYX_GEN__ID = Implementation_3_0Package.IMPLEMENTATION__ID;

	/**
	 * The feature id for the '<em><b>Brick ID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MYX_GEN__BRICK_ID = Implementation_3_0Package.IMPLEMENTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Myx Gen</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MYX_GEN_FEATURE_COUNT = Implementation_3_0Package.IMPLEMENTATION_FEATURE_COUNT + 1;

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.myxgen_3_0.MyxGen <em>Myx Gen</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Myx Gen</em>'.
	 * @see org.archstudio.xadl3.myxgen_3_0.MyxGen
	 * @generated
	 */
	EClass getMyxGen();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.myxgen_3_0.MyxGen#getBrickID
	 * <em>Brick ID</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Brick ID</em>'.
	 * @see org.archstudio.xadl3.myxgen_3_0.MyxGen#getBrickID()
	 * @see #getMyxGen()
	 * @generated
	 */
	EAttribute getMyxGen_BrickID();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Myxgen_3_0Factory getMyxgen_3_0Factory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that represent
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
		 * The meta object literal for the '{@link org.archstudio.xadl3.myxgen_3_0.impl.MyxGenImpl <em>Myx Gen</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.myxgen_3_0.impl.MyxGenImpl
		 * @see org.archstudio.xadl3.myxgen_3_0.impl.Myxgen_3_0PackageImpl#getMyxGen()
		 * @generated
		 */
		EClass MYX_GEN = eINSTANCE.getMyxGen();

		/**
		 * The meta object literal for the '<em><b>Brick ID</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MYX_GEN__BRICK_ID = eINSTANCE.getMyxGen_BrickID();

	}

} //Myxgen_3_0Package
