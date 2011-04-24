package org.archstudio.eclipsedev.common;

import org.archstudio.eclipsedev.common.EclipseDevConstants.ProjectCleanBehaviorType;
import org.eclipse.jface.preference.IPreferenceStore;

public class EclipseDevPreferences {
	private EclipseDevPreferences() {
	}

	public static ProjectCleanBehaviorType getProjectCleanBehavior(IPreferenceStore preferenceStore) {
		String projectCleanBehaviorString = preferenceStore.getString(EclipseDevConstants.PREFERENCE_PROJECT_CLEAN_BEHAVIOR);
		if ((projectCleanBehaviorString == null) || (projectCleanBehaviorString.equals(""))) {
			return ProjectCleanBehaviorType.DELETE_NOTHING;
		}
		return ProjectCleanBehaviorType.valueOf(projectCleanBehaviorString);
	}

	public static void setProjectCleanBehavior(IPreferenceStore preferenceStore, ProjectCleanBehaviorType newProjectCleanBehavior) {
		preferenceStore.setValue(EclipseDevConstants.PREFERENCE_PROJECT_CLEAN_BEHAVIOR, newProjectCleanBehavior.name());
	}
	
	public static void initDefaults(IPreferenceStore preferenceStore) {
		preferenceStore.setDefault(EclipseDevConstants.PREFERENCE_PROJECT_CLEAN_BEHAVIOR, ProjectCleanBehaviorType.DELETE_NOTHING.name());
	}
}
