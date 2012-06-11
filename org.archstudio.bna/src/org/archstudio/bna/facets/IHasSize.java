package org.archstudio.bna.facets;

import java.awt.Dimension;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.AbstractCloneThingKey;
import org.archstudio.bna.keys.CloneThingKey;

public interface IHasSize {

	public static final IThingKey<Dimension> SIZE_KEY = CloneThingKey.create("size", AbstractCloneThingKey.dimension());

	public Dimension getSize();

}
