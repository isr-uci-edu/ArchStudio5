package org.archstudio.myx.java.conn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxInterfaceDescription;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxJavaClassInterfaceDescription;
import org.archstudio.myx.fw.MyxUtils;

public class SynchronousProxyConnector extends AbstractMyxSimpleBrick implements IMyxDynamicBrick, InvocationHandler {

	public static final IMyxName REQUIRED_INTERFACE_NAME = MyxUtils.createName("out");
	public static final IMyxName PROVIDED_INTERFACE_NAME = MyxUtils.createName("in");

	protected Object trueServiceObject = null;
	protected Object proxyObject = null;

	public SynchronousProxyConnector() {
	}

	@Override
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
			String interfaceClassName = MyxUtils.getInitProperties(this).get("interfaceClassName" + i);
			if (interfaceClassName == null) {
				break;
			}
			interfaceClassNames.add(interfaceClassName);
			i++;
		}

		List<Class<?>> interfaceClassList = new ArrayList<Class<?>>();
		for (String interfaceClassName : interfaceClassNames) {
			try {
				Class<?> interfaceClass = Class.forName(interfaceClassName);
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

	@Override
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

	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(REQUIRED_INTERFACE_NAME)) {
			this.trueServiceObject = null;
			this.proxyObject = null;
		}
	}

	@Override
	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(PROVIDED_INTERFACE_NAME)) {
			return proxyObject;
		}
		return null;
	}

	@Override
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
			else {
				throw ite;
			}
		}
	}
}
