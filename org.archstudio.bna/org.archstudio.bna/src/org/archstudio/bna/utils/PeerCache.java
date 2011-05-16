package org.archstudio.bna.utils;

import java.lang.reflect.Constructor;
import java.util.Map;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;

import com.google.common.base.Function;
import com.google.common.collect.MapMaker;

public class PeerCache<D> {

	public static class Cache<T extends IThing, D> {
		public final IThingPeer<T> peer;
		public D renderData;

		public Cache(IThingPeer<T> peer) {
			this.peer = peer;
		}

		public D getRenderData() {
			return renderData;
		}

		public void setRenderData(D renderData) {
			this.renderData = renderData;
		}

		public IThingPeer<T> getPeer() {
			return peer;
		}
	}

	protected static Map<Class<IThingPeer<?>>, Constructor<?>> autoConstructors = new MapMaker()
			.makeComputingMap(new Function<Class<IThingPeer<?>>, Constructor<?>>() {
				@Override
				public Constructor<?> apply(Class<IThingPeer<?>> input) {
					for (Constructor<?> c : input.getConstructors()) {
						if (c.getParameterTypes().length == 1) {
							if (IThing.class.isAssignableFrom(c.getParameterTypes()[0])) {
								return c;
							}
						}
					}
					throw new IllegalArgumentException("Cannot find appropriate constructor: " + input);
				}
			});

	protected static Map<IThing, Cache<?, ?>> autoPeers = new MapMaker().weakKeys().makeComputingMap(
			new Function<IThing, Cache<?, ?>>() {
				@SuppressWarnings("unchecked")
				@Override
				public Cache<?, ?> apply(IThing input) {
					try {
						return new Cache<IThing, Object>((IThingPeer<IThing>) autoConstructors
								.get(input.getPeerClass()).newInstance(input));
					}
					catch (Exception e) {
						throw new RuntimeException("Cannot create peer: " + input, e);
					}
				}
			});

	@SuppressWarnings("unchecked")
	public <T extends IThing> Cache<T, D> getPeerCache(T thing) {
		return (Cache<T, D>) autoPeers.get(thing);
	}
}
