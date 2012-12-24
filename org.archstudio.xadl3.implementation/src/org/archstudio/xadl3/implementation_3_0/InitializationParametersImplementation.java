/**
 */
package org.archstudio.xadl3.implementation_3_0;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Initialization Parameters Implementation</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <!-- begin-model-doc -->
 * 
 * This type describes initialization parameters used during initialization.
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.archstudio.xadl3.implementation_3_0.InitializationParametersImplementation#getInitializationParameter
 * <em>Initialization Parameter</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package#getInitializationParametersImplementation()
 * @model extendedMetaData=
 *        "name='InitializationParametersImplementation' kind='elementOnly'"
 * @generated
 */
public interface InitializationParametersImplementation extends Implementation {
	/**
	 * Returns the value of the '<em><b>Initialization Parameter</b></em>'
	 * containment reference list. The list contents are of type
	 * {@link org.archstudio.xadl3.implementation_3_0.InitializationParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initialization Parameter</em>' containment
	 * reference list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Initialization Parameter</em>' containment
	 *         reference list.
	 * @see org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package#getInitializationParametersImplementation_InitializationParameter()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='initializationParameter' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<InitializationParameter> getInitializationParameter();

} // InitializationParametersImplementation
