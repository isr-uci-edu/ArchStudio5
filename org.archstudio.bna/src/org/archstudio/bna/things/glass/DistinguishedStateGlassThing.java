package org.archstudio.bna.things.glass;

import org.archstudio.bna.*;
import org.archstudio.bna.facets.*;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class DistinguishedStateGlassThing extends AbstractThing implements IHasBoundingBox, IHasMutableAnchorPoint, IHasMutableRadius, IRelativeMovable,
        IMarqueeSelectable, IHasMutableSelected, IHasMutableUserEditable, IHasMutableRotatingOffset, IHasMutableToolTip, IDragMovable, IHasStickyBox {

	public DistinguishedStateGlassThing() {
		this(BNAUtils.generateUID(DistinguishedStateGlassThing.class.getName()));
	}

	public DistinguishedStateGlassThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		setAnchorPoint(new Point(0, 0));
		setRadius(25);
		setProperty(ROTATING_OFFSET_PROPERTY_NAME, 0);
		setSelected(false);
		setUserEditable(true);
	}

	protected int getMinimumRadius() {
		return 5;
	}

	public Point getAnchorPoint() {
		Point p = getProperty(ANCHOR_POINT_PROPERTY_NAME);
		return new Point(p.x, p.y);
	}

	public void setAnchorPoint(Point p) {
		setProperty(ANCHOR_POINT_PROPERTY_NAME, p);
	}

	public void setRadius(int radius) {
		setProperty(RADIUS_PROPERTY_NAME, radius);
	}

	public int getRadius() {
		return getProperty(RADIUS_PROPERTY_NAME);
	}

	public Point getReferencePoint() {
		return getAnchorPoint();
	}

	public void setReferencePoint(Point p) {
		setAnchorPoint(p);
	}

	public Rectangle getBoundingBox() {
		Point p = getAnchorPoint();
		int r = getRadius();
		return new Rectangle(p.x - r, p.y - r, r + r, r + r);
	}

	public void setSelected(boolean selected) {
		setProperty(SELECTED_PROPERTY_NAME, selected);
	}

	public boolean isSelected() {
		return getProperty(SELECTED_PROPERTY_NAME);
	}

	public boolean isUserEditable() {
		return getProperty(USER_EDITABLE_PROPERTY_NAME);
	}

	public void setUserEditable(boolean userEditable) {
		setProperty(USER_EDITABLE_PROPERTY_NAME, userEditable);
	}

	public int getRotatingOffset() {
		return getProperty(ROTATING_OFFSET_PROPERTY_NAME);
	}

	public void incrementRotatingOffset() {
		if (isSelected()) {
			setProperty(ROTATING_OFFSET_PROPERTY_NAME, (getRotatingOffset() + 1) % 6);
		}
	}

	public String getToolTip() {
		return getProperty(TOOL_TIP_PROPERTY_NAME);
	}

	public void setToolTip(String toolTip) {
		setProperty(TOOL_TIP_PROPERTY_NAME, toolTip);
	}

	public Rectangle getStickyBox() {
		Point p = getAnchorPoint();
		return new Rectangle(p.x, p.y, 0, 0);
	}

}
