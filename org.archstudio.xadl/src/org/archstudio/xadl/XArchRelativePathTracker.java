package org.archstudio.xadl;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xarchadt.IXArchADTFeature;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.IXArchADTQuery;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.XArchADTPath;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public final class XArchRelativePathTracker implements IXArchADTModelListener {

	public static interface IFilter {
		//public boolean exclude(List<ObjRef> relLineage, XArchADTPath relPath, ObjRef objRef,
		//		XArchADTModelEvent evt);
	}

	//public static class RequiredAttributeFilter implements IFilter {
	//	protected final IXArchADTQuery xarch;
	//	protected final String attributeName;
	//	protected final String requiredValue;
	//
	//	public RequiredAttributeFilter(IXArchADTQuery xarch, String attributeName, String requiredValue) {
	//		this.xarch = xarch;
	//		this.attributeName = attributeName;
	//		this.requiredValue = requiredValue;
	//	}
	//
	//	@Override
	//	public boolean exclude(List<ObjRef> relLineage, XArchADTPath relPath, ObjRef objRef,
	//			XArchADTModelEvent evt) {
	//		if (evt != null) {
	//			if (relPath.getLength() == 0) {
	//				// we are dealing with the objRef
	//				if (objRef.equals(evt.getSource())) {
	//					if (attributeName.equals(evt.getFeatureName())) {
	//						return !SystemUtils.nullEquals(requiredValue, evt.getNewValue());
	//					}
	//					// we are dealing with some other attribute
	//					return false;
	//				}
	//			}
	//			else {
	//				// we are dealing with a child
	//				return false;
	//			}
	//		}
	//		return !SystemUtils.nullEquals(requiredValue, xarch.get(objRef, attributeName));
	//	}
	//}
	//
	//public static class ProhibitedAttributeFilter implements IFilter {
	//
	//	protected final IXArchADTQuery xarch;
	//	protected final String attributeName;
	//	protected final String prohibitedValue;
	//
	//	public ProhibitedAttributeFilter(IXArchADTQuery xarch, String attributeName, String prohibitedValue) {
	//		this.xarch = xarch;
	//		this.attributeName = attributeName;
	//		this.prohibitedValue = prohibitedValue;
	//	}
	//
	//	@Override
	//	public boolean exclude(List<ObjRef> relLineage, XArchADTPath relPath, ObjRef objRef,
	//			XArchADTModelEvent evt) {
	//		if (evt != null) {
	//			if (relPath.getLength() == 0) {
	//				// we are dealing with the objRef
	//				if (objRef.equals(evt.getSource())) {
	//					if (attributeName.equals(evt.getFeatureName())) {
	//						return SystemUtils.nullEquals(prohibitedValue, evt.getNewValue());
	//					}
	//					// we are dealing with some other attribute
	//					return false;
	//				}
	//			}
	//			else {
	//				// we are dealing with a child
	//				return false;
	//			}
	//		}
	//		return SystemUtils.nullEquals(prohibitedValue, xarch.get(objRef, attributeName));
	//	}
	//}

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
			l.processAdd(relLineage, objRef);
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

	//private final List<IFilter> filters = new CopyOnWriteArrayList<IFilter>();
	//
	//public void addFilter(IFilter f) {
	//	filters.add(f);
	//}
	//
	//public void removeFilter(IFilter f) {
	//	filters.remove(f);
	//}

	private ObjRef rootObjRef = null;
	private String relPath = null;
	private List<String> relPathNames = null;

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

	public String getRelPath() {
		return relPath;
	}

	public void setTrackInfo(ObjRef rootObjRef, String relPath) {
		for (String relPathName : relPath != null ? relPath.split("\\/") : new String[0]) {
			if (Character.isUpperCase(relPathName.charAt(0))) {
				throw new IllegalArgumentException("Each path segment must start with a lower case letter: " + relPath);
			}
		}

		if (!SystemUtils.nullEquals(this.rootObjRef, rootObjRef) || !SystemUtils.nullEquals(this.relPath, relPath)) {
			boolean wasScanning = scanning;
			stopScanning();

			this.rootObjRef = rootObjRef;
			this.relPath = relPath;
			this.relPathNames = relPath != null ? Arrays.asList(relPath.split("\\/")) : null;

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
		if (!scanning && rootObjRef != null && relPath != null) {
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

	protected int getMatchLength(XArchADTPath path) {
		int matchesLength = 0;
		while (matchesLength < relPathNames.size() && matchesLength < path.getLength()) {
			if (relPathNames.get(matchesLength).equals(path.getTagName(matchesLength))) {
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

		if (childNameIndex < relPathNames.size()) {
			String name = relPathNames.get(childNameIndex);
			IXArchADTTypeMetadata type = xarch.getTypeMetadata(objRef);
			IXArchADTFeature feature = type.getFeatures().get(name);
			if (feature != null) {
				switch (feature.getType()) {
				case ATTRIBUTE:
					break;

				case ELEMENT_SINGLE: {
					ObjRef childRef = (ObjRef) xarch.get(objRef, name);
					if (childRef != null) {
						relLineageRefs.add(childRef);
						scanObjRef(relLineageRefs, childRef, childNameIndex + 1);
						relLineageRefs.remove(relLineageRefs.size() - 1);
					}
				}
					break;

				case ELEMENT_MULTIPLE: {
					relLineageRefs.add(null);
					for (ObjRef childRef : xarch.getAll(objRef, name)) {
						relLineageRefs.set(relLineageRefs.size() - 1, childRef);
						scanObjRef(relLineageRefs, childRef, childNameIndex + 1);
					}
					relLineageRefs.remove(relLineageRefs.size() - 1);
				}
					break;
				}
			}
		}
		else if (childNameIndex == relPathNames.size()) {
			changedObjRef(relLineageRefs, null, objRef, null);
		}
	}

	protected void changedObjRef(List<ObjRef> relLineageRefs, XArchADTPath relPath, ObjRef objRef,
			XArchADTModelEvent evt) {
		checkArgument(relLineageRefs.get(0).equals(rootObjRef));
		checkArgument(relLineageRefs.get(relLineageRefs.size() - 1).equals(objRef));
		checkArgument(relLineageRefs.size() == relPathNames.size() + 1);

		//for (IFilter f : filters) {
		//	if (f.exclude(relLineage, relSourceTargetPath, objRef, evt)) {
		//		removedObjRef(objRef);
		//		return;
		//	}
		//}

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
						int length = Math.min(relPathNames.size(), evt.getSourcePath().getLength() - rootObjRefIndex);
						List<ObjRef> relLineageRefs = lineageRefs
								.subList(rootObjRefIndex, rootObjRefIndex + length + 1);
						XArchADTPath relPath = evt.getSourcePath().subpath(rootObjRefIndex, rootObjRefIndex + length);
						int matchLength = getMatchLength(relPath);
						if (matchLength == relPathNames.size()) {
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
					int length = Math.min(relPathNames.size(), evt.getNewValuePath().getLength() - rootObjRefIndex);
					List<ObjRef> relLineageRefs = lineageRefs.subList(rootObjRefIndex, rootObjRefIndex + length + 1);
					XArchADTPath relPath = evt.getNewValuePath().subpath(rootObjRefIndex, rootObjRefIndex + length);
					int matchLength = getMatchLength(relPath);
					if (matchLength == relPathNames.size()) {
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
						int matchLength = getMatchLength(relPath);
						if (matchLength == relPathNames.size()) {
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
					int length = Math.min(relPathNames.size(), evt.getOldValuePath().getLength() - rootObjRefIndex);
					List<ObjRef> relLineageRefs = lineageRefs.subList(rootObjRefIndex, rootObjRefIndex + length + 1);
					if (lineageRefs.size() - rootObjRefIndex > relPathNames.size() + 1) {
						XArchADTPath relPath = evt.getOldValuePath().subpath(rootObjRefIndex, rootObjRefIndex + length);
						int matchLength = getMatchLength(relPath);
						if (matchLength == relPathNames.size()) {
							changedObjRef(relLineageRefs, relPath, relLineageRefs.get(relLineageRefs.size() - 1), evt);
						}
					}
					else if (lineageRefs.size() - rootObjRefIndex < relPathNames.size() + 1) {
						int index = Math.min(relPathNames.size(), relLineageRefs.size()) - 1;
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
				"relPath=" + relPath + ", " + //
				"scanning=" + scanning + "]";
	}
}