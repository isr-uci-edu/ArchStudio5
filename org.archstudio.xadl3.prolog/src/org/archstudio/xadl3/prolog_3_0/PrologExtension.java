/**
 */
package org.archstudio.xadl3.prolog_3_0;

import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Prolog Extension</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.prolog_3_0.PrologExtension#getStatement <em>Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.xadl3.prolog_3_0.Prolog_3_0Package#getPrologExtension()
 * @model extendedMetaData="name='PrologExtension' kind='elementOnly'"
 * @generated
 */
public interface PrologExtension extends Extension {
	/**
	 * Returns the value of the '<em><b>Statement</b></em>' containment reference list. The list contents are of type
	 * {@link org.archstudio.xadl3.prolog_3_0.Statement}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Statement</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Statement</em>' containment reference list.
	 * @see org.archstudio.xadl3.prolog_3_0.Prolog_3_0Package#getPrologExtension_Statement()
	 * @model containment="true" extendedMetaData="kind='element' name='statement' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Statement> getStatement();

} // PrologExtension
