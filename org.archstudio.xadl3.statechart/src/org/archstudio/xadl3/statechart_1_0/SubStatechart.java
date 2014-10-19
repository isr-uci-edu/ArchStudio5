/**
 */
package org.archstudio.xadl3.statechart_1_0;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Sub Statechart</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.SubStatechart#getInnerStatechart <em>Inner Statechart</em>}</li>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.SubStatechart#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getSubStatechart()
 * @model extendedMetaData="name='SubStatechart' kind='elementOnly'"
 * @generated
 */
public interface SubStatechart extends EObject {
	/**
	 * Returns the value of the '<em><b>Inner Statechart</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inner Statechart</em>' reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Inner Statechart</em>' reference.
	 * @see #setInnerStatechart(Statechart)
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getSubStatechart_InnerStatechart()
	 * @model resolveProxies="false" required="true"
	 *        extendedMetaData="kind='element' name='innerStatechart' namespace='##targetNamespace'"
	 * @generated
	 */
	Statechart getInnerStatechart();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.statechart_1_0.SubStatechart#getInnerStatechart
	 * <em>Inner Statechart</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Inner Statechart</em>' reference.
	 * @see #getInnerStatechart()
	 * @generated
	 */
	void setInnerStatechart(Statechart value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getSubStatechart_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.statechart_1_0.SubStatechart#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // SubStatechart
