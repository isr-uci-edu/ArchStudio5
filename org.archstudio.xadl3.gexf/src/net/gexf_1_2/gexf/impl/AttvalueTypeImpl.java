/**
 */
package net.gexf_1_2.gexf.impl;

import net.gexf_1_2.gexf.AttvalueType;
import net.gexf_1_2.gexf.GexfPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Attvalue Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.impl.AttvalueTypeImpl#getEnd <em>End</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.AttvalueTypeImpl#getEndopen <em>Endopen
 * </em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.AttvalueTypeImpl#getFor <em>For</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.AttvalueTypeImpl#getStart <em>Start</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.AttvalueTypeImpl#getStartopen <em>Startopen
 * </em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.AttvalueTypeImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AttvalueTypeImpl extends EObjectImpl implements AttvalueType {
	/**
	 * The default value of the '{@link #getEnd() <em>End</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEnd()
	 * @generated
	 * @ordered
	 */
	protected static final Object END_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEnd() <em>End</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEnd()
	 * @generated
	 * @ordered
	 */
	protected Object end = END_EDEFAULT;

	/**
	 * The default value of the '{@link #getEndopen() <em>Endopen</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEndopen()
	 * @generated
	 * @ordered
	 */
	protected static final Object ENDOPEN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEndopen() <em>Endopen</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEndopen()
	 * @generated
	 * @ordered
	 */
	protected Object endopen = ENDOPEN_EDEFAULT;

	/**
	 * The default value of the '{@link #getFor() <em>For</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFor()
	 * @generated
	 * @ordered
	 */
	protected static final Object FOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFor() <em>For</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFor()
	 * @generated
	 * @ordered
	 */
	protected Object for_ = FOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getStart() <em>Start</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected static final Object START_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStart() <em>Start</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected Object start = START_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartopen() <em>Startopen</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStartopen()
	 * @generated
	 * @ordered
	 */
	protected static final Object STARTOPEN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStartopen() <em>Startopen</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStartopen()
	 * @generated
	 * @ordered
	 */
	protected Object startopen = STARTOPEN_EDEFAULT;

	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected AttvalueTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return GexfPackage.Literals.ATTVALUE_TYPE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getEnd() {
		return end;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEnd(Object newEnd) {
		Object oldEnd = end;
		end = newEnd;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.ATTVALUE_TYPE__END, oldEnd, end));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getEndopen() {
		return endopen;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEndopen(Object newEndopen) {
		Object oldEndopen = endopen;
		endopen = newEndopen;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.ATTVALUE_TYPE__ENDOPEN, oldEndopen,
					endopen));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getFor() {
		return for_;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setFor(Object newFor) {
		Object oldFor = for_;
		for_ = newFor;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.ATTVALUE_TYPE__FOR, oldFor, for_));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getStart() {
		return start;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setStart(Object newStart) {
		Object oldStart = start;
		start = newStart;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.ATTVALUE_TYPE__START, oldStart, start));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getStartopen() {
		return startopen;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setStartopen(Object newStartopen) {
		Object oldStartopen = startopen;
		startopen = newStartopen;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.ATTVALUE_TYPE__STARTOPEN, oldStartopen,
					startopen));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.ATTVALUE_TYPE__VALUE, oldValue, value));
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
		case GexfPackage.ATTVALUE_TYPE__END:
			return getEnd();
		case GexfPackage.ATTVALUE_TYPE__ENDOPEN:
			return getEndopen();
		case GexfPackage.ATTVALUE_TYPE__FOR:
			return getFor();
		case GexfPackage.ATTVALUE_TYPE__START:
			return getStart();
		case GexfPackage.ATTVALUE_TYPE__STARTOPEN:
			return getStartopen();
		case GexfPackage.ATTVALUE_TYPE__VALUE:
			return getValue();
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
		case GexfPackage.ATTVALUE_TYPE__END:
			setEnd(newValue);
			return;
		case GexfPackage.ATTVALUE_TYPE__ENDOPEN:
			setEndopen(newValue);
			return;
		case GexfPackage.ATTVALUE_TYPE__FOR:
			setFor(newValue);
			return;
		case GexfPackage.ATTVALUE_TYPE__START:
			setStart(newValue);
			return;
		case GexfPackage.ATTVALUE_TYPE__STARTOPEN:
			setStartopen(newValue);
			return;
		case GexfPackage.ATTVALUE_TYPE__VALUE:
			setValue((String) newValue);
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
		case GexfPackage.ATTVALUE_TYPE__END:
			setEnd(END_EDEFAULT);
			return;
		case GexfPackage.ATTVALUE_TYPE__ENDOPEN:
			setEndopen(ENDOPEN_EDEFAULT);
			return;
		case GexfPackage.ATTVALUE_TYPE__FOR:
			setFor(FOR_EDEFAULT);
			return;
		case GexfPackage.ATTVALUE_TYPE__START:
			setStart(START_EDEFAULT);
			return;
		case GexfPackage.ATTVALUE_TYPE__STARTOPEN:
			setStartopen(STARTOPEN_EDEFAULT);
			return;
		case GexfPackage.ATTVALUE_TYPE__VALUE:
			setValue(VALUE_EDEFAULT);
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
		case GexfPackage.ATTVALUE_TYPE__END:
			return END_EDEFAULT == null ? end != null : !END_EDEFAULT.equals(end);
		case GexfPackage.ATTVALUE_TYPE__ENDOPEN:
			return ENDOPEN_EDEFAULT == null ? endopen != null : !ENDOPEN_EDEFAULT.equals(endopen);
		case GexfPackage.ATTVALUE_TYPE__FOR:
			return FOR_EDEFAULT == null ? for_ != null : !FOR_EDEFAULT.equals(for_);
		case GexfPackage.ATTVALUE_TYPE__START:
			return START_EDEFAULT == null ? start != null : !START_EDEFAULT.equals(start);
		case GexfPackage.ATTVALUE_TYPE__STARTOPEN:
			return STARTOPEN_EDEFAULT == null ? startopen != null : !STARTOPEN_EDEFAULT.equals(startopen);
		case GexfPackage.ATTVALUE_TYPE__VALUE:
			return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
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
		result.append(" (end: ");
		result.append(end);
		result.append(", endopen: ");
		result.append(endopen);
		result.append(", for: ");
		result.append(for_);
		result.append(", start: ");
		result.append(start);
		result.append(", startopen: ");
		result.append(startopen);
		result.append(", value: ");
		result.append(value);
		result.append(')');
		return result.toString();
	}

} //AttvalueTypeImpl
