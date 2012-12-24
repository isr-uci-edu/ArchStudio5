package org.archstudio.archipelago.statechart.core;

import org.archstudio.eclipse.core.startup.InstantiateArchStudio;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FontFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class StatechartPreferences extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public StatechartPreferences() {
		super("Archipelago Statechart Preferences", FLAT);
		InstantiateArchStudio.instantiate();

		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("This panel lets you set statecharts preferences for Archipelago.");
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {
		addField(new FontFieldEditor(StatechartConstants.PREF_STATE_FONT, "Default State Font:", getFieldEditorParent()));
		addField(new ColorFieldEditor(StatechartConstants.PREF_STATE_COLOR, "Default State Color:",
				getFieldEditorParent()));
		addField(new FontFieldEditor(StatechartConstants.PREF_TRANSITION_FONT, "Transition Font:",
				getFieldEditorParent()));
	}

}
