package org.archstudio.eclipsedev.core.prefs;

import org.archstudio.eclipsedev.common.EclipseDevConstants;
import org.archstudio.eclipsedev.common.EclipseDevPreferences;
import org.archstudio.eclipsedev.common.EclipseDevConstants.ProjectCleanBehaviorType;
import org.archstudio.myx.fw.MyxRegistry;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class EclipseDevPreferencePanel extends FieldEditorPreferencePage implements IWorkbenchPreferencePage{
	private final MyxRegistry er = MyxRegistry.getSharedInstance();
	protected EclipseDevPreferencesMyxComponent comp;
	protected RadioGroupFieldEditor projectCleanBehaviorEditor;

	public EclipseDevPreferencePanel(){
		super("ArchStudio Development Preferences", GRID);
		setDescription("Preferences for ArchStudio Development Support Plug-ins");
	}
	
	@Override
	public void init(IWorkbench workbench) {
		comp = (EclipseDevPreferencesMyxComponent)er.waitForBrick(EclipseDevPreferencesMyxComponent.class);
		er.map(comp, this);
		
		setPreferenceStore(comp.prefs);
		EclipseDevPreferences.initDefaults(getPreferenceStore());
	}

	@Override
	protected void createFieldEditors(){
		String[][] labelAndValues = new String[ProjectCleanBehaviorType.values().length][2];
		int i = 0;
		for (ProjectCleanBehaviorType pcbt : ProjectCleanBehaviorType.values()) {
			labelAndValues[i++] = new String[] { pcbt.getDescription(), pcbt.name() };
		}
		
		projectCleanBehaviorEditor = new RadioGroupFieldEditor(EclipseDevConstants.PREFERENCE_PROJECT_CLEAN_BEHAVIOR, "Project Clean Behavior", 1, labelAndValues, getFieldEditorParent(), true);
		addField(projectCleanBehaviorEditor);
	}

}
