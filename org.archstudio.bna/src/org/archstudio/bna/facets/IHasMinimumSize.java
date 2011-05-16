package org.archstudio.bna.facets;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.DimensionThingKey;
import org.eclipse.draw2d.geometry.Dimension;

public interface IHasMinimumSize extends IHasMutableMinimumSize {

	public static final IThingKey<Dimension> MINIMUM_SIZE_KEY = DimensionThingKey.create("minimum-size");

	public Dimension getMinimumSize();
}
