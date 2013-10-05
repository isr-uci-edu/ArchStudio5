package org.archstudio.bna.things.glass;

import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.things.AbstractPolygonThing;
import org.eclipse.jdt.annotation.Nullable;

public class PolygonGlassThing extends AbstractPolygonThing implements IHasMutableSelected, IHasMutableRotatingOffset {

	public PolygonGlassThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
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
