package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;

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
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IHasShapeKeys;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.FastLongMap;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Lists;

public class StickPointLogic extends AbstractThingLogic implements IBNAModelListener {

	private static Point getNearPoint(IThing pointThing, IThingKey<Point> pointKey, StickyMode stickyMode) {
		if (stickyMode.isDependsOnSecondaryPoint()) {
			if (pointKey.equals(IHasEndpoints.ENDPOINT_1_KEY) && pointThing instanceof IHasPoints) {
				return ((IHasPoints) pointThing).getPoint(1);
			}
			if (pointKey.equals(IHasEndpoints.ENDPOINT_2_KEY) && pointThing instanceof IHasPoints) {
				return ((IHasPoints) pointThing).getPoint(-2);
			}
			if (pointKey.equals(IHasIndicatorPoint.INDICATOR_POINT_KEY) && pointThing instanceof IHasAnchorPoint) {
				return ((IHasAnchorPoint) pointThing).getAnchorPoint();
			}
			if (pointKey.equals(IHasAnchorPoint.ANCHOR_POINT_KEY) && pointThing instanceof IHasIndicatorPoint) {
				return ((IHasIndicatorPoint) pointThing).getIndicatorPoint();
			}
		}
		return pointThing.get(pointKey);
	}

	private static class StuckPoint {

		boolean isUpdating;

		final Object pointThingID;
		final IThingKey<Point> pointKey;
		final StickyMode stickyMode;
		final Object stickyThingID;

		public StuckPoint(Object pointThingID, IThingKey<Point> pointKey, StickyMode stickyMode, Object stickyThingID) {
			this.pointThingID = pointThingID;
			this.pointKey = pointKey;
			this.stickyMode = stickyMode;
			this.stickyThingID = stickyThingID;
		}

		public void update(IBNAModel model, ThingEvent thingEvent) {

			if (isUpdating) {
				return;
			}

			isUpdating = true;
			try {
				IThing pointThing = model.getThing(pointThingID);
				if (pointThing != null) {
					Point nearPoint = getNearPoint(pointThing, pointKey, stickyMode);
					if (nearPoint != null) {
						IIsSticky stickyThing = BNAUtils.castOrNull(model.getThing(stickyThingID), IIsSticky.class);
						if (stickyThing != null) {

							// adjust the point proportionally if the 'stickyThing' has a rectangle and was just resized/moved
							if (thingEvent != null && stickyThing.equals(thingEvent.getTargetThing())) {
								if (IHasBoundingBox.BOUNDING_BOX_KEY.equals(thingEvent.getPropertyName())) {
									nearPoint = BNAUtils.movePointWith((Rectangle) thingEvent.getOldPropertyValue(),
											(Rectangle) thingEvent.getNewPropertyValue(), nearPoint);
								}
							}

							// calculate the closest sticky point on the sticky thing, given the current point as reference
							Point stickyPoint = stickyThing.getStickyPointNear(stickyMode, nearPoint);

							// update the actual stuck point
							pointThing.set(pointKey, stickyPoint);
						}
					}
				}
			}
			finally {
				isUpdating = false;
			}
		}
	}

	private class Registrar {

		final int pointThingUID;
		final Collection<IThingKey<?>> pointThingShapeModifyingKeys;
		final int stickyThingUID;
		final Collection<IThingKey<?>> stickyThingShapeModifyingKeys;
		final StuckPoint stuckPoint;

		public Registrar(int pointThingUID, Collection<IThingKey<?>> pointThingShapeModifyingKeys, int stickyThingUID,
				Collection<IThingKey<?>> stickyThingShapeModifyingKeys, StuckPoint stuckPoint) {
			this.pointThingUID = pointThingUID;
			this.pointThingShapeModifyingKeys = pointThingShapeModifyingKeys;
			this.stickyThingUID = stickyThingUID;
			this.stickyThingShapeModifyingKeys = stickyThingShapeModifyingKeys;
			this.stuckPoint = stuckPoint;
		}

