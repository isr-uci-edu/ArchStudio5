package org.archstudio.xarchadt.internal;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.google.common.collect.MapMaker;

public class EListProxy extends AbstractProxy {

	static class ProxyImpl<E> extends AbstractList<E> implements EList<E>, InvocationHandler {

		protected final IXArchADT xarch;
		protected final ObjRef objRef;
		protected final String name;

		public ProxyImpl(IXArchADT xarch, ObjRef objRef, String name) {
			this.xarch = xarch;
			this.objRef = objRef;
			this.name = name;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			return method.invoke(this, args);
		}

		@Override
		public void move(int newPosition, E object) {
			throw new UnsupportedOperationException();
		}

		@Override
		public E move(int newPosition, int oldPosition) {
			throw new UnsupportedOperationException();
		}

		@Override
		@SuppressWarnings("unchecked")
		public E get(int index) {
			return (E) XArchADTProxy.proxy(xarch, (ObjRef) xarch.getAll(objRef, name).get(index));
		}

		@Override
		public int size() {
			return xarch.getAll(objRef, name).size();
		}

		@Override
		@SuppressWarnings("unchecked")
		public Iterator<E> iterator() {
			final Iterator<ObjRef> i = Iterables.filter(xarch.getAll(objRef, name), ObjRef.class).iterator();
			return new Iterator<E>() {

				ObjRef current = null;

				@Override
				public boolean hasNext() {
					return i.hasNext();
				}

				@Override
				public E next() {
					return (E) proxy(xarch, current = i.next());
				}

				@Override
				public void remove() {
					if (current == null) {
						throw new IllegalStateException();
					}
					xarch.remove(objRef, name, current);
					current = null;
				}
			};
		}

		@Override
		public boolean add(E e) {
			xarch.add(objRef, name, (Serializable) unproxy(e));
			return true;
		}

		@Override
		public boolean remove(Object o) {
			if (o instanceof EObject) {
				xarch.remove(objRef, name, (Serializable) unproxy(o));
				return true;
			}
			return false;
		}

		@Override
		public void clear() {
			xarch.remove(objRef, name, xarch.getAll(objRef, name));
		}

		@Override
		public String toString() {
			return SystemUtils.message("EList of '$0' for $1", name, objRef);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (name == null ? 0 : name.hashCode());
			result = prime * result + (objRef == null ? 0 : objRef.hashCode());
			return result;
		}

		@SuppressWarnings("rawtypes")
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
			if (name == null) {
				if (other.name != null) {
					return false;
				}
			}
			else if (!name.equals(other.name)) {
				return false;
			}
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

	static final LoadingCache<IXArchADT, ConcurrentMap<List<Object>, EList<Object>>> xArchProxiesCache = CacheBuilder
			.newBuilder().weakKeys().build(new CacheLoader<IXArchADT, ConcurrentMap<List<Object>, EList<Object>>>() {
				@Override
				public ConcurrentMap<List<Object>, EList<Object>> load(IXArchADT input) {
					return new MapMaker().softValues().makeMap();
				}
			});

	@SuppressWarnings("unchecked")
	public static final <T extends EObject> EList<T> proxy(IXArchADT xarch, ObjRef objRef, String name) {
		ConcurrentMap<List<Object>, EList<Object>> proxies = xArchProxiesCache.getUnchecked(xarch);
		EList<Object> proxy = proxies.get(Arrays.asList(objRef, name));
		if (proxy == null) {
			@SuppressWarnings("rawtypes")
			Class<EList> instanceClass = EList.class;
			proxies.putIfAbsent(//
					Arrays.<Object> asList(objRef, name), //
					proxy = (EList<Object>) Proxy.newProxyInstance(//
							instanceClass.getClassLoader(), //
							new Class<?>[] { instanceClass }, //
							new ProxyImpl<EObject>(xarch, objRef, name)));
		}
		return (EList<T>) proxy;
	}
}
