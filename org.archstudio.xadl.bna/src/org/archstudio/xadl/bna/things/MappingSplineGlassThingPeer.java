package org.archstudio.xadl.bna.things;

import java.awt.geom.Point2D;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.facets.peers.IHasInnerViewPeer;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.things.glass.SplineGlassThingPeer;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl.bna.logics.mapping.MappingSplineGlassThingLogic;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;

public class MappingSplineGlassThingPeer<T extends MappingSplineGlassThing> extends SplineGlassThingPeer<T> {

	public MappingSplineGlassThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, IResources r, Graphics g) {
		if (t.isNeedsUpdate()) {
			IBNAWorld outerWorld = view.getBNAWorld();
			IBNAModel outerModel = outerWorld.getBNAModel();
			outerWorld.getThingLogicManager().addThingLogic(MappingSplineGlassThingLogic.class);
			ThingValueTrackingLogic outerTrackingLogic = outerWorld.getThingLogicManager().addThingLogic(
					ThingValueTrackingLogic.class);
			IThing outerWorldThing = outerModel.getThing(SystemUtils.firstOrNull(outerTrackingLogic.getThingIDs(
					IHasObjRef.OBJREF_KEY, t.getWorldThingObjRef())));
			if (outerWorldThing instanceof IHasWorld) {
				IBNAWorld innerWorld = ((IHasWorld) outerWorldThing).getWorld();
				if (innerWorld != null) {
					IBNAModel innerModel = innerWorld.getBNAModel();
					ThingValueTrackingLogic innerTrackingLogic = innerWorld.getThingLogicManager().addThingLogic(
							ThingValueTrackingLogic.class);
					IThing innerWorldThing = innerModel.getThing(SystemUtils.firstOrNull(innerTrackingLogic
							.getThingIDs(IHasObjRef.OBJREF_KEY, t.getInternalThingObjRef())));
					if (innerWorldThing instanceof IIsSticky) {
						IBNAView innerView = ((IHasInnerViewPeer) view.getThingPeer(outerWorldThing)).getInnerView();
						if (innerView != null) {
							Point outerNearPoint = t.getPoint(1);
							Point localNearPoint = view.getCoordinateMapper().worldToLocal(outerNearPoint);
							Point innerNearPoint = innerView.getCoordinateMapper().localToWorld(localNearPoint);
							Point innerStuckPoint = ((IIsSticky) innerWorldThing).getStickyPointNear(t.getStickyMode(),
									innerNearPoint);
							Point localStuckPoint = innerView.getCoordinateMapper().worldToLocal(innerStuckPoint);
							Point outerStuckPoint = view.getCoordinateMapper().localToWorld(localStuckPoint);
							t.setEndpoint1(outerStuckPoint);
						}
					}
				}
			}
			t.setNeedsUpdate(false);
		}

		super.draw(view, cm, r, g);
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
