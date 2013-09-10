/**
 */
package org.archstudio.prolog.xtext.prolog;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.ListExpression#getHead <em>Head</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.ListExpression#getTail <em>Tail</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getListExpression()
 * @model
 * @generated
 */
public interface ListExpression extends Expression
{
  /**
   * Returns the value of the '<em><b>Head</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Head</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Head</em>' containment reference.
   * @see #setHead(Expression)
   * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getListExpression_Head()
   * @model containment="true"
   * @generated
   */
  Expression getHead();

  /**
   * Sets the value of the '{@link org.archstudio.prolog.xtext.prolog.ListExpression#getHead <em>Head</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Head</em>' containment reference.
   * @see #getHead()
   * @generated
   */
  void setHead(Expression value);

  /**
   * Returns the value of the '<em><b>Tail</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tail</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tail</em>' containment reference.
   * @see #setTail(Expression)
   * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getListExpression_Tail()
   * @model containment="true"
   * @generated
   */
  Expression getTail();

  /**
   * Sets the value of the '{@link org.archstudio.prolog.xtext.prolog.ListExpression#getTail <em>Tail</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tail</em>' containment reference.
   * @see #getTail()
   * @generated
   */
  void setTail(Expression value);

} // ListExpression
