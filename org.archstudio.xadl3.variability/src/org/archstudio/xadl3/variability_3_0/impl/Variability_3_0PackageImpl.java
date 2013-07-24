/**
 */
package org.archstudio.xadl3.variability_3_0.impl;

import org.archstudio.xadl3.variability_3_0.AttributeChange;
import org.archstudio.xadl3.variability_3_0.Change;
import org.archstudio.xadl3.variability_3_0.ChangeSet;
import org.archstudio.xadl3.variability_3_0.ChangeSetOfChanges;
import org.archstudio.xadl3.variability_3_0.DocumentRoot;
import org.archstudio.xadl3.variability_3_0.ElementChange;
import org.archstudio.xadl3.variability_3_0.ElementManyChange;
import org.archstudio.xadl3.variability_3_0.JavaTransformChangeSetOfChanges;
import org.archstudio.xadl3.variability_3_0.Relationship;
import org.archstudio.xadl3.variability_3_0.TransformChangeSetOfChanges;
import org.archstudio.xadl3.variability_3_0.Variability;
import org.archstudio.xadl3.variability_3_0.Variability_3_0Factory;
import org.archstudio.xadl3.variability_3_0.Variability_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.impl.Xadlcore_3_0PackageImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
public class Variability_3_0PackageImpl extends EPackageImpl implements Variability_3_0Package {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass attributeChangeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass changeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass changeSetEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass changeSetOfChangesEClass = null;

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
	private EClass elementChangeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass elementManyChangeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass javaTransformChangeSetOfChangesEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass relationshipEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass transformChangeSetOfChangesEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass variabilityEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
	 * EPackage.Registry} by the package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
	 * performs initialization of the package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Variability_3_0PackageImpl() {
		super(eNS_URI, Variability_3_0Factory.eINSTANCE);
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
	 * This method is used to initialize {@link Variability_3_0Package#eINSTANCE} when that field is accessed. Clients
	 * should not invoke it directly. Instead, they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Variability_3_0Package init() {
		if (isInited) {
			return (Variability_3_0Package) EPackage.Registry.INSTANCE.getEPackage(Variability_3_0Package.eNS_URI);
		}

		// Obtain or create and register package
		Variability_3_0PackageImpl theVariability_3_0Package = (Variability_3_0PackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof Variability_3_0PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new Variability_3_0PackageImpl());

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
		theVariability_3_0Package.createPackageContents();
		theXadlcore_3_0Package.createPackageContents();
		theXlinkPackage.createPackageContents();

		// Initialize created meta-data
		theVariability_3_0Package.initializePackageContents();
		theXadlcore_3_0Package.initializePackageContents();
		theXlinkPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theVariability_3_0Package.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Variability_3_0Package.eNS_URI, theVariability_3_0Package);
		return theVariability_3_0Package;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getAttributeChange() {
		return attributeChangeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getAttributeChange_Value() {
		return (EAttribute) attributeChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getChange() {
		return changeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getChange_Name() {
		return (EAttribute) changeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getChangeSet() {
		return changeSetEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getChangeSet_Ext() {
		return (EReference) changeSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getChangeSet_Id() {
		return (EAttribute) changeSetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getChangeSet_Name() {
		return (EAttribute) changeSetEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getChangeSetOfChanges() {
		return changeSetOfChangesEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getChangeSetOfChanges_ElementChange() {
		return (EReference) changeSetOfChangesEClass.getEStructuralFeatures().get(0);
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
	public EReference getDocumentRoot_Variability() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getElementChange() {
		return elementChangeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getElementChange_Change() {
		return (EReference) elementChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getElementChange_Type() {
		return (EAttribute) elementChangeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getElementManyChange() {
		return elementManyChangeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getElementManyChange_Change() {
		return (EReference) elementManyChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getJavaTransformChangeSetOfChanges() {
		return javaTransformChangeSetOfChangesEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getJavaTransformChangeSetOfChanges_Bundle() {
		return (EAttribute) javaTransformChangeSetOfChangesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getJavaTransformChangeSetOfChanges_Class() {
		return (EAttribute) javaTransformChangeSetOfChangesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getRelationship() {
		return relationshipEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getRelationship_Ext() {
		return (EReference) relationshipEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getRelationship_Id() {
		return (EAttribute) relationshipEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getRelationship_Name() {
		return (EAttribute) relationshipEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getTransformChangeSetOfChanges() {
		return transformChangeSetOfChangesEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getVariability() {
		return variabilityEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getVariability_ActiveChangeSet() {
		return (EReference) variabilityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getVariability_AppliedChangeSets() {
		return (EReference) variabilityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getVariability_ChangeSet() {
		return (EReference) variabilityEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getVariability_Relationship() {
		return (EReference) variabilityEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getVariability_Overview() {
		return (EAttribute) variabilityEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Variability_3_0Factory getVariability_3_0Factory() {
		return (Variability_3_0Factory) getEFactoryInstance();
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
		attributeChangeEClass = createEClass(ATTRIBUTE_CHANGE);
		createEAttribute(attributeChangeEClass, ATTRIBUTE_CHANGE__VALUE);

		changeEClass = createEClass(CHANGE);
		createEAttribute(changeEClass, CHANGE__NAME);

		changeSetEClass = createEClass(CHANGE_SET);
		createEReference(changeSetEClass, CHANGE_SET__EXT);
		createEAttribute(changeSetEClass, CHANGE_SET__ID);
		createEAttribute(changeSetEClass, CHANGE_SET__NAME);

		changeSetOfChangesEClass = createEClass(CHANGE_SET_OF_CHANGES);
		createEReference(changeSetOfChangesEClass, CHANGE_SET_OF_CHANGES__ELEMENT_CHANGE);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__VARIABILITY);

		elementChangeEClass = createEClass(ELEMENT_CHANGE);
		createEReference(elementChangeEClass, ELEMENT_CHANGE__CHANGE);
		createEAttribute(elementChangeEClass, ELEMENT_CHANGE__TYPE);

		elementManyChangeEClass = createEClass(ELEMENT_MANY_CHANGE);
		createEReference(elementManyChangeEClass, ELEMENT_MANY_CHANGE__CHANGE);

		javaTransformChangeSetOfChangesEClass = createEClass(JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES);
		createEAttribute(javaTransformChangeSetOfChangesEClass, JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__BUNDLE);
		createEAttribute(javaTransformChangeSetOfChangesEClass, JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__CLASS);

		relationshipEClass = createEClass(RELATIONSHIP);
		createEReference(relationshipEClass, RELATIONSHIP__EXT);
		createEAttribute(relationshipEClass, RELATIONSHIP__ID);
		createEAttribute(relationshipEClass, RELATIONSHIP__NAME);

		transformChangeSetOfChangesEClass = createEClass(TRANSFORM_CHANGE_SET_OF_CHANGES);

		variabilityEClass = createEClass(VARIABILITY);
		createEReference(variabilityEClass, VARIABILITY__ACTIVE_CHANGE_SET);
		createEReference(variabilityEClass, VARIABILITY__APPLIED_CHANGE_SETS);
		createEReference(variabilityEClass, VARIABILITY__CHANGE_SET);
		createEReference(variabilityEClass, VARIABILITY__RELATIONSHIP);
		createEAttribute(variabilityEClass, VARIABILITY__OVERVIEW);
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
		XMLTypePackage theXMLTypePackage = (XMLTypePackage) EPackage.Registry.INSTANCE
				.getEPackage(XMLTypePackage.eNS_URI);
		Xadlcore_3_0Package theXadlcore_3_0Package = (Xadlcore_3_0Package) EPackage.Registry.INSTANCE
				.getEPackage(Xadlcore_3_0Package.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		attributeChangeEClass.getESuperTypes().add(this.getChange());
		changeSetOfChangesEClass.getESuperTypes().add(this.getChangeSet());
		elementChangeEClass.getESuperTypes().add(this.getChange());
		elementManyChangeEClass.getESuperTypes().add(this.getChange());
		javaTransformChangeSetOfChangesEClass.getESuperTypes().add(this.getTransformChangeSetOfChanges());
		transformChangeSetOfChangesEClass.getESuperTypes().add(this.getChangeSetOfChanges());

		// Initialize classes, features, and operations; add parameters
		initEClass(attributeChangeEClass, AttributeChange.class, "AttributeChange", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAttributeChange_Value(), theXMLTypePackage.getString(), "value", null, 0, 1,
				AttributeChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(changeEClass, Change.class, "Change", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getChange_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Change.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(changeSetEClass, ChangeSet.class, "ChangeSet", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getChangeSet_Ext(), theXadlcore_3_0Package.getExtension(), null, "ext", null, 0, -1,
				ChangeSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getChangeSet_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ChangeSet.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getChangeSet_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ChangeSet.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(changeSetOfChangesEClass, ChangeSetOfChanges.class, "ChangeSetOfChanges", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getChangeSetOfChanges_ElementChange(), this.getElementChange(), null, "elementChange", null, 0,
				1, ChangeSetOfChanges.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		initEReference(getDocumentRoot_Variability(), this.getVariability(), null, "variability", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);

		initEClass(elementChangeEClass, ElementChange.class, "ElementChange", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getElementChange_Change(), this.getChange(), null, "change", null, 0, -1, ElementChange.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElementChange_Type(), theXMLTypePackage.getString(), "type", null, 0, 1, ElementChange.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(elementManyChangeEClass, ElementManyChange.class, "ElementManyChange", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getElementManyChange_Change(), this.getElementChange(), null, "change", null, 0, -1,
				ElementManyChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(javaTransformChangeSetOfChangesEClass, JavaTransformChangeSetOfChanges.class,
				"JavaTransformChangeSetOfChanges", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJavaTransformChangeSetOfChanges_Bundle(), theXMLTypePackage.getString(), "bundle", null, 0,
				1, JavaTransformChangeSetOfChanges.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJavaTransformChangeSetOfChanges_Class(), theXMLTypePackage.getString(), "class", null, 0, 1,
				JavaTransformChangeSetOfChanges.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(relationshipEClass, Relationship.class, "Relationship", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRelationship_Ext(), theXadlcore_3_0Package.getExtension(), null, "ext", null, 0, -1,
				Relationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelationship_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, Relationship.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelationship_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Relationship.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transformChangeSetOfChangesEClass, TransformChangeSetOfChanges.class, "TransformChangeSetOfChanges",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(variabilityEClass, Variability.class, "Variability", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVariability_ActiveChangeSet(), this.getChangeSet(), null, "activeChangeSet", null, 0, 1,
				Variability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVariability_AppliedChangeSets(), this.getChangeSet(), null, "appliedChangeSets", null, 1, -1,
				Variability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVariability_ChangeSet(), this.getChangeSet(), null, "changeSet", null, 0, -1,
				Variability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVariability_Relationship(), this.getRelationship(), null, "relationship", null, 0, -1,
				Variability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVariability_Overview(), theXMLTypePackage.getBoolean(), "overview", null, 0, 1,
				Variability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

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
		addAnnotation(attributeChangeEClass, source, new String[] { "name", "AttributeChange", "kind", "empty" });
		addAnnotation(getAttributeChange_Value(), source, new String[] { "kind", "attribute", "name", "value",
				"namespace", "##targetNamespace" });
		addAnnotation(changeEClass, source, new String[] { "name", "Change", "kind", "empty" });
		addAnnotation(getChange_Name(), source, new String[] { "kind", "attribute", "name", "name", "namespace",
				"##targetNamespace" });
		addAnnotation(changeSetEClass, source, new String[] { "name", "ChangeSet", "kind", "elementOnly" });
		addAnnotation(getChangeSet_Ext(), source, new String[] { "kind", "element", "name", "ext", "namespace",
				"##targetNamespace" });
		addAnnotation(getChangeSet_Id(), source, new String[] { "kind", "attribute", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(getChangeSet_Name(), source, new String[] { "kind", "attribute", "name", "name", "namespace",
				"##targetNamespace" });
		addAnnotation(changeSetOfChangesEClass, source, new String[] { "name", "ChangeSetOfChanges", "kind",
				"elementOnly" });
		addAnnotation(getChangeSetOfChanges_ElementChange(), source, new String[] { "kind", "element", "name",
				"elementChange", "namespace", "##targetNamespace" });
		addAnnotation(documentRootEClass, source, new String[] { "name", "", "kind", "mixed" });
		addAnnotation(getDocumentRoot_Mixed(), source, new String[] { "kind", "elementWildcard", "name", ":mixed" });
		addAnnotation(getDocumentRoot_XMLNSPrefixMap(), source, new String[] { "kind", "attribute", "name",
				"xmlns:prefix" });
		addAnnotation(getDocumentRoot_XSISchemaLocation(), source, new String[] { "kind", "attribute", "name",
				"xsi:schemaLocation" });
		addAnnotation(getDocumentRoot_Variability(), source, new String[] { "kind", "element", "name", "variability",
				"namespace", "##targetNamespace", "affiliation",
				"http://www.archstudio.org/xadl3/schemas/xadlcore-3.0.xsd#topLevelElement" });
		addAnnotation(elementChangeEClass, source, new String[] { "name", "ElementChange", "kind", "elementOnly" });
		addAnnotation(getElementChange_Change(), source, new String[] { "kind", "element", "name", "change",
				"namespace", "##targetNamespace" });
		addAnnotation(getElementChange_Type(), source, new String[] { "kind", "attribute", "name", "type", "namespace",
				"##targetNamespace" });
		addAnnotation(elementManyChangeEClass, source, new String[] { "name", "ElementManyChange", "kind",
				"elementOnly" });
		addAnnotation(getElementManyChange_Change(), source, new String[] { "kind", "element", "name", "change",
				"namespace", "##targetNamespace" });
		addAnnotation(javaTransformChangeSetOfChangesEClass, source, new String[] { "name",
				"JavaTransformChangeSetOfChanges", "kind", "elementOnly" });
		addAnnotation(getJavaTransformChangeSetOfChanges_Bundle(), source, new String[] { "kind", "attribute", "name",
				"bundle", "namespace", "##targetNamespace" });
		addAnnotation(getJavaTransformChangeSetOfChanges_Class(), source, new String[] { "kind", "attribute", "name",
				"class", "namespace", "##targetNamespace" });
		addAnnotation(relationshipEClass, source, new String[] { "name", "Relationship", "kind", "elementOnly" });
		addAnnotation(getRelationship_Ext(), source, new String[] { "kind", "element", "name", "ext", "namespace",
				"##targetNamespace" });
		addAnnotation(getRelationship_Id(), source, new String[] { "kind", "attribute", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(getRelationship_Name(), source, new String[] { "kind", "attribute", "name", "name", "namespace",
				"##targetNamespace" });
		addAnnotation(transformChangeSetOfChangesEClass, source, new String[] { "name", "TransformChangeSetOfChanges",
				"kind", "elementOnly" });
		addAnnotation(variabilityEClass, source, new String[] { "name", "Variability", "kind", "elementOnly" });
		addAnnotation(getVariability_ActiveChangeSet(), source, new String[] { "kind", "element", "name",
				"activeChangeSet", "namespace", "##targetNamespace" });
		addAnnotation(getVariability_AppliedChangeSets(), source, new String[] { "kind", "element", "name",
				"appliedChangeSets", "namespace", "##targetNamespace" });
		addAnnotation(getVariability_ChangeSet(), source, new String[] { "kind", "element", "name", "changeSet",
				"namespace", "##targetNamespace" });
		addAnnotation(getVariability_Relationship(), source, new String[] { "kind", "element", "name", "relationship",
				"namespace", "##targetNamespace" });
		addAnnotation(getVariability_Overview(), source, new String[] { "kind", "attribute", "name", "overview",
				"namespace", "##targetNamespace" });
	}

} //Variability_3_0PackageImpl
