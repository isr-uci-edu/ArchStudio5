/**
 */
package org.archstudio.prolog.xtext.prolog;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Single Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.SingleClause#getPredicates <em>Predicates</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getSingleClause()
 * @model
 * @generated
 */
public interface SingleClause extends EObject
{
  /**
   * Returns the value of the '<em><b>Predicates</b></em>' containment reference list.
   * The list contents are of type {@link org.archstudio.prolog.xtext.prolog.Predicate}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Predicates</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Predicates</em>' containment reference list.
   * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getSingleClause_Predicates()
   * @model containment="true"
   * @generated
   */
  EList<Predicate> getPredicates();

} // SingleClause
