package org.archstudio.bna.facets;

import java.awt.Dimension;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasMinimumSize extends IThing {

	public static final IThingKey<Dimension> MINIMUM_SIZE_KEY = ThingKey.create("minimum-size", ThingKey.dimension());

	public Dimension getMinimumSize();
}
