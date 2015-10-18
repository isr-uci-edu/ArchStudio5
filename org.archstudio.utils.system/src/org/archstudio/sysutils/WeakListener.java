package org.archstudio.sysutils;

import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Creates a weak reference wrapper around a listener that automatically removes itself when the original listener is
 * garbage collected.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public final class WeakListener {
	/**
	 * Called to remove this listener from the event producer. Called when the original listener is garbage collected.
	 *
	 * @param <T>
	 *            The proxy listener returned from
	 *            {@link WeakListener#createWeakListener(Object, Class, RemoveListenerHandler)}.
	 */
	public static interface RemoveListenerHandler<T> {
		/**
		 * Called to remove the proxy listener returned from
		 * {@link WeakListener#createWeakListener(Object, Class, RemoveListenerHandler)} from the event producer. Called
		 * when the original listener is garbage collected.
		 *
		 * @param proxyListener
		 */
		public void removeListener(T proxyListener);
	}

	/**
	 * Returns a proxy listener that should be added as a listener to the event producer. The original listener is
	 * wrapper in a weak reference. Once it is garbage collected, the {@link RemoveListenerHandler} is called to remove
	 * the proxy listener.
	 *
	 * @param originalListener
	 *            The listener to wrap in a weak reference.
	 * @param ofType
	 *            The type of the listener.
	 * @param removeListenerHandler
	 *            The handler to call to remove the proxy listener when the original listener is garbage collected.
	 * @return The proxy listener that should be added as a listener to the event producer.
	 */
	@SuppressWarnings("unchecked")
	public static final <T> T createWeakListener(T originalListener, Class<T> ofType,
			final RemoveListenerHandler<T> removeListenerHandler) {
		final WeakReference<T> weakReference = new WeakReference<T>(originalListener);
		final T[] proxyObject = (T[]) Array.newInstance(ofType, 1);
		proxyObject[0] =
				(T) Proxy.newProxyInstance(ofType.getClassLoader(), new Class<?>[] { ofType }, new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						T listener = weakReference.get();
						if (listener != null) {
							return method.invoke(listener, args);
						}
						else {
							removeListenerHandler.removeListener(proxyObject[0]);
							return null;
						}
					}
				});
		return proxyObject[0];
	}
}
