/**
 */
package net.gexf_1_2.gexf.impl;

import java.util.Collection;

import net.gexf_1_2.gexf.AttributeContent;
import net.gexf_1_2.gexf.AttributesContent;
import net.gexf_1_2.gexf.ClassType;
import net.gexf_1_2.gexf.GexfPackage;
import net.gexf_1_2.gexf.ModeType;

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
 * <em><b>Attributes Content</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.impl.AttributesContentImpl#getAttribute <em>
 * Attribute</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.AttributesContentImpl#getClass_ <em>Class
 * </em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.AttributesContentImpl#getEnd <em>End</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.AttributesContentImpl#getEndopen <em>
 * Endopen</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.AttributesContentImpl#getMode <em>Mode
 * </em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.AttributesContentImpl#getStart <em>Start
 * </em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.AttributesContentImpl#getStartopen <em>
 * Startopen</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AttributesContentImpl extends EObjectImpl implements AttributesContent {
	/**
	 * The cached value of the '{@link #getAttribute() <em>Attribute</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAttribute()
	 * @generated
	 * @ordered
	 */
	protected EList<AttributeContent> attribute;

	/**
	 * The default value of the '{@link #getClass_() <em>Class</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getClass_()
	 * @generated
	 * @ordered
	 */
	protected static final ClassType CLASS_EDEFAULT = ClassType.NODE;

	/**
	 * The cached value of the '{@link #getClass_() <em>Class</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getClass_()
	 * @generated
	 * @ordered
	 */
	protected ClassType class_ = CLASS_EDEFAULT;

	/**
	 * This is true if the Class attribute has been set. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean classESet;

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
	 * The default value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected static final ModeType MODE_EDEFAULT = ModeType.STATIC;

	/**
	 * The cached value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected ModeType mode = MODE_EDEFAULT;

	/**
	 * This is true if the Mode attribute has been set. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean modeESet;

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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected AttributesContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	protected EClass eStaticClass() {
		return GexfPackage.Literals.ATTRIBUTES_CONTENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<AttributeContent> getAttribute() {
		if (attribute == null) {
			attribute = new EObjectContainmentEList<AttributeContent>(AttributeContent.class, this,
					GexfPackage.ATTRIBUTES_CONTENT__ATTRIBUTE);
		}
		return attribute;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ClassType getClass_() {
		return class_;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setClass(ClassType newClass) {
		ClassType oldClass = class_;
		class_ = newClass == null ? CLASS_EDEFAULT : newClass;
		boolean oldClassESet = classESet;
		classESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.ATTRIBUTES_CONTENT__CLASS, oldClass,
					class_, !oldClassESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void unsetClass() {
		ClassType oldClass = class_;
		boolean oldClassESet = classESet;
		class_ = CLASS_EDEFAULT;
		classESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, GexfPackage.ATTRIBUTES_CONTENT__CLASS, oldClass,
					CLASS_EDEFAULT, oldClassESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSetClass() {
		return classESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object getEnd() {
		return end;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setEnd(Object newEnd) {
		Object oldEnd = end;
		end = newEnd;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.ATTRIBUTES_CONTENT__END, oldEnd, end));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object getEndopen() {
		return endopen;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setEndopen(Object newEndopen) {
		Object oldEndopen = endopen;
		endopen = newEndopen;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.ATTRIBUTES_CONTENT__ENDOPEN, oldEndopen,
					endopen));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ModeType getMode() {
		return mode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setMode(ModeType newMode) {
		ModeType oldMode = mode;
		mode = newMode == null ? MODE_EDEFAULT : newMode;
		boolean oldModeESet = modeESet;
		modeESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.ATTRIBUTES_CONTENT__MODE, oldMode, mode,
					!oldModeESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void unsetMode() {
		ModeType oldMode = mode;
		boolean oldModeESet = modeESet;
		mode = MODE_EDEFAULT;
		modeESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, GexfPackage.ATTRIBUTES_CONTENT__MODE, oldMode,
					MODE_EDEFAULT, oldModeESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSetMode() {
		return modeESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object getStart() {
		return start;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setStart(Object newStart) {
		Object oldStart = start;
		start = newStart;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.ATTRIBUTES_CONTENT__START, oldStart,
					start));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object getStartopen() {
		return startopen;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setStartopen(Object newStartopen) {
		Object oldStartopen = startopen;
		startopen = newStartopen;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.ATTRIBUTES_CONTENT__STARTOPEN,
					oldStartopen, startopen));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GexfPackage.ATTRIBUTES_CONTENT__ATTRIBUTE:
			return ((InternalEList<?>) getAttribute()).basicRemove(otherEnd, msgs);
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
		case GexfPackage.ATTRIBUTES_CONTENT__ATTRIBUTE:
			return getAttribute();
		case GexfPackage.ATTRIBUTES_CONTENT__CLASS:
			return getClass_();
		case GexfPackage.ATTRIBUTES_CONTENT__END:
			return getEnd();
		case GexfPackage.ATTRIBUTES_CONTENT__ENDOPEN:
			return getEndopen();
		case GexfPackage.ATTRIBUTES_CONTENT__MODE:
			return getMode();
		case GexfPackage.ATTRIBUTES_CONTENT__START:
			return getStart();
		case GexfPackage.ATTRIBUTES_CONTENT__STARTOPEN:
			return getStartopen();
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
		case GexfPackage.ATTRIBUTES_CONTENT__ATTRIBUTE:
			getAttribute().clear();
			getAttribute().addAll((Collection<? extends AttributeContent>) newValue);
			return;
		case GexfPackage.ATTRIBUTES_CONTENT__CLASS:
			setClass((ClassType) newValue);
			return;
		case GexfPackage.ATTRIBUTES_CONTENT__END:
			setEnd(newValue);
			return;
		case GexfPackage.ATTRIBUTES_CONTENT__ENDOPEN:
			setEndopen(newValue);
			return;
		case GexfPackage.ATTRIBUTES_CONTENT__MODE:
			setMode((ModeType) newValue);
			return;
		case GexfPackage.ATTRIBUTES_CONTENT__START:
			setStart(newValue);
			return;
		case GexfPackage.ATTRIBUTES_CONTENT__STARTOPEN:
			setStartopen(newValue);
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
		case GexfPackage.ATTRIBUTES_CONTENT__ATTRIBUTE:
			getAttribute().clear();
			return;
		case GexfPackage.ATTRIBUTES_CONTENT__CLASS:
			unsetClass();
			return;
		case GexfPackage.ATTRIBUTES_CONTENT__END:
			setEnd(END_EDEFAULT);
			return;
		case GexfPackage.ATTRIBUTES_CONTENT__ENDOPEN:
			setEndopen(ENDOPEN_EDEFAULT);
			return;
		case GexfPackage.ATTRIBUTES_CONTENT__MODE:
			unsetMode();
			return;
		case GexfPackage.ATTRIBUTES_CONTENT__START:
			setStart(START_EDEFAULT);
			return;
		case GexfPackage.ATTRIBUTES_CONTENT__STARTOPEN:
			setStartopen(STARTOPEN_EDEFAULT);
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
		case GexfPackage.ATTRIBUTES_CONTENT__ATTRIBUTE:
			return attribute != null && !attribute.isEmpty();
		case GexfPackage.ATTRIBUTES_CONTENT__CLASS:
			return isSetClass();
		case GexfPackage.ATTRIBUTES_CONTENT__END:
			return END_EDEFAULT == null ? end != null : !END_EDEFAULT.equals(end);
		case GexfPackage.ATTRIBUTES_CONTENT__ENDOPEN:
			return ENDOPEN_EDEFAULT == null ? endopen != null : !ENDOPEN_EDEFAULT.equals(endopen);
		case GexfPackage.ATTRIBUTES_CONTENT__MODE:
			return isSetMode();
		case GexfPackage.ATTRIBUTES_CONTENT__START:
			return START_EDEFAULT == null ? start != null : !START_EDEFAULT.equals(start);
		case GexfPackage.ATTRIBUTES_CONTENT__STARTOPEN:
			return STARTOPEN_EDEFAULT == null ? startopen != null : !STARTOPEN_EDEFAULT.equals(startopen);
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
		result.append(" (class: ");
		if (classESet) {
			result.append(class_);
		}
		else {
			result.append("<unset>");
		}
		result.append(", end: ");
		result.append(end);
		result.append(", endopen: ");
		result.append(endopen);
		result.append(", mode: ");
		if (modeESet) {
			result.append(mode);
		}
		else {
			result.append("<unset>");
		}
		result.append(", start: ");
		result.append(start);
		result.append(", startopen: ");
		result.append(startopen);
		result.append(')');
		return result.toString();
	}

} //AttributesContentImpl
