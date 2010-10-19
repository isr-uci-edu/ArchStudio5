package org.archstudio.bna.logics.editing;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.events.MouseEvent;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAMenuListener;
import org.archstudio.bna.IBNAMouseListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasUserEditable;
import org.archstudio.bna.logics.tracking.TypedThingSetTrackingLogic;

public class ClickSelectionLogic extends AbstractThingLogic implements IBNAMouseListener, IBNAMenuListener {
	protected TypedThingSetTrackingLogic<IHasMutableSelected> ttstlSelected = null;

	public ClickSelectionLogic(TypedThingSetTrackingLogic<IHasMutableSelected> ttstlSelected) {
		this.ttstlSelected = ttstlSelected;
	}

	private void removeAllSelections() {
		IHasMutableSelected[] allSelectableThings = ttstlSelected.getThings();
		for (IHasMutableSelected possiblySelectedThing : allSelectableThings) {
			possiblySelectedThing.setSelected(false);
		}
	}

	public void mouseUp(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
	}

	public void mouseDown(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
		if (t instanceof IHasUserEditable) {
			IHasUserEditable et = (IHasUserEditable) t;
			if (!et.isUserEditable())
				return;
		}
		if (evt.button == 1) {
			if ((t != null) && (t instanceof IHasMutableSelected)) {
				boolean controlPressed = BNAUtils.wasControlPressed(evt);
				boolean shiftPressed = BNAUtils.wasShiftPressed(evt);

				if ((!controlPressed) && (!shiftPressed)) {
					//Only deselect everything if the thing we're not clicking on
					//is not selected
					if (!((IHasMutableSelected) t).isSelected()) {
						removeAllSelections();
					}
					((IHasMutableSelected) t).setSelected(true);
				}
				else if ((controlPressed) && (!shiftPressed)) {
					//Toggle selection
					((IHasMutableSelected) t).setSelected(!((IHasMutableSelected) t).isSelected());
				}
				else if ((shiftPressed) && (!controlPressed)) {
					//Add to selection
					((IHasMutableSelected) t).setSelected(true);
				}
				else if ((shiftPressed) && (controlPressed)) {
					//Subtract from selection
					((IHasMutableSelected) t).setSelected(false);
				}
			}
			else if (t == null) {
				boolean controlPressed = BNAUtils.wasControlPressed(evt);
				boolean shiftPressed = BNAUtils.wasShiftPressed(evt);
				if ((!controlPressed) && (!shiftPressed)) {
					removeAllSelections();
				}
			}
		}
	}

	public void mouseClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
	}

	public void mouseDoubleClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
	}

	public void fillMenu(IBNAView view, IMenuManager m, int localX, int localY, IThing t, int worldX, int worldY) {
		//We don't actually want to fill the menu here, but we want to change
		//the selection before the menu really gets filled to reflect the
		//click.  If we clicked on something already selected, we leave
		//the selection alone.  If we click on something not selected, but
		//selectable, we change the selection to be that thing.  If we click
		//on something not selectable, then we clear the selection.
		if (t == null) {
			removeAllSelections();
		}
		else if (t instanceof IHasMutableSelected) {
			IHasMutableSelected st = (IHasMutableSelected) t;
			if (!st.isSelected()) {
				removeAllSelections();
				if (st instanceof IHasUserEditable) {
					IHasUserEditable et = (IHasUserEditable) t;
					if (!et.isUserEditable())
						return;
				}
				st.setSelected(true);
			}
		}
		else {
			removeAllSelections();
		}
	}
}
