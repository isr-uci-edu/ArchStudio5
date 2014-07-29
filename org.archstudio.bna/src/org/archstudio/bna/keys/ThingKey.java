package org.archstudio.bna.keys;

import static com.google.common.base.Preconditions.checkNotNull;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Polygon;
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
import java.util.List;
import java.util.Set;

import org.archstudio.bna.IThing;
import org.archstudio.sysutils.CloneableObject;
import org.archstudio.sysutils.FastMap;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.base.Function;
import com.google.common.base.Functions;
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

	@SuppressWarnings("unchecked")
	public static final <V> IThingKey<V> create(Object id) {
		return create(id, (Function<V, V>) any(), false);
	}

	@SuppressWarnings("unchecked")
	public static final <V> IThingKey<V> create(Object id, boolean nullable) {
		return identity(new ThingKey<V>(id, (Function<V, V>) any(), nullable));
	}

	public static final <V> IThingKey<V> create(Object id, Function<V, V> cloneFunction) {
		return identity(new ThingKey<V>(id, cloneFunction, false));
	}

	public static final <V> IThingKey<V> create(Object id, Function<V, V> cloneFunction, boolean nullable) {
		return identity(new ThingKey<V>(id, cloneFunction, nullable));
	}

	private static Function<?, ?> CLONE_FUNCTION = new Function<Object, Object>() {
		@SuppressWarnings("unchecked")
		@Override
		public Object apply(Object input) {
			if (input == null) {
				return null;
			}
			Class<?> inputClass = input.getClass();
			Function<Object, Object> fn = (Function<Object, Object>) CLONE_FUNCTIONS.get(inputClass);
			if (fn != null) {
				return fn.apply(input);
			}
			for (Class<?> iface : inputClass.getInterfaces()) {
				fn = (Function<Object, Object>) CLONE_FUNCTIONS.get(iface);
				if (fn != null) {
					CLONE_FUNCTIONS.put(iface, fn);
					return fn.apply(input);
				}
			}
			CLONE_FUNCTIONS.put(inputClass, Functions.identity());
			return input;
		}
	};

	/* package */static FastMap<Class<?>, Function<?, ?>> CLONE_FUNCTIONS = new FastMap<>(true);
	static {
		CLONE_FUNCTIONS.put(List.class, list(any()));
		CLONE_FUNCTIONS.put(Set.class, set(any()));
		CLONE_FUNCTIONS.put(CloneableObject.class, new Function<CloneableObject, CloneableObject>() {
			@Override
			public CloneableObject apply(CloneableObject input) {
				return (CloneableObject) input.clone();
			}
		});
		CLONE_FUNCTIONS.put(Dimension.class, new Function<Dimension, Dimension>() {
			@Override
			public Dimension apply(Dimension input) {
				return new Dimension(input.width, input.height);
			}
		});
		CLONE_FUNCTIONS.put(Insets.class, new Function<Insets, Insets>() {
			@Override
			public Insets apply(Insets input) {
				return (Insets) input.clone();
			}
		});
		CLONE_FUNCTIONS.put(Point.class, new Function<Point, Point>() {
			@Override
			public Point apply(Point input) {
				return new Point(input.x, input.y);
			}
		});
		CLONE_FUNCTIONS.put(Point2D.class, new Function<Point2D, Point2D>() {
			@Override
			public Point2D apply(Point2D input) {
				return (Point2D) input.clone();
			}
		});
		CLONE_FUNCTIONS.put(Rectangle.class, new Function<Rectangle, Rectangle>() {
			@Override
			public Rectangle apply(Rectangle input) {
				return new Rectangle(input.x, input.y, input.width, input.height);
			}
		});
		CLONE_FUNCTIONS.put(RGB.class, new Function<RGB, RGB>() {
			@Override
			public RGB apply(RGB input) {
				return new RGB(input.red, input.green, input.blue);
			}
		});
		CLONE_FUNCTIONS.put(Area.class, new Function<Area, Area>() {
			@Override
			public Area apply(Area input) {
				return (Area) input.clone();
			}
		});
		CLONE_FUNCTIONS.put(CubicCurve2D.class, new Function<CubicCurve2D, CubicCurve2D>() {
			@Override
			public CubicCurve2D apply(CubicCurve2D input) {
				return (CubicCurve2D) input.clone();
			}
		});
		CLONE_FUNCTIONS.put(Line2D.class, new Function<Line2D, Line2D>() {
			@Override
			public Line2D apply(Line2D input) {
				return (Line2D) input.clone();
			}
		});
		CLONE_FUNCTIONS.put(Path2D.class, new Function<Path2D, Path2D>() {
			@Override
			public Path2D apply(Path2D input) {
				return (Path2D) input.clone();
			}
		});
		CLONE_FUNCTIONS.put(Polygon.class, new Function<Polygon, Polygon>() {
			@Override
			public Polygon apply(Polygon input) {
				return new Polygon(Arrays.copyOf(input.xpoints, input.npoints), Arrays.copyOf(input.ypoints,
						input.npoints), input.npoints);
			}
		});
		CLONE_FUNCTIONS.put(QuadCurve2D.class, new Function<QuadCurve2D, QuadCurve2D>() {
			@Override
			public QuadCurve2D apply(QuadCurve2D input) {
				return (QuadCurve2D) input.clone();
			}
		});
		CLONE_FUNCTIONS.put(java.awt.Rectangle.class, new Function<java.awt.Rectangle, java.awt.Rectangle>() {
			@Override
			public java.awt.Rectangle apply(java.awt.Rectangle input) {
				return (java.awt.Rectangle) input.clone();
			}
		});
		CLONE_FUNCTIONS.put(RectangularShape.class, new Function<RectangularShape, RectangularShape>() {
			@Override
			public RectangularShape apply(RectangularShape input) {
				return (RectangularShape) input.clone();
			}
		});
		for (Class<?> c : new Class<?>[] { Arc2D.class, Ellipse2D.class, Rectangle2D.class, RoundRectangle2D.class }) {
			CLONE_FUNCTIONS.put(c, CLONE_FUNCTIONS.get(RectangularShape.class));
		}
		for (String suffix : new String[] { "$Float", ".Float", "$Double", ".Double" }) {
			for (Class<?> c : Lists.newArrayList(CLONE_FUNCTIONS.keySet())) {
				try {
					CLONE_FUNCTIONS.put(Class.forName(c.getName() + suffix), CLONE_FUNCTIONS.get(c));
				}
				catch (ClassNotFoundException e) {
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static final <V> Function<V, V> any() {
		return (Function<V, V>) CLONE_FUNCTION;
	}

	@SuppressWarnings("unchecked")
	public static final <V> Function<List<V>, List<V>> list(Function<V, V> cloneFunction) {
		// any() will be null if CLONE_FUNCTION is not yet initialized
		final Function<V, V> finalCloneFunction = cloneFunction != null ? cloneFunction
				: (Function<V, V>) checkNotNull(any());
		return new Function<List<V>, List<V>>() {
			@Override
			public List<V> apply(List<V> input) {
				List<V> list = Lists.newArrayListWithExpectedSize(input.size());
				list.addAll(Collections2.transform(input, finalCloneFunction));
				return list;
			}
		};
	}

	@SuppressWarnings("unchecked")
	public static final <V> Function<Set<V>, Set<V>> set(Function<V, V> cloneFunction) {
		// any() will be null if CLONE_FUNCTION is not yet initialized
		final Function<V, V> finalCloneFunction = cloneFunction != null ? cloneFunction
				: (Function<V, V>) checkNotNull(any());
		return new Function<Set<V>, Set<V>>() {
			@Override
			public Set<V> apply(Set<V> input) {
				Set<V> set = Sets.newHashSetWithExpectedSize(input.size());
				set.addAll(Collections2.transform(input, finalCloneFunction));
				return set;
			}
		};
	}

	protected ThingKey(Object id, @Nullable Function<V, V> cloneFunction, boolean nullable) {
		super(id, cloneFunction, nullable);
	}

}
