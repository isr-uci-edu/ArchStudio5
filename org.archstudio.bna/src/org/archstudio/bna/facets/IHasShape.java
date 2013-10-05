package org.archstudio.bna.facets;

import java.awt.Shape;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.CloneThingKey;

public interface IHasShape extends IThing {

	public static final IThingKey<Shape> SHAPE_KEY = CloneThingKey.create("shape", CloneThingKey.shape());

	public Shape getShape();
}
