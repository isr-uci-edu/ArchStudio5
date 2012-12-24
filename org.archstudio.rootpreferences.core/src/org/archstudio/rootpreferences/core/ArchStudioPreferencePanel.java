package org.archstudio.rootpreferences.core;

import org.archstudio.eclipse.core.startup.InstantiateArchStudio;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ArchStudioPreferencePanel extends PreferencePage implements IWorkbenchPreferencePage {

	protected Image image = null;

	public ArchStudioPreferencePanel() {
		super("ArchStudio Preferences");
		InstantiateArchStudio.instantiate();
	}

	@Override
	public void init(IWorkbench workbench) {
		image = new Image(workbench.getDisplay(), ArchStudioPreferencePanel.class.getResourceAsStream("res/banner.png"));
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite c = new Composite(parent, SWT.NONE);
		c.setLayout(new GridLayout(1, false));

		Label lBanner = new Label(c, SWT.BORDER);
		lBanner.setImage(image);

		Label lText = new Label(c, SWT.LEFT);
		lText.setText("Select a sub-node for options.");
		return c;
	}

	@Override
	public void dispose() {
		image.dispose();
		image = null;
		super.dispose();
	}

}
