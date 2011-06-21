package org.archstudio.bna.things.labels;

import org.archstudio.bna.constants.IFontConstants;
import org.archstudio.bna.facets.IHasMutableAngle;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableFontData;
import org.archstudio.bna.facets.IHasMutableHorizontalAlignment;
import org.archstudio.bna.facets.IHasMutableOffset;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasMutableVerticalAlignment;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.things.AbstractAnchorPointThing;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.VerticalAlignment;
import org.eclipse.swt.graphics.RGB;

public class AnchoredLabelThing extends AbstractAnchorPointThing implements IHasMutableText, IHasMutableColor,
		IHasMutableHorizontalAlignment, IHasMutableVerticalAlignment, IHasMutableFontData, IRelativeMovable,
		IHasMutableAngle, IHasMutableOffset {

	public AnchoredLabelThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setText("[text]");
		setColor(new RGB(0, 0, 0));
		setFontName(IFontConstants.DEFAULT_FONT_NAME);
		setFontSize(10);
		setFontStyle(FontStyle.NORMAL);
		setHorizontalAlignment(HorizontalAlignment.CENTER);
		setVerticalAlignment(VerticalAlignment.MIDDLE);
		setAngle(0);
		setOffset(0);
		super.initProperties();
	}

	@Override
	public RGB getColor() {
		return get(COLOR_KEY);
	}

	@Override
	public void setColor(RGB c) {
		set(COLOR_KEY, c);
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
	public HorizontalAlignment getHorizontalAlignment() {
		return get(HORIZONTAL_ALIGNMENT_KEY);
	}

	@Override
	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		set(HORIZONTAL_ALIGNMENT_KEY, horizontalAlignment);
	}

	@Override
	public VerticalAlignment getVerticalAlignment() {
		return get(VERTICAL_ALIGNMENT_KEY);
	}

	@Override
	public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
		set(VERTICAL_ALIGNMENT_KEY, verticalAlignment);
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
	public boolean getDontIncreaseFontSize() {
		return get(DONT_INCREASE_FONT_SIZE_KEY);
	}

	@Override
	public void setDontIncreaseFontSize(boolean dontIncrease) {
		set(DONT_INCREASE_FONT_SIZE_KEY, dontIncrease);
	}

	@Override
	public int getAngle() {
		return get(ANGLE_KEY);
	}

	@Override
	public void setAngle(int degrees) {
		set(ANGLE_KEY, degrees);
	}

	@Override
	public int getOffset() {
		return get(OFFSET_KEY);
	}

	@Override
	public void setOffset(int offset) {
		set(OFFSET_KEY, offset);
	}
}
