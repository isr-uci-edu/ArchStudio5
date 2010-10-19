package org.archstudio.bna.logics.tracking;

import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.eclipse.swt.graphics.Point;

public class AnchorPointTrackingLogic extends AbstractTrackingLogic<IAnchorPointTrackingListener, Point> {
	public AnchorPointTrackingLogic() {
		super(IHasAnchorPoint.class);
	}

	protected Point getTrackedElement(IThing targetThing) {
		if (targetThing instanceof IHasAnchorPoint) {
			return ((IHasAnchorPoint) targetThing).getAnchorPoint();
		}
		return null;
	}

	protected void fireTrackedChangedEvent(IThing targetThing, Point oldTracked, Point newTracked) {
		AnchorPointChangedEvent evt = new AnchorPointChangedEvent(this, targetThing, oldTracked, newTracked);
		for (IAnchorPointTrackingListener l : trackingListeners) {
			l.anchorPointChanged(evt);
		}
	}
}