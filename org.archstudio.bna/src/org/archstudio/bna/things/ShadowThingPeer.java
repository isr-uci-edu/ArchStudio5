package org.archstudio.bna.things;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.peers.IHasShadowPeer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.RGB;

import com.google.common.collect.Lists;

/**
 * Render all shadows at the same time. Otherwise, shadows do not overlap
 * correctly.
 */
public class ShadowThingPeer<T extends ShadowThing> extends AbstractThingPeer<T> implements IThingPeer<T> {

	public ShadowThingPeer(T thing) {
		super(thing);
	}

	private static int getOffset(ICoordinateMapper cm) {
		return (int) Math.ceil(cm.getLocalScale() + 2);
	}

	@Override
	public void draw(final IBNAView view, final ICoordinateMapper cm, final IResources r, final Graphics g) {
		// only draw for the top level things
		if (view.getParentView() != null) {
			return;
		}

		final List<IHasShadowPeer<?>> shadowThingPeers = Lists.newArrayListWithExpectedSize(view.getBNAWorld()
				.getBNAModel().getNumThings());
		for (IThing t : view.getBNAWorld().getBNAModel().getAllThings()) {
			IThingPeer<?> tp = view.getThingPeer(t);
			if (tp instanceof IHasShadowPeer) {
				IHasShadowPeer<?> stp = (IHasShadowPeer<?>) tp;
				shadowThingPeers.add(stp);
			}
		}

		if (!shadowThingPeers.isEmpty()) {
			final int offset = getOffset(cm);
			final float brightness = 0.85f;
			g.translate(offset, offset);
			g.setBackgroundColor(r.getColor(new RGB(0.0f, 0.0f, brightness)));
			for (IHasShadowPeer<?> tp : shadowThingPeers) {
				tp.drawShadow(view, cm, g, r);
			}
		}
	}

	@Override
	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r) {
		Rectangle bounds = new Rectangle(0, 0, 0, 0);
		bounds.x = bounds.y = Integer.MIN_VALUE / 2;
		bounds.width = bounds.height = Integer.MAX_VALUE;
		return bounds;
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}
}
