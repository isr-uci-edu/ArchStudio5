package org.archstudio.bna.things.labels;

import org.archstudio.bna.*;
import org.archstudio.bna.constants.IFontConstants;
import org.archstudio.bna.facets.*;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.VerticalAlignment;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

public class UserNotificationThing extends AbstractThing implements IHasMutableText, IHasMutableAnchorPoint, IHasMutableColor, IHasMutableSecondaryColor,
        IHasMutableLife, IHasMutableHorizontalAlignment, IHasMutableVerticalAlignment, IHasMutableFontData {

	public UserNotificationThing() {
		this(BNAUtils.generateUID(UserNotificationThing.class.getName()));
	}

	public UserNotificationThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		Point p = new Point(50, 50);
		setAnchorPoint(p);
		setText("");
		setFontName(IFontConstants.DEFAULT_FONT_NAME);
		setFontSize(10);
		setFontStyle(FontStyle.NORMAL);
		setHorizontalAlignment(HorizontalAlignment.CENTER);
		setVerticalAlignment(VerticalAlignment.MIDDLE);
		setLife(16);
	}

	public void setAnchorPoint(Point newAnchorPoint) {
		setProperty(ANCHOR_POINT_PROPERTY_NAME, newAnchorPoint);
	}

	public Point getAnchorPoint() {
		Point p = getProperty(ANCHOR_POINT_PROPERTY_NAME);
		return new Point(p.x, p.y);
	}

	public void setColor(RGB c) {
		setProperty(COLOR_PROPERTY_NAME, c);
	}

	public RGB getColor() {
		return getProperty(COLOR_PROPERTY_NAME);
	}

	public void setSecondaryColor(RGB c) {
		setProperty(SECONDARY_COLOR_PROPERTY_NAME, c);
	}

	public RGB getSecondaryColor() {
		return getProperty(SECONDARY_COLOR_PROPERTY_NAME);
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

	public int getLife() {
		return getProperty(LIFE_PROPERTY_NAME);
	}

	public void setLife(int life) {
		setProperty(LIFE_PROPERTY_NAME, life);
	}
}
