package org.archstudio.bna.things.graphs;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.things.graphs.NumericAxis.Range;
import org.archstudio.bna.things.graphs.NumericAxis.Value;
import org.archstudio.bna.things.graphs.NumericSurfaceGraphThing.Data;
import org.archstudio.bna.ui.IUIResources.FontMetrics;
import org.archstudio.bna.ui.jogl.IJOGLResources;
import org.archstudio.bna.ui.swt.ISWTResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.VerticalAlignment;
import org.archstudio.sysutils.Matrix;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Lists;

public class NumericSurfaceGraphThingPeer<T extends NumericSurfaceGraphThing> extends AbstractRectangleThingPeer<T> {

	private static final double NUDGE = 0.75;

	private static class Point {
		RGB rgb;
		double nx, ny, nz;
		double x, y, z;

		public Point(RGB rgb, double nx, double ny, double nz, double x, double y, double z) {
			this.rgb = rgb;
			this.nx = nx;
			this.ny = ny;
			this.nz = nz;
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public void rnv(IJOGLResources r, GL2 gl) {
			r.setColor(rgb, 1);
			gl.glNormal3d(nx, ny, nz);
			gl.glVertex3d(x, y, z);
		}

	}

	private static class Line {
		double x1, y1, z1;
		double x2, y2, z2;

		public Line(double x1, double y1, double z1, double x2, double y2, double z2) {
			this.x1 = x1;
			this.y1 = y1;
			this.z1 = z1;
			this.x2 = x2;
			this.y2 = y2;
			this.z2 = z2;
		}

		public Line(Point p1, Point p2) {
			this(p1.x, p1.y, p1.z, p2.x, p2.y, p2.z);
		}

		public void v(IJOGLResources r, GL2 gl) {
			gl.glVertex3d(x1, y1, z1);
			gl.glVertex3d(x2, y2, z2);
		}
	}

	private static class Triangle {
		Point[] p;

		public Triangle(Point p1, Point p2, Point p3) {
			p = new Point[] { p1, p2, p3 };
			Arrays.sort(p, new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					return o1.z < o2.z ? -1 : o1.z > o2.z ? 1 : 0;
				}
			});
		}

		public void addContourLines(Collection<Line> lines, double[] zValues) {
			int i = Arrays.binarySearch(zValues, p[0].z);
			i = i < 0 ? -i - 1 : i;
			while (i < zValues.length && zValues[i] <= p[1].z) {
				double z = zValues[i];
				double x2 = position(p[0].z, z, p[2].z, p[0].x, p[2].x);
				double x1 = position(p[0].z, z, p[1].z, p[0].x, p[1].x);
				double y2 = position(p[0].z, z, p[2].z, p[0].y, p[2].y);
				double y1 = position(p[0].z, z, p[1].z, p[0].y, p[1].y);
				lines.add(new Line(x1, y1, z, x2, y2, z));
				i++;
			}
			while (i < zValues.length && zValues[i] <= p[2].z) {
				double z = zValues[i];
				double x0 = position(p[0].z, z, p[2].z, p[0].x, p[2].x);
				double x1 = position(p[1].z, z, p[2].z, p[1].x, p[2].x);
				double y0 = position(p[0].z, z, p[2].z, p[0].y, p[2].y);
				double y1 = position(p[1].z, z, p[2].z, p[1].y, p[2].y);
				lines.add(new Line(x0, y0, z, x1, y1, z));
				i++;
			}
		}

		private double position(double z0, double z, double z1, double v0, double v1) {
			return (z - z0) / (z1 - z0) * (v1 - v0) + v0;
		}

		public void rnv(IJOGLResources r, GL2 gl) {
			for (Point element : p) {
				element.rnv(r, gl);
			}
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
	double[] offsets;
	double[] normalX;
	double[] normalY;
	double[] normalZ;
	Point[] points;
	List<Triangle> triangles = Lists.newArrayList();
	NumericAxis zMajorAxis;
	NumericAxis zMinorAxis;
	double gridLinesAlpha;
	List<Line> gridLines = Lists.newArrayList();
	double minorContourAlpha;
	List<Line> minorContourLines = Lists.newArrayList();
	double majorContourAlpha;
	List<Line> majorContourLines = Lists.newArrayList();

	public NumericSurfaceGraphThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GC gc, Rectangle localBounds, ISWTResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!lbb.intersects(localBounds)) {
			return;
		}

