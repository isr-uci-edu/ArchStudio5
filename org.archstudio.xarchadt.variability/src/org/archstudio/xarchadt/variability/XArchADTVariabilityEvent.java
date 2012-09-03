package org.archstudio.xarchadt.variability;

import java.util.List;
import java.util.Set;

import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.variability.IXArchADTVariability.ChangeStatus;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class XArchADTVariabilityEvent {

	public enum Type {
		ENABLED, DISABLED, ACTIVE, APPLIED, EXPLICIT, OVERVIEW, STATUS
	}

	final private Type type;
	final private ObjRef documentRootRef;
	final private ObjRef activeChangeSetRef;
	final private List<ObjRef> appliedChangeSetRefs;
	final private Set<ObjRef> explicitChangeSetRefs;
	final private boolean isOverview;
	final private ObjRef changedObjRef;
	final private ChangeStatus changeStatus;

	public XArchADTVariabilityEvent(Type type, ObjRef documentRootRef, ObjRef activeChangeSetRef,
			Iterable<ObjRef> appliedChangeSetRefs, Iterable<ObjRef> explicitChangeSetRefs, boolean isOverview,
			ObjRef changedObjRef, ChangeStatus changeStatus) {
		this.type = type;
		this.documentRootRef = documentRootRef;
		this.activeChangeSetRef = activeChangeSetRef;
		this.appliedChangeSetRefs = Lists.newArrayList(appliedChangeSetRefs);
		this.explicitChangeSetRefs = Sets.newHashSet(explicitChangeSetRefs);
		this.isOverview = isOverview;
		this.changedObjRef = changedObjRef;
		this.changeStatus = changeStatus;
	}

	public Type getType() {
		return type;
	}

	public ObjRef getDocumentRootRef() {
		return documentRootRef;
	}

	public ObjRef getActiveChangeSetRef() {
		return activeChangeSetRef;
	}

	public List<ObjRef> getAppliedChangeSetRefs() {
		return Lists.newArrayList(appliedChangeSetRefs);
	}

	public Set<ObjRef> getExplicitChangeSetRefs() {
		return Sets.newHashSet(explicitChangeSetRefs);
	}

	public boolean isOverview() {
		return isOverview;
	}

	public ObjRef getChangedObjRef() {
		return changedObjRef;
	}

	public ChangeStatus getChangeStatus() {
		return changeStatus;
	}

	@Override
	public String toString() {
		return "XArchADTVariabilityEvent [type=" + type + ", documentRootRef=" + documentRootRef
				+ ", activeChangeSetRef=" + activeChangeSetRef + ", appliedChangeSetRefs=" + appliedChangeSetRefs
				+ ", explicitChangeSetRefs=" + explicitChangeSetRefs + ", isOverview=" + isOverview
				+ ", changedObjRef=" + changedObjRef + ", changeStatus=" + changeStatus + "]";
	}

	
}
