package org.archstudio.bna.things.shapes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Region;

import org.archstudio.bna.AbstractThingPeer;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ResourceUtils;
import org.archstudio.bna.things.utility.HasPathDataThingPeerCache;

public class PathThingPeer extends AbstractThingPeer {

	protected PathThing lt;

	protected HasPathDataThingPeerCache pathCache;

	public PathThingPeer(IThing t) {
		super(t);
		this.lt = (PathThing) t;
		this.pathCache = new HasPathDataThingPeerCache(this.lt);
		//FIXME: dispose cache
		//FIXME: refresh cache on CM changes
	}

	@Override
	public void draw(IBNAView view, GC g) {
		Region region = null;
		try {
			synchronized (pathCache) {
				pathCache.update(view, getDisplay());
				if (pathCache.lBounds != null && g.getClipping().intersects(pathCache.lBounds)) {

					Color fg = ResourceUtils.getColor(getDisplay(), lt.getColor());
					if (fg == null) {
						fg = g.getDevice().getSystemColor(SWT.COLOR_GRAY);
					}

					boolean isGradientFilled = lt.isGradientFilled();
					Color bg = null;
					if (isGradientFilled) {
						bg = ResourceUtils.getColor(getDisplay(), lt.getSecondaryColor());
						if (bg == null) {
							bg = g.getDevice().getSystemColor(SWT.COLOR_DARK_GRAY);
						}
					}

					if (!isGradientFilled) {
						g.setBackground(fg);
						g.fillPath(pathCache.lPath);
					}
					else {
						g.setForeground(fg);
						g.setBackground(bg);

						region = new Region();
						g.getClipping(region);
						g.setClipping(pathCache.lPath);

						g.fillGradientRectangle(pathCache.lBounds.x, pathCache.lBounds.y, pathCache.lBounds.width, pathCache.lBounds.height, true);

						g.setClipping(region);
					}
				}
			}
		}
		finally {
			if (region != null)
				region.dispose();
			region = null;
		}
	}

	@Override
	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		synchronized (pathCache) {
			pathCache.update(view, getDisplay());
			if (pathCache.wBounds != null && pathCache.wBounds.contains(worldX, worldY)) {
				GC g = new GC(getDisplay());
				try {
					if (pathCache.wPath.contains(worldX, worldY, g, false))
						return true;
				}
				finally {
					g.dispose();
				}
			}

			return false;
		}
	}
}
