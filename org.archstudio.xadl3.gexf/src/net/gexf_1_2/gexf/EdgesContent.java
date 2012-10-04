/**
 */
package net.gexf_1_2.gexf;

import java.math.BigInteger;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edges Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.gexf_1_2.gexf.EdgesContent#getEdge <em>Edge</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.EdgesContent#getCount <em>Count</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.gexf_1_2.gexf.GexfPackage#getEdgesContent()
 * @model extendedMetaData="name='edges-content' kind='elementOnly'"
 * @generated
 */
public interface EdgesContent extends EObject
{
  /**
   * Returns the value of the '<em><b>Edge</b></em>' containment reference list.
   * The list contents are of type {@link net.gexf_1_2.gexf.EdgeContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Edge</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Edge</em>' containment reference list.
   * @see net.gexf_1_2.gexf.GexfPackage#getEdgesContent_Edge()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='edge' namespace='##targetNamespace'"
   * @generated
   */
  EList<EdgeContent> getEdge();

  /**
   * Returns the value of the '<em><b>Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Count</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Count</em>' attribute.
   * @see #setCount(BigInteger)
   * @see net.gexf_1_2.gexf.GexfPackage#getEdgesContent_Count()
   * @model dataType="org.eclipse.emf.ecore.xml.type.NonNegativeInteger"
   *        extendedMetaData="kind='attribute' name='count'"
   * @generated
   */
  BigInteger getCount();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.EdgesContent#getCount <em>Count</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Count</em>' attribute.
   * @see #getCount()
   * @generated
   */
  void setCount(BigInteger value);

} // EdgesContent
