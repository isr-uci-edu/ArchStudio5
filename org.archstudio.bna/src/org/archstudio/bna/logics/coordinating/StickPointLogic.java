package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMutableLoopOrientation;
import org.archstudio.bna.facets.IHasStickyShape;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.logics.AbstractCoordinatingThingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.Orientation;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public class StickPointLogic extends AbstractCoordinatingThingLogic implements IBNAModelListener {

	public boolean DEBUG = false;

	@NonNullByDefault
	public static interface IHasSecondaryPoint extends IThing {

		public Point2D getSecondaryPoint(IBNAModel model, StickPointLogic stickLogic, IThingKey<Point2D> pointKey);

	}

	@NonNullByDefault
	public static interface IHasLoopablePoint extends IHasMutableLoopOrientation {

		public Orientation getLoopOrientation(IBNAModel model, StickPointLogic stickLogic, IThingKey<Point2D> pointKey);

	}

	private class PointUpdater extends Updater {

		final Object pointThingID;
		final IThingKey<Point2D> pointKey;
		final StickyMode stickyMode;
		final Object stickyThingID;

		Shape stickyShape = null;

		public PointUpdater(IThing pointThing, IThingKey<Point2D> pointKey, StickyMode stickyMode,
				IHasStickyShape stickyThing) {
			this.pointThingID = pointThing.getID();
			this.pointKey = pointKey;
			this.stickyMode = stickyMode;
			this.stickyThingID = stickyThing.getID();
			stickyShape = stickyThing.getStickyShape();
		}

		private double select(int value, double negative, double zero, double positive) {
			return value < 0 ? negative : value > 0 ? positive : zero;
		}

		@Override
		public void update() {
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
								double x, dx = Math.min(preferred, r.width / 2);
								double y, dy = Math.min(preferred, r.height / 2);
								if (pointKey.equals(IHasEndpoints.ENDPOINT_1_KEY)) {
									x = select(loopOrientation.getDeltaX(), r.x, 0, r.x + r.width);
									y = select(loopOrientation.getDeltaY(), r.y + dy, 0, r.y + r.height - dy);
								}
								else if (pointKey.equals(IHasEndpoints.ENDPOINT_2_KEY)) {
									x = select(loopOrientation.getDeltaX(), r.x + dx, 0, r.x + r.width - dx);
									y = select(loopOrientation.getDeltaY(), r.y, 0, r.y + r.height);
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
					Point2D oldStuckPoint = pointThing.set(pointKey, stuckPoint);
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

	synchronized public void stick(IThing pointThing, IThingKey<Point2D> pointKey, StickyMode stickyMode,
			IHasStickyShape stickyThing) {
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

			track(updater, pointThing, pointThing.getShapeModifyingKeys());
			untrack(updater, pointThing, pointKey);
			track(updater, stickyThing, stickyThing.getShapeModifyingKeys());
		}
		else {
			unstick(pointThing, pointKey);
		}
	}

	synchronized public void unstick(IThing pointThing, IThingKey<Point2D> pointKey) {
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
	Object getStickyThingID(IThing pointThing, IThingKey<Point2D> pointKey) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);

		PointUpdater updater = getRegistered(pointThing, pointKey);
		if (updater != null) {
			return updater.stickyThingID;
		}
		return null;
	}

	synchronized public @Nullable
	IHasStickyShape getStickyThing(IThing pointThing, IThingKey<Point2D> pointKey) {
		checkNotNull(pointThing);
		checkNotNull(pointKey);

		PointUpdater updater = getRegistered(pointThing, pointKey);
		if (updater != null) {
			return SystemUtils.castOrNull(model.getThing(updater.stickyThingID), IHasStickyShape.class);
		}
		return null;
	}

	synchronized public Point2D getStuckPoint(IThing pointThing, IThingKey<Point2D> pointKey) {
		return getStuckPoint(pointThing, pointKey, pointThing.get(pointKey));
	}

	synchronized public Point2D getStuckPoint(IThing pointThing, IThingKey<Point2D> pointKey, Point2D stuckPoint) {
		/*
		 * Check to see if the point is stuck to something, and if stuck to the center point of that thing, return the
		 * center point rather than the secondary point as this will be more accurate
		 */
		PointUpdater updater = getRegistered(pointThing, pointKey);
		if (updater != null) {
			if (updater.stickyMode.isStuckToCenterPoint()) {
				IHasStickyShape stickyThing = SystemUtils.castOrNull(model.getThing(updater.stickyThingID),
						IHasStickyShape.class);
				if (stickyThing != null) {

					// if so, get the center point of what it's stuck to
					stuckPoint = BNAUtils.getCentralPoint(stickyThing);
				}
			}
		}
		return stuckPoint;
	}

	synchronized public boolean isLoopingPoint(IThing pointThing, IThingKey<Point2D> endpoint1Key,
			IThingKey<Point2D> endpoint2Key) {
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
