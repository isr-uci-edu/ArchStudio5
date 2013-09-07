package org.archstudio.xarchadt.internal;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.base.Function;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;

public class EObjectProxy extends AbstractProxy {

	private static final LoadingCache<String, EPackage> ePackageCache = CacheBuilder.newBuilder().build(
			new CacheLoader<String, EPackage>() {

				@Override
				public synchronized EPackage load(String nsURI) throws Exception {
					EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
					if (ePackage != null) {
						return ePackage;
					}
					throw new NullPointerException(SystemUtils.message("No EPackage with nsURI: $0", nsURI));
				}
			});

	static final class Get_EObject extends Handler<NameContext, ProxyImpl> {

		public Get_EObject(NameContext context) {
			super(context);
		}

		@Override
		public Object invoke(ProxyImpl proxy, Method method, Object[] args) throws Throwable {
			return proxy(proxy.xarch, (ObjRef) proxy.xarch.get(proxy.objRef, context.name));
		}
	}

	static final class Get_EList extends Handler<NameContext, ProxyImpl> {

		public Get_EList(NameContext context) {
			super(context);
		}

		@Override
		public Object invoke(ProxyImpl proxy, Method method, Object[] args) throws Throwable {
			return EListProxy.proxy(proxy.xarch, proxy.objRef, context.name);
		}
	}

	static final class Get_Object extends Handler<NameContext, ProxyImpl> {

		public Get_Object(NameContext context) {
			super(context);
		}

		@Override
		public Object invoke(ProxyImpl proxy, Method method, Object[] args) throws Throwable {
			return proxy.xarch.get(proxy.objRef, context.name);
		}
	}

	static final class Get_EClass extends Handler<NameContext, ProxyImpl> {

		public Get_EClass(NameContext context) {
			super(context);
		}

		@Override
		public Object invoke(ProxyImpl proxy, Method method, Object[] args) throws Throwable {
			IXArchADTTypeMetadata typeMetadata = proxy.xarch.getTypeMetadata(proxy.objRef);
			EPackage ePackage = ePackageCache.getUnchecked(typeMetadata.getNsURI());
			return ePackage.getEClassifier(typeMetadata.getTypeName());
		}
	}

	static final class Get_EContainer extends Handler<NameContext, ProxyImpl> {

		public Get_EContainer(NameContext context) {
			super(context);
		}

		@Override
		public Object invoke(ProxyImpl proxy, Method method, Object[] args) throws Throwable {
			return XArchADTProxy.proxy(proxy.xarch, proxy.xarch.getParent(proxy.objRef));
		}
	}

	static final class Get_EGet extends Handler<NameContext, ProxyImpl> {

		public Get_EGet(NameContext context) {
			super(context);
		}

		@Override
		public Object invoke(ProxyImpl proxy, Method method, Object[] args) throws Throwable {
			EStructuralFeature feature = (EStructuralFeature) args[0];
			if (feature.isMany()) {
				return EListProxy.proxy(proxy.xarch, proxy.objRef, feature.getName());
			}
			Serializable result = proxy.xarch.get(proxy.objRef, feature.getName());
			return result instanceof ObjRef ? XArchADTProxy.proxy(proxy.xarch, (ObjRef) result) : result;
		}
	}

	static final class Set_ESet extends Handler<NameContext, ProxyImpl> {

		public Set_ESet(NameContext context) {
			super(context);
		}

		@Override
		public Object invoke(ProxyImpl proxy, Method method, Object[] args) throws Throwable {
			EStructuralFeature feature = (EStructuralFeature) args[0];
			proxy.xarch.set(proxy.objRef, feature.getName(), (Serializable) args[1]);
			return null;
		}
	}

	static final class Set_EObject extends Handler<NameContext, ProxyImpl> {

		public Set_EObject(NameContext context) {
			super(context);
		}

		@Override
		public Object invoke(ProxyImpl proxy, Method method, Object[] args) throws Throwable {
			proxy.xarch.set(proxy.objRef, context.name, XArchADTProxy.unproxy((EObject) args[0]));
			return null;
		}
	}

	static final class Set_EList extends Handler<NameContext, ProxyImpl> {

		public Set_EList(NameContext context) {
			super(context);
		}

		@Override
		public Object invoke(ProxyImpl proxy, Method method, Object[] args) throws Throwable {
			return EListProxy.proxy(proxy.xarch, proxy.objRef, context.name);
		}
	}

	static final class Set_Serializable extends Handler<NameContext, ProxyImpl> {

		public Set_Serializable(NameContext context) {
			super(context);
		}

		@Override
		@SuppressWarnings("cast")
		public Object invoke(ProxyImpl proxy, Method method, Object[] args) throws Throwable {
			proxy.xarch.set(proxy.objRef, context.name, (Serializable) args[0]);
			return null;
		}
	}

	static final class ProxyImpl implements InvocationHandler {

		final IXArchADT xarch;
		final ObjRef objRef;

		ProxyImpl(IXArchADT xarch, ObjRef objRef) {
			this.xarch = xarch;
			this.objRef = objRef;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			return methodsCache.get(method).invoke(this, method, args);
		}

