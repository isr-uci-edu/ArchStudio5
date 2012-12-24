/**
 */
package net.gexf_1_2.gexf;

import net.gexf_1_2.viz.ColorContent;
import net.gexf_1_2.viz.PositionContent;
import net.gexf_1_2.viz.SizeContent;
import net.gexf_1_2.viz.ThicknessContent;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Document Root</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getMixed <em>Mixed</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix
 * Map</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getXSISchemaLocation <em>XSI Schema
 * Location</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getAttribute <em>Attribute</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getAttributes <em>Attributes</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getAttvalue <em>Attvalue</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getAttvalues <em>Attvalues</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getColor <em>Color</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getCreator <em>Creator</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getDefault <em>Default</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getDescription <em>Description
 * </em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getEdge <em>Edge</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getEdges <em>Edges</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getGexf <em>Gexf</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getGraph <em>Graph</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getKeywords <em>Keywords</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getMeta <em>Meta</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getNode <em>Node</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getNodes <em>Nodes</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getOptions <em>Options</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getParent <em>Parent</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getParents <em>Parents</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getPosition <em>Position</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getSize <em>Size</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getSpell <em>Spell</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getSpells <em>Spells</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.DocumentRoot#getThickness <em>Thickness</em>}</li>
 * </ul>
 * </p>
 * 
 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list. The list
	 * contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
	 *        many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map. The key
	 * is of type {@link java.lang.String}, and the value is of type
	 * {@link java.lang.String}, <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_XMLNSPrefixMap()
	 * @model mapType=
	 *        "org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 *        transient="true"
	 *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
	 * @generated
	 */
	EMap<String, String> getXMLNSPrefixMap();

	/**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map. The
	 * key is of type {@link java.lang.String}, and the value is of type
	 * {@link java.lang.String}, <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_XSISchemaLocation()
	 * @model mapType=
	 *        "org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 *        transient="true"
	 *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
	EMap<String, String> getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' containment
	 * reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Attribute</em>' containment reference.
	 * @see #setAttribute(AttributeContent)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Attribute()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='attribute' namespace='##targetNamespace'"
	 * @generated
	 */
	AttributeContent getAttribute();

	/**
	 * Sets the value of the '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getAttribute <em>Attribute</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Attribute</em>' containment
	 *            reference.
	 * @see #getAttribute()
	 * @generated
	 */
	void setAttribute(AttributeContent value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment
	 * reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Attributes</em>' containment reference.
	 * @see #setAttributes(AttributesContent)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Attributes()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='attributes' namespace='##targetNamespace'"
	 * @generated
	 */
	AttributesContent getAttributes();

	/**
	 * Sets the value of the '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getAttributes <em>Attributes</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Attributes</em>' containment
	 *            reference.
	 * @see #getAttributes()
	 * @generated
	 */
	void setAttributes(AttributesContent value);

	/**
	 * Returns the value of the '<em><b>Attvalue</b></em>' containment
	 * reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attvalue</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Attvalue</em>' containment reference.
	 * @see #setAttvalue(AttvalueType)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Attvalue()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='attvalue' namespace='##targetNamespace'"
	 * @generated
	 */
	AttvalueType getAttvalue();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.DocumentRoot#getAttvalue
	 * <em>Attvalue</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Attvalue</em>' containment
	 *            reference.
	 * @see #getAttvalue()
	 * @generated
	 */
	void setAttvalue(AttvalueType value);

	/**
	 * Returns the value of the '<em><b>Attvalues</b></em>' containment
	 * reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attvalues</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Attvalues</em>' containment reference.
	 * @see #setAttvalues(AttvaluesContent)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Attvalues()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='attvalues' namespace='##targetNamespace'"
	 * @generated
	 */
	AttvaluesContent getAttvalues();

	/**
	 * Sets the value of the '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getAttvalues <em>Attvalues</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Attvalues</em>' containment
	 *            reference.
	 * @see #getAttvalues()
	 * @generated
	 */
	void setAttvalues(AttvaluesContent value);

	/**
	 * Returns the value of the '<em><b>Color</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Color</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Color</em>' containment reference.
	 * @see #setColor(ColorContent)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Color()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='color' namespace='##targetNamespace'"
	 * @generated
	 */
	ColorContent getColor();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.DocumentRoot#getColor
	 * <em>Color</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Color</em>' containment reference.
	 * @see #getColor()
	 * @generated
	 */
	void setColor(ColorContent value);

	/**
	 * Returns the value of the '<em><b>Creator</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Creator</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Creator</em>' attribute.
	 * @see #setCreator(String)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Creator()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData
	 *        ="kind='element' name='creator' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCreator();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.DocumentRoot#getCreator
	 * <em>Creator</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Creator</em>' attribute.
	 * @see #getCreator()
	 * @generated
	 */
	void setCreator(String value);

	/**
	 * Returns the value of the '<em><b>Default</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Default</em>' attribute.
	 * @see #setDefault(String)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Default()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData
	 *        ="kind='element' name='default' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDefault();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.DocumentRoot#getDefault
	 * <em>Default</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Default</em>' attribute.
	 * @see #getDefault()
	 * @generated
	 */
	void setDefault(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Description()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData=
	 *        "kind='element' name='description' namespace='##targetNamespace'"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getDescription
	 * <em>Description</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Edge</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edge</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Edge</em>' containment reference.
	 * @see #setEdge(EdgeContent)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Edge()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='edge' namespace='##targetNamespace'"
	 * @generated
	 */
	EdgeContent getEdge();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.DocumentRoot#getEdge
	 * <em>Edge</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Edge</em>' containment reference.
	 * @see #getEdge()
	 * @generated
	 */
	void setEdge(EdgeContent value);

	/**
	 * Returns the value of the '<em><b>Edges</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edges</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Edges</em>' containment reference.
	 * @see #setEdges(EdgesContent)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Edges()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='edges' namespace='##targetNamespace'"
	 * @generated
	 */
	EdgesContent getEdges();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.DocumentRoot#getEdges
	 * <em>Edges</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Edges</em>' containment reference.
	 * @see #getEdges()
	 * @generated
	 */
	void setEdges(EdgesContent value);

	/**
	 * Returns the value of the '<em><b>Gexf</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gexf</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Gexf</em>' containment reference.
	 * @see #setGexf(GexfContent)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Gexf()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='gexf' namespace='##targetNamespace'"
	 * @generated
	 */
	GexfContent getGexf();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.DocumentRoot#getGexf
	 * <em>Gexf</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Gexf</em>' containment reference.
	 * @see #getGexf()
	 * @generated
	 */
	void setGexf(GexfContent value);

	/**
	 * Returns the value of the '<em><b>Graph</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Graph</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Graph</em>' containment reference.
	 * @see #setGraph(GraphContent)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Graph()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='graph' namespace='##targetNamespace'"
	 * @generated
	 */
	GraphContent getGraph();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.DocumentRoot#getGraph
	 * <em>Graph</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Graph</em>' containment reference.
	 * @see #getGraph()
	 * @generated
	 */
	void setGraph(GraphContent value);

	/**
	 * Returns the value of the '<em><b>Keywords</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Keywords</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Keywords</em>' attribute.
	 * @see #setKeywords(String)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Keywords()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData
	 *        ="kind='element' name='keywords' namespace='##targetNamespace'"
	 * @generated
	 */
	String getKeywords();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.DocumentRoot#getKeywords
	 * <em>Keywords</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Keywords</em>' attribute.
	 * @see #getKeywords()
	 * @generated
	 */
	void setKeywords(String value);

	/**
	 * Returns the value of the '<em><b>Meta</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Meta</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Meta</em>' containment reference.
	 * @see #setMeta(MetaContent)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Meta()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='meta' namespace='##targetNamespace'"
	 * @generated
	 */
	MetaContent getMeta();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.DocumentRoot#getMeta
	 * <em>Meta</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Meta</em>' containment reference.
	 * @see #getMeta()
	 * @generated
	 */
	void setMeta(MetaContent value);

	/**
	 * Returns the value of the '<em><b>Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Node</em>' containment reference.
	 * @see #setNode(NodeContent)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Node()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='node' namespace='##targetNamespace'"
	 * @generated
	 */
	NodeContent getNode();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.DocumentRoot#getNode
	 * <em>Node</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Node</em>' containment reference.
	 * @see #getNode()
	 * @generated
	 */
	void setNode(NodeContent value);

	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodes</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Nodes</em>' containment reference.
	 * @see #setNodes(NodesContent)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Nodes()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='nodes' namespace='##targetNamespace'"
	 * @generated
	 */
	NodesContent getNodes();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.DocumentRoot#getNodes
	 * <em>Nodes</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Nodes</em>' containment reference.
	 * @see #getNodes()
	 * @generated
	 */
	void setNodes(NodesContent value);

	/**
	 * Returns the value of the '<em><b>Options</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Options</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Options</em>' attribute.
	 * @see #setOptions(String)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Options()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData
	 *        ="kind='element' name='options' namespace='##targetNamespace'"
	 * @generated
	 */
	String getOptions();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.DocumentRoot#getOptions
	 * <em>Options</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Options</em>' attribute.
	 * @see #getOptions()
	 * @generated
	 */
	void setOptions(String value);

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Parent</em>' containment reference.
	 * @see #setParent(ParentType)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Parent()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='parent' namespace='##targetNamespace'"
	 * @generated
	 */
	ParentType getParent();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.DocumentRoot#getParent
	 * <em>Parent</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Parent</em>' containment reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(ParentType value);

	/**
	 * Returns the value of the '<em><b>Parents</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parents</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Parents</em>' containment reference.
	 * @see #setParents(ParentsContent)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Parents()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='parents' namespace='##targetNamespace'"
	 * @generated
	 */
	ParentsContent getParents();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.DocumentRoot#getParents
	 * <em>Parents</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Parents</em>' containment reference.
	 * @see #getParents()
	 * @generated
	 */
	void setParents(ParentsContent value);

	/**
	 * Returns the value of the '<em><b>Position</b></em>' containment
	 * reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Position</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Position</em>' containment reference.
	 * @see #setPosition(PositionContent)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Position()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='position' namespace='##targetNamespace'"
	 * @generated
	 */
	PositionContent getPosition();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.DocumentRoot#getPosition
	 * <em>Position</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Position</em>' containment
	 *            reference.
	 * @see #getPosition()
	 * @generated
	 */
	void setPosition(PositionContent value);

	/**
	 * Returns the value of the '<em><b>Size</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Size</em>' containment reference.
	 * @see #setSize(SizeContent)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Size()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='size' namespace='##targetNamespace'"
	 * @generated
	 */
	SizeContent getSize();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.DocumentRoot#getSize
	 * <em>Size</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Size</em>' containment reference.
	 * @see #getSize()
	 * @generated
	 */
	void setSize(SizeContent value);

	/**
	 * Returns the value of the '<em><b>Spell</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spell</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Spell</em>' containment reference.
	 * @see #setSpell(SpellType)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Spell()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='spell' namespace='##targetNamespace'"
	 * @generated
	 */
	SpellType getSpell();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.DocumentRoot#getSpell
	 * <em>Spell</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Spell</em>' containment reference.
	 * @see #getSpell()
	 * @generated
	 */
	void setSpell(SpellType value);

	/**
	 * Returns the value of the '<em><b>Spells</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spells</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Spells</em>' containment reference.
	 * @see #setSpells(SpellsContent)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Spells()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='spells' namespace='##targetNamespace'"
	 * @generated
	 */
	SpellsContent getSpells();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.DocumentRoot#getSpells
	 * <em>Spells</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Spells</em>' containment reference.
	 * @see #getSpells()
	 * @generated
	 */
	void setSpells(SpellsContent value);

	/**
	 * Returns the value of the '<em><b>Thickness</b></em>' containment
	 * reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Thickness</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Thickness</em>' containment reference.
	 * @see #setThickness(ThicknessContent)
	 * @see net.gexf_1_2.gexf.GexfPackage#getDocumentRoot_Thickness()
	 * @model containment="true" upper="-2" transient="true" volatile="true"
	 *        derived="true" extendedMetaData=
	 *        "kind='element' name='thickness' namespace='##targetNamespace'"
	 * @generated
	 */
	ThicknessContent getThickness();

	/**
	 * Sets the value of the '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getThickness <em>Thickness</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Thickness</em>' containment
	 *            reference.
	 * @see #getThickness()
	 * @generated
	 */
	void setThickness(ThicknessContent value);

} // DocumentRoot
