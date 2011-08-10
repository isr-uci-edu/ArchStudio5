package org.archstudio.xadlbna.logics.mapping;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.IThingKeyKey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKeyKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.xadlbna.things.IHasObjRef;
import org.archstudio.xarchadt.ObjRef;

import com.google.common.collect.Sets;

public class SynchronizeObjRefAndThingIDLogic extends AbstractThingLogic implements IBNASynchronousModelListener {

	int inUpdateCount = 0;
	ThingValueTrackingLogic valuesLogic = null;
	Set<IThingKeyKey<?, IThingKey<Object>, ObjRef>> objRef2Keys = Sets.newHashSet();
	Set<IThingKey<Object>> thingIDKeys = Sets.newHashSet();

	public IThingKey<ObjRef> syncObjRefKeyToThingIDKey(IThingRefKey<?> thingRefKey) {
		return getObjRef2Key(thingRefKey);
	}

	private IThingKey<ObjRef> getObjRef2Key(IThingKey<Object> thingIDKey) {
		thingIDKeys.add(thingIDKey);
		IThingKeyKey<?, IThingKey<Object>, ObjRef> objRefKey = ThingKeyKey.create("objRef", thingIDKey);
		objRef2Keys.add(objRefKey);
		return objRefKey;
	}

	@Override
	protected void init() {
		super.init();
		valuesLogic = addThingLogic(ThingValueTrackingLogic.class);
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChangedSync(BNAModelEvent<ET, EK, EV> evt) {
		if (inUpdateCount > 0) {
			return;
		}
		inUpdateCount++;
		try {
			switch (evt.getEventType()) {
			case THING_ADDED: {
				IThing t = evt.getTargetThing();
				ObjRef objRef = t.get(IHasObjRef.OBJREF_KEY);
				if (objRef != null) {
					setThingIdForObjRef2(objRef, t.getID());
				}
				for (IThingKeyKey<?, IThingKey<Object>, ObjRef> objRef2Key : objRef2Keys) {
					ObjRef objRef2 = t.get(objRef2Key);
					if (objRef2 != null) {
						t.set(objRef2Key.getKey(), firstOrNull(valuesLogic.getThingIDs(IHasObjRef.OBJREF_KEY, objRef2)));
					}
				}
				break;
			}
			case THING_CHANGED: {
				ThingEvent<ET, EK, EV> te = evt.getThingEvent();
				EK p = te.getPropertyName();
				if (thingIDKeys.contains(p)) {
					@SuppressWarnings("unchecked")
					IThingKey<Object> idKey = (IThingKey<Object>) p;
					IThing thingWithReference = evt.getTargetThing();
					IThing referencedThing = evt.getSource().getThing(thingWithReference.get(idKey));
					ObjRef objRef = referencedThing != null ? referencedThing.get(IHasObjRef.OBJREF_KEY) : null;
					thingWithReference.set(getObjRef2Key(idKey), objRef);
				}
				else if (IHasObjRef.OBJREF_KEY.equals(p)) {
					IThing thingWithObjRef = evt.getTargetThing();
					ObjRef objRef = thingWithObjRef.get(IHasObjRef.OBJREF_KEY);
					setThingIdForObjRef2(objRef, thingWithObjRef.getID());
				}
				else if (objRef2Keys.contains(p)) {
					IThing thingWithObjRef2 = evt.getTargetThing();
					@SuppressWarnings("unchecked")
					IThingKeyKey<?, IThingKey<Object>, ObjRef> objRef2Key = (IThingKeyKey<?, IThingKey<Object>, ObjRef>) p;
					ObjRef objRef2 = thingWithObjRef2.get(objRef2Key);
					thingWithObjRef2.set(objRef2Key.getKey(),
							firstOrNull(valuesLogic.getThingIDs(IHasObjRef.OBJREF_KEY, objRef2)));
				}
				break;
			}
			case THING_REMOVING: {
				IThing t = evt.getTargetThing();
				ObjRef objRef = t.get(IHasObjRef.OBJREF_KEY);
				if (objRef != null) {
					setThingIdForObjRef2(objRef, null);
				}
				break;
			}
			}
		}
		finally {
			inUpdateCount--;
		}
	}

	private void setThingIdForObjRef2(ObjRef objRef, Object thingId) {
		for (IThingKeyKey<?, IThingKey<Object>, ObjRef> objRef2Key : objRef2Keys) {
			for (IThing thingWithObjRef2 : getBNAModel().getThings(valuesLogic.getThingIDs(objRef2Key, objRef))) {
				thingWithObjRef2.set(objRef2Key.getKey(), thingId);
			}
		}
	}
}
