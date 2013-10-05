package org.archstudio.bna.graphs.things;

import org.archstudio.bna.facets.IHasMutableEdgeColor;
import org.archstudio.bna.facets.IHasMutableLineData;
import org.archstudio.bna.facets.IHasMutableUnit;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.things.AbstractRectangleThing;
import org.archstudio.swtutils.constants.LineStyle;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.RGB;

public class GraphGridLinesThing extends AbstractRectangleThing implements IHasMutableUnit, IHasMutableLineData,
		IHasMutableEdgeColor {

	public static enum Orientation {
		HORIZONTAL_LINES, VERTICAL_LINES
	}

	public static final IThingKey<Orientation> ORIENTATION_KEY = ThingKey.create("orientation");

	public GraphGridLinesThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setUnit(10);
		setOrientation(Orientation.HORIZONTAL_LINES);
		setLineStyle(LineStyle.DOT);
		setEdgeColor(new RGB(128, 128, 128));
		setLineWidth(1);
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

	public Orientation getOrientation() {
		return get(ORIENTATION_KEY);
	}

	public void setOrientation(Orientation orientation) {
		set(ORIENTATION_KEY, orientation);
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
