package org.archstudio.bna.facets;

import java.awt.Dimension;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasRoundedCorners extends IThing {

	public static final IThingKey<Dimension> CORNER_SIZE_KEY = ThingKey.create("corner", ThingKey.dimension());

	public Dimension getCornerSize();

}
