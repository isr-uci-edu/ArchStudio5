package org.archstudio.xadl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.archstudio.xarchadt.IXArchADTFeature;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.IXArchADTQuery;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Monitors an XPath for added, modified, and removed objRefs rooted at an arbitrary ObjRef. When a
 * change is detected, notifies listeners implementing {@link IXArchRelativePathTrackerListener}.
 * <p/>
 * There is limited support for filtering nodes by properties other than their name. You can filter:
 * <ul>
 * <li>by attribute value, e.g., "node[@attribute='value']",</li>
 * <li>by attribute contains substring, e.g., "node[contains(@attribute, 'substring')]", and</li>
 * <li>by name space, e.g., "node[*[namespace-uri()='name_space_URI']]".</li>
 * </ul>
 */
public final class XArchRelativePathTracker implements IXArchADTModelListener {
  /**
   * Captures a group in a regular expression that represents a name. E.g., "name".
   */
  private static final String NAME_GROUP = "([a-zA-Z_][a-zA-Z0-9_]*)";

  /**
   * Captures a group in a regular expression that represents a string. E.g., "'string'".
   */
  private static final String STR_GROUP = "'([^']*)'";

  /**
   * The pattern for defining an attribute constraint on an XPath segment. E.g.,
   * "node[@attribute='value']".
   */
  private static final Pattern ATTRIBUTE_PATTERN = Pattern.compile(
      "\\s*" + NAME_GROUP + "\\s*\\[\\s*@" + NAME_GROUP + "\\s*=\\s*" + STR_GROUP + "\\s*\\]");

  /**
   * The pattern for defining a contains constraint on an XPath segment. E.g.,
   * "node[contains(@attribute, 'value')]".
   */
  private static final Pattern ATTRIBUTE_CONTAINS_PATTERN = Pattern.compile("\\s*" + NAME_GROUP
      + "\\s*\\[\\s*contains\\s*\\(@" + NAME_GROUP + "\\s*,\\s*" + STR_GROUP + "\\s*\\)\\s*\\]");

  /**
   * The pattern for defining a name space constraint on an XPath segment. E.g.,
   * "node[*[namespace-uri()='URL']]".
   */
  private static final Pattern NAMESPACE_PATTERN = Pattern
      .compile("\\s*" + NAME_GROUP + "\\s*\\[\\s*\\*\\s*\\[\\s*namespace-uri\\s*\\(\\s*\\)\\s*=\\s*"
          + STR_GROUP + "\\s*\\]\\s*\\]");

  /**
   * A predicate to check a node for a required attribute value.
   */
  private static final class RequireAttributeValuePredicate implements Predicate<ObjRef> {
    IXArchADTQuery xarch;
    String attribute;
    String value;

    public RequireAttributeValuePredicate(IXArchADTQuery xarch, String attribute, String value) {
      this.xarch = xarch;
      this.attribute = attribute;
      this.value = value;
    }

    @Override
    public boolean apply(ObjRef input) {
      Object attrValue = xarch.get(input, attribute);
      if (attrValue != null) {
        return value.equals(attrValue.toString());
      }
      return false;
    }
  }

  /**
   * A predicate to check a node for a required substring in an attribute.
   */
  private static final class RequireAttributeContainsValuePredicate implements Predicate<ObjRef> {
    IXArchADTQuery xarch;
    String attribute;
    String value;

    public RequireAttributeContainsValuePredicate(IXArchADTQuery xarch, String attribute,
        String value) {
      this.xarch = xarch;
      this.attribute = attribute;
      this.value = value;
    }

    @Override
    public boolean apply(ObjRef input) {
      Object attrValue = xarch.get(input, attribute);
      if (attrValue != null) {
        return attrValue.toString().indexOf(value) >= 0;
      }
      return false;
    }
  }

  /**
   * A predicate to check a node for a required namespace.
   */
  private static final class RequireNamespaceURIPredicate implements Predicate<ObjRef> {
    IXArchADTQuery xarch;
    String namespaceURI;

    public RequireNamespaceURIPredicate(IXArchADTQuery xarch, String namespaceURI) {
      this.xarch = xarch;
      this.namespaceURI = namespaceURI;
    }

    @Override
    public boolean apply(ObjRef input) {
      IXArchADTTypeMetadata typeMetadata = xarch.getTypeMetadata(input);
      return typeMetadata.getNsURI().equals(namespaceURI);
    }
  }

  /**
   * A node constraint in an XPath, consisting of a name and a predicate.
   */
  private static final class Segment {
    final String name;
    final Predicate<ObjRef> predicate;

