package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasIndicatorPoint;
import org.archstudio.bna.facets.IHasMutableLoopPoint;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IHasShapeKeys;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.FastLongMap;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;

import com.google.common.collect.Sets;

public class StickPointLogic extends AbstractThingLogic implements IBNAModelListener {

	private class StuckPoint {

		final Object pointThingID;
		final IThingKey<Point> pointKey;
		final StickyMode stickyMode;
		final Object stickyThingID;

		Point stuckPoint = null;
		Shape stickyShape = null;

		public StuckPoint(IThing pointThing, IThingKey<Point> pointKey, StickyMode stickyMode, IIsSticky stickyThing) {
			this.pointThingID = pointThing.getID();
			this.pointKey = pointKey;
			this.stickyMode = stickyMode;
			this.stickyThingID = stickyThing.getID();

			stuckPoint = pointThing.get(pointKey);
			stickyShape = stickyThing.getStickyShape();
		}

		private boolean isLoop(IBNAModel model, IThing pointThing, IThingKey<Point> pointKey) {
			IThingKey<Point> otherPointKey = null;
			if (pointKey.equals(IHasEndpoints.ENDPOINT_1_KEY) && pointThing instanceof IHasEndpoints) {
				otherPointKey = IHasEndpoints.ENDPOINT_2_KEY;
			}
			if (pointKey.equals(IHasEndpoints.ENDPOINT_2_KEY) && pointThing instanceof IHasEndpoints) {
				otherPointKey = IHasEndpoints.ENDPOINT_1_KEY;
			}
			if (otherPointKey != null) {
				Registrar pointKeyRegistrar = registrars.get(BNAUtils.getThingKeyUID(pointThing, pointKey));
				Registrar otherPointKeyRegistrar = registrars.get(BNAUtils.getThingKeyUID(pointThing, otherPointKey));
				if (pointKeyRegistrar != null && otherPointKeyRegistrar != null) {
					if (pointKeyRegistrar.stickyThingUID == otherPointKeyRegistrar.stickyThingUID) {
						if (pointKeyRegistrar.stuckPoint.stickyMode == StickyMode.EDGE_FROM_CENTER) {
							if (otherPointKeyRegistrar.stuckPoint.stickyMode == StickyMode.EDGE_FROM_CENTER) {
								return true;
							}
						}
					}
				}
			}
			return false;
		}

		private Point getSecondaryPoint(IBNAModel model, IThing pointThing, IThingKey<Point> pointKey) {
			if (pointKey.equals(IHasEndpoints.ENDPOINT_1_KEY) && pointThing instanceof IHasPoints) {
				List<Point> points = ((IHasPoints) pointThing).getPoints();
				if (points.size() > 2) {
					return points.get(1);
				}
				return getSecondaryPoint(model, pointThing, IHasEndpoints.ENDPOINT_2_KEY,
						((IHasPoints) pointThing).getPoint(1));
			}
			if (pointKey.equals(IHasEndpoints.ENDPOINT_2_KEY) && pointThing instanceof IHasPoints) {
				List<Point> points = ((IHasPoints) pointThing).getPoints();
				if (points.size() > 2) {
					return points.get(points.size() - 2);
				}
				return getSecondaryPoint(model, pointThing, IHasEndpoints.ENDPOINT_1_KEY,
						((IHasPoints) pointThing).getPoint(-2));
			}
			if (pointKey.equals(IHasIndicatorPoint.INDICATOR_POINT_KEY) && pointThing instanceof IHasAnchorPoint) {
				return getSecondaryPoint(model, pointThing, IHasAnchorPoint.ANCHOR_POINT_KEY,
						((IHasAnchorPoint) pointThing).getAnchorPoint());
			}
			if (pointKey.equals(IHasAnchorPoint.ANCHOR_POINT_KEY) && pointThing instanceof IHasIndicatorPoint) {
				return getSecondaryPoint(model, pointThing, IHasIndicatorPoint.INDICATOR_POINT_KEY,
						((IHasIndicatorPoint) pointThing).getIndicatorPoint());
			}
			throw new IllegalArgumentException("Cannot find secondary point for " + pointThing + " and key " + pointKey);
		}

