package org.archstudio.bna.builder.ui.actions;

import org.archstudio.eclipse.ui.actions.AbstractToggleProjectNatureHandler;
import org.eclipse.ui.IStartup;

public class ToggleBNABuilderNatureAction extends AbstractToggleProjectNatureHandler implements IStartup {

	public ToggleBNABuilderNatureAction() {
		super("org.archstudio.bna.builder.nature");
	}

	@Override
	public void earlyStartup() {
		// do nothing, but required for correct initial menu checked setting
	}
}
