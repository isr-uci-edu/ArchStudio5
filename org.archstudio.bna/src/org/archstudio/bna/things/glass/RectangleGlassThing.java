package org.archstudio.bna.things.glass;

import java.awt.Dimension;

import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.facets.IHasMutableRoundedCorners;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasRotatingOffset;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.things.AbstractRoundedRectangleThing;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class RectangleGlassThing extends AbstractRoundedRectangleThing implements IHasMutableSelected,
		IHasMutableRotatingOffset, IHasMutableRoundedCorners {

	public RectangleGlassThing(@Nullable Object id) {
		super(id);
	}

	protected void initProperties() {
		super.initProperties();
		setSelected(false);
		incrementRotatingOffset();
		setCornerSize(new Dimension(0, 0));
	}

	public int getRotatingOffset() {
		return get(IHasRotatingOffset.ROTATING_OFFSET_KEY, 0);
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

	public Dimension getCornerSize() {
		return get(CORNER_SIZE_KEY, new Dimension(0, 0));
	}

	public void setCornerSize(Dimension dimension) {
		set(CORNER_SIZE_KEY, dimension);
	}

}
