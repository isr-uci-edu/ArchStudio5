package org.archstudio.bna.things;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IRegion;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils.DrawShadow;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

import com.google.common.collect.Lists;

/**
 * Rendered all shadows at the same time. Otherwise, areas of shadow overlap are darker than they ought to be.
 */
public class ShadowThingPeer<T extends ShadowThing> extends AbstractThingPeer<T> {

	public static void expandForShadow(Rectangle boundsResult) {
		boundsResult.width += 6;
		boundsResult.height += 6;
	}

	public ShadowThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(final IBNAView view, final ICoordinateMapper cm, final Graphics g, final IResources r,
			IRegion localClip, IRegion worldClip) {

		Rectangle boundsResult = new Rectangle();
		final List<IHasShadowThingPeer<?>> shadowThingPeers = Lists.newArrayListWithExpectedSize(256);
		for (IThing t : view.getBNAWorld().getBNAModel().getThings()) {
			IThingPeer<?> tp = view.getThingPeer(t);
			if (tp instanceof IHasShadowThingPeer) {
				IHasShadowThingPeer<?> stp = (IHasShadowThingPeer<?>) tp;
				tp.getLocalBounds(view, cm, g, r, boundsResult);
				if (localClip.intersects(boundsResult)) {
					shadowThingPeers.add(stp);
				}
			}
		}

		if (!shadowThingPeers.isEmpty()) {
			BNAUtils.drawShadow(g, r, 1, 1, 2 + BNAUtils.round(cm.getLocalScale() * 2), new DrawShadow() {
				@Override
				public void drawShadow(boolean fill) {
					for (IHasShadowThingPeer<?> tp : shadowThingPeers) {
						tp.drawShadow(view, cm, g, r, fill);
					}
				}
			});
		}
	}

	@Override
	public void getLocalBounds(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, Rectangle boundsResult) {
		boundsResult.x = boundsResult.y = Integer.MIN_VALUE / 2;
		boundsResult.width = boundsResult.height = Integer.MAX_VALUE;
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}
}
