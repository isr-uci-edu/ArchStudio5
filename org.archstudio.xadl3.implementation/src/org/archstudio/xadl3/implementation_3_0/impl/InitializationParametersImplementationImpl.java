/**
 */
package org.archstudio.xadl3.implementation_3_0.impl;

import java.util.Collection;

import org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package;
import org.archstudio.xadl3.implementation_3_0.InitializationParameter;
import org.archstudio.xadl3.implementation_3_0.InitializationParametersImplementation;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Initialization Parameters Implementation</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.archstudio.xadl3.implementation_3_0.impl.InitializationParametersImplementationImpl#getInitializationParameter
 * <em>Initialization Parameter</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class InitializationParametersImplementationImpl extends ImplementationImpl implements
		InitializationParametersImplementation {
	/**
	 * The cached value of the '{@link #getInitializationParameter()
	 * <em>Initialization Parameter</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getInitializationParameter()
	 * @generated
	 * @ordered
	 */
	protected EList<InitializationParameter> initializationParameter;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected InitializationParametersImplementationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return Implementation_3_0Package.Literals.INITIALIZATION_PARAMETERS_IMPLEMENTATION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<InitializationParameter> getInitializationParameter() {
		if (initializationParameter == null) {
			initializationParameter = new EObjectContainmentEList<InitializationParameter>(
					InitializationParameter.class, this,
					Implementation_3_0Package.INITIALIZATION_PARAMETERS_IMPLEMENTATION__INITIALIZATION_PARAMETER);
		}
		return initializationParameter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case Implementation_3_0Package.INITIALIZATION_PARAMETERS_IMPLEMENTATION__INITIALIZATION_PARAMETER:
			return ((InternalEList<?>) getInitializationParameter()).basicRemove(otherEnd, msgs);
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
		case Implementation_3_0Package.INITIALIZATION_PARAMETERS_IMPLEMENTATION__INITIALIZATION_PARAMETER:
			return getInitializationParameter();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case Implementation_3_0Package.INITIALIZATION_PARAMETERS_IMPLEMENTATION__INITIALIZATION_PARAMETER:
			getInitializationParameter().clear();
			getInitializationParameter().addAll((Collection<? extends InitializationParameter>) newValue);
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
		case Implementation_3_0Package.INITIALIZATION_PARAMETERS_IMPLEMENTATION__INITIALIZATION_PARAMETER:
			getInitializationParameter().clear();
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
		case Implementation_3_0Package.INITIALIZATION_PARAMETERS_IMPLEMENTATION__INITIALIZATION_PARAMETER:
			return initializationParameter != null && !initializationParameter.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //InitializationParametersImplementationImpl
