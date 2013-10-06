package org.archstudio.bna.things.utility;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.things.AbstractThingPeer;

public class NoThingPeer<T extends IThing> extends AbstractThingPeer<IThing> {

	public NoThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

}