		public void register() {
			for (IThingKey<?> shapeModifyingKey : pointThingShapeModifyingKeys) {
				get(BNAUtils.getThingKeyUID(pointThingUID, shapeModifyingKey.getUID()), true).add(stuckPoint);
			}
			for (IThingKey<?> shapeModifyingKey : stickyThingShapeModifyingKeys) {
				get(BNAUtils.getThingKeyUID(stickyThingUID, shapeModifyingKey.getUID()), true).add(stuckPoint);
			}
			registrars.put(BNAUtils.getThingKeyUID(pointThingUID, stuckPoint.pointKey.getUID()), this);
		}

		public void unregister() {
			for (IThingKey<?> shapeModifyingKey : pointThingShapeModifyingKeys) {
				get(BNAUtils.getThingKeyUID(pointThingUID, shapeModifyingKey.getUID()), false).remove(stuckPoint);
			}
			for (IThingKey<?> shapeModifyingKey : stickyThingShapeModifyingKeys) {
				get(BNAUtils.getThingKeyUID(stickyThingUID, shapeModifyingKey.getUID()), false).remove(stuckPoint);
			}
			registrars.remove(BNAUtils.getThingKeyUID(pointThingUID, stuckPoint.pointKey.getUID()));
		}
	}

	public StickPointLogic() {
	}

	FastLongMap<Collection<StuckPoint>> stuckPoints = new FastLongMap<Collection<StuckPoint>>(1024);
	FastLongMap<Registrar> registrars = new FastLongMap<Registrar>(128);

	private Collection<StuckPoint> get(long thingKeyUID, boolean create) {
		Collection<StuckPoint> stuckPointCollection = stuckPoints.get(thingKeyUID);
		if (stuckPointCollection == null) {
			if (create) {
				stuckPoints.put(thingKeyUID, stuckPointCollection = Lists.newArrayList());
			}
			else {
				return Collections.emptyList();
			}
		}
		return stuckPointCollection;
	}

	public void stick(IHasShapeKeys pointThing, IThingKey<Point> pointKey, final StickyMode stickyMode,
			IIsSticky stickyThing) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);
		checkNotNull(stickyMode);
		checkNotNull(stickyThing);

		unstick(pointThing, pointKey);

		StuckPoint stuckPoint = new StuckPoint(pointThing.getID(), pointKey, stickyMode, stickyThing.getID());
		Registrar registrar = new Registrar(pointThing.getUID(), pointThing.getShapeModifyingKeys(),
				stickyThing.getUID(), stickyThing.getShapeModifyingKeys(), stuckPoint);
		registrar.register();

		stuckPoint.update(getBNAModel(), null);
	}

	public void unstick(IHasShapeKeys pointThing, IThingKey<Point> pointKey) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);

		Registrar registrar = registrars.get(BNAUtils.getThingKeyUID(pointThing, pointKey));
		if (registrar != null) {
			registrar.unregister();
		}
	}

	public @Nullable
	StickyMode getStickyMode(IHasShapeKeys pointThing, IThingKey<Point> pointKey) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);

		Registrar registrar = registrars.get(BNAUtils.getThingKeyUID(pointThing, pointKey));
		if (registrar != null) {
			return registrar.stuckPoint.stickyMode;
		}
		return null;
	}

	public @Nullable
	Object getStickyThingID(IHasShapeKeys pointThing, IThingKey<Point> pointKey) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);

		Registrar registrar = registrars.get(BNAUtils.getThingKeyUID(pointThing, pointKey));
		if (registrar != null) {
			return registrar.stuckPoint.stickyThingID;
		}
		return null;
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		ThingEvent thingEvent = evt.getThingEvent();
		if (thingEvent != null) {
			for (StuckPoint stuckPoint : get(thingEvent.getThingKeyUID(), false)) {
				stuckPoint.update(evt.getSource(), thingEvent);
			}
		}
	}
}
