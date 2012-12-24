package org.archstudio.myx.java.conn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.archstudio.myx.fw.IMyxName;

import com.google.common.collect.Sets;

/**
 * Myx brick: "Synchronous Multiway Proxy Impl"
 * 
 * @see org.archstudio.myx.java.conn.SynchronousMultiwayProxyConnectorStub
 */
public class SynchronousMultiwayProxyConnector extends
		org.archstudio.myx.java.conn.SynchronousMultiwayProxyConnectorStub implements InvocationHandler,
		IMultiwayResults {

	protected Object[] returnValues = new Object[0];
	protected Throwable[] exceptions = new Throwable[0];

	protected ExecutorService asyncExecutor = null;

	public SynchronousMultiwayProxyConnector() {
		results = this;
	}

	@Override
	public void init() {
		asyncExecutor = Executors.newSingleThreadExecutor();
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

	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		super.interfaceConnected(interfaceName, serviceObject);

		if (OUT_OUT.equals(interfaceName)) {
			Object out1 = out.iterator().next();
			Class<?> out1Class = out1.getClass();
			ClassLoader outClassLoader = out1Class.getClassLoader();
			Set<Class<?>> outInterfaceClasses = Sets.newHashSet();
			while (out1Class != null) {
				outInterfaceClasses.addAll(Arrays.asList(out1Class.getInterfaces()));
				out1Class = out1Class.getSuperclass();
			}
			in = Proxy.newProxyInstance(outClassLoader, outInterfaceClasses.toArray(new Class<?>[0]), this);
		}
	}

	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (OUT_OUT.equals(interfaceName)) {
			in = null;
		}

		super.interfaceDisconnecting(interfaceName, serviceObject);
	}

	@Override
	public Object[] getReturnValues() {
		return returnValues;
	}

	@Override
	public Throwable[] getExceptions() {
		return exceptions;
	}

	protected void reportCallProgress(int callee, int numCallees, Object returnValue, Throwable exception) {
		if (progress.size() == 0) {
			return;
		}
		final int fcallee = callee;
		final int fnumCallees = numCallees;
		final Object freturnValue = returnValue;
		final Throwable fexception = exception;
		final IMultiwayProgressListener[] pls = progress.toArray(new IMultiwayProgressListener[progress.size()]);
		asyncExecutor.execute(new Runnable() {

			@Override
			public void run() {
				for (IMultiwayProgressListener pl : pls) {
					pl.callProgress(fcallee, fnumCallees, freturnValue, fexception);
				}
			}
		});
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (in == null) {
			throw new RuntimeException("Disconnected proxy.");
		}
		synchronized (this) {
			Object[] rvs = new Object[out.size()];
			Throwable[] exs = new Throwable[out.size()];
			int i = 0;
			reportCallProgress(0, out.size(), null, null);
			for (Object trueServiceObject : out) {
				try {
					rvs[i] = method.invoke(trueServiceObject, args);
					reportCallProgress(i + 1, out.size(), rvs[i], null);
				}
				catch (Throwable t) {
					exs[i] = t;
					reportCallProgress(i + 1, out.size(), null, exs[i]);
				}
				i++;
			}
			returnValues = rvs;
			exceptions = exs;
		}
		return null;
	}

}