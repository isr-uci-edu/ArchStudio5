package org.archstudio.bna.things.utility;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThingPeer;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.DefaultBNAView;
import org.archstudio.bna.DefaultCoordinateMapper;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;

public class WorldThingPeer extends AbstractThingPeer implements IThingPeer {
	protected WorldThing lt;
	protected IBNAView innerView = null;

	protected boolean localBoundingBoxChanged = false;
	protected Rectangle localBoundingBox = BNAUtils.NONEXISTENT_RECTANGLE;

	public WorldThingPeer(IThing t) {
		super(t);
		if (!(t instanceof WorldThing)) {
			throw new IllegalArgumentException("WorldThingPeer can only peer for WorldThing");
		}
		this.lt = (WorldThing) t;
	}

	public IBNAView getInnerView() {
		return innerView;
	}

	protected void updateLocalBoundingBox(ICoordinateMapper cm) {
		Rectangle lastLocalBoundingBox = localBoundingBox;
		localBoundingBox = BNAUtils.worldToLocal(cm, BNAUtils.normalizeRectangle(lt.getBoundingBox()));
		if (!lastLocalBoundingBox.equals(localBoundingBox))
			localBoundingBoxChanged = true;
	}

	protected Rectangle lastModelBounds = BNAUtils.NONEXISTENT_RECTANGLE;
	protected Rectangle clip = new Rectangle(0, 0, 0, 0);

	public static void setupCoordinateMapper(WorldThing vt, ICoordinateMapper cm, IMutableCoordinateMapper icm) {
		IBNAWorld innerWorld = vt.getWorld();
		if (innerWorld != null) {
			Rectangle localBoundingBox = BNAUtils.worldToLocal(cm, BNAUtils.normalizeRectangle(vt.getBoundingBox()));
			EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(innerWorld.getBNAModel());
			if (ept == null)
				return;
			Rectangle modelBounds = (Rectangle) ept.getProperty("#modelBounds");
			if (modelBounds == null) {
				return;
			}

			double sx = (double) localBoundingBox.width / (double) modelBounds.width;
			double sy = (double) localBoundingBox.height / (double) modelBounds.height;
			double s = Math.min(sx, sy);
			icm.rescaleAbsolute(s);

			double ddx = (s == sx) ? 0.0d : ((localBoundingBox.width / s) - modelBounds.width) / 2.0d;
			double ddy = (s == sy) ? 0.0d : ((localBoundingBox.height / s) - modelBounds.height) / 2.0d;

			int dx = BNAUtils.round(ddx);
			int dy = BNAUtils.round(ddy);

			icm.repositionAbsolute(modelBounds.x - BNAUtils.round(localBoundingBox.x / s) - dx, modelBounds.y - BNAUtils.round(localBoundingBox.y / s) - dy);
		}
	}

	public void draw(IBNAView view, GC g) {
		IBNAWorld innerWorld = lt.getWorld();
		if (innerWorld == null)
			return;

		if ((innerView != null) && (!innerWorld.equals(innerView.getWorld()))) {
			innerView = null;
		}

		if (innerView == null) {
			innerView = new DefaultBNAView(view, lt.getWorld(), new DefaultCoordinateMapper());
		}

		updateLocalBoundingBox(view.getCoordinateMapper());
		if ((localBoundingBox.height < 5) || (localBoundingBox.width < 5)) {
			return;
		}

		EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(innerView.getWorld().getBNAModel());
		if (ept == null)
			return;
		Rectangle modelBounds = (Rectangle) ept.getProperty("#modelBounds");
		if (modelBounds == null) {
			return;
		}
		if ((modelBounds.x == Integer.MIN_VALUE) || (modelBounds.width == 0) || (modelBounds.height == 0)) {
			modelBounds.x = innerView.getCoordinateMapper().getWorldCenterX();
			modelBounds.y = innerView.getCoordinateMapper().getWorldCenterY();
			modelBounds.width = 100;
			modelBounds.height = 100;
		}

		if (localBoundingBoxChanged || !BNAUtils.nulleq(modelBounds, lastModelBounds)) {
			ICoordinateMapper cm = innerView.getCoordinateMapper();
			if (cm instanceof IMutableCoordinateMapper) {
				IMutableCoordinateMapper mcm = (IMutableCoordinateMapper) cm;

				double sx = (double) localBoundingBox.width / (double) modelBounds.width;
				double sy = (double) localBoundingBox.height / (double) modelBounds.height;
				double s = Math.min(sx, sy);
				mcm.rescaleAbsolute(s);

				double ddx = (s == sx) ? 0.0d : ((localBoundingBox.width / s) - modelBounds.width) / 2.0d;
				double ddy = (s == sy) ? 0.0d : ((localBoundingBox.height / s) - modelBounds.height) / 2.0d;

				int dx = BNAUtils.round(ddx);
				int dy = BNAUtils.round(ddy);

				mcm
				        .repositionAbsolute(modelBounds.x - BNAUtils.round(localBoundingBox.x / s) - dx, modelBounds.y - BNAUtils.round(localBoundingBox.y / s)
				                - dy);
			}
		}
		localBoundingBoxChanged = false;
		lastModelBounds = modelBounds;

		clip.x = localBoundingBox.x;
		clip.y = localBoundingBox.y;
		clip.width = localBoundingBox.width + 1;
		clip.height = localBoundingBox.height + 1;
		g.setClipping(clip);
		for (IThing thing : innerView.getWorld().getBNAModel().getAllThings()) {
			IThingPeer peer = innerView.getPeer(thing);
			peer.draw(innerView, g);
		}
		g.setClipping((Rectangle) null);
	}

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		IBNAWorld innerWorld = lt.getWorld();
		if (innerWorld == null)
			return false;

		if (innerView == null) {
			innerView = new DefaultBNAView(view, lt.getWorld(), new DefaultCoordinateMapper());
		}

		Rectangle boundingBox = lt.getBoundingBox();
		if (boundingBox != null) {
			return boundingBox.contains(worldX, worldY);
		}
		return false;
	}
}
