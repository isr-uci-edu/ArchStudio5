package org.archstudio.bna.things.glass;

import java.awt.Dimension;

import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.things.AbstractBoundedAnchorPointThing;
import org.eclipse.jdt.annotation.Nullable;

public class EndpointGlassThing extends AbstractBoundedAnchorPointThing implements IHasMutableSelected,
		IHasMutableRotatingOffset {

	public EndpointGlassThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setSelected(false);
		setSize(new Dimension(10, 10));
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
