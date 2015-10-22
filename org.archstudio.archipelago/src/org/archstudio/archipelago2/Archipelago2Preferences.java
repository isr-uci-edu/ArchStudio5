package org.archstudio.archipelago2;

import org.archstudio.bna.constants.GridDisplayType;
import org.archstudio.bna.ui.IBNAUI;
import org.archstudio.eclipse.core.startup.InstantiateArchStudio;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.ScaleFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class Archipelago2Preferences extends FieldEditorPreferencePage
    implements IWorkbenchPreferencePage {
  public static final String PREF_ANTIALIAS_GRAPHICS =
      "org.archstudio.archipelago.antialiasGraphics";
  public static final String PREF_ANTIALIAS_TEXT = "org.archstudio.archipelago.antialiasText";
  public static final String PREF_BNA_UI = "org.archstudio.archipelago.bnaUI";
  public static final String PREF_DECORATIVE_GRAPHICS =
      "org.archstudio.archipelago.decorativeGraphics";
  public static final String PREF_DISPLAY_SHADOWS = "org.archstudio.archipelago.displayShadows";
  public static final String PREF_GRID_DISPLAY_TYPE = "org.archstudio.archipelago.gridDisplayType";
  public static final String PREF_GRID_SPACING = "org.archstudio.archipelago.gridSpacing";
  public static final String PREF_LINE_WIDTH = "org.archstudio.archipelago.lineWidth";

  public static IPreferenceStore getActivatorPreferenceStore() {
    return Activator.getDefault().getPreferenceStore();
  }

  public static boolean getAntialiasGraphics() {
    return getActivatorPreferenceStore().getBoolean(PREF_ANTIALIAS_GRAPHICS);
  }

  public static boolean getAntialiasText() {
    return getActivatorPreferenceStore().getBoolean(PREF_ANTIALIAS_TEXT);
  }

  public static IBNAUI.AvailableUI getBNAUI() {
    return IBNAUI.AvailableUI.valueOf(getActivatorPreferenceStore().getString(PREF_BNA_UI));
  }

  public static boolean getDecorativeGraphics() {
    return getActivatorPreferenceStore().getBoolean(PREF_DECORATIVE_GRAPHICS);
  }

  public static boolean getDisplayShadows() {
    return getActivatorPreferenceStore().getBoolean(PREF_DISPLAY_SHADOWS);
  }

  public static GridDisplayType getGridDisplayType() {
    return GridDisplayType.valueOf(getActivatorPreferenceStore().getString(PREF_GRID_DISPLAY_TYPE));
  }

  public static int getGridSpacing() {
    return getActivatorPreferenceStore().getInt(PREF_GRID_SPACING);
  }

  public static int getLineWidth() {
    return getActivatorPreferenceStore().getInt(PREF_LINE_WIDTH);
  }

  private static String[][] getLabelsAndValues(Class<? extends Enum<?>> enumClass) {
    Enum<?>[] enumConstants = enumClass.getEnumConstants();
    String[][] values = new String[enumConstants.length][];
    for (int i = 0; i < enumConstants.length; i++) {
      values[i] = new String[] {enumConstants[i].toString(), enumConstants[i].name()};
    }
    return values;
  }

  public Archipelago2Preferences() {
    super("Archipelago General Preferences", GRID);
    InstantiateArchStudio.instantiate();
    setPreferenceStore(getActivatorPreferenceStore());
    setDescription(
        "This panel lets you set general preferences for Archipelago; feature-specific preferences are in subpanels.");
  }

  @Override
  public void init(IWorkbench workbench) {}

  @Override
  protected void createFieldEditors() {
    addField(new BooleanFieldEditor(PREF_ANTIALIAS_GRAPHICS, "Antialias Graphics",
        getFieldEditorParent()));
    addField(new BooleanFieldEditor(PREF_ANTIALIAS_TEXT, "Antialias Text", getFieldEditorParent()));
    addField(new BooleanFieldEditor(PREF_DECORATIVE_GRAPHICS, "Decorative Graphics",
        getFieldEditorParent()));
    addField(
        new BooleanFieldEditor(PREF_DISPLAY_SHADOWS, "Display Shadows", getFieldEditorParent()));
    addField(new RadioGroupFieldEditor(PREF_BNA_UI, "Graphical Subsystem", 1,
        getLabelsAndValues(IBNAUI.AvailableUI.class), getFieldEditorParent(), true));

    ScaleFieldEditor defaultLineWidthEditor =
        new ScaleFieldEditor(PREF_LINE_WIDTH, "Line Width:", getFieldEditorParent());
    defaultLineWidthEditor.setMinimum(1);
    defaultLineWidthEditor.setMaximum(3);
    addField(defaultLineWidthEditor);

    IntegerFieldEditor gridSpacingEditor =
        new IntegerFieldEditor(PREF_GRID_SPACING, "Grid Spacing", getFieldEditorParent());
    gridSpacingEditor.setEmptyStringAllowed(false);
    gridSpacingEditor.setValidRange(0, 255);
    addField(gridSpacingEditor);

    addField(new RadioGroupFieldEditor(PREF_GRID_DISPLAY_TYPE, "Grid Display", 1,
        getLabelsAndValues(GridDisplayType.class), getFieldEditorParent(), true));
  }
}
