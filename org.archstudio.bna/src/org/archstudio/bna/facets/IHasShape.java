package org.archstudio.bna.facets;

import java.awt.Shape;
import java.awt.geom.Path2D;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.CloneThingKey;

import com.google.common.base.Function;

public interface IHasShape extends IThing {

	public static final IThingKey<Shape> SHAPE_KEY = CloneThingKey.create("shape", new Function<Shape, Shape>() {
		@Override
		public Shape apply(Shape input) {
			return input != null ? new Path2D.Double(input) : null;
		}
	});

	public Path2D getShape();
}
