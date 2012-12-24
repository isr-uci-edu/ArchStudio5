package org.archstudio.graphlayout.gui;

public class DotPanelProvider implements IGraphLayoutParameterPanelProvider {
	protected IGraphLayoutParameterPanel[] panels = new IGraphLayoutParameterPanel[] {
			new SizeAndScaleParameterPanel(), new GeneralOptionsParameterPanel(), new OrientInterfacesParameterPanel(),
			new DotSpacingParameterPanel() };

	@Override
	public String getEngineID() {
		return "dot";
	}

	@Override
	public IGraphLayoutParameterPanel[] getPanels() {
		return panels;
	}
}
