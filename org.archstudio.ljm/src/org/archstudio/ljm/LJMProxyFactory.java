package org.archstudio.ljm;

import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SuppressWarnings("rawtypes")
public class LJMProxyFactory {

	public static Object createProxy(String host, int port, String objectName, Class[] interfaceClasses)
			throws LJMException {
		//System.out.println("Creating new proxy for : " + host + ":" + port + "[" + objectName + "]");
		//new Throwable().printStackTrace();
		try {
			InetAddress remoteAddress = InetAddress.getByName(host);
			return createProxy(remoteAddress, port, objectName, interfaceClasses);
		}
		catch (UnknownHostException e) {
			throw new LJMException("Invalid host name.");
		}
	}

	public static Object createProxy(InetAddress host, int port, String objectName, Class[] interfaceClasses)
			throws LJMException {
		LJMProxyInvoker proxyInvoker = new LJMProxyInvoker(objectName, interfaceClasses, new LJMEndpoint(host, port,
				objectName));
		return Proxy.newProxyInstance(LJMProxyFactory.class.getClassLoader(), interfaceClasses, proxyInvoker);
	}

}
