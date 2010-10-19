package org.archstudio.bna.things.borders;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;

import org.archstudio.bna.AbstractThingPeer;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ResourceUtils;
import org.archstudio.bna.things.utility.HasPathDataThingPeerCache;

public class PathBorderThingPeer extends AbstractThingPeer {

	protected PathBorderThing lt;

	protected HasPathDataThingPeerCache pathCache;

	public PathBorderThingPeer(IThing t) {
		super(t);
		this.lt = (PathBorderThing) t;
		this.pathCache = new HasPathDataThingPeerCache(this.lt);
		//FIXME: dispose cache
		//FIXME: refresh cache on CM changes
	}

	@Override
	public void draw(IBNAView view, GC g) {
		synchronized (pathCache) {
			pathCache.update(view, getDisplay());
			if (pathCache.lBounds != null && g.getClipping().intersects(pathCache.lBounds)) {

				Color fg = ResourceUtils.getColor(getDisplay(), lt.getColor());
				if (fg == null) {
					fg = g.getDevice().getSystemColor(SWT.COLOR_BLACK);
				}

				g.setLineStyle(lt.getLineStyle());
				g.setLineWidth(lt.getLineWidth());

				g.setForeground(fg);
				g.drawPath(pathCache.lPath);
			}
		}
	}

	@Override
	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		synchronized (pathCache) {
			pathCache.update(view, getDisplay());
			if (pathCache.wBounds != null && pathCache.wBounds.contains(worldX, worldY)) {
				GC g = new GC(getDisplay());
				try {
					if (pathCache.wPath.contains(worldX, worldY, g, true)) {
						return true;
					}
				}
				finally {
					g.dispose();
				}
			}
		}
		return false;
	}
}
