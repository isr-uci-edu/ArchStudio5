/**
 */
package org.archstudio.xadl3.xadlcore_3_0;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Document Root</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.xadlcore_3_0.DocumentRoot#getMixed <em>Mixed</em>}</li>
 * <li>{@link org.archstudio.xadl3.xadlcore_3_0.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 * <li>{@link org.archstudio.xadl3.xadlcore_3_0.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 * <li>{@link org.archstudio.xadl3.xadlcore_3_0.DocumentRoot#getTopLevelElement <em>Top Level Element</em>}</li>
 * <li>{@link org.archstudio.xadl3.xadlcore_3_0.DocumentRoot#getXADL <em>XADL</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package#getDocumentRoot()
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
	 * @see org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package#getDocumentRoot_Mixed()
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
	 * @see org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package#getDocumentRoot_XMLNSPrefixMap()
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
	 * @see org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package#getDocumentRoot_XSISchemaLocation()
	 * @model mapType=
	 *        "org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 *        transient="true" extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
	EMap<String, String> getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>Top Level Element</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc -->
	 * 
	 * This is a placeholder element for xADL top-level elements (i.e., elements that are direct children of the
	 * document root element. Elements that are intended to be top-level elements should be in the topLevelElement
	 * substitutionGroup to declare their intent to be used as top-level elements.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Top Level Element</em>' containment reference.
	 * @see #setTopLevelElement(EObject)
	 * @see org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package#getDocumentRoot_TopLevelElement()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='topLevelElement' namespace='##targetNamespace'"
	 * @generated
	 */
	EObject getTopLevelElement();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.xadlcore_3_0.DocumentRoot#getTopLevelElement
	 * <em>Top Level Element</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Top Level Element</em>' containment reference.
	 * @see #getTopLevelElement()
	 * @generated
	 */
	void setTopLevelElement(EObject value);

	/**
	 * Returns the value of the '<em><b>XADL</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * --> <!-- begin-model-doc -->
	 * 
	 * This is the root element for all xADL documents. It is a container element, so it can contain a number of
	 * top-level xADL elements via the use of a substitution group.
	 * 
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>XADL</em>' containment reference.
	 * @see #setXADL(XADLType)
	 * @see org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package#getDocumentRoot_XADL()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='xADL' namespace='##targetNamespace'"
	 * @generated
	 */
	XADLType getXADL();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.xadlcore_3_0.DocumentRoot#getXADL <em>XADL</em>}' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>XADL</em>' containment reference.
	 * @see #getXADL()
	 * @generated
	 */
	void setXADL(XADLType value);

} // DocumentRoot
