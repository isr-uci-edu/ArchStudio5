/**
 */
package org.archstudio.xadl3.structure_3_0;

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
 * <!-- end-user-doc --> <!-- begin-model-doc -->
 * 
 * 
 * xADL 3 Structure Schema
 * 
 * This schema defines the basic elements that make up architectural structure:
 * components, connectors, interfaces, links, and the scaffolding necessary for
 * hierarchical modeling. This schema is intentionally neutral about the
 * semantics of these elements; semantics should be provided in extension
 * schemas.
 * 
 * Changelog: - 3.0.0 * Initial version
 * 
 * 
 * 
 * 
 * xADL 3 Core Schema
 * 
 * This schema defines the core elements for xADL 3 documents. The schema is
 * intentionally small, defining only a few elements that are intended to be
 * common across xADL descriptions and extensions, regardless of domain.
 * 
 * Changelog: - 3.0.0: * Initial version
 * 
 * 
 * <!-- end-model-doc -->
 * 
 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Factory
 * @model kind="package"
 * @generated
 */
public interface Structure_3_0Package extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "structure_3_0";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.archstudio.org/xadl3/schemas/structure-3.0.xsd";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "structure_3_0";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	Structure_3_0Package eINSTANCE = org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl.init();

	/**
	 * The meta object id for the '
	 * {@link org.archstudio.xadl3.structure_3_0.impl.BrickImpl <em>Brick</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.structure_3_0.impl.BrickImpl
	 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getBrick()
	 * @generated
	 */
	int BRICK = 0;

	/**
	 * The feature id for the '<em><b>Interface</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BRICK__INTERFACE = 0;

	/**
	 * The feature id for the '<em><b>Sub Structure</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BRICK__SUB_STRUCTURE = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BRICK__ID = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BRICK__NAME = 3;

	/**
	 * The number of structural features of the '<em>Brick</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BRICK_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '
	 * {@link org.archstudio.xadl3.structure_3_0.impl.ComponentImpl
	 * <em>Component</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.structure_3_0.impl.ComponentImpl
	 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getComponent()
	 * @generated
	 */
	int COMPONENT = 1;

	/**
	 * The feature id for the '<em><b>Interface</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT__INTERFACE = BRICK__INTERFACE;

	/**
	 * The feature id for the '<em><b>Sub Structure</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT__SUB_STRUCTURE = BRICK__SUB_STRUCTURE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT__ID = BRICK__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT__NAME = BRICK__NAME;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT__EXT = BRICK_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Component</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_FEATURE_COUNT = BRICK_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '
	 * {@link org.archstudio.xadl3.structure_3_0.impl.ConnectorImpl
	 * <em>Connector</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.structure_3_0.impl.ConnectorImpl
	 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getConnector()
	 * @generated
	 */
	int CONNECTOR = 2;

	/**
	 * The feature id for the '<em><b>Interface</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__INTERFACE = BRICK__INTERFACE;

	/**
	 * The feature id for the '<em><b>Sub Structure</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__SUB_STRUCTURE = BRICK__SUB_STRUCTURE;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__ID = BRICK__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__NAME = BRICK__NAME;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__EXT = BRICK_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Connector</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_FEATURE_COUNT = BRICK_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '
	 * {@link org.archstudio.xadl3.structure_3_0.impl.DocumentRootImpl
	 * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.archstudio.xadl3.structure_3_0.impl.DocumentRootImpl
	 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 3;

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
	 * The feature id for the '<em><b>Structure</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STRUCTURE = 3;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '
	 * {@link org.archstudio.xadl3.structure_3_0.impl.InterfaceImpl
	 * <em>Interface</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.structure_3_0.impl.InterfaceImpl
	 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getInterface()
	 * @generated
	 */
	int INTERFACE = 4;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INTERFACE__EXT = 0;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INTERFACE__DIRECTION = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INTERFACE__ID = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INTERFACE__NAME = 3;

	/**
	 * The number of structural features of the '<em>Interface</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INTERFACE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '
	 * {@link org.archstudio.xadl3.structure_3_0.impl.InterfaceMappingImpl
	 * <em>Interface Mapping</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.structure_3_0.impl.InterfaceMappingImpl
	 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getInterfaceMapping()
	 * @generated
	 */
	int INTERFACE_MAPPING = 5;

	/**
	 * The feature id for the '<em><b>Outer Interface Link</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INTERFACE_MAPPING__OUTER_INTERFACE_LINK = 0;

	/**
	 * The feature id for the '<em><b>Inner Interface Link</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INTERFACE_MAPPING__INNER_INTERFACE_LINK = 1;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INTERFACE_MAPPING__EXT = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INTERFACE_MAPPING__ID = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INTERFACE_MAPPING__NAME = 4;

	/**
	 * The number of structural features of the '<em>Interface Mapping</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INTERFACE_MAPPING_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '
	 * {@link org.archstudio.xadl3.structure_3_0.impl.LinkImpl <em>Link</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.structure_3_0.impl.LinkImpl
	 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getLink()
	 * @generated
	 */
	int LINK = 6;

	/**
	 * The feature id for the '<em><b>Point1</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LINK__POINT1 = 0;

	/**
	 * The feature id for the '<em><b>Point2</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LINK__POINT2 = 1;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LINK__EXT = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LINK__ID = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LINK__NAME = 4;

	/**
	 * The number of structural features of the '<em>Link</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LINK_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '
	 * {@link org.archstudio.xadl3.structure_3_0.impl.StructureImpl
	 * <em>Structure</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.structure_3_0.impl.StructureImpl
	 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getStructure()
	 * @generated
	 */
	int STRUCTURE = 7;

	/**
	 * The feature id for the '<em><b>Component</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STRUCTURE__COMPONENT = 0;

	/**
	 * The feature id for the '<em><b>Connector</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STRUCTURE__CONNECTOR = 1;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STRUCTURE__LINK = 2;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STRUCTURE__EXT = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STRUCTURE__ID = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STRUCTURE__NAME = 5;

	/**
	 * The number of structural features of the '<em>Structure</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '
	 * {@link org.archstudio.xadl3.structure_3_0.impl.SubStructureImpl
	 * <em>Sub Structure</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.archstudio.xadl3.structure_3_0.impl.SubStructureImpl
	 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getSubStructure()
	 * @generated
	 */
	int SUB_STRUCTURE = 8;

	/**
	 * The feature id for the '<em><b>Inner Structure Link</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SUB_STRUCTURE__INNER_STRUCTURE_LINK = 0;

	/**
	 * The feature id for the '<em><b>Interface Mapping</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SUB_STRUCTURE__INTERFACE_MAPPING = 1;

	/**
	 * The feature id for the '<em><b>Ext</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SUB_STRUCTURE__EXT = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SUB_STRUCTURE__ID = 3;

	/**
	 * The number of structural features of the '<em>Sub Structure</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SUB_STRUCTURE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '
	 * {@link org.archstudio.xadl3.structure_3_0.Direction <em>Direction</em>}'
	 * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.structure_3_0.Direction
	 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getDirection()
	 * @generated
	 */
	int DIRECTION = 9;

	/**
	 * The meta object id for the '<em>Direction Object</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.archstudio.xadl3.structure_3_0.Direction
	 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getDirectionObject()
	 * @generated
	 */
	int DIRECTION_OBJECT = 10;

	/**
	 * Returns the meta object for class '
	 * {@link org.archstudio.xadl3.structure_3_0.Brick <em>Brick</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Brick</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Brick
	 * @generated
	 */
	EClass getBrick();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.structure_3_0.Brick#getInterface
	 * <em>Interface</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Interface</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Brick#getInterface()
	 * @see #getBrick()
	 * @generated
	 */
	EReference getBrick_Interface();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.archstudio.xadl3.structure_3_0.Brick#getSubStructure
	 * <em>Sub Structure</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '
	 *         <em>Sub Structure</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Brick#getSubStructure()
	 * @see #getBrick()
	 * @generated
	 */
	EReference getBrick_SubStructure();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.structure_3_0.Brick#getId <em>Id</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Brick#getId()
	 * @see #getBrick()
	 * @generated
	 */
	EAttribute getBrick_Id();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.structure_3_0.Brick#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Brick#getName()
	 * @see #getBrick()
	 * @generated
	 */
	EAttribute getBrick_Name();

	/**
	 * Returns the meta object for class '
	 * {@link org.archstudio.xadl3.structure_3_0.Component <em>Component</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Component</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Component
	 * @generated
	 */
	EClass getComponent();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.structure_3_0.Component#getExt <em>Ext</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ext</em>
	 *         '.
	 * @see org.archstudio.xadl3.structure_3_0.Component#getExt()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Ext();

	/**
	 * Returns the meta object for class '
	 * {@link org.archstudio.xadl3.structure_3_0.Connector <em>Connector</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Connector</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Connector
	 * @generated
	 */
	EClass getConnector();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.structure_3_0.Connector#getExt <em>Ext</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ext</em>
	 *         '.
	 * @see org.archstudio.xadl3.structure_3_0.Connector#getExt()
	 * @see #getConnector()
	 * @generated
	 */
	EReference getConnector_Ext();

	/**
	 * Returns the meta object for class '
	 * {@link org.archstudio.xadl3.structure_3_0.DocumentRoot
	 * <em>Document Root</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.archstudio.xadl3.structure_3_0.DocumentRoot#getMixed
	 * <em>Mixed</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '
	 * {@link org.archstudio.xadl3.structure_3_0.DocumentRoot#getXMLNSPrefixMap
	 * <em>XMLNS Prefix Map</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '
	 * {@link org.archstudio.xadl3.structure_3_0.DocumentRoot#getXSISchemaLocation
	 * <em>XSI Schema Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.archstudio.xadl3.structure_3_0.DocumentRoot#getStructure
	 * <em>Structure</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Structure</em>
	 *         '.
	 * @see org.archstudio.xadl3.structure_3_0.DocumentRoot#getStructure()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Structure();

	/**
	 * Returns the meta object for class '
	 * {@link org.archstudio.xadl3.structure_3_0.Interface <em>Interface</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Interface</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Interface
	 * @generated
	 */
	EClass getInterface();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.structure_3_0.Interface#getExt <em>Ext</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ext</em>
	 *         '.
	 * @see org.archstudio.xadl3.structure_3_0.Interface#getExt()
	 * @see #getInterface()
	 * @generated
	 */
	EReference getInterface_Ext();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.structure_3_0.Interface#getDirection
	 * <em>Direction</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Direction</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Interface#getDirection()
	 * @see #getInterface()
	 * @generated
	 */
	EAttribute getInterface_Direction();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.structure_3_0.Interface#getId <em>Id</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Interface#getId()
	 * @see #getInterface()
	 * @generated
	 */
	EAttribute getInterface_Id();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.structure_3_0.Interface#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Interface#getName()
	 * @see #getInterface()
	 * @generated
	 */
	EAttribute getInterface_Name();

	/**
	 * Returns the meta object for class '
	 * {@link org.archstudio.xadl3.structure_3_0.InterfaceMapping
	 * <em>Interface Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Interface Mapping</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.InterfaceMapping
	 * @generated
	 */
	EClass getInterfaceMapping();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.archstudio.xadl3.structure_3_0.InterfaceMapping#getOuterInterfaceLink
	 * <em>Outer Interface Link</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Outer Interface Link</em>
	 *         '.
	 * @see org.archstudio.xadl3.structure_3_0.InterfaceMapping#getOuterInterfaceLink()
	 * @see #getInterfaceMapping()
	 * @generated
	 */
	EReference getInterfaceMapping_OuterInterfaceLink();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.archstudio.xadl3.structure_3_0.InterfaceMapping#getInnerInterfaceLink
	 * <em>Inner Interface Link</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Inner Interface Link</em>
	 *         '.
	 * @see org.archstudio.xadl3.structure_3_0.InterfaceMapping#getInnerInterfaceLink()
	 * @see #getInterfaceMapping()
	 * @generated
	 */
	EReference getInterfaceMapping_InnerInterfaceLink();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.structure_3_0.InterfaceMapping#getExt
	 * <em>Ext</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ext</em>
	 *         '.
	 * @see org.archstudio.xadl3.structure_3_0.InterfaceMapping#getExt()
	 * @see #getInterfaceMapping()
	 * @generated
	 */
	EReference getInterfaceMapping_Ext();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.structure_3_0.InterfaceMapping#getId
	 * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.InterfaceMapping#getId()
	 * @see #getInterfaceMapping()
	 * @generated
	 */
	EAttribute getInterfaceMapping_Id();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.structure_3_0.InterfaceMapping#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.InterfaceMapping#getName()
	 * @see #getInterfaceMapping()
	 * @generated
	 */
	EAttribute getInterfaceMapping_Name();

	/**
	 * Returns the meta object for class '
	 * {@link org.archstudio.xadl3.structure_3_0.Link <em>Link</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Link</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Link
	 * @generated
	 */
	EClass getLink();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.archstudio.xadl3.structure_3_0.Link#getPoint1 <em>Point1</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Point1</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Link#getPoint1()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_Point1();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.archstudio.xadl3.structure_3_0.Link#getPoint2 <em>Point2</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Point2</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Link#getPoint2()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_Point2();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.structure_3_0.Link#getExt <em>Ext</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ext</em>
	 *         '.
	 * @see org.archstudio.xadl3.structure_3_0.Link#getExt()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_Ext();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.structure_3_0.Link#getId <em>Id</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Link#getId()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_Id();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.structure_3_0.Link#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Link#getName()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_Name();

	/**
	 * Returns the meta object for class '
	 * {@link org.archstudio.xadl3.structure_3_0.Structure <em>Structure</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Structure</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Structure
	 * @generated
	 */
	EClass getStructure();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.structure_3_0.Structure#getComponent
	 * <em>Component</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Component</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Structure#getComponent()
	 * @see #getStructure()
	 * @generated
	 */
	EReference getStructure_Component();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.structure_3_0.Structure#getConnector
	 * <em>Connector</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Connector</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Structure#getConnector()
	 * @see #getStructure()
	 * @generated
	 */
	EReference getStructure_Connector();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.structure_3_0.Structure#getLink
	 * <em>Link</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Link</em>
	 *         '.
	 * @see org.archstudio.xadl3.structure_3_0.Structure#getLink()
	 * @see #getStructure()
	 * @generated
	 */
	EReference getStructure_Link();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.structure_3_0.Structure#getExt <em>Ext</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ext</em>
	 *         '.
	 * @see org.archstudio.xadl3.structure_3_0.Structure#getExt()
	 * @see #getStructure()
	 * @generated
	 */
	EReference getStructure_Ext();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.structure_3_0.Structure#getId <em>Id</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Structure#getId()
	 * @see #getStructure()
	 * @generated
	 */
	EAttribute getStructure_Id();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.structure_3_0.Structure#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Structure#getName()
	 * @see #getStructure()
	 * @generated
	 */
	EAttribute getStructure_Name();

	/**
	 * Returns the meta object for class '
	 * {@link org.archstudio.xadl3.structure_3_0.SubStructure
	 * <em>Sub Structure</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Sub Structure</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.SubStructure
	 * @generated
	 */
	EClass getSubStructure();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.archstudio.xadl3.structure_3_0.SubStructure#getInnerStructureLink
	 * <em>Inner Structure Link</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Inner Structure Link</em>
	 *         '.
	 * @see org.archstudio.xadl3.structure_3_0.SubStructure#getInnerStructureLink()
	 * @see #getSubStructure()
	 * @generated
	 */
	EReference getSubStructure_InnerStructureLink();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.structure_3_0.SubStructure#getInterfaceMapping
	 * <em>Interface Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Interface Mapping</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.SubStructure#getInterfaceMapping()
	 * @see #getSubStructure()
	 * @generated
	 */
	EReference getSubStructure_InterfaceMapping();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.archstudio.xadl3.structure_3_0.SubStructure#getExt
	 * <em>Ext</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ext</em>
	 *         '.
	 * @see org.archstudio.xadl3.structure_3_0.SubStructure#getExt()
	 * @see #getSubStructure()
	 * @generated
	 */
	EReference getSubStructure_Ext();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.archstudio.xadl3.structure_3_0.SubStructure#getId <em>Id</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.SubStructure#getId()
	 * @see #getSubStructure()
	 * @generated
	 */
	EAttribute getSubStructure_Id();

	/**
	 * Returns the meta object for enum '
	 * {@link org.archstudio.xadl3.structure_3_0.Direction <em>Direction</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Direction</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Direction
	 * @generated
	 */
	EEnum getDirection();

	/**
	 * Returns the meta object for data type '
	 * {@link org.archstudio.xadl3.structure_3_0.Direction
	 * <em>Direction Object</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for data type '<em>Direction Object</em>'.
	 * @see org.archstudio.xadl3.structure_3_0.Direction
	 * @model instanceClass="org.archstudio.xadl3.structure_3_0.Direction"
	 *        extendedMetaData="name='Direction:Object' baseType='Direction'"
	 * @generated
	 */
	EDataType getDirectionObject();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Structure_3_0Factory getStructure_3_0Factory();

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
		 * {@link org.archstudio.xadl3.structure_3_0.impl.BrickImpl
		 * <em>Brick</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.structure_3_0.impl.BrickImpl
		 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getBrick()
		 * @generated
		 */
		EClass BRICK = eINSTANCE.getBrick();

		/**
		 * The meta object literal for the '<em><b>Interface</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference BRICK__INTERFACE = eINSTANCE.getBrick_Interface();

		/**
		 * The meta object literal for the '<em><b>Sub Structure</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference BRICK__SUB_STRUCTURE = eINSTANCE.getBrick_SubStructure();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute BRICK__ID = eINSTANCE.getBrick_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute BRICK__NAME = eINSTANCE.getBrick_Name();

		/**
		 * The meta object literal for the '
		 * {@link org.archstudio.xadl3.structure_3_0.impl.ComponentImpl
		 * <em>Component</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.archstudio.xadl3.structure_3_0.impl.ComponentImpl
		 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getComponent()
		 * @generated
		 */
		EClass COMPONENT = eINSTANCE.getComponent();

		/**
		 * The meta object literal for the '<em><b>Ext</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference COMPONENT__EXT = eINSTANCE.getComponent_Ext();

		/**
		 * The meta object literal for the '
		 * {@link org.archstudio.xadl3.structure_3_0.impl.ConnectorImpl
		 * <em>Connector</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.archstudio.xadl3.structure_3_0.impl.ConnectorImpl
		 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getConnector()
		 * @generated
		 */
		EClass CONNECTOR = eINSTANCE.getConnector();

		/**
		 * The meta object literal for the '<em><b>Ext</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CONNECTOR__EXT = eINSTANCE.getConnector_Ext();

		/**
		 * The meta object literal for the '
		 * {@link org.archstudio.xadl3.structure_3_0.impl.DocumentRootImpl
		 * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.structure_3_0.impl.DocumentRootImpl
		 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getDocumentRoot()
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
		 * The meta object literal for the '<em><b>Structure</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STRUCTURE = eINSTANCE.getDocumentRoot_Structure();

		/**
		 * The meta object literal for the '
		 * {@link org.archstudio.xadl3.structure_3_0.impl.InterfaceImpl
		 * <em>Interface</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.archstudio.xadl3.structure_3_0.impl.InterfaceImpl
		 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getInterface()
		 * @generated
		 */
		EClass INTERFACE = eINSTANCE.getInterface();

		/**
		 * The meta object literal for the '<em><b>Ext</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INTERFACE__EXT = eINSTANCE.getInterface_Ext();

		/**
		 * The meta object literal for the '<em><b>Direction</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute INTERFACE__DIRECTION = eINSTANCE.getInterface_Direction();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute INTERFACE__ID = eINSTANCE.getInterface_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute INTERFACE__NAME = eINSTANCE.getInterface_Name();

		/**
		 * The meta object literal for the '
		 * {@link org.archstudio.xadl3.structure_3_0.impl.InterfaceMappingImpl
		 * <em>Interface Mapping</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.structure_3_0.impl.InterfaceMappingImpl
		 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getInterfaceMapping()
		 * @generated
		 */
		EClass INTERFACE_MAPPING = eINSTANCE.getInterfaceMapping();

		/**
		 * The meta object literal for the '<em><b>Outer Interface Link</b></em>
		 * ' reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INTERFACE_MAPPING__OUTER_INTERFACE_LINK = eINSTANCE.getInterfaceMapping_OuterInterfaceLink();

		/**
		 * The meta object literal for the '<em><b>Inner Interface Link</b></em>
		 * ' reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INTERFACE_MAPPING__INNER_INTERFACE_LINK = eINSTANCE.getInterfaceMapping_InnerInterfaceLink();

		/**
		 * The meta object literal for the '<em><b>Ext</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INTERFACE_MAPPING__EXT = eINSTANCE.getInterfaceMapping_Ext();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute INTERFACE_MAPPING__ID = eINSTANCE.getInterfaceMapping_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute INTERFACE_MAPPING__NAME = eINSTANCE.getInterfaceMapping_Name();

		/**
		 * The meta object literal for the '
		 * {@link org.archstudio.xadl3.structure_3_0.impl.LinkImpl
		 * <em>Link</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.structure_3_0.impl.LinkImpl
		 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getLink()
		 * @generated
		 */
		EClass LINK = eINSTANCE.getLink();

		/**
		 * The meta object literal for the '<em><b>Point1</b></em>' reference
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference LINK__POINT1 = eINSTANCE.getLink_Point1();

		/**
		 * The meta object literal for the '<em><b>Point2</b></em>' reference
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference LINK__POINT2 = eINSTANCE.getLink_Point2();

		/**
		 * The meta object literal for the '<em><b>Ext</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference LINK__EXT = eINSTANCE.getLink_Ext();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute LINK__ID = eINSTANCE.getLink_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute LINK__NAME = eINSTANCE.getLink_Name();

		/**
		 * The meta object literal for the '
		 * {@link org.archstudio.xadl3.structure_3_0.impl.StructureImpl
		 * <em>Structure</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.archstudio.xadl3.structure_3_0.impl.StructureImpl
		 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getStructure()
		 * @generated
		 */
		EClass STRUCTURE = eINSTANCE.getStructure();

		/**
		 * The meta object literal for the '<em><b>Component</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference STRUCTURE__COMPONENT = eINSTANCE.getStructure_Component();

		/**
		 * The meta object literal for the '<em><b>Connector</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference STRUCTURE__CONNECTOR = eINSTANCE.getStructure_Connector();

		/**
		 * The meta object literal for the '<em><b>Link</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference STRUCTURE__LINK = eINSTANCE.getStructure_Link();

		/**
		 * The meta object literal for the '<em><b>Ext</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference STRUCTURE__EXT = eINSTANCE.getStructure_Ext();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute STRUCTURE__ID = eINSTANCE.getStructure_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute STRUCTURE__NAME = eINSTANCE.getStructure_Name();

		/**
		 * The meta object literal for the '
		 * {@link org.archstudio.xadl3.structure_3_0.impl.SubStructureImpl
		 * <em>Sub Structure</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.structure_3_0.impl.SubStructureImpl
		 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getSubStructure()
		 * @generated
		 */
		EClass SUB_STRUCTURE = eINSTANCE.getSubStructure();

		/**
		 * The meta object literal for the '<em><b>Inner Structure Link</b></em>
		 * ' reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SUB_STRUCTURE__INNER_STRUCTURE_LINK = eINSTANCE.getSubStructure_InnerStructureLink();

		/**
		 * The meta object literal for the '<em><b>Interface Mapping</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SUB_STRUCTURE__INTERFACE_MAPPING = eINSTANCE.getSubStructure_InterfaceMapping();

		/**
		 * The meta object literal for the '<em><b>Ext</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SUB_STRUCTURE__EXT = eINSTANCE.getSubStructure_Ext();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SUB_STRUCTURE__ID = eINSTANCE.getSubStructure_Id();

		/**
		 * The meta object literal for the '
		 * {@link org.archstudio.xadl3.structure_3_0.Direction
		 * <em>Direction</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.archstudio.xadl3.structure_3_0.Direction
		 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getDirection()
		 * @generated
		 */
		EEnum DIRECTION = eINSTANCE.getDirection();

		/**
		 * The meta object literal for the '<em>Direction Object</em>' data
		 * type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.archstudio.xadl3.structure_3_0.Direction
		 * @see org.archstudio.xadl3.structure_3_0.impl.Structure_3_0PackageImpl#getDirectionObject()
		 * @generated
		 */
		EDataType DIRECTION_OBJECT = eINSTANCE.getDirectionObject();

	}

} //Structure_3_0Package
