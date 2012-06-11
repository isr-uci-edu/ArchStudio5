package org.archstudio.xadl.bna.things;

import java.awt.geom.Point2D;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.things.glass.SplineGlassThingPeer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class MappingSplineGlassThingPeer<T extends MappingSplineGlassThing> extends SplineGlassThingPeer<T> {

	public MappingSplineGlassThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
//		if (t.isNeedsUpdate()) {
//			IBNAWorld outerWorld = view.getBNAWorld();
//			IBNAModel outerModel = outerWorld.getBNAModel();
//			outerWorld.getThingLogicManager().addThingLogic(MappingSplineGlassThingLogic.class);
//			ThingValueTrackingLogic outerTrackingLogic = outerWorld.getThingLogicManager().addThingLogic(
//					ThingValueTrackingLogic.class);
//			IThing outerWorldThing = outerModel.getThing(SystemUtils.firstOrNull(outerTrackingLogic.getThingIDs(
//					IHasObjRef.OBJREF_KEY, t.getWorldThingObjRef())));
//			if (outerWorldThing instanceof IHasWorld) {
//				IBNAWorld innerWorld = ((IHasWorld) outerWorldThing).getWorld();
//				if (innerWorld != null) {
//					IBNAModel innerModel = innerWorld.getBNAModel();
//					ThingValueTrackingLogic innerTrackingLogic = innerWorld.getThingLogicManager().addThingLogic(
//							ThingValueTrackingLogic.class);
//					IThing innerWorldThing = innerModel.getThing(SystemUtils.firstOrNull(innerTrackingLogic
//							.getThingIDs(IHasObjRef.OBJREF_KEY, t.getInternalThingObjRef())));
//					if (innerWorldThing instanceof IIsSticky) {
//						IBNAView innerView = ((IHasInnerViewPeer) view.getThingPeer(outerWorldThing)).getInnerView();
//						if (innerView != null) {
//							Point outerNearPoint = t.getPoint(1);
//							Point localNearPoint = view.getCoordinateMapper().worldToLocal(outerNearPoint);
//							Point innerNearPoint = innerView.getCoordinateMapper().localToWorld(localNearPoint);
//							Point innerStuckPoint = ((IIsSticky) innerWorldThing).getStickyPointNear(t.getStickyMode(),
//									innerNearPoint);
//							Point localStuckPoint = innerView.getCoordinateMapper().worldToLocal(innerStuckPoint);
//							Point outerStuckPoint = view.getCoordinateMapper().localToWorld(localStuckPoint);
//							t.setEndpoint1(outerStuckPoint);
//						}
//					}
//				}
//			}
//			t.setNeedsUpdate(false);
//		}
//
//		super.draw(view, cm, gl, clip, r);
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		Point le1 = cm.worldToLocal(t.getEndpoint1());
		Point lp = location.getLocalPoint();
		if (Point2D.distance(le1.x, le1.y, lp.x, lp.y) <= 5) {
			return false;
		}

		return super.isInThing(view, cm, location);
	}

}
