package org.archstudio.graphlayout.gui;

import org.archstudio.graphlayout.GraphLayoutParameters;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class GeneralOptionsParameterPanel implements IGraphLayoutParameterPanel {

	protected Button cbDontRouteLinks;
	protected Button cbDontMoveInterfaces;

	@Override
	public void createPanel(Composite parent) {
		Group gPanel = new Group(parent, SWT.NONE);
		gPanel.setText("General Options");
		gPanel.setLayout(new FillLayout());

		Composite cPanel = new Composite(gPanel, SWT.NONE);
		cPanel.setLayout(new GridLayout(2, false));

		cbDontRouteLinks = new Button(cPanel, SWT.CHECK);
		cbDontRouteLinks.setText("Don't Route Links");

		cbDontMoveInterfaces = new Button(cPanel, SWT.CHECK);
		cbDontMoveInterfaces.setText("Don't Move Interfaces");
	}

	@Override
	public void loadParameters(GraphLayoutParameters params) {
		try {
			boolean dontRouteLinks = ((Boolean) params.getProperty("dontRouteLinks")).booleanValue();
			cbDontRouteLinks.setSelection(dontRouteLinks);
		}
		catch (Exception e) {
			cbDontRouteLinks.setSelection(false);
		}

		try {
			boolean dontMoveInterfaces = ((Boolean) params.getProperty("dontMoveInterfaces")).booleanValue();
			cbDontMoveInterfaces.setSelection(dontMoveInterfaces);
		}
		catch (Exception e) {
			cbDontMoveInterfaces.setSelection(false);
		}
	}

	@Override
	public void storeParameters(GraphLayoutParameters params) throws DataValidationException {
		params.setProperty("dontRouteLinks", cbDontRouteLinks.getSelection());
		params.setProperty("dontMoveInterfaces", cbDontMoveInterfaces.getSelection());
	}
}
