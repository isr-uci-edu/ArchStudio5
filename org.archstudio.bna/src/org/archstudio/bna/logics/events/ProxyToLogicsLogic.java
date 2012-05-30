package org.archstudio.bna.logics.events;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.logics.AbstractThingLogic;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class ProxyToLogicsLogic extends AbstractThingLogic {

	private final LoadingCache<Class<?>, Object> proxiesCache = CacheBuilder.newBuilder().build(
			new CacheLoader<Class<?>, Object>() {

				@Override
				public Object load(final Class<?> input) throws Exception {
					return Proxy.newProxyInstance(input.getClassLoader(), new Class[] { input },
							new InvocationHandler() {

								IBNAWorld world = checkNotNull(ProxyToLogicsLogic.this.getBNAWorld());
								IThingLogicManager logicManager = checkNotNull(world.getThingLogicManager());

								@Override
								public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
									for (Object o : logicManager.getThingLogics(input)) {
										method.invoke(o, args);
									}
									return "equals".equals(method.getName()) ? false : null;
								}
							});
				}
			});

	public ProxyToLogicsLogic() {
		super();
	}

	@SuppressWarnings("unchecked")
	public <T> T getProxyForInterface(Class<T> interfaceClass) {
		return (T) proxiesCache.getUnchecked(interfaceClass);
	}
}
