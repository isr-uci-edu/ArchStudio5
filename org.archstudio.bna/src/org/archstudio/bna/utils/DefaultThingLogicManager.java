package org.archstudio.bna.utils;

import static com.google.common.base.Preconditions.checkArgument;
import static org.archstudio.sysutils.SystemUtils.newCopyOnWriteArrayList;

import java.lang.reflect.Constructor;
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

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Maps;

public class DefaultThingLogicManager implements IThingLogicManager {

	protected static final boolean DEBUG = false;
	protected final Map<Object, AtomicLong> logicStats = !DEBUG ? null : new MapMaker().weakKeys().makeComputingMap(
			new Function<Object, AtomicLong>() {
				@Override
				public AtomicLong apply(Object input) {
					return new AtomicLong();
				}
			});

	protected final IBNAWorld bnaWorld;

	public DefaultThingLogicManager(IBNAWorld bnaWorld) {
		this.bnaWorld = bnaWorld;
	}

	CopyOnWriteArrayList<IThingLogicManagerListener> listeners = newCopyOnWriteArrayList();
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

	@SuppressWarnings("unchecked")
	@Override
	public synchronized <L extends IThingLogic> L addThingLogic(Class<L> tlClass) {
		try {
			IThingLogic tl = typedLogics.get(tlClass);
			if (tl != null) {
				return (L) tl;
			}
			for (Constructor<?> c : tlClass.getConstructors()) {
				Class<?>[] paramTypes = c.getParameterTypes();
				if (paramTypes.length == 1 && IThingLogicManager.class.isAssignableFrom(paramTypes[0])) {
					return addThingLogic((L) c.newInstance(this));
				}
			}
			for (Constructor<?> c : tlClass.getConstructors()) {
				Class<?>[] paramTypes = c.getParameterTypes();
				if (paramTypes.length == 0) {
					return addThingLogic((L) c.newInstance());
				}
			}
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Unable to instantiate logic: " + tlClass, e);
		}
		throw new IllegalArgumentException("Unable to instantiate logic: " + tlClass);
	}

	@Override
	public synchronized <L extends IThingLogic> L addThingLogic(L tl) {
		long time;
		if (DEBUG) {
			time = System.nanoTime();
		}
		tl.setBNAWorld(bnaWorld);
		checkArgument(logics.add(tl), "Logic was already added: %s", tl);
		typedLogics.put(tl.getClass(), tl);
		if (DEBUG) {
			time = System.nanoTime() - time;
			logicStats.get(tl).addAndGet(time);
		}
		fireThingLogicManagerEvent(EventType.LOGIC_ADDED, tl);

		return tl;
	}

	@Override
	public synchronized void removeThingLogic(IThingLogic tl) {
		fireThingLogicManagerEvent(EventType.LOGIC_REMOVING, tl);
		long time;
		if (DEBUG) {
			time = System.nanoTime();
		}
		logics.remove(tl);
		tl.setBNAWorld(null);
		typedLogics.remove(tl.getClass());
		if (DEBUG) {
			time = System.nanoTime() - time;
			logicStats.get(tl).addAndGet(time);
		}
		fireThingLogicManagerEvent(EventType.LOGIC_REMOVED, tl);
	}

	@Override
	public Iterable<IThingLogic> getAllThingLogics() {
		return Lists.newArrayList(logics);
	}

	@Override
	public <LT> Iterable<LT> getThingLogics(Class<LT> implementingInterface) {
		return Iterables.filter(logics, implementingInterface);
	}

	@Override
	public void destroy() {
		// perform remove in reverse order since latter logics often depend on former logics
		for (IThingLogic logic : Lists.newArrayList(Lists.reverse(logics))) {
			removeThingLogic(logic);
		}
	}
}
