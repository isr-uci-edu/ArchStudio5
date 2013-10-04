package org.archstudio.bna.keys;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Arrays;

import org.archstudio.bna.IThing.IThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.base.Function;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@NonNullByDefault
public class CloneThingKey<D, V> extends AbstractCloneThingKey<D, V> {

	public static final Function<Dimension, Dimension> dimension() {
		return new Function<Dimension, Dimension>() {

			@Override
			public @Nullable
			Dimension apply(@Nullable Dimension input) {
				return input != null ? new Dimension(input.width, input.height) : null;
			}
		};
	}

	public static final Function<Insets, Insets> insets() {
		return new Function<Insets, Insets>() {

			@Override
			public @Nullable
			Insets apply(@Nullable Insets input) {
				return input != null ? new Insets(input.top, input.left, input.bottom, input.right) : null;
			}
		};
	}

	public static final Function<Point, Point> point() {
		return new Function<Point, Point>() {

			@Override
			public @Nullable
			Point apply(@Nullable Point input) {
				return input != null ? new Point(input.x, input.y) : null;
			}
		};
	}

	public static final Function<Point2D, Point2D> point2D() {
		return new Function<Point2D, Point2D>() {

			@Override
			public @Nullable
			Point2D apply(@Nullable Point2D input) {
				return input != null ? (Point2D) input.clone() : null;
			}
		};
	}

	public static final Function<Rectangle, Rectangle> rectangle() {
		return new Function<Rectangle, Rectangle>() {

			@Override
			public @Nullable
			Rectangle apply(@Nullable Rectangle input) {
				return input != null ? new Rectangle(input.x, input.y, input.width, input.height) : null;
			}
		};
	}

	public static final Function<RGB, RGB> rgb() {
		return new Function<RGB, RGB>() {

			@Override
			public @Nullable
			RGB apply(@Nullable RGB input) {
				return input != null ? new RGB(input.red, input.green, input.blue) : null;
			}
		};
	}

	private static final Function<Shape, Shape> defaultShapeCloneFunction = new Function<Shape, Shape>() {
		@Override
		public Shape apply(Shape input) {
			return new Path2D.Double(input);
		};
	};
	private static final LoadingCache<Class<?>, Function<? extends Shape, ? extends Shape>> shapeCloneFunctions = CacheBuilder
			.newBuilder().build(new CacheLoader<Class<?>, Function<? extends Shape, ? extends Shape>>() {
				@Override
				public Function<? extends Shape, ? extends Shape> load(Class<?> key) throws Exception {
					Function<? extends Shape, ? extends Shape> cloneFunction = null;
					while (key != null) {
						cloneFunction = shapeCloneFunctions.getIfPresent(key);
						if (cloneFunction != null) {
							break;
						}
						key = key.getSuperclass();
					}
					if (cloneFunction == null) {
						cloneFunction = defaultShapeCloneFunction;
					}
					return cloneFunction;
				};
			});
	static {
		shapeCloneFunctions.put(Arc2D.class, new Function<Arc2D, Arc2D.Double>() {
			@Override
			public Arc2D.Double apply(Arc2D input) {
				return new Arc2D.Double(input.getX(), input.getY(), input.getWidth(), input.getHeight(), input
						.getAngleStart(), input.getAngleExtent(), input.getArcType());
			}
		});
		shapeCloneFunctions.put(CubicCurve2D.class, new Function<CubicCurve2D, CubicCurve2D.Double>() {
			@Override
			public CubicCurve2D.Double apply(CubicCurve2D input) {
				return new CubicCurve2D.Double(input.getX1(), input.getY1(), input.getCtrlX1(), input.getCtrlY1(),
						input.getCtrlX2(), input.getCtrlY2(), input.getX2(), input.getY2());
			}
		});
		shapeCloneFunctions.put(Ellipse2D.class, new Function<Ellipse2D, Ellipse2D.Double>() {
			@Override
			public Ellipse2D.Double apply(Ellipse2D input) {
				return new Ellipse2D.Double(input.getX(), input.getY(), input.getWidth(), input.getHeight());
			}
		});
		shapeCloneFunctions.put(Line2D.class, new Function<Line2D, Line2D.Double>() {
			@Override
			public Line2D.Double apply(Line2D input) {
				return new Line2D.Double(input.getX1(), input.getY1(), input.getX2(), input.getY2());
			}
		});
		shapeCloneFunctions.put(Polygon.class, new Function<Polygon, Polygon>() {
			@Override
			public Polygon apply(Polygon input) {
				return new Polygon(Arrays.copyOf(input.xpoints, input.npoints), Arrays.copyOf(input.ypoints,
						input.npoints), input.npoints);
			}
		});
		shapeCloneFunctions.put(QuadCurve2D.class, new Function<QuadCurve2D, QuadCurve2D.Double>() {
			@Override
			public QuadCurve2D.Double apply(QuadCurve2D input) {
				return new QuadCurve2D.Double(input.getX1(), input.getY1(), input.getCtrlX(), input.getCtrlY(), input
						.getX2(), input.getY2());
			}
		});
		shapeCloneFunctions.put(Rectangle2D.class, new Function<Rectangle2D, Rectangle2D.Double>() {
			@Override
			public Rectangle2D.Double apply(Rectangle2D input) {
				return new Rectangle2D.Double(input.getX(), input.getY(), input.getWidth(), input.getHeight());
			}
		});
		shapeCloneFunctions.put(RoundRectangle2D.class, new Function<RoundRectangle2D, RoundRectangle2D.Double>() {
			@Override
			public RoundRectangle2D.Double apply(RoundRectangle2D input) {
				return new RoundRectangle2D.Double(input.getX(), input.getY(), input.getWidth(), input.getHeight(),
						input.getArcWidth(), input.getArcHeight());
			}
		});
	}

	public static final Function<Shape, Shape> shape() {
		return new Function<Shape, Shape>() {
			@SuppressWarnings("unchecked")
			@Override
			public @Nullable
			Shape apply(Shape input) {
				return input != null ? ((Function<Shape, Shape>) shapeCloneFunctions.getUnchecked(input.getClass()))
						.apply(input) : null;
			}
		};
	}

	public static final <D, V> IThingKey<V> create(D keyData, Function<V, V> cloneFunction) {
		return new CloneThingKey<D, V>(keyData, true, cloneFunction);
	}

	public static final <D, V> IThingKey<V> create(D keyData, boolean isFireEventOnChange, Function<V, V> cloneFunction) {
		return new CloneThingKey<D, V>(keyData, isFireEventOnChange, cloneFunction);
	}

	public CloneThingKey(D keyData, boolean isFireEventOnChange, Function<V, V> cloneFunction) {
		super(keyData, isFireEventOnChange, cloneFunction);
	}
}
