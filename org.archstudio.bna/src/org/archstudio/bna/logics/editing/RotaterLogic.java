package org.archstudio.bna.logics.editing;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.things.utility.RotaterThing;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.IBNAMouseClickListener;
import org.archstudio.bna.utils.IBNAMouseListener;
import org.archstudio.bna.utils.IBNAMouseMoveListener;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.events.MouseEvent;

public class RotaterLogic extends AbstractThingLogic implements IBNAMouseListener, IBNAMouseClickListener,
		IBNAMouseMoveListener {

	protected RotaterThing rt;
	protected boolean pressed = false;

	@Override
	public void mouseDown(IBNAView view, MouseEvent evt, Iterable<IThing> things, ICoordinate location) {
		if (evt.button == 1) {
			rt = firstOrNull(things, RotaterThing.class);
			pressed = rt != null;
		}
	}

	@Override
	public void mouseUp(IBNAView view, MouseEvent evt, Iterable<IThing> t, ICoordinate location) {
		pressed = false;
		rt = null;
	}

	@Override
	public void mouseMove(IBNAView view, MouseEvent evt, Iterable<IThing> things, ICoordinate location) {
		if (pressed) {
			Point anchorPointWorld = rt.getAnchorPoint();

			int rwx = anchorPointWorld.x;
			int rwy = anchorPointWorld.y;

			Point wPoint = location.getWorldPoint(new Point());
			int dx = wPoint.x - rwx;
			int dy = wPoint.y - rwy;

			double angleInRadians = Math.atan((double) dy / (double) dx);
			double angleInDegrees = angleInRadians * 180 / Math.PI;
			if (dx < 0) {
				angleInDegrees = (angleInDegrees + 180) % 360;
			}
			//double rvsAngleInDegrees = 360 - angleInDegrees;
			int intAngle = BNAUtils.round(angleInDegrees);
			int increment = rt.getAdjustmentIncrement();
			if (increment > 1) {
				while (intAngle % increment != 0) {
					intAngle = (intAngle + 1) % 360;
				}
			}
			rt.setAngle(intAngle);
		}
	}

	@Override
	public void mouseClick(IBNAView view, MouseEvent evt, Iterable<IThing> t, ICoordinate location) {
	}

	@Override
	public void mouseDoubleClick(IBNAView view, MouseEvent evt, Iterable<IThing> things, ICoordinate location) {
		if (evt.button == 1) {
			IThing t = firstOrNull(things);
			if (t instanceof RotaterThing) {
				view.getBNAWorld().getBNAModel().removeThing(t);
			}
		}
	}
}
