package org.archstudio.myx.fw;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.myx.fw.IMyxLifecycleProcessor.Operation;

public class MyxBasicRuntime implements IMyxRuntime {
	protected IMyxContainer mainContainer = new MyxContainer();

	protected final List<BrickLoaderEntry> brickLoaders = Collections
			.synchronizedList(new CopyOnWriteArrayList<BrickLoaderEntry>());
	protected final Map<IMyxBrickDescription, IMyxBrickFactory> brickDescriptionToFactoryMap = Collections
			.synchronizedMap(new HashMap<IMyxBrickDescription, IMyxBrickFactory>());
	protected final List<IMyxWeld> weldList = Collections.synchronizedList(new CopyOnWriteArrayList<IMyxWeld>());

	protected final MyxInterfaceRepository interfaceRepository = new MyxInterfaceRepository();

	protected MyxBasicRuntime() {
	}

	protected static class BrickLoaderEntry {
		private final IMyxName loaderName;
		private final IMyxBrickLoader loader;

		public BrickLoaderEntry(IMyxName loaderName, IMyxBrickLoader loader) {
			this.loaderName = loaderName;
			this.loader = loader;
		}

		public IMyxName getLoaderName() {
			return loaderName;
		}

		public IMyxBrickLoader getLoader() {
			return loader;
		}
	}

	public void addBrickLoader(IMyxName loaderName, Class<? extends IMyxBrickLoader> brickLoaderClass,
			Properties initParams) throws MyxBrickLoaderException {
		try {
			IMyxBrickLoader loader = null;
			if (initParams == null) {
				Constructor<?> constructor = brickLoaderClass.getConstructor(new Class[0]);
				Object o = constructor.newInstance(new Object[0]);
				loader = (IMyxBrickLoader) o;
			}
			else {
				Constructor<?> constructor = brickLoaderClass.getConstructor(new Class<?>[] { Properties.class });
				Object o = constructor.newInstance(new Object[] { initParams });
				loader = (IMyxBrickLoader) o;
			}
			loader.setRuntime(this);

			BrickLoaderEntry ble = new BrickLoaderEntry(loaderName, loader);
			brickLoaders.add(ble);
		}
		catch (NoSuchMethodException nsme) {
			throw new MyxBrickLoaderException(nsme);
		}
		catch (IllegalAccessException iae) {
			throw new MyxBrickLoaderException(iae);
		}
		catch (InstantiationException ie) {
			throw new MyxBrickLoaderException(ie);
		}
		catch (InvocationTargetException ite) {
			throw new MyxBrickLoaderException(ite);
		}
	}

	public void removeBrickLoader(IMyxName loaderName) {
		synchronized (brickLoaders) {
			for (BrickLoaderEntry ble : brickLoaders) {
				if (loaderName.equals(ble.loaderName)) {
					// This is OK because it's a CopyOnWriteArrayList
					brickLoaders.remove(ble);
				}
			}
		}
	}

	public Collection<? extends IMyxName> getBrickLoaderNames() {
		synchronized (brickLoaders) {
			List<IMyxName> nameList = new ArrayList<IMyxName>();
			for (BrickLoaderEntry ble : brickLoaders) {
				nameList.add(ble.loaderName);
			}
			return Collections.unmodifiableList(nameList);
		}
	}

	/* Non-interface method */
	public IMyxBrickLoader getBrickLoader(IMyxName name) {
		synchronized (brickLoaders) {
			for (BrickLoaderEntry ble : brickLoaders) {
				if (ble.loaderName.equals(name)) {
					return ble.loader;
				}
			}
			return null;
		}
	}

	/* Non-interface method */
	public Collection<? extends IMyxBrickLoader> getAllBrickLoaders() {
		synchronized (brickLoaders) {
			List<IMyxBrickLoader> loaderList = new ArrayList<IMyxBrickLoader>();
			for (BrickLoaderEntry ble : brickLoaders) {
				loaderList.add(ble.loader);
			}
			return Collections.unmodifiableList(loaderList);
		}
	}

