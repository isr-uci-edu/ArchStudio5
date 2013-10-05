package org.archstudio.bna.logics.editing;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.events.DragMoveEvent;
import org.archstudio.bna.logics.events.DragMoveEventsLogic;
import org.archstudio.bna.logics.events.IDragMoveListener;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.things.shapes.ReshapeHandleThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.Point;

import com.google.common.collect.Maps;

public abstract class AbstractReshapeLogic<R extends IThing, D> extends AbstractThingLogic implements
		IBNAModelListener, IDragMoveListener {

	protected final ThingValueTrackingLogic valueLogic;
	protected final Class<R> reshapingThingClass;
	protected final Map<ReshapeHandleThing, D> reshapeHandles = Maps.newHashMap();

	protected R reshapingThing = null;
	protected Runnable initialSnapshot = null;
	protected Point initialPosition = null;

	public AbstractReshapeLogic(IBNAWorld world, Class<R> reshapingThingClass) {
		super(world);
		this.reshapingThingClass = reshapingThingClass;
		valueLogic = logics.addThingLogic(ThingValueTrackingLogic.class);
		logics.addThingLogic(DragMoveEventsLogic.class);
	}

	@Override
	synchronized public void dispose() {
		untrack();
		super.dispose();
	}

	protected void track(R selectedThing) {
		if (reshapingThing != null) {
			untrack();
		}
		reshapingThing = selectedThing;
		addHandles();
		updateHandles();
	}

	protected void untrack() {
		if (reshapingThing != null) {
			removeHandles();
			reshapingThing = null;
		}
	}

	abstract protected void addHandles();

	protected ReshapeHandleThing addHandle(ReshapeHandleThing handle, D data) {
		reshapeHandles.put(handle, data);
		UserEditableUtils.addEditableQualities(handle, IRelativeMovable.USER_MAY_MOVE);
		return handle;
	}

	protected void updateHandles() {
		for (Entry<ReshapeHandleThing, D> entry : reshapeHandles.entrySet()) {
			updateHandle(entry.getKey(), entry.getValue());
		}
	}

	abstract protected void updateHandle(ReshapeHandleThing handle, D data);

	protected void removeHandles() {
		for (ReshapeHandleThing handle : reshapeHandles.keySet()) {
			Assemblies.removeRootAndParts(model, handle);
		}
		reshapeHandles.clear();
	}

	@Override
	synchronized public void bnaModelChanged(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_REMOVED:
			if (SystemUtils.nullEquals(evt.getTargetThing(), reshapingThing)) {
				untrack();
			}
			break;
		case THING_CHANGED:
			if (evt.getThingEvent().getPropertyName().equals(IHasSelected.SELECTED_KEY)) {
				checkHandles();
			}
			else if (reshapingThing != null && SystemUtils.nullEquals(evt.getTargetThing(), reshapingThing)) {
				checkHandles();
			}
		default:
			// do nothing
		}
	}

	protected void checkHandles() {
		R selectedThing = null;
		Collection<IThing> selectedThings = valueLogic.getThings(IHasSelected.SELECTED_KEY, Boolean.TRUE);
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
			updateHandles();
		}
	}

	@Override
	synchronized public void dragStarted(DragMoveEvent evt) {
		IThing movedThing = evt.getInitialThing();
		D data = reshapeHandles.get(movedThing);
		if (data != null) {
			initialSnapshot = takeSnapshot();
			initialPosition = ((ReshapeHandleThing) movedThing).getReferencePoint();
			handleMoveStarted((ReshapeHandleThing) movedThing, data, evt);
			checkHandles();
		}
	}

	@Override
	synchronized public void dragMoved(DragMoveEvent evt) {
		IThing movedThing = evt.getInitialThing();
		D data = reshapeHandles.get(movedThing);
		if (data != null) {
			handleMoved((ReshapeHandleThing) movedThing, data, evt);
			checkHandles();
		}
	}

	@Override
	synchronized public void dragFinished(DragMoveEvent evt) {
		IThing movedThing = evt.getInitialThing();
		D data = reshapeHandles.get(movedThing);
		if (data != null) {
			handleMoveFinished((ReshapeHandleThing) movedThing, data, evt);
			checkHandles();

			if (!initialPosition.equals(((ReshapeHandleThing) movedThing).getReferencePoint())) {
				final Runnable undoSnapshot = initialSnapshot;
				final Runnable redoSnapshot = takeSnapshot();
				BNAOperations.runnable("Reshape", undoSnapshot, redoSnapshot, false);
			}

			initialSnapshot = null;
		}
	}

	protected void handleMoveStarted(ReshapeHandleThing handle, D data, DragMoveEvent evt) {
	}

	abstract protected void handleMoved(ReshapeHandleThing handle, D data, DragMoveEvent evt);

	protected void handleMoveFinished(ReshapeHandleThing handle, D data, DragMoveEvent evt) {
	}

	/*
	 * Record an operation that will restore the thing to its current shape. Used for undo/redo.
	 */
	abstract protected Runnable takeSnapshot();

}
