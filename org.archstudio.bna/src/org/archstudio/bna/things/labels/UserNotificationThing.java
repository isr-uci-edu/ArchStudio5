package org.archstudio.bna.things.labels;

import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableEdgeColor;
import org.archstudio.bna.facets.IHasMutableFontData;
import org.archstudio.bna.facets.IHasMutableGradientFill;
import org.archstudio.bna.facets.IHasMutableLife;
import org.archstudio.bna.facets.IHasMutableSecondaryColor;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.things.AbstractMutableAnchorPointThing;
import org.archstudio.swtutils.constants.FontStyle;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.RGB;

@NonNullByDefault
public class UserNotificationThing extends AbstractMutableAnchorPointThing implements IHasMutableText,
		IHasMutableFontData, IHasMutableColor, IHasMutableGradientFill, IHasMutableSecondaryColor,
		IHasMutableEdgeColor, IHasMutableLife {

	public UserNotificationThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setText("");
		setFontName(null);
		setFontSize(10);
		setDontIncreaseFontSize(Boolean.TRUE);
		setFontStyle(FontStyle.NORMAL);
		setColor(new RGB(255, 255, 0));
		setGradientFilled(true);
		setSecondaryColor(new RGB(255, 255, 192));
		setEdgeColor(new RGB(0, 0, 0));
		setLife(32);
		super.initProperties();
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
	public @Nullable
	RGB getColor() {
		return get(COLOR_KEY);
	}

	@Override
	public void setColor(@Nullable RGB c) {
		set(COLOR_KEY, c);
	}

	@Override
	public void setSecondaryColor(@Nullable RGB c) {
		set(SECONDARY_COLOR_KEY, c);
	}

	@Override
	public @Nullable
	RGB getSecondaryColor() {
		return get(SECONDARY_COLOR_KEY);
	}

	@Override
	public boolean isGradientFilled() {
		return get(GRADIENT_FILLED_KEY);
	}

	@Override
	public void setGradientFilled(boolean newHasGradientFill) {
		set(GRADIENT_FILLED_KEY, newHasGradientFill);
	}

	@Override
	public void setEdgeColor(@Nullable RGB c) {
		set(EDGE_COLOR_KEY, c);
	}

	@Override
	public @Nullable
	RGB getEdgeColor() {
		return get(EDGE_COLOR_KEY);
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
