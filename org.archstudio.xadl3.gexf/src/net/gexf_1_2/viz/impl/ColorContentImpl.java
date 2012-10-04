/**
 */
package net.gexf_1_2.viz.impl;

import java.math.BigInteger;

import net.gexf_1_2.gexf.SpellsContent;

import net.gexf_1_2.viz.ColorContent;
import net.gexf_1_2.viz.VizPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Color Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.gexf_1_2.viz.impl.ColorContentImpl#getSpells <em>Spells</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.impl.ColorContentImpl#getA <em>A</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.impl.ColorContentImpl#getB <em>B</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.impl.ColorContentImpl#getEnd <em>End</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.impl.ColorContentImpl#getEndopen <em>Endopen</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.impl.ColorContentImpl#getG <em>G</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.impl.ColorContentImpl#getR <em>R</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.impl.ColorContentImpl#getStart <em>Start</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.impl.ColorContentImpl#getStartopen <em>Startopen</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ColorContentImpl extends EObjectImpl implements ColorContent
{
  /**
   * The cached value of the '{@link #getSpells() <em>Spells</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSpells()
   * @generated
   * @ordered
   */
  protected SpellsContent spells;

  /**
   * The default value of the '{@link #getA() <em>A</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getA()
   * @generated
   * @ordered
   */
  protected static final float A_EDEFAULT = 0.0F;

  /**
   * The cached value of the '{@link #getA() <em>A</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getA()
   * @generated
   * @ordered
   */
  protected float a = A_EDEFAULT;

  /**
   * This is true if the A attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean aESet;

  /**
   * The default value of the '{@link #getB() <em>B</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getB()
   * @generated
   * @ordered
   */
  protected static final BigInteger B_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getB() <em>B</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getB()
   * @generated
   * @ordered
   */
  protected BigInteger b = B_EDEFAULT;

  /**
   * The default value of the '{@link #getEnd() <em>End</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEnd()
   * @generated
   * @ordered
   */
  protected static final Object END_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEnd() <em>End</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEnd()
   * @generated
   * @ordered
   */
  protected Object end = END_EDEFAULT;

  /**
   * The default value of the '{@link #getEndopen() <em>Endopen</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEndopen()
   * @generated
   * @ordered
   */
  protected static final Object ENDOPEN_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEndopen() <em>Endopen</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEndopen()
   * @generated
   * @ordered
   */
  protected Object endopen = ENDOPEN_EDEFAULT;

  /**
   * The default value of the '{@link #getG() <em>G</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getG()
   * @generated
   * @ordered
   */
  protected static final BigInteger G_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getG() <em>G</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getG()
   * @generated
   * @ordered
   */
  protected BigInteger g = G_EDEFAULT;

  /**
   * The default value of the '{@link #getR() <em>R</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getR()
   * @generated
   * @ordered
   */
  protected static final BigInteger R_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getR() <em>R</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getR()
   * @generated
   * @ordered
   */
  protected BigInteger r = R_EDEFAULT;

  /**
   * The default value of the '{@link #getStart() <em>Start</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStart()
   * @generated
   * @ordered
   */
  protected static final Object START_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getStart() <em>Start</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStart()
   * @generated
   * @ordered
   */
  protected Object start = START_EDEFAULT;

  /**
   * The default value of the '{@link #getStartopen() <em>Startopen</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStartopen()
   * @generated
   * @ordered
   */
  protected static final Object STARTOPEN_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getStartopen() <em>Startopen</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStartopen()
   * @generated
   * @ordered
   */
  protected Object startopen = STARTOPEN_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ColorContentImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return VizPackage.Literals.COLOR_CONTENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SpellsContent getSpells()
  {
    return spells;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSpells(SpellsContent newSpells, NotificationChain msgs)
  {
    SpellsContent oldSpells = spells;
    spells = newSpells;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, VizPackage.COLOR_CONTENT__SPELLS, oldSpells, newSpells);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSpells(SpellsContent newSpells)
  {
    if (newSpells != spells)
    {
      NotificationChain msgs = null;
      if (spells != null)
        msgs = ((InternalEObject)spells).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - VizPackage.COLOR_CONTENT__SPELLS, null, msgs);
      if (newSpells != null)
        msgs = ((InternalEObject)newSpells).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - VizPackage.COLOR_CONTENT__SPELLS, null, msgs);
      msgs = basicSetSpells(newSpells, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.COLOR_CONTENT__SPELLS, newSpells, newSpells));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public float getA()
  {
    return a;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setA(float newA)
  {
    float oldA = a;
    a = newA;
    boolean oldAESet = aESet;
    aESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.COLOR_CONTENT__A, oldA, a, !oldAESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetA()
  {
    float oldA = a;
    boolean oldAESet = aESet;
    a = A_EDEFAULT;
    aESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, VizPackage.COLOR_CONTENT__A, oldA, A_EDEFAULT, oldAESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetA()
  {
    return aESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigInteger getB()
  {
    return b;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setB(BigInteger newB)
  {
    BigInteger oldB = b;
    b = newB;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.COLOR_CONTENT__B, oldB, b));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getEnd()
  {
    return end;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEnd(Object newEnd)
  {
    Object oldEnd = end;
    end = newEnd;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.COLOR_CONTENT__END, oldEnd, end));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getEndopen()
  {
    return endopen;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEndopen(Object newEndopen)
  {
    Object oldEndopen = endopen;
    endopen = newEndopen;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.COLOR_CONTENT__ENDOPEN, oldEndopen, endopen));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigInteger getG()
  {
    return g;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setG(BigInteger newG)
  {
    BigInteger oldG = g;
    g = newG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.COLOR_CONTENT__G, oldG, g));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigInteger getR()
  {
    return r;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setR(BigInteger newR)
  {
    BigInteger oldR = r;
    r = newR;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.COLOR_CONTENT__R, oldR, r));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getStart()
  {
    return start;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStart(Object newStart)
  {
    Object oldStart = start;
    start = newStart;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.COLOR_CONTENT__START, oldStart, start));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getStartopen()
  {
    return startopen;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStartopen(Object newStartopen)
  {
    Object oldStartopen = startopen;
    startopen = newStartopen;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.COLOR_CONTENT__STARTOPEN, oldStartopen, startopen));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case VizPackage.COLOR_CONTENT__SPELLS:
        return basicSetSpells(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case VizPackage.COLOR_CONTENT__SPELLS:
        return getSpells();
      case VizPackage.COLOR_CONTENT__A:
        return getA();
      case VizPackage.COLOR_CONTENT__B:
        return getB();
      case VizPackage.COLOR_CONTENT__END:
        return getEnd();
      case VizPackage.COLOR_CONTENT__ENDOPEN:
        return getEndopen();
      case VizPackage.COLOR_CONTENT__G:
        return getG();
      case VizPackage.COLOR_CONTENT__R:
        return getR();
      case VizPackage.COLOR_CONTENT__START:
        return getStart();
      case VizPackage.COLOR_CONTENT__STARTOPEN:
        return getStartopen();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case VizPackage.COLOR_CONTENT__SPELLS:
        setSpells((SpellsContent)newValue);
        return;
      case VizPackage.COLOR_CONTENT__A:
        setA((Float)newValue);
        return;
      case VizPackage.COLOR_CONTENT__B:
        setB((BigInteger)newValue);
        return;
      case VizPackage.COLOR_CONTENT__END:
        setEnd(newValue);
        return;
      case VizPackage.COLOR_CONTENT__ENDOPEN:
        setEndopen(newValue);
        return;
      case VizPackage.COLOR_CONTENT__G:
        setG((BigInteger)newValue);
        return;
      case VizPackage.COLOR_CONTENT__R:
        setR((BigInteger)newValue);
        return;
      case VizPackage.COLOR_CONTENT__START:
        setStart(newValue);
        return;
      case VizPackage.COLOR_CONTENT__STARTOPEN:
        setStartopen(newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case VizPackage.COLOR_CONTENT__SPELLS:
        setSpells((SpellsContent)null);
        return;
      case VizPackage.COLOR_CONTENT__A:
        unsetA();
        return;
      case VizPackage.COLOR_CONTENT__B:
        setB(B_EDEFAULT);
        return;
      case VizPackage.COLOR_CONTENT__END:
        setEnd(END_EDEFAULT);
        return;
      case VizPackage.COLOR_CONTENT__ENDOPEN:
        setEndopen(ENDOPEN_EDEFAULT);
        return;
      case VizPackage.COLOR_CONTENT__G:
        setG(G_EDEFAULT);
        return;
      case VizPackage.COLOR_CONTENT__R:
        setR(R_EDEFAULT);
        return;
      case VizPackage.COLOR_CONTENT__START:
        setStart(START_EDEFAULT);
        return;
      case VizPackage.COLOR_CONTENT__STARTOPEN:
        setStartopen(STARTOPEN_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case VizPackage.COLOR_CONTENT__SPELLS:
        return spells != null;
      case VizPackage.COLOR_CONTENT__A:
        return isSetA();
      case VizPackage.COLOR_CONTENT__B:
        return B_EDEFAULT == null ? b != null : !B_EDEFAULT.equals(b);
      case VizPackage.COLOR_CONTENT__END:
        return END_EDEFAULT == null ? end != null : !END_EDEFAULT.equals(end);
      case VizPackage.COLOR_CONTENT__ENDOPEN:
        return ENDOPEN_EDEFAULT == null ? endopen != null : !ENDOPEN_EDEFAULT.equals(endopen);
      case VizPackage.COLOR_CONTENT__G:
        return G_EDEFAULT == null ? g != null : !G_EDEFAULT.equals(g);
      case VizPackage.COLOR_CONTENT__R:
        return R_EDEFAULT == null ? r != null : !R_EDEFAULT.equals(r);
      case VizPackage.COLOR_CONTENT__START:
        return START_EDEFAULT == null ? start != null : !START_EDEFAULT.equals(start);
      case VizPackage.COLOR_CONTENT__STARTOPEN:
        return STARTOPEN_EDEFAULT == null ? startopen != null : !STARTOPEN_EDEFAULT.equals(startopen);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (a: ");
    if (aESet) result.append(a); else result.append("<unset>");
    result.append(", b: ");
    result.append(b);
    result.append(", end: ");
    result.append(end);
    result.append(", endopen: ");
    result.append(endopen);
    result.append(", g: ");
    result.append(g);
    result.append(", r: ");
    result.append(r);
    result.append(", start: ");
    result.append(start);
    result.append(", startopen: ");
    result.append(startopen);
    result.append(')');
    return result.toString();
  }

} //ColorContentImpl
