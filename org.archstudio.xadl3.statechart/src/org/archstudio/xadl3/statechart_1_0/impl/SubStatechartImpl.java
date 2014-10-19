/**
 */
package org.archstudio.xadl3.statechart_1_0.impl;

import org.archstudio.xadl3.statechart_1_0.Statechart;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xadl3.statechart_1_0.SubStatechart;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Sub Statechart</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.impl.SubStatechartImpl#getInnerStatechart <em>Inner Statechart</em>}</li>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.impl.SubStatechartImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SubStatechartImpl extends MinimalEObjectImpl.Container implements SubStatechart {
	/**
	 * The cached value of the '{@link #getInnerStatechart() <em>Inner Statechart</em>}' reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getInnerStatechart()
	 * @generated
	 * @ordered
	 */
	protected Statechart innerStatechart;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected SubStatechartImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Statechart_1_0Package.Literals.SUB_STATECHART;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Statechart getInnerStatechart() {
		return innerStatechart;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setInnerStatechart(Statechart newInnerStatechart) {
		Statechart oldInnerStatechart = innerStatechart;
		innerStatechart = newInnerStatechart;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					Statechart_1_0Package.SUB_STATECHART__INNER_STATECHART, oldInnerStatechart, innerStatechart));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, Statechart_1_0Package.SUB_STATECHART__ID, oldId, id));
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
		case Statechart_1_0Package.SUB_STATECHART__INNER_STATECHART:
			return getInnerStatechart();
		case Statechart_1_0Package.SUB_STATECHART__ID:
			return getId();
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
		case Statechart_1_0Package.SUB_STATECHART__INNER_STATECHART:
			setInnerStatechart((Statechart) newValue);
			return;
		case Statechart_1_0Package.SUB_STATECHART__ID:
			setId((String) newValue);
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
		case Statechart_1_0Package.SUB_STATECHART__INNER_STATECHART:
			setInnerStatechart((Statechart) null);
			return;
		case Statechart_1_0Package.SUB_STATECHART__ID:
			setId(ID_EDEFAULT);
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
		case Statechart_1_0Package.SUB_STATECHART__INNER_STATECHART:
			return innerStatechart != null;
		case Statechart_1_0Package.SUB_STATECHART__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
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
		result.append(" (id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //SubStatechartImpl
