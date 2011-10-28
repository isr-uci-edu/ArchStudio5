package org.archstudio.bna.things.utility;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.LinearCoordinateMapper;
import org.archstudio.bna.facets.peers.IHasInnerViewPeer;
import org.archstudio.bna.logics.tracking.ModelBoundsTrackingLogic;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.DefaultBNAView;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class WorldThingPeer<T extends WorldThing> extends AbstractRectangleThingPeer<T> implements IThingPeer<T>,
		IHasInnerViewPeer {
	protected IBNAView innerView = null;

	protected boolean localBoundingBoxChanged = false;
	protected Rectangle localBoundingBox = BNAUtils.NONEXISTENT_RECTANGLE;

	public WorldThingPeer(T thing) {
		super(thing);
	}

	@Override
	public IBNAView getInnerView() {
		return innerView;
	}

	protected void updateLocalBoundingBox(ICoordinateMapper cm) {
		Rectangle lastLocalBoundingBox = localBoundingBox;
		localBoundingBox = cm.worldToLocal(BNAUtils.normalizeRectangle(t.getBoundingBox()));
		if (!lastLocalBoundingBox.equals(localBoundingBox)) {
			localBoundingBoxChanged = true;
		}
	}

	protected Rectangle lastModelBounds = BNAUtils.NONEXISTENT_RECTANGLE;
	protected Rectangle clip = new Rectangle(0, 0, 0, 0);

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {
		IBNAWorld innerWorld = t.getWorld();
		if (innerWorld == null) {
			return;
		}

		if (innerView != null && !innerWorld.equals(innerView.getBNAWorld())) {
			innerView = null;
		}

		if (innerView == null) {
			innerView = new DefaultBNAView(view, t.getWorld(), new LinearCoordinateMapper());
		}

		updateLocalBoundingBox(cm);
		if (localBoundingBox.height < 5 || localBoundingBox.width < 5) {
			return;
		}

		ModelBoundsTrackingLogic mbtl = innerWorld.getThingLogicManager().addThingLogic(ModelBoundsTrackingLogic.class);
		Rectangle modelBounds = mbtl.getModelBounds();
		if (modelBounds == null) {
			return;
		}

		if (modelBounds.x == Integer.MIN_VALUE || modelBounds.width == 0 || modelBounds.height == 0) {
			Rectangle worldBounds = innerView.getCoordinateMapper().getWorldBounds(new Rectangle());
			modelBounds.x = worldBounds.x + worldBounds.width / 2;
			modelBounds.y = worldBounds.y + worldBounds.height / 2;
			modelBounds.width = 100;
			modelBounds.height = 100;
		}
		modelBounds.expand(1, 1);

		ICoordinateMapper icm = innerView.getCoordinateMapper();

		if (localBoundingBoxChanged || !SystemUtils.nullEquals(modelBounds, lastModelBounds)) {
			if (icm instanceof IMutableCoordinateMapper) {
				IMutableCoordinateMapper imcm = (IMutableCoordinateMapper) icm;
				double sx = (double) localBoundingBox.width / (double) modelBounds.width;
				double sy = (double) localBoundingBox.height / (double) modelBounds.height;
				double parentScale = view.getCoordinateMapper().getLocalScale();
				double scale = Math.min(parentScale, Math.min(sx, sy));
				int dx = BNAUtils.round((localBoundingBox.width - modelBounds.width * scale) / 2);
				int dy = BNAUtils.round((localBoundingBox.height - modelBounds.height * scale) / 2);
				imcm.setLocalScaleAndAlign(scale, localBoundingBox.getTopLeft().translate(dx, dy),
						modelBounds.getTopLeft());
			}
		}
		localBoundingBoxChanged = false;
		lastModelBounds = modelBounds;

		g.pushState();
		try {
			for (IThing thing : innerView.getBNAWorld().getBNAModel().getAllThings()) {
				IThingPeer<?> peer = innerView.getThingPeer(thing);
				g.restoreState();
				peer.draw(innerView, icm, g, r);
			}
		}
		finally {
			g.popState();
		}
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate coordinate) {
		if (t.getWorld() != null && innerView != null) {
			return super.isInThing(view, cm, coordinate);
		}
		return false;
	}

}
