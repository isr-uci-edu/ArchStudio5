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

		GridDisplayType gridDisplayType = t.getGridDisplayType();
		if (gridDisplayType == GridDisplayType.NONE) {
			return false;
		}

		if (t.getGridSpacing() == 0) {
			return false;
		}
		long worldGridStep = t.getGridSpacing();
		while (worldGridStep * cm.getLocalScale() <= 8) {
			worldGridStep *= 2;
		}

		RGB color = t.getEdgeColor();
		if (color != null) {

			Rectangle lClip = new Rectangle(localBounds.x, localBounds.y, localBounds.width, localBounds.height);
			Point2D tl = cm.localToWorld(new Point2D.Double(localBounds.x, localBounds.y));
			Point2D br = cm.localToWorld(
					new Point2D.Double(localBounds.x + localBounds.width, localBounds.y + localBounds.height));
			long wx = Math.round(tl.getX());
			long wy = Math.round(tl.getY());
			long wx2 = Math.round(br.getX());
			long wy2 = Math.round(br.getY());

			long dx = wx % worldGridStep;
			long dy = wy % worldGridStep;

			if (gridDisplayType == GridDisplayType.SOLID_LINES || gridDisplayType == GridDisplayType.DOTTED_LINES) {
				for (long i = wx - dx; i <= wx2; i += worldGridStep) {
					double gx = cm.worldToLocal(new Point2D.Double(i, wy)).getX();
					r.drawShape(new Line2D.Double(gx, lClip.y, gx, lClip.y + lClip.height), color, 1,
							gridDisplayType.getLineStyle(), 1);
				}
				for (long i = wy - dy; i <= wy2; i += worldGridStep) {
					double gy = cm.worldToLocal(new Point2D.Double(wx, i)).getY();
					r.drawShape(new Line2D.Double(lClip.x, gy, lClip.x + lClip.width, gy), color, 1,
							gridDisplayType.getLineStyle(), 1);
				}
			}
			else if (gridDisplayType == GridDisplayType.DOTS_AT_CORNERS) {
				Point2D.Double point = new Point2D.Double();
				for (long i = wx - dx; i <= wx2; i += worldGridStep) {
					double gx = cm.worldToLocal(new Point2D.Double(i, wy)).getX();
					for (long j = wy - dy; j <= wy2; j += worldGridStep) {
						double gy = cm.worldToLocal(new Point2D.Double(wx, j)).getY();
						point.x = gx;
						point.y = gy;
						r.drawShape(point, color, 1);
					}
				}
			}
			else if (gridDisplayType == GridDisplayType.CROSSES_AT_CORNERS) {
				for (long i = wx - dx; i <= wx2; i += worldGridStep) {
					double gx = cm.worldToLocal(new Point2D.Double(i, wy)).getX();
					for (long j = wy - dy; j <= wy2; j += worldGridStep) {
						double gy = cm.worldToLocal(new Point2D.Double(wx, j)).getY();
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
