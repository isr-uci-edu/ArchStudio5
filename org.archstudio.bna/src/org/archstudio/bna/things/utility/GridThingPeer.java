package org.archstudio.bna.things.utility;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.constants.GridDisplayType;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.swtutils.constants.LineStyle;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class GridThingPeer<T extends GridThing> extends AbstractThingPeer<T> {

	public GridThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {

		// only draw for the top level things
		if (view.getParentView() != null) {
			return false;
		}

		GridDisplayType gridDisplayType = t.getRawGridDisplayType();
		if (gridDisplayType == GridDisplayType.NONE) {
			return false;
		}

		int worldGridStep = t.getRawGridSpacing();
		while (worldGridStep * cm.getLocalScale() <= 8) {
			worldGridStep *= 2;
		}

		RGB color = t.getRawEdgeColor();
		if (color != null) {

			Rectangle lClip = new Rectangle(localBounds.x, localBounds.y, localBounds.width, localBounds.height);
			Rectangle wClip = cm.localToWorld(lClip);
			int wx = wClip.x;
			int wy = wClip.y;
			int wx2 = wClip.x + wClip.width;
			int wy2 = wClip.y + wClip.height;

			int dx = wx % worldGridStep;
			int dy = wy % worldGridStep;

			if (gridDisplayType == GridDisplayType.SOLID_LINES || gridDisplayType == GridDisplayType.DOTTED_LINES) {
				for (int i = wx - dx; i <= wx2; i += worldGridStep) {
					int gx = cm.worldToLocal(new Point(i, wy)).x;
					r.drawShape(new Line2D.Double(gx, lClip.y, gx, lClip.y + lClip.height), color, 1,
							gridDisplayType.getLineStyle(), 1);
				}
				for (int i = wy - dy; i <= wy2; i += worldGridStep) {
					int gy = cm.worldToLocal(new Point(wx, i)).y;
					r.drawShape(new Line2D.Double(lClip.x, gy, lClip.x + lClip.width, gy), color, 1,
							gridDisplayType.getLineStyle(), 1);
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
						r.drawShape(point, null, 1);
					}
				}
			}
			else if (gridDisplayType == GridDisplayType.CROSSES_AT_CORNERS) {
				for (int i = wx - dx; i <= wx2; i += worldGridStep) {
					int gx = cm.worldToLocal(new Point(i, wy)).x;
					for (int j = wy - dy; j <= wy2; j += worldGridStep) {
						int gy = cm.worldToLocal(new Point(wx, j)).y;
						r.drawShape(new Line2D.Double(gx - 3, gy, gx + 3, gy), color, 1, LineStyle.SOLID, 1);
						r.drawShape(new Line2D.Double(gx, gy - 3, gx, gy + 3), color, 1, LineStyle.SOLID, 1);
					}
				}
			}
		}

		return true;
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		return false;
	}
}