    public Segment(String name, Predicate<ObjRef> predicate) {
      this.name = name;
      this.predicate = predicate;
    }
  }

  /**
   * Creates a new list with the additional element appended to it.
   *
   * @param list The original list.
   * @param newElement The new element to add.
   * @return a new list with the additional element appended to it.
   */
  private static final <T> List<T> append(List<T> list, T newElement) {
    List<T> newList = Lists.newArrayListWithCapacity(list.size() + 1);
    newList.addAll(list);
    newList.add(newElement);
    return newList;
  }

  /**
   * Creates a sublist of list, starting from the given index. Equivalent to
   * <code>list.subList(fromIndex, list.size())</code>.
   *
   * @param list The list from which to obtain the sublist.
   * @param fromIndex The beginning index of the sublist.
   * @return a sublist of list, starting from the given index.
   */
  private static final <T> List<T> subList(List<T> list, int fromIndex) {
    if (list == null) {
      return null;
    }
    return list.subList(fromIndex, list.size());
  }

  /**
   * Takes a path in the form of a string, selects the subpath from the given index, and returns the
   * subpath in the form of a string.
   *
   * @param path The starting path.
   * @param fromSegmentIndex The beginning segment index for the subpath.
   * @return a subpath starting at from the specified segment index.
   */
  private static final String subPath(String path, int fromSegmentIndex) {
    if (path == null) {
      return null;
    }
    int index = 0;
    int segment = 0;
    while (segment++ < fromSegmentIndex) {
      index = path.indexOf('/', index) + 1;
      if (index <= 0) {
        if (segment == fromSegmentIndex) {
          index = path.length();
        } else {
          throw new IllegalArgumentException(
              "Path \"" + path + "\" does not contain " + fromSegmentIndex + " segments.");
        }
      }
    }
    return path.substring(index);
  }

  /**
   * Parses a segment in an XPath (e.g., "node[@attribute='value']") into a node name and predicate
   * constraint.
   *
   * @param xarch The {@link IXArchADTQuery xarch} instance from which predicates should be
   *        verified.
   * @param segment The segment text to parse.
   * @return a {@link Segment} containing the node name and predicate constraint.
   */
  private static Segment parseSegment(IXArchADTQuery xarch, String segment) {
    Predicate<ObjRef> predicate = Predicates.alwaysTrue();
    Matcher m;
    if ((m = ATTRIBUTE_PATTERN.matcher(segment)).find()) {
      segment = m.group(1);
      predicate = new RequireAttributeValuePredicate(xarch, m.group(2), m.group(3));
    }
    if ((m = ATTRIBUTE_CONTAINS_PATTERN.matcher(segment)).find()) {
      segment = m.group(1);
      predicate = new RequireAttributeContainsValuePredicate(xarch, m.group(2), m.group(3));
    }
    if ((m = NAMESPACE_PATTERN.matcher(segment)).find()) {
      segment = m.group(1);
      predicate = new RequireNamespaceURIPredicate(xarch, m.group(2));
    }
    if (segment.indexOf('[') >= 0) {
      throw new IllegalArgumentException("Unrecognized XPath segment: " + segment);
    }
    return new Segment(segment, predicate);
  }

  private final IXArchADTQuery xarch;
  private final List<IXArchRelativePathTrackerListener> listeners = Lists.newCopyOnWriteArrayList();

  /** The root ObjRef from which the XPath will be applied. */
  private final ObjRef rootRef;

  /** The XPath in its original string format, e.g., "component/interface". */
  private final String xPathStr;

  /** The XPath parsed into individual segments, e.g., "component" and "interface". */
  private final List<Segment> xPath = Lists.newArrayList();

  /** Whether the {@link #rootRef} is currently being monitored for changes. */
  private boolean scanning = false;

  /**
   * The set of ObjRefs that have been added, with the list of ObjRefs connecting them to the
   * {@link #rootRef}. Keeping track of the ObjRefs from the rootRef is important because it allows
   * us to remove added ObjRefs if one of their ancestors connecting them to {@link #rootRef} is
   * removed.
   */
  private final Map<ObjRef, List<ObjRef>> addedObjRefToLineageRefs = Maps.newHashMap();

  /**
   * The set of all ObjRefs that match some segment of the XPath. This is used to detect when an
   * attribute change to an ObjRef effects whether that ObjRef matched the XPath segment. The value
   * is the segment index in {@link #xPath}.
   */
  private final Map<ObjRef, Integer> positiveObjRefs = Maps.newHashMap();

