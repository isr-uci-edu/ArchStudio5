/**
 */
package org.archstudio.xadl3.statechart_1_0;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>State</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.State#getEntry <em>Entry</em>}</li>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.State#getExit <em>Exit</em>}</li>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.State#getSubStatechart <em>Sub Statechart</em>}</li>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.State#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getState()
 * @model extendedMetaData="name='State' kind='elementOnly'"
 * @generated
 */
public interface State extends PseudoState {
	/**
	 * Returns the value of the '<em><b>Entry</b></em>' containment reference list. The list contents are of type
	 * {@link org.archstudio.xadl3.statechart_1_0.Behavior}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entry</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Entry</em>' containment reference list.
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getState_Entry()
	 * @model containment="true" extendedMetaData="kind='element' name='entry' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Behavior> getEntry();

	/**
	 * Returns the value of the '<em><b>Exit</b></em>' containment reference list. The list contents are of type
	 * {@link org.archstudio.xadl3.statechart_1_0.Behavior}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exit</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Exit</em>' containment reference list.
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getState_Exit()
	 * @model containment="true" extendedMetaData="kind='element' name='exit' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Behavior> getExit();

	/**
	 * Returns the value of the '<em><b>Sub Statechart</b></em>' containment reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Statechart</em>' containment reference isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Sub Statechart</em>' containment reference.
	 * @see #setSubStatechart(SubStatechart)
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getState_SubStatechart()
	 * @model containment="true" extendedMetaData="kind='element' name='subStatechart' namespace='##targetNamespace'"
	 * @generated
	 */
	SubStatechart getSubStatechart();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.statechart_1_0.State#getSubStatechart <em>Sub Statechart</em>}
	 * ' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Sub Statechart</em>' containment reference.
	 * @see #getSubStatechart()
	 * @generated
	 */
	void setSubStatechart(SubStatechart value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute. The default value is <code>"state"</code>. The
	 * literals are from the enumeration {@link org.archstudio.xadl3.statechart_1_0.StateType}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.archstudio.xadl3.statechart_1_0.StateType
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #setType(StateType)
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getState_Type()
	 * @model default="state" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='type' namespace='##targetNamespace'"
	 * @generated
	 */
	StateType getType();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.statechart_1_0.State#getType <em>Type</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Type</em>' attribute.
	 * @see org.archstudio.xadl3.statechart_1_0.StateType
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #getType()
	 * @generated
	 */
	void setType(StateType value);

	/**
	 * Unsets the value of the '{@link org.archstudio.xadl3.statechart_1_0.State#getType <em>Type</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(StateType)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '{@link org.archstudio.xadl3.statechart_1_0.State#getType <em>Type</em>}'
	 * attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(StateType)
	 * @generated
	 */
	boolean isSetType();

} // State
