package org.archstudio.bna.things.glass;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.*;

public class EndpointGlassThingPeer extends AbstractThingPeer {

	protected EndpointGlassThing lt;

	protected Rectangle localBoundingBox = new Rectangle(0, 0, 0, 0);

	public EndpointGlassThingPeer(IThing t) {
		super(t);
		if (!(t instanceof EndpointGlassThing)) {
			throw new IllegalArgumentException("EndpointGlassThingPeer can only peer for EndpointGlassThing");
		}
		this.lt = (EndpointGlassThing) t;
	}

	protected void updateLocalBoundingBox(ICoordinateMapper cm) {
		localBoundingBox = BNAUtils.worldToLocal(cm, BNAUtils.normalizeRectangle(lt.getBoundingBox()));
	}

	public void draw(IBNAView view, GC g) {
		updateLocalBoundingBox(view.getCoordinateMapper());
	}

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return BNAUtils.isWithin(lt.getBoundingBox(), worldX, worldY);
	}

}
