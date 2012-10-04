/**
 */
package org.archstudio.xadl3.statechart_1_0;

import org.archstudio.xadl3.xadlcore_3_0.Extension;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.archstudio.xadl3.statechart_1_0.Transition#getFrom <em>From</em>}</li>
 *   <li>{@link org.archstudio.xadl3.statechart_1_0.Transition#getTo <em>To</em>}</li>
 *   <li>{@link org.archstudio.xadl3.statechart_1_0.Transition#getConstraint <em>Constraint</em>}</li>
 *   <li>{@link org.archstudio.xadl3.statechart_1_0.Transition#getExt <em>Ext</em>}</li>
 *   <li>{@link org.archstudio.xadl3.statechart_1_0.Transition#getId <em>Id</em>}</li>
 *   <li>{@link org.archstudio.xadl3.statechart_1_0.Transition#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getTransition()
 * @model extendedMetaData="name='Transition' kind='elementOnly'"
 * @generated
 */
public interface Transition extends EObject
{
  /**
   * Returns the value of the '<em><b>From</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>From</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>From</em>' reference.
   * @see #setFrom(PseudoState)
   * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getTransition_From()
   * @model resolveProxies="false" required="true"
   *        extendedMetaData="kind='element' name='from' namespace='##targetNamespace'"
   * @generated
   */
  PseudoState getFrom();

  /**
   * Sets the value of the '{@link org.archstudio.xadl3.statechart_1_0.Transition#getFrom <em>From</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>From</em>' reference.
   * @see #getFrom()
   * @generated
   */
  void setFrom(PseudoState value);

  /**
   * Returns the value of the '<em><b>To</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>To</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>To</em>' reference.
   * @see #setTo(PseudoState)
   * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getTransition_To()
   * @model resolveProxies="false" required="true"
   *        extendedMetaData="kind='element' name='to' namespace='##targetNamespace'"
   * @generated
   */
  PseudoState getTo();

  /**
   * Sets the value of the '{@link org.archstudio.xadl3.statechart_1_0.Transition#getTo <em>To</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>To</em>' reference.
   * @see #getTo()
   * @generated
   */
  void setTo(PseudoState value);

  /**
   * Returns the value of the '<em><b>Constraint</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Constraint</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Constraint</em>' containment reference.
   * @see #setConstraint(Constraint)
   * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getTransition_Constraint()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='constraint' namespace='##targetNamespace'"
   * @generated
   */
  Constraint getConstraint();

  /**
   * Sets the value of the '{@link org.archstudio.xadl3.statechart_1_0.Transition#getConstraint <em>Constraint</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Constraint</em>' containment reference.
   * @see #getConstraint()
   * @generated
   */
  void setConstraint(Constraint value);

  /**
   * Returns the value of the '<em><b>Ext</b></em>' containment reference list.
   * The list contents are of type {@link org.archstudio.xadl3.xadlcore_3_0.Extension}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ext</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ext</em>' containment reference list.
   * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getTransition_Ext()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='ext' namespace='##targetNamespace'"
   * @generated
   */
  EList<Extension> getExt();

  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getTransition_Id()
   * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
   *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.archstudio.xadl3.statechart_1_0.Transition#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getTransition_Name()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String"
   *        extendedMetaData="kind='attribute' name='name' namespace='##targetNamespace'"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.archstudio.xadl3.statechart_1_0.Transition#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

} // Transition
