package org.archstudio.bna.things.shapes;

import org.archstudio.bna.facets.IHasLineData;
import org.archstudio.bna.facets.IHasMutableEdgeColor;
import org.archstudio.bna.facets.IHasMutableLineStyle;
import org.archstudio.bna.facets.IHasMutableLineWidth;
import org.archstudio.bna.things.AbstractMappingThing;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

public class MappingThing extends AbstractMappingThing implements IHasMutableEdgeColor, IHasMutableLineWidth,
		IHasMutableLineStyle, IHasLineData {

	public MappingThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setLineStyle(SWT.LINE_SOLID);
		setEdgeColor(new RGB(0, 0, 0));
		setLineWidth(1);
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
