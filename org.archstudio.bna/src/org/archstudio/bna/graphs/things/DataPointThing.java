package org.archstudio.bna.graphs.things;

import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableEdgeColor;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.things.AbstractAnchorPointThing;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.RGB;

public class DataPointThing extends AbstractAnchorPointThing implements IHasMutableColor, IHasMutableEdgeColor,
		IHasMutableSize {

	public static enum Shape {
		CIRCLE, SQUARE
	}

	public static final IThingKey<Shape> SHAPE_KEY = ThingKey.create("shape");

	public DataPointThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setColor(new RGB(128, 128, 128));
		setEdgeColor(new RGB(0, 0, 0));
		setSize(new Dimension(4, 4));
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
	public Dimension getSize() {
		return get(SIZE_KEY);
	}

	@Override
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
