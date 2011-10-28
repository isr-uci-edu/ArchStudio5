package org.archstudio.bna.logics.editing;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.Map;
import java.util.Map.Entry;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
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

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

public abstract class AbstractReshapeLogic<R extends IThing, D> extends AbstractThingLogic implements
		IBNASynchronousModelListener, IDragMoveListener {

	protected R reshapingThing = null;
	protected final Class<R> reshapingThingClass;
	protected final Map<ReshapeHandleGlassThing, D> reshapeHandles = Maps.newHashMap();

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
		handle.setTargetThingID(reshapingThing.getID());
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
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChangedSync(BNAModelEvent<ET, EK, EV> evt) {
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
		}
	}

	protected synchronized void checkHandles() {
		R selectedThing = null;
		IBNAModel model = getBNAModel();
		if (model != null) {
			Iterable<Object> selectedThingIDs = valuesLogic.getThingIDs(IHasSelected.SELECTED_KEY, Boolean.TRUE);
			if (Iterables.size(selectedThingIDs) == 1) {
				selectedThing = firstOrNull(model.getThingsByID(selectedThingIDs), reshapingThingClass);
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
		}
	}

	protected void handleMoveStarted(ReshapeHandleGlassThing handle, D data, DragMoveEvent evt) {
	}

	abstract protected void handleMoved(ReshapeHandleGlassThing handle, D data, DragMoveEvent evt);

	protected void handleMoveFinished(ReshapeHandleGlassThing handle, D data, DragMoveEvent evt) {
	}

}
