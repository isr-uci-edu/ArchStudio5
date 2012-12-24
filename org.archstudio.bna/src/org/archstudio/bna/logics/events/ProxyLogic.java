package org.archstudio.bna.logics.events;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.logics.AbstractThingLogic;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class ProxyLogic extends AbstractThingLogic {

	private final List<Object> additionalObjects = Lists.newCopyOnWriteArrayList();

	private final LoadingCache<Class<?>, Object> proxiesCache = CacheBuilder.newBuilder().build(
			new CacheLoader<Class<?>, Object>() {

				public Object load(final Class<?> input) throws Exception {
					return Proxy.newProxyInstance(input.getClassLoader(), new Class[] { input },
							new InvocationHandler() {

								IBNAWorld world = checkNotNull(ProxyLogic.this.getBNAWorld());
								IThingLogicManager logicManager = checkNotNull(world.getThingLogicManager());

								public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
									for (Object o : logicManager.getThingLogics(input)) {
										method.invoke(o, args);
									}
									for (Object o : Iterables.filter(additionalObjects, input)) {
										method.invoke(o, args);
									}
									return "equals".equals(method.getName()) ? false : null;
								}
							});
				}
			});

	public ProxyLogic() {
		super();
	}

	public <T> T addObject(T object) {
		additionalObjects.add(object);
		return object;
	}

	@SuppressWarnings("unchecked")
	public <T> T getProxyForInterface(Class<T> interfaceClass) {
		return (T) proxiesCache.getUnchecked(interfaceClass);
	}
}
