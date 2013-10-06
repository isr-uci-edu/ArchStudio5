package org.archstudio.bna.utils;

import static org.archstudio.sysutils.SystemUtils.castOrNull;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.swt.graphics.Point;
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

		final Control control = view.getBNAUI().getComposite();
		if (control == null) {
			return;
		}
		final IMutableCoordinateMapper cm = castOrNull(view.getCoordinateMapper(), IMutableCoordinateMapper.class);
		if (cm == null) {
			return;
		}
		final double originalScale = cm.getLocalScale();

		SWTWidgetUtils.async(control, new Runnable() {

			@Override
			public void run() {

				final Point localSize = control.getSize();
				final Point localCenter = new Point(localSize.x / 2, localSize.y / 2);

				Thread flyThread = new Thread(FlyToUtils.class.getName()) {

					@Override
					public void run() {

						try {
							Point worldStart = cm.localToWorld(localCenter);
							Point worldEnd = BNAUtils.clone(toWorldPoint);
							Point worldDiff = new Point(worldEnd.x - worldStart.x, worldEnd.y - worldStart.y);

							long duration = 1000;
							long startTime = System.currentTimeMillis();
							long currentTime;
							long endTime = startTime + duration;
							while ((currentTime = System.currentTimeMillis()) < endTime) {
								double d = Math.PI / 2 * (currentTime - startTime) / duration;
								double transposeFactor = Math.sin(d);
								final Point worldIntermediate = new Point(//
										worldStart.x + BNAUtils.round(worldDiff.x * transposeFactor),//
										worldStart.y + BNAUtils.round(worldDiff.y * transposeFactor));
								final double intermediateScale = Math.max(0.0001, originalScale - originalScale * 0.7
										* Math.sin(d * 2));
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
		});
	}

}
