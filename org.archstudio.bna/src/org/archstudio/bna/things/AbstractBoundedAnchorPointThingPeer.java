package org.archstudio.bna.things;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;

public abstract class AbstractBoundedAnchorPointThingPeer<T extends AbstractBoundedAnchorPointThing> extends
		AbstractAnchorPointThingPeer<T> {

	public AbstractBoundedAnchorPointThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		return t.getBoundingBox().contains(location.getWorldPoint());
	}

}
