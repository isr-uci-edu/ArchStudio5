package org.archstudio.myxgen.jet.translator;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;

import org.archstudio.myxgen.jet.extension.ConnectionTiming;
import org.archstudio.myxgen.jet.extension.Direction;
import org.archstudio.myxgen.jet.extension.IInterface;
import org.archstudio.myxgen.jet.extension.IMyxBrickExtension;
import org.archstudio.myxgen.jet.util.TextUtil;

/**
 * A translator from the eclipse extension point to xadl
 * 
 * @author Nobu Takeo [nobu.takeo@gmail.com, nobu.takeo@uci.edu]
 */
public class CodeGenXadlFullTranslator {

	protected IXArchADT xarch = null;
	protected ObjRef xArchRef = null;

	public CodeGenXadlFullTranslator(IXArchADT xarch, ObjRef xArchRef) {
		this.xarch = xarch;
		this.xArchRef = xArchRef;
	}

	/**
	 * Treanslates a brick into xadl. Based on the information from brick, this method creates
	 * componentType/connectorType, interfaceType, interface, signature and maps them onto brickRef.
	 * 
	 * @param archStructureRef
	 *            Structure
	 * @param brickRef
	 *            Component/Connector
	 * @param brick
	 *            Brick
	 */
	public void translate(ObjRef archStructureRef, ObjRef brickRef, IMyxBrickExtension brick) {
		ObjRef typesContextRef = xarch.createContext(xArchRef, "types");

		ObjRef archTypesRef = xarch.getElement(typesContextRef, "archTypes", xArchRef);
		if (archTypesRef == null) {
			archTypesRef = xarch.createElement(typesContextRef, "archTypes");
			xarch.add(xArchRef, "object", archTypesRef);
		}

		//removes the interfaceTypes and componentType this brick uses
		//if they are not used by other bricks.
		clearTypes(archStructureRef, archTypesRef, brickRef, brick);

		//adds component/connector type to xadl
		ObjRef brickTypeRef = addComponentType(archStructureRef, archTypesRef, typesContextRef, brickRef, brick);

		//adds signatures, interfaces, interfaceTypes to xadl.
		//first, checks defined inerfaceTypes
		Map<String, ObjRef> implInterfaceTypeMap = new HashMap<String, ObjRef>();
		for (ObjRef interfaceTypeRef : xarch.getAll(archTypesRef, "interfaceType")) {
			String key = CodeGenXadlFullTranslator.getJavaClassName(xarch, interfaceTypeRef);
			implInterfaceTypeMap.put(key, interfaceTypeRef);
		}

		//extension point brink interfaces and its ancestors
		Collection<IInterface> brickIntfs = new HashSet<IInterface>();
		brickIntfs.addAll(brick.getInterfaces());
		brickIntfs.addAll(brick.getAncestorsExtensionInterfaces());

		//updates componentType and interfaceType
		// TODO: check this?
		String symbolicName = brick.getSymbolicName();
		for (IInterface brickIntf : brickIntfs) {
			if (!brickIntf.getSymbolicName().equals(symbolicName)) {
				//only the brick interfaces that are defined in the same plugin 
				//can be fully translated into the conventional xadl
				continue;
			}
			addSignatureAndInterfaceType(archStructureRef, archTypesRef, typesContextRef, brickRef, brickTypeRef,
					brickIntf, implInterfaceTypeMap);
		}

		//updates components
		for (ObjRef bRef : getBricksWithTheSameBrickType(archStructureRef, brickTypeRef)) {
			for (IInterface brickIntf : brickIntfs) {
				addInterface(typesContextRef, bRef, brickTypeRef, brickIntf, implInterfaceTypeMap);
			}
		}
	}

