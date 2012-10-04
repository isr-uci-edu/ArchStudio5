/**
 */
package org.archstudio.xadl3.variability_3_0;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variability</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.archstudio.xadl3.variability_3_0.Variability#getActiveChangeSet <em>Active Change Set</em>}</li>
 *   <li>{@link org.archstudio.xadl3.variability_3_0.Variability#getAppliedChangeSets <em>Applied Change Sets</em>}</li>
 *   <li>{@link org.archstudio.xadl3.variability_3_0.Variability#getChangeSet <em>Change Set</em>}</li>
 *   <li>{@link org.archstudio.xadl3.variability_3_0.Variability#getRelationship <em>Relationship</em>}</li>
 *   <li>{@link org.archstudio.xadl3.variability_3_0.Variability#isOverview <em>Overview</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#getVariability()
 * @model extendedMetaData="name='Variability' kind='elementOnly'"
 * @generated
 */
public interface Variability extends EObject
{
  /**
   * Returns the value of the '<em><b>Active Change Set</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Active Change Set</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Active Change Set</em>' reference.
   * @see #setActiveChangeSet(ChangeSet)
   * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#getVariability_ActiveChangeSet()
   * @model resolveProxies="false"
   *        extendedMetaData="kind='element' name='activeChangeSet' namespace='##targetNamespace'"
   * @generated
   */
  ChangeSet getActiveChangeSet();

  /**
   * Sets the value of the '{@link org.archstudio.xadl3.variability_3_0.Variability#getActiveChangeSet <em>Active Change Set</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Active Change Set</em>' reference.
   * @see #getActiveChangeSet()
   * @generated
   */
  void setActiveChangeSet(ChangeSet value);

  /**
   * Returns the value of the '<em><b>Applied Change Sets</b></em>' reference list.
   * The list contents are of type {@link org.archstudio.xadl3.variability_3_0.ChangeSet}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Applied Change Sets</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Applied Change Sets</em>' reference list.
   * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#getVariability_AppliedChangeSets()
   * @model resolveProxies="false" required="true"
   *        extendedMetaData="kind='element' name='appliedChangeSets' namespace='##targetNamespace'"
   * @generated
   */
  EList<ChangeSet> getAppliedChangeSets();

  /**
   * Returns the value of the '<em><b>Change Set</b></em>' containment reference list.
   * The list contents are of type {@link org.archstudio.xadl3.variability_3_0.ChangeSet}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Change Set</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Change Set</em>' containment reference list.
   * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#getVariability_ChangeSet()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='changeSet' namespace='##targetNamespace'"
   * @generated
   */
  EList<ChangeSet> getChangeSet();

  /**
   * Returns the value of the '<em><b>Relationship</b></em>' containment reference list.
   * The list contents are of type {@link org.archstudio.xadl3.variability_3_0.Relationship}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Relationship</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Relationship</em>' containment reference list.
   * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#getVariability_Relationship()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='relationship' namespace='##targetNamespace'"
   * @generated
   */
  EList<Relationship> getRelationship();

  /**
   * Returns the value of the '<em><b>Overview</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Overview</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Overview</em>' attribute.
   * @see #isSetOverview()
   * @see #unsetOverview()
   * @see #setOverview(boolean)
   * @see org.archstudio.xadl3.variability_3_0.Variability_3_0Package#getVariability_Overview()
   * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
   *        extendedMetaData="kind='attribute' name='overview' namespace='##targetNamespace'"
   * @generated
   */
  boolean isOverview();

  /**
   * Sets the value of the '{@link org.archstudio.xadl3.variability_3_0.Variability#isOverview <em>Overview</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Overview</em>' attribute.
   * @see #isSetOverview()
   * @see #unsetOverview()
   * @see #isOverview()
   * @generated
   */
  void setOverview(boolean value);

  /**
   * Unsets the value of the '{@link org.archstudio.xadl3.variability_3_0.Variability#isOverview <em>Overview</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetOverview()
   * @see #isOverview()
   * @see #setOverview(boolean)
   * @generated
   */
  void unsetOverview();

  /**
   * Returns whether the value of the '{@link org.archstudio.xadl3.variability_3_0.Variability#isOverview <em>Overview</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Overview</em>' attribute is set.
   * @see #unsetOverview()
   * @see #isOverview()
   * @see #setOverview(boolean)
   * @generated
   */
  boolean isSetOverview();

} // Variability
