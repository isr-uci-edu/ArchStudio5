package org.archstudio.bna.things.glass;

import org.archstudio.bna.facets.IHasMutableOrientation;
import org.archstudio.bna.facets.IHasStandardCursor;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.things.AbstractAnchorPointThing;
import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.swt.SWT;

public class ReshapeHandleGlassThing extends AbstractAnchorPointThing implements IHasMutableOrientation,
		IHasMutableTargetThing, IHasStandardCursor {

	public ReshapeHandleGlassThing() {
		this(null);
	}

	public ReshapeHandleGlassThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setOrientation(Orientation.NONE);
	}

	public Orientation getOrientation() {
		return get(ORIENTATION_KEY);
	}

	public void setOrientation(Orientation orientation) {
		put(ORIENTATION_KEY, orientation);
	}

	public String getTargetThingID() {
		K key = TARGET_THING_ID_KEY;
		return get(key);
	}

	public void setTargetThingID(String targetThingID) {
		IThingKey<? super T> key = TARGET_THING_ID_KEY;
		put(key, targetThingID);
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

}
