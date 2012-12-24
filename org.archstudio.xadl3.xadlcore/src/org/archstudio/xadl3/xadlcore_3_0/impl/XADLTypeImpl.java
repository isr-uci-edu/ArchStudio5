/**
 */
package org.archstudio.xadl3.xadlcore_3_0.impl;

import java.util.Collection;

import org.archstudio.xadl3.xadlcore_3_0.XADLType;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>XADL Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.archstudio.xadl3.xadlcore_3_0.impl.XADLTypeImpl#getTopLevelElement
 * <em>Top Level Element</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class XADLTypeImpl extends EObjectImpl implements XADLType {
	/**
	 * The cached value of the '{@link #getTopLevelElement()
	 * <em>Top Level Element</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTopLevelElement()
	 * @generated
	 * @ordered
	 */
	protected EList<EObject> topLevelElement;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected XADLTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return Xadlcore_3_0Package.Literals.XADL_TYPE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<EObject> getTopLevelElement() {
		if (topLevelElement == null) {
			topLevelElement = new EObjectContainmentEList<EObject>(EObject.class, this,
					Xadlcore_3_0Package.XADL_TYPE__TOP_LEVEL_ELEMENT);
		}
		return topLevelElement;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case Xadlcore_3_0Package.XADL_TYPE__TOP_LEVEL_ELEMENT:
			return ((InternalEList<?>) getTopLevelElement()).basicRemove(otherEnd, msgs);
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
		case Xadlcore_3_0Package.XADL_TYPE__TOP_LEVEL_ELEMENT:
			return getTopLevelElement();
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
		case Xadlcore_3_0Package.XADL_TYPE__TOP_LEVEL_ELEMENT:
			getTopLevelElement().clear();
			getTopLevelElement().addAll((Collection<? extends EObject>) newValue);
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
		case Xadlcore_3_0Package.XADL_TYPE__TOP_LEVEL_ELEMENT:
			getTopLevelElement().clear();
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
		case Xadlcore_3_0Package.XADL_TYPE__TOP_LEVEL_ELEMENT:
			return topLevelElement != null && !topLevelElement.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //XADLTypeImpl
