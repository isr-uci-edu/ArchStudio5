/**
 */
package net.gexf_1_2.gexf;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Content</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> Tree <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.GexfContent#getMeta <em>Meta</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.GexfContent#getGraph <em>Graph</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.GexfContent#getVariant <em>Variant</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.GexfContent#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 * 
 * @see net.gexf_1_2.gexf.GexfPackage#getGexfContent()
 * @model extendedMetaData="name='gexf-content' kind='elementOnly'"
 * @generated
 */
public interface GexfContent extends EObject {
	/**
	 * Returns the value of the '<em><b>Meta</b></em>' containment reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Meta</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Meta</em>' containment reference.
	 * @see #setMeta(MetaContent)
	 * @see net.gexf_1_2.gexf.GexfPackage#getGexfContent_Meta()
	 * @model containment="true" extendedMetaData="kind='element' name='meta' namespace='##targetNamespace'"
	 * @generated
	 */
	MetaContent getMeta();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.GexfContent#getMeta <em>Meta</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Meta</em>' containment reference.
	 * @see #getMeta()
	 * @generated
	 */
	void setMeta(MetaContent value);

	/**
	 * Returns the value of the '<em><b>Graph</b></em>' containment reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Graph</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Graph</em>' containment reference.
	 * @see #setGraph(GraphContent)
	 * @see net.gexf_1_2.gexf.GexfPackage#getGexfContent_Graph()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='graph' namespace='##targetNamespace'"
	 * @generated
	 */
	GraphContent getGraph();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.GexfContent#getGraph <em>Graph</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Graph</em>' containment reference.
	 * @see #getGraph()
	 * @generated
	 */
	void setGraph(GraphContent value);

	/**
	 * Returns the value of the '<em><b>Variant</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variant</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Variant</em>' attribute.
	 * @see #setVariant(String)
	 * @see net.gexf_1_2.gexf.GexfPackage#getGexfContent_Variant()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData="kind='attribute' name='variant'"
	 * @generated
	 */
	String getVariant();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.GexfContent#getVariant <em>Variant</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Variant</em>' attribute.
	 * @see #getVariant()
	 * @generated
	 */
	void setVariant(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute. The literals are from the enumeration
	 * {@link net.gexf_1_2.gexf.VersionType}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see net.gexf_1_2.gexf.VersionType
	 * @see #isSetVersion()
	 * @see #unsetVersion()
	 * @see #setVersion(VersionType)
	 * @see net.gexf_1_2.gexf.GexfPackage#getGexfContent_Version()
	 * @model unsettable="true" required="true" extendedMetaData="kind='attribute' name='version'"
	 * @generated
	 */
	VersionType getVersion();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.GexfContent#getVersion <em>Version</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Version</em>' attribute.
	 * @see net.gexf_1_2.gexf.VersionType
	 * @see #isSetVersion()
	 * @see #unsetVersion()
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(VersionType value);

	/**
	 * Unsets the value of the '{@link net.gexf_1_2.gexf.GexfContent#getVersion <em>Version</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetVersion()
	 * @see #getVersion()
	 * @see #setVersion(VersionType)
	 * @generated
	 */
	void unsetVersion();

	/**
	 * Returns whether the value of the '{@link net.gexf_1_2.gexf.GexfContent#getVersion <em>Version</em>}' attribute is
	 * set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Version</em>' attribute is set.
	 * @see #unsetVersion()
	 * @see #getVersion()
	 * @see #setVersion(VersionType)
	 * @generated
	 */
	boolean isSetVersion();

} // GexfContent
