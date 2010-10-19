package org.archstudio.bna.things.glass;

import org.archstudio.bna.*;

public class StickySplineGlassThingPeer extends SplineGlassThingPeer {

	protected StickySplineGlassThing lt;

	public StickySplineGlassThingPeer(IThing t) {
		super(t);
		if (!(t instanceof StickySplineGlassThing)) {
			throw new IllegalArgumentException("StickySplineGlassThingPeer can only peer for StickySplineGlassThing");
		}
		this.lt = (StickySplineGlassThing) t;
	}

}
