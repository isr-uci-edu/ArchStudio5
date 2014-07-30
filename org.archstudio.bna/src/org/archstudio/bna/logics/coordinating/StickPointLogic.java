package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Set;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMutableLoopOrientation;
import org.archstudio.bna.facets.IHasStickyShape;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.logics.AbstractCoordinatingThingLogic;
import org.archstudio.bna.logics.coordinating.StickPointLogic.PointUpdater;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.Orientation;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Sets;

@NonNullByDefault
public class StickPointLogic extends AbstractCoordinatingThingLogic<PointUpdater> implements IBNAModelListener {

	public boolean DEBUG = false;

	@NonNullByDefault
	public static interface IHasSecondaryPoint extends IThing {

		public Point2D getSecondaryPoint(IBNAModel model, StickPointLogic stickLogic, IThingKey<Point2D> pointKey);

	}

	@NonNullByDefault
	public static interface IHasLoopablePoint extends IHasMutableLoopOrientation {

		public Orientation getLoopOrientation(IBNAModel model, StickPointLogic stickLogic, IThingKey<Point2D> pointKey);

	}

	public class PointUpdater extends AbstractCoordinatingThingLogic.Updater {

		final Object pointThingID;
		final IThingKey<Point2D> pointKey;
		final StickyMode stickyMode;
		final Object stickyThingID;
		Shape stickyShape;
		boolean updating;

		public PointUpdater(IThing pointThing, IThingKey<Point2D> pointKey, StickyMode stickyMode,
				IHasStickyShape stickyThing) {
			this.pointThingID = pointThing.getID();
			this.pointKey = pointKey;
			this.stickyMode = stickyMode;
			this.stickyThingID = stickyThing.getID();
			this.stickyShape = stickyThing.getStickyShape();
		}

