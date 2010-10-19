package org.archstudio.bna.logics.tracking;

import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.*;
import org.archstudio.bna.facets.IHasMidpoints;

public class MidpointsTrackingLogic extends AbstractTrackingLogic<IMidpointsTrackingListener, Point[]> {
	public MidpointsTrackingLogic() {
		super(IHasMidpoints.class);
	}

	protected Point[] getTrackedElement(IThing targetThing) {
		if (targetThing instanceof IHasMidpoints) {
			return ((IHasMidpoints) targetThing).getMidpoints();
		}
		return null;
	}

	protected void fireTrackedChangedEvent(IThing targetThing, Point[] oldTracked, Point[] newTracked) {
		MidpointsChangedEvent evt = new MidpointsChangedEvent(this, targetThing, oldTracked, newTracked);
		for (IMidpointsTrackingListener l : trackingListeners) {
			l.midpointsChanged(evt);
		}
	}
}