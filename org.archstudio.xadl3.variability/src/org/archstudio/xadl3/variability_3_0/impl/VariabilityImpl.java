/**
 */
package org.archstudio.xadl3.variability_3_0.impl;

import java.util.Collection;

import org.archstudio.xadl3.variability_3_0.ChangeSet;
import org.archstudio.xadl3.variability_3_0.Relationship;
import org.archstudio.xadl3.variability_3_0.Variability;
import org.archstudio.xadl3.variability_3_0.Variability_3_0Package;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variability</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.archstudio.xadl3.variability_3_0.impl.VariabilityImpl#getActiveChangeSet <em>Active Change Set</em>}</li>
 *   <li>{@link org.archstudio.xadl3.variability_3_0.impl.VariabilityImpl#getAppliedChangeSets <em>Applied Change Sets</em>}</li>
 *   <li>{@link org.archstudio.xadl3.variability_3_0.impl.VariabilityImpl#getChangeSet <em>Change Set</em>}</li>
 *   <li>{@link org.archstudio.xadl3.variability_3_0.impl.VariabilityImpl#getRelationship <em>Relationship</em>}</li>
 *   <li>{@link org.archstudio.xadl3.variability_3_0.impl.VariabilityImpl#isOverview <em>Overview</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariabilityImpl extends EObjectImpl implements Variability
{
  /**
   * The cached value of the '{@link #getActiveChangeSet() <em>Active Change Set</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getActiveChangeSet()
   * @generated
   * @ordered
   */
  protected ChangeSet activeChangeSet;

  /**
   * The cached value of the '{@link #getAppliedChangeSets() <em>Applied Change Sets</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAppliedChangeSets()
   * @generated
   * @ordered
   */
  protected EList<ChangeSet> appliedChangeSets;

  /**
   * The cached value of the '{@link #getChangeSet() <em>Change Set</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getChangeSet()
   * @generated
   * @ordered
   */
  protected EList<ChangeSet> changeSet;

  /**
   * The cached value of the '{@link #getRelationship() <em>Relationship</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRelationship()
   * @generated
   * @ordered
   */
  protected EList<Relationship> relationship;

  /**
   * The default value of the '{@link #isOverview() <em>Overview</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOverview()
   * @generated
   * @ordered
   */
  protected static final boolean OVERVIEW_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isOverview() <em>Overview</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOverview()
   * @generated
   * @ordered
   */
  protected boolean overview = OVERVIEW_EDEFAULT;

  /**
   * This is true if the Overview attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean overviewESet;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected VariabilityImpl()
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
    return Variability_3_0Package.Literals.VARIABILITY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ChangeSet getActiveChangeSet()
  {
    return activeChangeSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setActiveChangeSet(ChangeSet newActiveChangeSet)
  {
    ChangeSet oldActiveChangeSet = activeChangeSet;
    activeChangeSet = newActiveChangeSet;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Variability_3_0Package.VARIABILITY__ACTIVE_CHANGE_SET, oldActiveChangeSet, activeChangeSet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ChangeSet> getAppliedChangeSets()
  {
    if (appliedChangeSets == null)
    {
      appliedChangeSets = new EObjectEList<ChangeSet>(ChangeSet.class, this, Variability_3_0Package.VARIABILITY__APPLIED_CHANGE_SETS);
    }
    return appliedChangeSets;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ChangeSet> getChangeSet()
  {
    if (changeSet == null)
    {
      changeSet = new EObjectContainmentEList<ChangeSet>(ChangeSet.class, this, Variability_3_0Package.VARIABILITY__CHANGE_SET);
    }
    return changeSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Relationship> getRelationship()
  {
    if (relationship == null)
    {
      relationship = new EObjectContainmentEList<Relationship>(Relationship.class, this, Variability_3_0Package.VARIABILITY__RELATIONSHIP);
    }
    return relationship;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isOverview()
  {
    return overview;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOverview(boolean newOverview)
  {
    boolean oldOverview = overview;
    overview = newOverview;
    boolean oldOverviewESet = overviewESet;
    overviewESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Variability_3_0Package.VARIABILITY__OVERVIEW, oldOverview, overview, !oldOverviewESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetOverview()
  {
    boolean oldOverview = overview;
    boolean oldOverviewESet = overviewESet;
    overview = OVERVIEW_EDEFAULT;
    overviewESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, Variability_3_0Package.VARIABILITY__OVERVIEW, oldOverview, OVERVIEW_EDEFAULT, oldOverviewESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetOverview()
  {
    return overviewESet;
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
      case Variability_3_0Package.VARIABILITY__CHANGE_SET:
        return ((InternalEList<?>)getChangeSet()).basicRemove(otherEnd, msgs);
      case Variability_3_0Package.VARIABILITY__RELATIONSHIP:
        return ((InternalEList<?>)getRelationship()).basicRemove(otherEnd, msgs);
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
      case Variability_3_0Package.VARIABILITY__ACTIVE_CHANGE_SET:
        return getActiveChangeSet();
      case Variability_3_0Package.VARIABILITY__APPLIED_CHANGE_SETS:
        return getAppliedChangeSets();
      case Variability_3_0Package.VARIABILITY__CHANGE_SET:
        return getChangeSet();
      case Variability_3_0Package.VARIABILITY__RELATIONSHIP:
        return getRelationship();
      case Variability_3_0Package.VARIABILITY__OVERVIEW:
        return isOverview();
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
      case Variability_3_0Package.VARIABILITY__ACTIVE_CHANGE_SET:
        setActiveChangeSet((ChangeSet)newValue);
        return;
      case Variability_3_0Package.VARIABILITY__APPLIED_CHANGE_SETS:
        getAppliedChangeSets().clear();
        getAppliedChangeSets().addAll((Collection<? extends ChangeSet>)newValue);
        return;
      case Variability_3_0Package.VARIABILITY__CHANGE_SET:
        getChangeSet().clear();
        getChangeSet().addAll((Collection<? extends ChangeSet>)newValue);
        return;
      case Variability_3_0Package.VARIABILITY__RELATIONSHIP:
        getRelationship().clear();
        getRelationship().addAll((Collection<? extends Relationship>)newValue);
        return;
      case Variability_3_0Package.VARIABILITY__OVERVIEW:
        setOverview((Boolean)newValue);
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
      case Variability_3_0Package.VARIABILITY__ACTIVE_CHANGE_SET:
        setActiveChangeSet((ChangeSet)null);
        return;
      case Variability_3_0Package.VARIABILITY__APPLIED_CHANGE_SETS:
        getAppliedChangeSets().clear();
        return;
      case Variability_3_0Package.VARIABILITY__CHANGE_SET:
        getChangeSet().clear();
        return;
      case Variability_3_0Package.VARIABILITY__RELATIONSHIP:
        getRelationship().clear();
        return;
      case Variability_3_0Package.VARIABILITY__OVERVIEW:
        unsetOverview();
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
      case Variability_3_0Package.VARIABILITY__ACTIVE_CHANGE_SET:
        return activeChangeSet != null;
      case Variability_3_0Package.VARIABILITY__APPLIED_CHANGE_SETS:
        return appliedChangeSets != null && !appliedChangeSets.isEmpty();
      case Variability_3_0Package.VARIABILITY__CHANGE_SET:
        return changeSet != null && !changeSet.isEmpty();
      case Variability_3_0Package.VARIABILITY__RELATIONSHIP:
        return relationship != null && !relationship.isEmpty();
      case Variability_3_0Package.VARIABILITY__OVERVIEW:
        return isSetOverview();
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
    result.append(" (overview: ");
    if (overviewESet) result.append(overview); else result.append("<unset>");
    result.append(')');
    return result.toString();
  }

} //VariabilityImpl
