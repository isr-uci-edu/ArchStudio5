/**
 */
package net.gexf_1_2.gexf.impl;

import net.gexf_1_2.gexf.GexfPackage;
import net.gexf_1_2.gexf.ParentType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Parent Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.impl.ParentTypeImpl#getFor <em>For</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParentTypeImpl extends MinimalEObjectImpl.Container implements ParentType {
	/**
	 * The default value of the '{@link #getFor() <em>For</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getFor()
	 * @generated
	 * @ordered
	 */
	protected static final Object FOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFor() <em>For</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFor()
	 * @generated
	 * @ordered
	 */
	protected Object for_ = FOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ParentTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GexfPackage.Literals.PARENT_TYPE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getFor() {
		return for_;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setFor(Object newFor) {
		Object oldFor = for_;
		for_ = newFor;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.PARENT_TYPE__FOR, oldFor, for_));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GexfPackage.PARENT_TYPE__FOR:
			return getFor();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case GexfPackage.PARENT_TYPE__FOR:
			setFor(newValue);
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
		case GexfPackage.PARENT_TYPE__FOR:
			setFor(FOR_EDEFAULT);
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
		case GexfPackage.PARENT_TYPE__FOR:
			return FOR_EDEFAULT == null ? for_ != null : !FOR_EDEFAULT.equals(for_);
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
		result.append(" (for: ");
		result.append(for_);
		result.append(')');
		return result.toString();
	}

} //ParentTypeImpl
