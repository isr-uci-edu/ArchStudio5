/**
 */
package org.archstudio.xadl3.statechart_1_0.impl;

import java.util.Collection;

import org.archstudio.xadl3.statechart_1_0.Behavior;
import org.archstudio.xadl3.statechart_1_0.State;
import org.archstudio.xadl3.statechart_1_0.StateType;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xadl3.statechart_1_0.SubStatechart;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>State</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.impl.StateImpl#getEntry <em>Entry</em>}</li>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.impl.StateImpl#getExit <em>Exit</em>}</li>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.impl.StateImpl#getSubStatechart <em>Sub Statechart</em>}</li>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.impl.StateImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class StateImpl extends PseudoStateImpl implements State {
	/**
	 * The cached value of the '{@link #getEntry() <em>Entry</em>}' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getEntry()
	 * @generated
	 * @ordered
	 */
	protected EList<Behavior> entry;

	/**
	 * The cached value of the '{@link #getExit() <em>Exit</em>}' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getExit()
	 * @generated
	 * @ordered
	 */
	protected EList<Behavior> exit;

	/**
	 * The cached value of the '{@link #getSubStatechart() <em>Sub Statechart</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSubStatechart()
	 * @generated
	 * @ordered
	 */
	protected SubStatechart subStatechart;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final StateType TYPE_EDEFAULT = StateType.STATE;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected StateType type = TYPE_EDEFAULT;

	/**
	 * This is true if the Type attribute has been set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean typeESet;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected StateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Statechart_1_0Package.Literals.STATE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Behavior> getEntry() {
		if (entry == null) {
			entry = new EObjectContainmentEList<Behavior>(Behavior.class, this, Statechart_1_0Package.STATE__ENTRY);
		}
		return entry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Behavior> getExit() {
		if (exit == null) {
			exit = new EObjectContainmentEList<Behavior>(Behavior.class, this, Statechart_1_0Package.STATE__EXIT);
		}
		return exit;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public SubStatechart getSubStatechart() {
		return subStatechart;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetSubStatechart(SubStatechart newSubStatechart, NotificationChain msgs) {
		SubStatechart oldSubStatechart = subStatechart;
		subStatechart = newSubStatechart;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					Statechart_1_0Package.STATE__SUB_STATECHART, oldSubStatechart, newSubStatechart);
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
	public void setSubStatechart(SubStatechart newSubStatechart) {
		if (newSubStatechart != subStatechart) {
			NotificationChain msgs = null;
			if (subStatechart != null) {
				msgs = ((InternalEObject) subStatechart).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- Statechart_1_0Package.STATE__SUB_STATECHART, null, msgs);
			}
			if (newSubStatechart != null) {
				msgs = ((InternalEObject) newSubStatechart).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- Statechart_1_0Package.STATE__SUB_STATECHART, null, msgs);
			}
			msgs = basicSetSubStatechart(newSubStatechart, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		}
		else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, Statechart_1_0Package.STATE__SUB_STATECHART,
					newSubStatechart, newSubStatechart));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public StateType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setType(StateType newType) {
		StateType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		boolean oldTypeESet = typeESet;
		typeESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, Statechart_1_0Package.STATE__TYPE, oldType, type,
					!oldTypeESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetType() {
		StateType oldType = type;
		boolean oldTypeESet = typeESet;
		type = TYPE_EDEFAULT;
		typeESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, Statechart_1_0Package.STATE__TYPE, oldType,
					TYPE_EDEFAULT, oldTypeESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetType() {
		return typeESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case Statechart_1_0Package.STATE__ENTRY:
			return ((InternalEList<?>) getEntry()).basicRemove(otherEnd, msgs);
		case Statechart_1_0Package.STATE__EXIT:
			return ((InternalEList<?>) getExit()).basicRemove(otherEnd, msgs);
		case Statechart_1_0Package.STATE__SUB_STATECHART:
			return basicSetSubStatechart(null, msgs);
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
		case Statechart_1_0Package.STATE__ENTRY:
			return getEntry();
		case Statechart_1_0Package.STATE__EXIT:
			return getExit();
		case Statechart_1_0Package.STATE__SUB_STATECHART:
			return getSubStatechart();
		case Statechart_1_0Package.STATE__TYPE:
			return getType();
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
		case Statechart_1_0Package.STATE__ENTRY:
			getEntry().clear();
			getEntry().addAll((Collection<? extends Behavior>) newValue);
			return;
		case Statechart_1_0Package.STATE__EXIT:
			getExit().clear();
			getExit().addAll((Collection<? extends Behavior>) newValue);
			return;
		case Statechart_1_0Package.STATE__SUB_STATECHART:
			setSubStatechart((SubStatechart) newValue);
			return;
		case Statechart_1_0Package.STATE__TYPE:
			setType((StateType) newValue);
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
		case Statechart_1_0Package.STATE__ENTRY:
			getEntry().clear();
			return;
		case Statechart_1_0Package.STATE__EXIT:
			getExit().clear();
			return;
		case Statechart_1_0Package.STATE__SUB_STATECHART:
			setSubStatechart((SubStatechart) null);
			return;
		case Statechart_1_0Package.STATE__TYPE:
			unsetType();
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
		case Statechart_1_0Package.STATE__ENTRY:
			return entry != null && !entry.isEmpty();
		case Statechart_1_0Package.STATE__EXIT:
			return exit != null && !exit.isEmpty();
		case Statechart_1_0Package.STATE__SUB_STATECHART:
			return subStatechart != null;
		case Statechart_1_0Package.STATE__TYPE:
			return isSetType();
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
		result.append(" (type: ");
		if (typeESet) {
			result.append(type);
		}
		else {
			result.append("<unset>");
		}
		result.append(')');
		return result.toString();
	}

} //StateImpl
