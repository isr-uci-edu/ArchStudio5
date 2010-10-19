package org.archstudio.bna.logics.tracking;

import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasStickyBox;
import org.eclipse.swt.graphics.Rectangle;

public class StickyBoxTrackingLogic extends AbstractTrackingLogic<IStickyBoxTrackingListener, Rectangle> {
	public StickyBoxTrackingLogic() {
		super(IHasStickyBox.class);
	}

	protected Rectangle getTrackedElement(IThing targetThing) {
		if (targetThing instanceof IHasStickyBox) {
			return ((IHasStickyBox) targetThing).getStickyBox();
		}
		return null;
	}

	protected void fireTrackedChangedEvent(IThing targetThing, Rectangle oldTracked, Rectangle newTracked) {
		StickyBoxChangedEvent evt = new StickyBoxChangedEvent(this, targetThing, oldTracked, newTracked);
		for (IStickyBoxTrackingListener l : trackingListeners) {
			l.stickyBoxChanged(evt);
		}
	}
}