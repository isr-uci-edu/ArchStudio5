package org.archstudio.bna.logics.tracking;

import static org.archstudio.sysutils.SystemUtils.filter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IPrivilegedBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.AbstractThingLogic;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class ThingTypeTrackingLogic extends AbstractThingLogic implements IPrivilegedBNAModelListener {

	private final LoadingCache<Class<? extends IThing>, Collection<Object>> classToThingIDsCache = CacheBuilder
			.newBuilder().build(new CacheLoader<Class<? extends IThing>, Collection<Object>>() {

				@Override
				public Collection<Object> load(Class<? extends IThing> key) throws Exception {
					Set<Object> things = Sets.newHashSet();
					for (IThing t : model.getAllThings()) {
						if (key.isInstance(t)) {
							things.add(t.getID());
						}
					}
					return things;
				}
			});

	public ThingTypeTrackingLogic(IBNAWorld world) {
		super(world);
	}

	@Override
	synchronized public void dispose() {
		classToThingIDsCache.invalidateAll();
		super.dispose();
	}

	@Override
	synchronized public void privilegedBNAModelChanged(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_ADDED: {
			IThing t = evt.getTargetThing();
			for (Map.Entry<Class<? extends IThing>, Collection<Object>> e : classToThingIDsCache.asMap().entrySet()) {
				if (e.getKey().isInstance(t)) {
					e.getValue().add(t.getID());
				}
			}
		}
			break;

		case THING_REMOVED: {
			IThing t = evt.getTargetThing();
			for (Map.Entry<Class<? extends IThing>, Collection<Object>> e : classToThingIDsCache.asMap().entrySet()) {
				if (e.getKey().isInstance(t)) {
					e.getValue().remove(t.getID());
				}
			}
		}
			break;
		default:
			// do nothing
		}
	}

	synchronized public List<Object> getThingIDs(Class<? extends IThing> ofType) {
		return Lists.newArrayList(classToThingIDsCache.getUnchecked(ofType));
	}

	synchronized public <T extends IThing> List<T> getThings(Class<T> ofType) {
		return filter(model.getThingsByID(classToThingIDsCache.getUnchecked(ofType)), ofType);
	}
}
