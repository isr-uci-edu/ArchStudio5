package org.archstudio.bna.things.borders;

import java.util.concurrent.locks.Lock;

import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.things.AbstractRectangleThing;
import org.eclipse.swt.graphics.RGB;

public class PulsingBorderThing extends AbstractRectangleThing implements IHasMutableColor, IHasMutableRotatingOffset {

	public PulsingBorderThing() {
		this(null);
	}

	public PulsingBorderThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setColor(new RGB(255, 0, 0));
		set(ROTATING_OFFSET_KEY, 0);
	}

	public void setColor(RGB c) {
		set(COLOR_KEY, c);
	}

	public RGB getColor() {
		return get(COLOR_KEY);
	}

	public int getRotatingOffset() {
		return get(ROTATING_OFFSET_KEY);
	}

	public boolean shouldIncrementRotatingOffset() {
		return true;
	}

	public void incrementRotatingOffset() {
		Lock lock = getPropertyLock();
		lock.lock();
		try {
			set(ROTATING_OFFSET_KEY, (getRotatingOffset() + 1));
		}
		finally {
			lock.unlock();
		}
	}
}
