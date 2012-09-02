package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.ThingKey;

public interface IHasShape extends IThing {

	public static enum Shape {
		CIRCLE, SQUARE
	}

	public static final IThingKey<Shape> SHAPE_KEY = ThingKey.create("shape");

	public Shape getShape();

}
