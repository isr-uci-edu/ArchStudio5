package org.archstudio.archipelago2.core.outline;

import org.archstudio.archipelago2.AbstractArchipelago2LabelProvider;
import org.archstudio.archipelago2.Archipelago2Utils;
import org.archstudio.utils.resources.swt.ImageUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xarchadt.IXArchADTFeature;
import org.archstudio.xarchadt.IXArchADTFeature.FeatureType;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * Returns an image icon and name, if available, for a given ObjRef. The image is retrieved from
 * {@link ImageUtils#getIcon16ForType(org.eclipse.swt.graphics.Device, Class)}.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class ObjRefLabelProvider extends AbstractArchipelago2LabelProvider
    implements IXArchADTModelListener {
  @Override
  public Image getImage(Object element) {
    ObjRef objRef = Archipelago2Utils.getLastObjRef(element);
    if (objRef != null) {
      return ImageUtils.getIcon16ForType(Display.getDefault(), XadlUtils.getType(xarch, objRef));
    }
    return null;
  }

  @Override
  public StyledString getStyledText(Object element) {
    ObjRef objRef = Archipelago2Utils.getLastObjRef(element);
    if (objRef != null) {
      IXArchADTTypeMetadata type = xarch.getTypeMetadata(objRef);
      IXArchADTFeature nameFeature = type.getFeatures().get("name");
      if (nameFeature != null) {
        if (nameFeature.getType() == FeatureType.ATTRIBUTE) {
          Object value = xarch.get(objRef, "name");
          if (value != null) {
            return new StyledString(value.toString());
          } else {
            return new StyledString("[No Name]");
          }
        }
      }
    }
    return null;
  }

  @Override
  public void handleXArchADTModelEvent(final XArchADTModelEvent evt) {
    switch (evt.getEventType()) {
      case SET: // Fall through.
      case CLEAR:
        if ("name".equals(evt.getFeatureName())) {
          display.asyncExec(new Runnable() {
            @Override
            public void run() {
              fireLabelProviderEvent(evt.getSource());
            }
          });
        }
      default:
        // Do nothing.
    }
  }
}
