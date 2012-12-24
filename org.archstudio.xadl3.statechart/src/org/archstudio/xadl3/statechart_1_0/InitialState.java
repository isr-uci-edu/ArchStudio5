/**
 */
package org.archstudio.xadl3.statechart_1_0;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Initial State</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.InitialState#getType <em>Type
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getInitialState()
 * @model extendedMetaData="name='InitialState' kind='elementOnly'"
 * @generated
 */
public interface InitialState extends PseudoState {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute. The default
	 * value is <code>"initial"</code>. The literals are from the enumeration
	 * {@link org.archstudio.xadl3.statechart_1_0.StateType}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.archstudio.xadl3.statechart_1_0.StateType
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #setType(StateType)
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getInitialState_Type()
	 * @model default="initial" unsettable="true" extendedMetaData=
	 *        "kind='attribute' name='type' namespace='##targetNamespace'"
	 * @generated
	 */
	StateType getType();

	/**
	 * Sets the value of the '
	 * {@link org.archstudio.xadl3.statechart_1_0.InitialState#getType
	 * <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
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
	 * Unsets the value of the '
	 * {@link org.archstudio.xadl3.statechart_1_0.InitialState#getType
	 * <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(StateType)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '
	 * {@link org.archstudio.xadl3.statechart_1_0.InitialState#getType
	 * <em>Type</em>}' attribute is set. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(StateType)
	 * @generated
	 */
	boolean isSetType();

} // InitialState
