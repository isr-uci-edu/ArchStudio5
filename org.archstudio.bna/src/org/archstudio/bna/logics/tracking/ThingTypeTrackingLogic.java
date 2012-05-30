package org.archstudio.bna.logics.tracking;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class ThingTypeTrackingLogic extends AbstractThingLogic implements IBNASynchronousModelListener {

	private final LoadingCache<Class<? extends IThing>, Collection<Object>> classToThingIDsCache = CacheBuilder.newBuilder()
			.build(new CacheLoader<Class<? extends IThing>, Collection<Object>>() {

				@Override
				public Collection<Object> load(Class<? extends IThing> key) throws Exception {
					Set<Object> things = Sets.newHashSet();
					for (IThing t : getBNAModel().getAllThings()) {
						if (key.isInstance(t)) {
							things.add(t.getID());
						}
					}
					return things;
				}
			});

	public ThingTypeTrackingLogic() {
	}

	@Override
	protected void destroy() {
		classToThingIDsCache.invalidateAll();
		classToThingIDsCache.cleanUp();
		super.destroy();
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChangedSync(BNAModelEvent<ET, EK, EV> evt) {
		switch (evt.getEventType()) {
		case THING_ADDED: {
			IThing t = evt.getTargetThing();
			synchronized (classToThingIDsCache) {
				for (Map.Entry<Class<? extends IThing>, Collection<Object>> e : classToThingIDsCache.asMap().entrySet()) {
					if (e.getKey().isInstance(t)) {
						e.getValue().add(t.getID());
					}
				}
			}
		}
			break;

		case THING_REMOVED: {
			IThing t = evt.getTargetThing();
			synchronized (classToThingIDsCache) {
				for (Map.Entry<Class<? extends IThing>, Collection<Object>> e : classToThingIDsCache.asMap().entrySet()) {
					if (e.getKey().isInstance(t)) {
						e.getValue().remove(t.getID());
					}
				}
			}
		}
			break;
		}
	}

	public <T extends IThing> Iterable<Object> getThingIDs(Class<T> ofType) {
		synchronized (classToThingIDsCache) {
			return Lists.newArrayList(classToThingIDsCache.getUnchecked(ofType));
		}
	}

	public <T extends IThing> Iterable<T> getThings(IBNAModel model, Class<T> ofType) {
		synchronized (classToThingIDsCache) {
			return Iterables.filter(model.getThingsByID(classToThingIDsCache.getUnchecked(ofType)), ofType);
		}
	}
}
