/**
 */
package net.gexf_1_2.viz.impl;

import net.gexf_1_2.gexf.SpellsContent;
import net.gexf_1_2.viz.PositionContent;
import net.gexf_1_2.viz.VizPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Position Content</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.gexf_1_2.viz.impl.PositionContentImpl#getSpells <em>Spells</em>}</li>
 * <li>{@link net.gexf_1_2.viz.impl.PositionContentImpl#getEnd <em>End</em>}</li>
 * <li>{@link net.gexf_1_2.viz.impl.PositionContentImpl#getEndopen <em>Endopen</em>}</li>
 * <li>{@link net.gexf_1_2.viz.impl.PositionContentImpl#getStart <em>Start</em>}</li>
 * <li>{@link net.gexf_1_2.viz.impl.PositionContentImpl#getStartopen <em>Startopen</em>}</li>
 * <li>{@link net.gexf_1_2.viz.impl.PositionContentImpl#getX <em>X</em>}</li>
 * <li>{@link net.gexf_1_2.viz.impl.PositionContentImpl#getY <em>Y</em>}</li>
 * <li>{@link net.gexf_1_2.viz.impl.PositionContentImpl#getZ <em>Z</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PositionContentImpl extends EObjectImpl implements PositionContent {
	/**
	 * The cached value of the '{@link #getSpells() <em>Spells</em>}' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getSpells()
	 * @generated
	 * @ordered
	 */
	protected SpellsContent spells;

	/**
	 * The default value of the '{@link #getEnd() <em>End</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getEnd()
	 * @generated
	 * @ordered
	 */
	protected static final Object END_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEnd() <em>End</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEnd()
	 * @generated
	 * @ordered
	 */
	protected Object end = END_EDEFAULT;

	/**
	 * The default value of the '{@link #getEndopen() <em>Endopen</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEndopen()
	 * @generated
	 * @ordered
	 */
	protected static final Object ENDOPEN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEndopen() <em>Endopen</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEndopen()
	 * @generated
	 * @ordered
	 */
	protected Object endopen = ENDOPEN_EDEFAULT;

	/**
	 * The default value of the '{@link #getStart() <em>Start</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected static final Object START_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStart() <em>Start</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected Object start = START_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartopen() <em>Startopen</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getStartopen()
	 * @generated
	 * @ordered
	 */
	protected static final Object STARTOPEN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStartopen() <em>Startopen</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getStartopen()
	 * @generated
	 * @ordered
	 */
	protected Object startopen = STARTOPEN_EDEFAULT;

	/**
	 * The default value of the '{@link #getX() <em>X</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected static final float X_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getX() <em>X</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected float x = X_EDEFAULT;

	/**
	 * This is true if the X attribute has been set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean xESet;

	/**
	 * The default value of the '{@link #getY() <em>Y</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getY()
	 * @generated
	 * @ordered
	 */
	protected static final float Y_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getY() <em>Y</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getY()
	 * @generated
	 * @ordered
	 */
	protected float y = Y_EDEFAULT;

	/**
	 * This is true if the Y attribute has been set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean yESet;

	/**
	 * The default value of the '{@link #getZ() <em>Z</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getZ()
	 * @generated
	 * @ordered
	 */
	protected static final float Z_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getZ() <em>Z</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getZ()
	 * @generated
	 * @ordered
	 */
	protected float z = Z_EDEFAULT;

	/**
	 * This is true if the Z attribute has been set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean zESet;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PositionContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VizPackage.Literals.POSITION_CONTENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public SpellsContent getSpells() {
		return spells;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetSpells(SpellsContent newSpells, NotificationChain msgs) {
		SpellsContent oldSpells = spells;
		spells = newSpells;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					VizPackage.POSITION_CONTENT__SPELLS, oldSpells, newSpells);
			if (msgs == null) {
				msgs = notification;
			}
			else {
				msgs.add(notification);
			}
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setSpells(SpellsContent newSpells) {
		if (newSpells != spells) {
			NotificationChain msgs = null;
			if (spells != null) {
				msgs = ((InternalEObject) spells).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- VizPackage.POSITION_CONTENT__SPELLS, null, msgs);
			}
			if (newSpells != null) {
				msgs = ((InternalEObject) newSpells).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- VizPackage.POSITION_CONTENT__SPELLS, null, msgs);
			}
			msgs = basicSetSpells(newSpells, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		}
		else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.POSITION_CONTENT__SPELLS, newSpells,
					newSpells));
		}
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
			eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.POSITION_CONTENT__END, oldEnd, end));
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
			eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.POSITION_CONTENT__ENDOPEN, oldEndopen,
					endopen));
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
			eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.POSITION_CONTENT__START, oldStart, start));
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
			eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.POSITION_CONTENT__STARTOPEN, oldStartopen,
					startopen));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public float getX() {
		return x;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setX(float newX) {
		float oldX = x;
		x = newX;
		boolean oldXESet = xESet;
		xESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.POSITION_CONTENT__X, oldX, x, !oldXESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetX() {
		float oldX = x;
		boolean oldXESet = xESet;
		x = X_EDEFAULT;
		xESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, VizPackage.POSITION_CONTENT__X, oldX, X_EDEFAULT,
					oldXESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetX() {
		return xESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public float getY() {
		return y;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setY(float newY) {
		float oldY = y;
		y = newY;
		boolean oldYESet = yESet;
		yESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.POSITION_CONTENT__Y, oldY, y, !oldYESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetY() {
		float oldY = y;
		boolean oldYESet = yESet;
		y = Y_EDEFAULT;
		yESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, VizPackage.POSITION_CONTENT__Y, oldY, Y_EDEFAULT,
					oldYESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetY() {
		return yESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public float getZ() {
		return z;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setZ(float newZ) {
		float oldZ = z;
		z = newZ;
		boolean oldZESet = zESet;
		zESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.POSITION_CONTENT__Z, oldZ, z, !oldZESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetZ() {
		float oldZ = z;
		boolean oldZESet = zESet;
		z = Z_EDEFAULT;
		zESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, VizPackage.POSITION_CONTENT__Z, oldZ, Z_EDEFAULT,
					oldZESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetZ() {
		return zESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case VizPackage.POSITION_CONTENT__SPELLS:
			return basicSetSpells(null, msgs);
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
		case VizPackage.POSITION_CONTENT__SPELLS:
			return getSpells();
		case VizPackage.POSITION_CONTENT__END:
			return getEnd();
		case VizPackage.POSITION_CONTENT__ENDOPEN:
			return getEndopen();
		case VizPackage.POSITION_CONTENT__START:
			return getStart();
		case VizPackage.POSITION_CONTENT__STARTOPEN:
			return getStartopen();
		case VizPackage.POSITION_CONTENT__X:
			return getX();
		case VizPackage.POSITION_CONTENT__Y:
			return getY();
		case VizPackage.POSITION_CONTENT__Z:
			return getZ();
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
		case VizPackage.POSITION_CONTENT__SPELLS:
			setSpells((SpellsContent) newValue);
			return;
		case VizPackage.POSITION_CONTENT__END:
			setEnd(newValue);
			return;
		case VizPackage.POSITION_CONTENT__ENDOPEN:
			setEndopen(newValue);
			return;
		case VizPackage.POSITION_CONTENT__START:
			setStart(newValue);
			return;
		case VizPackage.POSITION_CONTENT__STARTOPEN:
			setStartopen(newValue);
			return;
		case VizPackage.POSITION_CONTENT__X:
			setX((Float) newValue);
			return;
		case VizPackage.POSITION_CONTENT__Y:
			setY((Float) newValue);
			return;
		case VizPackage.POSITION_CONTENT__Z:
			setZ((Float) newValue);
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
		case VizPackage.POSITION_CONTENT__SPELLS:
			setSpells((SpellsContent) null);
			return;
		case VizPackage.POSITION_CONTENT__END:
			setEnd(END_EDEFAULT);
			return;
		case VizPackage.POSITION_CONTENT__ENDOPEN:
			setEndopen(ENDOPEN_EDEFAULT);
			return;
		case VizPackage.POSITION_CONTENT__START:
			setStart(START_EDEFAULT);
			return;
		case VizPackage.POSITION_CONTENT__STARTOPEN:
			setStartopen(STARTOPEN_EDEFAULT);
			return;
		case VizPackage.POSITION_CONTENT__X:
			unsetX();
			return;
		case VizPackage.POSITION_CONTENT__Y:
			unsetY();
			return;
		case VizPackage.POSITION_CONTENT__Z:
			unsetZ();
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
		case VizPackage.POSITION_CONTENT__SPELLS:
			return spells != null;
		case VizPackage.POSITION_CONTENT__END:
			return END_EDEFAULT == null ? end != null : !END_EDEFAULT.equals(end);
		case VizPackage.POSITION_CONTENT__ENDOPEN:
			return ENDOPEN_EDEFAULT == null ? endopen != null : !ENDOPEN_EDEFAULT.equals(endopen);
		case VizPackage.POSITION_CONTENT__START:
			return START_EDEFAULT == null ? start != null : !START_EDEFAULT.equals(start);
		case VizPackage.POSITION_CONTENT__STARTOPEN:
			return STARTOPEN_EDEFAULT == null ? startopen != null : !STARTOPEN_EDEFAULT.equals(startopen);
		case VizPackage.POSITION_CONTENT__X:
			return isSetX();
		case VizPackage.POSITION_CONTENT__Y:
			return isSetY();
		case VizPackage.POSITION_CONTENT__Z:
			return isSetZ();
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
		result.append(", start: ");
		result.append(start);
		result.append(", startopen: ");
		result.append(startopen);
		result.append(", x: ");
		if (xESet) {
			result.append(x);
		}
		else {
			result.append("<unset>");
		}
		result.append(", y: ");
		if (yESet) {
			result.append(y);
		}
		else {
			result.append("<unset>");
		}
		result.append(", z: ");
		if (zESet) {
			result.append(z);
		}
		else {
			result.append("<unset>");
		}
		result.append(')');
		return result.toString();
	}

} //PositionContentImpl
