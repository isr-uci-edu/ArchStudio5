package org.archstudio.archipelago2.ext.statecharts;

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

public class StatechartPreferences extends FieldEditorPreferencePage
    implements IWorkbenchPreferencePage {
  public static final String PREF_STATE_COLOR =
      "org.archstudio.archipelago2.ext.statecharts.stateColor";
  public static final String PREF_STATE_FONT =
      "org.archstudio.archipelago2.ext.statecharts.stateFont";
  public static final String PREF_TRANSITION_FONT =
      "org.archstudio.archipelago2.ext.statecharts.transitionFont";

  public static IPreferenceStore getActivatorPreferenceStore() {
    return Activator.getDefault().getPreferenceStore();
  }

  public static RGB getStateColor() {
    return PreferenceConverter.getColor(getActivatorPreferenceStore(), PREF_STATE_COLOR);
  }

  public static FontData getStateFont() {
    return PreferenceConverter.getFontData(getActivatorPreferenceStore(), PREF_STATE_FONT);
  }

  public static FontData getTransitionFont() {
    return PreferenceConverter.getFontData(getActivatorPreferenceStore(), PREF_TRANSITION_FONT);
  }

  public StatechartPreferences() {
    super("Archipelago Statechart Preferences", FLAT);
    InstantiateArchStudio.instantiate();
    setPreferenceStore(getActivatorPreferenceStore());
    setDescription("This panel lets you set statecharts preferences for Archipelago.");
  }

  @Override
  public void init(IWorkbench workbench) {}

  @Override
  protected void createFieldEditors() {
    addField(new FontFieldEditor(PREF_STATE_FONT, "Default State Font:", getFieldEditorParent()));
    addField(
        new ColorFieldEditor(PREF_STATE_COLOR, "Default State Color:", getFieldEditorParent()));
    addField(new FontFieldEditor(PREF_TRANSITION_FONT, "Default Transition Font:",
        getFieldEditorParent()));
  }
}
