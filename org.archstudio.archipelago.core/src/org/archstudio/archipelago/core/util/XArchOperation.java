package org.archstudio.archipelago.core.util;

import java.io.Serializable;

import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.PlatformUI;

public class XArchOperation {

	public static <V> void set(String label, final IXArchADT xarch, final ObjRef objRef, final String name,
			final Serializable oldValue, final Serializable newValue, boolean execute) {

		if (execute)
			xarch.set(objRef, name, newValue);

		IUndoContext undoContext = PlatformUI.getWorkbench().getOperationSupport().getUndoContext();
		IOperationHistory operationHistory = PlatformUI.getWorkbench().getOperationSupport().getOperationHistory();
		AbstractOperation operation = new AbstractOperation(label) {
			@Override
			public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				return Status.OK_STATUS;
			}

			@Override
			public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				if (oldValue == null)
					xarch.clear(objRef, name);
				else
					xarch.set(objRef, name, oldValue);
				return Status.OK_STATUS;
			}

			@Override
			public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				if (newValue == null)
					xarch.clear(objRef, name);
				else
					xarch.set(objRef, name, newValue);
				return Status.OK_STATUS;
			}
		};
		operation.addContext(undoContext);
		operationHistory.add(operation);
	}

	public static <V> void add(String label, final IXArchADT xarch, final ObjRef objRef, final String name,
			final Serializable value, boolean execute) {

		if (execute)
			xarch.add(objRef, name, value);

		IUndoContext undoContext = PlatformUI.getWorkbench().getOperationSupport().getUndoContext();
		IOperationHistory operationHistory = PlatformUI.getWorkbench().getOperationSupport().getOperationHistory();
		AbstractOperation operation = new AbstractOperation(label) {
			@Override
			public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				return Status.OK_STATUS;
			}

			@Override
			public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				xarch.remove(objRef, name, value);
				return Status.OK_STATUS;
			}

			@Override
			public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				xarch.add(objRef, name, value);
				return Status.OK_STATUS;
			}
		};
		operation.addContext(undoContext);
		operationHistory.add(operation);
	}

	public static <V> void remove(String label, final IXArchADT xarch, final ObjRef objRef, final String name,
			final Serializable value, boolean execute) {

		if (execute)
			xarch.remove(objRef, name, value);

		IUndoContext undoContext = PlatformUI.getWorkbench().getOperationSupport().getUndoContext();
		IOperationHistory operationHistory = PlatformUI.getWorkbench().getOperationSupport().getOperationHistory();
		AbstractOperation operation = new AbstractOperation(label) {
			@Override
			public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				return Status.OK_STATUS;
			}

			@Override
			public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				xarch.add(objRef, name, value);
				return Status.OK_STATUS;
			}

			@Override
			public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				xarch.remove(objRef, name, value);
				return Status.OK_STATUS;
			}
		};
		operation.addContext(undoContext);
		operationHistory.add(operation);
	}

}
