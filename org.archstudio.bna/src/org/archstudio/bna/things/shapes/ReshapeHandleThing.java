package org.archstudio.bna.things.shapes;

import org.archstudio.bna.*;
import org.archstudio.bna.facets.*;
import org.archstudio.swtutils.constants.Orientation;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Point;

public class ReshapeHandleThing extends AbstractThing implements IHasMutableColor, IHasMutableAnchorPoint, IHasMutableOrientation, IMutableMirrorsAnchorPoint {

	protected static final String HANDLE_BOUNDING_BOX_PROPERTY_NAME = "#boundingBox";

	public ReshapeHandleThing() {
		this(BNAUtils.generateUID(ReshapeHandleThing.class.getName()));
	}

	public ReshapeHandleThing(String id) {
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

	public void setColor(RGB c) {
		setProperty(COLOR_PROPERTY_NAME, c);
	}

	public RGB getColor() {
		return getProperty(COLOR_PROPERTY_NAME);
	}

	public Point getAnchorPoint() {
		Point p = getProperty(ANCHOR_POINT_PROPERTY_NAME);
		return new Point(p.x, p.y);
	}

	public void setAnchorPoint(Point p) {
		setProperty(ANCHOR_POINT_PROPERTY_NAME, p);
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

}
