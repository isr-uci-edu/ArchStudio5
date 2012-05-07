package org.archstudio.bna.things;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;

public abstract class AbstractThingPeer<T extends IThing> implements IThingPeer<T> {

	protected final T t;

	public AbstractThingPeer(T thing) {
		this.t = thing;
	}

	@Override
	public void dispose() {
	}

}
