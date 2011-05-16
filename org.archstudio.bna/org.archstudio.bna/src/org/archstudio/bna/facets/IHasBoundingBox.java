package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.RectangleThingKey;
import org.eclipse.draw2d.geometry.Rectangle;

public interface IHasBoundingBox extends IThing {

	public static final IThingKey<Rectangle> BOUNDING_BOX_KEY = RectangleThingKey.create("boundingBox");

	public Rectangle getBoundingBox();
}
