/**
 */
package org.archstudio.xadl3.statechart_1_0;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Factory
 * @model kind="package"
 * @generated
 */
public interface Statechart_1_0Package extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "statechart_1_0";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.archstudio.org/xadl3/schemas/statechart-1.0.xsd";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "statechart_1_0";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	Statechart_1_0Package eINSTANCE = org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl.init();

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.statechart_1_0.impl.BehaviorImpl <em>Behavior</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.statechart_1_0.impl.BehaviorImpl
	 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getBehavior()
	 * @generated
	 */
	int BEHAVIOR = 0;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR__EXT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR__ID = 1;

	/**
	 * The number of structural features of the '<em>Behavior</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.statechart_1_0.impl.ConstraintImpl <em>Constraint</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.statechart_1_0.impl.ConstraintImpl
	 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getConstraint()
	 * @generated
	 */
	int CONSTRAINT = 1;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__EXT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__ID = 1;

	/**
	 * The number of structural features of the '<em>Constraint</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.statechart_1_0.impl.DocumentRootImpl
	 * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.statechart_1_0.impl.DocumentRootImpl
	 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 2;

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
	 * The feature id for the '<em><b>Statechart</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STATECHART = 3;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.statechart_1_0.impl.PseudoStateImpl
	 * <em>Pseudo State</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.statechart_1_0.impl.PseudoStateImpl
	 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getPseudoState()
	 * @generated
	 */
	int PSEUDO_STATE = 5;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__EXT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__ID = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__NAME = 2;

	/**
	 * The number of structural features of the '<em>Pseudo State</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.statechart_1_0.impl.FinalStateImpl <em>Final State</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.statechart_1_0.impl.FinalStateImpl
	 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getFinalState()
	 * @generated
	 */
	int FINAL_STATE = 3;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE__EXT = PSEUDO_STATE__EXT;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE__ID = PSEUDO_STATE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE__NAME = PSEUDO_STATE__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE__TYPE = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Final State</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.statechart_1_0.impl.InitialStateImpl
	 * <em>Initial State</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.statechart_1_0.impl.InitialStateImpl
	 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getInitialState()
	 * @generated
	 */
	int INITIAL_STATE = 4;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INITIAL_STATE__EXT = PSEUDO_STATE__EXT;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INITIAL_STATE__ID = PSEUDO_STATE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INITIAL_STATE__NAME = PSEUDO_STATE__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INITIAL_STATE__TYPE = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Initial State</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INITIAL_STATE_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.statechart_1_0.impl.StateImpl <em>State</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.statechart_1_0.impl.StateImpl
	 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getState()
	 * @generated
	 */
	int STATE = 6;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__EXT = PSEUDO_STATE__EXT;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__ID = PSEUDO_STATE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__NAME = PSEUDO_STATE__NAME;

	/**
	 * The feature id for the '<em><b>Entry</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__ENTRY = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Exit</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__EXIT = PSEUDO_STATE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Sub Statechart</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__SUB_STATECHART = PSEUDO_STATE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE__TYPE = PSEUDO_STATE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>State</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATE_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.statechart_1_0.impl.StatechartImpl <em>Statechart</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.statechart_1_0.impl.StatechartImpl
	 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getStatechart()
	 * @generated
	 */
	int STATECHART = 7;

	/**
	 * The feature id for the '<em><b>State</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATECHART__STATE = 0;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATECHART__TRANSITION = 1;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATECHART__EXT = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATECHART__ID = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATECHART__NAME = 4;

	/**
	 * The number of structural features of the '<em>Statechart</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STATECHART_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.statechart_1_0.impl.SubStatechartImpl
	 * <em>Sub Statechart</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.statechart_1_0.impl.SubStatechartImpl
	 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getSubStatechart()
	 * @generated
	 */
	int SUB_STATECHART = 8;

	/**
	 * The feature id for the '<em><b>Inner Statechart</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SUB_STATECHART__INNER_STATECHART = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SUB_STATECHART__ID = 1;

	/**
	 * The number of structural features of the '<em>Sub Statechart</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SUB_STATECHART_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.statechart_1_0.impl.TransitionImpl <em>Transition</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.statechart_1_0.impl.TransitionImpl
	 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getTransition()
	 * @generated
	 */
	int TRANSITION = 9;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSITION__FROM = 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSITION__TO = 1;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSITION__CONSTRAINT = 2;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSITION__EXT = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSITION__ID = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSITION__NAME = 5;

	/**
	 * The number of structural features of the '<em>Transition</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSITION_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.archstudio.xadl3.statechart_1_0.StateType <em>State Type</em>}' enum. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.statechart_1_0.StateType
	 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getStateType()
	 * @generated
	 */
	int STATE_TYPE = 10;

	/**
	 * The meta object id for the '<em>State Type Object</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.statechart_1_0.StateType
	 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getStateTypeObject()
	 * @generated
	 */
	int STATE_TYPE_OBJECT = 11;

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.statechart_1_0.Behavior <em>Behavior</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Behavior</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.Behavior
	 * @generated
	 */
	EClass getBehavior();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.statechart_1_0.Behavior#getExt <em>Ext</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ext</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.Behavior#getExt()
	 * @see #getBehavior()
	 * @generated
	 */
	EReference getBehavior_Ext();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.statechart_1_0.Behavior#getId <em>Id</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.Behavior#getId()
	 * @see #getBehavior()
	 * @generated
	 */
	EAttribute getBehavior_Id();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.statechart_1_0.Constraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Constraint</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.Constraint
	 * @generated
	 */
	EClass getConstraint();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.statechart_1_0.Constraint#getExt <em>Ext</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ext</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.Constraint#getExt()
	 * @see #getConstraint()
	 * @generated
	 */
	EReference getConstraint_Ext();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.statechart_1_0.Constraint#getId
	 * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.Constraint#getId()
	 * @see #getConstraint()
	 * @generated
	 */
	EAttribute getConstraint_Id();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.statechart_1_0.DocumentRoot
	 * <em>Document Root</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link org.archstudio.xadl3.statechart_1_0.DocumentRoot#getMixed
	 * <em>Mixed</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.archstudio.xadl3.statechart_1_0.DocumentRoot#getXMLNSPrefixMap
	 * <em>XMLNS Prefix Map</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '
	 * {@link org.archstudio.xadl3.statechart_1_0.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.archstudio.xadl3.statechart_1_0.DocumentRoot#getStatechart <em>Statechart</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Statechart</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.DocumentRoot#getStatechart()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Statechart();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.statechart_1_0.FinalState <em>Final State</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Final State</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.FinalState
	 * @generated
	 */
	EClass getFinalState();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.statechart_1_0.FinalState#getType
	 * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.FinalState#getType()
	 * @see #getFinalState()
	 * @generated
	 */
	EAttribute getFinalState_Type();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.statechart_1_0.InitialState
	 * <em>Initial State</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Initial State</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.InitialState
	 * @generated
	 */
	EClass getInitialState();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.statechart_1_0.InitialState#getType
	 * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.InitialState#getType()
	 * @see #getInitialState()
	 * @generated
	 */
	EAttribute getInitialState_Type();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.statechart_1_0.PseudoState <em>Pseudo State</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Pseudo State</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.PseudoState
	 * @generated
	 */
	EClass getPseudoState();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.statechart_1_0.PseudoState#getExt <em>Ext</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ext</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.PseudoState#getExt()
	 * @see #getPseudoState()
	 * @generated
	 */
	EReference getPseudoState_Ext();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.statechart_1_0.PseudoState#getId
	 * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.PseudoState#getId()
	 * @see #getPseudoState()
	 * @generated
	 */
	EAttribute getPseudoState_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.statechart_1_0.PseudoState#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.PseudoState#getName()
	 * @see #getPseudoState()
	 * @generated
	 */
	EAttribute getPseudoState_Name();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.statechart_1_0.State <em>State</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>State</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.State
	 * @generated
	 */
	EClass getState();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.statechart_1_0.State#getEntry <em>Entry</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Entry</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.State#getEntry()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_Entry();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.statechart_1_0.State#getExit <em>Exit</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Exit</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.State#getExit()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_Exit();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.archstudio.xadl3.statechart_1_0.State#getSubStatechart <em>Sub Statechart</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Sub Statechart</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.State#getSubStatechart()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_SubStatechart();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.statechart_1_0.State#getType
	 * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.State#getType()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Type();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.statechart_1_0.Statechart <em>Statechart</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Statechart</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart
	 * @generated
	 */
	EClass getStatechart();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.statechart_1_0.Statechart#getState <em>State</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>State</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart#getState()
	 * @see #getStatechart()
	 * @generated
	 */
	EReference getStatechart_State();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.statechart_1_0.Statechart#getTransition <em>Transition</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Transition</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart#getTransition()
	 * @see #getStatechart()
	 * @generated
	 */
	EReference getStatechart_Transition();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.statechart_1_0.Statechart#getExt <em>Ext</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ext</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart#getExt()
	 * @see #getStatechart()
	 * @generated
	 */
	EReference getStatechart_Ext();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.statechart_1_0.Statechart#getId
	 * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart#getId()
	 * @see #getStatechart()
	 * @generated
	 */
	EAttribute getStatechart_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.statechart_1_0.Statechart#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart#getName()
	 * @see #getStatechart()
	 * @generated
	 */
	EAttribute getStatechart_Name();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.statechart_1_0.SubStatechart
	 * <em>Sub Statechart</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Sub Statechart</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.SubStatechart
	 * @generated
	 */
	EClass getSubStatechart();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.archstudio.xadl3.statechart_1_0.SubStatechart#getInnerStatechart <em>Inner Statechart</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Inner Statechart</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.SubStatechart#getInnerStatechart()
	 * @see #getSubStatechart()
	 * @generated
	 */
	EReference getSubStatechart_InnerStatechart();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.statechart_1_0.SubStatechart#getId
	 * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.SubStatechart#getId()
	 * @see #getSubStatechart()
	 * @generated
	 */
	EAttribute getSubStatechart_Id();

	/**
	 * Returns the meta object for class '{@link org.archstudio.xadl3.statechart_1_0.Transition <em>Transition</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Transition</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.Transition
	 * @generated
	 */
	EClass getTransition();

	/**
	 * Returns the meta object for the reference '{@link org.archstudio.xadl3.statechart_1_0.Transition#getFrom
	 * <em>From</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>From</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.Transition#getFrom()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_From();

	/**
	 * Returns the meta object for the reference '{@link org.archstudio.xadl3.statechart_1_0.Transition#getTo
	 * <em>To</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>To</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.Transition#getTo()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_To();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.archstudio.xadl3.statechart_1_0.Transition#getConstraint <em>Constraint</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Constraint</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.Transition#getConstraint()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Constraint();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.statechart_1_0.Transition#getExt <em>Ext</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ext</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.Transition#getExt()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Ext();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.statechart_1_0.Transition#getId
	 * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.Transition#getId()
	 * @see #getTransition()
	 * @generated
	 */
	EAttribute getTransition_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.xadl3.statechart_1_0.Transition#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.Transition#getName()
	 * @see #getTransition()
	 * @generated
	 */
	EAttribute getTransition_Name();

	/**
	 * Returns the meta object for enum '{@link org.archstudio.xadl3.statechart_1_0.StateType <em>State Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>State Type</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.StateType
	 * @generated
	 */
	EEnum getStateType();

	/**
	 * Returns the meta object for data type '{@link org.archstudio.xadl3.statechart_1_0.StateType
	 * <em>State Type Object</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>State Type Object</em>'.
	 * @see org.archstudio.xadl3.statechart_1_0.StateType
	 * @model instanceClass="org.archstudio.xadl3.statechart_1_0.StateType"
	 *        extendedMetaData="name='StateType:Object' baseType='StateType'"
	 * @generated
	 */
	EDataType getStateTypeObject();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Statechart_1_0Factory getStatechart_1_0Factory();

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
		 * The meta object literal for the '{@link org.archstudio.xadl3.statechart_1_0.impl.BehaviorImpl
		 * <em>Behavior</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.statechart_1_0.impl.BehaviorImpl
		 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getBehavior()
		 * @generated
		 */
		EClass BEHAVIOR = eINSTANCE.getBehavior();

		/**
		 * The meta object literal for the '<em><b>Ext</b></em>' containment reference list feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference BEHAVIOR__EXT = eINSTANCE.getBehavior_Ext();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute BEHAVIOR__ID = eINSTANCE.getBehavior_Id();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.statechart_1_0.impl.ConstraintImpl
		 * <em>Constraint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.statechart_1_0.impl.ConstraintImpl
		 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getConstraint()
		 * @generated
		 */
		EClass CONSTRAINT = eINSTANCE.getConstraint();

		/**
		 * The meta object literal for the '<em><b>Ext</b></em>' containment reference list feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CONSTRAINT__EXT = eINSTANCE.getConstraint_Ext();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CONSTRAINT__ID = eINSTANCE.getConstraint_Id();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.statechart_1_0.impl.DocumentRootImpl
		 * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.statechart_1_0.impl.DocumentRootImpl
		 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getDocumentRoot()
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
		 * The meta object literal for the '<em><b>Statechart</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STATECHART = eINSTANCE.getDocumentRoot_Statechart();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.statechart_1_0.impl.FinalStateImpl
		 * <em>Final State</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.statechart_1_0.impl.FinalStateImpl
		 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getFinalState()
		 * @generated
		 */
		EClass FINAL_STATE = eINSTANCE.getFinalState();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute FINAL_STATE__TYPE = eINSTANCE.getFinalState_Type();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.statechart_1_0.impl.InitialStateImpl
		 * <em>Initial State</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.statechart_1_0.impl.InitialStateImpl
		 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getInitialState()
		 * @generated
		 */
		EClass INITIAL_STATE = eINSTANCE.getInitialState();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute INITIAL_STATE__TYPE = eINSTANCE.getInitialState_Type();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.statechart_1_0.impl.PseudoStateImpl
		 * <em>Pseudo State</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.statechart_1_0.impl.PseudoStateImpl
		 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getPseudoState()
		 * @generated
		 */
		EClass PSEUDO_STATE = eINSTANCE.getPseudoState();

		/**
		 * The meta object literal for the '<em><b>Ext</b></em>' containment reference list feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PSEUDO_STATE__EXT = eINSTANCE.getPseudoState_Ext();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PSEUDO_STATE__ID = eINSTANCE.getPseudoState_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PSEUDO_STATE__NAME = eINSTANCE.getPseudoState_Name();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.statechart_1_0.impl.StateImpl <em>State</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.statechart_1_0.impl.StateImpl
		 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getState()
		 * @generated
		 */
		EClass STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '<em><b>Entry</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference STATE__ENTRY = eINSTANCE.getState_Entry();

		/**
		 * The meta object literal for the '<em><b>Exit</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference STATE__EXIT = eINSTANCE.getState_Exit();

		/**
		 * The meta object literal for the '<em><b>Sub Statechart</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference STATE__SUB_STATECHART = eINSTANCE.getState_SubStatechart();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute STATE__TYPE = eINSTANCE.getState_Type();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.statechart_1_0.impl.StatechartImpl
		 * <em>Statechart</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.statechart_1_0.impl.StatechartImpl
		 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getStatechart()
		 * @generated
		 */
		EClass STATECHART = eINSTANCE.getStatechart();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference STATECHART__STATE = eINSTANCE.getStatechart_State();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference STATECHART__TRANSITION = eINSTANCE.getStatechart_Transition();

		/**
		 * The meta object literal for the '<em><b>Ext</b></em>' containment reference list feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference STATECHART__EXT = eINSTANCE.getStatechart_Ext();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute STATECHART__ID = eINSTANCE.getStatechart_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute STATECHART__NAME = eINSTANCE.getStatechart_Name();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.statechart_1_0.impl.SubStatechartImpl
		 * <em>Sub Statechart</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.statechart_1_0.impl.SubStatechartImpl
		 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getSubStatechart()
		 * @generated
		 */
		EClass SUB_STATECHART = eINSTANCE.getSubStatechart();

		/**
		 * The meta object literal for the '<em><b>Inner Statechart</b></em>' reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SUB_STATECHART__INNER_STATECHART = eINSTANCE.getSubStatechart_InnerStatechart();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SUB_STATECHART__ID = eINSTANCE.getSubStatechart_Id();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.statechart_1_0.impl.TransitionImpl
		 * <em>Transition</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.statechart_1_0.impl.TransitionImpl
		 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getTransition()
		 * @generated
		 */
		EClass TRANSITION = eINSTANCE.getTransition();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference TRANSITION__FROM = eINSTANCE.getTransition_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference TRANSITION__TO = eINSTANCE.getTransition_To();

		/**
		 * The meta object literal for the '<em><b>Constraint</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference TRANSITION__CONSTRAINT = eINSTANCE.getTransition_Constraint();

		/**
		 * The meta object literal for the '<em><b>Ext</b></em>' containment reference list feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference TRANSITION__EXT = eINSTANCE.getTransition_Ext();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute TRANSITION__ID = eINSTANCE.getTransition_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute TRANSITION__NAME = eINSTANCE.getTransition_Name();

		/**
		 * The meta object literal for the '{@link org.archstudio.xadl3.statechart_1_0.StateType <em>State Type</em>}'
		 * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.statechart_1_0.StateType
		 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getStateType()
		 * @generated
		 */
		EEnum STATE_TYPE = eINSTANCE.getStateType();

		/**
		 * The meta object literal for the '<em>State Type Object</em>' data type. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.statechart_1_0.StateType
		 * @see org.archstudio.xadl3.statechart_1_0.impl.Statechart_1_0PackageImpl#getStateTypeObject()
		 * @generated
		 */
		EDataType STATE_TYPE_OBJECT = eINSTANCE.getStateTypeObject();

	}

} //Statechart_1_0Package
