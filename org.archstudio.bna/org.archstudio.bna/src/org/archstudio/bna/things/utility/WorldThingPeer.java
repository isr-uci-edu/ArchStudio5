package org.archstudio.bna.things.utility;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.logics.tracking.ModelBoundsTrackingLogic;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.DefaultBNAView;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class WorldThingPeer<T extends WorldThing> extends AbstractThingPeer<T> implements IThingPeer {

	protected IBNAView innerView = null;

	public WorldThingPeer(T thing) {
		super(thing);
	}

	public IBNAView getInnerView() {
		return innerView;
	}

	protected void updateInnerView(IBNAView view) {
		IBNAWorld innerWorld = t.getWorld();
		if (innerWorld == null || innerView != null && !innerWorld.equals(innerView.getBNAWorld())) {
			innerView = null;
		}
		if (innerView == null && innerWorld != null) {
			innerView = new DefaultBNAView(view, innerWorld, new DefaultCoordinateMapper());
		}
	}

	protected Rectangle lastLocalBoundingBox = null;
	protected Rectangle lastModelBounds = null;

	@Override
	public void draw(IBNAView view, Graphics g, Rectangle clip, ResourceUtils res) {
		updateInnerView(view);
		if (innerView == null) {
			return;
		}

		Rectangle bb = t.getBoundingBox();
		Rectangle lbb = BNAUtils.worldToLocal(view.getCoordinateMapper(), bb);

		Rectangle modelBounds = ModelBoundsTrackingLogic.getModelBounds(innerView.getBNAWorld());
		if (modelBounds == null || modelBounds.width <= 0 || modelBounds.height <= 0) {
			return;
		}

		if (!BNAUtils.nulleq(lastLocalBoundingBox, lbb) || !BNAUtils.nulleq(lastModelBounds, modelBounds)) {
			ICoordinateMapper icm = innerView.getCoordinateMapper();
			if (icm instanceof IMutableCoordinateMapper) {
				IMutableCoordinateMapper mcm = (IMutableCoordinateMapper) icm;

				double sx = (double) lbb.width / (modelBounds.width + 2);
				double sy = (double) lbb.height / (modelBounds.height + 2);
				double s = Math.min(Math.min(sx, sy), 1.0d);
				mcm.rescaleAbsolute(s);

				double ddx = s == sx ? 0.0d : (lbb.width / s - modelBounds.width) / 2.0d;
				double ddy = s == sy ? 0.0d : (lbb.height / s - modelBounds.height) / 2.0d;

				int dx = BNAUtils.round(ddx);
				int dy = BNAUtils.round(ddy);

				mcm.repositionAbsolute(modelBounds.x - BNAUtils.round(lbb.x / s) - dx,
						modelBounds.y - BNAUtils.round(lbb.y / s) - dy);
			}
		}
		lastLocalBoundingBox = lbb;
		lastModelBounds = modelBounds;

		if (!clip.intersects(lbb) || lbb.width < 5 || lbb.height < 5) {
			return;
		}

		Rectangle iclip = clip.getIntersection(lbb);
		g.setClip(iclip);
		BNAUtils.renderWorld(innerView, g, iclip, res);
	}

	@Override
	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		updateInnerView(view);
		if (innerView == null) {
			return false;
		}

		return t.getBoundingBox().contains(worldX, worldY);
	}
}
