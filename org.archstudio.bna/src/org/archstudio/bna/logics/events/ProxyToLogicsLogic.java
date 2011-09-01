package org.archstudio.bna.logics.events;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.logics.AbstractThingLogic;

import com.google.common.base.Function;
import com.google.common.collect.MapMaker;

public class ProxyToLogicsLogic extends AbstractThingLogic {

	Map<Class<?>, Object> proxies = new MapMaker().makeComputingMap(new Function<Class<?>, Object>() {
		@Override
		public Object apply(final Class<?> input) {
			return Proxy.newProxyInstance(input.getClassLoader(), new Class[] { input }, new InvocationHandler() {

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
		return (T) proxies.get(interfaceClass);
	}
}
