package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IIsSticky;
import org.eclipse.draw2d.geometry.Point;

public class StickAnchorPointLogic extends AbstractStickyLogic<IIsSticky, IHasMutableAnchorPoint, StickyMode> {

	public StickAnchorPointLogic(IThingLogicManager tlm) {
		super(IIsSticky.class, IHasMutableAnchorPoint.class, tlm);
	}

	public void stick(IIsSticky stickToThing, StickyMode stickyMode, IHasMutableAnchorPoint... pointsThings) {
		checkNotNull(stickToThing);
		checkNotNull(stickyMode);

		for (IThingKey<?> key : stickToThing.getStickyModifyingKeys()) {
			setPropagate(stickToThing, key, IHasAnchorPoint.ANCHOR_POINT_KEY, stickyMode, pointsThings);
		}
	}

	@Override
	protected StickyMode getStickyMode(StickyMode data) {
		return data;
	}

	@Override
	protected Point getNearPoint(IBNAModel model, IIsSticky fromThing, IThingKey<?> fromKey,
			ThingEvent<IIsSticky, ?, ?> fromThingEvent, StickyMode data, IHasMutableAnchorPoint toThing,
			IThingKey<?> toKey, ThingEvent<IHasMutableAnchorPoint, ?, ?> toThingEvent) {
		return toThing.getAnchorPoint();
	}

	@Override
	protected void setStuckPoint(IBNAModel model, IIsSticky fromThing, IThingKey<?> fromKey,
			ThingEvent<IIsSticky, ?, ?> fromThingEvent, StickyMode data, IHasMutableAnchorPoint toThing,
			IThingKey<?> toKey, ThingEvent<IHasMutableAnchorPoint, ?, ?> toThingEvent, Point stuckPoint) {
		toThing.setAnchorPoint(stuckPoint);
	}
}
