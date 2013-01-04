package org.archstudio.swtutils;

import org.eclipse.swt.graphics.Point;

/**
 * A B-Spline calculator.
 * 
 * Ported by E. M. Dashofy from C++ code released into the public domain with the following notice:
 * 
 * <code>Copyright 1994 by Keith Vertanen (vertankd@cda.mrs.umn.edu)</code> <br>
 * <code>Released to the public domain (your mileage may vary)</code>
 * 
 * @author Eric M. Dashofy <a href="mailto:edashofy@ics.uci.edu">edashofy@ics.uci.edu</a>
 */
public class BSpline {

	public static final int DEFAULT_DEGREE = 4;

	private static class point {
		double x;
		double y;
		double z;
	};

	/**
	 * Calculate the points of a B-spline given an array of control points and some other parameters.
	 * 
	 * @param controlPoints
	 *            A set of control points for the B-Spline
	 * @param splinePointsPerMidpoint
	 *            How many points to add per midpoint (should be an even number), the more you put, the curvier the
	 *            spline.
	 * @return An ordered set of line segment endpoints that should be drawn to render the B-Spline
	 */
	public static Point[] bspline(Point[] controlPoints, int splinePointsPerMidpoint) {
		if (controlPoints.length <= 2) { //A bspline with 2 control points is called a line :)
			return controlPoints;
		}

		int n = controlPoints.length - 1;

		int t = DEFAULT_DEGREE;
		if (n + 2 <= t) {
			t = n + 1;
		}

		point[] control = new point[controlPoints.length];
		for (int i = 0; i < controlPoints.length; i++) {
			point p = new point();
			p.x = controlPoints[i].x;
			p.y = controlPoints[i].y;
			p.z = 0.0d;
			control[i] = p;
		}

		int numPoints = controlPoints.length;

		int numMidPoints = controlPoints.length - 2;
		if (numMidPoints > 0) {
			numPoints += numMidPoints * splinePointsPerMidpoint; //Add four additional control points for each midpoint.
		}
		//numPoints += 4; //Two additional for each endpoint

		point[] output = new point[numPoints];
		for (int i = 0; i < output.length; i++) {
			output[i] = new point();
		}

		//Call bspline function
		bspline(n, t, control, output, output.length);

		Point[] outputPoints = new Point[output.length];
		for (int i = 0; i < output.length; i++) {
			outputPoints[i] = new Point((int) output[i].x, (int) output[i].y);
		}

		return outputPoints;
	}

	/**
	 * Calculate the points of a B-spline given an array of control points and some other parameters.
	 * 
	 * @param n
	 *            the number of control points minus 1
	 * @param t
	 *            the degree of the polynomial plus 1
	 * @param control
	 *            control point array made up of point stucture
	 * @param output
	 *            array in which the calculate spline points are to be put
	 * @param num_output
	 *            how many points on the spline are to be calculated
	 */
	private static void bspline(int n, int t, point[] control, point[] output, int num_output) {
		int[] u;
		double increment, interval;
		point calcxyz = new point();
		int output_index;

		u = new int[n + t + 1];
		compute_intervals(u, n, t);

		increment = (double) (n - t + 2) / (num_output - 1); // how much parameter goes up each time
		interval = 0;

		for (output_index = 0; output_index < num_output - 1; output_index++) {
			compute_point(u, n, t, interval, control, calcxyz);
			output[output_index].x = calcxyz.x;
			output[output_index].y = calcxyz.y;
			output[output_index].z = calcxyz.z;
			interval = interval + increment; // increment our parameter
		}
		output[num_output - 1].x = control[n].x; // put in the last point
		output[num_output - 1].y = control[n].y;
		output[num_output - 1].z = control[n].z;
	}

	/*
	 * void main() { int *u; int n,t,i; n=7; // number of control points = n+1 t=4; // degree of polynomial = t-1
	 * 
	 * point[] pts; // allocate our control point array pts = new point[n+1];
	 * 
	 * // randomize(); // for (i=0; i<=n; i++) // assign the control points randomly // { //
	 * (pts[i].x)=random(100)+(i*600/n); // (pts[i].y)=random(500); // (pts[i].z)=random(500); // }
	 * 
	 * pts[0].x=10; pts[0].y=100; pts[0].z=0; pts[1].x=200; pts[1].y=100; pts[1].z=0; pts[2].x=345; pts[2].y=300;
	 * pts[2].z=0; pts[3].x=400; pts[3].y=250; pts[3].z=0; pts[4].x=500; pts[4].y=550; pts[4].z=0; pts[5].x=550;
	 * pts[5].y=150; pts[5].z=0; pts[6].x=570; pts[6].y=50; pts[6].z=0; pts[7].x=600; pts[7].y=100; pts[7].z=0;
	 * 
	 * int resolution = 100; // how many points our in our output array point *out_pts; out_pts = new point[resolution];
	 * 
	 * bspline(n, t, pts, out_pts, resolution); if (set_graph()) { setcolor(69); for (i=0; i<=n; i++)
	 * circle(pts[i].x,pts[i].y,2); // put circles at control points circle(pts[0].x,pts[0].y,0); // drop the pen down
	 * at first control point for (i=0; i<resolution; i++) { setcolor(i); // have a little fun with the colors
	 * putpixel(out_pts[i].x,out_pts[i].y,WHITE); } } }
	 */
	private static double blend(int k, int t, int[] u, double v) // calculate the blending value
	{
		double value;

		if (t == 1) // base case for the recursion
		{
			if (u[k] <= v && v < u[k + 1]) {
				value = 1;
			}
			else {
				value = 0;
			}
		}
		else {
			if (u[k + t - 1] == u[k] && u[k + t] == u[k + 1]) {
				value = 0;
			}
			else if (u[k + t - 1] == u[k]) {
				// if a term's denominator is zero,use just the other
				value = (u[k + t] - v) / (u[k + t] - u[k + 1]) * blend(k + 1, t - 1, u, v);
			}
			else if (u[k + t] == u[k + 1]) {
				value = (v - u[k]) / (u[k + t - 1] - u[k]) * blend(k, t - 1, u, v);
			}
			else {
				value = (v - u[k]) / (u[k + t - 1] - u[k]) * blend(k, t - 1, u, v) + (u[k + t] - v)
						/ (u[k + t] - u[k + 1]) * blend(k + 1, t - 1, u, v);
			}
		}
		return value;
	}

	private static void compute_intervals(int[] u, int n, int t) // figure out the knots
	{
		int j;

		for (j = 0; j <= n + t; j++) {
			if (j < t) {
				u[j] = 0;
			}
			else if (t <= j && j <= n) {
				u[j] = j - t + 1;
			}
			else if (j > n) {
				u[j] = n - t + 2; // if n-t=-2 then we're screwed, everything goes to 0
			}
		}
	}

	private static void compute_point(int[] u, int n, int t, double v, point[] control, point output) {
		int k;
		double temp;

		// initialize the variables that will hold our outputted point
		output.x = 0;
		output.y = 0;
		output.z = 0;

		for (k = 0; k <= n; k++) {
			temp = blend(k, t, u, v); // same blend is used for each dimension coordinate
			output.x = output.x + control[k].x * temp;
			output.y = output.y + control[k].y * temp;
			output.z = output.z + control[k].z * temp;
		}
	}

}
