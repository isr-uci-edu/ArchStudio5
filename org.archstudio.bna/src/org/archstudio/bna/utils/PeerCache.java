package org.archstudio.bna.utils;

import java.lang.reflect.Constructor;
import java.util.Iterator;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.utils.FastIntMap.Entry;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class PeerCache {

	protected static LoadingCache<Class<? extends IThingPeer<?>>, Constructor<?>> constructorsCache = CacheBuilder
			.newBuilder().build(new CacheLoader<Class<? extends IThingPeer<?>>, Constructor<?>>() {

				@Override
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

	FastIntMap<IThingPeer<?>> peersCache = new FastIntMap<IThingPeer<?>>(1000);

	@SuppressWarnings("unchecked")
	public <T extends IThing> IThingPeer<T> getPeer(T thing) {

		IThingPeer<?> thingPeer = peersCache.get(thing.getUID());
		if (thingPeer == null) {
			try {
				thingPeer = (IThingPeer<?>) constructorsCache.get(thing.getPeerClass()).newInstance(thing);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			peersCache.put(thing.getUID(), thingPeer);
		}

		return (IThingPeer<T>) thingPeer;
	}

	public void disposePeer(IThing thing) {
		getPeer(thing).dispose();
		peersCache.remove(thing.getUID());
	}

	public void dispose() {
		for (Iterator<Entry<IThingPeer<?>>> i = peersCache.iterator(); i.hasNext();) {
			IThingPeer<?> p = i.next().value;
			p.dispose();
		}
		peersCache.clear();
	}
}
