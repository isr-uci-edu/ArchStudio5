package org.archstudio.bna.things.shapes;

import org.archstudio.bna.facets.IHasLineData;
import org.archstudio.bna.facets.IHasMutableEdgeColor;
import org.archstudio.bna.facets.IHasMutableLineStyle;
import org.archstudio.bna.facets.IHasMutableLineWidth;
import org.archstudio.bna.things.AbstractSplineThing;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

public class SplineThing extends AbstractSplineThing implements IHasMutableEdgeColor, IHasMutableLineWidth,
		IHasMutableLineStyle, IHasLineData {

	public SplineThing(Object id) {
		super(id);
	}

	protected void initProperties() {
		super.initProperties();
		setLineStyle(SWT.LINE_SOLID);
		setEdgeColor(new RGB(0, 0, 0));
		setLineWidth(1);
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
