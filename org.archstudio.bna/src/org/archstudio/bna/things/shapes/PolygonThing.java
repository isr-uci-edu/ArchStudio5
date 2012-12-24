package org.archstudio.bna.things.shapes;

import org.archstudio.bna.facets.IHasLineData;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableEdgeColor;
import org.archstudio.bna.facets.IHasMutableGradientFill;
import org.archstudio.bna.facets.IHasMutableLineStyle;
import org.archstudio.bna.facets.IHasMutableLineWidth;
import org.archstudio.bna.facets.IHasMutableSecondaryColor;
import org.archstudio.bna.things.AbstractPolygonThing;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

public class PolygonThing extends AbstractPolygonThing implements IHasMutableColor, IHasMutableSecondaryColor,
		IHasMutableGradientFill, IHasMutableEdgeColor, IHasMutableLineStyle, IHasMutableLineWidth, IHasLineData {

	public PolygonThing(Object id) {
		super(id);
	}

	protected void initProperties() {
		super.initProperties();
		setColor(new RGB(128, 192, 128));
		setSecondaryColor(new RGB(192, 255, 192));
		setGradientFilled(true);
		setLineStyle(SWT.LINE_SOLID);
		setEdgeColor(new RGB(0, 0, 0));
		setLineWidth(1);
	}

	public void setColor(RGB c) {
		set(COLOR_KEY, c);
	}

	public RGB getColor() {
		return get(COLOR_KEY);
	}

	public void setSecondaryColor(RGB c) {
		set(SECONDARY_COLOR_KEY, c);
	}

	public RGB getSecondaryColor() {
		return get(SECONDARY_COLOR_KEY);
	}

	public boolean isGradientFilled() {
		return get(GRADIENT_FILLED_KEY);
	}

	public void setGradientFilled(boolean newHasGradientFill) {
		set(GRADIENT_FILLED_KEY, newHasGradientFill);
	}

	public void setEdgeColor(RGB c) {
		set(EDGE_COLOR_KEY, c);
	}

	public RGB getEdgeColor() {
		return get(EDGE_COLOR_KEY);
	}

	public int getLineStyle() {
		return get(LINE_STYLE_KEY);
	}

	public void setLineStyle(int lineStyle) {
		set(LINE_STYLE_KEY, lineStyle);
	}

	public int getLineWidth() {
		return get(LINE_WIDTH_KEY);
	}

	public void setLineWidth(int lineWidth) {
		set(LINE_WIDTH_KEY, lineWidth);
	}

}