	/**
	 * Clears existing IntefaceTypes and ComponentType of the given brickRef if they are used by only the brickRef.
	 * 
	 * @param brickRef
	 */
	private void clearTypes(ObjRef archStructureRef, ObjRef archTypesRef, ObjRef brickRef, IMyxBrickExtension brick) {

		//Gets the interfaceTypes this brickRef contains.
		// We will remove the interfaceTypes that are not used by others
		Set<ObjRef> interfaceTypeSet = new HashSet<ObjRef>();
		for (ObjRef interfaceRef : xarch.getAll(brickRef, "interface")) {
			ObjRef interfaceTypeRef = XadlUtils.resolveXLink(xarch, interfaceRef, "type");
			if (interfaceTypeRef != null) {
				interfaceTypeSet.add(interfaceTypeRef);
			}
		}

		//Looks at all the other components and if one of them uses the same intefaceType,
		//leaves the interfaceType not-removed.
		Set<ObjRef> brickRefSet = new HashSet<ObjRef>();
		for (ObjRef objRef : xarch.getAll(archStructureRef, "component")) {
			brickRefSet.add(objRef);
		}
		for (ObjRef objRef : xarch.getAll(archStructureRef, "connector")) {
			brickRefSet.add(objRef);
		}
		for (ObjRef objRef : brickRefSet) {
			if (objRef.equals(brickRef)) {
				continue;
			}
			for (ObjRef interfaceRef : xarch.getAll(objRef, "interface")) {
				ObjRef interfaceTypeRef = XadlUtils.resolveXLink(xarch, interfaceRef, "type");
				interfaceTypeSet.remove(interfaceTypeRef);
			}
		}

		//Removes interface from the brick
		for (ObjRef interfaceEltRef : xarch.getAll(brickRef, "interface")) {
			XadlUtils.remove(xarch, interfaceEltRef);
		}

		//Removes interfaceType if it is used by only this brick
		for (ObjRef interfaceTypeRef : interfaceTypeSet) {
			try {
				XadlUtils.remove(xarch, interfaceTypeRef);
			}
			catch (NoSuchObjectException e) {
				e.printStackTrace();
			}
		}

		//Removes signatures from the componentType/connectorType of the brick
		//and the interfaces from bricks that have the same componentType/connectorType
		ObjRef brickTypeRef = CodeGenXadlFullTranslator.getBrickTypeRef(xarch, archTypesRef, brickRef, brick);
		if (brickTypeRef != null) {

			//removes signatures from the componentType/connectorType of the brick
			for (ObjRef sigRef : xarch.getAll(brickTypeRef, "signature")) {
				//removes Signatures
				XadlUtils.remove(xarch, sigRef);
			}

			//removes interfaces from bricks that have the same componentType/connectorType
			Collection<ObjRef> brickRefs = getBricksWithTheSameBrickType(archStructureRef, brickTypeRef);
			for (ObjRef bRef : brickRefs) {
				for (ObjRef iEltRef : xarch.getAll(bRef, "interface")) {
					if (iEltRef != null) {
						XadlUtils.remove(xarch, iEltRef);
					}
				}
			}
		}

	}

	/**
	 * Gets the collection of Component/Connector whose Type is the same with the given brickTypeRef.
	 * 
	 * @param archStructureRef
	 * @param brickTypeRef
	 * @return Collection of ObjRef including the given brickRef
	 */
	private Collection<ObjRef> getBricksWithTheSameBrickType(ObjRef archStructureRef, ObjRef brickTypeRef) {

		Collection<ObjRef> brickRefs = new ArrayList<ObjRef>();

		if (brickTypeRef == null) {
			return brickRefs;
		}

		//checks if the given brickRef is component or connector
		String typeOfThing = xarch.isInstanceOf(brickTypeRef, "types#ComponentType") ? "component" : "connector";

		//sees if there is a component or connector that uses the same type.
		for (ObjRef objRef : xarch.getAll(archStructureRef, typeOfThing)) {
			ObjRef objTypeRef = XadlUtils.resolveXLink(xarch, objRef, "type");
			if (objTypeRef != null && objTypeRef.equals(brickTypeRef)) {
				brickRefs.add(objRef);
			}
		}

		return brickRefs;
	}

