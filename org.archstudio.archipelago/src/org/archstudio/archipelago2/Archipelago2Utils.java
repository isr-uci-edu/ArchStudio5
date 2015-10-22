package org.archstudio.archipelago2;

import java.util.Arrays;
import java.util.List;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.logics.editing.SelectionLogic;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2;
import org.archstudio.bna.utils.BNAUtils2.ThingReference;
import org.archstudio.sysutils.Finally;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.sysutils.WeakListener;
import org.archstudio.sysutils.WeakListener.RemoveListenerHandler;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.swt.graphics.Point;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

public class Archipelago2Utils {
  /**
   * Returns <code>true</code> if the last node objects in element match the node objects provided,
   * <code>false</code> otherwise.
   *
   * @see IArchipelago2ContentProvider
   * @param element The list of node objects in a Archipelago outline element.
   * @param nodeObjects The node objects expected at the end of the path.
   * @return <code>true</code> if the last node objects in element match the node objects provided,
   *         <code>false</code> otherwise.
   */
  public static final boolean pathEndsWith(Object element, Object... nodeObjects) {
    if (element instanceof List) {
      @SuppressWarnings("unchecked")
      List<Object> nodePath = (List<Object>) element;
      if (nodePath.size() >= nodeObjects.length) {
        if (nodePath.subList(nodePath.size() - nodeObjects.length, nodePath.size())
            .equals(Arrays.asList(nodeObjects))) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns the last node object if it is an ObjRef, or <code>null</code> otherwise.
   *
   * @see IArchipelago2ContentProvider
   * @param element The list of node objects in a Archipelago outline element.
   * @return The last node object if it is an ObjRef, or <code>null</code> otherwise.
   */
  public static final ObjRef getLastObjRef(Object element) {
    if (element instanceof List) {
      @SuppressWarnings("unchecked")
      List<Object> nodePath = (List<Object>) element;
      if (nodePath.size() > 0) {
        return SystemUtils.castOrNull(nodePath.get(nodePath.size() - 1), ObjRef.class);
      }
    }
    return null;
  }

  /**
   * Returns the ObjRef of the first thing with an {@link IHasObjRef#OBJREF_KEY an ObjRef value}, or
   * <code>null</code> if no things have an ObjRef.
   *
   * @param model The model with the things.
   * @param things The list of things to search for an ObjRef in.
   * @return the ObjRef of the first thing with an {@link IHasObjRef#OBJREF_KEY an ObjRef value}, or
   *         <code>null</code> if no things have an ObjRef.
   */
  public static final ObjRef getFirstObjRef(IBNAModel model, Iterable<IThing> things) {
    for (IThing thing : things) {
      IThing rootThing = Assemblies.getRoot(model, thing);
      ObjRef objRef = rootThing.get(IHasObjRef.OBJREF_KEY);
      if (objRef != null) {
        return objRef;
      }
    }
    return null;
  }

  /**
   * Searches for, selects, and navigates to the last ObjRef in the element path.
   *
   * @param bnaView The view in which to make the selection.
   * @param elementPath The {@link IArchipelago2ContentProvider element path} to use as a source of
   *        ObjRefs.
   */
  public static final void focusOnObjRef(IBNAView bnaView, List<Object> elementPath) {
    Preconditions.checkNotNull(bnaView);
    Preconditions.checkNotNull(elementPath);
    try (Finally lock = BNAUtils.lock()) {
      // Find the last ObjRef.
      ObjRef elementRef = null;
      for (Object element : Lists.reverse(elementPath)) {
        if (element instanceof ObjRef) {
          elementRef = (ObjRef) element;
          break;
        }
      }
      if (elementRef != null) {
        // Find a thing that represents that ObjRef.
        final ObjRef finalElementRef = elementRef;
        List<ThingReference> references = BNAUtils2.findThings(bnaView, new Predicate<IThing>() {
          @Override
          public boolean apply(IThing input) {
            return input.has(IHasObjRef.OBJREF_KEY, finalElementRef);
          }
        });
        if (references.size() > 0) {
          ThingReference reference = references.get(0);
          // Select the thing (and unselect everything else). But, only if it is user selectable.
          IHasMutableSelected selectableThing =
              Assemblies.getEditableThing(reference.getWorld().getBNAModel(), reference.getThing(),
                  IHasMutableSelected.class, IHasMutableSelected.USER_MAY_SELECT);
          if (selectableThing != null) {
            // Unselect everything else.
            SelectionLogic.setWorldWithSelectionFocus(reference.getWorld());
            SelectionLogic.unselectAllThings();
            selectableThing.setSelected(true);
          }
          // Fly to the thing.
          Point point =
              BNAUtils2.getRepresentativePoint(reference.getWorld(), reference.getThing());
          if (point != null) {
            BNAUtils2.flyViewTo(bnaView, point, 1000);
          }
          // Pulse notify thing.
          BNAUtils2.pulseNotify(reference.getWorld(), reference.getThing(), 4000);
          // Finally focus on view.
          bnaView.getBNAUI().getComposite().setFocus();
        }
      }
    }
  }

  /**
   * Returns a new list with the element added.
   *
   * @param list The original list to append to.
   * @param newElement The element to add to the end.
   * @return a new list with the element added.
   */
  public static <T> List<T> append(List<T> list, T newElement) {
    List<T> newList = Lists.newArrayListWithCapacity(list.size() + 1);
    newList.addAll(list);
    newList.add(newElement);
    return newList;
  }

  /**
   * Adds the listener to the preference store using a weak reference.
   *
   * @param preferenceStore The preference store to listen to.
   * @param listener The listener to receive property change events.
   */
  public static void weakListenToPropertyChanges(final IPreferenceStore preferenceStore,
      IPropertyChangeListener listener) {
    preferenceStore.addPropertyChangeListener(WeakListener.createWeakListener(listener,
        IPropertyChangeListener.class, new RemoveListenerHandler<IPropertyChangeListener>() {
          @Override
          public void removeListener(IPropertyChangeListener proxyListener) {
            preferenceStore.removePropertyChangeListener(proxyListener);
          }
        }));
  }
}
