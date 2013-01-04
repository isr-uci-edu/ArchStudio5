/**
 */
package org.archstudio.xadl3.variability_3_0;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Element Many Change</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.variability_3_0.ElementManyChange#getChange <em>Change</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#getElementManyChange()
 * @model extendedMetaData="name='ElementManyChange' kind='elementOnly'"
 * @generated
 */
public interface ElementManyChange extends Change {
	/**
	 * Returns the value of the '<em><b>Change</b></em>' containment reference list. The list contents are of type
	 * {@link org.archstudio.xadl3.variability_3_0.ElementChange}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Change</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Change</em>' containment reference list.
	 * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#getElementManyChange_Change()
	 * @model containment="true" extendedMetaData="kind='element' name='change' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ElementChange> getChange();

} // ElementManyChange
