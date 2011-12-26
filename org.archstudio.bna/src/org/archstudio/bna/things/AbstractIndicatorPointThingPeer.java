package org.archstudio.bna.things;

import java.awt.geom.Line2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public abstract class AbstractIndicatorPointThingPeer<T extends AbstractIndicatorPointThing> extends
		AbstractAnchorPointThingPeer<T> {

	public AbstractIndicatorPointThingPeer(T thing) {
		super(thing);
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		Point lap = cm.worldToLocal(t.getAnchorPoint());
		Point lip = cm.worldToLocal(t.getIndicatorPoint());
		Point lp = location.getLocalPoint();
		return Line2D.ptLineDist(lap.x, lap.y, lip.x, lip.y, lp.x, lp.y) <= 5;
	}

	@Override
	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm) {
		Point lap = cm.worldToLocal(t.getAnchorPoint());
		Point lip = cm.worldToLocal(t.getIndicatorPoint());
		int x1 = Math.min(lap.x, lip.x);
		int y1 = Math.min(lap.y, lip.y);
		int x2 = Math.max(lap.x, lip.x);
		int y2 = Math.max(lap.y, lip.y);
		return new Rectangle(x1, y1, x2 - x1, y2 - y1);
	}

}
