package org.archstudio.bna.utils;

import java.lang.reflect.Constructor;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class PeerCache {

	protected static LoadingCache<Class<? extends IThingPeer<?>>, Constructor<?>> constructorsCache = CacheBuilder
			.newBuilder().build(new CacheLoader<Class<? extends IThingPeer<?>>, Constructor<?>>() {

				public Constructor<?> load(Class<? extends IThingPeer<?>> key) throws Exception {
					for (Constructor<?> c : key.getConstructors()) {
						if (c.getParameterTypes().length == 1) {
							if (IThing.class.isAssignableFrom(c.getParameterTypes()[0])) {
								return c;
							}
						}
					}
					throw new IllegalArgumentException("Cannot find appropriate constructor: " + key);
				}
			});

	protected LoadingCache<IThing, IThingPeer<?>> peersCache = CacheBuilder.newBuilder().weakKeys()
			.build(new CacheLoader<IThing, IThingPeer<?>>() {

				public IThingPeer<?> load(IThing key) throws Exception {
					return (IThingPeer<?>) constructorsCache.get(key.getPeerClass()).newInstance(key);
				}
			});

	@SuppressWarnings("unchecked")
	public <T extends IThing> IThingPeer<T> getPeer(T thing) {
		return (IThingPeer<T>) peersCache.getUnchecked(thing);
	}

	public void disposePeer(IThing thing) {
		getPeer(thing).dispose();
	}
}
