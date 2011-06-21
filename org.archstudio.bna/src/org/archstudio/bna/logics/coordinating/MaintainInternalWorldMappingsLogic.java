package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.events.IInternalWorldEventListener;
import org.archstudio.bna.logics.events.InternalWorldEventsLogic;
import org.archstudio.bna.logics.tracking.ReferenceTrackingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.eclipse.draw2d.geometry.Point;

public class MaintainInternalWorldMappingsLogic extends
		AbstractMaintainReferencedThingsLogic<IHasWorld, IHasInternalWorldMapping> implements
		IInternalWorldEventListener {

	protected final ThingValueTrackingLogic tptl;
	protected final InternalWorldEventsLogic iwel;

	public MaintainInternalWorldMappingsLogic(ReferenceTrackingLogic rtl, ThingValueTrackingLogic tptl,
			InternalWorldEventsLogic iwel) {
		super(IHasWorld.class, new ThingKey<?, V>[] {}, IHasInternalWorldMapping.class, new ThingKey<?, V>[] {
				IHasInternalWorldEndpoint.INTERNAL_ENDPOINT_KEY,
				IHasInternalWorldMapping.INTERNAL_ENDPOINT_STUCK_TO_THING_ID_KEY }, rtl,
				IHasInternalWorldEndpoint.INTERNAL_ENDPOINT_WORLD_THING_ID_KEY);
		this.tptl = tptl;
		this.iwel = iwel;
	}

	@Override
	protected void maintain(IBNAModel sourceModel, IHasWorld sourceThing, IHasInternalWorldMapping targetThing,
			ThingEvent thingEvent) {
		IBNAWorld world = sourceThing.getWorld();
		if (world != null) {
			IThing internalThing = world.getBNAModel().getThing(targetThing.getInternalEndpointStuckToThingID());
			if (internalThing != null) {
				targetThing.setInternalEndpoint(StickRelativeMovablesLogic.getStuckPoint(internalThing,
						StickyMode.CENTER, new Point(0, 0)));
			}
		}
	}

	private class UpdateExternalWorldMappings extends AbstractThingLogic implements IBNASynchronousModelListener {

		public void bnaModelChangedSync(BNAModelEvent evt) {
			switch (evt.getEventType()) {
			case THING_ADDED:
			case THING_CHANGED: {
				for (IThing t : tptl.getThings(IHasInternalWorldMapping.INTERNAL_ENDPOINT_STUCK_TO_THING_ID_KEY, evt
						.getTargetThing().getID())) {
					if (t instanceof IHasInternalWorldMapping) {
						IThing worldThing = MaintainInternalWorldMappingsLogic.this.getBNAModel().getThing(
								((IHasInternalWorldMapping) t).getInternalEndpointWorldThingID());
						if (worldThing instanceof IHasWorld) {
							maintain(getBNAModel(), (IHasWorld) worldThing, (IHasInternalWorldMapping) t, null);
						}
					}
				}
			}
				break;
			}
		}
	}

	public void innerWorldAdded(IBNAWorld world) {
		world.getThingLogicManager().addThingLogic(new UpdateExternalWorldMappings());
	}

	public void innerWorldRemoved(IBNAWorld world) {
	}
}
