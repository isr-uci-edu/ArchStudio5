/**
 */
package org.archstudio.xadl3.javaimplementation_3_0.impl;

import org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package;
import org.archstudio.xadl3.implementation_3_0.impl.Implementation_3_0PackageImpl;
import org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry;
import org.archstudio.xadl3.javaimplementation_3_0.JavaClass;
import org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation;
import org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Factory;
import org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package;
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
public class Javaimplementation_3_0PackageImpl extends EPackageImpl implements Javaimplementation_3_0Package {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass classPathEntryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass javaClassEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass javaImplementationEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
	 * EPackage.Registry} by the package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
	 * performs initialization of the package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Javaimplementation_3_0PackageImpl() {
		super(eNS_URI, Javaimplementation_3_0Factory.eINSTANCE);
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
	 * This method is used to initialize {@link Javaimplementation_3_0Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Javaimplementation_3_0Package init() {
		if (isInited) {
			return (Javaimplementation_3_0Package) EPackage.Registry.INSTANCE
					.getEPackage(Javaimplementation_3_0Package.eNS_URI);
		}

		// Obtain or create and register package
		Javaimplementation_3_0PackageImpl theJavaimplementation_3_0Package = (Javaimplementation_3_0PackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof Javaimplementation_3_0PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new Javaimplementation_3_0PackageImpl());

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
		theJavaimplementation_3_0Package.createPackageContents();
		theXadlcore_3_0Package.createPackageContents();
		theXlinkPackage.createPackageContents();
		theImplementation_3_0Package.createPackageContents();

		// Initialize created meta-data
		theJavaimplementation_3_0Package.initializePackageContents();
		theXadlcore_3_0Package.initializePackageContents();
		theXlinkPackage.initializePackageContents();
		theImplementation_3_0Package.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theJavaimplementation_3_0Package.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Javaimplementation_3_0Package.eNS_URI, theJavaimplementation_3_0Package);
		return theJavaimplementation_3_0Package;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getClassPathEntry() {
		return classPathEntryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getClassPathEntry_Ext() {
		return (EReference) classPathEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getClassPathEntry_Entry() {
		return (EAttribute) classPathEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getClassPathEntry_Id() {
		return (EAttribute) classPathEntryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getJavaClass() {
		return javaClassEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getJavaClass_ClassPathEntry() {
		return (EReference) javaClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getJavaClass_Ext() {
		return (EReference) javaClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getJavaClass_ClassName() {
		return (EAttribute) javaClassEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getJavaClass_Id() {
		return (EAttribute) javaClassEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getJavaImplementation() {
		return javaImplementationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getJavaImplementation_MainClass() {
		return (EReference) javaImplementationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getJavaImplementation_AuxClass() {
		return (EReference) javaImplementationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getJavaImplementation_Ext() {
		return (EReference) javaImplementationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Javaimplementation_3_0Factory getJavaimplementation_3_0Factory() {
		return (Javaimplementation_3_0Factory) getEFactoryInstance();
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
		classPathEntryEClass = createEClass(CLASS_PATH_ENTRY);
		createEReference(classPathEntryEClass, CLASS_PATH_ENTRY__EXT);
		createEAttribute(classPathEntryEClass, CLASS_PATH_ENTRY__ENTRY);
		createEAttribute(classPathEntryEClass, CLASS_PATH_ENTRY__ID);

		javaClassEClass = createEClass(JAVA_CLASS);
		createEReference(javaClassEClass, JAVA_CLASS__CLASS_PATH_ENTRY);
		createEReference(javaClassEClass, JAVA_CLASS__EXT);
		createEAttribute(javaClassEClass, JAVA_CLASS__CLASS_NAME);
		createEAttribute(javaClassEClass, JAVA_CLASS__ID);

		javaImplementationEClass = createEClass(JAVA_IMPLEMENTATION);
		createEReference(javaImplementationEClass, JAVA_IMPLEMENTATION__MAIN_CLASS);
		createEReference(javaImplementationEClass, JAVA_IMPLEMENTATION__AUX_CLASS);
		createEReference(javaImplementationEClass, JAVA_IMPLEMENTATION__EXT);
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
		Implementation_3_0Package theImplementation_3_0Package = (Implementation_3_0Package) EPackage.Registry.INSTANCE
				.getEPackage(Implementation_3_0Package.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		javaImplementationEClass.getESuperTypes().add(theImplementation_3_0Package.getImplementation());

		// Initialize classes and features; add operations and parameters
		initEClass(classPathEntryEClass, ClassPathEntry.class, "ClassPathEntry", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassPathEntry_Ext(), theXadlcore_3_0Package.getExtension(), null, "ext", null, 0, -1,
				ClassPathEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClassPathEntry_Entry(), theXMLTypePackage.getString(), "entry", null, 0, 1,
				ClassPathEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getClassPathEntry_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ClassPathEntry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(javaClassEClass, JavaClass.class, "JavaClass", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getJavaClass_ClassPathEntry(), this.getClassPathEntry(), null, "classPathEntry", null, 0, -1,
				JavaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaClass_Ext(), theXadlcore_3_0Package.getExtension(), null, "ext", null, 0, -1,
				JavaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJavaClass_ClassName(), theXMLTypePackage.getString(), "className", null, 0, 1,
				JavaClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getJavaClass_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, JavaClass.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(javaImplementationEClass, JavaImplementation.class, "JavaImplementation", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getJavaImplementation_MainClass(), this.getJavaClass(), null, "mainClass", null, 1, 1,
				JavaImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaImplementation_AuxClass(), this.getJavaClass(), null, "auxClass", null, 0, -1,
				JavaImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJavaImplementation_Ext(), theXadlcore_3_0Package.getExtension(), null, "ext", null, 0, -1,
				JavaImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		addAnnotation(classPathEntryEClass, source, new String[] { "name", "ClassPathEntry", "kind", "elementOnly" });
		addAnnotation(getClassPathEntry_Ext(), source, new String[] { "kind", "element", "name", "ext", "namespace",
				"##targetNamespace" });
		addAnnotation(getClassPathEntry_Entry(), source, new String[] { "kind", "attribute", "name", "entry",
				"namespace", "##targetNamespace" });
		addAnnotation(getClassPathEntry_Id(), source, new String[] { "kind", "attribute", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(javaClassEClass, source, new String[] { "name", "JavaClass", "kind", "elementOnly" });
		addAnnotation(getJavaClass_ClassPathEntry(), source, new String[] { "kind", "element", "name",
				"classPathEntry", "namespace", "##targetNamespace" });
		addAnnotation(getJavaClass_Ext(), source, new String[] { "kind", "element", "name", "ext", "namespace",
				"##targetNamespace" });
		addAnnotation(getJavaClass_ClassName(), source, new String[] { "kind", "attribute", "name", "className",
				"namespace", "##targetNamespace" });
		addAnnotation(getJavaClass_Id(), source, new String[] { "kind", "attribute", "name", "id", "namespace",
				"##targetNamespace" });
		addAnnotation(javaImplementationEClass, source, new String[] { "name", "JavaImplementation", "kind",
				"elementOnly" });
		addAnnotation(getJavaImplementation_MainClass(), source, new String[] { "kind", "element", "name", "mainClass",
				"namespace", "##targetNamespace" });
		addAnnotation(getJavaImplementation_AuxClass(), source, new String[] { "kind", "element", "name", "auxClass",
				"namespace", "##targetNamespace" });
		addAnnotation(getJavaImplementation_Ext(), source, new String[] { "kind", "element", "name", "ext",
				"namespace", "##targetNamespace" });
	}

} //Javaimplementation_3_0PackageImpl
