package org.archstudio.xarchadt.variability.ui;

import java.util.List;
import java.util.Set;

import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.variability_3_0.ChangeSet;
import org.archstudio.xadl3.variability_3_0.Variability;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.archstudio.xarchadt.variability.IXArchADTVariability;
import org.archstudio.xarchadt.variability.VariabilityUtils;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Item;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class ChangeSetCellModifier implements ICellModifier {

	protected final TreeViewer viewer;

	protected final IXArchADTVariability xarch;

	public ChangeSetCellModifier(TreeViewer viewer, IXArchADTVariability xarch) {
		this.viewer = viewer;
		this.xarch = xarch;
	}

	public boolean canModify(Object element, String property) {
		if ("Apply".equals(property)) {
			return true;
		}
		if ("View".equals(property)) {
			return true;
		}
		if ("Change Set".equals(property)) {
			return true;
		}
		return false;
	}

	public Object getValue(Object element, String property) {
		if (element instanceof Item) {
			element = ((Item) element).getData();
		}

		if ("Apply".equals(property)) {
			return true;
		}
		if ("View".equals(property)) {
			return true;
		}
		if ("Change Set".equals(property)) {
			return XadlUtils.getName(xarch, (ObjRef) element);
		}
		return null;
	}

	public void modify(Object element, String property, Object value) {
		if (element instanceof Item) {
			element = ((Item) element).getData();
		}

		try {
			if ("Apply".equals(property)) {
				ObjRef documentRootRef = xarch.getDocumentRootRef((ObjRef) element);
				if (documentRootRef != null) {
					List<ObjRef> appliedChangeSetRefs = xarch.getAppliedChangeSets(documentRootRef);
					if (!appliedChangeSetRefs.remove((ObjRef) element)) {
						Variability variability = VariabilityUtils.getVariability(xarch, documentRootRef);
						if (variability != null) {
							List<ChangeSet> appliedChangeSets = Lists.newArrayList(variability.getChangeSet());
							Set<ChangeSet> filter = Sets.newHashSet(variability.getAppliedChangeSets());
							filter.add((ChangeSet) XArchADTProxy.proxy(xarch, (ObjRef) element));
							appliedChangeSets.retainAll(filter);
							appliedChangeSetRefs.clear();
							appliedChangeSetRefs.addAll(XArchADTProxy.unproxy(appliedChangeSets));
						}
					}
					xarch.applyChangeSets(documentRootRef, appliedChangeSetRefs);
				}
			}
			if ("View".equals(property)) {
				ObjRef documentRootRef = xarch.getDocumentRootRef((ObjRef) element);
				if (documentRootRef != null) {
					Set<ObjRef> explicitChangeSetRefs = xarch.getExplicitChangeSets(documentRootRef);
					if (!explicitChangeSetRefs.remove((ObjRef) element))
						explicitChangeSetRefs.add((ObjRef) element);
					xarch.setExplicitChangeSets(documentRootRef, explicitChangeSetRefs);
				}
			}
			if ("Change Set".equals(property)) {
				if (value instanceof String)
					xarch.set((ObjRef) element, "name", (String) value);
			}
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
