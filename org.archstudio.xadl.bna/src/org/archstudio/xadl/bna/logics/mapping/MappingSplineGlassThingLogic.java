package org.archstudio.xadl.bna.logics.mapping;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMidpoints;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.coordinating.IInternalBNAModelListener;
import org.archstudio.bna.logics.coordinating.WorldThingInternalEventsLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.xadl.bna.things.IHasObjRef;
import org.archstudio.xadl.bna.things.MappingSplineGlassThing;
import org.archstudio.xarchadt.ObjRef;

public class MappingSplineGlassThingLogic extends AbstractThingLogic implements IBNAModelListener,
		IInternalBNAModelListener {

	ThingValueTrackingLogic trackingLogic = null;

	public MappingSplineGlassThingLogic() {
	}

	@Override
	protected void init() {
		super.init();
		trackingLogic = addThingLogic(ThingValueTrackingLogic.class);
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChanged(BNAModelEvent<ET, EK, EV> evt) {
		ThingEvent<ET, EK, EV> thingEvent = evt.getThingEvent();
		if (thingEvent != null) {
			IThing thing = evt.getTargetThing();
			IThingKey<?> key = thingEvent.getPropertyName();

			// if the sticky information of the MappingSplineGlassThing changes, update it
			if (thing instanceof MappingSplineGlassThing) {
				if (MappingSplineGlassThing.WORLD_THING_OBJREF.equals(key)
						|| MappingSplineGlassThing.INTERNAL_THING_OBJREF.equals(key)
						|| IHasMidpoints.MIDPOINTS_KEY.equals(key) || IHasEndpoints.ENDPOINT_2_KEY.equals(key)) {
					((MappingSplineGlassThing) thing).setNeedsUpdate(true);
				}
			}

			// if a world has changed, update each corresponding MappingSplineGlassThing
			else if (thing instanceof IHasWorld) {
				ObjRef worldRef = thing.get(IHasObjRef.OBJREF_KEY);
				if (worldRef != null) {
					if (!WorldThingInternalEventsLogic.INTERNAL_MODEL_CHANGE_TICKER.equals(key)) {
						for (IThing mappingThing : evt.getSource().getThingsByID(
								trackingLogic.getThingIDs(MappingSplineGlassThing.WORLD_THING_OBJREF, worldRef))) {
							if (mappingThing instanceof MappingSplineGlassThing) {
								((MappingSplineGlassThing) mappingThing).setNeedsUpdate(true);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void internalBNAModelChanged(IHasWorld src,
			BNAModelEvent<ET, EK, EV> evt) {

		// if a thing in the world changed, update each corresponding MappingSplineGlassThing
		ObjRef worldRef = src.get(IHasObjRef.OBJREF_KEY);
		if (worldRef != null) {
			ThingEvent<ET, EK, EV> thingEvent = evt.getThingEvent();
			if (thingEvent != null) {
				IThing thing = evt.getTargetThing();
				ObjRef thingRef = thing.get(IHasObjRef.OBJREF_KEY);
				if (thingRef != null) {
					for (IThing mappingThing : getBNAModel().getThingsByID(
							trackingLogic.getThingIDs(MappingSplineGlassThing.WORLD_THING_OBJREF, worldRef,
									MappingSplineGlassThing.INTERNAL_THING_OBJREF, thingRef))) {
						if (mappingThing instanceof MappingSplineGlassThing) {
							((MappingSplineGlassThing) mappingThing).setNeedsUpdate(true);
						}
					}
				}
			}
		}
	}

}
