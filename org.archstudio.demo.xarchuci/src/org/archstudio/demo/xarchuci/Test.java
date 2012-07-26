package org.archstudio.demo.xarchuci;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.archstudio.xadl3.structure_3_0.Component;
import org.archstudio.xadl3.structure_3_0.Connector;
import org.archstudio.xadl3.structure_3_0.Interface;
import org.archstudio.xadl3.structure_3_0.Link;
import org.archstudio.xadl3.structure_3_0.Structure;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Factory;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.XADLType;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Factory;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.core.XArchADTImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ElementHandlerImpl;
import org.eclipse.emf.ecore.xmi.impl.GenericXMLResourceFactoryImpl;

public class Test {

	public static void main(String[] args) throws IOException {

		// Inform EMF how to serialize the models that are created

		Resource.Factory.Registry registry = Resource.Factory.Registry.INSTANCE;
		registry.getExtensionToFactoryMap().put("xml", new GenericXMLResourceFactoryImpl());

		// Initialize EMF packages by simply referring to the eINSTANCE variables

		@SuppressWarnings("unused")
		EPackage p;
		p = Xadlcore_3_0Package.eINSTANCE;
		p = Structure_3_0Package.eINSTANCE;

		Test test = new Test();

		System.err.println(test.createUsingEMF());
		System.err.println(test.createUsingXArchADT());
	}

	/**
	 * The following three methods do the same thing, they all create an
	 * architecture that looks like the following:
	 * 
	 * <pre>
	 *    ================
	 *    || ComponentA ||
	 *    =======O========
	 *           |\
	 *       top | bottom
	 *          \|
	 *    -------O--------
	 *    |  Connector   |
	 *    -------O--------
	 *           |\
	 *       top | bottom
	 *          \|
	 *    =======O========
	 *    || ComponentB ||
	 *    ================
	 * </pre>
	 */

	/**
	 * Create the architecture using the xADL data binding library directly,
	 * i.e., using the EMF generated classes from Apigen.
	 */
	public String createUsingEMF() throws IOException {

		// Get references to the factories used to create objects

		Xadlcore_3_0Factory coreFactory = Xadlcore_3_0Factory.eINSTANCE;
		Structure_3_0Factory structureFactory = Structure_3_0Factory.eINSTANCE;

		// create a resource in which to store the document

		URI resourceURI = URI.createURI("urn://test/emf.xml");
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.createResource(resourceURI);

		// create component A and its interface

		Component componentA = structureFactory.createComponent();
		componentA.setId("ComponentA");
		componentA.setName("ComponentA");

		Interface componentABottom = structureFactory.createInterface();
		componentABottom.setId("ComponentABottom");
		componentABottom.setName("ComponentABottom");

		// create component B and its interface

		Component componentB = structureFactory.createComponent();
		componentB.setId("ComponentB");
		componentB.setName("ComponentB");

		Interface componentBTop = structureFactory.createInterface();
		componentBTop.setId("ComponentBTop");
		componentBTop.setName("ComponentBTop");

		// create the connector and its interfaces

		Connector connector = structureFactory.createConnector();
		connector.setId("Connector");
		connector.setName("Connector");

		Interface connectorBottom = structureFactory.createInterface();
		connectorBottom.setId("ConnectorBottom");
		connectorBottom.setName("ConnectorBottom");

		Interface connectorTop = structureFactory.createInterface();
		connectorTop.setId("ConnectorTop");
		connectorTop.setName("ConnectorTop");

		// create links in the structure

		Link topLink = structureFactory.createLink();
		topLink.setId("topLink");
		topLink.setName("topLink");
		topLink.setPoint1(componentABottom);
		topLink.setPoint2(connectorTop);

		Link bottomLink = structureFactory.createLink();
		bottomLink.setId("bottomLink");
		bottomLink.setName("bottomLink");
		bottomLink.setPoint1(connectorBottom);
		bottomLink.setPoint2(componentBTop);

		// create a structure to hold the architecture

		Structure structure = structureFactory.createStructure();
		structure.setId("Structure");

		// add the components, connector, and links to the structure

		structure.getComponent().add(componentA);
		structure.getComponent().add(componentB);
		structure.getConnector().add(connector);
		structure.getLink().add(topLink);
		structure.getLink().add(bottomLink);

		// create a document in which to hold the architecture structure

		XADLType xadlType = coreFactory.createXADLType();
		xadlType.getTopLevelElement().add(structure);
		resource.getContents().add(xadlType);

		// serialize the result

		Map<Object, Object> SAVE_OPTIONS_MAP = new HashMap<Object, Object>();
		SAVE_OPTIONS_MAP.put(XMLResource.OPTION_ELEMENT_HANDLER, new ElementHandlerImpl(false));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		resource.save(baos, SAVE_OPTIONS_MAP);
		return new String(baos.toByteArray());
	}

