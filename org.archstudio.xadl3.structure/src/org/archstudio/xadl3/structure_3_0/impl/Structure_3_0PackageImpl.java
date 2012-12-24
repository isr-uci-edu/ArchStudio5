/**
 */
package org.archstudio.xadl3.structure_3_0.impl;

import org.archstudio.xadl3.structure_3_0.Brick;
import org.archstudio.xadl3.structure_3_0.Component;
import org.archstudio.xadl3.structure_3_0.Connector;
import org.archstudio.xadl3.structure_3_0.Direction;
import org.archstudio.xadl3.structure_3_0.DocumentRoot;
import org.archstudio.xadl3.structure_3_0.Interface;
import org.archstudio.xadl3.structure_3_0.InterfaceMapping;
import org.archstudio.xadl3.structure_3_0.Link;
import org.archstudio.xadl3.structure_3_0.Structure;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Factory;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xadl3.structure_3_0.SubStructure;
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
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class Structure_3_0PackageImpl extends EPackageImpl implements Structure_3_0Package {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass brickEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass componentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass connectorEClass = null;

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
	private EClass interfaceEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass interfaceMappingEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass linkEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass structureEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass subStructureEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum directionEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType directionObjectEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
	 * package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory
	 * method {@link #init init()}, which also performs initialization of the
	 * package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Structure_3_0PackageImpl() {
		super(eNS_URI, Structure_3_0Factory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model,
	 * and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link Structure_3_0Package#eINSTANCE}
	 * when that field is accessed. Clients should not invoke it directly.
	 * Instead, they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Structure_3_0Package init() {
		if (isInited) {
			return (Structure_3_0Package) EPackage.Registry.INSTANCE.getEPackage(Structure_3_0Package.eNS_URI);
		}

		// Obtain or create and register package
		Structure_3_0PackageImpl theStructure_3_0Package = (Structure_3_0PackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof Structure_3_0PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new Structure_3_0PackageImpl());

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
		theStructure_3_0Package.createPackageContents();
		theXadlcore_3_0Package.createPackageContents();
		theXlinkPackage.createPackageContents();

		// Initialize created meta-data
		theStructure_3_0Package.initializePackageContents();
		theXadlcore_3_0Package.initializePackageContents();
		theXlinkPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theStructure_3_0Package.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Structure_3_0Package.eNS_URI, theStructure_3_0Package);
		return theStructure_3_0Package;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getBrick() {
		return brickEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getBrick_Interface() {
		return (EReference) brickEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getBrick_SubStructure() {
		return (EReference) brickEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getBrick_Id() {
		return (EAttribute) brickEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getBrick_Name() {
		return (EAttribute) brickEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getComponent() {
		return componentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getComponent_Ext() {
		return (EReference) componentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getConnector() {
		return connectorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getConnector_Ext() {
		return (EReference) connectorEClass.getEStructuralFeatures().get(0);
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
	public EReference getDocumentRoot_Structure() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getInterface() {
		return interfaceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getInterface_Ext() {
		return (EReference) interfaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getInterface_Direction() {
		return (EAttribute) interfaceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getInterface_Id() {
		return (EAttribute) interfaceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getInterface_Name() {
		return (EAttribute) interfaceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getInterfaceMapping() {
		return interfaceMappingEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getInterfaceMapping_OuterInterfaceLink() {
		return (EReference) interfaceMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getInterfaceMapping_InnerInterfaceLink() {
		return (EReference) interfaceMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getInterfaceMapping_Ext() {
		return (EReference) interfaceMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getInterfaceMapping_Id() {
		return (EAttribute) interfaceMappingEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getInterfaceMapping_Name() {
		return (EAttribute) interfaceMappingEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getLink() {
		return linkEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getLink_Point1() {
		return (EReference) linkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getLink_Point2() {
		return (EReference) linkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getLink_Ext() {
		return (EReference) linkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getLink_Id() {
		return (EAttribute) linkEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getLink_Name() {
		return (EAttribute) linkEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getStructure() {
		return structureEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getStructure_Component() {
		return (EReference) structureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getStructure_Connector() {
		return (EReference) structureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getStructure_Link() {
		return (EReference) structureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getStructure_Ext() {
		return (EReference) structureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getStructure_Id() {
		return (EAttribute) structureEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getStructure_Name() {
		return (EAttribute) structureEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getSubStructure() {
		return subStructureEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getSubStructure_InnerStructureLink() {
		return (EReference) subStructureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getSubStructure_InterfaceMapping() {
		return (EReference) subStructureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getSubStructure_Ext() {
		return (EReference) subStructureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getSubStructure_Id() {
		return (EAttribute) subStructureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EEnum getDirection() {
		return directionEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EDataType getDirectionObject() {
		return directionObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Structure_3_0Factory getStructure_3_0Factory() {
		return (Structure_3_0Factory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is guarded to
	 * have no affect on any invocation but its first. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) {
			return;
		}
		isCreated = true;

		// Create classes and their features
		brickEClass = createEClass(BRICK);
		createEReference(brickEClass, BRICK__INTERFACE);
		createEReference(brickEClass, BRICK__SUB_STRUCTURE);
		createEAttribute(brickEClass, BRICK__ID);
		createEAttribute(brickEClass, BRICK__NAME);

		componentEClass = createEClass(COMPONENT);
		createEReference(componentEClass, COMPONENT__EXT);

		connectorEClass = createEClass(CONNECTOR);
		createEReference(connectorEClass, CONNECTOR__EXT);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__STRUCTURE);

		interfaceEClass = createEClass(INTERFACE);
		createEReference(interfaceEClass, INTERFACE__EXT);
		createEAttribute(interfaceEClass, INTERFACE__DIRECTION);
		createEAttribute(interfaceEClass, INTERFACE__ID);
		createEAttribute(interfaceEClass, INTERFACE__NAME);

		interfaceMappingEClass = createEClass(INTERFACE_MAPPING);
		createEReference(interfaceMappingEClass, INTERFACE_MAPPING__OUTER_INTERFACE_LINK);
		createEReference(interfaceMappingEClass, INTERFACE_MAPPING__INNER_INTERFACE_LINK);
		createEReference(interfaceMappingEClass, INTERFACE_MAPPING__EXT);
		createEAttribute(interfaceMappingEClass, INTERFACE_MAPPING__ID);
		createEAttribute(interfaceMappingEClass, INTERFACE_MAPPING__NAME);

		linkEClass = createEClass(LINK);
		createEReference(linkEClass, LINK__POINT1);
		createEReference(linkEClass, LINK__POINT2);
		createEReference(linkEClass, LINK__EXT);
		createEAttribute(linkEClass, LINK__ID);
		createEAttribute(linkEClass, LINK__NAME);

		structureEClass = createEClass(STRUCTURE);
		createEReference(structureEClass, STRUCTURE__COMPONENT);
		createEReference(structureEClass, STRUCTURE__CONNECTOR);
		createEReference(structureEClass, STRUCTURE__LINK);
		createEReference(structureEClass, STRUCTURE__EXT);
		createEAttribute(structureEClass, STRUCTURE__ID);
		createEAttribute(structureEClass, STRUCTURE__NAME);

		subStructureEClass = createEClass(SUB_STRUCTURE);
		createEReference(subStructureEClass, SUB_STRUCTURE__INNER_STRUCTURE_LINK);
		createEReference(subStructureEClass, SUB_STRUCTURE__INTERFACE_MAPPING);
		createEReference(subStructureEClass, SUB_STRUCTURE__EXT);
		createEAttribute(subStructureEClass, SUB_STRUCTURE__ID);

		// Create enums
		directionEEnum = createEEnum(DIRECTION);

		// Create data types
		directionObjectEDataType = createEDataType(DIRECTION_OBJECT);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This
	 * method is guarded to have no affect on any invocation but its first. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
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
		componentEClass.getESuperTypes().add(this.getBrick());
		connectorEClass.getESuperTypes().add(this.getBrick());

		// Initialize classes and features; add operations and parameters
		initEClass(brickEClass, Brick.class, "Brick", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBrick_Interface(), this.getInterface(), null, "interface", null, 0, -1, Brick.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBrick_SubStructure(), this.getSubStructure(), null, "subStructure", null, 0, 1, Brick.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBrick_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, Brick.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBrick_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Brick.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(componentEClass, Component.class, "Component", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponent_Ext(), theXadlcore_3_0Package.getExtension(), null, "ext", null, 0, -1,
				Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(connectorEClass, Connector.class, "Connector", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConnector_Ext(), theXadlcore_3_0Package.getExtension(), null, "ext", null, 0, -1,
				Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		initEReference(getDocumentRoot_Structure(), this.getStructure(), null, "structure", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);

		initEClass(interfaceEClass, Interface.class, "Interface", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInterface_Ext(), theXadlcore_3_0Package.getExtension(), null, "ext", null, 0, -1,
				Interface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInterface_Direction(), this.getDirection(), "direction", null, 0, 1, Interface.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInterface_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, Interface.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInterface_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Interface.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(interfaceMappingEClass, InterfaceMapping.class, "InterfaceMapping", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInterfaceMapping_OuterInterfaceLink(), this.getInterface(), null, "outerInterfaceLink", null,
				1, 1, InterfaceMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInterfaceMapping_InnerInterfaceLink(), this.getInterface(), null, "innerInterfaceLink", null,
				1, 1, InterfaceMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInterfaceMapping_Ext(), theXadlcore_3_0Package.getExtension(), null, "ext", null, 0, -1,
				InterfaceMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInterfaceMapping_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, InterfaceMapping.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInterfaceMapping_Name(), theXMLTypePackage.getString(), "name", null, 0, 1,
				InterfaceMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(linkEClass, Link.class, "Link", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLink_Point1(), this.getInterface(), null, "point1", null, 1, 1, Link.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getLink_Point2(), this.getInterface(), null, "point2", null, 1, 1, Link.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getLink_Ext(), theXadlcore_3_0Package.getExtension(), null, "ext", null, 0, -1, Link.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLink_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, Link.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLink_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Link.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(structureEClass, Structure.class, "Structure", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStructure_Component(), this.getComponent(), null, "component", null, 0, -1, Structure.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStructure_Connector(), this.getConnector(), null, "connector", null, 0, -1, Structure.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStructure_Link(), this.getLink(), null, "link", null, 0, -1, Structure.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getStructure_Ext(), theXadlcore_3_0Package.getExtension(), null, "ext", null, 0, -1,
				Structure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStructure_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, Structure.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStructure_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Structure.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(subStructureEClass, SubStructure.class, "SubStructure", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSubStructure_InnerStructureLink(), this.getStructure(), null, "innerStructureLink", null, 1,
				1, SubStructure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSubStructure_InterfaceMapping(), this.getInterfaceMapping(), null, "interfaceMapping", null,
				0, -1, SubStructure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSubStructure_Ext(), theXadlcore_3_0Package.getExtension(), null, "ext", null, 0, -1,
				SubStructure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSubStructure_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, SubStructure.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(directionEEnum, Direction.class, "Direction");
		addEEnumLiteral(directionEEnum, Direction.NONE);
		addEEnumLiteral(directionEEnum, Direction.IN);
		addEEnumLiteral(directionEEnum, Direction.OUT);
		addEEnumLiteral(directionEEnum, Direction.INOUT);

		// Initialize data types
		initEDataType(directionObjectEDataType, Direction.class, "DirectionObject", IS_SERIALIZABLE,
				IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for
	 * <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
		addAnnotation(brickEClass, source, new String[] { "name", "Brick", "kind", "elementOnly" });
		addAnnotation(getBrick_Interface(), source, new String[] { "kind", "element", "name", "interface", "namespace",
				"##targetNamespace" });
		addAnnotation(getBrick_SubStructure(), source, new String[] { "kind", "element", "name", "subStructure",
				"namespace", "##targetNamespace" });
		addAnnotation(getBrick_Id(), source, new String[] { "kind", "attribute", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(getBrick_Name(), source, new String[] { "kind", "attribute", "name", "name", "namespace",
				"##targetNamespace" });
		addAnnotation(componentEClass, source, new String[] { "name", "Component", "kind", "elementOnly" });
		addAnnotation(getComponent_Ext(), source, new String[] { "kind", "element", "name", "ext", "namespace",
				"##targetNamespace" });
		addAnnotation(connectorEClass, source, new String[] { "name", "Connector", "kind", "elementOnly" });
		addAnnotation(getConnector_Ext(), source, new String[] { "kind", "element", "name", "ext", "namespace",
				"##targetNamespace" });
		addAnnotation(directionEEnum, source, new String[] { "name", "Direction" });
		addAnnotation(directionObjectEDataType, source, new String[] { "name", "Direction:Object", "baseType",
				"Direction" });
		addAnnotation(documentRootEClass, source, new String[] { "name", "", "kind", "mixed" });
		addAnnotation(getDocumentRoot_Mixed(), source, new String[] { "kind", "elementWildcard", "name", ":mixed" });
		addAnnotation(getDocumentRoot_XMLNSPrefixMap(), source, new String[] { "kind", "attribute", "name",
				"xmlns:prefix" });
		addAnnotation(getDocumentRoot_XSISchemaLocation(), source, new String[] { "kind", "attribute", "name",
				"xsi:schemaLocation" });
		addAnnotation(getDocumentRoot_Structure(), source, new String[] { "kind", "element", "name", "structure",
				"namespace", "##targetNamespace", "affiliation",
				"http://www.archstudio.org/xadl3/schemas/xadlcore-3.0.xsd#topLevelElement" });
		addAnnotation(interfaceEClass, source, new String[] { "name", "Interface", "kind", "elementOnly" });
		addAnnotation(getInterface_Ext(), source, new String[] { "kind", "element", "name", "ext", "namespace",
				"##targetNamespace" });
		addAnnotation(getInterface_Direction(), source, new String[] { "kind", "attribute", "name", "direction",
				"namespace", "##targetNamespace" });
		addAnnotation(getInterface_Id(), source, new String[] { "kind", "attribute", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(getInterface_Name(), source, new String[] { "kind", "attribute", "name", "name", "namespace",
				"##targetNamespace" });
		addAnnotation(interfaceMappingEClass, source,
				new String[] { "name", "InterfaceMapping", "kind", "elementOnly" });
		addAnnotation(getInterfaceMapping_OuterInterfaceLink(), source, new String[] { "kind", "element", "name",
				"outerInterfaceLink", "namespace", "##targetNamespace" });
		addAnnotation(getInterfaceMapping_InnerInterfaceLink(), source, new String[] { "kind", "element", "name",
				"innerInterfaceLink", "namespace", "##targetNamespace" });
		addAnnotation(getInterfaceMapping_Ext(), source, new String[] { "kind", "element", "name", "ext", "namespace",
				"##targetNamespace" });
		addAnnotation(getInterfaceMapping_Id(), source, new String[] { "kind", "attribute", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(getInterfaceMapping_Name(), source, new String[] { "kind", "attribute", "name", "name",
				"namespace", "##targetNamespace" });
		addAnnotation(linkEClass, source, new String[] { "name", "Link", "kind", "elementOnly" });
		addAnnotation(getLink_Point1(), source, new String[] { "kind", "element", "name", "point1", "namespace",
				"##targetNamespace" });
		addAnnotation(getLink_Point2(), source, new String[] { "kind", "element", "name", "point2", "namespace",
				"##targetNamespace" });
		addAnnotation(getLink_Ext(), source, new String[] { "kind", "element", "name", "ext", "namespace",
				"##targetNamespace" });
		addAnnotation(getLink_Id(), source, new String[] { "kind", "attribute", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(getLink_Name(), source, new String[] { "kind", "attribute", "name", "name", "namespace",
				"##targetNamespace" });
		addAnnotation(structureEClass, source, new String[] { "name", "Structure", "kind", "elementOnly" });
		addAnnotation(getStructure_Component(), source, new String[] { "kind", "element", "name", "component",
				"namespace", "##targetNamespace" });
		addAnnotation(getStructure_Connector(), source, new String[] { "kind", "element", "name", "connector",
				"namespace", "##targetNamespace" });
		addAnnotation(getStructure_Link(), source, new String[] { "kind", "element", "name", "link", "namespace",
				"##targetNamespace" });
		addAnnotation(getStructure_Ext(), source, new String[] { "kind", "element", "name", "ext", "namespace",
				"##targetNamespace" });
		addAnnotation(getStructure_Id(), source, new String[] { "kind", "attribute", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(getStructure_Name(), source, new String[] { "kind", "attribute", "name", "name", "namespace",
				"##targetNamespace" });
		addAnnotation(subStructureEClass, source, new String[] { "name", "SubStructure", "kind", "elementOnly" });
		addAnnotation(getSubStructure_InnerStructureLink(), source, new String[] { "kind", "element", "name",
				"innerStructureLink", "namespace", "##targetNamespace" });
		addAnnotation(getSubStructure_InterfaceMapping(), source, new String[] { "kind", "element", "name",
				"interfaceMapping", "namespace", "##targetNamespace" });
		addAnnotation(getSubStructure_Ext(), source, new String[] { "kind", "element", "name", "ext", "namespace",
				"##targetNamespace" });
		addAnnotation(getSubStructure_Id(), source, new String[] { "kind", "attribute", "name", "id", "namespace",
				"##targetNamespace" });
	}

} //Structure_3_0PackageImpl
