package org.archstudio.graphlayout.gui;

import org.archstudio.graphlayout.GraphLayoutParameters;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class OrientInterfacesParameterPanel implements IGraphLayoutParameterPanel {

	protected Button cbOrientInterfaces;

	@Override
	public void createPanel(Composite parent) {
		Group gPanel = new Group(parent, SWT.NONE);
		gPanel.setText("Interface Orientations");
		gPanel.setLayout(new FillLayout());

		Composite cPanel = new Composite(gPanel, SWT.NONE);
		cPanel.setLayout(new GridLayout(1, false));

		cbOrientInterfaces = new Button(cPanel, SWT.CHECK);
		cbOrientInterfaces.setText("Guess Interface Orientations");
	}

	@Override
	public void loadParameters(GraphLayoutParameters params) {
		try {
			boolean orientInterfaces = ((Boolean) params.getProperty("orientInterfaces")).booleanValue();
			cbOrientInterfaces.setSelection(orientInterfaces);
		}
		catch (Exception e) {
			cbOrientInterfaces.setSelection(false);
		}
	}

	@Override
	public void storeParameters(GraphLayoutParameters params) throws DataValidationException {
		params.setProperty("orientInterfaces", cbOrientInterfaces.getSelection());
	}
}
