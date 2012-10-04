/**
 */
package org.archstudio.xadl3.implementation_3_0;

import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 * 			xADL 3 Abstract Implementation Schema
 * 
 * 			This schema defines the concept of an implementation.
 * 			The concept is abstract, so it is intended to be 
 * 			specialized by various specific types of implementations.
 * 			However, it exists so that implementations can be
 * 			treated similarly.
 * 
 * 			Changelog:
 * 			  - 3.0.0
 * 			    * Initial version
 * 			
 * 		
 * 
 * 		
 * 			xADL 3 Core Schema
 * 
 * 			This schema defines the core elements for xADL 3 documents.
 * 			The schema is intentionally small, defining only a few elements
 * 			that are intended to be common across xADL descriptions and
 * 			extensions, regardless of domain.
 * 
 * 			Changelog:
 * 			  - 3.0.0:
 * 			    * Initial version
 * 			
 * 		
 * <!-- end-model-doc -->
 * @see org.archstudio.xadl3.implementation_3_0.Implementation_3_0Factory
 * @model kind="package"
 * @generated
 */
public interface Implementation_3_0Package extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "implementation_3_0";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.archstudio.org/xadl3/schemas/implementation-3.0.xsd";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "implementation_3_0";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  Implementation_3_0Package eINSTANCE = org.archstudio.xadl3.implementation_3_0.impl.Implementation_3_0PackageImpl.init();

  /**
   * The meta object id for the '{@link org.archstudio.xadl3.implementation_3_0.impl.ImplementationImpl <em>Implementation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.archstudio.xadl3.implementation_3_0.impl.ImplementationImpl
   * @see org.archstudio.xadl3.implementation_3_0.impl.Implementation_3_0PackageImpl#getImplementation()
   * @generated
   */
  int IMPLEMENTATION = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTATION__ID = 0;

  /**
   * The number of structural features of the '<em>Implementation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTATION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.archstudio.xadl3.implementation_3_0.impl.ImplementationExtensionImpl <em>Implementation Extension</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.archstudio.xadl3.implementation_3_0.impl.ImplementationExtensionImpl
   * @see org.archstudio.xadl3.implementation_3_0.impl.Implementation_3_0PackageImpl#getImplementationExtension()
   * @generated
   */
  int IMPLEMENTATION_EXTENSION = 1;

  /**
   * The feature id for the '<em><b>Implementation</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTATION_EXTENSION__IMPLEMENTATION = Xadlcore_3_0Package.EXTENSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Implementation Extension</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTATION_EXTENSION_FEATURE_COUNT = Xadlcore_3_0Package.EXTENSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.archstudio.xadl3.implementation_3_0.impl.InitializationParameterImpl <em>Initialization Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.archstudio.xadl3.implementation_3_0.impl.InitializationParameterImpl
   * @see org.archstudio.xadl3.implementation_3_0.impl.Implementation_3_0PackageImpl#getInitializationParameter()
   * @generated
   */
  int INITIALIZATION_PARAMETER = 2;

  /**
   * The feature id for the '<em><b>Ext</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIALIZATION_PARAMETER__EXT = 0;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIALIZATION_PARAMETER__KEY = 1;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIALIZATION_PARAMETER__VALUE = 2;

  /**
   * The number of structural features of the '<em>Initialization Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIALIZATION_PARAMETER_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.archstudio.xadl3.implementation_3_0.impl.InitializationParametersImplementationImpl <em>Initialization Parameters Implementation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.archstudio.xadl3.implementation_3_0.impl.InitializationParametersImplementationImpl
   * @see org.archstudio.xadl3.implementation_3_0.impl.Implementation_3_0PackageImpl#getInitializationParametersImplementation()
   * @generated
   */
  int INITIALIZATION_PARAMETERS_IMPLEMENTATION = 3;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIALIZATION_PARAMETERS_IMPLEMENTATION__ID = IMPLEMENTATION__ID;

  /**
   * The feature id for the '<em><b>Initialization Parameter</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIALIZATION_PARAMETERS_IMPLEMENTATION__INITIALIZATION_PARAMETER = IMPLEMENTATION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Initialization Parameters Implementation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INITIALIZATION_PARAMETERS_IMPLEMENTATION_FEATURE_COUNT = IMPLEMENTATION_FEATURE_COUNT + 1;


  /**
   * Returns the meta object for class '{@link org.archstudio.xadl3.implementation_3_0.Implementation <em>Implementation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Implementation</em>'.
   * @see org.archstudio.xadl3.implementation_3_0.Implementation
   * @generated
   */
  EClass getImplementation();

  /**
   * Returns the meta object for the attribute '{@link org.archstudio.xadl3.implementation_3_0.Implementation#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.archstudio.xadl3.implementation_3_0.Implementation#getId()
   * @see #getImplementation()
   * @generated
   */
  EAttribute getImplementation_Id();

  /**
   * Returns the meta object for class '{@link org.archstudio.xadl3.implementation_3_0.ImplementationExtension <em>Implementation Extension</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Implementation Extension</em>'.
   * @see org.archstudio.xadl3.implementation_3_0.ImplementationExtension
   * @generated
   */
  EClass getImplementationExtension();

  /**
   * Returns the meta object for the containment reference list '{@link org.archstudio.xadl3.implementation_3_0.ImplementationExtension#getImplementation <em>Implementation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Implementation</em>'.
   * @see org.archstudio.xadl3.implementation_3_0.ImplementationExtension#getImplementation()
   * @see #getImplementationExtension()
   * @generated
   */
  EReference getImplementationExtension_Implementation();

  /**
   * Returns the meta object for class '{@link org.archstudio.xadl3.implementation_3_0.InitializationParameter <em>Initialization Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Initialization Parameter</em>'.
   * @see org.archstudio.xadl3.implementation_3_0.InitializationParameter
   * @generated
   */
  EClass getInitializationParameter();

  /**
   * Returns the meta object for the containment reference list '{@link org.archstudio.xadl3.implementation_3_0.InitializationParameter#getExt <em>Ext</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Ext</em>'.
   * @see org.archstudio.xadl3.implementation_3_0.InitializationParameter#getExt()
   * @see #getInitializationParameter()
   * @generated
   */
  EReference getInitializationParameter_Ext();

  /**
   * Returns the meta object for the attribute '{@link org.archstudio.xadl3.implementation_3_0.InitializationParameter#getKey <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see org.archstudio.xadl3.implementation_3_0.InitializationParameter#getKey()
   * @see #getInitializationParameter()
   * @generated
   */
  EAttribute getInitializationParameter_Key();

  /**
   * Returns the meta object for the attribute '{@link org.archstudio.xadl3.implementation_3_0.InitializationParameter#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.archstudio.xadl3.implementation_3_0.InitializationParameter#getValue()
   * @see #getInitializationParameter()
   * @generated
   */
  EAttribute getInitializationParameter_Value();

  /**
   * Returns the meta object for class '{@link org.archstudio.xadl3.implementation_3_0.InitializationParametersImplementation <em>Initialization Parameters Implementation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Initialization Parameters Implementation</em>'.
   * @see org.archstudio.xadl3.implementation_3_0.InitializationParametersImplementation
   * @generated
   */
  EClass getInitializationParametersImplementation();

  /**
   * Returns the meta object for the containment reference list '{@link org.archstudio.xadl3.implementation_3_0.InitializationParametersImplementation#getInitializationParameter <em>Initialization Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Initialization Parameter</em>'.
   * @see org.archstudio.xadl3.implementation_3_0.InitializationParametersImplementation#getInitializationParameter()
   * @see #getInitializationParametersImplementation()
   * @generated
   */
  EReference getInitializationParametersImplementation_InitializationParameter();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  Implementation_3_0Factory getImplementation_3_0Factory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.archstudio.xadl3.implementation_3_0.impl.ImplementationImpl <em>Implementation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.archstudio.xadl3.implementation_3_0.impl.ImplementationImpl
     * @see org.archstudio.xadl3.implementation_3_0.impl.Implementation_3_0PackageImpl#getImplementation()
     * @generated
     */
    EClass IMPLEMENTATION = eINSTANCE.getImplementation();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IMPLEMENTATION__ID = eINSTANCE.getImplementation_Id();

    /**
     * The meta object literal for the '{@link org.archstudio.xadl3.implementation_3_0.impl.ImplementationExtensionImpl <em>Implementation Extension</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.archstudio.xadl3.implementation_3_0.impl.ImplementationExtensionImpl
     * @see org.archstudio.xadl3.implementation_3_0.impl.Implementation_3_0PackageImpl#getImplementationExtension()
     * @generated
     */
    EClass IMPLEMENTATION_EXTENSION = eINSTANCE.getImplementationExtension();

    /**
     * The meta object literal for the '<em><b>Implementation</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IMPLEMENTATION_EXTENSION__IMPLEMENTATION = eINSTANCE.getImplementationExtension_Implementation();

    /**
     * The meta object literal for the '{@link org.archstudio.xadl3.implementation_3_0.impl.InitializationParameterImpl <em>Initialization Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.archstudio.xadl3.implementation_3_0.impl.InitializationParameterImpl
     * @see org.archstudio.xadl3.implementation_3_0.impl.Implementation_3_0PackageImpl#getInitializationParameter()
     * @generated
     */
    EClass INITIALIZATION_PARAMETER = eINSTANCE.getInitializationParameter();

    /**
     * The meta object literal for the '<em><b>Ext</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INITIALIZATION_PARAMETER__EXT = eINSTANCE.getInitializationParameter_Ext();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INITIALIZATION_PARAMETER__KEY = eINSTANCE.getInitializationParameter_Key();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INITIALIZATION_PARAMETER__VALUE = eINSTANCE.getInitializationParameter_Value();

    /**
     * The meta object literal for the '{@link org.archstudio.xadl3.implementation_3_0.impl.InitializationParametersImplementationImpl <em>Initialization Parameters Implementation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.archstudio.xadl3.implementation_3_0.impl.InitializationParametersImplementationImpl
     * @see org.archstudio.xadl3.implementation_3_0.impl.Implementation_3_0PackageImpl#getInitializationParametersImplementation()
     * @generated
     */
    EClass INITIALIZATION_PARAMETERS_IMPLEMENTATION = eINSTANCE.getInitializationParametersImplementation();

    /**
     * The meta object literal for the '<em><b>Initialization Parameter</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INITIALIZATION_PARAMETERS_IMPLEMENTATION__INITIALIZATION_PARAMETER = eINSTANCE.getInitializationParametersImplementation_InitializationParameter();

  }

} //Implementation_3_0Package
