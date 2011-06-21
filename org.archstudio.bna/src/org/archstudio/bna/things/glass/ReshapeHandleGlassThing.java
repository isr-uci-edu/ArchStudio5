package org.archstudio.bna.things.glass;

import org.archstudio.bna.facets.IHasMutableOrientation;
import org.archstudio.bna.facets.IHasStandardCursor;
import org.archstudio.bna.things.AbstractBoundedAnchorPointThing;
import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.swt.SWT;

public class ReshapeHandleGlassThing extends AbstractBoundedAnchorPointThing implements IHasMutableOrientation,
		IHasStandardCursor {

	public ReshapeHandleGlassThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setOrientation(Orientation.NONE);
	}

	@Override
	public Orientation getOrientation() {
		return get(ORIENTATION_KEY);
	}

	@Override
	public void setOrientation(Orientation orientation) {
		set(ORIENTATION_KEY, orientation);
	}

	@Override
	public int getStandardCursor() {
		switch (getOrientation()) {
		case NORTHWEST:
			return SWT.CURSOR_SIZENW;
		case SOUTHEAST:
			return SWT.CURSOR_SIZESE;
		case NORTHEAST:
			return SWT.CURSOR_SIZENE;
		case SOUTHWEST:
			return SWT.CURSOR_SIZESW;
		case NORTH:
			return SWT.CURSOR_SIZEN;
		case SOUTH:
			return SWT.CURSOR_SIZES;
		case EAST:
			return SWT.CURSOR_SIZEE;
		case WEST:
			return SWT.CURSOR_SIZEW;
		default:
			return SWT.CURSOR_SIZEALL;
		}
	}
}
