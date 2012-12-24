/**
 */
package org.archstudio.xadl3.statechart_1_0.impl;

import java.util.Collection;

import org.archstudio.xadl3.statechart_1_0.PseudoState;
import org.archstudio.xadl3.statechart_1_0.Statechart;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xadl3.statechart_1_0.Transition;
import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Statechart</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.impl.StatechartImpl#getState
 * <em>State</em>}</li>
 * <li>
 * {@link org.archstudio.xadl3.statechart_1_0.impl.StatechartImpl#getTransition
 * <em>Transition</em>}</li>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.impl.StatechartImpl#getExt
 * <em>Ext</em>}</li>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.impl.StatechartImpl#getId <em>
 * Id</em>}</li>
 * <li>{@link org.archstudio.xadl3.statechart_1_0.impl.StatechartImpl#getName
 * <em>Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class StatechartImpl extends EObjectImpl implements Statechart {
	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected EList<PseudoState> state;

	/**
	 * The cached value of the '{@link #getTransition() <em>Transition</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTransition()
	 * @generated
	 * @ordered
	 */
	protected EList<Transition> transition;

	/**
	 * The cached value of the '{@link #getExt() <em>Ext</em>}' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getExt()
	 * @generated
	 * @ordered
	 */
	protected EList<Extension> ext;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected StatechartImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	protected EClass eStaticClass() {
		return Statechart_1_0Package.Literals.STATECHART;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<PseudoState> getState() {
		if (state == null) {
			state = new EObjectContainmentEList<PseudoState>(PseudoState.class, this,
					Statechart_1_0Package.STATECHART__STATE);
		}
		return state;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Transition> getTransition() {
		if (transition == null) {
			transition = new EObjectContainmentEList<Transition>(Transition.class, this,
					Statechart_1_0Package.STATECHART__TRANSITION);
		}
		return transition;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Extension> getExt() {
		if (ext == null) {
			ext = new EObjectContainmentEList<Extension>(Extension.class, this, Statechart_1_0Package.STATECHART__EXT);
		}
		return ext;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, Statechart_1_0Package.STATECHART__ID, oldId, id));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, Statechart_1_0Package.STATECHART__NAME, oldName, name));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case Statechart_1_0Package.STATECHART__STATE:
			return ((InternalEList<?>) getState()).basicRemove(otherEnd, msgs);
		case Statechart_1_0Package.STATECHART__TRANSITION:
			return ((InternalEList<?>) getTransition()).basicRemove(otherEnd, msgs);
		case Statechart_1_0Package.STATECHART__EXT:
			return ((InternalEList<?>) getExt()).basicRemove(otherEnd, msgs);
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
		case Statechart_1_0Package.STATECHART__STATE:
			return getState();
		case Statechart_1_0Package.STATECHART__TRANSITION:
			return getTransition();
		case Statechart_1_0Package.STATECHART__EXT:
			return getExt();
		case Statechart_1_0Package.STATECHART__ID:
			return getId();
		case Statechart_1_0Package.STATECHART__NAME:
			return getName();
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
		case Statechart_1_0Package.STATECHART__STATE:
			getState().clear();
			getState().addAll((Collection<? extends PseudoState>) newValue);
			return;
		case Statechart_1_0Package.STATECHART__TRANSITION:
			getTransition().clear();
			getTransition().addAll((Collection<? extends Transition>) newValue);
			return;
		case Statechart_1_0Package.STATECHART__EXT:
			getExt().clear();
			getExt().addAll((Collection<? extends Extension>) newValue);
			return;
		case Statechart_1_0Package.STATECHART__ID:
			setId((String) newValue);
			return;
		case Statechart_1_0Package.STATECHART__NAME:
			setName((String) newValue);
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
		case Statechart_1_0Package.STATECHART__STATE:
			getState().clear();
			return;
		case Statechart_1_0Package.STATECHART__TRANSITION:
			getTransition().clear();
			return;
		case Statechart_1_0Package.STATECHART__EXT:
			getExt().clear();
			return;
		case Statechart_1_0Package.STATECHART__ID:
			setId(ID_EDEFAULT);
			return;
		case Statechart_1_0Package.STATECHART__NAME:
			setName(NAME_EDEFAULT);
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
		case Statechart_1_0Package.STATECHART__STATE:
			return state != null && !state.isEmpty();
		case Statechart_1_0Package.STATECHART__TRANSITION:
			return transition != null && !transition.isEmpty();
		case Statechart_1_0Package.STATECHART__EXT:
			return ext != null && !ext.isEmpty();
		case Statechart_1_0Package.STATECHART__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case Statechart_1_0Package.STATECHART__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //StatechartImpl
