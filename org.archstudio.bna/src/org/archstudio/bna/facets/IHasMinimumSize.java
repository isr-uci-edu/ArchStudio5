package org.archstudio.bna.facets;

import java.awt.Dimension;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.CloneThingKey;

public interface IHasMinimumSize extends IThing {

	public static final IThingKey<Dimension> MINIMUM_SIZE_KEY = CloneThingKey.create("minimum-size",
			CloneThingKey.dimension());

	public Dimension getMinimumSize();
}
