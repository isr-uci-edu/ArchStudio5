package org.archstudio.bna.keys;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.archstudio.bna.IThing;
import org.archstudio.sysutils.CloneableObject;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Captures a key that stores a value in an {@link IThing}.
 * 
 * @param <V>
 *            The type of value stored by the key, e.g., a Rectangle, RGB, an Integer, etc.
 */
@NonNullByDefault
public class ThingKey<V> extends AbstractThingKey<V> {

	public static final <V> IThingKey<V> create(Object id) {
		return create(id, null, false);
	}

	public static final <V> IThingKey<V> create(Object id, boolean nullable) {
		return identity(new ThingKey<V>(id, null, nullable));
	}

	public static final <V> IThingKey<V> create(Object id, Function<V, V> cloneFunction) {
		return identity(new ThingKey<V>(id, cloneFunction, false));
	}

	public static final <V> IThingKey<V> create(Object id, Function<V, V> cloneFunction, boolean nullable) {
		return identity(new ThingKey<V>(id, cloneFunction, nullable));
	}

	private static final Function<CloneableObject, CloneableObject> CLONEABLE = new Function<CloneableObject, CloneableObject>() {

		@Override
		public CloneableObject apply(CloneableObject input) {
			return (CloneableObject) input.clone();
		}
	};

	@SuppressWarnings("unchecked")
	public static final <T extends CloneableObject> Function<T, T> cloneable() {
		return (Function<T, T>) CLONEABLE;
	}

	private static final Function<Dimension, Dimension> CLONE_DIMENSION = new Function<Dimension, Dimension>() {

		@Override
		public Dimension apply(Dimension input) {
			return new Dimension(input.width, input.height);
		}
	};

	public static final Function<Dimension, Dimension> dimension() {
		return CLONE_DIMENSION;
	}

	private static final Function<Insets, Insets> CLONE_INSETS = new Function<Insets, Insets>() {

		@Override
		public Insets apply(Insets input) {
			return (Insets) input.clone();
		}
	};

	public static final Function<Insets, Insets> insets() {
		return CLONE_INSETS;
	}

	private static final Function<Point, Point> CLONE_POINT = new Function<Point, Point>() {

		@Override
		public Point apply(Point input) {
			return new Point(input.x, input.y);
		}
	};

	public static final Function<Point, Point> point() {
		return CLONE_POINT;
	}

	private static final Function<Point2D, Point2D> CLONE_POINT2D = new Function<Point2D, Point2D>() {

		@Override
		public Point2D apply(Point2D input) {
			return (Point2D) input.clone();
		}
	};

	public static final Function<Point2D, Point2D> point2D() {
		return CLONE_POINT2D;
	}

	private static final Function<Rectangle, Rectangle> CLONE_RECTANGLE = new Function<Rectangle, Rectangle>() {

		@Override
		public Rectangle apply(Rectangle input) {
			return new Rectangle(input.x, input.y, input.width, input.height);
		}
	};

	public static final Function<Rectangle, Rectangle> rectangle() {
		return CLONE_RECTANGLE;
	}

	private static final Function<RGB, RGB> CLONE_RGB = new Function<RGB, RGB>() {

		@Override
		public RGB apply(RGB input) {
			return new RGB(input.red, input.green, input.blue);
		}
	};

	public static final Function<RGB, RGB> rgb() {
		return CLONE_RGB;
	}

	private static final Map<Class<?>, Function<? extends Shape, ? extends Shape>> shapeCloneFunctions = new HashMap<>();
	static {
		shapeCloneFunctions.put(Area.class, new Function<Area, Area>() {
			@Override
			public Area apply(Area input) {
				return (Area) input.clone();
			}
		});
		shapeCloneFunctions.put(CubicCurve2D.class, new Function<CubicCurve2D, CubicCurve2D>() {
			@Override
			public CubicCurve2D apply(CubicCurve2D input) {
				return (CubicCurve2D) input.clone();
			}
		});
		shapeCloneFunctions.put(Line2D.class, new Function<Line2D, Line2D>() {
			@Override
			public Line2D apply(Line2D input) {
				return (Line2D) input.clone();
			}
		});
		shapeCloneFunctions.put(Path2D.class, new Function<Path2D, Path2D>() {
			@Override
			public Path2D apply(Path2D input) {
				return (Path2D) input.clone();
			}
		});
		shapeCloneFunctions.put(Polygon.class, new Function<Polygon, Polygon>() {
			@Override
			public Polygon apply(Polygon input) {
				return new Polygon(Arrays.copyOf(input.xpoints, input.npoints), Arrays.copyOf(input.ypoints,
						input.npoints), input.npoints);
			}
		});
		shapeCloneFunctions.put(QuadCurve2D.class, new Function<QuadCurve2D, QuadCurve2D>() {
			@Override
			public QuadCurve2D apply(QuadCurve2D input) {
				return (QuadCurve2D) input.clone();
			}
		});
		shapeCloneFunctions.put(java.awt.Rectangle.class, new Function<java.awt.Rectangle, java.awt.Rectangle>() {
			@Override
			public java.awt.Rectangle apply(java.awt.Rectangle input) {
				return (java.awt.Rectangle) input.clone();
			}
		});
		shapeCloneFunctions.put(RectangularShape.class, new Function<RectangularShape, RectangularShape>() {
			@Override
			public RectangularShape apply(RectangularShape input) {
				return (RectangularShape) input.clone();
			}
		});
		for (Class<?> c : new Class<?>[] { Arc2D.class, Ellipse2D.class, Rectangle2D.class, RoundRectangle2D.class }) {
			shapeCloneFunctions.put(c, shapeCloneFunctions.get(RectangularShape.class));
		}
		for (String suffix : new String[] { "$Float", ".Float", "$Double", ".Double" }) {
			for (Class<?> c : Lists.newArrayList(shapeCloneFunctions.keySet())) {
				try {
					shapeCloneFunctions.put(Class.forName(c.getName() + suffix), shapeCloneFunctions.get(c));
				}
				catch (ClassNotFoundException e) {
				}
			}
		}
	}

	private static final Function<Shape, Shape> CLONE_SHAPE = new Function<Shape, Shape>() {
		@SuppressWarnings("unchecked")
		@Override
		public Shape apply(Shape input) {
			Function<? extends Shape, ? extends Shape> cloneFunction = shapeCloneFunctions.get(input.getClass());
			if (cloneFunction == null) {
				throw new IllegalArgumentException("Cannot clone " + input.getClass());
			}
			return ((Function<Shape, Shape>) cloneFunction).apply(input);
		}
	};

	public static final Function<Shape, Shape> shape() {
		return CLONE_SHAPE;
	}

	public static final <V> Function<List<V>, List<V>> list(final Function<V, V> cloneFunction) {
		return new Function<List<V>, List<V>>() {

			@Override
			public List<V> apply(List<V> input) {
				return Lists.newArrayList(cloneFunction == null ? input : Collections2.transform(input, cloneFunction));
			}
		};
	}

	public static final <V> Function<Set<V>, Set<V>> set(final Function<V, V> cloneFunction) {
		return new Function<Set<V>, Set<V>>() {

			@Override
			public Set<V> apply(Set<V> input) {
				return Sets.newHashSet(cloneFunction == null ? input : Collections2.transform(input, cloneFunction));
			}
		};
	}

	protected ThingKey(Object id, @Nullable Function<V, V> cloneFunction, boolean nullable) {
		super(id, cloneFunction, nullable);
	}

}
