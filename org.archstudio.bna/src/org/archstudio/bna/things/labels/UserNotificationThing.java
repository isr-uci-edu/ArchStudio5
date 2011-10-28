package org.archstudio.bna.things.labels;

import org.archstudio.bna.constants.IFontConstants;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableFontData;
import org.archstudio.bna.facets.IHasMutableHorizontalAlignment;
import org.archstudio.bna.facets.IHasMutableLife;
import org.archstudio.bna.facets.IHasMutableSecondaryColor;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasMutableVerticalAlignment;
import org.archstudio.bna.things.AbstractAnchorPointThing;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.VerticalAlignment;
import org.eclipse.swt.graphics.RGB;

public class UserNotificationThing extends AbstractAnchorPointThing implements IHasMutableText, IHasMutableColor,
		IHasMutableSecondaryColor, IHasMutableLife, IHasMutableHorizontalAlignment, IHasMutableVerticalAlignment,
		IHasMutableFontData {

	public UserNotificationThing() {
		this(null);
	}

	public UserNotificationThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setText("");
		setFontName(IFontConstants.DEFAULT_FONT_NAME);
		setFontSize(10);
		setFontStyle(FontStyle.NORMAL);
		setHorizontalAlignment(HorizontalAlignment.CENTER);
		setVerticalAlignment(VerticalAlignment.MIDDLE);
		setLife(16);
	}

	@Override
	public void setColor(RGB c) {
		set(COLOR_KEY, c);
	}

	@Override
	public RGB getColor() {
		return get(COLOR_KEY);
	}

	@Override
	public void setSecondaryColor(RGB c) {
		set(SECONDARY_COLOR_KEY, c);
	}

	@Override
	public RGB getSecondaryColor() {
		return get(SECONDARY_COLOR_KEY);
	}

	@Override
	public String getText() {
		return (String) get(TEXT_KEY);
	}

	@Override
	public void setText(String text) {
		set(TEXT_KEY, text);
	}

	@Override
	public HorizontalAlignment getHorizontalAlignment() {
		return (HorizontalAlignment) get(HORIZONTAL_ALIGNMENT_KEY);
	}

	@Override
	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		set(HORIZONTAL_ALIGNMENT_KEY, horizontalAlignment);
	}

	@Override
	public VerticalAlignment getVerticalAlignment() {
		return (VerticalAlignment) get(VERTICAL_ALIGNMENT_KEY);
	}

	@Override
	public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
		set(VERTICAL_ALIGNMENT_KEY, verticalAlignment);
	}

	@Override
	public String getFontName() {
		return (String) get(FONT_NAME_KEY);
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
		return (FontStyle) get(FONT_STYLE_KEY);
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
	public void setDontIncreaseFontSize(boolean dontIncreaseFontSize) {
		set(DONT_INCREASE_FONT_SIZE_KEY, dontIncreaseFontSize);
	}

	@Override
	public int getLife() {
		return get(LIFE_KEY);
	}

	@Override
	public void setLife(int life) {
		set(LIFE_KEY, life);
	}
}
