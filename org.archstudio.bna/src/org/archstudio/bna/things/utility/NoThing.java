package org.archstudio.bna.things.utility;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.things.AbstractThing;
import org.eclipse.jdt.annotation.Nullable;

public class NoThing extends AbstractThing implements IThing {

	public NoThing(@Nullable Object id) {
		super(id);
	}

	@Override
	public IThingPeer<? extends IThing> createPeer(IBNAView view, ICoordinateMapper cm) {
		return new NoThingPeer<NoThing>(this, view, cm);
	}
}
