package org.archstudio.bna.things.shapes;

import java.awt.Dimension;

import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableEdgeColor;
import org.archstudio.bna.facets.IHasMutableLineData;
import org.archstudio.bna.facets.IHasMutableShape;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.things.AbstractPreciseAnchorPointThing;
import org.eclipse.swt.graphics.RGB;

public class PreciselyAnchoredShapeThing extends AbstractPreciseAnchorPointThing implements IHasMutableColor,
		IHasMutableEdgeColor, IHasMutableSize, IHasMutableLineData, IHasMutableShape {

	public PreciselyAnchoredShapeThing(Object id) {
		super(id);
	}

	protected void initProperties() {
		super.initProperties();
		setColor(new RGB(128, 128, 128));
		setEdgeColor(new RGB(0, 0, 0));
		setLineStyle(LINE_STYLE_SOLID);
		setLineWidth(1);
		setSize(new Dimension(8, 8));
		setShape(Shape.CIRCLE);
	}

	public void setColor(RGB c) {
		set(COLOR_KEY, c);
	}

	public RGB getColor() {
		return get(COLOR_KEY);
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

	public Dimension getSize() {
		return get(SIZE_KEY);
	}

	public void setSize(Dimension size) {
		set(SIZE_KEY, size);
	}

	public Shape getShape() {
		return get(SHAPE_KEY);
	}

	public void setShape(Shape shape) {
		set(SHAPE_KEY, shape);
	}

}
