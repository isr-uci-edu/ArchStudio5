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
import net.gexf_1_2.gexf.util.GexfValidator;
import net.gexf_1_2.viz.VizPackage;
import net.gexf_1_2.viz.impl.VizPackageImpl;

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
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class GexfPackageImpl extends EPackageImpl implements GexfPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass attributeContentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass attributesContentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass attvaluesContentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass attvalueTypeEClass = null;

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
	private EClass edgeContentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass edgesContentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass gexfContentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass graphContentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass metaContentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass nodeContentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass nodesContentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass parentsContentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass parentTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass spellsContentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass spellTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum attrtypeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum classTypeEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum defaultedgetypeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum edgetypeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum idtypeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum modeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum timeformatTypeEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum versionTypeEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType attrtypeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType classTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType defaultedgetypeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType edgetypeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType idTypeEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType idtypeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType modeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType timeformatTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType timeTypeEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType versionTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType weightTypeEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType weightTypeObjectEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
	 * EPackage.Registry} by the package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
	 * performs initialization of the package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see net.gexf_1_2.gexf.GexfPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private GexfPackageImpl() {
		super(eNS_URI, GexfFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link GexfPackage#eINSTANCE} when that field is accessed. Clients should not
	 * invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static GexfPackage init() {
		if (isInited) {
			return (GexfPackage) EPackage.Registry.INSTANCE.getEPackage(GexfPackage.eNS_URI);
		}

		// Obtain or create and register package
		GexfPackageImpl theGexfPackage = (GexfPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof GexfPackageImpl ? EPackage.Registry.INSTANCE
				.get(eNS_URI) : new GexfPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		VizPackageImpl theVizPackage = (VizPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(VizPackage.eNS_URI) instanceof VizPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(VizPackage.eNS_URI) : VizPackage.eINSTANCE);

		// Create package meta-data objects
		theGexfPackage.createPackageContents();
		theVizPackage.createPackageContents();

		// Initialize created meta-data
		theGexfPackage.initializePackageContents();
		theVizPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put(theGexfPackage, new EValidator.Descriptor() {
			@Override
			public EValidator getEValidator() {
				return GexfValidator.INSTANCE;
			}
		});

		// Mark meta-data to indicate it can't be changed
		theGexfPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(GexfPackage.eNS_URI, theGexfPackage);
		return theGexfPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getAttributeContent() {
		return attributeContentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getAttributeContent_Group() {
		return (EAttribute) attributeContentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getAttributeContent_Default() {
		return (EAttribute) attributeContentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getAttributeContent_Options() {
		return (EAttribute) attributeContentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getAttributeContent_Id() {
		return (EAttribute) attributeContentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getAttributeContent_Title() {
		return (EAttribute) attributeContentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getAttributeContent_Type() {
		return (EAttribute) attributeContentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getAttributesContent() {
		return attributesContentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getAttributesContent_Attribute() {
		return (EReference) attributesContentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getAttributesContent_Class() {
		return (EAttribute) attributesContentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getAttributesContent_End() {
		return (EAttribute) attributesContentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getAttributesContent_Endopen() {
		return (EAttribute) attributesContentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getAttributesContent_Mode() {
		return (EAttribute) attributesContentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getAttributesContent_Start() {
		return (EAttribute) attributesContentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getAttributesContent_Startopen() {
		return (EAttribute) attributesContentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getAttvaluesContent() {
		return attvaluesContentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getAttvaluesContent_Attvalue() {
		return (EReference) attvaluesContentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getAttvalueType() {
		return attvalueTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getAttvalueType_End() {
		return (EAttribute) attvalueTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getAttvalueType_Endopen() {
		return (EAttribute) attvalueTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getAttvalueType_For() {
		return (EAttribute) attvalueTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getAttvalueType_Start() {
		return (EAttribute) attvalueTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getAttvalueType_Startopen() {
		return (EAttribute) attvalueTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getAttvalueType_Value() {
		return (EAttribute) attvalueTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getDocumentRoot() {
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute) documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Attribute() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Attributes() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Attvalue() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Attvalues() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Color() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getDocumentRoot_Creator() {
		return (EAttribute) documentRootEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getDocumentRoot_Default() {
		return (EAttribute) documentRootEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getDocumentRoot_Description() {
		return (EAttribute) documentRootEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Edge() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Edges() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Gexf() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Graph() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getDocumentRoot_Keywords() {
		return (EAttribute) documentRootEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Meta() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Node() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Nodes() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getDocumentRoot_Options() {
		return (EAttribute) documentRootEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Parent() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Parents() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Position() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Size() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Spell() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Spells() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Thickness() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getEdgeContent() {
		return edgeContentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEdgeContent_Group() {
		return (EAttribute) edgeContentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEdgeContent_Attvalues() {
		return (EReference) edgeContentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEdgeContent_Spells() {
		return (EReference) edgeContentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEdgeContent_Color() {
		return (EReference) edgeContentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEdgeContent_Thickness() {
		return (EReference) edgeContentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEdgeContent_Shape() {
		return (EReference) edgeContentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEdgeContent_End() {
		return (EAttribute) edgeContentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEdgeContent_Endopen() {
		return (EAttribute) edgeContentEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEdgeContent_Id() {
		return (EAttribute) edgeContentEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEdgeContent_Label() {
		return (EAttribute) edgeContentEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEdgeContent_Source() {
		return (EAttribute) edgeContentEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEdgeContent_Start() {
		return (EAttribute) edgeContentEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEdgeContent_Startopen() {
		return (EAttribute) edgeContentEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEdgeContent_Target() {
		return (EAttribute) edgeContentEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEdgeContent_Type() {
		return (EAttribute) edgeContentEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEdgeContent_Weight() {
		return (EAttribute) edgeContentEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getEdgesContent() {
		return edgesContentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getEdgesContent_Edge() {
		return (EReference) edgesContentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getEdgesContent_Count() {
		return (EAttribute) edgesContentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getGexfContent() {
		return gexfContentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getGexfContent_Meta() {
		return (EReference) gexfContentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getGexfContent_Graph() {
		return (EReference) gexfContentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getGexfContent_Variant() {
		return (EAttribute) gexfContentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getGexfContent_Version() {
		return (EAttribute) gexfContentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getGraphContent() {
		return graphContentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getGraphContent_Group() {
		return (EAttribute) graphContentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getGraphContent_Attributes() {
		return (EReference) graphContentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getGraphContent_Nodes() {
		return (EReference) graphContentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getGraphContent_Edges() {
		return (EReference) graphContentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getGraphContent_Defaultedgetype() {
		return (EAttribute) graphContentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getGraphContent_End() {
		return (EAttribute) graphContentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getGraphContent_Endopen() {
		return (EAttribute) graphContentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getGraphContent_Idtype() {
		return (EAttribute) graphContentEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getGraphContent_Mode() {
		return (EAttribute) graphContentEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getGraphContent_Start() {
		return (EAttribute) graphContentEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getGraphContent_Startopen() {
		return (EAttribute) graphContentEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getGraphContent_Timeformat() {
		return (EAttribute) graphContentEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getMetaContent() {
		return metaContentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMetaContent_Group() {
		return (EAttribute) metaContentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMetaContent_Creator() {
		return (EAttribute) metaContentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMetaContent_Keywords() {
		return (EAttribute) metaContentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMetaContent_Description() {
		return (EAttribute) metaContentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMetaContent_Lastmodifieddate() {
		return (EAttribute) metaContentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getNodeContent() {
		return nodeContentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getNodeContent_Group() {
		return (EAttribute) nodeContentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getNodeContent_Attvalues() {
		return (EReference) nodeContentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getNodeContent_Spells() {
		return (EReference) nodeContentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getNodeContent_Nodes() {
		return (EReference) nodeContentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getNodeContent_Edges() {
		return (EReference) nodeContentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getNodeContent_Parents() {
		return (EReference) nodeContentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getNodeContent_Color() {
		return (EReference) nodeContentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getNodeContent_Position() {
		return (EReference) nodeContentEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getNodeContent_Size() {
		return (EReference) nodeContentEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getNodeContent_Shape() {
		return (EReference) nodeContentEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getNodeContent_End() {
		return (EAttribute) nodeContentEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getNodeContent_Endopen() {
		return (EAttribute) nodeContentEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getNodeContent_Id() {
		return (EAttribute) nodeContentEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getNodeContent_Label() {
		return (EAttribute) nodeContentEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getNodeContent_Pid() {
		return (EAttribute) nodeContentEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getNodeContent_Start() {
		return (EAttribute) nodeContentEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getNodeContent_Startopen() {
		return (EAttribute) nodeContentEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getNodesContent() {
		return nodesContentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getNodesContent_Node() {
		return (EReference) nodesContentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getNodesContent_Count() {
		return (EAttribute) nodesContentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getParentsContent() {
		return parentsContentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getParentsContent_Parent() {
		return (EReference) parentsContentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getParentType() {
		return parentTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getParentType_For() {
		return (EAttribute) parentTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getSpellsContent() {
		return spellsContentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getSpellsContent_Spell() {
		return (EReference) spellsContentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getSpellType() {
		return spellTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getSpellType_End() {
		return (EAttribute) spellTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getSpellType_Endopen() {
		return (EAttribute) spellTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getSpellType_Start() {
		return (EAttribute) spellTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getSpellType_Startopen() {
		return (EAttribute) spellTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EEnum getAttrtypeType() {
		return attrtypeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EEnum getClassType() {
		return classTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EEnum getDefaultedgetypeType() {
		return defaultedgetypeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EEnum getEdgetypeType() {
		return edgetypeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EEnum getIdtypeType() {
		return idtypeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EEnum getModeType() {
		return modeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EEnum getTimeformatType() {
		return timeformatTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EEnum getVersionType() {
		return versionTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EDataType getAttrtypeTypeObject() {
		return attrtypeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EDataType getClassTypeObject() {
		return classTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EDataType getDefaultedgetypeTypeObject() {
		return defaultedgetypeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EDataType getEdgetypeTypeObject() {
		return edgetypeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EDataType getIdType() {
		return idTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EDataType getIdtypeTypeObject() {
		return idtypeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EDataType getModeTypeObject() {
		return modeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EDataType getTimeformatTypeObject() {
		return timeformatTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EDataType getTimeType() {
		return timeTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EDataType getVersionTypeObject() {
		return versionTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EDataType getWeightType() {
		return weightTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EDataType getWeightTypeObject() {
		return weightTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public GexfFactory getGexfFactory() {
		return (GexfFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is guarded to have no affect on any invocation but
	 * its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) {
			return;
		}
		isCreated = true;

		// Create classes and their features
		attributeContentEClass = createEClass(ATTRIBUTE_CONTENT);
		createEAttribute(attributeContentEClass, ATTRIBUTE_CONTENT__GROUP);
		createEAttribute(attributeContentEClass, ATTRIBUTE_CONTENT__DEFAULT);
		createEAttribute(attributeContentEClass, ATTRIBUTE_CONTENT__OPTIONS);
		createEAttribute(attributeContentEClass, ATTRIBUTE_CONTENT__ID);
		createEAttribute(attributeContentEClass, ATTRIBUTE_CONTENT__TITLE);
		createEAttribute(attributeContentEClass, ATTRIBUTE_CONTENT__TYPE);

		attributesContentEClass = createEClass(ATTRIBUTES_CONTENT);
		createEReference(attributesContentEClass, ATTRIBUTES_CONTENT__ATTRIBUTE);
		createEAttribute(attributesContentEClass, ATTRIBUTES_CONTENT__CLASS);
		createEAttribute(attributesContentEClass, ATTRIBUTES_CONTENT__END);
		createEAttribute(attributesContentEClass, ATTRIBUTES_CONTENT__ENDOPEN);
		createEAttribute(attributesContentEClass, ATTRIBUTES_CONTENT__MODE);
		createEAttribute(attributesContentEClass, ATTRIBUTES_CONTENT__START);
		createEAttribute(attributesContentEClass, ATTRIBUTES_CONTENT__STARTOPEN);

		attvaluesContentEClass = createEClass(ATTVALUES_CONTENT);
		createEReference(attvaluesContentEClass, ATTVALUES_CONTENT__ATTVALUE);

		attvalueTypeEClass = createEClass(ATTVALUE_TYPE);
		createEAttribute(attvalueTypeEClass, ATTVALUE_TYPE__END);
		createEAttribute(attvalueTypeEClass, ATTVALUE_TYPE__ENDOPEN);
		createEAttribute(attvalueTypeEClass, ATTVALUE_TYPE__FOR);
		createEAttribute(attvalueTypeEClass, ATTVALUE_TYPE__START);
		createEAttribute(attvalueTypeEClass, ATTVALUE_TYPE__STARTOPEN);
		createEAttribute(attvalueTypeEClass, ATTVALUE_TYPE__VALUE);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ATTRIBUTE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ATTRIBUTES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ATTVALUE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ATTVALUES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__COLOR);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__CREATOR);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__DEFAULT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__DESCRIPTION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__EDGE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__EDGES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__GEXF);
		createEReference(documentRootEClass, DOCUMENT_ROOT__GRAPH);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__KEYWORDS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__META);
		createEReference(documentRootEClass, DOCUMENT_ROOT__NODE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__NODES);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__OPTIONS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PARENT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PARENTS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__POSITION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SIZE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SPELL);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SPELLS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__THICKNESS);

		edgeContentEClass = createEClass(EDGE_CONTENT);
		createEAttribute(edgeContentEClass, EDGE_CONTENT__GROUP);
		createEReference(edgeContentEClass, EDGE_CONTENT__ATTVALUES);
		createEReference(edgeContentEClass, EDGE_CONTENT__SPELLS);
		createEReference(edgeContentEClass, EDGE_CONTENT__COLOR);
		createEReference(edgeContentEClass, EDGE_CONTENT__THICKNESS);
		createEReference(edgeContentEClass, EDGE_CONTENT__SHAPE);
		createEAttribute(edgeContentEClass, EDGE_CONTENT__END);
		createEAttribute(edgeContentEClass, EDGE_CONTENT__ENDOPEN);
		createEAttribute(edgeContentEClass, EDGE_CONTENT__ID);
		createEAttribute(edgeContentEClass, EDGE_CONTENT__LABEL);
		createEAttribute(edgeContentEClass, EDGE_CONTENT__SOURCE);
		createEAttribute(edgeContentEClass, EDGE_CONTENT__START);
		createEAttribute(edgeContentEClass, EDGE_CONTENT__STARTOPEN);
		createEAttribute(edgeContentEClass, EDGE_CONTENT__TARGET);
		createEAttribute(edgeContentEClass, EDGE_CONTENT__TYPE);
		createEAttribute(edgeContentEClass, EDGE_CONTENT__WEIGHT);

		edgesContentEClass = createEClass(EDGES_CONTENT);
		createEReference(edgesContentEClass, EDGES_CONTENT__EDGE);
		createEAttribute(edgesContentEClass, EDGES_CONTENT__COUNT);

		gexfContentEClass = createEClass(GEXF_CONTENT);
		createEReference(gexfContentEClass, GEXF_CONTENT__META);
		createEReference(gexfContentEClass, GEXF_CONTENT__GRAPH);
		createEAttribute(gexfContentEClass, GEXF_CONTENT__VARIANT);
		createEAttribute(gexfContentEClass, GEXF_CONTENT__VERSION);

		graphContentEClass = createEClass(GRAPH_CONTENT);
		createEAttribute(graphContentEClass, GRAPH_CONTENT__GROUP);
		createEReference(graphContentEClass, GRAPH_CONTENT__ATTRIBUTES);
		createEReference(graphContentEClass, GRAPH_CONTENT__NODES);
		createEReference(graphContentEClass, GRAPH_CONTENT__EDGES);
		createEAttribute(graphContentEClass, GRAPH_CONTENT__DEFAULTEDGETYPE);
		createEAttribute(graphContentEClass, GRAPH_CONTENT__END);
		createEAttribute(graphContentEClass, GRAPH_CONTENT__ENDOPEN);
		createEAttribute(graphContentEClass, GRAPH_CONTENT__IDTYPE);
		createEAttribute(graphContentEClass, GRAPH_CONTENT__MODE);
		createEAttribute(graphContentEClass, GRAPH_CONTENT__START);
		createEAttribute(graphContentEClass, GRAPH_CONTENT__STARTOPEN);
		createEAttribute(graphContentEClass, GRAPH_CONTENT__TIMEFORMAT);

		metaContentEClass = createEClass(META_CONTENT);
		createEAttribute(metaContentEClass, META_CONTENT__GROUP);
		createEAttribute(metaContentEClass, META_CONTENT__CREATOR);
		createEAttribute(metaContentEClass, META_CONTENT__KEYWORDS);
		createEAttribute(metaContentEClass, META_CONTENT__DESCRIPTION);
		createEAttribute(metaContentEClass, META_CONTENT__LASTMODIFIEDDATE);

		nodeContentEClass = createEClass(NODE_CONTENT);
		createEAttribute(nodeContentEClass, NODE_CONTENT__GROUP);
		createEReference(nodeContentEClass, NODE_CONTENT__ATTVALUES);
		createEReference(nodeContentEClass, NODE_CONTENT__SPELLS);
		createEReference(nodeContentEClass, NODE_CONTENT__NODES);
		createEReference(nodeContentEClass, NODE_CONTENT__EDGES);
		createEReference(nodeContentEClass, NODE_CONTENT__PARENTS);
		createEReference(nodeContentEClass, NODE_CONTENT__COLOR);
		createEReference(nodeContentEClass, NODE_CONTENT__POSITION);
		createEReference(nodeContentEClass, NODE_CONTENT__SIZE);
		createEReference(nodeContentEClass, NODE_CONTENT__SHAPE);
		createEAttribute(nodeContentEClass, NODE_CONTENT__END);
		createEAttribute(nodeContentEClass, NODE_CONTENT__ENDOPEN);
		createEAttribute(nodeContentEClass, NODE_CONTENT__ID);
		createEAttribute(nodeContentEClass, NODE_CONTENT__LABEL);
		createEAttribute(nodeContentEClass, NODE_CONTENT__PID);
		createEAttribute(nodeContentEClass, NODE_CONTENT__START);
		createEAttribute(nodeContentEClass, NODE_CONTENT__STARTOPEN);

		nodesContentEClass = createEClass(NODES_CONTENT);
		createEReference(nodesContentEClass, NODES_CONTENT__NODE);
		createEAttribute(nodesContentEClass, NODES_CONTENT__COUNT);

		parentsContentEClass = createEClass(PARENTS_CONTENT);
		createEReference(parentsContentEClass, PARENTS_CONTENT__PARENT);

		parentTypeEClass = createEClass(PARENT_TYPE);
		createEAttribute(parentTypeEClass, PARENT_TYPE__FOR);

		spellsContentEClass = createEClass(SPELLS_CONTENT);
		createEReference(spellsContentEClass, SPELLS_CONTENT__SPELL);

		spellTypeEClass = createEClass(SPELL_TYPE);
		createEAttribute(spellTypeEClass, SPELL_TYPE__END);
		createEAttribute(spellTypeEClass, SPELL_TYPE__ENDOPEN);
		createEAttribute(spellTypeEClass, SPELL_TYPE__START);
		createEAttribute(spellTypeEClass, SPELL_TYPE__STARTOPEN);

		// Create enums
		attrtypeTypeEEnum = createEEnum(ATTRTYPE_TYPE);
		classTypeEEnum = createEEnum(CLASS_TYPE);
		defaultedgetypeTypeEEnum = createEEnum(DEFAULTEDGETYPE_TYPE);
		edgetypeTypeEEnum = createEEnum(EDGETYPE_TYPE);
		idtypeTypeEEnum = createEEnum(IDTYPE_TYPE);
		modeTypeEEnum = createEEnum(MODE_TYPE);
		timeformatTypeEEnum = createEEnum(TIMEFORMAT_TYPE);
		versionTypeEEnum = createEEnum(VERSION_TYPE);

		// Create data types
		attrtypeTypeObjectEDataType = createEDataType(ATTRTYPE_TYPE_OBJECT);
		classTypeObjectEDataType = createEDataType(CLASS_TYPE_OBJECT);
		defaultedgetypeTypeObjectEDataType = createEDataType(DEFAULTEDGETYPE_TYPE_OBJECT);
		edgetypeTypeObjectEDataType = createEDataType(EDGETYPE_TYPE_OBJECT);
		idTypeEDataType = createEDataType(ID_TYPE);
		idtypeTypeObjectEDataType = createEDataType(IDTYPE_TYPE_OBJECT);
		modeTypeObjectEDataType = createEDataType(MODE_TYPE_OBJECT);
		timeformatTypeObjectEDataType = createEDataType(TIMEFORMAT_TYPE_OBJECT);
		timeTypeEDataType = createEDataType(TIME_TYPE);
		versionTypeObjectEDataType = createEDataType(VERSION_TYPE_OBJECT);
		weightTypeEDataType = createEDataType(WEIGHT_TYPE);
		weightTypeObjectEDataType = createEDataType(WEIGHT_TYPE_OBJECT);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any
	 * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
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
		XMLTypePackage theXMLTypePackage = (XMLTypePackage) EPackage.Registry.INSTANCE
				.getEPackage(XMLTypePackage.eNS_URI);
		VizPackage theVizPackage = (VizPackage) EPackage.Registry.INSTANCE.getEPackage(VizPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(attributeContentEClass, AttributeContent.class, "AttributeContent", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAttributeContent_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1,
				AttributeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttributeContent_Default(), theXMLTypePackage.getString(), "default", null, 0, -1,
				AttributeContent.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttributeContent_Options(), theXMLTypePackage.getString(), "options", null, 0, -1,
				AttributeContent.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttributeContent_Id(), this.getIdType(), "id", null, 1, 1, AttributeContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttributeContent_Title(), theXMLTypePackage.getString(), "title", null, 1, 1,
				AttributeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttributeContent_Type(), this.getAttrtypeType(), "type", null, 1, 1, AttributeContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributesContentEClass, AttributesContent.class, "AttributesContent", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAttributesContent_Attribute(), this.getAttributeContent(), null, "attribute", null, 0, -1,
				AttributesContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttributesContent_Class(), this.getClassType(), "class", null, 1, 1, AttributesContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttributesContent_End(), this.getTimeType(), "end", null, 0, 1, AttributesContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttributesContent_Endopen(), this.getTimeType(), "endopen", null, 0, 1,
				AttributesContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttributesContent_Mode(), this.getModeType(), "mode", null, 0, 1, AttributesContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttributesContent_Start(), this.getTimeType(), "start", null, 0, 1, AttributesContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttributesContent_Startopen(), this.getTimeType(), "startopen", null, 0, 1,
				AttributesContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(attvaluesContentEClass, AttvaluesContent.class, "AttvaluesContent", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAttvaluesContent_Attvalue(), this.getAttvalueType(), null, "attvalue", null, 0, -1,
				AttvaluesContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attvalueTypeEClass, AttvalueType.class, "AttvalueType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAttvalueType_End(), this.getTimeType(), "end", null, 0, 1, AttvalueType.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttvalueType_Endopen(), this.getTimeType(), "endopen", null, 0, 1, AttvalueType.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttvalueType_For(), this.getIdType(), "for", null, 1, 1, AttvalueType.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttvalueType_Start(), this.getTimeType(), "start", null, 0, 1, AttvalueType.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttvalueType_Startopen(), this.getTimeType(), "startopen", null, 0, 1, AttvalueType.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttvalueType_Value(), theXMLTypePackage.getString(), "value", null, 1, 1, AttvalueType.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		initEReference(getDocumentRoot_Attribute(), this.getAttributeContent(), null, "attribute", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Attributes(), this.getAttributesContent(), null, "attributes", null, 0, -2,
				null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Attvalue(), this.getAttvalueType(), null, "attvalue", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Attvalues(), this.getAttvaluesContent(), null, "attvalues", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Color(), theVizPackage.getColorContent(), null, "color", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Creator(), theXMLTypePackage.getString(), "creator", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Default(), theXMLTypePackage.getString(), "default", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Description(), theXMLTypePackage.getString(), "description", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Edge(), this.getEdgeContent(), null, "edge", null, 0, -2, null, IS_TRANSIENT,
				IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);
		initEReference(getDocumentRoot_Edges(), this.getEdgesContent(), null, "edges", null, 0, -2, null, IS_TRANSIENT,
				IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);
		initEReference(getDocumentRoot_Gexf(), this.getGexfContent(), null, "gexf", null, 0, -2, null, IS_TRANSIENT,
				IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);
		initEReference(getDocumentRoot_Graph(), this.getGraphContent(), null, "graph", null, 0, -2, null, IS_TRANSIENT,
				IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getDocumentRoot_Keywords(), theXMLTypePackage.getString(), "keywords", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Meta(), this.getMetaContent(), null, "meta", null, 0, -2, null, IS_TRANSIENT,
				IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);
		initEReference(getDocumentRoot_Node(), this.getNodeContent(), null, "node", null, 0, -2, null, IS_TRANSIENT,
				IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);
		initEReference(getDocumentRoot_Nodes(), this.getNodesContent(), null, "nodes", null, 0, -2, null, IS_TRANSIENT,
				IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getDocumentRoot_Options(), theXMLTypePackage.getString(), "options", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Parent(), this.getParentType(), null, "parent", null, 0, -2, null, IS_TRANSIENT,
				IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);
		initEReference(getDocumentRoot_Parents(), this.getParentsContent(), null, "parents", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Position(), theVizPackage.getPositionContent(), null, "position", null, 0, -2,
				null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Size(), theVizPackage.getSizeContent(), null, "size", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Spell(), this.getSpellType(), null, "spell", null, 0, -2, null, IS_TRANSIENT,
				IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);
		initEReference(getDocumentRoot_Spells(), this.getSpellsContent(), null, "spells", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Thickness(), theVizPackage.getThicknessContent(), null, "thickness", null, 0,
				-2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(edgeContentEClass, EdgeContent.class, "EdgeContent", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEdgeContent_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1,
				EdgeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getEdgeContent_Attvalues(), this.getAttvaluesContent(), null, "attvalues", null, 0, -1,
				EdgeContent.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getEdgeContent_Spells(), this.getSpellsContent(), null, "spells", null, 0, -1,
				EdgeContent.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getEdgeContent_Color(), theVizPackage.getColorContent(), null, "color", null, 0, -1,
				EdgeContent.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getEdgeContent_Thickness(), theVizPackage.getThicknessContent(), null, "thickness", null, 0, -1,
				EdgeContent.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getEdgeContent_Shape(), theVizPackage.getEdgeShapeContent(), null, "shape", null, 0, -1,
				EdgeContent.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeContent_End(), this.getTimeType(), "end", null, 0, 1, EdgeContent.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeContent_Endopen(), this.getTimeType(), "endopen", null, 0, 1, EdgeContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeContent_Id(), this.getIdType(), "id", null, 1, 1, EdgeContent.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeContent_Label(), theXMLTypePackage.getToken(), "label", null, 0, 1, EdgeContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeContent_Source(), this.getIdType(), "source", null, 1, 1, EdgeContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeContent_Start(), this.getTimeType(), "start", null, 0, 1, EdgeContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeContent_Startopen(), this.getTimeType(), "startopen", null, 0, 1, EdgeContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeContent_Target(), this.getIdType(), "target", null, 1, 1, EdgeContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeContent_Type(), this.getEdgetypeType(), "type", null, 0, 1, EdgeContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeContent_Weight(), this.getWeightType(), "weight", null, 0, 1, EdgeContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(edgesContentEClass, EdgesContent.class, "EdgesContent", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEdgesContent_Edge(), this.getEdgeContent(), null, "edge", null, 0, -1, EdgesContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgesContent_Count(), theXMLTypePackage.getNonNegativeInteger(), "count", null, 0, 1,
				EdgesContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(gexfContentEClass, GexfContent.class, "GexfContent", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGexfContent_Meta(), this.getMetaContent(), null, "meta", null, 0, 1, GexfContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGexfContent_Graph(), this.getGraphContent(), null, "graph", null, 1, 1, GexfContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGexfContent_Variant(), theXMLTypePackage.getString(), "variant", null, 0, 1,
				GexfContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGexfContent_Version(), this.getVersionType(), "version", null, 1, 1, GexfContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(graphContentEClass, GraphContent.class, "GraphContent", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGraphContent_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1,
				GraphContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getGraphContent_Attributes(), this.getAttributesContent(), null, "attributes", null, 0, -1,
				GraphContent.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getGraphContent_Nodes(), this.getNodesContent(), null, "nodes", null, 0, -1, GraphContent.class,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getGraphContent_Edges(), this.getEdgesContent(), null, "edges", null, 0, -1, GraphContent.class,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphContent_Defaultedgetype(), this.getDefaultedgetypeType(), "defaultedgetype", null, 0, 1,
				GraphContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphContent_End(), this.getTimeType(), "end", null, 0, 1, GraphContent.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphContent_Endopen(), this.getTimeType(), "endopen", null, 0, 1, GraphContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphContent_Idtype(), this.getIdtypeType(), "idtype", null, 0, 1, GraphContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphContent_Mode(), this.getModeType(), "mode", null, 0, 1, GraphContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphContent_Start(), this.getTimeType(), "start", null, 0, 1, GraphContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphContent_Startopen(), this.getTimeType(), "startopen", null, 0, 1, GraphContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphContent_Timeformat(), this.getTimeformatType(), "timeformat", null, 0, 1,
				GraphContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(metaContentEClass, MetaContent.class, "MetaContent", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMetaContent_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1,
				MetaContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaContent_Creator(), theXMLTypePackage.getString(), "creator", null, 0, -1,
				MetaContent.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaContent_Keywords(), theXMLTypePackage.getString(), "keywords", null, 0, -1,
				MetaContent.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaContent_Description(), theXMLTypePackage.getString(), "description", null, 0, -1,
				MetaContent.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetaContent_Lastmodifieddate(), theXMLTypePackage.getDate(), "lastmodifieddate", null, 0, 1,
				MetaContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(nodeContentEClass, NodeContent.class, "NodeContent", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNodeContent_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1,
				NodeContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getNodeContent_Attvalues(), this.getAttvaluesContent(), null, "attvalues", null, 0, -1,
				NodeContent.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodeContent_Spells(), this.getSpellsContent(), null, "spells", null, 0, -1,
				NodeContent.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodeContent_Nodes(), this.getNodesContent(), null, "nodes", null, 0, -1, NodeContent.class,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getNodeContent_Edges(), this.getEdgesContent(), null, "edges", null, 0, -1, NodeContent.class,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getNodeContent_Parents(), this.getParentsContent(), null, "parents", null, 0, -1,
				NodeContent.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodeContent_Color(), theVizPackage.getColorContent(), null, "color", null, 0, -1,
				NodeContent.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodeContent_Position(), theVizPackage.getPositionContent(), null, "position", null, 0, -1,
				NodeContent.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodeContent_Size(), theVizPackage.getSizeContent(), null, "size", null, 0, -1,
				NodeContent.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodeContent_Shape(), theVizPackage.getNodeShapeContent(), null, "shape", null, 0, -1,
				NodeContent.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeContent_End(), this.getTimeType(), "end", null, 0, 1, NodeContent.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeContent_Endopen(), this.getTimeType(), "endopen", null, 0, 1, NodeContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeContent_Id(), this.getIdType(), "id", null, 1, 1, NodeContent.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeContent_Label(), theXMLTypePackage.getToken(), "label", null, 0, 1, NodeContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeContent_Pid(), this.getIdType(), "pid", null, 0, 1, NodeContent.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeContent_Start(), this.getTimeType(), "start", null, 0, 1, NodeContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeContent_Startopen(), this.getTimeType(), "startopen", null, 0, 1, NodeContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nodesContentEClass, NodesContent.class, "NodesContent", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNodesContent_Node(), this.getNodeContent(), null, "node", null, 0, -1, NodesContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodesContent_Count(), theXMLTypePackage.getNonNegativeInteger(), "count", null, 0, 1,
				NodesContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(parentsContentEClass, ParentsContent.class, "ParentsContent", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParentsContent_Parent(), this.getParentType(), null, "parent", null, 0, -1,
				ParentsContent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parentTypeEClass, ParentType.class, "ParentType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParentType_For(), this.getIdType(), "for", null, 1, 1, ParentType.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(spellsContentEClass, SpellsContent.class, "SpellsContent", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSpellsContent_Spell(), this.getSpellType(), null, "spell", null, 1, -1, SpellsContent.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(spellTypeEClass, SpellType.class, "SpellType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpellType_End(), this.getTimeType(), "end", null, 0, 1, SpellType.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpellType_Endopen(), this.getTimeType(), "endopen", null, 0, 1, SpellType.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpellType_Start(), this.getTimeType(), "start", null, 0, 1, SpellType.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpellType_Startopen(), this.getTimeType(), "startopen", null, 0, 1, SpellType.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(attrtypeTypeEEnum, AttrtypeType.class, "AttrtypeType");
		addEEnumLiteral(attrtypeTypeEEnum, AttrtypeType.INTEGER);
		addEEnumLiteral(attrtypeTypeEEnum, AttrtypeType.LONG);
		addEEnumLiteral(attrtypeTypeEEnum, AttrtypeType.DOUBLE);
		addEEnumLiteral(attrtypeTypeEEnum, AttrtypeType.FLOAT);
		addEEnumLiteral(attrtypeTypeEEnum, AttrtypeType.BOOLEAN);
		addEEnumLiteral(attrtypeTypeEEnum, AttrtypeType.LISTSTRING);
		addEEnumLiteral(attrtypeTypeEEnum, AttrtypeType.STRING);
		addEEnumLiteral(attrtypeTypeEEnum, AttrtypeType.ANY_URI);

		initEEnum(classTypeEEnum, ClassType.class, "ClassType");
		addEEnumLiteral(classTypeEEnum, ClassType.NODE);
		addEEnumLiteral(classTypeEEnum, ClassType.EDGE);

		initEEnum(defaultedgetypeTypeEEnum, DefaultedgetypeType.class, "DefaultedgetypeType");
		addEEnumLiteral(defaultedgetypeTypeEEnum, DefaultedgetypeType.DIRECTED);
		addEEnumLiteral(defaultedgetypeTypeEEnum, DefaultedgetypeType.UNDIRECTED);
		addEEnumLiteral(defaultedgetypeTypeEEnum, DefaultedgetypeType.MUTUAL);

		initEEnum(edgetypeTypeEEnum, EdgetypeType.class, "EdgetypeType");
		addEEnumLiteral(edgetypeTypeEEnum, EdgetypeType.DIRECTED);
		addEEnumLiteral(edgetypeTypeEEnum, EdgetypeType.UNDIRECTED);
		addEEnumLiteral(edgetypeTypeEEnum, EdgetypeType.MUTUAL);

		initEEnum(idtypeTypeEEnum, IdtypeType.class, "IdtypeType");
		addEEnumLiteral(idtypeTypeEEnum, IdtypeType.INTEGER);
		addEEnumLiteral(idtypeTypeEEnum, IdtypeType.STRING);

		initEEnum(modeTypeEEnum, ModeType.class, "ModeType");
		addEEnumLiteral(modeTypeEEnum, ModeType.STATIC);
		addEEnumLiteral(modeTypeEEnum, ModeType.DYNAMIC);

		initEEnum(timeformatTypeEEnum, TimeformatType.class, "TimeformatType");
		addEEnumLiteral(timeformatTypeEEnum, TimeformatType.INTEGER);
		addEEnumLiteral(timeformatTypeEEnum, TimeformatType.DOUBLE);
		addEEnumLiteral(timeformatTypeEEnum, TimeformatType.DATE);
		addEEnumLiteral(timeformatTypeEEnum, TimeformatType.DATE_TIME);

		initEEnum(versionTypeEEnum, VersionType.class, "VersionType");
		addEEnumLiteral(versionTypeEEnum, VersionType._12);

		// Initialize data types
		initEDataType(attrtypeTypeObjectEDataType, AttrtypeType.class, "AttrtypeTypeObject", IS_SERIALIZABLE,
				IS_GENERATED_INSTANCE_CLASS);
		initEDataType(classTypeObjectEDataType, ClassType.class, "ClassTypeObject", IS_SERIALIZABLE,
				IS_GENERATED_INSTANCE_CLASS);
		initEDataType(defaultedgetypeTypeObjectEDataType, DefaultedgetypeType.class, "DefaultedgetypeTypeObject",
				IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(edgetypeTypeObjectEDataType, EdgetypeType.class, "EdgetypeTypeObject", IS_SERIALIZABLE,
				IS_GENERATED_INSTANCE_CLASS);
		initEDataType(idTypeEDataType, Object.class, "IdType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(idtypeTypeObjectEDataType, IdtypeType.class, "IdtypeTypeObject", IS_SERIALIZABLE,
				IS_GENERATED_INSTANCE_CLASS);
		initEDataType(modeTypeObjectEDataType, ModeType.class, "ModeTypeObject", IS_SERIALIZABLE,
				IS_GENERATED_INSTANCE_CLASS);
		initEDataType(timeformatTypeObjectEDataType, TimeformatType.class, "TimeformatTypeObject", IS_SERIALIZABLE,
				IS_GENERATED_INSTANCE_CLASS);
		initEDataType(timeTypeEDataType, Object.class, "TimeType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(versionTypeObjectEDataType, VersionType.class, "VersionTypeObject", IS_SERIALIZABLE,
				IS_GENERATED_INSTANCE_CLASS);
		initEDataType(weightTypeEDataType, float.class, "WeightType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(weightTypeObjectEDataType, Float.class, "WeightTypeObject", IS_SERIALIZABLE,
				!IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
		addAnnotation(attributeContentEClass, source,
				new String[] { "name", "attribute-content", "kind", "elementOnly" });
		addAnnotation(getAttributeContent_Group(), source, new String[] { "kind", "group", "name", "group:0" });
		addAnnotation(getAttributeContent_Default(), source, new String[] { "kind", "element", "name", "default",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getAttributeContent_Options(), source, new String[] { "kind", "element", "name", "options",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getAttributeContent_Id(), source, new String[] { "kind", "attribute", "name", "id" });
		addAnnotation(getAttributeContent_Title(), source, new String[] { "kind", "attribute", "name", "title" });
		addAnnotation(getAttributeContent_Type(), source, new String[] { "kind", "attribute", "name", "type" });
		addAnnotation(attributesContentEClass, source, new String[] { "name", "attributes-content", "kind",
				"elementOnly" });
		addAnnotation(getAttributesContent_Attribute(), source, new String[] { "kind", "element", "name", "attribute",
				"namespace", "##targetNamespace" });
		addAnnotation(getAttributesContent_Class(), source, new String[] { "kind", "attribute", "name", "class" });
		addAnnotation(getAttributesContent_End(), source, new String[] { "kind", "attribute", "name", "end" });
		addAnnotation(getAttributesContent_Endopen(), source, new String[] { "kind", "attribute", "name", "endopen" });
		addAnnotation(getAttributesContent_Mode(), source, new String[] { "kind", "attribute", "name", "mode" });
		addAnnotation(getAttributesContent_Start(), source, new String[] { "kind", "attribute", "name", "start" });
		addAnnotation(getAttributesContent_Startopen(), source,
				new String[] { "kind", "attribute", "name", "startopen" });
		addAnnotation(attrtypeTypeEEnum, source, new String[] { "name", "attrtype-type" });
		addAnnotation(attrtypeTypeObjectEDataType, source, new String[] { "name", "attrtype-type:Object", "baseType",
				"attrtype-type" });
		addAnnotation(attvaluesContentEClass, source,
				new String[] { "name", "attvalues-content", "kind", "elementOnly" });
		addAnnotation(getAttvaluesContent_Attvalue(), source, new String[] { "kind", "element", "name", "attvalue",
				"namespace", "##targetNamespace" });
		addAnnotation(attvalueTypeEClass, source, new String[] { "name", "attvalue_._type", "kind", "empty" });
		addAnnotation(getAttvalueType_End(), source, new String[] { "kind", "attribute", "name", "end" });
		addAnnotation(getAttvalueType_Endopen(), source, new String[] { "kind", "attribute", "name", "endopen" });
		addAnnotation(getAttvalueType_For(), source, new String[] { "kind", "attribute", "name", "for" });
		addAnnotation(getAttvalueType_Start(), source, new String[] { "kind", "attribute", "name", "start" });
		addAnnotation(getAttvalueType_Startopen(), source, new String[] { "kind", "attribute", "name", "startopen" });
		addAnnotation(getAttvalueType_Value(), source, new String[] { "kind", "attribute", "name", "value" });
		addAnnotation(classTypeEEnum, source, new String[] { "name", "class-type" });
		addAnnotation(classTypeObjectEDataType, source, new String[] { "name", "class-type:Object", "baseType",
				"class-type" });
		addAnnotation(defaultedgetypeTypeEEnum, source, new String[] { "name", "defaultedgetype-type" });
		addAnnotation(defaultedgetypeTypeObjectEDataType, source, new String[] { "name", "defaultedgetype-type:Object",
				"baseType", "defaultedgetype-type" });
		addAnnotation(documentRootEClass, source, new String[] { "name", "", "kind", "mixed" });
		addAnnotation(getDocumentRoot_Mixed(), source, new String[] { "kind", "elementWildcard", "name", ":mixed" });
		addAnnotation(getDocumentRoot_XMLNSPrefixMap(), source, new String[] { "kind", "attribute", "name",
				"xmlns:prefix" });
		addAnnotation(getDocumentRoot_XSISchemaLocation(), source, new String[] { "kind", "attribute", "name",
				"xsi:schemaLocation" });
		addAnnotation(getDocumentRoot_Attribute(), source, new String[] { "kind", "element", "name", "attribute",
				"namespace", "##targetNamespace" });
		addAnnotation(getDocumentRoot_Attributes(), source, new String[] { "kind", "element", "name", "attributes",
				"namespace", "##targetNamespace" });
		addAnnotation(getDocumentRoot_Attvalue(), source, new String[] { "kind", "element", "name", "attvalue",
				"namespace", "##targetNamespace" });
		addAnnotation(getDocumentRoot_Attvalues(), source, new String[] { "kind", "element", "name", "attvalues",
				"namespace", "##targetNamespace" });
		addAnnotation(getDocumentRoot_Color(), source, new String[] { "kind", "element", "name", "color", "namespace",
				"##targetNamespace" });
		addAnnotation(getDocumentRoot_Creator(), source, new String[] { "kind", "element", "name", "creator",
				"namespace", "##targetNamespace" });
		addAnnotation(getDocumentRoot_Default(), source, new String[] { "kind", "element", "name", "default",
				"namespace", "##targetNamespace" });
		addAnnotation(getDocumentRoot_Description(), source, new String[] { "kind", "element", "name", "description",
				"namespace", "##targetNamespace" });
		addAnnotation(getDocumentRoot_Edge(), source, new String[] { "kind", "element", "name", "edge", "namespace",
				"##targetNamespace" });
		addAnnotation(getDocumentRoot_Edges(), source, new String[] { "kind", "element", "name", "edges", "namespace",
				"##targetNamespace" });
		addAnnotation(getDocumentRoot_Gexf(), source, new String[] { "kind", "element", "name", "gexf", "namespace",
				"##targetNamespace" });
		addAnnotation(getDocumentRoot_Graph(), source, new String[] { "kind", "element", "name", "graph", "namespace",
				"##targetNamespace" });
		addAnnotation(getDocumentRoot_Keywords(), source, new String[] { "kind", "element", "name", "keywords",
				"namespace", "##targetNamespace" });
		addAnnotation(getDocumentRoot_Meta(), source, new String[] { "kind", "element", "name", "meta", "namespace",
				"##targetNamespace" });
		addAnnotation(getDocumentRoot_Node(), source, new String[] { "kind", "element", "name", "node", "namespace",
				"##targetNamespace" });
		addAnnotation(getDocumentRoot_Nodes(), source, new String[] { "kind", "element", "name", "nodes", "namespace",
				"##targetNamespace" });
		addAnnotation(getDocumentRoot_Options(), source, new String[] { "kind", "element", "name", "options",
				"namespace", "##targetNamespace" });
		addAnnotation(getDocumentRoot_Parent(), source, new String[] { "kind", "element", "name", "parent",
				"namespace", "##targetNamespace" });
		addAnnotation(getDocumentRoot_Parents(), source, new String[] { "kind", "element", "name", "parents",
				"namespace", "##targetNamespace" });
		addAnnotation(getDocumentRoot_Position(), source, new String[] { "kind", "element", "name", "position",
				"namespace", "##targetNamespace" });
		addAnnotation(getDocumentRoot_Size(), source, new String[] { "kind", "element", "name", "size", "namespace",
				"##targetNamespace" });
		addAnnotation(getDocumentRoot_Spell(), source, new String[] { "kind", "element", "name", "spell", "namespace",
				"##targetNamespace" });
		addAnnotation(getDocumentRoot_Spells(), source, new String[] { "kind", "element", "name", "spells",
				"namespace", "##targetNamespace" });
		addAnnotation(getDocumentRoot_Thickness(), source, new String[] { "kind", "element", "name", "thickness",
				"namespace", "##targetNamespace" });
		addAnnotation(edgeContentEClass, source, new String[] { "name", "edge-content", "kind", "elementOnly" });
		addAnnotation(getEdgeContent_Group(), source, new String[] { "kind", "group", "name", "group:0" });
		addAnnotation(getEdgeContent_Attvalues(), source, new String[] { "kind", "element", "name", "attvalues",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getEdgeContent_Spells(), source, new String[] { "kind", "element", "name", "spells", "namespace",
				"##targetNamespace", "group", "#group:0" });
		addAnnotation(getEdgeContent_Color(), source, new String[] { "kind", "element", "name", "color", "namespace",
				"##targetNamespace", "group", "#group:0" });
		addAnnotation(getEdgeContent_Thickness(), source, new String[] { "kind", "element", "name", "thickness",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getEdgeContent_Shape(), source, new String[] { "kind", "element", "name", "shape", "namespace",
				"##targetNamespace", "group", "#group:0" });
		addAnnotation(getEdgeContent_End(), source, new String[] { "kind", "attribute", "name", "end" });
		addAnnotation(getEdgeContent_Endopen(), source, new String[] { "kind", "attribute", "name", "endopen" });
		addAnnotation(getEdgeContent_Id(), source, new String[] { "kind", "attribute", "name", "id" });
		addAnnotation(getEdgeContent_Label(), source, new String[] { "kind", "attribute", "name", "label" });
		addAnnotation(getEdgeContent_Source(), source, new String[] { "kind", "attribute", "name", "source" });
		addAnnotation(getEdgeContent_Start(), source, new String[] { "kind", "attribute", "name", "start" });
		addAnnotation(getEdgeContent_Startopen(), source, new String[] { "kind", "attribute", "name", "startopen" });
		addAnnotation(getEdgeContent_Target(), source, new String[] { "kind", "attribute", "name", "target" });
		addAnnotation(getEdgeContent_Type(), source, new String[] { "kind", "attribute", "name", "type" });
		addAnnotation(getEdgeContent_Weight(), source, new String[] { "kind", "attribute", "name", "weight" });
		addAnnotation(edgesContentEClass, source, new String[] { "name", "edges-content", "kind", "elementOnly" });
		addAnnotation(getEdgesContent_Edge(), source, new String[] { "kind", "element", "name", "edge", "namespace",
				"##targetNamespace" });
		addAnnotation(getEdgesContent_Count(), source, new String[] { "kind", "attribute", "name", "count" });
		addAnnotation(edgetypeTypeEEnum, source, new String[] { "name", "edgetype-type" });
		addAnnotation(edgetypeTypeObjectEDataType, source, new String[] { "name", "edgetype-type:Object", "baseType",
				"edgetype-type" });
		addAnnotation(gexfContentEClass, source, new String[] { "name", "gexf-content", "kind", "elementOnly" });
		addAnnotation(getGexfContent_Meta(), source, new String[] { "kind", "element", "name", "meta", "namespace",
				"##targetNamespace" });
		addAnnotation(getGexfContent_Graph(), source, new String[] { "kind", "element", "name", "graph", "namespace",
				"##targetNamespace" });
		addAnnotation(getGexfContent_Variant(), source, new String[] { "kind", "attribute", "name", "variant" });
		addAnnotation(getGexfContent_Version(), source, new String[] { "kind", "attribute", "name", "version" });
		addAnnotation(graphContentEClass, source, new String[] { "name", "graph-content", "kind", "elementOnly" });
		addAnnotation(getGraphContent_Group(), source, new String[] { "kind", "group", "name", "group:0" });
		addAnnotation(getGraphContent_Attributes(), source, new String[] { "kind", "element", "name", "attributes",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getGraphContent_Nodes(), source, new String[] { "kind", "element", "name", "nodes", "namespace",
				"##targetNamespace", "group", "#group:0" });
		addAnnotation(getGraphContent_Edges(), source, new String[] { "kind", "element", "name", "edges", "namespace",
				"##targetNamespace", "group", "#group:0" });
		addAnnotation(getGraphContent_Defaultedgetype(), source, new String[] { "kind", "attribute", "name",
				"defaultedgetype" });
		addAnnotation(getGraphContent_End(), source, new String[] { "kind", "attribute", "name", "end" });
		addAnnotation(getGraphContent_Endopen(), source, new String[] { "kind", "attribute", "name", "endopen" });
		addAnnotation(getGraphContent_Idtype(), source, new String[] { "kind", "attribute", "name", "idtype" });
		addAnnotation(getGraphContent_Mode(), source, new String[] { "kind", "attribute", "name", "mode" });
		addAnnotation(getGraphContent_Start(), source, new String[] { "kind", "attribute", "name", "start" });
		addAnnotation(getGraphContent_Startopen(), source, new String[] { "kind", "attribute", "name", "startopen" });
		addAnnotation(getGraphContent_Timeformat(), source, new String[] { "kind", "attribute", "name", "timeformat" });
		addAnnotation(idTypeEDataType, source, new String[] { "name", "id-type", "memberTypes",
				"http://www.eclipse.org/emf/2003/XMLType#string http://www.eclipse.org/emf/2003/XMLType#integer" });
		addAnnotation(idtypeTypeEEnum, source, new String[] { "name", "idtype-type" });
		addAnnotation(idtypeTypeObjectEDataType, source, new String[] { "name", "idtype-type:Object", "baseType",
				"idtype-type" });
		addAnnotation(metaContentEClass, source, new String[] { "name", "meta-content", "kind", "elementOnly" });
		addAnnotation(getMetaContent_Group(), source, new String[] { "kind", "group", "name", "group:0" });
		addAnnotation(getMetaContent_Creator(), source, new String[] { "kind", "element", "name", "creator",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getMetaContent_Keywords(), source, new String[] { "kind", "element", "name", "keywords",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getMetaContent_Description(), source, new String[] { "kind", "element", "name", "description",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getMetaContent_Lastmodifieddate(), source, new String[] { "kind", "attribute", "name",
				"lastmodifieddate" });
		addAnnotation(modeTypeEEnum, source, new String[] { "name", "mode-type" });
		addAnnotation(modeTypeObjectEDataType, source, new String[] { "name", "mode-type:Object", "baseType",
				"mode-type" });
		addAnnotation(nodeContentEClass, source, new String[] { "name", "node-content", "kind", "elementOnly" });
		addAnnotation(getNodeContent_Group(), source, new String[] { "kind", "group", "name", "group:0" });
		addAnnotation(getNodeContent_Attvalues(), source, new String[] { "kind", "element", "name", "attvalues",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getNodeContent_Spells(), source, new String[] { "kind", "element", "name", "spells", "namespace",
				"##targetNamespace", "group", "#group:0" });
		addAnnotation(getNodeContent_Nodes(), source, new String[] { "kind", "element", "name", "nodes", "namespace",
				"##targetNamespace", "group", "#group:0" });
		addAnnotation(getNodeContent_Edges(), source, new String[] { "kind", "element", "name", "edges", "namespace",
				"##targetNamespace", "group", "#group:0" });
		addAnnotation(getNodeContent_Parents(), source, new String[] { "kind", "element", "name", "parents",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getNodeContent_Color(), source, new String[] { "kind", "element", "name", "color", "namespace",
				"##targetNamespace", "group", "#group:0" });
		addAnnotation(getNodeContent_Position(), source, new String[] { "kind", "element", "name", "position",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getNodeContent_Size(), source, new String[] { "kind", "element", "name", "size", "namespace",
				"##targetNamespace", "group", "#group:0" });
		addAnnotation(getNodeContent_Shape(), source, new String[] { "kind", "element", "name", "shape", "namespace",
				"##targetNamespace", "group", "#group:0" });
		addAnnotation(getNodeContent_End(), source, new String[] { "kind", "attribute", "name", "end" });
		addAnnotation(getNodeContent_Endopen(), source, new String[] { "kind", "attribute", "name", "endopen" });
		addAnnotation(getNodeContent_Id(), source, new String[] { "kind", "attribute", "name", "id" });
		addAnnotation(getNodeContent_Label(), source, new String[] { "kind", "attribute", "name", "label" });
		addAnnotation(getNodeContent_Pid(), source, new String[] { "kind", "attribute", "name", "pid" });
		addAnnotation(getNodeContent_Start(), source, new String[] { "kind", "attribute", "name", "start" });
		addAnnotation(getNodeContent_Startopen(), source, new String[] { "kind", "attribute", "name", "startopen" });
		addAnnotation(nodesContentEClass, source, new String[] { "name", "nodes-content", "kind", "elementOnly" });
		addAnnotation(getNodesContent_Node(), source, new String[] { "kind", "element", "name", "node", "namespace",
				"##targetNamespace" });
		addAnnotation(getNodesContent_Count(), source, new String[] { "kind", "attribute", "name", "count" });
		addAnnotation(parentsContentEClass, source, new String[] { "name", "parents-content", "kind", "elementOnly" });
		addAnnotation(getParentsContent_Parent(), source, new String[] { "kind", "element", "name", "parent",
				"namespace", "##targetNamespace" });
		addAnnotation(parentTypeEClass, source, new String[] { "name", "parent_._type", "kind", "empty" });
		addAnnotation(getParentType_For(), source, new String[] { "kind", "attribute", "name", "for" });
		addAnnotation(spellsContentEClass, source, new String[] { "name", "spells-content", "kind", "elementOnly" });
		addAnnotation(getSpellsContent_Spell(), source, new String[] { "kind", "element", "name", "spell", "namespace",
				"##targetNamespace" });
		addAnnotation(spellTypeEClass, source, new String[] { "name", "spell_._type", "kind", "empty" });
		addAnnotation(getSpellType_End(), source, new String[] { "kind", "attribute", "name", "end" });
		addAnnotation(getSpellType_Endopen(), source, new String[] { "kind", "attribute", "name", "endopen" });
		addAnnotation(getSpellType_Start(), source, new String[] { "kind", "attribute", "name", "start" });
		addAnnotation(getSpellType_Startopen(), source, new String[] { "kind", "attribute", "name", "startopen" });
		addAnnotation(timeformatTypeEEnum, source, new String[] { "name", "timeformat-type" });
		addAnnotation(timeformatTypeObjectEDataType, source, new String[] { "name", "timeformat-type:Object",
				"baseType", "timeformat-type" });
		addAnnotation(
				timeTypeEDataType,
				source,
				new String[] {
						"name",
						"time-type",
						"memberTypes",
						"http://www.eclipse.org/emf/2003/XMLType#integer http://www.eclipse.org/emf/2003/XMLType#double http://www.eclipse.org/emf/2003/XMLType#date http://www.eclipse.org/emf/2003/XMLType#dateTime" });
		addAnnotation(versionTypeEEnum, source, new String[] { "name", "version_._type" });
		addAnnotation(versionTypeObjectEDataType, source, new String[] { "name", "version_._type:Object", "baseType",
				"version_._type" });
		addAnnotation(weightTypeEDataType, source, new String[] { "name", "weight-type", "baseType",
				"http://www.eclipse.org/emf/2003/XMLType#float" });
		addAnnotation(weightTypeObjectEDataType, source, new String[] { "name", "weight-type:Object", "baseType",
				"weight-type" });
	}

} //GexfPackageImpl
