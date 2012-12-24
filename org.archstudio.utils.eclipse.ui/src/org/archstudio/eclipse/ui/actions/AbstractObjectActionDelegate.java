package org.archstudio.eclipse.ui.actions;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkingSet;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public abstract class AbstractObjectActionDelegate implements IObjectActionDelegate {

	protected IAction action;
	protected IWorkbenchPart targetPart;
	protected ISelection selection;

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.action = action;
		this.targetPart = targetPart;
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.action = action;
		this.selection = selection;
	}

	@SuppressWarnings("unchecked")
	protected static <A> A adapt(Object input, Class<A> adapterClass) {
		if (input instanceof IAdaptable) {
			return (A) ((IAdaptable) input).getAdapter(adapterClass);
		}
		return null;
	}

	protected static Iterable<Object> getSelectedElements(final ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			return new Iterable<Object>() {
				@Override
				@SuppressWarnings("unchecked")
				public Iterator<Object> iterator() {
					return ((IStructuredSelection) selection).iterator();
				}
			};
		}
		return Collections.emptyList();
	}

	protected static Iterable<IProject> getProjects(final ISelection selection) {
		List<IProject> projects = Lists.newArrayList();
		for (Object element : getSelectedElements(selection)) {
			if (element instanceof IProject) {
				projects.add((IProject) element);
			}
			else if (element instanceof IWorkingSet) {
				for (IAdaptable adaptable : ((IWorkingSet) element).getElements()) {
					projects.add(adapt(adaptable, IProject.class));
				}
			}
			else {
				projects.add(adapt(element, IProject.class));
			}
		}
		return Iterables.filter(projects, Predicates.notNull());
	}
}
