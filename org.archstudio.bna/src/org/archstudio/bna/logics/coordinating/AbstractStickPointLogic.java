package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.archstudio.sysutils.SystemUtils.castOrNull;

import javax.annotation.Nullable;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.keys.ThingKeyKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import com.google.common.collect.Iterables;

public abstract class AbstractStickPointLogic<STUCK_THING extends IThing> extends AbstractThingLogic implements
		IBNASynchronousModelListener {

	protected final Class<STUCK_THING> stuckThingClass;

	protected final IThingKey<Point> pointKey;
	protected final IThingKey<StickyMode> stickyModeKey;
	protected final IThingKey<Object> thingIDKey;

	protected ThingValueTrackingLogic vtl = null;

	public AbstractStickPointLogic(Class<STUCK_THING> stuckThingClass, IThingKey<Point> pointKey) {
		this.stuckThingClass = stuckThingClass;
		this.pointKey = pointKey;
		this.stickyModeKey = ThingKeyKey.create("stickyMode", pointKey);
		this.thingIDKey = ThingKeyKey.create("thingID", pointKey);
	}

	@Override
	protected void init() {
		super.init();
		vtl = addThingLogic(ThingValueTrackingLogic.class);
	}

	public void stick(final STUCK_THING thing, @Nullable final IIsSticky toStickyThing, final StickyMode stickyMode) {
		checkNotNull(thing);
		checkNotNull(stickyMode);

		thing.synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				thing.set(stickyModeKey, stickyMode);
				thing.set(thingIDKey, toStickyThing != null ? toStickyThing.getID() : null);
			}
		});
	}

	public IThingKey<StickyMode> getStickyModeKey() {
		return stickyModeKey;
	}

	public IThingKey<Object> getThingIDKey() {
		return thingIDKey;
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChangedSync(BNAModelEvent<ET, EK, EV> evt) {
		switch (evt.getEventType()) {
		case THING_CHANGED: {
			EK p = evt.getThingEvent().getPropertyName();
			ThingEvent<IIsSticky, EK, EV> stickyEvent = BNAUtils.castOrNull(evt.getThingEvent(), IIsSticky.class);
			if (stickyEvent != null) {
				IIsSticky stickyThing = stickyEvent.getTargetThing();
				if (stickyThing.isShapeModifyingKey(stickyEvent.getPropertyName())) {
					for (STUCK_THING stuckThing : Iterables.filter(
							evt.getSource().getThings(vtl.getThingIDs(thingIDKey, stickyThing.getID())),
							stuckThingClass)) {
						updateStuckPoint(evt.getSource(), stickyThing, stickyEvent, stuckThing, null);
					}
				}
			}
			if (p.equals(stickyModeKey) || p.equals(thingIDKey) || p.equals(pointKey)) {
				ThingEvent<STUCK_THING, EK, EV> stuckThingEvent = BNAUtils.castOrNull(evt.getThingEvent(),
						stuckThingClass);
				if (stuckThingEvent != null) {
					STUCK_THING stuckThing = stuckThingEvent.getTargetThing();
					IIsSticky stickyThing = castOrNull(evt.getSource().getThing(stuckThing.get(thingIDKey)),
							IIsSticky.class);
					if (stickyThing != null) {
						updateStuckPoint(evt.getSource(), stickyThing, null, stuckThing, stuckThingEvent);
					}
				}
			}
		}
		}
	}

	protected void updateStuckPoint(final IBNAModel model, final IIsSticky stickyThing,
			@Nullable final ThingEvent<IIsSticky, ?, ?> stickyThingEvent, final STUCK_THING stuckThing,
			@Nullable final ThingEvent<STUCK_THING, ?, ?> stuckThingEvent) {
		stuckThing.synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				Point nearPoint = getNearPoint(stuckThing);

				// adjust the point proportionally if the 'fromThing' has a rectangle and was just resized/moved
				if (stickyThingEvent != null) {
					if (IHasBoundingBox.BOUNDING_BOX_KEY.equals(stickyThingEvent.getPropertyName())) {
						nearPoint = BNAUtils.movePointWith((Rectangle) stickyThingEvent.getOldPropertyValue(),
								(Rectangle) stickyThingEvent.getNewPropertyValue(), nearPoint);
					}
				}

				// calculate the closest sticky point on the sticky thing, given the current point as reference
				StickyMode stickyMode = getStickyMode(stuckThing);
				if (stickyMode == null) {
					stickyMode = StickyMode.CENTER;
				}
				Point stickyPoint = stickyThing.getStickyPointNear(stickyMode, nearPoint);

				// update the actual stuck point
				setStuckPoint(stuckThing, stickyPoint);
			}
		});
	}

	protected @Nullable
	Point getNearPoint(STUCK_THING stuckThing) {
		return stuckThing.get(pointKey);
	}

	protected StickyMode getStickyMode(STUCK_THING stuckThing) {
		return stuckThing.get(stickyModeKey);
	}

	protected void setStuckPoint(STUCK_THING stuckThing, Point stuckPoint) {
		stuckThing.set(pointKey, stuckPoint);
	}
}
