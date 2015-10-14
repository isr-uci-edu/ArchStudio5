package org.archstudio.bna.ui;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.constants.KeyType;
import org.eclipse.swt.events.KeyEvent;

/**
 * A BNA key listener. Only the top level world receives key events unless the logic also implements
 * {@link IBNAAllEventsListener2}, in which case the logic will always receive key events no matter what world it is in.
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