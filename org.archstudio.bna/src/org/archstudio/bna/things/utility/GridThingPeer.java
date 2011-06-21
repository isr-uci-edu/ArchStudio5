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
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

public class GridThingPeer<T extends GridThing> extends AbstractThingPeer<T> {

	public GridThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {
		int gridSpacing = t.getGridSpacing();
		if (gridSpacing == 0) {
			return;
		}

		GridDisplayType gdt = t.getGridDisplayType();
		if (gdt == null || gdt == GridDisplayType.NONE) {
			return;
		}

		if (r.setForegroundColor(g, t, IHasColor.COLOR_KEY)) {

			int jump = 1;
			int localGridSpacing = BNAUtils.round(gridSpacing * cm.getLocalScale());
			if (localGridSpacing < 1) {
				localGridSpacing = 1;
			}
			while (localGridSpacing * jump < 10) {
				jump++;
			}

			Rectangle lClip = g.getClip(new Rectangle());
			Rectangle wClip = cm.localToWorld(lClip.getCopy());
			int wx = wClip.x();
			int wy = wClip.y();
			int wx2 = wClip.right();
			int wy2 = wClip.bottom();

			int dx = wx % (gridSpacing * jump);
			int dy = wy % (gridSpacing * jump);

			if (gdt == GridDisplayType.SOLID_LINES || gdt == GridDisplayType.DOTTED_LINES) {
				if (gdt == GridDisplayType.DOTTED_LINES) {
					g.setLineWidth(1);
					g.setLineStyle(SWT.LINE_DOT);
				}
				else {
					g.setLineWidth(1);
					g.setLineStyle(SWT.LINE_SOLID);
				}
				// "~1" rounds down to the nearest even number, which is necessary
				// so that the DOTTED_LINES pattern is draw consistently when scrolled
				for (int i = wx - dx; i <= wx2; i += gridSpacing * jump) {
					int gx = cm.worldToLocal(new Point(i, wy)).x;
					g.drawLine(gx, lClip.y & ~1, gx, lClip.y + lClip.height + 2);
				}
				for (int i = wy - dy; i <= wy2; i += gridSpacing * jump) {
					int gy = cm.worldToLocal(new Point(wx, i)).y;
					g.drawLine(lClip.x & ~1, gy, lClip.x + lClip.width + 2, gy);
				}
			}
			else if (gdt == GridDisplayType.DOTS_AT_CORNERS) {
				g.setLineWidth(1);
				for (int i = wx - dx; i <= wx2; i += gridSpacing * jump) {
					int gx = cm.worldToLocal(new Point(i, wy)).x;
					for (int j = wy - dy; j <= wy2; j += gridSpacing * jump) {
						int gy = cm.worldToLocal(new Point(wx, i)).y;
						g.drawPoint(gx, gy);
					}
				}
			}
			else if (gdt == GridDisplayType.CROSSES_AT_CORNERS) {
				g.setLineWidth(1);
				for (int i = wx - dx; i <= wx2; i += gridSpacing * jump) {
					int gx = cm.worldToLocal(new Point(i, wy)).x;
					for (int j = wy - dy; j <= wy2; j += gridSpacing * jump) {
						int gy = cm.worldToLocal(new Point(wx, i)).y;
						g.drawLine(gx - 3, gy, gx + 3, gy);
						g.drawLine(gx, gy - 3, gx, gy + 3);
					}
				}
			}
		}
	}

	@Override
	public void getLocalBounds(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, Rectangle boundsResult) {
		boundsResult.x = boundsResult.y = Integer.MIN_VALUE / 2;
		boundsResult.width = boundsResult.height = Integer.MAX_VALUE;
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}
}
