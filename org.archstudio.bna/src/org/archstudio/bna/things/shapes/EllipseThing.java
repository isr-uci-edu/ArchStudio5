package org.archstudio.bna.things.shapes;

import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableEdgeColor;
import org.archstudio.bna.facets.IHasMutableGradientFill;
import org.archstudio.bna.facets.IHasMutableLineData;
import org.archstudio.bna.facets.IHasMutableSecondaryColor;
import org.archstudio.bna.things.AbstractEllipseThing;
import org.eclipse.swt.graphics.RGB;

public class EllipseThing extends AbstractEllipseThing implements IHasMutableColor, IHasMutableSecondaryColor,
		IHasMutableGradientFill, IHasMutableEdgeColor, IHasMutableLineData {

	public EllipseThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setColor(new RGB(192, 128, 128));
		setSecondaryColor(new RGB(255, 192, 192));
		setGradientFilled(true);
		setEdgeColor(new RGB(0, 0, 0));
		setLineStyle(LINE_STYLE_SOLID);
		setLineWidth(1);
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
	public int getLineStyle() {
		return get(LINE_STYLE_KEY);
	}

	@Override
	public void setLineStyle(int lineStyle) {
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