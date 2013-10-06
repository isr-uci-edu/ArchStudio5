package org.archstudio.bna.logics.events;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.coordinating.IInternalBNAModelListener;
import org.archstudio.bna.logics.tracking.ThingTypeTrackingLogic;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class WorldThingInternalEventsLogic extends AbstractThingLogic implements IBNAModelListener {

	static protected Set<IHasWorld> worldsBeingNotified = Sets.newHashSet();

	protected final ThingTypeTrackingLogic typeLogic;
	protected Map<IHasWorld, InternalModelListener> thingToListenerMap = new HashMap<IHasWorld, InternalModelListener>();

	public static final IThingKey<Integer> INTERNAL_MODEL_CHANGE_TICKER = ThingKey.create("InternalChangeTicker");

	public WorldThingInternalEventsLogic(IBNAWorld world) {
		super(world);
		typeLogic = logics.addThingLogic(ThingTypeTrackingLogic.class);
		for (IHasWorld vt : typeLogic.getThings(IHasWorld.class)) {
			addListener(vt);
		}
	}

	class InternalModelListener implements IBNAModelListener {

		final IHasWorld viewThing;
		final IBNAModel viewThingModel;

		public InternalModelListener(IHasWorld viewThing, IBNAModel viewThingModel) {
			this.viewThing = checkNotNull(viewThing);
			this.viewThingModel = checkNotNull(viewThingModel);
			viewThing.set(INTERNAL_MODEL_CHANGE_TICKER, 0);
		}

		boolean needsTick = false;

		@Override
		public void bnaModelChanged(BNAModelEvent evt) {
			// prevent infinite loops
			if (!worldsBeingNotified.add(viewThing)) {
				return;
			}

			try {
				fireInternalBNAModelEvent(viewThing, evt);
				switch (evt.getEventType()) {
				case BULK_CHANGE_BEGIN:
				case BULK_CHANGE_END:
					break;
				default:
					needsTick = true;
				}
				if (!evt.isInBulkChange() && needsTick) {
					needsTick = false;
					viewThing.set(INTERNAL_MODEL_CHANGE_TICKER, viewThing.get(INTERNAL_MODEL_CHANGE_TICKER) + 1);
				}
			}
			finally {
				worldsBeingNotified.remove(viewThing);
			}
		}
	}

	@Override
	synchronized public void dispose() {
		for (IHasWorld vt : Lists.newArrayList(thingToListenerMap.keySet())) {
			removeListener(vt);
		}
	}

	@Override
	synchronized public void bnaModelChanged(BNAModelEvent evt) {
		if (evt.getTargetThing() != null && evt.getTargetThing() instanceof IHasWorld) {
			IHasWorld vt = (IHasWorld) evt.getTargetThing();
			switch (evt.getEventType()) {
			case THING_ADDED:
				addListener(vt);
				break;
			case THING_REMOVED:
				removeListener(vt);
				break;
			case THING_CHANGED:
				ThingEvent te = evt.getThingEvent();
				if (te != null && te.getPropertyName().equals(IHasWorld.WORLD_KEY)) {
					removeListener(vt);
					addListener(vt);
				}
				break;
			default:
				// do nothing
			}
		}
	}

	protected void addListener(IHasWorld vt) {
		IBNAWorld world = vt.getWorld();
		if (world != null) {
			IBNAModel model = world.getBNAModel();
			InternalModelListener l = new InternalModelListener(vt, model);
			thingToListenerMap.put(vt, l);
			model.addBNAModelListener(l);
		}
	}

	protected void removeListener(IHasWorld vt) {
		InternalModelListener l = thingToListenerMap.remove(vt);
		if (l != null) {
			l.viewThingModel.removeBNAModelListener(l);
		}
	}

	protected void fireInternalBNAModelEvent(IHasWorld src, BNAModelEvent evt) {
		for (IInternalBNAModelListener l : logics.getThingLogics(IInternalBNAModelListener.class)) {
			l.internalBNAModelChanged(src, evt);
		}
	}
}
