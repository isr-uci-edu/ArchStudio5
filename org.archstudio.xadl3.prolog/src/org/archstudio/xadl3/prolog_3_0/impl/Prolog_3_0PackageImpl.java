/**
 */
package org.archstudio.xadl3.prolog_3_0.impl;

import org.archstudio.xadl3.prolog_3_0.PrologExtension;
import org.archstudio.xadl3.prolog_3_0.Prolog_3_0Factory;
import org.archstudio.xadl3.prolog_3_0.Prolog_3_0Package;
import org.archstudio.xadl3.prolog_3_0.Statement;
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
public class Prolog_3_0PackageImpl extends EPackageImpl implements Prolog_3_0Package {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass prologExtensionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass statementEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
	 * EPackage.Registry} by the package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
	 * performs initialization of the package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.archstudio.xadl3.prolog_3_0.Prolog_3_0Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Prolog_3_0PackageImpl() {
		super(eNS_URI, Prolog_3_0Factory.eINSTANCE);
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
	 * This method is used to initialize {@link Prolog_3_0Package#eINSTANCE} when that field is accessed. Clients should
	 * not invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Prolog_3_0Package init() {
		if (isInited) {
			return (Prolog_3_0Package) EPackage.Registry.INSTANCE.getEPackage(Prolog_3_0Package.eNS_URI);
		}

		// Obtain or create and register package
		Prolog_3_0PackageImpl theProlog_3_0Package = (Prolog_3_0PackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Prolog_3_0PackageImpl ? EPackage.Registry.INSTANCE
				.get(eNS_URI) : new Prolog_3_0PackageImpl());

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
		theProlog_3_0Package.createPackageContents();
		theXadlcore_3_0Package.createPackageContents();
		theXlinkPackage.createPackageContents();

		// Initialize created meta-data
		theProlog_3_0Package.initializePackageContents();
		theXadlcore_3_0Package.initializePackageContents();
		theXlinkPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theProlog_3_0Package.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Prolog_3_0Package.eNS_URI, theProlog_3_0Package);
		return theProlog_3_0Package;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getPrologExtension() {
		return prologExtensionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getPrologExtension_Statement() {
		return (EReference) prologExtensionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getStatement() {
		return statementEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getStatement_Value() {
		return (EAttribute) statementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Prolog_3_0Factory getProlog_3_0Factory() {
		return (Prolog_3_0Factory) getEFactoryInstance();
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
		prologExtensionEClass = createEClass(PROLOG_EXTENSION);
		createEReference(prologExtensionEClass, PROLOG_EXTENSION__STATEMENT);

		statementEClass = createEClass(STATEMENT);
		createEAttribute(statementEClass, STATEMENT__VALUE);
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
		prologExtensionEClass.getESuperTypes().add(theXadlcore_3_0Package.getExtension());

		// Initialize classes, features, and operations; add parameters
		initEClass(prologExtensionEClass, PrologExtension.class, "PrologExtension", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPrologExtension_Statement(), this.getStatement(), null, "statement", null, 0, -1,
				PrologExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(statementEClass, Statement.class, "Statement", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStatement_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, Statement.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		addAnnotation(prologExtensionEClass, source, new String[] { "name", "PrologExtension", "kind", "elementOnly" });
		addAnnotation(getPrologExtension_Statement(), source, new String[] { "kind", "element", "name", "statement",
				"namespace", "##targetNamespace" });
		addAnnotation(statementEClass, source, new String[] { "name", "Statement", "kind", "empty" });
		addAnnotation(getStatement_Value(), source, new String[] { "kind", "attribute", "name", "value", "namespace",
				"##targetNamespace" });
	}

} //Prolog_3_0PackageImpl
