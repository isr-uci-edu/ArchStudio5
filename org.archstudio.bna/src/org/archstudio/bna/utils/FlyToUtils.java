package org.archstudio.bna.utils;

import static org.archstudio.sysutils.SystemUtils.castOrNull;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.widgets.Control;

public class FlyToUtils {

	public static synchronized void flyTo(IBNAView view, final Point toWorldPoint) {

		//if the given view is not the top-level view, then translate
		//the world coordinates into world coordinates for the top view.
		//Then change the view to be the top level view.
		if (view.getParentView() != null) {
			IBNAView topLevelView = view.getParentView();
			while (topLevelView.getParentView() != null) {
				topLevelView = topLevelView.getParentView();
			}
			view.getCoordinateMapper().worldToLocal(toWorldPoint);
			topLevelView.getCoordinateMapper().localToWorld(toWorldPoint);
			view = topLevelView;
		}

		final Control control = view.getControl();
		if (control == null) {
			return;
		}
		final IMutableCoordinateMapper cm = castOrNull(view.getCoordinateMapper(), IMutableCoordinateMapper.class);
		if (cm == null) {
			return;
		}
		final double originalScale = cm.getLocalScale();
		final Point localCenter = BNAUtils.toPoint(control.getSize()).scale(0.5d);

		Thread flyThread = new Thread() {
			@Override
			public void run() {

				try {
					Point worldStart = cm.localToWorld(localCenter.getCopy());
					Point worldEnd = toWorldPoint.getCopy();
					Point worldDiff = worldEnd.getTranslated(worldStart.getNegated());

					for (double d = 0; d < Math.PI / 2; d += Math.PI / 8) {
						long startTime = System.currentTimeMillis();
						double transposeFactor = Math.sin(d);
						double scaleFactor = 1; // / Math.sin(d * 2);
						final Point worldIntermediate = worldStart.getTranslated(worldDiff.getScaled(transposeFactor));
						final double intermediateScale = originalScale * scaleFactor;
						SWTWidgetUtils.sync(control, new Runnable() {
							@Override
							public void run() {
								cm.setLocalScaleAndAlign(intermediateScale, localCenter, worldIntermediate);
							}
						});
						long elapsedTime = System.currentTimeMillis() - startTime;
						long delay = 50 - elapsedTime;
						if (delay > 0) {
							try {
								Thread.sleep(delay);
							}
							catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
				finally {
					// finally, set the cm to the correct scale and position
					SWTWidgetUtils.async(control, new Runnable() {
						@Override
						public void run() {
							cm.setLocalScaleAndAlign(originalScale, localCenter, toWorldPoint);
						}
					});
				}
			}
		};
		flyThread.start();
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
