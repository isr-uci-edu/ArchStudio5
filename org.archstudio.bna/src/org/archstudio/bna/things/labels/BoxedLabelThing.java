package org.archstudio.bna.things.labels;

import org.archstudio.bna.*;
import org.archstudio.bna.constants.IFontConstants;
import org.archstudio.bna.facets.*;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.VerticalAlignment;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class BoxedLabelThing extends AbstractThing implements IHasMutableText, IHasMutableBoundingBox, IHasMutableColor, IMutableMirrorsBoundingBox,
        IHasMutableHorizontalAlignment, IHasMutableVerticalAlignment, IHasMutableFontData {

	public static final String DONT_INCREASE_FONT_SIZE_PROPERTY_NAME = "dontIncreaseFontSize";

	public BoxedLabelThing() {
		this(BNAUtils.generateUID(BoxedLabelThing.class.getName()));
	}

	public BoxedLabelThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		Rectangle r = new Rectangle(0, 0, 50, 50);
		setBoundingBox(r);
		setText("");
		setFontName(IFontConstants.DEFAULT_FONT_NAME);
		setFontSize(10);
		setFontStyle(FontStyle.NORMAL);
		setHorizontalAlignment(HorizontalAlignment.CENTER);
		setVerticalAlignment(VerticalAlignment.MIDDLE);
		setDontIncreaseFontSize(true);
	}

	public Rectangle getBoundingBox() {
		Rectangle bb = (Rectangle) getProperty(BOUNDING_BOX_PROPERTY_NAME);
		return new Rectangle(bb.x, bb.y, bb.width, bb.height);
	}

	public void setBoundingBox(Rectangle r) {
		Rectangle nr = BNAUtils.normalizeRectangle(r);
		setProperty(BOUNDING_BOX_PROPERTY_NAME, nr);
	}

	public void setBoundingBox(int x, int y, int width, int height) {
		Rectangle r = new Rectangle(x, y, width, height);
		setBoundingBox(r);
	}

	public void setColor(RGB c) {
		setProperty(COLOR_PROPERTY_NAME, c);
	}

	public RGB getColor() {
		return getProperty(COLOR_PROPERTY_NAME);
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

	public String getText() {
		return (String) getProperty(TEXT_PROPERTY_NAME);
	}

	public void setText(String text) {
		setProperty(TEXT_PROPERTY_NAME, text);
	}

	public HorizontalAlignment getHorizontalAlignment() {
		return (HorizontalAlignment) getProperty(HORIZONTAL_ALIGNMENT_PROPERTY_NAME);
	}

	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		setProperty(HORIZONTAL_ALIGNMENT_PROPERTY_NAME, horizontalAlignment);
	}

	public VerticalAlignment getVerticalAlignment() {
		return (VerticalAlignment) getProperty(VERTICAL_ALIGNMENT_PROPERTY_NAME);
	}

	public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
		setProperty(VERTICAL_ALIGNMENT_PROPERTY_NAME, verticalAlignment);
	}

	public String getFontName() {
		return (String) getProperty(FONT_NAME_PROPERTY_NAME);
	}

	public void setFontName(String fontName) {
		setProperty(FONT_NAME_PROPERTY_NAME, fontName);
	}

	public int getFontSize() {
		return getProperty(FONT_SIZE_PROPERTY_NAME);
	}

	public void setFontSize(int fontSize) {
		setProperty(FONT_SIZE_PROPERTY_NAME, fontSize);
	}

	public FontStyle getFontStyle() {
		return (FontStyle) getProperty(FONT_STYLE_PROPERTY_NAME);
	}

	public void setFontStyle(FontStyle fontStyle) {
		setProperty(FONT_STYLE_PROPERTY_NAME, fontStyle);
	}

	public void setDontIncreaseFontSize(boolean dontIncrease) {
		setProperty(DONT_INCREASE_FONT_SIZE_PROPERTY_NAME, dontIncrease);
	}

	public boolean getDontIncreaseFontSize() {
		return getProperty(DONT_INCREASE_FONT_SIZE_PROPERTY_NAME);
	}
}
