package org.archstudio.bna.things.shapes;

import org.archstudio.bna.facets.IHasLineData;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableEdgeColor;
import org.archstudio.bna.facets.IHasMutableGradientFill;
import org.archstudio.bna.facets.IHasMutableLineStyle;
import org.archstudio.bna.facets.IHasMutableLineWidth;
import org.archstudio.bna.facets.IHasMutableSecondaryColor;
import org.archstudio.bna.things.AbstractPolygonThing;
import org.archstudio.swtutils.constants.LineStyle;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.RGB;

public class PolygonThing extends AbstractPolygonThing implements IHasMutableColor, IHasMutableSecondaryColor,
		IHasMutableGradientFill, IHasMutableEdgeColor, IHasMutableLineStyle, IHasMutableLineWidth, IHasLineData {

	public PolygonThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setColor(new RGB(128, 192, 128));
		setSecondaryColor(new RGB(192, 255, 192));
		setGradientFilled(true);
		setLineStyle(LineStyle.SOLID);
		setEdgeColor(new RGB(0, 0, 0));
		setLineWidth(1);
		super.initProperties();
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
	public boolean isGradientFilled() {
		return get(GRADIENT_FILLED_KEY);
	}

	@Override
	public void setGradientFilled(boolean newHasGradientFill) {
		set(GRADIENT_FILLED_KEY, newHasGradientFill);
	}

	@Override
	public void setEdgeColor(RGB c) {
		set(EDGE_COLOR_KEY, c);
	}

	@Override
	public RGB getEdgeColor() {
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

}