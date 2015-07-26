package org.archstudio.bna.logics.editing;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.MouseType;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.logics.background.RotatingOffsetLogic;
import org.archstudio.bna.ui.IBNAMenuListener;
import org.archstudio.bna.ui.IBNAMouseListener;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.events.MouseEvent;

public class ClickSelectionLogic extends SelectionLogic implements IBNAMouseListener, IBNAMenuListener {
	public ClickSelectionLogic(IBNAWorld world) {
		super(world);
		logics.addThingLogic(RotatingOffsetLogic.class);
	}

	@Override
	public void mouseUp(IBNAView view, MouseType type, MouseEvent evt, List<IThing> t, ICoordinate location) {
		BNAUtils.checkLock();
	}

	@Override
	public void mouseDown(IBNAView view, MouseType type, MouseEvent evt, List<IThing> t, ICoordinate location) {
		BNAUtils.checkLock();

		if (evt.button == 1) {
			IHasMutableSelected selectableThing = Assemblies.getEditableThing(model, firstOrNull(t),
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
			if (t.isEmpty()) {
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
	public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager m) {
		BNAUtils.checkLock();

		/*
		 * We don't actually want to fill the menu here, but we want to change the selection before the menu really gets
		 * filled to reflect the click. If we clicked on something already selected, we leave the selection alone. If we
		 * click on something not selected, but selectable, we change the selection to be that thing. If we click on
		 * something not selectable, then we clear the selection.
		 */
		if (things.isEmpty()) {
			setWorldWithSelectionFocus(world);
			unselectAllThings();
		}
		else {
			setWorldWithSelectionFocus(world);
			IHasMutableSelected mst = Assemblies.getEditableThing(model, firstOrNull(things), IHasMutableSelected.class,
					IHasMutableSelected.USER_MAY_SELECT);
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
