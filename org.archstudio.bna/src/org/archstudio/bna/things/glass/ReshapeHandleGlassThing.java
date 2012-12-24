package org.archstudio.bna.things.glass;

import java.awt.Dimension;

import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.facets.IHasMutableOrientation;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.facets.IHasStandardCursor;
import org.archstudio.bna.things.AbstractAnchorPointThing;
import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

public class ReshapeHandleGlassThing extends AbstractAnchorPointThing implements IHasMutableOrientation,
		IHasStandardCursor, IHasMutableSize {

	public ReshapeHandleGlassThing(Object id) {
		super(id);
	}

	protected void initProperties() {
		super.initProperties();
		setOrientation(Orientation.NONE);
		setSize(new Dimension(8, 8));
		set(IHasEdgeColor.EDGE_COLOR_KEY, new RGB(0, 0, 0));
	}

	public Orientation getOrientation() {
		return get(ORIENTATION_KEY);
	}

	public void setOrientation(Orientation orientation) {
		set(ORIENTATION_KEY, orientation);
	}

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

	public Dimension getSize() {
		return get(SIZE_KEY);
	}

	public void setSize(Dimension size) {
		set(SIZE_KEY, size);
	}
}
