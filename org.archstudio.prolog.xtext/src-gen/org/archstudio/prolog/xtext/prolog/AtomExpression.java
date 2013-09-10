/**
 */
package org.archstudio.prolog.xtext.prolog;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Atom Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.AtomExpression#getAtom <em>Atom</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.AtomExpression#getTerms <em>Terms</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getAtomExpression()
 * @model
 * @generated
 */
public interface AtomExpression extends Expression
{
  /**
   * Returns the value of the '<em><b>Atom</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Atom</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Atom</em>' attribute.
   * @see #setAtom(String)
   * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getAtomExpression_Atom()
   * @model
   * @generated
   */
  String getAtom();

  /**
   * Sets the value of the '{@link org.archstudio.prolog.xtext.prolog.AtomExpression#getAtom <em>Atom</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Atom</em>' attribute.
   * @see #getAtom()
   * @generated
   */
  void setAtom(String value);

  /**
   * Returns the value of the '<em><b>Terms</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Terms</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Terms</em>' containment reference.
   * @see #setTerms(Expression)
   * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getAtomExpression_Terms()
   * @model containment="true"
   * @generated
   */
  Expression getTerms();

  /**
   * Sets the value of the '{@link org.archstudio.prolog.xtext.prolog.AtomExpression#getTerms <em>Terms</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Terms</em>' containment reference.
   * @see #getTerms()
   * @generated
   */
  void setTerms(Expression value);

} // AtomExpression
