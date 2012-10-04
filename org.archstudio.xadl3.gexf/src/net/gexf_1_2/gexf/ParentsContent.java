/**
 */
package net.gexf_1_2.gexf;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parents Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.gexf_1_2.gexf.ParentsContent#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.gexf_1_2.gexf.GexfPackage#getParentsContent()
 * @model extendedMetaData="name='parents-content' kind='elementOnly'"
 * @generated
 */
public interface ParentsContent extends EObject
{
  /**
   * Returns the value of the '<em><b>Parent</b></em>' containment reference list.
   * The list contents are of type {@link net.gexf_1_2.gexf.ParentType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parent</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parent</em>' containment reference list.
   * @see net.gexf_1_2.gexf.GexfPackage#getParentsContent_Parent()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='parent' namespace='##targetNamespace'"
   * @generated
   */
  EList<ParentType> getParent();

} // ParentsContent
