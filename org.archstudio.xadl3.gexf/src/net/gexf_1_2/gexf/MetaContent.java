/**
 */
package net.gexf_1_2.gexf;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Meta Content</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.MetaContent#getGroup <em>Group</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.MetaContent#getCreator <em>Creator</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.MetaContent#getKeywords <em>Keywords</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.MetaContent#getDescription <em>Description</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.MetaContent#getLastmodifieddate <em>Lastmodifieddate</em>}</li>
 * </ul>
 * </p>
 * 
 * @see net.gexf_1_2.gexf.GexfPackage#getMetaContent()
 * @model extendedMetaData="name='meta-content' kind='elementOnly'"
 * @generated
 */
public interface MetaContent extends EObject {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list. The list contents are of type
	 * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see net.gexf_1_2.gexf.GexfPackage#getMetaContent_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Creator</b></em>' attribute list. The list contents are of type
	 * {@link java.lang.String}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Creator</em>' attribute list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Creator</em>' attribute list.
	 * @see net.gexf_1_2.gexf.GexfPackage#getMetaContent_Creator()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true"
	 *        derived="true"
	 *        extendedMetaData="kind='element' name='creator' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<String> getCreator();

	/**
	 * Returns the value of the '<em><b>Keywords</b></em>' attribute list. The list contents are of type
	 * {@link java.lang.String}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Keywords</em>' attribute list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Keywords</em>' attribute list.
	 * @see net.gexf_1_2.gexf.GexfPackage#getMetaContent_Keywords()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true"
	 *        derived="true"
	 *        extendedMetaData="kind='element' name='keywords' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<String> getKeywords();

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute list. The list contents are of type
	 * {@link java.lang.String}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Description</em>' attribute list.
	 * @see net.gexf_1_2.gexf.GexfPackage#getMetaContent_Description()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true"
	 *        derived="true"
	 *        extendedMetaData="kind='element' name='description' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<String> getDescription();

	/**
	 * Returns the value of the '<em><b>Lastmodifieddate</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lastmodifieddate</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Lastmodifieddate</em>' attribute.
	 * @see #setLastmodifieddate(XMLGregorianCalendar)
	 * @see net.gexf_1_2.gexf.GexfPackage#getMetaContent_Lastmodifieddate()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Date" extendedMetaData="kind='attribute' name='lastmodifieddate'"
	 * @generated
	 */
	XMLGregorianCalendar getLastmodifieddate();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.MetaContent#getLastmodifieddate <em>Lastmodifieddate</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Lastmodifieddate</em>' attribute.
	 * @see #getLastmodifieddate()
	 * @generated
	 */
	void setLastmodifieddate(XMLGregorianCalendar value);

} // MetaContent
