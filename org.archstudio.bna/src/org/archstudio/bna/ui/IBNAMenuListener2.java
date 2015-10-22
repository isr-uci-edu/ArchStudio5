package org.archstudio.bna.ui;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.utils.BNAUtils2;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchActionConstants;

/**
 * Logics that implement this interface provide context menu entries for BNA. Only the logics in the targeted view
 * (according to logic described in {@link BNAUtils2#getThingsAtLocation(IBNAView, ICoordinate)}) will be queried to
 * provide menu items unless a logic implements {@link IBNAAllEventsListener2}, in which case it will always be queried.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public interface IBNAMenuListener2 {
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

	/**
	 * Called when a menu event occurs.
	 *
	 * @param view
	 *            The view of the logic.
	 * @param location
	 *            The location in the view.
	 * @param thingsAtLocation
	 *            The things at the location under the original view. This may differ from the view of this logic if the
	 *            logic implements {@link IBNAAllEventsListener2}.
	 * @param menuManager
	 *            The {@link MenuManager} to which menu items should be added.
	 */
	public void fillMenu(IBNAView view, ICoordinate location, ThingsAtLocation thingsAtLocation,
			IMenuManager menuManager);
}