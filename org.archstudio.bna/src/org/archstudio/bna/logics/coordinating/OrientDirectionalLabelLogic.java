package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasOrientation;
import org.archstudio.bna.things.labels.DirectionalLabelThing;
import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class OrientDirectionalLabelLogic extends AbstractMirrorValueLogic<IHasBoundingBox, DirectionalLabelThing> {

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

	public OrientDirectionalLabelLogic() {
		super(IHasBoundingBox.class, DirectionalLabelThing.class);
	}

	public void unorient(DirectionalLabelThing directionalThing) {
		checkNotNull(directionalThing);

		unmirrorValue(directionalThing, IHasOrientation.ORIENTATION_KEY);
	}

	public void orient(IHasBoundingBox toBoundingBoxThing, DirectionalLabelThing directionalThing) {
		checkNotNull(toBoundingBoxThing);
		checkNotNull(directionalThing);

		mirrorValue(toBoundingBoxThing, IHasBoundingBox.BOUNDING_BOX_KEY, directionalThing,
				IHasOrientation.ORIENTATION_KEY, null);
		addSettingKey(IHasOrientation.ORIENTATION_KEY, IHasBoundingBox.BOUNDING_BOX_KEY);
	}

	@Override
	protected void doSynchronizedPropagation(IBNAModel model, IHasBoundingBox fromThing,
			ThingEvent<IHasBoundingBox, ?, ?> fromThingEvent, DirectionalLabelThing toThing,
			ThingEvent<DirectionalLabelThing, ?, ?> toThingEvent, IThingKey<?> toKey) {

		if (fromThingEvent != null && !fromThingEvent.getPropertyName().equals(IHasBoundingBox.BOUNDING_BOX_KEY)) {
			return;
		}

		Rectangle r = fromThing.getBoundingBox();
		Point p = toThing.getReferencePoint();
		if (r != null && p != null) {
			Orientation orientation = getOnEdgeOrientation(r.x, r.x + r.width, r.y, r.y + r.height, p.x, p.y);
			toThing.setOrientation(orientation);
		}
	}
}
