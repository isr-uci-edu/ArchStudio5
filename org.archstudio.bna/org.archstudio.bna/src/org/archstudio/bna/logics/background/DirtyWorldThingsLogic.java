package org.archstudio.bna.logics.background;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.events.IInternalWorldEventListener;
import org.archstudio.bna.logics.events.InternalWorldEventsLogic;

public class DirtyWorldThingsLogic extends AbstractThingLogic implements IInternalWorldEventListener {

	protected static int InternalModelChangeCount = 0;
	protected final InternalWorldEventsLogic iwel;

	public DirtyWorldThingsLogic(InternalWorldEventsLogic iwel) {
		this.iwel = iwel;
	}

	private class BumpOuterViewCounter extends AbstractThingLogic implements IBNASynchronousModelListener {

		public void bnaModelChangedSync(BNAModelEvent evt) {
			if (evt.getEventType().isModelModifyingEvent()) {
				for (IHasWorld worldThing : iwel.getWorldThings(getBNAWorld())) {
					worldThing.setProperty("InternalModelChangedTicker", ++InternalModelChangeCount);
				}
			}
		}
	}

	public void innerWorldAdded(IBNAWorld world) {
		world.getThingLogicManager().addThingLogic(new BumpOuterViewCounter());
	}

	public void innerWorldRemoved(IBNAWorld world) {
	}
}
