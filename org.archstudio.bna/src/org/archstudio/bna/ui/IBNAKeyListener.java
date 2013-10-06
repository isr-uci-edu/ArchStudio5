package org.archstudio.bna.ui;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.constants.KeyType;
import org.eclipse.swt.events.KeyEvent;

public interface IBNAKeyListener {

	public void keyPressed(IBNAView view, KeyType type, KeyEvent e);

	public void keyReleased(IBNAView view, KeyType type, KeyEvent e);

}