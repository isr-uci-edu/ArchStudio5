package org.archstudio.bna.logics.tracking;

import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.eclipse.swt.graphics.Rectangle;

public class BoundingBoxTrackingLogic extends AbstractTrackingLogic<IBoundingBoxTrackingListener, Rectangle> {
	public BoundingBoxTrackingLogic() {
		super(IHasBoundingBox.class);
	}

	protected Rectangle getTrackedElement(IThing targetThing) {
		if (targetThing instanceof IHasBoundingBox) {
			return ((IHasBoundingBox) targetThing).getBoundingBox();
		}
		return null;
	}

	protected void fireTrackedChangedEvent(IThing targetThing, Rectangle oldTracked, Rectangle newTracked) {
		BoundingBoxChangedEvent evt = new BoundingBoxChangedEvent(this, targetThing, oldTracked, newTracked);
		for (IBoundingBoxTrackingListener l : trackingListeners) {
			l.boundingBoxChanged(evt);
		}
	}
}