		@Override
		public void update(ThingEvent event) {
			if (updating) {
				return;
			}
			if (event != null) {
				if (event.getTargetThing().getID().equals(stickyThingID)) {
					if (!event.getTargetThing().isShapeModifyingKey(event.getPropertyName())) {
						return;
					}
				}
				else {
					if (!event.getTargetThing().isShapeModifyingKey(event.getPropertyName())) {
						return;
					}
				}
			}

			IThing pointThing = model.getThing(pointThingID);
			if (pointThing != null) {
				IHasStickyShape stickyThing = SystemUtils.castOrNull(model.getThing(stickyThingID),
						IHasStickyShape.class);
				if (stickyThing != null) {

					if (DEBUG) {
						System.err.println("    " + pointKey + " -> " + SystemUtils.simpleName(pointThing.getClass())
								+ "(" + pointThing.getID() + "):" + pointThing.get(pointKey) + " " + stickyMode + " "
								+ SystemUtils.simpleName(stickyThing.getClass()) + "(" + stickyThing.getID() + "):"
								+ stickyThing.getStickyShape());
					}

					Point2D stuckPoint = pointThing.get(pointKey);
					if (stuckPoint == null) {
						stuckPoint = new Point2D.Double(0, 0);
					}

					// adjust the point proportionally if the 'stickyThing' has
					// resized/moved
					Shape newStickyShape = stickyThing.getStickyShape();
					if (!newStickyShape.equals(stickyShape)) {
						Rectangle2D from = stickyShape.getBounds2D();
						Rectangle2D to = newStickyShape.getBounds2D();
						double x = (stuckPoint.getX() - from.getMinX()) * to.getWidth() / from.getWidth()
								+ to.getMinX();
						double y = (stuckPoint.getY() - from.getMinY()) * to.getHeight() / from.getHeight()
								+ to.getMinY();
						stuckPoint.setLocation(x, y);
						stickyShape = newStickyShape;
					}

					switch (stickyMode) {
					case CENTER: {

						// get center point
						Rectangle2D r = stickyShape.getBounds();
						stuckPoint.setLocation(r.getCenterX(), r.getCenterY());

					}
						break;

					case EDGE: {

						// calculate the closest point on the sticky shape,
						// given the current point as reference
						stuckPoint = BNAUtils.getClosestPointOnShape(stickyShape, stuckPoint.getX(), stuckPoint.getY());

					}
						break;

					case EDGE_FROM_CENTER: {

						IHasSecondaryPoint secondaryPointThing = (IHasSecondaryPoint) pointThing;

						// get center point
						{
							Rectangle2D r = stickyShape.getBounds2D();
							stuckPoint.setLocation(r.getCenterX(), r.getCenterY());
						}

						// get secondary point ...
						Point2D secondaryPoint;

						// ... check for loop
						if (pointThing instanceof IHasLoopablePoint) {
							Orientation loopOrientation = ((IHasLoopablePoint) pointThing).getLoopOrientation(model,
									StickPointLogic.this, pointKey);

							if (loopOrientation != Orientation.NONE) {
								// determine point offsets
								Rectangle r = BNAUtils.toRectangle(stickyShape.getBounds());
								double preferred = 30;
								double x, dx = Math.min(preferred, r.width / 3);
								double y, dy = Math.min(preferred, r.height / 3);
								double x1 = r.x, x2 = r.x + r.width, xm = (x1 + x2) / 2;
								double y1 = r.y, y2 = r.y + r.height, ym = (y1 + y2) / 2;
								if (pointKey.equals(IHasEndpoints.ENDPOINT_1_KEY)) {
									switch (loopOrientation) {
									case NORTHWEST:
										x = x1;
										y = y1 + dy;
										break;
									case NORTH:
										x = xm - dx * 2 / 3;
										y = y1;
										break;
									case NORTHEAST:
										x = x2 - dx;
										y = y1;
										break;
									case WEST:
										x = x1;
										y = ym + dy * 2 / 3;
										break;
									case EAST:
										x = x2;
										y = ym - dy * 2 / 3;
										break;
									case SOUTHWEST:
										x = x1 + dx;
										y = y2;
										break;
									case SOUTH:
										x = xm + dx * 2 / 3;
										y = y2;
										break;
									case SOUTHEAST:
										x = x2;
										y = y2 - dy;
										break;
									default:
										throw new IllegalArgumentException(loopOrientation.toString());
									}
								}
								else if (pointKey.equals(IHasEndpoints.ENDPOINT_2_KEY)) {
									switch (loopOrientation) {
									case NORTHWEST:
										x = x1 + dx;
										y = y1;
										break;
									case NORTH:
										x = xm + dx * 2 / 3;
										y = y1;
										break;
									case NORTHEAST:
										x = x2;
										y = y1 + dy;
										break;
									case WEST:
										x = x1;
										y = ym - dy * 2 / 3;
										break;
									case EAST:
										x = x2;
										y = ym + dy * 2 / 3;
										break;
									case SOUTHWEST:
										x = x1;
										y = y2 - dy;
										break;
									case SOUTH:
										x = xm - dx * 2 / 3;
										y = y2;
										break;
									case SOUTHEAST:
										x = x2 - dx;
										y = y2;
										break;
									default:
										throw new IllegalArgumentException(loopOrientation.toString());
									}
								}
								else {
									throw new IllegalArgumentException(pointKey.toString());
								}
								secondaryPoint = new Point2D.Double(x, y);
							}
							else {
								// get secondary point
								secondaryPoint = secondaryPointThing.getSecondaryPoint(model, StickPointLogic.this,
										pointKey);
							}
							((IHasLoopablePoint) pointThing).setLoopOrientation(loopOrientation);
						}
						else {
							// get secondary point
							secondaryPoint = secondaryPointThing.getSecondaryPoint(model, StickPointLogic.this,
									pointKey);

							if (pointThing instanceof IHasLoopablePoint) {
								((IHasLoopablePoint) pointThing).setLoopOrientation(Orientation.NONE);
							}
						}

						// calculate the closest point on the sticky shape,
						// given the current point as reference
						stuckPoint = BNAUtils.getClosestPointOnShape(stickyShape, secondaryPoint.getX(),
								secondaryPoint.getY(), stuckPoint.getX(), stuckPoint.getY());

					}
						break;
					}

					// update the actual stuck point
					Point2D oldStuckPoint;
					updating = true;
					try {
						oldStuckPoint = pointThing.set(pointKey, stuckPoint);
					}
					finally {
						updating = false;
					}
					if (DEBUG) {
						if (!BNAUtils.like(stuckPoint, oldStuckPoint)) {
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

	public void stick(IThing pointThing, IThingKey<Point2D> pointKey, StickyMode stickyMode, IHasStickyShape stickyThing) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);
		checkNotNull(stickyMode);
		checkNotNull(stickyThing);
		BNAUtils.checkLock();

		for (PointUpdater oldUpdater : getRegisteredUpdater(pointThing, pointKey)) {
			if (oldUpdater != null && oldUpdater.stickyMode == stickyMode
					&& oldUpdater.stickyThingID.equals(stickyThing.getID())) {
				return;
			}
		}
		unregister(pointThing, pointKey);

		if (stickyMode != null && stickyThing != null) {
			PointUpdater updater = new PointUpdater(pointThing, pointKey, stickyMode, stickyThing);
			register(updater, pointThing, pointKey);
			removeWithThing(updater, pointThing, stickyThing);
			track(updater, pointThing, stickyThing);
		}
		else {
			unstick(pointThing, pointKey);
		}
	}

	public void unstick(IThing pointThing, IThingKey<Point2D> pointKey) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);
		BNAUtils.checkLock();

		unregister(pointThing, pointKey);
	}

	public @Nullable
	StickyMode getStickyMode(IThing pointThing, IThingKey<Point> pointKey) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);
		BNAUtils.checkLock();

