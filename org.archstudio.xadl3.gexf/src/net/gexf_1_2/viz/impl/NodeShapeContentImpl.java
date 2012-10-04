/**
 */
package net.gexf_1_2.viz.impl;

import net.gexf_1_2.gexf.SpellsContent;

import net.gexf_1_2.viz.NodeShapeContent;
import net.gexf_1_2.viz.NodeShapeType;
import net.gexf_1_2.viz.VizPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node Shape Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.gexf_1_2.viz.impl.NodeShapeContentImpl#getSpells <em>Spells</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.impl.NodeShapeContentImpl#getEnd <em>End</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.impl.NodeShapeContentImpl#getEndopen <em>Endopen</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.impl.NodeShapeContentImpl#getStart <em>Start</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.impl.NodeShapeContentImpl#getStartopen <em>Startopen</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.impl.NodeShapeContentImpl#getUri <em>Uri</em>}</li>
 *   <li>{@link net.gexf_1_2.viz.impl.NodeShapeContentImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeShapeContentImpl extends EObjectImpl implements NodeShapeContent
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
   * The default value of the '{@link #getUri() <em>Uri</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUri()
   * @generated
   * @ordered
   */
  protected static final String URI_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getUri() <em>Uri</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUri()
   * @generated
   * @ordered
   */
  protected String uri = URI_EDEFAULT;

  /**
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected static final NodeShapeType VALUE_EDEFAULT = NodeShapeType.DISC;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected NodeShapeType value = VALUE_EDEFAULT;

  /**
   * This is true if the Value attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean valueESet;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NodeShapeContentImpl()
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
    return VizPackage.Literals.NODE_SHAPE_CONTENT;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, VizPackage.NODE_SHAPE_CONTENT__SPELLS, oldSpells, newSpells);
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
        msgs = ((InternalEObject)spells).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - VizPackage.NODE_SHAPE_CONTENT__SPELLS, null, msgs);
      if (newSpells != null)
        msgs = ((InternalEObject)newSpells).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - VizPackage.NODE_SHAPE_CONTENT__SPELLS, null, msgs);
      msgs = basicSetSpells(newSpells, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.NODE_SHAPE_CONTENT__SPELLS, newSpells, newSpells));
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
      eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.NODE_SHAPE_CONTENT__END, oldEnd, end));
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
      eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.NODE_SHAPE_CONTENT__ENDOPEN, oldEndopen, endopen));
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
      eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.NODE_SHAPE_CONTENT__START, oldStart, start));
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
      eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.NODE_SHAPE_CONTENT__STARTOPEN, oldStartopen, startopen));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getUri()
  {
    return uri;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUri(String newUri)
  {
    String oldUri = uri;
    uri = newUri;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.NODE_SHAPE_CONTENT__URI, oldUri, uri));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NodeShapeType getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(NodeShapeType newValue)
  {
    NodeShapeType oldValue = value;
    value = newValue == null ? VALUE_EDEFAULT : newValue;
    boolean oldValueESet = valueESet;
    valueESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, VizPackage.NODE_SHAPE_CONTENT__VALUE, oldValue, value, !oldValueESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetValue()
  {
    NodeShapeType oldValue = value;
    boolean oldValueESet = valueESet;
    value = VALUE_EDEFAULT;
    valueESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, VizPackage.NODE_SHAPE_CONTENT__VALUE, oldValue, VALUE_EDEFAULT, oldValueESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetValue()
  {
    return valueESet;
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
      case VizPackage.NODE_SHAPE_CONTENT__SPELLS:
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
      case VizPackage.NODE_SHAPE_CONTENT__SPELLS:
        return getSpells();
      case VizPackage.NODE_SHAPE_CONTENT__END:
        return getEnd();
      case VizPackage.NODE_SHAPE_CONTENT__ENDOPEN:
        return getEndopen();
      case VizPackage.NODE_SHAPE_CONTENT__START:
        return getStart();
      case VizPackage.NODE_SHAPE_CONTENT__STARTOPEN:
        return getStartopen();
      case VizPackage.NODE_SHAPE_CONTENT__URI:
        return getUri();
      case VizPackage.NODE_SHAPE_CONTENT__VALUE:
        return getValue();
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
      case VizPackage.NODE_SHAPE_CONTENT__SPELLS:
        setSpells((SpellsContent)newValue);
        return;
      case VizPackage.NODE_SHAPE_CONTENT__END:
        setEnd(newValue);
        return;
      case VizPackage.NODE_SHAPE_CONTENT__ENDOPEN:
        setEndopen(newValue);
        return;
      case VizPackage.NODE_SHAPE_CONTENT__START:
        setStart(newValue);
        return;
      case VizPackage.NODE_SHAPE_CONTENT__STARTOPEN:
        setStartopen(newValue);
        return;
      case VizPackage.NODE_SHAPE_CONTENT__URI:
        setUri((String)newValue);
        return;
      case VizPackage.NODE_SHAPE_CONTENT__VALUE:
        setValue((NodeShapeType)newValue);
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
      case VizPackage.NODE_SHAPE_CONTENT__SPELLS:
        setSpells((SpellsContent)null);
        return;
      case VizPackage.NODE_SHAPE_CONTENT__END:
        setEnd(END_EDEFAULT);
        return;
      case VizPackage.NODE_SHAPE_CONTENT__ENDOPEN:
        setEndopen(ENDOPEN_EDEFAULT);
        return;
      case VizPackage.NODE_SHAPE_CONTENT__START:
        setStart(START_EDEFAULT);
        return;
      case VizPackage.NODE_SHAPE_CONTENT__STARTOPEN:
        setStartopen(STARTOPEN_EDEFAULT);
        return;
      case VizPackage.NODE_SHAPE_CONTENT__URI:
        setUri(URI_EDEFAULT);
        return;
      case VizPackage.NODE_SHAPE_CONTENT__VALUE:
        unsetValue();
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
      case VizPackage.NODE_SHAPE_CONTENT__SPELLS:
        return spells != null;
      case VizPackage.NODE_SHAPE_CONTENT__END:
        return END_EDEFAULT == null ? end != null : !END_EDEFAULT.equals(end);
      case VizPackage.NODE_SHAPE_CONTENT__ENDOPEN:
        return ENDOPEN_EDEFAULT == null ? endopen != null : !ENDOPEN_EDEFAULT.equals(endopen);
      case VizPackage.NODE_SHAPE_CONTENT__START:
        return START_EDEFAULT == null ? start != null : !START_EDEFAULT.equals(start);
      case VizPackage.NODE_SHAPE_CONTENT__STARTOPEN:
        return STARTOPEN_EDEFAULT == null ? startopen != null : !STARTOPEN_EDEFAULT.equals(startopen);
      case VizPackage.NODE_SHAPE_CONTENT__URI:
        return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
      case VizPackage.NODE_SHAPE_CONTENT__VALUE:
        return isSetValue();
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
    result.append(" (end: ");
    result.append(end);
    result.append(", endopen: ");
    result.append(endopen);
    result.append(", start: ");
    result.append(start);
    result.append(", startopen: ");
    result.append(startopen);
    result.append(", uri: ");
    result.append(uri);
    result.append(", value: ");
    if (valueESet) result.append(value); else result.append("<unset>");
    result.append(')');
    return result.toString();
  }

} //NodeShapeContentImpl
