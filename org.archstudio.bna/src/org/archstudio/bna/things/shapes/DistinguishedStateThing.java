package org.archstudio.bna.things.shapes;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThing;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableRadius;
import org.archstudio.bna.facets.IMutableMirrorsAnchorPoint;

public class DistinguishedStateThing extends AbstractThing implements IHasMutableAnchorPoint, IHasMutableColor, IMutableMirrorsAnchorPoint, IHasMutableRadius,
        IHasBoundingBox {
	public static final String IS_FINAL_PROPERTY_NAME = "isFinal";

	public DistinguishedStateThing() {
		this(BNAUtils.generateUID(DistinguishedStateThing.class.getName()));
	}

	public DistinguishedStateThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		setAnchorPoint(new Point(0, 0));
		setRadius(25);
		setIsFinalState(false);
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

	public void setColor(RGB c) {
		setProperty(COLOR_PROPERTY_NAME, c);
	}

	public RGB getColor() {
		return getProperty(COLOR_PROPERTY_NAME);
	}

	public void setRadius(int radius) {
		setProperty(RADIUS_PROPERTY_NAME, radius);
	}

	public int getRadius() {
		return getProperty(RADIUS_PROPERTY_NAME);
	}

	public Rectangle getBoundingBox() {
		Point p = getAnchorPoint();
		int radius = getRadius();
		return new Rectangle(p.x - radius, p.y - radius, radius + radius, radius + radius);
	}

	public boolean isFinalState() {
		return getProperty(IS_FINAL_PROPERTY_NAME);
	}

	public void setIsFinalState(boolean isFinal) {
		setProperty(IS_FINAL_PROPERTY_NAME, isFinal);
	}

}