  /**
   * Creates a new XPath tracker. Note, this class must be added as a listener to the source of
   * change events for the document containing rootRef.
   *
   * @param xarch The {@link IXArchADTQuery xarch} instance on which to perform the XPath query.
   * @param rootRef The root ObjRef from which the XPath should be queried.
   * @param xPath The XPath to query.
   * @param startScanning If <code>true</code>, calls {@link #startScanning()} after initialization.
   */
  public XArchRelativePathTracker(IXArchADTQuery xarch, ObjRef rootRef, String xPath,
      boolean startScanning) {
    this.xarch = Preconditions.checkNotNull(xarch);
    this.rootRef = Preconditions.checkNotNull(rootRef);
    this.xPathStr = Preconditions.checkNotNull(xPath);
    Preconditions.checkArgument(xPath.length() != 0, "Missing required XPath.");
    parseXPath(xPath);
    if (startScanning) {
      startScanning();
    }
  }

  /**
   * Parses an XPath into individual {@link Segment}s.
   *
   * @param xPathStr The XPath string to parse.
   */
  private void parseXPath(String xPathStr) {
    int startIndex = 0;
    int endIndex = 0;
    int bracesCount = 0;
    for (char ch : (xPathStr + '/').toCharArray()) {
      switch (ch) {
        case '[':
          bracesCount++;
          break;
        case ']':
          bracesCount--;
          break;
        case '/':
          if (bracesCount == 0) {
            xPath.add(parseSegment(xarch, xPathStr.substring(startIndex, endIndex)));
            startIndex = endIndex + 1;
          }
          break;
      }
      endIndex++;
    }
    Preconditions.checkArgument(bracesCount == 0, "Malformed XPath: %s", xPathStr);
  }

  /**
   * Adds the listener to the collection of listeners what will be informed of XPath query results.
   *
   * @param listener The listener to be added.
   */
  public void addTrackerListener(IXArchRelativePathTrackerListener listener) {
    listeners.add(listener);
  }

  /**
   * Removes the listener from the collection of listeners what will be informed of XPath query
   * results.
   *
   * @param listener The listener to be removed.
   */
  public void removeTrackerListener(IXArchRelativePathTrackerListener listener) {
    listeners.remove(listener);
  }

  /**
   * Fires an {@link IXArchRelativePathTrackerListener#processAdd(List, ObjRef) add event} to the
   * listeners of XPath query results.
   *
   * @param descendantRefs The descendant refs starting with the rootRef leading to addedRef,
   *        inclusive.
   * @param addedRef The ObjRef that has been added.
   */
  protected void fireProcessAdd(List<ObjRef> descendantRefs, ObjRef addedRef) {
    for (IXArchRelativePathTrackerListener l : listeners) {
      try {
        l.processAdd(descendantRefs, addedRef);
      } catch (Throwable t) {
        t.printStackTrace();
      }
    }
  }

  /**
   * Fires an
   * {@link IXArchRelativePathTrackerListener#processUpdate(List, ObjRef, XArchADTModelEvent) update
   * event} to the listeners of XPath query results.
   *
   * @param descendantRefs The descendant refs starting with the rootRef leading to modifiedRef,
   *        inclusive.
   * @param modifiedRef The ObjRef that was modified.
   * @param relativeEvt The relative event, rooted in the modified Ref.
   */
  protected void fireProcessUpdate(List<ObjRef> descendantRefs, ObjRef modifiedRef,
      XArchADTModelEvent relEvt) {
    for (IXArchRelativePathTrackerListener l : listeners) {
      try {
        l.processUpdate(descendantRefs, modifiedRef, relEvt);
      } catch (Throwable t) {
        t.printStackTrace();
      }
    }
  }

  /**
   * Fires a {@link IXArchRelativePathTrackerListener#processRemove(List, ObjRef) remove event} to
   * the listeners of XPath query results.
   *
   * @param descendantRefs The descendant refs starting with the rootRef leading to removedRef,
   *        inclusive.
   * @param removedRef The ObjRef that has been removed.
   */
  protected void fireProcessRemove(List<ObjRef> descendantRefs, ObjRef removedRef) {
    for (IXArchRelativePathTrackerListener l : listeners) {
      try {
        l.processRemove(descendantRefs, removedRef);
      } catch (Throwable t) {
        t.printStackTrace();
      }
    }
  }

