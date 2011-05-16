package org.archstudio.bna.logics.editing;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasAngle;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.coordinating.AbstractMirrorValueLogic;
import org.archstudio.bna.things.labels.TagThing;
import org.archstudio.bna.things.utility.RotaterThing;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;

public class RotateTagsLogic extends AbstractThingLogic implements IBNAMenuListener {

	public void fillMenu(final IBNAView view, Point localPoint, Point worldPoint, final Iterable<IThing> things,
			IMenuManager m) {
		if (things instanceof TagThing) {
			IAction rotateAction = new Action("Rotate Tag") {

				@Override
				public void run() {
					TagThing tt = (TagThing) things;
					RotaterThing rt = new RotaterThing();
					rt.setAngle(tt.getAngle());

					AbstractMirrorValueLogic.mirrorValue(tt, IHasAnchorPoint.ANCHOR_POINT_KEY, rt);
					AbstractMirrorValueLogic.mirrorValue(rt, IHasAngle.ANGLE_KEY, tt);
					view.getBNAWorld().getBNAModel().addThing(rt);
				}
			};
			m.add(rotateAction);
			m.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		}
	}
}
