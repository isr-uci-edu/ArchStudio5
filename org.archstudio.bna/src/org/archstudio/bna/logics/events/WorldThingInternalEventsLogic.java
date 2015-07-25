package org.archstudio.bna.logics.events;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.logics.AbstractThingLogic;

/**
 * Deprecated. Use {@link ListenToSubWorldEventsLogic} instead.
 */
public class WorldThingInternalEventsLogic extends AbstractThingLogic {
	/**
	 * Deprecated. Use {@link IInternalBNAModelListener} and {@link ListenToSubWorldEventsLogic} instead.
	 */
	public static interface IInternalBNAModelListener
			extends org.archstudio.bna.logics.events.IInternalBNAModelListener {
	}

	public WorldThingInternalEventsLogic(IBNAWorld world) {
		super(world);
		logics.addThingLogic(ListenToSubWorldEventsLogic.class);
	}
}
