/**
 */
package org.archstudio.xadl3.lookupimplementation_3_0.impl;

import org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package;
import org.archstudio.xadl3.implementation_3_0.impl.Implementation_3_0PackageImpl;
import org.archstudio.xadl3.lookupimplementation_3_0.LookupImplementation;
import org.archstudio.xadl3.lookupimplementation_3_0.Lookupimplementation_3_0Factory;
import org.archstudio.xadl3.lookupimplementation_3_0.Lookupimplementation_3_0Package;
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
public class Lookupimplementation_3_0PackageImpl extends EPackageImpl implements Lookupimplementation_3_0Package {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass lookupImplementationEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
	 * EPackage.Registry} by the package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
	 * performs initialization of the package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.archstudio.xadl3.lookupimplementation_3_0.Lookupimplementation_3_0Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Lookupimplementation_3_0PackageImpl() {
		super(eNS_URI, Lookupimplementation_3_0Factory.eINSTANCE);
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
	 * This method is used to initialize {@link Lookupimplementation_3_0Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Lookupimplementation_3_0Package init() {
		if (isInited) {
			return (Lookupimplementation_3_0Package) EPackage.Registry.INSTANCE
					.getEPackage(Lookupimplementation_3_0Package.eNS_URI);
		}

		// Obtain or create and register package
		Lookupimplementation_3_0PackageImpl theLookupimplementation_3_0Package = (Lookupimplementation_3_0PackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof Lookupimplementation_3_0PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new Lookupimplementation_3_0PackageImpl());

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
		Implementation_3_0PackageImpl theImplementation_3_0Package = (Implementation_3_0PackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(Implementation_3_0Package.eNS_URI) instanceof Implementation_3_0PackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(Implementation_3_0Package.eNS_URI) : Implementation_3_0Package.eINSTANCE);

		// Create package meta-data objects
		theLookupimplementation_3_0Package.createPackageContents();
		theXadlcore_3_0Package.createPackageContents();
		theXlinkPackage.createPackageContents();
		theImplementation_3_0Package.createPackageContents();

		// Initialize created meta-data
		theLookupimplementation_3_0Package.initializePackageContents();
		theXadlcore_3_0Package.initializePackageContents();
		theXlinkPackage.initializePackageContents();
		theImplementation_3_0Package.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theLookupimplementation_3_0Package.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Lookupimplementation_3_0Package.eNS_URI, theLookupimplementation_3_0Package);
		return theLookupimplementation_3_0Package;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getLookupImplementation() {
		return lookupImplementationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getLookupImplementation_Ext() {
		return (EReference) lookupImplementationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getLookupImplementation_Lookup() {
		return (EAttribute) lookupImplementationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Lookupimplementation_3_0Factory getLookupimplementation_3_0Factory() {
		return (Lookupimplementation_3_0Factory) getEFactoryInstance();
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
		lookupImplementationEClass = createEClass(LOOKUP_IMPLEMENTATION);
		createEReference(lookupImplementationEClass, LOOKUP_IMPLEMENTATION__EXT);
		createEAttribute(lookupImplementationEClass, LOOKUP_IMPLEMENTATION__LOOKUP);
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
		Implementation_3_0Package theImplementation_3_0Package = (Implementation_3_0Package) EPackage.Registry.INSTANCE
				.getEPackage(Implementation_3_0Package.eNS_URI);
		Xadlcore_3_0Package theXadlcore_3_0Package = (Xadlcore_3_0Package) EPackage.Registry.INSTANCE
				.getEPackage(Xadlcore_3_0Package.eNS_URI);
		XMLTypePackage theXMLTypePackage = (XMLTypePackage) EPackage.Registry.INSTANCE
				.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		lookupImplementationEClass.getESuperTypes().add(theImplementation_3_0Package.getImplementation());

		// Initialize classes, features, and operations; add parameters
		initEClass(lookupImplementationEClass, LookupImplementation.class, "LookupImplementation", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLookupImplementation_Ext(), theXadlcore_3_0Package.getExtension(), null, "ext", null, 0, -1,
				LookupImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLookupImplementation_Lookup(), theXMLTypePackage.getString(), "lookup", null, 0, 1,
				LookupImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		addAnnotation(lookupImplementationEClass, source, new String[] { "name", "LookupImplementation", "kind",
				"elementOnly" });
		addAnnotation(getLookupImplementation_Ext(), source, new String[] { "kind", "element", "name", "ext",
				"namespace", "##targetNamespace" });
		addAnnotation(getLookupImplementation_Lookup(), source, new String[] { "kind", "attribute", "name", "lookup",
				"namespace", "##targetNamespace" });
	}

} //Lookupimplementation_3_0PackageImpl
