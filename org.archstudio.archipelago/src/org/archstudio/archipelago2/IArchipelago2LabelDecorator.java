package org.archstudio.archipelago2;

import java.util.List;

import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.StyledString;

/**
 * An extended {@link ILabelDecorator} to decorate a styled string.
 * <p/>
 * The Archipelago outline will iterate through all {@link IArchipelago2LabelDecorator} instances
 * and allow each one to decorate the styled text. Thus, implementers should be selective about
 * which elements return a value.
 * <p/>
 * All <code>element</code> parameters are lists, as described in
 * {@link IArchipelago2ContentProvider}.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public interface IArchipelago2LabelDecorator extends IArchipelago2Provider, ILabelDecorator {
  /**
   * Returns a (possibly updated) styled string, but decorated with additional information relating
   * to the provided element.
   * <p/>
   * Text and image decoration updates can occur as a result of other updates within the workbench
   * including deferred decoration by background processes. Clients should handle
   * labelProviderChangedEvents for the given element to get the complete decoration.
   *
   * @param styledString The input text label to decorate.
   * @param element A {@link List} of all node objects starting with the docRef root and including
   *        all node elements up to the leaf being queried. The list will always have at least the
   *        docRef element in it.
   * @return the new styled text label, or <code>null</code> if no decoration is to be applied.
   */
  public StyledString decorateStyledText(StyledString styledString, Object element);

  /**
   * This will not be called. Use {@link #decorateStyledText(StyledString, Object)}.
   */
  @Deprecated
  @Override
  String decorateText(String text, Object element);
}
