/**
 */
package net.gexf_1_2.gexf;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Parent Type</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.ParentType#getFor <em>For</em>}</li>
 * </ul>
 * </p>
 * 
 * @see net.gexf_1_2.gexf.GexfPackage#getParentType()
 * @model extendedMetaData="name='parent_._type' kind='empty'"
 * @generated
 */
public interface ParentType extends EObject {
	/**
	 * Returns the value of the '<em><b>For</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>For</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>For</em>' attribute.
	 * @see #setFor(Object)
	 * @see net.gexf_1_2.gexf.GexfPackage#getParentType_For()
	 * @model dataType="net.gexf_1_2.gexf.IdType" required="true"
	 *        extendedMetaData="kind='attribute' name='for'"
	 * @generated
	 */
	Object getFor();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.ParentType#getFor
	 * <em>For</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>For</em>' attribute.
	 * @see #getFor()
	 * @generated
	 */
	void setFor(Object value);

} // ParentType
