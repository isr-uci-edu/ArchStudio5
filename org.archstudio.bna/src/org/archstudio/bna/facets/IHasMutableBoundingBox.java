package org.archstudio.bna.facets;

import org.eclipse.draw2d.geometry.Rectangle;

public interface IHasMutableBoundingBox extends IHasBoundingBox {

	public void setBoundingBox(Rectangle r);

}
