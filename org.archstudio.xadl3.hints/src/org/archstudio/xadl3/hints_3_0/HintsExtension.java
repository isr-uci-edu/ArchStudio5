/**
 */
package org.archstudio.xadl3.hints_3_0;

import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Hints Extension</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc -->
 * 
 * This type is an extension that can be added to elements to give them sets of
 * hints.
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.hints_3_0.HintsExtension#getHint <em>Hint
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.xadl3.hints_3_0.Hints_3_0Package#getHintsExtension()
 * @model extendedMetaData="name='HintsExtension' kind='elementOnly'"
 * @generated
 */
public interface HintsExtension extends Extension {
	/**
	 * Returns the value of the '<em><b>Hint</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link org.archstudio.xadl3.hints_3_0.Hint}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hint</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Hint</em>' containment reference list.
	 * @see org.archstudio.xadl3.hints_3_0.Hints_3_0Package#getHintsExtension_Hint()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='hint' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Hint> getHint();

} // HintsExtension
