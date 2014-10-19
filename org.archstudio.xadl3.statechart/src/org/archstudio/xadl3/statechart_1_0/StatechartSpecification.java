/**
 */
package org.archstudio.xadl3.statechart_1_0;

import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Statechart Specification</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.StatechartSpecification#getStatechart <em>Statechart</em>}</li>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.StatechartSpecification#getExt <em>Ext</em>}</li>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.StatechartSpecification#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getStatechartSpecification()
 * @model extendedMetaData="name='StatechartSpecification' kind='elementOnly'"
 * @generated
 */
public interface StatechartSpecification extends Extension {
	/**
	 * Returns the value of the '<em><b>Statechart</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Statechart</em>' reference isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Statechart</em>' reference.
	 * @see #setStatechart(Statechart)
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getStatechartSpecification_Statechart()
	 * @model resolveProxies="false" required="true"
	 *        extendedMetaData="kind='element' name='statechart' namespace='##targetNamespace'"
	 * @generated
	 */
	Statechart getStatechart();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.statechart_1_0.StatechartSpecification#getStatechart
	 * <em>Statechart</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Statechart</em>' reference.
	 * @see #getStatechart()
	 * @generated
	 */
	void setStatechart(Statechart value);

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
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getStatechartSpecification_Ext()
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
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getStatechartSpecification_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.statechart_1_0.StatechartSpecification#getId <em>Id</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // StatechartSpecification
