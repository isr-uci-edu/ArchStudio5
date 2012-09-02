package org.archstudio.bna.logics.editing;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.Map;
import java.util.Map.Entry;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.events.DragMoveEvent;
import org.archstudio.bna.logics.events.DragMoveEventsLogic;
import org.archstudio.bna.logics.events.IDragMoveListener;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.things.glass.ReshapeHandleGlassThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.swt.graphics.Point;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

public abstract class AbstractReshapeLogic<R extends IThing, D> extends AbstractThingLogic implements
		IBNAModelListener, IDragMoveListener {

	protected R reshapingThing = null;
	protected final Class<R> reshapingThingClass;
	protected final Map<ReshapeHandleGlassThing, D> reshapeHandles = Maps.newHashMap();
	protected Runnable initialSnapshot = null;
	protected Point initialPosition = null;

	protected ThingValueTrackingLogic valuesLogic = null;

	public AbstractReshapeLogic(Class<R> reshapingThingClass) {
		this.reshapingThingClass = reshapingThingClass;
	}

	@Override
	protected void init() {
		super.init();
		valuesLogic = addThingLogic(ThingValueTrackingLogic.class);
		// this logic listens to events from the following
		addThingLogic(DragMoveEventsLogic.class);
	}

	@Override
	protected void destroy() {
		untrack();
		super.destroy();
	}

	protected void track(R selectedThing) {
		if (reshapingThing != null) {
			untrack();
		}
		reshapingThing = selectedThing;
		addHandles();
		updateHandles();
	}

	abstract protected void addHandles();

	protected ReshapeHandleGlassThing addHandle(ReshapeHandleGlassThing handle, D data) {
		reshapeHandles.put(handle, data);
		UserEditableUtils.addEditableQualities(handle, IRelativeMovable.USER_MAY_MOVE);
		return handle;
	}

	protected void updateHandles() {
		for (Entry<ReshapeHandleGlassThing, D> entry : reshapeHandles.entrySet()) {
			updateHandle(entry.getKey(), entry.getValue());
		}
	}

	abstract protected void updateHandle(ReshapeHandleGlassThing handle, D data);

	protected void untrack() {
		if (reshapingThing != null) {
			removeHandles();
			reshapingThing = null;
		}
	}

	protected synchronized void removeHandles() {
		for (ReshapeHandleGlassThing handle : reshapeHandles.keySet()) {
			Assemblies.removeRootAndParts(getBNAModel(), handle);
		}
		reshapeHandles.clear();
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_REMOVING:
			if (evt.getTargetThing().equals(reshapingThing)) {
				untrack();
			}
			break;
		case THING_CHANGED:
			if (evt.getThingEvent().getPropertyName().equals(IHasSelected.SELECTED_KEY)) {
				checkHandles();
			}
			else if (reshapingThing != null && evt.getThingEvent().getTargetThing().equals(reshapingThing)) {
				checkHandles();
			}
		default:
			// do nothing
		}
	}

	protected synchronized void checkHandles() {
		R selectedThing = null;
		IBNAModel model = getBNAModel();
		if (model != null) {
			Iterable<Object> selectedThingIDs = valuesLogic.getThingIDs(IHasSelected.SELECTED_KEY, Boolean.TRUE);
			if (Iterables.size(selectedThingIDs) == 1) {
				selectedThing = firstOrNull(model.getThingsByID(selectedThingIDs), reshapingThingClass);
				//if(selectedThing != null && !UserEditableUtils.isEditableForAllQualities(selectedThing, IHasMutableSize.USER_MAY_RESIZE)){
				//	selectedThing = null;
				//}
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
	}

	@Override
	public void dragStarted(DragMoveEvent evt) {
		IThing movedThing = evt.getInitialThing();
		D data = reshapeHandles.get(movedThing);
		if (data != null) {
			initialSnapshot = takeSnapshot();
			initialPosition = ((ReshapeHandleGlassThing) movedThing).getReferencePoint();

			handleMoveStarted((ReshapeHandleGlassThing) movedThing, data, evt);
			checkHandles();
		}
	}

	@Override
	public void dragMoved(DragMoveEvent evt) {
		IThing movedThing = evt.getInitialThing();
		D data = reshapeHandles.get(movedThing);
		if (data != null) {
			handleMoved((ReshapeHandleGlassThing) movedThing, data, evt);
			checkHandles();
		}
	}

	@Override
	public void dragFinished(DragMoveEvent evt) {
		IThing movedThing = evt.getInitialThing();
		D data = reshapeHandles.get(movedThing);
		if (data != null) {
			handleMoveFinished((ReshapeHandleGlassThing) movedThing, data, evt);
			checkHandles();

			if (!initialPosition.equals(((ReshapeHandleGlassThing) movedThing).getReferencePoint())) {
				final Runnable undoSnapshot = initialSnapshot;
				final Runnable redoSnapshot = takeSnapshot();
				BNAOperations.runnable("Reshape", undoSnapshot, redoSnapshot, false);
			}

			initialSnapshot = null;
		}
	}

	protected void handleMoveStarted(ReshapeHandleGlassThing handle, D data, DragMoveEvent evt) {
	}

	abstract protected void handleMoved(ReshapeHandleGlassThing handle, D data, DragMoveEvent evt);

	protected void handleMoveFinished(ReshapeHandleGlassThing handle, D data, DragMoveEvent evt) {
	}

	/*
	 * Record an operation that will restore the thing to its current shape.
	 * Used for undo/redo.
	 */
	abstract protected Runnable takeSnapshot();

}
