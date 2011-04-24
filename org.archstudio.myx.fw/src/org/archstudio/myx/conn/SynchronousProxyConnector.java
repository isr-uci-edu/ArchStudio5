package org.archstudio.myx.conn;


import java.lang.reflect.*;
import java.util.*;

import org.archstudio.myx.fw.*;

public class SynchronousProxyConnector extends AbstractMyxSimpleBrick implements IMyxDynamicBrick, InvocationHandler {

	public static final IMyxName REQUIRED_INTERFACE_NAME = MyxUtils.createName("out");
	public static final IMyxName PROVIDED_INTERFACE_NAME = MyxUtils.createName("in");

	protected Object trueServiceObject = null;
	protected Object proxyObject = null;

	public SynchronousProxyConnector() {
	}

	public void init() {
		Set<String> interfaceClassNames = new HashSet<String>();

		IMyxInterfaceDescription miDesc = getMyxBrickItems().getInterfaceManager().getInterfaceDescription(PROVIDED_INTERFACE_NAME);
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

	}

	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(REQUIRED_INTERFACE_NAME)) {
			this.trueServiceObject = serviceObject;
			if (proxyObject == null) {
				ClassLoader cl = serviceObject.getClass().getClassLoader();
				Class<?>[] interfaceClasses = serviceObject.getClass().getInterfaces();
				proxyObject = Proxy.newProxyInstance(cl, interfaceClasses, this);
			}
		}
	}

	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(REQUIRED_INTERFACE_NAME)) {
			this.trueServiceObject = null;
			this.proxyObject = null;
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
			throw new RuntimeException("Disconnected proxy.");
		}
		if (trueServiceObject == null) {
			throw new RuntimeException("Disconnected proxy.");
		}
		try {
			return method.invoke(trueServiceObject, args);
		}
		catch (InvocationTargetException ite) {
			Throwable rootCause = ite.getCause();
			if (rootCause != null) {
				throw rootCause;
			}
			else
				throw ite;
		}
	}
}
