/**
 */
package net.gexf_1_2.gexf.util;

import java.math.BigInteger;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

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

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;

/**
 * <!-- begin-user-doc --> The <b>Validator</b> for the model. <!-- end-user-doc
 * -->
 * 
 * @see net.gexf_1_2.gexf.GexfPackage
 * @generated
 */
public class GexfValidator extends EObjectValidator {
	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final GexfValidator INSTANCE = new GexfValidator();

	/**
	 * A constant for the
	 * {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of
	 * diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes}
	 * from this package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "net.gexf_1_2.gexf";

	/**
	 * A constant with a fixed name that can be used as the base value for
	 * additional hand written constants. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for
	 * additional hand written constants in a derived class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The cached base package validator. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected XMLTypeValidator xmlTypeValidator;

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public GexfValidator() {
		super();
		xmlTypeValidator = XMLTypeValidator.INSTANCE;
	}

	/**
	 * Returns the package of this validator switch. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected EPackage getEPackage() {
		return GexfPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the
	 * model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
		case GexfPackage.ATTRIBUTE_CONTENT:
			return validateAttributeContent((AttributeContent) value, diagnostics, context);
		case GexfPackage.ATTRIBUTES_CONTENT:
			return validateAttributesContent((AttributesContent) value, diagnostics, context);
		case GexfPackage.ATTVALUES_CONTENT:
			return validateAttvaluesContent((AttvaluesContent) value, diagnostics, context);
		case GexfPackage.ATTVALUE_TYPE:
			return validateAttvalueType((AttvalueType) value, diagnostics, context);
		case GexfPackage.DOCUMENT_ROOT:
			return validateDocumentRoot((DocumentRoot) value, diagnostics, context);
		case GexfPackage.EDGE_CONTENT:
			return validateEdgeContent((EdgeContent) value, diagnostics, context);
		case GexfPackage.EDGES_CONTENT:
			return validateEdgesContent((EdgesContent) value, diagnostics, context);
		case GexfPackage.GEXF_CONTENT:
			return validateGexfContent((GexfContent) value, diagnostics, context);
		case GexfPackage.GRAPH_CONTENT:
			return validateGraphContent((GraphContent) value, diagnostics, context);
		case GexfPackage.META_CONTENT:
			return validateMetaContent((MetaContent) value, diagnostics, context);
		case GexfPackage.NODE_CONTENT:
			return validateNodeContent((NodeContent) value, diagnostics, context);
		case GexfPackage.NODES_CONTENT:
			return validateNodesContent((NodesContent) value, diagnostics, context);
		case GexfPackage.PARENTS_CONTENT:
			return validateParentsContent((ParentsContent) value, diagnostics, context);
		case GexfPackage.PARENT_TYPE:
			return validateParentType((ParentType) value, diagnostics, context);
		case GexfPackage.SPELLS_CONTENT:
			return validateSpellsContent((SpellsContent) value, diagnostics, context);
		case GexfPackage.SPELL_TYPE:
			return validateSpellType((SpellType) value, diagnostics, context);
		case GexfPackage.ATTRTYPE_TYPE:
			return validateAttrtypeType((AttrtypeType) value, diagnostics, context);
		case GexfPackage.CLASS_TYPE:
			return validateClassType((ClassType) value, diagnostics, context);
		case GexfPackage.DEFAULTEDGETYPE_TYPE:
			return validateDefaultedgetypeType((DefaultedgetypeType) value, diagnostics, context);
		case GexfPackage.EDGETYPE_TYPE:
			return validateEdgetypeType((EdgetypeType) value, diagnostics, context);
		case GexfPackage.IDTYPE_TYPE:
			return validateIdtypeType((IdtypeType) value, diagnostics, context);
		case GexfPackage.MODE_TYPE:
			return validateModeType((ModeType) value, diagnostics, context);
		case GexfPackage.TIMEFORMAT_TYPE:
			return validateTimeformatType((TimeformatType) value, diagnostics, context);
		case GexfPackage.VERSION_TYPE:
			return validateVersionType((VersionType) value, diagnostics, context);
		case GexfPackage.ATTRTYPE_TYPE_OBJECT:
			return validateAttrtypeTypeObject((AttrtypeType) value, diagnostics, context);
		case GexfPackage.CLASS_TYPE_OBJECT:
			return validateClassTypeObject((ClassType) value, diagnostics, context);
		case GexfPackage.DEFAULTEDGETYPE_TYPE_OBJECT:
			return validateDefaultedgetypeTypeObject((DefaultedgetypeType) value, diagnostics, context);
		case GexfPackage.EDGETYPE_TYPE_OBJECT:
			return validateEdgetypeTypeObject((EdgetypeType) value, diagnostics, context);
		case GexfPackage.ID_TYPE:
			return validateIdType(value, diagnostics, context);
		case GexfPackage.IDTYPE_TYPE_OBJECT:
			return validateIdtypeTypeObject((IdtypeType) value, diagnostics, context);
		case GexfPackage.MODE_TYPE_OBJECT:
			return validateModeTypeObject((ModeType) value, diagnostics, context);
		case GexfPackage.TIMEFORMAT_TYPE_OBJECT:
			return validateTimeformatTypeObject((TimeformatType) value, diagnostics, context);
		case GexfPackage.TIME_TYPE:
			return validateTimeType(value, diagnostics, context);
		case GexfPackage.VERSION_TYPE_OBJECT:
			return validateVersionTypeObject((VersionType) value, diagnostics, context);
		case GexfPackage.WEIGHT_TYPE:
			return validateWeightType((Float) value, diagnostics, context);
		case GexfPackage.WEIGHT_TYPE_OBJECT:
			return validateWeightTypeObject((Float) value, diagnostics, context);
		default:
			return true;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateAttributeContent(AttributeContent attributeContent, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(attributeContent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateAttributesContent(AttributesContent attributesContent, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(attributesContent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateAttvaluesContent(AttvaluesContent attvaluesContent, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(attvaluesContent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateAttvalueType(AttvalueType attvalueType, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(attvalueType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateDocumentRoot(DocumentRoot documentRoot, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(documentRoot, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEdgeContent(EdgeContent edgeContent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(edgeContent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEdgesContent(EdgesContent edgesContent, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(edgesContent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateGexfContent(GexfContent gexfContent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(gexfContent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateGraphContent(GraphContent graphContent, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(graphContent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateMetaContent(MetaContent metaContent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(metaContent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateNodeContent(NodeContent nodeContent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(nodeContent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateNodesContent(NodesContent nodesContent, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(nodesContent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateParentsContent(ParentsContent parentsContent, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(parentsContent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateParentType(ParentType parentType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(parentType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateSpellsContent(SpellsContent spellsContent, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(spellsContent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateSpellType(SpellType spellType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(spellType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateAttrtypeType(AttrtypeType attrtypeType, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateClassType(ClassType classType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateDefaultedgetypeType(DefaultedgetypeType defaultedgetypeType, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEdgetypeType(EdgetypeType edgetypeType, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateIdtypeType(IdtypeType idtypeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateModeType(ModeType modeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateTimeformatType(TimeformatType timeformatType, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateVersionType(VersionType versionType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateAttrtypeTypeObject(AttrtypeType attrtypeTypeObject, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateClassTypeObject(ClassType classTypeObject, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateDefaultedgetypeTypeObject(DefaultedgetypeType defaultedgetypeTypeObject,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEdgetypeTypeObject(EdgetypeType edgetypeTypeObject, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateIdType(Object idType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateIdType_MemberTypes(idType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MemberTypes constraint of '<em>Id Type</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateIdType_MemberTypes(Object idType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (diagnostics != null) {
			BasicDiagnostic tempDiagnostics = new BasicDiagnostic();
			if (XMLTypePackage.Literals.STRING.isInstance(idType)) {
				if (xmlTypeValidator.validateString((String) idType, tempDiagnostics, context)) {
					return true;
				}
			}
			if (XMLTypePackage.Literals.INTEGER.isInstance(idType)) {
				if (xmlTypeValidator.validateInteger((BigInteger) idType, tempDiagnostics, context)) {
					return true;
				}
			}
			for (Diagnostic diagnostic : tempDiagnostics.getChildren()) {
				diagnostics.add(diagnostic);
			}
		}
		else {
			if (XMLTypePackage.Literals.STRING.isInstance(idType)) {
				if (xmlTypeValidator.validateString((String) idType, null, context)) {
					return true;
				}
			}
			if (XMLTypePackage.Literals.INTEGER.isInstance(idType)) {
				if (xmlTypeValidator.validateInteger((BigInteger) idType, null, context)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateIdtypeTypeObject(IdtypeType idtypeTypeObject, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateModeTypeObject(ModeType modeTypeObject, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateTimeformatTypeObject(TimeformatType timeformatTypeObject, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateTimeType(Object timeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateTimeType_MemberTypes(timeType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MemberTypes constraint of '<em>Time Type</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateTimeType_MemberTypes(Object timeType, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		if (diagnostics != null) {
			BasicDiagnostic tempDiagnostics = new BasicDiagnostic();
			if (XMLTypePackage.Literals.INTEGER.isInstance(timeType)) {
				if (xmlTypeValidator.validateInteger((BigInteger) timeType, tempDiagnostics, context)) {
					return true;
				}
			}
			if (XMLTypePackage.Literals.DOUBLE.isInstance(timeType)) {
				if (xmlTypeValidator.validateDouble((Double) timeType, tempDiagnostics, context)) {
					return true;
				}
			}
			if (XMLTypePackage.Literals.DATE.isInstance(timeType)) {
				if (xmlTypeValidator.validateDate((XMLGregorianCalendar) timeType, tempDiagnostics, context)) {
					return true;
				}
			}
			if (XMLTypePackage.Literals.DATE_TIME.isInstance(timeType)) {
				if (xmlTypeValidator.validateDateTime((XMLGregorianCalendar) timeType, tempDiagnostics, context)) {
					return true;
				}
			}
			for (Diagnostic diagnostic : tempDiagnostics.getChildren()) {
				diagnostics.add(diagnostic);
			}
		}
		else {
			if (XMLTypePackage.Literals.INTEGER.isInstance(timeType)) {
				if (xmlTypeValidator.validateInteger((BigInteger) timeType, null, context)) {
					return true;
				}
			}
			if (XMLTypePackage.Literals.DOUBLE.isInstance(timeType)) {
				if (xmlTypeValidator.validateDouble((Double) timeType, null, context)) {
					return true;
				}
			}
			if (XMLTypePackage.Literals.DATE.isInstance(timeType)) {
				if (xmlTypeValidator.validateDate((XMLGregorianCalendar) timeType, null, context)) {
					return true;
				}
			}
			if (XMLTypePackage.Literals.DATE_TIME.isInstance(timeType)) {
				if (xmlTypeValidator.validateDateTime((XMLGregorianCalendar) timeType, null, context)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateVersionTypeObject(VersionType versionTypeObject, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateWeightType(float weightType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateWeightTypeObject(Float weightTypeObject, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this
	 * validator's diagnostics. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //GexfValidator
