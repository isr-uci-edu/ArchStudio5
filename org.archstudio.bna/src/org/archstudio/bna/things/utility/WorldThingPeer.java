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
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.DefaultBNAView;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class WorldThingPeer<T extends WorldThing> extends AbstractThingPeer<T> implements IThingPeer<T> {
	protected IBNAView innerView = null;

	protected boolean localBoundingBoxChanged = false;
	protected Rectangle localBoundingBox = BNAUtils.NONEXISTENT_RECTANGLE;

	public WorldThingPeer(T thing) {
		super(thing);
	}

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

	public static void setupCoordinateMapper(WorldThing vt, ICoordinateMapper cm, IMutableCoordinateMapper icm) {
		IBNAWorld innerWorld = vt.getWorld();
		if (innerWorld != null) {
			Rectangle localBoundingBox = cm.worldToLocal(BNAUtils.normalizeRectangle(vt.getBoundingBox()));
			EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(innerWorld.getBNAModel());
			if (ept == null) {
				return;
			}
			
			Rectangle modelBounds = ept.getModelBounds();
			if (modelBounds == null) {
				return;
			}

			double sx = (double) localBoundingBox.width / (double) modelBounds.width;
			double sy = (double) localBoundingBox.height / (double) modelBounds.height;
			double s = Math.min(sx, sy);
			icm.setLocalScale(s);

			double ddx = (s == sx) ? 0.0d : ((localBoundingBox.width / s) - modelBounds.width) / 2.0d;
			double ddy = (s == sy) ? 0.0d : ((localBoundingBox.height / s) - modelBounds.height) / 2.0d;

			int dx = BNAUtils.round(ddx);
			int dy = BNAUtils.round(ddy);

			icm.setLocalOrigin(new Point(modelBounds.x - BNAUtils.round(localBoundingBox.x / s) - dx, modelBounds.y - BNAUtils.round(localBoundingBox.y / s) - dy));
		}
	}

	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {
		IBNAWorld innerWorld = t.getWorld();
		if (innerWorld == null) {
			return;
		}

		if ((innerView != null) && (!innerWorld.equals(innerView.getBNAWorld()))) {
			innerView = null;
		}

		if (innerView == null) {
			innerView = new DefaultBNAView(view.getControl(), view, t.getWorld(), new LinearCoordinateMapper());
		}

		updateLocalBoundingBox(view.getCoordinateMapper());
		if ((localBoundingBox.height < 5) || (localBoundingBox.width < 5)) {
			return;
		}

		EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(innerView.getBNAWorld().getBNAModel());
		if (ept == null) {
			return;
		}
		
		Rectangle modelBounds = ept.getModelBounds();
		if (modelBounds == null) {
			return;
		}
		
		if ((modelBounds.x == Integer.MIN_VALUE) || (modelBounds.width == 0) || (modelBounds.height == 0)) {
			Rectangle worldBounds = new Rectangle();
			innerView.getCoordinateMapper().getWorldBounds(worldBounds);
			modelBounds.x = worldBounds.x + (worldBounds.width / 2);
			modelBounds.y = worldBounds.y + (worldBounds.height / 2);
			
			modelBounds.width = 100;
			modelBounds.height = 100;
		}

		if (localBoundingBoxChanged || !SystemUtils.nullEquals(modelBounds, lastModelBounds)) {
			ICoordinateMapper icm = innerView.getCoordinateMapper();
			if (icm instanceof IMutableCoordinateMapper) {
				IMutableCoordinateMapper imcm = (IMutableCoordinateMapper) icm;

				double sx = (double) localBoundingBox.width / (double) modelBounds.width;
				double sy = (double) localBoundingBox.height / (double) modelBounds.height;
				double s = Math.min(sx, sy);
				imcm.setLocalScale(s);

				double ddx = (s == sx) ? 0.0d : ((localBoundingBox.width / s) - modelBounds.width) / 2.0d;
				double ddy = (s == sy) ? 0.0d : ((localBoundingBox.height / s) - modelBounds.height) / 2.0d;

				int dx = BNAUtils.round(ddx);
				int dy = BNAUtils.round(ddy);

				imcm.setLocalOrigin(new Point(modelBounds.x - BNAUtils.round(localBoundingBox.x / s) - dx, modelBounds.y - BNAUtils.round(localBoundingBox.y / s) - dy));
			}
		}
		localBoundingBoxChanged = false;
		lastModelBounds = modelBounds;

		clip.x = localBoundingBox.x;
		clip.y = localBoundingBox.y;
		clip.width = localBoundingBox.width + 1;
		clip.height = localBoundingBox.height + 1;
		
		Rectangle oldClip = new Rectangle();
		g.getClip(oldClip);
		
		g.setClip(clip);
		for (IThing thing : innerView.getBNAWorld().getBNAModel().getThings()) {
			IThingPeer<?> peer = innerView.getThingPeer(thing);
			peer.draw(innerView, innerView.getCoordinateMapper(), g, r);
		}
		g.setClip(oldClip);
	}

	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate coordinate) {
		IBNAWorld innerWorld = t.getWorld();
		if (innerWorld == null) {
			return false;
		}

		if (innerView == null) {
			return false;
		}

		Rectangle boundingBox = t.getBoundingBox();
		if (boundingBox != null) {
			return boundingBox.contains(coordinate.getWorldPoint(new Point()));
		}
		return false;
	}
	
	@Override
	public void getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r, Rectangle boundsResult) {
		cm.worldToLocal(boundsResult.setBounds(t.getBoundingBox()));
	}
}
