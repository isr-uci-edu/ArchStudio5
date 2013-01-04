/**
 */
package org.archstudio.xadl3.lookupimplementation_3_0.impl;

import java.util.Collection;

import org.archstudio.xadl3.implementation_3_0.impl.ImplementationImpl;
import org.archstudio.xadl3.lookupimplementation_3_0.LookupImplementation;
import org.archstudio.xadl3.lookupimplementation_3_0.Lookupimplementation_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Lookup Implementation</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.archstudio.xadl3.lookupimplementation_3_0.impl.LookupImplementationImpl#getExt <em>Ext</em>}</li>
 * <li>{@link org.archstudio.xadl3.lookupimplementation_3_0.impl.LookupImplementationImpl#getLookup <em>Lookup</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class LookupImplementationImpl extends ImplementationImpl implements LookupImplementation {
	/**
	 * The cached value of the '{@link #getExt() <em>Ext</em>}' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getExt()
	 * @generated
	 * @ordered
	 */
	protected EList<Extension> ext;

	/**
	 * The default value of the '{@link #getLookup() <em>Lookup</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getLookup()
	 * @generated
	 * @ordered
	 */
	protected static final String LOOKUP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLookup() <em>Lookup</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getLookup()
	 * @generated
	 * @ordered
	 */
	protected String lookup = LOOKUP_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected LookupImplementationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Lookupimplementation_3_0Package.Literals.LOOKUP_IMPLEMENTATION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Extension> getExt() {
		if (ext == null) {
			ext = new EObjectContainmentEList<Extension>(Extension.class, this,
					Lookupimplementation_3_0Package.LOOKUP_IMPLEMENTATION__EXT);
		}
		return ext;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getLookup() {
		return lookup;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setLookup(String newLookup) {
		String oldLookup = lookup;
		lookup = newLookup;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					Lookupimplementation_3_0Package.LOOKUP_IMPLEMENTATION__LOOKUP, oldLookup, lookup));
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
		case Lookupimplementation_3_0Package.LOOKUP_IMPLEMENTATION__EXT:
			return ((InternalEList<?>) getExt()).basicRemove(otherEnd, msgs);
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
		case Lookupimplementation_3_0Package.LOOKUP_IMPLEMENTATION__EXT:
			return getExt();
		case Lookupimplementation_3_0Package.LOOKUP_IMPLEMENTATION__LOOKUP:
			return getLookup();
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
		case Lookupimplementation_3_0Package.LOOKUP_IMPLEMENTATION__EXT:
			getExt().clear();
			getExt().addAll((Collection<? extends Extension>) newValue);
			return;
		case Lookupimplementation_3_0Package.LOOKUP_IMPLEMENTATION__LOOKUP:
			setLookup((String) newValue);
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
		case Lookupimplementation_3_0Package.LOOKUP_IMPLEMENTATION__EXT:
			getExt().clear();
			return;
		case Lookupimplementation_3_0Package.LOOKUP_IMPLEMENTATION__LOOKUP:
			setLookup(LOOKUP_EDEFAULT);
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
		case Lookupimplementation_3_0Package.LOOKUP_IMPLEMENTATION__EXT:
			return ext != null && !ext.isEmpty();
		case Lookupimplementation_3_0Package.LOOKUP_IMPLEMENTATION__LOOKUP:
			return LOOKUP_EDEFAULT == null ? lookup != null : !LOOKUP_EDEFAULT.equals(lookup);
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
		result.append(" (lookup: ");
		result.append(lookup);
		result.append(')');
		return result.toString();
	}

} //LookupImplementationImpl
