package org.archstudio.bna.facets;

import org.eclipse.draw2d.geometry.Dimension;

public interface IHasMutableMinimumSize extends IHasMinimumSize {

	public abstract void setMinimumSize(Dimension minDimension);

}