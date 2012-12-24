/**
 */
package org.archstudio.prolog.xtext.prolog.impl;

import java.util.Collection;

import org.archstudio.prolog.xtext.prolog.Expression;
import org.archstudio.prolog.xtext.prolog.PrologPackage;
import org.archstudio.prolog.xtext.prolog.Query;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Query</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.archstudio.prolog.xtext.prolog.impl.QueryImpl#getPredicates
 * <em>Predicates</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class QueryImpl extends MinimalEObjectImpl.Container implements Query {
	/**
	 * The cached value of the '{@link #getPredicates() <em>Predicates</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPredicates()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> predicates;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected QueryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PrologPackage.Literals.QUERY;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Expression> getPredicates() {
		if (predicates == null) {
			predicates = new EObjectContainmentEList<Expression>(Expression.class, this,
					PrologPackage.QUERY__PREDICATES);
		}
		return predicates;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case PrologPackage.QUERY__PREDICATES:
			return ((InternalEList<?>) getPredicates()).basicRemove(otherEnd, msgs);
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
		case PrologPackage.QUERY__PREDICATES:
			return getPredicates();
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
		case PrologPackage.QUERY__PREDICATES:
			getPredicates().clear();
			getPredicates().addAll((Collection<? extends Expression>) newValue);
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
		case PrologPackage.QUERY__PREDICATES:
			getPredicates().clear();
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
		case PrologPackage.QUERY__PREDICATES:
			return predicates != null && !predicates.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //QueryImpl
