package org.archstudio.xarchadt.variability.ui;

import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.xadl3.variability_3_0.Variability;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.XArchADTProxy;
import org.archstudio.xarchadt.variability.IXArchADTVariability;
import org.archstudio.xarchadt.variability.VariabilityUtils;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ChangeSetContentProvider implements ITreeContentProvider, IXArchADTModelListener {

	protected final IXArchADTVariability xarch;
	protected Viewer viewer = null;
	protected ObjRef documentRootRef = null;
	protected Variability variability = null;

	public ChangeSetContentProvider(IXArchADTVariability xarch) {
		this.xarch = xarch;
	}

	public void dispose() {
		viewer = null;
		documentRootRef = null;
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = viewer;
		this.documentRootRef = (ObjRef) newInput;
		this.variability = VariabilityUtils.getVariability(xarch, documentRootRef);
	}

	public Object[] getElements(Object inputElement) {
		if (variability != null) {
			return XArchADTProxy.unproxy(variability.getChangeSet()).toArray(new ObjRef[0]);
		}
		return new ObjRef[0];
	}

	public Object getParent(Object element) {
		return null;
	}

	public boolean hasChildren(Object element) {
		return false;
	}

	public Object[] getChildren(Object parentElement) {
		return new Object[0];
	}

	public void handleXArchADTModelEvent(XArchADTModelEvent evt) {
		switch (evt.getEventType()) {
		case ADD:
			if (evt.getNewValuePath().equals("xADL/variability")) {
				inputChanged(viewer, this.documentRootRef, this.documentRootRef);
			}
			if (evt.getNewValuePath().equals("xADL/variability/changeSet")) {
				refresh();
			}
			break;
		case REMOVE:
			if (evt.getOldValuePath().equals("xADL/variability/changeSet")) {
				refresh();
			}
			break;
		default:
			break;
		}
	}

	boolean needsRefresh = false;

	private void refresh() {
		needsRefresh = true;
		SWTWidgetUtils.async(viewer, new Runnable() {

			public void run() {
				if (needsRefresh) {
					needsRefresh = false;
					viewer.refresh();
				}
			}
		});

	}

}
