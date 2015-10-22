package org.archstudio.archipelago2.core.outline;

import java.util.List;

import org.archstudio.archipelago2.AbstractArchipelago2LabelProvider;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * Displays a label for the root xADL element.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class XAdlLabelProvider extends AbstractArchipelago2LabelProvider {

  @Override
  public StyledString getStyledText(Object element) {
    @SuppressWarnings("unchecked")
    List<Object> nodePath = (List<Object>) element;
    if (nodePath.size() == 2) {
      ObjRef xAdlRef = (ObjRef) xarch.get(docRef, "xADL");
      if (nodePath.get(1) == xAdlRef) {
        URI uri = xarch.getURI(xAdlRef);
        List<String> segments = uri.segmentsList();
        if (segments.size() > 0) {
          return new StyledString("xADL: " + segments.get(segments.size() - 1));
        }
        return new StyledString("xADL: " + uri.toString());
      }
    }
    return null;
  }

  @Override
  public Image getImage(Object element) {
    @SuppressWarnings("unchecked")
    List<Object> nodePath = (List<Object>) element;
    if (nodePath.size() == 2) {
      ObjRef xAdlRef = (ObjRef) xarch.get(docRef, "xADL");
      if (nodePath.get(0) == xAdlRef) {
        return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
      }
    }
    return null;
  }
}
