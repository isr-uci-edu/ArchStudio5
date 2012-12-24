package org.archstudio.aim.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.archstudio.aim.ArchitectureInstantiationException;
import org.archstudio.aim.IAIM;
import org.archstudio.aim.core.AIMInstantiationOrderCalculator.OrderedGroup;
import org.archstudio.myx.fw.EMyxInterfaceDirection;
import org.archstudio.myx.fw.IMyxBrickDescription;
import org.archstudio.myx.fw.IMyxBrickInitializationData;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.IMyxProgressMonitor;
import org.archstudio.myx.fw.IMyxRuntime;
import org.archstudio.myx.fw.IMyxWeld;
import org.archstudio.myx.fw.MyxBasicBrickInitializationData;
import org.archstudio.myx.fw.MyxBrickCreationException;
import org.archstudio.myx.fw.MyxBrickLoadException;
import org.archstudio.myx.fw.MyxContainer;
import org.archstudio.myx.fw.MyxJavaClassInterfaceDescription;
import org.archstudio.myx.fw.MyxNullProgressMonitor;
import org.archstudio.myx.fw.MyxSubProgressMonitor;
import org.archstudio.myx.fw.MyxUnsupportedBrickDescriptionException;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.myx.java.MyxJavaClassBrickDescription;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.myx.IMyxBrickDescriptionFromXadl;
import org.archstudio.xadl.myx.MyxGenBrickDescriptionFromXadl;
import org.archstudio.xadl.myx.MyxJavaClassBrickDescriptionFromXadl;
import org.archstudio.xadl.myx.MyxOSGiBrickDescriptionFromXadl;
import org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package;
import org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package;
import org.archstudio.xadl3.lookupimplementation_3_0.Lookupimplementation_3_0Package;
import org.archstudio.xadl3.structure_3_0.Direction;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class AIMImpl implements IAIM {
	protected IXArchADT xarch = null;
	protected IMyxRuntime myx = null;
	protected IMyxBrickDescription containerDescription = new MyxJavaClassBrickDescription(MyxContainer.class.getName());

	public AIMImpl() {
	}

	public AIMImpl(IXArchADT xarch, IMyxRuntime myx) {
		this.xarch = xarch;
		this.myx = myx;
	}

	public synchronized void setXArch(IXArchADT xarch) {
		this.xarch = xarch;
	}

	public synchronized void setMyxRuntime(IMyxRuntime myxruntime) {
		this.myx = myxruntime;
	}

	public synchronized void instantiate(String name, ObjRef documentRootRef, ObjRef structureRef,
			IMyxProgressMonitor monitor) throws ArchitectureInstantiationException {
		if (myx == null) {
			throw new IllegalStateException("AIMImpl has no Myx Runtime");
		}

		IMyxName containerName = MyxUtils.createName(name);
		try {
			myx.addBrick(IMyxRuntime.EMPTY_NAME_LIST, containerName, containerDescription, null);
			instantiate(myx, documentRootRef, structureRef, Collections.singletonList(containerName), monitor);
		}
		catch (MyxUnsupportedBrickDescriptionException e) {
			throw new ArchitectureInstantiationException("Myx cannot load brick description", e);
		}
		catch (MyxBrickLoadException mble) {
			throw new ArchitectureInstantiationException("Myx cannot load brick", mble);
		}
		catch (MyxBrickCreationException mbce) {
			throw new ArchitectureInstantiationException("Myx cannot create brick", mbce);
		}
	}

	public synchronized void begin(String name, IMyxProgressMonitor monitor) {
		if (myx == null) {
			throw new IllegalStateException("AIMImpl has no Myx Runtime");
		}

		IMyxName containerName = MyxUtils.createName(name);
		myx.begin(IMyxRuntime.EMPTY_NAME_LIST, containerName);
	}

	public synchronized void end(String name, IMyxProgressMonitor monitor) {
		if (myx == null) {
			throw new IllegalStateException("AIMImpl has no Myx Runtime");
		}

		IMyxName containerName = MyxUtils.createName(name);
		myx.end(IMyxRuntime.EMPTY_NAME_LIST, containerName);
	}

	public synchronized void destroy(String name, IMyxProgressMonitor monitor) {
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

	public void instantiate(IMyxRuntime myx, ObjRef xArchRef, ObjRef structureRef, List<IMyxName> containerPath,
			IMyxProgressMonitor monitor) throws ArchitectureInstantiationException,
			MyxUnsupportedBrickDescriptionException {
		if (monitor == null) {
			monitor = new MyxNullProgressMonitor();
		}

		monitor.beginTask("Instantiating " + xarch.get(structureRef, "name"), 3);

		// Let's do the components+connectors
		// This routine orders the bricks in dependency order
		List<? extends OrderedGroup> orderedGroups = AIMInstantiationOrderCalculator.calculateInstantiationOrder(xarch,
				structureRef);
		monitor.worked(1);

		// Iterate through the list and instantiate.
		IMyxProgressMonitor brickGroupsMonitor = new MyxSubProgressMonitor(monitor, 1,
				MyxSubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
		brickGroupsMonitor.beginTask("Brick", orderedGroups.size());
		for (OrderedGroup group : orderedGroups) {
			for (ObjRef brickRef : group.getBrickRefs()) {
				brickGroupsMonitor.subTask((String) xarch.get(brickRef, "name"));
				String brickID = XadlUtils.getID(xarch, brickRef);
				if (brickID == null) {
					throw new ArchitectureInstantiationException("Brick missing ID: " + brickRef);
				}
				IMyxName myxBrickName = MyxUtils.createName(brickID);

				//System.err.println("Processing brick: " + XadlUtils.getName(xarch, brickRef));

				ObjRef subStructureRef = (ObjRef) xarch.get(brickRef, "subStructure");
				if (subStructureRef != null) {
					//Process subarchitecture
					//Create the container:
					IMyxName containerName = MyxUtils.createName(brickID);
					try {
						myx.addBrick(containerPath, containerName, containerDescription, null);
					}
					catch (MyxBrickLoadException mble) {
						throw new ArchitectureInstantiationException("Myx cannot load brick", mble);
					}
					catch (MyxBrickCreationException mbce) {
						throw new ArchitectureInstantiationException("Myx cannot create brick", mbce);
					}

					ObjRef innerStructureRef = (ObjRef) xarch.get(subStructureRef, "innerStructureLink");
					if (innerStructureRef == null) {
						throw new ArchitectureInstantiationException("Invalid inner structure link on brick "
								+ XadlUtils.getName(xarch, brickRef));
					}

					//This is easy enough; we just recurse.
					List<IMyxName> innerContainerPath = new ArrayList<IMyxName>(containerPath.size() + 1);
					innerContainerPath.addAll(containerPath);
					innerContainerPath.add(containerName);

					instantiate(myx, xArchRef, innerStructureRef, innerContainerPath, new MyxSubProgressMonitor(
							brickGroupsMonitor, 1));

					//Okay, the container is created and added; its inner structure is all
					//set up, now we have to go about creating and mapping all its interfaces.
					for (ObjRef interfaceRef : Iterables.filter(xarch.getAll(brickRef, "interface"), ObjRef.class)) {
						AIMInterfaceData aid = parseAndValidateMappedInterfaceData(containerPath, brickRef,
								interfaceRef);
						MyxJavaClassInterfaceDescription aidDesc = new MyxJavaClassInterfaceDescription(
								aid.serviceObjectInterfaceNames);
						myx.addContainerInterface(aid.containerPath, aid.brickName, aid.interfaceName, aidDesc,
								aid.myxDirection, aid.internalBrickName, aid.internalBrickInterfaceName);
					}
				}
				else {
					IMyxBrickInitializationData initData = null;
					ObjRef initializationParametersRef = XadlUtils.getImplementation(xarch, brickRef,
							Implementation_3_0Package.Literals.INITIALIZATION_PARAMETERS_IMPLEMENTATION);
					if (initializationParametersRef != null) {
						Map<String, String> initializationParameters = getProperties(initializationParametersRef);
						if (initializationParameters != null) {
							initData = new MyxBasicBrickInitializationData(initializationParameters);
						}
					}

					IMyxBrickDescription myxBrickDescription = null;
					List<IMyxBrickDescriptionFromXadl> implParsers = Lists.newArrayList();
					//TODO: use some extension mechanisms
					implParsers.add(new MyxGenBrickDescriptionFromXadl());
					implParsers.add(new MyxOSGiBrickDescriptionFromXadl());
					implParsers.add(new MyxJavaClassBrickDescriptionFromXadl());
					for (IMyxBrickDescriptionFromXadl implParser : implParsers) {
						myxBrickDescription = implParser.parse(xarch, brickRef);
						if (myxBrickDescription != null) {
							break;
						}
					}

					if (myxBrickDescription == null) {
						throw new ArchitectureInstantiationException("Myx cannot parse brick implementation");
					}

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
					for (ObjRef interfaceRef : Iterables.filter(xarch.getAll(brickRef, "interface"), ObjRef.class)) {
						AIMInterfaceData aid = parseAndValidateInterfaceData(containerPath, brickRef, interfaceRef);
						MyxJavaClassInterfaceDescription aidDesc = new MyxJavaClassInterfaceDescription(
								aid.serviceObjectInterfaceNames);
						myx.addInterface(aid.containerPath, aid.brickName, aid.interfaceName, aidDesc, aid.myxDirection);
					}
					//System.err.println("initing " + myxBrickName);
					myx.init(containerPath, myxBrickName);
				}
			}
			brickGroupsMonitor.worked(1);
		}
		brickGroupsMonitor.done();

		//Process the links
		IMyxProgressMonitor linkGroupsMonitor = new MyxSubProgressMonitor(monitor, 1,
				MyxSubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
		linkGroupsMonitor.beginTask("Links", orderedGroups.size());
		for (OrderedGroup group : orderedGroups) {
			for (ObjRef linkRef : group.getLinkRefs()) {
				linkGroupsMonitor.subTask((String) xarch.get(linkRef, "name"));
				ObjRef interface1Ref = (ObjRef) xarch.get(linkRef, "point1");
				if (interface1Ref == null) {
					throw new ArchitectureInstantiationException("Link " + XadlUtils.getName(xarch, linkRef)
							+ " has invalid point 1 link.");
				}

				ObjRef interface2Ref = (ObjRef) xarch.get(linkRef, "point2");
				if (interface2Ref == null) {
					throw new ArchitectureInstantiationException("Link " + XadlUtils.getName(xarch, linkRef)
							+ " has invalid point 2 link.");
				}

				Direction direction1 = (Direction) xarch.get(interface1Ref, "direction");
				if (direction1 == null) {
					throw new ArchitectureInstantiationException("Interface " + XadlUtils.getName(xarch, interface1Ref)
							+ " missing direction.");
				}

				Direction direction2 = (Direction) xarch.get(interface2Ref, "direction");
				if (direction2 == null) {
					throw new ArchitectureInstantiationException("Interface " + XadlUtils.getName(xarch, interface1Ref)
							+ " missing direction.");
				}

				if (!(direction1.equals(Direction.IN) && direction2.equals(Direction.OUT) || direction1
						.equals(Direction.OUT) && direction2.equals(Direction.IN))) {
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
					throw new ArchitectureInstantiationException("Interface "
							+ XadlUtils.getName(xarch, providedInterfaceRef) + " missing parent.");
				}

				ObjRef requiredBrickRef = xarch.getParent(requiredInterfaceRef);
				if (requiredBrickRef == null) {
					throw new ArchitectureInstantiationException("Interface "
							+ XadlUtils.getName(xarch, requiredInterfaceRef) + " missing parent.");
				}

				String providedBrickID = XadlUtils.getID(xarch, providedBrickRef);
				if (providedBrickID == null) {
					throw new ArchitectureInstantiationException("Brick " + XadlUtils.getName(xarch, providedBrickRef)
							+ " missing ID.");
				}

				String requiredBrickID = XadlUtils.getID(xarch, requiredBrickRef);
				if (requiredBrickID == null) {
					throw new ArchitectureInstantiationException("Brick " + XadlUtils.getName(xarch, requiredBrickRef)
							+ " missing ID.");
				}

				String providedInterfaceID = XadlUtils.getID(xarch, providedInterfaceRef);
				if (providedInterfaceID == null) {
					throw new ArchitectureInstantiationException("Interface "
							+ XadlUtils.getName(xarch, providedInterfaceRef) + " on brick "
							+ XadlUtils.getName(xarch, providedBrickRef) + " missing ID.");
				}

				String requiredInterfaceID = XadlUtils.getID(xarch, requiredInterfaceRef);
				if (requiredInterfaceID == null) {
					throw new ArchitectureInstantiationException("Interface "
							+ XadlUtils.getName(xarch, requiredInterfaceRef) + " on brick "
							+ XadlUtils.getName(xarch, requiredBrickRef) + " missing ID.");
				}

				ObjRef providedLookupImplementationRef = XadlUtils.getImplementation(xarch, providedInterfaceRef,
						Lookupimplementation_3_0Package.Literals.LOOKUP_IMPLEMENTATION);
				if (providedLookupImplementationRef == null) {
					throw new ArchitectureInstantiationException("Interface "
							+ XadlUtils.getName(xarch, providedInterfaceRef) + " on brick "
							+ XadlUtils.getName(xarch, providedBrickRef) + " missing lookup implementation.");
				}

				ObjRef requiredLookupImplementationRef = XadlUtils.getImplementation(xarch, requiredInterfaceRef,
						Lookupimplementation_3_0Package.Literals.LOOKUP_IMPLEMENTATION);
				if (requiredLookupImplementationRef == null) {
					throw new ArchitectureInstantiationException("Interface "
							+ XadlUtils.getName(xarch, requiredInterfaceRef) + " on brick "
							+ XadlUtils.getName(xarch, requiredBrickRef) + " missing lookup implementation.");
				}

				String providedLookupImplementationName = (String) xarch.get(providedLookupImplementationRef, "lookup");
				if (providedLookupImplementationName == null) {
					throw new ArchitectureInstantiationException("Interface "
							+ XadlUtils.getName(xarch, providedInterfaceRef) + " on brick "
							+ XadlUtils.getName(xarch, providedBrickRef) + " missing lookup implementation info.");
				}

				String requiredLookupImplementationName = (String) xarch.get(requiredLookupImplementationRef, "lookup");
				if (requiredLookupImplementationName == null) {
					throw new ArchitectureInstantiationException("Interface "
							+ XadlUtils.getName(xarch, requiredInterfaceRef) + " on brick "
							+ XadlUtils.getName(xarch, requiredBrickRef) + " missing lookup implementation info.");
				}

				IMyxWeld weld = myx.createWeld(
				/* Required */
				containerPath, MyxUtils.createName(requiredBrickID),
						MyxUtils.createName(requiredLookupImplementationName),
						/* Provided */
						containerPath, MyxUtils.createName(providedBrickID),
						MyxUtils.createName(providedLookupImplementationName));

				try {
					myx.addWeld(weld);
				}
				catch (Exception e) {
					throw new ArchitectureInstantiationException("Problem adding weld or invalid weld: "
							+ weld.toString(), e);
				}
			}
			linkGroupsMonitor.worked(1);
		}
		linkGroupsMonitor.done();

		monitor.done();
	}

	private AIMInterfaceData parseAndValidateInterfaceData(List<IMyxName> containerPath, ObjRef brickRef,
			ObjRef interfaceRef) throws ArchitectureInstantiationException {
		String interfaceID = XadlUtils.getID(xarch, interfaceRef);
		if (interfaceID == null) {
			throw new ArchitectureInstantiationException("Missing ID on interface: "
					+ XadlUtils.getName(xarch, interfaceRef) + " on brick " + XadlUtils.getName(xarch, brickRef));
		}

		//We have to get the implementation lookup name for the interface.
		ObjRef lookupImplementationRef = XadlUtils.getImplementation(xarch, interfaceRef,
				Lookupimplementation_3_0Package.Literals.LOOKUP_IMPLEMENTATION);
		if (lookupImplementationRef == null) {
			throw new ArchitectureInstantiationException("Missing lookup implementation on interface "
					+ XadlUtils.getName(xarch, interfaceRef));
		}
		String lookupImplementationName = (String) xarch.get(lookupImplementationRef, "lookup");
		if (lookupImplementationName == null) {
			throw new ArchitectureInstantiationException("Missing lookup implementation name on interface "
					+ XadlUtils.getName(xarch, interfaceRef));
		}

		Direction direction = (Direction) xarch.get(interfaceRef, "direction");
		if (direction == null) {
			throw new ArchitectureInstantiationException("Missing direction on interface "
					+ XadlUtils.getName(xarch, interfaceRef));
		}
		EMyxInterfaceDirection myxDirection = null;
		if (direction.equals(Direction.IN)) {
			myxDirection = EMyxInterfaceDirection.IN;
		}
		else if (direction.equals(Direction.OUT)) {
			myxDirection = EMyxInterfaceDirection.OUT;
		}
		else {
			throw new ArchitectureInstantiationException("Invalid direction (not in/out) on interface "
					+ XadlUtils.getName(xarch, interfaceRef));
		}

		List<String> serviceObjectInterfaceNames = new ArrayList<String>();

		ObjRef javaImplementationRef = XadlUtils.getImplementation(xarch, interfaceRef,
				Javaimplementation_3_0Package.Literals.JAVA_IMPLEMENTATION);
		if (javaImplementationRef != null) {
			List<ObjRef> classRefs = new ArrayList<ObjRef>();
			ObjRef mainClassRef = (ObjRef) xarch.get(javaImplementationRef, "mainClass");
			if (mainClassRef != null) {
				classRefs.add(mainClassRef);
			}
			classRefs.addAll(Lists.newArrayList(Iterables.filter(xarch.getAll(javaImplementationRef, "auxClass"),
					ObjRef.class)));

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

	private AIMInterfaceData parseAndValidateMappedInterfaceData(List<IMyxName> containerPath, ObjRef brickRef,
			ObjRef interfaceRef) throws ArchitectureInstantiationException {
		AIMInterfaceData aid = parseAndValidateInterfaceData(containerPath, brickRef, interfaceRef);

		ObjRef subStructureRef = (ObjRef) xarch.get(brickRef, "subStructure");
		if (subStructureRef == null) {
			throw new ArchitectureInstantiationException("Can't find subarchitecture for brick "
					+ XadlUtils.getName(xarch, brickRef));
		}

		ObjRef innerStructureRef = (ObjRef) xarch.get(subStructureRef, "innerStructureLink");
		if (innerStructureRef == null) {
			throw new ArchitectureInstantiationException("Invalid inner structure link for brick "
					+ XadlUtils.getName(xarch, brickRef));
		}

		for (ObjRef interfaceMappingRef : Iterables.filter(xarch.getAll(subStructureRef, "interfaceMapping"),
				ObjRef.class)) {
			ObjRef outerInterfaceRef = (ObjRef) xarch.get(interfaceMappingRef, "outerInterfaceLink");
			if (outerInterfaceRef == null) {
				throw new ArchitectureInstantiationException("Invalid outer interface link for mapping "
						+ XadlUtils.getName(xarch, interfaceMappingRef) + " on brick "
						+ XadlUtils.getName(xarch, brickRef));
			}
			ObjRef innerInterfaceRef = (ObjRef) xarch.get(interfaceMappingRef, "innerInterfaceLink");
			if (innerInterfaceRef == null) {
				throw new ArchitectureInstantiationException("Invalid inner interface link for mapping "
						+ XadlUtils.getName(xarch, interfaceMappingRef) + " on brick "
						+ XadlUtils.getName(xarch, brickRef));
			}

			if (outerInterfaceRef.equals(interfaceRef)) {
				//This is a mapping for our interface.
				ObjRef innerBrickRef = xarch.getParent(innerInterfaceRef);
				if (innerBrickRef == null) {
					throw new ArchitectureInstantiationException("Missing parent brick for interface "
							+ XadlUtils.getName(xarch, innerInterfaceRef));
				}

				String innerBrickID = XadlUtils.getID(xarch, innerBrickRef);
				if (innerBrickID == null) {
					throw new ArchitectureInstantiationException("Missing ID on brick "
							+ XadlUtils.getName(xarch, innerBrickRef));
				}

				ObjRef innerInterfaceLookupImplementationRef = XadlUtils.getImplementation(xarch, innerInterfaceRef,
						Lookupimplementation_3_0Package.Literals.LOOKUP_IMPLEMENTATION);
				if (innerInterfaceLookupImplementationRef == null) {
					throw new ArchitectureInstantiationException("Missing lookup implementation on interface "
							+ XadlUtils.getName(xarch, innerInterfaceRef));
				}
				String innerInterfaceLookupImplementationName = (String) xarch.get(
						innerInterfaceLookupImplementationRef, "lookup");
				if (innerInterfaceLookupImplementationName == null) {
					throw new ArchitectureInstantiationException(
							"Missing lookup implementation information on interface "
									+ XadlUtils.getName(xarch, innerInterfaceRef));
				}

				aid.internalBrickName = MyxUtils.createName(innerBrickID);
				aid.internalBrickInterfaceName = MyxUtils.createName(innerInterfaceLookupImplementationName);

				return aid;
			}

		}
		throw new ArchitectureInstantiationException("Could not find matching interface mapping for interface "
				+ XadlUtils.getName(xarch, interfaceRef) + " on brick " + XadlUtils.getName(xarch, brickRef));
	}

	private Map<String, String> getProperties(ObjRef initializationParametersRef) {
		Map<String, String> p = Maps.newHashMap();
		for (ObjRef initializationParamRef : Iterables.filter(
				xarch.getAll(initializationParametersRef, "initializationParameter"), ObjRef.class)) {
			String name = (String) xarch.get(initializationParamRef, "key");
			String value = (String) xarch.get(initializationParamRef, "value");
			if (name != null && value != null) {
				p.put(name, value);
			}
		}
		return p;
	}
}
