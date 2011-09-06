package org.archstudio.bna.things.labels;

import org.archstudio.bna.constants.IFontConstants;
import org.archstudio.bna.facets.IHasIndicatorPoint;
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
import org.eclipse.swt.graphics.RGB;

public class TagThing extends AbstractAnchorPointThing implements IHasMutableColor, IHasMutableAnchorPoint,
		IHasMutableText, IHasMutableAngle, IHasMutableIndicatorPoint, IHasMutableFontData, IRelativeMovable {

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
		setColor(new RGB(0, 0, 0));
		addShapeModifyingKey(IHasIndicatorPoint.INDICATOR_POINT_KEY);
	}

	@Override
	public RGB getColor() {
		return get(COLOR_KEY);
	}

	@Override
	public void setColor(RGB newColor) {
		set(COLOR_KEY, newColor);
	}

	@Override
	public String getText() {
		return get(TEXT_KEY);
	}

	@Override
	public void setText(String text) {
		set(TEXT_KEY, text);
	}

	@Override
	public int getAngle() {
		return get(ANGLE_KEY);
	}

	@Override
	public void setAngle(int angle) {
		set(ANGLE_KEY, angle);
	}

	@Override
	public Point getIndicatorPoint() {
		return get(INDICATOR_POINT_KEY);
	}

	public void setIndicatorPoint(Point p) {
		set(INDICATOR_POINT_KEY, p);
	}

	@Override
	public String getFontName() {
		return get(FONT_NAME_KEY);
	}

	@Override
	public void setFontName(String fontName) {
		set(FONT_NAME_KEY, fontName);
	}

	@Override
	public int getFontSize() {
		return get(FONT_SIZE_KEY);
	}

	@Override
	public void setFontSize(int fontSize) {
		set(FONT_SIZE_KEY, fontSize);
	}

	@Override
	public FontStyle getFontStyle() {
		return get(FONT_STYLE_KEY);
	}

	@Override
	public void setFontStyle(FontStyle fontStyle) {
		set(FONT_STYLE_KEY, fontStyle);
	}

	@Override
	public void setDontIncreaseFontSize(boolean dontIncrease) {
		set(DONT_INCREASE_FONT_SIZE_KEY, dontIncrease);
	}

	@Override
	public boolean getDontIncreaseFontSize() {
		return get(DONT_INCREASE_FONT_SIZE_KEY);
	}
}
