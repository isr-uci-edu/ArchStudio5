package org.archstudio.archipelago2.core.editors;

import java.net.URL;
import java.util.List;

import org.archstudio.archipelago2.AbstractArchipelago2Editor;
import org.archstudio.archipelago2.core.Activator;
import org.archstudio.resources.IResources;
import org.archstudio.swtutils.Banner;
import org.archstudio.utils.resources.swt.ColorUtils;
import org.archstudio.utils.resources.swt.ImageUtils;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorPart;

/**
 * An "editor" that simply displays the Archipelago banner.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class Archipelago2DefaultEditor extends AbstractArchipelago2Editor {
  @Override
  public boolean canEdit(List<Object> elementPath) {
    return elementPath.size() == 0;
  }

  @Override
  public void createPartControl(IEditorPart editorPart, Composite parent,
      List<Object> elementPath) {
    GridLayout gridLayout = new GridLayout(1, true);
    gridLayout.marginTop = 5;
    gridLayout.marginBottom = 5;
    gridLayout.marginLeft = 5;
    gridLayout.marginRight = 5;
    gridLayout.verticalSpacing = 5;
    parent.setLayout(gridLayout);

    // Create the Archipelago banner.
    URL iconURL = Activator.getDefault().getBundle().getResource("/res/archipelago-icon-32.png");
    Image icon = ImageUtils.getImage(parent.getDisplay(), iconURL);
    Composite banner = new Banner(parent, icon, "Archipelago v2.0", "Graphical Architecture Editor",
        ColorUtils.getColor(parent.getDisplay(), IResources.RGB_BANNER_BRIGHT),
        ColorUtils.getColor(parent.getDisplay(), IResources.RGB_BANNER_DARK));
    GridData gridData = new GridData();
    gridData.horizontalAlignment = GridData.FILL;
    gridData.grabExcessHorizontalSpace = true;
    banner.setLayoutData(gridData);

    // Include a label requesting the selection of an item in the outline view.
    Label instructions = new Label(parent, SWT.NONE);
    instructions.setText("Double-click a node in the outline view to begin.");
    instructions.setFont(JFaceResources.getFont(IResources.PLATFORM_DEFAULT_FONT_ID));
    instructions.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
    gridData = new GridData();
    gridData.horizontalAlignment = GridData.FILL;
    gridData.grabExcessHorizontalSpace = true;
    gridData.verticalAlignment = GridData.FILL;
    gridData.grabExcessVerticalSpace = true;
    instructions.setLayoutData(gridData);

    setFocusControl(banner);
  }

  @Override
  public void setFocus(List<Object> elementPath) {}
}
