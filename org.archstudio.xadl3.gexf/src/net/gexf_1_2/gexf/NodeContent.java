/**
 */
package net.gexf_1_2.gexf;

import net.gexf_1_2.viz.ColorContent;
import net.gexf_1_2.viz.NodeShapeContent;
import net.gexf_1_2.viz.PositionContent;
import net.gexf_1_2.viz.SizeContent;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.gexf_1_2.gexf.NodeContent#getGroup <em>Group</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.NodeContent#getAttvalues <em>Attvalues</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.NodeContent#getSpells <em>Spells</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.NodeContent#getNodes <em>Nodes</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.NodeContent#getEdges <em>Edges</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.NodeContent#getParents <em>Parents</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.NodeContent#getColor <em>Color</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.NodeContent#getPosition <em>Position</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.NodeContent#getSize <em>Size</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.NodeContent#getShape <em>Shape</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.NodeContent#getEnd <em>End</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.NodeContent#getEndopen <em>Endopen</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.NodeContent#getId <em>Id</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.NodeContent#getLabel <em>Label</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.NodeContent#getPid <em>Pid</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.NodeContent#getStart <em>Start</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.NodeContent#getStartopen <em>Startopen</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.gexf_1_2.gexf.GexfPackage#getNodeContent()
 * @model extendedMetaData="name='node-content' kind='elementOnly'"
 * @generated
 */
public interface NodeContent extends EObject
{
  /**
   * Returns the value of the '<em><b>Group</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Group</em>' attribute list.
   * @see net.gexf_1_2.gexf.GexfPackage#getNodeContent_Group()
   * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
   *        extendedMetaData="kind='group' name='group:0'"
   * @generated
   */
  FeatureMap getGroup();

  /**
   * Returns the value of the '<em><b>Attvalues</b></em>' containment reference list.
   * The list contents are of type {@link net.gexf_1_2.gexf.AttvaluesContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Attvalues</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attvalues</em>' containment reference list.
   * @see net.gexf_1_2.gexf.GexfPackage#getNodeContent_Attvalues()
   * @model containment="true" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='attvalues' namespace='##targetNamespace' group='#group:0'"
   * @generated
   */
  EList<AttvaluesContent> getAttvalues();

  /**
   * Returns the value of the '<em><b>Spells</b></em>' containment reference list.
   * The list contents are of type {@link net.gexf_1_2.gexf.SpellsContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Spells</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Spells</em>' containment reference list.
   * @see net.gexf_1_2.gexf.GexfPackage#getNodeContent_Spells()
   * @model containment="true" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='spells' namespace='##targetNamespace' group='#group:0'"
   * @generated
   */
  EList<SpellsContent> getSpells();

  /**
   * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
   * The list contents are of type {@link net.gexf_1_2.gexf.NodesContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Nodes</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Nodes</em>' containment reference list.
   * @see net.gexf_1_2.gexf.GexfPackage#getNodeContent_Nodes()
   * @model containment="true" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='nodes' namespace='##targetNamespace' group='#group:0'"
   * @generated
   */
  EList<NodesContent> getNodes();

  /**
   * Returns the value of the '<em><b>Edges</b></em>' containment reference list.
   * The list contents are of type {@link net.gexf_1_2.gexf.EdgesContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Edges</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Edges</em>' containment reference list.
   * @see net.gexf_1_2.gexf.GexfPackage#getNodeContent_Edges()
   * @model containment="true" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='edges' namespace='##targetNamespace' group='#group:0'"
   * @generated
   */
  EList<EdgesContent> getEdges();

  /**
   * Returns the value of the '<em><b>Parents</b></em>' containment reference list.
   * The list contents are of type {@link net.gexf_1_2.gexf.ParentsContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parents</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parents</em>' containment reference list.
   * @see net.gexf_1_2.gexf.GexfPackage#getNodeContent_Parents()
   * @model containment="true" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='parents' namespace='##targetNamespace' group='#group:0'"
   * @generated
   */
  EList<ParentsContent> getParents();

  /**
   * Returns the value of the '<em><b>Color</b></em>' containment reference list.
   * The list contents are of type {@link net.gexf_1_2.viz.ColorContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Color</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Color</em>' containment reference list.
   * @see net.gexf_1_2.gexf.GexfPackage#getNodeContent_Color()
   * @model containment="true" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='color' namespace='##targetNamespace' group='#group:0'"
   * @generated
   */
  EList<ColorContent> getColor();

  /**
   * Returns the value of the '<em><b>Position</b></em>' containment reference list.
   * The list contents are of type {@link net.gexf_1_2.viz.PositionContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Position</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Position</em>' containment reference list.
   * @see net.gexf_1_2.gexf.GexfPackage#getNodeContent_Position()
   * @model containment="true" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='position' namespace='##targetNamespace' group='#group:0'"
   * @generated
   */
  EList<PositionContent> getPosition();

