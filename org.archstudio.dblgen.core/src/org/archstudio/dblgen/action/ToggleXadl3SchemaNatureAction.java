package org.archstudio.dblgen.action;

import org.archstudio.dblgen.Xadl3SchemaNature;
import org.archstudio.eclipse.action.AbstractToggleNatureObjectActionDelegate;

public class ToggleXadl3SchemaNatureAction extends
		AbstractToggleNatureObjectActionDelegate {

	public ToggleXadl3SchemaNatureAction() {
		super(Xadl3SchemaNature.NATURE_ID);
	}
}