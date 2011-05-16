package org.archstudio.bna.facets;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.DimensionThingKey;
import org.eclipse.draw2d.geometry.Dimension;

public interface IHasSize {

	public static final IThingKey<Dimension> SIZE_KEY = DimensionThingKey.create("size");

	public Dimension getSize();

}
