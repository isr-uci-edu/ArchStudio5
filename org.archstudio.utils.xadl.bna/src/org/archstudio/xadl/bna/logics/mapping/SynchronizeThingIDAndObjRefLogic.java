package org.archstudio.xadl.bna.logics.mapping;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.Collections;
import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.IThingMetakey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingMetakey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xarchadt.ObjRef;

import com.google.common.collect.Sets;

public class SynchronizeThingIDAndObjRefLogic extends AbstractThingLogic implements IBNAModelListener {

	ThingValueTrackingLogic valuesLogic = null;
	Set<IThingMetakey<?, IThingKey<Object>, ObjRef>> objRefKeys = Collections
			.<IThingMetakey<?, IThingKey<Object>, ObjRef>> synchronizedSet(Sets
					.<IThingMetakey<?, IThingKey<Object>, ObjRef>> newHashSet());
	Set<IThingKey<Object>> thingIDKeys = Collections.<IThingKey<Object>> synchronizedSet(Sets
			.<IThingKey<Object>> newHashSet());

	public IThingKey<ObjRef> syncObjRefKeyToThingIDKey(IThingRefKey<?> thingRefKey) {
		return getObjRefKey(thingRefKey);
	}

	private IThingKey<ObjRef> getObjRefKey(IThingKey<Object> thingIDKey) {
		thingIDKeys.add(thingIDKey);
		IThingMetakey<?, IThingKey<Object>, ObjRef> objRefKey = ThingMetakey.create(".objRef", thingIDKey);
		objRefKeys.add(objRefKey);
		return objRefKey;
	}

	@Override
	protected void init() {
		super.init();
		valuesLogic = addThingLogic(ThingValueTrackingLogic.class);
	}

	int inUpdateCount = 0;

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
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
					setThingIdForObjRef(objRef, t.getID());
				}
				for (IThingMetakey<?, IThingKey<Object>, ObjRef> objRefKey : objRefKeys) {
					objRef = t.get(objRefKey);
					if (objRef != null) {
						t.set(objRefKey.getKey(), firstOrNull(valuesLogic.getThingIDs(IHasObjRef.OBJREF_KEY, objRef)));
					}
				}
				break;
			}
			case THING_CHANGED: {
				ThingEvent te = evt.getThingEvent();
				IThingKey<?> p = te.getPropertyName();
				if (thingIDKeys.contains(p)) {
					@SuppressWarnings("unchecked")
					IThingKey<Object> idKey = (IThingKey<Object>) p;
					IThing thingWithReference = evt.getTargetThing();
					IThing referencedThing = evt.getSource().getThing(thingWithReference.get(idKey));
					ObjRef objRef = referencedThing != null ? referencedThing.get(IHasObjRef.OBJREF_KEY) : null;
					thingWithReference.set(getObjRefKey(idKey), objRef);
				}
				else if (IHasObjRef.OBJREF_KEY.equals(p)) {
					IThing thingWithObjRef = evt.getTargetThing();
					ObjRef objRef = thingWithObjRef.get(IHasObjRef.OBJREF_KEY);
					setThingIdForObjRef(objRef, thingWithObjRef.getID());
				}
				else if (objRefKeys.contains(p)) {
					IThing thingWithObjRef = evt.getTargetThing();
					@SuppressWarnings("unchecked")
					IThingMetakey<?, IThingKey<Object>, ObjRef> objRefKey = (IThingMetakey<?, IThingKey<Object>, ObjRef>) p;
					ObjRef objRef = thingWithObjRef.get(objRefKey);
					thingWithObjRef.set(objRefKey.getKey(),
							firstOrNull(valuesLogic.getThingIDs(IHasObjRef.OBJREF_KEY, objRef)));
				}
				break;
			}
			case THING_REMOVING: {
				IThing t = evt.getTargetThing();
				ObjRef objRef = t.get(IHasObjRef.OBJREF_KEY);
				if (objRef != null) {
					setThingIdForObjRef(objRef, null);
				}
				break;
			}
			default:
				// do nothing
			}
		}
		finally {
			inUpdateCount--;
		}
	}

	private void setThingIdForObjRef(ObjRef objRef, Object thingId) {
		for (IThingMetakey<?, IThingKey<Object>, ObjRef> objRefKey : objRefKeys) {
			for (IThing thingWithObjRef : getBNAModel().getThingsByID(valuesLogic.getThingIDs(objRefKey, objRef))) {
				thingWithObjRef.set(objRefKey.getKey(), thingId);
			}
		}
	}
}
