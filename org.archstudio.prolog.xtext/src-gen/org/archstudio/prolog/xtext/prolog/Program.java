/**
 */
package org.archstudio.prolog.xtext.prolog;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Program</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.prolog.xtext.prolog.Program#getClauses <em>Clauses
 * </em>}</li>
 * <li>{@link org.archstudio.prolog.xtext.prolog.Program#getQuery <em>Query
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getProgram()
 * @model
 * @generated
 */
public interface Program extends EObject {
	/**
	 * Returns the value of the '<em><b>Clauses</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link org.archstudio.prolog.xtext.prolog.Clause}. <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Clauses</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Clauses</em>' containment reference list.
	 * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getProgram_Clauses()
	 * @model containment="true"
	 * @generated
	 */
	EList<Clause> getClauses();

	/**
	 * Returns the value of the '<em><b>Query</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Query</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Query</em>' containment reference.
	 * @see #setQuery(Query)
	 * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getProgram_Query()
	 * @model containment="true"
	 * @generated
	 */
	Query getQuery();

	/**
	 * Sets the value of the '
	 * {@link org.archstudio.prolog.xtext.prolog.Program#getQuery
	 * <em>Query</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Query</em>' containment reference.
	 * @see #getQuery()
	 * @generated
	 */
	void setQuery(Query value);

} // Program
