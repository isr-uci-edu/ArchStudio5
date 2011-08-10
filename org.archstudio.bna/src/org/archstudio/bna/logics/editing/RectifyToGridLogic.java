package org.archstudio.bna.logics.editing;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.GridUtils;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;

import com.google.common.collect.Iterables;

public class RectifyToGridLogic extends AbstractThingLogic implements IBNAMenuListener {

	@Override
	public void fillMenu(IBNAView view, Iterable<IThing> things, ICoordinate location, IMenuManager m) {
		if (Iterables.isEmpty(things)) {
			final IBNAModel model = view.getBNAWorld().getBNAModel();
			if (GridUtils.getGridSpacing(model) != 0) {
				IAction rectifyAction = new Action("Rectify Diagram to Grid") {
					@Override
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
