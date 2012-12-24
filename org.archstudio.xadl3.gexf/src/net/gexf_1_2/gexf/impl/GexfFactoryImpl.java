/**
 */
package net.gexf_1_2.gexf.impl;

import net.gexf_1_2.gexf.AttributeContent;
import net.gexf_1_2.gexf.AttributesContent;
import net.gexf_1_2.gexf.AttrtypeType;
import net.gexf_1_2.gexf.AttvalueType;
import net.gexf_1_2.gexf.AttvaluesContent;
import net.gexf_1_2.gexf.ClassType;
import net.gexf_1_2.gexf.DefaultedgetypeType;
import net.gexf_1_2.gexf.DocumentRoot;
import net.gexf_1_2.gexf.EdgeContent;
import net.gexf_1_2.gexf.EdgesContent;
import net.gexf_1_2.gexf.EdgetypeType;
import net.gexf_1_2.gexf.GexfContent;
import net.gexf_1_2.gexf.GexfFactory;
import net.gexf_1_2.gexf.GexfPackage;
import net.gexf_1_2.gexf.GraphContent;
import net.gexf_1_2.gexf.IdtypeType;
import net.gexf_1_2.gexf.MetaContent;
import net.gexf_1_2.gexf.ModeType;
import net.gexf_1_2.gexf.NodeContent;
import net.gexf_1_2.gexf.NodesContent;
import net.gexf_1_2.gexf.ParentType;
import net.gexf_1_2.gexf.ParentsContent;
import net.gexf_1_2.gexf.SpellType;
import net.gexf_1_2.gexf.SpellsContent;
import net.gexf_1_2.gexf.TimeformatType;
import net.gexf_1_2.gexf.VersionType;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class GexfFactoryImpl extends EFactoryImpl implements GexfFactory {
	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static GexfFactory init() {
		try {
			GexfFactory theGexfFactory = (GexfFactory) EPackage.Registry.INSTANCE
					.getEFactory("http://www.gexf.net/1.2draft");
			if (theGexfFactory != null) {
				return theGexfFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new GexfFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public GexfFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case GexfPackage.ATTRIBUTE_CONTENT:
			return createAttributeContent();
		case GexfPackage.ATTRIBUTES_CONTENT:
			return createAttributesContent();
		case GexfPackage.ATTVALUES_CONTENT:
			return createAttvaluesContent();
		case GexfPackage.ATTVALUE_TYPE:
			return createAttvalueType();
		case GexfPackage.DOCUMENT_ROOT:
			return createDocumentRoot();
		case GexfPackage.EDGE_CONTENT:
			return createEdgeContent();
		case GexfPackage.EDGES_CONTENT:
			return createEdgesContent();
		case GexfPackage.GEXF_CONTENT:
			return createGexfContent();
		case GexfPackage.GRAPH_CONTENT:
			return createGraphContent();
		case GexfPackage.META_CONTENT:
			return createMetaContent();
		case GexfPackage.NODE_CONTENT:
			return createNodeContent();
		case GexfPackage.NODES_CONTENT:
			return createNodesContent();
		case GexfPackage.PARENTS_CONTENT:
			return createParentsContent();
		case GexfPackage.PARENT_TYPE:
			return createParentType();
		case GexfPackage.SPELLS_CONTENT:
			return createSpellsContent();
		case GexfPackage.SPELL_TYPE:
			return createSpellType();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
		case GexfPackage.ATTRTYPE_TYPE:
			return createAttrtypeTypeFromString(eDataType, initialValue);
		case GexfPackage.CLASS_TYPE:
			return createClassTypeFromString(eDataType, initialValue);
		case GexfPackage.DEFAULTEDGETYPE_TYPE:
			return createDefaultedgetypeTypeFromString(eDataType, initialValue);
		case GexfPackage.EDGETYPE_TYPE:
			return createEdgetypeTypeFromString(eDataType, initialValue);
		case GexfPackage.IDTYPE_TYPE:
			return createIdtypeTypeFromString(eDataType, initialValue);
		case GexfPackage.MODE_TYPE:
			return createModeTypeFromString(eDataType, initialValue);
		case GexfPackage.TIMEFORMAT_TYPE:
			return createTimeformatTypeFromString(eDataType, initialValue);
		case GexfPackage.VERSION_TYPE:
			return createVersionTypeFromString(eDataType, initialValue);
		case GexfPackage.ATTRTYPE_TYPE_OBJECT:
			return createAttrtypeTypeObjectFromString(eDataType, initialValue);
		case GexfPackage.CLASS_TYPE_OBJECT:
			return createClassTypeObjectFromString(eDataType, initialValue);
		case GexfPackage.DEFAULTEDGETYPE_TYPE_OBJECT:
			return createDefaultedgetypeTypeObjectFromString(eDataType, initialValue);
		case GexfPackage.EDGETYPE_TYPE_OBJECT:
			return createEdgetypeTypeObjectFromString(eDataType, initialValue);
		case GexfPackage.ID_TYPE:
			return createIdTypeFromString(eDataType, initialValue);
		case GexfPackage.IDTYPE_TYPE_OBJECT:
			return createIdtypeTypeObjectFromString(eDataType, initialValue);
		case GexfPackage.MODE_TYPE_OBJECT:
			return createModeTypeObjectFromString(eDataType, initialValue);
		case GexfPackage.TIMEFORMAT_TYPE_OBJECT:
			return createTimeformatTypeObjectFromString(eDataType, initialValue);
		case GexfPackage.TIME_TYPE:
			return createTimeTypeFromString(eDataType, initialValue);
		case GexfPackage.VERSION_TYPE_OBJECT:
			return createVersionTypeObjectFromString(eDataType, initialValue);
		case GexfPackage.WEIGHT_TYPE:
			return createWeightTypeFromString(eDataType, initialValue);
		case GexfPackage.WEIGHT_TYPE_OBJECT:
			return createWeightTypeObjectFromString(eDataType, initialValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
		case GexfPackage.ATTRTYPE_TYPE:
			return convertAttrtypeTypeToString(eDataType, instanceValue);
		case GexfPackage.CLASS_TYPE:
			return convertClassTypeToString(eDataType, instanceValue);
		case GexfPackage.DEFAULTEDGETYPE_TYPE:
			return convertDefaultedgetypeTypeToString(eDataType, instanceValue);
		case GexfPackage.EDGETYPE_TYPE:
			return convertEdgetypeTypeToString(eDataType, instanceValue);
		case GexfPackage.IDTYPE_TYPE:
			return convertIdtypeTypeToString(eDataType, instanceValue);
		case GexfPackage.MODE_TYPE:
			return convertModeTypeToString(eDataType, instanceValue);
		case GexfPackage.TIMEFORMAT_TYPE:
			return convertTimeformatTypeToString(eDataType, instanceValue);
		case GexfPackage.VERSION_TYPE:
			return convertVersionTypeToString(eDataType, instanceValue);
		case GexfPackage.ATTRTYPE_TYPE_OBJECT:
			return convertAttrtypeTypeObjectToString(eDataType, instanceValue);
		case GexfPackage.CLASS_TYPE_OBJECT:
			return convertClassTypeObjectToString(eDataType, instanceValue);
		case GexfPackage.DEFAULTEDGETYPE_TYPE_OBJECT:
			return convertDefaultedgetypeTypeObjectToString(eDataType, instanceValue);
		case GexfPackage.EDGETYPE_TYPE_OBJECT:
			return convertEdgetypeTypeObjectToString(eDataType, instanceValue);
		case GexfPackage.ID_TYPE:
			return convertIdTypeToString(eDataType, instanceValue);
		case GexfPackage.IDTYPE_TYPE_OBJECT:
			return convertIdtypeTypeObjectToString(eDataType, instanceValue);
		case GexfPackage.MODE_TYPE_OBJECT:
			return convertModeTypeObjectToString(eDataType, instanceValue);
		case GexfPackage.TIMEFORMAT_TYPE_OBJECT:
			return convertTimeformatTypeObjectToString(eDataType, instanceValue);
		case GexfPackage.TIME_TYPE:
			return convertTimeTypeToString(eDataType, instanceValue);
		case GexfPackage.VERSION_TYPE_OBJECT:
			return convertVersionTypeObjectToString(eDataType, instanceValue);
		case GexfPackage.WEIGHT_TYPE:
			return convertWeightTypeToString(eDataType, instanceValue);
		case GexfPackage.WEIGHT_TYPE_OBJECT:
			return convertWeightTypeObjectToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public AttributeContent createAttributeContent() {
		AttributeContentImpl attributeContent = new AttributeContentImpl();
		return attributeContent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public AttributesContent createAttributesContent() {
		AttributesContentImpl attributesContent = new AttributesContentImpl();
		return attributesContent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public AttvaluesContent createAttvaluesContent() {
		AttvaluesContentImpl attvaluesContent = new AttvaluesContentImpl();
		return attvaluesContent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public AttvalueType createAttvalueType() {
		AttvalueTypeImpl attvalueType = new AttvalueTypeImpl();
		return attvalueType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EdgeContent createEdgeContent() {
		EdgeContentImpl edgeContent = new EdgeContentImpl();
		return edgeContent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EdgesContent createEdgesContent() {
		EdgesContentImpl edgesContent = new EdgesContentImpl();
		return edgesContent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public GexfContent createGexfContent() {
		GexfContentImpl gexfContent = new GexfContentImpl();
		return gexfContent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public GraphContent createGraphContent() {
		GraphContentImpl graphContent = new GraphContentImpl();
		return graphContent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public MetaContent createMetaContent() {
		MetaContentImpl metaContent = new MetaContentImpl();
		return metaContent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NodeContent createNodeContent() {
		NodeContentImpl nodeContent = new NodeContentImpl();
		return nodeContent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NodesContent createNodesContent() {
		NodesContentImpl nodesContent = new NodesContentImpl();
		return nodesContent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ParentsContent createParentsContent() {
		ParentsContentImpl parentsContent = new ParentsContentImpl();
		return parentsContent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ParentType createParentType() {
		ParentTypeImpl parentType = new ParentTypeImpl();
		return parentType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public SpellsContent createSpellsContent() {
		SpellsContentImpl spellsContent = new SpellsContentImpl();
		return spellsContent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public SpellType createSpellType() {
		SpellTypeImpl spellType = new SpellTypeImpl();
		return spellType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public AttrtypeType createAttrtypeTypeFromString(EDataType eDataType, String initialValue) {
		AttrtypeType result = AttrtypeType.get(initialValue);
		if (result == null) {
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
					+ eDataType.getName() + "'");
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertAttrtypeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ClassType createClassTypeFromString(EDataType eDataType, String initialValue) {
		ClassType result = ClassType.get(initialValue);
		if (result == null) {
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
					+ eDataType.getName() + "'");
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertClassTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DefaultedgetypeType createDefaultedgetypeTypeFromString(EDataType eDataType, String initialValue) {
		DefaultedgetypeType result = DefaultedgetypeType.get(initialValue);
		if (result == null) {
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
					+ eDataType.getName() + "'");
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertDefaultedgetypeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EdgetypeType createEdgetypeTypeFromString(EDataType eDataType, String initialValue) {
		EdgetypeType result = EdgetypeType.get(initialValue);
		if (result == null) {
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
					+ eDataType.getName() + "'");
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertEdgetypeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public IdtypeType createIdtypeTypeFromString(EDataType eDataType, String initialValue) {
		IdtypeType result = IdtypeType.get(initialValue);
		if (result == null) {
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
					+ eDataType.getName() + "'");
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertIdtypeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ModeType createModeTypeFromString(EDataType eDataType, String initialValue) {
		ModeType result = ModeType.get(initialValue);
		if (result == null) {
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
					+ eDataType.getName() + "'");
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertModeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public TimeformatType createTimeformatTypeFromString(EDataType eDataType, String initialValue) {
		TimeformatType result = TimeformatType.get(initialValue);
		if (result == null) {
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
					+ eDataType.getName() + "'");
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertTimeformatTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public VersionType createVersionTypeFromString(EDataType eDataType, String initialValue) {
		VersionType result = VersionType.get(initialValue);
		if (result == null) {
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
					+ eDataType.getName() + "'");
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertVersionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public AttrtypeType createAttrtypeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createAttrtypeTypeFromString(GexfPackage.Literals.ATTRTYPE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertAttrtypeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertAttrtypeTypeToString(GexfPackage.Literals.ATTRTYPE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ClassType createClassTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createClassTypeFromString(GexfPackage.Literals.CLASS_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertClassTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertClassTypeToString(GexfPackage.Literals.CLASS_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DefaultedgetypeType createDefaultedgetypeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createDefaultedgetypeTypeFromString(GexfPackage.Literals.DEFAULTEDGETYPE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertDefaultedgetypeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertDefaultedgetypeTypeToString(GexfPackage.Literals.DEFAULTEDGETYPE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EdgetypeType createEdgetypeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createEdgetypeTypeFromString(GexfPackage.Literals.EDGETYPE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertEdgetypeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertEdgetypeTypeToString(GexfPackage.Literals.EDGETYPE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object createIdTypeFromString(EDataType eDataType, String initialValue) {
		if (initialValue == null) {
			return null;
		}
		Object result = null;
		RuntimeException exception = null;
		try {
			result = XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
			if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null)) {
				return result;
			}
		}
		catch (RuntimeException e) {
			exception = e;
		}
		try {
			result = XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.INTEGER, initialValue);
			if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null)) {
				return result;
			}
		}
		catch (RuntimeException e) {
			exception = e;
		}
		if (result != null || exception == null) {
			return result;
		}

		throw exception;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertIdTypeToString(EDataType eDataType, Object instanceValue) {
		if (instanceValue == null) {
			return null;
		}
		if (XMLTypePackage.Literals.STRING.isInstance(instanceValue)) {
			try {
				String value = XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
				if (value != null) {
					return value;
				}
			}
			catch (Exception e) {
				// Keep trying other member types until all have failed.
			}
		}
		if (XMLTypePackage.Literals.INTEGER.isInstance(instanceValue)) {
			try {
				String value = XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.INTEGER, instanceValue);
				if (value != null) {
					return value;
				}
			}
			catch (Exception e) {
				// Keep trying other member types until all have failed.
			}
		}
		throw new IllegalArgumentException("Invalid value: '" + instanceValue + "' for datatype :"
				+ eDataType.getName());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public IdtypeType createIdtypeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createIdtypeTypeFromString(GexfPackage.Literals.IDTYPE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertIdtypeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertIdtypeTypeToString(GexfPackage.Literals.IDTYPE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ModeType createModeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createModeTypeFromString(GexfPackage.Literals.MODE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertModeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertModeTypeToString(GexfPackage.Literals.MODE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public TimeformatType createTimeformatTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createTimeformatTypeFromString(GexfPackage.Literals.TIMEFORMAT_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertTimeformatTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTimeformatTypeToString(GexfPackage.Literals.TIMEFORMAT_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object createTimeTypeFromString(EDataType eDataType, String initialValue) {
		if (initialValue == null) {
			return null;
		}
		Object result = null;
		RuntimeException exception = null;
		try {
			result = XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.INTEGER, initialValue);
			if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null)) {
				return result;
			}
		}
		catch (RuntimeException e) {
			exception = e;
		}
		try {
			result = XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.DOUBLE, initialValue);
			if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null)) {
				return result;
			}
		}
		catch (RuntimeException e) {
			exception = e;
		}
		try {
			result = XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.DATE, initialValue);
			if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null)) {
				return result;
			}
		}
		catch (RuntimeException e) {
			exception = e;
		}
		try {
			result = XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.DATE_TIME, initialValue);
			if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null)) {
				return result;
			}
		}
		catch (RuntimeException e) {
			exception = e;
		}
		if (result != null || exception == null) {
			return result;
		}

		throw exception;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertTimeTypeToString(EDataType eDataType, Object instanceValue) {
		if (instanceValue == null) {
			return null;
		}
		if (XMLTypePackage.Literals.INTEGER.isInstance(instanceValue)) {
			try {
				String value = XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.INTEGER, instanceValue);
				if (value != null) {
					return value;
				}
			}
			catch (Exception e) {
				// Keep trying other member types until all have failed.
			}
		}
		if (XMLTypePackage.Literals.DOUBLE.isInstance(instanceValue)) {
			try {
				String value = XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.DOUBLE, instanceValue);
				if (value != null) {
					return value;
				}
			}
			catch (Exception e) {
				// Keep trying other member types until all have failed.
			}
		}
		if (XMLTypePackage.Literals.DATE.isInstance(instanceValue)) {
			try {
				String value = XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.DATE, instanceValue);
				if (value != null) {
					return value;
				}
			}
			catch (Exception e) {
				// Keep trying other member types until all have failed.
			}
		}
		if (XMLTypePackage.Literals.DATE_TIME.isInstance(instanceValue)) {
			try {
				String value = XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.DATE_TIME,
						instanceValue);
				if (value != null) {
					return value;
				}
			}
			catch (Exception e) {
				// Keep trying other member types until all have failed.
			}
		}
		throw new IllegalArgumentException("Invalid value: '" + instanceValue + "' for datatype :"
				+ eDataType.getName());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public VersionType createVersionTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createVersionTypeFromString(GexfPackage.Literals.VERSION_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertVersionTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertVersionTypeToString(GexfPackage.Literals.VERSION_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Float createWeightTypeFromString(EDataType eDataType, String initialValue) {
		return (Float) XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.FLOAT, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertWeightTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.FLOAT, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Float createWeightTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createWeightTypeFromString(GexfPackage.Literals.WEIGHT_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertWeightTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertWeightTypeToString(GexfPackage.Literals.WEIGHT_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public GexfPackage getGexfPackage() {
		return (GexfPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static GexfPackage getPackage() {
		return GexfPackage.eINSTANCE;
	}

} //GexfFactoryImpl
