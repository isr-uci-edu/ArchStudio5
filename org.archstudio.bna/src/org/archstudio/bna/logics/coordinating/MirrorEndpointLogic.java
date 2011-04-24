package org.archstudio.bna.logics.coordinating;

import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IMirrorsEndpoint;
import org.archstudio.bna.logics.tracking.EndpointsChangedEvent;
import org.archstudio.bna.logics.tracking.EndpointsTrackingLogic;
import org.archstudio.bna.logics.tracking.IEndpointsTrackingListener;
import org.archstudio.bna.logics.tracking.ThingRefTrackingLogic;

public class MirrorEndpointLogic extends AbstractThingLogic implements IEndpointsTrackingListener, IBNAModelListener {

	protected ThingRefTrackingLogic trtl = null;
	protected EndpointsTrackingLogic etl = null;

	public MirrorEndpointLogic(ThingRefTrackingLogic trtl, EndpointsTrackingLogic etl) {
		this.trtl = trtl;
		this.etl = etl;
	}

	public void init() {
		etl.addTrackingListener(this);
	}

	public void destroy() {
		etl.removeTrackingListener(this);
	}

	protected void checkAndMirror(IMirrorsEndpoint targetThing) {
		String emID = targetThing.getEndpointMasterThingID();
		int endpointNumber = targetThing.getEndpointNumber();
		if (emID != null) {
			IBNAModel m = getBNAModel();
			if (m != null) {
				IThing em = m.getThing(emID);
				if (em != null) {
					if (em instanceof IHasEndpoints) {
						if (endpointNumber == 1) {
							Point endpoint = ((IHasEndpoints) em).getEndpoint1();
							targetThing.setAnchorPoint(endpoint);
						}
						else if (endpointNumber == 2) {
							Point endpoint = ((IHasEndpoints) em).getEndpoint2();
							targetThing.setAnchorPoint(endpoint);
						}
					}
				}
			}
		}
	}

	public void bnaModelChanged(BNAModelEvent evt) {
		if (evt.getEventType() == BNAModelEvent.EventType.THING_ADDED) {
			IThing targetThing = evt.getTargetThing();
			if (targetThing instanceof IMirrorsEndpoint) {
				checkAndMirror((IMirrorsEndpoint) evt.getTargetThing());
			}
		}
		else if (evt.getEventType() == BNAModelEvent.EventType.THING_CHANGED) {
			IThing targetThing = evt.getTargetThing();
			ThingEvent tevt = evt.getThingEvent();
			if (targetThing instanceof IMirrorsEndpoint) {
				if ((tevt.getPropertyName() != null) && (tevt.getPropertyName().equals(IMirrorsEndpoint.ENDPOINT_MASTER_THING_ID_PROPERTY_NAME))) {
					checkAndMirror((IMirrorsEndpoint) targetThing);
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
				if (thing instanceof IMirrorsEndpoint) {
					checkAndMirror((IMirrorsEndpoint) thing);
				}
			}
		}
	}
}
