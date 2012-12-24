/**
 */
package org.archstudio.xadl3.javaimplementation_3_0;

import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Class Path Entry</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc -->
 * 
 * This type describes an entry on a classpath.
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry#getExt
 * <em>Ext</em>}</li>
 * <li>
 * {@link org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry#getEntry
 * <em>Entry</em>}</li>
 * <li>{@link org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry#getId
 * <em>Id</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package#getClassPathEntry()
 * @model extendedMetaData="name='ClassPathEntry' kind='elementOnly'"
 * @generated
 */
public interface ClassPathEntry extends EObject {
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
	 * @see org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package#getClassPathEntry_Ext()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='ext' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Extension> getExt();

	/**
	 * Returns the value of the '<em><b>Entry</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entry</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Entry</em>' attribute.
	 * @see #setEntry(String)
	 * @see org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package#getClassPathEntry_Entry()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData=
	 *        "kind='attribute' name='entry' namespace='##targetNamespace'"
	 * @generated
	 */
	String getEntry();

	/**
	 * Sets the value of the '
	 * {@link org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry#getEntry
	 * <em>Entry</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Entry</em>' attribute.
	 * @see #getEntry()
	 * @generated
	 */
	void setEntry(String value);

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
	 * @see org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package#getClassPathEntry_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData
	 *        ="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '
	 * {@link org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry#getId
	 * <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // ClassPathEntry
