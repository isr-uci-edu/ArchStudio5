package org.archstudio.bna.things.utility;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.Resources;
import org.archstudio.bna.things.AbstractThingPeer;
import org.eclipse.swt.graphics.Rectangle;

public final class NoThingPeer<T extends IThing> extends AbstractThingPeer<IThing> {

	public NoThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		return false;
	}

}
