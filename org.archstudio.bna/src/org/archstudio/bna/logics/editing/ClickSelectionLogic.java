package org.archstudio.bna.logics.editing;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.MouseType;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.ui.IBNAMenuListener;
import org.archstudio.bna.ui.IBNAMouseListener;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.events.MouseEvent;

public class ClickSelectionLogic extends AbstractThingLogic implements IBNAMouseListener, IBNAMenuListener {

	protected final ThingValueTrackingLogic valueLogic;

	public ClickSelectionLogic(IBNAWorld world) {
		super(world);
		valueLogic = logics.addThingLogic(ThingValueTrackingLogic.class);
	}

	private void removeAllSelections() {
		model.beginBulkChange();
		try {
			for (IHasMutableSelected t : valueLogic.getThings(IHasSelected.SELECTED_KEY, Boolean.TRUE,
					IHasMutableSelected.class)) {
				t.setSelected(false);
			}
		}
		finally {
			model.endBulkChange();
		}
	}

	@Override
	synchronized public void mouseUp(IBNAView view, MouseType type, MouseEvent evt, List<IThing> t, ICoordinate location) {
	}

	@Override
	synchronized public void mouseDown(IBNAView view, MouseType type, MouseEvent evt, List<IThing> t,
			ICoordinate location) {
		if (evt.button == 1) {
			IHasMutableSelected selectableThing = Assemblies.getEditableThing(model, firstOrNull(t),
					IHasMutableSelected.class, IHasMutableSelected.USER_MAY_SELECT);
			if (selectableThing != null) {
				boolean controlPressed = BNAUtils.wasControlPressed(evt);
				boolean shiftPressed = BNAUtils.wasShiftPressed(evt);

				if (!controlPressed && !shiftPressed) {
					//Only deselect everything if the thing we're clicking on is not selected
					if (!selectableThing.isSelected()) {
						removeAllSelections();
					}
					selectableThing.setSelected(true);
				}
				else if (controlPressed && !shiftPressed) {
					//Toggle selection
					selectableThing.setSelected(!selectableThing.isSelected());
				}
				else if (shiftPressed && !controlPressed) {
					//Add to selection
					selectableThing.setSelected(true);
				}
				else if (shiftPressed && controlPressed) {
					//Subtract from selection
					selectableThing.setSelected(false);
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
	synchronized public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager m) {
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
			IHasMutableSelected mst = Assemblies.getEditableThing(model, firstOrNull(things),
					IHasMutableSelected.class, IHasMutableSelected.USER_MAY_SELECT);
			if (mst != null) {

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
