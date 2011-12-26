package org.archstudio.xadlbna.things;

import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.keys.ThingKeyKey;
import org.archstudio.bna.things.glass.SplineGlassThing;
import org.archstudio.xarchadt.ObjRef;

public class MappingSplineGlassThing extends SplineGlassThing {

	public static final IThingKey<ObjRef> WORLD_THING_OBJREF = ThingKeyKey.create(".worldObjRef",
			IHasEndpoints.ENDPOINT_1_KEY);

	public static final IThingKey<ObjRef> INTERNAL_THING_OBJREF = ThingKeyKey.create(".internalThingObjRef",
			IHasEndpoints.ENDPOINT_1_KEY);

	public static final IThingKey<StickyMode> STICKY_MODE = ThingKeyKey.create(".stickyMode",
			IHasEndpoints.ENDPOINT_1_KEY);

	public static final IThingKey<Boolean> NEEDS_UPDATE = ThingKey.create("needsUpdate");

	public MappingSplineGlassThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		set(NEEDS_UPDATE, true);
	}

	public ObjRef getWorldThingObjRef() {
		return get(WORLD_THING_OBJREF);
	}

	public void setWorldThingObjRef(ObjRef worldThingObjRef) {
		set(WORLD_THING_OBJREF, worldThingObjRef);
	}

	public ObjRef getInternalThingObjRef() {
		return get(INTERNAL_THING_OBJREF);
	}

	public void setInternalThingObjRef(ObjRef internalThingObjRef) {
		set(INTERNAL_THING_OBJREF, internalThingObjRef);
	}

	public StickyMode getStickyMode() {
		return get(STICKY_MODE);
	}

	public void setStickyMode(StickyMode stickyMode) {
		set(STICKY_MODE, stickyMode);
	}

	public boolean isNeedsUpdate() {
		return Boolean.TRUE.equals(get(NEEDS_UPDATE));
	}

	public void setNeedsUpdate(boolean needsUpdate) {
		set(NEEDS_UPDATE, needsUpdate);
	}
}
