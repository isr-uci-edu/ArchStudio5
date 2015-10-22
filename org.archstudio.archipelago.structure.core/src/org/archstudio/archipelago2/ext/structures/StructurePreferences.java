package org.archstudio.archipelago2.ext.structures;

import org.archstudio.eclipse.core.startup.InstantiateArchStudio;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FontFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class StructurePreferences extends FieldEditorPreferencePage
    implements IWorkbenchPreferencePage {
  public static final String PREF_COMPONENT_COLOR =
      "org.archstudio.archipelago2.ext.structures.componentColor";
  public static final String PREF_COMPONENT_FONT =
      "org.archstudio.archipelago2.ext.structures.componentFont";
  public static final String PREF_CONNECTOR_COLOR =
      "org.archstudio.archipelago2.ext.structures.connectorColor";
  public static final String PREF_CONNECTOR_FONT =
      "org.archstudio.archipelago2.ext.structures.connectorFont";

  public static IPreferenceStore getActivatorPreferenceStore() {
    return Activator.getDefault().getPreferenceStore();
  }

  public static RGB getComponentColor() {
    return PreferenceConverter.getColor(getActivatorPreferenceStore(), PREF_COMPONENT_COLOR);
  }

  public static FontData getComponentFont() {
    return PreferenceConverter.getFontData(getActivatorPreferenceStore(), PREF_COMPONENT_FONT);
  }

  public static RGB getConnectorColor() {
    return PreferenceConverter.getColor(getActivatorPreferenceStore(), PREF_CONNECTOR_COLOR);
  }

  public static FontData getConnectorFont() {
    return PreferenceConverter.getFontData(getActivatorPreferenceStore(), PREF_CONNECTOR_FONT);
  }

  public StructurePreferences() {
    super("Archipelago Structure Preferences", FLAT);
    InstantiateArchStudio.instantiate();
    setPreferenceStore(getActivatorPreferenceStore());
    setDescription("This panel lets you set structure preferences for Archipelago.");
  }

  @Override
  public void init(IWorkbench workbench) {}

  @Override
  protected void createFieldEditors() {
    addField(new FontFieldEditor(PREF_COMPONENT_FONT, "Default Component Font:",
        getFieldEditorParent()));
    addField(new ColorFieldEditor(PREF_COMPONENT_COLOR, "Default Component Color:",
        getFieldEditorParent()));
    addField(new FontFieldEditor(PREF_CONNECTOR_FONT, "Default Connector Font:",
        getFieldEditorParent()));
    addField(new ColorFieldEditor(PREF_CONNECTOR_COLOR, "Default Connector Color:",
        getFieldEditorParent()));
  }
}
