package org.archstudio.bna.ui;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.constants.MouseType;
import org.archstudio.bna.utils.BNAUtils2;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
import org.eclipse.swt.events.MouseEvent;

/**
 * A mouse click listener that is called only when the user is interacting with this view. See
 * {@link BNAUtils2#getThingsAtLocation(IBNAView, ICoordinate)} for a discussion of what view the user is interacting
 * with. If the logic implements {@link IBNAAllEventsListener2} then this logic will always be called event if the
 * user is not interacting with its view.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public interface IBNAMouseWheelListener2 {
	/**
	 * Called when a vertical mouse wheel event occurs.
	 *
	 * @param view
	 *            The view of the logic.
	 * @param type
	 *            The type of mouse event, equal to {@link MouseType#VERTICAL_WHEEL}.
	 * @param evt
	 *            The original mouse event that triggered this call.
	 * @param location
	 *            The location in the view.
	 * @param thingsAtLocation
	 *            The things at the location under the original view. This may differ from the view of this logic if the
	 *            logic implements {@link IBNAAllEventsListener2}.
	 */
	public void mouseVerticalWheel(IBNAView view, MouseType type, MouseEvent evt, ICoordinate location,
			ThingsAtLocation thingsAtLocation);

	/**
	 * Called when a horizontal mouse wheel event occurs.
	 *
	 * @param view
	 *            The view of the logic.
	 * @param type
	 *            The type of mouse event, equal to {@link MouseType#HORIZONTAL_WHEEL}.
	 * @param evt
	 *            The original mouse event that triggered this call.
	 * @param location
	 *            The location in the view.
	 * @param thingsAtLocation
	 *            The things at the location under the original view. This may differ from the view of this logic if the
	 *            logic implements {@link IBNAAllEventsListener2}.
	 */
	public void mouseHorizontalWheel(IBNAView view, MouseType type, MouseEvent evt, ICoordinate location,
			ThingsAtLocation thingsAtLocation);
}
