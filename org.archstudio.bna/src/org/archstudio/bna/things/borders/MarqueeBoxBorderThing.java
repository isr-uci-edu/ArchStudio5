package org.archstudio.bna.things.borders;

import java.awt.Dimension;

import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.things.AbstractRectangleThing;
import org.eclipse.jdt.annotation.Nullable;

public class MarqueeBoxBorderThing extends AbstractRectangleThing implements IHasMutableRotatingOffset {

	public MarqueeBoxBorderThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setMinimumSize(new Dimension(0, 0));
		set(ROTATING_OFFSET_KEY, 0);
		super.initProperties();
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
