/**
 */
package net.gexf_1_2.gexf;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Spell Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.gexf_1_2.gexf.SpellType#getEnd <em>End</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.SpellType#getEndopen <em>Endopen</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.SpellType#getStart <em>Start</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.SpellType#getStartopen <em>Startopen</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.gexf_1_2.gexf.GexfPackage#getSpellType()
 * @model extendedMetaData="name='spell_._type' kind='empty'"
 * @generated
 */
public interface SpellType extends EObject
{
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
   * @see net.gexf_1_2.gexf.GexfPackage#getSpellType_End()
   * @model dataType="net.gexf_1_2.gexf.TimeType"
   *        extendedMetaData="kind='attribute' name='end'"
   * @generated
   */
  Object getEnd();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.SpellType#getEnd <em>End</em>}' attribute.
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
   * @see net.gexf_1_2.gexf.GexfPackage#getSpellType_Endopen()
   * @model dataType="net.gexf_1_2.gexf.TimeType"
   *        extendedMetaData="kind='attribute' name='endopen'"
   * @generated
   */
  Object getEndopen();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.SpellType#getEndopen <em>Endopen</em>}' attribute.
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
   * @see net.gexf_1_2.gexf.GexfPackage#getSpellType_Start()
   * @model dataType="net.gexf_1_2.gexf.TimeType"
   *        extendedMetaData="kind='attribute' name='start'"
   * @generated
   */
  Object getStart();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.SpellType#getStart <em>Start</em>}' attribute.
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
   * @see net.gexf_1_2.gexf.GexfPackage#getSpellType_Startopen()
   * @model dataType="net.gexf_1_2.gexf.TimeType"
   *        extendedMetaData="kind='attribute' name='startopen'"
   * @generated
   */
  Object getStartopen();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.SpellType#getStartopen <em>Startopen</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Startopen</em>' attribute.
   * @see #getStartopen()
   * @generated
   */
  void setStartopen(Object value);

} // SpellType
