/**
 */
package org.archstudio.xadl3.statechart_1_0;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Final State</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.FinalState#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getFinalState()
 * @model extendedMetaData="name='FinalState' kind='elementOnly'"
 * @generated
 */
public interface FinalState extends PseudoState {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute. The default value is <code>"final"</code>. The
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
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getFinalState_Type()
	 * @model default="final" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='type' namespace='##targetNamespace'"
	 * @generated
	 */
	StateType getType();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.statechart_1_0.FinalState#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * Unsets the value of the '{@link org.archstudio.xadl3.statechart_1_0.FinalState#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(StateType)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '{@link org.archstudio.xadl3.statechart_1_0.FinalState#getType <em>Type</em>}'
	 * attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(StateType)
	 * @generated
	 */
	boolean isSetType();

} // FinalState
