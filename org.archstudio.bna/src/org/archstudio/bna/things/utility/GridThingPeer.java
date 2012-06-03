package org.archstudio.bna.things.utility;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.constants.GridDisplayType;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

public class GridThingPeer<T extends GridThing> extends AbstractThingPeer<T> {

	public GridThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, IResources r, Graphics g) {
		// only draw for the top level things
		if (view.getParentView() != null) {
			return;
		}

		int worldGridStep = t.getGridSpacing();
		if (worldGridStep == 0) {
			return;
		}

		GridDisplayType gdt = t.getGridDisplayType();
		if (gdt == null || gdt == GridDisplayType.NONE) {
			return;
		}

		if (BNAUtils.setForegroundColor(r, g, t, IHasColor.COLOR_KEY)) {

			while (worldGridStep * cm.getLocalScale() <= 8) {
				worldGridStep *= 2;
			}

			Rectangle lClip = g.getClip(new Rectangle());
			Rectangle wClip = cm.localToWorld(lClip.getCopy());
			int wx = wClip.x;
			int wy = wClip.y;
			int wx2 = wClip.right();
			int wy2 = wClip.bottom();

			int dx = wx % worldGridStep;
			int dy = wy % worldGridStep;

			// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=155511
			g.setLineWidth(0);
			if (gdt == GridDisplayType.SOLID_LINES || gdt == GridDisplayType.DOTTED_LINES) {
				int dashLength = 1;
				if (gdt == GridDisplayType.DOTTED_LINES) {
					g.setLineCap(SWT.CAP_SQUARE);
					g.setLineStyle(SWT.LINE_DOT);
					dashLength = 6;
				}
				else {
					g.setLineStyle(SWT.LINE_SOLID);
				}
				for (int i = wx - dx; i <= wx2; i += worldGridStep) {
					int gx = cm.worldToLocal(new Point(i, wy)).x;
					if (g instanceof SWTGraphics) {
						((SWTGraphics) g).setLineDashOffset(0);
					}
					g.drawLine(gx, lClip.y / dashLength * dashLength, gx, lClip.y + lClip.height + 2);
				}
				for (int i = wy - dy; i <= wy2; i += worldGridStep) {
					int gy = cm.worldToLocal(new Point(wx, i)).y;
					if (g instanceof SWTGraphics) {
						((SWTGraphics) g).setLineDashOffset(0);
					}
					g.drawLine(lClip.x / dashLength * dashLength, gy, lClip.x + lClip.width + 2, gy);
				}
			}
			else if (gdt == GridDisplayType.DOTS_AT_CORNERS) {
				for (int i = wx - dx; i <= wx2; i += worldGridStep) {
					int gx = cm.worldToLocal(new Point(i, wy)).x;
					for (int j = wy - dy; j <= wy2; j += worldGridStep) {
						int gy = cm.worldToLocal(new Point(wx, i)).y;
						g.drawPoint(gx, gy);
					}
				}
			}
			else if (gdt == GridDisplayType.CROSSES_AT_CORNERS) {
				for (int i = wx - dx; i <= wx2; i += worldGridStep) {
					int gx = cm.worldToLocal(new Point(i, wy)).x;
					for (int j = wy - dy; j <= wy2; j += worldGridStep) {
						int gy = cm.worldToLocal(new Point(wx, i)).y;
						g.drawLine(gx - 3, gy, gx + 3, gy);
						g.drawLine(gx, gy - 3, gx, gy + 3);
					}
				}
			}
		}
	}

	@Override
	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r) {
		return null;
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}
}