		@Override
		public String toString() {
			IXArchADTTypeMetadata typeMetadata = xarch.getTypeMetadata(objRef);
			List<String> features = Lists.newArrayList("name", "id");
			String info = null;
			for (String feature : features) {
				if (typeMetadata.getFeatures().get(feature) != null) {
					Object value = xarch.get(objRef, feature);
					if (value != null) {
						String string = value.toString();
						if (string.length() > 0) {
							info = "[" + feature + "=" + string + "]";
							break;
						}
					}
				}
			}
			if (info == null) {
				info = "[type=" + typeMetadata.getTypeName() + "]";
			}
			return objRef.toString() + info;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (objRef == null ? 0 : objRef.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (obj instanceof Proxy) {
				obj = Proxy.getInvocationHandler(obj);
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ProxyImpl other = (ProxyImpl) obj;
			if (objRef == null) {
				if (other.objRef != null) {
					return false;
				}
			}
			else if (!objRef.equals(other.objRef)) {
				return false;
			}
			return true;
		}
	}

	static final LoadingCache<Method, Handler<NameContext, ProxyImpl>> methodsCache = CacheBuilder.newBuilder().build(
			new CacheLoader<Method, Handler<NameContext, ProxyImpl>>() {

				@Override
				public Handler<NameContext, ProxyImpl> load(Method method) throws Exception {
					String name = method.getName();
					String prefix;
					if (name.startsWith(prefix = "e")) {
						if (name.equals("eClass")) {
							return getHandler(Get_EClass.class, new NameContext(name));
						}
						if (name.equals("eContainer")) {
							return getHandler(Get_EContainer.class, new NameContext(name));
						}
						if (name.equals("eGet")) {
							return getHandler(Get_EGet.class, new NameContext(name));
						}
						if (name.equals("eSet")) {
							return getHandler(Set_ESet.class, new NameContext(name));
						}
					}
					if (name.startsWith(prefix = "get")) {
						if (EObject.class.isAssignableFrom(method.getReturnType())) {
							return getHandler(Get_EObject.class, new NameContext(name.substring(prefix.length())));
						}
						if (EList.class.isAssignableFrom(method.getReturnType())) {
							return getHandler(Get_EList.class, new NameContext(name.substring(prefix.length())));
						}
						return getHandler(Get_Object.class, new NameContext(name.substring(prefix.length())));
					}
					if (name.startsWith(prefix = "is")) {
						return getHandler(Get_Object.class, new NameContext(name.substring(prefix.length())));
					}
					if (name.startsWith(prefix = "set")) {
						if (EObject.class.isAssignableFrom(method.getParameterTypes()[0])) {
							return getHandler(Set_EObject.class, new NameContext(name.substring(prefix.length())));
						}
						if (EList.class.isAssignableFrom(method.getParameterTypes()[0])) {
							return getHandler(Set_EList.class, new NameContext(name.substring(prefix.length())));
						}
						// set methods can take many different object types, not just Strings
						return getHandler(Set_Serializable.class, new NameContext(name.substring(prefix.length())));
					}
					return getDefaultHandler();
				}
			});

	static final LoadingCache<IXArchADT, ConcurrentMap<ObjRef, EObject>> xArchProxiesCache = CacheBuilder.newBuilder()
			.weakKeys().build(new CacheLoader<IXArchADT, ConcurrentMap<ObjRef, EObject>>() {

				@Override
				public ConcurrentMap<ObjRef, EObject> load(IXArchADT xarch) throws Exception {
					return new MapMaker().softValues().makeMap();
				}
			});

	@SuppressWarnings("unchecked")
	public static final <T extends EObject> T proxy(IXArchADT xarch, ObjRef objRef) {
		if (objRef == null) {
			return null;
		}
		ConcurrentMap<ObjRef, EObject> proxies = xArchProxiesCache.getUnchecked(xarch);
		T proxy = (T) proxies.get(objRef);
		if (proxy == null) {
			IXArchADTTypeMetadata typeMetadata = xarch.getTypeMetadata(objRef);
			String nsURI = typeMetadata.getNsURI();
			EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
			if (ePackage == null) {
				throw new NullPointerException(SystemUtils.message(//
						"EPackage not registered: $0", nsURI));
			}
			String typeName = typeMetadata.getTypeName();
			EClassifier eClassifier = ePackage.getEClassifier(typeName);
			if (!(eClassifier instanceof EClass)) {
				throw new NullPointerException(SystemUtils.message(//
						"EPackage does not contain type '$1': $0", nsURI, typeName));
			}
			EClass eClass = (EClass) eClassifier;
			Class<T> instanceClass = (Class<T>) eClass.getInstanceClass();
			proxies.putIfAbsent(//
					objRef, //
					proxy = (T) Proxy.newProxyInstance(//
							instanceClass.getClassLoader(), //
							new Class<?>[] { instanceClass }, //
							new ProxyImpl(xarch, objRef)));
		}
		return proxy;
	}

	public static final <T extends EObject> Iterable<T> proxy(final IXArchADT xarch, Iterable<ObjRef> objRefs) {
		return Iterables.transform(objRefs, new Function<ObjRef, T>() {
			@Override
			@SuppressWarnings("unchecked")
			public T apply(ObjRef objRef) {
				return (T) proxy(xarch, objRef);
			}
		});
	}

	public static final ObjRef unproxy(EObject eObject) {
		if (eObject == null) {
			return null;
		}

		return ((ProxyImpl) Proxy.getInvocationHandler(eObject)).objRef;
	}

	public static final Iterable<ObjRef> unproxy(Iterable<EObject> eObjects) {
		return Iterables.transform(eObjects, new Function<EObject, ObjRef>() {

			@Override
			public ObjRef apply(EObject eObject) {
				return unproxy(eObject);
			}
		});
	}

	public static IXArchADT getXArchADT(EObject eObject) {
		return ((ProxyImpl) Proxy.getInvocationHandler(eObject)).xarch;
	}
}
