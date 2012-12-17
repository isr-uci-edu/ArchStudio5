package org.archstudio.bna.logics.editing;

import java.util.List;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.facets.IHasMutablePoints;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Lists;

public class BNAOperations {

	public static void runnable(String label, final Runnable undoRunnable, final Runnable redoRunnable, boolean execute) {
		if (execute) {
			redoRunnable.run();
		}
		if (!Platform.isRunning() || !PlatformUI.isWorkbenchRunning()) {
			return;
		}

		IUndoContext undoContext = PlatformUI.getWorkbench().getOperationSupport().getUndoContext();
		IOperationHistory operationHistory = PlatformUI.getWorkbench().getOperationSupport().getOperationHistory();
		AbstractOperation bnaOperation = new AbstractOperation(label) {
			@Override
			public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				return Status.OK_STATUS;
			}

			@Override
			public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				undoRunnable.run();
				return Status.OK_STATUS;
			}

			@Override
			public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				redoRunnable.run();
				return Status.OK_STATUS;
			}
		};
		bnaOperation.addContext(undoContext);
		operationHistory.add(bnaOperation);
	}

	public static <V> void set(String label, final IBNAModel model, IThing t, final IThingKey<V> key, final V newValue) {

		final V oldValue = t.get(key);
		t.set(key, newValue);

		final Object tID = t.getID();

		IUndoContext undoContext = PlatformUI.getWorkbench().getOperationSupport().getUndoContext();
		IOperationHistory operationHistory = PlatformUI.getWorkbench().getOperationSupport().getOperationHistory();
		AbstractOperation bnaOperation = new AbstractOperation(label) {
			@Override
			public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				return Status.OK_STATUS;
			}

			@Override
			public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				IThing t = model.getThing(tID);
				if (t != null) {
					t.set(key, oldValue);
				}
				return Status.OK_STATUS;
			}

			@Override
			public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				IThing t = model.getThing(tID);
				if (t != null) {
					t.set(key, newValue);
				}
				return Status.OK_STATUS;
			}
		};
		bnaOperation.addContext(undoContext);
		operationHistory.add(bnaOperation);
	}

	/**
	 * Records a set of operations that will restore things to their current
	 * locations. Used for undo/redo.
	 */
	public static Runnable takeSnapshotOfLocations(final IBNAModel model, Iterable<? extends IThing> things) {
		final List<Runnable> runnables = Lists.newArrayList();
		for (IThing t : things) {
			final Object tID = t.getID();
			if (t instanceof IHasMutableBoundingBox) {
				final Rectangle r = ((IHasMutableBoundingBox) t).getBoundingBox();
				runnables.add(new Runnable() {
					@Override
					public void run() {
						IThing t = model.getThing(tID);
						if (t != null) {
							((IHasMutableBoundingBox) t).setBoundingBox(r);
						}
					}
				});
				continue;
			}
			if (t instanceof IHasMutablePoints) {
				final List<Point> p = ((IHasMutablePoints) t).getPoints();
				runnables.add(new Runnable() {
					@Override
					public void run() {
						IThing t = model.getThing(tID);
						if (t != null) {
							((IHasMutablePoints) t).setPoints(p);
						}
					}
				});
				continue;
			}
			if (t instanceof IHasMutableReferencePoint) {
				final Point p = ((IHasMutableReferencePoint) t).getReferencePoint();
				runnables.add(new Runnable() {
					@Override
					public void run() {
						IThing t = model.getThing(tID);
						if (t != null) {
							((IHasMutableReferencePoint) t).setReferencePoint(p);
						}
					}
				});
				continue;
			}
		}
		return new Runnable() {
			@Override
			public void run() {
				for (Runnable r : runnables) {
					r.run();
				}
			}
		};
	}
}
