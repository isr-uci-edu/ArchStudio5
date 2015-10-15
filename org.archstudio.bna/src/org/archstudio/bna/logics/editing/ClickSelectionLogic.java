package org.archstudio.bna.logics.editing;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.constants.MouseType;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.logics.background.RotatingOffsetLogic;
import org.archstudio.bna.ui.IBNAMenuListener2;
import org.archstudio.bna.ui.IBNAMouseClickListener2;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.events.MouseEvent;

public class ClickSelectionLogic extends SelectionLogic implements IBNAMouseClickListener2, IBNAMenuListener2 {
	public ClickSelectionLogic(IBNAWorld world) {
		super(world);
		logics.addThingLogic(RotatingOffsetLogic.class);
	}

	@Override
	public void mouseDown(IBNAView view, MouseType type, MouseEvent evt, ICoordinate location,
			ThingsAtLocation thingsAtLocation) {
		BNAUtils.checkLock();
		if (evt.button == 1 && thingsAtLocation.getThingAtLocation() != null) {
			IHasMutableSelected selectableThing = Assemblies.getEditableThing(model, thingsAtLocation.getThing(),
					IHasMutableSelected.class, IHasMutableSelected.USER_MAY_SELECT);
			if (selectableThing != null) {
				setWorldWithSelectionFocus(world);
				boolean controlPressed = BNAUtils.wasControlPressed(evt);
				boolean shiftPressed = BNAUtils.wasShiftPressed(evt);

				if (!controlPressed && !shiftPressed) {
					// Only deselect everything if the thing we're clicking on is not selected
					if (!selectableThing.isSelected()) {
						unselectAllThings();
					}
					selectableThing.setSelected(true);
				}
				else if (controlPressed && !shiftPressed) {
					// Toggle selection
					selectableThing.setSelected(!selectableThing.isSelected());
				}
				else if (shiftPressed && !controlPressed) {
					// Add to selection
					selectableThing.setSelected(true);
				}
				else if (shiftPressed && controlPressed) {
					// Subtract from selection
					selectableThing.setSelected(false);
				}
				return;
			}
		}
		else if (evt.button == 1) {
			if (thingsAtLocation.getViewAtLocation() != null) {
				boolean controlPressed = BNAUtils.wasControlPressed(evt);
				boolean shiftPressed = BNAUtils.wasShiftPressed(evt);

				if (!controlPressed && !shiftPressed) {
					setWorldWithSelectionFocus(world);
					unselectAllThings();
				}
			}
		}
	}

	@Override
	public void mouseUp(IBNAView view, MouseType type, MouseEvent evt, ICoordinate location,
			ThingsAtLocation thingsAtLocation) {
	}

	@Override
	public void mouseClick(IBNAView view, MouseType type, MouseEvent evt, ICoordinate location,
			ThingsAtLocation thingsAtLocation) {
	}

	@Override
	public void fillMenu(IBNAView view, ICoordinate location, ThingsAtLocation things, IMenuManager menu) {
		BNAUtils.checkLock();
		/*
		 * We don't actually want to fill the menu here, but we want to change the selection before the menu really gets
		 * filled to reflect the click. If we clicked on something already selected, we leave the selection alone. If we
		 * click on something not selected, but selectable, we change the selection to be that thing. If we click on
		 * something not selectable, then we clear the selection.
		 */
		if (things.getViewAtLocation() != null) {
			setWorldWithSelectionFocus(world);
			unselectAllThings();
		}
		else {
			setWorldWithSelectionFocus(world);
			IHasMutableSelected mst = Assemblies.getEditableThing(model, things.getThingAtLocation().getThing(),
					IHasMutableSelected.class, IHasMutableSelected.USER_MAY_SELECT);
			if (mst != null) {
				if (!mst.isSelected()) {
					unselectAllThings();
				}
				mst.setSelected(true);

				return;
			}

			unselectAllThings();
		}
	}
}
