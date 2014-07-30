package org.archstudio.bna.logics.events;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.Finally;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class ProxyLogic extends AbstractThingLogic {

	private final List<Object> additionalObjects = Lists.newCopyOnWriteArrayList();

	private final LoadingCache<Class<?>, Object> proxiesCache = CacheBuilder.newBuilder().build(
			new CacheLoader<Class<?>, Object>() {

				@Override
				public Object load(final Class<?> input) throws Exception {
					return Proxy.newProxyInstance(input.getClassLoader(), new Class[] { input },
							new InvocationHandler() {

								IBNAWorld world = checkNotNull(ProxyLogic.this.world);
								IThingLogicManager logics = checkNotNull(world.getThingLogicManager());

								@Override
								public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
									try (Finally lock = BNAUtils.lock()) {
										for (Object o : logics.getThingLogics(input)) {
											method.invoke(o, args);
										}
										for (Object o : Iterables.filter(additionalObjects, input)) {
											method.invoke(o, args);
										}
										return "equals".equals(method.getName()) ? false : null;
									}
								}
							});
				}
			});

	public ProxyLogic(IBNAWorld world) {
		super(world);
	}

	public <T> T addObject(T object) {
		BNAUtils.checkLock();

		additionalObjects.add(object);
		return object;
	}

	@SuppressWarnings("unchecked")
	public <T> T getProxyForInterface(Class<T> interfaceClass) {
		BNAUtils.checkLock();

		return (T) proxiesCache.getUnchecked(interfaceClass);
	}
}
