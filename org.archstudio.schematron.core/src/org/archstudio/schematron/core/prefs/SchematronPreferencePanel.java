package org.archstudio.schematron.core.prefs;

import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.schematron.core.SchematronConstants;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.PathEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class SchematronPreferencePanel extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	private SchematronPrefsMyxComponent comp = null;
	private final MyxRegistry er = MyxRegistry.getSharedInstance();

	protected PathEditor testFilePathsPrefEditor;

	public SchematronPreferencePanel() {
		super("Schematron Preferences", GRID);
		comp = (SchematronPrefsMyxComponent) er.waitForBrick(SchematronPrefsMyxComponent.class);
		er.map(comp, this);

		setPreferenceStore(comp.getPreferences());
		setDescription("Additional test file paths:");
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {
		testFilePathsPrefEditor = new PathEditor(SchematronConstants.PREF_TEST_FILE_PATHS, "Test File Paths",
				"Choose Test File Path", getFieldEditorParent());
		addField(testFilePathsPrefEditor);
	}

}
