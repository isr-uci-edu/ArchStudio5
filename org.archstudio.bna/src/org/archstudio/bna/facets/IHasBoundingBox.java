package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.swt.graphics.Rectangle;

public interface IHasBoundingBox extends IThing {

	public static final IThingKey<Rectangle> BOUNDING_BOX_KEY = ThingKey.create("boundingBox", ThingKey.rectangle());

	public Rectangle getBoundingBox();
}
