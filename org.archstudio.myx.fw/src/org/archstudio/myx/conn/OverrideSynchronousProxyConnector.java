package org.archstudio.myx.conn;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

import org.archstudio.myx.fw.IMyxClassManager;
import org.archstudio.myx.fw.IMyxInterfaceDescription;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxJavaClassInterfaceDescription;
import org.archstudio.myx.fw.MyxUtils;


public class OverrideSynchronousProxyConnector extends SynchronousProxyConnector {

	public static final IMyxName REQUIRED_INTERFACE_NAME = MyxUtils.createName("out");
	public static final IMyxName PROVIDED_INTERFACE_NAME = MyxUtils.createName("in");
	public static final IMyxName OVERRIDE_INTERFACE_NAME = MyxUtils.createName("override");

	Set<Class<?>> providedClasses;
	Set<Class<?>> overrideClasses;

	protected Object trueServiceObject = null;
	protected Object overrideServiceObject = null;
	protected Object proxyObject = null;

	public OverrideSynchronousProxyConnector() {
	}

	@Override
	public void init() {
		providedClasses = getInterfaceClasses(PROVIDED_INTERFACE_NAME);
		overrideClasses = getInterfaceClasses(OVERRIDE_INTERFACE_NAME);
		proxyObject = Proxy.newProxyInstance(providedClasses.iterator().next().getClassLoader(), providedClasses.toArray(new Class<?>[providedClasses.size()]),
		        this);
	}

	Set<Class<?>> getInterfaceClasses(IMyxName interfaceName) {
		Set<String> classNames = new HashSet<String>();
		IMyxInterfaceDescription miDesc = getMyxBrickItems().getInterfaceManager().getInterfaceDescription(interfaceName);
		if (miDesc instanceof MyxJavaClassInterfaceDescription) {
			MyxJavaClassInterfaceDescription jmiDesc = (MyxJavaClassInterfaceDescription) miDesc;
			classNames.addAll(jmiDesc.getServiceObjectInterfaceNames());
		}

		Set<Class<?>> classes = new HashSet<Class<?>>();
		IMyxClassManager classManager = getMyxBrickItems().getClassManager();
		for (String interfaceClassName : classNames) {
			try {
				Class<?> interfaceClass = classManager.classForName(interfaceClassName);
				classes.add(interfaceClass);
			}
			catch (ClassNotFoundException cnfe) {
				throw new IllegalArgumentException("Can't find interface class: " + cnfe.getMessage());
			}
		}
		return classes;
	}

	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(REQUIRED_INTERFACE_NAME)) {
			this.trueServiceObject = serviceObject;
		}
		if (interfaceName.equals(OVERRIDE_INTERFACE_NAME)) {
			this.overrideServiceObject = serviceObject;
		}
	}

	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(REQUIRED_INTERFACE_NAME)) {
			this.trueServiceObject = null;
		}
		if (interfaceName.equals(OVERRIDE_INTERFACE_NAME)) {
			this.overrideServiceObject = null;
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
		if (overrideServiceObject == null) {
			throw new RuntimeException("Disconnected proxy.");
		}
		try {
			if (overrideClasses.contains(method.getDeclaringClass())) {
				return method.invoke(overrideServiceObject, args);
			}
			else {
				return method.invoke(trueServiceObject, args);
			}
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
