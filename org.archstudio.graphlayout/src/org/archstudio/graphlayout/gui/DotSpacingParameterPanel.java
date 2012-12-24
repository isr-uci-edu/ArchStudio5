package org.archstudio.graphlayout.gui;

import org.archstudio.graphlayout.GraphLayoutParameters;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class DotSpacingParameterPanel implements IGraphLayoutParameterPanel {

	protected Text tRankSep;
	protected Text tNodeSep;

	@Override
	public void createPanel(Composite parent) {
		Group gPanel = new Group(parent, SWT.NONE);
		gPanel.setText("Spacing Options");
		gPanel.setLayout(new FillLayout());

		Composite cPanel = new Composite(gPanel, SWT.NONE);
		cPanel.setLayout(new GridLayout(4, false));

		Label lRankSep = new Label(cPanel, SWT.NONE);
		lRankSep.setText("Layer Spacing:");

		tRankSep = new Text(cPanel, SWT.BORDER);
		tRankSep.setLayoutData(new GridData(40, SWT.DEFAULT));
		tRankSep.setText("1.0");

		Label lNodeSep = new Label(cPanel, SWT.NONE);
		lNodeSep.setText("Node Spacing:");

		tNodeSep = new Text(cPanel, SWT.BORDER);
		tNodeSep.setLayoutData(new GridData(40, SWT.DEFAULT));
		tNodeSep.setText("1.0");
	}

	@Override
	public void loadParameters(GraphLayoutParameters params) {
		try {
			double rankSep = ((Double) params.getProperty("rankSep")).doubleValue();
			tRankSep.setText(Double.toString(rankSep));
		}
		catch (Exception e) {
			tRankSep.setText("");
		}

		try {
			double nodeSep = ((Double) params.getProperty("nodeSep")).doubleValue();
			tNodeSep.setText(Double.toString(nodeSep));
		}
		catch (Exception e) {
			tNodeSep.setText("");
		}
	}

	@Override
	public void storeParameters(GraphLayoutParameters params) throws DataValidationException {
		String rankSepString = tRankSep.getText().trim();
		if (rankSepString.equals("")) {
			params.removeProperty("rankSep");
		}
		else {
			double rankSep = 1.0;
			try {
				rankSep = Double.parseDouble(tRankSep.getText());
				if (rankSep < 0) {
					throw new DataValidationException("Layer spacing must be positive");
				}
			}
			catch (NumberFormatException nfe) {
				throw new DataValidationException("Invalid layer spacing");
			}
			params.setProperty("rankSep", rankSep);
		}

		String nodeSepString = tNodeSep.getText().trim();
		if (nodeSepString.equals("")) {
			params.removeProperty("nodeSep");
		}
		else {
			double nodeSep = 1.0;
			try {
				nodeSep = Double.parseDouble(tNodeSep.getText());
				if (nodeSep < 0) {
					throw new DataValidationException("Node spacing must be positive");
				}
			}
			catch (NumberFormatException nfe) {
				throw new DataValidationException("Invalid node spacing");
			}
			params.setProperty("nodeSep", nodeSep);
		}
	}
}
