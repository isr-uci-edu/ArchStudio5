package org.archstudio.bna.logics.coordinating;

import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasEllipse;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IHasRoundedCorners;
import org.archstudio.bna.facets.IHasSecondaryAnchorPoint;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.archstudio.bna.logics.tracking.ReferenceTrackingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class StickRelativeMovablesLogic extends AbstractMaintainReferencesLogic<IThing, IRelativeMovable> {

	public static enum StickyMode {
		EDGE_FROM_CENTER(true, true), //
		EDGE(false, false), //
		CENTER(false, true);

		boolean dependsOnSecondaryPoint;
		boolean isStuckToCenterPoint;

		private StickyMode(boolean dependsOnSecondaryPoint, boolean isStuckToCenterPoint) {
			this.dependsOnSecondaryPoint = dependsOnSecondaryPoint;
			this.isStuckToCenterPoint = isStuckToCenterPoint;
		}
	}

	public static final void stickPoint(IThing sourceThing, StickyMode stickyMode, IRelativeMovable... targetThings) {
		stickPoint(sourceThing.getID(), stickyMode, targetThings);
	}

	public static final void stickPoint(final Object sourceThingId, final StickyMode stickyMode,
			IRelativeMovable... targetThings) {
		for (final IThing targetThing : targetThings) {
			targetThing.synchronizedUpdate(new Runnable() {
				@Override
				public void run() {
					if (stickyMode != null) {
						targetThing.set(STICKY_MODE_KEY, stickyMode);
					}
					targetThing.set(STICKY_REF_KEY, sourceThingId);
				}
			});
		}
	}

	public static final void unstickPoint(IThing... targetThings) {
		for (IThing targetThing : targetThings) {
			targetThing.set(STICKY_REF_KEY, null);
		}
	}

	protected final IThingKey<Point> pointKey;
	protected final IThingRefKey<IThing> STICKY_REF_KEY = ThingRefKey.create("&stickToThingId");
	protected final IThingKey<StickyMode> STICKY_MODE_KEY = ThingKey.create("stickyMode");

	protected StickRelativeMovablesLogic(ReferenceTrackingLogic rtl, IThingKey<Poin> pointKey) {
		super(IThing.class, IRelativeMovable.class, STICKY_REF_KEY, rtl);
		sourcePropertyKeys.add(IHasPoints.POINTS_KEY);
		sourcePropertyKeys.add(IHasBoundingBox.BOUNDING_BOX_KEY);
		sourcePropertyKeys.add(IHasAnchorPoint.ANCHOR_POINT_KEY);
		targetPropertyKeys.add(STICKY_MODE_KEY);
	}

	@Override
	protected void maintain(final IBNAModel sourceModel, final IThing sourceThing,
			final ThingEvent<IThing, ?, ?> sourceThingEvent, IThingRefKey<IThing> thingRefKey,
			final IRelativeMovable targetThing, final ThingEvent<IRelativeMovable, ?, ?> targetThingEvent) {
		targetThing.synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				Point point = targetThing.getReferencePoint();
				if (sourceThingEvent != null && sourceThingEvent.getTargetThing().equals(sourceThing)
						&& sourceThing instanceof IHasBoundingBox
						&& IHasBoundingBox.BOUNDING_BOX_KEY.equals(sourceThingEvent.getPropertyName())) {
					// the size/position of the source thing has changed, move the point accordingly
					point = BNAUtils.movePointWith((Rectangle) sourceThingEvent.getOldPropertyValue(),
							(Rectangle) sourceThingEvent.getNewPropertyValue(), point);
				}

				// calculate the stuck point location
				StickyMode stickyMode = targetThing.get(STICKY_MODE_KEY);
				point = getStuckPoint(sourceModel, stickyMode, targetThing, propertyName, point);

				if (point != null) {
					// only update the point if it is valid
					setThingProperty(targetThing, propertyName, point);
				}

				checkSecondaryPointFromPrimaryPoint(sourceModel, targetThing, propertyName);
			}
		});
	}

	protected static Point getCenterPoint(IThing thing) {
		if (thing instanceof IHasPoints) {
			List<Point> points = ((IHasPoints) thing).getPoints();
			if (points != null) {
				int x1 = Integer.MAX_VALUE;
				int y1 = Integer.MAX_VALUE;
				int x2 = Integer.MIN_VALUE;
				int y2 = Integer.MIN_VALUE;
				for (Point p : points) {
					int x = p.x;
					int y = p.y;
					if (x < x1) {
						x1 = x;
					}
					if (x > x2) {
						x2 = x;
					}
					if (y < y1) {
						y1 = y;
					}
					if (y > y2) {
						y2 = y;
					}
				}
				return new Point((x1 + x2) / 2, (y1 + y2) / 2);
			}
		}
		if (thing instanceof IHasBoundingBox) {
			Rectangle r = ((IHasBoundingBox) thing).getBoundingBox();
			if (r != null) {
				return new Point(r.x + r.width / 2, r.y + r.height / 2);
			}
		}
		if (thing instanceof IHasAnchorPoint) {
			Point anchorPoint = ((IHasAnchorPoint) thing).getAnchorPoint();
			if (anchorPoint != null) {
				return anchorPoint;
			}
		}
		return null;
	}

	protected static Point getSecondaryPoint(IBNAModel sourceModel, IThing thing, Object propertyName) {
		if (IHasEndpoints.ENDPOINT_1_KEY.equals(propertyName)) {
			if (thing instanceof IHasMidpoints) {
				Point[] midpoints = ((IHasMidpoints) thing).getMidpoints();
				if (midpoints != null && midpoints.length > 0) {
					return midpoints[0];
				}
			}
			StickyMode stickyMode = getStickyMode(IHasEndpoints.ENDPOINT_2_KEY, thing);
			if (stickyMode.isStuckToCenterPoint) {
				String secondaryThingID = getStuckToThingId(IHasEndpoints.ENDPOINT_2_KEY, thing);
				if (secondaryThingID != null) {
					IThing secondaryThing = sourceModel.getThing(secondaryThingID);
					if (secondaryThing != null) {
						return getCenterPoint(secondaryThing);
					}
				}
			}
			return thing.getProperty(IHasEndpoints.ENDPOINT_2_KEY);
		}
		if (IHasEndpoints.ENDPOINT_2_KEY.equals(propertyName)) {
			if (thing instanceof IHasMidpoints) {
				Point[] midpoints = ((IHasMidpoints) thing).getMidpoints();
				if (midpoints != null && midpoints.length > 0) {
					return midpoints[midpoints.length - 1];
				}
			}
			StickyMode stickyMode = getStickyMode(IHasEndpoints.ENDPOINT_1_KEY, thing);
			if (stickyMode.isStuckToCenterPoint) {
				String secondaryThingID = getStuckToThingId(IHasEndpoints.ENDPOINT_1_KEY, thing);
				if (secondaryThingID != null) {
					IThing secondaryThing = sourceModel.getThing(secondaryThingID);
					if (secondaryThing != null) {
						return getCenterPoint(secondaryThing);
					}
				}
			}
			return thing.getProperty(IHasEndpoints.ENDPOINT_1_KEY);
		}
		if (IHasAnchorPoint.ANCHOR_POINT_KEY.equals(propertyName)) {
			if (thing instanceof IHasSecondaryAnchorPoint) {
				Point secondaryAnchorPoint = ((IHasSecondaryAnchorPoint) thing).getSecondaryAnchorPoint();
				if (secondaryAnchorPoint != null) {
					return secondaryAnchorPoint;
				}
			}
		}
		return null;
	}

	public static float getStickDistance(IThing stickyThing, StickyMode stickyMode, Point point) {
		return BNAUtils.getDistance(point, getStuckReferencePoint(stickyThing, stickyMode, point));
	}

	public static Point getStuckReferencePoint(IThing stickyThing, StickyMode stickyMode, Point point) {
		Point op = stickyMode == null || stickyMode.isStuckToCenterPoint ? getCenterPoint(stickyThing) : getStuckPoint(
				stickyThing, stickyMode, point);
		return op;
	}

	public static Point getStuckPoint(IThing stickyThing, StickyMode stickyMode, Point point) {

		switch (stickyMode) {

		case CENTER: {
			Point centerPoint = getCenterPoint(stickyThing);
			if (centerPoint != null) {
				return centerPoint;
			}
		}
			break;

		case EDGE: {
			if (point != null) {
				if (stickyThing instanceof IHasPoints) {
					Point[] points = ((IHasPoints) stickyThing).getPoints();
					if (points != null) {
						int[] coords = new int[points.length * 2];
						for (int i = 0; i < points.length; i++) {
							Point p = points[i];
							coords[i * 2] = p.x;
							coords[i * 2 + 1] = p.y;
						}
						Point closestPoint = BNAUtils.getClosestPointOnPolygon(coords, point.x, point.y);
						if (closestPoint != null) {
							return closestPoint;
						}
					}
				}
				if (stickyThing instanceof IHasEllipse) {
					Rectangle r = ((IHasBoundingBox) stickyThing).getBoundingBox();
					if (r != null) {
						Point closestPoint = BNAUtils.getClosestPointOnEllipse(r, point.x, point.y);
						if (closestPoint != null) {
							return closestPoint;
						}
					}
				}
				if (stickyThing instanceof IHasBoundingBox) {
					Rectangle r = ((IHasBoundingBox) stickyThing).getBoundingBox();
					if (r != null) {
						int x1 = r.x;
						int y1 = r.y;
						int x2 = x1 + r.width;
						int y2 = y1 + r.height;
						Point closestPoint = BNAUtils.getClosestPointOnPolygon(new int[] { x1, y1, x2, y1, x2, y2, x1,
								y2, x1, y1 }, point.x, point.y);
						if (closestPoint != null) {
							if (stickyThing instanceof IHasRoundedCorners) {
								int cornerWidth = Math
										.min(((IHasRoundedCorners) stickyThing).getCornerWidth(), r.width);
								int cornerHeight = Math.min(((IHasRoundedCorners) stickyThing).getCornerHeight(),
										r.height);
								if (closestPoint.x < r.x + cornerWidth / 2
										|| closestPoint.x > r.x + r.width - cornerWidth / 2) {
									if (closestPoint.y < r.y + cornerHeight / 2
											|| closestPoint.y > r.y + r.height - cornerHeight / 2) {
										Point centerPoint = getCenterPoint(stickyThing);
										int cornerX = point.x < centerPoint.x ? r.x : r.x + r.width - cornerWidth;
										int cornerY = point.y < centerPoint.y ? r.y : r.y + r.height - cornerHeight;
										Rectangle cornerR = new Rectangle(cornerX, cornerY, cornerWidth, cornerHeight);
										closestPoint = BNAUtils.getClosestPointOnEllipse(cornerR, point.x, point.y,
												centerPoint.x, centerPoint.y);
									}
								}
							}
							return closestPoint;
						}
					}
				}
				if (stickyThing instanceof IHasAnchorPoint) {
					Point anchorPoint = ((IHasAnchorPoint) stickyThing).getAnchorPoint();
					if (anchorPoint != null) {
						return anchorPoint;
					}
				}
			}
		}
			break;

		case EDGE_FROM_CENTER: {
			if (point != null) {
				if (stickyThing instanceof IHasPoints) {
					Point centerPoint = getCenterPoint(stickyThing);
					Point[] points = ((IHasPoints) stickyThing).getPoints();
					if (points != null) {
						int[] coords = new int[points.length * 2];
						for (int i = 0; i < points.length; i++) {
							Point p = points[i];
							coords[i * 2] = p.x;
							coords[i * 2 + 1] = p.y;
						}
						Point closestPoint = BNAUtils.getClosestPointOnPolygon(coords, point.x, point.y, centerPoint.x,
								centerPoint.y);
						if (closestPoint != null) {
							return closestPoint;
						}
					}
				}
				if (stickyThing instanceof IHasEllipse) {
					Rectangle r = ((IHasBoundingBox) stickyThing).getBoundingBox();
					if (r != null) {
						Point closestPoint = BNAUtils.getClosestPointOnEllipse(r, point.x, point.y);
						if (closestPoint != null) {
							return closestPoint;
						}
					}
				}
				if (stickyThing instanceof IHasBoundingBox) {
					Point centerPoint = getCenterPoint(stickyThing);
					Rectangle r = ((IHasBoundingBox) stickyThing).getBoundingBox();
					if (r != null) {
						int x1 = r.x;
						int y1 = r.y;
						int x2 = x1 + r.width;
						int y2 = y1 + r.height;
						Point closestPoint = BNAUtils.getClosestPointOnPolygon(new int[] { x1, y1, x2, y1, x2, y2, x1,
								y2, x1, y1 }, point.x, point.y, centerPoint.x, centerPoint.y);
						if (closestPoint != null) {
							if (stickyThing instanceof IHasRoundedCorners) {
								int cornerWidth = Math
										.min(((IHasRoundedCorners) stickyThing).getCornerWidth(), r.width);
								int cornerHeight = Math.min(((IHasRoundedCorners) stickyThing).getCornerHeight(),
										r.height);
								if (closestPoint.x < r.x + cornerWidth / 2
										|| closestPoint.x > r.x + r.width - cornerWidth / 2) {
									if (closestPoint.y < r.y + cornerHeight / 2
											|| closestPoint.y > r.y + r.height - cornerHeight / 2) {
										int cornerX = point.x < centerPoint.x ? r.x : r.x + r.width - cornerWidth;
										int cornerY = point.y < centerPoint.y ? r.y : r.y + r.height - cornerHeight;
										Rectangle cornerR = new Rectangle(cornerX, cornerY, cornerWidth, cornerHeight);
										closestPoint = BNAUtils.getClosestPointOnEllipse(cornerR, point.x, point.y,
												centerPoint.x, centerPoint.y);
									}
								}
							}
							return closestPoint;
						}
					}
				}
				if (stickyThing instanceof IHasAnchorPoint) {
					Point anchorPoint = ((IHasAnchorPoint) stickyThing).getAnchorPoint();
					if (anchorPoint != null) {
						return anchorPoint;
					}
				}
			}
		}
			break;
		}

		return null;
	}

	private static Point getStuckPoint(IBNAModel stickyModel, StickyMode stickyMode, IThing stuckThing,
			Object propertyName, Point point) {
		String sourceThingID = getStuckToThingId(propertyName, stuckThing);
		if (sourceThingID != null) {
			IThing sourceThing = stickyModel.getThing(sourceThingID);
			if (sourceThing != null) {
				if (stickyMode.dependsOnSecondaryPoint) {
					Point secondaryPoint = getSecondaryPoint(stickyModel, stuckThing, propertyName);
					if (secondaryPoint != null) {
						point = secondaryPoint;
					}
				}
				point = getStuckPoint(sourceThing, stickyMode, point);
			}
		}
		return point;
	}

	protected void updateStuckPoint(IBNAModel stickyModel, StickyMode stickyMode, IThing stuckThing, Object propertyName) {
		if (stickyModel == null) {
			stickyModel = getBNAModel();
		}
		Point point = stuckThing.getProperty(propertyName);
		if (point != null) {
			point = getStuckPoint(stickyModel, stickyMode, stuckThing, propertyName, point);
			if (point != null) {
				setThingProperty(stuckThing, propertyName, point);
			}
		}
	}

	protected void checkSecondaryPoint(IBNAModel sourceModel, IThing targetThing, Object propertyName) {
		StickyMode secondaryStickyMode = getStickyMode(propertyName, targetThing);
		if (secondaryStickyMode.dependsOnSecondaryPoint) {
			updateStuckPoint(sourceModel, secondaryStickyMode, targetThing, propertyName);
		}
	}

	protected void checkSecondaryPointFromPrimaryPoint(IBNAModel sourceModel, IThing targetThing, Object propertyName) {
		if (IHasEndpoints.ENDPOINT_1_KEY.equals(propertyName)) {
			Point[] midpoints = targetThing instanceof IHasMidpoints ? ((IHasMidpoints) targetThing).getMidpoints()
					: null;
			if (midpoints == null || midpoints.length == 0) {
				checkSecondaryPoint(sourceModel, targetThing, IHasEndpoints.ENDPOINT_2_KEY);
			}
		}
		else if (IHasEndpoints.ENDPOINT_2_KEY.equals(propertyName)) {
			Point[] midpoints = targetThing instanceof IHasMidpoints ? ((IHasMidpoints) targetThing).getMidpoints()
					: null;
			if (midpoints == null || midpoints.length == 0) {
				checkSecondaryPoint(sourceModel, targetThing, IHasEndpoints.ENDPOINT_1_KEY);
			}
		}
	}

	@Override
	protected void updateToTarget(IBNAModel sourceModel, IThing targetThing, ThingEvent targetThingEvent) {
		if (targetThingEvent != null) {
			Object propertyName = targetThingEvent.getPropertyName();
			if (propertyName instanceof Tuple && ((Tuple) propertyName).startsWith(STICKY_MODE_KEY)) {
				updateToTarget(sourceModel, ((Tuple) propertyName).getElement(1), targetThingEvent);
				return;
			}
		}
		super.updateToTarget(sourceModel, targetThing, targetThingEvent);
	}

	@Override
	public void bnaModelChangedSync(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_CHANGED: {
			ThingEvent te = evt.getThingEvent();
			IThing thing = te.getTargetThing();
			Object propertyName = te.getPropertyName();
			if (IHasMidpoints.MIDPOINTS_KEY.equals(propertyName)) {
				Point[] oldPoints = (Point[]) te.getOldPropertyValue();
				Point[] newPoints = (Point[]) te.getNewPropertyValue();
				if (pointChanged(oldPoints, newPoints, 0)) {
					checkSecondaryPoint(null, thing, IHasEndpoints.ENDPOINT_1_KEY);
				}
				if (pointChanged(oldPoints, newPoints, -1)) {
					checkSecondaryPoint(null, thing, IHasEndpoints.ENDPOINT_2_KEY);
				}
			}
			else {
				checkSecondaryPointFromPrimaryPoint(null, thing, propertyName);
			}
		}
		}
		super.bnaModelChangedSync(evt);
	}

	private boolean pointChanged(Point[] oldPoints, Point[] newPoints, int i) {
		if (oldPoints == null || newPoints == null) {
			return true;
		}
		Point oldPoint;
		Point newPoint;
		if (i >= 0) {
			oldPoint = i < oldPoints.length ? oldPoints[i] : null;
			newPoint = i < newPoints.length ? newPoints[i] : null;
		}
		else {
			oldPoint = oldPoints.length + i >= 0 ? oldPoints[oldPoints.length + i] : null;
			newPoint = newPoints.length + i >= 0 ? newPoints[newPoints.length + i] : null;
		}
		return oldPoint == null || newPoint == null || !oldPoint.equals(newPoint);
	}

	@Override
	protected boolean isTargetThing(IThing targetThing, ThingEvent targetThingEvent) {
		if (targetThingEvent != null) {
			Object propertyName = targetThingEvent.getPropertyName();
			if (propertyName instanceof Tuple && ((Tuple) propertyName).startsWith(STICKY_MODE_KEY)) {
				// the stickyMode has changed, update the value
				return super.isTargetThing(targetThing, null);
			}
		}
		return super.isTargetThing(targetThing, targetThingEvent);
	}
}
