package org.archstudio.bna.logics.events;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.Finally;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Creates {@link #getProxyForInterface(Class) proxy objects} implementing a given interface which repeat method calls
 * made to the proxy object on all logics and {@link #addObject(Object) additional objects} implementing that interface
 * as well.
 * <p/>
 * This is useful for sending notification events to all logics from external sources. A listener passed in to the
 * interface can be used to add the proxy objects as a listener to its respective source of events.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class ProxyLogic extends AbstractThingLogic {
	/** The listener to receive proxy object creation events. */
	private final IProxyLogicListener proxyLogicListener;

	/** Additional objects on which to repeat calls made to the proxy objects. */
	private final List<Object> additionalObjects = Lists.newCopyOnWriteArrayList();

	/**
	 * Creates proxy objects on demand that implement a given interface. Calls to these proxy object will be repeated on
	 * all logics in the same world and all additional objects in {@link #additionalObjects} that implement the
	 * interface.
	 */
	private final LoadingCache<Class<?>, Object> proxiesCache =
			CacheBuilder.newBuilder().build(new CacheLoader<Class<?>, Object>() {
				@Override
				public Object load(final Class<?> input) throws Exception {
					Object proxy = Proxy.newProxyInstance(input.getClassLoader(), new Class[] { input },
							new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							try (Finally lock = BNAUtils.lock()) {
								for (Object o : logics.getThingLogics(input)) {
									method.invoke(o, args);
								}
								for (Object o : Iterables.filter(additionalObjects, input)) {
									method.invoke(o, args);
								}
								if ("equals".equals(method.getName()) && args.length == 1) {
									return super.equals(args[0]);
								}
								if ("hashCode".equals(method.getName()) && args.length == 0) {
									return super.hashCode();
								}
								if ("toString".equals(method.getName()) && args.length == 0) {
									return super.toString();
								}
								return null;
							}
						}
					});
					if (proxyLogicListener != null) {
						try {
							proxyLogicListener.proxyObjectCreated(ProxyLogic.this, proxy);
						}
						catch (Exception ignored) {
							ignored.printStackTrace();
						}
					}
					return proxy;
				}

			});

	/**
	 * Creates a new instance of {@link ProxyLogic} for the given world with the provided listener.
	 *
	 * @param world The world that will contain the logic.
	 * @param proxyLogicListener A listener that should be notified about the creation of proxy logic events.
	 */
	public ProxyLogic(IBNAWorld world, @Nullable IProxyLogicListener proxyLogicListener) {
		super(world);
		BNAUtils.checkLock();
		this.proxyLogicListener = proxyLogicListener;
		if (proxyLogicListener != null) {
			try {
				proxyLogicListener.proxyLogicCreated(this);
			}
			catch (Exception ignored) {
				ignored.printStackTrace();
			}
		}
	}

	/**
	 * Creates a new instance of {@link ProxyLogic} for the given world with a <code>null</code> listener.
	 *
	 * @param world The world that will contain the logic.
	 */
	public ProxyLogic(IBNAWorld world) {
		this(world, null);
	}

	/**
	 * Adds an additional object for which proxy object invocations should be repeated.
	 *
	 * @param object An object that should be invoked when the proxy objects are invoked.
	 * @return The object.
	 */
	public <T> T addObject(T object) {
		BNAUtils.checkLock();
		additionalObjects.add(object);
		return object;
	}

	/**
	 * Creates a proxy object implementing the given interface. Calls to these proxy object will also be made on all
	 * logics in the same world and all {@link #addObject(Object) additional objects} that implement the interface.
	 *
	 * @param interfaceClass The interface for which methods should be delegated.
	 * @return a proxy object implementing the given interface.
	 */
	@SuppressWarnings("unchecked")
	public <T> T getProxyForInterface(Class<T> interfaceClass) {
		BNAUtils.checkLock();
		return (T) proxiesCache.getUnchecked(interfaceClass);
	}
}
