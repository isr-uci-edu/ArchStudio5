package org.archstudio.bna.things.glass;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.*;

public class BoxGlassThingPeer extends AbstractThingPeer {
	protected BoxGlassThing lt;

	protected Rectangle localBoundingBox = new Rectangle(0, 0, 0, 0);

	public BoxGlassThingPeer(IThing t) {
		super(t);
		if (!(t instanceof BoxGlassThing)) {
			throw new IllegalArgumentException("BoxGlassThingPeer can only peer for BoxGlassThing");
		}
		this.lt = (BoxGlassThing) t;
	}

	protected void updateLocalBoundingBox(ICoordinateMapper cm) {
		localBoundingBox = BNAUtils.worldToLocal(cm, BNAUtils.normalizeRectangle(lt.getBoundingBox()));
	}

	public void draw(IBNAView view, GC g) {
		updateLocalBoundingBox(view.getCoordinateMapper());
		if (lt.isSelected()) {
			BNAUtils.drawMarqueeRectangle(g, localBoundingBox, lt.getRotatingOffset());
		}
	}

	/*
	 * public Rectangle getLocalBoundingBox(IBNAContext context, GC g,
	 * ICoordinateMapper cm){ updateLocalBoundingBox(cm); return
	 * localBoundingBox; }
	 */

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return BNAUtils.isWithin(lt.getBoundingBox(), worldX, worldY);
	}

}
