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

	@Override
	public List<? extends Object> getChildren(Object parentElement, List<? extends Object> childrenFromPreviousProvider) {
		if (parentElement instanceof IWorkbenchSite) {
			ObjRef xADLRef = (ObjRef) xarch.get(documentRootRef, "xADL");
			if (xADLRef != null) {
				return Collections.singletonList(xADLRef);
			}
		}
		return childrenFromPreviousProvider;
	}

	@Override
	public Object getParent(Object element, Object parentFromPreviousProvider) {
		return parentFromPreviousProvider;
	}

	@Override
	public boolean hasChildren(Object element, boolean hasChildrenFromPreviousProvider) {
		if (element instanceof IWorkbenchSite) {
			return true;
		}
		return hasChildrenFromPreviousProvider;
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public void dispose() {
	}
}
