/**
 */
package org.archstudio.prolog.xtext.prolog;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Term</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.Term#getAtom <em>Atom</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.Term#getNumber <em>Number</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.Term#getString <em>String</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.Term#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.Term#isList <em>List</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.Term#getHead <em>Head</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.Term#getTail <em>Tail</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getTerm()
 * @model
 * @generated
 */
public interface Term extends EObject {
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
	 * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getTerm_Atom()
	 * @model
	 * @generated
	 */
	String getAtom();

	/**
	 * Sets the value of the '{@link org.archstudio.prolog.xtext.prolog.Term#getAtom <em>Atom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Atom</em>' attribute.
	 * @see #getAtom()
	 * @generated
	 */
	void setAtom(String value);

	/**
	 * Returns the value of the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number</em>' attribute.
	 * @see #setNumber(String)
	 * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getTerm_Number()
	 * @model
	 * @generated
	 */
	String getNumber();

	/**
	 * Sets the value of the '{@link org.archstudio.prolog.xtext.prolog.Term#getNumber <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number</em>' attribute.
	 * @see #getNumber()
	 * @generated
	 */
	void setNumber(String value);

	/**
	 * Returns the value of the '<em><b>String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>String</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>String</em>' attribute.
	 * @see #setString(String)
	 * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getTerm_String()
	 * @model
	 * @generated
	 */
	String getString();

	/**
	 * Sets the value of the '{@link org.archstudio.prolog.xtext.prolog.Term#getString <em>String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>String</em>' attribute.
	 * @see #getString()
	 * @generated
	 */
	void setString(String value);

	/**
	 * Returns the value of the '<em><b>Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable</em>' attribute.
	 * @see #setVariable(String)
	 * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getTerm_Variable()
	 * @model
	 * @generated
	 */
	String getVariable();

	/**
	 * Sets the value of the '{@link org.archstudio.prolog.xtext.prolog.Term#getVariable <em>Variable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable</em>' attribute.
	 * @see #getVariable()
	 * @generated
	 */
	void setVariable(String value);

	/**
	 * Returns the value of the '<em><b>List</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>List</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>List</em>' attribute.
	 * @see #setList(boolean)
	 * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getTerm_List()
	 * @model
	 * @generated
	 */
	boolean isList();

	/**
	 * Sets the value of the '{@link org.archstudio.prolog.xtext.prolog.Term#isList <em>List</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>List</em>' attribute.
	 * @see #isList()
	 * @generated
	 */
	void setList(boolean value);

	/**
	 * Returns the value of the '<em><b>Head</b></em>' containment reference list.
	 * The list contents are of type {@link org.archstudio.prolog.xtext.prolog.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Head</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Head</em>' containment reference list.
	 * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getTerm_Head()
	 * @model containment="true"
	 * @generated
	 */
	EList<Expression> getHead();

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
	 * @see org.archstudio.prolog.xtext.prolog.PrologPackage#getTerm_Tail()
	 * @model containment="true"
	 * @generated
	 */
	Expression getTail();

	/**
	 * Sets the value of the '{@link org.archstudio.prolog.xtext.prolog.Term#getTail <em>Tail</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tail</em>' containment reference.
	 * @see #getTail()
	 * @generated
	 */
	void setTail(Expression value);

} // Term
