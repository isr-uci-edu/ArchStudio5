package org.archstudio.bna.facets;

import java.awt.Shape;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.CloneThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IIsSticky extends IHasShapeKeys, IThing {

	public static final IThingKey<Shape> STICKY_SHAPE_KEY = CloneThingKey.create("sticky_shape", CloneThingKey.shape());

	Shape getStickyShape();

}
