package org.archstudio.myx.java.conn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;

import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.myx.fw.MyxUtils;

import com.google.common.collect.Sets;

/**
 * Myx brick: "Synch Proxy Impl"
 * 
 * @see org.archstudio.myx.java.conn.SynchronousProxyConnectorStub
 * @generated
 */
public class SynchronousProxyConnector extends org.archstudio.myx.java.conn.SynchronousProxyConnectorStub {

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

	public void setMyxBrickItems(IMyxBrickItems brickItems) {
		super.setMyxBrickItems(brickItems);

		ClassLoader classLoader = getClassLoader(brickItems);
		in = Proxy.newProxyInstance(classLoader, getInterfaceClasses(brickItems, classLoader), new InvocationHandler() {

			public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
				if (out == null) {
					throw new RuntimeException("Disconnected proxy.");
				}
				return method.invoke(out, args);
			}
		});
	}
}