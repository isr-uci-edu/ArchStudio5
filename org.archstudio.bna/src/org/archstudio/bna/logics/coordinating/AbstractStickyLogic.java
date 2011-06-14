package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public abstract class AbstractStickyLogic<F extends IIsSticky, T extends IThing, D> extends
		AbstractPropagateValueLogic<F, T, D> {

	public AbstractStickyLogic(Class<F> fromThingClass, Class<T> toThingClass, IThingLogicManager tlm) {
		super(fromThingClass, toThingClass);
	}

	protected void setPropagate(F fromStickyThing, IThingKey<?> toKey, @Nullable D toData, T... toThings) {
		checkNotNull(fromStickyThing);
		checkNotNull(toKey);

		for (IThingKey<?> fromKey : fromStickyThing.getStickyModifyingKeys()) {
			super.setPropagate(fromStickyThing, fromKey, toKey, toData, toThings);
		}
	}

	@Override
	protected void doPropagation(final IBNAModel model, final F fromThing, final IThingKey<?> fromKey,
			final ThingEvent<F, ?, ?> fromThingEvent, final D data, final T toThing, final IThingKey<?> toKey,
			final ThingEvent<T, ?, ?> toThingEvent) {
		toThing.synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				Point nearPoint = getNearPoint(model, fromThing, fromKey, fromThingEvent, data, toThing, toKey,
						toThingEvent);

				// adjust the point if the 'fromThing' has a rectangle and was just resized/moved
				if (fromThingEvent != null) {
					if (IHasBoundingBox.BOUNDING_BOX_KEY.equals(fromThingEvent.getPropertyName())) {
						nearPoint = BNAUtils.movePointWith((Rectangle) fromThingEvent.getOldPropertyValue(),
								(Rectangle) fromThingEvent.getNewPropertyValue(), nearPoint);
					}
				}

				// calculate the closest sticky point on the sticky thing, given the current point as reference
				StickyMode stickyMode = getStickyMode(data);
				Point stickyPoint = fromThing.getStickyPointNear(stickyMode, nearPoint, nearPoint);

				// update the actual stuck point
				setStuckPoint(model, fromThing, fromKey, fromThingEvent, data, toThing, toKey, toThingEvent,
						stickyPoint);
			}
		});
	}

	protected abstract @Nullable
	Point getNearPoint(IBNAModel model, F fromThing, IThingKey<?> fromKey,
			@Nullable ThingEvent<F, ?, ?> fromThingEvent, D data, T toThing, @Nullable IThingKey<?> toKey,
			@Nullable ThingEvent<T, ?, ?> toThingEvent);

	protected abstract void setStuckPoint(IBNAModel model, F fromThing, IThingKey<?> fromKey,
			@Nullable ThingEvent<F, ?, ?> fromThingEvent, D data, T toThing, @Nullable IThingKey<?> toKey,
			@Nullable ThingEvent<T, ?, ?> toThingEvent, Point stuckPoint);

	protected abstract StickyMode getStickyMode(D data);
}
