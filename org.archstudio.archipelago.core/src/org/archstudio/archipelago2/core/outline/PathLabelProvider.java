package org.archstudio.archipelago2.core.outline;

import java.util.List;

import org.archstudio.archipelago2.AbstractArchipelago2LabelProvider;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * Returns a default image and label for the paths passed to
 * {@link PathContentProvider#addPath(String)}.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class PathLabelProvider extends AbstractArchipelago2LabelProvider {
  /** The PathContentProvider with path information for this label provider. */
  private final PathContentProvider pathContentProvider;

  public PathLabelProvider(PathContentProvider pathContentProvider) {
    this.pathContentProvider = pathContentProvider;
  }

  @Override
  public Image getImage(Object element) {
    @SuppressWarnings("unchecked")
    List<Object> nodePath = (List<Object>) element;
    if (nodePath.size() >= 2) {
      ObjRef xAdlRef = (ObjRef) xarch.get(docRef, "xADL");
      if (nodePath.get(1).equals(xAdlRef)) {
        if (pathContentProvider.paths.contains(nodePath.subList(2, nodePath.size()))) {
          return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
        }
      }
    }
    return null;
  }

  @Override
  public StyledString getStyledText(Object element) {
    @SuppressWarnings("unchecked")
    List<Object> nodePath = (List<Object>) element;
    if (nodePath.size() >= 2) {
      ObjRef xAdlRef = (ObjRef) xarch.get(docRef, "xADL");
      if (nodePath.get(1).equals(xAdlRef)) {
        if (pathContentProvider.paths.contains(nodePath.subList(2, nodePath.size()))) {
          return new StyledString(nodePath.get(nodePath.size() - 1).toString());
        }
      }
    }
    return null;
  }
}
