package org.archstudio.eclipsedev.core.action;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

public abstract class AbstractObjectActionDelegate implements IObjectActionDelegate {

	protected IAction action;
	protected IWorkbenchPart targetPart;
	protected ISelection selection;

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.action = action;
		this.targetPart = targetPart;
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.action = action;
		this.selection = selection;
	}

	protected static Iterable<Object> getSelectedElements(final ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			return new Iterable<Object>() {
				@SuppressWarnings("unchecked")
				@Override
				public Iterator<Object> iterator() {
					return ((IStructuredSelection) selection).iterator();
				}
			};
		}
		return Collections.emptyList();
	}

	protected static Iterable<IProject> getProjects(final ISelection selection) {
		return Iterables.filter(Iterables.<Object, IProject> transform(getSelectedElements(selection),
				new Function<Object, IProject>() {
					@Override
					public IProject apply(Object input) {
						IProject project = null;
						if (input instanceof IProject) {
							project = (IProject) input;
						}
						else if (input instanceof IAdaptable) {
							project = (IProject) ((IAdaptable) input).getAdapter(IProject.class);
						}
						return project;
					}
				}), Predicates.notNull());
	}
}
