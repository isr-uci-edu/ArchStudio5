package org.archstudio.archipelago2;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

public abstract class AbstractArchipelago2LabelProvider extends AbstractArchipelago2Provider
    implements IArchipelago2LabelProvider {
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
   * Fires a {@link LabelProviderChangedEvent} for the given elements to the label provider
   * listeners.
   *
   * @param elements The element whose labels have changed.
   */
  protected void fireLabelProviderEvent(Object... elements) {
    LabelProviderChangedEvent event = new LabelProviderChangedEvent(this, elements);
    for (ILabelProviderListener listener : labelListeners) {
      listener.labelProviderChanged(event);
    }
  }

  @Override
  public boolean isLabelProperty(Object element, String property) {
    return true;
  }

  @Override
  public Image getImage(Object element) {
    return null;
  }

  @Override
  public StyledString getStyledText(Object element) {
    return null;
  }
}