		private Point getSecondaryPoint(IBNAModel model, IThing pointThing, IThingKey<Point> pointKey,
				Point defaultPoint) {

			/*
			 * Check to see if the point is stuck to something, and if stuck to the center point of that thing, return
			 * the center point rather than the secondary point as this will be more accurate
			 */
			Registrar registrar = registrars.get(BNAUtils.getThingKeyUID(pointThing, pointKey));
			if (registrar != null) {
				if (registrar.stuckPoint.stickyMode.isStuckToCenterPoint()) {
					IIsSticky stickyThing = SystemUtils.castOrNull(model.getThing(registrar.stuckPoint.stickyThingID),
							IIsSticky.class);
					if (stickyThing != null) {

						// if so, get center point of what it's stuck to
						defaultPoint = BNAUtils.getCentralPoint(stickyThing);
					}
				}
			}
			return defaultPoint;
		}

		public void update(IBNAModel model, ThingEvent thingEvent, boolean inBulkChange) {
			IThing pointThing = model.getThing(pointThingID);
			if (pointThing != null) {
				IIsSticky stickyThing = BNAUtils.castOrNull(model.getThing(stickyThingID), IIsSticky.class);
				if (stickyThing != null) {

					// adjust the point proportionally if the 'stickyThing' has resized/moved
					if (thingEvent != null
							&& stickyThing.getShapeModifyingKeys().contains(thingEvent.getPropertyName())) {
						Shape newStickyShape = stickyThing.getStickyShape();
						if (stickyShape != null) {
							stuckPoint = BNAUtils.movePointWith(BNAUtils.toRectangle(stickyShape.getBounds()),
									BNAUtils.toRectangle(newStickyShape.getBounds()), stuckPoint);
						}
						stickyShape = newStickyShape;
					}

					// adjust the stuckPoint if the 'pointThing' has been moved
					if (thingEvent != null && pointKey.equals(thingEvent.getPropertyName())) {
						stuckPoint = (Point) thingEvent.getNewPropertyValue();
					}

					switch (stickyMode) {
					case CENTER: {

						// get center point
						Rectangle2D r = stickyShape.getBounds();
						stuckPoint = new Point(BNAUtils.round(r.getCenterX()), BNAUtils.round(r.getCenterY()));

					}
						break;

					case EDGE: {

						// calculate the closest point on the sticky shape, given the current point as reference
						stuckPoint = BNAUtils.getClosestPointOnShape(stickyShape, stuckPoint.x, stuckPoint.y);

					}
						break;

					case EDGE_FROM_CENTER: {

						// get center point
						stuckPoint = BNAUtils.getCentralPoint(stickyThing);

						// get secondary point ...
						Point secondaryPoint;

						// ... check for loop
						if (pointThing instanceof IHasMutableLoopPoint && isLoop(model, pointThing, pointKey)) {

							// determine top-right point offset
							int dx = 0;
							int dy = 0;
							if (pointKey.equals(IHasEndpoints.ENDPOINT_1_KEY)) {
								dx = 10;
								dy = 10;
							}
							else if (pointKey.equals(IHasEndpoints.ENDPOINT_2_KEY)) {
								dx = -10;
								dy = -10;
							}
							else {
								throw new IllegalArgumentException(pointKey.toString());
							}

							// get offset top-right point
							Rectangle r = stickyShape.getBounds();
							secondaryPoint = new Point(r.x + r.width + dx, r.y + dy);

							((IHasMutableLoopPoint) pointThing).setLoopPoint(new Point(r.x + r.width, r.y));
						}
						else {
							// get secondary point
							secondaryPoint = getSecondaryPoint(model, pointThing, pointKey);

							if (pointThing instanceof IHasMutableLoopPoint) {
								((IHasMutableLoopPoint) pointThing).setLoopPoint(null);
							}
						}

						// calculate the closest point on the sticky shape, given the current point as reference
						stuckPoint = BNAUtils.getClosestPointOnShape(stickyShape, secondaryPoint.x, secondaryPoint.y,
								stuckPoint.x, stuckPoint.y);

					}
						break;
					}

					// update the actual stuck point
					pointThing.set(pointKey, stuckPoint);
				}
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
				FastLongMap.getList(stuckPoints, BNAUtils.getThingKeyUID(pointThingUID, shapeModifyingKey.getUID()),
						true).add(stuckPoint);
			}
			for (IThingKey<?> shapeModifyingKey : stickyThingShapeModifyingKeys) {
				FastLongMap.getList(stuckPoints, BNAUtils.getThingKeyUID(stickyThingUID, shapeModifyingKey.getUID()),
						true).add(stuckPoint);
			}
			registrars.put(BNAUtils.getThingKeyUID(pointThingUID, stuckPoint.pointKey.getUID()), this);
		}

