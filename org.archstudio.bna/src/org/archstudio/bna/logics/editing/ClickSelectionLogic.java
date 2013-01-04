package org.archstudio.bna.logics.editing;

import java.util.List;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.archstudio.bna.utils.IBNAMouseListener;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.events.MouseEvent;

import com.google.common.collect.Iterables;

public class ClickSelectionLogic extends AbstractThingLogic implements IBNAMouseListener, IBNAMenuListener {

	protected ThingValueTrackingLogic valuesLogic;

	public ClickSelectionLogic() {
	}

	@Override
	protected void init() {
		super.init();
		valuesLogic = addThingLogic(ThingValueTrackingLogic.class);
	}

	private void removeAllSelections() {
		IBNAModel model = getBNAModel();
		if (model != null) {
			model.beginBulkChange();
			try {
				for (IHasMutableSelected t : Iterables.filter(
						BNAUtils.getThings(model, valuesLogic.getThingIDs(IHasSelected.SELECTED_KEY, Boolean.TRUE)),
						IHasMutableSelected.class)) {
					t.setSelected(false);
				}
			}
			finally {
				model.endBulkChange();
			}
		}
	}

	@Override
	public void mouseUp(IBNAView view, MouseEvent evt, List<IThing> t, ICoordinate location) {
	}

	@Override
	public void mouseDown(IBNAView view, MouseEvent evt, List<IThing> t, ICoordinate location) {
		if (evt.button == 1) {
			IHasMutableSelected mst = SystemUtils.firstOrNull(t, IHasMutableSelected.class);
			if (mst != null && UserEditableUtils.isEditableForAllQualities(mst, IHasMutableSelected.USER_MAY_SELECT)) {
				boolean controlPressed = BNAUtils.wasControlPressed(evt);
				boolean shiftPressed = BNAUtils.wasShiftPressed(evt);

				if (!controlPressed && !shiftPressed) {
					//Only deselect everything if the thing we're clicking on is not selected
					if (!mst.isSelected()) {
						removeAllSelections();
					}
					mst.setSelected(true);
				}
				else if (controlPressed && !shiftPressed) {
					//Toggle selection
					mst.setSelected(!mst.isSelected());
				}
				else if (shiftPressed && !controlPressed) {
					//Add to selection
					mst.setSelected(true);
				}
				else if (shiftPressed && controlPressed) {
					//Subtract from selection
					mst.setSelected(false);
				}
				return;
			}
			if (t.isEmpty()) {
				boolean controlPressed = BNAUtils.wasControlPressed(evt);
				boolean shiftPressed = BNAUtils.wasShiftPressed(evt);

				if (!controlPressed && !shiftPressed) {
					removeAllSelections();
				}
			}
		}
	}

	@Override
	public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager m) {
		/*
		 * We don't actually want to fill the menu here, but we want to change the selection before the menu really gets
		 * filled to reflect the click. If we clicked on something already selected, we leave the selection alone. If we
		 * click on something not selected, but selectable, we change the selection to be that thing. If we click on
		 * something not selectable, then we clear the selection.
		 */
		if (things.isEmpty()) {
			removeAllSelections();
		}
		else {
			IHasMutableSelected mst = SystemUtils.firstOrNull(things, IHasMutableSelected.class);
			if (mst != null && UserEditableUtils.isEditableForAllQualities(mst, IHasMutableSelected.USER_MAY_SELECT)) {

				if (!mst.isSelected()) {
					removeAllSelections();
				}
				mst.setSelected(true);

				return;
			}

			removeAllSelections();
		}
	}
}
