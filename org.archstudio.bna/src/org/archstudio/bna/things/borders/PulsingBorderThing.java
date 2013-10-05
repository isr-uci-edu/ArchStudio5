package org.archstudio.bna.things.borders;

import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.things.AbstractRectangleThing;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.RGB;

public class PulsingBorderThing extends AbstractRectangleThing implements IHasMutableColor, IHasMutableRotatingOffset {

	public PulsingBorderThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setColor(new RGB(255, 0, 0));
		set(ROTATING_OFFSET_KEY, 0);
		super.initProperties();
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
	public int getRotatingOffset() {
		return get(ROTATING_OFFSET_KEY);
	}

	@Override
	public boolean shouldIncrementRotatingOffset() {
		return true;
	}

	@Override
	public void incrementRotatingOffset() {
		set(ROTATING_OFFSET_KEY, get(ROTATING_OFFSET_KEY) + 1);
	}
}
