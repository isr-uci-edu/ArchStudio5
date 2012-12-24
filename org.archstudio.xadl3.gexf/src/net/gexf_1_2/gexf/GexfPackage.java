/**
 */
package net.gexf_1_2.gexf;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see net.gexf_1_2.gexf.GexfFactory
 * @model kind="package"
 * @generated
 */
public interface GexfPackage extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "gexf";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.gexf.net/1.2draft";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "_1";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	GexfPackage eINSTANCE = net.gexf_1_2.gexf.impl.GexfPackageImpl.init();

	/**
	 * The meta object id for the '
	 * {@link net.gexf_1_2.gexf.impl.AttributeContentImpl
	 * <em>Attribute Content</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.impl.AttributeContentImpl
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getAttributeContent()
	 * @generated
	 */
	int ATTRIBUTE_CONTENT = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_CONTENT__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_CONTENT__DEFAULT = 1;

	/**
	 * The feature id for the '<em><b>Options</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_CONTENT__OPTIONS = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_CONTENT__ID = 3;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_CONTENT__TITLE = 4;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_CONTENT__TYPE = 5;

	/**
	 * The number of structural features of the '<em>Attribute Content</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_CONTENT_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '
	 * {@link net.gexf_1_2.gexf.impl.AttributesContentImpl
	 * <em>Attributes Content</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.impl.AttributesContentImpl
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getAttributesContent()
	 * @generated
	 */
	int ATTRIBUTES_CONTENT = 1;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTES_CONTENT__ATTRIBUTE = 0;

	/**
	 * The feature id for the '<em><b>Class</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTES_CONTENT__CLASS = 1;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTES_CONTENT__END = 2;

	/**
	 * The feature id for the '<em><b>Endopen</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTES_CONTENT__ENDOPEN = 3;

	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTES_CONTENT__MODE = 4;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTES_CONTENT__START = 5;

	/**
	 * The feature id for the '<em><b>Startopen</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTES_CONTENT__STARTOPEN = 6;

	/**
	 * The number of structural features of the '<em>Attributes Content</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTES_CONTENT_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '
	 * {@link net.gexf_1_2.gexf.impl.AttvaluesContentImpl
	 * <em>Attvalues Content</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.impl.AttvaluesContentImpl
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getAttvaluesContent()
	 * @generated
	 */
	int ATTVALUES_CONTENT = 2;

	/**
	 * The feature id for the '<em><b>Attvalue</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTVALUES_CONTENT__ATTVALUE = 0;

	/**
	 * The number of structural features of the '<em>Attvalues Content</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTVALUES_CONTENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '
	 * {@link net.gexf_1_2.gexf.impl.AttvalueTypeImpl <em>Attvalue Type</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.impl.AttvalueTypeImpl
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getAttvalueType()
	 * @generated
	 */
	int ATTVALUE_TYPE = 3;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTVALUE_TYPE__END = 0;

	/**
	 * The feature id for the '<em><b>Endopen</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTVALUE_TYPE__ENDOPEN = 1;

	/**
	 * The feature id for the '<em><b>For</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTVALUE_TYPE__FOR = 2;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTVALUE_TYPE__START = 3;

	/**
	 * The feature id for the '<em><b>Startopen</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTVALUE_TYPE__STARTOPEN = 4;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTVALUE_TYPE__VALUE = 5;

	/**
	 * The number of structural features of the '<em>Attvalue Type</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTVALUE_TYPE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '
	 * {@link net.gexf_1_2.gexf.impl.DocumentRootImpl <em>Document Root</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.impl.DocumentRootImpl
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 4;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ATTRIBUTE = 3;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ATTRIBUTES = 4;

	/**
	 * The feature id for the '<em><b>Attvalue</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ATTVALUE = 5;

	/**
	 * The feature id for the '<em><b>Attvalues</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ATTVALUES = 6;

	/**
	 * The feature id for the '<em><b>Color</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COLOR = 7;

	/**
	 * The feature id for the '<em><b>Creator</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CREATOR = 8;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DEFAULT = 9;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DESCRIPTION = 10;

	/**
	 * The feature id for the '<em><b>Edge</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EDGE = 11;

	/**
	 * The feature id for the '<em><b>Edges</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EDGES = 12;

	/**
	 * The feature id for the '<em><b>Gexf</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__GEXF = 13;

	/**
	 * The feature id for the '<em><b>Graph</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__GRAPH = 14;

	/**
	 * The feature id for the '<em><b>Keywords</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__KEYWORDS = 15;

	/**
	 * The feature id for the '<em><b>Meta</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__META = 16;

	/**
	 * The feature id for the '<em><b>Node</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__NODE = 17;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__NODES = 18;

	/**
	 * The feature id for the '<em><b>Options</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__OPTIONS = 19;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PARENT = 20;

	/**
	 * The feature id for the '<em><b>Parents</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PARENTS = 21;

	/**
	 * The feature id for the '<em><b>Position</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__POSITION = 22;

	/**
	 * The feature id for the '<em><b>Size</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SIZE = 23;

	/**
	 * The feature id for the '<em><b>Spell</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SPELL = 24;

	/**
	 * The feature id for the '<em><b>Spells</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SPELLS = 25;

	/**
	 * The feature id for the '<em><b>Thickness</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__THICKNESS = 26;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 27;

	/**
	 * The meta object id for the '
	 * {@link net.gexf_1_2.gexf.impl.EdgeContentImpl <em>Edge Content</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.impl.EdgeContentImpl
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getEdgeContent()
	 * @generated
	 */
	int EDGE_CONTENT = 5;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGE_CONTENT__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Attvalues</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGE_CONTENT__ATTVALUES = 1;

	/**
	 * The feature id for the '<em><b>Spells</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGE_CONTENT__SPELLS = 2;

	/**
	 * The feature id for the '<em><b>Color</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGE_CONTENT__COLOR = 3;

	/**
	 * The feature id for the '<em><b>Thickness</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGE_CONTENT__THICKNESS = 4;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGE_CONTENT__SHAPE = 5;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGE_CONTENT__END = 6;

	/**
	 * The feature id for the '<em><b>Endopen</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGE_CONTENT__ENDOPEN = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGE_CONTENT__ID = 8;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGE_CONTENT__LABEL = 9;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGE_CONTENT__SOURCE = 10;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGE_CONTENT__START = 11;

	/**
	 * The feature id for the '<em><b>Startopen</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGE_CONTENT__STARTOPEN = 12;

	/**
	 * The feature id for the '<em><b>Target</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGE_CONTENT__TARGET = 13;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGE_CONTENT__TYPE = 14;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGE_CONTENT__WEIGHT = 15;

	/**
	 * The number of structural features of the '<em>Edge Content</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGE_CONTENT_FEATURE_COUNT = 16;

	/**
	 * The meta object id for the '
	 * {@link net.gexf_1_2.gexf.impl.EdgesContentImpl <em>Edges Content</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.impl.EdgesContentImpl
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getEdgesContent()
	 * @generated
	 */
	int EDGES_CONTENT = 6;

	/**
	 * The feature id for the '<em><b>Edge</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGES_CONTENT__EDGE = 0;

	/**
	 * The feature id for the '<em><b>Count</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGES_CONTENT__COUNT = 1;

	/**
	 * The number of structural features of the '<em>Edges Content</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EDGES_CONTENT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '
	 * {@link net.gexf_1_2.gexf.impl.GexfContentImpl <em>Content</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.impl.GexfContentImpl
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getGexfContent()
	 * @generated
	 */
	int GEXF_CONTENT = 7;

	/**
	 * The feature id for the '<em><b>Meta</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GEXF_CONTENT__META = 0;

	/**
	 * The feature id for the '<em><b>Graph</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GEXF_CONTENT__GRAPH = 1;

	/**
	 * The feature id for the '<em><b>Variant</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GEXF_CONTENT__VARIANT = 2;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GEXF_CONTENT__VERSION = 3;

	/**
	 * The number of structural features of the '<em>Content</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GEXF_CONTENT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '
	 * {@link net.gexf_1_2.gexf.impl.GraphContentImpl <em>Graph Content</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.impl.GraphContentImpl
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getGraphContent()
	 * @generated
	 */
	int GRAPH_CONTENT = 8;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONTENT__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONTENT__ATTRIBUTES = 1;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONTENT__NODES = 2;

	/**
	 * The feature id for the '<em><b>Edges</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONTENT__EDGES = 3;

	/**
	 * The feature id for the '<em><b>Defaultedgetype</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONTENT__DEFAULTEDGETYPE = 4;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONTENT__END = 5;

	/**
	 * The feature id for the '<em><b>Endopen</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONTENT__ENDOPEN = 6;

	/**
	 * The feature id for the '<em><b>Idtype</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONTENT__IDTYPE = 7;

	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONTENT__MODE = 8;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONTENT__START = 9;

	/**
	 * The feature id for the '<em><b>Startopen</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONTENT__STARTOPEN = 10;

	/**
	 * The feature id for the '<em><b>Timeformat</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONTENT__TIMEFORMAT = 11;

	/**
	 * The number of structural features of the '<em>Graph Content</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GRAPH_CONTENT_FEATURE_COUNT = 12;

	/**
	 * The meta object id for the '
	 * {@link net.gexf_1_2.gexf.impl.MetaContentImpl <em>Meta Content</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.impl.MetaContentImpl
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getMetaContent()
	 * @generated
	 */
	int META_CONTENT = 9;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int META_CONTENT__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Creator</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int META_CONTENT__CREATOR = 1;

	/**
	 * The feature id for the '<em><b>Keywords</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int META_CONTENT__KEYWORDS = 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int META_CONTENT__DESCRIPTION = 3;

	/**
	 * The feature id for the '<em><b>Lastmodifieddate</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int META_CONTENT__LASTMODIFIEDDATE = 4;

	/**
	 * The number of structural features of the '<em>Meta Content</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int META_CONTENT_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '
	 * {@link net.gexf_1_2.gexf.impl.NodeContentImpl <em>Node Content</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.impl.NodeContentImpl
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getNodeContent()
	 * @generated
	 */
	int NODE_CONTENT = 10;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODE_CONTENT__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Attvalues</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODE_CONTENT__ATTVALUES = 1;

	/**
	 * The feature id for the '<em><b>Spells</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODE_CONTENT__SPELLS = 2;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODE_CONTENT__NODES = 3;

	/**
	 * The feature id for the '<em><b>Edges</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODE_CONTENT__EDGES = 4;

	/**
	 * The feature id for the '<em><b>Parents</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODE_CONTENT__PARENTS = 5;

	/**
	 * The feature id for the '<em><b>Color</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODE_CONTENT__COLOR = 6;

	/**
	 * The feature id for the '<em><b>Position</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODE_CONTENT__POSITION = 7;

	/**
	 * The feature id for the '<em><b>Size</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODE_CONTENT__SIZE = 8;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODE_CONTENT__SHAPE = 9;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODE_CONTENT__END = 10;

	/**
	 * The feature id for the '<em><b>Endopen</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODE_CONTENT__ENDOPEN = 11;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODE_CONTENT__ID = 12;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODE_CONTENT__LABEL = 13;

	/**
	 * The feature id for the '<em><b>Pid</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODE_CONTENT__PID = 14;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODE_CONTENT__START = 15;

	/**
	 * The feature id for the '<em><b>Startopen</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODE_CONTENT__STARTOPEN = 16;

	/**
	 * The number of structural features of the '<em>Node Content</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODE_CONTENT_FEATURE_COUNT = 17;

	/**
	 * The meta object id for the '
	 * {@link net.gexf_1_2.gexf.impl.NodesContentImpl <em>Nodes Content</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.impl.NodesContentImpl
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getNodesContent()
	 * @generated
	 */
	int NODES_CONTENT = 11;

	/**
	 * The feature id for the '<em><b>Node</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODES_CONTENT__NODE = 0;

	/**
	 * The feature id for the '<em><b>Count</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODES_CONTENT__COUNT = 1;

	/**
	 * The number of structural features of the '<em>Nodes Content</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int NODES_CONTENT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '
	 * {@link net.gexf_1_2.gexf.impl.ParentsContentImpl
	 * <em>Parents Content</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.impl.ParentsContentImpl
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getParentsContent()
	 * @generated
	 */
	int PARENTS_CONTENT = 12;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PARENTS_CONTENT__PARENT = 0;

	/**
	 * The number of structural features of the '<em>Parents Content</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PARENTS_CONTENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link net.gexf_1_2.gexf.impl.ParentTypeImpl
	 * <em>Parent Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see net.gexf_1_2.gexf.impl.ParentTypeImpl
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getParentType()
	 * @generated
	 */
	int PARENT_TYPE = 13;

	/**
	 * The feature id for the '<em><b>For</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PARENT_TYPE__FOR = 0;

	/**
	 * The number of structural features of the '<em>Parent Type</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PARENT_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '
	 * {@link net.gexf_1_2.gexf.impl.SpellsContentImpl <em>Spells Content</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.impl.SpellsContentImpl
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getSpellsContent()
	 * @generated
	 */
	int SPELLS_CONTENT = 14;

	/**
	 * The feature id for the '<em><b>Spell</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SPELLS_CONTENT__SPELL = 0;

	/**
	 * The number of structural features of the '<em>Spells Content</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SPELLS_CONTENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link net.gexf_1_2.gexf.impl.SpellTypeImpl
	 * <em>Spell Type</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see net.gexf_1_2.gexf.impl.SpellTypeImpl
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getSpellType()
	 * @generated
	 */
	int SPELL_TYPE = 15;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SPELL_TYPE__END = 0;

	/**
	 * The feature id for the '<em><b>Endopen</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SPELL_TYPE__ENDOPEN = 1;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SPELL_TYPE__START = 2;

	/**
	 * The feature id for the '<em><b>Startopen</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SPELL_TYPE__STARTOPEN = 3;

	/**
	 * The number of structural features of the '<em>Spell Type</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SPELL_TYPE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link net.gexf_1_2.gexf.AttrtypeType
	 * <em>Attrtype Type</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see net.gexf_1_2.gexf.AttrtypeType
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getAttrtypeType()
	 * @generated
	 */
	int ATTRTYPE_TYPE = 16;

	/**
	 * The meta object id for the '{@link net.gexf_1_2.gexf.ClassType
	 * <em>Class Type</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.ClassType
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getClassType()
	 * @generated
	 */
	int CLASS_TYPE = 17;

	/**
	 * The meta object id for the '{@link net.gexf_1_2.gexf.DefaultedgetypeType
	 * <em>Defaultedgetype Type</em>}' enum. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.DefaultedgetypeType
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getDefaultedgetypeType()
	 * @generated
	 */
	int DEFAULTEDGETYPE_TYPE = 18;

	/**
	 * The meta object id for the '{@link net.gexf_1_2.gexf.EdgetypeType
	 * <em>Edgetype Type</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see net.gexf_1_2.gexf.EdgetypeType
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getEdgetypeType()
	 * @generated
	 */
	int EDGETYPE_TYPE = 19;

	/**
	 * The meta object id for the '{@link net.gexf_1_2.gexf.IdtypeType
	 * <em>Idtype Type</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see net.gexf_1_2.gexf.IdtypeType
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getIdtypeType()
	 * @generated
	 */
	int IDTYPE_TYPE = 20;

	/**
	 * The meta object id for the '{@link net.gexf_1_2.gexf.ModeType
	 * <em>Mode Type</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.ModeType
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getModeType()
	 * @generated
	 */
	int MODE_TYPE = 21;

	/**
	 * The meta object id for the '{@link net.gexf_1_2.gexf.TimeformatType
	 * <em>Timeformat Type</em>}' enum. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.TimeformatType
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getTimeformatType()
	 * @generated
	 */
	int TIMEFORMAT_TYPE = 22;

	/**
	 * The meta object id for the '{@link net.gexf_1_2.gexf.VersionType
	 * <em>Version Type</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see net.gexf_1_2.gexf.VersionType
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getVersionType()
	 * @generated
	 */
	int VERSION_TYPE = 23;

	/**
	 * The meta object id for the '<em>Attrtype Type Object</em>' data type.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.AttrtypeType
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getAttrtypeTypeObject()
	 * @generated
	 */
	int ATTRTYPE_TYPE_OBJECT = 24;

	/**
	 * The meta object id for the '<em>Class Type Object</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.ClassType
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getClassTypeObject()
	 * @generated
	 */
	int CLASS_TYPE_OBJECT = 25;

	/**
	 * The meta object id for the '<em>Defaultedgetype Type Object</em>' data
	 * type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.DefaultedgetypeType
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getDefaultedgetypeTypeObject()
	 * @generated
	 */
	int DEFAULTEDGETYPE_TYPE_OBJECT = 26;

	/**
	 * The meta object id for the '<em>Edgetype Type Object</em>' data type.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.EdgetypeType
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getEdgetypeTypeObject()
	 * @generated
	 */
	int EDGETYPE_TYPE_OBJECT = 27;

	/**
	 * The meta object id for the '<em>Id Type</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.lang.Object
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getIdType()
	 * @generated
	 */
	int ID_TYPE = 28;

	/**
	 * The meta object id for the '<em>Idtype Type Object</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.IdtypeType
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getIdtypeTypeObject()
	 * @generated
	 */
	int IDTYPE_TYPE_OBJECT = 29;

	/**
	 * The meta object id for the '<em>Mode Type Object</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.ModeType
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getModeTypeObject()
	 * @generated
	 */
	int MODE_TYPE_OBJECT = 30;

	/**
	 * The meta object id for the '<em>Timeformat Type Object</em>' data type.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.TimeformatType
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getTimeformatTypeObject()
	 * @generated
	 */
	int TIMEFORMAT_TYPE_OBJECT = 31;

	/**
	 * The meta object id for the '<em>Time Type</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.lang.Object
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getTimeType()
	 * @generated
	 */
	int TIME_TYPE = 32;

	/**
	 * The meta object id for the '<em>Version Type Object</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.VersionType
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getVersionTypeObject()
	 * @generated
	 */
	int VERSION_TYPE_OBJECT = 33;

	/**
	 * The meta object id for the '<em>Weight Type</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getWeightType()
	 * @generated
	 */
	int WEIGHT_TYPE = 34;

	/**
	 * The meta object id for the '<em>Weight Type Object</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.lang.Float
	 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getWeightTypeObject()
	 * @generated
	 */
	int WEIGHT_TYPE_OBJECT = 35;

	/**
	 * Returns the meta object for class '
	 * {@link net.gexf_1_2.gexf.AttributeContent <em>Attribute Content</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Attribute Content</em>'.
	 * @see net.gexf_1_2.gexf.AttributeContent
	 * @generated
	 */
	EClass getAttributeContent();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link net.gexf_1_2.gexf.AttributeContent#getGroup <em>Group</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see net.gexf_1_2.gexf.AttributeContent#getGroup()
	 * @see #getAttributeContent()
	 * @generated
	 */
	EAttribute getAttributeContent_Group();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link net.gexf_1_2.gexf.AttributeContent#getDefault <em>Default</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Default</em>'.
	 * @see net.gexf_1_2.gexf.AttributeContent#getDefault()
	 * @see #getAttributeContent()
	 * @generated
	 */
	EAttribute getAttributeContent_Default();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link net.gexf_1_2.gexf.AttributeContent#getOptions <em>Options</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Options</em>'.
	 * @see net.gexf_1_2.gexf.AttributeContent#getOptions()
	 * @see #getAttributeContent()
	 * @generated
	 */
	EAttribute getAttributeContent_Options();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.AttributeContent#getId <em>Id</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see net.gexf_1_2.gexf.AttributeContent#getId()
	 * @see #getAttributeContent()
	 * @generated
	 */
	EAttribute getAttributeContent_Id();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.AttributeContent#getTitle <em>Title</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see net.gexf_1_2.gexf.AttributeContent#getTitle()
	 * @see #getAttributeContent()
	 * @generated
	 */
	EAttribute getAttributeContent_Title();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.AttributeContent#getType <em>Type</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see net.gexf_1_2.gexf.AttributeContent#getType()
	 * @see #getAttributeContent()
	 * @generated
	 */
	EAttribute getAttributeContent_Type();

	/**
	 * Returns the meta object for class '
	 * {@link net.gexf_1_2.gexf.AttributesContent <em>Attributes Content</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Attributes Content</em>'.
	 * @see net.gexf_1_2.gexf.AttributesContent
	 * @generated
	 */
	EClass getAttributesContent();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.AttributesContent#getAttribute
	 * <em>Attribute</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Attribute</em>'.
	 * @see net.gexf_1_2.gexf.AttributesContent#getAttribute()
	 * @see #getAttributesContent()
	 * @generated
	 */
	EReference getAttributesContent_Attribute();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.AttributesContent#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Class</em>'.
	 * @see net.gexf_1_2.gexf.AttributesContent#getClass_()
	 * @see #getAttributesContent()
	 * @generated
	 */
	EAttribute getAttributesContent_Class();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.AttributesContent#getEnd <em>End</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>End</em>'.
	 * @see net.gexf_1_2.gexf.AttributesContent#getEnd()
	 * @see #getAttributesContent()
	 * @generated
	 */
	EAttribute getAttributesContent_End();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.AttributesContent#getEndopen <em>Endopen</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Endopen</em>'.
	 * @see net.gexf_1_2.gexf.AttributesContent#getEndopen()
	 * @see #getAttributesContent()
	 * @generated
	 */
	EAttribute getAttributesContent_Endopen();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.AttributesContent#getMode <em>Mode</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Mode</em>'.
	 * @see net.gexf_1_2.gexf.AttributesContent#getMode()
	 * @see #getAttributesContent()
	 * @generated
	 */
	EAttribute getAttributesContent_Mode();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.AttributesContent#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Start</em>'.
	 * @see net.gexf_1_2.gexf.AttributesContent#getStart()
	 * @see #getAttributesContent()
	 * @generated
	 */
	EAttribute getAttributesContent_Start();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.AttributesContent#getStartopen
	 * <em>Startopen</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Startopen</em>'.
	 * @see net.gexf_1_2.gexf.AttributesContent#getStartopen()
	 * @see #getAttributesContent()
	 * @generated
	 */
	EAttribute getAttributesContent_Startopen();

	/**
	 * Returns the meta object for class '
	 * {@link net.gexf_1_2.gexf.AttvaluesContent <em>Attvalues Content</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Attvalues Content</em>'.
	 * @see net.gexf_1_2.gexf.AttvaluesContent
	 * @generated
	 */
	EClass getAttvaluesContent();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.AttvaluesContent#getAttvalue <em>Attvalue</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Attvalue</em>'.
	 * @see net.gexf_1_2.gexf.AttvaluesContent#getAttvalue()
	 * @see #getAttvaluesContent()
	 * @generated
	 */
	EReference getAttvaluesContent_Attvalue();

	/**
	 * Returns the meta object for class '{@link net.gexf_1_2.gexf.AttvalueType
	 * <em>Attvalue Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Attvalue Type</em>'.
	 * @see net.gexf_1_2.gexf.AttvalueType
	 * @generated
	 */
	EClass getAttvalueType();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.AttvalueType#getEnd <em>End</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>End</em>'.
	 * @see net.gexf_1_2.gexf.AttvalueType#getEnd()
	 * @see #getAttvalueType()
	 * @generated
	 */
	EAttribute getAttvalueType_End();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.AttvalueType#getEndopen <em>Endopen</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Endopen</em>'.
	 * @see net.gexf_1_2.gexf.AttvalueType#getEndopen()
	 * @see #getAttvalueType()
	 * @generated
	 */
	EAttribute getAttvalueType_Endopen();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.AttvalueType#getFor <em>For</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>For</em>'.
	 * @see net.gexf_1_2.gexf.AttvalueType#getFor()
	 * @see #getAttvalueType()
	 * @generated
	 */
	EAttribute getAttvalueType_For();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.AttvalueType#getStart <em>Start</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Start</em>'.
	 * @see net.gexf_1_2.gexf.AttvalueType#getStart()
	 * @see #getAttvalueType()
	 * @generated
	 */
	EAttribute getAttvalueType_Start();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.AttvalueType#getStartopen <em>Startopen</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Startopen</em>'.
	 * @see net.gexf_1_2.gexf.AttvalueType#getStartopen()
	 * @see #getAttvalueType()
	 * @generated
	 */
	EAttribute getAttvalueType_Startopen();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.AttvalueType#getValue <em>Value</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see net.gexf_1_2.gexf.AttvalueType#getValue()
	 * @see #getAttvalueType()
	 * @generated
	 */
	EAttribute getAttvalueType_Value();

	/**
	 * Returns the meta object for class '{@link net.gexf_1_2.gexf.DocumentRoot
	 * <em>Document Root</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getMixed <em>Mixed</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getXMLNSPrefixMap
	 * <em>XMLNS Prefix Map</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getXSISchemaLocation
	 * <em>XSI Schema Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Attribute</em>
	 *         '.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getAttribute()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Attribute();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getAttributes <em>Attributes</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '
	 *         <em>Attributes</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getAttributes()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Attributes();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getAttvalue <em>Attvalue</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Attvalue</em>
	 *         '.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getAttvalue()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Attvalue();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getAttvalues <em>Attvalues</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Attvalues</em>
	 *         '.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getAttvalues()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Attvalues();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getColor <em>Color</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Color</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getColor()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Color();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getCreator <em>Creator</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Creator</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getCreator()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Creator();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getDefault <em>Default</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Default</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getDefault()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Default();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getDescription
	 * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getDescription()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Description();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getEdge <em>Edge</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Edge</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getEdge()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Edge();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getEdges <em>Edges</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Edges</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getEdges()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Edges();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getGexf <em>Gexf</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Gexf</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getGexf()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Gexf();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getGraph <em>Graph</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Graph</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getGraph()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Graph();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getKeywords <em>Keywords</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Keywords</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getKeywords()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Keywords();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getMeta <em>Meta</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Meta</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getMeta()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Meta();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getNode <em>Node</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Node</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getNode()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Node();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getNodes <em>Nodes</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Nodes</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getNodes()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Nodes();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getOptions <em>Options</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Options</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getOptions()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Options();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getParent <em>Parent</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Parent</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getParent()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Parent();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getParents <em>Parents</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Parents</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getParents()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Parents();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Position</em>
	 *         '.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getPosition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Position();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getSize <em>Size</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Size</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getSize()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Size();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getSpell <em>Spell</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Spell</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getSpell()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Spell();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getSpells <em>Spells</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Spells</em>'.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getSpells()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Spells();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.DocumentRoot#getThickness <em>Thickness</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Thickness</em>
	 *         '.
	 * @see net.gexf_1_2.gexf.DocumentRoot#getThickness()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Thickness();

	/**
	 * Returns the meta object for class '{@link net.gexf_1_2.gexf.EdgeContent
	 * <em>Edge Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Edge Content</em>'.
	 * @see net.gexf_1_2.gexf.EdgeContent
	 * @generated
	 */
	EClass getEdgeContent();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link net.gexf_1_2.gexf.EdgeContent#getGroup <em>Group</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see net.gexf_1_2.gexf.EdgeContent#getGroup()
	 * @see #getEdgeContent()
	 * @generated
	 */
	EAttribute getEdgeContent_Group();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.EdgeContent#getAttvalues <em>Attvalues</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Attvalues</em>'.
	 * @see net.gexf_1_2.gexf.EdgeContent#getAttvalues()
	 * @see #getEdgeContent()
	 * @generated
	 */
	EReference getEdgeContent_Attvalues();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.EdgeContent#getSpells <em>Spells</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Spells</em>'.
	 * @see net.gexf_1_2.gexf.EdgeContent#getSpells()
	 * @see #getEdgeContent()
	 * @generated
	 */
	EReference getEdgeContent_Spells();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.EdgeContent#getColor <em>Color</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Color</em>'.
	 * @see net.gexf_1_2.gexf.EdgeContent#getColor()
	 * @see #getEdgeContent()
	 * @generated
	 */
	EReference getEdgeContent_Color();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.EdgeContent#getThickness <em>Thickness</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Thickness</em>'.
	 * @see net.gexf_1_2.gexf.EdgeContent#getThickness()
	 * @see #getEdgeContent()
	 * @generated
	 */
	EReference getEdgeContent_Thickness();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.EdgeContent#getShape <em>Shape</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Shape</em>'.
	 * @see net.gexf_1_2.gexf.EdgeContent#getShape()
	 * @see #getEdgeContent()
	 * @generated
	 */
	EReference getEdgeContent_Shape();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.EdgeContent#getEnd <em>End</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>End</em>'.
	 * @see net.gexf_1_2.gexf.EdgeContent#getEnd()
	 * @see #getEdgeContent()
	 * @generated
	 */
	EAttribute getEdgeContent_End();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.EdgeContent#getEndopen <em>Endopen</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Endopen</em>'.
	 * @see net.gexf_1_2.gexf.EdgeContent#getEndopen()
	 * @see #getEdgeContent()
	 * @generated
	 */
	EAttribute getEdgeContent_Endopen();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.EdgeContent#getId <em>Id</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see net.gexf_1_2.gexf.EdgeContent#getId()
	 * @see #getEdgeContent()
	 * @generated
	 */
	EAttribute getEdgeContent_Id();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.EdgeContent#getLabel <em>Label</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see net.gexf_1_2.gexf.EdgeContent#getLabel()
	 * @see #getEdgeContent()
	 * @generated
	 */
	EAttribute getEdgeContent_Label();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.EdgeContent#getSource <em>Source</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see net.gexf_1_2.gexf.EdgeContent#getSource()
	 * @see #getEdgeContent()
	 * @generated
	 */
	EAttribute getEdgeContent_Source();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.EdgeContent#getStart <em>Start</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Start</em>'.
	 * @see net.gexf_1_2.gexf.EdgeContent#getStart()
	 * @see #getEdgeContent()
	 * @generated
	 */
	EAttribute getEdgeContent_Start();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.EdgeContent#getStartopen <em>Startopen</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Startopen</em>'.
	 * @see net.gexf_1_2.gexf.EdgeContent#getStartopen()
	 * @see #getEdgeContent()
	 * @generated
	 */
	EAttribute getEdgeContent_Startopen();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.EdgeContent#getTarget <em>Target</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Target</em>'.
	 * @see net.gexf_1_2.gexf.EdgeContent#getTarget()
	 * @see #getEdgeContent()
	 * @generated
	 */
	EAttribute getEdgeContent_Target();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.EdgeContent#getType <em>Type</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see net.gexf_1_2.gexf.EdgeContent#getType()
	 * @see #getEdgeContent()
	 * @generated
	 */
	EAttribute getEdgeContent_Type();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.EdgeContent#getWeight <em>Weight</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Weight</em>'.
	 * @see net.gexf_1_2.gexf.EdgeContent#getWeight()
	 * @see #getEdgeContent()
	 * @generated
	 */
	EAttribute getEdgeContent_Weight();

	/**
	 * Returns the meta object for class '{@link net.gexf_1_2.gexf.EdgesContent
	 * <em>Edges Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Edges Content</em>'.
	 * @see net.gexf_1_2.gexf.EdgesContent
	 * @generated
	 */
	EClass getEdgesContent();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.EdgesContent#getEdge <em>Edge</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Edge</em>
	 *         '.
	 * @see net.gexf_1_2.gexf.EdgesContent#getEdge()
	 * @see #getEdgesContent()
	 * @generated
	 */
	EReference getEdgesContent_Edge();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.EdgesContent#getCount <em>Count</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Count</em>'.
	 * @see net.gexf_1_2.gexf.EdgesContent#getCount()
	 * @see #getEdgesContent()
	 * @generated
	 */
	EAttribute getEdgesContent_Count();

	/**
	 * Returns the meta object for class '{@link net.gexf_1_2.gexf.GexfContent
	 * <em>Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Content</em>'.
	 * @see net.gexf_1_2.gexf.GexfContent
	 * @generated
	 */
	EClass getGexfContent();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.GexfContent#getMeta <em>Meta</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Meta</em>'.
	 * @see net.gexf_1_2.gexf.GexfContent#getMeta()
	 * @see #getGexfContent()
	 * @generated
	 */
	EReference getGexfContent_Meta();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link net.gexf_1_2.gexf.GexfContent#getGraph <em>Graph</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Graph</em>'.
	 * @see net.gexf_1_2.gexf.GexfContent#getGraph()
	 * @see #getGexfContent()
	 * @generated
	 */
	EReference getGexfContent_Graph();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.GexfContent#getVariant <em>Variant</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Variant</em>'.
	 * @see net.gexf_1_2.gexf.GexfContent#getVariant()
	 * @see #getGexfContent()
	 * @generated
	 */
	EAttribute getGexfContent_Variant();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.GexfContent#getVersion <em>Version</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see net.gexf_1_2.gexf.GexfContent#getVersion()
	 * @see #getGexfContent()
	 * @generated
	 */
	EAttribute getGexfContent_Version();

	/**
	 * Returns the meta object for class '{@link net.gexf_1_2.gexf.GraphContent
	 * <em>Graph Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Graph Content</em>'.
	 * @see net.gexf_1_2.gexf.GraphContent
	 * @generated
	 */
	EClass getGraphContent();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link net.gexf_1_2.gexf.GraphContent#getGroup <em>Group</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see net.gexf_1_2.gexf.GraphContent#getGroup()
	 * @see #getGraphContent()
	 * @generated
	 */
	EAttribute getGraphContent_Group();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.GraphContent#getAttributes <em>Attributes</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Attributes</em>'.
	 * @see net.gexf_1_2.gexf.GraphContent#getAttributes()
	 * @see #getGraphContent()
	 * @generated
	 */
	EReference getGraphContent_Attributes();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.GraphContent#getNodes <em>Nodes</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Nodes</em>'.
	 * @see net.gexf_1_2.gexf.GraphContent#getNodes()
	 * @see #getGraphContent()
	 * @generated
	 */
	EReference getGraphContent_Nodes();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.GraphContent#getEdges <em>Edges</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Edges</em>'.
	 * @see net.gexf_1_2.gexf.GraphContent#getEdges()
	 * @see #getGraphContent()
	 * @generated
	 */
	EReference getGraphContent_Edges();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.GraphContent#getDefaultedgetype
	 * <em>Defaultedgetype</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Defaultedgetype</em>'.
	 * @see net.gexf_1_2.gexf.GraphContent#getDefaultedgetype()
	 * @see #getGraphContent()
	 * @generated
	 */
	EAttribute getGraphContent_Defaultedgetype();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.GraphContent#getEnd <em>End</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>End</em>'.
	 * @see net.gexf_1_2.gexf.GraphContent#getEnd()
	 * @see #getGraphContent()
	 * @generated
	 */
	EAttribute getGraphContent_End();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.GraphContent#getEndopen <em>Endopen</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Endopen</em>'.
	 * @see net.gexf_1_2.gexf.GraphContent#getEndopen()
	 * @see #getGraphContent()
	 * @generated
	 */
	EAttribute getGraphContent_Endopen();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.GraphContent#getIdtype <em>Idtype</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Idtype</em>'.
	 * @see net.gexf_1_2.gexf.GraphContent#getIdtype()
	 * @see #getGraphContent()
	 * @generated
	 */
	EAttribute getGraphContent_Idtype();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.GraphContent#getMode <em>Mode</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Mode</em>'.
	 * @see net.gexf_1_2.gexf.GraphContent#getMode()
	 * @see #getGraphContent()
	 * @generated
	 */
	EAttribute getGraphContent_Mode();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.GraphContent#getStart <em>Start</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Start</em>'.
	 * @see net.gexf_1_2.gexf.GraphContent#getStart()
	 * @see #getGraphContent()
	 * @generated
	 */
	EAttribute getGraphContent_Start();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.GraphContent#getStartopen <em>Startopen</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Startopen</em>'.
	 * @see net.gexf_1_2.gexf.GraphContent#getStartopen()
	 * @see #getGraphContent()
	 * @generated
	 */
	EAttribute getGraphContent_Startopen();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.GraphContent#getTimeformat <em>Timeformat</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Timeformat</em>'.
	 * @see net.gexf_1_2.gexf.GraphContent#getTimeformat()
	 * @see #getGraphContent()
	 * @generated
	 */
	EAttribute getGraphContent_Timeformat();

	/**
	 * Returns the meta object for class '{@link net.gexf_1_2.gexf.MetaContent
	 * <em>Meta Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Meta Content</em>'.
	 * @see net.gexf_1_2.gexf.MetaContent
	 * @generated
	 */
	EClass getMetaContent();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link net.gexf_1_2.gexf.MetaContent#getGroup <em>Group</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see net.gexf_1_2.gexf.MetaContent#getGroup()
	 * @see #getMetaContent()
	 * @generated
	 */
	EAttribute getMetaContent_Group();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link net.gexf_1_2.gexf.MetaContent#getCreator <em>Creator</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Creator</em>'.
	 * @see net.gexf_1_2.gexf.MetaContent#getCreator()
	 * @see #getMetaContent()
	 * @generated
	 */
	EAttribute getMetaContent_Creator();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link net.gexf_1_2.gexf.MetaContent#getKeywords <em>Keywords</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Keywords</em>'.
	 * @see net.gexf_1_2.gexf.MetaContent#getKeywords()
	 * @see #getMetaContent()
	 * @generated
	 */
	EAttribute getMetaContent_Keywords();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link net.gexf_1_2.gexf.MetaContent#getDescription <em>Description</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Description</em>'.
	 * @see net.gexf_1_2.gexf.MetaContent#getDescription()
	 * @see #getMetaContent()
	 * @generated
	 */
	EAttribute getMetaContent_Description();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.MetaContent#getLastmodifieddate
	 * <em>Lastmodifieddate</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the attribute '<em>Lastmodifieddate</em>'.
	 * @see net.gexf_1_2.gexf.MetaContent#getLastmodifieddate()
	 * @see #getMetaContent()
	 * @generated
	 */
	EAttribute getMetaContent_Lastmodifieddate();

	/**
	 * Returns the meta object for class '{@link net.gexf_1_2.gexf.NodeContent
	 * <em>Node Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Node Content</em>'.
	 * @see net.gexf_1_2.gexf.NodeContent
	 * @generated
	 */
	EClass getNodeContent();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link net.gexf_1_2.gexf.NodeContent#getGroup <em>Group</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see net.gexf_1_2.gexf.NodeContent#getGroup()
	 * @see #getNodeContent()
	 * @generated
	 */
	EAttribute getNodeContent_Group();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.NodeContent#getAttvalues <em>Attvalues</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Attvalues</em>'.
	 * @see net.gexf_1_2.gexf.NodeContent#getAttvalues()
	 * @see #getNodeContent()
	 * @generated
	 */
	EReference getNodeContent_Attvalues();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.NodeContent#getSpells <em>Spells</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Spells</em>'.
	 * @see net.gexf_1_2.gexf.NodeContent#getSpells()
	 * @see #getNodeContent()
	 * @generated
	 */
	EReference getNodeContent_Spells();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.NodeContent#getNodes <em>Nodes</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Nodes</em>'.
	 * @see net.gexf_1_2.gexf.NodeContent#getNodes()
	 * @see #getNodeContent()
	 * @generated
	 */
	EReference getNodeContent_Nodes();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.NodeContent#getEdges <em>Edges</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Edges</em>'.
	 * @see net.gexf_1_2.gexf.NodeContent#getEdges()
	 * @see #getNodeContent()
	 * @generated
	 */
	EReference getNodeContent_Edges();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.NodeContent#getParents <em>Parents</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Parents</em>'.
	 * @see net.gexf_1_2.gexf.NodeContent#getParents()
	 * @see #getNodeContent()
	 * @generated
	 */
	EReference getNodeContent_Parents();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.NodeContent#getColor <em>Color</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Color</em>'.
	 * @see net.gexf_1_2.gexf.NodeContent#getColor()
	 * @see #getNodeContent()
	 * @generated
	 */
	EReference getNodeContent_Color();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.NodeContent#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Position</em>'.
	 * @see net.gexf_1_2.gexf.NodeContent#getPosition()
	 * @see #getNodeContent()
	 * @generated
	 */
	EReference getNodeContent_Position();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.NodeContent#getSize <em>Size</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Size</em>
	 *         '.
	 * @see net.gexf_1_2.gexf.NodeContent#getSize()
	 * @see #getNodeContent()
	 * @generated
	 */
	EReference getNodeContent_Size();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.NodeContent#getShape <em>Shape</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Shape</em>'.
	 * @see net.gexf_1_2.gexf.NodeContent#getShape()
	 * @see #getNodeContent()
	 * @generated
	 */
	EReference getNodeContent_Shape();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.NodeContent#getEnd <em>End</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>End</em>'.
	 * @see net.gexf_1_2.gexf.NodeContent#getEnd()
	 * @see #getNodeContent()
	 * @generated
	 */
	EAttribute getNodeContent_End();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.NodeContent#getEndopen <em>Endopen</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Endopen</em>'.
	 * @see net.gexf_1_2.gexf.NodeContent#getEndopen()
	 * @see #getNodeContent()
	 * @generated
	 */
	EAttribute getNodeContent_Endopen();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.NodeContent#getId <em>Id</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see net.gexf_1_2.gexf.NodeContent#getId()
	 * @see #getNodeContent()
	 * @generated
	 */
	EAttribute getNodeContent_Id();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.NodeContent#getLabel <em>Label</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see net.gexf_1_2.gexf.NodeContent#getLabel()
	 * @see #getNodeContent()
	 * @generated
	 */
	EAttribute getNodeContent_Label();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.NodeContent#getPid <em>Pid</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Pid</em>'.
	 * @see net.gexf_1_2.gexf.NodeContent#getPid()
	 * @see #getNodeContent()
	 * @generated
	 */
	EAttribute getNodeContent_Pid();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.NodeContent#getStart <em>Start</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Start</em>'.
	 * @see net.gexf_1_2.gexf.NodeContent#getStart()
	 * @see #getNodeContent()
	 * @generated
	 */
	EAttribute getNodeContent_Start();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.NodeContent#getStartopen <em>Startopen</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Startopen</em>'.
	 * @see net.gexf_1_2.gexf.NodeContent#getStartopen()
	 * @see #getNodeContent()
	 * @generated
	 */
	EAttribute getNodeContent_Startopen();

	/**
	 * Returns the meta object for class '{@link net.gexf_1_2.gexf.NodesContent
	 * <em>Nodes Content</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Nodes Content</em>'.
	 * @see net.gexf_1_2.gexf.NodesContent
	 * @generated
	 */
	EClass getNodesContent();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.NodesContent#getNode <em>Node</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Node</em>
	 *         '.
	 * @see net.gexf_1_2.gexf.NodesContent#getNode()
	 * @see #getNodesContent()
	 * @generated
	 */
	EReference getNodesContent_Node();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.NodesContent#getCount <em>Count</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Count</em>'.
	 * @see net.gexf_1_2.gexf.NodesContent#getCount()
	 * @see #getNodesContent()
	 * @generated
	 */
	EAttribute getNodesContent_Count();

	/**
	 * Returns the meta object for class '
	 * {@link net.gexf_1_2.gexf.ParentsContent <em>Parents Content</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Parents Content</em>'.
	 * @see net.gexf_1_2.gexf.ParentsContent
	 * @generated
	 */
	EClass getParentsContent();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.ParentsContent#getParent <em>Parent</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Parent</em>'.
	 * @see net.gexf_1_2.gexf.ParentsContent#getParent()
	 * @see #getParentsContent()
	 * @generated
	 */
	EReference getParentsContent_Parent();

	/**
	 * Returns the meta object for class '{@link net.gexf_1_2.gexf.ParentType
	 * <em>Parent Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Parent Type</em>'.
	 * @see net.gexf_1_2.gexf.ParentType
	 * @generated
	 */
	EClass getParentType();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.ParentType#getFor <em>For</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>For</em>'.
	 * @see net.gexf_1_2.gexf.ParentType#getFor()
	 * @see #getParentType()
	 * @generated
	 */
	EAttribute getParentType_For();

	/**
	 * Returns the meta object for class '
	 * {@link net.gexf_1_2.gexf.SpellsContent <em>Spells Content</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Spells Content</em>'.
	 * @see net.gexf_1_2.gexf.SpellsContent
	 * @generated
	 */
	EClass getSpellsContent();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link net.gexf_1_2.gexf.SpellsContent#getSpell <em>Spell</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Spell</em>'.
	 * @see net.gexf_1_2.gexf.SpellsContent#getSpell()
	 * @see #getSpellsContent()
	 * @generated
	 */
	EReference getSpellsContent_Spell();

	/**
	 * Returns the meta object for class '{@link net.gexf_1_2.gexf.SpellType
	 * <em>Spell Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Spell Type</em>'.
	 * @see net.gexf_1_2.gexf.SpellType
	 * @generated
	 */
	EClass getSpellType();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.SpellType#getEnd <em>End</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>End</em>'.
	 * @see net.gexf_1_2.gexf.SpellType#getEnd()
	 * @see #getSpellType()
	 * @generated
	 */
	EAttribute getSpellType_End();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.SpellType#getEndopen <em>Endopen</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Endopen</em>'.
	 * @see net.gexf_1_2.gexf.SpellType#getEndopen()
	 * @see #getSpellType()
	 * @generated
	 */
	EAttribute getSpellType_Endopen();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.SpellType#getStart <em>Start</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Start</em>'.
	 * @see net.gexf_1_2.gexf.SpellType#getStart()
	 * @see #getSpellType()
	 * @generated
	 */
	EAttribute getSpellType_Start();

	/**
	 * Returns the meta object for the attribute '
	 * {@link net.gexf_1_2.gexf.SpellType#getStartopen <em>Startopen</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Startopen</em>'.
	 * @see net.gexf_1_2.gexf.SpellType#getStartopen()
	 * @see #getSpellType()
	 * @generated
	 */
	EAttribute getSpellType_Startopen();

	/**
	 * Returns the meta object for enum '{@link net.gexf_1_2.gexf.AttrtypeType
	 * <em>Attrtype Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Attrtype Type</em>'.
	 * @see net.gexf_1_2.gexf.AttrtypeType
	 * @generated
	 */
	EEnum getAttrtypeType();

	/**
	 * Returns the meta object for enum '{@link net.gexf_1_2.gexf.ClassType
	 * <em>Class Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Class Type</em>'.
	 * @see net.gexf_1_2.gexf.ClassType
	 * @generated
	 */
	EEnum getClassType();

	/**
	 * Returns the meta object for enum '
	 * {@link net.gexf_1_2.gexf.DefaultedgetypeType
	 * <em>Defaultedgetype Type</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Defaultedgetype Type</em>'.
	 * @see net.gexf_1_2.gexf.DefaultedgetypeType
	 * @generated
	 */
	EEnum getDefaultedgetypeType();

	/**
	 * Returns the meta object for enum '{@link net.gexf_1_2.gexf.EdgetypeType
	 * <em>Edgetype Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Edgetype Type</em>'.
	 * @see net.gexf_1_2.gexf.EdgetypeType
	 * @generated
	 */
	EEnum getEdgetypeType();

	/**
	 * Returns the meta object for enum '{@link net.gexf_1_2.gexf.IdtypeType
	 * <em>Idtype Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Idtype Type</em>'.
	 * @see net.gexf_1_2.gexf.IdtypeType
	 * @generated
	 */
	EEnum getIdtypeType();

	/**
	 * Returns the meta object for enum '{@link net.gexf_1_2.gexf.ModeType
	 * <em>Mode Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Mode Type</em>'.
	 * @see net.gexf_1_2.gexf.ModeType
	 * @generated
	 */
	EEnum getModeType();

	/**
	 * Returns the meta object for enum '
	 * {@link net.gexf_1_2.gexf.TimeformatType <em>Timeformat Type</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Timeformat Type</em>'.
	 * @see net.gexf_1_2.gexf.TimeformatType
	 * @generated
	 */
	EEnum getTimeformatType();

	/**
	 * Returns the meta object for enum '{@link net.gexf_1_2.gexf.VersionType
	 * <em>Version Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Version Type</em>'.
	 * @see net.gexf_1_2.gexf.VersionType
	 * @generated
	 */
	EEnum getVersionType();

	/**
	 * Returns the meta object for data type '
	 * {@link net.gexf_1_2.gexf.AttrtypeType <em>Attrtype Type Object</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Attrtype Type Object</em>'.
	 * @see net.gexf_1_2.gexf.AttrtypeType
	 * @model instanceClass="net.gexf_1_2.gexf.AttrtypeType"
	 *        extendedMetaData="name='attrtype-type:Object' baseType='attrtype-type'"
	 * @generated
	 */
	EDataType getAttrtypeTypeObject();

	/**
	 * Returns the meta object for data type '
	 * {@link net.gexf_1_2.gexf.ClassType <em>Class Type Object</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Class Type Object</em>'.
	 * @see net.gexf_1_2.gexf.ClassType
	 * @model instanceClass="net.gexf_1_2.gexf.ClassType"
	 *        extendedMetaData="name='class-type:Object' baseType='class-type'"
	 * @generated
	 */
	EDataType getClassTypeObject();

	/**
	 * Returns the meta object for data type '
	 * {@link net.gexf_1_2.gexf.DefaultedgetypeType
	 * <em>Defaultedgetype Type Object</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for data type '
	 *         <em>Defaultedgetype Type Object</em>'.
	 * @see net.gexf_1_2.gexf.DefaultedgetypeType
	 * @model instanceClass="net.gexf_1_2.gexf.DefaultedgetypeType"
	 *        extendedMetaData=
	 *        "name='defaultedgetype-type:Object' baseType='defaultedgetype-type'"
	 * @generated
	 */
	EDataType getDefaultedgetypeTypeObject();

	/**
	 * Returns the meta object for data type '
	 * {@link net.gexf_1_2.gexf.EdgetypeType <em>Edgetype Type Object</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Edgetype Type Object</em>'.
	 * @see net.gexf_1_2.gexf.EdgetypeType
	 * @model instanceClass="net.gexf_1_2.gexf.EdgetypeType"
	 *        extendedMetaData="name='edgetype-type:Object' baseType='edgetype-type'"
	 * @generated
	 */
	EDataType getEdgetypeTypeObject();

	/**
	 * Returns the meta object for data type '{@link java.lang.Object
	 * <em>Id Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Id Type</em>'.
	 * @see java.lang.Object
	 * @model instanceClass="java.lang.Object" extendedMetaData=
	 *        "name='id-type' memberTypes='http://www.eclipse.org/emf/2003/XMLType#string http://www.eclipse.org/emf/2003/XMLType#integer'"
	 * @generated
	 */
	EDataType getIdType();

	/**
	 * Returns the meta object for data type '
	 * {@link net.gexf_1_2.gexf.IdtypeType <em>Idtype Type Object</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Idtype Type Object</em>'.
	 * @see net.gexf_1_2.gexf.IdtypeType
	 * @model instanceClass="net.gexf_1_2.gexf.IdtypeType"
	 *        extendedMetaData="name='idtype-type:Object' baseType='idtype-type'"
	 * @generated
	 */
	EDataType getIdtypeTypeObject();

	/**
	 * Returns the meta object for data type '{@link net.gexf_1_2.gexf.ModeType
	 * <em>Mode Type Object</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for data type '<em>Mode Type Object</em>'.
	 * @see net.gexf_1_2.gexf.ModeType
	 * @model instanceClass="net.gexf_1_2.gexf.ModeType"
	 *        extendedMetaData="name='mode-type:Object' baseType='mode-type'"
	 * @generated
	 */
	EDataType getModeTypeObject();

	/**
	 * Returns the meta object for data type '
	 * {@link net.gexf_1_2.gexf.TimeformatType <em>Timeformat Type Object</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Timeformat Type Object</em>'.
	 * @see net.gexf_1_2.gexf.TimeformatType
	 * @model instanceClass="net.gexf_1_2.gexf.TimeformatType" extendedMetaData=
	 *        "name='timeformat-type:Object' baseType='timeformat-type'"
	 * @generated
	 */
	EDataType getTimeformatTypeObject();

	/**
	 * Returns the meta object for data type '{@link java.lang.Object
	 * <em>Time Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Time Type</em>'.
	 * @see java.lang.Object
	 * @model instanceClass="java.lang.Object" extendedMetaData=
	 *        "name='time-type' memberTypes='http://www.eclipse.org/emf/2003/XMLType#integer http://www.eclipse.org/emf/2003/XMLType#double http://www.eclipse.org/emf/2003/XMLType#date http://www.eclipse.org/emf/2003/XMLType#dateTime'"
	 * @generated
	 */
	EDataType getTimeType();

	/**
	 * Returns the meta object for data type '
	 * {@link net.gexf_1_2.gexf.VersionType <em>Version Type Object</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Version Type Object</em>'.
	 * @see net.gexf_1_2.gexf.VersionType
	 * @model instanceClass="net.gexf_1_2.gexf.VersionType" extendedMetaData=
	 *        "name='version_._type:Object' baseType='version_._type'"
	 * @generated
	 */
	EDataType getVersionTypeObject();

	/**
	 * Returns the meta object for data type '<em>Weight Type</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Weight Type</em>'.
	 * @model instanceClass="float" extendedMetaData=
	 *        "name='weight-type' baseType='http://www.eclipse.org/emf/2003/XMLType#float'"
	 * @generated
	 */
	EDataType getWeightType();

	/**
	 * Returns the meta object for data type '{@link java.lang.Float
	 * <em>Weight Type Object</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for data type '<em>Weight Type Object</em>'.
	 * @see java.lang.Float
	 * @model instanceClass="java.lang.Float"
	 *        extendedMetaData="name='weight-type:Object' baseType='weight-type'"
	 * @generated
	 */
	EDataType getWeightTypeObject();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	GexfFactory getGexfFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that
	 * represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.impl.AttributeContentImpl
		 * <em>Attribute Content</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.impl.AttributeContentImpl
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getAttributeContent()
		 * @generated
		 */
		EClass ATTRIBUTE_CONTENT = eINSTANCE.getAttributeContent();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTRIBUTE_CONTENT__GROUP = eINSTANCE.getAttributeContent_Group();

		/**
		 * The meta object literal for the '<em><b>Default</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTRIBUTE_CONTENT__DEFAULT = eINSTANCE.getAttributeContent_Default();

		/**
		 * The meta object literal for the '<em><b>Options</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTRIBUTE_CONTENT__OPTIONS = eINSTANCE.getAttributeContent_Options();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTRIBUTE_CONTENT__ID = eINSTANCE.getAttributeContent_Id();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTRIBUTE_CONTENT__TITLE = eINSTANCE.getAttributeContent_Title();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTRIBUTE_CONTENT__TYPE = eINSTANCE.getAttributeContent_Type();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.impl.AttributesContentImpl
		 * <em>Attributes Content</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.impl.AttributesContentImpl
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getAttributesContent()
		 * @generated
		 */
		EClass ATTRIBUTES_CONTENT = eINSTANCE.getAttributesContent();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ATTRIBUTES_CONTENT__ATTRIBUTE = eINSTANCE.getAttributesContent_Attribute();

		/**
		 * The meta object literal for the '<em><b>Class</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTRIBUTES_CONTENT__CLASS = eINSTANCE.getAttributesContent_Class();

		/**
		 * The meta object literal for the '<em><b>End</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTRIBUTES_CONTENT__END = eINSTANCE.getAttributesContent_End();

		/**
		 * The meta object literal for the '<em><b>Endopen</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTRIBUTES_CONTENT__ENDOPEN = eINSTANCE.getAttributesContent_Endopen();

		/**
		 * The meta object literal for the '<em><b>Mode</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTRIBUTES_CONTENT__MODE = eINSTANCE.getAttributesContent_Mode();

		/**
		 * The meta object literal for the '<em><b>Start</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTRIBUTES_CONTENT__START = eINSTANCE.getAttributesContent_Start();

		/**
		 * The meta object literal for the '<em><b>Startopen</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTRIBUTES_CONTENT__STARTOPEN = eINSTANCE.getAttributesContent_Startopen();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.impl.AttvaluesContentImpl
		 * <em>Attvalues Content</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.impl.AttvaluesContentImpl
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getAttvaluesContent()
		 * @generated
		 */
		EClass ATTVALUES_CONTENT = eINSTANCE.getAttvaluesContent();

		/**
		 * The meta object literal for the '<em><b>Attvalue</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ATTVALUES_CONTENT__ATTVALUE = eINSTANCE.getAttvaluesContent_Attvalue();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.impl.AttvalueTypeImpl
		 * <em>Attvalue Type</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.impl.AttvalueTypeImpl
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getAttvalueType()
		 * @generated
		 */
		EClass ATTVALUE_TYPE = eINSTANCE.getAttvalueType();

		/**
		 * The meta object literal for the '<em><b>End</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTVALUE_TYPE__END = eINSTANCE.getAttvalueType_End();

		/**
		 * The meta object literal for the '<em><b>Endopen</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTVALUE_TYPE__ENDOPEN = eINSTANCE.getAttvalueType_Endopen();

		/**
		 * The meta object literal for the '<em><b>For</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTVALUE_TYPE__FOR = eINSTANCE.getAttvalueType_For();

		/**
		 * The meta object literal for the '<em><b>Start</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTVALUE_TYPE__START = eINSTANCE.getAttvalueType_Start();

		/**
		 * The meta object literal for the '<em><b>Startopen</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTVALUE_TYPE__STARTOPEN = eINSTANCE.getAttvalueType_Startopen();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ATTVALUE_TYPE__VALUE = eINSTANCE.getAttvalueType_Value();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.impl.DocumentRootImpl
		 * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.impl.DocumentRootImpl
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>'
		 * map feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>'
		 * map feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ATTRIBUTE = eINSTANCE.getDocumentRoot_Attribute();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ATTRIBUTES = eINSTANCE.getDocumentRoot_Attributes();

		/**
		 * The meta object literal for the '<em><b>Attvalue</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ATTVALUE = eINSTANCE.getDocumentRoot_Attvalue();

		/**
		 * The meta object literal for the '<em><b>Attvalues</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ATTVALUES = eINSTANCE.getDocumentRoot_Attvalues();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__COLOR = eINSTANCE.getDocumentRoot_Color();

		/**
		 * The meta object literal for the '<em><b>Creator</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__CREATOR = eINSTANCE.getDocumentRoot_Creator();

		/**
		 * The meta object literal for the '<em><b>Default</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__DEFAULT = eINSTANCE.getDocumentRoot_Default();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__DESCRIPTION = eINSTANCE.getDocumentRoot_Description();

		/**
		 * The meta object literal for the '<em><b>Edge</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EDGE = eINSTANCE.getDocumentRoot_Edge();

		/**
		 * The meta object literal for the '<em><b>Edges</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EDGES = eINSTANCE.getDocumentRoot_Edges();

		/**
		 * The meta object literal for the '<em><b>Gexf</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__GEXF = eINSTANCE.getDocumentRoot_Gexf();

		/**
		 * The meta object literal for the '<em><b>Graph</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__GRAPH = eINSTANCE.getDocumentRoot_Graph();

		/**
		 * The meta object literal for the '<em><b>Keywords</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__KEYWORDS = eINSTANCE.getDocumentRoot_Keywords();

		/**
		 * The meta object literal for the '<em><b>Meta</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__META = eINSTANCE.getDocumentRoot_Meta();

		/**
		 * The meta object literal for the '<em><b>Node</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__NODE = eINSTANCE.getDocumentRoot_Node();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__NODES = eINSTANCE.getDocumentRoot_Nodes();

		/**
		 * The meta object literal for the '<em><b>Options</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__OPTIONS = eINSTANCE.getDocumentRoot_Options();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PARENT = eINSTANCE.getDocumentRoot_Parent();

		/**
		 * The meta object literal for the '<em><b>Parents</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PARENTS = eINSTANCE.getDocumentRoot_Parents();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__POSITION = eINSTANCE.getDocumentRoot_Position();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SIZE = eINSTANCE.getDocumentRoot_Size();

		/**
		 * The meta object literal for the '<em><b>Spell</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SPELL = eINSTANCE.getDocumentRoot_Spell();

		/**
		 * The meta object literal for the '<em><b>Spells</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SPELLS = eINSTANCE.getDocumentRoot_Spells();

		/**
		 * The meta object literal for the '<em><b>Thickness</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__THICKNESS = eINSTANCE.getDocumentRoot_Thickness();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.impl.EdgeContentImpl <em>Edge Content</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.impl.EdgeContentImpl
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getEdgeContent()
		 * @generated
		 */
		EClass EDGE_CONTENT = eINSTANCE.getEdgeContent();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EDGE_CONTENT__GROUP = eINSTANCE.getEdgeContent_Group();

		/**
		 * The meta object literal for the '<em><b>Attvalues</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EDGE_CONTENT__ATTVALUES = eINSTANCE.getEdgeContent_Attvalues();

		/**
		 * The meta object literal for the '<em><b>Spells</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EDGE_CONTENT__SPELLS = eINSTANCE.getEdgeContent_Spells();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EDGE_CONTENT__COLOR = eINSTANCE.getEdgeContent_Color();

		/**
		 * The meta object literal for the '<em><b>Thickness</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EDGE_CONTENT__THICKNESS = eINSTANCE.getEdgeContent_Thickness();

		/**
		 * The meta object literal for the '<em><b>Shape</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EDGE_CONTENT__SHAPE = eINSTANCE.getEdgeContent_Shape();

		/**
		 * The meta object literal for the '<em><b>End</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EDGE_CONTENT__END = eINSTANCE.getEdgeContent_End();

		/**
		 * The meta object literal for the '<em><b>Endopen</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EDGE_CONTENT__ENDOPEN = eINSTANCE.getEdgeContent_Endopen();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EDGE_CONTENT__ID = eINSTANCE.getEdgeContent_Id();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EDGE_CONTENT__LABEL = eINSTANCE.getEdgeContent_Label();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EDGE_CONTENT__SOURCE = eINSTANCE.getEdgeContent_Source();

		/**
		 * The meta object literal for the '<em><b>Start</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EDGE_CONTENT__START = eINSTANCE.getEdgeContent_Start();

		/**
		 * The meta object literal for the '<em><b>Startopen</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EDGE_CONTENT__STARTOPEN = eINSTANCE.getEdgeContent_Startopen();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EDGE_CONTENT__TARGET = eINSTANCE.getEdgeContent_Target();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EDGE_CONTENT__TYPE = eINSTANCE.getEdgeContent_Type();

		/**
		 * The meta object literal for the '<em><b>Weight</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EDGE_CONTENT__WEIGHT = eINSTANCE.getEdgeContent_Weight();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.impl.EdgesContentImpl
		 * <em>Edges Content</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.impl.EdgesContentImpl
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getEdgesContent()
		 * @generated
		 */
		EClass EDGES_CONTENT = eINSTANCE.getEdgesContent();

		/**
		 * The meta object literal for the '<em><b>Edge</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EDGES_CONTENT__EDGE = eINSTANCE.getEdgesContent_Edge();

		/**
		 * The meta object literal for the '<em><b>Count</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EDGES_CONTENT__COUNT = eINSTANCE.getEdgesContent_Count();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.impl.GexfContentImpl <em>Content</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.impl.GexfContentImpl
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getGexfContent()
		 * @generated
		 */
		EClass GEXF_CONTENT = eINSTANCE.getGexfContent();

		/**
		 * The meta object literal for the '<em><b>Meta</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference GEXF_CONTENT__META = eINSTANCE.getGexfContent_Meta();

		/**
		 * The meta object literal for the '<em><b>Graph</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference GEXF_CONTENT__GRAPH = eINSTANCE.getGexfContent_Graph();

		/**
		 * The meta object literal for the '<em><b>Variant</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GEXF_CONTENT__VARIANT = eINSTANCE.getGexfContent_Variant();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GEXF_CONTENT__VERSION = eINSTANCE.getGexfContent_Version();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.impl.GraphContentImpl
		 * <em>Graph Content</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.impl.GraphContentImpl
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getGraphContent()
		 * @generated
		 */
		EClass GRAPH_CONTENT = eINSTANCE.getGraphContent();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GRAPH_CONTENT__GROUP = eINSTANCE.getGraphContent_Group();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference GRAPH_CONTENT__ATTRIBUTES = eINSTANCE.getGraphContent_Attributes();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference GRAPH_CONTENT__NODES = eINSTANCE.getGraphContent_Nodes();

		/**
		 * The meta object literal for the '<em><b>Edges</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference GRAPH_CONTENT__EDGES = eINSTANCE.getGraphContent_Edges();

		/**
		 * The meta object literal for the '<em><b>Defaultedgetype</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GRAPH_CONTENT__DEFAULTEDGETYPE = eINSTANCE.getGraphContent_Defaultedgetype();

		/**
		 * The meta object literal for the '<em><b>End</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GRAPH_CONTENT__END = eINSTANCE.getGraphContent_End();

		/**
		 * The meta object literal for the '<em><b>Endopen</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GRAPH_CONTENT__ENDOPEN = eINSTANCE.getGraphContent_Endopen();

		/**
		 * The meta object literal for the '<em><b>Idtype</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GRAPH_CONTENT__IDTYPE = eINSTANCE.getGraphContent_Idtype();

		/**
		 * The meta object literal for the '<em><b>Mode</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GRAPH_CONTENT__MODE = eINSTANCE.getGraphContent_Mode();

		/**
		 * The meta object literal for the '<em><b>Start</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GRAPH_CONTENT__START = eINSTANCE.getGraphContent_Start();

		/**
		 * The meta object literal for the '<em><b>Startopen</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GRAPH_CONTENT__STARTOPEN = eINSTANCE.getGraphContent_Startopen();

		/**
		 * The meta object literal for the '<em><b>Timeformat</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GRAPH_CONTENT__TIMEFORMAT = eINSTANCE.getGraphContent_Timeformat();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.impl.MetaContentImpl <em>Meta Content</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.impl.MetaContentImpl
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getMetaContent()
		 * @generated
		 */
		EClass META_CONTENT = eINSTANCE.getMetaContent();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute META_CONTENT__GROUP = eINSTANCE.getMetaContent_Group();

		/**
		 * The meta object literal for the '<em><b>Creator</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute META_CONTENT__CREATOR = eINSTANCE.getMetaContent_Creator();

		/**
		 * The meta object literal for the '<em><b>Keywords</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute META_CONTENT__KEYWORDS = eINSTANCE.getMetaContent_Keywords();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>'
		 * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute META_CONTENT__DESCRIPTION = eINSTANCE.getMetaContent_Description();

		/**
		 * The meta object literal for the '<em><b>Lastmodifieddate</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute META_CONTENT__LASTMODIFIEDDATE = eINSTANCE.getMetaContent_Lastmodifieddate();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.impl.NodeContentImpl <em>Node Content</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.impl.NodeContentImpl
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getNodeContent()
		 * @generated
		 */
		EClass NODE_CONTENT = eINSTANCE.getNodeContent();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute NODE_CONTENT__GROUP = eINSTANCE.getNodeContent_Group();

		/**
		 * The meta object literal for the '<em><b>Attvalues</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference NODE_CONTENT__ATTVALUES = eINSTANCE.getNodeContent_Attvalues();

		/**
		 * The meta object literal for the '<em><b>Spells</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference NODE_CONTENT__SPELLS = eINSTANCE.getNodeContent_Spells();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference NODE_CONTENT__NODES = eINSTANCE.getNodeContent_Nodes();

		/**
		 * The meta object literal for the '<em><b>Edges</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference NODE_CONTENT__EDGES = eINSTANCE.getNodeContent_Edges();

		/**
		 * The meta object literal for the '<em><b>Parents</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference NODE_CONTENT__PARENTS = eINSTANCE.getNodeContent_Parents();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference NODE_CONTENT__COLOR = eINSTANCE.getNodeContent_Color();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference NODE_CONTENT__POSITION = eINSTANCE.getNodeContent_Position();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference NODE_CONTENT__SIZE = eINSTANCE.getNodeContent_Size();

		/**
		 * The meta object literal for the '<em><b>Shape</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference NODE_CONTENT__SHAPE = eINSTANCE.getNodeContent_Shape();

		/**
		 * The meta object literal for the '<em><b>End</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute NODE_CONTENT__END = eINSTANCE.getNodeContent_End();

		/**
		 * The meta object literal for the '<em><b>Endopen</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute NODE_CONTENT__ENDOPEN = eINSTANCE.getNodeContent_Endopen();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute NODE_CONTENT__ID = eINSTANCE.getNodeContent_Id();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute NODE_CONTENT__LABEL = eINSTANCE.getNodeContent_Label();

		/**
		 * The meta object literal for the '<em><b>Pid</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute NODE_CONTENT__PID = eINSTANCE.getNodeContent_Pid();

		/**
		 * The meta object literal for the '<em><b>Start</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute NODE_CONTENT__START = eINSTANCE.getNodeContent_Start();

		/**
		 * The meta object literal for the '<em><b>Startopen</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute NODE_CONTENT__STARTOPEN = eINSTANCE.getNodeContent_Startopen();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.impl.NodesContentImpl
		 * <em>Nodes Content</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.impl.NodesContentImpl
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getNodesContent()
		 * @generated
		 */
		EClass NODES_CONTENT = eINSTANCE.getNodesContent();

		/**
		 * The meta object literal for the '<em><b>Node</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference NODES_CONTENT__NODE = eINSTANCE.getNodesContent_Node();

		/**
		 * The meta object literal for the '<em><b>Count</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute NODES_CONTENT__COUNT = eINSTANCE.getNodesContent_Count();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.impl.ParentsContentImpl
		 * <em>Parents Content</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.impl.ParentsContentImpl
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getParentsContent()
		 * @generated
		 */
		EClass PARENTS_CONTENT = eINSTANCE.getParentsContent();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PARENTS_CONTENT__PARENT = eINSTANCE.getParentsContent_Parent();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.impl.ParentTypeImpl <em>Parent Type</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.impl.ParentTypeImpl
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getParentType()
		 * @generated
		 */
		EClass PARENT_TYPE = eINSTANCE.getParentType();

		/**
		 * The meta object literal for the '<em><b>For</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PARENT_TYPE__FOR = eINSTANCE.getParentType_For();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.impl.SpellsContentImpl
		 * <em>Spells Content</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.impl.SpellsContentImpl
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getSpellsContent()
		 * @generated
		 */
		EClass SPELLS_CONTENT = eINSTANCE.getSpellsContent();

		/**
		 * The meta object literal for the '<em><b>Spell</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SPELLS_CONTENT__SPELL = eINSTANCE.getSpellsContent_Spell();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.impl.SpellTypeImpl <em>Spell Type</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.impl.SpellTypeImpl
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getSpellType()
		 * @generated
		 */
		EClass SPELL_TYPE = eINSTANCE.getSpellType();

		/**
		 * The meta object literal for the '<em><b>End</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SPELL_TYPE__END = eINSTANCE.getSpellType_End();

		/**
		 * The meta object literal for the '<em><b>Endopen</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SPELL_TYPE__ENDOPEN = eINSTANCE.getSpellType_Endopen();

		/**
		 * The meta object literal for the '<em><b>Start</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SPELL_TYPE__START = eINSTANCE.getSpellType_Start();

		/**
		 * The meta object literal for the '<em><b>Startopen</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SPELL_TYPE__STARTOPEN = eINSTANCE.getSpellType_Startopen();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.AttrtypeType <em>Attrtype Type</em>}' enum.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.AttrtypeType
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getAttrtypeType()
		 * @generated
		 */
		EEnum ATTRTYPE_TYPE = eINSTANCE.getAttrtypeType();

		/**
		 * The meta object literal for the '{@link net.gexf_1_2.gexf.ClassType
		 * <em>Class Type</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see net.gexf_1_2.gexf.ClassType
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getClassType()
		 * @generated
		 */
		EEnum CLASS_TYPE = eINSTANCE.getClassType();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.DefaultedgetypeType
		 * <em>Defaultedgetype Type</em>}' enum. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.DefaultedgetypeType
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getDefaultedgetypeType()
		 * @generated
		 */
		EEnum DEFAULTEDGETYPE_TYPE = eINSTANCE.getDefaultedgetypeType();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.EdgetypeType <em>Edgetype Type</em>}' enum.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.EdgetypeType
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getEdgetypeType()
		 * @generated
		 */
		EEnum EDGETYPE_TYPE = eINSTANCE.getEdgetypeType();

		/**
		 * The meta object literal for the '{@link net.gexf_1_2.gexf.IdtypeType
		 * <em>Idtype Type</em>}' enum. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.IdtypeType
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getIdtypeType()
		 * @generated
		 */
		EEnum IDTYPE_TYPE = eINSTANCE.getIdtypeType();

		/**
		 * The meta object literal for the '{@link net.gexf_1_2.gexf.ModeType
		 * <em>Mode Type</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see net.gexf_1_2.gexf.ModeType
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getModeType()
		 * @generated
		 */
		EEnum MODE_TYPE = eINSTANCE.getModeType();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.TimeformatType <em>Timeformat Type</em>}'
		 * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.TimeformatType
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getTimeformatType()
		 * @generated
		 */
		EEnum TIMEFORMAT_TYPE = eINSTANCE.getTimeformatType();

		/**
		 * The meta object literal for the '
		 * {@link net.gexf_1_2.gexf.VersionType <em>Version Type</em>}' enum.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.VersionType
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getVersionType()
		 * @generated
		 */
		EEnum VERSION_TYPE = eINSTANCE.getVersionType();

		/**
		 * The meta object literal for the '<em>Attrtype Type Object</em>' data
		 * type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.AttrtypeType
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getAttrtypeTypeObject()
		 * @generated
		 */
		EDataType ATTRTYPE_TYPE_OBJECT = eINSTANCE.getAttrtypeTypeObject();

		/**
		 * The meta object literal for the '<em>Class Type Object</em>' data
		 * type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.ClassType
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getClassTypeObject()
		 * @generated
		 */
		EDataType CLASS_TYPE_OBJECT = eINSTANCE.getClassTypeObject();

		/**
		 * The meta object literal for the '<em>Defaultedgetype Type Object</em>
		 * ' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.DefaultedgetypeType
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getDefaultedgetypeTypeObject()
		 * @generated
		 */
		EDataType DEFAULTEDGETYPE_TYPE_OBJECT = eINSTANCE.getDefaultedgetypeTypeObject();

		/**
		 * The meta object literal for the '<em>Edgetype Type Object</em>' data
		 * type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.EdgetypeType
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getEdgetypeTypeObject()
		 * @generated
		 */
		EDataType EDGETYPE_TYPE_OBJECT = eINSTANCE.getEdgetypeTypeObject();

		/**
		 * The meta object literal for the '<em>Id Type</em>' data type. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see java.lang.Object
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getIdType()
		 * @generated
		 */
		EDataType ID_TYPE = eINSTANCE.getIdType();

		/**
		 * The meta object literal for the '<em>Idtype Type Object</em>' data
		 * type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.IdtypeType
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getIdtypeTypeObject()
		 * @generated
		 */
		EDataType IDTYPE_TYPE_OBJECT = eINSTANCE.getIdtypeTypeObject();

		/**
		 * The meta object literal for the '<em>Mode Type Object</em>' data
		 * type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.ModeType
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getModeTypeObject()
		 * @generated
		 */
		EDataType MODE_TYPE_OBJECT = eINSTANCE.getModeTypeObject();

		/**
		 * The meta object literal for the '<em>Timeformat Type Object</em>'
		 * data type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.TimeformatType
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getTimeformatTypeObject()
		 * @generated
		 */
		EDataType TIMEFORMAT_TYPE_OBJECT = eINSTANCE.getTimeformatTypeObject();

		/**
		 * The meta object literal for the '<em>Time Type</em>' data type. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see java.lang.Object
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getTimeType()
		 * @generated
		 */
		EDataType TIME_TYPE = eINSTANCE.getTimeType();

		/**
		 * The meta object literal for the '<em>Version Type Object</em>' data
		 * type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.VersionType
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getVersionTypeObject()
		 * @generated
		 */
		EDataType VERSION_TYPE_OBJECT = eINSTANCE.getVersionTypeObject();

		/**
		 * The meta object literal for the '<em>Weight Type</em>' data type.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getWeightType()
		 * @generated
		 */
		EDataType WEIGHT_TYPE = eINSTANCE.getWeightType();

		/**
		 * The meta object literal for the '<em>Weight Type Object</em>' data
		 * type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see java.lang.Float
		 * @see net.gexf_1_2.gexf.impl.GexfPackageImpl#getWeightTypeObject()
		 * @generated
		 */
		EDataType WEIGHT_TYPE_OBJECT = eINSTANCE.getWeightTypeObject();

	}

} //GexfPackage
