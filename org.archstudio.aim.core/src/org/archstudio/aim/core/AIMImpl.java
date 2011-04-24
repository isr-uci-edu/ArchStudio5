package org.archstudio.aim.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.archstudio.aim.ArchitectureInstantiationException;
import org.archstudio.aim.IAIM;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.myx.fw.EMyxInterfaceDirection;
import org.archstudio.myx.fw.IMyxBrickDescription;
import org.archstudio.myx.fw.IMyxBrickInitializationData;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.IMyxRuntime;
import org.archstudio.myx.fw.IMyxWeld;
import org.archstudio.myx.fw.MyxBasicBrickInitializationData;
import org.archstudio.myx.fw.MyxBrickCreationException;
import org.archstudio.myx.fw.MyxBrickLoadException;
import org.archstudio.myx.fw.MyxJavaClassBrickDescription;
import org.archstudio.myx.fw.MyxJavaClassInterfaceDescription;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Direction;

public class AIMImpl implements IAIM {
	protected IXArchADT xarch = null;
	protected IMyxRuntime myx;

	protected IMyxBrickDescription containerBrickDescription;

	public AIMImpl(IXArchADT xarch) {
		this.xarch = xarch;
		containerBrickDescription = MyxUtils.getContainerBrickDescription();
	}

	public synchronized void setXArch(IXArchADT xarch) {
		this.xarch = xarch;
	}

	public synchronized void setMyxRuntime(IMyxRuntime myxruntime) {
		this.myx = myxruntime;
	}

	public synchronized void instantiate(String name, ObjRef documentRootRef, ObjRef structureRef) throws ArchitectureInstantiationException {
		if (myx == null) {
			throw new IllegalStateException("AIMImpl has no Myx Runtime");
		}

		IMyxName containerName = MyxUtils.createName(name);

		try {
			myx.addBrick(IMyxRuntime.EMPTY_NAME_LIST, containerName, containerBrickDescription, null);
			instantiate(myx, documentRootRef, structureRef, Collections.singletonList(containerName));
		}
		catch (MyxBrickLoadException mble) {
			throw new ArchitectureInstantiationException("Myx cannot load brick", mble);
		}
		catch (MyxBrickCreationException mbce) {
			throw new ArchitectureInstantiationException("Myx cannot create brick", mbce);
		}
	}

	public synchronized void begin(String name) {
		if (myx == null) {
			throw new IllegalStateException("AIMImpl has no Myx Runtime");
		}

		IMyxName containerName = MyxUtils.createName(name);
		myx.begin(IMyxRuntime.EMPTY_NAME_LIST, containerName);
	}

	public synchronized void end(String name) {
		if (myx == null) {
			throw new IllegalStateException("AIMImpl has no Myx Runtime");
		}

		IMyxName containerName = MyxUtils.createName(name);
		myx.end(IMyxRuntime.EMPTY_NAME_LIST, containerName);
	}

	public synchronized void destroy(String name) {
		if (myx == null) {
			throw new IllegalStateException("AIMImpl has no Myx Runtime");
		}

		IMyxName containerName = MyxUtils.createName(name);
		try {
			myx.destroy(IMyxRuntime.EMPTY_NAME_LIST, containerName);
		}
		finally {
			myx.removeBrick(IMyxRuntime.EMPTY_NAME_LIST, containerName);
		}
	}

	private static class AIMInterfaceData {
		public EMyxInterfaceDirection myxDirection;
		public List<IMyxName> containerPath;
		public IMyxName brickName;
		public IMyxName interfaceName;
		public List<String> serviceObjectInterfaceNames;

		/* Following are not used for non-mapped interfaces */

		public IMyxName internalBrickName;
		public IMyxName internalBrickInterfaceName;
	}

