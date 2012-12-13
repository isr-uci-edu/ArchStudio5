/**
 */
package org.archstudio.prolog.xtext.prolog;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.Expression#getOps <em>Ops</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.Expression#getExps <em>Exps</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.Expression#getTerm <em>Term</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getExpression()
 * @model
 * @generated
 */
public interface Expression extends EObject {
	/**
	 * Returns the value of the '<em><b>Ops</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ops</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ops</em>' attribute list.
	 * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getExpression_Ops()
	 * @model unique="false"
	 * @generated
	 */
	EList<String> getOps();

	/**
	 * Returns the value of the '<em><b>Exps</b></em>' containment reference list.
	 * The list contents are of type {@link org.archstudio.prolog.xtext.prolog.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exps</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exps</em>' containment reference list.
	 * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getExpression_Exps()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getExps();

	/**
	 * Returns the value of the '<em><b>Term</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Term</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Term</em>' containment reference.
	 * @see #setTerm(Term)
	 * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getExpression_Term()
	 * @model containment="true"
	 * @generated
	 */
	Term getTerm();

	/**
	 * Sets the value of the '{@link org.archstudio.prolog.xtext.prolog.Expression#getTerm <em>Term</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Term</em>' containment reference.
	 * @see #getTerm()
	 * @generated
	 */
	void setTerm(Term value);

} // Expression