	/**
	 * Gets java class name of the brickType or interfaceType
	 * 
	 * @param objTypeRef
	 * @return the fully qualified java class name. null if no class name is found.
	 */
	public static String getJavaClassName(IXArchADT xarch, ObjRef objTypeRef) {
		if (objTypeRef == null) {
			return null;
		}

		ObjRef[] implementationRefs = xarch.getAll(objTypeRef, "implementation");
		if (implementationRefs == null || implementationRefs.length == 0) {
			return null;
		}
		ObjRef javaImplementationRef = null;
		for (ObjRef element : implementationRefs) {
			if (xarch.isInstanceOf(element, "javaimplementation#JavaImplementation")) {
				javaImplementationRef = element;
				break;
			}
		}
		if (javaImplementationRef == null) {
			return null;
		}
		ObjRef mainClassRef = (ObjRef) xarch.get(javaImplementationRef, "mainClass");
		if (mainClassRef == null) {
			return null;
		}
		ObjRef mainClassNameRef = (ObjRef) xarch.get(mainClassRef, "javaClassName");
		if (mainClassNameRef == null) {
			return null;
		}
		String mainClassName = (String) xarch.get(mainClassNameRef, "value");

		return mainClassName;
	}

	/**
	 * Returns java class name from the given Signature
	 * 
	 * @param xarch
	 * @param sigRef
	 * @return
	 */
	private static String getJavaClassNameFromSignature(IXArchADT xarch, ObjRef sigRef) {
		ObjRef typeElt = (ObjRef) xarch.get(sigRef, "type");
		if (typeElt == null) {
			return null;
		}
		ObjRef intfType = XadlUtils.resolveXLink(xarch, typeElt);
		if (intfType == null) {
			return null;
		}
		return CodeGenXadlFullTranslator.getJavaClassName(xarch, intfType);
	}

	//	/**
	//	 * Returns InterfaceType from the given java class name
	//	 * @param xarch
	//	 * @param archTypesRef
	//	 * @param fqJavaClassName
	//	 * @return
	//	 */
	//	private static ObjRef getInterfaceTypeRef(IXArchADT xarch, ObjRef archTypesRef, String fqJavaClassName) {
	//		if(fqJavaClassName == null) {
	//			return null;
	//		}
	//		
	//		for(ObjRef intfTypeRef : xarch.getAll(archTypesRef, "interfaceType")) {
	//			if(fqJavaClassName.equals(getJavaClassName(xarch, intfTypeRef))) {
	//				return intfTypeRef;
	//			}
	//		}
	//		return null;
	//	}

	/**
	 * Returns Signature form the given java class name
	 * 
	 * @param xarch
	 * @param brickTypeRef
	 * @param fqJavaClassName
	 * @return
	 */
	private static ObjRef getSignatureRef(IXArchADT xarch, ObjRef brickTypeRef, String fqJavaClassName) {
		if (fqJavaClassName == null) {
			return null;
		}

		for (ObjRef signatureRef : xarch.getAll(brickTypeRef, "signature")) {
			String javaImplName = CodeGenXadlFullTranslator.getJavaClassNameFromSignature(xarch, signatureRef);
			if (fqJavaClassName.equals(javaImplName)) {
				return signatureRef;
			}
		}

		return null;
	}

	/**
	 * Returns ComponentType/ConnectorType. If ComponentType/ConnectorType is already defined in the given brickRef, it
	 * is returned. Otherwise, brick's java class name is used to find it.
	 */
	private static ObjRef getBrickTypeRef(IXArchADT xarch, ObjRef archTypesRef, ObjRef brickRef,
			IMyxBrickExtension brick) {
		ObjRef brickTypeObjRef = XadlUtils.resolveXLink(xarch, brickRef, "type");

		if (brickTypeObjRef != null) {
			return brickTypeObjRef;
		}

		//the brick's javaclass name is used to see if the brick type is the same.
		String brickClassName = brick.getFQDefaultImplClassName();

		//checks if the given brickRef is component or connector
		String typeOfThing = xarch.isInstanceOf(brickRef, "types#Component") ? "componentType" : "connectorType";
		//checks if there is the same brickType in the architecture
		for (ObjRef brickTypeRef : xarch.getAll(archTypesRef, typeOfThing)) {
			if (brickClassName.equals(CodeGenXadlFullTranslator.getJavaClassName(xarch, brickTypeRef))) {
				brickTypeObjRef = brickTypeRef;
				break;
			}
		}

		return brickTypeObjRef;
	}

