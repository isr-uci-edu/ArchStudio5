/**
 */
package org.archstudio.xadl3.statechart_1_0;

import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Constraint</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.Constraint#getExt <em>Ext</em>}</li>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.Constraint#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getConstraint()
 * @model extendedMetaData="name='Constraint' kind='elementOnly'"
 * @generated
 */
public interface Constraint extends EObject {
	/**
	 * Returns the value of the '<em><b>Ext</b></em>' containment reference list. The list contents are of type
	 * {@link org.archstudio.xadl3.xadlcore_3_0.Extension}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ext</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Ext</em>' containment reference list.
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getConstraint_Ext()
	 * @model containment="true" extendedMetaData="kind='element' name='ext' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Extension> getExt();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getConstraint_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.statechart_1_0.Constraint#getId <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // Constraint
