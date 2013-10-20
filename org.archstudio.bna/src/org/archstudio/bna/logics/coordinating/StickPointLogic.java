package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasShapeKeys;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractCoordinatingThingLogic;
import org.archstudio.bna.logics.coordinating.StickPointLogic.IHasLoopablePoint.LoopType;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public class StickPointLogic extends AbstractCoordinatingThingLogic implements IBNAModelListener {

	public boolean DEBUG = false;

	@NonNullByDefault
	public static interface IHasSecondaryPoint extends IThing, IHasShapeKeys {

		public Point getSecondaryPoint(IBNAModel model, StickPointLogic stickLogic, IThingKey<Point> pointKey);

	}

	@NonNullByDefault
	public static interface IHasLoopablePoint extends IHasSecondaryPoint {

		public static enum LoopType {
			NONE, TOP_RIGHT, BOTTOM_LEFT
		}

		public static final IThingKey<LoopType> LOOP_TYPE_KEY = ThingKey.create("loop-type");

		public LoopType getLoopType(IBNAModel model, StickPointLogic stickLogic, IThingKey<Point> pointKey);

		public void setLoopType(LoopType loopType);

	}

	private class PointUpdater extends Updater {

		final Object pointThingID;
		final IThingKey<Point> pointKey;
		final StickyMode stickyMode;
		final Object stickyThingID;

		Shape stickyShape = null;

		public PointUpdater(IThing pointThing, IThingKey<Point> pointKey, StickyMode stickyMode, IIsSticky stickyThing) {
			this.pointThingID = pointThing.getID();
			this.pointKey = pointKey;
			this.stickyMode = stickyMode;
			this.stickyThingID = stickyThing.getID();
			stickyShape = stickyThing.getStickyShape();
		}

		@Override
		public void update() {
			IThing pointThing = model.getThing(pointThingID);
			if (pointThing != null) {
				IIsSticky stickyThing = SystemUtils.castOrNull(model.getThing(stickyThingID), IIsSticky.class);
				if (stickyThing != null) {

					if (DEBUG) {
						System.err.println("    " + pointKey + " -> " + SystemUtils.simpleName(pointThing.getClass())
								+ "(" + pointThing.getUID() + "):" + pointThing.get(pointKey) + " " + stickyMode + " "
								+ SystemUtils.simpleName(stickyThing.getClass()) + "(" + stickyThing.getUID() + "):"
								+ stickyThing.getStickyShape());
					}

					Point stuckPoint = pointThing.get(pointKey);

					// adjust the point proportionally if the 'stickyThing' has resized/moved
					Shape newStickyShape = stickyThing.getStickyShape();
					if (!newStickyShape.equals(stickyShape)) {
						stuckPoint = BNAUtils.movePointWith(BNAUtils.toRectangle(stickyShape.getBounds()),
								BNAUtils.toRectangle(newStickyShape.getBounds()), stuckPoint);
						stickyShape = newStickyShape;
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

						IHasSecondaryPoint secondaryPointThing = (IHasSecondaryPoint) pointThing;

						// get center point
						{
							Rectangle2D r = stickyShape.getBounds();
							stuckPoint = new Point(BNAUtils.round(r.getCenterX()), BNAUtils.round(r.getCenterY()));
						}

						// get secondary point ...
						Point secondaryPoint;

						// ... check for loop
						LoopType loopType = LoopType.NONE;
						if (pointThing instanceof IHasLoopablePoint) {
							IHasLoopablePoint loopable = (IHasLoopablePoint) pointThing;
							loopType = loopable.getLoopType(model, StickPointLogic.this, pointKey);
						}
						if (loopType != null && loopType != LoopType.NONE) {

							// determine top-right point offsets
							Rectangle r = BNAUtils.toRectangle(stickyShape.getBounds());
							int preferred = 30;
							int x, dx = Math.min(preferred, r.width / 2);
							int y, dy = Math.min(preferred, r.height / 2);
							if (pointKey.equals(IHasEndpoints.ENDPOINT_1_KEY)) {
								switch (loopType) {
								case TOP_RIGHT:
									x = r.x + r.width;
									y = r.y + dy;
									break;
								case BOTTOM_LEFT:
									x = r.x;
									y = r.y + r.height - dy;
									break;
								default:
									throw new IllegalArgumentException(loopType.toString());
								}
							}
							else if (pointKey.equals(IHasEndpoints.ENDPOINT_2_KEY)) {
								switch (loopType) {
								case TOP_RIGHT:
									x = r.x + r.width - dx;
									y = r.y;
									break;
								case BOTTOM_LEFT:
									x = r.x + dx;
									y = r.y + r.height;
									break;
								default:
									throw new IllegalArgumentException(loopType.toString());
								}
							}
							else {
								throw new IllegalArgumentException(pointKey.toString());
							}

							// get offset top-right point
							secondaryPoint = new Point(x, y);

							((IHasLoopablePoint) pointThing).setLoopType(loopType);
						}
						else {
							// get secondary point
							secondaryPoint = secondaryPointThing.getSecondaryPoint(model, StickPointLogic.this,
									pointKey);

							if (pointThing instanceof IHasLoopablePoint) {
								((IHasLoopablePoint) pointThing).setLoopType(LoopType.NONE);
							}
						}

						// calculate the closest point on the sticky shape, given the current point as reference
						stuckPoint = BNAUtils.getClosestPointOnShape(stickyShape, secondaryPoint.x, secondaryPoint.y,
								stuckPoint.x, stuckPoint.y);

					}
						break;
					}

					// update the actual stuck point
					Point oldStuckPoint = pointThing.getAndSet(pointKey, stuckPoint);
					if (!stuckPoint.equals(oldStuckPoint)) {
						if (DEBUG) {
							System.err.println("     -- " + oldStuckPoint + " -> " + stuckPoint);
						}
					}
				}
			}
		}
	}

	public StickPointLogic(IBNAWorld world) {
		super(world);
	}

	synchronized public void stick(IThing pointThing, IThingKey<Point> pointKey, StickyMode stickyMode,
			IIsSticky stickyThing) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);
		checkNotNull(stickyMode);
		checkNotNull(stickyThing);

		PointUpdater oldUpdater = getRegistered(pointThing, pointKey);
		if (oldUpdater != null && oldUpdater.stickyMode == stickyMode
				&& oldUpdater.stickyThingID.equals(stickyThing.getID())) {
			return;
		}

		if (stickyMode != null && stickyThing != null) {
			PointUpdater updater = new PointUpdater(pointThing, pointKey, stickyMode, stickyThing);
			register(updater, pointThing, pointKey);

			if (pointThing instanceof IHasShapeKeys) {
				track(updater, pointThing, ((IHasShapeKeys) pointThing).getShapeModifyingKeys());
				untrack(updater, pointThing, pointKey);
			}
			track(updater, stickyThing, stickyThing.getShapeModifyingKeys());
		}
		else {
			unstick(pointThing, pointKey);
		}
	}

	synchronized public void unstick(IThing pointThing, IThingKey<Point> pointKey) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);

		unregister(pointThing, pointKey);
	}

	synchronized public @Nullable
	StickyMode getStickyMode(IThing pointThing, IThingKey<Point> pointKey) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);

		PointUpdater updater = getRegistered(pointThing, pointKey);
		if (updater != null) {
			return updater.stickyMode;
		}
		return null;
	}

	synchronized public @Nullable
	Object getStickyThingID(IThing pointThing, IThingKey<Point> pointKey) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);

		PointUpdater updater = getRegistered(pointThing, pointKey);
		if (updater != null) {
			return updater.stickyThingID;
		}
		return null;
	}

	synchronized public @Nullable
	IIsSticky getStickyThing(IThing pointThing, IThingKey<Point> pointKey) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);

		PointUpdater updater = getRegistered(pointThing, pointKey);
		if (updater != null) {
			return SystemUtils.castOrNull(model.getThing(updater.stickyThingID), IIsSticky.class);
		}
		return null;
	}

	synchronized public Point getStuckPoint(IThing pointThing, IThingKey<Point> pointKey) {
		return getStuckPoint(pointThing, pointKey, pointThing.get(pointKey));
	}

	synchronized public Point getStuckPoint(IThing pointThing, IThingKey<Point> pointKey, Point stuckPoint) {
		/*
		 * Check to see if the point is stuck to something, and if stuck to the center point of that thing, return the
		 * center point rather than the secondary point as this will be more accurate
		 */
		PointUpdater updater = getRegistered(pointThing, pointKey);
		if (updater != null) {
			if (updater.stickyMode.isStuckToCenterPoint()) {
				IIsSticky stickyThing = SystemUtils.castOrNull(model.getThing(updater.stickyThingID), IIsSticky.class);
				if (stickyThing != null) {

					// if so, get the center point of what it's stuck to
					stuckPoint = BNAUtils.getCentralPoint(stickyThing);
				}
			}
		}
		return stuckPoint;
	}

	synchronized public boolean isLoopingPoint(IThing pointThing, IThingKey<Point> endpoint1Key,
			IThingKey<Point> endpoint2Key) {
		PointUpdater endpoint1Updater = getRegistered(pointThing, endpoint1Key);
		PointUpdater endpoint2Updater = getRegistered(pointThing, endpoint2Key);
		if (endpoint1Updater != null && endpoint2Updater != null) {
			if (endpoint1Updater.stickyThingID == endpoint2Updater.stickyThingID) {
				return true;
			}
		}
		return false;
	}
}
