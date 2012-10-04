/**
 */
package org.archstudio.xadl3.archlight_3_0;

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
 * 			xADL 3 Archlight Schema
 * 
 * 			This schema defines a set of test references that can be
 * 			added to a xADL document to indicate what Archlight tests
 * 			should be enabled on that document.
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
 * @see org.archstudio.xadl3.archlight_3_0.Archlight_3_0Factory
 * @model kind="package"
 * @generated
 */
public interface Archlight_3_0Package extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "archlight_3_0";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.archstudio.org/xadl3/schemas/archlight-3.0.xsd";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "archlight_3_0";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  Archlight_3_0Package eINSTANCE = org.archstudio.xadl3.archlight_3_0.impl.Archlight_3_0PackageImpl.init();

  /**
   * The meta object id for the '{@link org.archstudio.xadl3.archlight_3_0.impl.ArchlightImpl <em>Archlight</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.archstudio.xadl3.archlight_3_0.impl.ArchlightImpl
   * @see org.archstudio.xadl3.archlight_3_0.impl.Archlight_3_0PackageImpl#getArchlight()
   * @generated
   */
  int ARCHLIGHT = 0;

  /**
   * The feature id for the '<em><b>Test</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARCHLIGHT__TEST = 0;

  /**
   * The feature id for the '<em><b>Ext</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARCHLIGHT__EXT = 1;

  /**
   * The number of structural features of the '<em>Archlight</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARCHLIGHT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.archstudio.xadl3.archlight_3_0.impl.DocumentRootImpl <em>Document Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.archstudio.xadl3.archlight_3_0.impl.DocumentRootImpl
   * @see org.archstudio.xadl3.archlight_3_0.impl.Archlight_3_0PackageImpl#getDocumentRoot()
   * @generated
   */
  int DOCUMENT_ROOT = 1;

  /**
   * The feature id for the '<em><b>Mixed</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__MIXED = 0;

  /**
   * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

  /**
   * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

  /**
   * The feature id for the '<em><b>Archlight</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__ARCHLIGHT = 3;

  /**
   * The number of structural features of the '<em>Document Root</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.archstudio.xadl3.archlight_3_0.impl.TestImpl <em>Test</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.archstudio.xadl3.archlight_3_0.impl.TestImpl
   * @see org.archstudio.xadl3.archlight_3_0.impl.Archlight_3_0PackageImpl#getTest()
   * @generated
   */
  int TEST = 2;

  /**
   * The feature id for the '<em><b>Ext</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEST__EXT = 0;

  /**
   * The feature id for the '<em><b>Enabled</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEST__ENABLED = 1;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEST__ID = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEST__NAME = 3;

  /**
   * The number of structural features of the '<em>Test</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEST_FEATURE_COUNT = 4;


  /**
   * Returns the meta object for class '{@link org.archstudio.xadl3.archlight_3_0.Archlight <em>Archlight</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Archlight</em>'.
   * @see org.archstudio.xadl3.archlight_3_0.Archlight
   * @generated
   */
  EClass getArchlight();

  /**
   * Returns the meta object for the containment reference list '{@link org.archstudio.xadl3.archlight_3_0.Archlight#getTest <em>Test</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Test</em>'.
   * @see org.archstudio.xadl3.archlight_3_0.Archlight#getTest()
   * @see #getArchlight()
   * @generated
   */
  EReference getArchlight_Test();

  /**
   * Returns the meta object for the containment reference list '{@link org.archstudio.xadl3.archlight_3_0.Archlight#getExt <em>Ext</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Ext</em>'.
   * @see org.archstudio.xadl3.archlight_3_0.Archlight#getExt()
   * @see #getArchlight()
   * @generated
   */
  EReference getArchlight_Ext();

  /**
   * Returns the meta object for class '{@link org.archstudio.xadl3.archlight_3_0.DocumentRoot <em>Document Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Document Root</em>'.
   * @see org.archstudio.xadl3.archlight_3_0.DocumentRoot
   * @generated
   */
  EClass getDocumentRoot();

  /**
   * Returns the meta object for the attribute list '{@link org.archstudio.xadl3.archlight_3_0.DocumentRoot#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see org.archstudio.xadl3.archlight_3_0.DocumentRoot#getMixed()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_Mixed();

  /**
   * Returns the meta object for the map '{@link org.archstudio.xadl3.archlight_3_0.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
   * @see org.archstudio.xadl3.archlight_3_0.DocumentRoot#getXMLNSPrefixMap()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_XMLNSPrefixMap();

  /**
   * Returns the meta object for the map '{@link org.archstudio.xadl3.archlight_3_0.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XSI Schema Location</em>'.
   * @see org.archstudio.xadl3.archlight_3_0.DocumentRoot#getXSISchemaLocation()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_XSISchemaLocation();

  /**
   * Returns the meta object for the containment reference '{@link org.archstudio.xadl3.archlight_3_0.DocumentRoot#getArchlight <em>Archlight</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Archlight</em>'.
   * @see org.archstudio.xadl3.archlight_3_0.DocumentRoot#getArchlight()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_Archlight();

  /**
   * Returns the meta object for class '{@link org.archstudio.xadl3.archlight_3_0.Test <em>Test</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Test</em>'.
   * @see org.archstudio.xadl3.archlight_3_0.Test
   * @generated
   */
  EClass getTest();

  /**
   * Returns the meta object for the containment reference list '{@link org.archstudio.xadl3.archlight_3_0.Test#getExt <em>Ext</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Ext</em>'.
   * @see org.archstudio.xadl3.archlight_3_0.Test#getExt()
   * @see #getTest()
   * @generated
   */
  EReference getTest_Ext();

  /**
   * Returns the meta object for the attribute '{@link org.archstudio.xadl3.archlight_3_0.Test#isEnabled <em>Enabled</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Enabled</em>'.
   * @see org.archstudio.xadl3.archlight_3_0.Test#isEnabled()
   * @see #getTest()
   * @generated
   */
  EAttribute getTest_Enabled();

  /**
   * Returns the meta object for the attribute '{@link org.archstudio.xadl3.archlight_3_0.Test#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.archstudio.xadl3.archlight_3_0.Test#getId()
   * @see #getTest()
   * @generated
   */
  EAttribute getTest_Id();

  /**
   * Returns the meta object for the attribute '{@link org.archstudio.xadl3.archlight_3_0.Test#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.archstudio.xadl3.archlight_3_0.Test#getName()
   * @see #getTest()
   * @generated
   */
  EAttribute getTest_Name();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  Archlight_3_0Factory getArchlight_3_0Factory();

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
     * The meta object literal for the '{@link org.archstudio.xadl3.archlight_3_0.impl.ArchlightImpl <em>Archlight</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.archstudio.xadl3.archlight_3_0.impl.ArchlightImpl
     * @see org.archstudio.xadl3.archlight_3_0.impl.Archlight_3_0PackageImpl#getArchlight()
     * @generated
     */
    EClass ARCHLIGHT = eINSTANCE.getArchlight();

    /**
     * The meta object literal for the '<em><b>Test</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARCHLIGHT__TEST = eINSTANCE.getArchlight_Test();

    /**
     * The meta object literal for the '<em><b>Ext</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARCHLIGHT__EXT = eINSTANCE.getArchlight_Ext();

    /**
     * The meta object literal for the '{@link org.archstudio.xadl3.archlight_3_0.impl.DocumentRootImpl <em>Document Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.archstudio.xadl3.archlight_3_0.impl.DocumentRootImpl
     * @see org.archstudio.xadl3.archlight_3_0.impl.Archlight_3_0PackageImpl#getDocumentRoot()
     * @generated
     */
    EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

    /**
     * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

    /**
     * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

    /**
     * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

    /**
     * The meta object literal for the '<em><b>Archlight</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOCUMENT_ROOT__ARCHLIGHT = eINSTANCE.getDocumentRoot_Archlight();

    /**
     * The meta object literal for the '{@link org.archstudio.xadl3.archlight_3_0.impl.TestImpl <em>Test</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.archstudio.xadl3.archlight_3_0.impl.TestImpl
     * @see org.archstudio.xadl3.archlight_3_0.impl.Archlight_3_0PackageImpl#getTest()
     * @generated
     */
    EClass TEST = eINSTANCE.getTest();

    /**
     * The meta object literal for the '<em><b>Ext</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TEST__EXT = eINSTANCE.getTest_Ext();

    /**
     * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TEST__ENABLED = eINSTANCE.getTest_Enabled();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TEST__ID = eINSTANCE.getTest_Id();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TEST__NAME = eINSTANCE.getTest_Name();

  }

} //Archlight_3_0Package
