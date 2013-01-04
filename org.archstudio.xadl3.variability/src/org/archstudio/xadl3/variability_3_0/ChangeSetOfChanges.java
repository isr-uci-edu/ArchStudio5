/**
 */
package org.archstudio.xadl3.variability_3_0;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Change Set Of Changes</b></em>'. <!--
 * end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.variability_3_0.ChangeSetOfChanges#getElementChange <em>Element Change</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#getChangeSetOfChanges()
 * @model extendedMetaData="name='ChangeSetOfChanges' kind='elementOnly'"
 * @generated
 */
public interface ChangeSetOfChanges extends ChangeSet {
	/**
	 * Returns the value of the '<em><b>Element Change</b></em>' containment reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element Change</em>' containment reference isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Element Change</em>' containment reference.
	 * @see #setElementChange(ElementChange)
	 * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#getChangeSetOfChanges_ElementChange()
	 * @model containment="true" extendedMetaData="kind='element' name='elementChange' namespace='##targetNamespace'"
	 * @generated
	 */
	ElementChange getElementChange();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.variability_3_0.ChangeSetOfChanges#getElementChange
	 * <em>Element Change</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Element Change</em>' containment reference.
	 * @see #getElementChange()
	 * @generated
	 */
	void setElementChange(ElementChange value);

} // ChangeSetOfChanges
