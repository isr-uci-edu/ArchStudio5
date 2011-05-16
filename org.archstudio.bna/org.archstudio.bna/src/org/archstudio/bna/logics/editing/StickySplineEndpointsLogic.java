package org.archstudio.bna.logics.editing;

import org.archstudio.bna.facets.IHasStickyBox;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingTypeTrackingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils.PointToRectangleDistanceData;
import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class StickySplineEndpointsLogic extends AbstractThingLogic implements IEndpointMovedListener {

	protected ThingTypeTrackingLogic ttstl = null;
	protected SplineReshapeHandleLogic srhl = null;

	public StickySplineEndpointsLogic(ThingTypeTrackingLogic ttstl, SplineReshapeHandleLogic srhl) {
		this.ttstl = ttstl;
		this.srhl = srhl;
	}

	protected void init() {
		srhl.addEndpointMovedListener(this);
	}

	protected void destroy() {
		srhl.removeEndpointMovedListener(this);
	}

	public void endpointMoved(EndpointMovedEvent evt) {
		if (!(evt.getTargetThing() instanceof IHasMutableStickyEndpoints)) {
			return;
		}
		IHasStickyBox[] stickyBoxThings = ttstl.getThings();
		IHasStickyBox closestThing = null;
		Rectangle closestRect = null;
		double closestDist = Double.MAX_VALUE;
		Orientation side = Orientation.NONE;

		Point p = new Point(evt.worldX, evt.worldY);
		for (IHasStickyBox t : stickyBoxThings) {
			Rectangle r = t.getStickyBox();
			if (r != null) {
				PointToRectangleDistanceData dd = BNAUtils.getPointToRectangleDistance(p, r);
				if (dd.dist < closestDist) {
					closestThing = t;
					closestRect = r;
					closestDist = dd.dist;
					side = dd.closestSide;
				}
			}
		}
		if (closestDist <= 8) {
			Point np = BNAUtils.snapToNormal(new Point(evt.worldX, evt.worldY), closestRect, side);
			if (evt.getEndpointNum() == 1) {
				evt.getTargetThing().setEndpoint1(np);
				((IHasMutableStickyEndpoints) evt.getTargetThing()).setEndpoint1StuckToThingID(closestThing.getID());
			}
			else if (evt.getEndpointNum() == 2) {
				evt.getTargetThing().setEndpoint2(np);
				((IHasMutableStickyEndpoints) evt.getTargetThing()).setEndpoint2StuckToThingID(closestThing.getID());
			}
		}
		else {
			if (evt.getEndpointNum() == 1) {
				((IHasMutableStickyEndpoints) evt.getTargetThing()).setEndpoint1StuckToThingID(null);
			}
			else if (evt.getEndpointNum() == 2) {
				((IHasMutableStickyEndpoints) evt.getTargetThing()).setEndpoint2StuckToThingID(null);
			}
		}
	}

}
