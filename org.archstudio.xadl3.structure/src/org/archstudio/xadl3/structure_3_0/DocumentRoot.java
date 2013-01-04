/**
 */
package org.archstudio.xadl3.structure_3_0;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Document Root</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.structure_3_0.DocumentRoot#getMixed <em>Mixed</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.DocumentRoot#getStructure <em>Structure</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list. The list contents are of type
	 * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getDocumentRoot_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map. The key is of type {@link java.lang.String}, and
	 * the value is of type {@link java.lang.String}, <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getDocumentRoot_XMLNSPrefixMap()
	 * @model mapType=
	 *        "org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 *        transient="true" extendedMetaData="kind='attribute' name='xmlns:prefix'"
	 * @generated
	 */
	EMap<String, String> getXMLNSPrefixMap();

	/**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map. The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String}, <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getDocumentRoot_XSISchemaLocation()
	 * @model mapType=
	 *        "org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 *        transient="true" extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
	EMap<String, String> getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>Structure</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * This element is a top-level xADL element containing an architectural structure. A single document may contain
	 * more than one structure, and structures may be connected to one another via links in substructure relationships.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Structure</em>' containment reference.
	 * @see #setStructure(Structure)
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getDocumentRoot_Structure()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true" extendedMetaData=
	 *        "kind='element' name='structure' namespace='##targetNamespace' affiliation='http://www.archstudio.org/xadl3/schemas/xadlcore-3.0.xsd#topLevelElement'"
	 * @generated
	 */
	Structure getStructure();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.structure_3_0.DocumentRoot#getStructure <em>Structure</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Structure</em>' containment reference.
	 * @see #getStructure()
	 * @generated
	 */
	void setStructure(Structure value);

} // DocumentRoot
