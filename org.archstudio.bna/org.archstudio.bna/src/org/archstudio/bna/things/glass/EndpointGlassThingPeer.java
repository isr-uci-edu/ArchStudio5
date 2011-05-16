package org.archstudio.bna.things.glass;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IRegion;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.things.AbstractEndpointThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class EndpointGlassThingPeer<T extends EndpointGlassThing> extends AbstractEndpointThingPeer<T> {

	public EndpointGlassThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, final Graphics g, IResources r, IRegion localClip,
			IRegion worldClip) {
		if (!worldClip.intersects(t.getBoundingBox())) {
			return;
		}

		if (Boolean.TRUE.equals(t.get(IHasSelected.SELECTED_KEY))) {
			final Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
			BNAUtils.drawMarquee(g, r, t.getRotatingOffset(), false, new Runnable() {
				@Override
				public void run() {
					g.drawRectangle(lbb);
				}
			});
		}
	}
}
