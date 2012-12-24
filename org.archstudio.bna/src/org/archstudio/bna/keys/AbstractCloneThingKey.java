package org.archstudio.bna.keys;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.geom.Point2D;

import org.archstudio.bna.IThing.IThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.base.Function;
import com.google.common.base.Functions;

@NonNullByDefault
public abstract class AbstractCloneThingKey<D, V> extends AbstractGenericThingKey<D, V> {

	public static final Function<Dimension, Dimension> dimension() {
		return new Function<Dimension, Dimension>() {

			public @Nullable
			Dimension apply(@Nullable Dimension input) {
				return input != null ? new Dimension(input.width, input.height) : null;
			}
		};
	}

	public static final Function<Insets, Insets> insets() {
		return new Function<Insets, Insets>() {

			public @Nullable
			Insets apply(@Nullable Insets input) {
				return input != null ? new Insets(input.top, input.left, input.bottom, input.right) : null;
			}
		};
	}

	public static final Function<Point, Point> point() {
		return new Function<Point, Point>() {

			public @Nullable
			Point apply(@Nullable Point input) {
				return input != null ? new Point(input.x, input.y) : null;
			}
		};
	}

	public static final Function<Point2D, Point2D> point2D() {
		return new Function<Point2D, Point2D>() {

			public @Nullable
			Point2D apply(@Nullable Point2D input) {
				return input != null ? (Point2D) input.clone() : null;
			}
		};
	}

	public static final Function<Rectangle, Rectangle> rectangle() {
		return new Function<Rectangle, Rectangle>() {

			public @Nullable
			Rectangle apply(@Nullable Rectangle input) {
				return input != null ? new Rectangle(input.x, input.y, input.width, input.height) : null;
			}
		};
	}

	public static final Function<RGB, RGB> rgb() {
		return new Function<RGB, RGB>() {

			public @Nullable
			RGB apply(@Nullable RGB input) {
				return input != null ? new RGB(input.red, input.green, input.blue) : null;
			}
		};
	}

	public static final Function<IThingKey<?>, IThingKey<?>> iThingKey() {
		return Functions.identity();
	}

	protected final Function<? super V, V> cloneFunction;

	protected AbstractCloneThingKey(D keyData, boolean isFireEventOnChange, Function<? super V, V> cloneFunction) {
		super(keyData, isFireEventOnChange);
		this.cloneFunction = cloneFunction;
	}

	public final @Nullable
	V preWrite(@Nullable V value) {
		return cloneFunction.apply(value);
	}

	public final @Nullable
	V postRead(@Nullable V value) {
		return cloneFunction.apply(value);
	}
}
