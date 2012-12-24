/**
 */
package org.archstudio.xadl3.variability_3_0;

import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Change Set</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.variability_3_0.ChangeSet#getExt <em>Ext
 * </em>}</li>
 * <li>{@link org.archstudio.xadl3.variability_3_0.ChangeSet#getId <em>Id</em>}</li>
 * <li>{@link org.archstudio.xadl3.variability_3_0.ChangeSet#getName <em>Name
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#getChangeSet()
 * @model abstract="true" extendedMetaData="name='ChangeSet' kind='elementOnly'"
 * @generated
 */
public interface ChangeSet extends EObject {
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
	 * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#getChangeSet_Ext()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='ext' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Extension> getExt();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#getChangeSet_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData
	 *        ="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '
	 * {@link org.archstudio.xadl3.variability_3_0.ChangeSet#getId <em>Id</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#getChangeSet_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData=
	 *        "kind='attribute' name='name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '
	 * {@link org.archstudio.xadl3.variability_3_0.ChangeSet#getName
	 * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // ChangeSet
