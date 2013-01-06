package org.archstudio.archipelago.core.prefs.structure;

import org.archstudio.archipelago.core.structure.ArchipelagoStructureConstants;
import org.archstudio.archipelago.structure.core.Activator;
import org.archstudio.eclipse.core.startup.InstantiateArchStudio;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FontFieldEditor;
import org.eclipse.jface.preference.ScaleFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ArchipelagoStructurePreferencePanel extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	protected FontFieldEditor defaultComponentFontEditor;
	protected FontFieldEditor defaultConnectorFontEditor;
	protected ColorFieldEditor defaultComponentColorEditor;
	protected ColorFieldEditor defaultConnectorColorEditor;
	protected ScaleFieldEditor defaultLineWidthEditor;

	public ArchipelagoStructurePreferencePanel() {
		super("Archipelago Structure Preferences", FLAT);
		InstantiateArchStudio.instantiate();

		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("This panel lets you set structure preferences for Archipelago.");
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {
		defaultComponentFontEditor = new FontFieldEditor(ArchipelagoStructureConstants.PREF_DEFAULT_COMPONENT_FONT,
				"Default Component Font:", getFieldEditorParent());
		addField(defaultComponentFontEditor);

		defaultComponentColorEditor = new ColorFieldEditor(ArchipelagoStructureConstants.PREF_DEFAULT_COMPONENT_COLOR,
				"Default Component Color:", getFieldEditorParent());
		addField(defaultComponentColorEditor);

		defaultConnectorFontEditor = new FontFieldEditor(ArchipelagoStructureConstants.PREF_DEFAULT_CONNECTOR_FONT,
				"Default Connector Font:", getFieldEditorParent());
		addField(defaultConnectorFontEditor);

		defaultConnectorColorEditor = new ColorFieldEditor(ArchipelagoStructureConstants.PREF_DEFAULT_CONNECTOR_COLOR,
				"Default Connector Color:", getFieldEditorParent());
		addField(defaultConnectorColorEditor);

		defaultLineWidthEditor = new ScaleFieldEditor(ArchipelagoStructureConstants.PREF_LINE_WIDTH,
				"Default Line Width:", getFieldEditorParent());
		defaultLineWidthEditor.setMinimum(1);
		defaultLineWidthEditor.setMaximum(10);
		addField(defaultLineWidthEditor);
	}

}