	/**
	 * Generates Component/ConnectorType based on the brick and insert it into XADL if it does not exist.
	 * 
	 * @param archStructureRef
	 * @param archTypesRef
	 * @param typesContextRef
	 * @param brickRef
	 * @param brick
	 * @return Component/ConnectorType
	 */
	private ObjRef addComponentType(ObjRef archStructureRef, ObjRef archTypesRef, ObjRef typesContextRef,
			ObjRef brickRef, IMyxBrickExtension brick) {

		//component
		//		final ObjRef archStructureRef = xarch.getByID(xArchRef, archStructureXArchID);
		//		ObjRef componentRef = xarch.create(typesContextRef, "component");
		//		xarch.set(componentRef, "id", UIDGenerator.generateUID("component"));
		//		XadlUtils.setDescription(xarch, componentRef, "[Nobu Component]");
		//		xarch.add(archStructureRef, "component", componentRef);

		/////////////////////////////////////////////
		//checks if the brickType has already existed.
		/////////////////////////////////////////////

		ObjRef brickTypeObjRef = CodeGenXadlFullTranslator.getBrickTypeRef(xarch, archTypesRef, brickRef, brick);

		//creates a new brickType if there doesn't exist the same brickType.
		if (brickTypeObjRef == null) {
			String typeOfThing = xarch.isInstanceOf(brickRef, "types#Component") ? "componentType" : "connectorType";

			//creates a new brickType
			brickTypeObjRef = xarch.create(typesContextRef, "componentType");
			String brickTypeId = UIDGenerator.generateUID(typeOfThing);
			xarch.add(archTypesRef, typeOfThing, brickTypeObjRef);
			xarch.set(brickTypeObjRef, "id", brickTypeId);
			XadlUtils.setDescription(xarch, brickTypeObjRef, brick.getName());
			//promote component type to VariantComponentTypeImpl"
			IXArchTypeMetadata compRefTypeMetadata = xarch.getTypeMetadata(brickTypeObjRef);
			IXArchTypeMetadata variantComponentTypeImplTypeMetadata = xarch
					.getTypeMetadata("implementation#VariantComponentTypeImpl");
			for (String type : XArchMetadataUtils.getPromotionPathTypes(xarch, compRefTypeMetadata.getType(),
					variantComponentTypeImplTypeMetadata.getType())) {
				IXArchTypeMetadata tm = xarch.getTypeMetadata(type);
				ObjRef contextRef = xarch.createContext(xArchRef, XArchMetadataUtils.getTypeContext(tm.getType()));
				xarch.promoteTo(contextRef, XArchMetadataUtils.getTypeName(tm.getType()), brickTypeObjRef);
			}

			//adds Implementation
			ObjRef compTypeImplContextRef = xarch.createContext(xarch.getXArch(brickTypeObjRef), "implementation");
			ObjRef compTypeImplRef = xarch.create(compTypeImplContextRef, "Implementation");
			xarch.add(brickTypeObjRef, "implementation", compTypeImplRef);

			//promotes to JavaImplementation
			IXArchTypeMetadata implRefTypeMetadata = xarch.getTypeMetadata(compTypeImplRef);
			IXArchTypeMetadata javaImplTypeMetadata = xarch.getTypeMetadata("javaimplementation#JavaImplementation");
			for (String type : XArchMetadataUtils.getPromotionPathTypes(xarch, implRefTypeMetadata.getType(),
					javaImplTypeMetadata.getType())) {
				IXArchTypeMetadata tm = xarch.getTypeMetadata(type);
				ObjRef contextRef = xarch.createContext(xArchRef, XArchMetadataUtils.getTypeContext(tm.getType()));
				xarch.promoteTo(contextRef, XArchMetadataUtils.getTypeName(tm.getType()), compTypeImplRef);
			}

			//adds MainClass
			//typePackage="javaimplementation", typeName="JavaClassFile", propName="mainClass"	
			ObjRef implContextRef = xarch.createContext(xarch.getXArch(compTypeImplRef), "javaimplementation");
			ObjRef javaImplRef = xarch.create(implContextRef, "JavaClassFile");
			xarch.set(compTypeImplRef, "mainClass", javaImplRef);

			//adds JavaClassName
			//typePackage="javaimplementation",typeName="JavaClassName",propName="javaClassName"	
			ObjRef javaImplontextRef = xarch.createContext(xarch.getXArch(javaImplRef), "javaimplementation");
			ObjRef javaClassNameRef = xarch.create(javaImplontextRef, "JavaClassName");
			xarch.set(javaImplRef, "javaClassName", javaClassNameRef);
			xarch.set(javaClassNameRef, "value", brick.getFQDefaultImplClassName());
		}
		//sets this brickType to the brick
		String brickTypeId = XadlUtils.getID(xarch, brickTypeObjRef);
		XadlUtils.setXLink(xarch, brickRef, "type", brickTypeId);

		return brickTypeObjRef;
	}

