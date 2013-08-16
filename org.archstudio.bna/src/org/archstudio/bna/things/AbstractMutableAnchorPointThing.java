package org.archstudio.bna.things;

import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;

@NonNullByDefault
public abstract class AbstractMutableAnchorPointThing extends AbstractAnchorPointThing implements
		IHasMutableAnchorPoint {

	public AbstractMutableAnchorPointThing(@Nullable Object id) {
		super(id);
	}

	@Override
	public void setAnchorPoint(@Nullable Point p) {
		super.setAnchorPoint(p);
	}
}
