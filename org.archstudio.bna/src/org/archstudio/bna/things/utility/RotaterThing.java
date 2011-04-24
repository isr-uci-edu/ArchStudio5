package org.archstudio.bna.things.utility;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThing;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasMutableAngle;
import org.archstudio.bna.facets.IMovesWith;
import org.archstudio.bna.facets.IMutableMovesWith;

public class RotaterThing extends AbstractThing implements IHasMutableAnchorPoint, IHasBoundingBox, IHasMutableAngle, IMutableMovesWith {

	public static final String RADIUS_PROPERTY_NAME = "radius";
	public static final String ROTATED_THING_IDS_PROPERTY_NAME = "rotatedThingIDs";
	public static final String ADJUSTMENT_INCREMENT_PROPERTY_NAME = "adjustmentIncrement";

	public RotaterThing() {
		this(BNAUtils.generateUID(RotaterThing.class.getName()));
	}

	public RotaterThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		setAnchorPoint(new Point(0, 0));
		setAdjustmentIncrement(15);
		setMovesWithMode(IMovesWith.TRACK_ANCHOR_POINT_FIRST);
		setRadius(50);
		setAngle(0);
	}

	public void setAnchorPoint(Point p) {
		setProperty(ANCHOR_POINT_PROPERTY_NAME, p);
	}

	public Point getAnchorPoint() {
		return getProperty(ANCHOR_POINT_PROPERTY_NAME);
	}

	public int getRadius() {
		return getProperty(RADIUS_PROPERTY_NAME);
	}

	public void setRadius(int radius) {
		setProperty(RADIUS_PROPERTY_NAME, radius);
	}

	public Rectangle getBoundingBox() {
		Point anchorPoint = getAnchorPoint();
		int radius = getRadius();
		return new Rectangle(anchorPoint.x - radius, anchorPoint.y - radius, radius * 2, radius * 2);
	}

	public void setRotatedThingIDs(String[] rotatedThingIDs) {
		setProperty(ROTATED_THING_IDS_PROPERTY_NAME, rotatedThingIDs);
	}

	public String[] getRotatedThingIDs() {
		return getProperty(ROTATED_THING_IDS_PROPERTY_NAME);
	}

	public void setAngle(int degrees) {
		setProperty(ANGLE_PROPERTY_NAME, degrees);
	}

	public int getAngle() {
		return getProperty(ANGLE_PROPERTY_NAME);
	}

	public void setAdjustmentIncrement(int increment) {
		setProperty(ADJUSTMENT_INCREMENT_PROPERTY_NAME, increment);
	}

	public int getAdjustmentIncrement() {
		return getProperty(ADJUSTMENT_INCREMENT_PROPERTY_NAME);
	}

	public int getMovesWithMode() {
		return getProperty(MOVES_WITH_MODE_PROPERTY_NAME);
	}

	public void setMovesWithMode(int mode) {
		setProperty(MOVES_WITH_MODE_PROPERTY_NAME, mode);
	}

	public String getMovesWithThingID() {
		return getProperty(MOVES_WITH_THING_ID_PROPERTY_NAME);
	}

	public void setMovesWithThingID(String movesWithThingID) {
		setProperty(MOVES_WITH_THING_ID_PROPERTY_NAME, movesWithThingID);
	}

	public Point getReferencePoint() {
		return getAnchorPoint();
	}

	public void setReferencePoint(Point p) {
		setAnchorPoint(p);
	}

}