	public void instantiate(IMyxRuntime myx, ObjRef xArchRef, ObjRef structureRef, List<IMyxName> containerPath) throws ArchitectureInstantiationException {
		// Let's do the components+connectors
		// This routine orders the bricks in dependency order
		List<ObjRef> brickRefs = AIMInstantiationOrderCalculator.calculateInstantiationOrder(xarch, structureRef);
		
		for (ObjRef brickRef : brickRefs) {
			System.err.println("should instantiate: " + xarch.get(brickRef, "name"));
		}
		
		// Iterate through array and instantiate.
		for (ObjRef brickRef : brickRefs) {
			String brickID = XadlUtils.getID(xarch, brickRef);
			if (brickID == null) {
				throw new ArchitectureInstantiationException("Brick missing ID: " + brickRef);
			}

			//System.err.println("Processing brick: " + XadlUtils.getName(xarch, brickRef));

			ObjRef subStructureRef = (ObjRef) xarch.get(brickRef, "subStructure");
			if (subStructureRef != null) {
				//Process subarchitecture
				//Create the container:
				IMyxName containerName = MyxUtils.createName(brickID);
				try {
					myx.addBrick(containerPath, containerName, containerBrickDescription, null);
				}
				catch (MyxBrickLoadException mble) {
					throw new ArchitectureInstantiationException("Myx cannot load brick", mble);
				}
				catch (MyxBrickCreationException mbce) {
					throw new ArchitectureInstantiationException("Myx cannot create brick", mbce);
				}

				ObjRef innerStructureRef = (ObjRef) xarch.get(subStructureRef, "innerStructureLink");
				if (innerStructureRef == null) {
					throw new ArchitectureInstantiationException("Invalid inner structure link on brick " + XadlUtils.getName(xarch, brickRef));
				}

				//This is easy enough; we just recurse.
				List<IMyxName> innerContainerPath = new ArrayList<IMyxName>(containerPath.size() + 1);
				innerContainerPath.addAll(containerPath);
				innerContainerPath.add(containerName);

				instantiate(myx, xArchRef, innerStructureRef, innerContainerPath);

				//Okay, the container is created and added; its inner structure is all
				//set up, now we have to go about creating and mapping all its interfaces.
				for (ObjRef interfaceRef : xarch.getAll(brickRef, "interface")) {
					AIMInterfaceData aid = parseAndValidateMappedInterfaceData(containerPath, brickRef, interfaceRef);
					MyxJavaClassInterfaceDescription aidDesc = new MyxJavaClassInterfaceDescription(aid.serviceObjectInterfaceNames);
					myx.addContainerInterface(aid.containerPath, aid.brickName, aid.interfaceName, aidDesc, aid.myxDirection, aid.internalBrickName,
					        aid.internalBrickInterfaceName);
				}
			}
			else {
				ObjRef javaImplementationRef = XadlUtils.getJavaImplementation(xarch, brickRef);
				if (javaImplementationRef == null) {
					throw new ArchitectureInstantiationException("Could not find Java implementation on brick " + XadlUtils.getName(xarch, brickRef));
				}
				ObjRef mainClassRef = (ObjRef) xarch.get(javaImplementationRef, "mainClass");
				if (mainClassRef == null) {
					throw new ArchitectureInstantiationException("Java implementation lacks main class on brick " + XadlUtils.getName(xarch, brickRef));
				}
				String mainClassName = (String) xarch.get(mainClassRef, "className");
				if (mainClassName == null) {
					throw new ArchitectureInstantiationException("Java implementation lacks main class name on brick " + XadlUtils.getName(xarch, brickRef));
				}
				//We have the main class name; let's get the properties (if any)
				IMyxBrickInitializationData initData = null;

				Properties initializationParameters = getProperties(mainClassRef);
				if (initializationParameters != null) {
					initData = new MyxBasicBrickInitializationData(initializationParameters);
				}

				IMyxBrickDescription myxBrickDescription = new MyxJavaClassBrickDescription(mainClassName);
				IMyxName myxBrickName = MyxUtils.createName(brickID);
				try {
					myx.addBrick(containerPath, myxBrickName, myxBrickDescription, initData);
				}
				catch (MyxBrickLoadException mble) {
					throw new ArchitectureInstantiationException("Myx cannot load brick", mble);
				}
				catch (MyxBrickCreationException mbce) {
					throw new ArchitectureInstantiationException("Myx cannot create brick", mbce);
				}

				//Okay, the brick is created and added; now we have to go about
				//creating all its interfaces.
				for (ObjRef interfaceRef : xarch.getAll(brickRef, "interface")) {
					AIMInterfaceData aid = parseAndValidateInterfaceData(containerPath, brickRef, interfaceRef);
					MyxJavaClassInterfaceDescription aidDesc = new MyxJavaClassInterfaceDescription(aid.serviceObjectInterfaceNames);
					myx.addInterface(aid.containerPath, aid.brickName, aid.interfaceName, aidDesc, aid.myxDirection);
				}
				//System.err.println("initing " + myxBrickName);
				myx.init(containerPath, myxBrickName);
			}
		}

		//Process the links
		for (ObjRef linkRef : xarch.getAll(structureRef, "link")) {
			ObjRef interface1Ref = (ObjRef) xarch.get(linkRef, "point1");
			if (interface1Ref == null) {
				throw new ArchitectureInstantiationException("Link " + XadlUtils.getName(xarch, linkRef) + " has invalid point 1 link.");
			}

			ObjRef interface2Ref = (ObjRef) xarch.get(linkRef, "point2");
			if (interface2Ref == null) {
				throw new ArchitectureInstantiationException("Link " + XadlUtils.getName(xarch, linkRef) + " has invalid point 2 link.");
			}

			Direction direction1 = (Direction) xarch.get(interface1Ref, "direction");
			if (direction1 == null) {
				throw new ArchitectureInstantiationException("Interface " + XadlUtils.getName(xarch, interface1Ref) + " missing direction.");
			}

			Direction direction2 = (Direction) xarch.get(interface2Ref, "direction");
			if (direction2 == null) {
				throw new ArchitectureInstantiationException("Interface " + XadlUtils.getName(xarch, interface1Ref) + " missing direction.");
			}

			if (!((direction1.equals(Direction.IN) && direction2.equals(Direction.OUT)) || (direction1.equals(Direction.OUT) && direction2.equals(Direction.IN)))) {
				throw new ArchitectureInstantiationException("Link " + XadlUtils.getName(xarch, linkRef)
				        + " links two interfaces with incompatible directions.");
			}

			ObjRef providedInterfaceRef = null;
			ObjRef requiredInterfaceRef = null;
			if (direction1.equals(Direction.IN)) {
				providedInterfaceRef = interface1Ref;
				requiredInterfaceRef = interface2Ref;
			}
			else {
				requiredInterfaceRef = interface1Ref;
				providedInterfaceRef = interface2Ref;
			}

			//Okay, we have both the provided and required interface refs.
			ObjRef providedBrickRef = xarch.getParent(providedInterfaceRef);
			if (providedBrickRef == null) {
				throw new ArchitectureInstantiationException("Interface " + XadlUtils.getName(xarch, providedInterfaceRef) + " missing parent.");
			}

			ObjRef requiredBrickRef = xarch.getParent(requiredInterfaceRef);
			if (requiredBrickRef == null) {
				throw new ArchitectureInstantiationException("Interface " + XadlUtils.getName(xarch, requiredInterfaceRef) + " missing parent.");
			}

			String providedBrickID = XadlUtils.getID(xarch, providedBrickRef);
			if (providedBrickID == null) {
				throw new ArchitectureInstantiationException("Brick " + XadlUtils.getName(xarch, providedBrickRef) + " missing ID.");
			}

			String requiredBrickID = XadlUtils.getID(xarch, requiredBrickRef);
			if (requiredBrickID == null) {
				throw new ArchitectureInstantiationException("Brick " + XadlUtils.getName(xarch, requiredBrickRef) + " missing ID.");
			}

			String providedInterfaceID = XadlUtils.getID(xarch, providedInterfaceRef);
			if (providedInterfaceID == null) {
				throw new ArchitectureInstantiationException("Interface " + XadlUtils.getName(xarch, providedInterfaceRef) + " on brick "
				        + XadlUtils.getName(xarch, providedBrickRef) + " missing ID.");
			}

			String requiredInterfaceID = XadlUtils.getID(xarch, requiredInterfaceRef);
			if (requiredInterfaceID == null) {
				throw new ArchitectureInstantiationException("Interface " + XadlUtils.getName(xarch, requiredInterfaceRef) + " on brick "
				        + XadlUtils.getName(xarch, requiredBrickRef) + " missing ID.");
			}

			ObjRef providedLookupImplementationRef = XadlUtils.getLookupImplementation(xarch, providedInterfaceRef);
			if (providedLookupImplementationRef == null) {
				throw new ArchitectureInstantiationException("Interface " + XadlUtils.getName(xarch, providedInterfaceRef) + " on brick "
				        + XadlUtils.getName(xarch, providedBrickRef) + " missing lookup implementation.");
			}

			ObjRef requiredLookupImplementationRef = XadlUtils.getLookupImplementation(xarch, requiredInterfaceRef);
			if (requiredLookupImplementationRef == null) {
				throw new ArchitectureInstantiationException("Interface " + XadlUtils.getName(xarch, requiredInterfaceRef) + " on brick "
				        + XadlUtils.getName(xarch, requiredBrickRef) + " missing lookup implementation.");
			}

			String providedLookupImplementationName = (String) xarch.get(providedLookupImplementationRef, "lookup");
			if (providedLookupImplementationName == null) {
				throw new ArchitectureInstantiationException("Interface " + XadlUtils.getName(xarch, providedInterfaceRef) + " on brick "
				        + XadlUtils.getName(xarch, providedBrickRef) + " missing lookup implementation info.");
			}

			String requiredLookupImplementationName = (String) xarch.get(requiredLookupImplementationRef, "lookup");
			if (requiredLookupImplementationName == null) {
				throw new ArchitectureInstantiationException("Interface " + XadlUtils.getName(xarch, requiredInterfaceRef) + " on brick "
				        + XadlUtils.getName(xarch, requiredBrickRef) + " missing lookup implementation info.");
			}

			IMyxWeld weld = myx.createWeld(
			/* Required */
			containerPath, MyxUtils.createName(requiredBrickID), MyxUtils.createName(requiredLookupImplementationName),
			/* Provided */
			containerPath, MyxUtils.createName(providedBrickID), MyxUtils.createName(providedLookupImplementationName));

			try {
				myx.addWeld(weld);
			}
			catch (Exception e) {
				throw new ArchitectureInstantiationException("Problem adding weld or invalid weld: " + weld.toString(), e);
			}
		}
	}

