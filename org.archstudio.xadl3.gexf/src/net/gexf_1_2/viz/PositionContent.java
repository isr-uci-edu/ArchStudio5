/**
 */
package net.gexf_1_2.viz;

import net.gexf_1_2.gexf.SpellsContent;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Position Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.gexf_1_2.viz.PositionContent#getSpells <em>Spells</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.PositionContent#getEnd <em>End</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.PositionContent#getEndopen <em>Endopen</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.PositionContent#getStart <em>Start</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.PositionContent#getStartopen <em>Startopen</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.PositionContent#getX <em>X</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.PositionContent#getY <em>Y</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.PositionContent#getZ <em>Z</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.gexf_1_2.viz.VizPackage#getPositionContent()
 * @model extendedMetaData="name='position-content' kind='elementOnly'"
 * @generated
 */
public interface PositionContent extends EObject
{
  /**
   * Returns the value of the '<em><b>Spells</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Spells</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Spells</em>' containment reference.
   * @see #setSpells(SpellsContent)
   * @see net.gexf_1_2.viz.VizPackage#getPositionContent_Spells()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='spells' namespace='##targetNamespace'"
   * @generated
   */
  SpellsContent getSpells();

  /**
   * Sets the value of the '{@link net.gexf_1_2.viz.PositionContent#getSpells <em>Spells</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Spells</em>' containment reference.
   * @see #getSpells()
   * @generated
   */
  void setSpells(SpellsContent value);

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
   * @see net.gexf_1_2.viz.VizPackage#getPositionContent_End()
   * @model dataType="net.gexf_1_2.gexf.TimeType"
   *        extendedMetaData="kind='attribute' name='end'"
   * @generated
   */
  Object getEnd();

  /**
   * Sets the value of the '{@link net.gexf_1_2.viz.PositionContent#getEnd <em>End</em>}' attribute.
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
   * @see net.gexf_1_2.viz.VizPackage#getPositionContent_Endopen()
   * @model dataType="net.gexf_1_2.gexf.TimeType"
   *        extendedMetaData="kind='attribute' name='endopen'"
   * @generated
   */
  Object getEndopen();

  /**
   * Sets the value of the '{@link net.gexf_1_2.viz.PositionContent#getEndopen <em>Endopen</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Endopen</em>' attribute.
   * @see #getEndopen()
   * @generated
   */
  void setEndopen(Object value);

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
   * @see net.gexf_1_2.viz.VizPackage#getPositionContent_Start()
   * @model dataType="net.gexf_1_2.gexf.TimeType"
   *        extendedMetaData="kind='attribute' name='start'"
   * @generated
   */
  Object getStart();

  /**
   * Sets the value of the '{@link net.gexf_1_2.viz.PositionContent#getStart <em>Start</em>}' attribute.
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
   * @see net.gexf_1_2.viz.VizPackage#getPositionContent_Startopen()
   * @model dataType="net.gexf_1_2.gexf.TimeType"
   *        extendedMetaData="kind='attribute' name='startopen'"
   * @generated
   */
  Object getStartopen();

  /**
   * Sets the value of the '{@link net.gexf_1_2.viz.PositionContent#getStartopen <em>Startopen</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Startopen</em>' attribute.
   * @see #getStartopen()
   * @generated
   */
  void setStartopen(Object value);

  /**
   * Returns the value of the '<em><b>X</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>X</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>X</em>' attribute.
   * @see #isSetX()
   * @see #unsetX()
   * @see #setX(float)
   * @see net.gexf_1_2.viz.VizPackage#getPositionContent_X()
   * @model unsettable="true" dataType="net.gexf_1_2.viz.SpacePoint" required="true"
   *        extendedMetaData="kind='attribute' name='x'"
   * @generated
   */
  float getX();

  /**
   * Sets the value of the '{@link net.gexf_1_2.viz.PositionContent#getX <em>X</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>X</em>' attribute.
   * @see #isSetX()
   * @see #unsetX()
   * @see #getX()
   * @generated
   */
  void setX(float value);

  /**
   * Unsets the value of the '{@link net.gexf_1_2.viz.PositionContent#getX <em>X</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetX()
   * @see #getX()
   * @see #setX(float)
   * @generated
   */
  void unsetX();

  /**
   * Returns whether the value of the '{@link net.gexf_1_2.viz.PositionContent#getX <em>X</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>X</em>' attribute is set.
   * @see #unsetX()
   * @see #getX()
   * @see #setX(float)
   * @generated
   */
  boolean isSetX();

  /**
   * Returns the value of the '<em><b>Y</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Y</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Y</em>' attribute.
   * @see #isSetY()
   * @see #unsetY()
   * @see #setY(float)
   * @see net.gexf_1_2.viz.VizPackage#getPositionContent_Y()
   * @model unsettable="true" dataType="net.gexf_1_2.viz.SpacePoint" required="true"
   *        extendedMetaData="kind='attribute' name='y'"
   * @generated
   */
  float getY();

  /**
   * Sets the value of the '{@link net.gexf_1_2.viz.PositionContent#getY <em>Y</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Y</em>' attribute.
   * @see #isSetY()
   * @see #unsetY()
   * @see #getY()
   * @generated
   */
  void setY(float value);

  /**
   * Unsets the value of the '{@link net.gexf_1_2.viz.PositionContent#getY <em>Y</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetY()
   * @see #getY()
   * @see #setY(float)
   * @generated
   */
  void unsetY();

  /**
   * Returns whether the value of the '{@link net.gexf_1_2.viz.PositionContent#getY <em>Y</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Y</em>' attribute is set.
   * @see #unsetY()
   * @see #getY()
   * @see #setY(float)
   * @generated
   */
  boolean isSetY();

  /**
   * Returns the value of the '<em><b>Z</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Z</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Z</em>' attribute.
   * @see #isSetZ()
   * @see #unsetZ()
   * @see #setZ(float)
   * @see net.gexf_1_2.viz.VizPackage#getPositionContent_Z()
   * @model unsettable="true" dataType="net.gexf_1_2.viz.SpacePoint" required="true"
   *        extendedMetaData="kind='attribute' name='z'"
   * @generated
   */
  float getZ();

  /**
   * Sets the value of the '{@link net.gexf_1_2.viz.PositionContent#getZ <em>Z</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Z</em>' attribute.
   * @see #isSetZ()
   * @see #unsetZ()
   * @see #getZ()
   * @generated
   */
  void setZ(float value);

  /**
   * Unsets the value of the '{@link net.gexf_1_2.viz.PositionContent#getZ <em>Z</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetZ()
   * @see #getZ()
   * @see #setZ(float)
   * @generated
   */
  void unsetZ();

  /**
   * Returns whether the value of the '{@link net.gexf_1_2.viz.PositionContent#getZ <em>Z</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Z</em>' attribute is set.
   * @see #unsetZ()
   * @see #getZ()
   * @see #setZ(float)
   * @generated
   */
  boolean isSetZ();

} // PositionContent
