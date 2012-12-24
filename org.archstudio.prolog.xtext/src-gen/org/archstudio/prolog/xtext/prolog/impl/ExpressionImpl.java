/**
 */
package org.archstudio.prolog.xtext.prolog.impl;

import java.util.Collection;

import org.archstudio.prolog.xtext.prolog.Expression;
import org.archstudio.prolog.xtext.prolog.PrologPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Expression</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getOps <em>
 * Ops</em>}</li>
 * <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getExps
 * <em>Exps</em>}</li>
 * <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#isComplex
 * <em>Complex</em>}</li>
 * <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getNumber
 * <em>Number</em>}</li>
 * <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getString
 * <em>String</em>}</li>
 * <li>
 * {@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getVariable
 * <em>Variable</em>}</li>
 * <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#isList <em>
 * List</em>}</li>
 * <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getHead
 * <em>Head</em>}</li>
 * <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getTail
 * <em>Tail</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ExpressionImpl extends MinimalEObjectImpl.Container implements Expression {
	/**
	 * The cached value of the '{@link #getOps() <em>Ops</em>}' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getOps()
	 * @generated
	 * @ordered
	 */
	protected EList<String> ops;

	/**
	 * The cached value of the '{@link #getExps() <em>Exps</em>}' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getExps()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> exps;

	/**
	 * The default value of the '{@link #isComplex() <em>Complex</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isComplex()
	 * @generated
	 * @ordered
	 */
	protected static final boolean COMPLEX_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isComplex() <em>Complex</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isComplex()
	 * @generated
	 * @ordered
	 */
	protected boolean complex = COMPLEX_EDEFAULT;

	/**
	 * The default value of the '{@link #getNumber() <em>Number</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected static final String NUMBER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected String number = NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getString() <em>String</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getString()
	 * @generated
	 * @ordered
	 */
	protected static final String STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getString() <em>String</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getString()
	 * @generated
	 * @ordered
	 */
	protected String string = STRING_EDEFAULT;

	/**
	 * The default value of the '{@link #getVariable() <em>Variable</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVariable()
	 * @generated
	 * @ordered
	 */
	protected static final String VARIABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVariable() <em>Variable</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVariable()
	 * @generated
	 * @ordered
	 */
	protected String variable = VARIABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isList() <em>List</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isList()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LIST_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isList() <em>List</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isList()
	 * @generated
	 * @ordered
	 */
	protected boolean list = LIST_EDEFAULT;

	/**
	 * The cached value of the '{@link #getHead() <em>Head</em>}' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getHead()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> head;

	/**
	 * The cached value of the '{@link #getTail() <em>Tail</em>}' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTail()
	 * @generated
	 * @ordered
	 */
	protected Expression tail;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PrologPackage.Literals.EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<String> getOps() {
		if (ops == null) {
			ops = new EDataTypeEList<String>(String.class, this, PrologPackage.EXPRESSION__OPS);
		}
		return ops;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Expression> getExps() {
		if (exps == null) {
			exps = new EObjectContainmentEList<Expression>(Expression.class, this, PrologPackage.EXPRESSION__EXPS);
		}
		return exps;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isComplex() {
		return complex;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setComplex(boolean newComplex) {
		boolean oldComplex = complex;
		complex = newComplex;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__COMPLEX, oldComplex,
					complex));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setNumber(String newNumber) {
		String oldNumber = number;
		number = newNumber;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__NUMBER, oldNumber, number));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getString() {
		return string;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setString(String newString) {
		String oldString = string;
		string = newString;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__STRING, oldString, string));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getVariable() {
		return variable;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setVariable(String newVariable) {
		String oldVariable = variable;
		variable = newVariable;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__VARIABLE, oldVariable,
					variable));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isList() {
		return list;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setList(boolean newList) {
		boolean oldList = list;
		list = newList;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__LIST, oldList, list));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Expression> getHead() {
		if (head == null) {
			head = new EObjectContainmentEList<Expression>(Expression.class, this, PrologPackage.EXPRESSION__HEAD);
		}
		return head;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Expression getTail() {
		return tail;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetTail(Expression newTail, NotificationChain msgs) {
		Expression oldTail = tail;
		tail = newTail;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PrologPackage.EXPRESSION__TAIL, oldTail, newTail);
			if (msgs == null) {
				msgs = notification;
			}
			else {
				msgs.add(notification);
			}
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setTail(Expression newTail) {
		if (newTail != tail) {
			NotificationChain msgs = null;
			if (tail != null) {
				msgs = ((InternalEObject) tail).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- PrologPackage.EXPRESSION__TAIL, null, msgs);
			}
			if (newTail != null) {
				msgs = ((InternalEObject) newTail).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PrologPackage.EXPRESSION__TAIL, null, msgs);
			}
			msgs = basicSetTail(newTail, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		}
		else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__TAIL, newTail, newTail));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case PrologPackage.EXPRESSION__EXPS:
			return ((InternalEList<?>) getExps()).basicRemove(otherEnd, msgs);
		case PrologPackage.EXPRESSION__HEAD:
			return ((InternalEList<?>) getHead()).basicRemove(otherEnd, msgs);
		case PrologPackage.EXPRESSION__TAIL:
			return basicSetTail(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case PrologPackage.EXPRESSION__OPS:
			return getOps();
		case PrologPackage.EXPRESSION__EXPS:
			return getExps();
		case PrologPackage.EXPRESSION__COMPLEX:
			return isComplex();
		case PrologPackage.EXPRESSION__NUMBER:
			return getNumber();
		case PrologPackage.EXPRESSION__STRING:
			return getString();
		case PrologPackage.EXPRESSION__VARIABLE:
			return getVariable();
		case PrologPackage.EXPRESSION__LIST:
			return isList();
		case PrologPackage.EXPRESSION__HEAD:
			return getHead();
		case PrologPackage.EXPRESSION__TAIL:
			return getTail();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case PrologPackage.EXPRESSION__OPS:
			getOps().clear();
			getOps().addAll((Collection<? extends String>) newValue);
			return;
		case PrologPackage.EXPRESSION__EXPS:
			getExps().clear();
			getExps().addAll((Collection<? extends Expression>) newValue);
			return;
		case PrologPackage.EXPRESSION__COMPLEX:
			setComplex((Boolean) newValue);
			return;
		case PrologPackage.EXPRESSION__NUMBER:
			setNumber((String) newValue);
			return;
		case PrologPackage.EXPRESSION__STRING:
			setString((String) newValue);
			return;
		case PrologPackage.EXPRESSION__VARIABLE:
			setVariable((String) newValue);
			return;
		case PrologPackage.EXPRESSION__LIST:
			setList((Boolean) newValue);
			return;
		case PrologPackage.EXPRESSION__HEAD:
			getHead().clear();
			getHead().addAll((Collection<? extends Expression>) newValue);
			return;
		case PrologPackage.EXPRESSION__TAIL:
			setTail((Expression) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case PrologPackage.EXPRESSION__OPS:
			getOps().clear();
			return;
		case PrologPackage.EXPRESSION__EXPS:
			getExps().clear();
			return;
		case PrologPackage.EXPRESSION__COMPLEX:
			setComplex(COMPLEX_EDEFAULT);
			return;
		case PrologPackage.EXPRESSION__NUMBER:
			setNumber(NUMBER_EDEFAULT);
			return;
		case PrologPackage.EXPRESSION__STRING:
			setString(STRING_EDEFAULT);
			return;
		case PrologPackage.EXPRESSION__VARIABLE:
			setVariable(VARIABLE_EDEFAULT);
			return;
		case PrologPackage.EXPRESSION__LIST:
			setList(LIST_EDEFAULT);
			return;
		case PrologPackage.EXPRESSION__HEAD:
			getHead().clear();
			return;
		case PrologPackage.EXPRESSION__TAIL:
			setTail((Expression) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case PrologPackage.EXPRESSION__OPS:
			return ops != null && !ops.isEmpty();
		case PrologPackage.EXPRESSION__EXPS:
			return exps != null && !exps.isEmpty();
		case PrologPackage.EXPRESSION__COMPLEX:
			return complex != COMPLEX_EDEFAULT;
		case PrologPackage.EXPRESSION__NUMBER:
			return NUMBER_EDEFAULT == null ? number != null : !NUMBER_EDEFAULT.equals(number);
		case PrologPackage.EXPRESSION__STRING:
			return STRING_EDEFAULT == null ? string != null : !STRING_EDEFAULT.equals(string);
		case PrologPackage.EXPRESSION__VARIABLE:
			return VARIABLE_EDEFAULT == null ? variable != null : !VARIABLE_EDEFAULT.equals(variable);
		case PrologPackage.EXPRESSION__LIST:
			return list != LIST_EDEFAULT;
		case PrologPackage.EXPRESSION__HEAD:
			return head != null && !head.isEmpty();
		case PrologPackage.EXPRESSION__TAIL:
			return tail != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (ops: ");
		result.append(ops);
		result.append(", complex: ");
		result.append(complex);
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

} //ExpressionImpl
