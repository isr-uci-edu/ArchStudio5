/**
 */
package net.gexf_1_2.gexf.impl;

import net.gexf_1_2.gexf.AttributeContent;
import net.gexf_1_2.gexf.AttributesContent;
import net.gexf_1_2.gexf.AttvalueType;
import net.gexf_1_2.gexf.AttvaluesContent;
import net.gexf_1_2.gexf.DocumentRoot;
import net.gexf_1_2.gexf.EdgeContent;
import net.gexf_1_2.gexf.EdgesContent;
import net.gexf_1_2.gexf.GexfContent;
import net.gexf_1_2.gexf.GexfPackage;
import net.gexf_1_2.gexf.GraphContent;
import net.gexf_1_2.gexf.MetaContent;
import net.gexf_1_2.gexf.NodeContent;
import net.gexf_1_2.gexf.NodesContent;
import net.gexf_1_2.gexf.ParentType;
import net.gexf_1_2.gexf.ParentsContent;
import net.gexf_1_2.gexf.SpellType;
import net.gexf_1_2.gexf.SpellsContent;
import net.gexf_1_2.viz.ColorContent;
import net.gexf_1_2.viz.PositionContent;
import net.gexf_1_2.viz.SizeContent;
import net.gexf_1_2.viz.ThicknessContent;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Document Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getAttribute <em>Attribute</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getAttributes <em>Attributes</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getAttvalue <em>Attvalue</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getAttvalues <em>Attvalues</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getColor <em>Color</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getCreator <em>Creator</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getDefault <em>Default</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getDescription <em>Description</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getEdge <em>Edge</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getEdges <em>Edges</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getGexf <em>Gexf</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getGraph <em>Graph</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getKeywords <em>Keywords</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getMeta <em>Meta</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getNode <em>Node</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getNodes <em>Nodes</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getOptions <em>Options</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getParent <em>Parent</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getParents <em>Parents</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getPosition <em>Position</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getSize <em>Size</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getSpell <em>Spell</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getSpells <em>Spells</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.DocumentRootImpl#getThickness <em>Thickness</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends MinimalEObjectImpl.Container implements DocumentRoot {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xMLNSPrefixMap;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xSISchemaLocation;

	/**
	 * The default value of the '{@link #getCreator() <em>Creator</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getCreator()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATOR_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getDefault() <em>Default</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDefault()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getKeywords() <em>Keywords</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getKeywords()
	 * @generated
	 * @ordered
	 */
	protected static final String KEYWORDS_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getOptions() <em>Options</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getOptions()
	 * @generated
	 * @ordered
	 */
	protected static final String OPTIONS_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected DocumentRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GexfPackage.Literals.DOCUMENT_ROOT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, GexfPackage.DOCUMENT_ROOT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EMap<String, String> getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap<String, String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY,
					EStringToStringMapEntryImpl.class, this, GexfPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EMap<String, String> getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap<String, String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY,
					EStringToStringMapEntryImpl.class, this, GexfPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public AttributeContent getAttribute() {
		return (AttributeContent) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetAttribute(AttributeContent newAttribute, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(GexfPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE, newAttribute,
				msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setAttribute(AttributeContent newAttribute) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE, newAttribute);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public AttributesContent getAttributes() {
		return (AttributesContent) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__ATTRIBUTES, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetAttributes(AttributesContent newAttributes, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(GexfPackage.Literals.DOCUMENT_ROOT__ATTRIBUTES,
				newAttributes, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setAttributes(AttributesContent newAttributes) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__ATTRIBUTES, newAttributes);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public AttvalueType getAttvalue() {
		return (AttvalueType) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__ATTVALUE, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetAttvalue(AttvalueType newAttvalue, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(GexfPackage.Literals.DOCUMENT_ROOT__ATTVALUE, newAttvalue,
				msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setAttvalue(AttvalueType newAttvalue) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__ATTVALUE, newAttvalue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public AttvaluesContent getAttvalues() {
		return (AttvaluesContent) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__ATTVALUES, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetAttvalues(AttvaluesContent newAttvalues, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(GexfPackage.Literals.DOCUMENT_ROOT__ATTVALUES, newAttvalues,
				msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setAttvalues(AttvaluesContent newAttvalues) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__ATTVALUES, newAttvalues);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ColorContent getColor() {
		return (ColorContent) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__COLOR, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetColor(ColorContent newColor, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(GexfPackage.Literals.DOCUMENT_ROOT__COLOR, newColor, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setColor(ColorContent newColor) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__COLOR, newColor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getCreator() {
		return (String) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__CREATOR, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setCreator(String newCreator) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__CREATOR, newCreator);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getDefault() {
		return (String) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__DEFAULT, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDefault(String newDefault) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__DEFAULT, newDefault);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getDescription() {
		return (String) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__DESCRIPTION, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__DESCRIPTION, newDescription);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EdgeContent getEdge() {
		return (EdgeContent) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__EDGE, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetEdge(EdgeContent newEdge, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(GexfPackage.Literals.DOCUMENT_ROOT__EDGE, newEdge, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEdge(EdgeContent newEdge) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__EDGE, newEdge);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EdgesContent getEdges() {
		return (EdgesContent) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__EDGES, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetEdges(EdgesContent newEdges, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(GexfPackage.Literals.DOCUMENT_ROOT__EDGES, newEdges, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEdges(EdgesContent newEdges) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__EDGES, newEdges);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public GexfContent getGexf() {
		return (GexfContent) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__GEXF, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetGexf(GexfContent newGexf, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(GexfPackage.Literals.DOCUMENT_ROOT__GEXF, newGexf, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setGexf(GexfContent newGexf) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__GEXF, newGexf);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public GraphContent getGraph() {
		return (GraphContent) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__GRAPH, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetGraph(GraphContent newGraph, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(GexfPackage.Literals.DOCUMENT_ROOT__GRAPH, newGraph, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setGraph(GraphContent newGraph) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__GRAPH, newGraph);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getKeywords() {
		return (String) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__KEYWORDS, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setKeywords(String newKeywords) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__KEYWORDS, newKeywords);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public MetaContent getMeta() {
		return (MetaContent) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__META, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetMeta(MetaContent newMeta, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(GexfPackage.Literals.DOCUMENT_ROOT__META, newMeta, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setMeta(MetaContent newMeta) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__META, newMeta);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NodeContent getNode() {
		return (NodeContent) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__NODE, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetNode(NodeContent newNode, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(GexfPackage.Literals.DOCUMENT_ROOT__NODE, newNode, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setNode(NodeContent newNode) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__NODE, newNode);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NodesContent getNodes() {
		return (NodesContent) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__NODES, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetNodes(NodesContent newNodes, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(GexfPackage.Literals.DOCUMENT_ROOT__NODES, newNodes, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setNodes(NodesContent newNodes) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__NODES, newNodes);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getOptions() {
		return (String) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__OPTIONS, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setOptions(String newOptions) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__OPTIONS, newOptions);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ParentType getParent() {
		return (ParentType) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__PARENT, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetParent(ParentType newParent, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(GexfPackage.Literals.DOCUMENT_ROOT__PARENT, newParent, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setParent(ParentType newParent) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__PARENT, newParent);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ParentsContent getParents() {
		return (ParentsContent) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__PARENTS, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetParents(ParentsContent newParents, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(GexfPackage.Literals.DOCUMENT_ROOT__PARENTS, newParents,
				msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setParents(ParentsContent newParents) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__PARENTS, newParents);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public PositionContent getPosition() {
		return (PositionContent) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__POSITION, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetPosition(PositionContent newPosition, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(GexfPackage.Literals.DOCUMENT_ROOT__POSITION, newPosition,
				msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setPosition(PositionContent newPosition) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__POSITION, newPosition);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public SizeContent getSize() {
		return (SizeContent) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__SIZE, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetSize(SizeContent newSize, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(GexfPackage.Literals.DOCUMENT_ROOT__SIZE, newSize, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setSize(SizeContent newSize) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__SIZE, newSize);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public SpellType getSpell() {
		return (SpellType) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__SPELL, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetSpell(SpellType newSpell, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(GexfPackage.Literals.DOCUMENT_ROOT__SPELL, newSpell, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setSpell(SpellType newSpell) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__SPELL, newSpell);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public SpellsContent getSpells() {
		return (SpellsContent) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__SPELLS, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetSpells(SpellsContent newSpells, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(GexfPackage.Literals.DOCUMENT_ROOT__SPELLS, newSpells, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setSpells(SpellsContent newSpells) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__SPELLS, newSpells);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ThicknessContent getThickness() {
		return (ThicknessContent) getMixed().get(GexfPackage.Literals.DOCUMENT_ROOT__THICKNESS, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetThickness(ThicknessContent newThickness, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(GexfPackage.Literals.DOCUMENT_ROOT__THICKNESS, newThickness,
				msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setThickness(ThicknessContent newThickness) {
		((FeatureMap.Internal) getMixed()).set(GexfPackage.Literals.DOCUMENT_ROOT__THICKNESS, newThickness);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GexfPackage.DOCUMENT_ROOT__MIXED:
			return ((InternalEList<?>) getMixed()).basicRemove(otherEnd, msgs);
		case GexfPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			return ((InternalEList<?>) getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
		case GexfPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			return ((InternalEList<?>) getXSISchemaLocation()).basicRemove(otherEnd, msgs);
		case GexfPackage.DOCUMENT_ROOT__ATTRIBUTE:
			return basicSetAttribute(null, msgs);
		case GexfPackage.DOCUMENT_ROOT__ATTRIBUTES:
			return basicSetAttributes(null, msgs);
		case GexfPackage.DOCUMENT_ROOT__ATTVALUE:
			return basicSetAttvalue(null, msgs);
		case GexfPackage.DOCUMENT_ROOT__ATTVALUES:
			return basicSetAttvalues(null, msgs);
		case GexfPackage.DOCUMENT_ROOT__COLOR:
			return basicSetColor(null, msgs);
		case GexfPackage.DOCUMENT_ROOT__EDGE:
			return basicSetEdge(null, msgs);
		case GexfPackage.DOCUMENT_ROOT__EDGES:
			return basicSetEdges(null, msgs);
		case GexfPackage.DOCUMENT_ROOT__GEXF:
			return basicSetGexf(null, msgs);
		case GexfPackage.DOCUMENT_ROOT__GRAPH:
			return basicSetGraph(null, msgs);
		case GexfPackage.DOCUMENT_ROOT__META:
			return basicSetMeta(null, msgs);
		case GexfPackage.DOCUMENT_ROOT__NODE:
			return basicSetNode(null, msgs);
		case GexfPackage.DOCUMENT_ROOT__NODES:
			return basicSetNodes(null, msgs);
		case GexfPackage.DOCUMENT_ROOT__PARENT:
			return basicSetParent(null, msgs);
		case GexfPackage.DOCUMENT_ROOT__PARENTS:
			return basicSetParents(null, msgs);
		case GexfPackage.DOCUMENT_ROOT__POSITION:
			return basicSetPosition(null, msgs);
		case GexfPackage.DOCUMENT_ROOT__SIZE:
			return basicSetSize(null, msgs);
		case GexfPackage.DOCUMENT_ROOT__SPELL:
			return basicSetSpell(null, msgs);
		case GexfPackage.DOCUMENT_ROOT__SPELLS:
			return basicSetSpells(null, msgs);
		case GexfPackage.DOCUMENT_ROOT__THICKNESS:
			return basicSetThickness(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GexfPackage.DOCUMENT_ROOT__MIXED:
			if (coreType) {
				return getMixed();
			}
			return ((FeatureMap.Internal) getMixed()).getWrapper();
		case GexfPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			if (coreType) {
				return getXMLNSPrefixMap();
			}
			else {
				return getXMLNSPrefixMap().map();
			}
		case GexfPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			if (coreType) {
				return getXSISchemaLocation();
			}
			else {
				return getXSISchemaLocation().map();
			}
		case GexfPackage.DOCUMENT_ROOT__ATTRIBUTE:
			return getAttribute();
		case GexfPackage.DOCUMENT_ROOT__ATTRIBUTES:
			return getAttributes();
		case GexfPackage.DOCUMENT_ROOT__ATTVALUE:
			return getAttvalue();
		case GexfPackage.DOCUMENT_ROOT__ATTVALUES:
			return getAttvalues();
		case GexfPackage.DOCUMENT_ROOT__COLOR:
			return getColor();
		case GexfPackage.DOCUMENT_ROOT__CREATOR:
			return getCreator();
		case GexfPackage.DOCUMENT_ROOT__DEFAULT:
			return getDefault();
		case GexfPackage.DOCUMENT_ROOT__DESCRIPTION:
			return getDescription();
		case GexfPackage.DOCUMENT_ROOT__EDGE:
			return getEdge();
		case GexfPackage.DOCUMENT_ROOT__EDGES:
			return getEdges();
		case GexfPackage.DOCUMENT_ROOT__GEXF:
			return getGexf();
		case GexfPackage.DOCUMENT_ROOT__GRAPH:
			return getGraph();
		case GexfPackage.DOCUMENT_ROOT__KEYWORDS:
			return getKeywords();
		case GexfPackage.DOCUMENT_ROOT__META:
			return getMeta();
		case GexfPackage.DOCUMENT_ROOT__NODE:
			return getNode();
		case GexfPackage.DOCUMENT_ROOT__NODES:
			return getNodes();
		case GexfPackage.DOCUMENT_ROOT__OPTIONS:
			return getOptions();
		case GexfPackage.DOCUMENT_ROOT__PARENT:
			return getParent();
		case GexfPackage.DOCUMENT_ROOT__PARENTS:
			return getParents();
		case GexfPackage.DOCUMENT_ROOT__POSITION:
			return getPosition();
		case GexfPackage.DOCUMENT_ROOT__SIZE:
			return getSize();
		case GexfPackage.DOCUMENT_ROOT__SPELL:
			return getSpell();
		case GexfPackage.DOCUMENT_ROOT__SPELLS:
			return getSpells();
		case GexfPackage.DOCUMENT_ROOT__THICKNESS:
			return getThickness();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case GexfPackage.DOCUMENT_ROOT__MIXED:
			((FeatureMap.Internal) getMixed()).set(newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			((EStructuralFeature.Setting) getXMLNSPrefixMap()).set(newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			((EStructuralFeature.Setting) getXSISchemaLocation()).set(newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__ATTRIBUTE:
			setAttribute((AttributeContent) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__ATTRIBUTES:
			setAttributes((AttributesContent) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__ATTVALUE:
			setAttvalue((AttvalueType) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__ATTVALUES:
			setAttvalues((AttvaluesContent) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__COLOR:
			setColor((ColorContent) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__CREATOR:
			setCreator((String) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__DEFAULT:
			setDefault((String) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__DESCRIPTION:
			setDescription((String) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__EDGE:
			setEdge((EdgeContent) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__EDGES:
			setEdges((EdgesContent) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__GEXF:
			setGexf((GexfContent) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__GRAPH:
			setGraph((GraphContent) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__KEYWORDS:
			setKeywords((String) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__META:
			setMeta((MetaContent) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__NODE:
			setNode((NodeContent) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__NODES:
			setNodes((NodesContent) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__OPTIONS:
			setOptions((String) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__PARENT:
			setParent((ParentType) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__PARENTS:
			setParents((ParentsContent) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__POSITION:
			setPosition((PositionContent) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__SIZE:
			setSize((SizeContent) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__SPELL:
			setSpell((SpellType) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__SPELLS:
			setSpells((SpellsContent) newValue);
			return;
		case GexfPackage.DOCUMENT_ROOT__THICKNESS:
			setThickness((ThicknessContent) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case GexfPackage.DOCUMENT_ROOT__MIXED:
			getMixed().clear();
			return;
		case GexfPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			getXMLNSPrefixMap().clear();
			return;
		case GexfPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			getXSISchemaLocation().clear();
			return;
		case GexfPackage.DOCUMENT_ROOT__ATTRIBUTE:
			setAttribute((AttributeContent) null);
			return;
		case GexfPackage.DOCUMENT_ROOT__ATTRIBUTES:
			setAttributes((AttributesContent) null);
			return;
		case GexfPackage.DOCUMENT_ROOT__ATTVALUE:
			setAttvalue((AttvalueType) null);
			return;
		case GexfPackage.DOCUMENT_ROOT__ATTVALUES:
			setAttvalues((AttvaluesContent) null);
			return;
		case GexfPackage.DOCUMENT_ROOT__COLOR:
			setColor((ColorContent) null);
			return;
		case GexfPackage.DOCUMENT_ROOT__CREATOR:
			setCreator(CREATOR_EDEFAULT);
			return;
		case GexfPackage.DOCUMENT_ROOT__DEFAULT:
			setDefault(DEFAULT_EDEFAULT);
			return;
		case GexfPackage.DOCUMENT_ROOT__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		case GexfPackage.DOCUMENT_ROOT__EDGE:
			setEdge((EdgeContent) null);
			return;
		case GexfPackage.DOCUMENT_ROOT__EDGES:
			setEdges((EdgesContent) null);
			return;
		case GexfPackage.DOCUMENT_ROOT__GEXF:
			setGexf((GexfContent) null);
			return;
		case GexfPackage.DOCUMENT_ROOT__GRAPH:
			setGraph((GraphContent) null);
			return;
		case GexfPackage.DOCUMENT_ROOT__KEYWORDS:
			setKeywords(KEYWORDS_EDEFAULT);
			return;
		case GexfPackage.DOCUMENT_ROOT__META:
			setMeta((MetaContent) null);
			return;
		case GexfPackage.DOCUMENT_ROOT__NODE:
			setNode((NodeContent) null);
			return;
		case GexfPackage.DOCUMENT_ROOT__NODES:
			setNodes((NodesContent) null);
			return;
		case GexfPackage.DOCUMENT_ROOT__OPTIONS:
			setOptions(OPTIONS_EDEFAULT);
			return;
		case GexfPackage.DOCUMENT_ROOT__PARENT:
			setParent((ParentType) null);
			return;
		case GexfPackage.DOCUMENT_ROOT__PARENTS:
			setParents((ParentsContent) null);
			return;
		case GexfPackage.DOCUMENT_ROOT__POSITION:
			setPosition((PositionContent) null);
			return;
		case GexfPackage.DOCUMENT_ROOT__SIZE:
			setSize((SizeContent) null);
			return;
		case GexfPackage.DOCUMENT_ROOT__SPELL:
			setSpell((SpellType) null);
			return;
		case GexfPackage.DOCUMENT_ROOT__SPELLS:
			setSpells((SpellsContent) null);
			return;
		case GexfPackage.DOCUMENT_ROOT__THICKNESS:
			setThickness((ThicknessContent) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case GexfPackage.DOCUMENT_ROOT__MIXED:
			return mixed != null && !mixed.isEmpty();
		case GexfPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
		case GexfPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
		case GexfPackage.DOCUMENT_ROOT__ATTRIBUTE:
			return getAttribute() != null;
		case GexfPackage.DOCUMENT_ROOT__ATTRIBUTES:
			return getAttributes() != null;
		case GexfPackage.DOCUMENT_ROOT__ATTVALUE:
			return getAttvalue() != null;
		case GexfPackage.DOCUMENT_ROOT__ATTVALUES:
			return getAttvalues() != null;
		case GexfPackage.DOCUMENT_ROOT__COLOR:
			return getColor() != null;
		case GexfPackage.DOCUMENT_ROOT__CREATOR:
			return CREATOR_EDEFAULT == null ? getCreator() != null : !CREATOR_EDEFAULT.equals(getCreator());
		case GexfPackage.DOCUMENT_ROOT__DEFAULT:
			return DEFAULT_EDEFAULT == null ? getDefault() != null : !DEFAULT_EDEFAULT.equals(getDefault());
		case GexfPackage.DOCUMENT_ROOT__DESCRIPTION:
			return DESCRIPTION_EDEFAULT == null ? getDescription() != null : !DESCRIPTION_EDEFAULT
					.equals(getDescription());
		case GexfPackage.DOCUMENT_ROOT__EDGE:
			return getEdge() != null;
		case GexfPackage.DOCUMENT_ROOT__EDGES:
			return getEdges() != null;
		case GexfPackage.DOCUMENT_ROOT__GEXF:
			return getGexf() != null;
		case GexfPackage.DOCUMENT_ROOT__GRAPH:
			return getGraph() != null;
		case GexfPackage.DOCUMENT_ROOT__KEYWORDS:
			return KEYWORDS_EDEFAULT == null ? getKeywords() != null : !KEYWORDS_EDEFAULT.equals(getKeywords());
		case GexfPackage.DOCUMENT_ROOT__META:
			return getMeta() != null;
		case GexfPackage.DOCUMENT_ROOT__NODE:
			return getNode() != null;
		case GexfPackage.DOCUMENT_ROOT__NODES:
			return getNodes() != null;
		case GexfPackage.DOCUMENT_ROOT__OPTIONS:
			return OPTIONS_EDEFAULT == null ? getOptions() != null : !OPTIONS_EDEFAULT.equals(getOptions());
		case GexfPackage.DOCUMENT_ROOT__PARENT:
			return getParent() != null;
		case GexfPackage.DOCUMENT_ROOT__PARENTS:
			return getParents() != null;
		case GexfPackage.DOCUMENT_ROOT__POSITION:
			return getPosition() != null;
		case GexfPackage.DOCUMENT_ROOT__SIZE:
			return getSize() != null;
		case GexfPackage.DOCUMENT_ROOT__SPELL:
			return getSpell() != null;
		case GexfPackage.DOCUMENT_ROOT__SPELLS:
			return getSpells() != null;
		case GexfPackage.DOCUMENT_ROOT__THICKNESS:
			return getThickness() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(')');
		return result.toString();
	}

} //DocumentRootImpl
