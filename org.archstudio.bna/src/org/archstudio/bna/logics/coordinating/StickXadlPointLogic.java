package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.logics.coordinating.StickXadlPointLogic.StickXadlPointLogicData;
import org.archstudio.bna.things.glass.SplineGlassThing;

public class StickXadlPointLogic extends
		AbstractPropagateValueLogic<SplineGlassThing, SplineGlassThing, StickXadlPointLogicData> {

	StickPointsLogic spl = null;

	static class StickXadlPointLogicData {
	}

	public StickXadlPointLogic() {
		super(SplineGlassThing.class, SplineGlassThing.class);
	}

	@Override
	protected void init() {
		super.init();
		spl = getBNAWorld().getThingLogicManager().addThingLogic(StickPointsLogic.class);
	}

	@Override
	protected void doPropagation(IBNAModel model, SplineGlassThing fromThing, IThingKey<?> fromKey,
			ThingEvent<SplineGlassThing, ?, ?> fromThingEvent, StickXadlPointLogicData data, SplineGlassThing toThing,
			IThingKey<?> toKey, ThingEvent<SplineGlassThing, ?, ?> toThingEvent) {

	}
}