  /**
   * Returns the root ObjRef from which the XPath query is performed.
   *
   * @return the root ObjRef from which the XPath query is performed.
   */
  public ObjRef getRootObjRef() {
    return rootRef;
  }

  /**
   * Returns the XPath query being tracked.
   *
   * @return the XPath query being tracked.
   */
  public String getXPath() {
    return xPathStr;
  }

  /**
   * Starts monitoring, including scanning for existing ObjRefs and firing an
   * {@link IXArchRelativePathTrackerListener#processAdd(List, ObjRef) add event} for each.
   */
  public void startScanning() {
    synchronized (addedObjRefToLineageRefs) {
      if (!scanning) {
        scanning = true;
        scanObjRef(Lists.newArrayList(rootRef));
      }
    }
  }

  /**
   * Returns <code>true</code> if this tracker has been started, <code>false</code> otherwise.
   *
   * @return <code>true</code> if this tracker has been started, <code>false</code> otherwise.
   */
  public boolean isScanning() {
    return scanning;
  }

  /**
   * Stops monitoring, including iterating through added ObjRefs and firing a
   * {@link IXArchRelativePathTrackerListener#processRemove(List, ObjRef) removal event} for each.
   */
  public void stopScanning() {
    synchronized (addedObjRefToLineageRefs) {
      if (scanning) {
        scanning = false;
        for (List<ObjRef> objRefs : Lists.newArrayList(addedObjRefToLineageRefs.values())) {
          fireProcessRemove(objRefs, objRefs.get(objRefs.size() - 1));
        }
        positiveObjRefs.clear();
        addedObjRefToLineageRefs.clear();
      }
    }
  }

  /**
   * Returns a copy of the ObjRefs currently resulting from the XPath query.
   *
   * @return a copy of the ObjRefs currently at the XPath.
   */
  public Set<ObjRef> getAddedRefs() {
    return Sets.newHashSet(addedObjRefToLineageRefs.keySet());
  }

  /**
   * Scans the last ObjRef of descendantRefs for ObjRefs matching the XPath.
   *
   * @param descendantRefs The descendants starting with {@link #rootRef} that match (a prefix of)
   *        the XPath.
   */
  protected void scanObjRef(List<ObjRef> descendantRefs) {
    // Ensure that the descendants starts with rootRef.
    Preconditions.checkArgument(descendantRefs.size() > 0);
    Preconditions.checkArgument(descendantRefs.get(0) == rootRef);
    // Ensure that the descendants do not exceed the XPath segment length.
    Preconditions.checkArgument(descendantRefs.size() <= xPath.size() + 1);

    // If the ObjRef matches the last segment of the XPath, add it.
    ObjRef objRef = descendantRefs.get(descendantRefs.size() - 1);
    positiveObjRefs.put(objRef, descendantRefs.size() - 2);
    if (descendantRefs.size() == xPath.size() + 1) {
      if (addedObjRefToLineageRefs.put(objRef, descendantRefs) == null) {
        fireProcessAdd(descendantRefs, objRef);
      }
      return;
    }

    // If not, check its children and check them against the corresponding XPath segment.
    Segment childSegment = xPath.get(descendantRefs.size() - 1);
    IXArchADTTypeMetadata type = xarch.getTypeMetadata(objRef);
    IXArchADTFeature feature = type.getFeatures().get(childSegment.name);
    if (feature == null) {
      throw new IllegalArgumentException(objRef + "(" + type.getTypeName()
          + ") doesn't contain feature " + childSegment.name + ".");
    }
    switch (feature.getType()) {
      case ELEMENT_SINGLE: {
        Object child = xarch.get(objRef, childSegment.name);
        if (child instanceof ObjRef) {
          if (childSegment.predicate.apply((ObjRef) child)) {
            scanObjRef(append(descendantRefs, (ObjRef) child));
          }
        } else if (child != null) {
          throw new IllegalArgumentException("Feature " + childSegment.name + " of " + objRef + "("
              + type.getTypeName() + ") doesn't contain an ObjRef for XPath " + xPathStr + ".");
        }
      }
        break;
      case ELEMENT_MULTIPLE: {
        for (Serializable child : xarch.getAll(objRef, childSegment.name)) {
          if (child instanceof ObjRef) {
            if (childSegment.predicate.apply((ObjRef) child)) {
              scanObjRef(append(descendantRefs, (ObjRef) child));
            }
          } else if (child != null) {
            throw new IllegalArgumentException(
                "Feature " + childSegment.name + " of " + objRef + "(" + type.getTypeName()
                    + ") doesn't contain an ObjRef for XPath " + xPathStr + ".");
          }
        }
      }
        break;
      default:
        throw new IllegalArgumentException("Feature " + childSegment.name + " of " + objRef + "("
            + type.getTypeName() + ") is an invalid type: " + feature.getType().name() + ".");
    }
  }

