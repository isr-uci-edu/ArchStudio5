package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasStickyBox;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingTypeTrackingLogic;
import org.archstudio.bna.things.glass.MappingGlassThing;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils.PointToRectangleDistanceData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class MaintainMappingEndpointsLogic extends AbstractThingLogic implements IInternalBNAModelListener,
		IThingSetListener, IBNASynchronousModelListener {

	protected ThingRefTrackingLogic trtl = null;
	protected ThingTypeTrackingLogic ttstl = null;
	protected WorldThingInternalEventsLogic vtiel = null;

	public MaintainMappingEndpointsLogic(ThingRefTrackingLogic trtl, ThingTypeTrackingLogic ttstl,
			WorldThingInternalEventsLogic vtiel) {
		this.trtl = trtl;
		this.ttstl = ttstl;
		this.vtiel = vtiel;
	}

	protected void init() {
		ttstl.addThingSetListener(this);
		vtiel.addInternalBNAModelListener(this);
		checkAllEndpoints();
	}

	protected void destroy() {
		vtiel.removeInternalBNAModelListener(this);
		ttstl.removeThingSetListener(this);
	}

	protected void checkAllEndpoints() {
		MappingGlassThing[] ts = ttstl.getThings();
		for (MappingGlassThing t : ts) {
			checkEndpoints(t);
		}
	}

	protected void checkEndpoints(MappingGlassThing t) {
		IBNAModel m = getBNAModel();
		if (m != null) {
			String externalEndpointStuckToThingID = t.getExternalEndpointStuckToThingID();
			Point externalPoint = t.getEndpoint1();
			IThing externalEndpointStuckToThing = m.getThing(externalEndpointStuckToThingID);
			if ((externalEndpointStuckToThing != null) && (externalEndpointStuckToThing instanceof IHasStickyBox)) {
				Rectangle sb = ((IHasStickyBox) externalEndpointStuckToThing).getStickyBox();
				if (sb != null) {
					if (!BNAUtils.isPointOnRectangle(externalPoint, sb)) {
						// The endpoint is stuck, but its endpoint is not on the sticky box.
						PointToRectangleDistanceData dd = BNAUtils.getPointToRectangleDistance(externalPoint, sb);
						Point newEndpoint = BNAUtils.snapToNormal(externalPoint, sb, dd.closestSide);
						t.setEndpoint1(newEndpoint);
					}
				}
			}

			String viewThingID = t.getWorldThingID();
			if (viewThingID != null) {
				IThing possibleViewThing = m.getThing(viewThingID);
				if (possibleViewThing instanceof IHasWorld) {
					IHasWorld vt = (IHasWorld) possibleViewThing;
					IBNAWorld internalWorld = vt.getWorld();
					if (internalWorld != null) {
						IBNAModel im = internalWorld.getBNAModel();
						if (im != null) {
							String internalEndpointStuckToThingID = t.getInternalEndpointStuckToThingID();
							Point internalPoint = t.getEndpoint2();
							IThing internalEndpointStuckToThing = im.getThing(internalEndpointStuckToThingID);
							if ((internalEndpointStuckToThing != null)
									&& (internalEndpointStuckToThing instanceof IHasStickyBox)) {
								Rectangle sb = ((IHasStickyBox) internalEndpointStuckToThing).getStickyBox();
								if (sb != null) {
									if (!BNAUtils.isPointOnRectangle(internalPoint, sb)) {
										// The endpoint is stuck, but its endpoint is not on the sticky box.
										PointToRectangleDistanceData dd = BNAUtils.getPointToRectangleDistance(
												internalPoint, sb);
										Point newEndpoint = BNAUtils.snapToNormal(internalPoint, sb, dd.closestSide);
										t.setEndpoint2(newEndpoint);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public void bnaModelChangedSync(BNAModelEvent evt) {
		IThing t = evt.getTargetThing();
		if (t instanceof MappingGlassThing) {
			MappingGlassThing st = (MappingGlassThing) t;
			ThingEvent te = evt.getThingEvent();
			if (te != null) {
				String propertyName = te.getPropertyName();
				if (propertyName != null) {
					if (propertyName.equals(MappingGlassThing.EXTERNAL_ENDPOINT_STUCK_TO_THING_ID_KEY)
							|| propertyName.equals(MappingGlassThing.WORLD_THING_ID)
							|| propertyName.equals(MappingGlassThing.INTERNAL_ENDPOINT_STUCK_TO_THING_ID_KEY)) {
						checkEndpoints(st);
					}
					if (propertyName.equals(IHasEndpoints.ENDPOINT_1_KEY)
							|| propertyName.equals(IHasEndpoints.ENDPOINT_2_KEY)) {
						checkEndpoints(st);
					}
				}
			}
		}
		//TODO This needs to be made more efficient by tracking which
		//sticky boxes are associated with which mgt
		if (t instanceof IHasStickyBox) {
			String stickyThingID = t.getID();
			MappingGlassThing[] mgts = ttstl.getThings();
			for (MappingGlassThing mgt : mgts) {
				String externalEndpointStuckToThingID = mgt.getExternalEndpointStuckToThingID();
				if (BNAUtils.nulleq(externalEndpointStuckToThingID, stickyThingID)) {
					checkEndpoints(mgt);
				}
			}
		}
	}

	public void internalBNAModelChanged(IHasWorld src, BNAModelEvent evt) {
		//This method intentionally left blank
	}

	public void internalBNAModelChangedSync(IHasWorld src, BNAModelEvent evt) {
		IThing t = evt.getTargetThing();
		if (t instanceof IHasStickyBox) {
			String stickyThingID = t.getID();
			MappingGlassThing[] mgts = ttstl.getThings();
			for (MappingGlassThing mgt : mgts) {
				String internalEndpointStuckToThingID = mgt.getInternalEndpointStuckToThingID();
				if (BNAUtils.nulleq(internalEndpointStuckToThingID, stickyThingID)) {
					checkEndpoints(mgt);
				}
			}
		}
	}

	public void thingSetChanged(ThingSetChangedEvent evt) {
		EventType et = evt.getEventType();
		if (et == EventType.THING_ADDED) {
			MappingGlassThing t = (MappingGlassThing) evt.getTargetThing();
			checkEndpoints(t);
		}
	}
}
