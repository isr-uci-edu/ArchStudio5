package org.archstudio.xadl;

import java.util.Arrays;
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
		public boolean exclude(ObjRef objRef, List<ObjRef> relativeAncestors, XArchADTModelEvent evt,
				XArchADTPath relativeSourceTargetPath);
	}

	public static class RequiredAttributeFilter implements IFilter {
		protected final IXArchADTQuery xarch;
		protected final String attributeName;
		protected final String requiredValue;

		public RequiredAttributeFilter(IXArchADTQuery xarch, String attributeName, String requiredValue) {
			this.xarch = xarch;
			this.attributeName = attributeName;
			this.requiredValue = requiredValue;
		}

		@Override
		public boolean exclude(ObjRef objRef, List<ObjRef> relativeAncestors, XArchADTModelEvent evt,
				XArchADTPath relativeSourceTargetPath) {
			if (evt != null) {
				if (relativeSourceTargetPath.getLength() == 0) {
					// we are dealing with the objRef
					if (objRef.equals(evt.getSource())) {
						if (attributeName.equals(evt.getFeatureName())) {
							return !SystemUtils.nullEquals(requiredValue, evt.getNewValue());
						}
						// we are dealing with some other attribute
						return false;
					}
				}
				else {
					// we are dealing with a child
					return false;
				}
			}
			return !SystemUtils.nullEquals(requiredValue, xarch.get(objRef, attributeName));
		}
	}

	public static class ProhibitedAttributeFilter implements IFilter {

		protected final IXArchADTQuery xarch;
		protected final String attributeName;
		protected final String prohibitedValue;

		public ProhibitedAttributeFilter(IXArchADTQuery xarch, String attributeName, String prohibitedValue) {
			this.xarch = xarch;
			this.attributeName = attributeName;
			this.prohibitedValue = prohibitedValue;
		}

		@Override
		public boolean exclude(ObjRef objRef, List<ObjRef> relativeAncestors, XArchADTModelEvent evt,
				XArchADTPath relativeSourceTargetPath) {
			if (evt != null) {
				if (relativeSourceTargetPath.getLength() == 0) {
					// we are dealing with the objRef
					if (objRef.equals(evt.getSource())) {
						if (attributeName.equals(evt.getFeatureName())) {
							return SystemUtils.nullEquals(prohibitedValue, evt.getNewValue());
						}
						// we are dealing with some other attribute
						return false;
					}
				}
				else {
					// we are dealing with a child
					return false;
				}
			}
			return SystemUtils.nullEquals(prohibitedValue, xarch.get(objRef, attributeName));
		}
	}

	private final IXArchADTQuery xarch;
	private final List<IXArchRelativePathTrackerListener> pathTrackerListeners = new CopyOnWriteArrayList<IXArchRelativePathTrackerListener>();
	private final List<IFilter> filters = new CopyOnWriteArrayList<IFilter>();

	private ObjRef rootObjRef = null;
	private String relativePath = null;
	private List<String> relativePathNames = null;

	private boolean scanning = true;
	private final Map<ObjRef, List<ObjRef>> addedObjRefToAncestorRefs = Maps.newHashMap();

	public XArchRelativePathTracker(IXArchADTQuery xarch) {
		this(xarch, null, null, false);
	}

	public XArchRelativePathTracker(IXArchADTQuery xarch, ObjRef rootObjRef, String relativePath, boolean startScanning) {
		this.xarch = xarch;
		this.scanning = startScanning;
		setTrackInfo(rootObjRef, relativePath);
	}

	public ObjRef getRootObjRef() {
		return rootObjRef;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setTrackInfo(ObjRef rootObjRef, String relativePath) {
		for (String relativePathName : relativePath != null ? relativePath.split("\\/") : new String[0]) {
			if (Character.isUpperCase(relativePathName.charAt(0))) {
				throw new IllegalArgumentException("Each path segment must start with a lower case letter: "
						+ relativePath);
			}
		}

		if (!SystemUtils.nullEquals(this.rootObjRef, rootObjRef)
				|| !SystemUtils.nullEquals(this.relativePath, relativePath)) {
			boolean wasScanning = scanning;
			stopScanning();

			this.rootObjRef = rootObjRef;
			this.relativePath = relativePath;
			this.relativePathNames = relativePath != null ? Arrays.asList(relativePath.split("\\/")) : null;

			if (wasScanning) {
				startScanning();
			}
		}
	}

	public Iterable<ObjRef> getAddedObjRefs() {
		List<ObjRef> addedObjRefs;
		synchronized (addedObjRefToAncestorRefs) {
			addedObjRefs = Lists.newArrayList(addedObjRefToAncestorRefs.keySet());
		}
		return addedObjRefs;
	}

	public boolean isAddedObjRef(ObjRef objRef) {
		return addedObjRefToAncestorRefs.containsKey(objRef);
	}

	public void startScanning() {
		if (!scanning && rootObjRef != null && relativePath != null) {
			scanning = true;
			scanObjRef(rootObjRef, Lists.newArrayList(rootObjRef), 0);
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
				scanObjRef(rootObjRef, Lists.newArrayList(rootObjRef), 0);
			}
			else {
				List<ObjRef> startingAncestors = xarch.getAllAncestors(startingRef);
				int index = startingAncestors.indexOf(rootObjRef);
				if (index >= 0) {
					int count = index + 1;
					scanObjRef(startingRef, startingAncestors.subList(0, count), count - 1);
				}
			}
		}
	}

	protected int getMatchLength(List<String> pathNames) {
		int iR = 0;
		int iP = 0;
		int cP = pathNames.size() - iP;
		int cM = cP < relativePathNames.size() ? cP : relativePathNames.size();
		int c = 0;
		while (c < cM && relativePathNames.get(iR++).equals(pathNames.get(iP++))) {
			c++;
		}
		return c;
	}

	protected void scanObjRef(ObjRef objRef, List<ObjRef> relativeAncestors, int childNameIndex) {
		if (childNameIndex < relativePathNames.size()) {
			String name = relativePathNames.get(childNameIndex);
			IXArchADTTypeMetadata type = xarch.getTypeMetadata(objRef);
			IXArchADTFeature feature = type.getFeatures().get(name);
			if (feature != null) {
				switch (feature.getType()) {
				case ATTRIBUTE:
					break;

				case ELEMENT_SINGLE: {
					ObjRef childRef = (ObjRef) xarch.get(objRef, name);
					if (childRef != null) {
						relativeAncestors.add(0, childRef);
						scanObjRef(childRef, relativeAncestors, childNameIndex + 1);
					}
				}
					break;

				case ELEMENT_MULTIPLE: {
					relativeAncestors.add(0, null);
					for (ObjRef childRef : xarch.getAll(objRef, name)) {
						relativeAncestors.set(0, childRef);
						scanObjRef(childRef, Lists.newArrayList(relativeAncestors), childNameIndex + 1);
					}
				}
					break;
				}
			}
		}
		else if (childNameIndex == relativePathNames.size()) {
			List<ObjRef> relativeAncestorsClone = Lists.newArrayList(relativeAncestors);
			changedObjRef(objRef, relativeAncestorsClone, null, null);
		}
	}

	@Override
	public void handleXArchADTModelEvent(XArchADTModelEvent evt) {
		//		try {
		if (scanning) {
			switch (evt.getEventType()) {
			case SET: {
				final Object target = evt.getNewValue();
				if (!(target instanceof ObjRef)) {
					List<ObjRef> ancestors = evt.getSourceAncestors();
					final int rootObjRefIndex = ancestors.indexOf(rootObjRef);
					if (rootObjRefIndex >= 0) {
						List<ObjRef> relAncestors = ancestors.subList(0, rootObjRefIndex + 1);
						XArchADTPath relPath = evt.getSourcePath().subpath(
								evt.getSourcePath().getLength() - rootObjRefIndex);
						changedObjRef(relAncestors.get(0), relAncestors, evt, relPath);
					}
					break;
				}
			}
			// fall through

			case ADD: {
				List<ObjRef> ancestors = evt.getNewValueAncestors();
				final int rootObjRefIndex = ancestors.indexOf(rootObjRef);
				if (rootObjRefIndex >= 1) {
					List<ObjRef> relAncestors = ancestors.subList(0, rootObjRefIndex + 1);
					XArchADTPath relPath = evt.getNewValuePath().subpath(ancestors.size() - relAncestors.size());
					int matchLength = getMatchLength(relPath.getTagNames());
					if (matchLength == relativePathNames.size()) {
						changedObjRef(relAncestors.get(0), relAncestors, evt, relPath);
					}
					else if (matchLength > 0) {
						scanObjRef(relAncestors.get(0), Lists.newArrayList(relAncestors), matchLength);
					}
				}
				break;
			}

			case CLEAR: {
				final Object target = evt.getOldValue();
				if (!(target instanceof ObjRef)) {
					List<ObjRef> ancestors = evt.getSourceAncestors();
					final int rootObjRefIndex = ancestors.indexOf(rootObjRef);
					if (rootObjRefIndex >= 0) {
						List<ObjRef> relAncestors = ancestors.subList(0, rootObjRefIndex + 1);
						XArchADTPath relPath = evt.getSourcePath().subpath(
								evt.getSourcePath().getLength() - rootObjRefIndex);
						changedObjRef(relAncestors.get(0), relAncestors, evt, relPath);
					}
					break;
				}
			}
			// fall through
			case REMOVE: {
				final Object target = evt.getOldValue();
				List<ObjRef> ancestors = evt.getOldValueAncestors();
				final int rootObjRefIndex = ancestors.indexOf(rootObjRef);
				if (rootObjRefIndex >= 1) {
					if (!removedObjRef((ObjRef) target)) {
						int ancestorIndex = relativePathNames.size() - rootObjRefIndex;
						List<ObjRef> toRemoveRefs = Lists.newArrayList();
						for (Entry<ObjRef, List<ObjRef>> addedRef : addedObjRefToAncestorRefs.entrySet()) {
							List<ObjRef> ancestorRefs = addedRef.getValue();
							if (ancestorRefs != null && ancestorIndex < ancestorRefs.size()
									&& target.equals(ancestorRefs.get(ancestorIndex))) {
								toRemoveRefs.add(addedRef.getKey());
							}
						}
						for (ObjRef toRemoveRef : toRemoveRefs) {
							removedObjRef(toRemoveRef);
						}
					}
				}
				break;
			}
			}
		}
		//		}
		//		catch (NoSuchObjectException e) {
		//			stopScanning();
		//		}
	}

	public void addFilter(IFilter f) {
		filters.add(f);
	}

	public void removeFilter(IFilter f) {
		filters.remove(f);
	}

	protected void changedObjRef(ObjRef objRef, List<ObjRef> relativeAncestors, XArchADTModelEvent evt,
			XArchADTPath relativeSourceTargetPath) {
		assert relativeAncestors.get(0).equals(objRef);
		assert relativeAncestors.get(relativeAncestors.size() - 1).equals(rootObjRef);

		for (IFilter f : filters) {
			if (f.exclude(objRef, relativeAncestors, evt, relativeSourceTargetPath)) {
				removedObjRef(objRef);
				return;
			}
		}

		if (addedObjRefToAncestorRefs.put(objRef, relativeAncestors) == null) {
			processAdd(objRef, relativeAncestors);
		}
		else if (evt != null) {
			processUpdate(objRef, relativeAncestors, evt, relativeSourceTargetPath);
		}
	}

	protected boolean removedObjRef(ObjRef objRef) {
		List<ObjRef> ancestorRefs = addedObjRefToAncestorRefs.remove(objRef);
		if (ancestorRefs != null) {
			processRemove(objRef, ancestorRefs);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "XArcRelativePathTracker[" + //
				"rootObjRef=" + rootObjRef + ", " + //
				"rootObjRefPath=" + (rootObjRef != null ? xarch.getPath(rootObjRef).toTagsOnlyString() : "") + ", " + //
				"relativePath=" + relativePath + ", " + //
				"scanning=" + scanning + "]";
	}

	public void addTrackerListener(IXArchRelativePathTrackerListener l) {
		pathTrackerListeners.add(l);
	}

	public void removeTrackerListener(IXArchRelativePathTrackerListener l) {
		pathTrackerListeners.remove(l);
	}

	protected void processAdd(ObjRef objRef, List<ObjRef> relativeAncestors) {
		for (IXArchRelativePathTrackerListener l : pathTrackerListeners) {
			l.processAdd(objRef, relativeAncestors);
		}
	}

	protected void processUpdate(ObjRef objRef, List<ObjRef> relativeAncestors, XArchADTModelEvent evt,
			XArchADTPath relativeSourceTargetPath) {
		for (IXArchRelativePathTrackerListener l : pathTrackerListeners) {
			l.processUpdate(objRef, relativeAncestors, evt, relativeSourceTargetPath);
		}
	}

	protected void processRemove(ObjRef objRef, List<ObjRef> relativeAncestors) {
		for (IXArchRelativePathTrackerListener l : pathTrackerListeners) {
			l.processRemove(objRef, relativeAncestors);
		}
	}
}