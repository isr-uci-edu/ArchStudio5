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


/**
 * @deprecated The synchronous eventing in this class is not supported by the
 *             Myx style.
 */
public class SynchAsynchPumpConnector extends AbstractMyxSimpleBrick implements IMyxDynamicBrick, InvocationHandler {

	public static final IMyxName IN_INTERFACE_NAME = MyxUtils.createName("in");

	public static final IMyxName SYNCH_OUT_INTERFACE_NAME = MyxUtils.createName("synch");

	public static final IMyxName ASYNCH_OUT_INTERFACE_NAME = MyxUtils.createName("asynch");

	protected Object inServiceObject = null;

	protected Object[] synchServiceObjects = new Object[0];

	protected Object[] asynchServiceObjects = new Object[0];

	protected ExecutorService asyncExecutor = null;

	public SynchAsynchPumpConnector() {
	}

	public void init() {
		Set<String> interfaceClassNames = new HashSet<String>();

		IMyxInterfaceDescription miDesc = getMyxBrickItems().getInterfaceManager().getInterfaceDescription(IN_INTERFACE_NAME);
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
			inServiceObject = Proxy.newProxyInstance(interfaceClasses[0].getClassLoader(), interfaceClasses, this);
		}
		asyncExecutor = Executors.newSingleThreadExecutor();
	}

	public void destroy() {
		if (asyncExecutor != null) {
			try {
				asyncExecutor.shutdown();
				asyncExecutor.awaitTermination(5L, TimeUnit.SECONDS);
			}
			catch (InterruptedException ie) {
			}
			finally {
				asyncExecutor = null;
			}
		}
	}

	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(ASYNCH_OUT_INTERFACE_NAME)) {
			synchronized (this) {
				List<Object> l = new ArrayList<Object>(Arrays.asList(asynchServiceObjects));
				l.add(serviceObject);
				asynchServiceObjects = l.toArray(new Object[l.size()]);
			}
		}
		else if (interfaceName.equals(SYNCH_OUT_INTERFACE_NAME)) {
			synchronized (this) {
				List<Object> l = new ArrayList<Object>(Arrays.asList(synchServiceObjects));
				l.add(serviceObject);
				synchServiceObjects = l.toArray(new Object[l.size()]);
			}
		}
	}

	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(ASYNCH_OUT_INTERFACE_NAME)) {
			synchronized (this) {
				List<Object> l = new ArrayList<Object>(Arrays.asList(asynchServiceObjects));
				l.remove(serviceObject);
				asynchServiceObjects = l.toArray(new Object[l.size()]);
			}
		}
		else if (interfaceName.equals(SYNCH_OUT_INTERFACE_NAME)) {
			synchronized (this) {
				List<Object> l = new ArrayList<Object>(Arrays.asList(synchServiceObjects));
				l.remove(serviceObject);
				synchServiceObjects = l.toArray(new Object[l.size()]);
			}
		}
	}

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(IN_INTERFACE_NAME)) {
			return inServiceObject;
		}
		return null;
	}

	public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
		for (int i = 0; i < synchServiceObjects.length; i++) {
			method.invoke(synchServiceObjects[i], args);
		}

		Object[] asos = asynchServiceObjects;
		for (int i = 0; i < asos.length; i++) {
			final Object aso = asos[i];
			Runnable r = new Runnable() {
				public void run() {
					try {
						method.invoke(aso, args);
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

		return null;
	}
}
