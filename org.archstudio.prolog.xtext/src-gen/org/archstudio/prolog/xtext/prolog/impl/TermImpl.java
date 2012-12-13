/**
 */
package org.archstudio.prolog.xtext.prolog.impl;

import java.util.Collection;

import org.archstudio.prolog.xtext.prolog.Expression;
import org.archstudio.prolog.xtext.prolog.PrologPackage;
import org.archstudio.prolog.xtext.prolog.Term;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Term</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.TermImpl#getAtom <em>Atom</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.TermImpl#getNumber <em>Number</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.TermImpl#getString <em>String</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.TermImpl#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.TermImpl#isList <em>List</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.TermImpl#getHead <em>Head</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.TermImpl#getTail <em>Tail</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TermImpl extends MinimalEObjectImpl.Container implements Term {
	/**
	 * The default value of the '{@link #getAtom() <em>Atom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAtom()
	 * @generated
	 * @ordered
	 */
	protected static final String ATOM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAtom() <em>Atom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAtom()
	 * @generated
	 * @ordered
	 */
	protected String atom = ATOM_EDEFAULT;

	/**
	 * The default value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected static final String NUMBER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected String number = NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getString() <em>String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getString()
	 * @generated
	 * @ordered
	 */
	protected static final String STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getString() <em>String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getString()
	 * @generated
	 * @ordered
	 */
	protected String string = STRING_EDEFAULT;

	/**
	 * The default value of the '{@link #getVariable() <em>Variable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariable()
	 * @generated
	 * @ordered
	 */
	protected static final String VARIABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVariable() <em>Variable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariable()
	 * @generated
	 * @ordered
	 */
	protected String variable = VARIABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isList() <em>List</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isList()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LIST_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isList() <em>List</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isList()
	 * @generated
	 * @ordered
	 */
	protected boolean list = LIST_EDEFAULT;

	/**
	 * The cached value of the '{@link #getHead() <em>Head</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHead()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> head;

	/**
	 * The cached value of the '{@link #getTail() <em>Tail</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTail()
	 * @generated
	 * @ordered
	 */
	protected Expression tail;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TermImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PrologPackage.Literals.TERM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAtom() {
		return atom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAtom(String newAtom) {
		String oldAtom = atom;
		atom = newAtom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.TERM__ATOM, oldAtom, atom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumber(String newNumber) {
		String oldNumber = number;
		number = newNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.TERM__NUMBER, oldNumber, number));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getString() {
		return string;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setString(String newString) {
		String oldString = string;
		string = newString;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.TERM__STRING, oldString, string));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVariable() {
		return variable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariable(String newVariable) {
		String oldVariable = variable;
		variable = newVariable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.TERM__VARIABLE, oldVariable, variable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isList() {
		return list;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setList(boolean newList) {
		boolean oldList = list;
		list = newList;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.TERM__LIST, oldList, list));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getHead() {
		if (head == null) {
			head = new EObjectContainmentEList<Expression>(Expression.class, this, PrologPackage.TERM__HEAD);
		}
		return head;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getTail() {
		return tail;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTail(Expression newTail, NotificationChain msgs) {
		Expression oldTail = tail;
		tail = newTail;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PrologPackage.TERM__TAIL, oldTail, newTail);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTail(Expression newTail) {
		if (newTail != tail) {
			NotificationChain msgs = null;
			if (tail != null)
				msgs = ((InternalEObject)tail).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PrologPackage.TERM__TAIL, null, msgs);
			if (newTail != null)
				msgs = ((InternalEObject)newTail).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PrologPackage.TERM__TAIL, null, msgs);
			msgs = basicSetTail(newTail, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.TERM__TAIL, newTail, newTail));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PrologPackage.TERM__HEAD:
				return ((InternalEList<?>)getHead()).basicRemove(otherEnd, msgs);
			case PrologPackage.TERM__TAIL:
				return basicSetTail(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PrologPackage.TERM__ATOM:
				return getAtom();
			case PrologPackage.TERM__NUMBER:
				return getNumber();
			case PrologPackage.TERM__STRING:
				return getString();
			case PrologPackage.TERM__VARIABLE:
				return getVariable();
			case PrologPackage.TERM__LIST:
				return isList();
			case PrologPackage.TERM__HEAD:
				return getHead();
			case PrologPackage.TERM__TAIL:
				return getTail();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PrologPackage.TERM__ATOM:
				setAtom((String)newValue);
				return;
			case PrologPackage.TERM__NUMBER:
				setNumber((String)newValue);
				return;
			case PrologPackage.TERM__STRING:
				setString((String)newValue);
				return;
			case PrologPackage.TERM__VARIABLE:
				setVariable((String)newValue);
				return;
			case PrologPackage.TERM__LIST:
				setList((Boolean)newValue);
				return;
			case PrologPackage.TERM__HEAD:
				getHead().clear();
				getHead().addAll((Collection<? extends Expression>)newValue);
				return;
			case PrologPackage.TERM__TAIL:
				setTail((Expression)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PrologPackage.TERM__ATOM:
				setAtom(ATOM_EDEFAULT);
				return;
			case PrologPackage.TERM__NUMBER:
				setNumber(NUMBER_EDEFAULT);
				return;
			case PrologPackage.TERM__STRING:
				setString(STRING_EDEFAULT);
				return;
			case PrologPackage.TERM__VARIABLE:
				setVariable(VARIABLE_EDEFAULT);
				return;
			case PrologPackage.TERM__LIST:
				setList(LIST_EDEFAULT);
				return;
			case PrologPackage.TERM__HEAD:
				getHead().clear();
				return;
			case PrologPackage.TERM__TAIL:
				setTail((Expression)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PrologPackage.TERM__ATOM:
				return ATOM_EDEFAULT == null ? atom != null : !ATOM_EDEFAULT.equals(atom);
			case PrologPackage.TERM__NUMBER:
				return NUMBER_EDEFAULT == null ? number != null : !NUMBER_EDEFAULT.equals(number);
			case PrologPackage.TERM__STRING:
				return STRING_EDEFAULT == null ? string != null : !STRING_EDEFAULT.equals(string);
			case PrologPackage.TERM__VARIABLE:
				return VARIABLE_EDEFAULT == null ? variable != null : !VARIABLE_EDEFAULT.equals(variable);
			case PrologPackage.TERM__LIST:
				return list != LIST_EDEFAULT;
			case PrologPackage.TERM__HEAD:
				return head != null && !head.isEmpty();
			case PrologPackage.TERM__TAIL:
				return tail != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (atom: ");
		result.append(atom);
		result.append(", number: ");
		result.append(number);
		result.append(", string: ");
		result.append(string);
		result.append(", variable: ");
		result.append(variable);
		result.append(", list: ");
		result.append(list);
		result.append(')');
		return result.toString();
	}

} //TermImpl
