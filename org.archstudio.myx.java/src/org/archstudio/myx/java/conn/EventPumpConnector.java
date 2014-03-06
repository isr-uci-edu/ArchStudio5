package org.archstudio.myx.java.conn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.myx.fw.MyxUtils;

import com.google.common.collect.Sets;

/**
 * Myx brick: "Event Pump Impl"
 * 
 * @see org.archstudio.myx.java.conn.EventPumpConnectorStub
 * @generated
 */
public class EventPumpConnector extends org.archstudio.myx.java.conn.EventPumpConnectorStub {

	protected ExecutorService asyncExecutor = null;

	@Override
	public void init() {
		super.init();
		asyncExecutor = Executors.newSingleThreadExecutor();
	}

	@Override
	public void destroy() {
		asyncExecutor.shutdown();
		try {
			asyncExecutor.awaitTermination(5, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		asyncExecutor = null;
		super.destroy();
	}

	protected ClassLoader getClassLoader(IMyxBrickItems brickItems) {
		return getClass().getClassLoader();
	}

	protected Class<?>[] getInterfaceClasses(IMyxBrickItems brickItems, ClassLoader classLoader) {
		try {
			Set<Class<?>> interfaceClasses = Sets.newHashSet();
			int i = 0;
			while (true) {
				String interfaceClassName = MyxUtils.getInitProperties(this).get("interfaceClassName" + i);
				if (interfaceClassName == null) {
					break;
				}
				interfaceClasses.add(classLoader.loadClass(interfaceClassName));
				i++;
			}
			return interfaceClasses.toArray(new Class<?>[0]);
		}
		catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setMyxBrickItems(IMyxBrickItems brickItems) {
		super.setMyxBrickItems(brickItems);

		ClassLoader classLoader = getClassLoader(brickItems);
		in = Proxy.newProxyInstance(classLoader, getInterfaceClasses(brickItems, classLoader), new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
				for (final Object o : out) {
					try {
						asyncExecutor.submit(new Runnable() {
							@Override
							public void run() {
								try {
									method.invoke(o, args);
								}
								catch (Throwable e) {
									e.printStackTrace();
								}
							}
						});
					}
					catch (Throwable t) {
						t.printStackTrace();
					}
				}
				return null;
			}
		});
	}
}
