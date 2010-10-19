package org.archstudio.myx.fw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class MyxUtils {

	protected static final IMyxImplementation DEFAULT_MYX_IMPLEMENTATION = new MyxBasicImplementation();
	protected static final IMyxBrickDescription CONTAINER_BRICK_DESCRIPTION = new MyxJavaClassBrickDescription(MyxContainer.class.getName());
	public static final List<IMyxName> DEFAULT_PATH = Collections.emptyList();

	private MyxUtils() {
	}

	public static IMyxImplementation getDefaultImplementation() {
		return DEFAULT_MYX_IMPLEMENTATION;
	}

	public static IMyxName createName(String name) {
		MyxBasicName bn = new MyxBasicName(name);
		return bn;
	}

	public static IMyxBrickDescription getContainerBrickDescription() {
		return CONTAINER_BRICK_DESCRIPTION;
	}

	public static IMyxName getName(IMyxBrick brick) {
		if (brick == null) {
			return new MyxBasicName("[null-brick]");
		}
		IMyxBrickItems bi = brick.getMyxBrickItems();
		if (bi == null) {
			return new MyxBasicName("[null-brick-items]");
		}
		return bi.getBrickName();
	}

	public static boolean nulleq(Object o1, Object o2) {
		if (o1 == o2)
			return true;
		if ((o1 == null) && (o2 != null))
			return false;
		if ((o1 != null) && (o2 == null))
			return false;
		return o1.equals(o2);
	}

	public static int hc(Object o) {
		if (o == null)
			return 0;
		return o.hashCode();
	}

	public static boolean classeq(Object o1, Object o2) {
		return o1.getClass().equals(o2.getClass());
	}

	public static String pathToString(List<? extends IMyxName> path) {
		if (path == null) {
			return "/";
		}

		if (path.isEmpty()) {
			return "/";
		}
		StringBuffer sb = new StringBuffer();
		for (IMyxName pathElt : path) {
			sb.append("/");
			sb.append(pathElt);
		}
		return sb.toString();
	}

	public static IMyxContainer resolvePath(IMyxContainer rootContainer, List<? extends IMyxName> path) {
		if (path == null)
			return rootContainer;

		IMyxContainer currentContainer = rootContainer;
		for (IMyxName pathElt : path) {
			IMyxBrick internalBrick = currentContainer.getInternalBrick(pathElt);
			if ((internalBrick == null) || (!(internalBrick instanceof IMyxContainer))) {
				return null;
			}
			currentContainer = (IMyxContainer) internalBrick;
		}
		return currentContainer;
	}

	public static Class<?> classForName(String name, ClassLoader[] clArray) throws ClassNotFoundException {
		ClassNotFoundException lastCnfe = null;
		for (int i = 0; i < clArray.length; i++) {
			try {
				Class<?> c = Class.forName(name, true, clArray[i]);
				return c;
			}
			catch (ClassNotFoundException cnfe) {
				lastCnfe = cnfe;
			}
		}
		if (lastCnfe != null) {
			throw lastCnfe;
		}
		else {
			throw new ClassNotFoundException(name);
		}
	}

	public static Object getFirstRequiredServiceObject(IMyxBrick b, IMyxName interfaceName) {
		IMyxBrickItems brickItems = b.getMyxBrickItems();
		if (brickItems != null) {
			IMyxRequiredServiceProvider rsp = brickItems.getRequiredServiceProvider();
			Collection<? extends Object> svcs = rsp.getServiceObjects(interfaceName);
			if (!svcs.isEmpty()) {
				return svcs.iterator().next();
			}
		}
		return null;
	}

	public static Collection<? extends Object> getRequiredServiceObjects(IMyxBrick b, IMyxName interfaceName) {
		IMyxBrickItems brickItems = b.getMyxBrickItems();
		if (brickItems != null) {
			IMyxRequiredServiceProvider rsp = brickItems.getRequiredServiceProvider();
			return rsp.getServiceObjects(interfaceName);
		}
		return null;
	}

	public static Properties getInitProperties(IMyxBrick b) {
		IMyxBrickItems brickItems = b.getMyxBrickItems();
		if (brickItems != null) {
			IMyxBrickInitializationData d = brickItems.getInitializationData();
			if (d instanceof MyxBasicBrickInitializationData)
				return ((MyxBasicBrickInitializationData) d).getProperties();
		}
		return new java.util.Properties();
	}

	public static List<IMyxName> createPath(IMyxName... pathElements) {
		if (pathElements == null) {
			return Collections.emptyList();
		}
		return Collections.unmodifiableList(Arrays.asList(pathElements));
	}

	public static List<IMyxName> createPath(String... pathElements) {
		if (pathElements == null) {
			return Collections.emptyList();
		}
		List<IMyxName> path = new ArrayList<IMyxName>(pathElements.length);
		for (String pathElement : pathElements) {
			path.add(createName(pathElement));
		}
		return Collections.unmodifiableList(path);
	}
}
