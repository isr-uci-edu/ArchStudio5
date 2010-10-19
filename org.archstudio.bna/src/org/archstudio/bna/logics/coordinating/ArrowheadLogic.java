package org.archstudio.bna.logics.coordinating;

import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMidpoints;
import org.archstudio.bna.facets.IMirrorsEndpoint;
import org.archstudio.bna.logics.tracking.EndpointsChangedEvent;
import org.archstudio.bna.logics.tracking.EndpointsTrackingLogic;
import org.archstudio.bna.logics.tracking.IEndpointsTrackingListener;
import org.archstudio.bna.logics.tracking.IMidpointsTrackingListener;
import org.archstudio.bna.logics.tracking.MidpointsChangedEvent;
import org.archstudio.bna.logics.tracking.MidpointsTrackingLogic;
import org.archstudio.bna.logics.tracking.ThingRefTrackingLogic;
import org.archstudio.bna.things.labels.ArrowheadThing;

public class ArrowheadLogic extends AbstractThingLogic implements IEndpointsTrackingListener, IMidpointsTrackingListener, IBNAModelListener {

	protected ThingRefTrackingLogic trtl = null;
	protected EndpointsTrackingLogic etl = null;
	protected MidpointsTrackingLogic mtl = null;

	public ArrowheadLogic(ThingRefTrackingLogic trtl, EndpointsTrackingLogic etl, MidpointsTrackingLogic mtl) {
		this.trtl = trtl;
		this.etl = etl;
		this.mtl = mtl;
	}

	public void init() {
		etl.addTrackingListener(this);
		mtl.addTrackingListener(this);
	}

	public void destroy() {
		mtl.removeTrackingListener(this);
		etl.removeTrackingListener(this);
	}

	protected void checkAndMirror(ArrowheadThing targetThing) {
		String emID = targetThing.getEndpointMasterThingID();
		int endpointNumber = targetThing.getEndpointNumber();
		if (emID != null) {
			IBNAModel m = getBNAModel();
			if (m != null) {
				IThing em = m.getThing(emID);
				if (em != null) {
					if (em instanceof IHasEndpoints) {
						if (endpointNumber == 1) {
							Point p2 = null;
							if (em instanceof IHasMidpoints) {
								Point[] midpoints = ((IHasMidpoints) em).getMidpoints();
								if ((midpoints != null) && (midpoints.length > 0)) {
									p2 = midpoints[0];
								}
							}
							if (p2 == null) {
								p2 = ((IHasEndpoints) em).getEndpoint2();
							}
							targetThing.setSecondaryPoint(p2);
						}
						else if (endpointNumber == 2) {
							Point p2 = null;
							if (em instanceof IHasMidpoints) {
								Point[] midpoints = ((IHasMidpoints) em).getMidpoints();
								if ((midpoints != null) && (midpoints.length > 0)) {
									p2 = midpoints[midpoints.length - 1];
								}
							}
							if (p2 == null) {
								p2 = ((IHasEndpoints) em).getEndpoint1();
							}
							targetThing.setSecondaryPoint(p2);
						}
					}
				}
			}
		}
	}

	public void bnaModelChanged(BNAModelEvent evt) {
		if (evt.getEventType() == BNAModelEvent.EventType.THING_ADDED) {
			IThing targetThing = evt.getTargetThing();
			if (targetThing instanceof ArrowheadThing) {
				checkAndMirror((ArrowheadThing) evt.getTargetThing());
			}
		}
		else if (evt.getEventType() == BNAModelEvent.EventType.THING_CHANGED) {
			IThing targetThing = evt.getTargetThing();
			ThingEvent tevt = evt.getThingEvent();
			if (targetThing instanceof ArrowheadThing) {
				if ((tevt.getPropertyName() != null) && (tevt.getPropertyName().equals("secondaryPoint"))) {
					checkAndMirror((ArrowheadThing) targetThing);
				}
			}
		}
	}

	public void endpointsChanged(EndpointsChangedEvent evt) {
		IThing targetThing = evt.getTargetThing();
		IBNAModel m = getBNAModel();
		if (m != null) {
			String[] thingIDs = trtl.findReferencingThings(targetThing, IMirrorsEndpoint.ENDPOINT_MASTER_THING_ID_PROPERTY_NAME);
			for (int i = 0; i < thingIDs.length; i++) {
				IThing thing = m.getThing(thingIDs[i]);
				if (thing instanceof ArrowheadThing) {
					checkAndMirror((ArrowheadThing) thing);
				}
			}
		}
	}

	public void midpointsChanged(MidpointsChangedEvent evt) {
		IThing targetThing = evt.getTargetThing();
		IBNAModel m = getBNAModel();
		if (m != null) {
			String[] thingIDs = trtl.findReferencingThings(targetThing, IMirrorsEndpoint.ENDPOINT_MASTER_THING_ID_PROPERTY_NAME);
			for (int i = 0; i < thingIDs.length; i++) {
				IThing thing = m.getThing(thingIDs[i]);
				if (thing instanceof ArrowheadThing) {
					checkAndMirror((ArrowheadThing) thing);
				}
			}
		}
	}

}
