package org.archstudio.bna.utils;

import static com.google.common.base.Preconditions.checkArgument;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogic;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.IThingLogicManagerListener;
import org.archstudio.bna.ThingLogicManagerEvent;
import org.archstudio.bna.ThingLogicManagerEvent.EventType;
import org.archstudio.sysutils.FilterableCopyOnWriteArrayList;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class DefaultThingLogicManager implements IThingLogicManager {

	protected static final boolean DEBUG = true;
	protected final LoadingCache<Object, AtomicLong> debugStats = !DEBUG ? null : CacheBuilder.newBuilder().weakKeys()
			.build(new CacheLoader<Object, AtomicLong>() {

				@Override
				public AtomicLong load(Object input) {
					return new AtomicLong();
				}
			});

	protected final IBNAWorld world;

	public DefaultThingLogicManager(IBNAWorld world) {
		this.world = world;
	}

	CopyOnWriteArrayList<IThingLogicManagerListener> listeners = Lists.newCopyOnWriteArrayList();
	FilterableCopyOnWriteArrayList<IThingLogic> logics = FilterableCopyOnWriteArrayList.create();
	Map<Class<?>, IThingLogic> typedLogics = Maps.newHashMap();

	@Override
	public void addThingLogicManagerListener(IThingLogicManagerListener l) {
		listeners.add(l);
	}

	@Override
	public void removeThingLogicManagerListener(IThingLogicManagerListener l) {
		listeners.remove(l);
	}

	protected void fireThingLogicManagerEvent(EventType eventType, IThingLogic tl) {
		if (!listeners.isEmpty()) {
			ThingLogicManagerEvent evt = new ThingLogicManagerEvent(eventType, tl);
			for (IThingLogicManagerListener l : listeners) {
				l.handleThingLogicManagerEvent(evt);
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <L extends IThingLogic> L addThingLogic(Class<L> logicClass) {
		try {
			L logic = (L) typedLogics.get(logicClass);
			if (logic != null) {
				return logic;
			}
			for (Constructor<?> c : logicClass.getConstructors()) {
				Class<?>[] paramTypes = c.getParameterTypes();
				if (paramTypes.length == 1) {
					if (IBNAWorld.class.isAssignableFrom(paramTypes[0])) {
						return addThingLogic((L) c.newInstance(world));
					}
				}
			}
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Unable to instantiate logic: " + logicClass, e);
		}
		throw new IllegalArgumentException("Unable to instantiate logic: " + logicClass);
	}

	@Override
	public synchronized <L extends IThingLogic> L addThingLogic(L l) {
		checkArgument(l.getBNAWorld() == world, "Logic is of a different world: %s", l);
		checkArgument(!logics.contains(l), "Logic was already added: %s", l);
		long time;
		if (DEBUG) {
			time = System.nanoTime();
		}
		l.init();
		logics.add(l);
		typedLogics.put(l.getClass(), l);
		if (DEBUG) {
			time = System.nanoTime() - time;
			debugStats.getUnchecked(l).addAndGet(time);
		}
		fireThingLogicManagerEvent(EventType.LOGIC_ADDED, l);

		return l;
	}

	@Override
	public synchronized void removeThingLogic(IThingLogic tl) {
		fireThingLogicManagerEvent(EventType.LOGIC_REMOVING, tl);
		long time;
		if (DEBUG) {
			time = System.nanoTime();
		}
		logics.remove(tl);
		typedLogics.remove(tl.getClass());
		tl.dispose();
		if (DEBUG) {
			time = System.nanoTime() - time;
			debugStats.getUnchecked(tl).addAndGet(time);
		}
		fireThingLogicManagerEvent(EventType.LOGIC_REMOVED, tl);
	}

	@Override
	public List<IThingLogic> getAllThingLogics() {
		return Lists.newArrayList(logics);
	}

	@Override
	public <T> Iterable<T> getThingLogics(Class<T> ofType) {
		return Iterables.filter(logics, ofType);
	}

	@Override
	public void dispose() {
		// perform removals in reverse order since latter logics often depend on former logics
		for (IThingLogic logic : Lists.newArrayList(Lists.reverse(logics))) {
			removeThingLogic(logic);
		}
	}
}
