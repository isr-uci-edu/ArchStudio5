package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.coordinating.MoveWithLogic.MoveWithMode;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class MoveWithLogic extends AbstractPropagateValueLogic<IThing, IRelativeMovable, MoveWithMode> {

	public static enum MoveWithMode {
		TrackBoundingBoxOnly, //
		TrackAnchorPointOnly, //
		TrackBoundingBoxFirst, //
		TrackAnchorPointFirst
	}

	public MoveWithLogic() {
		super(IThing.class, IRelativeMovable.class);
	}

	public void moveWith(IThing withThing, MoveWithMode moveWithMode, IRelativeMovable... movableThings) {
		checkNotNull(withThing);
		checkNotNull(moveWithMode);

		setPropagate(withThing, IHasAnchorPoint.ANCHOR_POINT_KEY, null, moveWithMode, movableThings);
		setPropagate(withThing, IHasBoundingBox.BOUNDING_BOX_KEY, null, moveWithMode, movableThings);
	}

	public void moveWith(IHasAnchorPoint withThing, IRelativeMovable... movableThings) {
		checkNotNull(withThing);

		setPropagate(withThing, IHasAnchorPoint.ANCHOR_POINT_KEY, null, MoveWithMode.TrackAnchorPointOnly,
				movableThings);
	}

	public void moveWith(IHasBoundingBox withThing, IRelativeMovable... movableThings) {
		checkNotNull(withThing);

		setPropagate(withThing, IHasBoundingBox.BOUNDING_BOX_KEY, null, MoveWithMode.TrackBoundingBoxFirst,
				movableThings);
	}

	@Override
	protected void doPropagation(IBNAModel model, IThing fromThing, IThingKey<?> fromKey,
			@Nullable ThingEvent<IThing, ?, ?> fromThingEvent, MoveWithMode data, IRelativeMovable toThing,
			IThingKey<?> toKey, @Nullable ThingEvent<IRelativeMovable, ?, ?> toThingEvent) {

		switch (data) {

		case TrackBoundingBoxFirst:
			if (fromThing instanceof IHasBoundingBox) {
				ThingEvent<?, ?, Rectangle> rectangleEvent = BNAUtils.castOrNull(fromThingEvent,
						IHasBoundingBox.BOUNDING_BOX_KEY);
				if (rectangleEvent != null) {
					maintainWithBoundingBoxDelta(rectangleEvent.getOldPropertyValue(),
							rectangleEvent.getNewPropertyValue(), toThing);
				}
				break;
			}
			// fall through
		case TrackAnchorPointOnly:
			if (fromThing instanceof IHasAnchorPoint) {
				ThingEvent<?, ?, Point> pointEvent = BNAUtils.castOrNull(fromThingEvent,
						IHasAnchorPoint.ANCHOR_POINT_KEY);
				if (pointEvent != null) {
					maintainWithAnchorPointDelta(pointEvent.getOldPropertyValue(), pointEvent.getNewPropertyValue(),
							toThing);
				}
			}
			break;

		case TrackAnchorPointFirst:
			if (fromThing instanceof IHasAnchorPoint) {
				ThingEvent<?, ?, Point> pointEvent = BNAUtils.castOrNull(fromThingEvent,
						IHasAnchorPoint.ANCHOR_POINT_KEY);
				if (pointEvent != null) {
					maintainWithAnchorPointDelta(pointEvent.getOldPropertyValue(), pointEvent.getNewPropertyValue(),
							toThing);
				}
				break;
			}
			// fall through
		case TrackBoundingBoxOnly:
			if (fromThing instanceof IHasBoundingBox) {
				ThingEvent<?, ?, Rectangle> rectangleEvent = BNAUtils.castOrNull(fromThingEvent,
						IHasBoundingBox.BOUNDING_BOX_KEY);
				if (rectangleEvent != null) {
					maintainWithBoundingBoxDelta(rectangleEvent.getOldPropertyValue(),
							rectangleEvent.getNewPropertyValue(), toThing);
				}
			}
			break;
		}
	}

	protected void maintainWithAnchorPointDelta(final Point oldAnchorPoint, final Point newAnchorPoint,
			final IRelativeMovable toThing) {
		if (oldAnchorPoint != null && newAnchorPoint != null) {
			toThing.synchronizedUpdate(new Runnable() {
				@Override
				public void run() {
					toThing.moveRelative(newAnchorPoint.getDifference(oldAnchorPoint));
				}
			});
		}
	}

	protected void maintainWithBoundingBoxDelta(final Rectangle oldBoundingBox, final Rectangle newBoundingBox,
			final IRelativeMovable toThing) {
		if (oldBoundingBox != null && newBoundingBox != null) {
			toThing.synchronizedUpdate(new Runnable() {
				@Override
				public void run() {
					Point newPoint = BNAUtils.movePointWith(oldBoundingBox, newBoundingBox, toThing.getReferencePoint());
					toThing.setReferencePoint(newPoint);
				}
			});
		}
	}
}
