package org.archstudio.graphlayout.core;

import org.archstudio.eclipse.core.startup.InstantiateArchStudio;
import org.archstudio.graphlayout.GraphLayoutConstants;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class GraphLayoutPreferencePanel extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	protected DirectoryFieldEditor graphvizPathEditor;

	public GraphLayoutPreferencePanel() {
		super("Graph Layout Preferences", GRID);
		InstantiateArchStudio.instantiate();

		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("This panel lets you set graph layout preferences, particularly the path to the GraphViz toolkit.");
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {
		graphvizPathEditor = new DirectoryFieldEditor(GraphLayoutConstants.PREF_GRAPHVIZ_PATH, "Path to Graphviz",
				getFieldEditorParent());
		addField(graphvizPathEditor);
	}

}