		public void unregister() {
			for (IThingKey<?> shapeModifyingKey : pointThingShapeModifyingKeys) {
				FastLongMap.getList(stuckPoints, BNAUtils.getThingKeyUID(pointThingUID, shapeModifyingKey.getUID()),
						false).remove(stuckPoint);
			}
			for (IThingKey<?> shapeModifyingKey : stickyThingShapeModifyingKeys) {
				FastLongMap.getList(stuckPoints, BNAUtils.getThingKeyUID(stickyThingUID, shapeModifyingKey.getUID()),
						false).remove(stuckPoint);
			}
			registrars.remove(BNAUtils.getThingKeyUID(pointThingUID, stuckPoint.pointKey.getUID()));
		}
	}

	public StickPointLogic(IBNAWorld world) {
		super(world);
	}

	FastLongMap<List<StuckPoint>> stuckPoints = new FastLongMap<>(1024);
	FastLongMap<Registrar> registrars = new FastLongMap<Registrar>(128);

	synchronized public void stick(IHasShapeKeys pointThing, IThingKey<Point> pointKey, final StickyMode stickyMode,
			IIsSticky stickyThing) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);
		checkNotNull(stickyMode);
		checkNotNull(stickyThing);

		unstick(pointThing, pointKey);

		StuckPoint stuckPoint = new StuckPoint(pointThing, pointKey, stickyMode, stickyThing);
		Registrar registrar = new Registrar(pointThing.getUID(), pointThing.getShapeModifyingKeys(),
				stickyThing.getUID(), stickyThing.getShapeModifyingKeys(), stuckPoint);
		registrar.register();

		stuckPoint.update(model, null, false);
	}

	synchronized public void unstick(IHasShapeKeys pointThing, IThingKey<Point> pointKey) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);

		Registrar registrar = registrars.get(BNAUtils.getThingKeyUID(pointThing, pointKey));
		if (registrar != null) {
			registrar.unregister();
		}
	}

	synchronized public @Nullable
	StickyMode getStickyMode(IHasShapeKeys pointThing, IThingKey<Point> pointKey) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);

		Registrar registrar = registrars.get(BNAUtils.getThingKeyUID(pointThing, pointKey));
		if (registrar != null) {
			return registrar.stuckPoint.stickyMode;
		}
		return null;
	}

	synchronized public @Nullable
	Object getStickyThingID(IHasShapeKeys pointThing, IThingKey<Point> pointKey) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);

		Registrar registrar = registrars.get(BNAUtils.getThingKeyUID(pointThing, pointKey));
		if (registrar != null) {
			return registrar.stuckPoint.stickyThingID;
		}
		return null;
	}

	Set<Long> currentlyUpdating = Sets.newHashSet();

	@Override
	synchronized public void bnaModelChanged(BNAModelEvent evt) {
		ThingEvent thingEvent = evt.getThingEvent();
		if (thingEvent != null) {
			for (StuckPoint stuckPoint : FastLongMap.getList(stuckPoints, thingEvent.getThingKeyUID(), false)) {
				if (currentlyUpdating.add(thingEvent.getThingKeyUID())) {
					try {
						stuckPoint.update(evt.getSource(), thingEvent, evt.isInBulkChange());
					}
					finally {
						currentlyUpdating.remove(thingEvent.getThingKeyUID());
					}
				}
			}
		}
	}
}
