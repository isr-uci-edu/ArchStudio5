/**
 */
package net.gexf_1_2.viz.impl;

import java.math.BigInteger;

import net.gexf_1_2.gexf.GexfPackage;
import net.gexf_1_2.gexf.impl.GexfPackageImpl;
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
import net.gexf_1_2.viz.util.VizValidator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class VizPackageImpl extends EPackageImpl implements VizPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass colorContentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass edgeShapeContentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass nodeShapeContentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass positionContentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass sizeContentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass thicknessContentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass documentRootEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum edgeShapeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum nodeShapeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType alphaChannelEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType alphaChannelObjectEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType colorChannelEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType edgeShapeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType nodeShapeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType sizeTypeEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType sizeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType spacePointEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType spacePointObjectEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType thicknessTypeEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType thicknessTypeObjectEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
	 * package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory
	 * method {@link #init init()}, which also performs initialization of the
	 * package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see net.gexf_1_2.viz.VizPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private VizPackageImpl() {
		super(eNS_URI, VizFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model,
	 * and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link VizPackage#eINSTANCE} when that
	 * field is accessed. Clients should not invoke it directly. Instead, they
	 * should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static VizPackage init() {
		if (isInited) {
			return (VizPackage) EPackage.Registry.INSTANCE.getEPackage(VizPackage.eNS_URI);
		}

		// Obtain or create and register package
		VizPackageImpl theVizPackage = (VizPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof VizPackageImpl ? EPackage.Registry.INSTANCE
				.get(eNS_URI) : new VizPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		GexfPackageImpl theGexfPackage = (GexfPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(GexfPackage.eNS_URI) instanceof GexfPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(GexfPackage.eNS_URI) : GexfPackage.eINSTANCE);

		// Create package meta-data objects
		theVizPackage.createPackageContents();
		theGexfPackage.createPackageContents();

		// Initialize created meta-data
		theVizPackage.initializePackageContents();
		theGexfPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put(theVizPackage, new EValidator.Descriptor() {
			public EValidator getEValidator() {
				return VizValidator.INSTANCE;
			}
		});

		// Mark meta-data to indicate it can't be changed
		theVizPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(VizPackage.eNS_URI, theVizPackage);
		return theVizPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getColorContent() {
		return colorContentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getColorContent_Spells() {
		return (EReference) colorContentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getColorContent_A() {
		return (EAttribute) colorContentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getColorContent_B() {
		return (EAttribute) colorContentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getColorContent_End() {
		return (EAttribute) colorContentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getColorContent_Endopen() {
		return (EAttribute) colorContentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getColorContent_G() {
		return (EAttribute) colorContentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getColorContent_R() {
		return (EAttribute) colorContentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getColorContent_Start() {
		return (EAttribute) colorContentEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getColorContent_Startopen() {
		return (EAttribute) colorContentEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getEdgeShapeContent() {
		return edgeShapeContentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getEdgeShapeContent_Spells() {
		return (EReference) edgeShapeContentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getEdgeShapeContent_End() {
		return (EAttribute) edgeShapeContentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getEdgeShapeContent_Endopen() {
		return (EAttribute) edgeShapeContentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getEdgeShapeContent_Start() {
		return (EAttribute) edgeShapeContentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getEdgeShapeContent_Startopen() {
		return (EAttribute) edgeShapeContentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getEdgeShapeContent_Value() {
		return (EAttribute) edgeShapeContentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getNodeShapeContent() {
		return nodeShapeContentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getNodeShapeContent_Spells() {
		return (EReference) nodeShapeContentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getNodeShapeContent_End() {
		return (EAttribute) nodeShapeContentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getNodeShapeContent_Endopen() {
		return (EAttribute) nodeShapeContentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getNodeShapeContent_Start() {
		return (EAttribute) nodeShapeContentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getNodeShapeContent_Startopen() {
		return (EAttribute) nodeShapeContentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getNodeShapeContent_Uri() {
		return (EAttribute) nodeShapeContentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getNodeShapeContent_Value() {
		return (EAttribute) nodeShapeContentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPositionContent() {
		return positionContentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPositionContent_Spells() {
		return (EReference) positionContentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPositionContent_End() {
		return (EAttribute) positionContentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPositionContent_Endopen() {
		return (EAttribute) positionContentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPositionContent_Start() {
		return (EAttribute) positionContentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPositionContent_Startopen() {
		return (EAttribute) positionContentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPositionContent_X() {
		return (EAttribute) positionContentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPositionContent_Y() {
		return (EAttribute) positionContentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPositionContent_Z() {
		return (EAttribute) positionContentEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getSizeContent() {
		return sizeContentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getSizeContent_Spells() {
		return (EReference) sizeContentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getSizeContent_End() {
		return (EAttribute) sizeContentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getSizeContent_Endopen() {
		return (EAttribute) sizeContentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getSizeContent_Start() {
		return (EAttribute) sizeContentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getSizeContent_Startopen() {
		return (EAttribute) sizeContentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getSizeContent_Value() {
		return (EAttribute) sizeContentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getThicknessContent() {
		return thicknessContentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getThicknessContent_Spells() {
		return (EReference) thicknessContentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getThicknessContent_End() {
		return (EAttribute) thicknessContentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getThicknessContent_Endopen() {
		return (EAttribute) thicknessContentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getThicknessContent_Start() {
		return (EAttribute) thicknessContentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getThicknessContent_Startopen() {
		return (EAttribute) thicknessContentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getThicknessContent_Value() {
		return (EAttribute) thicknessContentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getDocumentRoot() {
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute) documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_Spells() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EEnum getEdgeShapeType() {
		return edgeShapeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EEnum getNodeShapeType() {
		return nodeShapeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getAlphaChannel() {
		return alphaChannelEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getAlphaChannelObject() {
		return alphaChannelObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getColorChannel() {
		return colorChannelEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getEdgeShapeTypeObject() {
		return edgeShapeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getNodeShapeTypeObject() {
		return nodeShapeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getSizeType() {
		return sizeTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getSizeTypeObject() {
		return sizeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getSpacePoint() {
		return spacePointEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getSpacePointObject() {
		return spacePointObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getThicknessType() {
		return thicknessTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getThicknessTypeObject() {
		return thicknessTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public VizFactory getVizFactory() {
		return (VizFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is guarded to
	 * have no affect on any invocation but its first. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) {
			return;
		}
		isCreated = true;

		// Create classes and their features
		colorContentEClass = createEClass(COLOR_CONTENT);
		createEReference(colorContentEClass, COLOR_CONTENT__SPELLS);
		createEAttribute(colorContentEClass, COLOR_CONTENT__A);
		createEAttribute(colorContentEClass, COLOR_CONTENT__B);
		createEAttribute(colorContentEClass, COLOR_CONTENT__END);
		createEAttribute(colorContentEClass, COLOR_CONTENT__ENDOPEN);
		createEAttribute(colorContentEClass, COLOR_CONTENT__G);
		createEAttribute(colorContentEClass, COLOR_CONTENT__R);
		createEAttribute(colorContentEClass, COLOR_CONTENT__START);
		createEAttribute(colorContentEClass, COLOR_CONTENT__STARTOPEN);

		edgeShapeContentEClass = createEClass(EDGE_SHAPE_CONTENT);
		createEReference(edgeShapeContentEClass, EDGE_SHAPE_CONTENT__SPELLS);
		createEAttribute(edgeShapeContentEClass, EDGE_SHAPE_CONTENT__END);
		createEAttribute(edgeShapeContentEClass, EDGE_SHAPE_CONTENT__ENDOPEN);
		createEAttribute(edgeShapeContentEClass, EDGE_SHAPE_CONTENT__START);
		createEAttribute(edgeShapeContentEClass, EDGE_SHAPE_CONTENT__STARTOPEN);
		createEAttribute(edgeShapeContentEClass, EDGE_SHAPE_CONTENT__VALUE);

		nodeShapeContentEClass = createEClass(NODE_SHAPE_CONTENT);
		createEReference(nodeShapeContentEClass, NODE_SHAPE_CONTENT__SPELLS);
		createEAttribute(nodeShapeContentEClass, NODE_SHAPE_CONTENT__END);
		createEAttribute(nodeShapeContentEClass, NODE_SHAPE_CONTENT__ENDOPEN);
		createEAttribute(nodeShapeContentEClass, NODE_SHAPE_CONTENT__START);
		createEAttribute(nodeShapeContentEClass, NODE_SHAPE_CONTENT__STARTOPEN);
		createEAttribute(nodeShapeContentEClass, NODE_SHAPE_CONTENT__URI);
		createEAttribute(nodeShapeContentEClass, NODE_SHAPE_CONTENT__VALUE);

		positionContentEClass = createEClass(POSITION_CONTENT);
		createEReference(positionContentEClass, POSITION_CONTENT__SPELLS);
		createEAttribute(positionContentEClass, POSITION_CONTENT__END);
		createEAttribute(positionContentEClass, POSITION_CONTENT__ENDOPEN);
		createEAttribute(positionContentEClass, POSITION_CONTENT__START);
		createEAttribute(positionContentEClass, POSITION_CONTENT__STARTOPEN);
		createEAttribute(positionContentEClass, POSITION_CONTENT__X);
		createEAttribute(positionContentEClass, POSITION_CONTENT__Y);
		createEAttribute(positionContentEClass, POSITION_CONTENT__Z);

		sizeContentEClass = createEClass(SIZE_CONTENT);
		createEReference(sizeContentEClass, SIZE_CONTENT__SPELLS);
		createEAttribute(sizeContentEClass, SIZE_CONTENT__END);
		createEAttribute(sizeContentEClass, SIZE_CONTENT__ENDOPEN);
		createEAttribute(sizeContentEClass, SIZE_CONTENT__START);
		createEAttribute(sizeContentEClass, SIZE_CONTENT__STARTOPEN);
		createEAttribute(sizeContentEClass, SIZE_CONTENT__VALUE);

		thicknessContentEClass = createEClass(THICKNESS_CONTENT);
		createEReference(thicknessContentEClass, THICKNESS_CONTENT__SPELLS);
		createEAttribute(thicknessContentEClass, THICKNESS_CONTENT__END);
		createEAttribute(thicknessContentEClass, THICKNESS_CONTENT__ENDOPEN);
		createEAttribute(thicknessContentEClass, THICKNESS_CONTENT__START);
		createEAttribute(thicknessContentEClass, THICKNESS_CONTENT__STARTOPEN);
		createEAttribute(thicknessContentEClass, THICKNESS_CONTENT__VALUE);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SPELLS);

		// Create enums
		edgeShapeTypeEEnum = createEEnum(EDGE_SHAPE_TYPE);
		nodeShapeTypeEEnum = createEEnum(NODE_SHAPE_TYPE);

		// Create data types
		alphaChannelEDataType = createEDataType(ALPHA_CHANNEL);
		alphaChannelObjectEDataType = createEDataType(ALPHA_CHANNEL_OBJECT);
		colorChannelEDataType = createEDataType(COLOR_CHANNEL);
		edgeShapeTypeObjectEDataType = createEDataType(EDGE_SHAPE_TYPE_OBJECT);
		nodeShapeTypeObjectEDataType = createEDataType(NODE_SHAPE_TYPE_OBJECT);
		sizeTypeEDataType = createEDataType(SIZE_TYPE);
		sizeTypeObjectEDataType = createEDataType(SIZE_TYPE_OBJECT);
		spacePointEDataType = createEDataType(SPACE_POINT);
		spacePointObjectEDataType = createEDataType(SPACE_POINT_OBJECT);
		thicknessTypeEDataType = createEDataType(THICKNESS_TYPE);
		thicknessTypeObjectEDataType = createEDataType(THICKNESS_TYPE_OBJECT);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This
	 * method is guarded to have no affect on any invocation but its first. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) {
			return;
		}
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		GexfPackage theGexfPackage = (GexfPackage) EPackage.Registry.INSTANCE.getEPackage(GexfPackage.eNS_URI);
		XMLTypePackage theXMLTypePackage = (XMLTypePackage) EPackage.Registry.INSTANCE
				.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(colorContentEClass, ColorContent.class, "ColorContent", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getColorContent_Spells(), theGexfPackage.getSpellsContent(), null, "spells", null, 0, 1,
				ColorContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getColorContent_A(), this.getAlphaChannel(), "a", null, 0, 1, ColorContent.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getColorContent_B(), this.getColorChannel(), "b", null, 1, 1, ColorContent.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getColorContent_End(), theGexfPackage.getTimeType(), "end", null, 0, 1, ColorContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getColorContent_Endopen(), theGexfPackage.getTimeType(), "endopen", null, 0, 1,
				ColorContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getColorContent_G(), this.getColorChannel(), "g", null, 1, 1, ColorContent.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getColorContent_R(), this.getColorChannel(), "r", null, 1, 1, ColorContent.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getColorContent_Start(), theGexfPackage.getTimeType(), "start", null, 0, 1, ColorContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getColorContent_Startopen(), theGexfPackage.getTimeType(), "startopen", null, 0, 1,
				ColorContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(edgeShapeContentEClass, EdgeShapeContent.class, "EdgeShapeContent", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEdgeShapeContent_Spells(), theGexfPackage.getSpellsContent(), null, "spells", null, 0, 1,
				EdgeShapeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeShapeContent_End(), theGexfPackage.getTimeType(), "end", null, 0, 1,
				EdgeShapeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeShapeContent_Endopen(), theGexfPackage.getTimeType(), "endopen", null, 0, 1,
				EdgeShapeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeShapeContent_Start(), theGexfPackage.getTimeType(), "start", null, 0, 1,
				EdgeShapeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeShapeContent_Startopen(), theGexfPackage.getTimeType(), "startopen", null, 0, 1,
				EdgeShapeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeShapeContent_Value(), this.getEdgeShapeType(), "value", null, 1, 1,
				EdgeShapeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(nodeShapeContentEClass, NodeShapeContent.class, "NodeShapeContent", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNodeShapeContent_Spells(), theGexfPackage.getSpellsContent(), null, "spells", null, 0, 1,
				NodeShapeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeShapeContent_End(), theGexfPackage.getTimeType(), "end", null, 0, 1,
				NodeShapeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeShapeContent_Endopen(), theGexfPackage.getTimeType(), "endopen", null, 0, 1,
				NodeShapeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeShapeContent_Start(), theGexfPackage.getTimeType(), "start", null, 0, 1,
				NodeShapeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeShapeContent_Startopen(), theGexfPackage.getTimeType(), "startopen", null, 0, 1,
				NodeShapeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeShapeContent_Uri(), theXMLTypePackage.getAnyURI(), "uri", null, 0, 1,
				NodeShapeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeShapeContent_Value(), this.getNodeShapeType(), "value", null, 1, 1,
				NodeShapeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(positionContentEClass, PositionContent.class, "PositionContent", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPositionContent_Spells(), theGexfPackage.getSpellsContent(), null, "spells", null, 0, 1,
				PositionContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPositionContent_End(), theGexfPackage.getTimeType(), "end", null, 0, 1,
				PositionContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPositionContent_Endopen(), theGexfPackage.getTimeType(), "endopen", null, 0, 1,
				PositionContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPositionContent_Start(), theGexfPackage.getTimeType(), "start", null, 0, 1,
				PositionContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPositionContent_Startopen(), theGexfPackage.getTimeType(), "startopen", null, 0, 1,
				PositionContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPositionContent_X(), this.getSpacePoint(), "x", null, 1, 1, PositionContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPositionContent_Y(), this.getSpacePoint(), "y", null, 1, 1, PositionContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPositionContent_Z(), this.getSpacePoint(), "z", null, 1, 1, PositionContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sizeContentEClass, SizeContent.class, "SizeContent", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSizeContent_Spells(), theGexfPackage.getSpellsContent(), null, "spells", null, 0, 1,
				SizeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSizeContent_End(), theGexfPackage.getTimeType(), "end", null, 0, 1, SizeContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSizeContent_Endopen(), theGexfPackage.getTimeType(), "endopen", null, 0, 1,
				SizeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getSizeContent_Start(), theGexfPackage.getTimeType(), "start", null, 0, 1, SizeContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSizeContent_Startopen(), theGexfPackage.getTimeType(), "startopen", null, 0, 1,
				SizeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getSizeContent_Value(), this.getSizeType(), "value", null, 1, 1, SizeContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(thicknessContentEClass, ThicknessContent.class, "ThicknessContent", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getThicknessContent_Spells(), theGexfPackage.getSpellsContent(), null, "spells", null, 0, 1,
				ThicknessContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getThicknessContent_End(), theGexfPackage.getTimeType(), "end", null, 0, 1,
				ThicknessContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getThicknessContent_Endopen(), theGexfPackage.getTimeType(), "endopen", null, 0, 1,
				ThicknessContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getThicknessContent_Start(), theGexfPackage.getTimeType(), "start", null, 0, 1,
				ThicknessContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getThicknessContent_Startopen(), theGexfPackage.getTimeType(), "startopen", null, 0, 1,
				ThicknessContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getThicknessContent_Value(), this.getThicknessType(), "value", null, 1, 1,
				ThicknessContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null,
				"xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null,
				"xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Spells(), theGexfPackage.getSpellsContent(), null, "spells", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(edgeShapeTypeEEnum, EdgeShapeType.class, "EdgeShapeType");
		addEEnumLiteral(edgeShapeTypeEEnum, EdgeShapeType.SOLID);
		addEEnumLiteral(edgeShapeTypeEEnum, EdgeShapeType.DOTTED);
		addEEnumLiteral(edgeShapeTypeEEnum, EdgeShapeType.DASHED);
		addEEnumLiteral(edgeShapeTypeEEnum, EdgeShapeType.DOUBLE);

		initEEnum(nodeShapeTypeEEnum, NodeShapeType.class, "NodeShapeType");
		addEEnumLiteral(nodeShapeTypeEEnum, NodeShapeType.DISC);
		addEEnumLiteral(nodeShapeTypeEEnum, NodeShapeType.SQUARE);
		addEEnumLiteral(nodeShapeTypeEEnum, NodeShapeType.TRIANGLE);
		addEEnumLiteral(nodeShapeTypeEEnum, NodeShapeType.DIAMOND);
		addEEnumLiteral(nodeShapeTypeEEnum, NodeShapeType.IMAGE);

		// Initialize data types
		initEDataType(alphaChannelEDataType, float.class, "AlphaChannel", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(alphaChannelObjectEDataType, Float.class, "AlphaChannelObject", IS_SERIALIZABLE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEDataType(colorChannelEDataType, BigInteger.class, "ColorChannel", IS_SERIALIZABLE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEDataType(edgeShapeTypeObjectEDataType, EdgeShapeType.class, "EdgeShapeTypeObject", IS_SERIALIZABLE,
				IS_GENERATED_INSTANCE_CLASS);
		initEDataType(nodeShapeTypeObjectEDataType, NodeShapeType.class, "NodeShapeTypeObject", IS_SERIALIZABLE,
				IS_GENERATED_INSTANCE_CLASS);
		initEDataType(sizeTypeEDataType, float.class, "SizeType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(sizeTypeObjectEDataType, Float.class, "SizeTypeObject", IS_SERIALIZABLE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEDataType(spacePointEDataType, float.class, "SpacePoint", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(spacePointObjectEDataType, Float.class, "SpacePointObject", IS_SERIALIZABLE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEDataType(thicknessTypeEDataType, float.class, "ThicknessType", IS_SERIALIZABLE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEDataType(thicknessTypeObjectEDataType, Float.class, "ThicknessTypeObject", IS_SERIALIZABLE,
				!IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for
	 * <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
		addAnnotation(alphaChannelEDataType, source, new String[] { "name", "alpha-channel", "baseType",
				"http://www.eclipse.org/emf/2003/XMLType#float", "minInclusive", "0.0", "maxInclusive", "1.0" });
		addAnnotation(alphaChannelObjectEDataType, source, new String[] { "name", "alpha-channel:Object", "baseType",
				"alpha-channel" });
		addAnnotation(colorChannelEDataType, source, new String[] { "name", "color-channel", "baseType",
				"http://www.eclipse.org/emf/2003/XMLType#nonNegativeInteger", "maxInclusive", "255" });
		addAnnotation(colorContentEClass, source, new String[] { "name", "color-content", "kind", "elementOnly" });
		addAnnotation(getColorContent_Spells(), source, new String[] { "kind", "element", "name", "spells",
				"namespace", "##targetNamespace" });
		addAnnotation(getColorContent_A(), source, new String[] { "kind", "attribute", "name", "a" });
		addAnnotation(getColorContent_B(), source, new String[] { "kind", "attribute", "name", "b" });
		addAnnotation(getColorContent_End(), source, new String[] { "kind", "attribute", "name", "end" });
		addAnnotation(getColorContent_Endopen(), source, new String[] { "kind", "attribute", "name", "endopen" });
		addAnnotation(getColorContent_G(), source, new String[] { "kind", "attribute", "name", "g" });
		addAnnotation(getColorContent_R(), source, new String[] { "kind", "attribute", "name", "r" });
		addAnnotation(getColorContent_Start(), source, new String[] { "kind", "attribute", "name", "start" });
		addAnnotation(getColorContent_Startopen(), source, new String[] { "kind", "attribute", "name", "startopen" });
		addAnnotation(edgeShapeContentEClass, source, new String[] { "name", "edge-shape-content", "kind",
				"elementOnly" });
		addAnnotation(getEdgeShapeContent_Spells(), source, new String[] { "kind", "element", "name", "spells",
				"namespace", "##targetNamespace" });
		addAnnotation(getEdgeShapeContent_End(), source, new String[] { "kind", "attribute", "name", "end" });
		addAnnotation(getEdgeShapeContent_Endopen(), source, new String[] { "kind", "attribute", "name", "endopen" });
		addAnnotation(getEdgeShapeContent_Start(), source, new String[] { "kind", "attribute", "name", "start" });
		addAnnotation(getEdgeShapeContent_Startopen(), source,
				new String[] { "kind", "attribute", "name", "startopen" });
		addAnnotation(getEdgeShapeContent_Value(), source, new String[] { "kind", "attribute", "name", "value" });
		addAnnotation(edgeShapeTypeEEnum, source, new String[] { "name", "edge-shape-type" });
		addAnnotation(edgeShapeTypeObjectEDataType, source, new String[] { "name", "edge-shape-type:Object",
				"baseType", "edge-shape-type" });
		addAnnotation(nodeShapeContentEClass, source, new String[] { "name", "node-shape-content", "kind",
				"elementOnly" });
		addAnnotation(getNodeShapeContent_Spells(), source, new String[] { "kind", "element", "name", "spells",
				"namespace", "##targetNamespace" });
		addAnnotation(getNodeShapeContent_End(), source, new String[] { "kind", "attribute", "name", "end" });
		addAnnotation(getNodeShapeContent_Endopen(), source, new String[] { "kind", "attribute", "name", "endopen" });
		addAnnotation(getNodeShapeContent_Start(), source, new String[] { "kind", "attribute", "name", "start" });
		addAnnotation(getNodeShapeContent_Startopen(), source,
				new String[] { "kind", "attribute", "name", "startopen" });
		addAnnotation(getNodeShapeContent_Uri(), source, new String[] { "kind", "attribute", "name", "uri" });
		addAnnotation(getNodeShapeContent_Value(), source, new String[] { "kind", "attribute", "name", "value" });
		addAnnotation(nodeShapeTypeEEnum, source, new String[] { "name", "node-shape-type" });
		addAnnotation(nodeShapeTypeObjectEDataType, source, new String[] { "name", "node-shape-type:Object",
				"baseType", "node-shape-type" });
		addAnnotation(positionContentEClass, source, new String[] { "name", "position-content", "kind", "elementOnly" });
		addAnnotation(getPositionContent_Spells(), source, new String[] { "kind", "element", "name", "spells",
				"namespace", "##targetNamespace" });
		addAnnotation(getPositionContent_End(), source, new String[] { "kind", "attribute", "name", "end" });
		addAnnotation(getPositionContent_Endopen(), source, new String[] { "kind", "attribute", "name", "endopen" });
		addAnnotation(getPositionContent_Start(), source, new String[] { "kind", "attribute", "name", "start" });
		addAnnotation(getPositionContent_Startopen(), source, new String[] { "kind", "attribute", "name", "startopen" });
		addAnnotation(getPositionContent_X(), source, new String[] { "kind", "attribute", "name", "x" });
		addAnnotation(getPositionContent_Y(), source, new String[] { "kind", "attribute", "name", "y" });
		addAnnotation(getPositionContent_Z(), source, new String[] { "kind", "attribute", "name", "z" });
		addAnnotation(sizeContentEClass, source, new String[] { "name", "size-content", "kind", "elementOnly" });
		addAnnotation(getSizeContent_Spells(), source, new String[] { "kind", "element", "name", "spells", "namespace",
				"##targetNamespace" });
		addAnnotation(getSizeContent_End(), source, new String[] { "kind", "attribute", "name", "end" });
		addAnnotation(getSizeContent_Endopen(), source, new String[] { "kind", "attribute", "name", "endopen" });
		addAnnotation(getSizeContent_Start(), source, new String[] { "kind", "attribute", "name", "start" });
		addAnnotation(getSizeContent_Startopen(), source, new String[] { "kind", "attribute", "name", "startopen" });
		addAnnotation(getSizeContent_Value(), source, new String[] { "kind", "attribute", "name", "value" });
		addAnnotation(sizeTypeEDataType, source, new String[] { "name", "size-type", "baseType",
				"http://www.eclipse.org/emf/2003/XMLType#float", "minInclusive", "0.0" });
		addAnnotation(sizeTypeObjectEDataType, source, new String[] { "name", "size-type:Object", "baseType",
				"size-type" });
		addAnnotation(spacePointEDataType, source, new String[] { "name", "space-point", "baseType",
				"http://www.eclipse.org/emf/2003/XMLType#float" });
		addAnnotation(spacePointObjectEDataType, source, new String[] { "name", "space-point:Object", "baseType",
				"space-point" });
		addAnnotation(thicknessContentEClass, source,
				new String[] { "name", "thickness-content", "kind", "elementOnly" });
		addAnnotation(getThicknessContent_Spells(), source, new String[] { "kind", "element", "name", "spells",
				"namespace", "##targetNamespace" });
		addAnnotation(getThicknessContent_End(), source, new String[] { "kind", "attribute", "name", "end" });
		addAnnotation(getThicknessContent_Endopen(), source, new String[] { "kind", "attribute", "name", "endopen" });
		addAnnotation(getThicknessContent_Start(), source, new String[] { "kind", "attribute", "name", "start" });
		addAnnotation(getThicknessContent_Startopen(), source,
				new String[] { "kind", "attribute", "name", "startopen" });
		addAnnotation(getThicknessContent_Value(), source, new String[] { "kind", "attribute", "name", "value" });
		addAnnotation(thicknessTypeEDataType, source, new String[] { "name", "thickness-type", "baseType",
				"http://www.eclipse.org/emf/2003/XMLType#float", "minInclusive", "0.0" });
		addAnnotation(thicknessTypeObjectEDataType, source, new String[] { "name", "thickness-type:Object", "baseType",
				"thickness-type" });
		addAnnotation(documentRootEClass, source, new String[] { "name", "", "kind", "mixed" });
		addAnnotation(getDocumentRoot_Mixed(), source, new String[] { "kind", "elementWildcard", "name", ":mixed" });
		addAnnotation(getDocumentRoot_XMLNSPrefixMap(), source, new String[] { "kind", "attribute", "name",
				"xmlns:prefix" });
		addAnnotation(getDocumentRoot_XSISchemaLocation(), source, new String[] { "kind", "attribute", "name",
				"xsi:schemaLocation" });
		addAnnotation(getDocumentRoot_Spells(), source, new String[] { "kind", "element", "name", "spells",
				"namespace", "##targetNamespace" });
	}

} //VizPackageImpl