	/**
	 * Gets string expression of the given signature for xadl.
	 * 
	 * @return
	 */
	private static String getXadlDirection(Direction direction) {
		String strDirection = null;
		switch (direction) {
		case IN:
			strDirection = "in";
			break;
		case OUT_MULTI:
		case OUT_SINGLE:
			strDirection = "out";
			break;
		default:
			strDirection = "none";

		}

		return strDirection;
	}

	/**
	 * Adds Interface into Component/Connector
	 * 
	 * @param typesContextRef
	 * @param brickRef
	 * @param brickTypeRef
	 * @param brickInterface
	 * @param implInterfaceTypeMap
	 */
	private void addInterface(ObjRef typesContextRef, ObjRef brickRef, ObjRef brickTypeRef, IInterface brickInterface,
			Map<String, ObjRef> implInterfaceTypeMap) {

		//craetes Interface
		ObjRef interfaceRef = xarch.create(typesContextRef, "interface");
		String interfaceId = UIDGenerator.generateUID("interface");
		xarch.set(interfaceRef, "id", interfaceId);
		XadlUtils.setDescription(xarch, interfaceRef, brickInterface.getName());
		XadlUtils.setDirection(xarch, interfaceRef,
				CodeGenXadlFullTranslator.getXadlDirection(brickInterface.getDirection()));
		xarch.add(brickRef, "interface", interfaceRef);

		//sets signature to the Interface
		ObjRef signatureRef = CodeGenXadlFullTranslator.getSignatureRef(xarch, brickTypeRef,
				brickInterface.getFQJavaInterfaceName());
		if (signatureRef != null) {
			String signatureId = XadlUtils.getID(xarch, signatureRef);
			XadlUtils.setXLink(xarch, interfaceRef, "signature", signatureId);
		}

		//sets interfaceType to the Interface
		ObjRef interfaceTypeRef = implInterfaceTypeMap.get(brickInterface.getFQJavaInterfaceName());
		if (interfaceTypeRef != null) {
			String interfaceTypeId = XadlUtils.getID(xarch, interfaceTypeRef);
			XadlUtils.setXLink(xarch, interfaceRef, "type", interfaceTypeId);
		}
	}

