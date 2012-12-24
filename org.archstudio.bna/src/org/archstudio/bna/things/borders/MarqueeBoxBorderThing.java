package org.archstudio.bna.things.borders;

import java.awt.Dimension;

import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.facets.IHasRotatingOffset;
import org.archstudio.bna.things.AbstractRectangleThing;

public class MarqueeBoxBorderThing extends AbstractRectangleThing implements IHasMutableRotatingOffset {

	public MarqueeBoxBorderThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		incrementRotatingOffset();
		setMinimumSize(new Dimension(0, 0));
	}

	@Override
	public int getRotatingOffset() {
		return get(IHasRotatingOffset.ROTATING_OFFSET_KEY);
	}

	@Override
	public boolean shouldIncrementRotatingOffset() {
		return true;
	}

	@Override
	public void incrementRotatingOffset() {
		Integer io = get(IHasRotatingOffset.ROTATING_OFFSET_KEY);
		int i = io == null ? 0 : io + 1;
		set(IHasRotatingOffset.ROTATING_OFFSET_KEY, i);
	}
}
