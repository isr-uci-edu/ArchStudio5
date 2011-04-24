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
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxClassManager;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxInterfaceDescription;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxJavaClassInterfaceDescription;
import org.archstudio.myx.fw.MyxUtils;


public class EventRegulatorConnector extends AbstractMyxSimpleBrick implements IMyxDynamicBrick, InvocationHandler, IEventRegulatorValve {

	public static final IMyxName REQUIRED_INTERFACE_NAME = MyxUtils.createName("out");
	public static final IMyxName PROVIDED_INTERFACE_NAME = MyxUtils.createName("in");
	public static final IMyxName VALVE_INTERFACE_NAME = MyxUtils.createName("valve");

	protected Object[] trueServiceObjects = new Object[0];
	protected Object proxyObject = null;
	protected PausableSingleThreadExecutor asyncExecutor = null;

	class PausableSingleThreadExecutor extends ThreadPoolExecutor {

		private boolean isPaused;
		private ReentrantLock pauseLock = new ReentrantLock();
		private Condition unpaused = pauseLock.newCondition();

		public PausableSingleThreadExecutor() {
			super(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		}

		@Override
		protected void beforeExecute(Thread t, Runnable r) {
			super.beforeExecute(t, r);
			pauseLock.lock();
			try {
				while (isPaused) {
					unpaused.await();
				}
			}
			catch (InterruptedException ie) {
				t.interrupt();
			}
			finally {
				pauseLock.unlock();
			}
		}

		public void pause() {
			pauseLock.lock();
			try {
				isPaused = true;
			}
			finally {
				pauseLock.unlock();
			}
		}

		public void resume() {
			pauseLock.lock();
			try {
				isPaused = false;
				unpaused.signalAll();
			}
			finally {
				pauseLock.unlock();
			}
		}

		public boolean isPaused() {
			return isPaused;
		}
	}

	public EventRegulatorConnector() {
	}

	@Override
	public void init() {
		Set<String> interfaceClassNames = new HashSet<String>();

		IMyxInterfaceDescription miDesc = getMyxBrickItems().getInterfaceManager().getInterfaceDescription(REQUIRED_INTERFACE_NAME);
		if (miDesc instanceof MyxJavaClassInterfaceDescription) {
			MyxJavaClassInterfaceDescription jmiDesc = (MyxJavaClassInterfaceDescription) miDesc;
			interfaceClassNames.addAll(jmiDesc.getServiceObjectInterfaceNames());
		}

		int i = 0;
		while (true) {
			String interfaceClassName = MyxUtils.getInitProperties(this).getProperty("interfaceClassName" + i);
			if (interfaceClassName == null) {
				break;
			}
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
		asyncExecutor = new PausableSingleThreadExecutor();
	}

	@Override
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
		else if (interfaceName.equals(VALVE_INTERFACE_NAME)) {
			return this;
		}
		return null;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (proxyObject == null) {
			//Async messages do not have to get delivered.
			return null;
		}
		if (trueServiceObjects == null || trueServiceObjects.length == 0) {
			//Async messages do not have to get delivered.
			return null;
		}
		Object[] tsos = trueServiceObjects;
		for (final Object tso : tsos) {
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

	public void closeValve() {
		asyncExecutor.pause();
	}

	public void openValve() {
		asyncExecutor.resume();
	}

	public boolean isValveOpen() {
		return !asyncExecutor.isPaused();
	}
}
