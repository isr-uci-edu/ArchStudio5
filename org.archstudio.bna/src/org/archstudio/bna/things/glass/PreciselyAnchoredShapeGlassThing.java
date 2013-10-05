package org.archstudio.bna.things.glass;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.things.AbstractPreciselyAnchoredShapeThing;
import org.eclipse.jdt.annotation.Nullable;

public class PreciselyAnchoredShapeGlassThing extends AbstractPreciselyAnchoredShapeThing implements IIsSticky,
		IHasMutableSelected, IHasMutableRotatingOffset {

	public PreciselyAnchoredShapeGlassThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setSize(new Dimension(8, 8));
		setShape(new Rectangle2D.Float(-0.5f, -0.5f, 1f, 1f));
		setSelected(false);
		set(ROTATING_OFFSET_KEY, 0);
		super.initProperties();
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

	@Override
	public boolean isSelected() {
		return has(SELECTED_KEY, true);
	}

	@Override
	public void setSelected(boolean selected) {
		set(SELECTED_KEY, selected);
	}

}
