package org.archstudio.archipelago.core.prefs.structure;

import org.archstudio.archipelago.core.structure.ArchipelagoStructureConstants;
import org.archstudio.myx.fw.MyxRegistry;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FontFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ArchipelagoStructurePreferencePanel extends FieldEditorPreferencePage implements IWorkbenchPreferencePage{
	private ArchipelagoStructurePrefsMyxComponent comp = null;
	private MyxRegistry er = MyxRegistry.getSharedInstance();
	
	protected FontFieldEditor defaultBrickFontEditor;
	protected ColorFieldEditor defaultComponentColorEditor;
	protected ColorFieldEditor defaultConnectorColorEditor;
	protected ColorFieldEditor defaultComponentTypeColorEditor;
	protected ColorFieldEditor defaultConnectorTypeColorEditor;
	
	public ArchipelagoStructurePreferencePanel(){
		super("Archipelago Structure Preferences", FLAT);
		comp = (ArchipelagoStructurePrefsMyxComponent)er.waitForBrick(ArchipelagoStructurePrefsMyxComponent.class);
		er.map(comp, this);
		
		setPreferenceStore(comp.preferences);
		setDescription("This panel lets you set structure and types preferences for Archipelago.");
	}
	
	public void init(IWorkbench workbench){
	}

	protected void createFieldEditors(){
		defaultBrickFontEditor = new FontFieldEditor(ArchipelagoStructureConstants.PREF_DEFAULT_FONT, 
			"Default Font:", getFieldEditorParent());
		addField(defaultBrickFontEditor);
		
		defaultComponentColorEditor = new ColorFieldEditor(ArchipelagoStructureConstants.PREF_DEFAULT_COMPONENT_COLOR, 
			"Default Component Color:", getFieldEditorParent());
		addField(defaultComponentColorEditor);
		
		defaultConnectorColorEditor = new ColorFieldEditor(ArchipelagoStructureConstants.PREF_DEFAULT_CONNECTOR_COLOR, 
			"Default Connector Color:", getFieldEditorParent());
		addField(defaultConnectorColorEditor);
	}

}
