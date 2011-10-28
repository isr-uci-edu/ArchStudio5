package org.archstudio.bna.things;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils.DrawShadow;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

import com.google.common.collect.Lists;

/**
 * Render all shadows at the same time. Otherwise, shadows do not overlap
 * correctly.
 */
public class ShadowThingPeer<T extends ShadowThing> extends AbstractThingPeer<T> {

	public ShadowThingPeer(T thing) {
		super(thing);
	}

	private static int getOffset(ICoordinateMapper cm) {
		return (int) Math.ceil(cm.getLocalScale() + 2);
	}

	private static int getSize(ICoordinateMapper cm) {
		return (int) Math.ceil(cm.getLocalScale() + 2);
	}

	public static void expandForShadow(ICoordinateMapper cm, Rectangle boundsResult) {
		if (!boundsResult.isEmpty()) {
			final int offset = getOffset(cm);
			final int size = getSize(cm);
			final int minInset = offset - size;
			final int maxInset = offset + size;
			boundsResult.x += minInset;
			boundsResult.y += minInset;
			boundsResult.width += maxInset - minInset;
			boundsResult.height += maxInset - minInset;
		}
	}

	@Override
	public void draw(final IBNAView view, final ICoordinateMapper cm, final Graphics g, final IResources r) {
		// only draw for the top level things
		if (view.getParentView() != null) {
			return;
		}

		if (g.getAdvanced()) {
			Rectangle boundsResult = new Rectangle();
			final List<IHasShadowPeer<?>> shadowThingPeers = Lists.newArrayListWithExpectedSize(256);
			for (IThing t : view.getBNAWorld().getBNAModel().getAllThings()) {
				IThingPeer<?> tp = view.getThingPeer(t);
				if (tp instanceof IHasShadowPeer) {
					IHasShadowPeer<?> stp = (IHasShadowPeer<?>) tp;
					tp.getLocalBounds(view, cm, r, boundsResult);
					shadowThingPeers.add(stp);
				}
			}

			final int offset = getOffset(cm);
			final int size = getSize(cm);
			if (!shadowThingPeers.isEmpty()) {
				BNAUtils.drawShadow(g, r, offset, offset, size, 2, new DrawShadow() {
					@Override
					public void drawShadow(boolean fill) {
						for (IHasShadowPeer<?> tp : shadowThingPeers) {
							tp.drawShadow(view, cm, g, r, fill);
						}
					}
				});
			}
		}
	}

	@Override
	public void getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r, Rectangle boundsResult) {
		boundsResult.x = boundsResult.y = Integer.MIN_VALUE / 2;
		boundsResult.width = boundsResult.height = Integer.MAX_VALUE;
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}
}
