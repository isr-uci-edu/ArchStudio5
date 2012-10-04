/**
 */
package net.gexf_1_2.viz;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
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
 * @see net.gexf_1_2.viz.VizFactory
 * @model kind="package"
 * @generated
 */
public interface VizPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "viz";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.gexf.net/1.2draft/viz";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "viz";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  VizPackage eINSTANCE = net.gexf_1_2.viz.impl.VizPackageImpl.init();

  /**
   * The meta object id for the '{@link net.gexf_1_2.viz.impl.ColorContentImpl <em>Color Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.gexf_1_2.viz.impl.ColorContentImpl
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getColorContent()
   * @generated
   */
  int COLOR_CONTENT = 0;

  /**
   * The feature id for the '<em><b>Spells</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLOR_CONTENT__SPELLS = 0;

  /**
   * The feature id for the '<em><b>A</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLOR_CONTENT__A = 1;

  /**
   * The feature id for the '<em><b>B</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLOR_CONTENT__B = 2;

  /**
   * The feature id for the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLOR_CONTENT__END = 3;

  /**
   * The feature id for the '<em><b>Endopen</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLOR_CONTENT__ENDOPEN = 4;

  /**
   * The feature id for the '<em><b>G</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLOR_CONTENT__G = 5;

  /**
   * The feature id for the '<em><b>R</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLOR_CONTENT__R = 6;

  /**
   * The feature id for the '<em><b>Start</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLOR_CONTENT__START = 7;

  /**
   * The feature id for the '<em><b>Startopen</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLOR_CONTENT__STARTOPEN = 8;

  /**
   * The number of structural features of the '<em>Color Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLOR_CONTENT_FEATURE_COUNT = 9;

  /**
   * The meta object id for the '{@link net.gexf_1_2.viz.impl.EdgeShapeContentImpl <em>Edge Shape Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.gexf_1_2.viz.impl.EdgeShapeContentImpl
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getEdgeShapeContent()
   * @generated
   */
  int EDGE_SHAPE_CONTENT = 1;

  /**
   * The feature id for the '<em><b>Spells</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_SHAPE_CONTENT__SPELLS = 0;

  /**
   * The feature id for the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_SHAPE_CONTENT__END = 1;

  /**
   * The feature id for the '<em><b>Endopen</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_SHAPE_CONTENT__ENDOPEN = 2;

  /**
   * The feature id for the '<em><b>Start</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_SHAPE_CONTENT__START = 3;

  /**
   * The feature id for the '<em><b>Startopen</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_SHAPE_CONTENT__STARTOPEN = 4;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_SHAPE_CONTENT__VALUE = 5;

  /**
   * The number of structural features of the '<em>Edge Shape Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_SHAPE_CONTENT_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link net.gexf_1_2.viz.impl.NodeShapeContentImpl <em>Node Shape Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.gexf_1_2.viz.impl.NodeShapeContentImpl
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getNodeShapeContent()
   * @generated
   */
  int NODE_SHAPE_CONTENT = 2;

  /**
   * The feature id for the '<em><b>Spells</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_SHAPE_CONTENT__SPELLS = 0;

  /**
   * The feature id for the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_SHAPE_CONTENT__END = 1;

  /**
   * The feature id for the '<em><b>Endopen</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_SHAPE_CONTENT__ENDOPEN = 2;

  /**
   * The feature id for the '<em><b>Start</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_SHAPE_CONTENT__START = 3;

  /**
   * The feature id for the '<em><b>Startopen</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_SHAPE_CONTENT__STARTOPEN = 4;

  /**
   * The feature id for the '<em><b>Uri</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_SHAPE_CONTENT__URI = 5;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_SHAPE_CONTENT__VALUE = 6;

  /**
   * The number of structural features of the '<em>Node Shape Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_SHAPE_CONTENT_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link net.gexf_1_2.viz.impl.PositionContentImpl <em>Position Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.gexf_1_2.viz.impl.PositionContentImpl
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getPositionContent()
   * @generated
   */
  int POSITION_CONTENT = 3;

  /**
   * The feature id for the '<em><b>Spells</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POSITION_CONTENT__SPELLS = 0;

  /**
   * The feature id for the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POSITION_CONTENT__END = 1;

  /**
   * The feature id for the '<em><b>Endopen</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POSITION_CONTENT__ENDOPEN = 2;

  /**
   * The feature id for the '<em><b>Start</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POSITION_CONTENT__START = 3;

  /**
   * The feature id for the '<em><b>Startopen</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POSITION_CONTENT__STARTOPEN = 4;

  /**
   * The feature id for the '<em><b>X</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POSITION_CONTENT__X = 5;

  /**
   * The feature id for the '<em><b>Y</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POSITION_CONTENT__Y = 6;

  /**
   * The feature id for the '<em><b>Z</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POSITION_CONTENT__Z = 7;

  /**
   * The number of structural features of the '<em>Position Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POSITION_CONTENT_FEATURE_COUNT = 8;

  /**
   * The meta object id for the '{@link net.gexf_1_2.viz.impl.SizeContentImpl <em>Size Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.gexf_1_2.viz.impl.SizeContentImpl
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getSizeContent()
   * @generated
   */
  int SIZE_CONTENT = 4;

  /**
   * The feature id for the '<em><b>Spells</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_CONTENT__SPELLS = 0;

  /**
   * The feature id for the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_CONTENT__END = 1;

  /**
   * The feature id for the '<em><b>Endopen</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_CONTENT__ENDOPEN = 2;

  /**
   * The feature id for the '<em><b>Start</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_CONTENT__START = 3;

  /**
   * The feature id for the '<em><b>Startopen</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_CONTENT__STARTOPEN = 4;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_CONTENT__VALUE = 5;

  /**
   * The number of structural features of the '<em>Size Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIZE_CONTENT_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link net.gexf_1_2.viz.impl.ThicknessContentImpl <em>Thickness Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.gexf_1_2.viz.impl.ThicknessContentImpl
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getThicknessContent()
   * @generated
   */
  int THICKNESS_CONTENT = 5;

  /**
   * The feature id for the '<em><b>Spells</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THICKNESS_CONTENT__SPELLS = 0;

  /**
   * The feature id for the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THICKNESS_CONTENT__END = 1;

  /**
   * The feature id for the '<em><b>Endopen</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THICKNESS_CONTENT__ENDOPEN = 2;

  /**
   * The feature id for the '<em><b>Start</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THICKNESS_CONTENT__START = 3;

  /**
   * The feature id for the '<em><b>Startopen</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THICKNESS_CONTENT__STARTOPEN = 4;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THICKNESS_CONTENT__VALUE = 5;

  /**
   * The number of structural features of the '<em>Thickness Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int THICKNESS_CONTENT_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link net.gexf_1_2.viz.impl.DocumentRootImpl <em>Document Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.gexf_1_2.viz.impl.DocumentRootImpl
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getDocumentRoot()
   * @generated
   */
  int DOCUMENT_ROOT = 6;

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
   * The feature id for the '<em><b>Spells</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT__SPELLS = 3;

  /**
   * The number of structural features of the '<em>Document Root</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOCUMENT_ROOT_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link net.gexf_1_2.viz.EdgeShapeType <em>Edge Shape Type</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.gexf_1_2.viz.EdgeShapeType
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getEdgeShapeType()
   * @generated
   */
  int EDGE_SHAPE_TYPE = 7;

  /**
   * The meta object id for the '{@link net.gexf_1_2.viz.NodeShapeType <em>Node Shape Type</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.gexf_1_2.viz.NodeShapeType
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getNodeShapeType()
   * @generated
   */
  int NODE_SHAPE_TYPE = 8;

  /**
   * The meta object id for the '<em>Alpha Channel</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getAlphaChannel()
   * @generated
   */
  int ALPHA_CHANNEL = 9;

  /**
   * The meta object id for the '<em>Alpha Channel Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Float
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getAlphaChannelObject()
   * @generated
   */
  int ALPHA_CHANNEL_OBJECT = 10;

  /**
   * The meta object id for the '<em>Color Channel</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.math.BigInteger
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getColorChannel()
   * @generated
   */
  int COLOR_CHANNEL = 11;

  /**
   * The meta object id for the '<em>Edge Shape Type Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.gexf_1_2.viz.EdgeShapeType
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getEdgeShapeTypeObject()
   * @generated
   */
  int EDGE_SHAPE_TYPE_OBJECT = 12;

  /**
   * The meta object id for the '<em>Node Shape Type Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.gexf_1_2.viz.NodeShapeType
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getNodeShapeTypeObject()
   * @generated
   */
  int NODE_SHAPE_TYPE_OBJECT = 13;

  /**
   * The meta object id for the '<em>Size Type</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getSizeType()
   * @generated
   */
  int SIZE_TYPE = 14;

  /**
   * The meta object id for the '<em>Size Type Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Float
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getSizeTypeObject()
   * @generated
   */
  int SIZE_TYPE_OBJECT = 15;

  /**
   * The meta object id for the '<em>Space Point</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getSpacePoint()
   * @generated
   */
  int SPACE_POINT = 16;

  /**
   * The meta object id for the '<em>Space Point Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Float
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getSpacePointObject()
   * @generated
   */
  int SPACE_POINT_OBJECT = 17;

  /**
   * The meta object id for the '<em>Thickness Type</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getThicknessType()
   * @generated
   */
  int THICKNESS_TYPE = 18;

  /**
   * The meta object id for the '<em>Thickness Type Object</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.Float
   * @see net.gexf_1_2.viz.impl.VizPackageImpl#getThicknessTypeObject()
   * @generated
   */
  int THICKNESS_TYPE_OBJECT = 19;


  /**
   * Returns the meta object for class '{@link net.gexf_1_2.viz.ColorContent <em>Color Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Color Content</em>'.
   * @see net.gexf_1_2.viz.ColorContent
   * @generated
   */
  EClass getColorContent();

  /**
   * Returns the meta object for the containment reference '{@link net.gexf_1_2.viz.ColorContent#getSpells <em>Spells</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Spells</em>'.
   * @see net.gexf_1_2.viz.ColorContent#getSpells()
   * @see #getColorContent()
   * @generated
   */
  EReference getColorContent_Spells();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.ColorContent#getA <em>A</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>A</em>'.
   * @see net.gexf_1_2.viz.ColorContent#getA()
   * @see #getColorContent()
   * @generated
   */
  EAttribute getColorContent_A();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.ColorContent#getB <em>B</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>B</em>'.
   * @see net.gexf_1_2.viz.ColorContent#getB()
   * @see #getColorContent()
   * @generated
   */
  EAttribute getColorContent_B();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.ColorContent#getEnd <em>End</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End</em>'.
   * @see net.gexf_1_2.viz.ColorContent#getEnd()
   * @see #getColorContent()
   * @generated
   */
  EAttribute getColorContent_End();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.ColorContent#getEndopen <em>Endopen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Endopen</em>'.
   * @see net.gexf_1_2.viz.ColorContent#getEndopen()
   * @see #getColorContent()
   * @generated
   */
  EAttribute getColorContent_Endopen();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.ColorContent#getG <em>G</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>G</em>'.
   * @see net.gexf_1_2.viz.ColorContent#getG()
   * @see #getColorContent()
   * @generated
   */
  EAttribute getColorContent_G();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.ColorContent#getR <em>R</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>R</em>'.
   * @see net.gexf_1_2.viz.ColorContent#getR()
   * @see #getColorContent()
   * @generated
   */
  EAttribute getColorContent_R();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.ColorContent#getStart <em>Start</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Start</em>'.
   * @see net.gexf_1_2.viz.ColorContent#getStart()
   * @see #getColorContent()
   * @generated
   */
  EAttribute getColorContent_Start();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.ColorContent#getStartopen <em>Startopen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Startopen</em>'.
   * @see net.gexf_1_2.viz.ColorContent#getStartopen()
   * @see #getColorContent()
   * @generated
   */
  EAttribute getColorContent_Startopen();

  /**
   * Returns the meta object for class '{@link net.gexf_1_2.viz.EdgeShapeContent <em>Edge Shape Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Edge Shape Content</em>'.
   * @see net.gexf_1_2.viz.EdgeShapeContent
   * @generated
   */
  EClass getEdgeShapeContent();

  /**
   * Returns the meta object for the containment reference '{@link net.gexf_1_2.viz.EdgeShapeContent#getSpells <em>Spells</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Spells</em>'.
   * @see net.gexf_1_2.viz.EdgeShapeContent#getSpells()
   * @see #getEdgeShapeContent()
   * @generated
   */
  EReference getEdgeShapeContent_Spells();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.EdgeShapeContent#getEnd <em>End</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End</em>'.
   * @see net.gexf_1_2.viz.EdgeShapeContent#getEnd()
   * @see #getEdgeShapeContent()
   * @generated
   */
  EAttribute getEdgeShapeContent_End();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.EdgeShapeContent#getEndopen <em>Endopen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Endopen</em>'.
   * @see net.gexf_1_2.viz.EdgeShapeContent#getEndopen()
   * @see #getEdgeShapeContent()
   * @generated
   */
  EAttribute getEdgeShapeContent_Endopen();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.EdgeShapeContent#getStart <em>Start</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Start</em>'.
   * @see net.gexf_1_2.viz.EdgeShapeContent#getStart()
   * @see #getEdgeShapeContent()
   * @generated
   */
  EAttribute getEdgeShapeContent_Start();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.EdgeShapeContent#getStartopen <em>Startopen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Startopen</em>'.
   * @see net.gexf_1_2.viz.EdgeShapeContent#getStartopen()
   * @see #getEdgeShapeContent()
   * @generated
   */
  EAttribute getEdgeShapeContent_Startopen();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.EdgeShapeContent#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see net.gexf_1_2.viz.EdgeShapeContent#getValue()
   * @see #getEdgeShapeContent()
   * @generated
   */
  EAttribute getEdgeShapeContent_Value();

  /**
   * Returns the meta object for class '{@link net.gexf_1_2.viz.NodeShapeContent <em>Node Shape Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Node Shape Content</em>'.
   * @see net.gexf_1_2.viz.NodeShapeContent
   * @generated
   */
  EClass getNodeShapeContent();

  /**
   * Returns the meta object for the containment reference '{@link net.gexf_1_2.viz.NodeShapeContent#getSpells <em>Spells</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Spells</em>'.
   * @see net.gexf_1_2.viz.NodeShapeContent#getSpells()
   * @see #getNodeShapeContent()
   * @generated
   */
  EReference getNodeShapeContent_Spells();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.NodeShapeContent#getEnd <em>End</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End</em>'.
   * @see net.gexf_1_2.viz.NodeShapeContent#getEnd()
   * @see #getNodeShapeContent()
   * @generated
   */
  EAttribute getNodeShapeContent_End();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.NodeShapeContent#getEndopen <em>Endopen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Endopen</em>'.
   * @see net.gexf_1_2.viz.NodeShapeContent#getEndopen()
   * @see #getNodeShapeContent()
   * @generated
   */
  EAttribute getNodeShapeContent_Endopen();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.NodeShapeContent#getStart <em>Start</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Start</em>'.
   * @see net.gexf_1_2.viz.NodeShapeContent#getStart()
   * @see #getNodeShapeContent()
   * @generated
   */
  EAttribute getNodeShapeContent_Start();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.NodeShapeContent#getStartopen <em>Startopen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Startopen</em>'.
   * @see net.gexf_1_2.viz.NodeShapeContent#getStartopen()
   * @see #getNodeShapeContent()
   * @generated
   */
  EAttribute getNodeShapeContent_Startopen();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.NodeShapeContent#getUri <em>Uri</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Uri</em>'.
   * @see net.gexf_1_2.viz.NodeShapeContent#getUri()
   * @see #getNodeShapeContent()
   * @generated
   */
  EAttribute getNodeShapeContent_Uri();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.NodeShapeContent#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see net.gexf_1_2.viz.NodeShapeContent#getValue()
   * @see #getNodeShapeContent()
   * @generated
   */
  EAttribute getNodeShapeContent_Value();

  /**
   * Returns the meta object for class '{@link net.gexf_1_2.viz.PositionContent <em>Position Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Position Content</em>'.
   * @see net.gexf_1_2.viz.PositionContent
   * @generated
   */
  EClass getPositionContent();

  /**
   * Returns the meta object for the containment reference '{@link net.gexf_1_2.viz.PositionContent#getSpells <em>Spells</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Spells</em>'.
   * @see net.gexf_1_2.viz.PositionContent#getSpells()
   * @see #getPositionContent()
   * @generated
   */
  EReference getPositionContent_Spells();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.PositionContent#getEnd <em>End</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End</em>'.
   * @see net.gexf_1_2.viz.PositionContent#getEnd()
   * @see #getPositionContent()
   * @generated
   */
  EAttribute getPositionContent_End();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.PositionContent#getEndopen <em>Endopen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Endopen</em>'.
   * @see net.gexf_1_2.viz.PositionContent#getEndopen()
   * @see #getPositionContent()
   * @generated
   */
  EAttribute getPositionContent_Endopen();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.PositionContent#getStart <em>Start</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Start</em>'.
   * @see net.gexf_1_2.viz.PositionContent#getStart()
   * @see #getPositionContent()
   * @generated
   */
  EAttribute getPositionContent_Start();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.PositionContent#getStartopen <em>Startopen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Startopen</em>'.
   * @see net.gexf_1_2.viz.PositionContent#getStartopen()
   * @see #getPositionContent()
   * @generated
   */
  EAttribute getPositionContent_Startopen();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.PositionContent#getX <em>X</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>X</em>'.
   * @see net.gexf_1_2.viz.PositionContent#getX()
   * @see #getPositionContent()
   * @generated
   */
  EAttribute getPositionContent_X();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.PositionContent#getY <em>Y</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Y</em>'.
   * @see net.gexf_1_2.viz.PositionContent#getY()
   * @see #getPositionContent()
   * @generated
   */
  EAttribute getPositionContent_Y();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.PositionContent#getZ <em>Z</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Z</em>'.
   * @see net.gexf_1_2.viz.PositionContent#getZ()
   * @see #getPositionContent()
   * @generated
   */
  EAttribute getPositionContent_Z();

  /**
   * Returns the meta object for class '{@link net.gexf_1_2.viz.SizeContent <em>Size Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Size Content</em>'.
   * @see net.gexf_1_2.viz.SizeContent
   * @generated
   */
  EClass getSizeContent();

  /**
   * Returns the meta object for the containment reference '{@link net.gexf_1_2.viz.SizeContent#getSpells <em>Spells</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Spells</em>'.
   * @see net.gexf_1_2.viz.SizeContent#getSpells()
   * @see #getSizeContent()
   * @generated
   */
  EReference getSizeContent_Spells();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.SizeContent#getEnd <em>End</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End</em>'.
   * @see net.gexf_1_2.viz.SizeContent#getEnd()
   * @see #getSizeContent()
   * @generated
   */
  EAttribute getSizeContent_End();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.SizeContent#getEndopen <em>Endopen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Endopen</em>'.
   * @see net.gexf_1_2.viz.SizeContent#getEndopen()
   * @see #getSizeContent()
   * @generated
   */
  EAttribute getSizeContent_Endopen();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.SizeContent#getStart <em>Start</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Start</em>'.
   * @see net.gexf_1_2.viz.SizeContent#getStart()
   * @see #getSizeContent()
   * @generated
   */
  EAttribute getSizeContent_Start();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.SizeContent#getStartopen <em>Startopen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Startopen</em>'.
   * @see net.gexf_1_2.viz.SizeContent#getStartopen()
   * @see #getSizeContent()
   * @generated
   */
  EAttribute getSizeContent_Startopen();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.SizeContent#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see net.gexf_1_2.viz.SizeContent#getValue()
   * @see #getSizeContent()
   * @generated
   */
  EAttribute getSizeContent_Value();

  /**
   * Returns the meta object for class '{@link net.gexf_1_2.viz.ThicknessContent <em>Thickness Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Thickness Content</em>'.
   * @see net.gexf_1_2.viz.ThicknessContent
   * @generated
   */
  EClass getThicknessContent();

  /**
   * Returns the meta object for the containment reference '{@link net.gexf_1_2.viz.ThicknessContent#getSpells <em>Spells</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Spells</em>'.
   * @see net.gexf_1_2.viz.ThicknessContent#getSpells()
   * @see #getThicknessContent()
   * @generated
   */
  EReference getThicknessContent_Spells();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.ThicknessContent#getEnd <em>End</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End</em>'.
   * @see net.gexf_1_2.viz.ThicknessContent#getEnd()
   * @see #getThicknessContent()
   * @generated
   */
  EAttribute getThicknessContent_End();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.ThicknessContent#getEndopen <em>Endopen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Endopen</em>'.
   * @see net.gexf_1_2.viz.ThicknessContent#getEndopen()
   * @see #getThicknessContent()
   * @generated
   */
  EAttribute getThicknessContent_Endopen();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.ThicknessContent#getStart <em>Start</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Start</em>'.
   * @see net.gexf_1_2.viz.ThicknessContent#getStart()
   * @see #getThicknessContent()
   * @generated
   */
  EAttribute getThicknessContent_Start();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.ThicknessContent#getStartopen <em>Startopen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Startopen</em>'.
   * @see net.gexf_1_2.viz.ThicknessContent#getStartopen()
   * @see #getThicknessContent()
   * @generated
   */
  EAttribute getThicknessContent_Startopen();

  /**
   * Returns the meta object for the attribute '{@link net.gexf_1_2.viz.ThicknessContent#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see net.gexf_1_2.viz.ThicknessContent#getValue()
   * @see #getThicknessContent()
   * @generated
   */
  EAttribute getThicknessContent_Value();

  /**
   * Returns the meta object for class '{@link net.gexf_1_2.viz.DocumentRoot <em>Document Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Document Root</em>'.
   * @see net.gexf_1_2.viz.DocumentRoot
   * @generated
   */
  EClass getDocumentRoot();

  /**
   * Returns the meta object for the attribute list '{@link net.gexf_1_2.viz.DocumentRoot#getMixed <em>Mixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Mixed</em>'.
   * @see net.gexf_1_2.viz.DocumentRoot#getMixed()
   * @see #getDocumentRoot()
   * @generated
   */
  EAttribute getDocumentRoot_Mixed();

  /**
   * Returns the meta object for the map '{@link net.gexf_1_2.viz.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
   * @see net.gexf_1_2.viz.DocumentRoot#getXMLNSPrefixMap()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_XMLNSPrefixMap();

  /**
   * Returns the meta object for the map '{@link net.gexf_1_2.viz.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>XSI Schema Location</em>'.
   * @see net.gexf_1_2.viz.DocumentRoot#getXSISchemaLocation()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_XSISchemaLocation();

  /**
   * Returns the meta object for the containment reference '{@link net.gexf_1_2.viz.DocumentRoot#getSpells <em>Spells</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Spells</em>'.
   * @see net.gexf_1_2.viz.DocumentRoot#getSpells()
   * @see #getDocumentRoot()
   * @generated
   */
  EReference getDocumentRoot_Spells();

  /**
   * Returns the meta object for enum '{@link net.gexf_1_2.viz.EdgeShapeType <em>Edge Shape Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Edge Shape Type</em>'.
   * @see net.gexf_1_2.viz.EdgeShapeType
   * @generated
   */
  EEnum getEdgeShapeType();

  /**
   * Returns the meta object for enum '{@link net.gexf_1_2.viz.NodeShapeType <em>Node Shape Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Node Shape Type</em>'.
   * @see net.gexf_1_2.viz.NodeShapeType
   * @generated
   */
  EEnum getNodeShapeType();

  /**
   * Returns the meta object for data type '<em>Alpha Channel</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Alpha Channel</em>'.
   * @model instanceClass="float"
   *        extendedMetaData="name='alpha-channel' baseType='http://www.eclipse.org/emf/2003/XMLType#float' minInclusive='0.0' maxInclusive='1.0'"
   * @generated
   */
  EDataType getAlphaChannel();

  /**
   * Returns the meta object for data type '{@link java.lang.Float <em>Alpha Channel Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Alpha Channel Object</em>'.
   * @see java.lang.Float
   * @model instanceClass="java.lang.Float"
   *        extendedMetaData="name='alpha-channel:Object' baseType='alpha-channel'"
   * @generated
   */
  EDataType getAlphaChannelObject();

  /**
   * Returns the meta object for data type '{@link java.math.BigInteger <em>Color Channel</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Color Channel</em>'.
   * @see java.math.BigInteger
   * @model instanceClass="java.math.BigInteger"
   *        extendedMetaData="name='color-channel' baseType='http://www.eclipse.org/emf/2003/XMLType#nonNegativeInteger' maxInclusive='255'"
   * @generated
   */
  EDataType getColorChannel();

  /**
   * Returns the meta object for data type '{@link net.gexf_1_2.viz.EdgeShapeType <em>Edge Shape Type Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Edge Shape Type Object</em>'.
   * @see net.gexf_1_2.viz.EdgeShapeType
   * @model instanceClass="net.gexf_1_2.viz.EdgeShapeType"
   *        extendedMetaData="name='edge-shape-type:Object' baseType='edge-shape-type'"
   * @generated
   */
  EDataType getEdgeShapeTypeObject();

  /**
   * Returns the meta object for data type '{@link net.gexf_1_2.viz.NodeShapeType <em>Node Shape Type Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Node Shape Type Object</em>'.
   * @see net.gexf_1_2.viz.NodeShapeType
   * @model instanceClass="net.gexf_1_2.viz.NodeShapeType"
   *        extendedMetaData="name='node-shape-type:Object' baseType='node-shape-type'"
   * @generated
   */
  EDataType getNodeShapeTypeObject();

  /**
   * Returns the meta object for data type '<em>Size Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Size Type</em>'.
   * @model instanceClass="float"
   *        extendedMetaData="name='size-type' baseType='http://www.eclipse.org/emf/2003/XMLType#float' minInclusive='0.0'"
   * @generated
   */
  EDataType getSizeType();

  /**
   * Returns the meta object for data type '{@link java.lang.Float <em>Size Type Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Size Type Object</em>'.
   * @see java.lang.Float
   * @model instanceClass="java.lang.Float"
   *        extendedMetaData="name='size-type:Object' baseType='size-type'"
   * @generated
   */
  EDataType getSizeTypeObject();

  /**
   * Returns the meta object for data type '<em>Space Point</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Space Point</em>'.
   * @model instanceClass="float"
   *        extendedMetaData="name='space-point' baseType='http://www.eclipse.org/emf/2003/XMLType#float'"
   * @generated
   */
  EDataType getSpacePoint();

  /**
   * Returns the meta object for data type '{@link java.lang.Float <em>Space Point Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Space Point Object</em>'.
   * @see java.lang.Float
   * @model instanceClass="java.lang.Float"
   *        extendedMetaData="name='space-point:Object' baseType='space-point'"
   * @generated
   */
  EDataType getSpacePointObject();

  /**
   * Returns the meta object for data type '<em>Thickness Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Thickness Type</em>'.
   * @model instanceClass="float"
   *        extendedMetaData="name='thickness-type' baseType='http://www.eclipse.org/emf/2003/XMLType#float' minInclusive='0.0'"
   * @generated
   */
  EDataType getThicknessType();

  /**
   * Returns the meta object for data type '{@link java.lang.Float <em>Thickness Type Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Thickness Type Object</em>'.
   * @see java.lang.Float
   * @model instanceClass="java.lang.Float"
   *        extendedMetaData="name='thickness-type:Object' baseType='thickness-type'"
   * @generated
   */
  EDataType getThicknessTypeObject();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  VizFactory getVizFactory();

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
     * The meta object literal for the '{@link net.gexf_1_2.viz.impl.ColorContentImpl <em>Color Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.gexf_1_2.viz.impl.ColorContentImpl
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getColorContent()
     * @generated
     */
    EClass COLOR_CONTENT = eINSTANCE.getColorContent();

    /**
     * The meta object literal for the '<em><b>Spells</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COLOR_CONTENT__SPELLS = eINSTANCE.getColorContent_Spells();

    /**
     * The meta object literal for the '<em><b>A</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COLOR_CONTENT__A = eINSTANCE.getColorContent_A();

    /**
     * The meta object literal for the '<em><b>B</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COLOR_CONTENT__B = eINSTANCE.getColorContent_B();

    /**
     * The meta object literal for the '<em><b>End</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COLOR_CONTENT__END = eINSTANCE.getColorContent_End();

    /**
     * The meta object literal for the '<em><b>Endopen</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COLOR_CONTENT__ENDOPEN = eINSTANCE.getColorContent_Endopen();

    /**
     * The meta object literal for the '<em><b>G</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COLOR_CONTENT__G = eINSTANCE.getColorContent_G();

    /**
     * The meta object literal for the '<em><b>R</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COLOR_CONTENT__R = eINSTANCE.getColorContent_R();

    /**
     * The meta object literal for the '<em><b>Start</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COLOR_CONTENT__START = eINSTANCE.getColorContent_Start();

    /**
     * The meta object literal for the '<em><b>Startopen</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COLOR_CONTENT__STARTOPEN = eINSTANCE.getColorContent_Startopen();

    /**
     * The meta object literal for the '{@link net.gexf_1_2.viz.impl.EdgeShapeContentImpl <em>Edge Shape Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.gexf_1_2.viz.impl.EdgeShapeContentImpl
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getEdgeShapeContent()
     * @generated
     */
    EClass EDGE_SHAPE_CONTENT = eINSTANCE.getEdgeShapeContent();

    /**
     * The meta object literal for the '<em><b>Spells</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EDGE_SHAPE_CONTENT__SPELLS = eINSTANCE.getEdgeShapeContent_Spells();

    /**
     * The meta object literal for the '<em><b>End</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EDGE_SHAPE_CONTENT__END = eINSTANCE.getEdgeShapeContent_End();

    /**
     * The meta object literal for the '<em><b>Endopen</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EDGE_SHAPE_CONTENT__ENDOPEN = eINSTANCE.getEdgeShapeContent_Endopen();

    /**
     * The meta object literal for the '<em><b>Start</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EDGE_SHAPE_CONTENT__START = eINSTANCE.getEdgeShapeContent_Start();

    /**
     * The meta object literal for the '<em><b>Startopen</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EDGE_SHAPE_CONTENT__STARTOPEN = eINSTANCE.getEdgeShapeContent_Startopen();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EDGE_SHAPE_CONTENT__VALUE = eINSTANCE.getEdgeShapeContent_Value();

    /**
     * The meta object literal for the '{@link net.gexf_1_2.viz.impl.NodeShapeContentImpl <em>Node Shape Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.gexf_1_2.viz.impl.NodeShapeContentImpl
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getNodeShapeContent()
     * @generated
     */
    EClass NODE_SHAPE_CONTENT = eINSTANCE.getNodeShapeContent();

    /**
     * The meta object literal for the '<em><b>Spells</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NODE_SHAPE_CONTENT__SPELLS = eINSTANCE.getNodeShapeContent_Spells();

    /**
     * The meta object literal for the '<em><b>End</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NODE_SHAPE_CONTENT__END = eINSTANCE.getNodeShapeContent_End();

    /**
     * The meta object literal for the '<em><b>Endopen</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NODE_SHAPE_CONTENT__ENDOPEN = eINSTANCE.getNodeShapeContent_Endopen();

    /**
     * The meta object literal for the '<em><b>Start</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NODE_SHAPE_CONTENT__START = eINSTANCE.getNodeShapeContent_Start();

    /**
     * The meta object literal for the '<em><b>Startopen</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NODE_SHAPE_CONTENT__STARTOPEN = eINSTANCE.getNodeShapeContent_Startopen();

    /**
     * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NODE_SHAPE_CONTENT__URI = eINSTANCE.getNodeShapeContent_Uri();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NODE_SHAPE_CONTENT__VALUE = eINSTANCE.getNodeShapeContent_Value();

    /**
     * The meta object literal for the '{@link net.gexf_1_2.viz.impl.PositionContentImpl <em>Position Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.gexf_1_2.viz.impl.PositionContentImpl
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getPositionContent()
     * @generated
     */
    EClass POSITION_CONTENT = eINSTANCE.getPositionContent();

    /**
     * The meta object literal for the '<em><b>Spells</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POSITION_CONTENT__SPELLS = eINSTANCE.getPositionContent_Spells();

    /**
     * The meta object literal for the '<em><b>End</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POSITION_CONTENT__END = eINSTANCE.getPositionContent_End();

    /**
     * The meta object literal for the '<em><b>Endopen</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POSITION_CONTENT__ENDOPEN = eINSTANCE.getPositionContent_Endopen();

    /**
     * The meta object literal for the '<em><b>Start</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POSITION_CONTENT__START = eINSTANCE.getPositionContent_Start();

    /**
     * The meta object literal for the '<em><b>Startopen</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POSITION_CONTENT__STARTOPEN = eINSTANCE.getPositionContent_Startopen();

    /**
     * The meta object literal for the '<em><b>X</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POSITION_CONTENT__X = eINSTANCE.getPositionContent_X();

    /**
     * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POSITION_CONTENT__Y = eINSTANCE.getPositionContent_Y();

    /**
     * The meta object literal for the '<em><b>Z</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POSITION_CONTENT__Z = eINSTANCE.getPositionContent_Z();

    /**
     * The meta object literal for the '{@link net.gexf_1_2.viz.impl.SizeContentImpl <em>Size Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.gexf_1_2.viz.impl.SizeContentImpl
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getSizeContent()
     * @generated
     */
    EClass SIZE_CONTENT = eINSTANCE.getSizeContent();

    /**
     * The meta object literal for the '<em><b>Spells</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SIZE_CONTENT__SPELLS = eINSTANCE.getSizeContent_Spells();

    /**
     * The meta object literal for the '<em><b>End</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SIZE_CONTENT__END = eINSTANCE.getSizeContent_End();

    /**
     * The meta object literal for the '<em><b>Endopen</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SIZE_CONTENT__ENDOPEN = eINSTANCE.getSizeContent_Endopen();

    /**
     * The meta object literal for the '<em><b>Start</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SIZE_CONTENT__START = eINSTANCE.getSizeContent_Start();

    /**
     * The meta object literal for the '<em><b>Startopen</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SIZE_CONTENT__STARTOPEN = eINSTANCE.getSizeContent_Startopen();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SIZE_CONTENT__VALUE = eINSTANCE.getSizeContent_Value();

    /**
     * The meta object literal for the '{@link net.gexf_1_2.viz.impl.ThicknessContentImpl <em>Thickness Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.gexf_1_2.viz.impl.ThicknessContentImpl
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getThicknessContent()
     * @generated
     */
    EClass THICKNESS_CONTENT = eINSTANCE.getThicknessContent();

    /**
     * The meta object literal for the '<em><b>Spells</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference THICKNESS_CONTENT__SPELLS = eINSTANCE.getThicknessContent_Spells();

    /**
     * The meta object literal for the '<em><b>End</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute THICKNESS_CONTENT__END = eINSTANCE.getThicknessContent_End();

    /**
     * The meta object literal for the '<em><b>Endopen</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute THICKNESS_CONTENT__ENDOPEN = eINSTANCE.getThicknessContent_Endopen();

    /**
     * The meta object literal for the '<em><b>Start</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute THICKNESS_CONTENT__START = eINSTANCE.getThicknessContent_Start();

    /**
     * The meta object literal for the '<em><b>Startopen</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute THICKNESS_CONTENT__STARTOPEN = eINSTANCE.getThicknessContent_Startopen();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute THICKNESS_CONTENT__VALUE = eINSTANCE.getThicknessContent_Value();

    /**
     * The meta object literal for the '{@link net.gexf_1_2.viz.impl.DocumentRootImpl <em>Document Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.gexf_1_2.viz.impl.DocumentRootImpl
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getDocumentRoot()
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
     * The meta object literal for the '<em><b>Spells</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOCUMENT_ROOT__SPELLS = eINSTANCE.getDocumentRoot_Spells();

    /**
     * The meta object literal for the '{@link net.gexf_1_2.viz.EdgeShapeType <em>Edge Shape Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.gexf_1_2.viz.EdgeShapeType
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getEdgeShapeType()
     * @generated
     */
    EEnum EDGE_SHAPE_TYPE = eINSTANCE.getEdgeShapeType();

    /**
     * The meta object literal for the '{@link net.gexf_1_2.viz.NodeShapeType <em>Node Shape Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.gexf_1_2.viz.NodeShapeType
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getNodeShapeType()
     * @generated
     */
    EEnum NODE_SHAPE_TYPE = eINSTANCE.getNodeShapeType();

    /**
     * The meta object literal for the '<em>Alpha Channel</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getAlphaChannel()
     * @generated
     */
    EDataType ALPHA_CHANNEL = eINSTANCE.getAlphaChannel();

    /**
     * The meta object literal for the '<em>Alpha Channel Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.Float
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getAlphaChannelObject()
     * @generated
     */
    EDataType ALPHA_CHANNEL_OBJECT = eINSTANCE.getAlphaChannelObject();

    /**
     * The meta object literal for the '<em>Color Channel</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.math.BigInteger
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getColorChannel()
     * @generated
     */
    EDataType COLOR_CHANNEL = eINSTANCE.getColorChannel();

    /**
     * The meta object literal for the '<em>Edge Shape Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.gexf_1_2.viz.EdgeShapeType
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getEdgeShapeTypeObject()
     * @generated
     */
    EDataType EDGE_SHAPE_TYPE_OBJECT = eINSTANCE.getEdgeShapeTypeObject();

    /**
     * The meta object literal for the '<em>Node Shape Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.gexf_1_2.viz.NodeShapeType
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getNodeShapeTypeObject()
     * @generated
     */
    EDataType NODE_SHAPE_TYPE_OBJECT = eINSTANCE.getNodeShapeTypeObject();

    /**
     * The meta object literal for the '<em>Size Type</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getSizeType()
     * @generated
     */
    EDataType SIZE_TYPE = eINSTANCE.getSizeType();

    /**
     * The meta object literal for the '<em>Size Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.Float
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getSizeTypeObject()
     * @generated
     */
    EDataType SIZE_TYPE_OBJECT = eINSTANCE.getSizeTypeObject();

    /**
     * The meta object literal for the '<em>Space Point</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getSpacePoint()
     * @generated
     */
    EDataType SPACE_POINT = eINSTANCE.getSpacePoint();

    /**
     * The meta object literal for the '<em>Space Point Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.Float
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getSpacePointObject()
     * @generated
     */
    EDataType SPACE_POINT_OBJECT = eINSTANCE.getSpacePointObject();

    /**
     * The meta object literal for the '<em>Thickness Type</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getThicknessType()
     * @generated
     */
    EDataType THICKNESS_TYPE = eINSTANCE.getThicknessType();

    /**
     * The meta object literal for the '<em>Thickness Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.Float
     * @see net.gexf_1_2.viz.impl.VizPackageImpl#getThicknessTypeObject()
     * @generated
     */
    EDataType THICKNESS_TYPE_OBJECT = eINSTANCE.getThicknessTypeObject();

  }

} //VizPackage
