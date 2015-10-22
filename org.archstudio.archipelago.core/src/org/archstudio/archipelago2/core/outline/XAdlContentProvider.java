package org.archstudio.archipelago2.core.outline;

import java.util.List;

import org.archstudio.archipelago2.AbstractArchipelago2ContentProvider;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xarchadt.ObjRef;

/**
 * Provides the root xADL element.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class XAdlContentProvider extends AbstractArchipelago2ContentProvider {
  @Override
  public Object[] getElements(Object inputElement) {
    @SuppressWarnings("unchecked")
    List<Object> nodePath = (List<Object>) inputElement;
    if (nodePath.size() == 1) {
      ObjRef xAdlRef =
          (ObjRef) xarch.get(docRef, Xadlcore_3_0Package.Literals.DOCUMENT_ROOT__XADL.getName());
      if (xAdlRef == null) {
        xAdlRef = xarch.create(Xadlcore_3_0Package.eNS_URI,
            Xadlcore_3_0Package.Literals.XADL_TYPE.getName());
        xarch.set(docRef, Xadlcore_3_0Package.Literals.DOCUMENT_ROOT__XADL.getName(), xAdlRef);
      }
      return new Object[] {xAdlRef};
    }
    return EMPTY_ARRAY;
  }
}
