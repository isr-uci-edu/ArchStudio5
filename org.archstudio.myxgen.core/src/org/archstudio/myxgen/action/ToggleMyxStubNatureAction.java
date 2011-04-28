package org.archstudio.myxgen.action;

import org.archstudio.eclipse.action.AbstractToggleNatureObjectActionDelegate;
import org.archstudio.myxgen.MyxStubNature;

public class ToggleMyxStubNatureAction extends AbstractToggleNatureObjectActionDelegate {

	public ToggleMyxStubNatureAction() {
		super(MyxStubNature.NATURE_ID);
	}
}
