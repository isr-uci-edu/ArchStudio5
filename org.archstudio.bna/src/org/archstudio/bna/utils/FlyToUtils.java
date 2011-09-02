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

		Thread flyThread = new Thread(FlyToUtils.class.getName()) {
			@Override
			public void run() {

				try {
					Point worldStart = cm.localToWorld(localCenter.getCopy());
					Point worldEnd = toWorldPoint.getCopy();
					Point worldDiff = worldEnd.getTranslated(worldStart.getNegated());

					long duration = 1000;
					long startTime = System.currentTimeMillis();
					long currentTime;
					long endTime = startTime + duration;
					while ((currentTime = System.currentTimeMillis()) < endTime) {
						double d = Math.PI / 2 * (currentTime - startTime) / duration;
						double transposeFactor = Math.sin(d);
						final Point worldIntermediate = worldStart.getTranslated(worldDiff.getScaled(transposeFactor));
						final double intermediateScale = Math.max(0.0001, originalScale - originalScale * 0.7 * Math.sin(d * 2));
						SWTWidgetUtils.sync(control, new Runnable() {
							@Override
							public void run() {
								cm.setLocalScaleAndAlign(intermediateScale, localCenter, worldIntermediate);
							}
						});
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

}
