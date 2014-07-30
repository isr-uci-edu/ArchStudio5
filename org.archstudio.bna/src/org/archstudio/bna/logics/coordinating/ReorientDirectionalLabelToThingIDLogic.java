package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.archstudio.bna.logics.AbstractKeyCoordinatingThingLogic;
import org.archstudio.bna.things.labels.DirectionalLabelThing;
import org.archstudio.bna.utils.BNAUtils;

public class ReorientDirectionalLabelToThingIDLogic extends AbstractKeyCoordinatingThingLogic implements
		IBNAModelListener {

	private static final IThingRefKey<IHasBoundingBox> REORIENT_TO_THING_ID_KEY = ThingRefKey
			.create(ReorientDirectionalLabelToThingIDLogic.class);

	protected final OrientDirectionalLabelLogic orientLogic;

	public ReorientDirectionalLabelToThingIDLogic(IBNAWorld world) {
		super(world, false);
		orientLogic = logics.addThingLogic(OrientDirectionalLabelLogic.class);
		track(REORIENT_TO_THING_ID_KEY);
	}

	public IThingRefKey<IHasBoundingBox> getReorientToThingKey() {
		BNAUtils.checkLock();

		return REORIENT_TO_THING_ID_KEY;
	}

	@Override
	protected void update(IThing thing, IThingKey<?> key) {
		if (thing instanceof DirectionalLabelThing) {
			IHasBoundingBox rectangleThing = REORIENT_TO_THING_ID_KEY.get(thing, model);
			if (rectangleThing != null) {
				orientLogic.orient(rectangleThing, (DirectionalLabelThing) thing);
			}
			else {
				orientLogic.unorient(thing);
			}
		}
	}
}