	/**
	 * Create the architecture using the IXArchADT flat interface.
	 */
	public String createUsingXArchADT() {

		/*
		 * Normally, this is provided through the XArchADT component, but we
		 * have to manually create it here.
		 */
		IXArchADT xarch = new XArchADTImpl();

		/*
		 * Note: from this point on, we will comment out the EMF-based version
		 * and include the equivalent IXArchADT version of the code.
		 */

		// Get references to the factories used to create objects
		/*
		 * Unlike when using EMF, we use the namespace URI's to refer to the
		 * factories in the flat interface.
		 */

		// Xadlcore_3_0Factory coreFactory = Xadlcore_3_0Factory.eINSTANCE;
		String coreNsUri = Xadlcore_3_0Package.eINSTANCE.getNsURI();
		// Structure_3_0Factory structureFactory = Structure_3_0Factory.eINSTANCE;
		String structureNsUri = Structure_3_0Package.eINSTANCE.getNsURI();

		// create a resource in which to store the document
		/*
		 * We create the resource through a call to the IXArchADT create method
		 */

		URI resourceURI = URI.createURI("urn://test/xarchadt.xml");
		// ResourceSet resourceSet = new ResourceSetImpl();
		// Resource resource = resourceSet.createResource(resourceURI);
		ObjRef resourceRef = xarch.createDocument(resourceURI);

		// create component A and its interface

		// Component componentA = structureFactory.createComponent();
		ObjRef componentARef = xarch.create(structureNsUri, "Component");
		// componentA.setId("ComponentA");
		xarch.set(componentARef, "id", "ComponentA");
		// componentA.setName("ComponentA");
		xarch.set(componentARef, "name", "ComponentA");

		// Interface componentABottom = structureFactory.createInterface();
		ObjRef componentABottomRef = xarch.create(structureNsUri, "Interface");
		// componentABottom.setId("ComponentABottom");
		xarch.set(componentABottomRef, "id", "ComponentABottom");
		// componentABottom.setName("ComponentABottom");
		xarch.set(componentABottomRef, "name", "ComponentABottom");

		// create component B and its interface

		//Component componentB = structureFactory.createComponent();
		ObjRef componentBRef = xarch.create(structureNsUri, "Component");
		//componentB.setId("ComponentB");
		xarch.set(componentBRef, "id", "ComponentB");
		//componentB.setName("ComponentB");
		xarch.set(componentBRef, "name", "ComponentB");

		//Interface componentBTop = structureFactory.createInterface();
		ObjRef componentBTopRef = xarch.create(structureNsUri, "Interface");
		//componentBTop.setId("ComponentBTop");
		xarch.set(componentBTopRef, "id", "ComponentBTop");
		//componentBTop.setName("ComponentBTop");
		xarch.set(componentBTopRef, "name", "ComponentBTop");

		// create the connector and its interfaces

		//Connector connector = structureFactory.createConnector();
		ObjRef connectorRef = xarch.create(structureNsUri, "Connector");
		//connector.setId("Connector");
		xarch.set(connectorRef, "id", "Connector");
		//connector.setName("Connector");
		xarch.set(connectorRef, "name", "Connector");

		//Interface connectorBottom = structureFactory.createInterface();
		ObjRef connectorBottomRef = xarch.create(structureNsUri, "Interface");
		//connectorBottom.setId("ConnectorBottom");
		xarch.set(connectorBottomRef, "id", "ConnectorBottom");
		//connectorBottom.setName("ConnectorBottom");
		xarch.set(connectorBottomRef, "name", "ConnectorBottom");

		//Interface connectorTop = structureFactory.createInterface();
		ObjRef connectorTopRef = xarch.create(structureNsUri, "Interface");
		//connectorTop.setId("ConnectorTop");
		xarch.set(connectorTopRef, "id", "ConnectorTop");
		//connectorTop.setName("ConnectorTop");
		xarch.set(connectorTopRef, "name", "ConnectorTop");

		// create links in the structure

		//Link topLink = structureFactory.createLink();
		ObjRef topLinkRef = xarch.create(structureNsUri, "Link");
		//topLink.setId("topLink");
		xarch.set(topLinkRef, "id", "topLink");
		//topLink.setName("topLink");
		xarch.set(topLinkRef, "name", "topLink");
		//topLink.setPoint1(componentABottom);
		xarch.set(topLinkRef, "point1", componentABottomRef);
		//topLink.setPoint2(connectorTop);
		xarch.set(topLinkRef, "point2", connectorTopRef);

		//Link bottomLink = structureFactory.createLink();
		ObjRef bottomLinkRef = xarch.create(structureNsUri, "Link");
		//bottomLink.setId("bottomLink");
		xarch.set(bottomLinkRef, "id", "bottomLink");
		//bottomLink.setName("bottomLink");
		xarch.set(bottomLinkRef, "name", "bottomLink");
		//bottomLink.setPoint1(connectorBottom);
		xarch.set(bottomLinkRef, "point1", connectorBottomRef);
		//bottomLink.setPoint2(componentBTop);
		xarch.set(bottomLinkRef, "point2", componentBTopRef);

		// create a structure to hold the architecture

		//Structure structure = structureFactory.createStructure();
		ObjRef structureRef = xarch.create(structureNsUri, "Structure");
		//structure.setId("Structure");
		xarch.set(structureRef, "id", "Structure");

		// add the components, connector, and links to the structure

		//structure.getComponent().add(componentA);
		xarch.add(structureRef, "component", componentARef);
		//structure.getComponent().add(componentB);
		xarch.add(structureRef, "component", componentBRef);
		//structure.getConnector().add(connector);
		xarch.add(structureRef, "connector", connectorRef);
		//structure.getLink().add(topLink);
		xarch.add(structureRef, "link", topLinkRef);
		//structure.getLink().add(bottomLink);
		xarch.add(structureRef, "link", bottomLinkRef);

		// create a document in which to hold the architecture structure

		//XADLType xadlType = coreFactory.createXADLType();
		ObjRef xadlTypeRef = xarch.create(coreNsUri, "XADLType");
		//xadlType.getTopLevelElement().add(structure);
		xarch.add(xadlTypeRef, "topLevelElement", structureRef);
		//resource.getContents().add(xadlType);
		xarch.set(resourceRef, "XADL", xadlTypeRef);

		// serialize the result

		//Map<Object, Object> SAVE_OPTIONS_MAP = new HashMap<Object, Object>();
		//SAVE_OPTIONS_MAP.put(XMLResource.OPTION_ELEMENT_HANDLER, new ElementHandlerImpl(false));
		//ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//resource.save(baos, SAVE_OPTIONS_MAP);
		return new String(xarch.serialize(resourceURI));
	}
}
