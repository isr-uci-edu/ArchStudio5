package org.archstudio.bna.things.glass;

import java.awt.Dimension;

import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableShape;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.facets.IHasRotatingOffset;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.things.AbstractPreciseAnchorPointThing;

public class PreciselyAnchoredShapeGlassThing extends AbstractPreciseAnchorPointThing implements IIsSticky,
		IHasMutableSelected, IHasMutableRotatingOffset, IHasMutableSize, IHasMutableShape {

	public PreciselyAnchoredShapeGlassThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setSelected(false);
		incrementRotatingOffset();
		setSize(new Dimension(8, 8));
		setShape(Shape.CIRCLE);
	}

	@Override
	public int getRotatingOffset() {
		return get(IHasRotatingOffset.ROTATING_OFFSET_KEY);
	}

	@Override
	public boolean shouldIncrementRotatingOffset() {
		return isSelected();
	}

	@Override
	public void incrementRotatingOffset() {
		Integer io = get(IHasRotatingOffset.ROTATING_OFFSET_KEY);
		int i = io == null ? 0 : io + 1;
		set(IHasRotatingOffset.ROTATING_OFFSET_KEY, i);
	}

	@Override
	public boolean isSelected() {
		return Boolean.TRUE.equals(get(IHasSelected.SELECTED_KEY));
	}

	@Override
	public void setSelected(boolean selected) {
		set(IHasSelected.SELECTED_KEY, selected);
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
