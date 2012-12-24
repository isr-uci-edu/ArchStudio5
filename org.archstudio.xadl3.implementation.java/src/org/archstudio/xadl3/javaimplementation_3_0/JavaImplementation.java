/**
 */
package org.archstudio.xadl3.javaimplementation_3_0;

import org.archstudio.xadl3.implementation_3_0.Implementation;
import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Java Implementation</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc -->
 * 
 * This type defines a Java implementation.
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation#getMainClass
 * <em>Main Class</em>}</li>
 * <li>
 * {@link org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation#getAuxClass
 * <em>Aux Class</em>}</li>
 * <li>
 * {@link org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation#getExt
 * <em>Ext</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package#getJavaImplementation()
 * @model extendedMetaData="name='JavaImplementation' kind='elementOnly'"
 * @generated
 */
public interface JavaImplementation extends Implementation {
	/**
	 * Returns the value of the '<em><b>Main Class</b></em>' containment
	 * reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Main Class</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Main Class</em>' containment reference.
	 * @see #setMainClass(JavaClass)
	 * @see org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package#getJavaImplementation_MainClass()
	 * @model containment="true" required="true" extendedMetaData=
	 *        "kind='element' name='mainClass' namespace='##targetNamespace'"
	 * @generated
	 */
	JavaClass getMainClass();

	/**
	 * Sets the value of the '
	 * {@link org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation#getMainClass
	 * <em>Main Class</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Main Class</em>' containment
	 *            reference.
	 * @see #getMainClass()
	 * @generated
	 */
	void setMainClass(JavaClass value);

	/**
	 * Returns the value of the '<em><b>Aux Class</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link org.archstudio.xadl3.javaimplementation_3_0.JavaClass}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aux Class</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Aux Class</em>' containment reference list.
	 * @see org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package#getJavaImplementation_AuxClass()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='auxClass' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<JavaClass> getAuxClass();

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
	 * @see org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package#getJavaImplementation_Ext()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='ext' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Extension> getExt();

} // JavaImplementation
