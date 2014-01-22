package org.archstudio.bna.things.graphs;

import static com.google.common.base.Preconditions.checkArgument;

import java.text.DecimalFormat;
import java.util.Iterator;

import org.archstudio.swtutils.constants.LineStyle;
import org.archstudio.sysutils.CloneableObject;
import org.archstudio.sysutils.SystemUtils;

import com.google.common.base.Function;
import com.google.common.collect.AbstractIterator;

public class NumericAxis implements CloneableObject {

	public static enum UnitType {
		LINEAR(true, false), LOGARITHMIC(false, true), LOGARITHMIC_SUBDIVIDED(false, true);

		private final boolean linear;
		private final boolean logarithmic;

		private UnitType(boolean linear, boolean logarithmic) {
			this.linear = linear;
			this.logarithmic = logarithmic;
		}

		public boolean isLinear() {
			return linear;
		}

		public boolean isLogarithmic() {
			return logarithmic;
		}
	}

	public class Range {
		public final double min;
		public final double max;

		private Range(double min, double max) {
			this.min = min;
			this.max = max;
		}

		@Override
		public String toString() {
			return "[" + min + "," + max + "]";
		}
	}

	public static class Value {
		public final double actualValue;
		public final double linearOffset;

		public Value(double actualValue, double linearOffset) {
			this.actualValue = actualValue;
			this.linearOffset = linearOffset;
		}

		@Override
		public String toString() {
			return actualValue + "(" + linearOffset + ")";
		}
	}

	public UnitType type = UnitType.LINEAR;
	public double min = Double.NaN; // NaN indicates automatic
	public double max = Double.NaN; // NaN indicates automatic
	public double unit = 10;
	public Function<Double, String> formatter = new Function<Double, String>() {
		DecimalFormat formatter = new DecimalFormat("###,###.###");

		@Override
		public String apply(Double input) {
			return formatter.format(input);
		}
	};

	public LineStyle lineStyle = LineStyle.SOLID;
	public int angle = 0;
	public boolean reverse = false;

	public NumericAxis() {
		super();
	}

