package org.archstudio.bna.things.graphs;

import static com.google.common.base.Preconditions.checkPositionIndex;

import java.util.Arrays;

import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.keys.CloneThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.things.AbstractRectangleThing;
import org.archstudio.swtutils.constants.LineStyle;
import org.archstudio.sysutils.CloneableObject;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.RGB;

import com.google.common.collect.Lists;

public class NumericSurfaceGraphThing extends AbstractRectangleThing implements IHasMutableRotatingOffset,
		IHasMutableSelected {

	final public static class Data implements CloneableObject {
		private int width = 0;
		private int height = 0;
		private double[] values = new double[0];
		private RGB[] colors = new RGB[0];

		synchronized public int getWidth() {
			return width;
		}

		synchronized public int getHeight() {
			return height;
		}

		synchronized public void resize(int width, int height) {
			double[] values = new double[width * height];
			for (int i = 0; i < values.length; i++) {
				values[i] = 0;
			}
			RGB[] colors = new RGB[width * height];
			for (int i = 0; i < colors.length; i++) {
				colors[i] = new RGB(128, 128, 128);
			}
			for (int x = 0; x < Math.min(width, this.width); x++) {
				for (int y = 0; y < Math.min(height, this.height); y++) {
					values[y * width + x] = this.values[y * this.width + x];
					colors[y * width + x] = this.colors[y * this.width + x];
				}
			}
			this.width = width;
			this.height = height;
			this.values = values;
			this.colors = colors;

		}

		public void flipXY() {
			double[] values = new double[width * height];
			RGB[] colors = new RGB[width * height];
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					values[x * height + y] = this.values[y * width + x];
					colors[x * height + y] = this.colors[y * width + x];
				}
			}
			int temp = width;
			this.width = height;
			this.height = temp;
			this.values = values;
			this.colors = colors;
		}

		synchronized public double getValue(int x, int y) {
			checkPositionIndex(x, width - 1);
			checkPositionIndex(y, height - 1);
			return values[y * width + x];
		}

		synchronized public void setValue(int x, int y, double value) {
			checkPositionIndex(x, width - 1);
			checkPositionIndex(y, height - 1);
			values[y * width + x] = value;
		}

		synchronized public RGB getColor(int x, int y) {
			checkPositionIndex(x, width - 1);
			checkPositionIndex(y, height - 1);
			return colors[y * width + x];
		}

		synchronized public void setColor(int x, int y, RGB color) {
			checkPositionIndex(x, width - 1);
			checkPositionIndex(y, height - 1);
			colors[y * width + x] = color;
		}

		@Override
		synchronized public Object clone() {
			try {
				Data clone = (Data) super.clone();
				clone.values = values.clone();
				clone.colors = new RGB[colors.length];
				for (int i = 0; i < colors.length; i++) {
					clone.colors[i] = CloneThingKey.rgb().apply(colors[i]);
				}
				return clone;
			}
			catch (CloneNotSupportedException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(colors);
			result = prime * result + height;
			result = prime * result + Arrays.hashCode(values);
			result = prime * result + width;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Data other = (Data) obj;
			if (!Arrays.equals(colors, other.colors)) {
				return false;
			}
			if (height != other.height) {
				return false;
			}
			if (!Arrays.equals(values, other.values)) {
				return false;
			}
			if (width != other.width) {
				return false;
			}
			return true;
		}

	}

	public static final IThingKey<Data> DATA_KEY = CloneThingKey.create(
			Lists.newArrayList("data", NumericSurfaceGraphThing.class), CloneThingKey.<Data> cloneable());
	public static final IThingKey<Boolean> FLIP_DATA_KEY = ThingKey.create(Lists.newArrayList("flip-data",
			NumericSurfaceGraphThing.class));
	public static final IThingKey<NumericAxis> X_MAJOR_AXIS_KEY = CloneThingKey
			.create(Lists.newArrayList("x-major-axis", NumericSurfaceGraphThing.class),
					CloneThingKey.<NumericAxis> cloneable());
	public static final IThingKey<NumericAxis> X_MINOR_AXIS_KEY = CloneThingKey
			.create(Lists.newArrayList("x-minor-axis", NumericSurfaceGraphThing.class),
					CloneThingKey.<NumericAxis> cloneable());
	public static final IThingKey<NumericAxis> Y_MAJOR_AXIS_KEY = CloneThingKey
			.create(Lists.newArrayList("y-major-axis", NumericSurfaceGraphThing.class),
					CloneThingKey.<NumericAxis> cloneable());
	public static final IThingKey<NumericAxis> Y_MINOR_AXIS_KEY = CloneThingKey
			.create(Lists.newArrayList("y-minor-axis", NumericSurfaceGraphThing.class),
					CloneThingKey.<NumericAxis> cloneable());
	public static final IThingKey<NumericAxis> Z_MAJOR_AXIS_KEY = CloneThingKey
			.create(Lists.newArrayList("z-major-axis", NumericSurfaceGraphThing.class),
					CloneThingKey.<NumericAxis> cloneable());
	public static final IThingKey<NumericAxis> Z_MINOR_AXIS_KEY = CloneThingKey
			.create(Lists.newArrayList("z-minor-axis", NumericSurfaceGraphThing.class),
					CloneThingKey.<NumericAxis> cloneable());
	public static final IThingKey<Integer> X_ROTATION_KEY = ThingKey.create(Lists.newArrayList("x-rotation",
			NumericSurfaceGraphThing.class));
	public static final IThingKey<Integer> Y_ROTATION_KEY = ThingKey.create(Lists.newArrayList("y-rotation",
			NumericSurfaceGraphThing.class));
	public static final IThingKey<Double> GRID_ALPHA = ThingKey.create(Lists.newArrayList("grid-alpha",
			NumericSurfaceGraphThing.class));
	public static final IThingKey<Double> MAJOR_CONTOUR_ALPHA = ThingKey.create(Lists.newArrayList(
			"major-contour-alpha", NumericSurfaceGraphThing.class));
	public static final IThingKey<Double> MINOR_CONTOUR_ALPHA = ThingKey.create(Lists.newArrayList(
			"minor-contour-alpha", NumericSurfaceGraphThing.class));

	public NumericSurfaceGraphThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setData(new Data());
		setFlipData(false);
		setSelected(false);
		NumericAxis axis = new NumericAxis();
		setXMajorAxis(axis);
		setYMajorAxis(axis);
		setZMajorAxis(axis);
		axis.unit = 1;
		axis.lineStyle = LineStyle.DOT;
		setXMinorAxis(axis);
		setYMinorAxis(axis);
		setZMinorAxis(axis);
		setXRotation(30);
		setYRotation(20);
		setGridAlpha(0.25);
		setMajorContourAlpha(0);
		setMinorContourAlpha(0);
		set(ROTATING_OFFSET_KEY, 0);
		super.initProperties();
	}

	public Data getData() {
		return get(DATA_KEY);
	}

	public void setData(Data value) {
		set(DATA_KEY, value);
	}

	public boolean getFlipData() {
		return get(FLIP_DATA_KEY);
	}

	public void setFlipData(boolean flipData) {
		set(FLIP_DATA_KEY, flipData);
	}

	@Override
	public int getRotatingOffset() {
		return get(ROTATING_OFFSET_KEY);
	}

	@Override
	public boolean shouldIncrementRotatingOffset() {
		return isSelected();
	}

	@Override
	public void incrementRotatingOffset() {
		set(ROTATING_OFFSET_KEY, get(ROTATING_OFFSET_KEY) + 1);
	}

	@Override
	public boolean isSelected() {
		return has(SELECTED_KEY, true);
	}

	@Override
	public void setSelected(boolean selected) {
		set(SELECTED_KEY, selected);
	}

	public NumericAxis getXMajorAxis() {
		return get(X_MAJOR_AXIS_KEY);
	}

	public void setXMajorAxis(NumericAxis axis) {
		set(X_MAJOR_AXIS_KEY, axis);
	}

	public NumericAxis getXMinorAxis() {
		return get(X_MINOR_AXIS_KEY);
	}

	public void setXMinorAxis(NumericAxis axis) {
		set(X_MINOR_AXIS_KEY, axis);
	}

	public NumericAxis getYMajorAxis() {
		return get(Y_MAJOR_AXIS_KEY);
	}

	public void setYMajorAxis(NumericAxis axis) {
		set(Y_MAJOR_AXIS_KEY, axis);
	}

	public NumericAxis getYMinorAxis() {
		return get(Y_MINOR_AXIS_KEY);
	}

	public void setYMinorAxis(NumericAxis axis) {
		set(Y_MINOR_AXIS_KEY, axis);
	}

	public NumericAxis getZMajorAxis() {
		return get(Z_MAJOR_AXIS_KEY);
	}

	public void setZMajorAxis(NumericAxis axis) {
		set(Z_MAJOR_AXIS_KEY, axis);
	}

	public NumericAxis getZMinorAxis() {
		return get(Z_MINOR_AXIS_KEY);
	}

	public void setZMinorAxis(NumericAxis axis) {
		set(Z_MINOR_AXIS_KEY, axis);
	}

	public int getXRotation() {
		return get(X_ROTATION_KEY);
	}

	public void setXRotation(int rotation) {
		set(X_ROTATION_KEY, rotation);
	}

	public int getYRotation() {
		return get(Y_ROTATION_KEY);
	}

	public void setYRotation(int rotation) {
		set(Y_ROTATION_KEY, rotation);
	}

	public double getGridAlpha() {
		return get(GRID_ALPHA);
	}

	public void setGridAlpha(double alpha) {
		set(GRID_ALPHA, alpha);
	}

	public double getMajorContourAlpha() {
		return get(MAJOR_CONTOUR_ALPHA);
	}

	public void setMajorContourAlpha(double alpha) {
		set(MAJOR_CONTOUR_ALPHA, alpha);
	}

	public double getMinorContourAlpha() {
		return get(MINOR_CONTOUR_ALPHA);
	}

	public void setMinorContourAlpha(double alpha) {
		set(MINOR_CONTOUR_ALPHA, alpha);
	}

}
