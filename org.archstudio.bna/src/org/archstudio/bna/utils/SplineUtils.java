package org.archstudio.bna.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.archstudio.bna.IThing;
import org.eclipse.draw2d.geometry.Point;

public class SplineUtils {

	public static class SplineData {

		public List<Point> allPoints;
		public boolean includesEndpoint1;
		public boolean includesEndpoint2;

		public List<Point> getMidpoints() {
			return allPoints.subList(includesEndpoint1 ? 1 : 0, allPoints.size() - (includesEndpoint2 ? 1 : 0));
		}

		public List<Point> getEndpoints() {
			List<Point> endpoints = new ArrayList<Point>();
			if (includesEndpoint1) {
				endpoints.add(allPoints.get(0));
			}
			if (includesEndpoint2) {
				endpoints.add(allPoints.get(allPoints.size() - 1));
			}
			return endpoints;
		}
	}

	public static SplineData getPoints(IThing t) {
		SplineData sd = new SplineData();

		sd.allPoints = new ArrayList<Point>();
		if (t instanceof IHasEndpoints) {
			Point p = ((IHasEndpoints) t).getEndpoint1();
			if (p != null) {
				sd.includesEndpoint1 = true;
				sd.allPoints.add(p);
			}
		}
		if (t instanceof IHasMidpoints) {
			Point[] points = ((IHasMidpoints) t).getMidpoints();
			if (points != null) {
				sd.allPoints.addAll(Arrays.asList(points));
			}
		}
		if (t instanceof IHasEndpoints) {
			Point p = ((IHasEndpoints) t).getEndpoint2();
			if (p != null) {
				sd.includesEndpoint2 = true;
				sd.allPoints.add(p);
			}
		}

		return sd;
	}

	public static void setPoints(IThing t, SplineData sd) {
		if (t instanceof IHasMutableEndpoints && sd.includesEndpoint1) {
			((IHasMutableEndpoints) t).setEndpoint1(sd.allPoints.get(0));
		}
		if (t instanceof IHasMutableEndpoints && sd.includesEndpoint2) {
			((IHasMutableEndpoints) t).setEndpoint2(sd.allPoints.get(sd.allPoints.size() - 1));
		}
		if (t instanceof IHasMutableMidpoints) {
			((IHasMutableMidpoints) t).setMidpoints(sd.getMidpoints().toArray(new Point[0]));
		}
	}
}
