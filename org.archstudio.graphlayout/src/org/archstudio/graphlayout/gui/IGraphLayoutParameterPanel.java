package org.archstudio.graphlayout.gui;

import org.eclipse.swt.widgets.Composite;

import org.archstudio.graphlayout.GraphLayoutParameters;

interface IGraphLayoutParameterPanel {
	public void createPanel(Composite parent);

	public void storeParameters(GraphLayoutParameters params) throws DataValidationException;

	public void loadParameters(GraphLayoutParameters params);
}
