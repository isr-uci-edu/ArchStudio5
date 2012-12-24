/**
 */
package org.archstudio.xadl3.hints_3_0.impl;

import java.util.Collection;

import org.archstudio.xadl3.hints_3_0.Hint;
import org.archstudio.xadl3.hints_3_0.HintsExtension;
import org.archstudio.xadl3.hints_3_0.Hints_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.impl.ExtensionImpl;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Hints Extension</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.archstudio.xadl3.hints_3_0.impl.HintsExtensionImpl#getHint
 * <em>Hint</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class HintsExtensionImpl extends ExtensionImpl implements HintsExtension {
	/**
	 * The cached value of the '{@link #getHint() <em>Hint</em>}' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getHint()
	 * @generated
	 * @ordered
	 */
	protected EList<Hint> hint;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected HintsExtensionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	protected EClass eStaticClass() {
		return Hints_3_0Package.Literals.HINTS_EXTENSION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Hint> getHint() {
		if (hint == null) {
			hint = new EObjectContainmentEList<Hint>(Hint.class, this, Hints_3_0Package.HINTS_EXTENSION__HINT);
		}
		return hint;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case Hints_3_0Package.HINTS_EXTENSION__HINT:
			return ((InternalEList<?>) getHint()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case Hints_3_0Package.HINTS_EXTENSION__HINT:
			return getHint();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case Hints_3_0Package.HINTS_EXTENSION__HINT:
			getHint().clear();
			getHint().addAll((Collection<? extends Hint>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void eUnset(int featureID) {
		switch (featureID) {
		case Hints_3_0Package.HINTS_EXTENSION__HINT:
			getHint().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case Hints_3_0Package.HINTS_EXTENSION__HINT:
			return hint != null && !hint.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //HintsExtensionImpl
