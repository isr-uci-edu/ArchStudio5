package org.archstudio.bna.things.utility;

import java.awt.geom.Ellipse2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.things.AbstractAnchorPointThingPeer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Path;

public class RotaterThingPeer<T extends RotaterThing> extends AbstractAnchorPointThingPeer<T> {

	final int thickness = 10;
	final int halfwedgewidth = 5;

	public RotaterThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {
		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());

		Path path = new Path(r.getDevice());
		path.addArc(lbb.x, lbb.y, lbb.width, lbb.height, 0, 360);
		path.addArc(lbb.x + thickness, lbb.y + thickness, lbb.width - 2 * thickness, lbb.height - 2 * thickness, 0, 360);
		path.close();

		g.setForegroundColor(r.getColor(SWT.COLOR_GRAY));
		g.setAlpha(64);
		g.fillPath(path);

		g.setForegroundColor(r.getColor(SWT.COLOR_BLACK));
		g.setAlpha(255);
		g.drawPath(path);
		path.dispose();

		int angle = t.getAngle();

		g.setBackgroundColor(r.getColor(SWT.COLOR_RED));
		g.setAlpha(192);
		int startAngle = (360 - (angle + halfwedgewidth)) % 360;
		if (startAngle < 0) {
			startAngle += 360;
		}
		g.setAlpha(255);
		g.fillArc(lbb.x, lbb.y, lbb.width, lbb.height, startAngle, 2 * halfwedgewidth);
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		Rectangle bb = t.getBoundingBox();
		if (!bb.contains(location.getWorldPoint(new Point()))) {
			return false;
		}

		Point lPoint = location.getLocalPoint(new Point());
		Rectangle lbb = cm.worldToLocal(bb);
		Ellipse2D.Double outerEllipse = new Ellipse2D.Double(lbb.x, lbb.y, lbb.width, lbb.height);
		Ellipse2D.Double innerEllipse = new Ellipse2D.Double(lbb.x + thickness, lbb.y + thickness, lbb.width - 2
				* thickness, lbb.height - 2 * thickness);

		return outerEllipse.contains(lPoint.x, lPoint.y) && !innerEllipse.contains(lPoint.x, lPoint.y);
	}

	@Override
	public void getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r, Rectangle boundsResult) {
		cm.worldToLocal(boundsResult.setBounds(t.getBoundingBox()));
	}
}
