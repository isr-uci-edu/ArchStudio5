package org.archstudio.preferencesadt.core;

/**
 * Myx brick: "Preferences ADT Impl"
 * 
 * @see org.archstudio.preferencesadt.core.PreferencesADTMyxComponentStub
 * @generated
 */
public class PreferencesADTMyxComponent extends
		org.archstudio.preferencesadt.core.PreferencesADTMyxComponentStub {

	@Override
	public void init() {
		// It is necessary to use the activator in this plugin rather than the main
		// ArchStudio plugin because if you don't, you can get a ClassCircularityException
		// if you go to the preference pages first before you activate an ArchStudio view
		// or perspective
		preferences = PreferencesADTActivator.getDefault().getPreferenceStore();
		preferences.addPropertyChangeListener(propertyEventsProxy);
	}

}