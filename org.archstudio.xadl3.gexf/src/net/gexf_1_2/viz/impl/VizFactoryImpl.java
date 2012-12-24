/**
 */
package net.gexf_1_2.viz.impl;

import java.math.BigInteger;

import net.gexf_1_2.viz.ColorContent;
import net.gexf_1_2.viz.DocumentRoot;
import net.gexf_1_2.viz.EdgeShapeContent;
import net.gexf_1_2.viz.EdgeShapeType;
import net.gexf_1_2.viz.NodeShapeContent;
import net.gexf_1_2.viz.NodeShapeType;
import net.gexf_1_2.viz.PositionContent;
import net.gexf_1_2.viz.SizeContent;
import net.gexf_1_2.viz.ThicknessContent;
import net.gexf_1_2.viz.VizFactory;
import net.gexf_1_2.viz.VizPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class VizFactoryImpl extends EFactoryImpl implements VizFactory {
	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static VizFactory init() {
		try {
			VizFactory theVizFactory = (VizFactory) EPackage.Registry.INSTANCE
					.getEFactory("http://www.gexf.net/1.2draft/viz");
			if (theVizFactory != null) {
				return theVizFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new VizFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public VizFactoryImpl() {
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
		case VizPackage.COLOR_CONTENT:
			return createColorContent();
		case VizPackage.EDGE_SHAPE_CONTENT:
			return createEdgeShapeContent();
		case VizPackage.NODE_SHAPE_CONTENT:
			return createNodeShapeContent();
		case VizPackage.POSITION_CONTENT:
			return createPositionContent();
		case VizPackage.SIZE_CONTENT:
			return createSizeContent();
		case VizPackage.THICKNESS_CONTENT:
			return createThicknessContent();
		case VizPackage.DOCUMENT_ROOT:
			return createDocumentRoot();
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
		case VizPackage.EDGE_SHAPE_TYPE:
			return createEdgeShapeTypeFromString(eDataType, initialValue);
		case VizPackage.NODE_SHAPE_TYPE:
			return createNodeShapeTypeFromString(eDataType, initialValue);
		case VizPackage.ALPHA_CHANNEL:
			return createAlphaChannelFromString(eDataType, initialValue);
		case VizPackage.ALPHA_CHANNEL_OBJECT:
			return createAlphaChannelObjectFromString(eDataType, initialValue);
		case VizPackage.COLOR_CHANNEL:
			return createColorChannelFromString(eDataType, initialValue);
		case VizPackage.EDGE_SHAPE_TYPE_OBJECT:
			return createEdgeShapeTypeObjectFromString(eDataType, initialValue);
		case VizPackage.NODE_SHAPE_TYPE_OBJECT:
			return createNodeShapeTypeObjectFromString(eDataType, initialValue);
		case VizPackage.SIZE_TYPE:
			return createSizeTypeFromString(eDataType, initialValue);
		case VizPackage.SIZE_TYPE_OBJECT:
			return createSizeTypeObjectFromString(eDataType, initialValue);
		case VizPackage.SPACE_POINT:
			return createSpacePointFromString(eDataType, initialValue);
		case VizPackage.SPACE_POINT_OBJECT:
			return createSpacePointObjectFromString(eDataType, initialValue);
		case VizPackage.THICKNESS_TYPE:
			return createThicknessTypeFromString(eDataType, initialValue);
		case VizPackage.THICKNESS_TYPE_OBJECT:
			return createThicknessTypeObjectFromString(eDataType, initialValue);
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
		case VizPackage.EDGE_SHAPE_TYPE:
			return convertEdgeShapeTypeToString(eDataType, instanceValue);
		case VizPackage.NODE_SHAPE_TYPE:
			return convertNodeShapeTypeToString(eDataType, instanceValue);
		case VizPackage.ALPHA_CHANNEL:
			return convertAlphaChannelToString(eDataType, instanceValue);
		case VizPackage.ALPHA_CHANNEL_OBJECT:
			return convertAlphaChannelObjectToString(eDataType, instanceValue);
		case VizPackage.COLOR_CHANNEL:
			return convertColorChannelToString(eDataType, instanceValue);
		case VizPackage.EDGE_SHAPE_TYPE_OBJECT:
			return convertEdgeShapeTypeObjectToString(eDataType, instanceValue);
		case VizPackage.NODE_SHAPE_TYPE_OBJECT:
			return convertNodeShapeTypeObjectToString(eDataType, instanceValue);
		case VizPackage.SIZE_TYPE:
			return convertSizeTypeToString(eDataType, instanceValue);
		case VizPackage.SIZE_TYPE_OBJECT:
			return convertSizeTypeObjectToString(eDataType, instanceValue);
		case VizPackage.SPACE_POINT:
			return convertSpacePointToString(eDataType, instanceValue);
		case VizPackage.SPACE_POINT_OBJECT:
			return convertSpacePointObjectToString(eDataType, instanceValue);
		case VizPackage.THICKNESS_TYPE:
			return convertThicknessTypeToString(eDataType, instanceValue);
		case VizPackage.THICKNESS_TYPE_OBJECT:
			return convertThicknessTypeObjectToString(eDataType, instanceValue);
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
	public ColorContent createColorContent() {
		ColorContentImpl colorContent = new ColorContentImpl();
		return colorContent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EdgeShapeContent createEdgeShapeContent() {
		EdgeShapeContentImpl edgeShapeContent = new EdgeShapeContentImpl();
		return edgeShapeContent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NodeShapeContent createNodeShapeContent() {
		NodeShapeContentImpl nodeShapeContent = new NodeShapeContentImpl();
		return nodeShapeContent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public PositionContent createPositionContent() {
		PositionContentImpl positionContent = new PositionContentImpl();
		return positionContent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public SizeContent createSizeContent() {
		SizeContentImpl sizeContent = new SizeContentImpl();
		return sizeContent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ThicknessContent createThicknessContent() {
		ThicknessContentImpl thicknessContent = new ThicknessContentImpl();
		return thicknessContent;
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
	public EdgeShapeType createEdgeShapeTypeFromString(EDataType eDataType, String initialValue) {
		EdgeShapeType result = EdgeShapeType.get(initialValue);
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
	public String convertEdgeShapeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NodeShapeType createNodeShapeTypeFromString(EDataType eDataType, String initialValue) {
		NodeShapeType result = NodeShapeType.get(initialValue);
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
	public String convertNodeShapeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Float createAlphaChannelFromString(EDataType eDataType, String initialValue) {
		return (Float) XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.FLOAT, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertAlphaChannelToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.FLOAT, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Float createAlphaChannelObjectFromString(EDataType eDataType, String initialValue) {
		return createAlphaChannelFromString(VizPackage.Literals.ALPHA_CHANNEL, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertAlphaChannelObjectToString(EDataType eDataType, Object instanceValue) {
		return convertAlphaChannelToString(VizPackage.Literals.ALPHA_CHANNEL, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public BigInteger createColorChannelFromString(EDataType eDataType, String initialValue) {
		return (BigInteger) XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER,
				initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertColorChannelToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EdgeShapeType createEdgeShapeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createEdgeShapeTypeFromString(VizPackage.Literals.EDGE_SHAPE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertEdgeShapeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertEdgeShapeTypeToString(VizPackage.Literals.EDGE_SHAPE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NodeShapeType createNodeShapeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createNodeShapeTypeFromString(VizPackage.Literals.NODE_SHAPE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertNodeShapeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertNodeShapeTypeToString(VizPackage.Literals.NODE_SHAPE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Float createSizeTypeFromString(EDataType eDataType, String initialValue) {
		return (Float) XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.FLOAT, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertSizeTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.FLOAT, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Float createSizeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createSizeTypeFromString(VizPackage.Literals.SIZE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertSizeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertSizeTypeToString(VizPackage.Literals.SIZE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Float createSpacePointFromString(EDataType eDataType, String initialValue) {
		return (Float) XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.FLOAT, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertSpacePointToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.FLOAT, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Float createSpacePointObjectFromString(EDataType eDataType, String initialValue) {
		return createSpacePointFromString(VizPackage.Literals.SPACE_POINT, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertSpacePointObjectToString(EDataType eDataType, Object instanceValue) {
		return convertSpacePointToString(VizPackage.Literals.SPACE_POINT, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Float createThicknessTypeFromString(EDataType eDataType, String initialValue) {
		return (Float) XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.FLOAT, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertThicknessTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.FLOAT, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Float createThicknessTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createThicknessTypeFromString(VizPackage.Literals.THICKNESS_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertThicknessTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertThicknessTypeToString(VizPackage.Literals.THICKNESS_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public VizPackage getVizPackage() {
		return (VizPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static VizPackage getPackage() {
		return VizPackage.eINSTANCE;
	}

} //VizFactoryImpl
