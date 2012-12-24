package org.archstudio.bna.things;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;

public abstract class AbstractBoundedAnchorPointThingPeer<T extends AbstractBoundedAnchorPointThing> extends
		AbstractAnchorPointThingPeer<T> {

	public AbstractBoundedAnchorPointThingPeer(T thing) {
		super(thing);
	}

	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return t.getBoundingBox().contains(location.getWorldPoint());
	}

}
