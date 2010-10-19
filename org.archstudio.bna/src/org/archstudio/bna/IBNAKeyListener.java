package org.archstudio.bna;

import org.eclipse.swt.events.KeyEvent;

public interface IBNAKeyListener {

	//Key Events
	public void keyPressed(IBNAView view, KeyEvent e);

	public void keyReleased(IBNAView view, KeyEvent e);

}