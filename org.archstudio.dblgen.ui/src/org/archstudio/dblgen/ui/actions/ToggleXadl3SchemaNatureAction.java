package org.archstudio.dblgen.ui.actions;

import org.archstudio.dblgen.Xadl3SchemaNature;
import org.archstudio.eclipse.ui.actions.AbstractToggleNatureObjectActionDelegate;
import org.eclipse.core.resources.IProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.PlatformUI;

public class ToggleXadl3SchemaNatureAction extends AbstractToggleNatureObjectActionDelegate {

	public ToggleXadl3SchemaNatureAction() {
		super(Xadl3SchemaNature.NATURE_ID);
	}

	protected boolean setNatureAdded(IProject project, String natureID, boolean addNature) {

		if (addNature) {
			MessageBox messageBox = new MessageBox(PlatformUI.getWorkbench().getDisplay().getActiveShell(),
					SWT.ICON_WARNING | SWT.YES | SWT.NO);
			messageBox.setMessage("Generating xADL data bindings in " + project.getName()
					+ " will erase the contents of its current src folder. This operation"
					+ " cannot be undone. Proceed?");
			int rc = messageBox.open();
			if (rc == SWT.YES) {
				return super.setNatureAdded(project, natureID, addNature);
			}
			return false;
		}

		return super.setNatureAdded(project, natureID, false);
	}
}