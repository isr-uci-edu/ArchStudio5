package org.archstudio.bna.things.glass;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.*;

public class DistinguishedStateGlassThingPeer extends AbstractThingPeer {
	protected DistinguishedStateGlassThing lt;

	protected Rectangle localBoundingBox = new Rectangle(0, 0, 0, 0);

	public DistinguishedStateGlassThingPeer(IThing t) {
		super(t);
		if (!(t instanceof DistinguishedStateGlassThing)) {
			throw new IllegalArgumentException("DistinguishedStateGlassThingPeer can only peer for DistinguishedStateGlassThing");
		}
		this.lt = (DistinguishedStateGlassThing) t;
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

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return BNAUtils.isWithin(lt.getBoundingBox(), worldX, worldY);
	}

}
