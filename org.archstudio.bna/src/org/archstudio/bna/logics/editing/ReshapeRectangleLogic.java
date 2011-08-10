package org.archstudio.bna.logics.editing;

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
	protected void addHandles() {
		for (Orientation o : Orientation.values()) {
			if (o != Orientation.NONE) {
				addHandle(Assemblies.createHandle(getBNAWorld(), null, null), o);
			}
		}
	}

	@Override
	protected void updateHandle(ReshapeHandleGlassThing handle, Orientation data) {
		Rectangle boundingBox = reshapingThing.getBoundingBox();
		Point location = boundingBox.getCenter();
		int cursor = SWT.CURSOR_SIZEALL;
		switch (data) {
		case NORTHEAST:
			location = boundingBox.getTopRight();
			cursor = SWT.CURSOR_SIZENE;
			break;
		case NORTH:
			location = boundingBox.getTop();
			cursor = SWT.CURSOR_SIZEN;
			break;
		case NORTHWEST:
			location = boundingBox.getTopLeft();
			cursor = SWT.CURSOR_SIZENW;
			break;
		case EAST:
			location = boundingBox.getRight();
			cursor = SWT.CURSOR_SIZEE;
			break;
		case WEST:
			location = boundingBox.getLeft();
			cursor = SWT.CURSOR_SIZEW;
			break;
		case SOUTHEAST:
			location = boundingBox.getBottomRight();
			cursor = SWT.CURSOR_SIZESE;
			break;
		case SOUTH:
			location = boundingBox.getBottom();
			cursor = SWT.CURSOR_SIZES;
			break;
		case SOUTHWEST:
			location = boundingBox.getBottomLeft();
			cursor = SWT.CURSOR_SIZESW;
			break;
		}
		handle.setAnchorPoint(location);
		handle.set(IHasStandardCursor.STANDARD_CURSOR_KEY, cursor);
	}

	@Override
	protected void handleMoved(ReshapeHandleGlassThing handle, Orientation data, DragMoveEvent evt) {
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
