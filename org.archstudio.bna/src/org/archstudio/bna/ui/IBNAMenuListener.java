package org.archstudio.bna.ui;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchActionConstants;

public interface IBNAMenuListener {
	/**
	 * Name of the group for menu items that modify primary properties.
	 */
	public static final String PRIMARY_PROPERTIES_GROUP = "Primary Properties";

	/**
	 * Name of the group for menu items that add new elements.
	 */
	public static final String NEW_ELEMENTS_GROUP = "New Elements";

	/**
	 * Name of the group for menu items that modify secondary properties.
	 */
	public static final String SECONDARY_PROPERTIES_GROUP = "Secondary Properties";

	/**
	 * Name of the group for additional menu entries. This group follows {@link IWorkbenchActionConstants#MB_ADDITIONS}.
	 */
	public static final String FINAL_ENTRIES_GROUP = "Final Entries";

	public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu);

}