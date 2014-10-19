/**
 */
package org.archstudio.xadl3.variability_3_0;

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
 * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Factory
 * @model kind="package"
 * @generated
 */
public interface Variability_3_0Package extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "variability_3_0";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.archstudio.org/xadl3/schemas/variability-3.0.xsd";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "variability_3_0";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	Variability_3_0Package eINSTANCE = org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl.init();

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.variability_3_0.impl.ChangeImpl <em>Change</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.variability_3_0.impl.ChangeImpl
	 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getChange()
	 * @generated
	 */
	int CHANGE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CHANGE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Change</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CHANGE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Change</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CHANGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.variability_3_0.impl.AttributeChangeImpl
	 * <em>Attribute Change</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.variability_3_0.impl.AttributeChangeImpl
	 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getAttributeChange()
	 * @generated
	 */
	int ATTRIBUTE_CHANGE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_CHANGE__NAME = CHANGE__NAME;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_CHANGE__VALUE = CHANGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Attribute Change</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_CHANGE_FEATURE_COUNT = CHANGE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Attribute Change</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_CHANGE_OPERATION_COUNT = CHANGE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.variability_3_0.impl.ChangeSetImpl <em>Change Set</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.variability_3_0.impl.ChangeSetImpl
	 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getChangeSet()
	 * @generated
	 */
	int CHANGE_SET = 2;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CHANGE_SET__EXT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CHANGE_SET__ID = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CHANGE_SET__NAME = 2;

	/**
	 * The number of structural features of the '<em>Change Set</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CHANGE_SET_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Change Set</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CHANGE_SET_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.variability_3_0.impl.ChangeSetOfChangesImpl
	 * <em>Change Set Of Changes</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.variability_3_0.impl.ChangeSetOfChangesImpl
	 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getChangeSetOfChanges()
	 * @generated
	 */
	int CHANGE_SET_OF_CHANGES = 3;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CHANGE_SET_OF_CHANGES__EXT = CHANGE_SET__EXT;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CHANGE_SET_OF_CHANGES__ID = CHANGE_SET__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CHANGE_SET_OF_CHANGES__NAME = CHANGE_SET__NAME;

	/**
	 * The feature id for the '<em><b>Element Change</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CHANGE_SET_OF_CHANGES__ELEMENT_CHANGE = CHANGE_SET_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Change Set Of Changes</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CHANGE_SET_OF_CHANGES_FEATURE_COUNT = CHANGE_SET_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Change Set Of Changes</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CHANGE_SET_OF_CHANGES_OPERATION_COUNT = CHANGE_SET_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.variability_3_0.impl.DocumentRootImpl
	 * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.variability_3_0.impl.DocumentRootImpl
	 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 4;

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
	 * The feature id for the '<em><b>Variability</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VARIABILITY = 3;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Document Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.variability_3_0.impl.ElementChangeImpl
	 * <em>Element Change</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.variability_3_0.impl.ElementChangeImpl
	 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getElementChange()
	 * @generated
	 */
	int ELEMENT_CHANGE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ELEMENT_CHANGE__NAME = CHANGE__NAME;

	/**
	 * The feature id for the '<em><b>Change</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ELEMENT_CHANGE__CHANGE = CHANGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ELEMENT_CHANGE__TYPE = CHANGE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Element Change</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ELEMENT_CHANGE_FEATURE_COUNT = CHANGE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Element Change</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ELEMENT_CHANGE_OPERATION_COUNT = CHANGE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.variability_3_0.impl.ElementManyChangeImpl
	 * <em>Element Many Change</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.variability_3_0.impl.ElementManyChangeImpl
	 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getElementManyChange()
	 * @generated
	 */
	int ELEMENT_MANY_CHANGE = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ELEMENT_MANY_CHANGE__NAME = CHANGE__NAME;

	/**
	 * The feature id for the '<em><b>Change</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ELEMENT_MANY_CHANGE__CHANGE = CHANGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Element Many Change</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ELEMENT_MANY_CHANGE_FEATURE_COUNT = CHANGE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Element Many Change</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ELEMENT_MANY_CHANGE_OPERATION_COUNT = CHANGE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.variability_3_0.impl.TransformChangeSetOfChangesImpl
	 * <em>Transform Change Set Of Changes</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.variability_3_0.impl.TransformChangeSetOfChangesImpl
	 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getTransformChangeSetOfChanges()
	 * @generated
	 */
	int TRANSFORM_CHANGE_SET_OF_CHANGES = 9;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSFORM_CHANGE_SET_OF_CHANGES__EXT = CHANGE_SET_OF_CHANGES__EXT;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSFORM_CHANGE_SET_OF_CHANGES__ID = CHANGE_SET_OF_CHANGES__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSFORM_CHANGE_SET_OF_CHANGES__NAME = CHANGE_SET_OF_CHANGES__NAME;

	/**
	 * The feature id for the '<em><b>Element Change</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSFORM_CHANGE_SET_OF_CHANGES__ELEMENT_CHANGE = CHANGE_SET_OF_CHANGES__ELEMENT_CHANGE;

	/**
	 * The number of structural features of the '<em>Transform Change Set Of Changes</em>' class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSFORM_CHANGE_SET_OF_CHANGES_FEATURE_COUNT = CHANGE_SET_OF_CHANGES_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Transform Change Set Of Changes</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSFORM_CHANGE_SET_OF_CHANGES_OPERATION_COUNT = CHANGE_SET_OF_CHANGES_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.variability_3_0.impl.JavaTransformChangeSetOfChangesImpl
	 * <em>Java Transform Change Set Of Changes</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.variability_3_0.impl.JavaTransformChangeSetOfChangesImpl
	 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getJavaTransformChangeSetOfChanges()
	 * @generated
	 */
	int JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES = 7;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__EXT = TRANSFORM_CHANGE_SET_OF_CHANGES__EXT;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__ID = TRANSFORM_CHANGE_SET_OF_CHANGES__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__NAME = TRANSFORM_CHANGE_SET_OF_CHANGES__NAME;

	/**
	 * The feature id for the '<em><b>Element Change</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__ELEMENT_CHANGE = TRANSFORM_CHANGE_SET_OF_CHANGES__ELEMENT_CHANGE;

	/**
	 * The feature id for the '<em><b>Bundle</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__BUNDLE = TRANSFORM_CHANGE_SET_OF_CHANGES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__CLASS = TRANSFORM_CHANGE_SET_OF_CHANGES_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Java Transform Change Set Of Changes</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES_FEATURE_COUNT = TRANSFORM_CHANGE_SET_OF_CHANGES_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Java Transform Change Set Of Changes</em>' class. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES_OPERATION_COUNT = TRANSFORM_CHANGE_SET_OF_CHANGES_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.variability_3_0.impl.RelationshipImpl
	 * <em>Relationship</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.variability_3_0.impl.RelationshipImpl
	 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getRelationship()
	 * @generated
	 */
	int RELATIONSHIP = 8;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__EXT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__ID = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__NAME = 2;

	/**
	 * The number of structural features of the '<em>Relationship</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Relationship</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.variability_3_0.impl.VariabilityImpl
	 * <em>Variability</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.variability_3_0.impl.VariabilityImpl
	 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getVariability()
	 * @generated
	 */
	int VARIABILITY = 10;

	/**
	 * The feature id for the '<em><b>Active Change Set</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VARIABILITY__ACTIVE_CHANGE_SET = 0;

	/**
	 * The feature id for the '<em><b>Applied Change Sets</b></em>' reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VARIABILITY__APPLIED_CHANGE_SETS = 1;

	/**
	 * The feature id for the '<em><b>Change Set</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VARIABILITY__CHANGE_SET = 2;

	/**
	 * The feature id for the '<em><b>Relationship</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VARIABILITY__RELATIONSHIP = 3;

	/**
	 * The feature id for the '<em><b>Overview</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VARIABILITY__OVERVIEW = 4;

	/**
	 * The number of structural features of the '<em>Variability</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VARIABILITY_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Variability</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VARIABILITY_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.variability_3_0.AttributeChange
	 * <em>Attribute Change</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Attribute Change</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.AttributeChange
	 * @generated
	 */
	EClass getAttributeChange();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.variability_3_0.AttributeChange#getValue
	 * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.AttributeChange#getValue()
	 * @see #getAttributeChange()
	 * @generated
	 */
	EAttribute getAttributeChange_Value();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.variability_3_0.Change <em>Change</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Change</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.Change
	 * @generated
	 */
	EClass getChange();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.variability_3_0.Change#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.Change#getName()
	 * @see #getChange()
	 * @generated
	 */
	EAttribute getChange_Name();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.variability_3_0.ChangeSet <em>Change Set</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Change Set</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.ChangeSet
	 * @generated
	 */
	EClass getChangeSet();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.variability_3_0.ChangeSet#getExt <em>Ext</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ext</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.ChangeSet#getExt()
	 * @see #getChangeSet()
	 * @generated
	 */
	EReference getChangeSet_Ext();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.variability_3_0.ChangeSet#getId
	 * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.ChangeSet#getId()
	 * @see #getChangeSet()
	 * @generated
	 */
	EAttribute getChangeSet_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.variability_3_0.ChangeSet#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.ChangeSet#getName()
	 * @see #getChangeSet()
	 * @generated
	 */
	EAttribute getChangeSet_Name();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.variability_3_0.ChangeSetOfChanges
	 * <em>Change Set Of Changes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Change Set Of Changes</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.ChangeSetOfChanges
	 * @generated
	 */
	EClass getChangeSetOfChanges();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.archstudio.xadl3.variability_3_0.ChangeSetOfChanges#getElementChange <em>Element Change</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Element Change</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.ChangeSetOfChanges#getElementChange()
	 * @see #getChangeSetOfChanges()
	 * @generated
	 */
	EReference getChangeSetOfChanges_ElementChange();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.variability_3_0.DocumentRoot
	 * <em>Document Root</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.archstudio.xadl3.variability_3_0.DocumentRoot#getMixed <em>Mixed</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.archstudio.xadl3.variability_3_0.DocumentRoot#getXMLNSPrefixMap
	 * <em>XMLNS Prefix Map</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '
	 * {@link org.archstudio.xadl3.variability_3_0.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.archstudio.xadl3.variability_3_0.DocumentRoot#getVariability <em>Variability</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Variability</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.DocumentRoot#getVariability()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Variability();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.variability_3_0.ElementChange
	 * <em>Element Change</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Element Change</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.ElementChange
	 * @generated
	 */
	EClass getElementChange();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.variability_3_0.ElementChange#getChange <em>Change</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Change</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.ElementChange#getChange()
	 * @see #getElementChange()
	 * @generated
	 */
	EReference getElementChange_Change();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.variability_3_0.ElementChange#getType
	 * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.ElementChange#getType()
	 * @see #getElementChange()
	 * @generated
	 */
	EAttribute getElementChange_Type();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.variability_3_0.ElementManyChange
	 * <em>Element Many Change</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Element Many Change</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.ElementManyChange
	 * @generated
	 */
	EClass getElementManyChange();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.variability_3_0.ElementManyChange#getChange <em>Change</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Change</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.ElementManyChange#getChange()
	 * @see #getElementManyChange()
	 * @generated
	 */
	EReference getElementManyChange_Change();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.variability_3_0.JavaTransformChangeSetOfChanges
	 * <em>Java Transform Change Set Of Changes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Java Transform Change Set Of Changes</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.JavaTransformChangeSetOfChanges
	 * @generated
	 */
	EClass getJavaTransformChangeSetOfChanges();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.variability_3_0.JavaTransformChangeSetOfChanges#getBundle <em>Bundle</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Bundle</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.JavaTransformChangeSetOfChanges#getBundle()
	 * @see #getJavaTransformChangeSetOfChanges()
	 * @generated
	 */
	EAttribute getJavaTransformChangeSetOfChanges_Bundle();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.variability_3_0.JavaTransformChangeSetOfChanges#getClass_ <em>Class</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Class</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.JavaTransformChangeSetOfChanges#getClass_()
	 * @see #getJavaTransformChangeSetOfChanges()
	 * @generated
	 */
	EAttribute getJavaTransformChangeSetOfChanges_Class();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.variability_3_0.Relationship
	 * <em>Relationship</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Relationship</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.Relationship
	 * @generated
	 */
	EClass getRelationship();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.variability_3_0.Relationship#getExt <em>Ext</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ext</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.Relationship#getExt()
	 * @see #getRelationship()
	 * @generated
	 */
	EReference getRelationship_Ext();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.variability_3_0.Relationship#getId
	 * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.Relationship#getId()
	 * @see #getRelationship()
	 * @generated
	 */
	EAttribute getRelationship_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.variability_3_0.Relationship#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.Relationship#getName()
	 * @see #getRelationship()
	 * @generated
	 */
	EAttribute getRelationship_Name();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.variability_3_0.TransformChangeSetOfChanges
	 * <em>Transform Change Set Of Changes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Transform Change Set Of Changes</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.TransformChangeSetOfChanges
	 * @generated
	 */
	EClass getTransformChangeSetOfChanges();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.variability_3_0.Variability <em>Variability</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Variability</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.Variability
	 * @generated
	 */
	EClass getVariability();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.archstudio.xadl3.variability_3_0.Variability#getActiveChangeSet <em>Active Change Set</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Active Change Set</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.Variability#getActiveChangeSet()
	 * @see #getVariability()
	 * @generated
	 */
	EReference getVariability_ActiveChangeSet();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.archstudio.xadl3.variability_3_0.Variability#getAppliedChangeSets <em>Applied Change Sets</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Applied Change Sets</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.Variability#getAppliedChangeSets()
	 * @see #getVariability()
	 * @generated
	 */
	EReference getVariability_AppliedChangeSets();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.variability_3_0.Variability#getChangeSet <em>Change Set</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Change Set</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.Variability#getChangeSet()
	 * @see #getVariability()
	 * @generated
	 */
	EReference getVariability_ChangeSet();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.variability_3_0.Variability#getRelationship <em>Relationship</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Relationship</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.Variability#getRelationship()
	 * @see #getVariability()
	 * @generated
	 */
	EReference getVariability_Relationship();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.variability_3_0.Variability#isOverview
	 * <em>Overview</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Overview</em>'.
	 * @see org.archstudio.xadl3.variability_3_0.Variability#isOverview()
	 * @see #getVariability()
	 * @generated
	 */
	EAttribute getVariability_Overview();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Variability_3_0Factory getVariability_3_0Factory();

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
		 * The meta object literal for the '{@link org.archstudio.xadl3.variability_3_0.impl.AttributeChangeImpl
		 * <em>Attribute Change</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.variability_3_0.impl.AttributeChangeImpl
		 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getAttributeChange()
		 * @generated
		 */
		EClass ATTRIBUTE_CHANGE = eINSTANCE.getAttributeChange();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTRIBUTE_CHANGE__VALUE = eINSTANCE.getAttributeChange_Value();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.variability_3_0.impl.ChangeImpl <em>Change</em>}
		 * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.variability_3_0.impl.ChangeImpl
		 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getChange()
		 * @generated
		 */
		EClass CHANGE = eINSTANCE.getChange();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CHANGE__NAME = eINSTANCE.getChange_Name();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.variability_3_0.impl.ChangeSetImpl
		 * <em>Change Set</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.variability_3_0.impl.ChangeSetImpl
		 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getChangeSet()
		 * @generated
		 */
		EClass CHANGE_SET = eINSTANCE.getChangeSet();

		/**
		 * The meta object literal for the '<em><b>Ext</b></em>' containment reference list feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CHANGE_SET__EXT = eINSTANCE.getChangeSet_Ext();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CHANGE_SET__ID = eINSTANCE.getChangeSet_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CHANGE_SET__NAME = eINSTANCE.getChangeSet_Name();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.variability_3_0.impl.ChangeSetOfChangesImpl
		 * <em>Change Set Of Changes</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.variability_3_0.impl.ChangeSetOfChangesImpl
		 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getChangeSetOfChanges()
		 * @generated
		 */
		EClass CHANGE_SET_OF_CHANGES = eINSTANCE.getChangeSetOfChanges();

		/**
		 * The meta object literal for the '<em><b>Element Change</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CHANGE_SET_OF_CHANGES__ELEMENT_CHANGE = eINSTANCE.getChangeSetOfChanges_ElementChange();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.variability_3_0.impl.DocumentRootImpl
		 * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.variability_3_0.impl.DocumentRootImpl
		 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getDocumentRoot()
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
		 * The meta object literal for the '<em><b>Variability</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__VARIABILITY = eINSTANCE.getDocumentRoot_Variability();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.variability_3_0.impl.ElementChangeImpl
		 * <em>Element Change</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.variability_3_0.impl.ElementChangeImpl
		 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getElementChange()
		 * @generated
		 */
		EClass ELEMENT_CHANGE = eINSTANCE.getElementChange();

		/**
		 * The meta object literal for the '<em><b>Change</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ELEMENT_CHANGE__CHANGE = eINSTANCE.getElementChange_Change();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ELEMENT_CHANGE__TYPE = eINSTANCE.getElementChange_Type();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.variability_3_0.impl.ElementManyChangeImpl
		 * <em>Element Many Change</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.variability_3_0.impl.ElementManyChangeImpl
		 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getElementManyChange()
		 * @generated
		 */
		EClass ELEMENT_MANY_CHANGE = eINSTANCE.getElementManyChange();

		/**
		 * The meta object literal for the '<em><b>Change</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ELEMENT_MANY_CHANGE__CHANGE = eINSTANCE.getElementManyChange_Change();

		/**
		 * The meta object literal for the '
		 * {@link org.archstudio.xadl3.variability_3_0.impl.JavaTransformChangeSetOfChangesImpl
		 * <em>Java Transform Change Set Of Changes</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.variability_3_0.impl.JavaTransformChangeSetOfChangesImpl
		 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getJavaTransformChangeSetOfChanges()
		 * @generated
		 */
		EClass JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES = eINSTANCE.getJavaTransformChangeSetOfChanges();

		/**
		 * The meta object literal for the '<em><b>Bundle</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__BUNDLE = eINSTANCE.getJavaTransformChangeSetOfChanges_Bundle();

		/**
		 * The meta object literal for the '<em><b>Class</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__CLASS = eINSTANCE.getJavaTransformChangeSetOfChanges_Class();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.variability_3_0.impl.RelationshipImpl
		 * <em>Relationship</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.variability_3_0.impl.RelationshipImpl
		 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getRelationship()
		 * @generated
		 */
		EClass RELATIONSHIP = eINSTANCE.getRelationship();

		/**
		 * The meta object literal for the '<em><b>Ext</b></em>' containment reference list feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference RELATIONSHIP__EXT = eINSTANCE.getRelationship_Ext();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute RELATIONSHIP__ID = eINSTANCE.getRelationship_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute RELATIONSHIP__NAME = eINSTANCE.getRelationship_Name();

		/**
		 * The meta object literal for the '
		 * {@link org.archstudio.xadl3.variability_3_0.impl.TransformChangeSetOfChangesImpl
		 * <em>Transform Change Set Of Changes</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.variability_3_0.impl.TransformChangeSetOfChangesImpl
		 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getTransformChangeSetOfChanges()
		 * @generated
		 */
		EClass TRANSFORM_CHANGE_SET_OF_CHANGES = eINSTANCE.getTransformChangeSetOfChanges();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.variability_3_0.impl.VariabilityImpl
		 * <em>Variability</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.variability_3_0.impl.VariabilityImpl
		 * @see org.archstudio.xadl3.variability_3_0.impl.Variability_3_0PackageImpl#getVariability()
		 * @generated
		 */
		EClass VARIABILITY = eINSTANCE.getVariability();

		/**
		 * The meta object literal for the '<em><b>Active Change Set</b></em>' reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference VARIABILITY__ACTIVE_CHANGE_SET = eINSTANCE.getVariability_ActiveChangeSet();

		/**
		 * The meta object literal for the '<em><b>Applied Change Sets</b></em>' reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference VARIABILITY__APPLIED_CHANGE_SETS = eINSTANCE.getVariability_AppliedChangeSets();

		/**
		 * The meta object literal for the '<em><b>Change Set</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference VARIABILITY__CHANGE_SET = eINSTANCE.getVariability_ChangeSet();

		/**
		 * The meta object literal for the '<em><b>Relationship</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference VARIABILITY__RELATIONSHIP = eINSTANCE.getVariability_Relationship();

		/**
		 * The meta object literal for the '<em><b>Overview</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute VARIABILITY__OVERVIEW = eINSTANCE.getVariability_Overview();

	}

} //Variability_3_0Package
