package org.archstudio.bna;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

class DefaultThingLogicManager implements IThingLogicManager {
	protected IBNAWorld bnaWorld = null;

	protected List<IThingLogic> allThingLogicsList = new CopyOnWriteArrayList<IThingLogic>();
	
	// Maps an interface class to a list of logics that implement that interface class.
	// Unfortunately there is no way to fully express this relationship with generics, so
	// we have to do some uncheckable casts.
	protected Map<Class<?>, List<?>> eventThingLogicLists = new HashMap<Class<?>, List<?>>();

	public DefaultThingLogicManager(IBNAWorld bnaWorld) {
		this.bnaWorld = bnaWorld;
	}

	@SuppressWarnings("unchecked")
    private synchronized <T extends IThingLogic> void updateThingLogics(T tl, boolean isAdding) {
		//Update the all-logics list/array
		if (isAdding) {
			tl.setBNAWorld(bnaWorld);
			allThingLogicsList.add(tl);
		}
		else {
			allThingLogicsList.remove(tl);
			tl.setBNAWorld(null);
		}

		//Update the event-specific lists/arrays
		for (Map.Entry<Class<?>, List<?>> entry : eventThingLogicLists.entrySet()) {
			Class<?> key = entry.getKey();
			if (key.isAssignableFrom(tl.getClass())) {
				List<?> thingLogics = entry.getValue();
				if (isAdding) {
					((List<T>)thingLogics).add(tl);
				}
				else {
					thingLogics.remove(tl);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private synchronized <T> List<T> updateThingLogics(Class<T> implementingInterface) {
		List<T> thingLogicList = (List<T>)eventThingLogicLists.get(implementingInterface);
		if (thingLogicList == null) {
			eventThingLogicLists.put(implementingInterface, thingLogicList = new CopyOnWriteArrayList<T>());
		}
		for (IThingLogic tl : allThingLogicsList) {
			if (implementingInterface.isAssignableFrom(tl.getClass())) {
				thingLogicList.add((T)tl);
			}
		}
		
		return thingLogicList;
	}

	public synchronized void addThingLogic(IThingLogic tl) {
		updateThingLogics(tl, true);
	}

	public synchronized void removeThingLogic(IThingLogic tl) {
		updateThingLogics(tl, false);
	}

	public List<IThingLogic> getAllThingLogics() {
		return Collections.unmodifiableList(allThingLogicsList);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getThingLogics(Class<T> implementingInterface) {
		List<T> thingLogicList = (List<T>)eventThingLogicLists.get(implementingInterface);
		if (thingLogicList == null) {
			thingLogicList = updateThingLogics(implementingInterface);
		}
		return Collections.unmodifiableList(thingLogicList);
	}

	public void destroy() {
		for (IThingLogic tl : allThingLogicsList) {
			removeThingLogic(tl);
		}
	}
}
