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
public interface IBNAMouseClickListener2 {
	/**
	 * Called when a mouse button is pressed.
	 *
	 * @param view
	 *            The view of the logic.
	 * @param type
	 *            The type of mouse event, equal to {@link MouseType#DOWN}.
	 * @param evt
	 *            The original mouse event that triggered this call.
	 * @param location
	 *            The location in the view.
	 * @param thingsAtLocation
	 *            The things at the location under the original view. This may differ from the view of this logic if the
	 *            logic implements {@link IBNAAllEventsListener2}.
	 */
	public void mouseDown(IBNAView view, MouseType type, MouseEvent evt, ICoordinate location,
			ThingsAtLocation thingsAtLocation);

	/**
	 * Called when a mouse button is released.
	 *
	 * @param view
	 *            The view of the logic.
	 * @param type
	 *            The type of mouse event, equal to {@link MouseType#UP}.
	 * @param evt
	 *            The original mouse event that triggered this call.
	 * @param location
	 *            The location in the view.
	 * @param thingsAtLocation
	 *            The things at the location under the original view. This may differ from the view of this logic if the
	 *            logic implements {@link IBNAAllEventsListener2}.
	 */
	public void mouseUp(IBNAView view, MouseType type, MouseEvent evt, ICoordinate location,
			ThingsAtLocation thingsAtLocation);

	/**
	 * Called when a mouse button is clicked. This will occur after the down/up events.
	 *
	 * @param view
	 *            The view of the logic.
	 * @param type
	 *            The type of mouse event, equal to {@link MouseType#CLICK}.
	 * @param evt
	 *            The original mouse event that triggered this call.
	 * @param location
	 *            The location in the view.
	 * @param thingsAtLocation
	 *            The things at the location under the original view. This may differ from the view of this logic if the
	 *            logic implements {@link IBNAAllEventsListener2}.
	 */
	public void mouseClick(IBNAView view, MouseType type, MouseEvent evt, ICoordinate location,
			ThingsAtLocation thingsAtLocation);
}