	/**
	 * Generates InterfaceType, and Adds Signature to ComponentType/ConnectorType based on brickInterface and insert
	 * them into XADL.
	 * 
	 * @param archStructureRef
	 * @param archTypesRef
	 * @param typesContextRef
	 * @param brickRef
	 * @param compTypeRef
	 * @param brickInterface
	 * @param implInterfaceTypeMap
	 */
	private void addSignatureAndInterfaceType(ObjRef archStructureRef, ObjRef archTypesRef, ObjRef typesContextRef,
			ObjRef brickRef, ObjRef compTypeRef, IInterface brickInterface, Map<String, ObjRef> implInterfaceTypeMap) {

		String direction = CodeGenXadlFullTranslator.getXadlDirection(brickInterface.getDirection());

		//////////////////////
		//Signature
		//////////////////////
		ObjRef signatureRef = null;
		signatureRef = xarch.create(typesContextRef, "signature");
		String signatureId = UIDGenerator.generateUID("signature");
		xarch.set(signatureRef, "id", signatureId);
		XadlUtils.setDescription(xarch, signatureRef, brickInterface.getName());
		XadlUtils.setDirection(xarch, signatureRef, direction);
		xarch.add(compTypeRef, "signature", signatureRef);
		//add ServiceType
		ObjRef serviceTypeRef = xarch.create(typesContextRef, "SignatureServiceType");
		xarch.set(serviceTypeRef, "Value",
				brickInterface.getConnectionTiming() == ConnectionTiming.CONNECT_BEFORE_BEGIN ? "provides" : "requires");
		xarch.set(signatureRef, "ServiceType", serviceTypeRef);

		//promote signature to SignatureImpl"
		IXArchTypeMetadata signatureRefTypeMetadata = xarch.getTypeMetadata(signatureRef);
		IXArchTypeMetadata signatureImplTypeMetadata = xarch.getTypeMetadata("implementation#SignatureImpl");
		for (String type : XArchMetadataUtils.getPromotionPathTypes(xarch, signatureRefTypeMetadata.getType(),
				signatureImplTypeMetadata.getType())) {
			IXArchTypeMetadata tm = xarch.getTypeMetadata(type);
			ObjRef contextRef = xarch.createContext(xArchRef, XArchMetadataUtils.getTypeContext(tm.getType()));
			xarch.promoteTo(contextRef, XArchMetadataUtils.getTypeName(tm.getType()), signatureRef);
		}
		// add Implementation
		ObjRef signatureContextRef = xarch.createContext(xarch.getXArch(signatureRef), "implementation");
		ObjRef signatureImplRef = xarch.create(signatureContextRef, "Implementation");
		xarch.add(signatureRef, "implementation", signatureImplRef);
		//promote to LookupImplementation
		IXArchTypeMetadata signatureImplRefTypeMetadata = xarch.getTypeMetadata(signatureImplRef);
		IXArchTypeMetadata signatureLookupImplTypeMetadata = xarch
				.getTypeMetadata("lookupimplementation#LookupImplementation");
		for (String type : XArchMetadataUtils.getPromotionPathTypes(xarch, signatureImplRefTypeMetadata.getType(),
				signatureLookupImplTypeMetadata.getType())) {
			IXArchTypeMetadata tm = xarch.getTypeMetadata(type);
			ObjRef contextRef = xarch.createContext(xArchRef, XArchMetadataUtils.getTypeContext(tm.getType()));
			xarch.promoteTo(contextRef, XArchMetadataUtils.getTypeName(tm.getType()), signatureImplRef);
		}
		//typePackage	"lookupimplementation"	
		//typeName	"LookupName"	
		//propName	"name"
		ObjRef signatureLookupImplContextRef = xarch.createContext(xarch.getXArch(signatureImplRef),
				"lookupimplementation");
		ObjRef signatureLookupNameRef = xarch.create(signatureLookupImplContextRef, "LookupName");
		xarch.set(signatureImplRef, "name", signatureLookupNameRef);
		xarch.set(signatureLookupNameRef, "value", brickInterface.getId());

		///////////////////////
		//InterfaceType
		//////////////////////
		String fqJavaInterfaceName = brickInterface.getFQJavaInterfaceName();
		ObjRef interfaceTypeObjRef = implInterfaceTypeMap.get(fqJavaInterfaceName);
		//checks if the interfaceType has already existed.
		if (interfaceTypeObjRef == null) {
			//create InterfaceType

			interfaceTypeObjRef = xarch.create(typesContextRef, "interfaceType");
			// add the interfaceTypeObjRef into the map
			implInterfaceTypeMap.put(fqJavaInterfaceName, interfaceTypeObjRef);

			String interfaceTypeId = UIDGenerator.generateUID("interfaceType");
			xarch.set(interfaceTypeObjRef, "id", interfaceTypeId);
			XadlUtils.setDescription(xarch, interfaceTypeObjRef, TextUtil.getClassPart(fqJavaInterfaceName));
			xarch.add(archTypesRef, "interfaceType", interfaceTypeObjRef);

			// promotes to InterfaceTypeImpl
			IXArchTypeMetadata interfaceTypeRefTypeMetadata = xarch.getTypeMetadata(interfaceTypeObjRef);
			IXArchTypeMetadata interfaceTypeImplTypeMetadata = xarch
					.getTypeMetadata("implementation#InterfaceTypeImpl");
			for (String type : XArchMetadataUtils.getPromotionPathTypes(xarch, interfaceTypeRefTypeMetadata.getType(),
					interfaceTypeImplTypeMetadata.getType())) {
				IXArchTypeMetadata tm = xarch.getTypeMetadata(type);
				ObjRef contextRef = xarch.createContext(xArchRef, XArchMetadataUtils.getTypeContext(tm.getType()));
				xarch.promoteTo(contextRef, XArchMetadataUtils.getTypeName(tm.getType()), interfaceTypeObjRef);
			}

			// adds Implementation
			ObjRef interfaceTypeContextRef = xarch.createContext(xarch.getXArch(interfaceTypeObjRef), "implementation");
			ObjRef interfaceTypeImplRef = xarch.create(interfaceTypeContextRef, "Implementation");
			xarch.add(interfaceTypeObjRef, "implementation", interfaceTypeImplRef);

			//promotes to JavaImplementation
			IXArchTypeMetadata interfaceTypeImplRefTypeMetadata = xarch.getTypeMetadata(interfaceTypeImplRef);
			IXArchTypeMetadata interfaceTypeJavaImplTypeMetadata = xarch
					.getTypeMetadata("javaimplementation#JavaImplementation");
			for (String type : XArchMetadataUtils.getPromotionPathTypes(xarch,
					interfaceTypeImplRefTypeMetadata.getType(), interfaceTypeJavaImplTypeMetadata.getType())) {
				IXArchTypeMetadata tm = xarch.getTypeMetadata(type);
				ObjRef contextRef = xarch.createContext(xArchRef, XArchMetadataUtils.getTypeContext(tm.getType()));
				xarch.promoteTo(contextRef, XArchMetadataUtils.getTypeName(tm.getType()), interfaceTypeImplRef);
			}

			//adds MainClass
			ObjRef interfaceTypeImplContextRef = xarch.createContext(xarch.getXArch(interfaceTypeImplRef),
					"javaimplementation");
			ObjRef interfaceTypeJavaImplRef = xarch.create(interfaceTypeImplContextRef, "JavaClassFile");
			xarch.set(interfaceTypeImplRef, "mainClass", interfaceTypeJavaImplRef);

			//adds JavaClassName
			ObjRef interfaceTypeJavaImplContextRef = xarch.createContext(xarch.getXArch(interfaceTypeJavaImplRef),
					"javaimplementation");
			ObjRef interfaceTypeJavaClassNameRef = xarch.create(interfaceTypeJavaImplContextRef, "JavaClassName");
			xarch.set(interfaceTypeJavaImplRef, "javaClassName", interfaceTypeJavaClassNameRef);
			xarch.set(interfaceTypeJavaClassNameRef, "value", brickInterface.getFQJavaInterfaceName());
		}
		//sets this interfaceType to signature
		String interfaceTypeId = XadlUtils.getID(xarch, interfaceTypeObjRef);
		XadlUtils.setXLink(xarch, signatureRef, "type", interfaceTypeId);

	}

	//	/**
	//	 * Updates lookup implementation value
	//	 * @param xarch
	//	 * @param signatureRef
	//	 * @param value
	//	 */
	//	private static void updateLookupImplValue(IXArchADT xarch, ObjRef signatureRef, String value) {
	//		ObjRef[] implementationRefs = xarch.getAll(signatureRef, "implementation");
	//		if (implementationRefs == null || implementationRefs.length == 0) {
	//			return;
	//		}
	//		ObjRef lookupImplementationRef = null;
	//		for (ObjRef element : implementationRefs) {
	//			if (xarch.isInstanceOf(element, "lookupimplementation#LookupImplementation")) {
	//				lookupImplementationRef = element;
	//				break;
	//			}
	//		}
	//		if (lookupImplementationRef == null) {
	//			return;
	//		}
	//		ObjRef lookupNameRef = (ObjRef) xarch.get(lookupImplementationRef, "name");
	//		if (lookupNameRef == null) {
	//			return;
	//		}
	//		xarch.set(lookupNameRef, "value", value);
	//	}
}
