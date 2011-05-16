package org.archstudio.bna.utils;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.widgets.Composite;

public class FlyToUtils {

	public static synchronized void flyTo(IBNAView view, int wx, int wy) {
		//if the given view is not the top-level view, then translate
		//the world coordinates into world coordinates for the top view.
		//Then change the view to be the top level view.
		if (view.getParentView() != null) {
			IBNAView topLevelView = view.getParentView();
			while (topLevelView.getParentView() != null) {
				topLevelView = topLevelView.getParentView();
			}
			int lx = view.getCoordinateMapper().worldXtoLocalX(wx);
			int ly = view.getCoordinateMapper().worldYtoLocalY(wy);

			wx = topLevelView.getCoordinateMapper().localXtoWorldX(lx);
			wy = topLevelView.getCoordinateMapper().localYtoWorldY(ly);
			view = topLevelView;
		}

		final IBNAView fview = view;
		final int worldX = wx;
		final int worldY = wy;

		ICoordinateMapper nmcm = view.getCoordinateMapper();
		if (!(nmcm instanceof IMutableCoordinateMapper)) {
			return;
		}
		final IMutableCoordinateMapper cm = (IMutableCoordinateMapper) nmcm;

		final Composite c = BNAUtils.getParentComposite(view);
		if (c != null) {
			final int componentWidth = c.getSize().x;
			final int componentHeight = c.getSize().y;

			Thread flyThread = new Thread() {

				@Override
				public void run() {
					synchronized (FlyToUtils.class) {
						int lcx = componentWidth / 2;
						int lcy = componentHeight / 2;

						int ox = cm.localXtoWorldX(0);
						int oy = cm.localYtoWorldY(0);

						int cx = cm.localXtoWorldX(lcx);
						int cy = cm.localYtoWorldY(lcy);

						//System.out.println("Calculating fly to from " + cx + "," + cy + " to " + worldX + "," + worldY + ".");

						int dx = worldX - cx;
						int dy = worldY - cy;

						int lineLength = (int) Math.round(Math.sqrt(dx * dx + dy * dy));

						int[] segLengths = calcSteps(lineLength, ((int) Math.round(lg(lineLength)) * 2));
						//int[] segLengths = calcSteps(lineLength, 20);

						Point o = new Point(ox, oy);
						Point d = new Point(ox + dx, oy + dy);

						//System.out.println("O = " + o.x + "," + o.y + " ; D =  " + d.x + "," + d.y + ".");
						double oscale = cm.getScale();

						//System.out.println(c2.util.ArrayUtils.arrayToString(segLengths));

						double scaleFactor = oscale / 50.0d;

						Point lastPoint = o;
						for (int i = 0; i < segLengths.length; i++) {

							IBNAModel model = fview.getBNAWorld().getBNAModel();
							model.beginBulkChange();
							try {
								Point p = BNAUtils.toPoint(SWTWidgetUtils.calcPointOnLineAtDist(BNAUtils.toSwtPoint(o),
										BNAUtils.toSwtPoint(d), segLengths[i]));

								if (i < segLengths.length / 2) {
									cm.rescaleRelative(-scaleFactor);
								}
								if (i > segLengths.length / 2) {
									cm.rescaleRelative(scaleFactor);
								}

								cm.repositionRelative(p.x - lastPoint.x, p.y - lastPoint.y);
								lastPoint = p;
							}
							finally {
								model.endBulkChange();
							}

							try {
								Thread.sleep(100);
							}
							catch (InterruptedException e) {
							}
						}

						cm.rescaleAbsolute(oscale);
						cm.repositionAbsolute(ox + dx, oy + dy);

					}
				}
			};
			flyThread.start();
		}
	}

	public static int[] calcSteps(int numPixels, int numSteps) {
		if (numSteps % 2 == 0) {
			numSteps++;
		}

		int[] steps = new int[numSteps];

		int np = numPixels;
		int mp = numSteps / 2;
		for (int i = mp; i >= 0; i--) {
			np = np / 2;
			steps[i] = np;
		}

		//int j = 1;
		for (int i = mp + 1; i < numSteps; i++) {
			steps[i] = steps[mp] + steps[mp] - steps[numSteps - i - 1];
			//j++;
		}
		return steps;
	}

	//Base-2 logarithm
	private static double lg(double x) {
		double a = Math.log(x);
		double b = Math.log(2.0);
		return a / b;
	}

}
