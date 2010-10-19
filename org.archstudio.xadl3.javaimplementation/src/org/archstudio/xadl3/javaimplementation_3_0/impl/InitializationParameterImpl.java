/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.archstudio.xadl3.javaimplementation_3_0.impl;

import java.util.Collection;

import org.archstudio.xadl3.javaimplementation_3_0.InitializationParameter;
import org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package;

import org.archstudio.xadl3.xadlcore_3_0.Extension;

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
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Initialization Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.archstudio.xadl3.javaimplementation_3_0.impl.InitializationParameterImpl#getExt <em>Ext</em>}</li>
 *   <li>{@link org.archstudio.xadl3.javaimplementation_3_0.impl.InitializationParameterImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.archstudio.xadl3.javaimplementation_3_0.impl.InitializationParameterImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.archstudio.xadl3.javaimplementation_3_0.impl.InitializationParameterImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InitializationParameterImpl extends EObjectImpl implements InitializationParameter
{
  /**
   * The cached value of the '{@link #getExt() <em>Ext</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExt()
   * @generated
   * @ordered
   */
  protected EList<Extension> ext;

  /**
   * The default value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected static final String ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected String id = ID_EDEFAULT;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected static final String VALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected String value = VALUE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected InitializationParameterImpl()
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
    return Javaimplementation_3_0Package.Literals.INITIALIZATION_PARAMETER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Extension> getExt()
  {
    if (ext == null)
    {
      ext = new EObjectContainmentEList<Extension>(Extension.class, this, Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__EXT);
    }
    return ext;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getId()
  {
    return id;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setId(String newId)
  {
    String oldId = id;
    id = newId;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__ID, oldId, id));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(String newValue)
  {
    String oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__VALUE, oldValue, value));
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
      case Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__EXT:
        return ((InternalEList<?>)getExt()).basicRemove(otherEnd, msgs);
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
      case Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__EXT:
        return getExt();
      case Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__ID:
        return getId();
      case Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__NAME:
        return getName();
      case Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__VALUE:
        return getValue();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__EXT:
        getExt().clear();
        getExt().addAll((Collection<? extends Extension>)newValue);
        return;
      case Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__ID:
        setId((String)newValue);
        return;
      case Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__NAME:
        setName((String)newValue);
        return;
      case Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__VALUE:
        setValue((String)newValue);
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
      case Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__EXT:
        getExt().clear();
        return;
      case Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__ID:
        setId(ID_EDEFAULT);
        return;
      case Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__NAME:
        setName(NAME_EDEFAULT);
        return;
      case Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__VALUE:
        setValue(VALUE_EDEFAULT);
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
      case Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__EXT:
        return ext != null && !ext.isEmpty();
      case Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case Javaimplementation_3_0Package.INITIALIZATION_PARAMETER__VALUE:
        return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
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
    result.append(" (id: ");
    result.append(id);
    result.append(", name: ");
    result.append(name);
    result.append(", value: ");
    result.append(value);
    result.append(')');
    return result.toString();
  }

} //InitializationParameterImpl
