package org.archstudio.bna.things.labels;

import org.archstudio.bna.constants.IFontConstants;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableEdgeColor;
import org.archstudio.bna.facets.IHasMutableFontData;
import org.archstudio.bna.facets.IHasMutableGradientFill;
import org.archstudio.bna.facets.IHasMutableLife;
import org.archstudio.bna.facets.IHasMutableSecondaryColor;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.things.AbstractAnchorPointThing;
import org.archstudio.swtutils.constants.FontStyle;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.RGB;

@NonNullByDefault
public class UserNotificationThing extends AbstractAnchorPointThing implements IHasMutableText, IHasMutableFontData,
		IHasMutableColor, IHasMutableGradientFill, IHasMutableSecondaryColor, IHasMutableEdgeColor, IHasMutableLife {

	public UserNotificationThing(@Nullable Object id) {
		super(id);
	}

	protected void initProperties() {
		super.initProperties();
		setText("");
		setFontName(IFontConstants.DEFAULT_FONT_NAME);
		setFontSize(10);
		setDontIncreaseFontSize(Boolean.TRUE);
		setFontStyle(FontStyle.NORMAL);
		setColor(new RGB(255, 255, 0));
		setGradientFilled(true);
		setSecondaryColor(new RGB(255, 255, 192));
		setEdgeColor(new RGB(0, 0, 0));
		setLife(32);
	}

	public String getText() {
		return get(TEXT_KEY, "");
	}

	public void setText(String text) {
		set(TEXT_KEY, text);
	}

	public String getFontName() {
		return get(FONT_NAME_KEY, IFontConstants.DEFAULT_FONT_NAME);
	}

	public void setFontName(String fontName) {
		set(FONT_NAME_KEY, fontName);
	}

	public int getFontSize() {
		return get(FONT_SIZE_KEY, 10);
	}

	public void setFontSize(int fontSize) {
		set(FONT_SIZE_KEY, fontSize);
	}

	public FontStyle getFontStyle() {
		return get(FONT_STYLE_KEY, FontStyle.NORMAL);
	}

	public void setFontStyle(FontStyle fontStyle) {
		set(FONT_STYLE_KEY, fontStyle);
	}

	public boolean getDontIncreaseFontSize() {
		return get(DONT_INCREASE_FONT_SIZE_KEY, true);
	}

	public void setDontIncreaseFontSize(boolean dontIncrease) {
		set(DONT_INCREASE_FONT_SIZE_KEY, dontIncrease);
	}

	public @Nullable
	RGB getColor() {
		return get(COLOR_KEY);
	}

	public void setColor(@Nullable RGB c) {
		set(COLOR_KEY, c);
	}

	public void setSecondaryColor(@Nullable RGB c) {
		set(SECONDARY_COLOR_KEY, c);
	}

	public @Nullable
	RGB getSecondaryColor() {
		return get(SECONDARY_COLOR_KEY);
	}

	public boolean isGradientFilled() {
		return get(GRADIENT_FILLED_KEY, true);
	}

	public void setGradientFilled(boolean newHasGradientFill) {
		set(GRADIENT_FILLED_KEY, newHasGradientFill);
	}

	public void setEdgeColor(@Nullable RGB c) {
		set(EDGE_COLOR_KEY, c);
	}

	public @Nullable
	RGB getEdgeColor() {
		return get(EDGE_COLOR_KEY);
	}

	public int getLife() {
		return get(LIFE_KEY, 32);
	}

	public void setLife(int life) {
		set(LIFE_KEY, life);
	}

}
