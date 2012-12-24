/**
 */
package org.archstudio.xadl3.structure_3_0;

import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Component</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc -->
 * 
 * Components are the loci of computation in an architecture. They inherit all
 * the properties of a Brick: ID, name, interfaces, and optional substructure.
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.structure_3_0.Component#getExt <em>Ext</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getComponent()
 * @model extendedMetaData="name='Component' kind='elementOnly'"
 * @generated
 */
public interface Component extends Brick {
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
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getComponent_Ext()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='ext' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Extension> getExt();

} // Component