  /**
   * Removes added ObjRefs that have been a part of a positive match of the XPath. This is
   * accomplished by scanning each value in {@link #addedObjRefToLineageRefs} and removing the
   * corresponding keys.
   *
   * @param objRef The ObjRef in the lineage of ObjRefs that should be removed.
   * @return <code>true</code> if ObjRefs were removed, <code>false</code> otherwise.
   */
  protected boolean removeObjRef(ObjRef objRef) {
    boolean removedRefs = false;
    Integer xPathIndexInt = positiveObjRefs.remove(objRef);
    if (xPathIndexInt != null) {
      int xPathIndex = xPathIndexInt;
      // Gather the lineage refs of the ObjRefs that are to be removed.
      List<List<ObjRef>> removedLineageRefs =
          Lists.newArrayListWithCapacity(addedObjRefToLineageRefs.size());
      for (Iterator<Entry<ObjRef, List<ObjRef>>> i =
          addedObjRefToLineageRefs.entrySet().iterator(); i.hasNext();) {
        List<ObjRef> lineageRefs = i.next().getValue();
        if (lineageRefs.get(xPathIndex + 1).equals(objRef)) {
          i.remove();
          removedLineageRefs.add(lineageRefs);
        }
      }
      // Remove them.
      removedRefs = removedLineageRefs.size() > 0;
      for (List<ObjRef> lineageRefs : removedLineageRefs) {
        // Remove the ObjRefs from the set of positive ObjRefs.
        for (int i = xPathIndex + 1; i < lineageRefs.size(); ++i) {
          positiveObjRefs.remove(lineageRefs.get(i));
        }
        // Fire the removal event.
        fireProcessRemove(lineageRefs, lineageRefs.get(lineageRefs.size() - 1));
      }
    }
    return removedRefs;
  }

