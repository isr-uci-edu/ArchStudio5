package org.archstudio.bna.facets;

import java.awt.Dimension;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasSize extends IThing {

	public static final IThingKey<Dimension> SIZE_KEY = ThingKey.create("size", ThingKey.dimension());

	public Dimension getSize();

}
