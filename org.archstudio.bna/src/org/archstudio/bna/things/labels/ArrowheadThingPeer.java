package org.archstudio.bna.things.labels;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.AbstractThingPeer;
import org.archstudio.bna.ArrowheadUtils;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ResourceUtils;
import org.archstudio.bna.constants.ArrowheadShape;

public class ArrowheadThingPeer extends AbstractThingPeer {
	protected ArrowheadThing lt;

	public ArrowheadThingPeer(IThing t) {
		super(t);
		if (!(t instanceof ArrowheadThing)) {
			throw new IllegalArgumentException("ArrowheadThingPeer can only peer for ArrowheadThing");
		}
		this.lt = (ArrowheadThing) t;
	}

	public void draw(IBNAView view, GC g) {
		ICoordinateMapper cm = view.getCoordinateMapper();
		Point wp = lt.getAnchorPoint();
		int lx = cm.worldXtoLocalX(wp.x);
		int ly = cm.worldYtoLocalY(wp.y);

		Point wp2 = lt.getSecondaryPoint();
		int lx2 = cm.worldXtoLocalX(wp2.x);
		int ly2 = cm.worldYtoLocalY(wp2.y);

		if (!(g.getClipping().contains(lx, ly) || g.getClipping().contains(lx2, ly2)))
			return;

		ArrowheadShape arrowheadShape = lt.getArrowheadShape();
		if ((arrowheadShape == null) || (arrowheadShape == ArrowheadShape.NONE))
			return;

		int arrowheadSize = lt.getArrowheadSize();
		boolean arrowheadFilled = lt.isArrowheadFilled();

		Color fg = ResourceUtils.getColor(getDisplay(), lt.getColor());
		if (fg == null) {
			fg = g.getDevice().getSystemColor(SWT.COLOR_BLACK);
		}

		Color bg = ResourceUtils.getColor(getDisplay(), lt.getSecondaryColor());
		if (bg == null) {
			bg = g.getDevice().getSystemColor(SWT.COLOR_BLACK);
		}

		if (arrowheadShape == ArrowheadShape.WEDGE) {
			int[] points = ArrowheadUtils.calculateTriangularArrowhead(wp2.x, wp2.y, wp.x, wp.y, arrowheadSize);
			if (points == null)
				return;
			for (int i = 0; i < points.length; i += 2) {
				points[i] = cm.worldXtoLocalX(points[i]);
				points[i + 1] = cm.worldYtoLocalY(points[i + 1]);
			}
			if (arrowheadFilled) {
				g.setBackground(bg);
				g.fillPolygon(points);
			}
			g.setForeground(fg);
			g.drawPolyline(points);
		}
		else if (arrowheadShape == ArrowheadShape.TRIANGLE) {
			int[] points = ArrowheadUtils.calculateTriangularArrowhead(wp2.x, wp2.y, wp.x, wp.y, arrowheadSize);
			if (points == null)
				return;
			for (int i = 0; i < points.length; i += 2) {
				points[i] = cm.worldXtoLocalX(points[i]);
				points[i + 1] = cm.worldYtoLocalY(points[i + 1]);
			}
			if (arrowheadFilled) {
				g.setBackground(bg);
				g.fillPolygon(points);
			}
			g.setForeground(fg);
			g.drawPolygon(points);
		}
	}

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return false;
	}

}
