/**
 */
package org.archstudio.xadl3.domain_3_0.impl;

import org.archstudio.xadl3.domain_3_0.Domain;
import org.archstudio.xadl3.domain_3_0.DomainExtension;
import org.archstudio.xadl3.domain_3_0.DomainType;
import org.archstudio.xadl3.domain_3_0.Domain_3_0Factory;
import org.archstudio.xadl3.domain_3_0.Domain_3_0Package;

import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;

import org.archstudio.xadl3.xadlcore_3_0.impl.Xadlcore_3_0PackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.w3.xlink.XlinkPackage;

import org.w3.xlink.impl.XlinkPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Domain_3_0PackageImpl extends EPackageImpl implements Domain_3_0Package
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass domainEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass domainExtensionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum domainTypeEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType domainTypeObjectEDataType = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.archstudio.xadl3.domain_3_0.Domain_3_0Package#eNS_URI
   * @see #init()
   * @generated
   */
  private Domain_3_0PackageImpl()
  {
    super(eNS_URI, Domain_3_0Factory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link Domain_3_0Package#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static Domain_3_0Package init()
  {
    if (isInited) return (Domain_3_0Package)EPackage.Registry.INSTANCE.getEPackage(Domain_3_0Package.eNS_URI);

    // Obtain or create and register package
    Domain_3_0PackageImpl theDomain_3_0Package = (Domain_3_0PackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Domain_3_0PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Domain_3_0PackageImpl());

    isInited = true;

    // Obtain or create and register interdependencies
    Xadlcore_3_0PackageImpl theXadlcore_3_0Package = (Xadlcore_3_0PackageImpl)(EPackage.Registry.INSTANCE.getEPackage(Xadlcore_3_0Package.eNS_URI) instanceof Xadlcore_3_0PackageImpl ? EPackage.Registry.INSTANCE.getEPackage(Xadlcore_3_0Package.eNS_URI) : Xadlcore_3_0Package.eINSTANCE);
    XlinkPackageImpl theXlinkPackage = (XlinkPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(XlinkPackage.eNS_URI) instanceof XlinkPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(XlinkPackage.eNS_URI) : XlinkPackage.eINSTANCE);

    // Create package meta-data objects
    theDomain_3_0Package.createPackageContents();
    theXadlcore_3_0Package.createPackageContents();
    theXlinkPackage.createPackageContents();

    // Initialize created meta-data
    theDomain_3_0Package.initializePackageContents();
    theXadlcore_3_0Package.initializePackageContents();
    theXlinkPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theDomain_3_0Package.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(Domain_3_0Package.eNS_URI, theDomain_3_0Package);
    return theDomain_3_0Package;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDomain()
  {
    return domainEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDomain_Type()
  {
    return (EAttribute)domainEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDomainExtension()
  {
    return domainExtensionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDomainExtension_Domain()
  {
    return (EReference)domainExtensionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getDomainType()
  {
    return domainTypeEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getDomainTypeObject()
  {
    return domainTypeObjectEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Domain_3_0Factory getDomain_3_0Factory()
  {
    return (Domain_3_0Factory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    domainEClass = createEClass(DOMAIN);
    createEAttribute(domainEClass, DOMAIN__TYPE);

    domainExtensionEClass = createEClass(DOMAIN_EXTENSION);
    createEReference(domainExtensionEClass, DOMAIN_EXTENSION__DOMAIN);

    // Create enums
    domainTypeEEnum = createEEnum(DOMAIN_TYPE);

    // Create data types
    domainTypeObjectEDataType = createEDataType(DOMAIN_TYPE_OBJECT);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    Xadlcore_3_0Package theXadlcore_3_0Package = (Xadlcore_3_0Package)EPackage.Registry.INSTANCE.getEPackage(Xadlcore_3_0Package.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    domainExtensionEClass.getESuperTypes().add(theXadlcore_3_0Package.getExtension());

    // Initialize classes and features; add operations and parameters
    initEClass(domainEClass, Domain.class, "Domain", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDomain_Type(), this.getDomainType(), "type", null, 0, 1, Domain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(domainExtensionEClass, DomainExtension.class, "DomainExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDomainExtension_Domain(), this.getDomain(), null, "domain", null, 1, 1, DomainExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(domainTypeEEnum, DomainType.class, "DomainType");
    addEEnumLiteral(domainTypeEEnum, DomainType.TOP);
    addEEnumLiteral(domainTypeEEnum, DomainType.BOTTOM);

    // Initialize data types
    initEDataType(domainTypeObjectEDataType, DomainType.class, "DomainTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);

    // Create annotations
    // http://www.archstudio.org/xadl3/schemas/extensionHint
    createExtensionHintAnnotations();
    // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
    createExtendedMetaDataAnnotations();
  }

  /**
   * Initializes the annotations for <b>http://www.archstudio.org/xadl3/schemas/extensionHint</b>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void createExtensionHintAnnotations()
  {
    String source = "http://www.archstudio.org/xadl3/schemas/extensionHint";			
    addAnnotation
      (this, 
       source, 
       new String[] 
       {
       "appinfo", "\r\n\t\t\t<hint extensionSchema=\"http://www.archstudio.org/xadl3/schemas/domain-3.0.xsd\" extensionType=\"DomainExtension\" targetSchema=\"http://www.archstudio.org/xadl3/schemas/structure-3.0.xsd\" targetType=\"Interface\" xmlns=\"http://www.archstudio.org/xadl3/schemas/domain-3.0.xsd\"/>\r\n\t\t"
       });									
  }

  /**
   * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void createExtendedMetaDataAnnotations()
  {
    String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";					
    addAnnotation
      (domainEClass, 
       source, 
       new String[] 
       {
       "name", "Domain",
       "kind", "empty"
       });		
    addAnnotation
      (getDomain_Type(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "type",
       "namespace", "##targetNamespace"
       });			
    addAnnotation
      (domainExtensionEClass, 
       source, 
       new String[] 
       {
       "name", "DomainExtension",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getDomainExtension_Domain(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "domain",
       "namespace", "##targetNamespace"
       });			
    addAnnotation
      (domainTypeEEnum, 
       source, 
       new String[] 
       {
       "name", "DomainType"
       });		
    addAnnotation
      (domainTypeObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "DomainType:Object",
       "baseType", "DomainType"
       });
  }

} //Domain_3_0PackageImpl
