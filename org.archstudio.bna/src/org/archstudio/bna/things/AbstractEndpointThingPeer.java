package org.archstudio.bna.things;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public abstract class AbstractEndpointThingPeer<T extends AbstractEndpointThing> extends AbstractThingPeer<T> {

	public AbstractEndpointThingPeer(T thing) {
		super(thing);
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return t.getBoundingBox().contains(location.getWorldPoint(new Point()));
	}

	@Override
	public boolean getLocalBounds(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, Rectangle boundsResult) {
		cm.worldToLocal(boundsResult.setBounds(t.getBoundingBox()));
		boundsResult.expand(1, 1);
		return true;
	}
}
