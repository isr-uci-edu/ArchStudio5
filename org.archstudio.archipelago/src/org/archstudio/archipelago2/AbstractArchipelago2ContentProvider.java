package org.archstudio.archipelago2;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

public abstract class AbstractArchipelago2ContentProvider extends AbstractArchipelago2Provider
    implements IArchipelago2ContentProvider {
  /** An empty array to return when there are no children. */
  protected static final Object[] EMPTY_ARRAY = new Object[0];

  /** The viewer provided to {@link #inputChanged(Viewer, Object, Object)}. */
  protected TreeViewer viewer = null;

  /** The list of label provider listeners. */
  protected Set<ILabelProviderListener> labelListeners = new CopyOnWriteArraySet<>();

  @Override
  public void addListener(ILabelProviderListener listener) {
    labelListeners.add(listener);
  }

  @Override
  public void removeListener(ILabelProviderListener listener) {
    labelListeners.remove(listener);
  }

  /**
   * This function is only pulled in because of {@link IBaseLabelProvider}, it is not used.
   */
  @Override
  public final boolean isLabelProperty(Object element, String property) {
    return false;
  }

  /**
   * Fires a {@link LabelProviderChangedEvent} for the given elements to indicate that they should
   * be refreshed. The elements may be tree elements (i.e., lists of node objects) or nodes (the
   * objects themselves).
   *
   * @param elements The element whose contents should be refreshed.
   */
  protected void fireLabelProviderEvent(Object... elements) {
    LabelProviderChangedEvent event = new LabelProviderChangedEvent(this, elements);
    for (ILabelProviderListener listener : labelListeners) {
      listener.labelProviderChanged(event);
    }
  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    this.viewer = (TreeViewer) viewer;
  }
}
