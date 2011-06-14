package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.AbstractCloneThingKey;
import org.archstudio.bna.keys.CloneThingKey;
import org.eclipse.draw2d.geometry.Dimension;

public interface IHasRoundedCorners extends IThing {

	public static final IThingKey<Dimension> CORNER_SIZE_KEY = CloneThingKey.create("corner",
			AbstractCloneThingKey.dimension());

	public Dimension getCornerSize();

}
