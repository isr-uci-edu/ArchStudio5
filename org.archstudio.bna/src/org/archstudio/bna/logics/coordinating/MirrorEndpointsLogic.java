package org.archstudio.bna.logics.coordinating;

import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IMirrorsEndpoints;
import org.archstudio.bna.logics.tracking.EndpointsChangedEvent;
import org.archstudio.bna.logics.tracking.EndpointsTrackingLogic;
import org.archstudio.bna.logics.tracking.IEndpointsTrackingListener;
import org.archstudio.bna.logics.tracking.ThingRefTrackingLogic;

public class MirrorEndpointsLogic extends AbstractThingLogic implements IEndpointsTrackingListener, IBNAModelListener {

	protected ThingRefTrackingLogic trtl = null;
	protected EndpointsTrackingLogic epstl = null;

	public MirrorEndpointsLogic(ThingRefTrackingLogic trtl, EndpointsTrackingLogic epstl) {
		this.trtl = trtl;
		this.epstl = epstl;
	}

	public void init() {
		epstl.addTrackingListener(this);
	}

	public void destroy() {
		epstl.removeTrackingListener(this);
	}

	protected void checkAndMirror(IMirrorsEndpoints targetThing) {
		String apmID = targetThing.getEndpointsMasterThingID();
		if (apmID != null) {
			IBNAModel m = getBNAModel();
			if (m != null) {
				IThing apm = m.getThing(apmID);
				if (apm != null) {
					if (apm instanceof IHasEndpoints) {
						Point endpoint1 = ((IHasEndpoints) apm).getEndpoint1();
						Point endpoint2 = ((IHasEndpoints) apm).getEndpoint2();
						targetThing.setEndpoint1(endpoint1);
						targetThing.setEndpoint2(endpoint2);
					}
				}
			}
		}
	}

	public void bnaModelChanged(BNAModelEvent evt) {
		if (evt.getEventType() == BNAModelEvent.EventType.THING_ADDED) {
			IThing targetThing = evt.getTargetThing();
			if (targetThing instanceof IMirrorsEndpoints) {
				checkAndMirror((IMirrorsEndpoints) evt.getTargetThing());
			}
		}
		else if (evt.getEventType() == BNAModelEvent.EventType.THING_CHANGED) {
			IThing targetThing = evt.getTargetThing();
			ThingEvent tevt = evt.getThingEvent();
			if (targetThing instanceof IMirrorsEndpoints) {
				if ((tevt.getPropertyName() != null) && (tevt.getPropertyName().equals(IMirrorsEndpoints.ENDPOINTS_MASTER_THING_ID_PROPERTY_NAME))) {
					checkAndMirror((IMirrorsEndpoints) targetThing);
				}
			}
		}
	}

	public void endpointsChanged(EndpointsChangedEvent evt) {
		IThing targetThing = evt.getTargetThing();
		IBNAModel m = getBNAModel();
		if (m != null) {
			String[] thingIDs = trtl.findReferencingThings(targetThing, IMirrorsEndpoints.ENDPOINTS_MASTER_THING_ID_PROPERTY_NAME);
			for (int i = 0; i < thingIDs.length; i++) {
				IThing thing = m.getThing(thingIDs[i]);
				if (thing instanceof IMirrorsEndpoints) {
					checkAndMirror((IMirrorsEndpoints) thing);
				}
			}
		}
	}
}
