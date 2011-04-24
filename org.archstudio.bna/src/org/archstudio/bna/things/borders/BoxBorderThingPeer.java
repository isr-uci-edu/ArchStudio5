package org.archstudio.bna.things.borders;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.*;

public class BoxBorderThingPeer extends AbstractThingPeer {

	protected BoxBorderThing lt;

	protected Rectangle localBoundingBox = new Rectangle(0, 0, 0, 0);

	public BoxBorderThingPeer(IThing t) {
		super(t);
		if (!(t instanceof BoxBorderThing)) {
			throw new IllegalArgumentException("BoxBorderThingPeer can only peer for BoxBorderThing");
		}
		this.lt = (BoxBorderThing) t;
	}

	protected void updateLocalBoundingBox(ICoordinateMapper cm) {
		Rectangle worldBoundingBox = lt.getBoundingBox();
		worldBoundingBox = BNAUtils.normalizeRectangle(worldBoundingBox);
		localBoundingBox.x = cm.worldXtoLocalX(worldBoundingBox.x);
		localBoundingBox.y = cm.worldYtoLocalY(worldBoundingBox.y);

		int lx2 = cm.worldXtoLocalX(worldBoundingBox.x + worldBoundingBox.width);
		int ly2 = cm.worldYtoLocalY(worldBoundingBox.y + worldBoundingBox.height);

		localBoundingBox.width = lx2 - localBoundingBox.x;
		localBoundingBox.height = ly2 - localBoundingBox.y;
	}

	public void draw(IBNAView view, GC g) {
		updateLocalBoundingBox(view.getCoordinateMapper());
		if (!g.getClipping().intersects(localBoundingBox))
			return;

		Color fg = ResourceUtils.getColor(getDisplay(), lt.getColor());
		if (fg == null) {
			fg = g.getDevice().getSystemColor(SWT.COLOR_BLACK);
		}

		int cornerWidth = lt.getCornerWidth();
		int cornerHeight = lt.getCornerHeight();
		boolean hasRoundedCorners = ((cornerWidth + cornerHeight) > 0);
		g.setForeground(fg);
		g.setLineStyle(lt.getLineStyle());
		int lineWidth = lt.getLineWidth();
		g.setLineWidth(lineWidth);
		int count = lt.getCount();
		if (count == 1) {
			if (!hasRoundedCorners) {
				g.drawRectangle(localBoundingBox);
			}
			else {
				g.drawRoundRectangle(localBoundingBox.x, localBoundingBox.y, localBoundingBox.width, localBoundingBox.height, cornerWidth, cornerHeight);
			}
		}
		else {
			for (int i = 0; i < lt.getCount(); i++) {
				Rectangle r = new Rectangle(localBoundingBox.x, localBoundingBox.y, localBoundingBox.width, localBoundingBox.height);
				if (i > 0) {
					r.x += lineWidth * 2 * i;
					r.y += lineWidth * 2 * i;
					r.width -= lineWidth * 4 * i;
					r.height -= lineWidth * 4 * i;
				}
				if ((r.width > 0) && (r.height > 0)) {
					if (!hasRoundedCorners) {
						g.drawRectangle(r);
					}
					else {
						g.drawRoundRectangle(r.x, r.y, r.width, r.height, cornerWidth, cornerHeight);
					}
				}
			}
		}
	}

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return false;
	}

}
