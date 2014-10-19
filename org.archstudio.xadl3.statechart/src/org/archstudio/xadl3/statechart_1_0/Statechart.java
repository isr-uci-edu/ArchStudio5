/**
 */
package org.archstudio.xadl3.statechart_1_0;

import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Statechart</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.Statechart#getState <em>State</em>}</li>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.Statechart#getTransition <em>Transition</em>}</li>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.Statechart#getExt <em>Ext</em>}</li>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.Statechart#getId <em>Id</em>}</li>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.Statechart#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getStatechart()
 * @model extendedMetaData="name='Statechart' kind='elementOnly'"
 * @generated
 */
public interface Statechart extends EObject {
	/**
	 * Returns the value of the '<em><b>State</b></em>' containment reference list. The list contents are of type
	 * {@link org.archstudio.xadl3.statechart_1_0.PseudoState}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>State</em>' containment reference list.
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getStatechart_State()
	 * @model containment="true" extendedMetaData="kind='element' name='state' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<PseudoState> getState();

	/**
	 * Returns the value of the '<em><b>Transition</b></em>' containment reference list. The list contents are of type
	 * {@link org.archstudio.xadl3.statechart_1_0.Transition}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transition</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Transition</em>' containment reference list.
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getStatechart_Transition()
	 * @model containment="true" extendedMetaData="kind='element' name='transition' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Transition> getTransition();

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
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getStatechart_Ext()
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
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getStatechart_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.statechart_1_0.Statechart#getId <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package#getStatechart_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.statechart_1_0.Statechart#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Statechart
