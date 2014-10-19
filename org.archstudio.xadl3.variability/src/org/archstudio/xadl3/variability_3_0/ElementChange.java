/**
 */
package org.archstudio.xadl3.variability_3_0;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Element Change</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.variability_3_0.ElementChange#getChange <em>Change</em>}</li>
 * <li>{@link org.archstudio.xadl3.variability_3_0.ElementChange#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#getElementChange()
 * @model extendedMetaData="name='ElementChange' kind='elementOnly'"
 * @generated
 */
public interface ElementChange extends Change {
	/**
	 * Returns the value of the '<em><b>Change</b></em>' containment reference list. The list contents are of type
	 * {@link org.archstudio.xadl3.variability_3_0.Change}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Change</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Change</em>' containment reference list.
	 * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#getElementChange_Change()
	 * @model containment="true" extendedMetaData="kind='element' name='change' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Change> getChange();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#getElementChange_Type()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='type' namespace='##targetNamespace'"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.variability_3_0.ElementChange#getType <em>Type</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

} // ElementChange
