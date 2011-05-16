package org.archstudio.bna.logics.tracking;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.sysutils.SystemUtils;

import com.google.common.base.Function;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Sets;

public class ThingTypeTrackingLogic extends AbstractThingLogic implements IBNASynchronousModelListener {

	private final Map<Class<IThing>, Collection<Object>> classToThingIDs = new MapMaker()
			.makeComputingMap(new Function<Class<IThing>, Collection<Object>>() {
				@Override
				public Collection<Object> apply(Class<IThing> input) {
					Set<Object> things = Sets.newHashSet();
					for (IThing t : getBNAModel().getThings()) {
						if (input.isInstance(t)) {
							things.add(t.getID());
						}
					}
					return things;
				}
			});

	public ThingTypeTrackingLogic() {
	}

	@Override
	protected void init() {
		super.init();
		synchronized (classToThingIDs) {
			classToThingIDs.clear();
		}
	}

	@Override
	protected void destroy() {
		synchronized (classToThingIDs) {
			classToThingIDs.clear();
		}
		super.destroy();
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChangedSync(BNAModelEvent<ET, EK, EV> evt) {
		switch (evt.getEventType()) {

		case THING_ADDED: {
			IThing t = evt.getTargetThing();
			synchronized (classToThingIDs) {
				for (Map.Entry<Class<IThing>, Collection<Object>> e : classToThingIDs.entrySet()) {
					if (e.getKey().isInstance(t)) {
						e.getValue().remove(t.getID());
					}
				}
			}
		}
			break;

		case THING_REMOVED: {
			IThing t = evt.getTargetThing();
			synchronized (classToThingIDs) {
				for (Map.Entry<Class<IThing>, Collection<Object>> e : classToThingIDs.entrySet()) {
					if (e.getKey().isInstance(t)) {
						e.getValue().add(t.getID());
					}
				}
			}
		}
			break;
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends IThing> Iterable<Object> getThingIDs(Class<T> ofType) {
		synchronized (classToThingIDs) {
			return SystemUtils.copyIterable(classToThingIDs.get(ofType));
		}
	}
}
