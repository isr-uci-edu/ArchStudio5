package org.archstudio.xadl;

import static com.google.common.base.Preconditions.checkArgument;

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
import org.archstudio.xarchadt.XArchADTPath;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

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

	protected void processUpdate(List<ObjRef> relLineage, XArchADTPath relPath, ObjRef objRef, XArchADTModelEvent evt) {
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

	private static class Segment {

		final String name;
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
			if (name.indexOf('[') >= 0) {
				throw new IllegalArgumentException("Unrecognized xpath options for " + name + ": " + segmentTexts);
			}
			segments.add(new Segment(name, predicate));
		}
		return segments;
	}

	private ObjRef rootObjRef = null;
	private String relXPath = null;
	private List<Segment> relPathSegments = null;

	private boolean scanning = true;
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
		return relXPath;
	}

	public void setTrackInfo(ObjRef rootObjRef, String relXPath) {
		//for (String relPathName : relXPath != null ? relXPath.split("\\/") : new String[0]) {
		//	if (Character.isUpperCase(relPathName.charAt(0))) {
		//		throw new IllegalArgumentException("Each path segment must start with a lower case letter: " + relXPath);
		//	}
		//}

		if (!SystemUtils.nullEquals(this.rootObjRef, rootObjRef) || !SystemUtils.nullEquals(this.relXPath, relXPath)) {
			boolean wasScanning = scanning;
			stopScanning();

			this.rootObjRef = rootObjRef;
			this.relXPath = relXPath;
			this.relPathSegments = null;
			if (relXPath != null) {
				List<String> segments = Lists.newArrayList();
				int startIndex = 0;
				int endIndex = 0;
				int bracesCount = 0;
				for (char ch : (relXPath + '/').toCharArray()) {
					switch (ch) {
					case '[':
						bracesCount++;
						break;
					case ']':
						bracesCount--;
						break;
					case '/':
						if (bracesCount == 0) {
							segments.add(relXPath.substring(startIndex, endIndex));
							startIndex = endIndex + 1;
						}
						break;
					}
					endIndex++;
				}
				this.relPathSegments = parseSegments(segments);
			}

			if (wasScanning) {
				startScanning();
			}
		}
	}

	public Iterable<ObjRef> getAddedObjRefs() {
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

	public void startScanning() {
		if (!scanning && rootObjRef != null && relXPath != null) {
			scanning = true;
			scanObjRef(Lists.newArrayList(rootObjRef), rootObjRef, 0);
		}
	}

	public boolean isScanning() {
		return scanning;
	}

	public void stopScanning() {
		if (scanning) {
			scanning = false;
			for (ObjRef toRemoveRef : getAddedObjRefs()) {
				removedObjRef(toRemoveRef);
			}
		}
	}

	public void rescan() {
		rescan(null);
	}

	public void rescan(ObjRef startingRef) {
		if (scanning) {
			if (startingRef == null) {
				scanObjRef(Lists.newArrayList(rootObjRef), rootObjRef, 0);
			}
			else {
				List<ObjRef> startingLineageRefs = xarch.getLineage(startingRef);
				int index = startingLineageRefs.indexOf(rootObjRef);
				if (index >= 0) {
					startingLineageRefs.subList(0, index).clear();
					scanObjRef(startingLineageRefs, startingRef, startingLineageRefs.size() - 1);
				}
			}
		}
	}

	protected int getMatchLength(XArchADTPath relPath, List<ObjRef> relLineage) {
		int matchesLength = 0;
		while (matchesLength < relPathSegments.size() && matchesLength < relPath.getLength()) {
			Segment segment = relPathSegments.get(matchesLength);
			if (segment.name.equals(relPath.getTagName(matchesLength))
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

	protected void scanObjRef(List<ObjRef> relLineageRefs, ObjRef objRef, int childNameIndex) {
		assert relLineageRefs.get(0).equals(rootObjRef);
		assert relLineageRefs.get(relLineageRefs.size() - 1).equals(objRef);
		assert relLineageRefs.size() - 1 == childNameIndex;

		if (childNameIndex < relPathSegments.size()) {
			Segment relPathSegment = relPathSegments.get(childNameIndex);
			IXArchADTTypeMetadata type = xarch.getTypeMetadata(objRef);
			IXArchADTFeature feature = type.getFeatures().get(relPathSegment.name);
			if (feature != null) {
				switch (feature.getType()) {
				case ATTRIBUTE:
					break;

				case ELEMENT_SINGLE: {
					ObjRef childRef = (ObjRef) xarch.get(objRef, relPathSegment.name);
					if (childRef != null) {
						if (relPathSegment.predicate.apply(childRef)) {
							relLineageRefs.add(childRef);
							scanObjRef(relLineageRefs, childRef, childNameIndex + 1);
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
					for (ObjRef childRef : xarch.getAll(objRef, relPathSegment.name)) {
						if (relPathSegment.predicate.apply(childRef)) {
							relLineageRefs.set(relLineageRefs.size() - 1, childRef);
							scanObjRef(relLineageRefs, childRef, childNameIndex + 1);
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
		else if (childNameIndex == relPathSegments.size()) {
			changedObjRef(relLineageRefs, null, objRef, null);
		}
	}

	protected void changedObjRef(List<ObjRef> relLineageRefs, XArchADTPath relPath, ObjRef objRef,
			XArchADTModelEvent evt) {
		checkArgument(relLineageRefs.get(0).equals(rootObjRef));
		checkArgument(relLineageRefs.get(relLineageRefs.size() - 1).equals(objRef));
		checkArgument(relLineageRefs.size() == relPathSegments.size() + 1);

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

	@Override
	public void handleXArchADTModelEvent(XArchADTModelEvent evt) {
		if (scanning) {
			switch (evt.getEventType()) {
			case SET: {
				final Object target = evt.getNewValue();
				if (!(target instanceof ObjRef)) {
					List<ObjRef> lineageRefs = Lists.reverse(evt.getSourceAncestors());
					final int rootObjRefIndex = lineageRefs.indexOf(rootObjRef);
					if (rootObjRefIndex >= 0) {
						int length = Math
								.min(relPathSegments.size(), evt.getSourcePath().getLength() - rootObjRefIndex);
						List<ObjRef> relLineageRefs = lineageRefs
								.subList(rootObjRefIndex, rootObjRefIndex + length + 1);
						XArchADTPath relPath = evt.getSourcePath().subpath(rootObjRefIndex, rootObjRefIndex + length);
						int matchLength = getMatchLength(relPath, relLineageRefs);
						if (matchLength == relPathSegments.size()) {
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
					int length = Math.min(relPathSegments.size(), evt.getNewValuePath().getLength() - rootObjRefIndex);
					List<ObjRef> relLineageRefs = lineageRefs.subList(rootObjRefIndex, rootObjRefIndex + length + 1);
					XArchADTPath relPath = evt.getNewValuePath().subpath(rootObjRefIndex, rootObjRefIndex + length);
					int matchLength = getMatchLength(relPath, relLineageRefs);
					if (matchLength == relPathSegments.size()) {
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
				if (!(target instanceof ObjRef)) {
					List<ObjRef> lineageRefs = Lists.reverse(evt.getOldValueAncestors());
					final int rootObjRefIndex = lineageRefs.indexOf(rootObjRef);
					if (rootObjRefIndex >= 0) {
						List<ObjRef> relLineageRefs = lineageRefs.subList(rootObjRefIndex, lineageRefs.size());
						XArchADTPath relPath = evt.getOldValuePath().subpath(rootObjRefIndex);
						int matchLength = getMatchLength(relPath, relLineageRefs);
						if (matchLength == relPathSegments.size()) {
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
					int length = Math.min(relPathSegments.size(), evt.getOldValuePath().getLength() - rootObjRefIndex);
					List<ObjRef> relLineageRefs = lineageRefs.subList(rootObjRefIndex, rootObjRefIndex + length + 1);
					if (lineageRefs.size() - rootObjRefIndex > relPathSegments.size() + 1) {
						XArchADTPath relPath = evt.getOldValuePath().subpath(rootObjRefIndex, rootObjRefIndex + length);
						int matchLength = getMatchLength(relPath, relLineageRefs);
						if (matchLength == relPathSegments.size()) {
							changedObjRef(relLineageRefs, relPath, relLineageRefs.get(relLineageRefs.size() - 1), evt);
						}
					}
					else if (lineageRefs.size() - rootObjRefIndex < relPathSegments.size() + 1) {
						int index = Math.min(relPathSegments.size(), relLineageRefs.size()) - 1;
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

	@Override
	public String toString() {
		return "XArcRelPathTracker[" + //
				"rootObjRef=" + rootObjRef + ", " + //
				"relPath=" + relXPath + ", " + //
				"scanning=" + scanning + "]";
	}
}