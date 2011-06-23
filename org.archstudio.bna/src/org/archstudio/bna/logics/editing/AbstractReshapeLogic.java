package org.archstudio.bna.logics.editing;

import java.util.Map;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.coordinating.AbstractPropagateValueLogic;
import org.archstudio.bna.logics.events.DragMoveEvent;
import org.archstudio.bna.logics.events.IDragMoveListener;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.things.glass.ReshapeHandleGlassThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.sysutils.SystemUtils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

public abstract class AbstractReshapeLogic<F extends IThing, D> extends
		AbstractPropagateValueLogic<F, ReshapeHandleGlassThing, D> implements IDragMoveListener {

	protected F reshapingThing = null;
	private final Map<ReshapeHandleGlassThing, D> reshapeHandles = Maps.newHashMap();

	protected DragMovableLogic dml = null;
	protected ThingValueTrackingLogic tvtl = null;

	public AbstractReshapeLogic(Class<F> fromThingClass) {
		super(fromThingClass, ReshapeHandleGlassThing.class);
	}

	@Override
	protected void init() {
		super.init();
		dml = getBNAWorld().getThingLogicManager().addThingLogic(DragMovableLogic.class);
		tvtl = getBNAWorld().getThingLogicManager().addThingLogic(ThingValueTrackingLogic.class);
		checkHandles();
	}

	@Override
	protected void destroy() {
		removeHandles(getBNAModel());
		super.destroy();
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChangedSync(BNAModelEvent<ET, EK, EV> evt) {
		switch (evt.getEventType()) {
		case THING_REMOVING:
			if (evt.getTargetThing().equals(reshapingThing)) {
				removeHandles(evt.getSource());
			}
			break;
		case THING_CHANGED:
			if (evt.getThingEvent().getPropertyName().equals(IHasSelected.SELECTED_KEY)) {
				checkHandles();
			}
		}
		super.bnaModelChangedSync(evt);
	}

	protected synchronized void checkHandles() {
		F selectedThing = null;
		IBNAModel model = getBNAModel();
		if (model != null) {
			Iterable<Object> selectedThingIDs = tvtl.getThingIDs(IHasSelected.SELECTED_KEY, Boolean.TRUE);
			if (Iterables.size(selectedThingIDs) == 1) {
				selectedThing = SystemUtils.castOrNull(model.getThing(selectedThingIDs.iterator().next()),
						fromThingClass);
			}
			if (selectedThing != reshapingThing || selectedThing == null) {
				removeHandles(model);
				reshapingThing = null;
			}
			if (selectedThing != null) {
				reshapingThing = selectedThing;
				addHandles();
			}
		}
	}

	abstract protected void addHandles();

	protected <T extends ReshapeHandleGlassThing> T addHandle(T handle, D data) {
		reshapeHandles.put(handle, data);
		UserEditableUtils.addEditableQualities(handle, IRelativeMovable.USER_MAY_MOVE);
		return handle;
	}

	protected synchronized void removeHandles(IBNAModel model) {
		for (ReshapeHandleGlassThing handle : reshapeHandles.keySet()) {
			Assemblies.removeRootAndParts(model, handle);
		}
		reshapingThing = null;
		reshapeHandles.clear();
	}

	@Override
	public void dragStarted(DragMoveEvent evt) {
	}

	@Override
	public void dragMoved(DragMoveEvent evt) {
		IThing movedThing = evt.getInitialThing();
		D data = reshapeHandles.get(movedThing);
		if (data != null) {
			movedHandle((ReshapeHandleGlassThing) movedThing, data, evt);
		}
	}

	@Override
	public void dragFinished(DragMoveEvent evt) {
	}

	abstract protected void movedHandle(ReshapeHandleGlassThing handle, D data, DragMoveEvent evt);
}
