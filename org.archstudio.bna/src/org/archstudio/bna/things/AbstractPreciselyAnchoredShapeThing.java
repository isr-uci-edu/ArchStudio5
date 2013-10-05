package org.archstudio.bna.things;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.facets.IHasMutableShape;
import org.archstudio.bna.facets.IHasMutableSize;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public abstract class AbstractPreciselyAnchoredShapeThing extends AbstractPreciseAnchorPointThing implements
		IHasMutableSize, IHasMutableShape {

	public AbstractPreciselyAnchoredShapeThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setSize(new Dimension(8, 8));
		setShape(new Rectangle2D.Float(-0.5f, -0.5f, 1f, 1f));
		super.initProperties();
		addShapeModifyingKey(SIZE_KEY);
		addShapeModifyingKey(SHAPE_KEY);
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
