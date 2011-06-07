package org.archstudio.myx.conn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxClassManager;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxInterfaceDescription;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxJavaClassInterfaceDescription;
import org.archstudio.myx.fw.MyxUtils;

public class EventPumpConnector extends AbstractMyxSimpleBrick implements IMyxDynamicBrick, InvocationHandler {

	public static final IMyxName REQUIRED_INTERFACE_NAME = MyxUtils.createName("out");
	public static final IMyxName PROVIDED_INTERFACE_NAME = MyxUtils.createName("in");

	protected Object[] trueServiceObjects = new Object[0];
	protected Object proxyObject = null;

	protected ExecutorService asyncExecutor = null;

	public EventPumpConnector() {
	}

	public void init() {
		Set<String> interfaceClassNames = new HashSet<String>();

		IMyxInterfaceDescription miDesc = getMyxBrickItems().getInterfaceManager().getInterfaceDescription(
				PROVIDED_INTERFACE_NAME);
		if (miDesc instanceof MyxJavaClassInterfaceDescription) {
			MyxJavaClassInterfaceDescription jmiDesc = (MyxJavaClassInterfaceDescription) miDesc;
			interfaceClassNames.addAll(jmiDesc.getServiceObjectInterfaceNames());
		}

		int i = 0;
		while (true) {
			String interfaceClassName = MyxUtils.getInitProperties(this).getProperty("interfaceClassName" + i);
			if (interfaceClassName == null)
				break;
			interfaceClassNames.add(interfaceClassName);
			i++;
		}

		List<Class<?>> interfaceClassList = new ArrayList<Class<?>>();
		IMyxClassManager classManager = getMyxBrickItems().getClassManager();
		for (String interfaceClassName : interfaceClassNames) {
			try {
				Class<?> interfaceClass = classManager.classForName(interfaceClassName);
				interfaceClassList.add(interfaceClass);
			}
			catch (ClassNotFoundException cnfe) {
				throw new IllegalArgumentException("Can't find interface class: " + cnfe.getMessage());
			}
		}

		Class<?>[] interfaceClasses = interfaceClassList.toArray(new Class[interfaceClassList.size()]);
		if (interfaceClasses.length > 0) {
			proxyObject = Proxy.newProxyInstance(interfaceClasses[0].getClassLoader(), interfaceClasses, this);
		}
		asyncExecutor = Executors.newSingleThreadExecutor();
	}

	public void end() {
		if (asyncExecutor != null) {
			asyncExecutor.shutdown();
			try {
				asyncExecutor.awaitTermination(5L, TimeUnit.SECONDS);
			}
			catch (InterruptedException ie) {
			}
		}
	}

	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(REQUIRED_INTERFACE_NAME)) {
			synchronized (this) {
				List<Object> l = new ArrayList<Object>(Arrays.asList(trueServiceObjects));
				l.add(serviceObject);
				trueServiceObjects = l.toArray(new Object[l.size()]);
			}

			if (proxyObject == null) {
				ClassLoader cl = serviceObject.getClass().getClassLoader();
				Class<?>[] interfaceClasses = serviceObject.getClass().getInterfaces();
				proxyObject = Proxy.newProxyInstance(cl, interfaceClasses, this);
			}
		}
	}

	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(REQUIRED_INTERFACE_NAME)) {
			synchronized (this) {
				List<Object> l = new ArrayList<Object>(Arrays.asList(trueServiceObjects));
				l.remove(serviceObject);
				trueServiceObjects = l.toArray(new Object[l.size()]);
				if (trueServiceObjects.length == 0) {
					trueServiceObjects = null;
				}
			}
		}
	}

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(PROVIDED_INTERFACE_NAME)) {
			return proxyObject;
		}
		return null;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (proxyObject == null) {
			//Async messages do not have to get delivered.
			return null;
		}
		if ((trueServiceObjects == null) || (trueServiceObjects.length == 0)) {
			//Async messages do not have to get delivered.
			return null;
		}
		Object[] tsos = trueServiceObjects;
		for (int i = 0; i < tsos.length; i++) {
			final Object tso = tsos[i];
			final Method m = method;
			final Object[] a = args;
			Runnable r = new Runnable() {
				public void run() {
					try {
						m.invoke(tso, a);
					}
					catch (IllegalAccessException iae) {
						iae.printStackTrace();
						return;
					}
					catch (InvocationTargetException ite) {
						ite.printStackTrace();
						return;
					}
				}
			};

			asyncExecutor.execute(r);
		}
		//We don't return values from async calls
		return null;
	}

}
