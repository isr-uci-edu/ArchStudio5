/**
 */
package org.archstudio.xadl3.variability_3_0.impl;

import org.archstudio.xadl3.variability_3_0.JavaTransformChangeSetOfChanges;
import org.archstudio.xadl3.variability_3_0.Variability_3_0Package;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Java Transform Change Set Of Changes</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.archstudio.xadl3.variability_3_0.impl.JavaTransformChangeSetOfChangesImpl#getBundle
 * <em>Bundle</em>}</li>
 * <li>
 * {@link org.archstudio.xadl3.variability_3_0.impl.JavaTransformChangeSetOfChangesImpl#getClass_
 * <em>Class</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class JavaTransformChangeSetOfChangesImpl extends TransformChangeSetOfChangesImpl implements
		JavaTransformChangeSetOfChanges {
	/**
	 * The default value of the '{@link #getBundle() <em>Bundle</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getBundle()
	 * @generated
	 * @ordered
	 */
	protected static final String BUNDLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBundle() <em>Bundle</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getBundle()
	 * @generated
	 * @ordered
	 */
	protected String bundle = BUNDLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getClass_() <em>Class</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getClass_()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClass_() <em>Class</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getClass_()
	 * @generated
	 * @ordered
	 */
	protected String class_ = CLASS_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected JavaTransformChangeSetOfChangesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	protected EClass eStaticClass() {
		return Variability_3_0Package.Literals.JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getBundle() {
		return bundle;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setBundle(String newBundle) {
		String oldBundle = bundle;
		bundle = newBundle;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					Variability_3_0Package.JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__BUNDLE, oldBundle, bundle));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getClass_() {
		return class_;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setClass(String newClass) {
		String oldClass = class_;
		class_ = newClass;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					Variability_3_0Package.JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__CLASS, oldClass, class_));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case Variability_3_0Package.JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__BUNDLE:
			return getBundle();
		case Variability_3_0Package.JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__CLASS:
			return getClass_();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case Variability_3_0Package.JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__BUNDLE:
			setBundle((String) newValue);
			return;
		case Variability_3_0Package.JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__CLASS:
			setClass((String) newValue);
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
		case Variability_3_0Package.JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__BUNDLE:
			setBundle(BUNDLE_EDEFAULT);
			return;
		case Variability_3_0Package.JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__CLASS:
			setClass(CLASS_EDEFAULT);
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
		case Variability_3_0Package.JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__BUNDLE:
			return BUNDLE_EDEFAULT == null ? bundle != null : !BUNDLE_EDEFAULT.equals(bundle);
		case Variability_3_0Package.JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES__CLASS:
			return CLASS_EDEFAULT == null ? class_ != null : !CLASS_EDEFAULT.equals(class_);
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
		result.append(" (bundle: ");
		result.append(bundle);
		result.append(", class: ");
		result.append(class_);
		result.append(')');
		return result.toString();
	}

} //JavaTransformChangeSetOfChangesImpl
