package org.archstudio.xarchadt.internal;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.emf.common.util.EMap;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.MapMaker;

public class EMapProxy extends AbstractProxy {

	static class ProxyImpl<K, V> extends EListProxy.ProxyImpl<Map.Entry<K, V>> implements EMap<K, V>, InvocationHandler {

		public ProxyImpl(IXArchADT xarch, ObjRef objRef, String name) {
			super(xarch, objRef, name);
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			return method.invoke(this, args);
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

		@Override
		public V get(Object key) {
			throw new UnsupportedOperationException();
		}

		@SuppressWarnings("unchecked")
		@Override
		public V put(K key, V value) {
			return (V) proxy(xarch, xarch.put(objRef, name, (Serializable) unproxy(key), (Serializable) unproxy(value)));
		}

		@Override
		public void putAll(Map<? extends K, ? extends V> map) {
			for (Map.Entry<? extends K, ? extends V> e : map.entrySet()) {
				put(e.getKey(), e.getValue());
			}
		}

		@Override
		public void putAll(EMap<? extends K, ? extends V> map) {
			for (Map.Entry<? extends K, ? extends V> e : map.entrySet()) {
				put(e.getKey(), e.getValue());
			}
		}

		@Override
		public int indexOfKey(Object key) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean containsKey(Object key) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean containsValue(Object value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public V removeKey(Object key) {
			throw new UnsupportedOperationException();
		}

		@Override
		public Map<K, V> map() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Set<Entry<K, V>> entrySet() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Set<K> keySet() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Collection<V> values() {
			throw new UnsupportedOperationException();
		}
	}

	static final LoadingCache<IXArchADT, ConcurrentMap<List<Object>, EMap<Object, Object>>> xArchProxiesCache = CacheBuilder
			.newBuilder().weakKeys()
			.build(new CacheLoader<IXArchADT, ConcurrentMap<List<Object>, EMap<Object, Object>>>() {
				@Override
				public ConcurrentMap<List<Object>, EMap<Object, Object>> load(IXArchADT key) throws Exception {
					return new MapMaker().softValues().makeMap();
				}
			});

	@SuppressWarnings("unchecked")
	public static final <K, V> EMap<K, V> proxy(IXArchADT xarch, ObjRef objRef, String name) {
		ConcurrentMap<List<Object>, EMap<Object, Object>> proxies = xArchProxiesCache.getUnchecked(xarch);
		EMap<Object, Object> proxy = proxies.get(Arrays.asList(objRef, name));
		if (proxy == null) {
			@SuppressWarnings("rawtypes")
			Class<EMap> instanceClass = EMap.class;
			proxies.putIfAbsent(//
					Arrays.<Object> asList(objRef, name), //
					proxy = (EMap<Object, Object>) Proxy.newProxyInstance(//
							instanceClass.getClassLoader(), //
							new Class<?>[] { instanceClass }, //
							new ProxyImpl<Object, Object>(xarch, objRef, name)));
		}
		return (EMap<K, V>) proxy;
	}
}
