/**
 */
package org.archstudio.xadl3.xadlcore_3_0;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Simple Link</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * This type is meant to describe elements that are simple outbound links outside the current document (internal links
 * should use IDREFs). Elements of this type are simple-type XLinks that can use a simple href to link to a document, or
 * XPointer to refer to an element within a document.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.xadlcore_3_0.SimpleLink#getHref <em>Href</em>}</li>
 * <li>{@link org.archstudio.xadl3.xadlcore_3_0.SimpleLink#getId <em>Id</em>}</li>
 * <li>{@link org.archstudio.xadl3.xadlcore_3_0.SimpleLink#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package#getSimpleLink()
 * @model extendedMetaData="name='SimpleLink' kind='empty'"
 * @generated
 */
public interface SimpleLink extends EObject {
	/**
	 * Returns the value of the '<em><b>Href</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Href</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Href</em>' attribute.
	 * @see #setHref(String)
	 * @see org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package#getSimpleLink_Href()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI" required="true"
	 *        extendedMetaData="kind='attribute' name='href' namespace='http://www.w3.org/1999/xlink'"
	 * @generated
	 */
	String getHref();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.xadlcore_3_0.SimpleLink#getHref <em>Href</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Href</em>' attribute.
	 * @see #getHref()
	 * @generated
	 */
	void setHref(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package#getSimpleLink_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.xadlcore_3_0.SimpleLink#getId <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute. The default value is <code>"simple"</code>. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #setType(String)
	 * @see org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package#getSimpleLink_Type()
	 * @model default="simple" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='type' namespace='http://www.w3.org/1999/xlink'"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.xadlcore_3_0.SimpleLink#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Type</em>' attribute.
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Unsets the value of the '{@link org.archstudio.xadl3.xadlcore_3_0.SimpleLink#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(String)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '{@link org.archstudio.xadl3.xadlcore_3_0.SimpleLink#getType <em>Type</em>}'
	 * attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(String)
	 * @generated
	 */
	boolean isSetType();

} // SimpleLink
