package org.archstudio.bna.things;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public abstract class AbstractThingPeer<T extends IThing> implements IThingPeer<T> {

	protected final T t;

	public AbstractThingPeer(T thing) {
		this.t = thing;
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean getLocalBounds(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, Rectangle boundsResult) {
		return false;
	}

	@Override
	public boolean isOpaqueAndFilled() {
		return false;
	}
}
