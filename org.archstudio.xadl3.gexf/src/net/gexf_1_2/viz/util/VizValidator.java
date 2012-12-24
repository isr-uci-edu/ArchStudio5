/**
 */
package net.gexf_1_2.viz.util;

import java.math.BigInteger;
import java.util.Map;

import net.gexf_1_2.viz.ColorContent;
import net.gexf_1_2.viz.DocumentRoot;
import net.gexf_1_2.viz.EdgeShapeContent;
import net.gexf_1_2.viz.EdgeShapeType;
import net.gexf_1_2.viz.NodeShapeContent;
import net.gexf_1_2.viz.NodeShapeType;
import net.gexf_1_2.viz.PositionContent;
import net.gexf_1_2.viz.SizeContent;
import net.gexf_1_2.viz.ThicknessContent;
import net.gexf_1_2.viz.VizPackage;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;

/**
 * <!-- begin-user-doc --> The <b>Validator</b> for the model. <!-- end-user-doc
 * -->
 * 
 * @see net.gexf_1_2.viz.VizPackage
 * @generated
 */
public class VizValidator extends EObjectValidator {
	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final VizValidator INSTANCE = new VizValidator();

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
	public static final String DIAGNOSTIC_SOURCE = "net.gexf_1_2.viz";

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
	public VizValidator() {
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
		return VizPackage.eINSTANCE;
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
		case VizPackage.COLOR_CONTENT:
			return validateColorContent((ColorContent) value, diagnostics, context);
		case VizPackage.EDGE_SHAPE_CONTENT:
			return validateEdgeShapeContent((EdgeShapeContent) value, diagnostics, context);
		case VizPackage.NODE_SHAPE_CONTENT:
			return validateNodeShapeContent((NodeShapeContent) value, diagnostics, context);
		case VizPackage.POSITION_CONTENT:
			return validatePositionContent((PositionContent) value, diagnostics, context);
		case VizPackage.SIZE_CONTENT:
			return validateSizeContent((SizeContent) value, diagnostics, context);
		case VizPackage.THICKNESS_CONTENT:
			return validateThicknessContent((ThicknessContent) value, diagnostics, context);
		case VizPackage.DOCUMENT_ROOT:
			return validateDocumentRoot((DocumentRoot) value, diagnostics, context);
		case VizPackage.EDGE_SHAPE_TYPE:
			return validateEdgeShapeType((EdgeShapeType) value, diagnostics, context);
		case VizPackage.NODE_SHAPE_TYPE:
			return validateNodeShapeType((NodeShapeType) value, diagnostics, context);
		case VizPackage.ALPHA_CHANNEL:
			return validateAlphaChannel((Float) value, diagnostics, context);
		case VizPackage.ALPHA_CHANNEL_OBJECT:
			return validateAlphaChannelObject((Float) value, diagnostics, context);
		case VizPackage.COLOR_CHANNEL:
			return validateColorChannel((BigInteger) value, diagnostics, context);
		case VizPackage.EDGE_SHAPE_TYPE_OBJECT:
			return validateEdgeShapeTypeObject((EdgeShapeType) value, diagnostics, context);
		case VizPackage.NODE_SHAPE_TYPE_OBJECT:
			return validateNodeShapeTypeObject((NodeShapeType) value, diagnostics, context);
		case VizPackage.SIZE_TYPE:
			return validateSizeType((Float) value, diagnostics, context);
		case VizPackage.SIZE_TYPE_OBJECT:
			return validateSizeTypeObject((Float) value, diagnostics, context);
		case VizPackage.SPACE_POINT:
			return validateSpacePoint((Float) value, diagnostics, context);
		case VizPackage.SPACE_POINT_OBJECT:
			return validateSpacePointObject((Float) value, diagnostics, context);
		case VizPackage.THICKNESS_TYPE:
			return validateThicknessType((Float) value, diagnostics, context);
		case VizPackage.THICKNESS_TYPE_OBJECT:
			return validateThicknessTypeObject((Float) value, diagnostics, context);
		default:
			return true;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateColorContent(ColorContent colorContent, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(colorContent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEdgeShapeContent(EdgeShapeContent edgeShapeContent, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(edgeShapeContent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateNodeShapeContent(NodeShapeContent nodeShapeContent, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(nodeShapeContent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePositionContent(PositionContent positionContent, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(positionContent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateSizeContent(SizeContent sizeContent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(sizeContent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateThicknessContent(ThicknessContent thicknessContent, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(thicknessContent, diagnostics, context);
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
	public boolean validateEdgeShapeType(EdgeShapeType edgeShapeType, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateNodeShapeType(NodeShapeType nodeShapeType, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateAlphaChannel(float alphaChannel, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateAlphaChannel_Min(alphaChannel, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validateAlphaChannel_Max(alphaChannel, diagnostics, context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @see #validateAlphaChannel_Min
	 */
	public static final float ALPHA_CHANNEL__MIN__VALUE = 0.0F;

	/**
	 * Validates the Min constraint of '<em>Alpha Channel</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateAlphaChannel_Min(float alphaChannel, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = alphaChannel >= ALPHA_CHANNEL__MIN__VALUE;
		if (!result && diagnostics != null) {
			reportMinViolation(VizPackage.Literals.ALPHA_CHANNEL, alphaChannel, ALPHA_CHANNEL__MIN__VALUE, true,
					diagnostics, context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @see #validateAlphaChannel_Max
	 */
	public static final float ALPHA_CHANNEL__MAX__VALUE = 1.0F;

	/**
	 * Validates the Max constraint of '<em>Alpha Channel</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateAlphaChannel_Max(float alphaChannel, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = alphaChannel <= ALPHA_CHANNEL__MAX__VALUE;
		if (!result && diagnostics != null) {
			reportMaxViolation(VizPackage.Literals.ALPHA_CHANNEL, alphaChannel, ALPHA_CHANNEL__MAX__VALUE, true,
					diagnostics, context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateAlphaChannelObject(Float alphaChannelObject, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		boolean result = validateAlphaChannel_Min(alphaChannelObject, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validateAlphaChannel_Max(alphaChannelObject, diagnostics, context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateColorChannel(BigInteger colorChannel, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		boolean result = xmlTypeValidator.validateNonNegativeInteger_Min(colorChannel, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validateColorChannel_Max(colorChannel, diagnostics, context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @see #validateColorChannel_Max
	 */
	public static final BigInteger COLOR_CHANNEL__MAX__VALUE = new BigInteger("255");

	/**
	 * Validates the Max constraint of '<em>Color Channel</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateColorChannel_Max(BigInteger colorChannel, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		boolean result = colorChannel.compareTo(COLOR_CHANNEL__MAX__VALUE) <= 0;
		if (!result && diagnostics != null) {
			reportMaxViolation(VizPackage.Literals.COLOR_CHANNEL, colorChannel, COLOR_CHANNEL__MAX__VALUE, true,
					diagnostics, context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEdgeShapeTypeObject(EdgeShapeType edgeShapeTypeObject, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateNodeShapeTypeObject(NodeShapeType nodeShapeTypeObject, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateSizeType(float sizeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateSizeType_Min(sizeType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @see #validateSizeType_Min
	 */
	public static final float SIZE_TYPE__MIN__VALUE = 0.0F;

	/**
	 * Validates the Min constraint of '<em>Size Type</em>'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateSizeType_Min(float sizeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = sizeType >= SIZE_TYPE__MIN__VALUE;
		if (!result && diagnostics != null) {
			reportMinViolation(VizPackage.Literals.SIZE_TYPE, sizeType, SIZE_TYPE__MIN__VALUE, true, diagnostics,
					context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateSizeTypeObject(Float sizeTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateSizeType_Min(sizeTypeObject, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateSpacePoint(float spacePoint, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateSpacePointObject(Float spacePointObject, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateThicknessType(float thicknessType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateThicknessType_Min(thicknessType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @see #validateThicknessType_Min
	 */
	public static final float THICKNESS_TYPE__MIN__VALUE = 0.0F;

	/**
	 * Validates the Min constraint of '<em>Thickness Type</em>'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateThicknessType_Min(float thicknessType, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		boolean result = thicknessType >= THICKNESS_TYPE__MIN__VALUE;
		if (!result && diagnostics != null) {
			reportMinViolation(VizPackage.Literals.THICKNESS_TYPE, thicknessType, THICKNESS_TYPE__MIN__VALUE, true,
					diagnostics, context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateThicknessTypeObject(Float thicknessTypeObject, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		boolean result = validateThicknessType_Min(thicknessTypeObject, diagnostics, context);
		return result;
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

} //VizValidator
