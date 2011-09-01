package org.archstudio.xadlbna.logics.mapping;

import static org.archstudio.sysutils.SystemUtils.castOrNull;
import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.keys.IThingKeyKey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.IThingRefKeyKey;
import org.archstudio.bna.keys.ThingKeyKey;
import org.archstudio.bna.keys.ThingRefKeyKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.coordinating.IInternalBNAModelListener;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.xadlbna.things.IHasObjRef;
import org.archstudio.xarchadt.ObjRef;

import com.google.common.collect.Sets;

public class LookupInternalThingIDFromObjRefLogic extends AbstractThingLogic implements IBNASynchronousModelListener,
		IInternalBNAModelListener {

	ThingValueTrackingLogic valuesLogic = null;
	Set<IThingRefKey<?>> idKeys = Sets.newHashSet();
	Set<IThingKey<?>> settingKeys = Sets.newHashSet();

	public IThingKey<ObjRef> getObjRefToThingIDKeyFor(IThingRefKey<?> thingIDKey) {
		idKeys.add(thingIDKey);
		IThingKeyKey<?, ?, ObjRef> objRefKey = _getObjRefKeyFor(thingIDKey);
		settingKeys.add(objRefKey);
		return objRefKey;
	}

	private IThingKeyKey<?, ?, ObjRef> _getObjRefKeyFor(IThingRefKey<?> thingIDKey) {
		return ThingKeyKey.create("objRef", thingIDKey);
	}

	public IThingRefKey<IHasWorld> getWorldThingIDKeyFor(IThingRefKey<?> thingIDKey) {
		idKeys.add(thingIDKey);
		IThingRefKeyKey<IHasWorld, ?, ?> worldThingRefKey = _getWorldThingIDKeyFor(thingIDKey);
		settingKeys.add(worldThingRefKey);
		return worldThingRefKey;
	}

	private IThingRefKeyKey<IHasWorld, ?, ?> _getWorldThingIDKeyFor(IThingRefKey<?> thingIDKey) {
		return ThingRefKeyKey.create("&worldThingID", thingIDKey);
	}

	@Override
	protected void init() {
		super.init();
		valuesLogic = addThingLogic(ThingValueTrackingLogic.class);
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChangedSync(BNAModelEvent<ET, EK, EV> evt) {
		IThing t = evt.getTargetThing();
		switch (evt.getEventType()) {
		case THING_ADDED: {
			for (IThingRefKey<?> idKey : idKeys) {
				setThingID(t, idKey);
			}
			break;
		}
		case THING_CHANGED: {
			IThingKey<?> key = evt.getThingEvent().getPropertyName();
			if (settingKeys.contains(key)) {
				setThingID(t, (IThingRefKey<?>) ((IThingKeyKey<?, ?, ?>) key).getKey());
			}
			break;
		}
		}
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void internalBNAModelChanged(IHasWorld src,
			BNAModelEvent<ET, EK, EV> evt) {
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void internalBNAModelChangedSync(IHasWorld src,
			BNAModelEvent<ET, EK, EV> evt) {
		IThing t = evt.getTargetThing();
		switch (evt.getEventType()) {
		case THING_ADDED: {
			ObjRef objRef = t.get(IHasObjRef.OBJREF_KEY);
			if (objRef != null) {
				setThingID(src, objRef, t);
			}
			break;
		}
		case THING_REMOVING: {
			ObjRef objRef = t.get(IHasObjRef.OBJREF_KEY);
			if (objRef != null) {
				setThingID(src, objRef, null);
			}
			break;
		}
		case THING_CHANGED: {
			ThingEvent<ET, IThingKey<ObjRef>, ObjRef> te = BNAUtils.castOrNull(evt.getThingEvent(),
					IHasObjRef.OBJREF_KEY);
			if (te != null) {
				setThingID(src, te.getOldPropertyValue(), null);
				setThingID(src, te.getNewPropertyValue(), t);
			}
			break;
		}
		}
	}

	private void setThingID(IHasWorld src, ObjRef objRef, IThing t) {
		for (IThingRefKey<?> idKey : idKeys) {
			for (IThing refThing : getBNAModel().getThings(valuesLogic.getThingIDs(//
					_getObjRefKeyFor(idKey), objRef,//
					_getWorldThingIDKeyFor(idKey), src.getID()))) {
				refThing.set(idKey, t != null ? t.getID() : null);
			}
		}
	}

	private void setThingID(IThing t, IThingRefKey<?> idKey) {
		IThingKey<ObjRef> objRefKey = _getObjRefKeyFor(idKey);
		ObjRef objRef = t.get(objRefKey);
		if (objRef != null) {
			IThingRefKey<IHasWorld> worldThingRefKey = _getWorldThingIDKeyFor(idKey);
			Object worldThingID = t.get(worldThingRefKey);
			if (worldThingID != null) {
				IHasWorld worldThing = castOrNull(getBNAModel().getThing(worldThingID), IHasWorld.class);
				if (worldThing != null) {
					IBNAWorld world = worldThing.getWorld();
					if (world != null) {
						ThingValueTrackingLogic innerValueLogic = world.getThingLogicManager().addThingLogic(
								ThingValueTrackingLogic.class);
						t.set(idKey, firstOrNull(innerValueLogic.getThingIDs(IHasObjRef.OBJREF_KEY, objRef)));
						return;
					}
				}
			}
			t.set(idKey, null);
		}
	}
}
