package org.archstudio.archipelago2.ext.structures.outline;

import java.util.ArrayList;
import java.util.List;

import org.archstudio.archipelago2.AbstractArchipelago2ContentProvider;
import org.archstudio.archipelago2.Archipelago2Utils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;

public class StructuresContentProvider extends AbstractArchipelago2ContentProvider
    implements IXArchADTModelListener {
  @Override
  public Object[] getElements(Object inputElement) {
    ObjRef xAdlRef = (ObjRef) xarch.get(docRef, "xADL");
    if (Archipelago2Utils.pathEndsWith(inputElement, xAdlRef, "Structures")) {
      return XadlUtils
          .getAllSubstitutionGroupElementsByTag(xarch, xAdlRef, "topLevelElement", "structure")
          .toArray();
    }
    ObjRef lastRef = Archipelago2Utils.getLastObjRef(inputElement);
    if (XadlUtils.isInstanceOf(xarch, lastRef, Structure_3_0Package.Literals.STRUCTURE)) {
      List<Object> children = new ArrayList<>();
      children.addAll(xarch.getAll(lastRef, "Component"));
      children.addAll(xarch.getAll(lastRef, "Connector"));
      children.addAll(xarch.getAll(lastRef, "Link"));
      return children.toArray();
    }
    if (XadlUtils.isInstanceOf(xarch, lastRef, Structure_3_0Package.Literals.BRICK)) {
      List<Object> children = new ArrayList<>();
      children.addAll(xarch.getAll(lastRef, "Interface"));
      return children.toArray();
    }
    return EMPTY_ARRAY;
  }

  @Override
  public void handleXArchADTModelEvent(final XArchADTModelEvent evt) {
    switch (evt.getEventType()) {
      case ADD: // Fall through.
      case REMOVE:
        // If a structure was added/removed, refresh the structure list.
        if (evt.getSource().equals(xarch.get(docRef, "xADL"))
            && evt.getFeatureName().equals("structure")) {
          display.asyncExec(new Runnable() {
            @Override
            public void run() {
              fireLabelProviderEvent("Structures");
            }
          });
        }
        // If an element in a structure was added/removed, refresh that structure.
        if (XadlUtils.isInstanceOf(xarch, evt.getSource(),
            Structure_3_0Package.Literals.STRUCTURE)) {
          display.asyncExec(new Runnable() {
            @Override
            public void run() {
              fireLabelProviderEvent(evt.getSource());
            }
          });
        }
        // If an interface on a brick was added/removed, refresh that brick.
        if (XadlUtils.isInstanceOf(xarch, evt.getSource(), Structure_3_0Package.Literals.BRICK)) {
          display.asyncExec(new Runnable() {
            @Override
            public void run() {
              fireLabelProviderEvent(evt.getSource());
            }
          });
        }
      default: // Ignore.
    }
  }
}
