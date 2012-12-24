/**
 */
package org.archstudio.xadl3.javaimplementation_3_0;

import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Java Class</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc -->
 * 
 * This type describes a Java class for an implementation.
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.archstudio.xadl3.javaimplementation_3_0.JavaClass#getClassPathEntry
 * <em>Class Path Entry</em>}</li>
 * <li>{@link org.archstudio.xadl3.javaimplementation_3_0.JavaClass#getExt <em>
 * Ext</em>}</li>
 * <li>
 * {@link org.archstudio.xadl3.javaimplementation_3_0.JavaClass#getClassName
 * <em>Class Name</em>}</li>
 * <li>{@link org.archstudio.xadl3.javaimplementation_3_0.JavaClass#getId <em>Id
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package#getJavaClass()
 * @model extendedMetaData="name='JavaClass' kind='elementOnly'"
 * @generated
 */
public interface JavaClass extends EObject {
	/**
	 * Returns the value of the '<em><b>Class Path Entry</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Path Entry</em>' containment reference
	 * list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Class Path Entry</em>' containment
	 *         reference list.
	 * @see org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package#getJavaClass_ClassPathEntry()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='classPathEntry' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ClassPathEntry> getClassPathEntry();

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
	 * @see org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package#getJavaClass_Ext()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='ext' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Extension> getExt();

	/**
	 * Returns the value of the '<em><b>Class Name</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Name</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Class Name</em>' attribute.
	 * @see #setClassName(String)
	 * @see org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package#getJavaClass_ClassName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData=
	 *        "kind='attribute' name='className' namespace='##targetNamespace'"
	 * @generated
	 */
	String getClassName();

	/**
	 * Sets the value of the '
	 * {@link org.archstudio.xadl3.javaimplementation_3_0.JavaClass#getClassName
	 * <em>Class Name</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Class Name</em>' attribute.
	 * @see #getClassName()
	 * @generated
	 */
	void setClassName(String value);

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
	 * @see org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package#getJavaClass_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData
	 *        ="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '
	 * {@link org.archstudio.xadl3.javaimplementation_3_0.JavaClass#getId
	 * <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // JavaClass
