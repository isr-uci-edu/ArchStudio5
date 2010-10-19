package org.archstudio.bna.logics.navigating;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Widget;

import org.archstudio.bna.*;

public class MouseWheelZoomingLogic extends AbstractThingLogic implements IBNAUntypedListener {

	public static final int SCALE_TO_ORIGIN = 100;
	public static final int SCALE_TO_CENTER = 200;

	protected int scalePolicy = SCALE_TO_CENTER;

	public MouseWheelZoomingLogic() {
		this(SCALE_TO_CENTER);
	}

	public MouseWheelZoomingLogic(int scalePolicy) {
		super();
		this.scalePolicy = scalePolicy;
	}

	public void handleEvent(IBNAView view, Event event) {
		//Only handle events for the top world
		if (view.getParentComposite() == null)
			return;

		if (event.type != SWT.MouseWheel)
			return;
		ICoordinateMapper cm = view.getCoordinateMapper();
		if (cm instanceof IMutableCoordinateMapper) {
			IMutableCoordinateMapper mcm = (IMutableCoordinateMapper) cm;
			double scale = mcm.getScale();

			double newScale = (event.count < 0) ? ZoomUtils.getNextLowestZoomValue(scale) : ZoomUtils.getNextHighestZoomValue(scale);

			if (scalePolicy == SCALE_TO_CENTER) {
				Widget w = event.widget;
				if (w instanceof Control) {
					Point size = ((Control) w).getSize();
					int lcx = size.x / 2;
					int lcy = size.y / 2;
					int worldCenterX = cm.localXtoWorldX(lcx);
					int worldCenterY = cm.localYtoWorldY(lcy);

					mcm.rescaleAbsolute(newScale);

					int newWorldCenterX = cm.localXtoWorldX(lcx);
					int newWorldCenterY = cm.localYtoWorldY(lcy);
					int dwcx = newWorldCenterX - worldCenterX;
					int dwcy = newWorldCenterY - worldCenterY;
					mcm.repositionRelative(-dwcx, -dwcy);
				}
			}
			else {
				mcm.rescaleAbsolute(newScale);
			}

			event.doit = false;
		}
	}

}
