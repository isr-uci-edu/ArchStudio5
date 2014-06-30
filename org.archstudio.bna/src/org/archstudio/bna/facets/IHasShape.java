package org.archstudio.bna.facets;

import java.awt.Shape;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasShape extends IThing {

	public static final IThingKey<Shape> SHAPE_KEY = ThingKey.create("shape", ThingKey.shape());

	public Shape getShape();
}
