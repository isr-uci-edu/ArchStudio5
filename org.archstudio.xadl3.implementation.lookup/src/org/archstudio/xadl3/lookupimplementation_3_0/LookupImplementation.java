/**
 */
package org.archstudio.xadl3.lookupimplementation_3_0;

import org.archstudio.xadl3.implementation_3_0.Implementation;
import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Lookup Implementation</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * This type defines a Lookup-based implementation.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.lookupimplementation_3_0.LookupImplementation#getExt <em>Ext</em>}</li>
 * <li>{@link org.archstudio.xadl3.lookupimplementation_3_0.LookupImplementation#getLookup <em>Lookup</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.xadl3.lookupimplementation_3_0.Lookupimplementation_3_0Package#getLookupImplementation()
 * @model extendedMetaData="name='LookupImplementation' kind='elementOnly'"
 * @generated
 */
public interface LookupImplementation extends Implementation {
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
	 * @see org.archstudio.xadl3.lookupimplementation_3_0.Lookupimplementation_3_0Package#getLookupImplementation_Ext()
	 * @model containment="true" extendedMetaData="kind='element' name='ext' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Extension> getExt();

	/**
	 * Returns the value of the '<em><b>Lookup</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lookup</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Lookup</em>' attribute.
	 * @see #setLookup(String)
	 * @see org.archstudio.xadl3.lookupimplementation_3_0.Lookupimplementation_3_0Package#getLookupImplementation_Lookup()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='lookup' namespace='##targetNamespace'"
	 * @generated
	 */
	String getLookup();

	/**
	 * Sets the value of the '{@link org.archstudio.xadl3.lookupimplementation_3_0.LookupImplementation#getLookup
	 * <em>Lookup</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Lookup</em>' attribute.
	 * @see #getLookup()
	 * @generated
	 */
	void setLookup(String value);

} // LookupImplementation
