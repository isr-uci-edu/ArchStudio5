package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasIndicatorPoint;
import org.archstudio.bna.facets.IHasMidpoints;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;

public class StickPointLogic extends AbstractThingLogic implements IBNAModelListener {

	static Point getNearPoint(IThing pointThing, IThingKey<Point> pointKey, StickyMode stickyMode) {
		if (stickyMode.isDependsOnSecondaryPoint()) {
			if (pointKey.equals(IHasEndpoints.ENDPOINT_1_KEY)) {
				return ((IHasPoints) pointThing).getPoint(1);
			}
			if (pointKey.equals(IHasEndpoints.ENDPOINT_2_KEY)) {
				return ((IHasPoints) pointThing).getPoint(-2);
			}
			if (pointKey.equals(IHasIndicatorPoint.INDICATOR_POINT_KEY)) {
				if (pointThing instanceof IHasAnchorPoint) {
					return ((IHasAnchorPoint) pointThing).getAnchorPoint();
				}
			}
			if (pointKey.equals(IHasAnchorPoint.ANCHOR_POINT_KEY)) {
				if (pointThing instanceof IHasIndicatorPoint) {
					return ((IHasIndicatorPoint) pointThing).getIndicatorPoint();
				}
			}
		}
		return pointThing.get(pointKey);
	}

	private static class StuckPoint {

		final Object pointThingID;
		final IThingKey<Point> pointKey;
		StickyMode stickyMode;
		final Object stickyThingID;

		public StuckPoint(Object pointThingID, IThingKey<Point> pointKey, StickyMode stickyMode, Object stickyThingID) {
			checkNotNull(pointThingID);
			checkNotNull(pointKey);
			checkNotNull(stickyMode);
			checkNotNull(stickyThingID);

			this.pointThingID = pointThingID;
			this.pointKey = pointKey;
			this.stickyMode = stickyMode;
			this.stickyThingID = stickyThingID;
		}
	}

	Multimap<List<?>, StuckPoint> stuckPoints = Multimaps.synchronizedMultimap(ArrayListMultimap
			.<List<?>, StuckPoint> create());

	public StickPointLogic() {
	}

