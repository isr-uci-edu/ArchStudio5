package org.archstudio.archipelago.core;

import java.util.Collections;
import java.util.List;

import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IWorkbenchSite;

public class RootContentProvider implements IArchipelagoTreeContentProvider {

	protected ArchipelagoServices AS = null;
	protected ObjRef documentRootRef = null;

	public RootContentProvider(ArchipelagoServices services, ObjRef documentRootRef) {
		this.AS = services;
		this.documentRootRef = documentRootRef;
	}

	public List<? extends Object> getChildren(Object parentElement, List<? extends Object> childrenFromPreviousProvider) {
		if (parentElement instanceof IWorkbenchSite) {
			ObjRef xADLRef = (ObjRef) AS.xarch.get(documentRootRef, "xADL");
			if (xADLRef != null) {
				return Collections.singletonList(xADLRef);
			}
		}
		return childrenFromPreviousProvider;
	}

	public Object getParent(Object element, Object parentFromPreviousProvider) {
		return parentFromPreviousProvider;
	}

	public boolean hasChildren(Object element, boolean hasChildrenFromPreviousProvider) {
		if (element instanceof IWorkbenchSite) {
			return true;
		}
		return hasChildrenFromPreviousProvider;
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public void dispose() {
	}
}