		for (PointUpdater updater : getRegisteredUpdater(pointThing, pointKey)) {
			return updater.stickyMode;
		}
		return null;
	}

	public @Nullable
	Object getStickyThingID(IThing pointThing, IThingKey<Point2D> pointKey) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);
		BNAUtils.checkLock();

		for (PointUpdater updater : getRegisteredUpdater(pointThing, pointKey)) {
			return updater.stickyThingID;
		}
		return null;
	}

	public @Nullable
	IHasStickyShape getStickyThing(IThing pointThing, IThingKey<Point2D> pointKey) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);
		BNAUtils.checkLock();

		for (PointUpdater updater : getRegisteredUpdater(pointThing, pointKey)) {
			return SystemUtils.castOrNull(model.getThing(updater.stickyThingID), IHasStickyShape.class);
		}
		return null;
	}

	public Point2D getStuckPoint(IThing pointThing, IThingKey<Point2D> pointKey) {
		return getStuckPoint(pointThing, pointKey, pointThing.get(pointKey));
	}

	public Point2D getStuckPoint(IThing pointThing, IThingKey<Point2D> pointKey, Point2D stuckPoint) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);
		checkNotNull(stuckPoint);
		BNAUtils.checkLock();

		/*
		 * Check to see if the point is stuck to something, and if stuck to the center point of that thing, return the
		 * center point rather than the secondary point as this will be more accurate
		 */
		for (PointUpdater updater : getRegisteredUpdater(pointThing, pointKey)) {
			if (updater.stickyMode.isStuckToCenterPoint()) {
				IHasStickyShape stickyThing = SystemUtils.castOrNull(model.getThing(updater.stickyThingID),
						IHasStickyShape.class);
				if (stickyThing != null) {

					// if so, get the center point of what it's stuck to
					return BNAUtils.getCentralPoint(stickyThing);
				}
			}
		}
		return stuckPoint;
	}

	public boolean isLoopingPoint(IThing pointThing, IThingKey<Point2D> endpoint1Key, IThingKey<Point2D> endpoint2Key) {
		checkNotNull(pointThing);
		checkNotNull(endpoint1Key);
		checkNotNull(endpoint2Key);
		BNAUtils.checkLock();

		Set<Object> stickyThingID = Sets.newHashSet();
		for (PointUpdater updater : getRegisteredUpdater(pointThing, endpoint1Key)) {
			stickyThingID.add(updater.stickyThingID);
		}
		for (PointUpdater updater : getRegisteredUpdater(pointThing, endpoint2Key)) {
			if (stickyThingID.contains(updater.stickyThingID)) {
				return true;
			}
		}
		return false;
	}
}
