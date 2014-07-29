package org.archstudio.bna.things.graphs;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.things.graphs.NumericAxis.Range;
import org.archstudio.bna.things.graphs.NumericAxis.Value;
import org.archstudio.bna.things.graphs.NumericSurfaceGraphThing.Data;
import org.archstudio.bna.ui.IUIResources.FontMetrics;
import org.archstudio.bna.ui.jogl.IJOGLResources;
import org.archstudio.bna.ui.swt.ISWTResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.LineStyle;
import org.archstudio.swtutils.constants.VerticalAlignment;
import org.archstudio.sysutils.ExpandableFloatBuffer;
import org.archstudio.sysutils.Matrix;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Lists;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.PMVMatrix;

public class NumericSurfaceGraphThingPeer<T extends NumericSurfaceGraphThing> extends AbstractThingPeer<T> {

	private static final double NUDGE = 0.75;

	private static class Point {
		float rgba[];
		float xyz[];

		public Point(RGB rgb, double x, double y, double z) {
			rgba = new float[] { rgb.red / 255f, rgb.green / 255f, rgb.blue / 255f, 1 };
			xyz = new float[] { (float) x, (float) y, (float) z };
		}

	}

	private static class Line {
		float[] xyz1;
		float[] xyz2;

		public Line(double x1, double y1, double z1, double x2, double y2, double z2) {
			xyz1 = new float[] { (float) x1, (float) y1, (float) z1 };
			xyz2 = new float[] { (float) x2, (float) y2, (float) z2 };
		}

		public Line(Point p1, Point p2) {
			xyz1 = p1.xyz;
			xyz2 = p2.xyz;
		}

	}

	private static class Triangle {
		Point[] p;

