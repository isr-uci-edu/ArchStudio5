package org.archstudio.bna.utils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.PathData;

public class PathDataConstructor {

	private int typesLength = 0;
	private byte[] types = new byte[50];

	private int pointsLength = 0;
	private float[] points = new float[100];

	private void addType(byte value) {
		if (typesLength >= types.length) {
			byte[] newTypes = new byte[(types.length + 1) * 2];
			System.arraycopy(types, 0, newTypes, 0, typesLength);
			types = newTypes;
		}
		types[typesLength++] = value;
	}

	private void addPoint(float value) {
		if (pointsLength >= points.length) {
			float[] newPoints = new float[(points.length + 2) * 2];
			System.arraycopy(points, 0, newPoints, 0, pointsLength);
			points = newPoints;
		}
		points[pointsLength++] = value;
	}

	public void close() {
		addType((byte) SWT.PATH_CLOSE);
	}

	public void lineTo(float x, float y) {
		addType((byte) SWT.PATH_LINE_TO);
		addPoint(x);
		addPoint(y);
	}

	public void moveTo(float x, float y) {
		addType((byte) SWT.PATH_MOVE_TO);
		addPoint(x);
		addPoint(y);
	}

	public void scale(float sx, float sy) {
		for (int i = 0; i < pointsLength; i += 2) {
			points[i] *= sx;
			points[i + 1] *= sy;
		}
	}

	public void translate(float ox, float oy) {
		for (int i = 0; i < pointsLength; i += 2) {
			points[i] += ox;
			points[i + 1] += oy;
		}
	}

	public PathData getPathData() {
		PathData pathData = new PathData();
		pathData.types = new byte[typesLength];
		System.arraycopy(types, 0, pathData.types, 0, typesLength);
		pathData.points = new float[pointsLength];
		System.arraycopy(points, 0, pathData.points, 0, pointsLength);
		return pathData;
	}
}
