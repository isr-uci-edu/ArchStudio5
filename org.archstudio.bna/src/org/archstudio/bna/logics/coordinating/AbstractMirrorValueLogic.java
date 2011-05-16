package org.archstudio.bna.logics.coordinating;

import static org.archstudio.sysutils.SystemUtils.castOrNull;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.IThingRefKeyKey;
import org.archstudio.bna.logics.tracking.ReferenceTrackingLogic;

public class AbstractMirrorValueLogic<S extends IThing, T extends IThing> extends AbstractMaintainReferencesLogic<S, T> {

	public AbstractMirrorValueLogic(Class<S> sourceThingClass, Class<T> targetThingClass, ReferenceTrackingLogic rtl) {
		super(sourceThingClass, targetThingClass, null, rtl);
	}

	@Override
	protected boolean isSourceThing(IBNAModel sourceModel, S sourceThing,
			org.archstudio.bna.ThingEvent<S, ?, ?> sourceThingEvent) {
		return true;
	};

	@Override
	protected void maintain(IBNAModel sourceModel, S sourceThing, ThingEvent<S, ?, ?> sourceThingEvent,
			IThingRefKey<S> thingRefKey, T targetThing, ThingEvent<T, ?, ?> targetThingEvent) {
		@SuppressWarnings("unchecked")
		IThingRefKeyKey<S, ?, IThingKey<Object>> thingRefKeyKey = castOrNull(thingRefKey, IThingRefKeyKey.class);
		if (thingRefKeyKey != null) {
			IThingKey<Object> key = thingRefKeyKey.getKey();
			targetThing.set(key, sourceThing.get(key));
		}
	}
}
