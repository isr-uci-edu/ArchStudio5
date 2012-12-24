/**
 */
package net.gexf_1_2.gexf.impl;

import java.util.Collection;

import net.gexf_1_2.gexf.AttvalueType;
import net.gexf_1_2.gexf.AttvaluesContent;
import net.gexf_1_2.gexf.GexfPackage;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Attvalues Content</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.impl.AttvaluesContentImpl#getAttvalue <em>
 * Attvalue</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AttvaluesContentImpl extends EObjectImpl implements AttvaluesContent {
	/**
	 * The cached value of the '{@link #getAttvalue() <em>Attvalue</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAttvalue()
	 * @generated
	 * @ordered
	 */
	protected EList<AttvalueType> attvalue;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected AttvaluesContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return GexfPackage.Literals.ATTVALUES_CONTENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<AttvalueType> getAttvalue() {
		if (attvalue == null) {
			attvalue = new EObjectContainmentEList<AttvalueType>(AttvalueType.class, this,
					GexfPackage.ATTVALUES_CONTENT__ATTVALUE);
		}
		return attvalue;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GexfPackage.ATTVALUES_CONTENT__ATTVALUE:
			return ((InternalEList<?>) getAttvalue()).basicRemove(otherEnd, msgs);
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
		case GexfPackage.ATTVALUES_CONTENT__ATTVALUE:
			return getAttvalue();
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
		case GexfPackage.ATTVALUES_CONTENT__ATTVALUE:
			getAttvalue().clear();
			getAttvalue().addAll((Collection<? extends AttvalueType>) newValue);
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
		case GexfPackage.ATTVALUES_CONTENT__ATTVALUE:
			getAttvalue().clear();
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
		case GexfPackage.ATTVALUES_CONTENT__ATTVALUE:
			return attvalue != null && !attvalue.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AttvaluesContentImpl
