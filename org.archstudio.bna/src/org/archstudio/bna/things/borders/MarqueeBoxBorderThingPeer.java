package org.archstudio.bna.things.borders;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.*;

public class MarqueeBoxBorderThingPeer extends AbstractThingPeer {

	protected MarqueeBoxBorderThing lt;

	protected Rectangle localBoundingBox = new Rectangle(0, 0, 0, 0);

	protected static final int[][] lineDash = new int[][] { new int[] { 1, 5 }, new int[] { 0, 1, 1, 4 }, new int[] { 0, 2, 1, 3 }, new int[] { 0, 3, 1, 2 },
	        new int[] { 0, 4, 1, 1 }, new int[] { 0, 5, 1, 0 } };

	public MarqueeBoxBorderThingPeer(IThing t) {
		super(t);
		if (!(t instanceof MarqueeBoxBorderThing)) {
			throw new IllegalArgumentException("MarqueeBorderThingPeer can only peer for MarqueeBorderThing");
		}
		this.lt = (MarqueeBoxBorderThing) t;
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

		int offset = lt.getRotatingOffset();

		BNAUtils.drawMarqueeRectangle(g, localBoundingBox, offset);
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