	@Override
	public Object clone() {
		try {
			NumericAxis clone = (NumericAxis) super.clone();
			return clone;
		}
		catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	public Value toValue(double value, Range range) {
		checkArgument(range.min <= value && value <= range.max, "value (%s) must be within range (%s)", value, range);
		switch (type) {
		case LINEAR:
			if (!reverse) {
				return new Value(value, value - range.min);
			}
			else {
				return new Value(value, range.max - value);
			}
		case LOGARITHMIC:
		case LOGARITHMIC_SUBDIVIDED:
			checkArgument(value > 0, "value (%s) must be > 0", value);
			checkArgument(unit > 1, "unit (%s) must be > 1", unit);
			if (!reverse) {
				return new Value(value, (Math.log(value) - Math.log(range.min)) / Math.log(unit) * unit);
			}
			else {
				return new Value(value, (Math.log(range.max) - Math.log(value)) / Math.log(unit) * unit);
			}
		}
		throw new IllegalArgumentException(type.toString());
	}

	public Range getAxisRange(double minValue, double maxValue) {
		checkArgument(minValue <= maxValue, "min value (%s) must be <= max value (%s)", minValue, maxValue);
		switch (type) {
		case LINEAR:
			checkArgument(unit > 0, "unit (%s) must be > 0", unit);
			break;
		case LOGARITHMIC:
		case LOGARITHMIC_SUBDIVIDED:
			checkArgument(unit > 1, "unit (%s) must be > 1", unit);
			break;
		}
		double min = this.min;
		if (Double.isNaN(min)) {
			switch (type) {
			case LINEAR:
				min = Math.floor(minValue / unit) * unit;
				break;
			case LOGARITHMIC:
			case LOGARITHMIC_SUBDIVIDED:
				checkArgument(minValue > 0, "min (%s) must be positive", minValue);
				min = Math.pow(unit, Math.floor(Math.log(minValue) / Math.log(unit)));
				break;
			}
		}
		double max = this.max;
		if (Double.isNaN(max)) {
			switch (type) {
			case LINEAR:
				max = Math.ceil(maxValue / unit) * unit;
				break;
			case LOGARITHMIC:
			case LOGARITHMIC_SUBDIVIDED:
				checkArgument(maxValue > 0, "max (%s) must be positive", maxValue);
				max = Math.pow(unit, Math.ceil(Math.log(maxValue) / Math.log(unit)));
				break;
			}
		}
		checkArgument(min <= max, "min value (%s) must be <= max value (%s)", min, max);
		return new Range(min, max);
	}

	public Iterable<Value> getAxisValues(final Range range) {
		final double unit = this.unit;
		checkArgument(range.min <= range.max, "min value (%s) must be <= max value (%s)", range.min, range.max);
		switch (type) {
		case LINEAR: {
			checkArgument(unit > 0, "unit (%s) must be greater than 0", unit);
			return new Iterable<Value>() {
				@Override
				public Iterator<Value> iterator() {
					return new AbstractIterator<Value>() {

						long offset = 0;
						long count = SystemUtils.round(Math.floor((range.max - range.min) / unit));

						@Override
						protected Value computeNext() {
							if (offset > count) {
								return endOfData();
							}
							return toValue(range.min + offset++ * unit, range);
						}
					};
				}
			};
		}
		case LOGARITHMIC: {
			checkArgument(unit > 1, "unit (%s) must be > 1", unit);
			checkArgument(range.min > 0, "min (%s) must be > 0", range.min);
			checkArgument(range.max > 0, "max (%s) must be > 0", range.max);
			return new Iterable<Value>() {
				@Override
				public Iterator<Value> iterator() {
					return new AbstractIterator<Value>() {

						long offset = 0;
						long count = SystemUtils.round(Math.log(range.max / range.min) / Math.log(unit));

						@Override
						protected Value computeNext() {
							if (offset > count) {
								return endOfData();
							}
							return toValue(range.min * Math.pow(unit, offset++), range);
						}
					};
				}
			};
		}
		case LOGARITHMIC_SUBDIVIDED: {
			checkArgument(unit > 1, "unit (%s) must be > 1", unit);
			checkArgument(range.min > 0, "min (%s) must be > 0", range.min);
			checkArgument(range.max > 0, "max (%s) must be > 0", range.max);
			return new Iterable<Value>() {
				@Override
				public Iterator<Value> iterator() {
					return new AbstractIterator<Value>() {

						long offset = 0;
						long count = SystemUtils.round(Math.log(range.max / range.min) / Math.log(unit));
						long suboffset = 1;
						long subcount = SystemUtils.round(Math.floor(unit));

						@Override
						protected Value computeNext() {
							if (offset >= count) {
								return endOfData();
							}
							if (suboffset >= subcount) {
								suboffset = 1;
								offset++;
							}
							return toValue(range.min * Math.pow(unit, offset + Math.log(suboffset++) / Math.log(unit)),
									range);
						}
					};
				}
			};
		}
		}
		throw new IllegalArgumentException(type.toString());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + angle;
		result = prime * result + (formatter == null ? 0 : formatter.hashCode());
		result = prime * result + (lineStyle == null ? 0 : lineStyle.hashCode());
		long temp;
		temp = Double.doubleToLongBits(max);
		result = prime * result + (int) (temp ^ temp >>> 32);
		temp = Double.doubleToLongBits(min);
		result = prime * result + (int) (temp ^ temp >>> 32);
		result = prime * result + (reverse ? 1231 : 1237);
		result = prime * result + (type == null ? 0 : type.hashCode());
		temp = Double.doubleToLongBits(unit);
		result = prime * result + (int) (temp ^ temp >>> 32);
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
		NumericAxis other = (NumericAxis) obj;
		if (angle != other.angle) {
			return false;
		}
		if (formatter == null) {
			if (other.formatter != null) {
				return false;
			}
		}
		else if (!formatter.equals(other.formatter)) {
			return false;
		}
		if (lineStyle != other.lineStyle) {
			return false;
		}
		if (Double.doubleToLongBits(max) != Double.doubleToLongBits(other.max)) {
			return false;
		}
		if (Double.doubleToLongBits(min) != Double.doubleToLongBits(other.min)) {
			return false;
		}
		if (reverse != other.reverse) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		if (Double.doubleToLongBits(unit) != Double.doubleToLongBits(other.unit)) {
			return false;
		}
		return true;
	}

}
