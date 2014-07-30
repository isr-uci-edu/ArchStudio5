package org.archstudio.xarchadt.variability;

import java.util.List;
import java.util.Set;

import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jdt.annotation.Nullable;

public interface IXArchADTVariability extends IXArchADT {

	public enum ChangeStatus {
		/** Indicates that the objRef is added by an explicit change set */
		EXPLICITLY_ADDED(true),
		/**
		 * Indicates that the objRef is added by an explicit change set, but would normally not be present in the model
		 * because it was also removed elsewhere.
		 */
		EXPLICITLY_ADDED_BUT_REALLY_REMOVED(false),
		/** Indicates that the objRef is modified by an explicit change set */
		EXPLICITLY_MODIFIED(true),
		/**
		 * Indicates that the objRef is modified by an explicit change set, but would normally not be present in the
		 * model because it was also removed elsewhere.
		 */
		EXPLICITLY_MODIFIED_BUT_REALLY_REMOVED(false),
		/** Indicates that the objRef is removed by an explicit change set */
		EXPLICITLY_REMOVED(false),
		/**
		 * Indicates that the objRef is removed by an explicit change set, but it is not removed by the applied change
		 * sets
		 */
		EXPLICITLY_REMOVED_BUT_REALLY_ADDED(true),
		/** Indicates that the objRef can be added by an unapplied change set */
		OVERVIEW(false),
		/** Indicates that the objRef is added by the applied change sets */
		ATTACHED(true),
		/** Indicates that change sets are not enabled for this document */
		NOT_ENABLED(true);

		private final boolean attached;

		private ChangeStatus(boolean attached) {
			this.attached = attached;
		}

		/**
		 * Indicates whether the objRef would be part of a normal model (i.e., when not in overview mode and without any
		 * explicit change sets)
		 */
		public boolean isAttached() {
			return attached;
		}
	}

	public void setChangeSetsEnabled(ObjRef documentRootRef, boolean enabled);

	public boolean isChangeSetsEnabled(ObjRef documentRootRef);

	public void setOverviewModeEnabled(ObjRef documentRootRef, boolean enabled);

	public boolean isOverviewModeEnabled(ObjRef documentRootRef);

	public void setActiveChangeSet(ObjRef documentRootRef, @Nullable ObjRef changeSetRef);

	@Nullable
	public ObjRef getActiveChangeSet(ObjRef documentRootRef);

	public void applyChangeSets(ObjRef documentRootRef, List<ObjRef> changeSetRefs);

	public List<ObjRef> getAppliedChangeSets(ObjRef documentRootRef);

	public void setExplicitChangeSets(ObjRef documentRootRef, Iterable<ObjRef> changeSetRefs);

	public Set<ObjRef> getExplicitChangeSets(ObjRef documentRootRef);

	public ChangeStatus getChangeStatus(ObjRef objRef);
}
