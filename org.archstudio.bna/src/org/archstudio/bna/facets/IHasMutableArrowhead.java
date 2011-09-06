package org.archstudio.bna.facets;

import org.archstudio.bna.constants.ArrowheadShape;

public interface IHasMutableArrowhead extends IHasArrowhead {
	public void setArrowheadShape(ArrowheadShape arrowheadShape);

	public void setArrowheadSize(int arrowheadSize);
}
