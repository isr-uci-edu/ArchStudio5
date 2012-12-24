package org.archstudio.bna.things;

import java.awt.geom.Ellipse2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public abstract class AbstractEllipseThingPeer<T extends AbstractEllipseThing> extends AbstractThingPeer<T> {

	public AbstractEllipseThingPeer(T thing) {
		super(thing);
	}

	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		Point lp = location.getLocalPoint();
		return new Ellipse2D.Double(lbb.x, lbb.y, lbb.width, lbb.height).contains(lp.x, lp.y);
	}

}
