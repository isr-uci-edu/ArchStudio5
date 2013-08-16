package org.archstudio.bna.facets;

import java.awt.Shape;
import java.awt.geom.Path2D;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.CloneThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;

import com.google.common.base.Function;

@NonNullByDefault
public interface IIsSticky extends IHasShapeKeys, IThing {

	public static final IThingKey<Shape> STICKY_SHAPE_KEY = CloneThingKey.create("sticky_shape", new Function<Shape, Shape>() {
		@Override
		public Shape apply(Shape input) {
			return input != null ? new Path2D.Double(input) : null;
		}
	});

	Shape getStickyShape();

}
