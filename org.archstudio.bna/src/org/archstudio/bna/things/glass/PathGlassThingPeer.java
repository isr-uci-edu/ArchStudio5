package org.archstudio.bna.things.glass;

import org.eclipse.swt.graphics.GC;

import org.archstudio.bna.AbstractThingPeer;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.things.utility.HasPathDataThingPeerCache;

public class PathGlassThingPeer extends AbstractThingPeer {

	protected PathGlassThing lt;

	protected HasPathDataThingPeerCache pathCache;

	public PathGlassThingPeer(IThing t) {
		super(t);
		this.lt = (PathGlassThing) t;
		this.pathCache = new HasPathDataThingPeerCache(this.lt);
		//FIXME: dispose cache
		//FIXME: refresh cache on CM changes
	}

	@Override
	public void draw(IBNAView view, GC g) {
		if (lt.isSelected()) {
			synchronized (pathCache) {
				pathCache.update(view, getDisplay());
				if (pathCache.lBounds != null && g.getClipping().intersects(pathCache.lBounds)) {
					BNAUtils.drawMarquee(g, pathCache.lPath, lt.getRotatingOffset());
				}
			}
		}
	}

	@Override
	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		synchronized (pathCache) {
			pathCache.update(view, getDisplay());
			GC g = new GC(getDisplay());
			try {
				if (pathCache.wBounds != null && pathCache.wBounds.contains(worldX, worldY)) {
					if (pathCache.wPath.contains(worldX, worldY, g, false))
						return true;
				}
			}
			finally {
				g.dispose();
			}
		}
		return false;
	}
}
