package org.archstudio.bna.facets;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.Point;

@NonNullByDefault
public interface IHasMutableReferencePoint extends IHasReferencePoint {

	public void setReferencePoint(Point worldPoint);

}