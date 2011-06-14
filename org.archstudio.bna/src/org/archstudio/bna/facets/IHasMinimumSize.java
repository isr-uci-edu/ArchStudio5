package org.archstudio.bna.facets;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.AbstractCloneThingKey;
import org.archstudio.bna.keys.CloneThingKey;
import org.eclipse.draw2d.geometry.Dimension;

public interface IHasMinimumSize extends IHasMutableMinimumSize {

	public static final IThingKey<Dimension> MINIMUM_SIZE_KEY = CloneThingKey.create("minimum-size",
			AbstractCloneThingKey.dimension());

	public Dimension getMinimumSize();
}
