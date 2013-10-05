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
import org.archstudio.swtutils.constants.LineStyle;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
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

	@Override
	protected void initProperties() {
		setUnit(10);
		setLocalTickSize(6);
		setOrientation(Orientation.BOTTOM);
		setLineStyle(LineStyle.SOLID);
		setEdgeColor(new RGB(0, 0, 0));
		setLineWidth(3);
		setFontName(IFontConstants.DEFAULT_FONT_NAME);
		setFontSize(12);
		setFontStyle(FontStyle.NORMAL);
		setDontIncreaseFontSize(true);
		setText("");
		super.initProperties();
	}

	@Override
	public int getUnit() {
		return get(UNIT_KEY);
	}

	@Override
	public void setUnit(int unit) {
		set(UNIT_KEY, unit);
	}

	public void setLocalTickSize(int localTickSize) {
		set(LOCAL_TICK_SIZE_KEY, localTickSize);
	}

	public int getLocalTickSize() {
		return get(LOCAL_TICK_SIZE_KEY);
	}

	public Orientation getOrientation() {
		return get(ORIENTATION_KEY);
	}

	public void setOrientation(Orientation orientation) {
		set(ORIENTATION_KEY, orientation);
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
	public LineStyle getLineStyle() {
		return get(LINE_STYLE_KEY);
	}

	@Override
	public void setLineStyle(LineStyle lineStyle) {
		set(LINE_STYLE_KEY, lineStyle);
	}

	@Override
	public int getLineWidth() {
		return get(LINE_WIDTH_KEY);
	}

	@Override
	public void setLineWidth(int lineWidth) {
		set(LINE_WIDTH_KEY, lineWidth);
	}

	@Override
	public String getFontName() {
		return get(FONT_NAME_KEY);
	}

	@Override
	public int getFontSize() {
		return get(FONT_SIZE_KEY);
	}

	@Override
	public FontStyle getFontStyle() {
		return get(FONT_STYLE_KEY);
	}

	@Override
	public boolean getDontIncreaseFontSize() {
		return get(DONT_INCREASE_FONT_SIZE_KEY);
	}

	@Override
	public void setFontName(String fontName) {
		set(FONT_NAME_KEY, fontName);
	}

	@Override
	public void setFontSize(int fontSize) {
		set(FONT_SIZE_KEY, fontSize);
	}

	@Override
	public void setFontStyle(FontStyle fontStyle) {
		set(FONT_STYLE_KEY, fontStyle);
	}

	@Override
	public void setDontIncreaseFontSize(boolean dontIncreaseFontSize) {
		set(DONT_INCREASE_FONT_SIZE_KEY, dontIncreaseFontSize);
	}

	@Override
	public String getText() {
		return get(TEXT_KEY);
	}

	@Override
	public void setText(String text) {
		set(TEXT_KEY, text);
	}
}
