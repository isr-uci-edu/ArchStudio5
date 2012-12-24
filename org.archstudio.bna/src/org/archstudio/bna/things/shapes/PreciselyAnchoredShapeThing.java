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

	@Override
	protected void initProperties() {
		super.initProperties();
		setColor(new RGB(128, 128, 128));
		setEdgeColor(new RGB(0, 0, 0));
		setLineStyle(LINE_STYLE_SOLID);
		setLineWidth(1);
		setSize(new Dimension(8, 8));
		setShape(Shape.CIRCLE);
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

	@Override
	public Dimension getSize() {
		return get(SIZE_KEY);
	}

	@Override
	public void setSize(Dimension size) {
		set(SIZE_KEY, size);
	}

	@Override
	public Shape getShape() {
		return get(SHAPE_KEY);
	}

	@Override
	public void setShape(Shape shape) {
		set(SHAPE_KEY, shape);
	}

}
