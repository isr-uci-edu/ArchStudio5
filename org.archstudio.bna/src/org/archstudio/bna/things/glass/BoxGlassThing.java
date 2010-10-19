package org.archstudio.bna.things.glass;

import org.archstudio.bna.*;
import org.archstudio.bna.facets.*;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class BoxGlassThing extends AbstractThing implements IHasMutableBoundingBox, IRelativeMovable, IMarqueeSelectable, IHasMutableSelected,
        IHasMutableUserEditable, IHasMutableRotatingOffset, IBoxReshapable, IHasMutableToolTip, IDragMovable, IHasStickyBox {
	public static final String STICKY_BOX_MODE_PROPERTY_NAME = "stickyBoxMode";

	public static enum StickyBoxMode {
		NONE, CENTER_POINT, BOUNDING_BOX
	}

	public BoxGlassThing() {
		this(BNAUtils.generateUID(BoxGlassThing.class.getName()));
	}

	public BoxGlassThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		Rectangle r = new Rectangle(0, 0, 50, 50);
		setBoundingBox(r);
		setStickyBoxMode(StickyBoxMode.NONE);
		setProperty(ROTATING_OFFSET_PROPERTY_NAME, 0);
		setSelected(false);
		setUserEditable(true);
	}

	protected int getMinimumHeight() {
		return 5;
	}

	protected int getMinimumWidth() {
		return 5;
	}

	public Point getReferencePoint() {
		Rectangle r = getBoundingBox();
		return new Point(r.x, r.y);
	}

	public void setReferencePoint(Point p) {
		Rectangle r = getBoundingBox();
		r.x = p.x;
		r.y = p.y;
		setBoundingBox(r);
	}

	public Rectangle getBoundingBox() {
		Rectangle bb = (Rectangle) getProperty(BOUNDING_BOX_PROPERTY_NAME);
		return new Rectangle(bb.x, bb.y, bb.width, bb.height);
	}

	public void setBoundingBox(Rectangle r) {
		Rectangle nr = BNAUtils.normalizeRectangle(r);

		/*
		 * if(nr.height < getMinimumHeight()){ nr.height = getMinimumHeight(); }
		 * if(nr.width < getMinimumWidth()){ nr.width = getMinimumWidth(); }
		 */
		setProperty(BOUNDING_BOX_PROPERTY_NAME, nr);
	}

	public void setBoundingBox(int x, int y, int width, int height) {
		Rectangle r = new Rectangle(x, y, width, height);
		setBoundingBox(r);
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
		switch (getStickyBoxMode()) {
		case NONE:
			return null;
		case CENTER_POINT:
			Rectangle boundingBox = getProperty(BOUNDING_BOX_PROPERTY_NAME);
			return new Rectangle(boundingBox.x + (boundingBox.width / 2), boundingBox.y + (boundingBox.height / 2), 0, 0);
		case BOUNDING_BOX:
			return getBoundingBox();
		}
		return null;
	}

	public StickyBoxMode getStickyBoxMode() {
		return getProperty(STICKY_BOX_MODE_PROPERTY_NAME);
	}

	public void setStickyBoxMode(StickyBoxMode mode) {
		setProperty(STICKY_BOX_MODE_PROPERTY_NAME, mode);
	}
}
