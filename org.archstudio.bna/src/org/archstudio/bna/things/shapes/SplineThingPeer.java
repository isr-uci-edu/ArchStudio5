package org.archstudio.bna.things.shapes;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.things.AbstractSplineThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class SplineThingPeer<T extends SplineThing> extends AbstractSplineThingPeer<T> {

	public SplineThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {
		List<Point> localPoints = BNAUtils.getWorldToLocal(cm, t.getPoints());
		if (localPoints.size() == 2) {
			if (r.setForegroundColor(g, t, IHasEdgeColor.EDGE_COLOR_KEY) && r.setLineStyle(g, t)) {

				Point p1 = localPoints.get(0);
				Point p2 = localPoints.get(1);
				int x1 = p1.x;
				int y1 = p1.y;
				int x2 = p2.x;
				int y2 = p2.y;

				{ // clip line, if possible

					/*
					 * Apparently, drawing a line to a point way, way, way off
					 * screen takes a significant amount of time. So much so,
					 * that if we move the point off screen to the edge of the
					 * screen (making the line significantly shorter), the line
					 * is drawn significantly faster.
					 */

					Rectangle clip = BNAUtils.toRectangle(view.getComposite().getClientArea());
					clip.setLocation(0, 0);
					clip.expand(10, 10);

					int ox1 = x1 < clip.x ? -1 : x1 > clip.x + clip.width ? 1 : 0;
					int oy1 = y1 < clip.y ? -1 : y1 > clip.y + clip.height ? 1 : 0;
					int ox2 = x2 < clip.x ? -1 : x2 > clip.x + clip.width ? 1 : 0;
					int oy2 = y2 < clip.y ? -1 : y2 > clip.y + clip.height ? 1 : 0;

					if (ox1 != 0 && ox1 == ox2 || oy1 != 0 && oy1 == oy2) {
						// both x's or y's are outside the rectangle and on the same side
						// so we don't need to draw it
						return;
					}

					if (ox1 != 0 || oy1 != 0) {
						Point p = BNAUtils.getClosestPointOnRectangle(clip, new Dimension(), p1, p2);
						if (p.x == p2.x && p.y == p2.y) {
							// the line doesn't intersect with the rectangle (bad API, should be null)
							// so we don't need to draw it
							return;
						}
						x1 = p.x;
						y1 = p.y;
					}
					if (ox2 != 0 || oy2 != 0) {
						Point p = BNAUtils.getClosestPointOnRectangle(clip, new Dimension(), p2, p1);
						if (p.x == p1.x && p.y == p1.y) {
							// the line doesn't intersect with the rectangle (bad API, should be null)
							// so we don't need to draw it
							return;
						}
						x2 = p.x;
						y2 = p.y;
					}
				}

				g.drawLine(x1, y1, x2, y2);
			}
		}
		else if (localPoints.size() > 2) {
			if (r.setForegroundColor(g, t, IHasEdgeColor.EDGE_COLOR_KEY) && r.setLineStyle(g, t)) {
				g.drawPolyline(BNAUtils.toXYArray(localPoints));
			}
		}
	}
}
