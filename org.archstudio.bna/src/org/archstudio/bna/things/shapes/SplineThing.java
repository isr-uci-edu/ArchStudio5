package org.archstudio.bna.things.shapes;

import org.archstudio.bna.facets.IHasLineData;
import org.archstudio.bna.facets.IHasMutableEdgeColor;
import org.archstudio.bna.facets.IHasMutableLineStyle;
import org.archstudio.bna.facets.IHasMutableLineWidth;
import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.things.AbstractSplineThing;
import org.archstudio.swtutils.constants.LineStyle;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.RGB;

public class SplineThing extends AbstractSplineThing implements IHasMutableEdgeColor, IHasMutableLineWidth,
		IHasMutableLineStyle, IHasLineData, IHasMutableSelected, IHasMutableRotatingOffset {

	public SplineThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setLineStyle(LineStyle.SOLID);
		setEdgeColor(new RGB(0, 0, 0));
		setLineWidth(1);
		setSelected(false);
		set(ROTATING_OFFSET_KEY, 0);
		super.initProperties();
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

	@Override
	public boolean isSelected() {
		return has(SELECTED_KEY, true);
	}

	@Override
	public void setSelected(boolean selected) {
		set(SELECTED_KEY, selected);
	}

	@Override
	public int getRotatingOffset() {
		return get(ROTATING_OFFSET_KEY);
	}

	@Override
	public boolean shouldIncrementRotatingOffset() {
		return isSelected();
	}

	@Override
	public void incrementRotatingOffset() {
		set(ROTATING_OFFSET_KEY, get(ROTATING_OFFSET_KEY) + 1);
	}
}
