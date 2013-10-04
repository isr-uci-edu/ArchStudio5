package org.archstudio.bna.things;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;

public abstract class AbstractPreciseAnchorPointThingPeer<T extends AbstractPreciseAnchorPointThing> extends
		AbstractThingPeer<T> {

	public AbstractPreciseAnchorPointThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

}
