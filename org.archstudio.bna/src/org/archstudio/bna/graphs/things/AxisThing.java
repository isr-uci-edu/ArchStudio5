package org.archstudio.bna.graphs.things;

import org.archstudio.bna.constants.IFontConstants;
import org.archstudio.bna.facets.IHasMutableEdgeColor;
import org.archstudio.bna.facets.IHasMutableFontData;
import org.archstudio.bna.facets.IHasMutableLineData;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasMutableUnit;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.things.AbstractRectangleThing;
import org.archstudio.swtutils.constants.FontStyle;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

@NonNullByDefault
public class AxisThing extends AbstractRectangleThing implements IHasMutableUnit, IHasMutableLineData,
		IHasMutableEdgeColor, IHasMutableFontData, IHasMutableText {

	public static enum Orientation {
		TOP, BOTTOM, LEFT, RIGHT
	}

	public static final IThingKey<Orientation> ORIENTATION_KEY = ThingKey.create("orientation");
	public static final IThingKey<Integer> LOCAL_TICK_SIZE_KEY = ThingKey.create("local-tick-size");

	public AxisThing(@Nullable Object id) {
		super(id);
	}

	protected void initProperties() {
		super.initProperties();
		setUnit(10);
		setLocalTickSize(6);
		setOrientation(Orientation.BOTTOM);
		setLineStyle(SWT.LINE_SOLID);
		setEdgeColor(new RGB(0, 0, 0));
		setLineWidth(3);
		setFontName(IFontConstants.DEFAULT_FONT_NAME);
		setFontSize(12);
		setFontStyle(FontStyle.NORMAL);
		setDontIncreaseFontSize(true);
		setText("");
	}

	public int getUnit() {
		return get(UNIT_KEY, 10);
	}

	public void setUnit(int unit) {
		set(UNIT_KEY, unit);
	}

	public void setLocalTickSize(int localTickSize) {
		set(LOCAL_TICK_SIZE_KEY, localTickSize);
	}

	public int getLocalTickSize() {
		return get(LOCAL_TICK_SIZE_KEY, 6);
	}

	public Orientation getOrientation() {
		return get(ORIENTATION_KEY, Orientation.BOTTOM);
	}

	public void setOrientation(Orientation orientation) {
		set(ORIENTATION_KEY, orientation);
	}

	public void setEdgeColor(@Nullable RGB c) {
		set(EDGE_COLOR_KEY, c);
	}

	public @Nullable
	RGB getEdgeColor() {
		return get(EDGE_COLOR_KEY);
	}

	public int getLineStyle() {
		return get(LINE_STYLE_KEY, LINE_STYLE_SOLID);
	}

	public void setLineStyle(int lineStyle) {
		set(LINE_STYLE_KEY, lineStyle);
	}

	public int getLineWidth() {
		return get(LINE_WIDTH_KEY, 3);
	}

	public void setLineWidth(int lineWidth) {
		set(LINE_WIDTH_KEY, lineWidth);
	}

	public String getFontName() {
		return get(FONT_NAME_KEY, IFontConstants.DEFAULT_FONT_NAME);
	}

	public int getFontSize() {
		return get(FONT_SIZE_KEY, 12);
	}

	public FontStyle getFontStyle() {
		return get(FONT_STYLE_KEY, FontStyle.NORMAL);
	}

	public boolean getDontIncreaseFontSize() {
		return get(DONT_INCREASE_FONT_SIZE_KEY, true);
	}

	public void setFontName(String fontName) {
		set(FONT_NAME_KEY, fontName);
	}

	public void setFontSize(int fontSize) {
		set(FONT_SIZE_KEY, fontSize);
	}

	public void setFontStyle(FontStyle fontStyle) {
		set(FONT_STYLE_KEY, fontStyle);
	}

	public void setDontIncreaseFontSize(boolean dontIncreaseFontSize) {
		set(DONT_INCREASE_FONT_SIZE_KEY, dontIncreaseFontSize);
	}

	public String getText() {
		return get(TEXT_KEY, "");
	}

	public void setText(String text) {
		set(TEXT_KEY, text);
	}
}
