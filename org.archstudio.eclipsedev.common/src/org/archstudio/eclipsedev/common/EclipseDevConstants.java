package org.archstudio.eclipsedev.common;

public class EclipseDevConstants {
	public static final String NATURE_ID = "org.archstudio.eclipsedev.xadl3SchemaNature";
	public static final String BUILDER_ID = "org.archstudio.eclipsedev.xadl3SchemaBuilder";
	public static final String SCHEMALOCATION_EXTENSION_POINT_ID = "org.archstudio.eclipsedev.schemalocation";
	
	// This ID has to match one in the plugin.xml file for this to work, and for these to
	// show up in the problems view, the marker type has to have the Problem marker type
	// set as a 'super' in its attributes.
	public static final String MARKER_TYPE = "org.archstudio.eclipsedev.xadl3SchemaBuilderProblem";

	public static final String PREFERENCES_NODE_ID = "org.archstudio.eclipsedev.prefs";

	public static final String PREFERENCE_PROJECT_CLEAN_BEHAVIOR = "projectCleanBehavior";
	
	public static final String SCHEMA_LAST_UPDATE_TIME_URI = "http://www.archstudio.org/schemaLastUpdateTime/";
	
	public enum ProjectCleanBehaviorType {
		DELETE_NOTHING("Delete nothing"),
		//MARK_DERIVED("Mark generated files as derived"),  //TODO: Support this
		DELETE_FILES("Delete generated files"),
		DELETE_FOLDERS("Delete generated files and folders");
		
		private final String description;
		
		private ProjectCleanBehaviorType(String description) {
			this.description = description;
		}
		
		public static ProjectCleanBehaviorType createFromDescription(String description) {
			for (ProjectCleanBehaviorType pct : ProjectCleanBehaviorType.values()) {
				if (pct.getDescription().equals(description)) {
					return pct;
				}
			}
			throw new IllegalArgumentException("Invalid description.");
		}
		
		public String getDescription() {
			return description;
		}
	}
}
