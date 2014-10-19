/**
 */
package org.archstudio.xadl3.implementation_3_0.impl;

import java.util.Collection;

import org.archstudio.xadl3.implementation_3_0.Implementation;
import org.archstudio.xadl3.implementation_3_0.ImplementationExtension;
import org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.impl.ExtensionImpl;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Implementation Extension</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.archstudio.xadl3.implementation_3_0.impl.ImplementationExtensionImpl#getImplementation <em>
 * Implementation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ImplementationExtensionImpl extends ExtensionImpl implements ImplementationExtension {
	/**
	 * The cached value of the '{@link #getImplementation() <em>Implementation</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getImplementation()
	 * @generated
	 * @ordered
	 */
	protected EList<Implementation> implementation;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ImplementationExtensionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Implementation_3_0Package.Literals.IMPLEMENTATION_EXTENSION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Implementation> getImplementation() {
		if (implementation == null) {
			implementation = new EObjectContainmentEList<Implementation>(Implementation.class, this,
					Implementation_3_0Package.IMPLEMENTATION_EXTENSION__IMPLEMENTATION);
		}
		return implementation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case Implementation_3_0Package.IMPLEMENTATION_EXTENSION__IMPLEMENTATION:
			return ((InternalEList<?>) getImplementation()).basicRemove(otherEnd, msgs);
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
		case Implementation_3_0Package.IMPLEMENTATION_EXTENSION__IMPLEMENTATION:
			return getImplementation();
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
		case Implementation_3_0Package.IMPLEMENTATION_EXTENSION__IMPLEMENTATION:
			getImplementation().clear();
			getImplementation().addAll((Collection<? extends Implementation>) newValue);
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
		case Implementation_3_0Package.IMPLEMENTATION_EXTENSION__IMPLEMENTATION:
			getImplementation().clear();
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
		case Implementation_3_0Package.IMPLEMENTATION_EXTENSION__IMPLEMENTATION:
			return implementation != null && !implementation.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ImplementationExtensionImpl
