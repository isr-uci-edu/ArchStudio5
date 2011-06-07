package org.archstudio.archlight.core.prefs;

import org.archstudio.archlight.ArchlightConstants;
import org.archstudio.archlight.DoubleClickAction;
import org.archstudio.eclipse.ui.EclipseUtils;
import org.archstudio.myx.fw.MyxRegistry;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ArchlightPreferencePanel extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	private ArchlightPrefsMyxComponent comp = null;
	private final MyxRegistry er = MyxRegistry.getSharedInstance();

	protected RadioGroupFieldEditor doubleClickActionEditor;

	public ArchlightPreferencePanel() {
		super("Archlight General Preferences", GRID);
		comp = (ArchlightPrefsMyxComponent) er.waitForBrick(ArchlightPrefsMyxComponent.class);
		er.map(comp, this);

		setPreferenceStore(comp.getPreferences());
		setDescription("This panel lets you set general preferences for Archlight.");
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {
		doubleClickActionEditor = new RadioGroupFieldEditor(ArchlightConstants.PREF_DOUBLE_CLICK_ACTION,
				"Double Click Action", 1, EclipseUtils.getFieldEditorPreferenceData(DoubleClickAction.class),
				getFieldEditorParent(), true);
		addField(doubleClickActionEditor);

	}

}