	public void stick(IThing pointThing, IThingKey<Point> pointKey, final StickyMode stickyMode, IIsSticky stickyThing) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);
		checkNotNull(stickyMode);
		checkNotNull(stickyThing);

		unstick(pointThing, pointKey);

		StuckPoint stuckPoint = new StuckPoint(pointThing.getID(), pointKey, stickyMode, stickyThing.getID());

		synchronized (stuckPoints) {
			stuckPoints.put(Lists.newArrayList(pointThing.getID(), pointKey), stuckPoint);
			for (IThingKey<?> shapeModifyingKey : stickyThing.getShapeModifyingKeys()) {
				stuckPoints.put(Lists.newArrayList(stickyThing.getID(), shapeModifyingKey), stuckPoint);
			}
		}

		updatePoints(null, getBNAModel(), pointThing, pointKey, null);
	}

	private @Nullable
	StuckPoint getStuckPoint(IThing pointThing, IThingKey<Point> pointKey) {
		synchronized (stuckPoints) {
			Iterator<StuckPoint> i;
			while ((i = stuckPoints.get(Lists.newArrayList(pointThing.getID(), pointKey)).iterator()).hasNext()) {
				return i.next();
			}
			return null;
		}
	}

	public void unstick(IThing pointThing, IThingKey<Point> pointKey) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);

		synchronized (stuckPoints) {
			StuckPoint stuckPoint;
			while ((stuckPoint = getStuckPoint(pointThing, pointKey)) != null) {
				while (stuckPoints.values().remove(stuckPoint)) {
				}
			}
		}
	}

	public StickyMode getStickyMode(IThing pointThing, IThingKey<Point> pointKey) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);

		StuckPoint stuckPoint = getStuckPoint(pointThing, pointKey);
		if (stuckPoint != null) {
			return stuckPoint.stickyMode;
		}
		return null;
	}

	public void setStickyMode(IThing pointThing, IThingKey<Point> pointKey, StickyMode stickyMode) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);
		checkNotNull(stickyMode);

		StuckPoint stuckPoint = getStuckPoint(pointThing, pointKey);
		if (stuckPoint != null) {
			stuckPoint.stickyMode = stickyMode;
			updatePoints(null, getBNAModel(), pointThing, pointKey, null);
		}
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		ThingEvent thingEvent = evt.getThingEvent();
		if (thingEvent != null) {
			updatePoints(null, evt.getSource(), thingEvent.getTargetThing(), thingEvent.getPropertyName(), thingEvent);
		}
	}

	Set<List<Object>> isUpdating = Sets.newHashSet();

	private void updatePoints(IHasWorld worldThing, IBNAModel model, IThing pointThing, IThingKey<?> key,
			@Nullable ThingEvent thingEvent) {

		// prevent update cycles
		List<Object> updatingKey = Lists.newArrayList(pointThing, key);
		if (!isUpdating.add(updatingKey)) {
			return;
		}
		try {

			updatePoint(model, pointThing, key, thingEvent);

			// update related points, if necessary
			if (IHasEndpoints.ENDPOINT_1_KEY.equals(key)) {
				updatePoint(model, pointThing, IHasEndpoints.ENDPOINT_2_KEY, null);
			}
			else if (IHasEndpoints.ENDPOINT_2_KEY.equals(key)) {
				updatePoint(model, pointThing, IHasEndpoints.ENDPOINT_1_KEY, null);
			}
			else if (IHasMidpoints.MIDPOINTS_KEY.equals(key) && pointThing instanceof IHasEndpoints) {
				updatePoint(model, pointThing, IHasEndpoints.ENDPOINT_1_KEY, null);
				updatePoint(model, pointThing, IHasEndpoints.ENDPOINT_2_KEY, null);
			}
			else if (IHasAnchorPoint.ANCHOR_POINT_KEY.equals(key) && pointThing instanceof IHasIndicatorPoint) {
				updatePoint(model, pointThing, IHasIndicatorPoint.INDICATOR_POINT_KEY, null);
			}
			else if (IHasIndicatorPoint.INDICATOR_POINT_KEY.equals(key) && pointThing instanceof IHasAnchorPoint) {
				updatePoint(model, pointThing, IHasAnchorPoint.ANCHOR_POINT_KEY, null);
			}
		}
		finally {
			isUpdating.remove(updatingKey);
		}
	}

	private void updatePoint(IBNAModel model, IThing thing, IThingKey<?> key, @Nullable ThingEvent thingEvent) {

		List<StuckPoint> stuckPointsList;
		synchronized (stuckPoints) {
			stuckPointsList = Lists.newArrayList(stuckPoints.get(Lists.newArrayList(thing.getID(), key)));
		}
		for (StuckPoint stuckPoint : stuckPointsList) {

			IThing pointThing = model.getThing(stuckPoint.pointThingID);
			if (pointThing == null) {
				continue;
			}

			Point nearPoint = getNearPoint(pointThing, stuckPoint.pointKey, stuckPoint.stickyMode);
			if (nearPoint != null) {

				IIsSticky stickyThing = BNAUtils.castOrNull(model.getThing(stuckPoint.stickyThingID), IIsSticky.class);
				if (stickyThing == null) {
					continue;
				}

				// adjust the point proportionally if the 'stickyThing' has a rectangle and was just resized/moved
				if (thingEvent != null && stickyThing.equals(thingEvent.getTargetThing())) {
					if (IHasBoundingBox.BOUNDING_BOX_KEY.equals(thingEvent.getPropertyName())) {
						nearPoint = BNAUtils.movePointWith((Rectangle) thingEvent.getOldPropertyValue(),
								(Rectangle) thingEvent.getNewPropertyValue(), nearPoint);
					}
				}

				if (nearPoint != null) {
					// calculate the closest sticky point on the sticky thing, given the current point as reference
					Point stickyPoint = stickyThing.getStickyPointNear(stuckPoint.stickyMode, nearPoint);

					// update the actual stuck point
					pointThing.set(stuckPoint.pointKey, stickyPoint);
				}
			}
		}
	}
}