  /**
   * Returns whether the descendants match the XPath.
   *
   * @param descendantRefs The descendants, starting with {@link #rootRef}, to test agains the
   *        XPath. May be <code>null</code> if only names should be checked.
   * @param descendantNames The names of the descendants, <i>excluding</i> that for the rootRef, to
   *        test against the XPath.
   * @return <code>true</code> if the descendants match, <code>false</code> otherwise.
   */
  protected boolean xPathMatches(List<ObjRef> descendantRefs, List<String> descendantNames) {
    if (descendantRefs != null) {
      // Ensure that the descendants starts with rootRef.
      Preconditions.checkArgument(descendantRefs.size() > 0);
      Preconditions.checkArgument(descendantRefs.get(0) == rootRef);
      // Ensure that the descendants are of the same size.
      Preconditions.checkArgument(descendantRefs.size() == descendantNames.size() + 1);
    }
    // Check that the names match.
    for (int i = 0; i < xPath.size() && i < descendantNames.size(); ++i) {
      if (!xPath.get(i).name.equals(descendantNames.get(i))) {
        return false;
      }
    }
    if (descendantRefs != null) {
      // Check that the preconditions match.
      for (int i = 0; i < xPath.size() && i + 1 < descendantRefs.size(); ++i) {
        if (!xPath.get(i).predicate.apply(descendantRefs.get(i + 1))) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public void handleXArchADTModelEvent(XArchADTModelEvent evt) {
    if (scanning) {
      synchronized (addedObjRefToLineageRefs) {
        // Indicates that an update event is unnecessary, e.g., because an add or remove event
        // already occurred.
        boolean skipUpdateEvent = false;
        // If an ObjRef was removed (or replaced) create a remove event.
        switch (evt.getEventType()) {
          case CLEAR: // Fall through.
          case REMOVE: // Fall through.
          case SET:
            if (evt.getOldValue() instanceof ObjRef) {
              ObjRef oldRef = (ObjRef) evt.getOldValue();
              // Check whether the removed ObjRef is a candidate for an XPath match.
              List<ObjRef> descendants = Lists.reverse(evt.getOldValueAncestors());
              int indexOfRootRef = descendants.indexOf(rootRef);
              if (indexOfRootRef < 0) {
                return;
              }
              descendants = subList(descendants, indexOfRootRef);
              // Determine whether oldRef causes the removal of an ObjRef.
              if (descendants.size() > xPath.size() + 1) {
                break;
              }
              // If all descendants are in positiveObjRefs then we remove it.
              for (ObjRef descendantRef : descendants) {
                if (!positiveObjRefs.containsKey(descendantRef)) {
                  return;
                }
              }
              if (removeObjRef(oldRef)) {
                skipUpdateEvent = true;
                break;
              }
            }
            break;
          default:
            // Do nothing.
            break;
        }
        // If an ObjRef was added (or replaced) create an add event.
        switch (evt.getEventType()) {
          case ADD: // Fall through.
          case SET:
            if (evt.getNewValue() instanceof ObjRef) {
              // We don't process the addition of the rootRef itself.
              ObjRef newRef = (ObjRef) evt.getNewValue();
              if (rootRef.equals(newRef)) {
                break;
              }
              // Determine if newRef is related to rootRef.
              List<ObjRef> descendants = Lists.reverse(evt.getNewValueAncestors());
              int indexOfRootRef = descendants.indexOf(rootRef);
              if (indexOfRootRef < 0) {
                return;
              }
              descendants = subList(descendants, indexOfRootRef);
              // Determine whether newRef causes the addition of an ObjRef.
              if (descendants.size() > xPath.size() + 1) {
                break;
              }
              List<String> path = Arrays.asList(evt.getNewValuePath().split("/"));
              path = subList(path, indexOfRootRef);
              if (!xPathMatches(descendants, path)) {
                break;
              }
              // Scan for new ObjRefs.
              scanObjRef(descendants);
              skipUpdateEvent = true;
            }
            break;
          default:
            // Do nothing.
            break;
        }
        // Changing an attribute can cause an ObjRef to match or not match an XPath segment.
        switch (evt.getEventType()) {
          case CLEAR: // Fall through.
          case SET:
            // We already handled ObjRef changes above.
            if (evt.getOldValue() instanceof ObjRef) {
              break;
            }
            if (evt.getNewValue() instanceof ObjRef) {
              break;
            }
            // Check whether the newly modified ObjRef is a candidate for an XPath match.
            List<ObjRef> descendants = Lists.reverse(evt.getSourceAncestors());
            int indexOfRootRef = descendants.indexOf(rootRef);
            if (indexOfRootRef < 0) {
              return;
            }
            descendants = subList(descendants, indexOfRootRef);
            if (descendants.size() > xPath.size() + 1) {
              break;
            }
            // Check whether the ObjRef matched before and if it is the same now.
            List<String> path = Arrays.asList(evt.getSourcePath().split("/"));
            path = subList(path, indexOfRootRef);
            if (xPathMatches(descendants, path)) {
              if (!positiveObjRefs.containsKey(evt.getSource())) {
                scanObjRef(descendants);
                skipUpdateEvent = true;
              }
            } else {
              if (removeObjRef(evt.getSource())) {
                skipUpdateEvent = true;
              }
            }
            break;
          default:
            // Do nothing.
            break;
        }
        // If an addition/removal event already occurred, skip sending an update event.
        if (skipUpdateEvent) {
          return;
        }
        // Get the relative descendants under rootRef.
        List<ObjRef> descendants = Lists.reverse(evt.getSourceAncestors());
        int indexOfRootRef = descendants.indexOf(rootRef);
        if (indexOfRootRef < 0) {
          return;
        }
        descendants = subList(descendants, indexOfRootRef);
        // Send an event if the update is under an added ref.
        if (descendants.size() > xPath.size()) {
          ObjRef addedRef = descendants.get(xPath.size());
          if (addedObjRefToLineageRefs.containsKey(addedRef)) {
            // Make an event relative to the added ref.
            int addedRefIndex = indexOfRootRef + xPath.size();
            XArchADTModelEvent relEvt = new XArchADTModelEvent(evt.getEventType(), evt.getSource(),
                evt.getSourceAncestors().subList(0,
                    evt.getSourceAncestors().size() - addedRefIndex),
                subPath(evt.getSourcePath(), addedRefIndex), evt.getFeatureName(),
                evt.getOldValue(), subPath(evt.getOldValuePath(), addedRefIndex), evt.getNewValue(),
                subPath(evt.getNewValuePath(), addedRefIndex));
            fireProcessUpdate(descendants, addedRef, relEvt);
          }
        }
      }
    }
  }

  @Override
  public String toString() {
    return "rootRef=" + rootRef + "(" + xarch.getTypeMetadata(rootRef).getTypeName() + "), " + //
        "xPath=" + xPathStr + ", " + "scanning=" + scanning;
  }
}
