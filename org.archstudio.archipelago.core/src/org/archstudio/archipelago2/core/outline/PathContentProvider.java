package org.archstudio.archipelago2.core.outline;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.archstudio.archipelago2.AbstractArchipelago2ContentProvider;
import org.archstudio.xarchadt.ObjRef;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

/**
 * Adds nodes matching the paths passed to {@link #addPath(String)} under the root xADL node of the
 * Archipelago outline view.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class PathContentProvider extends AbstractArchipelago2ContentProvider {
  /** Mapping from parent path nodes to child nodes. */
  private final Multimap<List<Object>, Object> parentPathToChildren = HashMultimap.create();

  /** The set of all paths leading up to and include those passed to {@link #addPath(String)}. */
  /* package */ final Set<List<Object>> paths = Sets.newHashSet();

  /**
   * Add a path to appear in the outline.
   *
   * @param path a '/' separates nodes to include in the outline.
   */
  public void addPath(String path) {
    List<Object> pathNodes = Arrays.asList((Object[]) path.split("/"));
    for (int i = 0; i < pathNodes.size(); ++i) {
      parentPathToChildren.put(pathNodes.subList(0, i), pathNodes.get(i));
    }
    for (int i = 1; i <= pathNodes.size(); ++i) {
      paths.add(pathNodes.subList(0, i));
    }
  }

  @Override
  public Object[] getElements(Object inputElement) {
    @SuppressWarnings("unchecked")
    List<Object> nodePath = (List<Object>) inputElement;
    if (nodePath.size() >= 2) {
      ObjRef xAdlRef = (ObjRef) xarch.get(docRef, "xADL");
      if (nodePath.get(1).equals(xAdlRef)) {
        return parentPathToChildren.get(nodePath.subList(2, nodePath.size())).toArray();
      }
    }
    return EMPTY_ARRAY;
  }
}
