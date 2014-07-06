package org.archstudio.bna.logics.editing;

import java.awt.geom.Point2D;
import java.util.List;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.facets.IHasMutableEndpoints;
import org.archstudio.bna.facets.IHasMutableMidpoints;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.facets.IHasReferencePoint;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.utils.UserEditableUtils;
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
	 * Records a set of operations that will restore things to their current locations. Used for undo/redo.
	 */
	public static Runnable takeSnapshotOfLocations(final IBNAModel model, Iterable<? extends IThing> things) {
		final List<Runnable> runnables = Lists.newArrayList();
		for (IThing t : things) {
			final Object tID = t.getID();
			if (t instanceof IHasReferencePoint
					&& UserEditableUtils.isEditableForAnyQualities(t, IHasMutableReferencePoint.USER_MAY_MOVE)) {
				final Point p = ((IHasReferencePoint) t).getReferencePoint();
				runnables.add(new Runnable() {

					@Override
					public void run() {
						IThing t = model.getThing(tID);
						if (t != null) {
							((IHasReferencePoint) t).setReferencePoint(p);
						}
					}
				});
				continue;
			}
			if (t instanceof IHasMutableBoundingBox
					&& UserEditableUtils.isEditableForAnyQualities(t, IHasMutableReferencePoint.USER_MAY_MOVE,
							IHasMutableSize.USER_MAY_RESIZE)) {
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
			if (t instanceof IHasMutableEndpoints
					&& UserEditableUtils.isEditableForAllQualities(t, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_1)) {
				final Point2D ep1 = ((IHasMutableEndpoints) t).getEndpoint1();
				runnables.add(new Runnable() {

					@Override
					public void run() {
						IThing t = model.getThing(tID);
						if (t != null) {
							((IHasMutableEndpoints) t).setEndpoint1(ep1);
						}
					}
				});
				continue;
			}
			if (t instanceof IHasMutableEndpoints
					&& UserEditableUtils.isEditableForAllQualities(t, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_2)) {
				final Point2D ep2 = ((IHasMutableEndpoints) t).getEndpoint2();
				runnables.add(new Runnable() {

					@Override
					public void run() {
						IThing t = model.getThing(tID);
						if (t != null) {
							((IHasMutableEndpoints) t).setEndpoint2(ep2);
						}
					}
				});
				continue;
			}
			if (t instanceof IHasMutableMidpoints
					&& UserEditableUtils.isEditableForAllQualities(t, IHasMutableMidpoints.USER_MAY_MOVE_MIDPOINTS)) {
				final List<Point2D> p = ((IHasMutableMidpoints) t).getMidpoints();
				runnables.add(new Runnable() {

					@Override
					public void run() {
						IThing t = model.getThing(tID);
						if (t != null) {
							((IHasMutableMidpoints) t).setMidpoints(p);
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