	protected IMyxBrick createBrick(IMyxName brickName, IMyxBrickDescription brickDescription,
			IMyxBrickInitializationData initializationData) throws MyxBrickLoadException, MyxBrickCreationException {
		synchronized (brickDescriptionToFactoryMap) {
			IMyxBrickFactory factory = brickDescriptionToFactoryMap.get(brickDescription);
			if (factory == null) {
				List<Exception> exceptionList = new ArrayList<Exception>();

				for (IMyxBrickLoader brickLoader : getAllBrickLoaders()) {
					try {
						factory = brickLoader.load(brickDescription);
						if (factory != null) {
							break;
						}
					}
					catch (MyxBrickNotFoundException bnfe) {
						exceptionList.add(bnfe);
					}
					catch (MyxBrickLoadFailedException blfe) {
						exceptionList.add(blfe);
					}
					catch (MyxUnsupportedBrickDescriptionException ubde) {
						exceptionList.add(ubde);
					}
				}
				if (factory == null) {
					//we couldn't load the brick and create a factory
					throw new MyxBrickLoadException(brickName.getName(), "Can't load brick with any loader",
							exceptionList);
				}
			}
			//Now we have a factory
			IMyxBrick brick = factory.create(brickName, brickDescription, initializationData);

			return brick;
		}
	}

	public void addBrick(List<? extends IMyxName> path, IMyxName brickName, IMyxBrickDescription brickDescription,
			IMyxBrickInitializationData initializationData) throws MyxBrickLoadException, MyxBrickCreationException {
		IMyxContainer container = MyxUtils.resolvePath(mainContainer, path);
		if (container == null) {
			throw new MyxInvalidPathException(path);
		}
		IMyxBrick b = createBrick(brickName, brickDescription, initializationData);

		//Let's set up the brick items
		//First, the required service provider
		IMyxRequiredServiceProvider requiredServiceProvider = new MyxBasicRequiredServiceProvider();

		//Next, the interface manager
		IMyxInterfaceManager interfaceManager = new MyxBasicInterfaceManager(this, path, brickName);

		IMyxBrickItems brickItems = new MyxBasicBrickItems(brickName, requiredServiceProvider, interfaceManager,
				brickDescription, initializationData);
		b.setMyxBrickItems(brickItems);

		container.addInternalBrick(b);
	}

	public void removeBrick(List<? extends IMyxName> path, IMyxName brickName) {
		IMyxContainer container = MyxUtils.resolvePath(mainContainer, path);
		if (container == null) {
			throw new MyxInvalidPathException(path);
		}
		synchronized (container) {
			IMyxBrick b = container.getInternalBrick(brickName);
			if (b == null) {
				throw new IllegalArgumentException("No brick found with name: " + brickName + " at "
						+ MyxUtils.pathToString(path));
			}
			container.removeInternalBrick(b);
		}
	}

	public Collection<? extends IMyxName> getAllBrickNames(List<? extends IMyxName> path) {
		IMyxContainer container = MyxUtils.resolvePath(mainContainer, path);
		if (container == null) {
			throw new MyxInvalidPathException(path);
		}
		synchronized (container) {
			List<IMyxName> names = new ArrayList<IMyxName>();
			for (IMyxBrick internalBrick : container.getInternalBricks()) {
				names.add(internalBrick.getMyxBrickItems().getBrickName());
			}
			return names;
		}
	}

	public IMyxBrickDescription getBrickDescription(List<? extends IMyxName> path, IMyxName brickName) {
		IMyxContainer container = MyxUtils.resolvePath(mainContainer, path);
		if (container == null) {
			throw new MyxInvalidPathException(path);
		}
		IMyxBrick b = container.getInternalBrick(brickName);
		if (b == null) {
			throw new IllegalArgumentException("No brick found with name: " + brickName + " at "
					+ MyxUtils.pathToString(path));
		}

		return b.getMyxBrickItems().getBrickDescription();
	}

