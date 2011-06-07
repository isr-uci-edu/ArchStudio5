package org.archstudio.bna.things.borders;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IRegion;
import org.archstudio.bna.IResources;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class MarqueeBoxBorderThingPeer<T extends MarqueeBoxBorderThing> extends AbstractRectangleThingPeer<T> {

	public MarqueeBoxBorderThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, final Graphics g, IResources r, IRegion localClip,
			IRegion worldClip) {
		if (!worldClip.intersects(t.getBoundingBox())) {
			return;
		}

		final Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		BNAUtils.drawMarquee(g, r, t.getRotatingOffset(), false, new Runnable() {
			@Override
			public void run() {
				g.drawRectangle(lbb);
			}
		});
	}

	@Override
	public void getLocalBounds(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, Rectangle boundsResult) {
		super.getLocalBounds(view, cm, g, r, boundsResult);
		// width of marquee line
		boundsResult.expand(3, 3);
	}
}
