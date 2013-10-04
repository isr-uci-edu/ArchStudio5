package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.CloneThingKey;
import org.eclipse.swt.graphics.Rectangle;

public interface IHasBoundingBox extends IThing {

	public static final IThingKey<Rectangle> BOUNDING_BOX_KEY = CloneThingKey.create("boundingBox",
			CloneThingKey.rectangle());

	public Rectangle getBoundingBox();
}
