package org.archstudio.myxgen.ui.actions;

import org.archstudio.eclipse.ui.actions.AbstractToggleProjectNatureHandler;
import org.archstudio.myxgen.MyxStubNature;
import org.eclipse.ui.IStartup;

public class ToggleMyxStubNatureAction extends AbstractToggleProjectNatureHandler implements IStartup {

	public ToggleMyxStubNatureAction() {
		super(MyxStubNature.NATURE_ID);
	}

	@Override
	public void earlyStartup() {
		// do nothing, but required for correct initial menu checked setting
	}
}