	public void addInterface(List<? extends IMyxName> path, IMyxName brickName, IMyxName interfaceName,
			IMyxInterfaceDescription interfaceDescription, EMyxInterfaceDirection interfaceDirection) {
		IMyxContainer container = MyxUtils.resolvePath(mainContainer, path);
		if (container == null) {
			throw new MyxInvalidPathException(path);
		}
		IMyxBrick b = container.getInternalBrick(brickName);
		if (b == null) {
			throw new IllegalArgumentException("No brick found with name: " + brickName + " at "
					+ MyxUtils.pathToString(path));
		}

		//Don't use this method to add container interfaces, which are mapped.
		if (b instanceof IMyxContainer) {
			throw new IllegalArgumentException("Use addContainerInterface(...) to add interfaces to container "
					+ brickName + " at " + MyxUtils.pathToString(path));
		}

		interfaceRepository.addInterface(b, interfaceName, interfaceDescription, interfaceDirection);
	}

	public void addContainerInterface(List<? extends IMyxName> path, IMyxName brickName, IMyxName interfaceName,
			IMyxInterfaceDescription interfaceDescription, EMyxInterfaceDirection interfaceDirection,
			IMyxName internalBrickName, IMyxName internalBrickInterfaceName) {
		IMyxContainer outercontainer = MyxUtils.resolvePath(mainContainer, path);
		if (outercontainer == null) {
			throw new MyxInvalidPathException(path);
		}
		IMyxBrick b = outercontainer.getInternalBrick(brickName);
		if (b == null) {
			throw new IllegalArgumentException("No brick found with name: " + brickName + " at "
					+ MyxUtils.pathToString(path));
		}
		if (!(b instanceof IMyxContainer)) {
			throw new IllegalArgumentException("Brick " + brickName + " at " + MyxUtils.pathToString(path)
					+ " should have been a container, but wasn't.");
		}
		IMyxContainer container = (IMyxContainer) b;

		IMyxBrick internalBrick = container.getInternalBrick(internalBrickName);
		if (internalBrick == null) {
			throw new IllegalArgumentException("No brick found with name: " + internalBrickName + " at "
					+ MyxUtils.pathToString(path) + "/" + brickName);
		}
		Collection<? extends IMyxName> internalInterfaceNames = interfaceRepository.getAllInterfaceNames(internalBrick);
		if (!internalInterfaceNames.contains(internalBrickInterfaceName)) {
			throw new IllegalArgumentException("No interface named: " + internalBrickInterfaceName + " on brick: "
					+ internalBrickName + " at " + MyxUtils.pathToString(path) + "/" + brickName);
		}

		interfaceRepository.addInterface(b, interfaceName, interfaceDescription, interfaceDirection, internalBrickName,
				internalBrickInterfaceName);
	}

	public void removeInterface(List<? extends IMyxName> path, IMyxName brickName, IMyxName interfaceName) {
		IMyxContainer container = MyxUtils.resolvePath(mainContainer, path);
		if (container == null) {
			throw new MyxInvalidPathException(path);
		}
		IMyxBrick b = container.getInternalBrick(brickName);
		if (b == null) {
			throw new IllegalArgumentException("No brick found with name: " + brickName + " at "
					+ MyxUtils.pathToString(path));
		}

		//TODO: Disallow the removal of interfaces that are involved in welds.
		interfaceRepository.removeInterface(b, interfaceName);
	}

	public Collection<? extends IMyxName> getAllInterfaceNames(List<? extends IMyxName> path, IMyxName brickName) {
		IMyxContainer container = MyxUtils.resolvePath(mainContainer, path);
		if (container == null) {
			throw new MyxInvalidPathException(path);
		}
		IMyxBrick b = container.getInternalBrick(brickName);
		if (b == null) {
			throw new IllegalArgumentException("No brick found with name: " + brickName + " at "
					+ MyxUtils.pathToString(path));
		}

		return interfaceRepository.getAllInterfaceNames(b);
	}