		public Triangle(Point p1, Point p2, Point p3) {
			p = new Point[] { p1, p2, p3 };
			Arrays.sort(p, new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					return o1.xyz[2] < o2.xyz[2] ? -1 : o1.xyz[2] > o2.xyz[2] ? 1 : 0;
				}
			});
		}

		public void addContourLines(Collection<Line> lines, double[] zValues) {
			int i = Arrays.binarySearch(zValues, p[0].xyz[2]);
			i = i < 0 ? -i - 1 : i;
			while (i < zValues.length && zValues[i] <= p[1].xyz[2]) {
				double z = zValues[i];
				double x2 = position(p[0].xyz[2], z, p[2].xyz[2], p[0].xyz[0], p[2].xyz[0]);
				double x1 = position(p[0].xyz[2], z, p[1].xyz[2], p[0].xyz[0], p[1].xyz[0]);
				double y2 = position(p[0].xyz[2], z, p[2].xyz[2], p[0].xyz[1], p[2].xyz[1]);
				double y1 = position(p[0].xyz[2], z, p[1].xyz[2], p[0].xyz[1], p[1].xyz[1]);
				lines.add(new Line(x1, y1, z, x2, y2, z));
				i++;
			}
			while (i < zValues.length && zValues[i] <= p[2].xyz[2]) {
				double z = zValues[i];
				double x0 = position(p[0].xyz[2], z, p[2].xyz[2], p[0].xyz[0], p[2].xyz[0]);
				double x1 = position(p[1].xyz[2], z, p[2].xyz[2], p[1].xyz[0], p[2].xyz[0]);
				double y0 = position(p[0].xyz[2], z, p[2].xyz[2], p[0].xyz[1], p[2].xyz[1]);
				double y1 = position(p[1].xyz[2], z, p[2].xyz[2], p[1].xyz[1], p[2].xyz[1]);
				lines.add(new Line(x0, y0, z, x1, y1, z));
				i++;
			}
		}

		private double position(double z0, double z, double z1, double v0, double v1) {
			return (z - z0) / (z1 - z0) * (v1 - v0) + v0;
		}

	}

	private double[] toValues(NumericAxis zAxis, Range zRange) {
		List<NumericAxis.Value> zValuesList = Lists.newArrayList(zAxis.getAxisValues(zRange));
		double[] zValues = new double[zValuesList.size()];
		for (int i = 0; i < zValuesList.size(); i++) {
			zValues[i] = zValuesList.get(i).linearOffset;
		}
		return zValues;
	}

	private Point2D.Double transformXY(Matrix matrix, double x, double y, double z) {
		Matrix xy = matrix.product(Matrix.newRowMajorMatrix(1, x, y, z, 1));
		return new Point2D.Double(xy.get(0, 0), xy.get(0, 1));
	}

	List<Object> cacheValidConditions = Lists.newArrayList();
	Data data;
	Matrix matrix;
	double xyScale, zScale;
	Range xRange, yRange, zRange;
	double originOffsetX, originOffsetY;
	boolean xCrossMin;
	boolean yCrossMin;
	boolean zCrossMin;
	double xCrossOffset, yCrossOffset, zCrossOffset;
	double xLabelCrossOffset, yLabelCrossOffset, zLabelCrossOffset, zxLabelCrossOffset, zyLabelCrossOffset;
	HorizontalAlignment xLabelHorizontalAlignment, yLabelHorizontalAlignment;
	VerticalAlignment xLabelVerticalAlignment, yLabelVerticalAlignment;
	ExpandableFloatBuffer triangleVertices = new ExpandableFloatBuffer();
	ExpandableFloatBuffer triangleColors = new ExpandableFloatBuffer();
	NumericAxis zMajorAxis;
	NumericAxis zMinorAxis;
	double gridLinesAlpha;
	ExpandableFloatBuffer gridVertices = new ExpandableFloatBuffer();
	ExpandableFloatBuffer gridColors = new ExpandableFloatBuffer();
	double minorContourAlpha;
	ExpandableFloatBuffer minorContourVertices = new ExpandableFloatBuffer();
	ExpandableFloatBuffer minorContourColors = new ExpandableFloatBuffer();
	double majorContourAlpha;
	ExpandableFloatBuffer majorContourVertices = new ExpandableFloatBuffer();
	ExpandableFloatBuffer majorContourColors = new ExpandableFloatBuffer();

	public NumericSurfaceGraphThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GC gc, Rectangle localBounds, ISWTResources r) {
		Rectangle lbb = cm.worldToLocal(t.getRawBoundingBox());
		if (!lbb.intersects(localBounds)) {
			return;
		}

		String message = "Unsupported UI";
		Font f = new Font(Font.DIALOG, Font.PLAIN, 12);
		Dimension d = r.getTextSize(f, message);
		r.drawText(f, message, lbb.x + (lbb.width - d.width) / 2, lbb.y + (lbb.height - d.height) / 2,
				new RGB(0, 0, 0), 1);
	}

	@Override
	public void draw(GL2ES2 gl, Rectangle localBounds, IJOGLResources r) {
		Rectangle lbb = cm.worldToLocal(t.getRawBoundingBox());
		if (!lbb.intersects(localBounds)) {
			return;
		}

		PMVMatrix glMatrix = r.getMatrix();

		Data thingData = t.getRawData();
		boolean flipData = false;
		int xRotation = SystemUtils.loop(0, t.getRawXRotation(), 360);
		int yRotation = SystemUtils.loop(0, t.getRawYRotation(), 360);
		NumericAxis xMajorAxis = !flipData ? t.getRawXMajorAxis() : t.getYMajorAxis();
		NumericAxis xMinorAxis = !flipData ? t.getRawXMinorAxis() : t.getYMinorAxis();
		NumericAxis yMajorAxis = !flipData ? t.getRawYMajorAxis() : t.getXMajorAxis();
		NumericAxis yMinorAxis = !flipData ? t.getRawYMinorAxis() : t.getXMinorAxis();
		zMajorAxis = t.getRawZMajorAxis();
		zMinorAxis = t.getRawZMinorAxis();
		gridLinesAlpha = t.getRawGridAlpha();
		minorContourAlpha = t.getRawMinorContourAlpha();
		majorContourAlpha = t.getRawMajorContourAlpha();

		updateMinorAxis(xMajorAxis, xMinorAxis);
		updateMinorAxis(yMajorAxis, yMinorAxis);
		updateMinorAxis(zMajorAxis, zMinorAxis);

		List<Object> cacheValidConditions = Lists.newArrayList();
		cacheValidConditions.add(thingData);
		cacheValidConditions.add(flipData);
		cacheValidConditions.add(xRotation);
		cacheValidConditions.add(yRotation);
		cacheValidConditions.add(xMajorAxis);
		cacheValidConditions.add(xMinorAxis);
		cacheValidConditions.add(yMajorAxis);
		cacheValidConditions.add(yMinorAxis);
		cacheValidConditions.add(zMajorAxis);
		cacheValidConditions.add(zMinorAxis);
		cacheValidConditions.add(gridLinesAlpha > 0);
		cacheValidConditions.add(minorContourAlpha > 0);
		cacheValidConditions.add(majorContourAlpha > 0);
		// don't use the local bounding box as it can vary slightly depending on location
		cacheValidConditions.add(BNAUtils.toDimension(t.getRawBoundingBox()));
		cacheValidConditions.add(cm.getLocalScale());

		if (!this.cacheValidConditions.equals(cacheValidConditions)) {

			data = thingData;
			if (flipData) {
				data.flipXY();
			}

			// determine data range
			double dataMin = Double.POSITIVE_INFINITY;
			double dataMax = Double.NEGATIVE_INFINITY;
			for (int y = 0; y < data.getHeight(); y++) {
				for (int x = 0; x < data.getWidth(); x++) {
					double value = data.getValue(x, y);
					if (!Double.isNaN(value)) {
						dataMin = Math.min(dataMin, value);
						dataMax = Math.max(dataMax, value);
					}
				}
			}

			// determine axis ranges
			xRange = xMajorAxis.getAxisRange(0, data.getWidth() - 1);
			double xRangeMinOffset = xMajorAxis.toValue(xRange.min, xRange).linearOffset;
			double xRangeMaxOffset = xMajorAxis.toValue(xRange.max, xRange).linearOffset;
			yRange = yMajorAxis.getAxisRange(0, data.getHeight() - 1);
			double yRangeMinOffset = yMajorAxis.toValue(yRange.min, yRange).linearOffset;
			double yRangeMaxOffset = yMajorAxis.toValue(yRange.max, yRange).linearOffset;
			zRange = zMajorAxis.getAxisRange(dataMin, dataMax);
			double zRangeMinOffset = zMajorAxis.toValue(zRange.min, zRange).linearOffset;
			double zRangeMaxOffset = zMajorAxis.toValue(zRange.max, zRange).linearOffset;

			// determine graph bounds
			double zMinXMin = Double.POSITIVE_INFINITY;
			double zMinYMin = Double.POSITIVE_INFINITY;
			double zMinXMax = Double.NEGATIVE_INFINITY;
			double zMinYMax = Double.NEGATIVE_INFINITY;
			double zMaxXMin = Double.POSITIVE_INFINITY;
			double zMaxYMin = Double.POSITIVE_INFINITY;
			double zMaxXMax = Double.NEGATIVE_INFINITY;
			double zMaxYMax = Double.NEGATIVE_INFINITY;
			glMatrix.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
			r.pushMatrix(GLMatrixFunc.GL_PROJECTION);
			try {
				glMatrix.glLoadIdentity();
				glMatrix.glRotatef(90 - yRotation, 1, 0, 0);
				glMatrix.glRotatef(xRotation, 0, 0, 1);
				float[] f = new float[16];
				glMatrix.glGetFloatv(GLMatrixFunc.GL_PROJECTION_MATRIX, f, 0);
				matrix = Matrix.newColumnMajorMatrix(4, f);
			}
			finally {
				r.popMatrix(GLMatrixFunc.GL_PROJECTION);
			}
			for (double x : new double[] { xRangeMinOffset, xRangeMaxOffset }) {
				for (double y : new double[] { yRangeMinOffset, yRangeMaxOffset }) {
					Point2D.Double p = transformXY(matrix, x, y, zRangeMinOffset);
					zMinXMin = Math.min(zMinXMin, p.x);
					zMinYMin = Math.min(zMinYMin, p.y);
					zMinXMax = Math.max(zMinXMax, p.x);
					zMinYMax = Math.max(zMinYMax, p.y);
				}
			}
			for (double x : new double[] { xRangeMinOffset, xRangeMaxOffset }) {
				for (double y : new double[] { yRangeMinOffset, yRangeMaxOffset }) {
					Point2D.Double p = transformXY(matrix, x, y, zRangeMaxOffset);
					zMaxXMin = Math.min(zMaxXMin, p.x);
					zMaxYMin = Math.min(zMaxYMin, p.y);
					zMaxXMax = Math.max(zMaxXMax, p.x);
					zMaxYMax = Math.max(zMaxYMax, p.y);
				}
			}

			// scale graph
			double zMinXScale = lbb.width / Math.abs(zMinXMax - zMinXMin);
			double zMaxXScale = lbb.width / Math.abs(zMaxXMax - zMaxXMin);
			double xScale = Math.min(zMinXScale, zMaxXScale);
			double zMinYScale = lbb.height / Math.abs(zMinYMax - zMinYMin);
			double zMaxYScale = lbb.height / Math.abs(zMaxYMax - zMaxYMin);
			double yScale = Math.min(zMinYScale, zMaxYScale);
			xyScale = Math.min(xScale, yScale);
			double zMaxScale = (lbb.height - xyScale * Math.abs(zMinYMax - zMinYMin)) / Math.abs(zMaxYMax - zMinYMax);
			double zMinScale = (lbb.height - xyScale * Math.abs(zMinYMax - zMinYMin)) / Math.abs(zMaxYMin - zMinYMin);
			zScale = Math.min(zMaxScale, zMinScale);

			// determine offset
			originOffsetX = -zMinXMin * xyScale + (lbb.width - xyScale * Math.abs(zMinXMax - zMinXMin)) / 2;
			originOffsetY = lbb.height - Math.max(zMinYMax, zMaxYMax) * xyScale;

			// determine cross points
			xCrossMin = true;
			yCrossMin = true;
			zCrossMin = true;
			boolean xLabelCrossMin = true;
			boolean yLabelCrossMin = true;
			boolean zLabelCrossMin = true;
			boolean zxLabelCrossMin = true;
			boolean zyLabelCrossMin = true;
			if (xRotation > 90 && xRotation <= 270) {
				yCrossMin ^= true;
				zxLabelCrossMin ^= true;
			}
			else {
				yLabelCrossMin ^= true;
			}
			if (xRotation > 180 && xRotation < 360 || xRotation == 0) {
				xCrossMin ^= true;
			}
			else {
				zyLabelCrossMin ^= true;
				xLabelCrossMin ^= true;
			}
			xCrossMin ^= xMajorAxis.reverse;
			yCrossMin ^= yMajorAxis.reverse;
			zCrossMin ^= zMajorAxis.reverse;
			xLabelCrossMin ^= xMajorAxis.reverse;
			yLabelCrossMin ^= yMajorAxis.reverse;
			zLabelCrossMin ^= zMajorAxis.reverse;
			zxLabelCrossMin ^= xMajorAxis.reverse;
			zyLabelCrossMin ^= yMajorAxis.reverse;
			xCrossOffset = xMajorAxis.toValue(xCrossMin ? xRange.min : xRange.max, xRange).linearOffset;
			yCrossOffset = yMajorAxis.toValue(yCrossMin ? yRange.min : yRange.max, yRange).linearOffset;
			zCrossOffset = zMajorAxis.toValue(zCrossMin ? zRange.min : zRange.max, zRange).linearOffset;
			xLabelCrossOffset = xMajorAxis.toValue(xLabelCrossMin ? xRange.min : xRange.max, xRange).linearOffset;
			yLabelCrossOffset = yMajorAxis.toValue(yLabelCrossMin ? yRange.min : yRange.max, yRange).linearOffset;
			zLabelCrossOffset = zMajorAxis.toValue(zLabelCrossMin ? zRange.min : zRange.max, zRange).linearOffset;
			zxLabelCrossOffset = xMajorAxis.toValue(zxLabelCrossMin ? xRange.min : xRange.max, xRange).linearOffset;
			zyLabelCrossOffset = yMajorAxis.toValue(zyLabelCrossMin ? yRange.min : yRange.max, yRange).linearOffset;

			// calculate alignment of text labels
			xLabelHorizontalAlignment = HorizontalAlignment.CENTER;
			xLabelVerticalAlignment = VerticalAlignment.BOTTOM;
			yLabelHorizontalAlignment = HorizontalAlignment.LEFT;
			yLabelVerticalAlignment = VerticalAlignment.MIDDLE;
			int xAlignmentRotation = SystemUtils.loop(0, xRotation, 180);
			if (xAlignmentRotation > 0 && xAlignmentRotation <= 45) {
				yLabelHorizontalAlignment = HorizontalAlignment.RIGHT;
				yLabelVerticalAlignment = VerticalAlignment.MIDDLE;
			}
			if (xAlignmentRotation > 45 && xAlignmentRotation <= 90) {
				xLabelHorizontalAlignment = HorizontalAlignment.LEFT;
				xLabelVerticalAlignment = VerticalAlignment.MIDDLE;
				yLabelHorizontalAlignment = HorizontalAlignment.CENTER;
				yLabelVerticalAlignment = VerticalAlignment.BOTTOM;
			}
			if (xAlignmentRotation > 90 && xAlignmentRotation < 135) {
				xLabelHorizontalAlignment = HorizontalAlignment.RIGHT;
				xLabelVerticalAlignment = VerticalAlignment.MIDDLE;
				yLabelHorizontalAlignment = HorizontalAlignment.CENTER;
				yLabelVerticalAlignment = VerticalAlignment.BOTTOM;
			}

			// calculate offsets for each data value
			int width = data.getWidth();
			int height = data.getHeight();
			double[] offsets = new double[width * height];
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					double value = data.getValue(x, y);
					if (Double.isNaN(value)) {
						offsets[y * width + x] = Double.NaN;
					}
					else {
						offsets[y * width + x] = zMajorAxis.toValue(value, zRange).linearOffset;
					}
				}
			}

			// calculate final matrix
			r.pushMatrix(GLMatrixFunc.GL_PROJECTION);
			try {
				glMatrix.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
				glMatrix.glLoadIdentity();
				glMatrix.glRotatef(90 - yRotation, 1, 0, 0);
				glMatrix.glRotatef(xRotation, 0, 0, 1);
				glMatrix.glScalef((float) xyScale, (float) xyScale, (float) zScale);
				float[] f = new float[16];
				glMatrix.glGetFloatv(GLMatrixFunc.GL_PROJECTION_MATRIX, f, 0);
				matrix = Matrix.newColumnMajorMatrix(4, f);
			}
			finally {
				r.popMatrix(GLMatrixFunc.GL_PROJECTION);
			}

			// calculate points
			Point[] points = new Point[width * height];
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					int index = y * width + x;
					if (!Double.isNaN(offsets[index])) {
						points[index] = new Point(data.getColor(x, y), x, y, offsets[index]);
					}
				}
			}

			// calculate graph triangles, grid lines
			List<Triangle> triangles = Lists.newArrayList();
			List<Line> gridLines = Lists.newArrayList();
			for (int y = 1; y < height; y++) {
				X: for (int x = 1; x < width; x++) {
					Point p1 = points[(y - 1) * width + x - 1];
					Point p2 = points[(y - 1) * width + x - 0];
					Point p3 = points[(y - 0) * width + x - 1];
					Point p4 = points[(y - 0) * width + x - 0];

					// if one value is NaN, merge it with an adjacent value to render a triangle
					int totalNaN = 0;
					int x1 = x - 1;
					int x2 = x - 0;
					int x3 = x - 1;
					int x4 = x - 1;
					if (p1 == null) {
						totalNaN++;
						p1 = p2;
						x1 = x2;
					}
					if (p2 == null) {
						totalNaN++;
						p2 = p1;
						x2 = x1;
					}
					if (p3 == null) {
						totalNaN++;
						p3 = p4;
						x3 = x4;
					}
					if (p4 == null) {
						totalNaN++;
						p4 = p3;
						x4 = x3;
					}
					// if more than one value is NaN, skip this quad
					if (totalNaN > 1) {
						continue X;
					}

					if (p1.xyz[2] + p4.xyz[2] > p2.xyz[2] + p3.xyz[2]) {
						if (p3 != p4) {
							triangles.add(new Triangle(p1, p3, p4));
						}
						if (p1 != p2) {
							triangles.add(new Triangle(p1, p2, p4));
						}
					}
					else {
						if (p1 != p2) {
							triangles.add(new Triangle(p1, p2, p3));
						}
						if (p3 != p4) {
							triangles.add(new Triangle(p2, p3, p4));
						}
					}

					if (gridLinesAlpha > 0) {
						if (x == 1) {
							gridLines.add(new Line(p1, p3));
						}
						gridLines.add(new Line(p2, p4));

						if (y == 1) {
							gridLines.add(new Line(p1, p2));
						}
						gridLines.add(new Line(p3, p4));
					}
				}
			}
			triangleVertices.rewind();
			triangleColors.rewind();
			for (Triangle t : triangles) {
				for (Point p : t.p) {
					triangleVertices.put(p.xyz);
					triangleColors.put(p.rgba);
				}
			}
			trim(triangleVertices, triangleColors);
			gridVertices.rewind();
			gridColors.rewind();
			putAllLines(gridLines, new RGB(0, 0, 0), gridVertices, gridColors);
			trim(gridVertices, gridColors);

			// calculate contour lines
			List<Line> minorContourLines = Lists.newArrayList();
			if (minorContourAlpha > 0) {
				double[] zValues = toValues(zMinorAxis, zRange);
				for (Triangle t : triangles) {
					t.addContourLines(minorContourLines, zValues);
				}
			}
			minorContourVertices.rewind();
			minorContourColors.rewind();
			putAllLines(minorContourLines, new RGB(0, 0, 0), minorContourVertices, minorContourColors);
			trim(minorContourVertices, minorContourColors);
			List<Line> majorContourLines = Lists.newArrayList();
			if (majorContourAlpha > 0) {
				double[] zValues = toValues(zMajorAxis, zRange);
				for (Triangle triangle : triangles) {
					triangle.addContourLines(majorContourLines, zValues);
				}
			}
			majorContourVertices.rewind();
			majorContourColors.rewind();
			putAllLines(majorContourLines, new RGB(0, 0, 0), majorContourVertices, majorContourColors);
			trim(majorContourVertices, majorContourColors);

			this.cacheValidConditions = cacheValidConditions;
		}

		// render graph
		r.pushMatrix(GLMatrixFunc.GL_PROJECTION);
		try {
			gl.glEnable(GL.GL_DEPTH_TEST);
			gl.glDepthFunc(GL.GL_LEQUAL);

			glMatrix.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
			glMatrix.glTranslatef(lbb.x + (float) originOffsetX, lbb.y + (float) originOffsetY, 0);
			glMatrix.glRotatef(90 - yRotation, 1, 0, 0);
			glMatrix.glRotatef(xRotation, 0, 0, 1);
			glMatrix.glScalef((float) xyScale, (float) xyScale, (float) zScale);
			gl.glDepthRange(-10000, 10000);

			drawGraph(gl, localBounds, r, data, xMajorAxis, yMajorAxis, zMajorAxis);
			drawAxis(gl, localBounds, r, data, xMajorAxis, xMinorAxis, yMajorAxis, yMinorAxis, zMajorAxis, zMinorAxis);
		}
		finally {
			r.popMatrix(GLMatrixFunc.GL_PROJECTION);
			gl.glDisable(GL.GL_DEPTH_TEST);
		}

		// render labels
		Font font = new Font(Font.DIALOG, Font.PLAIN, 12);
		int tick = r.getTextSize(font, "  ").width;
		FontMetrics metrics = r.getFontMetrics(font);
		for (Value v : xMajorAxis.getAxisValues(xRange)) {
			String label = xMajorAxis.formatter.apply(v.actualValue);
			Dimension size = r.getTextSize(font, label);
			Point2D.Double p = transformXY(matrix, v.linearOffset, yLabelCrossOffset, zLabelCrossOffset);
			p.x += lbb.x + originOffsetX;
			p.y += lbb.y + originOffsetY;
			double x = p.x;
			double y = p.y;
			switch (xLabelHorizontalAlignment) {
			case LEFT:
				x -= size.width + tick + metrics.getLeading();
				r.drawShape(new Line2D.Double(p.x, p.y, p.x - tick, p.y), new RGB(0, 0, 0), 1, LineStyle.SOLID, 1);
				break;
			case CENTER:
				x -= size.width / 2;
				r.drawShape(new Line2D.Double(p.x, p.y, p.x, p.y + tick), new RGB(0, 0, 0), 1, LineStyle.SOLID, 1);
				break;
			case RIGHT:
				x += tick + metrics.getLeading();
				r.drawShape(new Line2D.Double(p.x, p.y, p.x + tick, p.y), new RGB(0, 0, 0), 1, LineStyle.SOLID, 1);
				break;
			}
			switch (xLabelVerticalAlignment) {
			case TOP:
				y -= size.height;
				break;
			case MIDDLE:
				y -= metrics.getLeading() + metrics.getAscent() / 2;
				break;
			case BOTTOM:
				y += tick;
				break;
			}
			r.drawText(font, label, x, y, new RGB(0, 0, 0), 1);
		}
		for (Value v : yMajorAxis.getAxisValues(yRange)) {
			String label = yMajorAxis.formatter.apply(v.actualValue);
			Dimension size = r.getTextSize(font, label);
			Point2D.Double p = transformXY(matrix, xLabelCrossOffset, v.linearOffset, zLabelCrossOffset);
			p.x += lbb.x + originOffsetX;
			p.y += lbb.y + originOffsetY;
			double x = p.x;
			double y = p.y;
			switch (yLabelHorizontalAlignment) {
			case LEFT:
				x -= size.width + tick + metrics.getLeading();
				r.drawShape(new Line2D.Double(p.x, p.y, p.x - tick, p.y), new RGB(0, 0, 0), 1, LineStyle.SOLID, 1);
				break;
			case CENTER:
				x -= size.width / 2;
				r.drawShape(new Line2D.Double(p.x, p.y, p.x, p.y + tick), new RGB(0, 0, 0), 1, LineStyle.SOLID, 1);
				break;
			case RIGHT:
				x += tick + metrics.getLeading();
				r.drawShape(new Line2D.Double(p.x, p.y, p.x + tick, p.y), new RGB(0, 0, 0), 1, LineStyle.SOLID, 1);
				break;
			}
			switch (yLabelVerticalAlignment) {
			case TOP:
				y -= size.height;
				break;
			case MIDDLE:
				y -= metrics.getLeading() + metrics.getAscent() / 2;
				break;
			case BOTTOM:
				y += tick;
				break;
			}
			r.drawText(font, label, x, y, new RGB(0, 0, 0), 1);
		}
		for (Value v : zMajorAxis.getAxisValues(zRange)) {
			String label = zMajorAxis.formatter.apply(v.actualValue);
			Dimension size = r.getTextSize(font, label);
			Point2D.Double p = transformXY(matrix, zxLabelCrossOffset, zyLabelCrossOffset, v.linearOffset);
			p.x += lbb.x + originOffsetX;
			p.y += lbb.y + originOffsetY;
			r.drawShape(new Line2D.Double(p.x, p.y, p.x - tick, p.y), new RGB(0, 0, 0), 1, LineStyle.SOLID, 1);
			double x = p.x - tick - size.width - metrics.getLeading();
			double y = p.y - metrics.getLeading() - metrics.getAscent() / 2;
			r.drawText(font, label, x, y, new RGB(0, 0, 0), 1);
		}

		if (t.isRawSelected()) {
			r.selectShape(new Rectangle2D.Double(lbb.x, lbb.y, lbb.width, lbb.height), t.getRawRotatingOffset());
		}
	}

	private void putAllLines(List<Line> gridLines, RGB rgb, ExpandableFloatBuffer vertices, ExpandableFloatBuffer colors) {
		float[] rgbargba = new float[] {//
		//
				rgb.red / 255f, rgb.green / 255f, rgb.blue / 255f, 1,//
				rgb.red / 255f, rgb.green / 255f, rgb.blue / 255f, 1 };

		for (Line l : gridLines) {
			vertices.put(l.xyz1);
			vertices.put(l.xyz2);
			colors.put(rgbargba);
		}
	}

	private void trim(ExpandableFloatBuffer... buffers) {
		for (ExpandableFloatBuffer buffer : buffers) {
			buffer.trim();
		}
	}

	private void updateMinorAxis(NumericAxis majorAxis, NumericAxis minorAxis) {
		minorAxis.reverse = majorAxis.reverse;
	}

	private void drawGraph(GL2ES2 gl, Rectangle localBounds, IJOGLResources r, Data data, NumericAxis xAxis,
			NumericAxis yAxis, NumericAxis zAxis) {

		PMVMatrix glMatrix = r.getMatrix();

		// render triangles
		{
			triangleVertices.rewind();
			triangleColors.rewind();
			r.fillShape(GL.GL_TRIANGLES, triangleVertices.getBackingBuffer(), triangleColors.getBackingBuffer(),
					triangleVertices.limit() / 3);
		}

		// render contours
		{
			double zNudge = NUDGE / zScale;
			double xyNudge = NUDGE / xyScale;

			r.pushMatrix(GLMatrixFunc.GL_PROJECTION);
			try {
				glMatrix.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
				for (double nudge : new double[] { -1, 2 }) {
					glMatrix.glTranslatef((float) (nudge * (xCrossMin ? -xyNudge : xyNudge)),
							(float) (nudge * (yCrossMin ? -xyNudge : xyNudge)), (float) (nudge * (zCrossMin ? -zNudge
									: zNudge)));

					if (gridLinesAlpha > 0) {
						drawLines(localBounds, r, gridVertices, gridColors, new RGB(0, 0, 0), LineStyle.SOLID,
								gridLinesAlpha);
					}
					if (minorContourAlpha > 0) {
						drawLines(localBounds, r, minorContourVertices, minorContourColors, new RGB(0, 0, 0),
								zMinorAxis.lineStyle, minorContourAlpha);
					}
					if (majorContourAlpha > 0) {
						drawLines(localBounds, r, majorContourVertices, majorContourColors, new RGB(0, 0, 0),
								zMajorAxis.lineStyle, majorContourAlpha);
					}
				}
			}
			finally {
				r.popMatrix(GLMatrixFunc.GL_PROJECTION);
			}
		}
	}

	private void drawLines(Rectangle localBounds, IJOGLResources r, ExpandableFloatBuffer vertices,
			ExpandableFloatBuffer colors, RGB rgb, LineStyle lineStyle, double alpha) {
		vertices.rewind();
		colors.rewind();
		r.drawShape(GL.GL_LINES, vertices.getBackingBuffer(), colors.getBackingBuffer(), vertices.limit() / 3,
				lineStyle.toBitPattern());
	}

	private void drawAxis(GL2ES2 gl, Rectangle localBounds, IJOGLResources r, Data data, NumericAxis xMajorAxis,
			NumericAxis xMinorAxis, NumericAxis yMajorAxis, NumericAxis yMinorAxis, NumericAxis zMajorAxis,
			NumericAxis zMinorAxis) {

		PMVMatrix glMatrix = r.getMatrix();

		double xMin = xMajorAxis.toValue(xRange.min, xRange).linearOffset;
		double xMax = xMajorAxis.toValue(xRange.max, xRange).linearOffset;
		double yMin = yMajorAxis.toValue(yRange.min, yRange).linearOffset;
		double yMax = yMajorAxis.toValue(yRange.max, yRange).linearOffset;
		double zMin = zMajorAxis.toValue(zRange.min, zRange).linearOffset;
		double zMax = zMajorAxis.toValue(zRange.max, zRange).linearOffset;

		r.pushMatrix(GLMatrixFunc.GL_PROJECTION);
		try {
			double zNudge = NUDGE / zScale;
			double xyNudge = NUDGE / xyScale;
			glMatrix.glTranslatef((float) (xCrossMin ? -xyNudge : xyNudge), (float) (yCrossMin ? -xyNudge : xyNudge),
					(float) (zCrossMin ? -zNudge : zNudge));

			FloatBuffer colors = Buffers.newDirectFloatBuffer(new float[] { 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0,
					0, 1 });

			xMinorAxis.unit = 1;
			xMinorAxis.lineStyle = LineStyle.DOT;
			for (NumericAxis axis : Lists.newArrayList(xMinorAxis, xMajorAxis)) {
				for (Value v : axis.getAxisValues(xRange)) {
					r.drawShape(GL.GL_LINES, Buffers.newDirectFloatBuffer(new float[] { //
							//
									(float) v.linearOffset, (float) yCrossOffset, (float) zMin, //
									(float) v.linearOffset, (float) yCrossOffset, (float) zMax, //
									(float) v.linearOffset, (float) yMin, (float) zCrossOffset, //
									(float) v.linearOffset, (float) yMax, (float) zCrossOffset //
							}), colors, 4, axis.lineStyle.toBitPattern());
				}
			}

			for (NumericAxis axis : Lists.newArrayList(yMinorAxis, yMajorAxis)) {
				for (Value v : axis.getAxisValues(yRange)) {
					r.drawShape(GL.GL_LINES, Buffers.newDirectFloatBuffer(new float[] { //
							//
									(float) xMin, (float) v.linearOffset, (float) zCrossOffset, //
									(float) xMax, (float) v.linearOffset, (float) zCrossOffset, //
									(float) xCrossOffset, (float) v.linearOffset, (float) zMin, //
									(float) xCrossOffset, (float) v.linearOffset, (float) zMax //
							}), colors, 4, axis.lineStyle.toBitPattern());
				}
			}

			for (NumericAxis axis : Lists.newArrayList(zMinorAxis, zMajorAxis)) {
				for (Value v : axis.getAxisValues(zRange)) {
					r.drawShape(GL.GL_LINES, Buffers.newDirectFloatBuffer(new float[] { //
							//
									(float) xMin, (float) yCrossOffset, (float) v.linearOffset, //
									(float) xMax, (float) yCrossOffset, (float) v.linearOffset, //
									(float) xCrossOffset, (float) yMin, (float) v.linearOffset, //
									(float) xCrossOffset, (float) yMax, (float) v.linearOffset //
							}), colors, 4, axis.lineStyle.toBitPattern());
				}
			}
		}
		finally {
			r.popMatrix(GLMatrixFunc.GL_PROJECTION);
		}
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		return t.getRawBoundingBox().contains(location.getWorldPoint());
	}
}