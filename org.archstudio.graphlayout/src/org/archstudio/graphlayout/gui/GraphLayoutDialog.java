package org.archstudio.graphlayout.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.archstudio.graphlayout.GraphLayout;
import org.archstudio.graphlayout.GraphLayoutException;
import org.archstudio.graphlayout.GraphLayoutParameters;
import org.archstudio.graphlayout.IGraphLayout;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class GraphLayoutDialog extends Dialog {
	public static void main(String[] args) {
		Display display = new Display();

		Shell shell = new Shell(display);
		shell.setText("Dialog Example");
		shell.setSize(300, 200);
		shell.open();

		IGraphLayout fakeGraphLayout = new IGraphLayout() {
			@Override
			public String[] getEngineIDs() {
				return new String[] { "dot", "neato" };
			}

			@Override
			public String getEngineDescription(String engineID) {
				if (engineID.equals("dot")) {
					return "GraphViz Dot Engine";
				}
				else if (engineID.equals("neato")) {
					return "GraphViz Neato Engine";
				}
				else {
					return "???";
				}
			}

			@Override
			public GraphLayout layoutGraph(String engineID, ObjRef rootRef, GraphLayoutParameters params)
					throws GraphLayoutException {
				System.err.println("laying out now.");
				return null;
			}
		};

		GraphLayoutDialog gld = new GraphLayoutDialog(shell);
		System.err.println("opening");
		GraphLayoutParameters result = gld.open(fakeGraphLayout);
		System.err.println("opened done: " + result);

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		System.err.println("exit");
	}

	protected GraphLayoutParameters result = null;
	private Shell shell = null;
	protected Composite panelParent = null;

	protected IGraphLayoutParameterPanel[] parameterPanels = null;

	public GraphLayoutDialog(Shell parent, int style) {
		super(parent, style);
		initPanelProviders();
		initPresets();
	}

	public GraphLayoutDialog(Shell parent) {
		this(parent, 0); // your default style bits go here (not the Shell's style bits)
	}

	protected void initPanelProviders() {
		addPanelProvider(new DotPanelProvider());
		addPanelProvider(new NeatoPanelProvider());
	}

	protected void initPresets() {
		GraphLayoutParameters defaultParams = new GraphLayoutParameters();
		defaultParams.setRelativeComponentWidth(1.0);
		defaultParams.setRelativeComponentHeight(1.0);
		defaultParams.setRelativeConnectorWidth(1.0);
		defaultParams.setRelativeConnectorHeight(1.0);
		defaultParams.setScale(100.0);
		defaultParams.setProperty("rankSep", 1.0);
		defaultParams.setProperty("nodeSep", 1.0);
		defaultParams.setProperty("orientInterfaces", false);
		addPreset("Default", defaultParams);

		GraphLayoutParameters niceParams = new GraphLayoutParameters();
		niceParams.setRelativeComponentWidth(5.0);
		niceParams.setRelativeComponentHeight(4.0);
		niceParams.setRelativeConnectorWidth(3.0);
		niceParams.setRelativeConnectorHeight(2.0);
		niceParams.setScale(25.0);
		niceParams.setProperty("dontRouteLinks", true);
		niceParams.setProperty("dontMoveInterfaces", false);
		niceParams.setProperty("rankSep", 1.75);
		niceParams.setProperty("nodeSep", 1.5);
		niceParams.setProperty("orientInterfaces", false);
		addPreset("Nice", niceParams);

		GraphLayoutParameters c2Params = new GraphLayoutParameters();
		c2Params.setRelativeComponentWidth(5.0);
		c2Params.setRelativeComponentHeight(4.0);
		c2Params.setRelativeConnectorWidth(10.0);
		c2Params.setRelativeConnectorHeight(1.0);
		c2Params.setScale(25.0);
		c2Params.setProperty("dontRouteLinks", true);
		c2Params.setProperty("dontMoveInterfaces", false);
		c2Params.setProperty("rankSep", 1.75);
		c2Params.setProperty("nodeSep", 1.5);
		c2Params.setProperty("orientInterfaces", true);
		addPreset("C2 Style", c2Params);

	}

	protected void done(GraphLayoutParameters result) {
		this.result = result;
		this.shell.dispose();
	}

	public GraphLayoutParameters open(IGraphLayout graphLayout) {
		final Shell parent = getParent();

		final String[] engineIDs = graphLayout.getEngineIDs();
		if (engineIDs == null || engineIDs.length == 0) {
			MessageDialog.openError(parent, "Error", "No graph layout engines available.");
			return null;
		}

		this.shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);

		String text = getText();
		if (text == null || text.trim().equals("")) {
			text = "Graph Layout Options";
		}
		shell.setText(text);

		shell.setLayout(new FillLayout());

		Composite mainComposite = new Composite(shell, SWT.NONE);
		mainComposite.setLayout(new GridLayout(1, false));

		Composite cSelectEngine = new Composite(mainComposite, SWT.NONE);
		cSelectEngine.setLayout(new GridLayout(2, false));

		Label lSelectEngine = new Label(cSelectEngine, SWT.NONE);
		lSelectEngine.setText("Layout Engine:");

		String[] engineDescriptions = new String[engineIDs.length];
		for (int i = 0; i < engineDescriptions.length; i++) {
			engineDescriptions[i] = graphLayout.getEngineDescription(engineIDs[i]);
			if (engineDescriptions[i] == null) {
				engineDescriptions[i] = engineIDs[i];
			}
		}

		final Combo cmbSelectEngine = new Combo(cSelectEngine, SWT.READ_ONLY);
		cmbSelectEngine.setItems(engineDescriptions);
		cmbSelectEngine.select(0);

		panelParent = new Composite(mainComposite, SWT.NONE);
		panelParent.setLayout(new GridLayout(1, false));

		cmbSelectEngine.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = cmbSelectEngine.getSelectionIndex();
				setupEnginePanels(engineIDs[selectionIndex]);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		setupEnginePanels(engineIDs[0]);

		Group gPresets = new Group(mainComposite, SWT.NONE);
		gPresets.setText("Load Settings");
		gPresets.setLayout(new FillLayout());

		Composite cPresets = new Composite(gPresets, SWT.NONE);
		cPresets.setLayout(new GridLayout(2, false));

		NamedGraphLayoutParameters[] presets = getPresets();
		final String[] presetNames = new String[presets.length];
		for (int i = 0; i < presets.length; i++) {
			presetNames[i] = presets[i].name;
		}

		final Combo cmbPresets = new Combo(cPresets, SWT.READ_ONLY);
		cmbPresets.setItems(presetNames);
		cmbPresets.select(0);

		Button bLoadPreset = new Button(cPresets, SWT.PUSH);
		bLoadPreset.setText("Load");
		bLoadPreset.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (parameterPanels != null) {
					NamedGraphLayoutParameters[] presets = getPresets();
					NamedGraphLayoutParameters preset = presets[cmbPresets.getSelectionIndex()];
					for (IGraphLayoutParameterPanel parameterPanel : parameterPanels) {
						parameterPanel.loadParameters(preset.params);
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		Composite cButtons = new Composite(mainComposite, SWT.NONE);
		cButtons.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));
		cButtons.setLayout(new GridLayout(2, false));

		Button bOK = new Button(cButtons, SWT.PUSH);
		bOK.setText("OK");
		bOK.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (parameterPanels == null) {
					done(null);
					return;
				}
				int selectionIndex = cmbSelectEngine.getSelectionIndex();
				String engineID = engineIDs[selectionIndex];

				GraphLayoutParameters glp = new GraphLayoutParameters();
				try {
					for (IGraphLayoutParameterPanel parameterPanel : parameterPanels) {
						parameterPanel.storeParameters(glp);
					}
					glp.setProperty("engineID", engineID);
					done(glp);
					return;
				}
				catch (DataValidationException dve) {
					MessageDialog.openError(parent, "Error", dve.getMessage());
					return;
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		Button bCancel = new Button(cButtons, SWT.PUSH);
		bCancel.setText("Cancel");
		bCancel.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				done(null);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		shell.pack();
		shell.open();
		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		return result;
	}

	protected void setupEnginePanels(String engineID) {
		//dispose old controls
		Control[] children = panelParent.getChildren();
		for (Control element : children) {
			element.dispose();
		}
		parameterPanels = null;

		IGraphLayoutParameterPanelProvider[] panelProviders = getPanelProviders();
		IGraphLayoutParameterPanelProvider panelProvider = null;
		for (IGraphLayoutParameterPanelProvider panelProvider2 : panelProviders) {
			if (panelProvider2.getEngineID().equals(engineID)) {
				panelProvider = panelProvider2;
				break;
			}
		}
		if (panelProvider == null) {
			Label l = new Label(panelParent, SWT.NONE);
			l.setText("Select a valid engine.");
		}
		else {
			parameterPanels = panelProvider.getPanels();
			for (IGraphLayoutParameterPanel parameterPanel : parameterPanels) {
				parameterPanel.createPanel(panelParent);
			}
		}
		shell.layout(true, true);
		shell.pack(true);
	}

	protected List<NamedGraphLayoutParameters> presetList = Collections
			.synchronizedList(new ArrayList<NamedGraphLayoutParameters>());

	protected void addPreset(String name, GraphLayoutParameters params) {
		presetList.add(new NamedGraphLayoutParameters(name, params));
	}

	protected NamedGraphLayoutParameters[] getPresets() {
		return presetList.toArray(new NamedGraphLayoutParameters[presetList.size()]);
	}

	private static class NamedGraphLayoutParameters {
		public String name;
		public GraphLayoutParameters params;

		public NamedGraphLayoutParameters(String name, GraphLayoutParameters params) {
			this.name = name;
			this.params = params;
		}
	}

	protected List<IGraphLayoutParameterPanelProvider> panelProviderList = Collections
			.synchronizedList(new ArrayList<IGraphLayoutParameterPanelProvider>());

	protected void addPanelProvider(IGraphLayoutParameterPanelProvider panelProvider) {
		panelProviderList.add(panelProvider);
	}

	protected void removePanelProvider(IGraphLayoutParameterPanelProvider panelProvider) {
		panelProviderList.remove(panelProvider);
	}

	protected IGraphLayoutParameterPanelProvider[] getPanelProviders() {
		return panelProviderList.toArray(new IGraphLayoutParameterPanelProvider[panelProviderList.size()]);
	}
}
