package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasRoundedCorners;
import org.eclipse.draw2d.geometry.Point;

public class StickAnchorPointLogic extends AbstractStickyLogic<IThing, IHasMutableAnchorPoint, StickyMode> {

	public StickAnchorPointLogic(IThingLogicManager tlm) {
		super(IThing.class, IHasMutableAnchorPoint.class, tlm);
	}

	public void stick(IHasBoundingBox stickToThing, StickyMode stickyMode, IHasMutableAnchorPoint... anchoredThings) {
		checkNotNull(stickToThing);
		checkNotNull(stickyMode);

		setPropagate(stickToThing, IHasBoundingBox.BOUNDING_BOX_KEY, IHasAnchorPoint.ANCHOR_POINT_KEY, stickyMode,
				anchoredThings);
		setPropagate(stickToThing, IHasRoundedCorners.CORNER_SIZE_KEY, IHasAnchorPoint.ANCHOR_POINT_KEY, stickyMode,
				anchoredThings);
	}

	public void stick(IHasAnchorPoint stickToThing, StickyMode stickyMode, IHasMutableAnchorPoint... anchoredThings) {
		checkNotNull(stickToThing);
		checkNotNull(stickyMode);

		setPropagate(stickToThing, IHasAnchorPoint.ANCHOR_POINT_KEY, IHasAnchorPoint.ANCHOR_POINT_KEY, stickyMode,
				anchoredThings);
	}

	@Override
	protected StickyMode getStickyMode(StickyMode data) {
		return data;
	}

	@Override
	protected Point getCurrentPoint(IBNAModel model, IThing fromThing, IThingKey<?> fromKey,
			ThingEvent<IThing, ?, ?> fromThingEvent, StickyMode data, IHasMutableAnchorPoint toThing,
			IThingKey<?> toKey, ThingEvent<IHasMutableAnchorPoint, ?, ?> toThingEvent) {
		return toThing.getAnchorPoint();
	}

	@Override
	protected void setCurrentPoint(IBNAModel model, IThing fromThing, IThingKey<?> fromKey,
			ThingEvent<IThing, ?, ?> fromThingEvent, StickyMode data, IHasMutableAnchorPoint toThing,
			IThingKey<?> toKey, ThingEvent<IHasMutableAnchorPoint, ?, ?> toThingEvent, Point stuckPoint) {
		toThing.setAnchorPoint(stuckPoint);
	}
}
