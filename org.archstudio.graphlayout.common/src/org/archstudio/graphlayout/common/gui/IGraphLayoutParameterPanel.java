package org.archstudio.graphlayout.common.gui;

import org.eclipse.swt.widgets.Composite;

import org.archstudio.graphlayout.common.GraphLayoutParameters;

interface IGraphLayoutParameterPanel {
	public void createPanel(Composite parent);

	public void storeParameters(GraphLayoutParameters params) throws DataValidationException;

	public void loadParameters(GraphLayoutParameters params);
}
