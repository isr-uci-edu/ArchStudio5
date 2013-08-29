package org.archstudio.xadl;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xarchadt.IXArchADTFeature;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.IXArchADTQuery;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Notifies listeners of added, modified, and removed objRefs that are pointed to by an xPath relative to a given root
 * objRef. The root objRef does not need to be attached.
 * 
 * @see IXArchRelativePathTrackerListener
 */
public final class XArchRelativePathTracker implements IXArchADTModelListener {

	private final IXArchADTQuery xarch;

	private final List<IXArchRelativePathTrackerListener> pathTrackerListeners = new CopyOnWriteArrayList<IXArchRelativePathTrackerListener>();

	public void addTrackerListener(IXArchRelativePathTrackerListener l) {
		pathTrackerListeners.add(l);
	}

	public void removeTrackerListener(IXArchRelativePathTrackerListener l) {
		pathTrackerListeners.remove(l);
	}

	protected void processAdd(List<ObjRef> relLineage, ObjRef objRef) {
		for (IXArchRelativePathTrackerListener l : pathTrackerListeners) {
			try {
				l.processAdd(relLineage, objRef);
			}
			catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}

	protected void processUpdate(List<ObjRef> relLineage, String relPath, ObjRef objRef, XArchADTModelEvent evt) {
		for (IXArchRelativePathTrackerListener l : pathTrackerListeners) {
			l.processUpdate(relLineage, relPath, objRef, evt);
		}
	}

	protected void processRemove(List<ObjRef> relLineage, ObjRef objRef) {
		for (IXArchRelativePathTrackerListener l : pathTrackerListeners) {
			l.processRemove(relLineage, objRef);
		}
	}

	// TODO: use real xpaths, JET supposedly implements one for EMF

	private static final Pattern namespacePattern = Pattern.compile("([^\\[]*)\\[\\*\\[namespace-uri\\(\\)='([^']*)'");

	private class RequireNamespaceURIPredicate implements Predicate<ObjRef> {

		String namespaceURI;

		public RequireNamespaceURIPredicate(String namespaceURI) {
			this.namespaceURI = namespaceURI;
		}

		@Override
		public boolean apply(ObjRef input) {
			IXArchADTTypeMetadata typeMetadata = xarch.getTypeMetadata(input);
			return typeMetadata.getNsURI().equals(namespaceURI);
		}
	}

	private static final Pattern attributePattern = Pattern.compile("([^\\[]*)\\[@([^=\\s]*)\\s*=\\s*'([^']*)'");

	private class RequireAttributeValuePredicate implements Predicate<ObjRef> {

		String attribute;
		String value;

		public RequireAttributeValuePredicate(String attribute, String value) {
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

	private static final Pattern attributeContainsPattern = Pattern.compile("([^\\[]*)\\[contains\\(@([^=\\s]*),'([^']*)'");

	private class RequireAttributeContainsValuePredicate implements Predicate<ObjRef> {

		String attribute;
		String value;

		public RequireAttributeContainsValuePredicate(String attribute, String value) {
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

	private static class Segment {

		/**
		 * The name of the segment
		 */
		final String name;

		/**
		 * A predicate to handle special xPath segment conditions, e.g., to check for a required attribute
		 */
		final Predicate<ObjRef> predicate;

		public Segment(String name, Predicate<ObjRef> predicate) {
			super();
			this.name = name;
			this.predicate = predicate;
		}

	}

	private List<Segment> parseSegments(List<String> segmentTexts) {
		List<Segment> segments = Lists.newArrayListWithCapacity(segmentTexts.size());
		for (String name : segmentTexts) {
			Predicate<ObjRef> predicate = Predicates.alwaysTrue();
			Matcher m;
			if ((m = namespacePattern.matcher(name)).find()) {
				name = m.group(1);
				predicate = new RequireNamespaceURIPredicate(m.group(2));
			}
			if ((m = attributePattern.matcher(name)).find()) {
				name = m.group(1);
				predicate = new RequireAttributeValuePredicate(m.group(2), m.group(3));
			}
			if ((m = attributeContainsPattern.matcher(name)).find()) {
				name = m.group(1);
				predicate = new RequireAttributeContainsValuePredicate(m.group(2), m.group(3));
			}
			if (name.indexOf('[') >= 0) {
				throw new IllegalArgumentException("Unrecognized xpath options for " + name + ": " + segmentTexts);
			}
			segments.add(new Segment(name, predicate));
		}
		return segments;
	}

	/**
	 * The root ObjRef from which the xPath will be calculated.
	 */
	private ObjRef rootObjRef = null;
	/**
	 * The xPath in its original string format, e.g., "component/interface"
	 */
	private String xPath = null;
	/**
	 * The xPath parsed into individual segments, e.g., "component" and "interface"
	 */
	private List<Segment> xPathSegments = null;
	/**
	 * Whether model events are being monitored for changes to ObjRefs pointed to by the xPath
	 */
	private boolean scanning = true;
	/**
	 * The set of ObjRefs that have been found by the xPath, mapped to the lineage of ObjRefs connecting them to the
	 * rootObjRef. Keeping track of the lineage is important because it allows us to remove ObjRefs if one of their
	 * ancestors that connects it to the rootObjRef is removed.
	 */
	private final Map<ObjRef, List<ObjRef>> addedObjRefToLineageRefs = Maps.newHashMap();

	public XArchRelativePathTracker(IXArchADTQuery xarch) {
		this(xarch, null, null, false);
	}

	public XArchRelativePathTracker(IXArchADTQuery xarch, ObjRef rootObjRef, String relPath, boolean startScanning) {
		this.xarch = xarch;
		this.scanning = startScanning;
		setTrackInfo(rootObjRef, relPath);
	}

	public ObjRef getRootObjRef() {
		return rootObjRef;
	}

	public String getRelativeXPath() {
		return xPath;
	}

	public void setTrackInfo(ObjRef rootObjRef, String xPath) {
		if (!SystemUtils.nullEquals(this.rootObjRef, rootObjRef) || !SystemUtils.nullEquals(this.xPath, xPath)) {
			boolean wasScanning = scanning;
			stopScanning();

			this.rootObjRef = rootObjRef;
			this.xPath = xPath;
			this.xPathSegments = null;
			if (xPath != null) {
				List<String> segments = Lists.newArrayList();
				int startIndex = 0;
				int endIndex = 0;
				int bracesCount = 0;
				for (char ch : (xPath + '/').toCharArray()) {
					switch (ch) {
					case '[':
						bracesCount++;
						break;
					case ']':
						bracesCount--;
						break;
					case '/':
						if (bracesCount == 0) {
							segments.add(xPath.substring(startIndex, endIndex));
							startIndex = endIndex + 1;
						}
						break;
					}
					endIndex++;
				}
				this.xPathSegments = parseSegments(segments);
			}

			if (wasScanning) {
				startScanning();
			}
		}
	}

	public Collection<ObjRef> getAddedObjRefs() {
		List<ObjRef> addedObjRefs;
		synchronized (addedObjRefToLineageRefs) {
			addedObjRefs = Lists.newArrayList(addedObjRefToLineageRefs.keySet());
		}
		return addedObjRefs;
	}

	public boolean isAddedObjRef(ObjRef objRef) {
		synchronized (addedObjRefToLineageRefs) {
			return addedObjRefToLineageRefs.containsKey(objRef);
		}
	}

	/**
	 * Finds all ObjRefs described by the xPath relative to the rootObjRef, begins monitoring model events for new
	 * ObjRefs, as well modifications to them. Note: calling this method will result in multiple calls to
	 * {@link IXArchRelativePathTrackerListener#processAdd(List, ObjRef)} for each ObjRef initially found.
	 */
	public void startScanning() {
		if (!scanning && rootObjRef != null && xPath != null) {
			scanning = true;
			scanObjRef(Lists.newArrayList(rootObjRef), rootObjRef, 0);
		}
	}

	public boolean isScanning() {
		return scanning;
	}

	/**
	 * Discontinues monitoring of model events for additions, modifications, and removals of ObjRefs referred to by the
	 * xPath. Note: calling this method will result in multiple calls to
	 * {@link IXArchRelativePathTrackerListener#processRemove(List, ObjRef)} for all previously added ObjRefs.
	 */
	public void stopScanning() {
		if (scanning) {
			scanning = false;
			for (ObjRef toRemoveRef : getAddedObjRefs()) {
				removedObjRef(toRemoveRef);
			}
		}
	}

	/**
	 * Determines the number of ObjRefs in relLineage that match the xPath, treating the first ObjRef in relLineage as
	 * the root ObjRef.
	 * 
	 * @param xPath
	 *            xPath to check, e.g., component/interface
	 * @param relLineage
	 *            ObjRefs to check for a match, e.g., [ArchStructure, Component, Interface]
	 * @return the number of ObjRefs that match the xPath, or -1 if none.
	 */

	protected int getMatchLength(String xPath, List<ObjRef> relLineage) {
		int matchesLength = 0;
		String[] xPaths = xPath.split("/");
		while (matchesLength < xPathSegments.size() && matchesLength < xPaths.length
				&& matchesLength + 1 < relLineage.size()) {
			Segment segment = xPathSegments.get(matchesLength);
			if (segment.name.equals(xPaths[matchesLength])
					&& segment.predicate.apply(relLineage.get(matchesLength + 1))) {
				matchesLength++;
				continue;
			}
			else {
				return -1;
			}
		}
		return matchesLength;
	}

	/**
	 * Scan the given ObjRef's children for those that match the specified segment in the xPath
	 */
	protected void scanObjRef(List<ObjRef> relLineageRefs, ObjRef objRef, int xPathSegmentIndex) {
		assert relLineageRefs.get(0).equals(rootObjRef);
		assert relLineageRefs.get(relLineageRefs.size() - 1).equals(objRef);
		assert relLineageRefs.size() - 1 == xPathSegmentIndex;

		if (xPathSegmentIndex < xPathSegments.size()) {
			Segment xPathSegment = xPathSegments.get(xPathSegmentIndex);
			IXArchADTTypeMetadata type = xarch.getTypeMetadata(objRef);
			IXArchADTFeature feature = type.getFeatures().get(xPathSegment.name);
			if (feature != null) {
				switch (feature.getType()) {
				case ATTRIBUTE:
					break;

				case ELEMENT_SINGLE: {
					ObjRef childRef = (ObjRef) xarch.get(objRef, xPathSegment.name);
					if (childRef != null) {
						if (xPathSegment.predicate.apply(childRef)) {
							relLineageRefs.add(childRef);
							scanObjRef(relLineageRefs, childRef, xPathSegmentIndex + 1);
							relLineageRefs.remove(relLineageRefs.size() - 1);
						}
						else {
							removedObjRef(childRef);
						}
					}
				}
					break;

				case ELEMENT_MULTIPLE: {
					relLineageRefs.add(null);
					for (ObjRef childRef : Iterables.filter(xarch.getAll(objRef, xPathSegment.name), ObjRef.class)) {
						if (xPathSegment.predicate.apply(childRef)) {
							relLineageRefs.set(relLineageRefs.size() - 1, childRef);
							scanObjRef(relLineageRefs, childRef, xPathSegmentIndex + 1);
						}
						else {
							removedObjRef(childRef);
						}
					}
					relLineageRefs.remove(relLineageRefs.size() - 1);
				}
					break;
				}
			}
		}
		else if (xPathSegmentIndex == xPathSegments.size()) {
			changedObjRef(relLineageRefs, null, objRef, null);
		}
	}

	/**
	 * Called when one of the ObjRefs at the given xPath is added or changed and notifies the listeners of the
	 * added/modified ObjRef.
	 */
	protected void changedObjRef(List<ObjRef> relLineageRefs, String relPath, ObjRef objRef, XArchADTModelEvent evt) {
		checkArgument(relLineageRefs.get(0).equals(rootObjRef));
		checkArgument(relLineageRefs.get(relLineageRefs.size() - 1).equals(objRef));
		checkArgument(relLineageRefs.size() == xPathSegments.size() + 1);

		List<ObjRef> clonedRelLineage = Collections.unmodifiableList(Lists.newArrayList(relLineageRefs));
		if (!addedObjRefToLineageRefs.containsKey(objRef)) {
			addedObjRefToLineageRefs.put(objRef, clonedRelLineage);
			try {
				processAdd(clonedRelLineage, objRef);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (evt != null) {
			processUpdate(clonedRelLineage, relPath, objRef, evt);
		}
	}

	/**
	 * Called when of the ObjRefs at the given xPath is removed and notifies the listeners of the removed ObjRef.
	 */
	protected boolean removedObjRef(ObjRef objRef) {
		List<ObjRef> relLineageRefs = addedObjRefToLineageRefs.remove(objRef);
		if (relLineageRefs != null) {
			assert relLineageRefs.get(0).equals(rootObjRef);
			assert relLineageRefs.get(relLineageRefs.size() - 1).equals(objRef);

			processRemove(relLineageRefs, objRef);
			return true;
		}
		return false;
	}

	/**
	 * Monitors the events for changes to ObjRefs relevant to the xPath.
	 */

	@Override
	public void handleXArchADTModelEvent(XArchADTModelEvent evt) {
		if (scanning) {
			switch (evt.getEventType()) {
			case SET: {
				final Object target = evt.getNewValue();
				if (!(target instanceof ObjRef) || treatAsAttribute(evt)) {
					List<ObjRef> lineageRefs = Lists.reverse(evt.getSourceAncestors());
					final int rootObjRefIndex = lineageRefs.indexOf(rootObjRef);
					if (rootObjRefIndex >= 0) {
						int length = Math.min(xPathSegments.size(), length(evt.getSourcePath()) - rootObjRefIndex);
						List<ObjRef> relLineageRefs = lineageRefs
								.subList(rootObjRefIndex, rootObjRefIndex + length + 1);
						String relPath = subpath(evt.getSourcePath(), rootObjRefIndex, rootObjRefIndex + length);
						int matchLength = getMatchLength(relPath, relLineageRefs);
						if (matchLength == xPathSegments.size()) {
							relLineageRefs = relLineageRefs.subList(0, matchLength + 1);
							changedObjRef(relLineageRefs, relPath, relLineageRefs.get(relLineageRefs.size() - 1), evt);
						}
					}
					break;
				}
			}
			// fall through
			case ADD: {
				List<ObjRef> lineageRefs = Lists.reverse(evt.getNewValueAncestors());
				final int rootObjRefIndex = lineageRefs.indexOf(rootObjRef);
				if (rootObjRefIndex >= 0) {
					int length = Math.min(xPathSegments.size(), length(evt.getNewValuePath()) - rootObjRefIndex);
					List<ObjRef> relLineageRefs = lineageRefs.subList(rootObjRefIndex, rootObjRefIndex + length + 1);
					String relPath = subpath(evt.getNewValuePath(), rootObjRefIndex, rootObjRefIndex + length);
					int matchLength = getMatchLength(relPath, relLineageRefs);
					if (matchLength == xPathSegments.size()) {
						changedObjRef(relLineageRefs, relPath, relLineageRefs.get(relLineageRefs.size() - 1), evt);
					}
					else if (matchLength >= 0) {
						scanObjRef(Lists.newArrayList(relLineageRefs), relLineageRefs.get(relLineageRefs.size() - 1),
								matchLength);
					}
				}
				break;
			}

			case CLEAR: {
				final Object target = evt.getOldValue();
				if (!(target instanceof ObjRef) || treatAsAttribute(evt)) {
					List<ObjRef> lineageRefs = Lists.reverse(evt.getSourceAncestors());
					final int rootObjRefIndex = lineageRefs.indexOf(rootObjRef);
					if (rootObjRefIndex >= 0) {
						List<ObjRef> relLineageRefs = lineageRefs.subList(rootObjRefIndex, lineageRefs.size());
						String relPath = subpath(evt.getSourcePath(), rootObjRefIndex, length(evt.getSourcePath()));
						int matchLength = getMatchLength(relPath, relLineageRefs);
						if (matchLength == xPathSegments.size()) {
							relLineageRefs = relLineageRefs.subList(0, matchLength + 1);
							changedObjRef(relLineageRefs, relPath, relLineageRefs.get(relLineageRefs.size() - 1), evt);
						}
					}
					break;
				}
			}
			// fall through
			case REMOVE: {
				List<ObjRef> lineageRefs = Lists.reverse(evt.getOldValueAncestors());
				final int rootObjRefIndex = lineageRefs.indexOf(rootObjRef);
				if (rootObjRefIndex >= 0) {
					int length = Math.min(xPathSegments.size(), length(evt.getOldValuePath()) - rootObjRefIndex);
					List<ObjRef> relLineageRefs = lineageRefs.subList(rootObjRefIndex, rootObjRefIndex + length + 1);
					if (lineageRefs.size() - rootObjRefIndex > xPathSegments.size() + 1) {
						String relPath = subpath(evt.getOldValuePath(), rootObjRefIndex, rootObjRefIndex + length);
						int matchLength = getMatchLength(relPath, relLineageRefs);
						if (matchLength == xPathSegments.size()) {
							changedObjRef(relLineageRefs, relPath, relLineageRefs.get(relLineageRefs.size() - 1), evt);
						}
					}
					else if (lineageRefs.size() - rootObjRefIndex < xPathSegments.size() + 1) {
						int index = Math.min(xPathSegments.size(), relLineageRefs.size()) - 1;
						ObjRef lineageObjRefAtIndex = relLineageRefs.get(index);
						List<ObjRef> toRemoveRefs = Lists.newArrayListWithExpectedSize(addedObjRefToLineageRefs.size());
						for (Entry<ObjRef, List<ObjRef>> addedRef : addedObjRefToLineageRefs.entrySet()) {
							List<ObjRef> relLineageRefsEntry = addedRef.getValue();
							if (lineageObjRefAtIndex.equals(relLineageRefsEntry.get(index))) {
								toRemoveRefs.add(addedRef.getKey());
							}
						}
						for (ObjRef toRemoveRef : toRemoveRefs) {
							removedObjRef(toRemoveRef);
						}
					}
					else {
						removedObjRef((ObjRef) evt.getOldValue());
					}
				}
				break;
			}
			}
		}
	}

	private int length(String path) {
		return path.length() == 0 ? 0 : path.split("/").length;
	}

	private String subpath(String path, int start, int end) {
		return Joiner.on("/").join(Arrays.asList(path.split("/")).subList(start, end));
	}

	private boolean treatAsAttribute(XArchADTModelEvent evt) {
		List<ObjRef> sourceAncestors = evt.getSourceAncestors();
		List<ObjRef> oldValueAncestors = evt.getOldValueAncestors();
		if (oldValueAncestors != null && oldValueAncestors.subList(1, oldValueAncestors.size()).equals(sourceAncestors)) {
			return true;
		}
		List<ObjRef> newValueAncestors = evt.getNewValueAncestors();
		if (newValueAncestors != null && newValueAncestors.subList(1, newValueAncestors.size()).equals(sourceAncestors)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "XArcRelPathTracker[" + //
				"rootObjRef=" + rootObjRef + ", " + //
				"relPath=" + xPath + ", " + //
				"scanning=" + scanning + "]";
	}
}