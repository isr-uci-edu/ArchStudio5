package org.archstudio.bna.things.shapes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.*;

public class SplineThingPeer extends AbstractThingPeer {

	protected SplineThing lt;

	protected Rectangle localBoundingBox = new Rectangle(0, 0, 0, 0);
	protected int[] localPoints = new int[4];

	public SplineThingPeer(IThing t) {
		super(t);
		if (!(t instanceof SplineThing)) {
			throw new IllegalArgumentException("SplineThingPeer can only peer for SplineThing");
		}
		this.lt = (SplineThing) t;
	}

	protected void updateLocalBoundingBox(IBNAView view, ICoordinateMapper cm) {
		localBoundingBox = BNAUtils.worldToLocal(cm, BNAUtils.normalizeRectangle(lt.getBoundingBox()));
	}

	protected void updateLocalPoints(IBNAView view, ICoordinateMapper cm) {
		Point wp1 = lt.getEndpoint1();
		if (wp1 == null)
			wp1 = new Point(0, 0);

		Point[] midpoints = lt.getMidpoints();
		int numMidpoints = 0;
		if (midpoints != null) {
			numMidpoints = midpoints.length;
		}

		Point wp2 = lt.getEndpoint2();
		if (wp2 == null)
			wp2 = new Point(0, 0);

		int lpsize = 4 + numMidpoints + numMidpoints;
		if (localPoints.length != lpsize) {
			localPoints = new int[lpsize];
		}
		int i = 0;
		localPoints[i++] = cm.worldXtoLocalX(wp1.x);
		localPoints[i++] = cm.worldYtoLocalY(wp1.y);
		for (int p = 0; p < numMidpoints; p++) {
			Point mp = midpoints[p];
			localPoints[i++] = cm.worldXtoLocalX(mp.x);
			localPoints[i++] = cm.worldYtoLocalY(mp.y);
		}
		localPoints[i++] = cm.worldXtoLocalX(wp2.x);
		localPoints[i++] = cm.worldYtoLocalY(wp2.y);
	}

	public void draw(IBNAView view, GC g) {
		updateLocalBoundingBox(view, view.getCoordinateMapper());
		if (!g.getClipping().intersects(localBoundingBox))
			return;

		updateLocalPoints(view, view.getCoordinateMapper());
		Color fg = ResourceUtils.getColor(getDisplay(), lt.getColor());
		if (fg == null) {
			fg = g.getDevice().getSystemColor(SWT.COLOR_BLACK);
		}
		g.setForeground(fg);
		g.setLineWidth(lt.getLineWidth());
		g.setLineStyle(lt.getLineStyle());

		g.drawPolyline(localPoints);
	}

	/*
	 * public Rectangle getLocalBoundingBox(IBNAContext context, GC g,
	 * ICoordinateMapper cm){ updateLocalBoundingBox(cm); return
	 * localBoundingBox; }
	 */

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return false;
	}

}
