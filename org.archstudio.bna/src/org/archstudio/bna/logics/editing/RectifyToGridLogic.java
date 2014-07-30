package org.archstudio.bna.logics.editing;

import java.util.List;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.ui.IBNAMenuListener;
import org.archstudio.bna.utils.BNAAction;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.GridUtils;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;

public class RectifyToGridLogic extends AbstractThingLogic implements IBNAMenuListener {

	public RectifyToGridLogic(IBNAWorld world) {
		super(world);
	}

	@Override
	public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager m) {
		BNAUtils.checkLock();

		if (things.isEmpty()) {
			final IBNAModel model = view.getBNAWorld().getBNAModel();
			if (GridUtils.getGridSpacing(world) != 0) {
				IAction rectifyAction = new BNAAction("Rectify Diagram to Grid") {

					@Override
					public void runWithLock() {
						Runnable undoRunnable = BNAOperations.takeSnapshotOfLocations(model, model.getAllThings());
						GridUtils.rectifyToGrid(world);
						BNAOperations.runnable("Rectify", undoRunnable,
								BNAOperations.takeSnapshotOfLocations(model, model.getAllThings()), false);
					}
				};
				m.add(rectifyAction);
				m.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
			}
		}
	}
}
