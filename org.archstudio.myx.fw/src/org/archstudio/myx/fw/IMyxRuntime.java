package org.archstudio.myx.fw;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public interface IMyxRuntime {

	public static final List<IMyxName> EMPTY_NAME_LIST = Collections.emptyList();

	public void addBrickLoader(IMyxName loaderName, String className, Properties initParams) throws MyxBrickLoaderException;

	public void addClassManager(IMyxName loaderName, String className, Properties initParams) throws MyxClassManagerException;

	public void removeBrickLoader(IMyxName loaderName);

	public Collection<? extends IMyxName> getBrickLoaderNames();

	public void addBrick(List<? extends IMyxName> path, IMyxName brickName, IMyxBrickDescription brickDescription,
	        IMyxBrickInitializationData initializationData) throws MyxBrickLoadException, MyxBrickCreationException;

	public void removeBrick(List<? extends IMyxName> path, IMyxName brickName);

	public Collection<? extends IMyxName> getAllBrickNames(List<? extends IMyxName> path);

	public IMyxBrickDescription getBrickDescription(List<? extends IMyxName> path, IMyxName brickName);

	public void addInterface(List<? extends IMyxName> path, IMyxName brickName, IMyxName interfaceName, IMyxInterfaceDescription interfaceDescription,
	        EMyxInterfaceDirection interfaceDirection);

	public void addContainerInterface(List<? extends IMyxName> path, IMyxName brickName, IMyxName interfaceName, IMyxInterfaceDescription interfaceDescription,
	        EMyxInterfaceDirection interfaceDirection, IMyxName internalBrickName, IMyxName internalBrickInterfaceName);

	public void removeInterface(List<? extends IMyxName> path, IMyxName brickName, IMyxName interfaceName);

	public Collection<? extends IMyxName> getAllInterfaceNames(List<? extends IMyxName> path, IMyxName brickName);

	public IMyxInterfaceDescription getInterfaceDescription(List<? extends IMyxName> path, IMyxName brickName, IMyxName interfaceName);

	public EMyxInterfaceDirection getInterfaceDirection(List<? extends IMyxName> path, IMyxName brickName, IMyxName interfaceName);

	public void init(List<? extends IMyxName> path, IMyxName brickName);

	public void begin(List<? extends IMyxName> path, IMyxName brickName);

	public void end(List<? extends IMyxName> path, IMyxName brickName);

	public void destroy(List<? extends IMyxName> path, IMyxName brickName);

	public IMyxWeld createWeld(List<? extends IMyxName> requiredPath, IMyxName requiredBrickName, IMyxName requiredInterfaceName,
	        List<? extends IMyxName> providedPath, IMyxName providedBrickName, IMyxName providedInterfaceName);

	public void addWeld(IMyxWeld weld);

	public void removeWeld(IMyxWeld weld);

	public Collection<? extends IMyxWeld> getAllWelds();
}
