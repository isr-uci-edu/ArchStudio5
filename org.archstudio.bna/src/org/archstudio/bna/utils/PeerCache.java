package org.archstudio.bna.utils;

import java.lang.reflect.Constructor;
import java.util.Map;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;

import com.google.common.base.Function;
import com.google.common.collect.MapMaker;

public class PeerCache {

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

	protected static Map<IThing, IThingPeer<?>> autoPeers = new MapMaker().weakKeys().makeComputingMap(
			new Function<IThing, IThingPeer<?>>() {
				@Override
				public IThingPeer<?> apply(IThing input) {
					try {
						return (IThingPeer<?>) autoConstructors.get(input.getPeerClass()).newInstance(input);
					}
					catch (Exception e) {
						throw new RuntimeException("Cannot create peer: " + input, e);
					}
				}
			});

	@SuppressWarnings("unchecked")
	public <T extends IThing> IThingPeer<T> getPeer(T thing) {
		return (IThingPeer<T>) autoPeers.get(thing);
	}

	public void disposePeer(IThing thing) {
		IThingPeer<?> peer = autoPeers.remove(thing);
		if (peer != null) {
			peer.dispose();
		}
	}
}
