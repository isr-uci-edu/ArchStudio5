package org.archstudio.xarchadt.core.internal;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.concurrent.ConcurrentMap;

import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.core.XArchADTProxy;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.MapMaker;

public class EObjectProxy extends AbstractProxy {

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

	static final class SetEObject extends Handler<NameContext, ProxyImpl> {

		public SetEObject(NameContext context) {
			super(context);
		}

		@Override
		public Object invoke(ProxyImpl proxy, Method method, Object[] args) throws Throwable {
			proxy.xarch.set(proxy.objRef, context.name, XArchADTProxy.unproxy((EObject) args[0]));
			return null;
		}
	}

	static final class SetEList extends Handler<NameContext, ProxyImpl> {

		public SetEList(NameContext context) {
			super(context);
		}

		@Override
		public Object invoke(ProxyImpl proxy, Method method, Object[] args) throws Throwable {
			return EListProxy.proxy(proxy.xarch, proxy.objRef, context.name);
		}
	}

	static final class SetSerializable extends Handler<NameContext, ProxyImpl> {

		public SetSerializable(NameContext context) {
			super(context);
		}

		@SuppressWarnings("cast")
		@Override
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
			return methods.get(method).invoke(this, method, args);
		}

		@Override
		public String toString() {
			Class<?>[] ifaces = getClass().getInterfaces();
			Arrays.sort(ifaces);
			return Arrays.asList(ifaces) + " for " + objRef;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((objRef == null) ? 0 : objRef.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (obj instanceof Proxy)
				obj = Proxy.getInvocationHandler(obj);
			if (getClass() != obj.getClass())
				return false;
			ProxyImpl other = (ProxyImpl) obj;
			if (objRef == null) {
				if (other.objRef != null)
					return false;
			}
			else if (!objRef.equals(other.objRef))
				return false;
			return true;
		}
	}

	static final ConcurrentMap<Method, Handler<NameContext, ProxyImpl>> methods = new MapMaker().softKeys()
			.makeComputingMap(new Function<Method, Handler<NameContext, ProxyImpl>>() {
				public Handler<NameContext, ProxyImpl> apply(Method method) {
					String name = method.getName();
					String prefix;
					if (name.startsWith(prefix = "get")) {
						if (EObject.class.isAssignableFrom(method.getReturnType()))
							return getHandler(Get_EObject.class, new NameContext(name.substring(prefix.length())));
						if (EList.class.isAssignableFrom(method.getReturnType()))
							return getHandler(Get_EList.class, new NameContext(name.substring(prefix.length())));
						return getHandler(Get_Object.class, new NameContext(name.substring(prefix.length())));
					}
					if (name.startsWith(prefix = "set")) {
						if (EObject.class.isAssignableFrom(method.getParameterTypes()[0]))
							return getHandler(SetEObject.class, new NameContext(name.substring(prefix.length())));
						if (EList.class.isAssignableFrom(method.getParameterTypes()[0]))
							return getHandler(SetEList.class, new NameContext(name.substring(prefix.length())));
						// set methods can take many different object types, not just Strings
						return getHandler(SetSerializable.class, new NameContext(name.substring(prefix.length())));
					}
					return getDefaultHandler();
				}
			});

	static final ConcurrentMap<IXArchADT, ConcurrentMap<ObjRef, EObject>> autoXArchProxies = new MapMaker()//
			.weakKeys().makeComputingMap(new Function<IXArchADT, ConcurrentMap<ObjRef, EObject>>() {
				@Override
				public ConcurrentMap<ObjRef, EObject> apply(IXArchADT input) {
					return new MapMaker().softValues().makeMap();
				}
			});

	@SuppressWarnings("unchecked")
	public static final <T extends EObject> T proxy(IXArchADT xarch, ObjRef objRef) {
		if (objRef == null)
			return null;
		ConcurrentMap<ObjRef, EObject> proxies = autoXArchProxies.get(xarch);
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
			@SuppressWarnings("unchecked")
			@Override
			public T apply(ObjRef objRef) {
				return (T) proxy(xarch, objRef);
			}
		});
	}

	public static final ObjRef unproxy(EObject eObject) {
		if (eObject == null)
			return null;

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
}
