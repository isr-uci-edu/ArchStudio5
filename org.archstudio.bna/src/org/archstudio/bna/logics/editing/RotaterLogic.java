package org.archstudio.bna.logics.editing;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAMouseListener;
import org.archstudio.bna.IBNAMouseMoveListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasMutableAngle;
import org.archstudio.bna.things.utility.RotaterThing;

public class RotaterLogic extends AbstractThingLogic implements IBNAMouseListener, IBNAMouseMoveListener {

	protected RotaterThing rt;
	protected boolean pressed = false;

	public void mouseDown(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
		if (evt.button == 1) {
			if (t instanceof RotaterThing) {
				pressed = true;
				rt = (RotaterThing) t;
			}
		}
	}

	public void mouseUp(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
		pressed = false;
		rt = null;
	}

	public void mouseMove(IBNAView view, MouseEvent e, IThing t, int worldX, int worldY) {
		if (pressed) {
			Point anchorPointWorld = rt.getAnchorPoint();

			int rwx = anchorPointWorld.x;
			int rwy = anchorPointWorld.y;

			int dx = worldX - rwx;
			int dy = worldY - rwy;

			double angleInRadians = Math.atan((double) dy / (double) dx);
			double angleInDegrees = (angleInRadians * 180) / Math.PI;
			if (dx < 0)
				angleInDegrees = (angleInDegrees + 180) % 360;
			//double rvsAngleInDegrees = 360 - angleInDegrees;
			int intAngle = BNAUtils.round(angleInDegrees);
			int increment = rt.getAdjustmentIncrement();
			if (increment > 1) {
				while ((intAngle % increment) != 0) {
					intAngle = (intAngle + 1) % 360;
				}
			}
			rt.setAngle(intAngle);

			String[] rotatedThingIDs = rt.getRotatedThingIDs();
			if (rotatedThingIDs != null) {
				for (int i = 0; i < rotatedThingIDs.length; i++) {
					IThing rotatedThing = view.getWorld().getBNAModel().getThing(rotatedThingIDs[i]);
					if ((rotatedThing != null) && (rotatedThing instanceof IHasMutableAngle)) {
						((IHasMutableAngle) rotatedThing).setAngle(intAngle);
					}
				}
			}
		}
	}

	public void mouseClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
	}

	public void mouseDoubleClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
		if (evt.button == 1) {
			if (t instanceof RotaterThing) {
				view.getWorld().getBNAModel().removeThing(t);
			}
		}
	}
}
