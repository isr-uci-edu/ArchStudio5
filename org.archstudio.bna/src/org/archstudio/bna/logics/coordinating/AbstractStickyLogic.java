package org.archstudio.bna.logics.coordinating;

import java.util.List;

import javax.annotation.Nullable;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasEllipse;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IHasRoundedCorners;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public abstract class AbstractStickyLogic<F extends IThing, T extends IThing, D> extends
		AbstractPropagateValueLogic<F, T, D> {

	public AbstractStickyLogic(Class<F> fromThingClass, Class<T> toThingClass, IThingLogicManager tlm) {
		super(fromThingClass, toThingClass);
	}

	@Override
	protected void doPropagation(final IBNAModel model, final F fromThing, final IThingKey<?> fromKey,
			final ThingEvent<F, ?, ?> fromThingEvent, final D data, final T toThing, final IThingKey<?> toKey,
			final ThingEvent<T, ?, ?> toThingEvent) {
		toThing.synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				Point currentPoint = getCurrentPoint(model, fromThing, fromKey, fromThingEvent, data, toThing, toKey,
						toThingEvent);

				// adjust the point if the 'fromThing' has a rectangle and was just resized/moved
				if (fromThingEvent != null && IHasBoundingBox.BOUNDING_BOX_KEY.equals(fromThingEvent.getPropertyName())) {
					currentPoint = BNAUtils.movePointWith((Rectangle) fromThingEvent.getOldPropertyValue(),
							(Rectangle) fromThingEvent.getNewPropertyValue(), currentPoint);
				}

				// calculate the closest sticky point on the sticky thing, given the current point as reference
				StickyMode stickyMode = getStickyMode(data);
				Point stuckPoint = getClosestStuckAtPoint(data, fromThing, stickyMode, currentPoint);

				// update the actual stuck point
				setCurrentPoint(model, fromThing, fromKey, fromThingEvent, data, toThing, toKey, toThingEvent,
						stuckPoint);
			}
		});
	}

	protected abstract Point getCurrentPoint(IBNAModel model, F fromThing, IThingKey<?> fromKey,
			@Nullable ThingEvent<F, ?, ?> fromThingEvent, D data, T toThing, @Nullable IThingKey<?> toKey,
			@Nullable ThingEvent<T, ?, ?> toThingEvent);

	protected abstract void setCurrentPoint(IBNAModel model, F fromThing, IThingKey<?> fromKey,
			@Nullable ThingEvent<F, ?, ?> fromThingEvent, D data, T toThing, @Nullable IThingKey<?> toKey,
			@Nullable ThingEvent<T, ?, ?> toThingEvent, Point stuckPoint);

	protected abstract StickyMode getStickyMode(D data);

	protected Point getCenterPoint(D data, IThing stickyThing) {
		return BNAUtils.getCentralPoint(stickyThing);
	}

	public Point getClosestStuckAtPoint(D data, IThing stickyThing, StickyMode stickyMode, Point point) {

		switch (stickyMode) {

		case CENTER:
			return getCenterPoint(data, stickyThing);

		case EDGE:
			if (stickyThing instanceof IHasPoints) {
				List<Point> points = ((IHasPoints) stickyThing).getPoints();
				return BNAUtils.getClosestPointOnPolygon(BNAUtils.toXYArray(points), point.x, point.y);
			}
			if (stickyThing instanceof IHasEllipse) {
				Rectangle ellipseBounds = ((IHasBoundingBox) stickyThing).getBoundingBox();
				return BNAUtils.getClosestPointOnEllipse(ellipseBounds, point.x, point.y);
			}
			if (stickyThing instanceof IHasBoundingBox) {
				Rectangle rectangle = ((IHasBoundingBox) stickyThing).getBoundingBox();
				if (stickyThing instanceof IHasRoundedCorners) {
					Dimension cornerSize = ((IHasRoundedCorners) stickyThing).getCornerSize();
					return BNAUtils.getClosestPointOnRectangle(rectangle, cornerSize, point);
				}
				else {
					return BNAUtils.getClosestPointOnRectangle(rectangle, new Dimension(), point);
				}
			}
			if (stickyThing instanceof IHasAnchorPoint) {
				return ((IHasAnchorPoint) stickyThing).getAnchorPoint();
			}
			break;

		case EDGE_FROM_CENTER:
			if (stickyThing instanceof IHasPoints) {
				Point centerPoint = getCenterPoint(data, stickyThing);
				List<Point> points = ((IHasPoints) stickyThing).getPoints();
				return BNAUtils.getClosestPointOnPolygon(BNAUtils.toXYArray(points), point.x, point.y, centerPoint.x,
						centerPoint.y);
			}
			if (stickyThing instanceof IHasEllipse) {
				Rectangle ellipseBounds = ((IHasBoundingBox) stickyThing).getBoundingBox();
				return BNAUtils.getClosestPointOnEllipse(ellipseBounds, point.x, point.y);
			}
			if (stickyThing instanceof IHasBoundingBox) {
				Point centerPoint = getCenterPoint(data, stickyThing);
				Rectangle rectangle = ((IHasBoundingBox) stickyThing).getBoundingBox();
				if (stickyThing instanceof IHasRoundedCorners) {
					Dimension cornerSize = ((IHasRoundedCorners) stickyThing).getCornerSize();
					return BNAUtils.getClosestPointOnRectangle(rectangle, cornerSize, point, centerPoint);
				}
				else {
					return BNAUtils.getClosestPointOnRectangle(rectangle, new Dimension(), point, centerPoint);
				}
			}
			if (stickyThing instanceof IHasAnchorPoint) {
				return ((IHasAnchorPoint) stickyThing).getAnchorPoint();
			}
			break;
		}
		throw new IllegalArgumentException("Unable to determine sticky point: " + stickyThing);
	}
}
