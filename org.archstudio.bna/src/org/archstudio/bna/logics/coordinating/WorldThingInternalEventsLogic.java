package org.archstudio.bna.logics.coordinating;

import java.util.HashMap;
import java.util.Map;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingTypeTrackingLogic;

import com.google.common.collect.Lists;

public class WorldThingInternalEventsLogic extends AbstractThingLogic implements IBNASynchronousModelListener {

	protected ThingTypeTrackingLogic typeLogic = null;
	protected Map<IHasWorld, InternalModelListener> thingToListenerMap = new HashMap<IHasWorld, InternalModelListener>();

	public static final IThingKey<Integer> INTERNAL_MODEL_CHANGE_TICKER = ThingKey.create("InternalChangeTicker");

	public WorldThingInternalEventsLogic() {
	}

	class InternalModelListener implements IBNAModelListener, IBNASynchronousModelListener {

		private final IHasWorld viewThing;

		public InternalModelListener(IHasWorld viewThing) {
			this.viewThing = viewThing;
			viewThing.set(INTERNAL_MODEL_CHANGE_TICKER, 0);
		}

		@Override
		public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChanged(BNAModelEvent<ET, EK, EV> evt) {
			fireInternalBNAModelEvent(viewThing, evt);
		}

		boolean needsTick = false;

		@Override
		public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChangedSync(BNAModelEvent<ET, EK, EV> evt) {
			fireInternalBNAModelEventSync(viewThing, evt);
			switch (evt.getEventType()) {
			case BULK_CHANGE_BEGIN:
			case BULK_CHANGE_END:
				break;
			default:
				needsTick = true;
			}
			if (!evt.isInBulkChange() && needsTick) {
				needsTick = false;
				viewThing.synchronizedUpdate(new Runnable() {
					@Override
					public void run() {
						viewThing.set(INTERNAL_MODEL_CHANGE_TICKER, viewThing.get(INTERNAL_MODEL_CHANGE_TICKER) + 1);
					}
				});
			}
		}
	}

	@Override
	protected void init() {
		super.init();
		typeLogic = addThingLogic(ThingTypeTrackingLogic.class);
		for (IHasWorld vt : typeLogic.getThings(getBNAModel(), IHasWorld.class)) {
			addListener(vt);
		}
	}

	@Override
	protected void destroy() {
		for (IHasWorld vt : Lists.newArrayList(thingToListenerMap.keySet())) {
			removeListener(vt);
		}
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChangedSync(BNAModelEvent<ET, EK, EV> evt) {
		if (evt.getTargetThing() != null && evt.getTargetThing() instanceof IHasWorld) {
			IHasWorld vt = (IHasWorld) evt.getTargetThing();
			switch (evt.getEventType()) {
			case THING_ADDED:
				addListener(vt);
				break;
			case THING_REMOVING:
				removeListener(vt);
				break;
			case THING_CHANGED:
				ThingEvent<ET, EK, EV> te = evt.getThingEvent();
				if (te != null && te.getPropertyName().equals(IHasWorld.WORLD_KEY)) {
					removeListener(vt);
					addListener(vt);
				}
				break;
			}
		}
	}

	protected void addListener(IHasWorld vt) {
		IBNAWorld world = vt.getWorld();
		if (world != null) {
			InternalModelListener l = new InternalModelListener(vt);
			thingToListenerMap.put(vt, l);
			world.getBNAModel().addBNAModelListener(l);
			world.getBNAModel().addSynchronousBNAModelListener(l);
		}
	}

	protected void removeListener(IHasWorld vt) {
		InternalModelListener l = thingToListenerMap.remove(vt);
		IBNAWorld world = vt.getWorld();
		if (world != null) {
			world.getBNAModel().removeSynchronousBNAModelListener(l);
			world.getBNAModel().removeBNAModelListener(l);
		}
	}

	protected <ET extends IThing, EK extends IThingKey<EV>, EV> void fireInternalBNAModelEvent(IHasWorld src,
			BNAModelEvent<ET, EK, EV> evt) {
		for (IInternalBNAModelListener l : getBNAWorld().getThingLogicManager().getThingLogics(
				IInternalBNAModelListener.class)) {
			l.internalBNAModelChanged(src, evt);
		}
	}

	protected <ET extends IThing, EK extends IThingKey<EV>, EV> void fireInternalBNAModelEventSync(IHasWorld src,
			BNAModelEvent<ET, EK, EV> evt) {
		for (IInternalBNAModelListener l : getBNAWorld().getThingLogicManager().getThingLogics(
				IInternalBNAModelListener.class)) {
			l.internalBNAModelChangedSync(src, evt);
		}
	}
}
