package org.archstudio.bna;

import java.lang.reflect.*;
import java.util.*;

public class PeerCache {
	protected Map<IThing, IThingPeer> peerMap;

	public PeerCache() {
		this.peerMap = Collections.synchronizedMap(new HashMap<IThing, IThingPeer>());
	}

	public IThingPeer createPeer(IThing th) {
		try {
			Class peerClass = th.getPeerClass();
			Constructor constructor = peerClass.getConstructor(new Class[] { IThing.class });
			IThingPeer peer = (IThingPeer) constructor.newInstance(new Object[] { th });
			peerMap.put(th, peer);
			return peer;
		}
		catch (InvocationTargetException ite) {
			throw new RuntimeException("Could not instantiate peer.", ite);
		}
		catch (IllegalAccessException iae) {
			throw new RuntimeException("Could not instantiate peer.", iae);
		}
		catch (InstantiationException ie) {
			throw new RuntimeException("Could not instantiate peer.", ie);
		}
		catch (NoSuchMethodException nsme) {
			throw new RuntimeException("Invalid peer class.", nsme);
		}
	}

	public IThingPeer getPeer(IThing th) {
		IThingPeer peer = peerMap.get(th);
		if (peer != null) {
			return peer;
		}
		else {
			return createPeer(th);
		}
	}

}