  /**
   * Returns the value of the '<em><b>Size</b></em>' containment reference list.
   * The list contents are of type {@link net.gexf_1_2.viz.SizeContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Size</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Size</em>' containment reference list.
   * @see net.gexf_1_2.gexf.GexfPackage#getNodeContent_Size()
   * @model containment="true" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='size' namespace='##targetNamespace' group='#group:0'"
   * @generated
   */
  EList<SizeContent> getSize();

  /**
   * Returns the value of the '<em><b>Shape</b></em>' containment reference list.
   * The list contents are of type {@link net.gexf_1_2.viz.NodeShapeContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Shape</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Shape</em>' containment reference list.
   * @see net.gexf_1_2.gexf.GexfPackage#getNodeContent_Shape()
   * @model containment="true" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='shape' namespace='##targetNamespace' group='#group:0'"
   * @generated
   */
  EList<NodeShapeContent> getShape();

  /**
   * Returns the value of the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End</em>' attribute.
   * @see #setEnd(Object)
   * @see net.gexf_1_2.gexf.GexfPackage#getNodeContent_End()
   * @model dataType="net.gexf_1_2.gexf.TimeType"
   *        extendedMetaData="kind='attribute' name='end'"
   * @generated
   */
  Object getEnd();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.NodeContent#getEnd <em>End</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End</em>' attribute.
   * @see #getEnd()
   * @generated
   */
  void setEnd(Object value);

  /**
   * Returns the value of the '<em><b>Endopen</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Endopen</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Endopen</em>' attribute.
   * @see #setEndopen(Object)
   * @see net.gexf_1_2.gexf.GexfPackage#getNodeContent_Endopen()
   * @model dataType="net.gexf_1_2.gexf.TimeType"
   *        extendedMetaData="kind='attribute' name='endopen'"
   * @generated
   */
  Object getEndopen();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.NodeContent#getEndopen <em>Endopen</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Endopen</em>' attribute.
   * @see #getEndopen()
   * @generated
   */
  void setEndopen(Object value);

  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(Object)
   * @see net.gexf_1_2.gexf.GexfPackage#getNodeContent_Id()
   * @model dataType="net.gexf_1_2.gexf.IdType" required="true"
   *        extendedMetaData="kind='attribute' name='id'"
   * @generated
   */
  Object getId();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.NodeContent#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(Object value);

  /**
   * Returns the value of the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Label</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Label</em>' attribute.
   * @see #setLabel(String)
   * @see net.gexf_1_2.gexf.GexfPackage#getNodeContent_Label()
   * @model dataType="org.eclipse.emf.ecore.xml.type.Token"
   *        extendedMetaData="kind='attribute' name='label'"
   * @generated
   */
  String getLabel();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.NodeContent#getLabel <em>Label</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Label</em>' attribute.
   * @see #getLabel()
   * @generated
   */
  void setLabel(String value);

  /**
   * Returns the value of the '<em><b>Pid</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pid</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pid</em>' attribute.
   * @see #setPid(Object)
   * @see net.gexf_1_2.gexf.GexfPackage#getNodeContent_Pid()
   * @model dataType="net.gexf_1_2.gexf.IdType"
   *        extendedMetaData="kind='attribute' name='pid'"
   * @generated
   */
  Object getPid();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.NodeContent#getPid <em>Pid</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pid</em>' attribute.
   * @see #getPid()
   * @generated
   */
  void setPid(Object value);

  /**
   * Returns the value of the '<em><b>Start</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Start</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Start</em>' attribute.
   * @see #setStart(Object)
   * @see net.gexf_1_2.gexf.GexfPackage#getNodeContent_Start()
   * @model dataType="net.gexf_1_2.gexf.TimeType"
   *        extendedMetaData="kind='attribute' name='start'"
   * @generated
   */
  Object getStart();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.NodeContent#getStart <em>Start</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Start</em>' attribute.
   * @see #getStart()
   * @generated
   */
  void setStart(Object value);

  /**
   * Returns the value of the '<em><b>Startopen</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Startopen</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Startopen</em>' attribute.
   * @see #setStartopen(Object)
   * @see net.gexf_1_2.gexf.GexfPackage#getNodeContent_Startopen()
   * @model dataType="net.gexf_1_2.gexf.TimeType"
   *        extendedMetaData="kind='attribute' name='startopen'"
   * @generated
   */
  Object getStartopen();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.NodeContent#getStartopen <em>Startopen</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Startopen</em>' attribute.
   * @see #getStartopen()
   * @generated
   */
  void setStartopen(Object value);

} // NodeContent
