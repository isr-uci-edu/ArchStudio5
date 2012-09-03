package org.archstudio.xarchadt.variability.core;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.archstudio.sysutils.SystemUtils;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl3.hints_3_0.Hints_3_0Package;
import org.archstudio.xadl3.variability_3_0.AttributeChange;
import org.archstudio.xadl3.variability_3_0.Change;
import org.archstudio.xadl3.variability_3_0.ChangeSet;
import org.archstudio.xadl3.variability_3_0.ChangeSetOfChanges;
import org.archstudio.xadl3.variability_3_0.ElementChange;
import org.archstudio.xadl3.variability_3_0.ElementManyChange;
import org.archstudio.xadl3.variability_3_0.Variability;
import org.archstudio.xadl3.variability_3_0.Variability_3_0Factory;
import org.archstudio.xadl3.variability_3_0.Variability_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.DocumentRoot;
import org.archstudio.xadl3.xadlcore_3_0.XADLType;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Factory;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.XArchADTModelEvent.EventType;
import org.archstudio.xarchadt.XArchADTProxy;
import org.archstudio.xarchadt.core.XArchADTImpl;
import org.archstudio.xarchadt.variability.IXArchADTVariability;
import org.archstudio.xarchadt.variability.IXArchADTVariabilityListener;
import org.archstudio.xarchadt.variability.XArchADTVariabilityEvent;
import org.archstudio.xarchadt.variability.XArchADTVariabilityEvent.Type;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xml.type.internal.DataValue.Base64;
import org.eclipse.jdt.annotation.Nullable;
import org.osgi.framework.Bundle;

