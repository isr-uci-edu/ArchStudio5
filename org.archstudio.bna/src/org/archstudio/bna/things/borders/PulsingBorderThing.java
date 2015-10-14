package org.archstudio.bna.things.borders;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.RGB;

@NonNullByDefault
public class PulsingBorderThing extends PulsingBorderThingBase {

	public PulsingBorderThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setGlowColor(new RGB(255, 0, 0));
		setGlowWidth(20);
		setGlowAlpha(1d);
		setTicksPerIncrement(4);
	}

	@Override
	public boolean shouldIncrementRotatingOffset() {
		return true;
	}

}
