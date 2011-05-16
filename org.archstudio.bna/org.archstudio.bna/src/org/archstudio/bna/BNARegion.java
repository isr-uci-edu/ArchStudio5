package org.archstudio.bna;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Region;

public class BNARegion implements IRegion {

	Region r;
	ICoordinateMapper cm;

	public BNARegion(Region r, ICoordinateMapper cm) {
		this.r = r;
		this.cm = cm;
	}

	@Override
	public boolean intersects(Rectangle rectangle) {
		Rectangle lr = cm.worldToLocal(rectangle.getCopy());
		int expand = (int) Math.ceil(cm.getLocalScale());
		lr.expand(expand, expand);
		return r.intersects(lr.x, lr.y, lr.width, lr.height);
	}
}
