package org.archstudio.xarchadt.core.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ConcurrentMap;

import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xarchadt.common.IXArchADT;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;

import com.google.common.base.Function;
import com.google.common.collect.MapMaker;

public class EFactoryProxy extends AbstractProxy {

	static final class Create_EObject extends Handler<NameContext, ProxyImpl> {

		public Create_EObject(NameContext context) {
			super(context);
		}

		@Override
		public Object invoke(ProxyImpl proxy, Method method, Object[] args) throws Throwable {
			return EObjectProxy.proxy(proxy.xarch, proxy.xarch.create(proxy.nsURI, context.name));
		}
	}

	static final class ProxyImpl implements InvocationHandler {

		private final IXArchADT xarch;
		private final String nsURI;

		public ProxyImpl(IXArchADT xarch, String nsURI) {
			this.xarch = xarch;
			this.nsURI = nsURI;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			return methods.get(method).invoke(this, method, args);
		}

		@Override
		public String toString() {
			return "Factory for " + nsURI;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((nsURI == null) ? 0 : nsURI.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ProxyImpl other = (ProxyImpl) obj;
			if (nsURI == null) {
				if (other.nsURI != null)
					return false;
			}
			else if (!nsURI.equals(other.nsURI))
				return false;
			return true;
		}
	}

	static final ConcurrentMap<Method, Handler<NameContext, ProxyImpl>> methods = new MapMaker().softKeys()
			.makeComputingMap(new Function<Method, Handler<NameContext, ProxyImpl>>() {
				public Handler<NameContext, ProxyImpl> apply(Method method) {
					String name = method.getName();
					String prefix;
					if (name.startsWith(prefix = "create")) {
						return getHandler(Create_EObject.class, new NameContext(name.substring(prefix.length())));
					}
					return getDefaultHandler();
				};
			});

	static final ConcurrentMap<IXArchADT, ConcurrentMap<String, EFactory>> autoXArchProxies = new MapMaker()//
			.weakKeys().makeComputingMap(new Function<IXArchADT, ConcurrentMap<String, EFactory>>() {
				@Override
				public ConcurrentMap<String, EFactory> apply(IXArchADT input) {
					return new MapMaker().softValues().makeMap();
				}
			});

	@SuppressWarnings("unchecked")
	public static final <T extends EFactory> T proxy(IXArchADT xarch, String nsURI) {
		ConcurrentMap<String, EFactory> proxies = autoXArchProxies.get(xarch);
		T proxy = (T) proxies.get(nsURI);
		if (proxy == null) {
			EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
			if (ePackage == null) {
				throw new NullPointerException(SystemUtils.message(//
						"EPackage not registered: $0", nsURI));
			}
			Class<T> instanceClass = (Class<T>) ePackage.getEFactoryInstance().getClass().getInterfaces()[0];
			proxies.putIfAbsent(//
					nsURI, //
					proxy = (T) Proxy.newProxyInstance(//
							instanceClass.getClassLoader(), //
							new Class<?>[] { instanceClass }, //
							new ProxyImpl(xarch, nsURI)));
		}
		return proxy;
	}

	public static final String unproxy(EFactory eFactory) {
		return ((ProxyImpl) Proxy.getInvocationHandler(eFactory)).nsURI;
	}
}
