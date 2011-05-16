package org.archstudio.bna.utils;

import org.archstudio.bna.ICoordinateMapper;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.PathData;

public class PathDataUtils {

	public static final PathData EMPTY_PATH_DATA = new PathDataConstructor().getPathData();

	public static void addPathDataToPath(Path path, PathData pathData, Point pathDataOffset, ICoordinateMapper cm) {
		byte[] types = pathData.types;
		float[] points = pathData.points;
		float ox = pathDataOffset.x;
		float oy = pathDataOffset.y;
		float x1, y1, x2, y2, x3, y3;
		int pi = 0;
		for (byte element : types) {
			switch (element) {
			case SWT.PATH_CLOSE:
				path.close();
				break;
			case SWT.PATH_CUBIC_TO:
				x1 = cm.worldXtoLocalX(ox + points[pi++]);
				y1 = cm.worldYtoLocalY(oy + points[pi++]);
				x2 = cm.worldXtoLocalX(ox + points[pi++]);
				y2 = cm.worldYtoLocalY(oy + points[pi++]);
				x3 = cm.worldXtoLocalX(ox + points[pi++]);
				y3 = cm.worldYtoLocalY(oy + points[pi++]);
				path.cubicTo(x1, y1, x2, y2, x3, y3);
				break;
			case SWT.PATH_LINE_TO:
				x1 = cm.worldXtoLocalX(ox + points[pi++]);
				y1 = cm.worldYtoLocalY(oy + points[pi++]);
				path.lineTo(x1, y1);
				break;
			case SWT.PATH_MOVE_TO:
				x1 = cm.worldXtoLocalX(ox + points[pi++]);
				y1 = cm.worldYtoLocalY(oy + points[pi++]);
				path.moveTo(x1, y1);
				break;
			case SWT.PATH_QUAD_TO:
				x1 = cm.worldXtoLocalX(ox + points[pi++]);
				y1 = cm.worldYtoLocalY(oy + points[pi++]);
				x2 = cm.worldXtoLocalX(ox + points[pi++]);
				y2 = cm.worldYtoLocalY(oy + points[pi++]);
				path.quadTo(x1, y1, x2, y2);
				break;
			default:
				throw new IllegalArgumentException();
			}
		}
	}

	public static void addPolygonToPath(Path path, Point[] polygonPoints, ICoordinateMapper cm) {
		for (int i = 0; i < polygonPoints.length; i++) {
			Point p = polygonPoints[i];
			if (i == 0) {
				path.moveTo(cm.worldXtoLocalX(p.x), cm.worldYtoLocalY(p.y));
			}
			else {
				path.lineTo(cm.worldXtoLocalX(p.x), cm.worldYtoLocalY(p.y));
			}
		}
	}

	public static Rectangle getBounds(Path path) {
		float[] bounds = new float[4];
		path.getBounds(bounds);
		int x1 = (int) Math.floor(bounds[0]);
		int y1 = (int) Math.floor(bounds[1]);
		int x2 = (int) Math.ceil(bounds[0] + bounds[2]);
		int y2 = (int) Math.ceil(bounds[1] + bounds[3]);
		return new Rectangle(x1, y1, x2 - x1 + 1, y2 - y1 + 1);
	}

	public static PathData clone(PathData p) {
		PathData pd = new PathData();
		pd.points = p.points.clone();
		pd.types = p.types.clone();
		return pd;
	}

	public static void drawMarquee(GC g, Path p, int o) {
		int[] oldDash = g.getLineDash();

		o = o % 6;
		g.setLineWidth(1);

		// true marquee outline paths are not possible at this point
		// because swt does not allow a dashed line to start out with 0
		// in other words, it's not possible to start a dashed line
		// with empty space

		for (int i = 5; i >= 0; i--) {
			Color c = null;
			switch ((6 + i - o) % 6) {
			case 0:
				c = g.getDevice().getSystemColor(SWT.COLOR_WHITE);
				break;
			case 1:
				c = g.getDevice().getSystemColor(SWT.COLOR_GRAY);
				break;
			case 2:
				c = g.getDevice().getSystemColor(SWT.COLOR_DARK_GRAY);
				break;
			case 3:
				c = g.getDevice().getSystemColor(SWT.COLOR_BLACK);
				break;
			case 4:
				c = g.getDevice().getSystemColor(SWT.COLOR_BLACK);
				break;
			case 5:
				c = g.getDevice().getSystemColor(SWT.COLOR_BLACK);
				break;
			}

			if (i == 5) {
				g.setLineDash(null);
			}
			else {
				g.setLineDash(new int[] { g.getLineWidth() * (i + 1), g.getLineWidth() * (5 - i) });
			}

			g.setForeground(c);
			g.drawPath(p);
		}

		g.setLineDash(null);
	}

	public static final void convertWorldToLocal(ICoordinateMapper cm, PathData pathData) {
		float[] points = pathData.points;
		for (int pi = 0; pi < points.length; pi += 2) {
			points[pi] = cm.worldXtoLocalX(Math.round(points[pi]));
			points[pi + 1] = cm.worldYtoLocalY(Math.round(points[pi + 1]));
		}
	}

	public static PathDataConstructor createUnitPlus(float cp1) {
		float p0 = 0f, p1 = cp1, p2 = 1f - cp1, p3 = 1f;

		PathDataConstructor pdc = new PathDataConstructor();

		pdc.moveTo(p1, p1);
		pdc.lineTo(p1, p0);
		pdc.lineTo(p2, p0);
		pdc.lineTo(p2, p1);
		pdc.lineTo(p3, p1);
		pdc.lineTo(p3, p2);
		pdc.lineTo(p2, p2);
		pdc.lineTo(p2, p3);
		pdc.lineTo(p1, p3);
		pdc.lineTo(p1, p2);
		pdc.lineTo(p0, p2);
		pdc.lineTo(p0, p1);
		pdc.close();

		return pdc;
	}

	public static PathDataConstructor createUnitX(float cp1, float cp2) {
		float p0 = 0f, p1 = cp1, p2 = 0.5f, p3 = 1f - cp1, p4 = 1f;
		float q0 = 0f, q1 = cp2, q2 = 0.5f, q3 = 1f - cp2, q4 = 1f;

		PathDataConstructor pdc = new PathDataConstructor();

		pdc.moveTo(q1, p2);
		pdc.lineTo(p0, p1);
		pdc.lineTo(p1, p0);
		pdc.lineTo(p2, q1);
		pdc.lineTo(p3, p0);
		pdc.lineTo(p4, p1);
		pdc.lineTo(q3, p2);
		pdc.lineTo(p4, p3);
		pdc.lineTo(p3, p4);
		pdc.lineTo(p2, q3);
		pdc.lineTo(p1, p4);
		pdc.lineTo(p0, p3);
		pdc.close();

		return pdc;
	}
}
