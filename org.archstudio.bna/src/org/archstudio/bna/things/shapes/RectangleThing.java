package org.archstudio.bna.things.shapes;

import java.awt.Dimension;

import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableCount;
import org.archstudio.bna.facets.IHasMutableEdgeColor;
import org.archstudio.bna.facets.IHasMutableGradientFill;
import org.archstudio.bna.facets.IHasMutableLineData;
import org.archstudio.bna.facets.IHasMutableRoundedCorners;
import org.archstudio.bna.facets.IHasMutableSecondaryColor;
import org.archstudio.bna.things.AbstractRoundedRectangleThing;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.RGB;

@NonNullByDefault
public class RectangleThing extends AbstractRoundedRectangleThing implements IHasMutableColor,
		IHasMutableSecondaryColor, IHasMutableGradientFill, IHasMutableEdgeColor, IHasMutableCount,
		IHasMutableLineData, IHasMutableRoundedCorners {

	public RectangleThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setColor(new RGB(255, 255, 192));
		setSecondaryColor(new RGB(192, 192, 128));
		setGradientFilled(true);
		setEdgeColor(new RGB(0, 0, 0));
		setLineStyle(LINE_STYLE_SOLID);
		setLineWidth(1);
		setCount(1);
		setCornerSize(new Dimension(0, 0));
	}

	@Override
	public void setColor(@Nullable RGB c) {
		set(COLOR_KEY, c);
	}

	@Override
	public @Nullable
	RGB getColor() {
		return get(COLOR_KEY);
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
		return get(GRADIENT_FILLED_KEY, true);
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
	public void setCount(int count) {
		set(COUNT_KEY, count);
	}

	@Override
	public int getCount() {
		return get(COUNT_KEY, 1);
	}

	@Override
	public int getLineStyle() {
		return get(LINE_STYLE_KEY, LINE_STYLE_SOLID);
	}

	@Override
	public void setLineStyle(int lineStyle) {
		set(LINE_STYLE_KEY, lineStyle);
	}

	@Override
	public int getLineWidth() {
		return get(LINE_WIDTH_KEY, 1);
	}

	@Override
	public void setLineWidth(int lineWidth) {
		set(LINE_WIDTH_KEY, lineWidth);
	}
}