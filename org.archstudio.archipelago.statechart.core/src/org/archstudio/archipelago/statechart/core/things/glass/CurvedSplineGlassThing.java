package org.archstudio.archipelago.statechart.core.things.glass;

import org.archstudio.archipelago.statechart.core.things.AbstractCurvedSplineThing;
import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasRotatingOffset;
import org.archstudio.bna.facets.IHasSelected;

public class CurvedSplineGlassThing extends AbstractCurvedSplineThing implements IHasMutableSelected,
		IHasMutableRotatingOffset {

	public CurvedSplineGlassThing(Object id) {
		super(id);
	}

	protected void initProperties() {
		super.initProperties();
		setSelected(false);
		incrementRotatingOffset();
	}

	public int getRotatingOffset() {
		return get(IHasRotatingOffset.ROTATING_OFFSET_KEY);
	}

	public boolean shouldIncrementRotatingOffset() {
		return isSelected();
	}

	public void incrementRotatingOffset() {
		Integer io = get(IHasRotatingOffset.ROTATING_OFFSET_KEY);
		int i = io == null ? 0 : io + 1;
		set(IHasRotatingOffset.ROTATING_OFFSET_KEY, i);
	}

	public boolean isSelected() {
		return Boolean.TRUE.equals(get(IHasSelected.SELECTED_KEY));
	}

	public void setSelected(boolean selected) {
		set(IHasSelected.SELECTED_KEY, selected);
	}

}
