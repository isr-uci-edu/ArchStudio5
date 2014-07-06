package org.archstudio.bna.things.shapes;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class LocalShapeThing extends LocalShapeThingBase {

	public LocalShapeThing(@Nullable Object id) {
		super(id);
	}

	@Override
	public boolean shouldIncrementRotatingOffset() {
		return isRawSelected();
	}

}