	public IMyxInterfaceDescription getInterfaceDescription(List<? extends IMyxName> path, IMyxName brickName,
			IMyxName interfaceName) {
		IMyxContainer container = MyxUtils.resolvePath(mainContainer, path);
		if (container == null) {
			throw new MyxInvalidPathException(path);
		}
		IMyxBrick b = container.getInternalBrick(brickName);
		if (b == null) {
			throw new IllegalArgumentException("No brick found with name: " + brickName + " at "
					+ MyxUtils.pathToString(path));
		}

		return interfaceRepository.getInterfaceDescription(b, interfaceName);
	}

	public EMyxInterfaceDirection getInterfaceDirection(List<? extends IMyxName> path, IMyxName brickName,
			IMyxName interfaceName) {
		IMyxContainer container = MyxUtils.resolvePath(mainContainer, path);
		if (container == null) {
			throw new MyxInvalidPathException(path);
		}
		IMyxBrick b = container.getInternalBrick(brickName);
		if (b == null) {
			throw new IllegalArgumentException("No brick found with name: " + brickName + " at "
					+ MyxUtils.pathToString(path));
		}

		return interfaceRepository.getInterfaceDirection(b, interfaceName);
	}

	public void init(List<? extends IMyxName> path, IMyxName brickName) {
		callLifecycleMethod(path, brickName, Operation.INIT);
	}

	public void begin(List<? extends IMyxName> path, IMyxName brickName) {
		callLifecycleMethod(path, brickName, Operation.BEGIN);
	}

	public void end(List<? extends IMyxName> path, IMyxName brickName) {
		callLifecycleMethod(path, brickName, Operation.END);
	}

	public void destroy(List<? extends IMyxName> path, IMyxName brickName) {
		callLifecycleMethod(path, brickName, Operation.DESTROY);
	}

	private void callLifecycleMethod(List<? extends IMyxName> path, IMyxName brickName, Operation method) {
		IMyxContainer container = MyxUtils.resolvePath(mainContainer, path);
		if (container == null) {
			throw new MyxInvalidPathException(path);
		}
		IMyxBrick b = null;
		if (brickName == null) {
			b = container;
		}
		else {
			b = container.getInternalBrick(brickName);
			if (b == null) {
				throw new IllegalArgumentException("No brick found with name: " + brickName + " at "
						+ MyxUtils.pathToString(path));
			}
		}

		for (IMyxLifecycleProcessor lp : b.getLifecycleProcessors()) {
			switch (method) {
			case INIT:
				lp.init();
				break;
			case BEGIN:
				lp.begin();
				break;
			case END:
				lp.end();
				break;
			case DESTROY:
				lp.destroy();
				break;
			}
		}

	}

	private static class MyxWeldData {
		public final List<? extends IMyxBrick> requiredBricks;
		public final List<? extends IMyxName> requiredInterfaces;
		public final List<? extends IMyxBrick> providedBricks;
		public final List<? extends IMyxName> providedInterfaces;

		public MyxWeldData(List<? extends IMyxBrick> requiredBricks, List<? extends IMyxName> requiredInterfaces,
				List<? extends IMyxBrick> providedBricks, List<? extends IMyxName> providedInterfaces) {
			this.requiredBricks = requiredBricks;
			this.requiredInterfaces = requiredInterfaces;
			this.providedBricks = providedBricks;
			this.providedInterfaces = providedInterfaces;
		}
	}

