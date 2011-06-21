package org.archstudio.graphlayout.gui;

import org.archstudio.graphlayout.GraphLayoutParameters;
import org.eclipse.swt.widgets.Composite;

interface IGraphLayoutParameterPanel {
	public void createPanel(Composite parent);

	public void storeParameters(GraphLayoutParameters params) throws DataValidationException;

	public void loadParameters(GraphLayoutParameters params);
}
