package org.archstudio.bna.things.graphs;

import static com.google.common.base.Preconditions.checkPositionIndex;

import java.util.Arrays;

import org.archstudio.bna.keys.ThingKey;
import org.archstudio.swtutils.constants.LineStyle;
import org.archstudio.sysutils.CloneableObject;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class NumericSurfaceGraphThing extends NumericSurfaceGraphThingBase {

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
					clone.colors[i] = ThingKey.rgb().apply(colors[i]);
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

	public NumericSurfaceGraphThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		NumericAxis axis = new NumericAxis();
		axis.unit = 1;
		axis.lineStyle = LineStyle.DOT;
		initProperty(X_MINOR_AXIS_KEY, axis);
		initProperty(Y_MINOR_AXIS_KEY, axis);
		initProperty(Z_MINOR_AXIS_KEY, axis);
	}

	@Override
	public boolean shouldIncrementRotatingOffset() {
		return isSelected();
	}

	@Override
	public Point getReferencePoint() {
		Rectangle r = getBoundingBox();
		return new Point(r.x + r.width / 2, r.y + r.height / 2);
	}

	@Override
	public void setReferencePoint(Point value) {
		Point oldReferencePoint = getReferencePoint();
		Rectangle r = getBoundingBox();
		r.x += value.x - oldReferencePoint.x;
		r.y += value.y - oldReferencePoint.y;
		setRawBoundingBox(r);
	}

}
