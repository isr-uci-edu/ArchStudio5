package org.archstudio.xadl.bna.logics.mapping;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.IThingMetakey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingMetakey;
import org.archstudio.bna.logics.AbstractKeyCoordinatingThingLogic;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xarchadt.ObjRef;

import com.google.common.collect.Sets;

public class SynchronizeThingIDAndObjRefLogic extends AbstractKeyCoordinatingThingLogic implements IBNAModelListener {

	private static final String OBJREF_KEY_NAME = ".objRef";
	private final Set<IThingMetakey<?, IThingRefKey<?>, ObjRef>> objRefMetakeys = Sets.newHashSet();

	public SynchronizeThingIDAndObjRefLogic(IBNAWorld world) {
		super(world, true);
		track(IHasObjRef.OBJREF_KEY);
	}

	synchronized public IThingMetakey<?, IThingRefKey<?>, ObjRef> syncObjRefKeyToThingIDKey(IThingRefKey<?> thingRefKey) {
		track(thingRefKey);
		IThingMetakey<?, IThingRefKey<?>, ObjRef> objRefMetakey = _syncObjRefKeyToThingIDKey(thingRefKey);
		objRefMetakeys.add(objRefMetakey);
		track(objRefMetakey);
		return objRefMetakey;
	}

	private IThingMetakey<?, IThingRefKey<?>, ObjRef> _syncObjRefKeyToThingIDKey(IThingRefKey<?> thingRefKey) {
		return ThingMetakey.<String, IThingRefKey<?>, ObjRef> create(OBJREF_KEY_NAME, thingRefKey);
	}

	@Override
	protected void update(IThing thing, IThingKey<?> key) {
		if (IHasObjRef.OBJREF_KEY.equals(key)) {
			ObjRef objRef = thing.get(IHasObjRef.OBJREF_KEY);
			if (objRef != null) {
				for (IThingMetakey<?, IThingRefKey<?>, ObjRef> objRefKey : objRefMetakeys) {
					for (IThing thingWithObjRef : valueLogic.getThings(objRefKey, objRef)) {
						thingWithObjRef.set(objRefKey.getKey(), thing.getID());
					}
				}
			}
		}
		else if (key instanceof IThingMetakey && OBJREF_KEY_NAME.equals(((IThingMetakey<?, ?, ?>) key).getName())) {
			@SuppressWarnings("unchecked")
			IThingMetakey<?, IThingRefKey<?>, ObjRef> objRefKey = (IThingMetakey<?, IThingRefKey<?>, ObjRef>) key;
			ObjRef objRef = thing.get(objRefKey);
			if (objRef != null) {
				thing.set(objRefKey.getKey(), firstOrNull(valueLogic.getThingIDs(IHasObjRef.OBJREF_KEY, objRef)));
			}
			else {
				thing.set(objRefKey.getKey(), null);
			}
		}
		else if (key instanceof IThingRefKey) {
			IThingRefKey<?> refKey = (IThingRefKey<?>) key;
			IThingMetakey<?, IThingRefKey<?>, ObjRef> objRefKey = _syncObjRefKeyToThingIDKey(refKey);
			if (thing.has(objRefKey)) {
				IThing referencedThing = refKey.get(thing, model);
				ObjRef objRef = referencedThing != null ? referencedThing.get(IHasObjRef.OBJREF_KEY) : null;
				thing.set(objRefKey, objRef);
			}
		}
	}

	@Override
	synchronized public void bnaModelChanged(BNAModelEvent evt) {
		super.bnaModelChanged(evt);
		if (evt.getEventType() == EventType.THING_REMOVED) {
			IThing thing = evt.getTargetThing();
			ObjRef objRef = thing.get(IHasObjRef.OBJREF_KEY);
			if (objRef != null) {
				for (IThingMetakey<?, IThingRefKey<?>, ObjRef> objRefKey : objRefMetakeys) {
					for (IThing thingWithObjRef : valueLogic.getThings(objRefKey, objRef)) {
						thingWithObjRef.set(objRefKey.getKey(), null);
					}
				}
			}
		}
	}
}