	private AIMInterfaceData parseAndValidateInterfaceData(List<IMyxName> containerPath, ObjRef brickRef, ObjRef interfaceRef)
	        throws ArchitectureInstantiationException {
		String interfaceID = XadlUtils.getID(xarch, interfaceRef);
		if (interfaceID == null) {
			throw new ArchitectureInstantiationException("Missing ID on interface: " + XadlUtils.getName(xarch, interfaceRef) + " on brick "
			        + XadlUtils.getName(xarch, brickRef));
		}

		//We have to get the implementation lookup name for the interface.
		ObjRef lookupImplementationRef = XadlUtils.getLookupImplementation(xarch, interfaceRef);
		if (lookupImplementationRef == null) {
			throw new ArchitectureInstantiationException("Missing lookup implementation on interface " + XadlUtils.getName(xarch, interfaceRef));
		}
		String lookupImplementationName = (String) xarch.get(lookupImplementationRef, "lookup");
		if (lookupImplementationName == null) {
			throw new ArchitectureInstantiationException("Missing lookup implementation name on interface " + XadlUtils.getName(xarch, interfaceRef));
		}

		Direction direction = (Direction) xarch.get(interfaceRef, "direction");
		if (direction == null) {
			throw new ArchitectureInstantiationException("Missing direction on interface " + XadlUtils.getName(xarch, interfaceRef));
		}
		EMyxInterfaceDirection myxDirection = null;
		if (direction.equals(Direction.IN)) {
			myxDirection = EMyxInterfaceDirection.IN;
		}
		else if (direction.equals(Direction.OUT)) {
			myxDirection = EMyxInterfaceDirection.OUT;
		}
		else {
			throw new ArchitectureInstantiationException("Invalid direction (not in/out) on interface " + XadlUtils.getName(xarch, interfaceRef));
		}

		List<String> serviceObjectInterfaceNames = new ArrayList<String>();

		ObjRef javaImplementationRef = XadlUtils.getJavaImplementation(xarch, interfaceRef);
		if (javaImplementationRef != null) {
			List<ObjRef> classRefs = new ArrayList<ObjRef>();
			ObjRef mainClassRef = (ObjRef) xarch.get(javaImplementationRef, "mainClass");
			if (mainClassRef != null) {
				classRefs.add(mainClassRef);
			}
			classRefs.addAll(xarch.getAll(javaImplementationRef, "auxClass"));

			for (ObjRef classRef : classRefs) {
				String className = (String) xarch.get(classRef, "className");
				if (className != null) {
					serviceObjectInterfaceNames.add(className);
				}
			}
		}

		AIMInterfaceData aid = new AIMInterfaceData();
		aid.myxDirection = myxDirection;
		aid.containerPath = containerPath;
		aid.brickName = MyxUtils.createName(XadlUtils.getID(xarch, brickRef));
		aid.interfaceName = MyxUtils.createName(lookupImplementationName);
		aid.serviceObjectInterfaceNames = serviceObjectInterfaceNames;

		return aid;
	}

