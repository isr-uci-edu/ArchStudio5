package org.archstudio.bna.logics.editing;

import java.util.Arrays;
import java.util.List;

import org.archstudio.bna.DefaultCoordinate;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableEndpoints;
import org.archstudio.bna.facets.IHasMutableMidpoints;
import org.archstudio.bna.facets.IHasMutablePoints;
import org.archstudio.bna.facets.IHasStandardCursor;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.logics.coordinating.DynamicStickPointLogic;
import org.archstudio.bna.logics.events.DragMoveEvent;
import org.archstudio.bna.things.glass.ReshapeHandleGlassThing;
import org.archstudio.bna.things.shapes.ReshapeHandleThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class ReshapeSplineLogic extends AbstractReshapeLogic<IHasMutablePoints, Integer> {

	private static final RGB UNSTUCK_COLOR = new RGB(255, 0, 0);
	private static final RGB STUCK_COLOR = new RGB(0, 255, 0);
	private static final RGB NORMAL_COLOR = new RGB(0, 0, 255);

	private static final int MERGE_DIST = 8;
	private static final int STICK_DIST = 8;
	private static final int UNSTICK_DIST = 8;

	private final List<IReshapeSplineGuide> reshapeSplineGuides = Lists.newArrayList();

	protected DynamicStickPointLogic stickLogic = null;

	public ReshapeSplineLogic() {
		super(IHasMutablePoints.class);
	}

	@Override
	protected void init() {
		super.init();
		stickLogic = addThingLogic(DynamicStickPointLogic.class);
		addThingLogic(StandardCursorLogic.class);
	}

	public void addReshapeSplineGuides(IReshapeSplineGuide... guides) {
		reshapeSplineGuides.addAll(Arrays.asList(guides));
	}

	int potentialHandles = 0;

	@Override
	protected void addHandles() {
		potentialHandles = 0;
		for (int i = 0; i < reshapingThing.getPointsSize(); i++) {
			potentialHandles++;

			if (i == 0) {
				if (!UserEditableUtils.isEditableForAnyQualities(reshapingThing,
						IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT1, IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT1)) {
					continue;
				}
			}
			else if (i == reshapingThing.getPointsSize() - 1) {
				if (!UserEditableUtils.isEditableForAnyQualities(reshapingThing,
						IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT2, IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT2)) {
					continue;
				}
			}
			else if (!UserEditableUtils.isEditableForAnyQualities(reshapingThing,
					IHasMutableMidpoints.USER_MAY_MOVE_MIDPOINTS)) {
				continue;
			}

			addHandle(Assemblies.createHandle(getBNAWorld(), null, null), i);
		}
	}

	@Override
	protected synchronized void updateHandles() {
		if (reshapingThing != null) {
			if (potentialHandles != reshapingThing.getPointsSize()) {
				removeHandles();
				addHandles();
			}
		}
		super.updateHandles();
	}

	@Override
	protected void updateHandle(ReshapeHandleGlassThing handle, Integer data) {

		// if stuck to the center of something, place the handle at the center (not the calculated edge point)
		Point point = reshapingThing.getPoint(data);
		IThingKey<Point> endpointKey = getEndpointKey(data);
		if (endpointKey != null) {
			StickyMode stickyMode = reshapingThing.get(stickLogic.getStickyModeKey(endpointKey));
			if (stickyMode != null && stickyMode.isStuckToCenterPoint()) {
				IIsSticky stickyThing = stickLogic.getStickyThingKey(endpointKey).get(reshapingThing, getBNAModel());
				if (stickyThing != null) {
					point = stickyThing.getStickyPointNear(StickyMode.CENTER, reshapingThing.getPoint(data));
				}
			}
		}

		handle.setAnchorPoint(point);
		handle.set(IHasStandardCursor.STANDARD_CURSOR_KEY, SWT.CURSOR_SIZEALL);
		updateColor(handle, data);
	}

	@Override
	protected void handleMoved(ReshapeHandleGlassThing handle, Integer data, DragMoveEvent evt) {

		Point p = evt.getMouseLocation().getWorldPoint();
		Point ap = evt.getAdjustedMouseLocation().getWorldPoint();

		IThingKey<Point> endpointKey = getEndpointKey(data);
		if (endpointKey != null) {

			// one of the endpoints is being moved
			StickyMode stickToThingMode = reshapingThing.get(stickLogic.getStickyModeKey(endpointKey));
			Object stickToThingID = reshapingThing.get(stickLogic.getStickyThingKey(endpointKey));

			// if pulled far away from a stuck point, unstick it
			if (BNAUtils.getDistance(reshapingThing.getPoint(data), ap) >= UNSTICK_DIST) {
				stickToThingID = null;
			}

			// if moved close to a sticky thing, stick to it
			for (IIsSticky stickyThing : Iterables.filter(Lists.reverse(getBNAModel().getAllThings()), IIsSticky.class)) {
				if (stickyThing instanceof ReshapeHandleGlassThing || stickyThing instanceof ReshapeHandleThing)
					continue;
				for (IReshapeSplineGuide guide : reshapeSplineGuides) {
					StickyMode stickyMode = guide.getStickyMode(reshapingThing, stickyThing, data);
					if (stickyMode != null) {
						Point stuckPoint = stickyThing.getStickyPointNear(stickyMode, p);
						IBNAView view = evt.getView();
						ICoordinateMapper cm = view.getCoordinateMapper();
						IThingPeer<?> peer = view.getThingPeer(stickyThing);
						if (stuckPoint != null
								&& (BNAUtils.getDistance(stuckPoint, p) <= STICK_DIST || peer.isInThing(view, cm,
										DefaultCoordinate.forWorld(p, cm)))) {
							stickToThingID = stickyThing.getID();
							stickToThingMode = stickyMode;
						}
					}
				}
			}

			// update the stuck thing
			reshapingThing.set(stickLogic.getStickyModeKey(endpointKey), stickToThingMode);
			reshapingThing.set(stickLogic.getStickyThingKey(endpointKey), stickToThingID);

		}

		reshapingThing.setPoint(data, ap);
	}

	@Override
	protected void handleMoveFinished(ReshapeHandleGlassThing handle, Integer data, DragMoveEvent evt) {

		// merge points (i.e., remove one), if one has been moved close to its neighbor
		List<Point> points = reshapingThing.getPoints();
		if (points.size() > 2) {
			// only remove a midpoint
			if (data == 0) {
				// we're looking at endpoint 1, select the next point
				data++;
			}
			if (data == -1 || data == reshapingThing.getPointsSize() - 1) {
				// we're looking at endpoint 2, select the previous point
				data = reshapingThing.getPointsSize() - 1;
				data--;
			}

			// determine if the point is close to its neighbor
			boolean remove = false;
			Point dp = points.get(data);
			remove |= BNAUtils.getDistance(dp, points.get(data - 1)) <= MERGE_DIST;
			remove |= BNAUtils.getDistance(dp, points.get(data + 1)) <= MERGE_DIST;

			// if so, remove the point
			if (remove) {
				points.remove((int) data);
				reshapingThing.setPoints(points);
			}
		}

		super.handleMoveFinished(handle, data, evt);
	}

	protected void updateColor(ReshapeHandleGlassThing handle, Integer data) {
		boolean shouldBeStuck = false;
		for (IReshapeSplineGuide guide : reshapeSplineGuides) {
			shouldBeStuck |= guide.shouldBeStuck(reshapingThing, data);
		}
		boolean isStuck = false;
		IThingKey<Point> endpointKey = getEndpointKey(data);
		if (endpointKey != null) {
			isStuck |= reshapingThing.get(stickLogic.getStickyThingKey(endpointKey)) != null;
		}
		RGB color = shouldBeStuck ? isStuck ? STUCK_COLOR : UNSTUCK_COLOR : NORMAL_COLOR;
		((IHasMutableColor) Assemblies.BACKGROUND_KEY.get(handle, getBNAModel())).setColor(color);
	}

	protected IThingKey<Point> getEndpointKey(int point) {
		if (point == 0) {
			return IHasEndpoints.ENDPOINT_1_KEY;
		}
		if (point == -1 || point == reshapingThing.getPointsSize() - 1) {
			return IHasEndpoints.ENDPOINT_2_KEY;
		}
		return null;
	}

	@Override
	protected Runnable takeSnapshot() {
		final Object tID = this.reshapingThing.getID();
		final List<Point> points = reshapingThing.getPoints();
		final StickyMode stickToThingMode1 = reshapingThing.get(stickLogic
				.getStickyModeKey(IHasEndpoints.ENDPOINT_1_KEY));
		final Object stickToThingID1 = reshapingThing.get(stickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_1_KEY));
		final StickyMode stickToThingMode2 = reshapingThing.get(stickLogic
				.getStickyModeKey(IHasEndpoints.ENDPOINT_2_KEY));
		final Object stickToThingID2 = reshapingThing.get(stickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_2_KEY));
		return new Runnable() {
			public void run() {
				IHasMutablePoints t = SystemUtils.castOrNull(getBNAModel().getThing(tID), IHasMutablePoints.class);
				{
					t.setPoints(points);
					t.set(stickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_1_KEY), stickToThingMode1);
					t.set(stickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_1_KEY), stickToThingID1);
					t.set(stickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_2_KEY), stickToThingMode2);
					t.set(stickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_2_KEY), stickToThingID2);
				}
			}
		};
	}
}
