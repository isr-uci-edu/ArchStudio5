/**
 */
package net.gexf_1_2.gexf;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attributes Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.gexf_1_2.gexf.AttributesContent#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.AttributesContent#getClass_ <em>Class</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.AttributesContent#getEnd <em>End</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.AttributesContent#getEndopen <em>Endopen</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.AttributesContent#getMode <em>Mode</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.AttributesContent#getStart <em>Start</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.AttributesContent#getStartopen <em>Startopen</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.gexf_1_2.gexf.GexfPackage#getAttributesContent()
 * @model extendedMetaData="name='attributes-content' kind='elementOnly'"
 * @generated
 */
public interface AttributesContent extends EObject
{
  /**
   * Returns the value of the '<em><b>Attribute</b></em>' containment reference list.
   * The list contents are of type {@link net.gexf_1_2.gexf.AttributeContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Attribute</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute</em>' containment reference list.
   * @see net.gexf_1_2.gexf.GexfPackage#getAttributesContent_Attribute()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='attribute' namespace='##targetNamespace'"
   * @generated
   */
  EList<AttributeContent> getAttribute();

  /**
   * Returns the value of the '<em><b>Class</b></em>' attribute.
   * The literals are from the enumeration {@link net.gexf_1_2.gexf.ClassType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Class</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Class</em>' attribute.
   * @see net.gexf_1_2.gexf.ClassType
   * @see #isSetClass()
   * @see #unsetClass()
   * @see #setClass(ClassType)
   * @see net.gexf_1_2.gexf.GexfPackage#getAttributesContent_Class()
   * @model unsettable="true" required="true"
   *        extendedMetaData="kind='attribute' name='class'"
   * @generated
   */
  ClassType getClass_();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.AttributesContent#getClass_ <em>Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Class</em>' attribute.
   * @see net.gexf_1_2.gexf.ClassType
   * @see #isSetClass()
   * @see #unsetClass()
   * @see #getClass_()
   * @generated
   */
  void setClass(ClassType value);

  /**
   * Unsets the value of the '{@link net.gexf_1_2.gexf.AttributesContent#getClass_ <em>Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetClass()
   * @see #getClass_()
   * @see #setClass(ClassType)
   * @generated
   */
  void unsetClass();

  /**
   * Returns whether the value of the '{@link net.gexf_1_2.gexf.AttributesContent#getClass_ <em>Class</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Class</em>' attribute is set.
   * @see #unsetClass()
   * @see #getClass_()
   * @see #setClass(ClassType)
   * @generated
   */
  boolean isSetClass();

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
   * @see net.gexf_1_2.gexf.GexfPackage#getAttributesContent_End()
   * @model dataType="net.gexf_1_2.gexf.TimeType"
   *        extendedMetaData="kind='attribute' name='end'"
   * @generated
   */
  Object getEnd();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.AttributesContent#getEnd <em>End</em>}' attribute.
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
   * @see net.gexf_1_2.gexf.GexfPackage#getAttributesContent_Endopen()
   * @model dataType="net.gexf_1_2.gexf.TimeType"
   *        extendedMetaData="kind='attribute' name='endopen'"
   * @generated
   */
  Object getEndopen();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.AttributesContent#getEndopen <em>Endopen</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Endopen</em>' attribute.
   * @see #getEndopen()
   * @generated
   */
  void setEndopen(Object value);

  /**
   * Returns the value of the '<em><b>Mode</b></em>' attribute.
   * The literals are from the enumeration {@link net.gexf_1_2.gexf.ModeType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mode</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mode</em>' attribute.
   * @see net.gexf_1_2.gexf.ModeType
   * @see #isSetMode()
   * @see #unsetMode()
   * @see #setMode(ModeType)
   * @see net.gexf_1_2.gexf.GexfPackage#getAttributesContent_Mode()
   * @model unsettable="true"
   *        extendedMetaData="kind='attribute' name='mode'"
   * @generated
   */
  ModeType getMode();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.AttributesContent#getMode <em>Mode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mode</em>' attribute.
   * @see net.gexf_1_2.gexf.ModeType
   * @see #isSetMode()
   * @see #unsetMode()
   * @see #getMode()
   * @generated
   */
  void setMode(ModeType value);

  /**
   * Unsets the value of the '{@link net.gexf_1_2.gexf.AttributesContent#getMode <em>Mode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetMode()
   * @see #getMode()
   * @see #setMode(ModeType)
   * @generated
   */
  void unsetMode();

  /**
   * Returns whether the value of the '{@link net.gexf_1_2.gexf.AttributesContent#getMode <em>Mode</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Mode</em>' attribute is set.
   * @see #unsetMode()
   * @see #getMode()
   * @see #setMode(ModeType)
   * @generated
   */
  boolean isSetMode();

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
   * @see net.gexf_1_2.gexf.GexfPackage#getAttributesContent_Start()
   * @model dataType="net.gexf_1_2.gexf.TimeType"
   *        extendedMetaData="kind='attribute' name='start'"
   * @generated
   */
  Object getStart();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.AttributesContent#getStart <em>Start</em>}' attribute.
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
   * @see net.gexf_1_2.gexf.GexfPackage#getAttributesContent_Startopen()
   * @model dataType="net.gexf_1_2.gexf.TimeType"
   *        extendedMetaData="kind='attribute' name='startopen'"
   * @generated
   */
  Object getStartopen();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.AttributesContent#getStartopen <em>Startopen</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Startopen</em>' attribute.
   * @see #getStartopen()
   * @generated
   */
  void setStartopen(Object value);

} // AttributesContent
