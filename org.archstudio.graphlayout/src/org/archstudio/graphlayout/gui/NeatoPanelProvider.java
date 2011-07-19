package org.archstudio.graphlayout.gui;

public class NeatoPanelProvider implements IGraphLayoutParameterPanelProvider {
	protected IGraphLayoutParameterPanel[] panels = new IGraphLayoutParameterPanel[] {
			new SizeAndScaleParameterPanel(), new GeneralOptionsParameterPanel(), };

	public String getEngineID() {
		return "neato";
	}

	public IGraphLayoutParameterPanel[] getPanels() {
		return panels;
	}
}
