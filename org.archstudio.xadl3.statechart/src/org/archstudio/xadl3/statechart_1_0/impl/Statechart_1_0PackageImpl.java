/**
 */
package org.archstudio.xadl3.statechart_1_0.impl;

import org.archstudio.xadl3.statechart_1_0.Behavior;
import org.archstudio.xadl3.statechart_1_0.Constraint;
import org.archstudio.xadl3.statechart_1_0.DocumentRoot;
import org.archstudio.xadl3.statechart_1_0.FinalState;
import org.archstudio.xadl3.statechart_1_0.InitialState;
import org.archstudio.xadl3.statechart_1_0.PseudoState;
import org.archstudio.xadl3.statechart_1_0.State;
import org.archstudio.xadl3.statechart_1_0.StateType;
import org.archstudio.xadl3.statechart_1_0.Statechart;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Factory;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xadl3.statechart_1_0.SubStatechart;
import org.archstudio.xadl3.statechart_1_0.Transition;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.impl.Xadlcore_3_0PackageImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.w3.xlink.XlinkPackage;
import org.w3.xlink.impl.XlinkPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class Statechart_1_0PackageImpl extends EPackageImpl implements Statechart_1_0Package {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass behaviorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass constraintEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass documentRootEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass finalStateEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass initialStateEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass pseudoStateEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass stateEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass statechartEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass subStatechartEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass transitionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum stateTypeEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType stateTypeObjectEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
	 * EPackage.Registry} by the package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
	 * performs initialization of the package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Statechart_1_0PackageImpl() {
		super(eNS_URI, Statechart_1_0Factory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link Statechart_1_0Package#eINSTANCE} when that field is accessed. Clients
	 * should not invoke it directly. Instead, they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Statechart_1_0Package init() {
		if (isInited) {
			return (Statechart_1_0Package) EPackage.Registry.INSTANCE.getEPackage(Statechart_1_0Package.eNS_URI);
		}

		// Obtain or create and register package
		Statechart_1_0PackageImpl theStatechart_1_0Package = (Statechart_1_0PackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof Statechart_1_0PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new Statechart_1_0PackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		Xadlcore_3_0PackageImpl theXadlcore_3_0Package = (Xadlcore_3_0PackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(Xadlcore_3_0Package.eNS_URI) instanceof Xadlcore_3_0PackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(Xadlcore_3_0Package.eNS_URI) : Xadlcore_3_0Package.eINSTANCE);
		XlinkPackageImpl theXlinkPackage = (XlinkPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(XlinkPackage.eNS_URI) instanceof XlinkPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(XlinkPackage.eNS_URI) : XlinkPackage.eINSTANCE);

		// Create package meta-data objects
		theStatechart_1_0Package.createPackageContents();
		theXadlcore_3_0Package.createPackageContents();
		theXlinkPackage.createPackageContents();

		// Initialize created meta-data
		theStatechart_1_0Package.initializePackageContents();
		theXadlcore_3_0Package.initializePackageContents();
		theXlinkPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theStatechart_1_0Package.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Statechart_1_0Package.eNS_URI, theStatechart_1_0Package);
		return theStatechart_1_0Package;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getBehavior() {
		return behaviorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getBehavior_Ext() {
		return (EReference) behaviorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getBehavior_Id() {
		return (EAttribute) behaviorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getConstraint() {
		return constraintEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getConstraint_Ext() {
		return (EReference) constraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getConstraint_Id() {
		return (EAttribute) constraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getDocumentRoot() {
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute) documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Statechart() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getFinalState() {
		return finalStateEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getFinalState_Type() {
		return (EAttribute) finalStateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getInitialState() {
		return initialStateEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getInitialState_Type() {
		return (EAttribute) initialStateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getPseudoState() {
		return pseudoStateEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getPseudoState_Ext() {
		return (EReference) pseudoStateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getPseudoState_Id() {
		return (EAttribute) pseudoStateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getPseudoState_Name() {
		return (EAttribute) pseudoStateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getState() {
		return stateEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getState_Entry() {
		return (EReference) stateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getState_Exit() {
		return (EReference) stateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getState_SubStatechart() {
		return (EReference) stateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getState_Type() {
		return (EAttribute) stateEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getStatechart() {
		return statechartEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getStatechart_State() {
		return (EReference) statechartEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getStatechart_Transition() {
		return (EReference) statechartEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getStatechart_Ext() {
		return (EReference) statechartEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getStatechart_Id() {
		return (EAttribute) statechartEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getStatechart_Name() {
		return (EAttribute) statechartEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getSubStatechart() {
		return subStatechartEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getSubStatechart_InnerStatechart() {
		return (EReference) subStatechartEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getSubStatechart_Id() {
		return (EAttribute) subStatechartEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getTransition() {
		return transitionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getTransition_From() {
		return (EReference) transitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getTransition_To() {
		return (EReference) transitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getTransition_Constraint() {
		return (EReference) transitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getTransition_Ext() {
		return (EReference) transitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getTransition_Id() {
		return (EAttribute) transitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getTransition_Name() {
		return (EAttribute) transitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EEnum getStateType() {
		return stateTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EDataType getStateTypeObject() {
		return stateTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Statechart_1_0Factory getStatechart_1_0Factory() {
		return (Statechart_1_0Factory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is guarded to have no affect on any invocation but
	 * its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) {
			return;
		}
		isCreated = true;

		// Create classes and their features
		behaviorEClass = createEClass(BEHAVIOR);
		createEReference(behaviorEClass, BEHAVIOR__EXT);
		createEAttribute(behaviorEClass, BEHAVIOR__ID);

		constraintEClass = createEClass(CONSTRAINT);
		createEReference(constraintEClass, CONSTRAINT__EXT);
		createEAttribute(constraintEClass, CONSTRAINT__ID);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__STATECHART);

		finalStateEClass = createEClass(FINAL_STATE);
		createEAttribute(finalStateEClass, FINAL_STATE__TYPE);

		initialStateEClass = createEClass(INITIAL_STATE);
		createEAttribute(initialStateEClass, INITIAL_STATE__TYPE);

		pseudoStateEClass = createEClass(PSEUDO_STATE);
		createEReference(pseudoStateEClass, PSEUDO_STATE__EXT);
		createEAttribute(pseudoStateEClass, PSEUDO_STATE__ID);
		createEAttribute(pseudoStateEClass, PSEUDO_STATE__NAME);

		stateEClass = createEClass(STATE);
		createEReference(stateEClass, STATE__ENTRY);
		createEReference(stateEClass, STATE__EXIT);
		createEReference(stateEClass, STATE__SUB_STATECHART);
		createEAttribute(stateEClass, STATE__TYPE);

		statechartEClass = createEClass(STATECHART);
		createEReference(statechartEClass, STATECHART__STATE);
		createEReference(statechartEClass, STATECHART__TRANSITION);
		createEReference(statechartEClass, STATECHART__EXT);
		createEAttribute(statechartEClass, STATECHART__ID);
		createEAttribute(statechartEClass, STATECHART__NAME);

		subStatechartEClass = createEClass(SUB_STATECHART);
		createEReference(subStatechartEClass, SUB_STATECHART__INNER_STATECHART);
		createEAttribute(subStatechartEClass, SUB_STATECHART__ID);

		transitionEClass = createEClass(TRANSITION);
		createEReference(transitionEClass, TRANSITION__FROM);
		createEReference(transitionEClass, TRANSITION__TO);
		createEReference(transitionEClass, TRANSITION__CONSTRAINT);
		createEReference(transitionEClass, TRANSITION__EXT);
		createEAttribute(transitionEClass, TRANSITION__ID);
		createEAttribute(transitionEClass, TRANSITION__NAME);

		// Create enums
		stateTypeEEnum = createEEnum(STATE_TYPE);

		// Create data types
		stateTypeObjectEDataType = createEDataType(STATE_TYPE_OBJECT);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any
	 * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) {
			return;
		}
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		Xadlcore_3_0Package theXadlcore_3_0Package = (Xadlcore_3_0Package) EPackage.Registry.INSTANCE
				.getEPackage(Xadlcore_3_0Package.eNS_URI);
		XMLTypePackage theXMLTypePackage = (XMLTypePackage) EPackage.Registry.INSTANCE
				.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		finalStateEClass.getESuperTypes().add(this.getPseudoState());
		initialStateEClass.getESuperTypes().add(this.getPseudoState());
		stateEClass.getESuperTypes().add(this.getPseudoState());

		// Initialize classes, features, and operations; add parameters
		initEClass(behaviorEClass, Behavior.class, "Behavior", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBehavior_Ext(), theXadlcore_3_0Package.getExtension(), null, "ext", null, 0, -1,
				Behavior.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBehavior_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, Behavior.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(constraintEClass, Constraint.class, "Constraint", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConstraint_Ext(), theXadlcore_3_0Package.getExtension(), null, "ext", null, 0, -1,
				Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstraint_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, Constraint.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null,
				"xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null,
				"xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Statechart(), this.getStatechart(), null, "statechart", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);

		initEClass(finalStateEClass, FinalState.class, "FinalState", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFinalState_Type(), this.getStateType(), "type", "final", 0, 1, FinalState.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(initialStateEClass, InitialState.class, "InitialState", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInitialState_Type(), this.getStateType(), "type", "initial", 0, 1, InitialState.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pseudoStateEClass, PseudoState.class, "PseudoState", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPseudoState_Ext(), theXadlcore_3_0Package.getExtension(), null, "ext", null, 0, -1,
				PseudoState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPseudoState_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, PseudoState.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPseudoState_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, PseudoState.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateEClass, State.class, "State", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getState_Entry(), this.getBehavior(), null, "entry", null, 0, -1, State.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getState_Exit(), this.getBehavior(), null, "exit", null, 0, -1, State.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getState_SubStatechart(), this.getSubStatechart(), null, "subStatechart", null, 0, 1,
				State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getState_Type(), this.getStateType(), "type", "state", 0, 1, State.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(statechartEClass, Statechart.class, "Statechart", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStatechart_State(), this.getPseudoState(), null, "state", null, 0, -1, Statechart.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStatechart_Transition(), this.getTransition(), null, "transition", null, 0, -1,
				Statechart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStatechart_Ext(), theXadlcore_3_0Package.getExtension(), null, "ext", null, 0, -1,
				Statechart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatechart_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, Statechart.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStatechart_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Statechart.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(subStatechartEClass, SubStatechart.class, "SubStatechart", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSubStatechart_InnerStatechart(), this.getStatechart(), null, "innerStatechart", null, 1, 1,
				SubStatechart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSubStatechart_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, SubStatechart.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transitionEClass, Transition.class, "Transition", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransition_From(), this.getPseudoState(), null, "from", null, 1, 1, Transition.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransition_To(), this.getPseudoState(), null, "to", null, 1, 1, Transition.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransition_Constraint(), this.getConstraint(), null, "constraint", null, 0, 1,
				Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransition_Ext(), theXadlcore_3_0Package.getExtension(), null, "ext", null, 0, -1,
				Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransition_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, Transition.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransition_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Transition.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(stateTypeEEnum, StateType.class, "StateType");
		addEEnumLiteral(stateTypeEEnum, StateType.INITIAL);
		addEEnumLiteral(stateTypeEEnum, StateType.STATE);
		addEEnumLiteral(stateTypeEEnum, StateType.FINAL);

		// Initialize data types
		initEDataType(stateTypeObjectEDataType, StateType.class, "StateTypeObject", IS_SERIALIZABLE,
				IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
		addAnnotation(behaviorEClass, source, new String[] { "name", "Behavior", "kind", "elementOnly" });
		addAnnotation(getBehavior_Ext(), source, new String[] { "kind", "element", "name", "ext", "namespace",
				"##targetNamespace" });
		addAnnotation(getBehavior_Id(), source, new String[] { "kind", "attribute", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(constraintEClass, source, new String[] { "name", "Constraint", "kind", "elementOnly" });
		addAnnotation(getConstraint_Ext(), source, new String[] { "kind", "element", "name", "ext", "namespace",
				"##targetNamespace" });
		addAnnotation(getConstraint_Id(), source, new String[] { "kind", "attribute", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(documentRootEClass, source, new String[] { "name", "", "kind", "mixed" });
		addAnnotation(getDocumentRoot_Mixed(), source, new String[] { "kind", "elementWildcard", "name", ":mixed" });
		addAnnotation(getDocumentRoot_XMLNSPrefixMap(), source, new String[] { "kind", "attribute", "name",
				"xmlns:prefix" });
		addAnnotation(getDocumentRoot_XSISchemaLocation(), source, new String[] { "kind", "attribute", "name",
				"xsi:schemaLocation" });
		addAnnotation(getDocumentRoot_Statechart(), source, new String[] { "kind", "element", "name", "statechart",
				"namespace", "##targetNamespace", "affiliation",
				"http://www.archstudio.org/xadl3/schemas/xadlcore-3.0.xsd#topLevelElement" });
		addAnnotation(finalStateEClass, source, new String[] { "name", "FinalState", "kind", "elementOnly" });
		addAnnotation(getFinalState_Type(), source, new String[] { "kind", "attribute", "name", "type", "namespace",
				"##targetNamespace" });
		addAnnotation(initialStateEClass, source, new String[] { "name", "InitialState", "kind", "elementOnly" });
		addAnnotation(getInitialState_Type(), source, new String[] { "kind", "attribute", "name", "type", "namespace",
				"##targetNamespace" });
		addAnnotation(pseudoStateEClass, source, new String[] { "name", "PseudoState", "kind", "elementOnly" });
		addAnnotation(getPseudoState_Ext(), source, new String[] { "kind", "element", "name", "ext", "namespace",
				"##targetNamespace" });
		addAnnotation(getPseudoState_Id(), source, new String[] { "kind", "attribute", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(getPseudoState_Name(), source, new String[] { "kind", "attribute", "name", "name", "namespace",
				"##targetNamespace" });
		addAnnotation(stateEClass, source, new String[] { "name", "State", "kind", "elementOnly" });
		addAnnotation(getState_Entry(), source, new String[] { "kind", "element", "name", "entry", "namespace",
				"##targetNamespace" });
		addAnnotation(getState_Exit(), source, new String[] { "kind", "element", "name", "exit", "namespace",
				"##targetNamespace" });
		addAnnotation(getState_SubStatechart(), source, new String[] { "kind", "element", "name", "subStatechart",
				"namespace", "##targetNamespace" });
		addAnnotation(getState_Type(), source, new String[] { "kind", "attribute", "name", "type", "namespace",
				"##targetNamespace" });
		addAnnotation(statechartEClass, source, new String[] { "name", "Statechart", "kind", "elementOnly" });
		addAnnotation(getStatechart_State(), source, new String[] { "kind", "element", "name", "state", "namespace",
				"##targetNamespace" });
		addAnnotation(getStatechart_Transition(), source, new String[] { "kind", "element", "name", "transition",
				"namespace", "##targetNamespace" });
		addAnnotation(getStatechart_Ext(), source, new String[] { "kind", "element", "name", "ext", "namespace",
				"##targetNamespace" });
		addAnnotation(getStatechart_Id(), source, new String[] { "kind", "attribute", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(getStatechart_Name(), source, new String[] { "kind", "attribute", "name", "name", "namespace",
				"##targetNamespace" });
		addAnnotation(stateTypeEEnum, source, new String[] { "name", "StateType" });
		addAnnotation(stateTypeObjectEDataType, source, new String[] { "name", "StateType:Object", "baseType",
				"StateType" });
		addAnnotation(subStatechartEClass, source, new String[] { "name", "SubStatechart", "kind", "elementOnly" });
		addAnnotation(getSubStatechart_InnerStatechart(), source, new String[] { "kind", "element", "name",
				"innerStatechart", "namespace", "##targetNamespace" });
		addAnnotation(getSubStatechart_Id(), source, new String[] { "kind", "attribute", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(transitionEClass, source, new String[] { "name", "Transition", "kind", "elementOnly" });
		addAnnotation(getTransition_From(), source, new String[] { "kind", "element", "name", "from", "namespace",
				"##targetNamespace" });
		addAnnotation(getTransition_To(), source, new String[] { "kind", "element", "name", "to", "namespace",
				"##targetNamespace" });
		addAnnotation(getTransition_Constraint(), source, new String[] { "kind", "element", "name", "constraint",
				"namespace", "##targetNamespace" });
		addAnnotation(getTransition_Ext(), source, new String[] { "kind", "element", "name", "ext", "namespace",
				"##targetNamespace" });
		addAnnotation(getTransition_Id(), source, new String[] { "kind", "attribute", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(getTransition_Name(), source, new String[] { "kind", "attribute", "name", "name", "namespace",
				"##targetNamespace" });
	}

} //Statechart_1_0PackageImpl
