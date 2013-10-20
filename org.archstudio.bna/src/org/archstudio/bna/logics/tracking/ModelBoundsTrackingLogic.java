package org.archstudio.bna.logics.tracking;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IPrivilegedBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasShapeKeys;
import org.archstudio.bna.facets.IIsHidden;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class ModelBoundsTrackingLogic extends AbstractThingLogic implements IPrivilegedBNAModelListener {

	protected final ThingTypeTrackingLogic typeLogic;
	private Rectangle cachedBounds = null;

	public ModelBoundsTrackingLogic(IBNAWorld world) {
		super(world);
		typeLogic = logics.addThingLogic(ThingTypeTrackingLogic.class);
	}

	@Override
	synchronized public void privilegedBNAModelChanged(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_ADDED: {
			IThing thing = evt.getTargetThing();
			if (thing instanceof IHasShapeKeys) {
				cachedBounds = null;
			}
		}
			break;
		case THING_REMOVED: {
			IThing thing = evt.getTargetThing();
			if (thing instanceof IHasShapeKeys) {
				cachedBounds = null;
			}
		}
			break;
		case THING_CHANGED: {
			if (cachedBounds != null) {
				ThingEvent thingEvent = evt.getThingEvent();
				IThing thing = evt.getTargetThing();
				if (thing instanceof IHasShapeKeys) {
					if (((IHasShapeKeys) thing).getShapeModifyingKeys().contains(thingEvent.getPropertyName())) {
						cachedBounds = null;
					}
				}
			}
		}
			break;
		default:
			// do nothing
		}
	}

	synchronized public Rectangle getModelBounds() {
		if (cachedBounds == null) {
			int x1 = Integer.MAX_VALUE;
			int y1 = Integer.MAX_VALUE;
			int x2 = Integer.MIN_VALUE;
			int y2 = Integer.MIN_VALUE;

			for (IHasBoundingBox t : typeLogic.getThings(IHasBoundingBox.class)) {
				if (!t.has(IIsHidden.HIDDEN_KEY, true)) {
					Rectangle bb = t.getBoundingBox();
					if (bb.x < x1) {
						x1 = bb.x;
					}
					if (bb.y < y1) {
						y1 = bb.y;
					}
					if (bb.x + bb.width > x2) {
						x2 = bb.x + bb.width;
					}
					if (bb.y + bb.height > y2) {
						y2 = bb.y + bb.height;
					}
				}
			}
			if (x1 >= x2 || y1 >= y2) {
				cachedBounds = new Rectangle(0, 0, 0, 0);
			}
			else {
				cachedBounds = new Rectangle(x1, y1, x2 - x1, y2 - y1);
			}
		}
		return BNAUtils.clone(cachedBounds);
	}
}