	private MyxWeldData parseAndValidateWeld(IMyxWeld weld) {
		synchronized (weldList) {
			IMyxContainer container1 = MyxUtils.resolvePath(mainContainer, weld.getRequiredPath());
			if (container1 == null) {
				throw new MyxInvalidPathException(weld.getRequiredPath());
			}
			IMyxBrick b1 = container1.getInternalBrick(weld.getRequiredBrickName());
			if (b1 == null) {
				throw new IllegalArgumentException("No brick found with name: " + weld.getRequiredBrickName() + " at "
						+ MyxUtils.pathToString(weld.getRequiredPath()));
			}
			IMyxName i1 = weld.getRequiredInterfaceName();
			Collection<? extends IMyxName> names = interfaceRepository.getAllInterfaceNames(b1);
			if (!names.contains(i1)) {
				throw new IllegalArgumentException("No interface found with name: " + i1 + " on brick "
						+ weld.getRequiredBrickName());
			}

			IMyxContainer container2 = MyxUtils.resolvePath(mainContainer, weld.getProvidedPath());
			if (container2 == null) {
				throw new MyxInvalidPathException(weld.getProvidedPath());
			}
			IMyxBrick b2 = container2.getInternalBrick(weld.getProvidedBrickName());
			if (b2 == null) {
				throw new IllegalArgumentException("No brick found with name: " + weld.getProvidedBrickName() + " at "
						+ MyxUtils.pathToString(weld.getRequiredPath()));
			}
			IMyxName i2 = weld.getProvidedInterfaceName();
			Collection<? extends IMyxName> names2 = interfaceRepository.getAllInterfaceNames(b2);
			if (!names2.contains(i2)) {
				throw new IllegalArgumentException("No interface found with name: " + i2 + " on brick "
						+ weld.getProvidedBrickName());
			}

			//Okay, we've got all the data set up.  Now we have to actually
			//hook up the interfaces.  If either the provided or required
			//side is a container, that means we have to follow mappings down
			//until we get to the real provider/requirer brick.

			//1 == required; 2 == provided
			IMyxBrick requiredBrick = b1;
			List<IMyxBrick> rb = Collections.singletonList(requiredBrick);
			IMyxName requiredInterface = i1;
			List<IMyxName> ri = Collections.singletonList(requiredInterface);
			while (requiredBrick instanceof IMyxContainer) {
				Collection<? extends IMyxName> internalBrickNames = interfaceRepository.getInternalBrickName(
						requiredBrick, requiredInterface);
				ri = new ArrayList<IMyxName>(interfaceRepository.getInternalInterfaceName(requiredBrick,
						requiredInterface));

				rb = new ArrayList<IMyxBrick>(internalBrickNames.size());
				for (IMyxName internalBrickName : internalBrickNames) {
					rb.add(((IMyxContainer) requiredBrick).getInternalBrick(internalBrickName));
				}
				requiredBrick = rb.iterator().next();
			}

			IMyxBrick providedBrick = b2;
			List<IMyxBrick> pb = Collections.singletonList(providedBrick);
			IMyxName providedInterface = i2;
			List<IMyxName> pi = Collections.singletonList(providedInterface);
			while (providedBrick instanceof IMyxContainer) {
				Collection<? extends IMyxName> internalBrickNames = interfaceRepository.getInternalBrickName(
						providedBrick, providedInterface);
				pi = new ArrayList<IMyxName>(interfaceRepository.getInternalInterfaceName(providedBrick,
						providedInterface));

				rb = new ArrayList<IMyxBrick>(internalBrickNames.size());
				for (IMyxName internalBrickName : internalBrickNames) {
					pb.add(((IMyxContainer) requiredBrick).getInternalBrick(internalBrickName));
				}
				providedBrick = pb.iterator().next();
			}

			//Wrap up the data
			MyxWeldData mwd = new MyxWeldData(rb, ri, pb, pi);
			return mwd;
		}
	}

	public IMyxWeld createWeld(List<? extends IMyxName> requiredPath, IMyxName requiredBrickName,
			IMyxName requiredInterfaceName, List<? extends IMyxName> providedPath, IMyxName providedBrickName,
			IMyxName providedInterfaceName) {
		return new MyxBasicWeld(requiredPath, requiredBrickName, requiredInterfaceName, providedPath,
				providedBrickName, providedInterfaceName);
	}

