package org.archstudio.sysutils;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkPositionIndex;

import java.text.DecimalFormat;

public class Matrix {

	private int columns;
	private int rows;
	private double[] values;

	private Matrix(int columns, int rows) {
		this.columns = columns;
		this.rows = rows;
		values = new double[columns * rows];
	}

	public static Matrix newMatrix(int columns, int rows) {
		return new Matrix(columns, rows);
	}

	public static Matrix newRowMajorMatrix(int columns, double... values) {
		checkArgument(values.length % columns == 0, "Incomplete matrix");
		Matrix m = new Matrix(columns, values.length / columns);
		m.values = values.clone();
		return m;
	}

	public static Matrix newColumnMajorMatrix(int rows, double... values) {
		checkArgument(values.length % rows == 0, "Incomplete matrix");
		int columns = values.length / rows;
		Matrix m = new Matrix(columns, rows);
		int i = 0;
		for (int column = 0; column < columns; column++) {
			for (int row = 0; row < rows; row++) {
				m.values[row * columns + column] = values[i++];
			}
		}
		return m;
	}

	public static Matrix newIdentityMatrix(int size) {
		Matrix m = new Matrix(size, size);
		for (int i = 0; i < size; i++) {
			m.set(i, i, 1);
		}
		return m;
	}

	public int getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}

	public void resize(int columns, int rows) {
		double[] values = new double[columns * rows];
		for (int i = 0; i < values.length; i++) {
			values[i] = 0;
		}
		for (int row = 0; row < Math.min(rows, this.rows); row++) {
			for (int column = 0; column < Math.min(columns, this.columns); column++) {
				values[row * columns + column] = this.values[row * this.columns + column];
			}
		}
		this.columns = columns;
		this.rows = rows;
		this.values = values;
	}

	public double get(int column, int row) {
		checkPositionIndex(column, columns - 1);
		checkPositionIndex(row, rows - 1);
		return values[row * columns + column];
	}

	public void set(int column, int row, double value) {
		checkPositionIndex(column, columns - 1);
		checkPositionIndex(row, rows - 1);
		values[row * columns + column] = value;
	}

	public Matrix product(Matrix other) {
		checkArgument(columns == other.rows, "Cannot multiply %sx%s with %sx%s", rows, columns, other.rows,
				other.columns);

		Matrix result = new Matrix(other.columns, rows);
		for (int row = 0; row < result.rows; row++) {
			for (int column = 0; column < result.columns; column++) {
				double value = 0;
				for (int i = 0; i < columns; i++) {
					value += values[row * columns + i] * other.values[i * other.columns + column];
				}
				result.values[row * result.columns + column] = value;
			}
		}
		return result;
	}

	private static final DecimalFormat format = new DecimalFormat(" #,##0.000;-#,##0.000");
	private static final String EOL = System.getProperty("line.separator");

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int row = 0; row < rows; row++) {
			if (row != 0) {
				sb.append(EOL);
			}
			sb.append("[");
			for (int column = 0; column < columns; column++) {
				if (column != 0) {
					sb.append(", ");
				}
				sb.append(format.format(values[row * columns + column]));
			}
			sb.append("]");
		}
		return sb.toString();
	}
}
