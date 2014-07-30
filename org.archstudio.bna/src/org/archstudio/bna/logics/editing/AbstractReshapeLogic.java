package org.archstudio.bna.logics.editing;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.Collection;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.events.DragMoveEvent;
import org.archstudio.bna.logics.events.DragMoveEventsLogic;
import org.archstudio.bna.logics.events.IDragMoveListener;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.things.shapes.ReshapeHandleThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.sysutils.Finally;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.Point;

import com.google.common.collect.Lists;

public abstract class AbstractReshapeLogic<R extends IThing, D> extends AbstractThingLogic implements
		IBNAModelListener, IDragMoveListener {

	private final IThingKey<D> HANDLE_DATA_KEY = ThingKey.create(AbstractReshapeLogic.class);

	protected final ThingValueTrackingLogic valueLogic;
	protected final Class<R> reshapingThingClass;

	private R reshapingThing = null;
	private Runnable initialSnapshot = null;
	private Point initialPosition = null;
	private final Collection<ReshapeHandleThing> handles = Lists.newArrayList();

	public AbstractReshapeLogic(IBNAWorld world, Class<R> reshapingThingClass) {
		super(world);
		this.reshapingThingClass = reshapingThingClass;
		valueLogic = logics.addThingLogic(ThingValueTrackingLogic.class);
		logics.addThingLogic(DragMoveEventsLogic.class);
	}

	@Override
	public void dispose() {
		BNAUtils.checkLock();

		untrack();
		super.dispose();
	}

	private void track(R reshapingThing) {
		try (Finally bulkChange = model.beginBulkChange()) {
			if (this.reshapingThing != null) {
				untrack();
			}
			this.reshapingThing = reshapingThing;
			addHandles(this.reshapingThing);
			updateHandles(this.reshapingThing);
		}
	}

	private void untrack() {
		try (Finally bulkChange = model.beginBulkChange()) {
			if (reshapingThing != null) {
				removeHandles(reshapingThing);
				reshapingThing = null;
			}
		}
	}

	abstract protected void addHandles(R reshapingThing);

	protected ReshapeHandleThing addHandle(R reshapingThing, ReshapeHandleThing handle, D data) {
		handle.set(HANDLE_DATA_KEY, data);
		handles.add(handle);
		UserEditableUtils.addEditableQualities(handle, IHasMutableReferencePoint.USER_MAY_MOVE);
		return handle;
	}

	private void updateHandles(R reshapingThing) {
		for (ReshapeHandleThing handle : handles) {
			updateHandle(reshapingThing, handle, handle.get(HANDLE_DATA_KEY));
		}
	}

	abstract protected void updateHandle(R reshapingThing, ReshapeHandleThing handle, D data);

	protected void removeHandles(R reshapingThing) {
		try (Finally bulkChange = model.beginBulkChange()) {
			for (ReshapeHandleThing handle : handles) {
				Assemblies.removeRootAndParts(model, handle);
			}
			handles.clear();
		}
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		BNAUtils.checkLock();

		switch (evt.getEventType()) {
		case THING_REMOVED:
			if (SystemUtils.nullEquals(evt.getTargetThing(), reshapingThing)) {
				untrack();
			}
			break;
		case THING_CHANGED:
			if (evt.getThingEvent().getPropertyName().equals(IHasSelected.SELECTED_KEY)) {
				checkHandles(reshapingThing);
			}
			else if (SystemUtils.nullEquals(evt.getTargetThing(), reshapingThing)) {
				checkHandles(reshapingThing);
			}
		default:
			// do nothing
		}
	}

	protected void checkHandles(R reshapingThing) {
		try (Finally bulkChange = model.beginBulkChange()) {
			R selectedThing = null;
			Collection<IThing> selectedThings = valueLogic.getThings(IHasSelected.SELECTED_KEY, true);
			if (selectedThings.size() == 1) {
				selectedThing = firstOrNull(selectedThings, reshapingThingClass);
			}
			if (selectedThing == null || selectedThing != reshapingThing) {
				untrack();
			}
			if (selectedThing != null && selectedThing != reshapingThing) {
				track(selectedThing);
			}
			if (reshapingThing != null) {
				updateHandles(reshapingThing);
			}
		}
	}

	@Override
	public void dragStarted(DragMoveEvent evt) {
		BNAUtils.checkLock();

		IThing movedThing = evt.getInitialThing();
		if (handles.contains(movedThing)) {
			D data = movedThing.get(HANDLE_DATA_KEY);
			initialSnapshot = takeSnapshot(reshapingThing);
			initialPosition = ((ReshapeHandleThing) movedThing).getReferencePoint();
			handleMoveStarted(reshapingThing, (ReshapeHandleThing) movedThing, data, evt);
			checkHandles(reshapingThing);
		}
	}

	@Override
	public void dragMoved(DragMoveEvent evt) {
		BNAUtils.checkLock();

		IThing movedThing = evt.getInitialThing();
		if (handles.contains(movedThing)) {
			D data = movedThing.get(HANDLE_DATA_KEY);
			handleMoved(reshapingThing, (ReshapeHandleThing) movedThing, data, evt);
			checkHandles(reshapingThing);
		}
	}

	@Override
	public void dragFinished(DragMoveEvent evt) {
		BNAUtils.checkLock();

		IThing movedThing = evt.getInitialThing();
		if (handles.contains(movedThing)) {
			D data = movedThing.get(HANDLE_DATA_KEY);
			handleMoveFinished(reshapingThing, (ReshapeHandleThing) movedThing, data, evt);
			checkHandles(reshapingThing);

			if (!initialPosition.equals(((ReshapeHandleThing) movedThing).getReferencePoint())) {
				final Runnable undoSnapshot = initialSnapshot;
				final Runnable redoSnapshot = takeSnapshot(reshapingThing);
				BNAOperations.runnable("Reshape", undoSnapshot, redoSnapshot, false);
			}

			initialSnapshot = null;
			resetHandles();
		}
	}

	protected void resetHandles() {
		if (reshapingThing != null) {
			removeHandles(reshapingThing);
			addHandles(reshapingThing);
		}
	}

	protected void handleMoveStarted(R reshapingThing, ReshapeHandleThing handle, D data, DragMoveEvent evt) {
	}

	abstract protected void handleMoved(R reshapingThing, ReshapeHandleThing handle, D data, DragMoveEvent evt);

	protected void handleMoveFinished(R reshapingThing, ReshapeHandleThing handle, D data, DragMoveEvent evt) {
	}

	/*
	 * Record an operation that will restore the thing to its current shape. Used for undo/redo.
	 */
	abstract protected Runnable takeSnapshot(R reshapingThing);

}