	public void addWeld(IMyxWeld weld) {
		synchronized (weldList) {
			MyxWeldData mwd = parseAndValidateWeld(weld);

			for (int i = 0; i < mwd.requiredBricks.size(); i++) {
				IMyxBrick requiredBrick = mwd.requiredBricks.get(i);
				IMyxBrickItems requiredBrickItems = requiredBrick.getMyxBrickItems();
				if (requiredBrickItems == null) {
					throw new RuntimeException("Brick " + MyxUtils.getName(requiredBrick) + " has no brick items.");
				}
				IMyxRequiredServiceProvider sp = requiredBrickItems.getRequiredServiceProvider();
				if (sp == null) {
					throw new RuntimeException("Brick " + MyxUtils.getName(requiredBrick)
							+ " has a null required service provider.");
				}
				if (!(sp instanceof MyxBasicRequiredServiceProvider)) {
					throw new RuntimeException("Brick " + MyxUtils.getName(requiredBrick)
							+ " has an invalid required service provider.");
				}
				MyxBasicRequiredServiceProvider bsp = (MyxBasicRequiredServiceProvider) sp;

				for (int j = 0; j < mwd.providedBricks.size(); j++) {
					IMyxBrick providedBrick = mwd.providedBricks.get(j);
					IMyxProvidedServiceProvider providedServiceProvider = providedBrick.getProvidedServiceProvider();
					Object providedServiceObject = providedServiceProvider.getServiceObject(mwd.providedInterfaces
							.get(j));
					bsp.addService(mwd.requiredInterfaces.get(i), providedServiceObject);
					//Notify the brick of the connection if it's dynamic
					if (mwd.requiredBricks.get(i) instanceof IMyxDynamicBrick) {
						((IMyxDynamicBrick) mwd.requiredBricks.get(i)).interfaceConnected(
								mwd.requiredInterfaces.get(i), providedServiceObject);
					}
				}
			}
			weldList.add(weld);
		}
	}

	public void removeWeld(IMyxWeld weld) {
		synchronized (weldList) {
			MyxWeldData mwd = parseAndValidateWeld(weld);

			for (int i = 0; i < mwd.requiredBricks.size(); i++) {
				IMyxBrickItems requiredBrickItems = mwd.requiredBricks.get(i).getMyxBrickItems();
				if (requiredBrickItems == null) {
					throw new RuntimeException("Brick " + MyxUtils.getName(mwd.requiredBricks.get(i))
							+ " has no brick items.");
				}
				IMyxRequiredServiceProvider sp = requiredBrickItems.getRequiredServiceProvider();
				if (sp == null) {
					throw new RuntimeException("Brick " + MyxUtils.getName(mwd.requiredBricks.get(i))
							+ " has a null required service provider.");
				}
				if (!(sp instanceof MyxBasicRequiredServiceProvider)) {
					throw new RuntimeException("Brick " + MyxUtils.getName(mwd.requiredBricks.get(i))
							+ " has an invalid required service provider.");
				}
				MyxBasicRequiredServiceProvider bsp = (MyxBasicRequiredServiceProvider) sp;
				for (int j = 0; j < mwd.providedBricks.size(); j++) {
					IMyxProvidedServiceProvider providedServiceProvider = mwd.providedBricks.get(j)
							.getProvidedServiceProvider();
					Object providedServiceObject = providedServiceProvider.getServiceObject(mwd.providedInterfaces
							.get(j));
					bsp.removeService(mwd.requiredInterfaces.get(i), providedServiceObject);
					//Notify the brick of the disconnection if it's dynamic
					if (mwd.requiredBricks.get(i) instanceof IMyxDynamicBrick) {
						((IMyxDynamicBrick) mwd.requiredBricks.get(i)).interfaceDisconnected(
								mwd.requiredInterfaces.get(i), providedServiceObject);
					}
				}
			}
			weldList.remove(weld);

		}
	}

	public Collection<? extends IMyxWeld> getAllWelds() {
		synchronized (weldList) {
			return Collections.unmodifiableList(new ArrayList<IMyxWeld>(weldList));
		}
	}

}
