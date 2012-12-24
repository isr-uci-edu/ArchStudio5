/**
 */
package org.archstudio.xadl3.myxgen_3_0.impl;

import org.archstudio.xadl3.implementation_3_0.impl.ImplementationImpl;
import org.archstudio.xadl3.myxgen_3_0.MyxGen;
import org.archstudio.xadl3.myxgen_3_0.Myxgen_3_0Package;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Myx Gen</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.archstudio.xadl3.myxgen_3_0.impl.MyxGenImpl#getBrickID <em>
 * Brick ID</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MyxGenImpl extends ImplementationImpl implements MyxGen {
	/**
	 * The default value of the '{@link #getBrickID() <em>Brick ID</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getBrickID()
	 * @generated
	 * @ordered
	 */
	protected static final String BRICK_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBrickID() <em>Brick ID</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getBrickID()
	 * @generated
	 * @ordered
	 */
	protected String brickID = BRICK_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MyxGenImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return Myxgen_3_0Package.Literals.MYX_GEN;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getBrickID() {
		return brickID;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setBrickID(String newBrickID) {
		String oldBrickID = brickID;
		brickID = newBrickID;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, Myxgen_3_0Package.MYX_GEN__BRICK_ID, oldBrickID,
					brickID));
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
		case Myxgen_3_0Package.MYX_GEN__BRICK_ID:
			return getBrickID();
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
		case Myxgen_3_0Package.MYX_GEN__BRICK_ID:
			setBrickID((String) newValue);
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
		case Myxgen_3_0Package.MYX_GEN__BRICK_ID:
			setBrickID(BRICK_ID_EDEFAULT);
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
		case Myxgen_3_0Package.MYX_GEN__BRICK_ID:
			return BRICK_ID_EDEFAULT == null ? brickID != null : !BRICK_ID_EDEFAULT.equals(brickID);
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
		result.append(" (brickID: ");
		result.append(brickID);
		result.append(')');
		return result.toString();
	}

} //MyxGenImpl
