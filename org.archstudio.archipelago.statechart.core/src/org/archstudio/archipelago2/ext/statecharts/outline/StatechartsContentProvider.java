package org.archstudio.archipelago2.ext.statecharts.outline;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.archstudio.archipelago2.AbstractArchipelago2ContentProvider;
import org.archstudio.archipelago2.Archipelago2Utils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;

public class StatechartsContentProvider extends AbstractArchipelago2ContentProvider
    implements IXArchADTModelListener {
  /**
   * Returns <code>true</code> if the element is a statechart ref, <code>false</code> otherwise.
   *
   * @param object The element to evaluate.
   * @return <code>true</code> if the element is a statechart ref, <code>false</code> otherwise.
   */
  private boolean isStatechart(Object object) {
    if (object instanceof Serializable) {
      return xarch.isInstanceOf((Serializable) object, Statechart_1_0Package.eNS_URI,
          Statechart_1_0Package.Literals.STATECHART.getName());
    } else {
      return false;
    }
  }

  @Override
  public Object[] getElements(Object inputElement) {
    ObjRef xAdlRef = (ObjRef) xarch.get(docRef, "xADL");
    if (Archipelago2Utils.pathEndsWith(inputElement, xAdlRef, "Statecharts")) {
      return XadlUtils
          .getAllSubstitutionGroupElementsByTag(xarch, xAdlRef, "topLevelElement", "statechart")
          .toArray();
    }
    ObjRef lastRef = Archipelago2Utils.getLastObjRef(inputElement);
    if (isStatechart(lastRef)) {
      List<Object> children = new ArrayList<>();
      children.addAll(xarch.getAll(lastRef, "State"));
      children.addAll(xarch.getAll(lastRef, "Transition"));
      return children.toArray();
    }
    return EMPTY_ARRAY;
  }

  @Override
  public void handleXArchADTModelEvent(final XArchADTModelEvent evt) {
    switch (evt.getEventType()) {
      case ADD: // Fall through.
      case REMOVE:
        // If a statechart was added/removed, refresh the statechart list.
        if (evt.getSource().equals(xarch.get(docRef, "xADL"))
            && (isStatechart(evt.getOldValue()) || isStatechart(evt.getNewValue()))) {
          display.asyncExec(new Runnable() {
            @Override
            public void run() {
              fireLabelProviderEvent("Statecharts");
            }
          });
        }
        // If an element of a statechart was added/removed, refresh that statechart.
        if (isStatechart(evt.getSource())) {
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
