package org.archstudio.myx.conn;

import java.lang.reflect.*;
import java.util.*;

import java.util.concurrent.*;

import org.archstudio.myx.fw.*;

public class SynchronousMultiwayProxyConnector extends AbstractMyxSimpleBrick
		implements IMyxDynamicBrick, InvocationHandler, IMultiwayResults {

	public static final IMyxName REQUIRED_INTERFACE_NAME = MyxUtils
			.createName("out");
	public static final IMyxName PROVIDED_INTERFACE_NAME = MyxUtils
			.createName("in");
	public static final IMyxName RESULTS_INTERFACE_NAME = MyxUtils
			.createName("results");
	public static final IMyxName PROGRESS_INTERFACE_NAME = MyxUtils
			.createName("progress");

	protected List<Object> trueServiceObjects = new ArrayList<Object>();
	protected List<IMultiwayProgressListener> progressListeners = Collections
			.synchronizedList(new ArrayList<IMultiwayProgressListener>());

	protected Object proxyObject = null;

	protected Object[] returnValues = new Object[0];
	protected Throwable[] exceptions = new Throwable[0];

	protected ExecutorService asyncExecutor = null;

	public SynchronousMultiwayProxyConnector() {
	}

	public void init() {
		Set<String> interfaceClassNames = new HashSet<String>();

		IMyxInterfaceDescription miDesc = getMyxBrickItems()
				.getInterfaceManager().getInterfaceDescription(
						PROVIDED_INTERFACE_NAME);
		if (miDesc instanceof MyxJavaClassInterfaceDescription) {
			MyxJavaClassInterfaceDescription jmiDesc = (MyxJavaClassInterfaceDescription) miDesc;
			interfaceClassNames
					.addAll(jmiDesc.getServiceObjectInterfaceNames());
		}

		int i = 0;
		while (true) {
			String interfaceClassName = MyxUtils.getInitProperties(this)
					.getProperty("interfaceClassName" + i);
			if (interfaceClassName == null)
				break;
			interfaceClassNames.add(interfaceClassName);
			i++;
		}

		List<Class<?>> interfaceClassList = new ArrayList<Class<?>>();
		IMyxClassManager classManager = getMyxBrickItems().getClassManager();
		for (String interfaceClassName : interfaceClassNames) {
			try {
				Class<?> interfaceClass = classManager
						.classForName(interfaceClassName);
				interfaceClassList.add(interfaceClass);
			} catch (ClassNotFoundException cnfe) {
				throw new IllegalArgumentException(
						"Can't find interface class: " + cnfe.getMessage());
			}
		}

		Class<?>[] interfaceClasses = interfaceClassList
				.toArray(new Class[interfaceClassList.size()]);
		if (interfaceClasses.length > 0) {
			proxyObject = Proxy.newProxyInstance(
					interfaceClasses[0].getClassLoader(), interfaceClasses,
					this);
		}
		asyncExecutor = Executors.newSingleThreadExecutor();
	}

	public void end() {
		if (asyncExecutor != null) {
			asyncExecutor.shutdown();
			try {
				asyncExecutor.awaitTermination(5L, TimeUnit.SECONDS);
			} catch (InterruptedException ie) {
			}
		}
	}

	public synchronized void interfaceConnected(IMyxName interfaceName,
			Object serviceObject) {
		if (interfaceName.equals(REQUIRED_INTERFACE_NAME)) {
			this.trueServiceObjects.add(serviceObject);
			if (proxyObject == null) {
				ClassLoader cl = serviceObject.getClass().getClassLoader();
				Class<?>[] interfaceClasses = serviceObject.getClass()
						.getInterfaces();
				proxyObject = Proxy
						.newProxyInstance(cl, interfaceClasses, this);
			}
		} else if (interfaceName.equals(PROGRESS_INTERFACE_NAME)) {
			this.progressListeners
					.add((IMultiwayProgressListener) serviceObject);
		}
	}

	public synchronized void interfaceDisconnecting(IMyxName interfaceName,
			Object serviceObject) {
		if (interfaceName.equals(REQUIRED_INTERFACE_NAME)) {
			this.trueServiceObjects.remove(serviceObject);
			if (trueServiceObjects.size() == 0) {
				this.proxyObject = null;
			}
		} else if (interfaceName.equals(PROGRESS_INTERFACE_NAME)) {
			this.progressListeners.remove(serviceObject);
		}
	}

	public void interfaceDisconnected(IMyxName interfaceName,
			Object serviceObject) {
	}

	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(PROVIDED_INTERFACE_NAME)) {
			return proxyObject;
		} else if (interfaceName.equals(RESULTS_INTERFACE_NAME)) {
			return this;
		}
		return null;
	}

	public Object[] getReturnValues() {
		return returnValues;
	}

	public Throwable[] getExceptions() {
		return exceptions;
	}

	protected void reportCallProgress(int callee, int numCallees,
			Object returnValue, Throwable exception) {
		if (progressListeners.size() == 0) {
			return;
		}
		final int fcallee = callee;
		final int fnumCallees = numCallees;
		final Object freturnValue = returnValue;
		final Throwable fexception = exception;
		final IMultiwayProgressListener[] pls = progressListeners
				.toArray(new IMultiwayProgressListener[progressListeners.size()]);
		asyncExecutor.execute(new Runnable() {
			public void run() {
				for (int i = 0; i < pls.length; i++) {
					pls[i].callProgress(fcallee, fnumCallees, freturnValue,
							fexception);
				}
			}
		});
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if (proxyObject == null) {
			throw new RuntimeException("Disconnected proxy.");
		}
		if (trueServiceObjects.size() == 0) {
			throw new RuntimeException("Disconnected proxy.");
		} else {
			synchronized (this) {
				Object[] rvs = new Object[trueServiceObjects.size()];
				Throwable[] exs = new Throwable[trueServiceObjects.size()];
				int i = 0;
				reportCallProgress(0, trueServiceObjects.size(), null, null);
				for (Object trueServiceObject : trueServiceObjects) {
					try {
						rvs[i] = method.invoke(trueServiceObject, args);
						reportCallProgress(i + 1, trueServiceObjects.size(),
								rvs[i], null);
					} catch (Throwable t) {
						exs[i] = t;
						reportCallProgress(i + 1, trueServiceObjects.size(),
								null, exs[i]);
					}
					i++;
				}
				returnValues = rvs;
				exceptions = exs;
			}
		}
		return null;
	}
}
