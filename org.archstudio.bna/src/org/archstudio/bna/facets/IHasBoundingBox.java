package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.AbstractCloneThingKey;
import org.archstudio.bna.keys.CloneThingKey;
import org.eclipse.draw2d.geometry.Rectangle;

public interface IHasBoundingBox extends IThing {

	public static final IThingKey<Rectangle> BOUNDING_BOX_KEY = CloneThingKey.create("boundingBox",
			AbstractCloneThingKey.rectangle());

	public Rectangle getBoundingBox();
}
