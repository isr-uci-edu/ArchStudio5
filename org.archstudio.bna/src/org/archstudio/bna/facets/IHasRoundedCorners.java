package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.DimensionThingKey;
import org.eclipse.draw2d.geometry.Dimension;

public interface IHasRoundedCorners extends IThing {

	public static final IThingKey<Dimension> CORNER_SIZE_KEY = DimensionThingKey.create("corner");

	public Dimension getCornerSize();

}
