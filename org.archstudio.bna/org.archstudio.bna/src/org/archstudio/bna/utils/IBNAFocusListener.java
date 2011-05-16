package org.archstudio.bna.utils;

import org.archstudio.bna.IBNAView;
import org.eclipse.swt.events.FocusEvent;

public interface IBNAFocusListener {

	//Focus Events
	public void focusGained(IBNAView view, FocusEvent e);

	public void focusLost(IBNAView view, FocusEvent e);

}