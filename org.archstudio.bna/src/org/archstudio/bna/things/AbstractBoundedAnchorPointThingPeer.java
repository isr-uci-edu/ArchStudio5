package org.archstudio.bna.things;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.eclipse.draw2d.geometry.Rectangle;

public abstract class AbstractBoundedAnchorPointThingPeer<T extends AbstractBoundedAnchorPointThing> extends
		AbstractAnchorPointThingPeer<T> {

	public AbstractBoundedAnchorPointThingPeer(T thing) {
		super(thing);
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return t.getBoundingBox().contains(location.getWorldPoint());
	}

	@Override
	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r) {
		return cm.worldToLocal(t.getBoundingBox());
	}
}
