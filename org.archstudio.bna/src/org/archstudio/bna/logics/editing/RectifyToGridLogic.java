package org.archstudio.bna.logics.editing;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.GridUtils;
import org.archstudio.bna.IBNAMenuListener;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;

public class RectifyToGridLogic extends AbstractThingLogic implements IBNAMenuListener {

	public void fillMenu(IBNAView view, IMenuManager m, int localX, int localY, IThing t, int worldX, int worldY) {
		if (t == null) {
			final IBNAModel model = view.getWorld().getBNAModel();
			if (GridUtils.getGridSpacing(model) != 0) {
				IAction rectifyAction = new Action("Rectify Diagram to Grid") {
					public void run() {
						GridUtils.rectifyToGrid(model);
					}
				};
				m.add(rectifyAction);
				m.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
			}
		}
	}
}
