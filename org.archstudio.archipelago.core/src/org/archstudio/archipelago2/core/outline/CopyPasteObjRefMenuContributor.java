package org.archstudio.archipelago2.core.outline;

import java.util.List;

import org.archstudio.archipelago2.AbstractArchipelago2MenuContributor;
import org.archstudio.archipelago2.Archipelago2Utils;
import org.archstudio.archipelago2.IArchipelago2MenuContributor;
import org.archstudio.archipelago2.IArchipelago2Outline;
import org.archstudio.bna.ui.IBNAMenuListener2;
import org.archstudio.utils.resources.Resources;
import org.archstudio.utils.resources.swt.ImageUtils;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xarchadt.IXArchADTFeature;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;

public class CopyPasteObjRefMenuContributor extends AbstractArchipelago2MenuContributor
    implements IArchipelago2MenuContributor {
  @Override
  public void fillMenu(IArchipelago2Outline outline, List<Object> element,
      IMenuManager menuManager) {
    final ObjRef objRef = Archipelago2Utils.getLastObjRef(element);
    if (objRef != null) {
      // Don't delete the XADLType.
      if (xarch.isInstanceOf(objRef, Xadlcore_3_0Package.eNS_URI,
          Xadlcore_3_0Package.Literals.XADL_TYPE.getName())) {
        return;
      }
      IXArchADTTypeMetadata type = xarch.getTypeMetadata(objRef);
      menuManager.appendToGroup(IBNAMenuListener2.PRIMARY_PROPERTIES_GROUP,
          new Action("Delete " + type.getTypeName()) {
            @Override
            public void run() {
              ObjRef parentRef = xarch.getParent(objRef);
              String featureName = xarch.getContainingFeatureName(objRef);
              IXArchADTTypeMetadata type = xarch.getTypeMetadata(parentRef);
              IXArchADTFeature feature = type.getFeatures().get(featureName);
              switch (feature.getType()) {
                case ELEMENT_SINGLE:
                  xarch.clear(parentRef, featureName);
                  break;
                case ELEMENT_MULTIPLE:
                  xarch.remove(parentRef, featureName, objRef);
                  break;
                default:
                  // Do nothing.
                  break;
              }
            }

            @Override
            public ImageDescriptor getImageDescriptor() {
              return ImageUtils.toImageDescriptor(
                  ImageUtils.getImage(Display.getCurrent(), Resources.DELETE_ICON_16));
            }
          });
    }
  }
}
