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
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Lists;

public class BNAOperation<V> extends AbstractOperation {

	public static void add(String label, final Runnable undoRunnable, final Runnable redoRunnable, boolean execute) {
		if (execute)
			redoRunnable.run();

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

	public static <V> void add(String label, final IBNAModel model, IThing t, final IThingKey<V> key, final V oldValue,
			final V newValue, boolean execute) {
		if (execute)
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
				if (t != null)
					t.set(key, oldValue);
				return Status.OK_STATUS;
			}

			@Override
			public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				IThing t = model.getThing(tID);
				if (t != null)
					t.set(key, newValue);
				return Status.OK_STATUS;
			}
		};
		bnaOperation.addContext(undoContext);
		operationHistory.add(bnaOperation);
	}

	public static <V> void execute(String label, IBNAModel model, IThing t, IThingKey<V> key, V newValue) {
		add(label, model, t, key, t.get(key), newValue, true);
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
					public void run() {
						IThing t = model.getThing(tID);
						if (t != null)
							((IHasMutableBoundingBox) t).setBoundingBox(r);
					}
				});
				continue;
			}
			if (t instanceof IHasMutablePoints) {
				final List<Point> p = ((IHasMutablePoints) t).getPoints();
				runnables.add(new Runnable() {
					public void run() {
						IThing t = model.getThing(tID);
						if (t != null)
							((IHasMutablePoints) t).setPoints(p);
					}
				});
				continue;
			}
			if (t instanceof IHasMutableReferencePoint) {
				final Point p = ((IHasMutableReferencePoint) t).getReferencePoint();
				runnables.add(new Runnable() {
					public void run() {
						IThing t = model.getThing(tID);
						if (t != null)
							((IHasMutableReferencePoint) t).setReferencePoint(p);
					}
				});
				continue;
			}
		}
		return new Runnable() {
			public void run() {
				for (Runnable r : runnables)
					r.run();
			}
		};
	}

	protected final IThing t;
	protected final IThingKey<V> key;
	protected final V oldValue;
	protected final V newValue;

	public BNAOperation(String label, IThing t, IThingKey<V> key, V newValue) {
		super(label);
		this.t = t;
		this.key = key;
		this.oldValue = t.get(key);
		this.newValue = newValue;
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		t.set(key, newValue);
		return Status.OK_STATUS;
	}

	@Override
	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return execute(monitor, info);
	}

	@Override
	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		t.set(key, oldValue);
		return Status.OK_STATUS;
	}

}
