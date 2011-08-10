package org.archstudio.bna.things.glass;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.things.AbstractEllipseThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class EllipseGlassThingPeer<T extends EllipseGlassThing> extends AbstractEllipseThingPeer<T> {

	public EllipseGlassThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, final Graphics g, IResources r) {
		if (Boolean.TRUE.equals(t.get(IHasSelected.SELECTED_KEY))) {
			final Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
			BNAUtils.drawMarquee(g, r, t.getRotatingOffset(), false, new Runnable() {
				@Override
				public void run() {
					g.drawOval(lbb);
				}
			});
		}
	}

	@Override
	public void getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r, Rectangle boundsResult) {
		super.getLocalBounds(view, cm, r, boundsResult);
		// width of marquee line
		boundsResult.expand(3, 3);
	}
}
