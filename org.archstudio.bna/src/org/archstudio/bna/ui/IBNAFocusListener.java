package org.archstudio.bna.ui;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.constants.FocusType;
import org.eclipse.swt.events.FocusEvent;

public interface IBNAFocusListener {

	public void focusGained(IBNAView view, FocusType type, FocusEvent e);

	public void focusLost(IBNAView view, FocusType type, FocusEvent e);

}