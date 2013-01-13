package org.archstudio.archipelago.core.prefs;

import org.archstudio.archipelago.core.Activator;
import org.archstudio.archipelago.core.ArchipelagoConstants;
import org.archstudio.bna.constants.GridDisplayType;
import org.archstudio.eclipse.core.startup.InstantiateArchStudio;
import org.archstudio.eclipse.ui.EclipseUtils;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.ScaleFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ArchipelagoPreferencePanel extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	protected BooleanFieldEditor antialiasGraphicsEditor;
	protected BooleanFieldEditor antialiasTextEditor;
	protected BooleanFieldEditor decorativeGraphicsEditor;
	protected BooleanFieldEditor shadowsEditor;

	protected ScaleFieldEditor defaultLineWidthEditor;
	
	protected IntegerFieldEditor gridSpacingEditor;
	protected RadioGroupFieldEditor gridDisplayTypeEditor;

	public ArchipelagoPreferencePanel() {
		super("Archipelago General Preferences", GRID);
		InstantiateArchStudio.instantiate();
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("This panel lets you set general preferences for Archipelago; feature-specific preferences are in subpanels.");
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {
		antialiasGraphicsEditor = new BooleanFieldEditor(ArchipelagoConstants.PREF_ANTIALIAS_GRAPHICS,
				"Antialias Graphics", getFieldEditorParent());
		addField(antialiasGraphicsEditor);

		antialiasTextEditor = new BooleanFieldEditor(ArchipelagoConstants.PREF_ANTIALIAS_TEXT, "Antialias Text",
				getFieldEditorParent());
		addField(antialiasTextEditor);

		decorativeGraphicsEditor = new BooleanFieldEditor(ArchipelagoConstants.PREF_DECORATIVE_GRAPHICS,
				"Decorative Graphics", getFieldEditorParent());
		addField(decorativeGraphicsEditor);

		shadowsEditor = new BooleanFieldEditor(ArchipelagoConstants.PREF_DISPLAY_SHADOWS,
				"Display Shadows", getFieldEditorParent());
		addField(shadowsEditor);

		defaultLineWidthEditor = new ScaleFieldEditor(ArchipelagoConstants.PREF_LINE_WIDTH,
				"Line Width:", getFieldEditorParent());
		defaultLineWidthEditor.setMinimum(1);
		defaultLineWidthEditor.setMaximum(3);
		addField(defaultLineWidthEditor);

		gridSpacingEditor = new IntegerFieldEditor(ArchipelagoConstants.PREF_GRID_SPACING, "Grid Spacing",
				getFieldEditorParent());
		gridSpacingEditor.setEmptyStringAllowed(false);
		gridSpacingEditor.setValidRange(0, 255);
		addField(gridSpacingEditor);

		gridDisplayTypeEditor = new RadioGroupFieldEditor(ArchipelagoConstants.PREF_GRID_DISPLAY_TYPE, "Grid Display",
				1, EclipseUtils.getFieldEditorPreferenceData(GridDisplayType.class), getFieldEditorParent(), true);
		addField(gridDisplayTypeEditor);
	}
}
