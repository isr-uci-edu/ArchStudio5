package org.archstudio.bna.things.utility;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.constants.GridDisplayType;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.swtutils.constants.LineStyle;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class GridThingPeer<T extends GridThing> extends AbstractThingPeer<T> {

	public GridThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	int worldGridStep;
	GridDisplayType gridDisplayType;

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {

		// only draw for the top level things
		if (view.getParentView() != null) {
			return false;
		}

		if ((worldGridStep = t.getGridSpacing()) == 0) {
			return false;
		}

		if ((gridDisplayType = t.getGridDisplayType()) == GridDisplayType.NONE) {
			return false;
		}

		while (worldGridStep * cm.getLocalScale() <= 8) {
			worldGridStep *= 2;
		}

		if (r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY)) {

			Rectangle lClip = new Rectangle(localBounds.x, localBounds.y, localBounds.width, localBounds.height);
			Rectangle wClip = cm.localToWorld(lClip);
			int wx = wClip.x;
			int wy = wClip.y;
			int wx2 = wClip.x + wClip.width;
			int wy2 = wClip.y + wClip.height;

			int dx = wx % worldGridStep;
			int dy = wy % worldGridStep;

			if (gridDisplayType == GridDisplayType.SOLID_LINES || gridDisplayType == GridDisplayType.DOTTED_LINES) {
				r.resetLineStyle();
				if (gridDisplayType == GridDisplayType.DOTTED_LINES) {
					r.setLineStyle(1, LineStyle.DOT.toBitPattern());
				}
				Line2D.Double line = new Line2D.Double();
				line.y1 = lClip.y;
				line.y2 = lClip.y + lClip.height;
				for (int i = wx - dx; i <= wx2; i += worldGridStep) {
					int gx = cm.worldToLocal(new Point(i, wy)).x;
					line.x1 = line.x2 = gx;
					r.drawShape(line);
				}
				line.x1 = lClip.x;
				line.x2 = lClip.x + lClip.width;
				for (int i = wy - dy; i <= wy2; i += worldGridStep) {
					int gy = cm.worldToLocal(new Point(wx, i)).y;
					line.y1 = line.y2 = gy;
					r.drawShape(line);
				}
			}
			else if (gridDisplayType == GridDisplayType.DOTS_AT_CORNERS) {
				Point2D.Double point = new Point2D.Double();
				for (int i = wx - dx; i <= wx2; i += worldGridStep) {
					int gx = cm.worldToLocal(new Point(i, wy)).x;
					for (int j = wy - dy; j <= wy2; j += worldGridStep) {
						int gy = cm.worldToLocal(new Point(wx, j)).y;
						point.x = gx;
						point.y = gy;
						r.drawShape(point);
					}
				}
			}
			else if (gridDisplayType == GridDisplayType.CROSSES_AT_CORNERS) {
				Line2D.Double line = new Line2D.Double();
				for (int i = wx - dx; i <= wx2; i += worldGridStep) {
					int gx = cm.worldToLocal(new Point(i, wy)).x;
					for (int j = wy - dy; j <= wy2; j += worldGridStep) {
						int gy = cm.worldToLocal(new Point(wx, j)).y;
						line.x1 = gx - 3;
						line.x2 = gx + 3;
						line.y1 = line.y2 = gy;
						r.drawShape(line);
						line.x1 = line.x2 = gx;
						line.y1 = gy - 3;
						line.y2 = gy + 3;
						r.drawShape(line);
					}
				}
			}

			r.resetLineStyle();
		}

		return true;
	}
}
