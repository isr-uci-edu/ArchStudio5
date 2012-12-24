package org.archstudio.xadl.bna.logics.mapping;

import static org.archstudio.sysutils.SystemUtils.castOrNull;
import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasInternalWorldEndpoint;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.keys.IThingMetakey;
import org.archstudio.bna.keys.ThingMetakey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.coordinating.IInternalBNAModelListener;
import org.archstudio.bna.logics.coordinating.WorldThingInternalEventsLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.things.glass.MappingGlassThing;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.graphics.Point;

public class MaintainMappingLogic extends AbstractThingLogic implements IBNAModelListener, IInternalBNAModelListener {

	public static final IThingMetakey<String, IThingKey<Point>, Object> INTERNAL_THING_KEY = ThingMetakey.create(
			".&internalThingID", IHasInternalWorldEndpoint.INTERNAL_ENDPOINT_KEY);
	public static final IThingMetakey<String, IThingKey<Point>, ObjRef> INTERNAL_OBJREF_KEY = ThingMetakey.create(
			".internalObjRef", IHasInternalWorldEndpoint.INTERNAL_ENDPOINT_KEY);

	ThingValueTrackingLogic thingValueTrackingLogic = null;

	public MaintainMappingLogic() {
	}

	protected void init() {
		super.init();
		IThingLogicManager tlm = getBNAWorld().getThingLogicManager();
		this.thingValueTrackingLogic = tlm.addThingLogic(ThingValueTrackingLogic.class);
		// listen to internal world events
		tlm.addThingLogic(WorldThingInternalEventsLogic.class);
	}

	public void bnaModelChanged(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_ADDED: {
			IThing t = evt.getTargetThing();
			if (t instanceof MappingGlassThing) {
				updateThing((MappingGlassThing) t);
			}
		}
			break;
		case THING_CHANGED: {
			IThing t = evt.getTargetThing();
			if (t instanceof MappingGlassThing) {
				if (evt.getThingEvent().getPropertyName().equals(INTERNAL_THING_KEY)
						|| evt.getThingEvent().getPropertyName().equals(INTERNAL_OBJREF_KEY)
						|| evt.getThingEvent().getPropertyName()
								.equals(IHasInternalWorldEndpoint.INTERNAL_ENDPOINT_WORLD_THING_KEY)) {
					updateThing((MappingGlassThing) t);
				}
			}
			break;
		}
		default:
			// do nothing
		}
	}

	public void internalBNAModelChanged(IHasWorld src, BNAModelEvent evt) {

		switch (evt.getEventType()) {
		case THING_ADDED: {
			IThing t = evt.getTargetThing();
			updateObjRef(src, t.getID(), null, t.get(IHasObjRef.OBJREF_KEY));
			updateEndpoint(src, t);
		}
			break;
		case THING_CHANGED: {
			ThingEvent tevt = evt.getThingEvent();
			IThing t = evt.getTargetThing();
			if (evt.getThingEvent().getPropertyName().equals(IHasObjRef.OBJREF_KEY)) {
				updateObjRef(src, t.getID(), (ObjRef) tevt.getOldPropertyValue(), (ObjRef) tevt.getNewPropertyValue());
			}
			updateEndpoint(src, t);
		}
			break;
		case THING_REMOVED: {
			IThing t = evt.getTargetThing();
			updateObjRef(src, t.getID(), t.get(IHasObjRef.OBJREF_KEY), null);
		}
			break;
		default:
			// do nothing
		}

	}

	private void updateThing(MappingGlassThing t) {
		IHasWorld worldThing = castOrNull(getBNAModel().getThing(t.getInternalEndpointWorldThingID()), IHasWorld.class);
		if (worldThing != null) {
			IBNAWorld iWorld = worldThing.getWorld();
			if (iWorld != null) {
				ObjRef objRef = t.get(INTERNAL_OBJREF_KEY);
				if (objRef != null) {
					ThingValueTrackingLogic iThingValueTrackingLogic = iWorld.getThingLogicManager().addThingLogic(
							ThingValueTrackingLogic.class);
					IIsSticky iThing = castOrNull(
							firstOrNull(iWorld.getBNAModel().getThingsByID(
									iThingValueTrackingLogic.getThingIDs(IHasObjRef.OBJREF_KEY, objRef))),
							IIsSticky.class);
					if (iThing != null) {
						t.set(INTERNAL_THING_KEY, iThing.getID());
						t.setInternalEndpoint(iThing.getStickyPointNear(StickyMode.CENTER, new Point(0, 0)));
					}
				}
			}
		}
	}

	protected void updateObjRef(IHasWorld worldThing, Object innerThingId, ObjRef oldObjRef, ObjRef newObjRef) {
		if (oldObjRef != null) {
			for (IThing t : getBNAModel().getThingsByID(
					thingValueTrackingLogic.getThingIDs(INTERNAL_OBJREF_KEY, oldObjRef,
							IHasInternalWorldEndpoint.INTERNAL_ENDPOINT_WORLD_THING_KEY, worldThing.getID()))) {
				t.set(INTERNAL_THING_KEY, null);
			}
		}
		if (newObjRef != null) {
			for (IThing t : getBNAModel().getThingsByID(
					thingValueTrackingLogic.getThingIDs(INTERNAL_OBJREF_KEY, newObjRef,
							IHasInternalWorldEndpoint.INTERNAL_ENDPOINT_WORLD_THING_KEY, worldThing.getID()))) {
				t.set(INTERNAL_THING_KEY, innerThingId);
			}
		}
	}

	private void updateEndpoint(IHasWorld worldThing, IThing changedThing) {
		if (changedThing instanceof IIsSticky) {
			Point endpoint = ((IIsSticky) changedThing).getStickyPointNear(StickyMode.CENTER, new Point(0, 0));
			for (IThing t : getBNAModel().getThingsByID(
					thingValueTrackingLogic.getThingIDs(INTERNAL_THING_KEY, changedThing.getID(),
							IHasInternalWorldEndpoint.INTERNAL_ENDPOINT_WORLD_THING_KEY, worldThing.getID()))) {
				t.set(IHasInternalWorldEndpoint.INTERNAL_ENDPOINT_KEY, endpoint);
			}
		}
	}
}
