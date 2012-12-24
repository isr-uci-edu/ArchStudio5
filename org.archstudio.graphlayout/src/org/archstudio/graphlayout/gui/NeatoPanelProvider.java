package org.archstudio.graphlayout.gui;

public class NeatoPanelProvider implements IGraphLayoutParameterPanelProvider {
	protected IGraphLayoutParameterPanel[] panels = new IGraphLayoutParameterPanel[] {
			new SizeAndScaleParameterPanel(), new GeneralOptionsParameterPanel(), };

	@Override
	public String getEngineID() {
		return "neato";
	}

	@Override
	public IGraphLayoutParameterPanel[] getPanels() {
		return panels;
	}
}
