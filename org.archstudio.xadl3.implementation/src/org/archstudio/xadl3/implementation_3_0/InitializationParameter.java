/**
 */
package org.archstudio.xadl3.implementation_3_0;

import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Initialization Parameter</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc -->
 * 
 * This type describes an initialization parameter.
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.archstudio.xadl3.implementation_3_0.InitializationParameter#getExt
 * <em>Ext</em>}</li>
 * <li>
 * {@link org.archstudio.xadl3.implementation_3_0.InitializationParameter#getKey
 * <em>Key</em>}</li>
 * <li>
 * {@link org.archstudio.xadl3.implementation_3_0.InitializationParameter#getValue
 * <em>Value</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package#getInitializationParameter()
 * @model extendedMetaData="name='InitializationParameter' kind='elementOnly'"
 * @generated
 */
public interface InitializationParameter extends EObject {
	/**
	 * Returns the value of the '<em><b>Ext</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link org.archstudio.xadl3.xadlcore_3_0.Extension}. <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Ext</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Ext</em>' containment reference list.
	 * @see org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package#getInitializationParameter_Ext()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='ext' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Extension> getExt();

	/**
	 * Returns the value of the '<em><b>Key</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Key</em>' attribute.
	 * @see #setKey(String)
	 * @see org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package#getInitializationParameter_Key()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData=
	 *        "kind='attribute' name='name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getKey();

	/**
	 * Sets the value of the '
	 * {@link org.archstudio.xadl3.implementation_3_0.InitializationParameter#getKey
	 * <em>Key</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Key</em>' attribute.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(String value);

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
	 * @see org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package#getInitializationParameter_Value()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData=
	 *        "kind='attribute' name='value' namespace='##targetNamespace'"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '
	 * {@link org.archstudio.xadl3.implementation_3_0.InitializationParameter#getValue
	 * <em>Value</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // InitializationParameter
