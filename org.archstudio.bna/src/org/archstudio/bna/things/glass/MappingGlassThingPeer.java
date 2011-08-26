package org.archstudio.bna.things.glass;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;

public class MappingGlassThingPeer extends SplineGlassThingPeer<MappingGlassThing> {

	public MappingGlassThingPeer(MappingGlassThing t) {
		super(t);
	}

	//protected WorldThing getViewThing(IBNAView outerView) {
	//	String viewThingID = t.getWorldThingID();
	//	if (viewThingID != null) {
	//		IThing potentialViewThing = outerView.getWorld().getBNAModel().getThing(viewThingID);
	//		if ((potentialViewThing != null) && (potentialViewThing instanceof WorldThing)) {
	//			return (WorldThing) potentialViewThing;
	//		}
	//	}
	//	return null;
	//}
	//
	//protected IBNAView getInternalView(IBNAView outerView, WorldThing vt) {
	//	if (vt == null)
	//		return null;
	//	WorldThingPeer vtp = (WorldThingPeer) outerView.getPeer(vt);
	//	if (vtp == null)
	//		return null;
	//	IBNAView internalView = vtp.getInnerView();
	//	//IBNAView internalView = ((IHasWorld)vt).getView();
	//	if (internalView != null) {
	//		return internalView;
	//	}
	//	return null;
	//}
	//
	//protected void updateLocalBoundingBox(IBNAView view, ICoordinateMapper cm) {
	//	int minX = Integer.MAX_VALUE;
	//	int minY = Integer.MAX_VALUE;
	//	int maxX = Integer.MIN_VALUE;
	//	int maxY = Integer.MIN_VALUE;
	//
	//	updateLocalPoints(view, cm);
	//	for (int i = 0; i < localPoints.length; i += 2) {
	//		int x = localPoints[i];
	//		int y = localPoints[i + 1];
	//		if (x < minX)
	//			minX = x;
	//		if (x > maxX)
	//			maxX = x;
	//		if (y < minY)
	//			minY = y;
	//		if (y > maxY)
	//			maxY = y;
	//	}
	//	localBoundingBox = new Rectangle(minX, minY, maxX - minX, maxY - minY);
	//	//localBoundingBox =  BNAUtils.worldToLocal(cm, BNAUtils.normalizeRectangle(lt.getBoundingBox()));
	//}
	//
	//protected void updateLocalPoints(IBNAView view, ICoordinateMapper cm) {
	//	super.updateLocalPoints(view, cm);
	//
	//	Point wp2 = t.getEndpoint2();
	//	if (wp2 == null)
	//		wp2 = new Point(0, 0);
	//
	//	WorldThing vt = getViewThing(view);
	//	IBNAView internalView = getInternalView(view, vt);
	//	if (internalView != null) {
	//		WorldThingPeer.setupCoordinateMapper(vt, cm, cm2);
	//	}
	//
	//	//Here, we calculate endpoint 2 in cm2's coordinate system
	//	localPoints[localPoints.length - 2] = cm2.worldXtoLocalX(wp2.x);
	//	localPoints[localPoints.length - 1] = cm2.worldYtoLocalY(wp2.y);
	//}

	/*
	 * The purpose of this routine is to reduce the hitbox around the inner
	 * endpoint of the mapping, because otherwise it sits on top of its endpoint
	 * and doesn't let you click or manipulate the endpoint. It can't be on top
	 * of the view and underneath the endpoint at the same time.
	 */
	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		if (super.isInThing(view, cm, location)) {
			//TODO: reinstate
			//Point localPoint = location.getLocalPoint(new Point());
			//int d = BNAUtils.round(5.0d * cm2.getScale());
			//if ((java.lang.Math.abs(localX - localPoints[localPoints.length - 2])) <= d) {
			//	if ((java.lang.Math.abs(localY - localPoints[localPoints.length - 1])) <= d) {
			//		return false;
			//	}
			//}
			return true;
		}
		return false;
	}
}
