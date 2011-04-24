package org.archstudio.bna.logics.editing;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.BNAUtils.PointToRectangleDistanceData;
import org.archstudio.bna.facets.IHasMutableStickyEndpoints;
import org.archstudio.bna.facets.IHasStickyBox;
import org.archstudio.bna.logics.tracking.TypedThingSetTrackingLogic;
import org.archstudio.swtutils.constants.Orientation;

public class StickySplineEndpointsLogic extends AbstractThingLogic implements IEndpointMovedListener {

	protected TypedThingSetTrackingLogic<IHasStickyBox> ttstl = null;
	protected SplineReshapeHandleLogic srhl = null;

	public StickySplineEndpointsLogic(TypedThingSetTrackingLogic<IHasStickyBox> ttstl, SplineReshapeHandleLogic srhl) {
		this.ttstl = ttstl;
		this.srhl = srhl;
	}

	public void init() {
		srhl.addEndpointMovedListener(this);
	}

	public void destroy() {
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
