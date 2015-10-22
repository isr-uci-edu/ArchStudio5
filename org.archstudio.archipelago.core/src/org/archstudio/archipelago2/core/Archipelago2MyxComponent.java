package org.archstudio.archipelago2.core;

import org.archstudio.launcher.ILaunchData;
import org.archstudio.launcher.LaunchData;
import org.archstudio.utils.resources.swt.ImageUtils;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * Myx brick: Archipelago v2.0 Impl.
 */
public class Archipelago2MyxComponent
    extends org.archstudio.archipelago2.core.Archipelago2MyxComponentStub {
  public static final String EDITOR_NAME = "Archipelago v2.0";
  public static final String ECLIPSE_EDITOR_ID = Archipelago2Editor.class.getName();

  public Archipelago2MyxComponent() {
    super(EDITOR_NAME, ECLIPSE_EDITOR_ID, false);
  }

  protected Archipelago2MyxComponent(String editorName, String eclipseEditorID,
      boolean registerWithEditorManager) {
    super(editorName, eclipseEditorID, registerWithEditorManager);
  }

  @Override
  public ILaunchData getLaunchData() {
    return new LaunchData(ECLIPSE_EDITOR_ID, EDITOR_NAME, "A graphical architecture editor v2.0",
        getIcon(), ILaunchData.LaunchType.EDITOR);
  }

  public Image getIcon() {
    return ImageUtils.getImage(Display.getDefault(),
        Activator.getDefault().getBundle().getResource("res/archipelago-icon-32.png"));
  }
}
