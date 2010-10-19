package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.Rectangle;

public interface IHasMutableBoundingBox extends IHasBoundingBox {

	public void setBoundingBox(Rectangle worldBoundingBox);

}
