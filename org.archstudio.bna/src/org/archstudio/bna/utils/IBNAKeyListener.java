package org.archstudio.bna.utils;

import org.archstudio.bna.IBNAView;
import org.eclipse.swt.events.KeyEvent;

public interface IBNAKeyListener {

	public void keyPressed(IBNAView view, KeyEvent e);

	public void keyReleased(IBNAView view, KeyEvent e);

}