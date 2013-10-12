package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.archstudio.sysutils.SystemUtils.castOrNull;

import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.logics.AbstractCoordinatingThingLogic;
import org.archstudio.bna.things.labels.DirectionalLabelThing;
import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class OrientDirectionalLabelLogic extends AbstractCoordinatingThingLogic implements IBNAModelListener {

	private static Orientation getOnEdgeOrientation(int x1, int x2, int y1, int y2, int x, int y) {
		if (y == y1) {
			return getOnLineSegmentOrientation(x1, x, x2, Orientation.NORTHWEST, Orientation.NORTH,
					Orientation.NORTHEAST);
		}
		else if (y == y2) {
			return getOnLineSegmentOrientation(x1, x, x2, Orientation.SOUTHWEST, Orientation.SOUTH,
					Orientation.SOUTHEAST);
		}
		else if (x == x1) {
			return getOnLineSegmentOrientation(y1, y, y2, Orientation.NORTHWEST, Orientation.WEST,
					Orientation.SOUTHWEST);
		}
		else if (x == x2) {
			return getOnLineSegmentOrientation(y1, y, y2, Orientation.NORTHEAST, Orientation.EAST,
					Orientation.SOUTHEAST);
		}
		return Orientation.NONE;
	}

	private static Orientation getOnLineSegmentOrientation(int p1, int p, int p2, Orientation o1, Orientation o,
			Orientation o2) {
		if (p == p1) {
			return o1;
		}
		if (p == p2) {
			return o2;
		}
		if (p >= p1 && p <= p2) {
			return o;
		}
		return Orientation.NONE;
	}

	private class OrientationUpdater extends Updater {

		final Object rectangleThingID;
		final Object labelThingID;

		public OrientationUpdater(IHasBoundingBox rectangleThing, DirectionalLabelThing labelThing) {
			this.rectangleThingID = rectangleThing.getID();
			this.labelThingID = labelThing.getID();
		}

		@Override
		public void update() {
			IHasBoundingBox rectangleThing = castOrNull(model.getThing(rectangleThingID), IHasBoundingBox.class);
			if (rectangleThing != null) {
				DirectionalLabelThing labelThing = castOrNull(model.getThing(labelThingID), DirectionalLabelThing.class);
				if (labelThing != null) {
					Rectangle r = rectangleThing.getBoundingBox();
					Point p = labelThing.getReferencePoint();
					Orientation orientation = getOnEdgeOrientation(r.x, r.x + r.width, r.y, r.y + r.height, p.x, p.y);
					labelThing.setOrientation(orientation);
				}
			}
		}
	}

	public OrientDirectionalLabelLogic(IBNAWorld world) {
		super(world);
	}

	synchronized public void orient(IHasBoundingBox rectangleThing, DirectionalLabelThing labelThing) {
		checkNotNull(rectangleThing);
		checkNotNull(labelThing);

		OrientationUpdater updater = new OrientationUpdater(rectangleThing, labelThing);
		register(updater, labelThing);

		track(updater, rectangleThing, IHasBoundingBox.BOUNDING_BOX_KEY);
		track(updater, labelThing, IHasBoundingBox.BOUNDING_BOX_KEY);
	}

	synchronized public void unorient(IThing labelThing) {
		checkNotNull(labelThing);

		unregister(labelThing);
	}

}
