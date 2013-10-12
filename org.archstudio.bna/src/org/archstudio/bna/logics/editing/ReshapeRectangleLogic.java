package org.archstudio.bna.logics.editing;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.facets.IHasStandardCursor;
import org.archstudio.bna.logics.events.DragMoveEvent;
import org.archstudio.bna.things.shapes.ReshapeHandleThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class ReshapeRectangleLogic extends AbstractReshapeLogic<IHasMutableBoundingBox, Orientation> {

	public ReshapeRectangleLogic(IBNAWorld world) {
		super(world, IHasMutableBoundingBox.class);
		logics.addThingLogic(StandardCursorLogic.class);
	}

	@Override
	protected void addHandles(IHasMutableBoundingBox reshapingThing) {
		for (Orientation o : Orientation.values()) {
			if (o != Orientation.NONE) {
				addHandle(reshapingThing, Assemblies.createHandle(world, null, null), o);
			}
		}
	}

	@Override
	protected void updateHandle(IHasMutableBoundingBox reshapingThing, ReshapeHandleThing handle, Orientation data) {
		Rectangle boundingBox = reshapingThing.getBoundingBox();
		int cursor = SWT.CURSOR_SIZEALL;
		int x1 = boundingBox.x;
		int y1 = boundingBox.y;
		int x2 = boundingBox.x + boundingBox.width;
		int y2 = boundingBox.y + boundingBox.height;
		Point location = new Point((x1 + x2) / 2, (y1 + y2) / 2);
		switch (data) {
		case NORTHEAST:
			location = new Point(x2, y1);
			cursor = SWT.CURSOR_SIZENE;
			break;
		case NORTH:
			location = new Point((x1 + x2) / 2, y1);
			cursor = SWT.CURSOR_SIZEN;
			break;
		case NORTHWEST:
			location = new Point(x1, y1);
			cursor = SWT.CURSOR_SIZENW;
			break;
		case EAST:
			location = new Point(x2, (y1 + y2) / 2);
			cursor = SWT.CURSOR_SIZEE;
			break;
		case WEST:
			location = new Point(x1, (y1 + y2) / 2);
			cursor = SWT.CURSOR_SIZEW;
			break;
		case SOUTHEAST:
			location = new Point(x2, y2);
			cursor = SWT.CURSOR_SIZESE;
			break;
		case SOUTH:
			location = new Point((x1 + x2) / 2, y2);
			cursor = SWT.CURSOR_SIZES;
			break;
		case SOUTHWEST:
			location = new Point(x1, y2);
			cursor = SWT.CURSOR_SIZESW;
			break;
		case NONE:
			location = new Point((x1 + x2) / 2, (y1 + y2) / 2);
			cursor = SWT.CURSOR_SIZESW;
			break;
		}
		handle.setAnchorPoint(location);
		handle.set(IHasStandardCursor.STANDARD_CURSOR_KEY, cursor);
	}

	@Override
	protected void handleMoved(IHasMutableBoundingBox reshapingThing, ReshapeHandleThing handle, Orientation data,
			DragMoveEvent evt) {
		Rectangle bb = reshapingThing.getBoundingBox();

		int nx1 = bb.x;
		int ny1 = bb.y;
		int nx2 = bb.x + bb.width;
		int ny2 = bb.y + bb.height;

		Point ap = evt.getAdjustedMouseLocation().getWorldPoint();

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
		case NONE:
			// do nothing
			break;
		}

		bb.x = Math.min(nx1, nx2);
		bb.y = Math.min(ny1, ny2);
		bb.width = Math.max(0, Math.max(nx1, nx2) - bb.x);
		bb.height = Math.max(0, Math.max(ny1, ny2) - bb.y);

		reshapingThing.setBoundingBox(bb);
	}

	@Override
	protected Runnable takeSnapshot(IHasMutableBoundingBox reshapingThing) {
		final Object reshapingThingID = reshapingThing.getID();
		final Rectangle bounds = reshapingThing.getBoundingBox();
		return new Runnable() {
			@Override
			public void run() {
				IThing t = model.getThing(reshapingThingID);
				if (t != null) {
					t.set(IHasBoundingBox.BOUNDING_BOX_KEY, bounds);
				}
			}
		};
	}
}