import com.google.common.base.Joiner;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class XArchADTVariabilityImpl extends XArchADTImpl implements IXArchADTVariability {

	private enum Status {

		EXPLICITLY_ADDED(ChangeStatus.EXPLICITLY_ADDED, true, true, true), //
		EXPLICITLY_ADDED_BUT_REALLY_REMOVED(ChangeStatus.EXPLICITLY_ADDED_BUT_REALLY_REMOVED, true, false, true), //
		EXPLICITLY_MODIFIED(ChangeStatus.EXPLICITLY_MODIFIED, true, true, true), //
		EXPLICITLY_MODIFIED_BUT_REALLY_REMOVED(ChangeStatus.EXPLICITLY_MODIFIED_BUT_REALLY_REMOVED, true, false, true), //
		EXPLICITLY_REMOVED(ChangeStatus.EXPLICITLY_REMOVED, true, false, true), //
		EXPLICITLY_REMOVED_BUT_REALLY_ADDED(ChangeStatus.EXPLICITLY_REMOVED_BUT_REALLY_ADDED, true, true, true), //
		OVERVIEW(ChangeStatus.OVERVIEW, true, false, false), //
		ATTACHED(ChangeStatus.ATTACHED, true, true, false), //
		DETACHED(ChangeStatus.ATTACHED, false, false, false);

		public final ChangeStatus status;
		public final boolean explicitAttached;
		public final boolean attached;
		public final boolean explicit;

		private Status(ChangeStatus status, boolean explicitAttached, boolean attached, boolean explicit) {
			this.status = status;
			this.explicitAttached = explicitAttached;
			this.attached = attached;
			this.explicit = explicit;
		}
	}

	private class VariabilityStatus {

		final ObjRef documentRootRef;
		Variability variability = null;
		boolean isChangeSetsEnabled = false;
		boolean isOverview = false;
		ChangeSet activeChangeSet = null;
		List<ChangeSet> allChangeSets = Lists.newArrayList();
		List<ChangeSet> appliedChangeSets = Lists.newArrayList();
		Set<ChangeSet> explicitChangeSets = Sets.newHashSet();
		List<ChangeSet> workingChangeSets = Lists.newArrayList();
		int workingChangeSetsBeginIndex = 0;

		public VariabilityStatus(ObjRef documentRootRef) {
			this.documentRootRef = documentRootRef;
			refreshFromXadl();
		}

		public void refreshFromXadl() {
			variability = null;
			DocumentRoot documentRoot = (DocumentRoot) get(documentRootRef);
			XADLType xadlType = documentRoot.getXADL();
			if (xadlType != null) {
				for (EObject eObject : xadlType.getTopLevelElement()) {
					if (eObject instanceof Variability) {
						variability = (Variability) eObject;
						break;
					}
				}
			}
			isChangeSetsEnabled = false;
			isOverview = false;
			activeChangeSet = null;
			allChangeSets.clear();
			appliedChangeSets.clear();
			workingChangeSets.clear();
			workingChangeSetsBeginIndex = 0;

			if (variability != null) {
				isChangeSetsEnabled = true;
				isOverview = variability.isOverview();
				activeChangeSet = variability.getActiveChangeSet();
				allChangeSets = Lists.newArrayList(variability.getChangeSet());
				appliedChangeSets = Lists.newArrayList(variability.getAppliedChangeSets());
				// TODO: make these implied?
				isOverview |= !appliedChangeSets.containsAll(explicitChangeSets);

				if (!isOverview) {
					workingChangeSets.addAll(allChangeSets);
					Set<ChangeSet> filter = Sets.newHashSet();
					filter.addAll(appliedChangeSets);
					workingChangeSets.retainAll(filter);
				}
				else {
					workingChangeSets.addAll(allChangeSets);
					Set<ChangeSet> filter = Sets.newHashSet();
					filter.addAll(appliedChangeSets);
					workingChangeSets.removeAll(filter);
					workingChangeSetsBeginIndex = workingChangeSets.size();
					workingChangeSets.addAll(appliedChangeSets);
				}
			}
		}

		// the following are used during a synchronization
		public List<ChangeSet> _changeSets;
		public int _activeChangeSetIndex;
		public int _beginChangeSetIndex;
		public int[] _synchronizeIndecies;
		public boolean[] _explicitChangeSet;

		public void setup(List<ChangeSet> changeSets, int beginChangeSetIndex, int activeChangeSetIndex,
				int[] synchronizeIndecies) {

			checkArgument(0 <= beginChangeSetIndex && beginChangeSetIndex <= changeSets.size());
			checkArgument(activeChangeSetIndex == -1 || 0 <= activeChangeSetIndex
					&& activeChangeSetIndex < changeSets.size());
			for (int i : synchronizeIndecies)
				checkArgument(0 <= i && i < changeSets.size());

			this._changeSets = changeSets;
			this._activeChangeSetIndex = activeChangeSetIndex;
			this._beginChangeSetIndex = beginChangeSetIndex;
			this._synchronizeIndecies = synchronizeIndecies;
			this._explicitChangeSet = new boolean[changeSets.size()];
			for (int csIndex = isOverview ? 0 : beginChangeSetIndex; csIndex < changeSets.size(); csIndex++)
				_explicitChangeSet[csIndex] = explicitChangeSets.contains(changeSets.get(csIndex));
		}
	}

	private class SynchAttributeHelper {

		@Nullable
		public AttributeChange createChange(VariabilityStatus vs, EObject eObject, EStructuralFeature eAttribute) {
			return createAttributeChange(vs.activeChangeSet, eObject, eAttribute.getName());
		}
	}

	private abstract class SynchElementHelper {

		boolean isImplicitlyResolvable;

		public SynchElementHelper(boolean isImplicitlyResolvable) {
			this.isImplicitlyResolvable = isImplicitlyResolvable;
		}

		public void clear(EObject oldEObject) {
			EStructuralFeature eFeature = oldEObject.eContainmentFeature();
			EObject eContainer = oldEObject.eContainer();
			if (eFeature != null && eContainer != null) {
				if (eFeature.isMany()) {
					((EList<?>) eContainer.eGet(eFeature)).remove(oldEObject);
				}
				else {
					eContainer.eSet(eFeature, null);
				}
			}
		}

		abstract public void set(EObject newEObject);

		@Nullable
		public ElementChange createChange(VariabilityStatus vs, EObject newEObject) {
			return createElementChange(vs.activeChangeSet, newEObject);
		}

		public boolean isImplicitlyResolvable() {
			return isImplicitlyResolvable;
		}
	}

	private final LoadingCache<ObjRef, VariabilityStatus> variabilityStatusCache = CacheBuilder.newBuilder().build(
			new CacheLoader<ObjRef, VariabilityStatus>() {
				@Override
				public VariabilityStatus load(@Nullable ObjRef documentRootRef) throws Exception {
					if (documentRootRef == null)
						throw new NullPointerException();

					return new VariabilityStatus(documentRootRef);
				}
			});

	private final List<IXArchADTVariabilityListener> variabilityListeners = Lists.newCopyOnWriteArrayList();

	public void addXArchADTVariabilityListener(IXArchADTVariabilityListener listener) {
		variabilityListeners.add(listener);
	}

	public void removeXArchADTVariabilityListener(IXArchADTVariabilityListener listener) {
		variabilityListeners.remove(listener);
	}

	protected void fireVariabilityEvent(XArchADTVariabilityEvent.Type type, VariabilityStatus vs, ObjRef changedObjRef,
			ChangeStatus changeStatus) {
		XArchADTVariabilityEvent event = new XArchADTVariabilityEvent(type, vs.documentRootRef,
				putNullable(vs.activeChangeSet), putAll(vs.appliedChangeSets), putAll(vs.explicitChangeSets),
				vs.isOverview, changedObjRef, changeStatus);
		for (IXArchADTVariabilityListener l : variabilityListeners)
			l.handleXArchADTVariabilityEvent(event);
	}

	private final Map<EObject, Status> eObjectStatus = Maps.newHashMap();

	protected void setStatus(VariabilityStatus vs, EObject eObj, Status status) {
		Status oldStatus = getStatus(eObj);
		if (status == Status.ATTACHED)
			eObjectStatus.remove(eObj);
		else
			eObjectStatus.put(eObj, status);
		if (oldStatus != status) {
			if (oldStatus.explicitAttached != status.explicitAttached) {
				fireXArchADTModelEvent(eObj, status != Status.DETACHED);
			}
			fireVariabilityEvent(Type.STATUS, vs, put(eObj), status.status);
		}
	}

	protected Status getStatus(EObject eObject) {
		Status status = eObjectStatus.get(eObject);
		return status != null ? status : Status.ATTACHED;
	}

	private boolean isAttached(EObject eObject) {
		return getStatus(eObject).explicitAttached;
	}

	@Override
	public ChangeStatus getChangeStatus(ObjRef objRef) {
		return getStatus(get(objRef)).status;
	}

	private void fireXArchADTModelEvent(EObject eObj, boolean attached) {
		EStructuralFeature eFeature = eObj.eContainingFeature();
		if (eFeature == null)
			return;
		EObject src = eObj.eContainer();
		EventType eventType;
		ObjRef oldChange = null;
		String oldChangePath = null;
		ObjRef newChange = null;
		String newChangePath = null;
		if (attached) {
			eventType = eFeature.isMany() ? EventType.ADD : EventType.SET;
			newChange = put(eObj);
			newChangePath = getTagsOnlyPathString(newChange);
		}
		else {
			eventType = eFeature.isMany() ? EventType.REMOVE : EventType.CLEAR;
			oldChange = put(eObj);
			oldChangePath = Joiner.on('/').join(getTagsOnlyPathString(put(src)), super.getTagName(oldChange));
		}
		fireXArchADTModelEvent(new XArchADTModelEvent(eventType, put(src), getAllAncestors(put(src)),
				getTagsOnlyPathString(put(src)), eFeature.getName(), oldChange, oldChangePath, newChange, newChangePath));
	}

	protected boolean isAttachedObjRef(@Nullable Serializable element) {
		if (element instanceof ObjRef) {
			return isAttached(get((ObjRef) element));
		}
		return true;
	}

	@Nullable
	protected Serializable filterDetached(@Nullable Serializable element) {
		return isAttachedObjRef(element) ? element : null;
	}

	protected List<Serializable> filterDetached(List<Serializable> elements) {
		for (Iterator<Serializable> i = elements.iterator(); i.hasNext();) {
			if (!isAttachedObjRef(i.next()))
				i.remove();
		}
		return elements;
	}

	@Nullable
	protected ObjRef filterDetachedByAncestors(@Nullable ObjRef objRef) {
		if (objRef == null)
			return null;
		for (ObjRef aRef : getAllAncestors(objRef)) {
			if (!isAttachedObjRef(aRef)) {
				return null;
			}
		}
		return objRef;
	}

	@Override
	public synchronized void setChangeSetsEnabled(ObjRef documentRootRef, boolean enabled) {

		checkArgument(documentRootRef.equals(getDocumentRootRef(documentRootRef)));

		VariabilityStatus vs = variabilityStatusCache.getUnchecked(documentRootRef);
		if (!vs.isChangeSetsEnabled && enabled) {
			DocumentRoot documentRoot = (DocumentRoot) get(documentRootRef);
			XADLType xadlType = documentRoot.getXADL();
			if (xadlType == null) {
				xadlType = Xadlcore_3_0Factory.eINSTANCE.createXADLType();
				documentRoot.setXADL(xadlType);
			}
			Variability variability = null;
			for (EObject eObject : xadlType.getTopLevelElement()) {
				if (eObject instanceof Variability) {
					variability = (Variability) eObject;
					break;
				}
			}
			if (variability == null) {
				variability = Variability_3_0Factory.eINSTANCE.createVariability();
				xadlType.getTopLevelElement().add(variability);
			}
			ChangeSetOfChanges changeSet = Variability_3_0Factory.eINSTANCE.createChangeSetOfChanges();
			changeSet.setId(UIDGenerator.generateUID());
			changeSet.setName("Baseline");
			variability.getChangeSet().add(changeSet);
			variability.getAppliedChangeSets().add(changeSet);
			variability.setActiveChangeSet(changeSet);

			vs.refreshFromXadl();
			fireVariabilityEvent(Type.ENABLED, vs, null, null);

			vs.setup(vs.workingChangeSets, 0, vs.workingChangeSets.indexOf(vs.activeChangeSet), new int[0]);
			synchronizeDocumentRoot(vs, documentRoot);
		}
		else if (vs.isChangeSetsEnabled && !enabled) {
			DocumentRoot documentRoot = (DocumentRoot) get(documentRootRef);
			XADLType xadlType = documentRoot.getXADL();
			if (xadlType == null) {
				xadlType = Xadlcore_3_0Factory.eINSTANCE.createXADLType();
				documentRoot.setXADL(xadlType);
			}
			for (EObject eObject : xadlType.getTopLevelElement()) {
				if (eObject instanceof Variability) {
					xadlType.getTopLevelElement().remove(eObject);
				}
			}

			vs.refreshFromXadl();
			fireVariabilityEvent(Type.DISABLED, vs, null, null);
		}
	}

	@Override
	public synchronized boolean isChangeSetsEnabled(ObjRef documentRootRef) {
		checkArgument(documentRootRef.equals(getDocumentRootRef(documentRootRef)));

		VariabilityStatus vs = variabilityStatusCache.getUnchecked(documentRootRef);
		return vs.isChangeSetsEnabled;
	}

	@Override
	public synchronized void setActiveChangeSet(ObjRef documentRootRef, @Nullable ObjRef changeSetRef) {
		checkArgument(documentRootRef.equals(getDocumentRootRef(documentRootRef)));
		checkArgument(changeSetRef == null
				|| isInstanceOf(changeSetRef, Variability_3_0Package.eNS_URI,
						Variability_3_0Package.Literals.CHANGE_SET.getName()));

		VariabilityStatus vs = variabilityStatusCache.getUnchecked(documentRootRef);
		if (!vs.isChangeSetsEnabled) {
			throw new RuntimeException("Change sets are not enabled");
		}

		if (!SystemUtils.nullEquals(vs.activeChangeSet, changeSetRef != null ? (ChangeSet) get(changeSetRef) : null)) {
			vs.variability.setActiveChangeSet(changeSetRef != null ? (ChangeSet) get(changeSetRef) : null);

			vs.refreshFromXadl();
			fireVariabilityEvent(Type.ACTIVE, vs, null, null);
		}
	}

	@Nullable
	@Override
	public synchronized ObjRef getActiveChangeSet(ObjRef documentRootRef) {
		checkArgument(documentRootRef.equals(getDocumentRootRef(documentRootRef)));

		VariabilityStatus vs = variabilityStatusCache.getUnchecked(documentRootRef);
		if (!vs.isChangeSetsEnabled) {
			throw new RuntimeException("Change sets are not enabled");
		}

		return putNullable(vs.activeChangeSet);
	}

	// if this needs to be optimized later, see: http://www.cs.dartmouth.edu/~doug/diff.ps
	static final <T> Set<T> diff(T[] o1, T[] o2) {
		int[][] c = new int[o1.length + 1][o2.length + 1];
		for (int i = 1; i <= o1.length; i++) {
			for (int j = 1; j <= o2.length; j++) {
				if (SystemUtils.nullEquals(o1[i - 1], o2[j - 1])) {
					c[i][j] = c[i - 1][j - 1] + 1;
				}
				else {
					c[i][j] = Math.max(c[i][j - 1], c[i - 1][j]);
				}
			}
		}
		int i = o1.length;
		int j = o2.length;
		Set<T> diff = new HashSet<T>();
		while (i >= 0 && j >= 0) {
			if (i > 0 && j > 0 && SystemUtils.nullEquals(o1[i - 1], o2[j - 1])) {
				i--;
				j--;
				continue;
			}
			if (j > 0 && (i == 0 || c[i][j - 1] >= c[i - 1][j])) {
				j--;
				diff.add(o2[j]);
				continue;
			}
			if (i > 0 && (j == 0 || c[i][j - 1] < c[i - 1][j])) {
				i--;
				diff.add(o1[i]);
				continue;
			}
			break;
		}
		return diff;
	}

	@Override
	public synchronized void applyChangeSets(ObjRef documentRootRef, List<ObjRef> changeSetRefs) {
		checkArgument(documentRootRef.equals(getDocumentRootRef(documentRootRef)));
		for (ObjRef changeSetRef : changeSetRefs)
			checkArgument(isInstanceOf(changeSetRef, Variability_3_0Package.eNS_URI,
					Variability_3_0Package.Literals.CHANGE_SET.getName()));

		VariabilityStatus vs = variabilityStatusCache.getUnchecked(documentRootRef);
		if (!vs.isChangeSetsEnabled) {
			throw new RuntimeException("Change sets are not enabled");
		}

		if (!putAll(vs.appliedChangeSets).equals(changeSetRefs)) {
			List<ChangeSet> changeSets = Lists.newArrayList();
			for (ObjRef changeSetRef : changeSetRefs)
				changeSets.add((ChangeSet) get(changeSetRef));

			// only update based on change sets that were (un)applied recently
			Set<ChangeSet> changeSetDiff = diff(changeSets.toArray(new ChangeSet[0]),
					vs.appliedChangeSets.toArray(new ChangeSet[0]));

			vs.variability.getAppliedChangeSets().clear();
			vs.variability.getAppliedChangeSets().addAll(changeSets);
			vs.refreshFromXadl();
			fireVariabilityEvent(Type.APPLIED, vs, null, null);

			Set<Integer> ints = Sets.newHashSet();
			for (ChangeSet changeSet : changeSetDiff)
				ints.add(vs.workingChangeSets.indexOf(changeSet));

			//TODO: use ints
			vs.setup(vs.workingChangeSets, vs.workingChangeSetsBeginIndex, -1, new int[0]); // Ints.toArray(ints));
			synchronizeDocumentRoot(vs, get(documentRootRef));
		}
	}

	@Override
	public synchronized List<ObjRef> getAppliedChangeSets(ObjRef documentRootRef) {
		checkArgument(documentRootRef.equals(getDocumentRootRef(documentRootRef)));

		VariabilityStatus vs = variabilityStatusCache.getUnchecked(documentRootRef);
		if (!vs.isChangeSetsEnabled) {
			throw new RuntimeException("Change sets are not enabled");
		}

		List<ObjRef> changeSetRefs = Lists.newArrayListWithCapacity(vs.appliedChangeSets.size());
		for (ChangeSet changeSet : vs.appliedChangeSets)
			changeSetRefs.add(put(changeSet));
		return changeSetRefs;
	}

	@Override
	public void setExplicitChangeSets(ObjRef documentRootRef, Iterable<ObjRef> changeSetRefs) {
		checkArgument(documentRootRef.equals(getDocumentRootRef(documentRootRef)));
		for (ObjRef changeSetRef : changeSetRefs)
			checkArgument(isInstanceOf(changeSetRef, Variability_3_0Package.eNS_URI,
					Variability_3_0Package.Literals.CHANGE_SET.getName()));

		VariabilityStatus vs = variabilityStatusCache.getUnchecked(documentRootRef);
		if (!vs.isChangeSetsEnabled) {
			throw new RuntimeException("Change sets are not enabled");
		}

		if (!Sets.newHashSet(putAll(vs.explicitChangeSets)).equals(Sets.newHashSet(changeSetRefs))) {
			vs.explicitChangeSets.clear();
			for (ObjRef changeSetRef : changeSetRefs)
				vs.explicitChangeSets.add((ChangeSet) get(changeSetRef));

			vs.refreshFromXadl();
			fireVariabilityEvent(Type.EXPLICIT, vs, null, null);

			// TODO: set ints[0] to include the explicit change set diff change sets
			vs.setup(vs.workingChangeSets, vs.workingChangeSetsBeginIndex, -1, new int[0]);
			synchronizeDocumentRoot(vs, get(documentRootRef));
		}
	}

	@Override
	public Set<ObjRef> getExplicitChangeSets(ObjRef documentRootRef) {
		checkArgument(documentRootRef.equals(getDocumentRootRef(documentRootRef)));

		VariabilityStatus vs = variabilityStatusCache.getUnchecked(documentRootRef);
		if (!vs.isChangeSetsEnabled) {
			throw new RuntimeException("Change sets are not enabled");
		}

		return Sets.newHashSet(putAll(vs.explicitChangeSets));
	}

	@Override
	public boolean isOverviewModeEnabled(ObjRef documentRootRef) {
		checkArgument(documentRootRef.equals(getDocumentRootRef(documentRootRef)));

		VariabilityStatus vs = variabilityStatusCache.getUnchecked(documentRootRef);
		if (!vs.isChangeSetsEnabled) {
			throw new RuntimeException("Change sets are not enabled");
		}

		return vs.isOverview;
	}

	@Override
	public void setOverviewModeEnabled(ObjRef documentRootRef, boolean enabled) {
		checkArgument(documentRootRef.equals(getDocumentRootRef(documentRootRef)));

		VariabilityStatus vs = variabilityStatusCache.getUnchecked(documentRootRef);
		if (!vs.isChangeSetsEnabled) {
			throw new RuntimeException("Change sets are not enabled");
		}

		vs.variability.setOverview(enabled);

		vs.refreshFromXadl();
		fireVariabilityEvent(Type.OVERVIEW, vs, null, null);

		if (enabled) {
			// TODO make ints the new change sets
			vs.setup(vs.workingChangeSets, vs.workingChangeSetsBeginIndex, -1, new int[0]);
			synchronizeDocumentRoot(vs, get(documentRootRef));
		}
		else {
			// TODO make ints the old change sets
			List<ChangeSet> changeSets = Lists.newArrayList(vs.allChangeSets);
			Set<ChangeSet> appliedChangeSets = Sets.newHashSet(vs.appliedChangeSets);
			changeSets.removeAll(appliedChangeSets);
			int beginChangeSetIndex = changeSets.size();
			changeSets.addAll(vs.appliedChangeSets);

			vs.setup(changeSets, beginChangeSetIndex, -1, new int[0]);
			synchronizeDocumentRoot(vs, get(documentRootRef));
		}
	}

	@Nullable
	private VariabilityStatus getVariabilityStatusCache(ObjRef baseObjRef) {
		ObjRef documentRootRef = getDocumentRootRef(baseObjRef);
		if (documentRootRef != null) {
			return variabilityStatusCache.getUnchecked(documentRootRef);
		}
		return null;
	}

	IXArchADT elementXArch = new XArchADTVariabilityElementImpl(this);

	@Nullable
	private String getKey(EObject eObject) {

		// don't store change sets for change sets themselves
		if (Variability_3_0Package.Literals.VARIABILITY.isInstance(eObject))
			return null;

		// use id if it exists
		EAttribute idAttribute = eObject.eClass().getEIDAttribute();
		if (idAttribute != null) {
			return (String) eObject.eGet(idAttribute);
		}

		// use extension type
		if (Xadlcore_3_0Package.Literals.EXTENSION.isInstance(eObject))
			return typeToString(eObject.eClass());

		// use hint name
		if (Hints_3_0Package.Literals.HINT.isInstance(eObject)) {
			return (String) eObject.eGet(Hints_3_0Package.Literals.HINT__NAME);
		}

		return null;
	}

	private class TypeMerger {

		EClass type = null;
		boolean mergedNull = false;
		boolean isResolvable = false;

		public TypeMerger(boolean isResolvable) {
			this.isResolvable = isResolvable;
		}

		public void mergeHigherPriorityType(@Nullable EClass newType, boolean isResolvable) {
			if (newType == null) {
				mergedNull = true;
			}
			else if (type == null || !newType.isSuperTypeOf(type)) {
				type = newType;
			}
			this.isResolvable |= isResolvable;
		}

	}

	private class AttributeMerger {

		Serializable value = null;
		boolean mergedChange = false;

		void mergeChange(@Nullable Serializable higherPrecedenceAttribute) {
			value = higherPrecedenceAttribute;
			mergedChange = true;
		}

	}

	@Nullable
	private EObject promote(@Nullable EObject eObj, @Nullable EClass type) {
		if (type == null)
			return null;
		if (eObj != null) {
			EClass eClass = eObj.eClass();
			if (eClass.equals(type)) {
				return eObj;
			}
			EObject newEObj = get(create(type.getEPackage().getNsURI(), type.getName()));
			// TODO copy over values
			return newEObj;
		}
		return get(create(type.getEPackage().getNsURI(), type.getName()));
	}

	@Nullable
	static String serialize(@Nullable Serializable value) {
		try {
			if (value == null)
				return null;
			if (value instanceof String)
				return "String:" + value;
			if (value instanceof Byte)
				return "byte:" + value;
			if (value instanceof Short)
				return "short:" + value;
			if (value instanceof Integer)
				return "int:" + value;
			if (value instanceof Long)
				return "long:" + value;
			if (value instanceof Float)
				return "float:" + value;
			if (value instanceof Double)
				return "double:" + value;
			if (value instanceof Enum<?>)
				return "enum:" + value.getClass().getName() + ":" + ((Enum<?>) value).name();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(value);
			oos.flush();
			oos.close();

			return "object:" + value.getClass().getName() + ":" + Base64.encode(baos.toByteArray());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Nullable
	@SuppressWarnings({ "rawtypes", "unchecked" })
	static Serializable deserialize(@Nullable String value) {
		if (value == null)
			return null;

		try {
			String[] values = value.split(":", 2);
			if (value.equals("null"))
				return null;
			if (values[0].equals("String"))
				return values[1];
			if (values[0].equals("byte"))
				return Byte.valueOf(values[1]);
			if (values[0].equals("short"))
				return Short.valueOf(values[1]);
			if (values[0].equals("int"))
				return Integer.valueOf(values[1]);
			if (values[0].equals("long"))
				return Long.valueOf(values[1]);
			if (values[0].equals("float"))
				return Float.valueOf(values[1]);
			if (values[0].equals("double"))
				return Double.valueOf(values[1]);

			values = value.split(":", 3);
			if (values[0].equals("enum"))
				return Enum.valueOf((Class<? extends Enum>) classForNameCache.getUnchecked(values[1]), values[2]);

			ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(values[2]));
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Serializable) ois.readObject();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static final LoadingCache<String, Class<?>> classForNameCache = CacheBuilder.newBuilder().build(
			new CacheLoader<String, Class<?>>() {
				@Override
				public Class<?> load(@Nullable String className) throws Exception {
					if (className == null)
						throw new NullPointerException();

					try {
						return Class.forName(className);
					}
					catch (Throwable t) {
					}

					for (Bundle bundle : Activator.getSingleton().getContext().getBundles()) {
						try {
							return bundle.loadClass(className);
						}
						catch (Throwable t) {
						}
					}

					throw new ClassNotFoundException(className);
				}
			});

	//////////////////////////////////////////////////////////////////////////

	@Nullable
	private AttributeChange createAttributeChange(@Nullable ChangeSet changeSet, EObject eObject, String name) {
		if (changeSet == null)
			return null;
		checkNotNull(eObject);
		checkNotNull(name);
		assert super.isAttached(put(eObject));

		if (getChangePath(eObject).contains(null))
			return null;

		return createAttributeChange(createElementChange(changeSet, eObject), name);
	}

	@Nullable
	private ElementChange createElementChange(@Nullable ChangeSet changeSet, EObject eObject) {
		if (changeSet == null)
			return null;
		checkNotNull(eObject);
		assert super.isAttached(put(eObject));

		if (getChangePath(eObject).contains(null))
			return null;

		ElementChange elementChange;
		if (eObject.eContainer() == null) {
			// DocumentRoot
			elementChange = ((ChangeSetOfChanges) changeSet).getElementChange();
			if (elementChange == null) {
				elementChange = Variability_3_0Factory.eINSTANCE.createElementChange();
				elementChange.setType(eObject.eClass().getEPackage().getNsURI() + "#" + eObject.eClass().getName());
				((ChangeSetOfChanges) changeSet).setElementChange(elementChange);
			}
			return elementChange;
		}

		elementChange = createElementChange(changeSet, eObject.eContainer());
		EStructuralFeature eFeature = eObject.eContainingFeature();
		if (!eFeature.isMany())
			return createElementChange(elementChange, eObject.eContainingFeature().getName(), eObject);
		return createElementChange(elementChange, eObject.eContainingFeature().getName(), getKey(eObject), eObject);
	}

	////////////////////////////////////////////////////////////////////////// utility methods

	@Nullable
	private Change lookup(ElementChange parent, String childName) {
		for (Change child : parent.getChange()) {
			if (childName.equals(child.getName()))
				return child;
		}
		return null;
	}

	@Nullable
	private Change lookup(ElementManyChange parent, String childName) {
		for (Change child : parent.getChange()) {
			if (childName.equals(child.getName()))
				return child;
		}
		return null;
	}

	private String typeToString(EClass eClass) {
		return eClass.getEPackage().getNsURI() + "#" + eClass.getName();
	}

	@Nullable
	private EClass typeFromString(@Nullable String type) {
		if (type == null)
			return null;
		String[] typeParts = type.split("#");
		return (EClass) EPackage.Registry.INSTANCE.getEPackage(typeParts[0]).getEClassifier(typeParts[1]);
	}

	private void run(List<Runnable> runnables) {
		for (Runnable r : runnables)
			r.run();
	}

	private boolean doSync(List<? extends Change> values, int[] synchronizeIndecies) {
		if (synchronizeIndecies.length == 0)
			return true;
		for (int i : synchronizeIndecies) {
			if (values.get(i) != null)
				return true;
		}
		return false;
	}

	private List<String> getChangePath(EObject eObject) {
		checkNotNull(eObject);
		assert super.isAttached(put(eObject));

		if (eObject.eContainer() == null || eObject.eContainer().eContainingFeature() == null)
			return Lists.newArrayListWithCapacity(20);

		List<String> changePath = getChangePath(eObject.eContainer());
		EStructuralFeature eFeature = eObject.eContainingFeature();
		if (eFeature instanceof EAttribute) {
			changePath.add(eFeature.getName());
		}
		else if (!eFeature.isMany()) {
			changePath.add(eFeature.getName());
		}
		else {
			changePath.add(eFeature.getName());
			changePath.add(getKey(eObject));
		}
		return changePath;
	}

	//@SuppressWarnings("unchecked")
	//private ElementChange resolveElementChange(ChangeSet changeSet, List<String> changePath) {
	//	checkNotNull(changeSet);
	//	checkNotNull(changePath);
	//	checkArgument(!changePath.contains(null));
	//
	//	Object value = ((ChangeSetOfChanges) changeSet).getElementChange();
	//	for (String change : changePath) {
	//		if (value == null)
	//			return null;
	//		else if (value instanceof EMap) {
	//			value = ((EMap<String, Change>) value).get(change);
	//		}
	//		else if (value instanceof ElementChange) {
	//			value = ((ElementChange) value).getChange().get(change);
	//		}
	//		else if (value instanceof ElementManyChange) {
	//			value = ((ElementManyChange) value).getChange().get(change);
	//		}
	//		else
	//			checkArgument(false, "Cannot resolve change path %s in change set %s", changePath, changeSet);
	//	}
	//	return (ElementChange) value;
	//}

	@Nullable
	private Serializable getSerializable(EObject eObject, EStructuralFeature eFeature) {
		if (eFeature instanceof EAttribute) {
			return (Serializable) eObject.eGet(eFeature);
		}
		if (eFeature instanceof EReference && !((EReference) eFeature).isContainment()) {
			EObject eRef = (EObject) eObject.eGet(eFeature);
			return eRef != null ? (Serializable) eRef.eGet(eRef.eClass().getEIDAttribute()) : null;
		}
		throw new IllegalArgumentException(eFeature.toString());
	}

	private void setSerializable(final EObject eObject, final EStructuralFeature eFeature,
			@Nullable final Serializable newChange, List<Runnable> endRunnables) {
		if (eFeature instanceof EAttribute) {
			eObject.eSet(eFeature, newChange);
			return;
		}
		if (eFeature instanceof EReference && !((EReference) eFeature).isContainment()) {
			endRunnables.add(new Runnable() {
				public void run() {
					ObjRef objRef = newChange != null ? XArchADTVariabilityImpl.super.getByID((String) newChange)
							: null;
					EObject eRef = objRef != null ? get(objRef) : null;
					eObject.eSet(eFeature, eRef);
				}
			});
			return;
		}
		throw new IllegalArgumentException(eFeature.toString());
	}

	////////////////////////////////////////////////////////////////////////// create methods

	@Nullable
	private AttributeChange createAttributeChange(@Nullable ElementChange elementChange, String name) {
		if (elementChange == null)
			return null;
		checkNotNull(elementChange);
		checkNotNull(name);

		Change attributeChange = lookup(elementChange, name);
		if (!(attributeChange instanceof AttributeChange)) {
			attributeChange = Variability_3_0Factory.eINSTANCE.createAttributeChange();
			attributeChange.setName(name);
			elementChange.getChange().add(attributeChange);
		}
		return (AttributeChange) attributeChange;
	}

	@Nullable
	private ElementChange createElementChange(@Nullable ElementChange elementChange, String name, EObject eObject) {
		if (elementChange == null)
			return null;
		checkNotNull(elementChange);
		checkNotNull(name);
		checkNotNull(eObject);

		Change childElementChange = lookup(elementChange, name);
		if (!(childElementChange instanceof ElementChange)) {
			childElementChange = Variability_3_0Factory.eINSTANCE.createElementChange();
			childElementChange.setName(name);
			((ElementChange) childElementChange).setType(typeToString(eObject.eClass()));
			elementChange.getChange().add(childElementChange);
		}
		return (ElementChange) childElementChange;
	}

	@Nullable
	private ElementChange createElementChange(@Nullable ElementChange elementChange, String name, @Nullable String key,
			EObject eObject) {
		if (elementChange == null)
			return null;
		checkNotNull(name);
		if (key == null)
			return null;

		Change elementManyChange = lookup(elementChange, name);
		if (!(elementManyChange instanceof ElementManyChange)) {
			elementManyChange = Variability_3_0Factory.eINSTANCE.createElementManyChange();
			elementManyChange.setName(name);
			elementChange.getChange().add(elementManyChange);
		}
		Change childElementChange = lookup((ElementManyChange) elementManyChange, key);
		if (!(childElementChange instanceof ElementChange)) {
			childElementChange = Variability_3_0Factory.eINSTANCE.createElementChange();
			childElementChange.setName(key);
			((ElementChange) childElementChange).setType(typeToString(eObject.eClass()));
			((ElementManyChange) elementManyChange).getChange().add((ElementChange) childElementChange);
		}
		return (ElementChange) childElementChange;
	}

	////////////////////////////////////////////////////////////////////////// resolve methods

	private List<AttributeChange> resolveAttributeChanges(List<ChangeSet> changeSets, EObject eObject, String name) {
		checkNotNull(changeSets);
		checkNotNull(eObject);
		checkNotNull(name);
		assert super.isAttached(put(eObject));

		return resolveAttributeChanges(resolveElementChanges(changeSets, eObject), name);
	}

	private List<ElementChange> resolveElementChanges(List<ChangeSet> changeSets, EObject eObject) {
		checkNotNull(changeSets);
		checkNotNull(eObject);
		assert super.isAttached(put(eObject));

		List<ElementChange> elementChanges;
		if (eObject.eContainer() == null) {
			// DocumentRoot
			elementChanges = Lists.newArrayListWithCapacity(changeSets.size());
			for (ChangeSet changeSet : changeSets)
				elementChanges.add(((ChangeSetOfChanges) changeSet).getElementChange());
			return elementChanges;
		}

		elementChanges = resolveElementChanges(changeSets, eObject.eContainer());
		EStructuralFeature eFeature = eObject.eContainmentFeature();
		if (!eFeature.isMany())
			return resolveElementChanges(elementChanges, eFeature.getName());
		return resolveElementChanges(elementChanges, eFeature.getName(), getKey(eObject));
	}

	private List<AttributeChange> resolveAttributeChanges(List<ElementChange> elementChanges, String name) {
		checkNotNull(elementChanges);
		checkNotNull(name);

		List<AttributeChange> attributeChanges = Lists.newArrayListWithCapacity(elementChanges.size());
		for (ElementChange elementChange : elementChanges) {
			if (elementChange != null) {
				Change attributeChange = lookup(elementChange, name);
				if (attributeChange instanceof AttributeChange) {
					attributeChanges.add((AttributeChange) attributeChange);
					continue;
				}
			}
			attributeChanges.add(null);
		}

		return attributeChanges;
	}

	private List<ElementChange> resolveElementChanges(List<ElementChange> elementChanges, String name) {
		checkNotNull(elementChanges);
		checkNotNull(name);

		List<ElementChange> childElementChanges = Lists.newArrayListWithCapacity(elementChanges.size());
		for (ElementChange elementChange : elementChanges) {
			if (elementChange != null) {
				Change childElementChange = lookup(elementChange, name);
				if (childElementChange instanceof ElementChange) {
					childElementChanges.add((ElementChange) childElementChange);
					continue;
				}
			}
			childElementChanges.add(null);
		}

		return childElementChanges;
	}

	private List<ElementChange> resolveElementChanges(List<ElementChange> elementChanges, String name,
			@Nullable String key) {
		checkNotNull(elementChanges);
		checkNotNull(name);

		List<ElementChange> childElementChanges = Lists.newArrayListWithCapacity(elementChanges.size());
		if (key == null) {
			for (int i = 0; i < elementChanges.size(); i++)
				childElementChanges.add(null);
			return childElementChanges;
		}
		for (ElementChange elementChange : elementChanges) {
			if (elementChange != null) {
				Change elementManyChange = lookup(elementChange, name);
				if (elementManyChange instanceof ElementManyChange) {
					Change childElementChange = lookup((ElementManyChange) elementManyChange, key);
					if (childElementChange instanceof ElementChange) {
						childElementChanges.add((ElementChange) childElementChange);
						continue;
					}
				}
			}
			childElementChanges.add(null);
		}

		return childElementChanges;
	}

	////////////////////////////////////////////////////////////////////////// synchronize methods

	protected void synchronizeAttribute(VariabilityStatus vs, EObject eObject, EStructuralFeature eFeature,
			List<AttributeChange> resolveAttributeChanges, SynchAttributeHelper synchAttributeHelper) {
		List<Runnable> endRunnables = Lists.newArrayList();
		vs.setup(vs.workingChangeSets, vs.workingChangeSetsBeginIndex,
				vs.workingChangeSets.indexOf(vs.activeChangeSet), new int[0]);
		synchronizeAttribute(vs, vs._synchronizeIndecies, eObject, eFeature, resolveAttributeChanges,
				synchAttributeHelper, endRunnables);
		run(endRunnables);
	}

	protected void synchronizeElement(VariabilityStatus vs, EObject eObject, List<ElementChange> elementChanges,
			SynchElementHelper synchElementHelper) {
		List<Runnable> endRunnables = Lists.newArrayList();
		vs.setup(vs.workingChangeSets, vs.workingChangeSetsBeginIndex,
				vs.workingChangeSets.indexOf(vs.activeChangeSet), new int[0]);
		synchronizeElement(vs, vs._synchronizeIndecies, eObject, elementChanges, synchElementHelper, endRunnables);
		run(endRunnables);
	}

	protected void synchronizeElement(VariabilityStatus vs, EObject eObject, List<String> preChangePath,
			@Nullable ElementChange preElementChange, List<String> postChangePath) {
		List<Runnable> endRunnables = Lists.newArrayList();
		vs.setup(vs.workingChangeSets, vs.workingChangeSetsBeginIndex,
				vs.workingChangeSets.indexOf(vs.activeChangeSet), new int[0]);
		synchronizeElement(vs, vs._synchronizeIndecies, eObject, preChangePath, preElementChange, postChangePath,
				endRunnables);
		run(endRunnables);
	}

	private void synchronizeAttribute(VariabilityStatus vs, int[] synchronizeIndecies, EObject eObject,
			EStructuralFeature eAttribute, List<AttributeChange> values, SynchAttributeHelper synchHelper,
			List<Runnable> endRunnables) {

		if (!doSync(values, synchronizeIndecies))
			return;

		checkArgument(vs._changeSets.size() == values.size());

		/*
		 * Determine the attribute value according to (1) the change sets as if
		 * the active change set had the current attribute value (aMergedType),
		 * and (2) the changes sets as they are (oMergedType)
		 */
		AttributeMerger aMergedChange = new AttributeMerger();
		AttributeMerger oMergedChange = new AttributeMerger();
		boolean explicitlyModified = false;

		// calculated the merged value
		for (int csIndex = vs.isOverview ? 0 : vs._beginChangeSetIndex, length = vs._changeSets.size(); csIndex < length; csIndex++) {
			AttributeChange attributeChange = values.get(csIndex);

			// use the current attribute for the active change set
			if (csIndex == vs._activeChangeSetIndex) {
				aMergedChange.mergeChange(getSerializable(eObject, eAttribute));
				if (vs._explicitChangeSet[vs._activeChangeSetIndex]) {
					explicitlyModified = true;
				}
			}

			// now merge the value from the change set
			if (attributeChange != null) {
				Serializable newChange = deserialize(attributeChange.getValue());
				oMergedChange.mergeChange(newChange);
				if (csIndex >= vs._beginChangeSetIndex) {
					if (csIndex != vs._activeChangeSetIndex) {
						aMergedChange.mergeChange(newChange);
					}
				}
			}
		}

		// update the change set to reflect the new value
		if (vs._activeChangeSetIndex != -1) {
			if (!SystemUtils.nullEquals(aMergedChange.value, oMergedChange.value)) {
				AttributeChange value = synchHelper.createChange(vs, eObject, eAttribute);
				if (value != null) {
					value.setValue(serialize((Serializable) aMergedChange.value));
				}
			}
		}

		// update the model with the merged value (if one was merged)
		Serializable newChange = null;
		if (vs._activeChangeSetIndex != -1 && aMergedChange.mergedChange) {
			newChange = aMergedChange.value;
		}
		else if (vs._activeChangeSetIndex == -1 && oMergedChange.mergedChange) {
			newChange = oMergedChange.value;
		}
		if (!SystemUtils.nullEquals(newChange, getSerializable(eObject, eAttribute))) {
			setSerializable(eObject, eAttribute, newChange, endRunnables);
		}
		if (explicitlyModified) {
			mergeStatus(vs, eObject, Status.EXPLICITLY_MODIFIED);
		}
	}

	private void synchronizeDocumentRoot(VariabilityStatus vs, EObject documentRoot) {

		List<Runnable> endRunnables = Lists.newArrayList();
		synchronizeElement(vs, vs._synchronizeIndecies, documentRoot,
				resolveElementChanges(vs._changeSets, documentRoot), new SynchElementHelper(true) {
					@Override
					public void clear(EObject oldEObject) {
						throw new UnsupportedOperationException();
					}

					@Override
					public void set(EObject newEObject) {
						throw new UnsupportedOperationException();
					}
				}, endRunnables);
		run(endRunnables);
	}

	private void synchronizeElement(VariabilityStatus vs, int[] synchronizeIndecies, @Nullable EObject eObject,
			List<ElementChange> values, SynchElementHelper synchHelper, List<Runnable> endRunnables) {

		if (!doSync(values, synchronizeIndecies))
			return;

		// keep document root and top level element
		EObject newEObject = eObject;
		if (eObject == null || eObject.eContainer() != null && eObject.eContainer().eContainer() != null) {
			newEObject = synchronizeElementType(vs, synchronizeIndecies, eObject, values, synchHelper, endRunnables);
		}

		if (newEObject != null) {

			// if the object was created, we need to reconstruct it using all the change sets
			if (eObject == null)
				synchronizeIndecies = new int[0];

			synchronizeElementContent(vs, synchronizeIndecies, newEObject, values, synchHelper, endRunnables);
		}
	}

	@Nullable
	private EObject synchronizeElementType(VariabilityStatus vs, int[] synchronizeIndecies,
			@Nullable EObject oldEObject, List<ElementChange> values, SynchElementHelper synchHelper,
			List<Runnable> endRunnables) {

		if (!doSync(values, synchronizeIndecies))
			return oldEObject;

		checkArgument(vs._changeSets.size() == values.size());

		/*
		 * Determine the type according to (1) the change sets as if the active
		 * change set had the current objRef type (aMergedType), (2) as if all
		 * change sets were applied (for the overview - oMergedType), and (3)
		 * for explicit change sets (eMergedType)
		 */
		TypeMerger aMergedType = new TypeMerger(synchHelper.isImplicitlyResolvable());
		TypeMerger cMergedType = new TypeMerger(synchHelper.isImplicitlyResolvable());
		TypeMerger oMergedType = new TypeMerger(synchHelper.isImplicitlyResolvable());
		TypeMerger eMergedType = new TypeMerger(synchHelper.isImplicitlyResolvable());

		// merge type information
		for (int csIndex = vs.isOverview ? 0 : vs._beginChangeSetIndex, length = vs._changeSets.size(); csIndex < length; csIndex++) {
			ElementChange elementChange = values.get(csIndex);

			// use the current type for the active change set
			if (csIndex == vs._activeChangeSetIndex) {
				if (oldEObject != null) {
					aMergedType.mergeHigherPriorityType(oldEObject.eClass(), true);
					if (vs._explicitChangeSet[csIndex]) {
						eMergedType.mergeHigherPriorityType(oldEObject.eClass(), true);
					}
				}
				else {
					aMergedType.mergeHigherPriorityType(null, false);
					if (vs._explicitChangeSet[csIndex]) {
						eMergedType.mergeHigherPriorityType(null, false);
					}
				}
			}

			// now merge the type from the change set
			if (elementChange != null) {
				EClass eClass = typeFromString(elementChange.getType());
				boolean isResolvable = eClass != null ? getKey(XArchADTProxy.proxy(elementXArch, put(elementChange))) != null
						: false;
				oMergedType.mergeHigherPriorityType(eClass, isResolvable);
				if (csIndex >= vs._beginChangeSetIndex) {
					cMergedType.mergeHigherPriorityType(eClass, isResolvable);
					if (csIndex != vs._activeChangeSetIndex) {
						aMergedType.mergeHigherPriorityType(eClass, isResolvable);
					}
				}
				if (vs._explicitChangeSet[csIndex]) {
					if (csIndex != vs._activeChangeSetIndex) {
						eMergedType.mergeHigherPriorityType(eClass, isResolvable);
					}
				}
			}
		}

		// update model
		EObject newEObject = aMergedType.isResolvable && aMergedType.type != null ? promote(oldEObject,
				aMergedType.type) : oMergedType.isResolvable && oMergedType.type != null ? promote(oldEObject,
				oMergedType.type) : null;

		if (newEObject != oldEObject) {
			if (oldEObject != null) {
				setStatus(vs, oldEObject, Status.DETACHED);
			}
			if (newEObject != null) {
				if (oldEObject != null)
					synchHelper.clear(oldEObject);
				synchHelper.set(newEObject);
			}
		}
		if (newEObject != null) {
			Status status;
			if (eMergedType.mergedNull) {
				status = !aMergedType.mergedNull && aMergedType.isResolvable ? Status.EXPLICITLY_REMOVED_BUT_REALLY_ADDED
						: Status.EXPLICITLY_REMOVED;
			}
			else if (eMergedType.isResolvable && eMergedType.type != null) {
				status = aMergedType.mergedNull || !aMergedType.isResolvable ? Status.EXPLICITLY_ADDED_BUT_REALLY_REMOVED
						: Status.EXPLICITLY_ADDED;
			}
			else if (eMergedType.type != null) {
				status = aMergedType.mergedNull || !aMergedType.isResolvable ? Status.EXPLICITLY_MODIFIED_BUT_REALLY_REMOVED
						: Status.EXPLICITLY_MODIFIED;
			}
			else if (aMergedType.isResolvable && !aMergedType.mergedNull) {
				status = Status.ATTACHED;
			}
			else if (vs.isOverview && oMergedType.isResolvable) {
				status = Status.OVERVIEW;
			}
			else {
				status = Status.DETACHED;
			}

			setStatus(vs, newEObject, mergeStatus(vs, newEObject.eContainer(), status));
		}

		// update the change set
		if (vs._activeChangeSetIndex != -1) {
			if (aMergedType.mergedNull && !oMergedType.mergedNull) {
				if (oldEObject != null) {
					ElementChange elementChange = synchHelper.createChange(vs, oldEObject);
					if (elementChange != null) {
						elementChange.setType(null);
						elementChange.getChange().clear();
					}
				}
			}
			else if (!SystemUtils.nullEquals(aMergedType.type, cMergedType.type) && !aMergedType.mergedNull) {
				if (newEObject != null) {
					ElementChange elementChange = synchHelper.createChange(vs, newEObject);
					if (elementChange != null) {
						elementChange.setType(typeToString(aMergedType.type));
						elementChange.getChange().clear();
					}
				}
			}
		}

		return newEObject;
	}

	private Status mergeStatus(VariabilityStatus vs, EObject eContainer, Status status) {
		boolean isOverview = false;
		boolean childrenAttached = true;

		EObject parent = eContainer;
		while (parent != null) {
			Status parentStatus = getStatus(parent);
			isOverview |= parentStatus == Status.OVERVIEW;
			childrenAttached &= parentStatus.attached;
			parent = parent.eContainer();
		}

		// mark parents as modified, if necessary
		if (status.explicit) {
			parent = eContainer;
			OUTER: while (parent != null && parent.eContainer() != null && parent.eContainer().eContainer() != null) {
				switch (getStatus(parent)) {
				case ATTACHED:
					setStatus(vs, parent, Status.EXPLICITLY_MODIFIED);
					break;
				case OVERVIEW:
					setStatus(vs, parent, Status.EXPLICITLY_MODIFIED_BUT_REALLY_REMOVED);
					break;
				default:
					break OUTER;
				}
				parent = parent.eContainer();
			}
		}

		if (isOverview || !childrenAttached) {
			switch (status) {
			case EXPLICITLY_ADDED:
			case EXPLICITLY_ADDED_BUT_REALLY_REMOVED:
				return Status.EXPLICITLY_ADDED_BUT_REALLY_REMOVED;
			case EXPLICITLY_MODIFIED:
			case EXPLICITLY_MODIFIED_BUT_REALLY_REMOVED:
				return Status.EXPLICITLY_MODIFIED_BUT_REALLY_REMOVED;
			case EXPLICITLY_REMOVED:
			case EXPLICITLY_REMOVED_BUT_REALLY_ADDED:
				return Status.EXPLICITLY_REMOVED;
			case ATTACHED:
			case DETACHED:
			case OVERVIEW:
				return Status.OVERVIEW;
			}
		}

		return status;
	}

	private void synchronizeElementContent(final VariabilityStatus vs, int[] synchronizeIndecies,
			final EObject eObject, List<ElementChange> values, final SynchElementHelper synchHelper,
			List<Runnable> endRunnables) {

		if (!doSync(values, synchronizeIndecies))
			return;

		checkArgument(vs._changeSets.size() == values.size());

		boolean isDocumentRoot = eObject.eContainer() == null;
		for (final EStructuralFeature eFeature : eObject.eClass().getEAllStructuralFeatures()) {
			if (isDocumentRoot) {
				if (eFeature.getName().equals("mixed"))
					continue;
				if (eFeature.getName().equals("xMLNSPrefixMap"))
					continue;
				if (eFeature.getName().equals("xSISchemaLocation"))
					continue;
				if (eFeature.getName().equals("topLevelElement"))
					continue;
			}
			if (eFeature instanceof EAttribute || eFeature instanceof EReference
					&& !((EReference) eFeature).isContainment()) {
				synchronizeAttribute(vs, synchronizeIndecies, eObject, eFeature,
						resolveAttributeChanges(values, eFeature.getName()), new SynchAttributeHelper() {
							@Nullable
							@Override
							public AttributeChange createChange(VariabilityStatus vs, EObject eObject,
									EStructuralFeature eAttribute) {
								return createAttributeChange(synchHelper.createChange(vs, eObject),
										eAttribute.getName());
							}
						}, endRunnables);
			}
			else if (!eFeature.isMany()) {
				synchronizeElement(vs, synchronizeIndecies, (EObject) eObject.eGet(eFeature),
						resolveElementChanges(values, eFeature.getName()), new SynchElementHelper(true) {
							@Override
							public void set(EObject newEObject) {
								eObject.eSet(eFeature, newEObject);
							}

							@Nullable
							@Override
							public ElementChange createChange(VariabilityStatus vs, EObject newEObject) {
								return createElementChange(synchHelper.createChange(vs, eObject), eFeature.getName(),
										newEObject);
							}
						}, endRunnables);
			}
			else {
				synchronizeElementMany(vs, synchronizeIndecies, eObject, eFeature, values,
						new SynchElementHelper(false) {
							@SuppressWarnings("unchecked")
							@Override
							public void set(EObject newEObject) {
								((EList<EObject>) eObject.eGet(eFeature)).add(newEObject);
							}

							@Nullable
							@Override
							public ElementChange createChange(VariabilityStatus vs, EObject newEObject) {
								return createElementChange(synchHelper.createChange(vs, eObject), eFeature.getName(),
										getKey(newEObject), newEObject);
							}
						}, endRunnables);
			}
		}
	}

	private void synchronizeElement(VariabilityStatus vs, int[] synchronizeIndecies, EObject eObject,
			List<String> preChangePath, @Nullable ElementChange preElementChange, List<String> postChangePath,
			List<Runnable> endRunnables) {

		checkArgument(!preChangePath.equals(postChangePath));

		if (preElementChange != null) {
			preElementChange.setType(null);
			preElementChange.getChange().clear();
		}

		int diffIndex = 0;
		while (diffIndex < preChangePath.size() && diffIndex < postChangePath.size()) {
			if (!SystemUtils.nullEquals(preChangePath.get(diffIndex), postChangePath.get(diffIndex))) {
				break;
			}
			diffIndex++;
		}

		while (getChangePath(eObject).size() > diffIndex)
			eObject = eObject.eContainer();
		final EObject eParentObject = eObject.eContainer();
		final EStructuralFeature eFeature = eObject.eContainingFeature();

		synchronizeElement(vs, synchronizeIndecies, eObject, resolveElementChanges(vs._changeSets, eObject),
				new SynchElementHelper(!eFeature.isMany()) {
					@SuppressWarnings("unchecked")
					@Override
					public void set(EObject newEObject) {
						if (!eFeature.isMany())
							eParentObject.eSet(eFeature, newEObject);
						else
							((EList<EObject>) eParentObject.eGet(eFeature)).add(newEObject);
					}
				}, endRunnables);
	}

	@SuppressWarnings("unchecked")
	private void synchronizeElementMany(VariabilityStatus vs, int[] synchronizeIndecies, final EObject eObject,
			final EStructuralFeature eFeature, List<ElementChange> values, final SynchElementHelper synchHelper,
			List<Runnable> endRunnables) {

		if (!doSync(values, synchronizeIndecies))
			return;

		checkArgument(vs._changeSets.size() == values.size());

		// get references to all children
		Map<String, EObject> referenceToOldChild = Maps.newHashMap();
		for (EObject childEObject : Lists.newArrayList((EList<EObject>) eObject.eGet(eFeature))) {
			String childKey = getKey(childEObject);
			if (childKey != null) {
				EObject duplicateChildEObject = referenceToOldChild.put(childKey, childEObject);
				if (duplicateChildEObject != null)
					// we have a duplicate reference, remove it
					((EList<EObject>) eObject.eGet(eFeature)).remove(duplicateChildEObject);
			}
		}

		// process the recorded references
		Set<String> changesKeys = Sets.newHashSet();
		for (ElementChange elementChange : values) {
			if (elementChange != null) {
				Change value = lookup(elementChange, eFeature.getName());
				if (value instanceof ElementManyChange) {
					for (ElementChange childElementChange : ((ElementManyChange) value).getChange())
						changesKeys.add(childElementChange.getName());
				}
			}
		}
		for (String changeKey : changesKeys) {
			synchronizeElement(vs, synchronizeIndecies, referenceToOldChild.remove(changeKey),
					resolveElementChanges(values, eFeature.getName(), changeKey), synchHelper, endRunnables);
		}

		// process the remaining references
		if (vs.activeChangeSet != null) {
			// synchronize with the active change set
			for (Entry<String, EObject> entry : referenceToOldChild.entrySet()) {
				synchronizeElement(vs, synchronizeIndecies, entry.getValue(),
						resolveElementChanges(values, eFeature.getName(), entry.getKey()), synchHelper, endRunnables);
			}
		}
		else {
			// there's no active change set so just remove
			for (Entry<String, EObject> entry : referenceToOldChild.entrySet()) {
				setStatus(vs, entry.getValue(), Status.DETACHED);
			}
		}
	}

	//////////////////////////////////////////////////////////////////////////

	@Override
	public synchronized void set(ObjRef baseObjRef, String typeOfThing, @Nullable Serializable value) {
		final VariabilityStatus vs = getVariabilityStatusCache(baseObjRef);
		if (vs == null || !vs.isChangeSetsEnabled || !super.isAttached(baseObjRef)) {
			super.set(baseObjRef, typeOfThing, value);
		}
		else {
			final EObject eObject = get(baseObjRef);
			final EStructuralFeature eFeature = getEFeature(eObject, typeOfThing);

			List<String> preChangePath = getChangePath(eObject);
			Serializable oldValue = super.get(baseObjRef, typeOfThing);
			super.set(baseObjRef, typeOfThing, value);
			List<String> postChangePath = getChangePath(eObject);

			if (!preChangePath.equals(postChangePath)) {
				super.set(baseObjRef, typeOfThing, oldValue);
				ElementChange preElementChange = preChangePath.contains(null) ? null : createElementChange(
						vs.activeChangeSet, eObject);
				super.set(baseObjRef, typeOfThing, value);
				synchronizeElement(vs, eObject, preChangePath, preElementChange, postChangePath);
			}
			else if (eFeature instanceof EAttribute || eFeature instanceof EReference
					&& !((EReference) eFeature).isContainment()) {
				synchronizeAttribute(vs, eObject, eFeature,
						resolveAttributeChanges(vs.workingChangeSets, eObject, eFeature.getName()),
						new SynchAttributeHelper());
			}
			else if (value != null) {
				EObject childEObject = get((ObjRef) value);
				synchronizeElement(vs, childEObject, resolveElementChanges(vs.workingChangeSets, childEObject),
						new SynchElementHelper(true) {
							@Override
							public void set(EObject newEObject) {
								eObject.eSet(eFeature, newEObject);
							}
						});
			}
			else {
				synchronizeElement(vs, eObject, resolveElementChanges(vs.workingChangeSets, eObject),
						new SynchElementHelper(true) {
							@Override
							public void set(EObject newEObject) {
								eObject.eSet(eFeature, newEObject);
							}
						});
			}
		}
	}

	@Override
	public synchronized void clear(ObjRef baseObjRef, String typeOfThing) {
		final VariabilityStatus vs = getVariabilityStatusCache(baseObjRef);
		if (vs == null || !vs.isChangeSetsEnabled || !super.isAttached(baseObjRef)) {
			super.clear(baseObjRef, typeOfThing);
		}
		else {
			final EObject eObject = get(baseObjRef);
			final EStructuralFeature eFeature = getEFeature(eObject, typeOfThing);

			List<String> preChangePath = getChangePath(eObject);
			ElementChange preElementChange = preChangePath.contains(null) ? null : createElementChange(
					vs.activeChangeSet, eObject);

			if (eFeature instanceof EAttribute || eFeature instanceof EReference
					&& ((EReference) eFeature).isContainment()) {
				super.clear(baseObjRef, typeOfThing);
				List<String> postChangePath = getChangePath(eObject);

				if (!preChangePath.equals(postChangePath)) {
					synchronizeElement(vs, eObject, preChangePath, preElementChange, postChangePath);
				}
				else {
					synchronizeAttribute(vs, eObject, eFeature,
							resolveAttributeChanges(vs.workingChangeSets, eObject, eFeature.getName()),
							new SynchAttributeHelper());
				}
			}
			else {
				EObject childEObject = (EObject) eObject.eGet(eFeature);
				setStatus(vs, childEObject, Status.DETACHED);
				List<String> postChangePath = getChangePath(eObject);

				if (!preChangePath.equals(postChangePath)) {
					synchronizeElement(vs, eObject, preChangePath, preElementChange, postChangePath);
				}
				else {
					synchronizeElement(vs, childEObject, resolveElementChanges(vs.workingChangeSets, childEObject),
							new SynchElementHelper(true) {
								@Override
								public void set(EObject newEObject) {
									eObject.eSet(eFeature, newEObject);
								}
							});
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public synchronized void add(ObjRef baseObjRef, String typeOfThing, Serializable thingToAdd) {
		final VariabilityStatus vs = getVariabilityStatusCache(baseObjRef);
		if (vs == null || !vs.isChangeSetsEnabled || !super.isAttached(baseObjRef)) {
			super.add(baseObjRef, typeOfThing, thingToAdd);
		}
		else {
			final EObject eObject = get(baseObjRef);
			final EStructuralFeature eFeature = getEFeature(eObject, typeOfThing);
			super.add(baseObjRef, typeOfThing, thingToAdd);
			if (!(thingToAdd instanceof ObjRef)) {
				List<String> changePath = getChangePath(eObject);
				if (!changePath.contains(null)) {
					synchronizeElement(vs, eObject, resolveElementChanges(vs.workingChangeSets, eObject),
							new SynchElementHelper(false) {
								@Override
								public void set(EObject newEObject) {
									throw new UnsupportedOperationException();
								}
							});
				}
			}
			else {
				final EObject eChildObject = get((ObjRef) thingToAdd);
				List<String> changePath = getChangePath(eChildObject);
				if (!changePath.contains(null)) {
					synchronizeElement(vs, eChildObject, resolveElementChanges(vs.workingChangeSets, eChildObject),
							new SynchElementHelper(false) {
								@Override
								public void set(EObject newEObject) {
									((EList<EObject>) eObject.eGet(eFeature)).add(newEObject);
								}
							});
				}
				else {
					EList<EObject> eList = (EList<EObject>) eObject.eGet(eFeature);
					eList.move(eList.size() - 1, eChildObject);
					setStatus(vs, eChildObject, Status.ATTACHED);
				}
			}
		}
	}

	@Override
	public synchronized void add(ObjRef baseObjRef, String typeOfThing, Collection<? extends Serializable> thingsToAdd) {
		for (Serializable thingToAdd : thingsToAdd)
			add(baseObjRef, typeOfThing, thingToAdd);
	}

	@Override
	public synchronized void remove(ObjRef baseObjRef, String typeOfThing, Serializable thingToRemove) {
		final VariabilityStatus vs = getVariabilityStatusCache(baseObjRef);
		if (vs == null || !vs.isChangeSetsEnabled || !super.isAttached(baseObjRef)) {
			super.remove(baseObjRef, typeOfThing, thingToRemove);
		}
		else {
			final EObject eObject = get(baseObjRef);
			if (!(thingToRemove instanceof ObjRef)) {
				super.remove(baseObjRef, typeOfThing, thingToRemove);
				List<String> changePath = getChangePath(eObject);
				if (!changePath.contains(null)) {
					synchronizeElement(vs, eObject, resolveElementChanges(vs.workingChangeSets, eObject),
							new SynchElementHelper(false) {
								@Override
								public void set(EObject newEObject) {
									throw new UnsupportedOperationException();
								}
							});
				}
			}
			else {
				final EObject eChildObject = get((ObjRef) thingToRemove);
				List<String> preChangePath = getChangePath(eChildObject);
				ElementChange preElementChange = preChangePath.contains(null) ? null : createElementChange(
						vs.activeChangeSet, eChildObject);
				//super.remove(baseObjRef, typeOfThing, thingToRemove);
				setStatus(vs, eChildObject, Status.DETACHED);
				if (preElementChange != null) {
					preElementChange.setType(null);
					preElementChange.getChange().clear();
				}
			}
		}
	}

	@Override
	public synchronized void remove(ObjRef baseObjRef, String typeOfThing,
			Collection<? extends Serializable> thingsToRemove) {
		for (Serializable thingToRemove : thingsToRemove)
			remove(baseObjRef, typeOfThing, thingToRemove);
	}

	//////////////////////////////////////////////////////////////////////////

	@Override
	public synchronized @Nullable
	Serializable get(ObjRef baseObjRef, String typeOfThing) {
		return filterDetached(super.get(baseObjRef, typeOfThing));
	}

	@Override
	public synchronized @Nullable
	Serializable get(ObjRef baseObjRef, String typeOfThing, boolean resolve) {
		return filterDetached(super.get(baseObjRef, typeOfThing, resolve));
	}

	@Override
	public synchronized List<Serializable> getAll(ObjRef baseObjRef, String typeOfThing) {
		return filterDetached(super.getAll(baseObjRef, typeOfThing));
	}

	@Override
	@Nullable
	public synchronized ObjRef getByID(ObjRef documentRef, String id) {
		return filterDetachedByAncestors(super.getByID(documentRef, id));
	}

	@Override
	@Nullable
	public synchronized ObjRef getByID(String id) {
		return filterDetachedByAncestors(super.getByID(id));
	}

	@Override
	@Nullable
	public synchronized String getContainingFeatureName(ObjRef ref) {
		return isAttachedObjRef(ref) ? super.getContainingFeatureName(ref) : null;
	}

	@Override
	@Nullable
	public synchronized ObjRef getParent(ObjRef ref) {
		return isAttachedObjRef(ref) ? super.getParent(ref) : null;
	}

	@Override
	@Nullable
	public synchronized String getTagName(ObjRef ref) {
		return isAttachedObjRef(ref) ? super.getTagName(ref) : null;
	}

	@Override
	@Nullable
	public synchronized URI getURI(ObjRef ref) {
		return isAttachedObjRef(ref) ? super.getURI(ref) : null;
	}

	@Override
	public synchronized List<ObjRef> getAllAncestors(ObjRef ref) {
		EObject eObject = get(ref);
		List<ObjRef> ancestorObjRefs = Lists.newArrayList();
		while (eObject != null) {
			ancestorObjRefs.add(put(eObject));
			if (!isAttached(eObject))
				break;
			eObject = eObject.eContainer();
		}
		return ancestorObjRefs;
	}

	@Override
	public synchronized String getTagsOnlyPathString(ObjRef ref) {
		EObject eObject = get(ref);
		List<String> tags = Lists.newArrayList();
		while (eObject != null) {
			if (!isAttached(eObject))
				break;
			String tagName = getTagName(put(eObject));
			if (tagName == null)
				break;
			tags.add(tagName);
			eObject = eObject.eContainer();
		}
		return Joiner.on("/").join(Lists.reverse(tags));
	}

	@Override
	@Nullable
	public synchronized ObjRef getDocumentRootRef(ObjRef ref) {
		return isAttachedObjRef(ref) ? super.getDocumentRootRef(ref) : null;
	}
}