		String message = "Unsupported UI";
		Font f = new Font(Font.DIALOG, Font.PLAIN, 12);
		Dimension d = r.getTextSize(f, message);
		r.setColor(new RGB(0, 0, 0), 1);
		r.drawText(f, message, lbb.x + (lbb.width - d.width) / 2, lbb.y + (lbb.height - d.height) / 2);
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, IJOGLResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!lbb.intersects(localBounds)) {
			return;
		}

		Data thingData = t.getData();
		boolean flipData = false; // t.getFlipData();
		int xRotation = SystemUtils.loop(0, t.getXRotation(), 360);
		int yRotation = SystemUtils.loop(0, t.getYRotation(), 360);
		NumericAxis xMajorAxis = !flipData ? t.getXMajorAxis() : t.getYMajorAxis();
		NumericAxis xMinorAxis = !flipData ? t.getXMinorAxis() : t.getYMinorAxis();
		NumericAxis yMajorAxis = !flipData ? t.getYMajorAxis() : t.getXMajorAxis();
		NumericAxis yMinorAxis = !flipData ? t.getYMinorAxis() : t.getXMinorAxis();
		zMajorAxis = t.getZMajorAxis();
		zMinorAxis = t.getZMinorAxis();
		gridLinesAlpha = t.getGridAlpha();
		minorContourAlpha = t.getMinorContourAlpha();
		majorContourAlpha = t.getMajorContourAlpha();

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
		cacheValidConditions.add(BNAUtils.toDimension(t.getBoundingBox()));
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
			xRange = xMinorAxis.getAxisRange(0, data.getWidth() - 1);
			double xRangeMinOffset = xMajorAxis.toValue(xRange.min, xRange).linearOffset;
			double xRangeMaxOffset = xMajorAxis.toValue(xRange.max, xRange).linearOffset;
			yRange = yMinorAxis.getAxisRange(0, data.getHeight() - 1);
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
			gl.glPushMatrix();
			try {
				gl.glLoadIdentity();
				gl.glRotated(90 - yRotation, 1, 0, 0);
				gl.glRotated(xRotation, 0, 0, 1);
				matrix = r.getMatrix();
			}
			finally {
				gl.glPopMatrix();
			}
			for (double x : new double[] { xRangeMinOffset, xRangeMaxOffset }) {
				for (double y : new double[] { yRangeMinOffset, yRangeMaxOffset }) {
					Point2D.Double p = r.transformXY(matrix, x, y, zRangeMinOffset);
					zMinXMin = Math.min(zMinXMin, p.x);
					zMinYMin = Math.min(zMinYMin, p.y);
					zMinXMax = Math.max(zMinXMax, p.x);
					zMinYMax = Math.max(zMinYMax, p.y);
				}
			}
			for (double x : new double[] { xRangeMinOffset, xRangeMaxOffset }) {
				for (double y : new double[] { yRangeMinOffset, yRangeMaxOffset }) {
					Point2D.Double p = r.transformXY(matrix, x, y, zRangeMaxOffset);
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
			offsets = new double[width * height];
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

			// calculate normals for each data value
			normalX = new double[width * height];
			normalY = new double[width * height];
			normalZ = new double[width * height];
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width - 1; x++) {
					double o1 = offsets[y * width + x];
					double o2 = offsets[y * width + x + 1];
					if (!Double.isNaN(o1) && !Double.isNaN(o2)) {
						normalX[y * width + x] += Math.atan2(o1 - o2, 1);
					}
				}
				for (int x = 1; x < width; x++) {
					double o1 = offsets[y * width + x - 1];
					double o2 = offsets[y * width + x];
					if (!Double.isNaN(o1) && !Double.isNaN(o2)) {
						normalX[y * width + x] += Math.atan2(o1 - o2, 1);
					}
				}
			}
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height - 1; y++) {
					double o1 = offsets[y * width + x];
					double o2 = offsets[(y + 1) * width + x];
					if (!Double.isNaN(o1) && !Double.isNaN(o2)) {
						normalY[y * width + x] += Math.atan2(o1 - o2, 1);
					}
				}
				for (int y = 1; y < height; y++) {
					double o1 = offsets[(y - 1) * width + x];
					double o2 = offsets[y * width + x];
					if (!Double.isNaN(o1) && !Double.isNaN(o2)) {
						normalY[y * width + x] += Math.atan2(o1 - o2, 1);
					}
				}
			}
			Arrays.fill(normalZ, 1);
			if (xMajorAxis.reverse) {
				for (int i = 0; i < width * height; i++) {
					normalX[i] *= -1;
				}
			}
			if (yMajorAxis.reverse) {
				for (int i = 0; i < width * height; i++) {
					normalY[i] *= -1;
				}
			}

			// calculate final matrix
			gl.glPushMatrix();
			try {
				gl.glLoadIdentity();
				gl.glRotated(90 - yRotation, 1, 0, 0);
				gl.glRotated(xRotation, 0, 0, 1);
				gl.glScaled(xyScale, xyScale, zScale);
				matrix = r.getMatrix();
			}
			finally {
				gl.glPopMatrix();
			}

			// calculate points
			points = new Point[width * height];
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					int index = y * width + x;
					if (!Double.isNaN(offsets[index])) {
						points[index] = new Point(data.getColor(x, y), normalX[index], normalY[index], normalZ[index],
								x, y, offsets[index]);
					}
				}
			}

			// calculate graph triangles, grid lines
			triangles.clear();
			gridLines.clear();
			for (int y = 1; y < height; y++) {
				X: for (int x = 1; x < width; x++) {
					Point p1 = points[(y - 1) * width + x - 1];
					Point p2 = points[(y - 1) * width + x - 0];
					Point p3 = points[(y - 0) * width + x - 1];
					Point p4 = points[(y - 0) * width + x - 0];

					// if one value is Nan, merge it with an adjacent value to render a triangle
					int totalNan = 0;
					int x1 = x - 1;
					int x2 = x - 0;
					int x3 = x - 1;
					int x4 = x - 1;
					if (p1 == null) {
						totalNan++;
						p1 = p2;
						x1 = x2;
					}
					if (p2 == null) {
						totalNan++;
						p2 = p1;
						x2 = x1;
					}
					if (p3 == null) {
						totalNan++;
						p3 = p4;
						x3 = x4;
					}
					if (p4 == null) {
						totalNan++;
						p4 = p3;
						x4 = x3;
					}
					// if more than one value is Nan, skip this quad
					if (totalNan > 1) {
						continue X;
					}

					if (p1.z + p4.z > p2.z + p3.z) {
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

			// calculate contour lines
			minorContourLines.clear();
			if (minorContourAlpha > 0) {
				double[] zValues = toValues(zMinorAxis, zRange);
				for (Triangle triangle : triangles) {
					triangle.addContourLines(minorContourLines, zValues);
				}
			}
			majorContourLines.clear();
			if (majorContourAlpha > 0) {
				double[] zValues = toValues(zMajorAxis, zRange);
				for (Triangle triangle : triangles) {
					triangle.addContourLines(majorContourLines, zValues);
				}
			}

			this.cacheValidConditions = cacheValidConditions;
		}

		// render graph
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glPushMatrix();
		try {
			gl.glEnable(GL.GL_DEPTH_TEST);
			gl.glDepthFunc(GL2.GL_LEQUAL);

			gl.glTranslated(lbb.x + originOffsetX, lbb.y + originOffsetY, 0);
			gl.glRotated(90 - yRotation, 1, 0, 0);
			gl.glRotated(xRotation, 0, 0, 1);
			gl.glScaled(xyScale, xyScale, zScale);
			gl.glDepthRange(-10000, 10000);

			drawGraph(gl, r, data, xMajorAxis, yMajorAxis, zMajorAxis);
			drawAxis(gl, r, data, xMajorAxis, xMinorAxis, yMajorAxis, yMinorAxis, zMajorAxis, zMinorAxis);
		}
		finally {
			gl.glPopMatrix();
			gl.glDisable(GL.GL_DEPTH_TEST);
		}

		// render labels
		Font font = new Font(Font.DIALOG, Font.PLAIN, 12);
		int tick = r.getTextSize(font, "  ").width;
		FontMetrics metrics = r.getFontMetrics(font);
		for (Value v : xMajorAxis.getAxisValues(xRange)) {
			String label = xMajorAxis.formatter.apply(v.actualValue);
			Dimension size = r.getTextSize(font, label);
			Point2D.Double p = r.transformXY(matrix, v.linearOffset, yLabelCrossOffset, zLabelCrossOffset);
			p.x += lbb.x + originOffsetX;
			p.y += lbb.y + originOffsetY;
			double x = p.x;
			double y = p.y;
			switch (xLabelHorizontalAlignment) {
			case LEFT:
				x -= size.width + tick + metrics.getLeading();
				r.drawShape(new Line2D.Double(p.x, p.y, p.x - tick, p.y));
				break;
			case CENTER:
				x -= size.width / 2;
				r.drawShape(new Line2D.Double(p.x, p.y, p.x, p.y + tick));
				break;
			case RIGHT:
				x += tick + metrics.getLeading();
				r.drawShape(new Line2D.Double(p.x, p.y, p.x + tick, p.y));
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
			r.drawText(font, label, x, y);
		}
		for (Value v : yMajorAxis.getAxisValues(yRange)) {
			String label = yMajorAxis.formatter.apply(v.actualValue);
			Dimension size = r.getTextSize(font, label);
			Point2D.Double p = r.transformXY(matrix, xLabelCrossOffset, v.linearOffset, zLabelCrossOffset);
			p.x += lbb.x + originOffsetX;
			p.y += lbb.y + originOffsetY;
			double x = p.x;
			double y = p.y;
			switch (yLabelHorizontalAlignment) {
			case LEFT:
				x -= size.width + tick + metrics.getLeading();
				r.drawShape(new Line2D.Double(p.x, p.y, p.x - tick, p.y));
				break;
			case CENTER:
				x -= size.width / 2;
				r.drawShape(new Line2D.Double(p.x, p.y, p.x, p.y + tick));
				break;
			case RIGHT:
				x += tick + metrics.getLeading();
				r.drawShape(new Line2D.Double(p.x, p.y, p.x + tick, p.y));
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
			r.drawText(font, label, x, y);
		}
		for (Value v : zMajorAxis.getAxisValues(zRange)) {
			String label = zMajorAxis.formatter.apply(v.actualValue);
			Dimension size = r.getTextSize(font, label);
			Point2D.Double p = r.transformXY(matrix, zxLabelCrossOffset, zyLabelCrossOffset, v.linearOffset);
			p.x += lbb.x + originOffsetX;
			p.y += lbb.y + originOffsetY;
			r.drawShape(new Line2D.Double(p.x, p.y, p.x - tick, p.y));
			double x = p.x - tick - size.width - metrics.getLeading();
			double y = p.y - metrics.getLeading() - metrics.getAscent() / 2;
			r.drawText(font, label, x, y);
		}

		if (t.isSelected()) {
			r.selectShape(new Rectangle2D.Double(lbb.x, lbb.y, lbb.width, lbb.height), t.getRotatingOffset());
		}
	}

	private void updateMinorAxis(NumericAxis majorAxis, NumericAxis minorAxis) {
		minorAxis.reverse = majorAxis.reverse;
	}

	private void drawGraph(GL2 gl, IJOGLResources r, Data data, NumericAxis xAxis, NumericAxis yAxis, NumericAxis zAxis) {

		try {
			double zMax = zAxis.toValue(zRange.max, zRange).linearOffset;
			float[] lightPos = { (float) 1 * (data.getWidth() - 1) / 2, 0, (float) zMax, 1 };
			float ambient = 0.5f;
			float[] lightColorAmbient = { ambient, ambient, ambient, ambient };
			float specular = 1f;
			float[] lightColorSpecular = { specular, specular, specular, specular };
			float diffuse = 1f;
			float[] lightColorDiffuse = { diffuse, diffuse, diffuse, diffuse };
			float materialRGB = 0.2f;
			float shininess = 0.5f;

			// Set light parameters.
			gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPos, 0);
			gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, lightColorAmbient, 0);
			gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, lightColorSpecular, 0);
			gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, lightColorDiffuse, 0);

			// Enable lighting in GL.
			gl.glEnable(GL2.GL_LIGHT0);
			gl.glEnable(GL2.GL_LIGHTING);
			gl.glEnable(GL2.GL_COLOR_MATERIAL);
			gl.glEnable(GL2.GL_NORMALIZE);
			gl.glColorMaterial(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE);

			float[] materialRGBv = { materialRGB, materialRGB, materialRGB };
			gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, materialRGBv, 0);
			gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, materialRGBv, 0);
			gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, shininess);

			// render data
			gl.glBegin(GL2.GL_TRIANGLES);
			for (Triangle t : triangles) {
				t.rnv(r, gl);
			}
			gl.glEnd();

			// render contours
			double zNudge = NUDGE / zScale;
			double xyNudge = NUDGE / xyScale;
			gl.glPushMatrix();
			try {
				for (double nudge : new double[] { -1, 2 }) {
					gl.glTranslated(nudge * (xCrossMin ? -xyNudge : xyNudge), nudge * (yCrossMin ? -xyNudge : xyNudge),
							nudge * (zCrossMin ? -zNudge : zNudge));

					if (gridLinesAlpha > 0) {
						gl.glColor4d(0, 0, 0, gridLinesAlpha);
						gl.glBegin(GL.GL_LINES);
						for (Line line : gridLines) {
							line.v(r, gl);
						}
						gl.glEnd();
					}
					if (minorContourAlpha > 0) {
						setLineStyle(gl, r, zMinorAxis);
						gl.glColor4d(0, 0, 0, minorContourAlpha);
						gl.glBegin(GL.GL_LINES);
						for (Line line : minorContourLines) {
							line.v(r, gl);
						}
						gl.glEnd();
						resetLineStyle(gl, r);
					}
					if (majorContourAlpha > 0) {
						setLineStyle(gl, r, zMajorAxis);
						gl.glColor4d(0, 0, 0, majorContourAlpha);
						gl.glBegin(GL.GL_LINES);
						for (Line line : majorContourLines) {
							line.v(r, gl);
						}
						gl.glEnd();
						resetLineStyle(gl, r);
					}
				}
			}
			finally {
				gl.glPopMatrix();
			}

		}
		finally {
			gl.glDisable(GL2.GL_LIGHT0);
			gl.glDisable(GL2.GL_LIGHTING);
		}
	}

	private void setLineStyle(GL2 gl, IJOGLResources r, NumericAxis axis) {
		gl.glLineStipple(0, (short) axis.lineStyle.toBitPattern());
	}

	private void resetLineStyle(GL2 gl, IJOGLResources r) {
		gl.glLineStipple(1, (short) 0xffffffff);
	}

	private void drawAxis(GL2 gl, IJOGLResources r, Data data, NumericAxis xMajorAxis, NumericAxis xMinorAxis,
			NumericAxis yMajorAxis, NumericAxis yMinorAxis, NumericAxis zMajorAxis, NumericAxis zMinorAxis) {

		double xMin = xMajorAxis.toValue(xRange.min, xRange).linearOffset;
		double xMax = xMajorAxis.toValue(xRange.max, xRange).linearOffset;
		double yMin = yMajorAxis.toValue(yRange.min, yRange).linearOffset;
		double yMax = yMajorAxis.toValue(yRange.max, yRange).linearOffset;
		double zMin = zMajorAxis.toValue(zRange.min, zRange).linearOffset;
		double zMax = zMajorAxis.toValue(zRange.max, zRange).linearOffset;

		gl.glPushMatrix();
		try {
			double zNudge = NUDGE / zScale;
			double xyNudge = NUDGE / xyScale;
			gl.glTranslated(xCrossMin ? -xyNudge : xyNudge, yCrossMin ? -xyNudge : xyNudge, zCrossMin ? -zNudge
					: zNudge);

			r.setColor(new RGB(0, 0, 0), 1);

			for (NumericAxis axis : Lists.newArrayList(xMinorAxis, xMajorAxis)) {
				setLineStyle(gl, r, axis);
				for (Value v : axis.getAxisValues(xRange)) {
					gl.glBegin(GL.GL_LINES);
					gl.glVertex3d(v.linearOffset, yCrossOffset, zMin);
					gl.glVertex3d(v.linearOffset, yCrossOffset, zMax);
					gl.glVertex3d(v.linearOffset, yMin, zCrossOffset);
					gl.glVertex3d(v.linearOffset, yMax, zCrossOffset);
					gl.glEnd();
				}
				resetLineStyle(gl, r);
			}

			for (NumericAxis axis : Lists.newArrayList(yMinorAxis, yMajorAxis)) {
				setLineStyle(gl, r, axis);
				for (Value v : axis.getAxisValues(yRange)) {
					gl.glBegin(GL.GL_LINES);
					gl.glVertex3d(xMin, v.linearOffset, zCrossOffset);
					gl.glVertex3d(xMax, v.linearOffset, zCrossOffset);
					gl.glVertex3d(xCrossOffset, v.linearOffset, zMin);
					gl.glVertex3d(xCrossOffset, v.linearOffset, zMax);
					gl.glEnd();
				}
				resetLineStyle(gl, r);
			}

			for (NumericAxis axis : Lists.newArrayList(zMinorAxis, zMajorAxis)) {
				setLineStyle(gl, r, axis);
				for (Value v : axis.getAxisValues(zRange)) {
					gl.glBegin(GL.GL_LINES);
					gl.glVertex3d(xMin, yCrossOffset, v.linearOffset);
					gl.glVertex3d(xMax, yCrossOffset, v.linearOffset);
					gl.glVertex3d(xCrossOffset, yMin, v.linearOffset);
					gl.glVertex3d(xCrossOffset, yMax, v.linearOffset);
					gl.glEnd();
				}
				resetLineStyle(gl, r);
			}
		}
		finally {
			gl.glPopMatrix();
		}
	}

}
