/**
 */
package org.archstudio.xadl3.implementation_3_0;

import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Implementation Extension</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * This type is an extension that can be added to elements to give them implementations.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.implementation_3_0.ImplementationExtension#getImplementation <em>Implementation</em>}
 * </li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package#getImplementationExtension()
 * @model extendedMetaData="name='ImplementationExtension' kind='elementOnly'"
 * @generated
 */
public interface ImplementationExtension extends Extension {
	/**
	 * Returns the value of the '<em><b>Implementation</b></em>' containment reference list. The list contents are of
	 * type {@link org.archstudio.xadl3.implementation_3_0.Implementation}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementation</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Implementation</em>' containment reference list.
	 * @see org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package#getImplementationExtension_Implementation()
	 * @model containment="true" extendedMetaData="kind='element' name='implementation' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Implementation> getImplementation();

} // ImplementationExtension
