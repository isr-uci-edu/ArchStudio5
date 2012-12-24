/**
 */
package org.archstudio.xadl3.variability_3_0;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Attribute Change</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.variability_3_0.AttributeChange#getValue <em>
 * Value</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#getAttributeChange()
 * @model extendedMetaData="name='AttributeChange' kind='empty'"
 * @generated
 */
public interface AttributeChange extends Change {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#getAttributeChange_Value()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData=
	 *        "kind='attribute' name='value' namespace='##targetNamespace'"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '
	 * {@link org.archstudio.xadl3.variability_3_0.AttributeChange#getValue
	 * <em>Value</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // AttributeChange
