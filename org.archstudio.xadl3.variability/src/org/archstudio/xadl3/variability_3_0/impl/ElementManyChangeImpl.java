/**
 */
package org.archstudio.xadl3.variability_3_0.impl;

import java.util.Collection;

import org.archstudio.xadl3.variability_3_0.ElementChange;
import org.archstudio.xadl3.variability_3_0.ElementManyChange;
import org.archstudio.xadl3.variability_3_0.Variability_3_0Package;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element Many Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.archstudio.xadl3.variability_3_0.impl.ElementManyChangeImpl#getChange <em>Change</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElementManyChangeImpl extends ChangeImpl implements ElementManyChange
{
  /**
   * The cached value of the '{@link #getChange() <em>Change</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getChange()
   * @generated
   * @ordered
   */
  protected EList<ElementChange> change;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ElementManyChangeImpl()
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
    return Variability_3_0Package.Literals.ELEMENT_MANY_CHANGE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ElementChange> getChange()
  {
    if (change == null)
    {
      change = new EObjectContainmentEList<ElementChange>(ElementChange.class, this, Variability_3_0Package.ELEMENT_MANY_CHANGE__CHANGE);
    }
    return change;
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
      case Variability_3_0Package.ELEMENT_MANY_CHANGE__CHANGE:
        return ((InternalEList<?>)getChange()).basicRemove(otherEnd, msgs);
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
      case Variability_3_0Package.ELEMENT_MANY_CHANGE__CHANGE:
        return getChange();
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
      case Variability_3_0Package.ELEMENT_MANY_CHANGE__CHANGE:
        getChange().clear();
        getChange().addAll((Collection<? extends ElementChange>)newValue);
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
      case Variability_3_0Package.ELEMENT_MANY_CHANGE__CHANGE:
        getChange().clear();
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
      case Variability_3_0Package.ELEMENT_MANY_CHANGE__CHANGE:
        return change != null && !change.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ElementManyChangeImpl
