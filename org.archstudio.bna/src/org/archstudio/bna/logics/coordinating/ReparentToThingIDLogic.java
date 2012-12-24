package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.archstudio.bna.logics.AbstractThingLogic;

public class ReparentToThingIDLogic extends AbstractThingLogic implements IBNAModelListener {

	public static final IThingRefKey<IThing> REPARENT_TO_THING_KEY = ThingRefKey.create(ReparentToThingIDLogic.class);

	public IThingRefKey<IThing> getReparentToThingIDKey() {
		return REPARENT_TO_THING_KEY;
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_ADDED: {
			reparentThing(evt.getSource(), evt.getTargetThing());
			break;
		}
		case THING_CHANGED: {
			ThingEvent te = evt.getThingEvent();
			if (REPARENT_TO_THING_KEY.equals(te.getPropertyName())) {
				reparentThing(evt.getSource(), te.getTargetThing());
			}
			break;
		}
		default:
			// do nothing
		}
	}

	private void reparentThing(IBNAModel model, IThing childThing) {
		IThing parentThing = REPARENT_TO_THING_KEY.get(childThing, model);
		if (parentThing != null) {
			model.reparent(parentThing, childThing);
		}
	}
}
