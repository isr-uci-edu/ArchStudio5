package org.archstudio.archipelago.core;

import java.util.Collections;
import java.util.List;

import org.archstudio.myx.fw.Services;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IWorkbenchSite;

public class RootContentProvider implements IArchipelagoTreeContentProvider {

	protected Services AS = null;
	protected ObjRef documentRootRef = null;
	protected final IXArchADT xarch;

	public RootContentProvider(Services AS, ObjRef documentRootRef) {
		this.AS = AS;
		this.documentRootRef = documentRootRef;
		this.xarch = AS.get(IXArchADT.class);
	}

	public List<? extends Object> getChildren(Object parentElement, List<? extends Object> childrenFromPreviousProvider) {
		if (parentElement instanceof IWorkbenchSite) {
			ObjRef xADLRef = (ObjRef) xarch.get(documentRootRef, "xADL");
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
