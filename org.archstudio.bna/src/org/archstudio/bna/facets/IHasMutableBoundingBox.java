package org.archstudio.bna.facets;

import org.eclipse.draw2d.geometry.Rectangle;

/**
 * For mutable properties, see {@link IRelativeMovable} and
 * {@link IHasMutableSize}.
 */

public interface IHasMutableBoundingBox extends IHasBoundingBox {

	public void setBoundingBox(Rectangle r);

}
