package org.archstudio.bna.things.labels;

import org.archstudio.bna.constants.IFontConstants;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasMutableAngle;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableFontData;
import org.archstudio.bna.facets.IHasMutableIndicatorPoint;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.things.AbstractAnchorPointThing;
import org.archstudio.swtutils.constants.FontStyle;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.RGB;

public class TagThing extends AbstractAnchorPointThing implements IHasBoundingBox, IHasMutableColor,
		IHasMutableAnchorPoint, IHasMutableText, IHasMutableAngle, IHasMutableIndicatorPoint, IHasMutableFontData,
		IRelativeMovable {

	public TagThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setText("");
		setAngle(0);
		setFontName(IFontConstants.DEFAULT_FONT_NAME);
		setFontSize(10);
		setFontStyle(FontStyle.NORMAL);
		setDontIncreaseFontSize(true);
	}

	public Rectangle getBoundingBox() {
		// TODO: Note why this is so
		Rectangle r = get(BOUNDING_BOX_KEY);
		if (r != null) {
			return new Rectangle(r.x, r.y, r.width, r.height);
		}

		Point p = get(ANCHOR_POINT_KEY);
		return new Rectangle(p.x, p.y, 1, 1);
	}

	public RGB getColor() {
		return get(COLOR_KEY);
	}

	public void setColor(RGB newColor) {
		put(COLOR_KEY, newColor);
	}

	public String getText() {
		return get(TEXT_KEY);
	}

	public void setText(String text) {
		put(TEXT_KEY, text);
	}

	public int getAngle() {
		return get(ANGLE_KEY);
	}

	public void setAngle(int angle) {
		put(ANGLE_KEY, angle);
	}

	public Point getIndicatorPoint() {
		return get(INDICATOR_POINT_KEY);
	}

	public void setIndicatorPoint(Point p) {
		put(INDICATOR_POINT_KEY, p);
	}

	public String getFontName() {
		return get(FONT_NAME_KEY);
	}

	public void setFontName(String fontName) {
		put(FONT_NAME_KEY, fontName);
	}

	public int getFontSize() {
		return get(FONT_SIZE_KEY);
	}

	public void setFontSize(int fontSize) {
		put(FONT_SIZE_KEY, fontSize);
	}

	public FontStyle getFontStyle() {
		return get(FONT_STYLE_KEY);
	}

	public void setFontStyle(FontStyle fontStyle) {
		put(FONT_STYLE_KEY, fontStyle);
	}

	public void setDontIncreaseFontSize(boolean dontIncrease) {
		put(DONT_INCREASE_FONT_SIZE_KEY, dontIncrease);
	}

	public boolean getDontIncreaseFontSize() {
		return get(DONT_INCREASE_FONT_SIZE_KEY);
	}
}
