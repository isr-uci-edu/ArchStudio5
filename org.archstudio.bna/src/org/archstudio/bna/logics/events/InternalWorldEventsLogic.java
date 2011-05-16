package org.archstudio.bna.logics.events;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingLogicManagerListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.ThingLogicManagerEvent;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.BNAUtils;

public class InternalWorldEventsLogic extends AbstractThingLogic implements IThingLogicManagerListener,
		IBNASynchronousModelListener {

	protected final Map<IBNAWorld, Set<IHasWorld>> worldToWorldThings = new HashMap<IBNAWorld, Set<IHasWorld>>();

	public InternalWorldEventsLogic() {
	}

	@Override
	protected void init() {
		super.init();
		bnaWorld.getThingLogicManager().addThingLogicManagerListener(this);
		for (IThing t : getBNAModel().getAllThings()) {
			if (t instanceof IHasWorld) {
				updateWorldThing((IHasWorld) t, null, ((IHasWorld) t).getWorld());
			}
		}
	}

	@Override
	protected void destroy() {
		bnaWorld.getThingLogicManager().removeThingLogicManagerListener(this);
		synchronized (worldToWorldThings) {
			for (IBNAWorld world : worldToWorldThings.keySet().toArray(new IBNAWorld[0])) {
				for (IInternalWorldEventListener l : bnaWorld.getThingLogicManager().getThingLogics(
						IInternalWorldEventListener.class)) {
					l.innerWorldRemoved(world);
				}
				worldToWorldThings.remove(world);
			}
		}
		super.destroy();
	}

	public void bnaModelChangedSync(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_ADDED: {
			IThing t = evt.getTargetThing();
			if (t instanceof IHasWorld) {
				IHasWorld worldThing = (IHasWorld) t;
				updateWorldThing(worldThing, null, worldThing.getWorld());
			}
		}
			break;
		case THING_REMOVING: {
			IThing t = evt.getTargetThing();
			if (t instanceof IHasWorld) {
				IHasWorld worldThing = (IHasWorld) t;
				updateWorldThing(worldThing, worldThing.getWorld(), null);
			}
		}
			break;
		case THING_CHANGED: {
			ThingEvent te = evt.getThingEvent();
			if (IHasWorld.WORLD_KEY.equals(te.getPropertyName())) {
				IThing t = evt.getTargetThing();
				if (t instanceof IHasWorld) {
					updateWorldThing((IHasWorld) t, (IBNAWorld) te.getOldPropertyValue(),
							(IBNAWorld) te.getNewPropertyValue());
				}
			}
		}
			break;
		}
	}

	protected void updateWorldThing(IHasWorld worldThing, IBNAWorld oldWorld, IBNAWorld newWorld) {
		if (!BNAUtils.nulleq(oldWorld, newWorld)) {
			synchronized (worldToWorldThings) {
				if (oldWorld != null) {
					Set<IHasWorld> worldThings = worldToWorldThings.get(oldWorld);
					if (worldThings != null) {
						if (worldThings.remove(worldThing)) {
							if (worldThings.size() == 0) {
								for (IInternalWorldEventListener l : bnaWorld.getThingLogicManager().getThingLogics(
										IInternalWorldEventListener.class)) {
									l.innerWorldRemoved(oldWorld);
								}
								worldToWorldThings.remove(oldWorld);
							}
						}
					}
				}
				if (newWorld != null) {
					Set<IHasWorld> worldThings = worldToWorldThings.get(newWorld);
					if (worldThings == null) {
						worldToWorldThings.put(newWorld, worldThings = new HashSet<IHasWorld>());
					}
					if (worldThings.add(worldThing)) {
						for (IInternalWorldEventListener l : bnaWorld.getThingLogicManager().getThingLogics(
								IInternalWorldEventListener.class)) {
							l.innerWorldAdded(newWorld);
						}
					}
				}
			}
		}
	}

	public void handleThingLogicManagerEvent(ThingLogicManagerEvent evt) {
		switch (evt.getEventType()) {
		case LOGIC_ADDED:
			if (evt.getLogic() instanceof IInternalWorldEventListener) {
				IInternalWorldEventListener l = (IInternalWorldEventListener) evt.getLogic();
				synchronized (worldToWorldThings) {
					for (IBNAWorld world : worldToWorldThings.keySet().toArray(new IBNAWorld[0])) {
						l.innerWorldAdded(world);
					}
				}
			}
			break;
		case LOGIC_REMOVING:
			if (evt.getLogic() instanceof IInternalWorldEventListener) {
				IInternalWorldEventListener l = (IInternalWorldEventListener) evt.getLogic();
				synchronized (worldToWorldThings) {
					for (IBNAWorld world : worldToWorldThings.keySet().toArray(new IBNAWorld[0])) {
						l.innerWorldRemoved(world);
					}
				}
			}
			break;
		case LOGIC_REMOVED:
			break;
		}
	}

	public IHasWorld[] getWorldThings(IBNAWorld world) {
		synchronized (worldToWorldThings) {
			Set<IHasWorld> worldThings = worldToWorldThings.get(world);
			if (worldThings != null) {
				return worldThings.toArray(new IHasWorld[worldThings.size()]);
			}
			return new IHasWorld[0];
		}
	}
}
