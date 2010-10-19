package org.archstudio.bna.things.utility;

import java.awt.geom.Ellipse2D;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThingPeer;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;

public class RotaterThingPeer extends AbstractThingPeer {
	protected RotaterThing lt;

	protected Rectangle localBoundingBox = new Rectangle(0, 0, 0, 0);

	public RotaterThingPeer(IThing t) {
		super(t);
		if (!(t instanceof RotaterThing)) {
			throw new IllegalArgumentException("RotaterThingPeer can only peer for RotaterThing");
		}
		this.lt = (RotaterThing) t;
	}

	public void draw(IBNAView view, GC g) {
		ICoordinateMapper cm = view.getCoordinateMapper();
		Rectangle worldBoundingBox = lt.getBoundingBox();
		localBoundingBox = BNAUtils.worldToLocal(cm, worldBoundingBox);

		if (!g.getClipping().intersects(localBoundingBox))
			return;

		Path p = new Path(g.getDevice());
		p.addArc(localBoundingBox.x, localBoundingBox.y, localBoundingBox.width, localBoundingBox.height, 0, 360);
		p.addArc(localBoundingBox.x + 5, localBoundingBox.y + 5, localBoundingBox.width - 10, localBoundingBox.height - 10, 0, 360);
		p.close();

		Color fg = getDisplay().getSystemColor(SWT.COLOR_GRAY);
		g.setForeground(fg);
		int oldAlpha = g.getAlpha();
		g.setAlpha(64);
		g.fillPath(p);
		g.setAlpha(oldAlpha);

		fg = getDisplay().getSystemColor(SWT.COLOR_BLACK);
		g.setForeground(fg);

		g.drawPath(p);
		p.dispose();

		int angle = lt.getAngle();

		fg = getDisplay().getSystemColor(SWT.COLOR_RED);
		g.setBackground(fg);
		g.setAlpha(192);
		int startAngle = 360 - (angle + 5);
		if (startAngle < 0)
			startAngle += 360;
		if (startAngle > 360)
			startAngle -= 360;
		g.fillArc(localBoundingBox.x, localBoundingBox.y, localBoundingBox.width, localBoundingBox.height, startAngle, 10);
		g.setAlpha(oldAlpha);

	}

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		if (!BNAUtils.isWithin(lt.getBoundingBox(), worldX, worldY))
			return false;

		Ellipse2D.Double outerEllipse = new Ellipse2D.Double(localBoundingBox.x, localBoundingBox.y, localBoundingBox.width, localBoundingBox.height);
		Ellipse2D.Double innerEllipse = new Ellipse2D.Double(localBoundingBox.x + 5, localBoundingBox.y + 5, localBoundingBox.width - 10,
		        localBoundingBox.height - 10);

		int localX = view.getCoordinateMapper().worldXtoLocalX(worldX);
		int localY = view.getCoordinateMapper().worldYtoLocalY(worldY);

		boolean in = outerEllipse.contains(localX, localY) && (!innerEllipse.contains(localX, localY));
		return in;
	}

	/*
	 * public void draw(Graphics2D g, CoordinateMapper cm){ Rectangle
	 * worldBounds = t.getBoundingBox(); Rectangle localBounds =
	 * BNAUtils.worldRectangleToLocalRectangle(cm, worldBounds);
	 * g.setColor(Color.BLACK); Shape gp = getWidgetShape(localBounds);
	 * Composite originalComposite = g.getComposite();
	 * g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
	 * 0.20f)); g.fill(gp); g.setComposite(originalComposite); g.draw(gp);
	 * g.setColor(Color.RED); Arc2D.Double arc = new Arc2D.Double(localBounds.x
	 * + (WIDGET_WIDTH / 2), localBounds.y + (WIDGET_WIDTH / 2),
	 * localBounds.width - WIDGET_WIDTH, localBounds.height - WIDGET_WIDTH, 360
	 * - t.getRotationAngle(), 1, Arc2D.PIE); Point2D endpoint =
	 * arc.getStartPoint(); Ellipse2D.Double borderEllipse = new
	 * Ellipse2D.Double(endpoint.getX() - (WIDGET_WIDTH / 2), endpoint.getY() -
	 * (WIDGET_WIDTH / 2), WIDGET_WIDTH, WIDGET_WIDTH); originalComposite =
	 * g.getComposite();
	 * g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
	 * 0.60f)); g.fill(arc); g.fill(borderEllipse);
	 * g.setComposite(originalComposite); //g.fillArc(localBounds.x +
	 * (WIDGET_WIDTH / 2), localBounds.y + (WIDGET_WIDTH / 2), localBounds.width
	 * + WIDGET_WIDTH, localBounds.height + WIDGET_WIDTH, 360 -
	 * t.getRotationAngle(), 2); g.setColor(Color.BLACK); Font f =
	 * BNAUtils.DEFAULT_FONT; g.setFont(f); int angle = t.getRotationAngle();
	 * angle %= 360; while(angle < 0) angle += 360; g.drawString("" + angle,
	 * localBounds.x + 2, localBounds.y + localBounds.height - 5); }
	 */
}
