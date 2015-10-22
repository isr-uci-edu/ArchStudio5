package org.archstudio.bna.ui;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.constants.KeyType;
import org.eclipse.swt.events.KeyEvent;

/**
 * Logics that implement this interface receive keyboard events. Only the top-level view logics will receive events
 * unless a logic implements {@link IBNAAllEventsListener2}, in which case it will always receive events regardless of
 * which view it is in.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public interface IBNAKeyListener2 {
	/**
	 * Receives key press (i.e., down) events.
	 *
	 * @param view
	 *            The view of the logic.
	 * @param type
	 *            The key event type, equal to {@link KeyType#PRESSED}.
	 * @param e
	 *            The original key event.
	 */
	public void keyPressed(IBNAView view, KeyType type, KeyEvent e);

	/**
	 * Receives key release (i.e., up) events.
	 *
	 * @param view
	 *            The view of the logic.
	 * @param type
	 *            The key event type, equal to {@link KeyType#RELEASED}.
	 * @param e
	 *            The original key event.
	 */
	public void keyReleased(IBNAView view, KeyType type, KeyEvent e);
}