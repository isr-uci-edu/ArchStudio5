package org.archstudio.archipelago2;

import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * Provides children for a given element in the Archipelago outline view.
 * <p/>
 * The Archipelago outline consists of {@link TreeViewer} elements. The Archipelago outline stores
 * in each element a list of node objects starting from the root docRef provided in the init(...)
 * method then the node objects of all nodes in the path leading up to the leaf. In this way,
 * operations always have the full context of each element in the tree. We use "node object" to
 * indicate the single object for a node in the tree and "element" to indicate the List of node
 * objects stored at each element in the {@link TreeViewer}.
 * <p/>
 * Because elements in the tree are lists rather than individual objects, this interface adds
 * <p/>
 * Content from multiple {@link IArchipelago2ContentProvider} instances are merged together to form
 * a complete set of all child elements in the outline. Thus, {@link #getElements(Object)} will be
 * called with results from other content providers and should handle this situation gracefully.
 * <p/>
 * Though the outline is a tree structure, we only implement
 * {@link IStructuredContentProvider#getElements(Object)}. This method takes the list of node
 * objects making the element in the tree and returns the child objects (not child element lists)
 * that should be created as children.
 * <p/>
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public interface IArchipelago2ContentProvider
    extends IArchipelago2Provider, IBaseLabelProvider, IStructuredContentProvider {
  /**
   * Returns children for a given element list of node objects in the Archipelago outline view.
   * Content from multiple instances of {@link IArchipelago2ContentProvider} are merged together by
   * the Archipelago outline to form a complete tree of all content providers. Thus, this method
   * should handle elements from other providers gracefully (e.g., by returning an empty array of
   * objects).
   *
   * @param inputElement A {@link List} of all node objects starting with the docRef root and
   *        including all node elements up to the leaf being queried. The list will always have at
   *        least the docRef element in it.
   * @return An array of child node objects (not {@link List} instances). The Archipelago outline
   *         will create new lists for these node objects, or an empty array if this content
   *         provider is to contribute no child node objects.
   */
  @Override
  Object[] getElements(Object inputElement);

  /**
   * Adds a listener to be informed when tree elements (i.e., lists of node objects) or nodes (the
   * objects themselves) should be refreshed.
   *
   * @param listener A label provider listener.
   */
  @Override
  public void addListener(ILabelProviderListener listener);

  /**
   * Removes a listener to be informed when tree elements (i.e., lists of node objects) or nodes
   * (the objects themselves) should be refreshed.
   *
   * @param listener A label provider listener.
   */
  @Override
  public void removeListener(ILabelProviderListener listener);
}