	private AIMInterfaceData parseAndValidateMappedInterfaceData(List<IMyxName> containerPath, ObjRef brickRef, ObjRef interfaceRef)
	        throws ArchitectureInstantiationException {
		AIMInterfaceData aid = parseAndValidateInterfaceData(containerPath, brickRef, interfaceRef);

		ObjRef subStructureRef = (ObjRef) xarch.get(brickRef, "subStructure");
		if (subStructureRef == null) {
			throw new ArchitectureInstantiationException("Can't find subarchitecture for brick " + XadlUtils.getName(xarch, brickRef));
		}

		ObjRef innerStructureRef = (ObjRef) xarch.get(subStructureRef, "innerStructureLink");
		if (innerStructureRef == null) {
			throw new ArchitectureInstantiationException("Invalid inner structure link for brick " + XadlUtils.getName(xarch, brickRef));
		}

		List<ObjRef> interfaceMappingRefs = xarch.getAll(subStructureRef, "interfaceMapping");

		for (ObjRef interfaceMappingRef : interfaceMappingRefs) {
			ObjRef outerInterfaceRef = (ObjRef) xarch.get(interfaceMappingRef, "outerInterfaceLink");
			if (outerInterfaceRef == null) {
				throw new ArchitectureInstantiationException("Invalid outer interface link for mapping " + XadlUtils.getName(xarch, interfaceMappingRef)
				        + " on brick " + XadlUtils.getName(xarch, brickRef));
			}
			ObjRef innerInterfaceRef = (ObjRef) xarch.get(interfaceMappingRef, "innerInterfaceLink");
			if (innerInterfaceRef == null) {
				throw new ArchitectureInstantiationException("Invalid inner interface link for mapping " + XadlUtils.getName(xarch, interfaceMappingRef)
				        + " on brick " + XadlUtils.getName(xarch, brickRef));
			}

			if (xarch.equals(outerInterfaceRef, interfaceRef)) {
				//This is a mapping for our interface.
				ObjRef innerBrickRef = xarch.getParent(innerInterfaceRef);
				if (innerBrickRef == null) {
					throw new ArchitectureInstantiationException("Missing parent brick for interface " + XadlUtils.getName(xarch, innerInterfaceRef));
				}

				String innerBrickID = XadlUtils.getID(xarch, innerBrickRef);
				if (innerBrickID == null) {
					throw new ArchitectureInstantiationException("Missing ID on brick " + XadlUtils.getName(xarch, innerBrickRef));
				}

				ObjRef innerInterfaceLookupImplementationRef = XadlUtils.getLookupImplementation(xarch, innerInterfaceRef);
				if (innerInterfaceLookupImplementationRef == null) {
					throw new ArchitectureInstantiationException("Missing lookup implementation on interface " + XadlUtils.getName(xarch, innerInterfaceRef));
				}
				String innerInterfaceLookupImplementationName = (String) xarch.get(innerInterfaceLookupImplementationRef, "lookup");
				if (innerInterfaceLookupImplementationName == null) {
					throw new ArchitectureInstantiationException("Missing lookup implementation information on interface "
					        + XadlUtils.getName(xarch, innerInterfaceRef));
				}

				aid.internalBrickName = MyxUtils.createName(innerBrickID);
				aid.internalBrickInterfaceName = MyxUtils.createName(innerInterfaceLookupImplementationName);

				return aid;
			}

		}
		throw new ArchitectureInstantiationException("Could not find matching interface mapping for interface " + XadlUtils.getName(xarch, interfaceRef)
		        + " on brick " + XadlUtils.getName(xarch, brickRef));
	}

	private Properties getProperties(ObjRef javaClassRef) {
		List<ObjRef> initializationParamRefs = xarch.getAll(javaClassRef, "initializationParameter");
		if (initializationParamRefs.size() == 0) {
			return null;
		}
		Properties p = new Properties();
		for (ObjRef initializationParamRef : initializationParamRefs) {
			String name = (String) xarch.get(initializationParamRef, "name");
			String value = (String) xarch.get(initializationParamRef, "value");
			if ((name != null) && (value != null)) {
				p.put(name, value);
			}
		}
		return p;
	}
}
