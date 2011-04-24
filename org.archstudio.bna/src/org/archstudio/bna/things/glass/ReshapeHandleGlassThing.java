package org.archstudio.bna.things.glass;

import org.archstudio.bna.*;
import org.archstudio.bna.facets.*;
import org.archstudio.swtutils.constants.Orientation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;

public class ReshapeHandleGlassThing extends AbstractThing implements IHasMutableAnchorPoint, IHasMutableOrientation, IMutableMirrorsAnchorPoint,
        IHasMutableTargetThing, IDragMovable, IHasStandardCursor {

	public ReshapeHandleGlassThing() {
		this(BNAUtils.generateUID(ReshapeHandleGlassThing.class.getName()));
	}

	public ReshapeHandleGlassThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		setAnchorPoint(new Point(0, 0));
		setAnchorPointMirrorOffsets(new Point(0, 0));
		setOrientation(Orientation.NONE);
	}

	public void moveRelative(int dx, int dy) {
		Point p = getAnchorPoint();
		p.x += dx;
		p.y += dy;
		setAnchorPoint(p);
	}

	public Point getAnchorPoint() {
		Point p = getProperty(ANCHOR_POINT_PROPERTY_NAME);
		return new Point(p.x, p.y);
	}

	public void setAnchorPoint(Point p) {
		setProperty(ANCHOR_POINT_PROPERTY_NAME, p);
	}

	public Point getReferencePoint() {
		return getAnchorPoint();
	}

	public void setReferencePoint(Point p) {
		setAnchorPoint(p);
	}

	public String getAnchorPointMasterThingID() {
		return getProperty(ANCHOR_POINT_MASTER_THING_ID_PROPERTY_NAME);
	}

	public void setAnchorPointMasterThingID(String anchorPointMasterThingID) {
		setProperty(ANCHOR_POINT_MASTER_THING_ID_PROPERTY_NAME, anchorPointMasterThingID);
	}

	public Point getAnchorPointMirrorOffsets() {
		return getProperty(ANCHOR_POINT_MIRROR_OFFSETS_PROPETY_NAME);
	}

	public void setAnchorPointMirrorOffsets(Point newOffsets) {
		setProperty(ANCHOR_POINT_MIRROR_OFFSETS_PROPETY_NAME, newOffsets);
	}

	public Orientation getOrientation() {
		return getProperty(ORIENTATION_PROPERTY_NAME);
	}

	public void setOrientation(Orientation orientation) {
		setProperty(ORIENTATION_PROPERTY_NAME, orientation);
	}

	public String getTargetThingID() {
		return getProperty(TARGET_THING_ID_PROPERTY_NAME);
	}

	public void setTargetThingID(String targetThingID) {
		setProperty(TARGET_THING_ID_PROPERTY_NAME, targetThingID);
	}

	public boolean isUserEditable() {
		return true;
	}

	public int getStandardCursor() {
		switch (getOrientation()) {
		case NORTHWEST:
		case SOUTHEAST:
			return SWT.CURSOR_SIZENWSE;
		case NORTHEAST:
		case SOUTHWEST:
			return SWT.CURSOR_SIZENESW;
		case NORTH:
		case SOUTH:
			return SWT.CURSOR_SIZENS;
		case EAST:
		case WEST:
			return SWT.CURSOR_SIZEWE;
		default:
			return SWT.CURSOR_SIZEALL;
		}
	}

}
