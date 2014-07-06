package org.archstudio.bna.things.borders;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class PulsingBorderThing extends PulsingBorderThingBase {

	public PulsingBorderThing(@Nullable Object id) {
		super(id);
	}

	@Override
	public boolean shouldIncrementRotatingOffset() {
		return true;
	}

}
