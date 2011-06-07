package org.archstudio.archipelago.core.prefs;

import org.archstudio.archipelago.core.ArchipelagoConstants;
import org.archstudio.bna.constants.GridDisplayType;
import org.archstudio.eclipse.ui.EclipseUtils;
import org.archstudio.myx.fw.MyxRegistry;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ArchipelagoPreferencePanel extends FieldEditorPreferencePage implements IWorkbenchPreferencePage{
	private ArchipelagoPrefsMyxComponent comp = null;
	private MyxRegistry er = MyxRegistry.getSharedInstance();
	
	protected BooleanFieldEditor antialiasGraphicsEditor;
	protected BooleanFieldEditor antialiasTextEditor;
	protected BooleanFieldEditor decorativeGraphicsEditor;
	
	protected IntegerFieldEditor gridSpacingEditor;
	protected RadioGroupFieldEditor gridDisplayTypeEditor;
	
	public ArchipelagoPreferencePanel(){
		super("Archipelago General Preferences", GRID);
		comp = (ArchipelagoPrefsMyxComponent)er.waitForBrick(ArchipelagoPrefsMyxComponent.class);
		er.map(comp, this);
		
		// TODO: How is it that preferences is public? This should cause an error and require getPreferences()
		setPreferenceStore(comp.preferences);
		setDescription("This panel lets you set general preferences for Archipelago; feature-specific preferences are in subpanels.");
	}
	
	public void init(IWorkbench workbench){
	}

	protected void createFieldEditors(){
		antialiasGraphicsEditor = new BooleanFieldEditor(ArchipelagoConstants.PREF_ANTIALIAS_GRAPHICS, "Antialias Graphics", getFieldEditorParent());
		addField(antialiasGraphicsEditor);

		antialiasTextEditor = new BooleanFieldEditor(ArchipelagoConstants.PREF_ANTIALIAS_TEXT, "Antialias Text", getFieldEditorParent());
		addField(antialiasTextEditor);
		
		decorativeGraphicsEditor = new BooleanFieldEditor(ArchipelagoConstants.PREF_DECORATIVE_GRAPHICS, "Decorative Graphics", getFieldEditorParent());
		addField(decorativeGraphicsEditor);
		
		gridSpacingEditor = new IntegerFieldEditor(ArchipelagoConstants.PREF_GRID_SPACING, "Grid Spacing", getFieldEditorParent());
		gridSpacingEditor.setEmptyStringAllowed(false);
		gridSpacingEditor.setValidRange(0, 255);
		addField(gridSpacingEditor);
		
		gridDisplayTypeEditor = new RadioGroupFieldEditor(ArchipelagoConstants.PREF_GRID_DISPLAY_TYPE, "Grid Display", 1,
			EclipseUtils.getFieldEditorPreferenceData(GridDisplayType.class), 
			getFieldEditorParent(), true);	
		addField(gridDisplayTypeEditor);

	}

}
