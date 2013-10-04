package org.archstudio.bna.things;

import java.awt.geom.Line2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.eclipse.swt.graphics.Point;

public abstract class AbstractIndicatorPointThingPeer<T extends AbstractIndicatorPointThing> extends
		AbstractAnchorPointThingPeer<T> {

	public AbstractIndicatorPointThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		Point lap = cm.worldToLocal(t.getAnchorPoint());
		Point lip = cm.worldToLocal(t.getIndicatorPoint());
		Point lp = location.getLocalPoint();
		return Line2D.ptLineDist(lap.x, lap.y, lip.x, lip.y, lp.x, lp.y) <= 5;
	}

}