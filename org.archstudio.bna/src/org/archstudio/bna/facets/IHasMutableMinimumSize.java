package org.archstudio.bna.facets;

import java.awt.Dimension;

public interface IHasMutableMinimumSize extends IHasMinimumSize {

	public abstract void setMinimumSize(Dimension minDimension);

}