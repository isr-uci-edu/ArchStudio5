package org.archstudio.bna.things.glass;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.things.AbstractSplineThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;

public class SplineGlassThingPeer<T extends SplineGlassThing> extends AbstractSplineThingPeer<T> {

	public SplineGlassThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, final Graphics g, IResources r) {
		if (Boolean.TRUE.equals(t.get(IHasSelected.SELECTED_KEY))) {
			final int[] localXYArray = BNAUtils.toXYArray(BNAUtils.getWorldToLocal(cm, t.getPoints()));
			BNAUtils.drawMarquee(g, r, t.getRotatingOffset(), false, new Runnable() {
				@Override
				public void run() {
					g.drawPolyline(localXYArray);
				}
			});
		}
	}

	@Override
	public void getLocalBounds(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r,
			org.eclipse.draw2d.geometry.Rectangle boundsResult) {
		super.getLocalBounds(view, cm, g, r, boundsResult);
		// width of marquee line
		boundsResult.expand(3, 3);
	}
}
