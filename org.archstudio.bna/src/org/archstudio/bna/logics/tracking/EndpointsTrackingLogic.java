package org.archstudio.bna.logics.tracking;

import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasEndpoints;

public class EndpointsTrackingLogic extends AbstractTrackingLogic<IEndpointsTrackingListener, EndpointPair> {
	public EndpointsTrackingLogic() {
		super(IHasEndpoints.class);
	}

	protected EndpointPair getTrackedElement(IThing targetThing) {
		if (targetThing instanceof IHasEndpoints) {
			return new EndpointPair(((IHasEndpoints) targetThing).getEndpoint1(), ((IHasEndpoints) targetThing).getEndpoint2());
		}
		return null;
	}

	protected void fireTrackedChangedEvent(IThing targetThing, EndpointPair oldTracked, EndpointPair newTracked) {
		EndpointsChangedEvent evt = new EndpointsChangedEvent(this, targetThing, oldTracked == null ? null : oldTracked.endpoint1, newTracked == null ? null
		        : newTracked.endpoint1, oldTracked == null ? null : oldTracked.endpoint2, newTracked == null ? null : newTracked.endpoint2);
		for (IEndpointsTrackingListener l : trackingListeners) {
			l.endpointsChanged(evt);
		}
	}
}