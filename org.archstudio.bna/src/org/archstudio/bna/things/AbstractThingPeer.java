package org.archstudio.bna.things;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.eclipse.swt.graphics.Rectangle;

public abstract class AbstractThingPeer<T extends IThing> implements IThingPeer<T> {

	protected final T t;

	public AbstractThingPeer(T thing) {
		this.t = thing;
	}

	@Override
	public void updateCache(IBNAView view, ICoordinateMapper cm) {
	}

	@Override
	public void dispose() {
	}

	@Override
	abstract public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm);
}
