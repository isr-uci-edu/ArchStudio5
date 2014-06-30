package org.archstudio.bna.logics.editing;

import static org.archstudio.sysutils.SystemUtils.castOrNull;
import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.awt.Dimension;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.constants.MouseType;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMidpoints;
import org.archstudio.bna.facets.IHasMutableEndpoints;
import org.archstudio.bna.facets.IHasMutableMidpoints;
import org.archstudio.bna.facets.IHasMutablePoints;
import org.archstudio.bna.facets.IHasPreciseAnchorPoint;
import org.archstudio.bna.facets.IHasStandardCursor;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.archstudio.bna.logics.coordinating.DynamicStickPointLogic;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.logics.events.DragMoveEvent;
import org.archstudio.bna.things.shapes.PreciselyAnchoredShapeThing;
import org.archstudio.bna.things.shapes.ReshapeHandleThing;
import org.archstudio.bna.ui.IBNAMouseClickListener;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.DefaultCoordinate;
import org.archstudio.bna.utils.ShapeUtils;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class ReshapeSplineLogic extends AbstractReshapeLogic<IThing, Integer> implements IBNAMouseClickListener {

	private static IThingKey<IThingKey<Point>> POINT_KEY_KEY = ThingKey.create("pointKey");
	private static IThingKey<IThingKey<List<Point>>> POINTS_KEY_KEY = ThingKey.create("pointsKey");
	private static IThingKey<Integer> POINTS_INDEX_KEY = ThingKey.create("index");
	private static IThingKey<Boolean> REMOVE_KEY = ThingKey.create("remove");
	private static IThingRefKey<PreciselyAnchoredShapeThing> REMOVE_PART_KEY = ThingRefKey.create("remove-part");

	private static final RGB UNSTUCK_COLOR = new RGB(255, 0, 0);
	private static final RGB STUCK_COLOR = new RGB(0, 255, 0);
	private static final RGB NORMAL_COLOR = new RGB(0, 0, 255);

	private static final int MERGE_DIST = 8;
	private static final int STICK_DIST = 8;
	private static final int UNSTICK_DIST = 8;

	protected final DynamicStickPointLogic dynamicStickLogic;
	protected final StickPointLogic stickLogic;
	protected final MirrorValueLogic mirrorLogic;

	private final List<IReshapeSplineGuide> reshapeSplineGuides = Lists.newArrayList();

	public ReshapeSplineLogic(IBNAWorld world) {
		super(world, IThing.class);
		dynamicStickLogic = logics.addThingLogic(DynamicStickPointLogic.class);
		stickLogic = logics.addThingLogic(StickPointLogic.class);
		mirrorLogic = logics.addThingLogic(MirrorValueLogic.class);
		logics.addThingLogic(StandardCursorLogic.class);
	}

	synchronized public void addReshapeSplineGuides(IReshapeSplineGuide... guides) {
		reshapeSplineGuides.addAll(Arrays.asList(guides));
	}

	@SuppressWarnings("unchecked")
	private void augmentHandle(ReshapeHandleThing handle, IThingKey<?> key, int index) {
		if (index == -1) {
			handle.set(POINT_KEY_KEY, (IThingKey<Point>) key);
		}
		else {
			handle.set(POINTS_KEY_KEY, (IThingKey<List<Point>>) key);
			handle.set(POINTS_INDEX_KEY, index);
		}
		handle.set(REMOVE_KEY, false);
	}

	@SuppressWarnings("unchecked")
	@Override
	synchronized public void bnaModelChanged(BNAModelEvent evt) {
		super.bnaModelChanged(evt);
		ThingEvent thingEvent = evt.getThingEvent();
		if (thingEvent != null) {
			if (IHasMidpoints.MIDPOINTS_KEY.equals(thingEvent.getPropertyName())) {
				int oldSize = ((Collection<Point>) thingEvent.getOldPropertyValue()).size();
				int newSize = ((Collection<Point>) thingEvent.getNewPropertyValue()).size();
				if (oldSize != newSize) {
					resetHandles();
				}
			}
		}
	}

	@Override
	protected void addHandles(IThing reshapingThing) {
		int index = 0;
		if (reshapingThing instanceof IHasMutableEndpoints) {
			if (UserEditableUtils.isEditableForAnyQualities(reshapingThing,
					IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT2, IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT2)) {
				augmentHandle(addHandle(reshapingThing, Assemblies.createHandle(world, null, null), index++),
						IHasEndpoints.ENDPOINT_2_KEY, -1);
			}
		}
		if (reshapingThing instanceof IHasMutableMidpoints) {
			if (UserEditableUtils.isEditableForAnyQualities(reshapingThing,
					IHasMutableMidpoints.USER_MAY_MOVE_MIDPOINTS, IHasMutableMidpoints.USER_MAY_REMOVE_MIDPOINTS)) {
				List<Point> midpoints = ((IHasMutableMidpoints) reshapingThing).getMidpoints();
				for (int midpointIndex = 0; midpointIndex < midpoints.size(); midpointIndex++) {
					augmentHandle(addHandle(reshapingThing, Assemblies.createHandle(world, null, null), index++),
							IHasMidpoints.MIDPOINTS_KEY, midpointIndex);
				}
			}
		}
		// cover this endpoint last so that it is the topmost handle
		if (reshapingThing instanceof IHasMutableEndpoints) {
			if (UserEditableUtils.isEditableForAnyQualities(reshapingThing,
					IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT1, IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT1)) {
				augmentHandle(addHandle(reshapingThing, Assemblies.createHandle(world, null, null), index++),
						IHasEndpoints.ENDPOINT_1_KEY, -1);
			}
		}
	}

	@Override
	protected Runnable takeSnapshot(IThing reshapingThing) {

		final Object reshapingThingID = reshapingThing.getID();

		final Point endpoint1 = reshapingThing.get(IHasEndpoints.ENDPOINT_1_KEY);
		final StickyMode stickToThingMode1 = reshapingThing.get(dynamicStickLogic
				.getStickyModeKey(IHasEndpoints.ENDPOINT_1_KEY));
		final Object stickToThingID1 = reshapingThing.get(dynamicStickLogic
				.getStickyThingKey(IHasEndpoints.ENDPOINT_1_KEY));

		final List<Point> midpoints = reshapingThing.get(IHasMidpoints.MIDPOINTS_KEY);

		final Point endpoint2 = reshapingThing.get(IHasEndpoints.ENDPOINT_2_KEY);
		final StickyMode stickToThingMode2 = reshapingThing.get(dynamicStickLogic
				.getStickyModeKey(IHasEndpoints.ENDPOINT_2_KEY));
		final Object stickToThingID2 = reshapingThing.get(dynamicStickLogic
				.getStickyThingKey(IHasEndpoints.ENDPOINT_2_KEY));

		return new Runnable() {
			@Override
			public void run() {
				IThing t = SystemUtils.castOrNull(model.getThing(reshapingThingID), IThing.class);
				if (t != null) {

					// since the dynamic stick logic updates asynchronously, we also update through the stick logic

					if (stickToThingID1 != null && stickToThingMode1 != null) {
						t.set(dynamicStickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_1_KEY), stickToThingMode1);
						t.set(dynamicStickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_1_KEY), stickToThingID1);
					}
					else {
						t.remove(dynamicStickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_1_KEY));
						t.remove(dynamicStickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_1_KEY));
						stickLogic.unstick(t, IHasEndpoints.ENDPOINT_1_KEY);
					}
					if (endpoint1 != null) {
						t.set(IHasEndpoints.ENDPOINT_1_KEY, endpoint1);
					}

					if (midpoints != null) {
						t.set(IHasMidpoints.MIDPOINTS_KEY, midpoints);
					}

					if (stickToThingID2 != null && stickToThingMode2 != null) {
						t.set(dynamicStickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_2_KEY), stickToThingMode2);
						t.set(dynamicStickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_2_KEY), stickToThingID2);
					}
					else {
						t.remove(dynamicStickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_2_KEY));
						t.remove(dynamicStickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_2_KEY));
						stickLogic.unstick(t, IHasEndpoints.ENDPOINT_2_KEY);
					}
					if (endpoint2 != null) {
						t.set(IHasEndpoints.ENDPOINT_2_KEY, endpoint2);
					}

				}
			}
		};
	}

	@Override
	protected void updateHandle(IThing reshapingThing, ReshapeHandleThing handle, Integer data) {
		Point point = handle.getAnchorPoint();
		if (handle.get(POINT_KEY_KEY) != null) {
			IThingKey<Point> pointKey = handle.get(POINT_KEY_KEY);
			point = stickLogic.getStuckPoint(reshapingThing, pointKey);
		}
		if (handle.get(POINTS_KEY_KEY) != null) {
			IThingKey<List<Point>> pointsKey = handle.get(POINTS_KEY_KEY);
			int index = handle.get(POINTS_INDEX_KEY);
			List<Point> points = reshapingThing.get(pointsKey);
			if (index < points.size()) {
				point = points.get(index);
			}
		}
		handle.setAnchorPoint(point);
		handle.set(IHasStandardCursor.STANDARD_CURSOR_KEY, SWT.CURSOR_SIZEALL);

		boolean shouldBeStuck = false;
		boolean isStuck = false;
		IThingKey<Point> pointKey = handle.get(POINT_KEY_KEY);
		if (pointKey != null) {
			for (IReshapeSplineGuide guide : reshapeSplineGuides) {
				shouldBeStuck |= guide.shouldBeStuck(reshapingThing, pointKey);
			}
			isStuck |= stickLogic.getStickyThingID(reshapingThing, pointKey) != null;
		}
		handle.setColor(shouldBeStuck ? isStuck ? STUCK_COLOR : UNSTUCK_COLOR : NORMAL_COLOR);

		if (handle.has(REMOVE_KEY, true)) {
			if (Assemblies.getPart(model, handle, REMOVE_PART_KEY) == null) {
				PreciselyAnchoredShapeThing removalIndicatorThing = model
						.addThing(new PreciselyAnchoredShapeThing(null));
				removalIndicatorThing.setShape(ShapeUtils.createUnitX(0.35, 0.1, 0));
				removalIndicatorThing.setColor(new RGB(255, 0, 0));
				removalIndicatorThing.setEdgeColor(new RGB(0, 0, 0));
				removalIndicatorThing.setSize(new Dimension(20, 20));
				mirrorLogic.mirrorValue(handle, IHasAnchorPoint.ANCHOR_POINT_KEY, removalIndicatorThing,
						IHasPreciseAnchorPoint.PRECISION_ANCHOR_POINT_KEY, new Function<Point, Point2D>() {
							@Override
							public Point2D apply(Point input) {
								return BNAUtils.toPoint2D(input);
							}
						});
				Assemblies.markPart(handle, REMOVE_PART_KEY, removalIndicatorThing);
			}
		}
		else {
			Assemblies.removeRootAndParts(model, REMOVE_PART_KEY.get(handle, model));
		}
	}

	@Override
	protected void handleMoved(IThing reshapingThing, ReshapeHandleThing handle, Integer data, DragMoveEvent evt) {

		Point mp = evt.getMouseLocation().getWorldPoint();
		Point amp = evt.getAdjustedMouseLocation().getWorldPoint();
		ICoordinateMapper cm = evt.getView().getCoordinateMapper();

		if (handle.get(POINT_KEY_KEY) != null) {
			IThingKey<Point> pointKey = handle.get(POINT_KEY_KEY);

			// an explicit point is being moved
			StickyMode stickToThingMode = reshapingThing.get(dynamicStickLogic.getStickyModeKey(pointKey));
			Object stickToThingID = reshapingThing.get(dynamicStickLogic.getStickyThingKey(pointKey));

			// if pulled far away from a stuck point, unstick it
			if (BNAUtils.getDistance(reshapingThing.get(pointKey), amp) >= UNSTICK_DIST) {
				stickToThingID = null;
			}

			// if moved close to a sticky thing, stick to it
			for (IIsSticky stickyThing : Iterables.filter(Lists.reverse(model.getAllThings()), IIsSticky.class)) {
				if (stickyThing instanceof ReshapeHandleThing) {
					continue;
				}
				for (IReshapeSplineGuide guide : reshapeSplineGuides) {
					StickyMode stickyMode = guide.getStickyMode(reshapingThing, stickyThing, pointKey);
					if (stickyMode != null) {
						Point stuckPoint = BNAUtils.getClosestPointOnShape(stickyThing.getStickyShape(), mp.x, mp.y);
						IBNAView view = evt.getView();
						IThingPeer<?> peer = view.getThingPeer(stickyThing);
						if (stuckPoint != null
								&& (BNAUtils.getDistance(stuckPoint, mp) <= STICK_DIST || peer
										.isInThing(DefaultCoordinate.forWorld(mp, cm)))) {
							stickToThingID = stickyThing.getID();
							stickToThingMode = stickyMode;
						}
					}
				}
			}

			// update the stuck thing
			reshapingThing.set(dynamicStickLogic.getStickyModeKey(pointKey), stickToThingMode);
			reshapingThing.set(dynamicStickLogic.getStickyThingKey(pointKey), stickToThingID);

			// update the point if not stuck
			if (stickToThingID == null || stickToThingMode == null) {
				reshapingThing.set(pointKey, amp);
			}
		}

		if (handle.get(POINTS_KEY_KEY) != null) {
			IThingKey<List<Point>> pointsKey = handle.get(POINTS_KEY_KEY);
			int index = handle.get(POINTS_INDEX_KEY);

			// remove point if it is dragged close to its neighbors
			if (IHasMidpoints.MIDPOINTS_KEY.equals(pointsKey)
					&& UserEditableUtils.isEditableForAnyQualities(reshapingThing,
							IHasMutableMidpoints.USER_MAY_REMOVE_MIDPOINTS)) {
				List<Point> points = reshapingThing.get(pointsKey);
				if (index < points.size()) {

					// midpoint being tested for removal
					Point lmp = cm.worldToLocal(mp);

					// next point on one side
					Point p1 = null;
					if (index > 0) {
						p1 = points.get(index - 1);
					}
					else {
						if (reshapingThing instanceof IHasEndpoints) {
							p1 = stickLogic.getStuckPoint(reshapingThing, IHasEndpoints.ENDPOINT_1_KEY);
						}
					}
					Point lp1 = p1 != null ? cm.worldToLocal(p1) : null;

					// next point on other side
					Point p2 = null;
					if (index < points.size() - 1) {
						p2 = points.get(index + 1);
					}
					else {
						if (reshapingThing instanceof IHasEndpoints) {
							p2 = stickLogic.getStuckPoint(reshapingThing, IHasEndpoints.ENDPOINT_2_KEY);
						}
					}
					Point lp2 = p2 != null ? cm.worldToLocal(p2) : null;

					// if it's close enough, mark it to be removed
					boolean remove = false;
					if (lp1 != null && (BNAUtils.getDistance(lmp, lp1) <= MERGE_DIST || amp.equals(p1))) {
						remove = true;
						amp = p1;
					}
					if (lp2 != null && (BNAUtils.getDistance(lmp, lp2) <= MERGE_DIST || amp.equals(p2))) {
						remove = true;
						amp = p2;
					}
					handle.set(REMOVE_KEY, remove);
				}
			}

			List<Point> points = reshapingThing.get(pointsKey);
			if (index < points.size()) {
				points.set(index, amp);
				reshapingThing.set(pointsKey, points);
			}
		}
	}

	@Override
	protected void handleMoveFinished(IThing reshapingThing, ReshapeHandleThing handle, Integer data, DragMoveEvent evt) {

		if (handle.get(POINT_KEY_KEY) != null) {
			IThingKey<Point> pointKey = handle.get(POINT_KEY_KEY);
			if (handle.get(REMOVE_KEY, false)) {
				reshapingThing.remove(pointKey);
			}
		}

		if (handle.get(POINTS_KEY_KEY) != null) {
			IThingKey<List<Point>> pointsKey = handle.get(POINTS_KEY_KEY);
			int index = handle.get(POINTS_INDEX_KEY);
			if (handle.get(REMOVE_KEY, false)) {
				List<Point> points = reshapingThing.get(pointsKey);
				if (index < points.size()) {
					points.remove(index);
					reshapingThing.set(pointsKey, points);
				}
			}
		}

		super.handleMoveFinished(reshapingThing, handle, data, evt);
	}

	@Override
	synchronized public void mouseClick(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things,
			ICoordinate location) {
		if (evt.count == 2) {
			final IHasMutablePoints t = castOrNull(firstOrNull(things), IHasMutablePoints.class);
			if (t != null
					&& UserEditableUtils.isEditableForAllQualities(t, IHasMutableMidpoints.USER_MAY_ADD_MIDPOINTS)) {

				// insert the new point
				boolean pointAdded = false;
				Point worldPoint = location.getWorldPoint();
				final IHasMutablePoints finalT = t;
				final List<Point> oldPoints = t.getPoints();
				final List<Point> newPoints = t.getPoints();
				for (int i = 1; i < newPoints.size(); i++) {
					Point p1 = newPoints.get(i - 1);
					Point p2 = newPoints.get(i);
					double dist = Line2D.ptSegDist(p2.x, p2.y, p1.x, p1.y, worldPoint.x, worldPoint.y);
					if (dist <= 5) {
						pointAdded = true;
						newPoints.add(i, new Point(worldPoint.x, worldPoint.y));
						break;
					}
				}

				// if a point wasn't added, do so now
				if (!pointAdded) {
					newPoints.add(new Point(worldPoint.x, worldPoint.y));
				}

				BNAOperations.runnable("Reshape", new Runnable() {

					@Override
					public void run() {
						finalT.setPoints(oldPoints);
					}
				}, new Runnable() {

					@Override
					public void run() {
						finalT.setPoints(newPoints);
					}
				}, true);
			}
		}
	}
}
