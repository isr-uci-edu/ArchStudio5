/**
 */
package org.archstudio.xadl3.domain_3_0;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Domain</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc -->
 * 
 * This type defines an element that can be added to another element to indicate
 * its domain.
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.domain_3_0.Domain#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.xadl3.domain_3_0.Domain_3_0Package#getDomain()
 * @model extendedMetaData="name='Domain' kind='empty'"
 * @generated
 */
public interface Domain extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute. The literals
	 * are from the enumeration
	 * {@link org.archstudio.xadl3.domain_3_0.DomainType}. <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.archstudio.xadl3.domain_3_0.DomainType
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #setType(DomainType)
	 * @see org.archstudio.xadl3.domain_3_0.Domain_3_0Package#getDomain_Type()
	 * @model unsettable="true" extendedMetaData=
	 *        "kind='attribute' name='type' namespace='##targetNamespace'"
	 * @generated
	 */
	DomainType getType();

	/**
	 * Sets the value of the '
	 * {@link org.archstudio.xadl3.domain_3_0.Domain#getType <em>Type</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Type</em>' attribute.
	 * @see org.archstudio.xadl3.domain_3_0.DomainType
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #getType()
	 * @generated
	 */
	void setType(DomainType value);

	/**
	 * Unsets the value of the '
	 * {@link org.archstudio.xadl3.domain_3_0.Domain#getType <em>Type</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(DomainType)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '
	 * {@link org.archstudio.xadl3.domain_3_0.Domain#getType <em>Type</em>}'
	 * attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(DomainType)
	 * @generated
	 */
	boolean isSetType();

} // Domain
