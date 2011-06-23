package org.archstudio.bna.logics.editing;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.facets.IHasStandardCursor;
import org.archstudio.bna.logics.events.DragMoveEvent;
import org.archstudio.bna.things.glass.ReshapeHandleGlassThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

public class ReshapeRectangleLogic extends AbstractReshapeLogic<IHasMutableBoundingBox, Orientation> {

	public ReshapeRectangleLogic() {
		super(IHasMutableBoundingBox.class);
	}

	@Override
	protected void doPropagation(IBNAModel model, IHasMutableBoundingBox fromThing, IThingKey<?> fromKey,
			ThingEvent<IHasMutableBoundingBox, ?, ?> fromThingEvent, Orientation data, ReshapeHandleGlassThing toThing,
			IThingKey<?> toKey, ThingEvent<ReshapeHandleGlassThing, ?, ?> toThingEvent) {

		Rectangle boundingBox = fromThing.getBoundingBox();
		Point location = boundingBox.getCenter();
		switch (data) {
		case NORTHEAST:
			location = boundingBox.getTopRight();
			break;
		case NORTH:
			location = boundingBox.getTop();
			break;
		case NORTHWEST:
			location = boundingBox.getTopLeft();
			break;
		case EAST:
			location = boundingBox.getRight();
			break;
		case WEST:
			location = boundingBox.getLeft();
			break;
		case SOUTHEAST:
			location = boundingBox.getBottomRight();
			break;
		case SOUTH:
			location = boundingBox.getBottom();
			break;
		case SOUTHWEST:
			location = boundingBox.getBottomLeft();
			break;
		}
		toThing.setAnchorPoint(location);
	}

	@Override
	protected void addHandles() {
		for (Orientation o : Orientation.values()) {
			if (o != Orientation.NONE) {
				ReshapeHandleGlassThing handle = addHandle(
						Assemblies.createHandle(getBNAWorld(), null, reshapingThing), o);

				int cursor = SWT.CURSOR_SIZEALL;
				switch (o) {
				case NORTHEAST:
					cursor = SWT.CURSOR_SIZENE;
					break;
				case NORTH:
					cursor = SWT.CURSOR_SIZEN;
					break;
				case NORTHWEST:
					cursor = SWT.CURSOR_SIZENW;
					break;
				case EAST:
					cursor = SWT.CURSOR_SIZEE;
					break;
				case WEST:
					cursor = SWT.CURSOR_SIZEW;
					break;
				case SOUTHEAST:
					cursor = SWT.CURSOR_SIZESE;
					break;
				case SOUTH:
					cursor = SWT.CURSOR_SIZES;
					break;
				case SOUTHWEST:
					cursor = SWT.CURSOR_SIZESW;
					break;
				}
				handle.set(IHasStandardCursor.STANDARD_CURSOR_KEY, cursor);

				setPropagate(reshapingThing, IHasBoundingBox.BOUNDING_BOX_KEY, IHasAnchorPoint.ANCHOR_POINT_KEY, o,
						handle);
			}
		}
	}

	@Override
	protected void movedHandle(ReshapeHandleGlassThing handle, Orientation data, DragMoveEvent evt) {
		Rectangle bb = reshapingThing.getBoundingBox();

		int nx1 = bb.x;
		int ny1 = bb.y;
		int nx2 = bb.x + bb.width;
		int ny2 = bb.y + bb.height;

		Point ap = evt.getAdjustedMouseLocation().getWorldPoint(new Point());

		switch (data) {
		case NORTHWEST:
			nx1 = ap.x;
			ny1 = ap.y;
			break;
		case NORTH:
			ny1 = ap.y;
			break;
		case NORTHEAST:
			nx2 = ap.x;
			ny1 = ap.y;
			break;
		case EAST:
			nx2 = ap.x;
			break;
		case SOUTHEAST:
			nx2 = ap.x;
			ny2 = ap.y;
			break;
		case SOUTH:
			ny2 = ap.y;
			break;
		case SOUTHWEST:
			nx1 = ap.x;
			ny2 = ap.y;
			break;
		case WEST:
			nx1 = ap.x;
			break;
		}

		bb.x = Math.min(nx1, nx2);
		bb.y = Math.min(ny1, ny2);
		bb.width = Math.max(0, Math.max(nx1, nx2) - bb.x);
		bb.height = Math.max(0, Math.max(ny1, ny2) - bb.y);

		reshapingThing.setBoundingBox(bb);
	}
}
