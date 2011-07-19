package org.archstudio.myxgen.ui.actions;

import org.archstudio.eclipse.ui.actions.AbstractToggleNatureObjectActionDelegate;
import org.archstudio.myxgen.MyxStubNature;

public class ToggleMyxStubNatureAction extends AbstractToggleNatureObjectActionDelegate {

	public ToggleMyxStubNatureAction() {
		super(MyxStubNature.NATURE_ID);
	}
}
