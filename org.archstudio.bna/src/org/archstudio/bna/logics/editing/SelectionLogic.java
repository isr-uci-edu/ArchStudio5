package org.archstudio.bna.logics.editing;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.eclipse.jdt.annotation.Nullable;

/**
 * A logic to handle the selection of things. Supports unselecting things in other worlds when a new selection takes
 * place in a different world.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class SelectionLogic extends AbstractThingLogic {
	/** The world that currently holds the selection. */
	private static IBNAWorld worldWithSelectionFocus = null;

	/**
	 * Sets the (new) world with the selection focus. If the new world differs from the old world (with the current
	 * selection focus), all things in the old world are first unselected.
	 */
	public static final void setWorldWithSelectionFocus(IBNAWorld newWorldWithSelectionFocus) {
		synchronized (SelectionLogic.class) {
			if (newWorldWithSelectionFocus != worldWithSelectionFocus) {
				unselectAllThings();
				worldWithSelectionFocus = newWorldWithSelectionFocus;
			}
		}
	}

	/**
	 * Returns the world with the selection focus, or <code>null</code> if none.
	 *
	 * @return the world with the selection focus, or <code>null</code> if none.
	 */
	@Nullable
	public static final IBNAWorld getWorldWithSelectionFocus() {
		return worldWithSelectionFocus;
	}

	/** Unselects all things in the world with the selection focus. */
	public static final void unselectAllThings() {
		synchronized (SelectionLogic.class) {
			if (worldWithSelectionFocus != null) {
				ThingValueTrackingLogic valueLogic =
						worldWithSelectionFocus.getThingLogicManager().addThingLogic(ThingValueTrackingLogic.class);
				for (IHasMutableSelected t : valueLogic.getThings(IHasSelected.SELECTED_KEY, Boolean.TRUE,
						IHasMutableSelected.class)) {
					t.setSelected(false);
				}
			}
		}
	}

	public SelectionLogic(IBNAWorld world) {
		super(world);
	}
}
