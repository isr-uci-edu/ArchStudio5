package org.archstudio.bna.facets;

import java.awt.Dimension;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IHasMutableMinimumSize extends IHasMinimumSize {

	public abstract void setMinimumSize(Dimension minDimension);

}