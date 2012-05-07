package org.archstudio.bna.things.utility;

import java.awt.geom.Point2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.things.AbstractAnchorPointThingPeer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.RGB;

public class RotaterThingPeer<T extends RotaterThing> extends AbstractAnchorPointThingPeer<T> implements IThingPeer<T> {

	public RotaterThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, IResources r, Graphics g) {
		Point lap = cm.worldToLocal(t.getAnchorPoint());
		int Radius = t.getRadius();
		int radius = Radius - 4;
		int angle = t.getAngle();

		g.translate(lap.x, lap.y);

		// background circle
		g.setAlpha(32);
		g.setBackgroundColor(r.getColor(new RGB(0,0,0)));
		g.fillOval(-radius, -radius, radius * 2, radius * 2);

		// outer circle edge
		g.setAlpha(255);
		g.setBackgroundColor(r.getColor(new RGB(0,0,0)));
		g.drawOval(-radius, -radius, radius * 2 - 1, radius * 2 - 1);
		
		// red wedge
		g.setAlpha(196);
		g.setBackgroundColor(r.getColor(new RGB(255,0,0)));
		g.fillArc(-Radius, -Radius, Radius * 2, Radius * 2, -angle - 10, 20);
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		Point lap = cm.worldToLocal(t.getAnchorPoint());
		int radius = t.getRadius();
		Point lLocation = location.getLocalPoint();
		float distance = (float) Point2D.distance(lap.x, lap.y, lLocation.x, lLocation.y);
		return distance <= radius;
	}

	@Override
	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r) {
		Point lap = cm.worldToLocal(t.getAnchorPoint());
		int radius = t.getRadius();
		return new Rectangle(lap.x - radius, lap.y - radius, radius * 2, radius * 2);
	}
}
