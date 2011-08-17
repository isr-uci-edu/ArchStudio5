package org.archstudio.bna.things.glass;

import java.awt.geom.Line2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class MappingGlassThingPeer<T extends MappingGlassThing> extends AbstractThingPeer<T> {

	public MappingGlassThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, Graphics g, Rectangle clip, ResourceUtils res) {
	}

	@Override
	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		Point ap = t.getAnchorPoint();

		// check that the point is within the vicinity of the anchor point
		// and world thing bounding box
		IThing worldThing = view.getBNAWorld().getBNAModel().getThing(t.getInternalEndpointWorldThingID());
		if (worldThing instanceof IHasBoundingBox) {
			Rectangle r = ((IHasBoundingBox) worldThing).getBoundingBox();
			BNAUtils.expandRectangle(r, ap);
			if (!r.contains(worldX, worldY)) {
				return false;
			}
		}

		IBNAView iView = BNAUtils.getInternalView(view, worldThing);
		if (iView != null) {
			ICoordinateMapper cm = view.getCoordinateMapper();
			ICoordinateMapper iCM = iView.getCoordinateMapper();

			// check that the point is not within the internally referenced thing
			IThing mappedThing = iView.getBNAWorld().getBNAModel().getThing(t.getInternalEndpointStuckToThingID());
			if (mappedThing != null) {
				IThingPeer mappedThingPeer = iView.getPeerCacheEntry(mappedThing);
				if (mappedThingPeer != null) {
					int iWorldX = BNAUtils.round(iCM.localXtoWorldX(cm.worldXtoLocalX((float) worldX)));
					int iWorldY = BNAUtils.round(iCM.localYtoWorldY(cm.worldYtoLocalY((float) worldY)));
					if (mappedThingPeer.isInThing(iView, iWorldX, iWorldY)) {
						return false;
					}
				}
			}

			// check that the point is on the line connecting the anchor point
			// and the internal point
			int localX = cm.worldXtoLocalX(worldX);
			int localY = cm.worldYtoLocalY(worldY);
			Point lp1 = BNAUtils.worldToLocal(cm, t.getAnchorPoint());
			Point lp2 = BNAUtils.worldToLocal(iCM, t.getInternalEndpoint());
			if (Math.abs(BNAUtils.round(Line2D.ptSegDist(lp1.x, lp1.y, lp2.x, lp2.y, localX, localY))) <= 5) {
				return true;
			}
		}

		return false;
	}
}
