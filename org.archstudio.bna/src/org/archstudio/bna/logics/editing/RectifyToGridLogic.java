package org.archstudio.bna.logics.editing;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.ui.IBNAMenuListener2;
import org.archstudio.bna.utils.BNAAction;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
import org.archstudio.bna.utils.GridUtils;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;

public class RectifyToGridLogic extends AbstractThingLogic implements IBNAMenuListener2 {

	public RectifyToGridLogic(IBNAWorld world) {
		super(world);
	}

	@Override
	public void fillMenu(IBNAView view, ICoordinate location, ThingsAtLocation thingsAtLocation,
			IMenuManager menuManager) {
		BNAUtils.checkLock();

		if (thingsAtLocation.getThing() == null) {
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
				menuManager.add(rectifyAction);
				menuManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
			}
		}
	}
}
