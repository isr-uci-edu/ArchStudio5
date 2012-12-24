/**
 */
package org.archstudio.xadl3.archlight_3_0;

import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Archlight</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc -->
 * 
 * TODO: @@
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.archlight_3_0.Archlight#getTest <em>Test
 * </em>}</li>
 * <li>{@link org.archstudio.xadl3.archlight_3_0.Archlight#getExt <em>Ext</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.xadl3.archlight_3_0.Archlight_3_0Package#getArchlight()
 * @model extendedMetaData="name='Archlight' kind='elementOnly'"
 * @generated
 */
public interface Archlight extends EObject {
	/**
	 * Returns the value of the '<em><b>Test</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link org.archstudio.xadl3.archlight_3_0.Test}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Test</em>' containment reference list.
	 * @see org.archstudio.xadl3.archlight_3_0.Archlight_3_0Package#getArchlight_Test()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='test' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Test> getTest();

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
	 * @see org.archstudio.xadl3.archlight_3_0.Archlight_3_0Package#getArchlight_Ext()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='ext' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Extension> getExt();

} // Archlight
