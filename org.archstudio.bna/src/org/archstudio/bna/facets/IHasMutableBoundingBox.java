package org.archstudio.bna.facets;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.Rectangle;

/**
 * For mutable properties, see {@link IRelativeMovable} and {@link IHasMutableSize}.
 */

@NonNullByDefault
public interface IHasMutableBoundingBox extends IHasBoundingBox {

	public void setBoundingBox(Rectangle r);

}
