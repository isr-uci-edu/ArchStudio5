package org.archstudio.bna.facets;

import java.awt.Dimension;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.CloneThingKey;

public interface IHasRoundedCorners extends IThing {

	public static final IThingKey<Dimension> CORNER_SIZE_KEY = CloneThingKey
			.create("corner", CloneThingKey.dimension());

	public Dimension getCornerSize();

}
