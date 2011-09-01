package org.archstudio.bna.things.labels;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.constants.ArrowheadShape;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.ArrowheadUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

public class ArrowheadThingPeer<T extends ArrowheadThing> extends AbstractThingPeer<T> {

	public ArrowheadThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {

		Point lp1 = cm.worldToLocal(t.getAnchorPoint());
		Point lp2 = cm.worldToLocal(t.getReferencePoint());

		ArrowheadShape arrowheadShape = t.getArrowheadShape();
		if (arrowheadShape == null || arrowheadShape == ArrowheadShape.NONE) {
			return;
		}

		int arrowheadSize = t.getArrowheadSize();
		boolean arrowheadFilled = t.isArrowheadFilled();

		Color fg = r.getColor(t.getColor(), SWT.COLOR_BLACK);
		Color bg = r.getColor(t.getSecondaryColor(), SWT.COLOR_BLACK);

		int[] points = ArrowheadUtils.calculateTriangularArrowhead(wp2.x, wp2.y, wp1.x, wp1.y, arrowheadSize);
		if (points == null) {
			return;
		}
		for (int i = 0; i < points.length; i += 2) {
			points[i] = cm.worldXtoLocalX(points[i]);
			points[i + 1] = cm.worldYtoLocalY(points[i + 1]);
		}

		if (arrowheadFilled) {
			g.setBackgroundColor(bg);
			g.fillPolygon(points);
		}
		g.setForegroundColor(fg);
		if (arrowheadShape == ArrowheadShape.WEDGE) {
			g.drawPolyline(points);
		}
		else if (arrowheadShape == ArrowheadShape.TRIANGLE) {
			g.drawPolygon(points);
		}
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}
}
