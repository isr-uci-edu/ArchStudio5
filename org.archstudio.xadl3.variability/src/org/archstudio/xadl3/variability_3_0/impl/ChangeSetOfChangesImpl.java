/**
 */
package org.archstudio.xadl3.variability_3_0.impl;

import org.archstudio.xadl3.variability_3_0.ChangeSetOfChanges;
import org.archstudio.xadl3.variability_3_0.ElementChange;
import org.archstudio.xadl3.variability_3_0.Variability_3_0Package;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Change Set Of Changes</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.archstudio.xadl3.variability_3_0.impl.ChangeSetOfChangesImpl#getElementChange <em>Element Change</em>}
 * </li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ChangeSetOfChangesImpl extends ChangeSetImpl implements ChangeSetOfChanges {
	/**
	 * The cached value of the '{@link #getElementChange() <em>Element Change</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getElementChange()
	 * @generated
	 * @ordered
	 */
	protected ElementChange elementChange;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ChangeSetOfChangesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Variability_3_0Package.Literals.CHANGE_SET_OF_CHANGES;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ElementChange getElementChange() {
		return elementChange;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetElementChange(ElementChange newElementChange, NotificationChain msgs) {
		ElementChange oldElementChange = elementChange;
		elementChange = newElementChange;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					Variability_3_0Package.CHANGE_SET_OF_CHANGES__ELEMENT_CHANGE, oldElementChange, newElementChange);
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
	@Override
	public void setElementChange(ElementChange newElementChange) {
		if (newElementChange != elementChange) {
			NotificationChain msgs = null;
			if (elementChange != null) {
				msgs = ((InternalEObject) elementChange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- Variability_3_0Package.CHANGE_SET_OF_CHANGES__ELEMENT_CHANGE, null, msgs);
			}
			if (newElementChange != null) {
				msgs = ((InternalEObject) newElementChange).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- Variability_3_0Package.CHANGE_SET_OF_CHANGES__ELEMENT_CHANGE, null, msgs);
			}
			msgs = basicSetElementChange(newElementChange, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		}
		else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					Variability_3_0Package.CHANGE_SET_OF_CHANGES__ELEMENT_CHANGE, newElementChange, newElementChange));
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
		case Variability_3_0Package.CHANGE_SET_OF_CHANGES__ELEMENT_CHANGE:
			return basicSetElementChange(null, msgs);
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
		case Variability_3_0Package.CHANGE_SET_OF_CHANGES__ELEMENT_CHANGE:
			return getElementChange();
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
		case Variability_3_0Package.CHANGE_SET_OF_CHANGES__ELEMENT_CHANGE:
			setElementChange((ElementChange) newValue);
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
		case Variability_3_0Package.CHANGE_SET_OF_CHANGES__ELEMENT_CHANGE:
			setElementChange((ElementChange) null);
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
		case Variability_3_0Package.CHANGE_SET_OF_CHANGES__ELEMENT_CHANGE:
			return elementChange != null;
		}
		return super.eIsSet(featureID);
	}

} //ChangeSetOfChangesImpl
