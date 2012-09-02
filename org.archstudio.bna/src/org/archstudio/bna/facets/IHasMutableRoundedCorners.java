package org.archstudio.bna.facets;

import java.awt.Dimension;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IHasMutableRoundedCorners extends IHasRoundedCorners {

	public void setCornerSize(Dimension dimension);

}
