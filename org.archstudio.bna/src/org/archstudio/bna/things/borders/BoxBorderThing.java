package org.archstudio.bna.things.borders;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThing;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.facets.*;

public class BoxBorderThing extends AbstractThing implements IHasMutableBoundingBox, IHasMutableColor, IHasMutableLineStyle, IHasMutableLineWidth,
        IMutableMirrorsBoundingBox, IHasMutableRoundedCorners {

	public static final String COUNT_PROPERTY_NAME = "count";

	public BoxBorderThing() {
		this(BNAUtils.generateUID(BoxBorderThing.class.getName()));
	}

	public BoxBorderThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		Rectangle r = new Rectangle(0, 0, 50, 50);
		setBoundingBox(r);
		setLineStyle(LINE_STYLE_SOLID);
		setLineWidth(1);
		setCount(1);
		setCornerHeight(0);
		setCornerWidth(0);
	}

	public Rectangle getBoundingBox() {
		Rectangle r = (Rectangle) getProperty(BOUNDING_BOX_PROPERTY_NAME);
		return new Rectangle(r.x, r.y, r.width, r.height);
	}

	public void setBoundingBox(Rectangle r) {
		setProperty(BOUNDING_BOX_PROPERTY_NAME, r);
	}

	public void setBoundingBox(int x, int y, int width, int height) {
		setBoundingBox(new Rectangle(x, y, width, height));
	}

	public void moveRelative(int dx, int dy) {
		Rectangle r = getBoundingBox();
		r.x += dx;
		r.y += dy;
		setBoundingBox(r);
	}

	public void setColor(RGB c) {
		setProperty(COLOR_PROPERTY_NAME, c);
	}

	public RGB getColor() {
		return getProperty(COLOR_PROPERTY_NAME);
	}

	public int getLineStyle() {
		return getProperty(LINE_STYLE_PROPERTY_NAME);
	}

	public void setLineStyle(int lineStyle) {
		setProperty(LINE_STYLE_PROPERTY_NAME, lineStyle);
	}

	public int getLineWidth() {
		return getProperty(LINE_WIDTH_PROPERTY_NAME);
	}

	public void setLineWidth(int lineWidth) {
		setProperty(LINE_WIDTH_PROPERTY_NAME, lineWidth);
	}

	public void setCount(int count) {
		setProperty(COUNT_PROPERTY_NAME, count);
	}

	public int getCount() {
		return getProperty(COUNT_PROPERTY_NAME);
	}

	public String getBoundingBoxMasterThingID() {
		return (String) getProperty(BOUNDING_BOX_MASTER_THING_ID_PROPERTY_NAME);
	}

	public void setBoundingBoxMasterThingID(String boundingBoxMasterThingID) {
		setProperty(BOUNDING_BOX_MASTER_THING_ID_PROPERTY_NAME, boundingBoxMasterThingID);
	}

	public Rectangle getBoundingBoxMirrorOffsets() {
		return (Rectangle) getProperty(BOUNDING_BOX_MIRROR_OFFSETS_PROPETY_NAME);
	}

	public void setBoundingBoxMirrorOffsets(Rectangle newOffsets) {
		setProperty(BOUNDING_BOX_MIRROR_OFFSETS_PROPETY_NAME, newOffsets);
	}

	public int getCornerHeight() {
		return getProperty(CORNER_HEIGHT_PROPERTY_NAME);
	}

	public void setCornerHeight(int cornerHeight) {
		setProperty(CORNER_HEIGHT_PROPERTY_NAME, cornerHeight);
	}

	public int getCornerWidth() {
		return getProperty(CORNER_WIDTH_PROPERTY_NAME);
	}

	public void setCornerWidth(int cornerWidth) {
		setProperty(CORNER_WIDTH_PROPERTY_NAME, cornerWidth);
	}
}
