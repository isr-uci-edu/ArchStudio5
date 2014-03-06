package org.archstudio.bna.facets;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;

public interface IHasMutableIndicatorPoint extends IHasIndicatorPoint {
	public void setIndicatorPoint(@Nullable Point indicatorPoint);
}
