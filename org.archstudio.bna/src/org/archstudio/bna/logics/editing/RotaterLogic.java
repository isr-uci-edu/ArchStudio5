package org.archstudio.bna.logics.editing;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.awt.geom.Point2D;
import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasAngle;
import org.archstudio.bna.facets.IHasMutableAngle;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.things.labels.AnchoredLabelThing;
import org.archstudio.bna.things.utility.RotaterThing;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.archstudio.bna.utils.IBNAMouseListener;
import org.archstudio.bna.utils.IBNAMouseMoveListener;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchActionConstants;

public class RotaterLogic extends AbstractThingLogic implements IBNAMouseListener, IBNAMouseMoveListener,
		IBNAMenuListener {

	MirrorValueLogic mirrorLogic;

	protected RotaterThing rt = null;
	protected boolean pressed = false;

	protected IThing originalThing = null;
	protected Integer originalValue = null;

	protected void init() {
		super.init();
		mirrorLogic = addThingLogic(MirrorValueLogic.class);
	}

	public void fillMenu(final IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu) {
		final AnchoredLabelThing tt = firstOrNull(things, AnchoredLabelThing.class);
		if (tt != null && UserEditableUtils.isEditableForAllQualities(tt, IHasMutableAngle.USER_MAY_CHANGE_ANGLE)) {
			originalThing = tt;
			IAction rotateAction = new Action("Rotate") {

				public void run() {
					rt = view.getBNAWorld().getBNAModel().addThing(new RotaterThing(null), tt);
					rt.setAngle(tt.getAngle());
					mirrorLogic.mirrorValue(tt, IHasAnchorPoint.ANCHOR_POINT_KEY, rt);
					mirrorLogic.mirrorValue(rt, IHasAngle.ANGLE_KEY, tt);
				}
			};
			menu.add(rotateAction);
			menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		}
	}

	public void mouseDown(IBNAView view, MouseEvent evt, List<IThing> things, ICoordinate location) {
		if (evt.button == 1) {
			if (rt != null) {
				Point lap = view.getCoordinateMapper().worldToLocal(rt.getAnchorPoint());
				Point lm = location.getLocalPoint();
				if (Point2D.distance(lap.x, lap.y, lm.x, lm.y) > rt.getRadius()) {
					view.getBNAWorld().getBNAModel().removeThing(rt);
					rt = null;
				}
				else if (view.getThingPeer(rt).isInThing(view, view.getCoordinateMapper(), location)) {
					pressed = true;
					originalValue = rt.get(IHasAngle.ANGLE_KEY);
				}
			}
		}
	}

	public void mouseUp(IBNAView view, MouseEvent evt, List<IThing> t, ICoordinate location) {
		if (pressed) {
			originalThing.set(IHasAngle.ANGLE_KEY, originalValue);
			BNAOperations.set("Rotate", getBNAModel(), originalThing, IHasAngle.ANGLE_KEY, rt.get(IHasAngle.ANGLE_KEY));
		}
		pressed = false;
	}

	public void mouseMove(IBNAView view, MouseEvent evt, List<IThing> things, ICoordinate location) {
		if (pressed) {
			Point anchorPointWorld = rt.getAnchorPoint();

			int rwx = anchorPointWorld.x;
			int rwy = anchorPointWorld.y;

			Point wPoint = location.getWorldPoint();
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
}
