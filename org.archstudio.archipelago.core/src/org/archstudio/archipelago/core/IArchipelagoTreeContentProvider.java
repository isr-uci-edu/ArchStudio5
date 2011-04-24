package org.archstudio.archipelago.core;

import java.util.List;

import org.eclipse.jface.viewers.Viewer;

public interface IArchipelagoTreeContentProvider{
	public Object getParent(Object element, Object parentFromPreviousProvider);
	public List<? extends Object> getChildren(Object parentElement, List<? extends Object> childrenFromPreviousProvider);
	public boolean hasChildren(Object element, boolean hasChildrenFromPreviousProvider);
	
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput);
	public void dispose();
}
