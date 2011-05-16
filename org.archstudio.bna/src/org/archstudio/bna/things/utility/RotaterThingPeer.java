package org.archstudio.bna.things.utility;

import java.awt.geom.Ellipse2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Path;

public class RotaterThingPeer<T extends RotaterThing> extends AbstractThingPeer<T> {

	final int thickness = 10;
	final int halfwedgewidth = 5;

	public RotaterThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, Graphics g, Rectangle clip, ResourceUtils res) {
		Rectangle bb = t.getBoundingBox();
		Rectangle lbb = BNAUtils.worldToLocal(view.getCoordinateMapper(), bb);
		if (!clip.intersects(lbb)) {
			return;
		}

		Path path = new Path(res.getDevice());
		path.addArc(lbb.x, lbb.y, lbb.width, lbb.height, 0, 360);
		path.addArc(lbb.x + thickness, lbb.y + thickness, lbb.width - 2 * thickness, lbb.height - 2 * thickness, 0, 360);
		path.close();

		g.setForegroundColor(res.getColor(null, SWT.COLOR_GRAY));
		g.setAlpha(64);
		g.fillPath(path);

		g.setForegroundColor(res.getColor(null, SWT.COLOR_BLACK));
		g.setAlpha(255);
		g.drawPath(path);
		path.dispose();

		int angle = t.getAngle();

		g.setBackgroundColor(res.getColor(null, SWT.COLOR_RED));
		g.setAlpha(192);
		int startAngle = (360 - (angle + halfwedgewidth)) % 360;
		if (startAngle < 0) {
			startAngle += 360;
		}
		g.setAlpha(255);
		g.fillArc(lbb.x, lbb.y, lbb.width, lbb.height, startAngle, 2 * halfwedgewidth);
	}

	@Override
	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		Rectangle bb = t.getBoundingBox();
		if (!bb.contains(worldX, worldY)) {
			return false;
		}

		int localX = view.getCoordinateMapper().worldXtoLocalX(worldX);
		int localY = view.getCoordinateMapper().worldYtoLocalY(worldY);
		Rectangle lbb = BNAUtils.worldToLocal(view.getCoordinateMapper(), bb);
		Ellipse2D.Double outerEllipse = new Ellipse2D.Double(lbb.x, lbb.y, lbb.width, lbb.height);
		Ellipse2D.Double innerEllipse = new Ellipse2D.Double(lbb.x + thickness, lbb.y + thickness, lbb.width - 2
				* thickness, lbb.height - 2 * thickness);

		return outerEllipse.contains(localX, localY) && !innerEllipse.contains(localX, localY);
	}
}
