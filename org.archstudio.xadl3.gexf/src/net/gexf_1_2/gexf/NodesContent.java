/**
 */
package net.gexf_1_2.gexf;

import java.math.BigInteger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Nodes Content</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.NodesContent#getNode <em>Node</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.NodesContent#getCount <em>Count</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.gexf_1_2.gexf.GexfPackage#getNodesContent()
 * @model extendedMetaData="name='nodes-content' kind='elementOnly'"
 * @generated
 */
public interface NodesContent extends EObject {
	/**
	 * Returns the value of the '<em><b>Node</b></em>' containment reference list. The list contents are of type
	 * {@link net.gexf_1_2.gexf.NodeContent}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Node</em>' containment reference list.
	 * @see net.gexf_1_2.gexf.GexfPackage#getNodesContent_Node()
	 * @model containment="true" extendedMetaData="kind='element' name='node' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<NodeContent> getNode();

	/**
	 * Returns the value of the '<em><b>Count</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Count</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Count</em>' attribute.
	 * @see #setCount(BigInteger)
	 * @see net.gexf_1_2.gexf.GexfPackage#getNodesContent_Count()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NonNegativeInteger"
	 *        extendedMetaData="kind='attribute' name='count'"
	 * @generated
	 */
	BigInteger getCount();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.NodesContent#getCount <em>Count</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Count</em>' attribute.
	 * @see #getCount()
	 * @generated
	 */
	void setCount(BigInteger value);

} // NodesContent
