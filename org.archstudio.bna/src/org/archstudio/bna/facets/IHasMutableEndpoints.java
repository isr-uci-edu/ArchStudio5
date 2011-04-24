package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.Point;

public interface IHasMutableEndpoints extends IHasEndpoints {

	public void setEndpoint1(Point endpoint1);

	public void setEndpoint2(Point endpoint2);
}
