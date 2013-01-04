/**
 */
package org.archstudio.xadl3.structure_3_0;

import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Interface Mapping</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <!-- begin-model-doc -->
 * 
 * Interface mappings are used in the specification of sub-structure for a Brick (Component or Connector). An interface
 * mapping maps (or connects) an interface on the outer Brick to an interface on an inner Brick. The outerInterfaceLink
 * is a link to the interface on the outer brick. The innerInterfaceLink is a link to the interface on the inner brick.
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.structure_3_0.InterfaceMapping#getOuterInterfaceLink <em>Outer Interface Link</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.InterfaceMapping#getInnerInterfaceLink <em>Inner Interface Link</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.InterfaceMapping#getExt <em>Ext</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.InterfaceMapping#getId <em>Id</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.InterfaceMapping#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getInterfaceMapping()
 * @model extendedMetaData="name='InterfaceMapping' kind='elementOnly'"
 * @generated
 */
public interface InterfaceMapping extends EObject {
	/**
	 * Returns the value of the '<em><b>Outer Interface Link</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outer Interface Link</em>' reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Outer Interface Link</em>' reference.
	 * @see #setOuterInterfaceLink(Interface)
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getInterfaceMapping_OuterInterfaceLink()
	 * @model resolveProxies="false" required="true"
	 *        extendedMetaData="kind='element' name='outerInterfaceLink' namespace='##targetNamespace'"
	 * @generated
	 */
	Interface getOuterInterfaceLink();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.structure_3_0.InterfaceMapping#getOuterInterfaceLink
	 * <em>Outer Interface Link</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Outer Interface Link</em>' reference.
	 * @see #getOuterInterfaceLink()
	 * @generated
	 */
	void setOuterInterfaceLink(Interface value);

	/**
	 * Returns the value of the '<em><b>Inner Interface Link</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inner Interface Link</em>' reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Inner Interface Link</em>' reference.
	 * @see #setInnerInterfaceLink(Interface)
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getInterfaceMapping_InnerInterfaceLink()
	 * @model resolveProxies="false" required="true"
	 *        extendedMetaData="kind='element' name='innerInterfaceLink' namespace='##targetNamespace'"
	 * @generated
	 */
	Interface getInnerInterfaceLink();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.structure_3_0.InterfaceMapping#getInnerInterfaceLink
	 * <em>Inner Interface Link</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Inner Interface Link</em>' reference.
	 * @see #getInnerInterfaceLink()
	 * @generated
	 */
	void setInnerInterfaceLink(Interface value);

	/**
	 * Returns the value of the '<em><b>Ext</b></em>' containment reference list. The list contents are of type
	 * {@link org.archstudio.xadl3.xadlcore_3_0.Extension}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ext</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Ext</em>' containment reference list.
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getInterfaceMapping_Ext()
	 * @model containment="true" extendedMetaData="kind='element' name='ext' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Extension> getExt();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getInterfaceMapping_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.structure_3_0.InterfaceMapping#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getInterfaceMapping_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.structure_3_0.InterfaceMapping#getName <em>Name</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // InterfaceMapping
