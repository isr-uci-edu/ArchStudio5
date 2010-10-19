/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.archstudio.xadl3.implementation_3_0.impl;

import org.archstudio.xadl3.implementation_3_0.Implementation;
import org.archstudio.xadl3.implementation_3_0.ImplementationExtension;
import org.archstudio.xadl3.implementation_3_0.Implementation_3_0Factory;
import org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package;

import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Implementation_3_0PackageImpl extends EPackageImpl implements Implementation_3_0Package
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass implementationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass implementationExtensionEClass = null;

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
   * @see org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package#eNS_URI
   * @see #init()
   * @generated
   */
  private Implementation_3_0PackageImpl()
  {
    super(eNS_URI, Implementation_3_0Factory.eINSTANCE);
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
   * <p>This method is used to initialize {@link Implementation_3_0Package#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static Implementation_3_0Package init()
  {
    if (isInited) return (Implementation_3_0Package)EPackage.Registry.INSTANCE.getEPackage(Implementation_3_0Package.eNS_URI);

    // Obtain or create and register package
    Implementation_3_0PackageImpl theImplementation_3_0Package = (Implementation_3_0PackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof Implementation_3_0PackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new Implementation_3_0PackageImpl());

    isInited = true;

    // Initialize simple dependencies
    Xadlcore_3_0Package.eINSTANCE.eClass();
    XMLTypePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theImplementation_3_0Package.createPackageContents();

    // Initialize created meta-data
    theImplementation_3_0Package.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theImplementation_3_0Package.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(Implementation_3_0Package.eNS_URI, theImplementation_3_0Package);
    return theImplementation_3_0Package;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImplementation()
  {
    return implementationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getImplementation_Id()
  {
    return (EAttribute)implementationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImplementationExtension()
  {
    return implementationExtensionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getImplementationExtension_Implementation()
  {
    return (EReference)implementationExtensionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Implementation_3_0Factory getImplementation_3_0Factory()
  {
    return (Implementation_3_0Factory)getEFactoryInstance();
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
    implementationEClass = createEClass(IMPLEMENTATION);
    createEAttribute(implementationEClass, IMPLEMENTATION__ID);

    implementationExtensionEClass = createEClass(IMPLEMENTATION_EXTENSION);
    createEReference(implementationExtensionEClass, IMPLEMENTATION_EXTENSION__IMPLEMENTATION);
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
    XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);
    Xadlcore_3_0Package theXadlcore_3_0Package = (Xadlcore_3_0Package)EPackage.Registry.INSTANCE.getEPackage(Xadlcore_3_0Package.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    implementationExtensionEClass.getESuperTypes().add(theXadlcore_3_0Package.getExtension());

    // Initialize classes and features; add operations and parameters
    initEClass(implementationEClass, Implementation.class, "Implementation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getImplementation_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, Implementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(implementationExtensionEClass, ImplementationExtension.class, "ImplementationExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getImplementationExtension_Implementation(), this.getImplementation(), null, "implementation", null, 0, -1, ImplementationExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);

    // Create annotations
    // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
    createExtendedMetaDataAnnotations();
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
      (implementationEClass, 
       source, 
       new String[] 
       {
       "name", "Implementation",
       "kind", "empty"
       });		
    addAnnotation
      (getImplementation_Id(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "id",
       "namespace", "##targetNamespace"
       });			
    addAnnotation
      (implementationExtensionEClass, 
       source, 
       new String[] 
       {
       "name", "ImplementationExtension",
       "kind", "elementOnly"
       });		
    addAnnotation
      (getImplementationExtension_Implementation(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "implementation",
       "namespace", "##targetNamespace"
       });
  }

} //Implementation_3_0PackageImpl
