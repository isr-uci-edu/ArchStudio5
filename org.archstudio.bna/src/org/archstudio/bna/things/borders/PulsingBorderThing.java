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
		setRawGlowColor(new RGB(255, 0, 0));
		setRawGlowWidth(20);
		setRawGlowAlpha(1d);
	}

	@Override
	public boolean shouldIncrementRotatingOffset() {
		return true;
	}

}
