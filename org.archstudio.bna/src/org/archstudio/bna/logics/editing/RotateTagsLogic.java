package org.archstudio.bna.logics.editing;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasAngle;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.things.labels.TagThing;
import org.archstudio.bna.things.utility.RotaterThing;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;

public class RotateTagsLogic extends AbstractThingLogic implements IBNAMenuListener {

	MirrorValueLogic mirrorLogic;

	@Override
	protected void init() {
		super.init();
		mirrorLogic = addThingLogic(MirrorValueLogic.class);
	}

	@Override
	public void fillMenu(final IBNAView view, Iterable<IThing> things, ICoordinate location, IMenuManager menu) {
		final TagThing tt = firstOrNull(things, TagThing.class);
		if (tt != null) {
			IAction rotateAction = new Action("Rotate Tag") {

				@Override
				public void run() {
					RotaterThing rt = view.getBNAWorld().getBNAModel().addThing(new RotaterThing(null));
					rt.setAngle(tt.getAngle());

					mirrorLogic.mirrorValue(tt, IHasAnchorPoint.ANCHOR_POINT_KEY, rt);
					mirrorLogic.mirrorValue(rt, IHasAngle.ANGLE_KEY, tt);
				}
			};
			menu.add(rotateAction);
			menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		}
	}
}
