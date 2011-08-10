package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class StickPointLogic extends AbstractPropagateValueLogic<IIsSticky, IThing, IThingKey<Point>> implements
		IBNASynchronousModelListener {

	protected static final IThingKey<StickyMode> STICKY_MODE_KEY = ThingKey.create("stickyMode");

	public StickPointLogic() {
		super(IIsSticky.class, IThing.class);
	}

	public IThingKey<StickyMode> getStickyModeKey(IThingKey<Point> forPointKey) {
		return getSettingKey(forPointKey, STICKY_MODE_KEY);
	}

	public StickyMode getStickyMode(IThing toThing, IThingKey<Point> forPointKey) {
		StickyMode stickyMode = getSetting(toThing, forPointKey, STICKY_MODE_KEY);
		if (stickyMode == null) {
			stickyMode = StickyMode.CENTER;
		}
		return stickyMode;
	}

	public void setStickyMode(IThing toThing, IThingKey<Point> forPointKey, StickyMode stickyMode) {
		setSetting(toThing, forPointKey, STICKY_MODE_KEY, stickyMode);
	}

	public void unstick(IThing toThing, IThingKey<Point> forPointKey) {
		unpropagate(toThing, forPointKey);
	}

	@Override
	protected void doSynchronizedPropagation(IBNAModel model, IIsSticky fromThing,
			ThingEvent<IIsSticky, ?, ?> fromThingEvent, IThing toThing, ThingEvent<IThing, ?, ?> toThingEvent,
			IThingKey<Point> toKey) {

		StickyMode stickyMode = getSetting(toThing, toKey, STICKY_MODE_KEY);
		if (stickyMode == null) {
			return;
		}
		Point nearPoint = getNearPoint(toThing, toKey, stickyMode);
		if (nearPoint == null) {
			return;
		}

		// adjust the point proportionally if the 'stickyThing' has a rectangle and was just resized/moved
		if (fromThingEvent != null) {
			if (IHasBoundingBox.BOUNDING_BOX_KEY.equals(fromThingEvent.getPropertyName())) {
				nearPoint = BNAUtils.movePointWith((Rectangle) fromThingEvent.getOldPropertyValue(),
						(Rectangle) fromThingEvent.getNewPropertyValue(), nearPoint);
			}
		}

		// calculate the closest sticky point on the sticky thing, given the current point as reference
		Point stickyPoint = fromThing.getStickyPointNear(stickyMode, nearPoint);

		// update the actual stuck point
		toThing.set(toKey, stickyPoint);

	}

	protected Point getNearPoint(IThing pointThing, IThingKey<Point> pointKey, StickyMode stickyMode) {
		if (stickyMode.isDependsOnSecondaryPoint() && pointKey.equals(IHasEndpoints.ENDPOINT_1_KEY)) {
			return ((IHasPoints) pointThing).getPoint(1);
		}
		if (stickyMode.isDependsOnSecondaryPoint() && pointKey.equals(IHasEndpoints.ENDPOINT_2_KEY)) {
			return ((IHasPoints) pointThing).getPoint(-2);
		}
		return pointThing.get(pointKey);
	}
}
