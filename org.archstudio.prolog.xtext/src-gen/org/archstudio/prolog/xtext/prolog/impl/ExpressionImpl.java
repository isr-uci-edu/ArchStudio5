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

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getOps <em>Ops</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getExps <em>Exps</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getTerm <em>Term</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExpressionImpl extends MinimalEObjectImpl.Container implements Expression {
	/**
	 * The cached value of the '{@link #getOps() <em>Ops</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOps()
	 * @generated
	 * @ordered
	 */
	protected EList<String> ops;

	/**
	 * The cached value of the '{@link #getExps() <em>Exps</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExps()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> exps;

	/**
	 * The cached value of the '{@link #getTerm() <em>Term</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTerm()
	 * @generated
	 * @ordered
	 */
	protected Term term;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PrologPackage.Literals.EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getOps() {
		if (ops == null) {
			ops = new EDataTypeEList<String>(String.class, this, PrologPackage.EXPRESSION__OPS);
		}
		return ops;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getExps() {
		if (exps == null) {
			exps = new EObjectContainmentEList<Expression>(Expression.class, this, PrologPackage.EXPRESSION__EXPS);
		}
		return exps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Term getTerm() {
		return term;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTerm(Term newTerm, NotificationChain msgs) {
		Term oldTerm = term;
		term = newTerm;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__TERM, oldTerm, newTerm);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTerm(Term newTerm) {
		if (newTerm != term) {
			NotificationChain msgs = null;
			if (term != null)
				msgs = ((InternalEObject)term).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PrologPackage.EXPRESSION__TERM, null, msgs);
			if (newTerm != null)
				msgs = ((InternalEObject)newTerm).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PrologPackage.EXPRESSION__TERM, null, msgs);
			msgs = basicSetTerm(newTerm, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__TERM, newTerm, newTerm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PrologPackage.EXPRESSION__EXPS:
				return ((InternalEList<?>)getExps()).basicRemove(otherEnd, msgs);
			case PrologPackage.EXPRESSION__TERM:
				return basicSetTerm(null, msgs);
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
			case PrologPackage.EXPRESSION__OPS:
				return getOps();
			case PrologPackage.EXPRESSION__EXPS:
				return getExps();
			case PrologPackage.EXPRESSION__TERM:
				return getTerm();
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
			case PrologPackage.EXPRESSION__OPS:
				getOps().clear();
				getOps().addAll((Collection<? extends String>)newValue);
				return;
			case PrologPackage.EXPRESSION__EXPS:
				getExps().clear();
				getExps().addAll((Collection<? extends Expression>)newValue);
				return;
			case PrologPackage.EXPRESSION__TERM:
				setTerm((Term)newValue);
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
			case PrologPackage.EXPRESSION__OPS:
				getOps().clear();
				return;
			case PrologPackage.EXPRESSION__EXPS:
				getExps().clear();
				return;
			case PrologPackage.EXPRESSION__TERM:
				setTerm((Term)null);
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
			case PrologPackage.EXPRESSION__OPS:
				return ops != null && !ops.isEmpty();
			case PrologPackage.EXPRESSION__EXPS:
				return exps != null && !exps.isEmpty();
			case PrologPackage.EXPRESSION__TERM:
				return term != null;
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
		result.append(" (ops: ");
		result.append(ops);
		result.append(')');
		return result.toString();
	}

} //ExpressionImpl
