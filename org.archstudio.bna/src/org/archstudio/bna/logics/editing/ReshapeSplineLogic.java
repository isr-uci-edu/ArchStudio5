package org.archstudio.bna.logics.editing;

import static org.archstudio.sysutils.SystemUtils.castOrNull;

import java.util.Arrays;
import java.util.List;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutablePoints;
import org.archstudio.bna.facets.IHasStandardCursor;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.logics.events.DragMoveEvent;
import org.archstudio.bna.things.glass.ReshapeHandleGlassThing;
import org.archstudio.bna.utils.Assemblies;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.SWT;
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

	protected StickPointLogic stickLogic = null;

	public ReshapeSplineLogic() {
		super(IHasMutablePoints.class);
	}

	@Override
	protected void init() {
		super.init();
		stickLogic = addThingLogic(StickPointLogic.class);
	}

	public void addReshapeSplineGuides(IReshapeSplineGuide... guides) {
		reshapeSplineGuides.addAll(Arrays.asList(guides));
	}

	@Override
	protected void addHandles() {
		for (int i = 0; i < reshapingThing.getPointsSize(); i++) {
			addHandle(Assemblies.createHandle(getBNAWorld(), null, null), i);
		}
	}

	@Override
	protected synchronized void updateHandles() {
		if (reshapingThing != null) {
			if (reshapeHandles.size() != reshapingThing.getPointsSize()) {
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
			StickyMode stickyMode = stickLogic.getStickyMode(reshapingThing, endpointKey);
			if (stickyMode != null && stickyMode.isStuckToCenterPoint()) {
				IIsSticky stickyThing = castOrNull(
						getBNAModel().getThing(stickLogic.getThingRef(reshapingThing, endpointKey)), IIsSticky.class);
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

		Point p = evt.getMouseLocation().getWorldPoint(new Point());
		Point ap = evt.getAdjustedMouseLocation().getWorldPoint(new Point());

		IThingKey<Point> endpointKey = getEndpointKey(data);
		if (endpointKey != null) {

			// one of the endpoints is being moved
			StickyMode stickToThingMode = stickLogic.getStickyMode(reshapingThing, endpointKey);
			Object stickToThingID = stickLogic.getThingRef(reshapingThing, endpointKey);

			// if pulled far away from a stuck point, unstick it
			if (reshapingThing.getPoint(data).getDistance(ap) >= UNSTICK_DIST) {
				stickToThingID = null;
			}

			// if moved close to a sticky thing, stick to it
			for (IIsSticky stickyThing : Iterables.filter(Lists.reverse(getBNAModel().getThings()), IIsSticky.class)) {
				for (IReshapeSplineGuide guide : reshapeSplineGuides) {
					StickyMode stickyMode = guide.getStickyMode(reshapingThing, stickyThing, data);
					if (stickyMode != null) {
						Point stuckPoint = stickyThing.getStickyPointNear(stickyMode, p);
						if (stuckPoint != null && stuckPoint.getDistance(p) <= STICK_DIST) {
							stickToThingID = stickyThing.getID();
							stickToThingMode = stickyMode;
						}
					}
				}
			}

			// update the stuck thing
			reshapingThing.set(stickLogic.getStickyModeKey(endpointKey), stickToThingMode);
			reshapingThing.set(stickLogic.getThingRefKey(endpointKey), stickToThingID);

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
			remove |= dp.getDistance(points.get(data - 1)) <= MERGE_DIST;
			remove |= dp.getDistance(points.get(data + 1)) <= MERGE_DIST;

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
			isStuck |= stickLogic.getThingRef(reshapingThing, endpointKey) != null;
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

}
