package org.archstudio.bna.things;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;

public abstract class AbstractAnchorPointThingPeer<T extends AbstractMutableAnchorPointThing> extends
		AbstractThingPeer<T> {

	public AbstractAnchorPointThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

}
