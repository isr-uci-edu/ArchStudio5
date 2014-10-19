/**
 */
package org.archstudio.xadl3.structure_3_0;

import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Sub Structure</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * This type allows the specification of sub-structure for a brick (component or connector); that is, the internal
 * structure of a Brick can also contain its own structure of components and connectors. This allows for hierarchical
 * (and possibly recursive) composition.
 * 
 * A subStructure consists of a link to the inner structure, along with zero or more interface mappings. Interface
 * mappings map (or connect) interfaces on the outer components and connectors with interfaces on the inner components
 * and connectors.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.structure_3_0.SubStructure#getInnerStructureLink <em>Inner Structure Link</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.SubStructure#getInterfaceMapping <em>Interface Mapping</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.SubStructure#getExt <em>Ext</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.SubStructure#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getSubStructure()
 * @model extendedMetaData="name='SubStructure' kind='elementOnly'"
 * @generated
 */
public interface SubStructure extends EObject {
	/**
	 * Returns the value of the '<em><b>Inner Structure Link</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inner Structure Link</em>' reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Inner Structure Link</em>' reference.
	 * @see #setInnerStructureLink(Structure)
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getSubStructure_InnerStructureLink()
	 * @model resolveProxies="false" required="true"
	 *        extendedMetaData="kind='element' name='innerStructureLink' namespace='##targetNamespace'"
	 * @generated
	 */
	Structure getInnerStructureLink();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.structure_3_0.SubStructure#getInnerStructureLink
	 * <em>Inner Structure Link</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Inner Structure Link</em>' reference.
	 * @see #getInnerStructureLink()
	 * @generated
	 */
	void setInnerStructureLink(Structure value);

	/**
	 * Returns the value of the '<em><b>Interface Mapping</b></em>' containment reference list. The list contents are of
	 * type {@link org.archstudio.xadl3.structure_3_0.InterfaceMapping}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface Mapping</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Interface Mapping</em>' containment reference list.
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getSubStructure_InterfaceMapping()
	 * @model containment="true" extendedMetaData="kind='element' name='interfaceMapping' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<InterfaceMapping> getInterfaceMapping();

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
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getSubStructure_Ext()
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
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getSubStructure_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.structure_3_0.SubStructure#getId <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // SubStructure
